package com.bungii.ios.utilityfunctions;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.DataProvider;

public class CustomFormatter extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;


    @DataProvider
    @Override
    public Object[][] scenarios() {
        //testNGCucumberRunner.
        return super.scenarios();
    }
}