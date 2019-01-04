package com.bungii.runner;

import com.bungii.hooks.CucumberHooks;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;

@CucumberOptions(features = "target/test-classes/features/ios", monochrome = true, tags = "@Android_DUO", plugin = {
        "pretty", "html:target/cucumber-report/single",
        "json:target/cucumber-report/single/cucumber.json",
        "rerun:target/cucumber-report/single/rerun.txt", "com.bungii.common.utilities.CustomFormatter"},
        glue = {"com.bungii.ios", "com.bungii.hooks"}
)
public class RunSuite_IOS extends AbstractTestNGCucumberTests {


    CucumberHooks hooks;

    /**
     * @param device Device variable from maven
     */
    @Parameters("test.Device")
    public RunSuite_IOS(@Optional("device1") String device) {
        //Use below statement only in test runner running which are not suppose to run with maven ,
        //In case of maven logFilepath is set in maven set in POM.xml

        System.setProperty("LogFilePath", "Results");

        String ClassName = this.getClass().getSimpleName();
        String[] deviceList = device.split(",");
        //if mutiple devices are pass from maven then get class number and use that device for running that class
        if (deviceList.length > 1) {
            int threadNumber = Integer.parseInt(ClassName.substring(8, 10));

            System.setProperty("DEVICE", deviceList[threadNumber - 1]);
        } else {
            System.setProperty("DEVICE", device);

        }
        System.setProperty("runner.class", ClassName);

        this.hooks = new CucumberHooks();

    }

    /**
     * @param resultFolder Result Folder variable from maven
     */
    @BeforeSuite
    @Parameters("NameWithtimestamp")
    public void start(@Optional("") String resultFolder) {
        this.hooks.start(resultFolder);
    }

    /**
     * After suite method
     *
     * @throws IOException
     */
    @AfterSuite
    public void afterSuite() throws IOException {
        this.hooks.tearDown();
    }

}