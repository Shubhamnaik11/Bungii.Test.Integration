package com.bungii.runner;

import com.bungii.CucumberHooks;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.*;
import java.io.IOException;

@CucumberOptions(features = "src/test/resources/features/android", monochrome = true, tags = {"@Android1"}, plugin = {
		"pretty", "html:target/cucumber-report/single",
		"json:target/cucumber-report/single/cucumber.json",
		"rerun:target/cucumber-report/single/rerun.txt"},
		glue ={"com.bungii.android.stepdefinitions"}
)
public class RunSuite extends AbstractTestNGCucumberTests {

	
	CucumberHooks hooks;
    /**
     * @param device Device variable from maven
     */
    @Parameters("test.Device")
	public RunSuite(@Optional("device1") String device){
		//Use below statement only in test runner running which are not suppose to run with maven ,
		//In case of maven logFilepath is set in maven set in POM.xml

		System.setProperty("LogFilePath","Results");

		String ClassName =this.getClass().getSimpleName();
		String[]deviceList = device.split(",");
		//if mutiple devices are pass from maven then get class number and use that device for running that class
		if(deviceList.length>1){
			int threadNumber = Integer.parseInt(ClassName.substring(8,10));		

		System.setProperty("DEVICE",deviceList[threadNumber-1]);}
		else{
			System.setProperty("DEVICE",device);

		}
		System.setProperty("runner.class",ClassName);

		this.hooks= new CucumberHooks();

	}
    
 	/**
 	 * 
 	 * 
 	 * @param resultFolder Result Folder variable from maven
 	 */
 	@BeforeSuite
    @Parameters("NameWithtimestamp")
 	public void start(@Optional("") String resultFolder){
 		this.hooks.start(resultFolder);
 	}
 	
    /**
     * After suite method
     * 
     * @throws IOException
     */
    @AfterSuite
    public  void afterSuite() throws IOException {
    	this.hooks.tearDown();
    }
	public static void Main(String args[]){
		System.out.print("first statement. ");
	}
}