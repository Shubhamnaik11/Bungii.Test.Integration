package com.bungii.runner;

import com.bungii.hooks.CucumberHooks;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@CucumberOptions(features = "target/test-classes/features/web", monochrome = true, tags = "@web and @sanity1", plugin = {
        "pretty", "html:target/cucumber-report/single",
        "json:target/cucumber-report/single/cucumber.json",
        "rerun:target/cucumber-report/single/rerun.txt", "com.bungii.common.utilities.CustomFormatter"},
        glue = {"com.bungii.web.stepdefinitions", "com.bungii.hooks"}
)
public class RunAutoSuite extends AbstractTestNGCucumberTests {


    CucumberHooks hooks;

    /**
     * @param device Device variable from maven
     */
    @Parameters({"test.Device","test.Platform","test.Environment","test.Category"})
    public RunAutoSuite(@Optional("device1") String device, @Optional("web") String Platform, @Optional("QA") String environment, @Optional("sanity") String category) {
        //Use below statement only in test runner running which are not suppose to run with maven ,
        //In case of maven logFilepath is set in maven set in POM.xml

        System.setProperty("LogFilePath", "Results");

    String ClassName = this.getClass().getSimpleName();
        if(Platform =="ios" || Platform=="android") {
    String[] deviceList = device.split(",");
    //if mutiple devices are pass from maven then get class number and use that device for running that class
    if (deviceList.length > 1) {
        int threadNumber = Integer.parseInt(ClassName.substring(8, 10));

        System.setProperty("DEVICE", deviceList[threadNumber - 1]);
    } else {
        System.setProperty("DEVICE", device);

    }
}
        System.setProperty("runner.class", ClassName);

        this.hooks = new CucumberHooks();

    }

    /**
     * @param resultFolder Result Folder variable from maven
     */
    @BeforeSuite
    @Parameters({"NameWithtimestamp","test.Platform","test.Environment"})
    public void start(@Optional("") String resultFolder,@Optional("web") String Platform, @Optional("QA") String environment) {

        Properties props = new Properties();

        String propsFileName = "./src/main/resources/UserProperties/config.properties";
        try {
            //first load old one:
            FileInputStream configStream = new FileInputStream(propsFileName);
            props.load(configStream);
            configStream.close();

            //modifies existing or adds new property
            props.setProperty("target.platform", Platform);
            props.setProperty("environment", environment);

            //save modified property file
            FileOutputStream output = new FileOutputStream(propsFileName);
            props.store(output, "");
            output.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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