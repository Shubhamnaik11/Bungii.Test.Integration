package com.bungii;

import com.bungii.SetupManager;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.bungii.hooks.CucumberHooks;

import java.io.IOException;

@CucumberOptions(
        strict = true,
        features = {"D:/Bungii-QA-Automation/Bungii.Test.Integration/src/test/resources/features/android/Bungii/testfeature.feature:9"},
        plugin = {"json:D:/Bungii-QA-Automation/Bungii.Test.Integration/Results/Executed on 2018_12_18_052508/defaultReport/1.json", "com.example.CustomHtmlFormatter:D:/Bungii-QA-Automation/Bungii.Test.Integration/Results/Executed on 2018_12_18_052508/defaultReport/1.html"},
        monochrome = true,
        glue = {"com.bungii.android.stepdefinitions"})
public class Parallel01IT extends AbstractTestNGCucumberTests {
	private static final String RUN_CONFIG_PROPERTIES = "/profile.properties";

	CucumberHooks hooks;
	/*public Parallel01IT(){
		this.hooks= new Hooks();
	}*/
	
	    @Parameters("test.Device")
	public Parallel01IT(@Optional("device1") String device){
		String ClassName =this.getClass().getSimpleName();

		String[]deviceList = device.split(",");
		if(deviceList.length>1){
			int threadNumber = Integer.parseInt(ClassName.substring(8,10));		

		System.err.println("DEVICE is "+device+"deviceTo be consider"+deviceList[threadNumber-1]);
		System.setProperty("DEVICE",deviceList[threadNumber-1]);}
		else{
			System.setProperty("DEVICE",device);

		}
				System.setProperty("runner.class",ClassName);
		
		this.hooks= new CucumberHooks();
	}
	
    @Parameters("NameWithtimestamp")
 	@BeforeSuite
 	public void start(@Optional("") String resultFolder){
 		
 		this.hooks.start(resultFolder);
 	}
    @AfterSuite
    public  void afterSuite() throws IOException {
    	this.hooks.tearDown();
    }
/*	
   protected static WebDriver appiumDriver;
  //  private static HTMLReportManager reportManager;
    private static ReportManager reportManager;

//	private static LogUtility logger = new LogUtility("this");

	public Parallel01IT(){
		this.reportManager= new ReportManager();
	}
	
	@BeforeSuite
	public static void start(){
		String runHome = null, autoHome = null;
		try{
		    
		   appiumDriver= DriverHelper.getAppiumDriver();
						autoHome = (String) PropertyUtility.getProp("AutoHome");
			runHome = (String) PropertyUtility.getProp("RunHome");
			ResourcePaths.getInstance(autoHome, runHome);
			DriverManager.getObject().storeDriverInstance();

		} catch(Exception e) {
			System.out.println("Please check the config files for workHome, autoHome and runHome");
		}
		try {
			//reportManager= new ReportManager();
			reportManager.startSuiteFile();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	@Before
	public void beforeTest(Scenario scenario){
		reportManager.startTestCase(scenario.getName()+this.getClass());
	}
	
	@After
	public void afterTest(Scenario scenario){
		reportManager.endTestCase(scenario.isFailed());
		if(scenario.isFailed()){
			//Recover recover = new Recover();
		}
	}
	
    @AfterSuite
    public static void setup() throws IOException {
    	reportManager.endSuiteFile();
    }*/
	}