package com.bungii.hooks;

import com.bungii.SetupManager;
import com.bungii.common.manager.AssertManager;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.manager.ReportManager;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


//Cannot be moved Framework as it has to call recovery secnario

/**
 *
 * @author vishal.bagi
 *
 */
public class CucumberHooks {

	protected WebDriver driver;
	private ReportManager reportManager;
	private static boolean isFirstTestCase;
	private static LogUtility logger = new LogUtility(CucumberHooks.class);
	static {
		PropertyUtility.loadRunConfigProps();
		String autoHome =CucumberHooks.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("/target/test-classes/", "");// (String) PropertyUtility.getProp("auto.home");
		autoHome=autoHome.substring(0, 1).equals("/")?autoHome.substring(1):autoHome;
		FileUtility.autoHome =autoHome;
		String log4jConfPath ="src/main/resources/SystemProperties/log4j.properties";
		PropertyConfigurator.configure(FileUtility.getSuiteResource("",log4jConfPath));
		isFirstTestCase=true;
	}

	public CucumberHooks() {
		this.reportManager = new ReportManager();
	}

	// @BeforeSuite
	/**
	 * This method will be called at start of each test suite
	 */
	public synchronized void start(String resultFolder) {

		try {
			logger.detail("Device On which test will be run is" + System.getProperty("DEVICE"));
			//Create new default driver instance and save it
			SetupManager.getObject().getDriver();
		} catch (Exception e) {
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
	 * @param scenario
	 *            Scenario that is being executed
	 */
	@Before
	public void beforeTest(Scenario scenario) {
		this.reportManager.startTestCase(scenario.getName());
		logger.detail( "Starting "+scenario.getName());
		//Set original instance as default instance at start of each test case
		SetupManager.getObject().useDriverInstance("ORIGINAL");

		//Vishal[1801]: Restart app before Each test case
		//If not first test case
		if(!isFirstTestCase)
			SetupManager.getObject().restartApp();

	}

	/**
	 * Cucumber hook to update test case in report
	 * 
	 * @param scenario
	 *            Scenario that was being executed
	 */
	@After
	public void afterTest(Scenario scenario) {
		//if first test case flag is ste to true then change it to false
		if(isFirstTestCase)isFirstTestCase=false;
		DriverManager.getObject().closeAllDriverInstanceExceptOriginal();

		this.reportManager.endTestCase(scenario.isFailed());
		if (scenario.isFailed()) {
			//Recover recover = new Recover();
		}

	}

	// @AfterSuite
	/**
	 * This will be called at start of each suite
	 * 
	 * @throws IOException
	 */
	public void tearDown() throws IOException {
		this.reportManager.endSuiteFile();
		//SetupManager.stopAppiumServer();
		//logger.detail("PAGE SOURCE:"+DriverManager.getObject().getDriver().getPageSource());

	}
}
