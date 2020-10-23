package com.bungii.hooks;

import com.bungii.SetupManager;
import com.bungii.api.stepdefinitions.BungiiSteps;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.manager.ReportManager;
import com.bungii.common.utilities.*;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.util.Map;

import static com.bungii.common.manager.DriverManager.getCurrentKey;

public class CucumberHooks {
    private static boolean isFirstTestCase;
    private static LogUtility logger = new LogUtility(CucumberHooks.class);

    static {
        PropertyUtility.loadRunConfigProps();
        String autoHome = CucumberHooks.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("/target/test-classes/", "");// (String) PropertyUtility.getProp("auto.home");
        if (SystemUtils.IS_OS_WINDOWS)
            autoHome = autoHome.substring(0, 1).equals("/") ? autoHome.substring(1) : autoHome;
        FileUtility.autoHome = autoHome;
        String log4jConfPath = "src/main/resources/SystemProperties/log4j.properties";
        PropertyConfigurator.configure(FileUtility.getSuiteResource("", log4jConfPath));
        isFirstTestCase = true;
    }

    protected WebDriver driver;
    public ReportManager reportManager;
    private boolean isTestcaseFailed = false;

    public CucumberHooks() {
        this.reportManager = new ReportManager();
    }

    public synchronized void start(String resultFolder) {
        try {
            this.reportManager.startSuiteFile(resultFolder);
            String device = System.getProperty("DEVICE") == null ? "Windows VM" : System.getProperty("DEVICE");
            logger.detail("********** Initializing Test on Device : "+device.toUpperCase()+" ************");
            SetupManager.getObject().getDriver();
        } catch (Exception e) {
            logger.error("Unable to connect with default appium server. Either VPN is down or Browserstack tunnel is broken");
            e.printStackTrace();
        }


    }

    @Before
    public void beforeTest(Scenario scenario) throws InterruptedException {

        logger.detail("**********************************************************************************");
        String[] rawFeature = scenario.getId().split("features/")[1].split("/");
        String[] rawFeatureName = rawFeature[rawFeature.length - 1].split(":");

        logger.detail("FEATURE : " + rawFeatureName[0]);
        logger.detail("STARTING SCENARIO : " + scenario.getName());
        this.reportManager.startTestCase(scenario.getName(), rawFeatureName[0]);
        SetupManager.getObject().useDriverInstance("ORIGINAL");
            Thread.sleep(2000);
        if (!isFirstTestCase) {
            SetupManager.getObject().restartApp();
        }
    }


    @After
    public void afterTest(Scenario scenario) {
        try {

            //if first test case flag is ste to true then change it to false
            if (isFirstTestCase) isFirstTestCase = false;
            DriverManager.getObject().closeAllDriverInstanceExceptOriginal();
            SetupManager.getObject().useDriverInstance("ORIGINAL");
            this.reportManager.endTestCase(scenario.isFailed());

            if (!scenario.isFailed() || !this.reportManager.isVerificationFailed())
            {
                String Failure = (String) CucumberContextManager.getObject().getScenarioContext("FAILURE");

                if (Failure.equals("TRUE")) {
                    logger.detail("SKIPPED TEST SCENARIO : " + scenario.getName()+" | Skipped Count : "+this.reportManager.skipped());
                }

                else
                    logger.detail("PASSING TEST SCENARIO : " + scenario.getName());
                    CucumberContextManager.getObject().setScenarioContext("FAILURE", "FALSE");
            }
            else if (scenario.isFailed() || this.reportManager.isVerificationFailed()) {
                //if consecutive two case failed then create new instance
                if (isTestcaseFailed)
                    SetupManager.getObject().createNewAppiumInstance("ORIGINAL", "device1");
                try {
                    logger.detail("FAILED TEST SCENARIO : " + scenario.getName());
                    logger.warning("PAGE SOURCE :" + StringUtils.normalizeSpace(DriverManager.getObject().getDriver().getPageSource()));

                } catch (Exception e) {
                }

                if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {
                    new BungiiSteps().recoveryScenario();
                    //new GeneralUtility().resetDriverAppsStateToInital();
                  new GeneralUtility().recoverScenario();
                    //new GeneralUtility().hideNotifications();
                } else if (PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID")) {
                    new GeneralUtility().hideNotifications();
                    new BungiiSteps().recoveryScenario();
                    new com.bungii.android.utilityfunctions.GeneralUtility().recoverScenario();
                    SetupManager.getObject().useDriverInstance("ORIGINAL");
                }
                isTestcaseFailed = true;
            } else if (!PropertyUtility.targetPlatform.equalsIgnoreCase("WEB")) {
                SetupManager.getObject().terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                SetupManager.getObject().restartApp();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(PropertyUtility.targetPlatform.equalsIgnoreCase("WEB")){
                JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
                js.executeScript(String.format("window.localStorage.clear();"));
            }

            //clear scenario context
            CucumberContextManager.getObject().clearSecnarioContextMap();
        } catch (Exception e) {
            logger.error("Error in After Test Block ", ExceptionUtils.getStackTrace(e));

        }


    }

    public void tearDown() throws IOException {
        try {
            this.reportManager.endSuiteFile();
        }
        catch (Exception ex)
        {

        }
    }

    @BeforeStep
    public void beforeStep() {
        String name = ThreadLocalStepDefinitionMatch.get();
        if (name!= null)
        logger.detail("CUCUMBER STEP COMPLETE : "+ name.toUpperCase());

        if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS") || PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID")) {
            String currentKey = DriverManager.getCurrentKey();
            if(DriverManager.driverArray.size()>1) {
                for (Map.Entry<String, WebDriver> entry : DriverManager.driverArray.entrySet()) {
                    entry.getValue().getPageSource();
                    logger.detail("Pinging : "+ entry.getKey());
                }
                DriverManager.driverArray.get(currentKey).getPageSource();
                //Ping all instances to keep them running in browserstack, used in duo scenarioss
            }
        }

    }

    //for first test case after duo reinstall the apps
    @Before("@POSTDUO")
    public void afterDuoScenario() {
        if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {
        }
    }

    //Cancel bungii from admin panel
    @After("@scheduled")
    public void afterScheduledBungii(Scenario scenario) {
        //This scenario is not complete/full prof
        if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS") && scenario.isFailed()) {
            //   new GeneralUtility().recoverScenarioscheduled();
        }
    }

    //Create a duo
    @Before("@DUO_SCH_DONOT_ACCEPT")
    public void createDuoBungii() {
        //create trip for denver and keep
        if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {
            new BungiiSteps().createTripAndSaveInFeatureContext("duo", "denver", PropertyUtility.getDataProperties("denver.customer2.phone"), PropertyUtility.getDataProperties("denver.customer2.name"), PropertyUtility.getDataProperties("denver.customer2.password"), "DUO_SCH_DONOT_ACCEPT");

        }

        //create trip for Kansas and keep
        if (PropertyUtility.targetPlatform.equalsIgnoreCase("android")) {
            new BungiiSteps().createTripAndSaveInFeatureContext("duo", "Kansas", PropertyUtility.getDataProperties("kansas.customer1.phone"),
                    PropertyUtility.getDataProperties("kansas.customer1.name"), PropertyUtility.getDataProperties("kansas.customer1.password"), "DUO_SCH_DONOT_ACCEPT");
        }
    }

}
