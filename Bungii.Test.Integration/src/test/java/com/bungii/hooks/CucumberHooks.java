package com.bungii.hooks;

import com.bungii.SetupManager;
import com.bungii.api.stepdefinitions.BungiiSteps;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.manager.ReportManager;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.driver.LogInSteps;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


//Cannot be moved Framework as it has to call recovery secnario

/**
 * @author vishal.bagi
 */
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
    private ReportManager reportManager;
    private boolean isTestcaseFailed = false;

    public CucumberHooks() {
        this.reportManager = new ReportManager();
    }

    // @BeforeSuite

    /**
     * This method will be called at start of each test suite
     */
    public synchronized void start(String resultFolder) {

        try {
            //adding ternary operator in logger is creating issue
            String device=System.getProperty("DEVICE") == null ? "Windows VM" : System.getProperty("DEVICE");
            logger.detail("Device On which test will be run is : " +device );
            //Create new default driver instance and save it
            SetupManager.getObject().getDriver();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Unable to create default appium driver");
        }

        try {
            this.reportManager.startSuiteFile(resultFolder);
        } catch (Exception e) {
            logger.error("Unable to start report com.bungii.android.manager");
        }
    }


    /**
     * Cucumber hook to update test case in report
     *
     * @param scenario Scenario that is being executed
     */
    @Before
    public void beforeTest(Scenario scenario) {
        this.reportManager.startTestCase(scenario.getName());
        logger.detail("**********************************************************************************");
        logger.detail("Starting " + scenario.getName());
/*		if(PropertyUtility.targetPlatform.equalsIgnoreCase("IOS"))
			new GeneralUtility().recoverScenario();*/
        //Set original instance as default instance at start of each test case
        SetupManager.getObject().useDriverInstance("ORIGINAL");
        // SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Customer"));

        //restart driver app
        //SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
        //SetupManager.getObject().restartApp();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //restart driver app
        //SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
        //Vishal[1801]: Restart app before Each test case
        //If not first test case
        if (!isFirstTestCase) {

            SetupManager.getObject().restartApp();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Cucumber hook to update test case in report
     *
     * @param scenario Scenario that was being executed
     */
    @After
    public void afterTest(Scenario scenario) {
        try {
            //clear scenario context
            CucumberContextManager.getObject().clearSecnarioContextMap();
            //if first test case flag is ste to true then change it to false
            if (isFirstTestCase) isFirstTestCase = false;
            DriverManager.getObject().closeAllDriverInstanceExceptOriginal();
            SetupManager.getObject().useDriverInstance("ORIGINAL");

            this.reportManager.endTestCase(scenario.isFailed());
            if (scenario.isFailed() || this.reportManager.isVerificationFailed()) {
                //if consecutive two case failed then create new instance
                if (isTestcaseFailed)
                    SetupManager.getObject().createNewAppiumInstance("ORIGINAL", "device1");
                try {
                    logger.detail(" PAGE SOURCE:" + StringUtils.normalizeSpace(DriverManager.getObject().getDriver().getPageSource()));

                } catch (Exception e) {
                }

                if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS"))
                {   new BungiiSteps().recoveryScenario();
                    new GeneralUtility().recoverScenario();}
                else if (PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID")) {
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
        } catch (Exception e) {
            logger.error("Error performing step ", ExceptionUtils.getStackTrace(e));

        }


}

    // @AfterSuite

    /**
     * This will be called at end of each suite
     *
     * @throws IOException
     */
    public void tearDown() throws IOException {
        this.reportManager.endSuiteFile();
        //SetupManager.stopAppiumServer();
     //   logger.detail("PAGE SOURCE:" + DriverManager.getObject().getDriver().getPageSource());

    }

    //for first test case after duo reinstall the apps
    @Before("@POSTDUO")
    public void afterDuoScenario() {
        if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {
           // new GeneralUtility().installDriverApp();
           // try{ SetupManager.getObject().launchApp(PropertyUtility.getProp("bundleId_Driver"));new LogInSteps().i_am_logged_in_as_something_driver("valid");}catch (Exception e){}
           // new GeneralUtility().installCustomerApp();
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
            new BungiiSteps().createTripAndSaveInFeatureContext("duo", "denver", PropertyUtility.getDataProperties("denver.customer2.phone"),PropertyUtility.getDataProperties("denver.customer2.name"), PropertyUtility.getDataProperties("denver.customer2.password"),"DUO_SCH_DONOT_ACCEPT");

        }

        //create trip for Kansas and keep
        if (PropertyUtility.targetPlatform.equalsIgnoreCase("android")) {
            new BungiiSteps().createTripAndSaveInFeatureContext("duo", "Kansas", PropertyUtility.getDataProperties("kansas.customer1.phone"),
                    PropertyUtility.getDataProperties("kansas.customer1.name"), PropertyUtility.getDataProperties("kansas.customer1.password"),"DUO_SCH_DONOT_ACCEPT");
        }
    }
}
