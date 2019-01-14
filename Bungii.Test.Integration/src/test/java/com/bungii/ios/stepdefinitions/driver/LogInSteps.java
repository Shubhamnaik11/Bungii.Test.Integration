package com.bungii.ios.stepdefinitions.driver;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.Given;

public class LogInSteps extends DriverBase {
    GeneralUtility utility = new GeneralUtility();
    @Given("^I am logged in as \"([^\"]*)\" driver$")
    public void i_am_logged_in_as_something_driver(String option) throws Throwable {
        String phone, password;
        boolean shouldLoginSucessful;
        switch (option.toLowerCase()) {
            case "valid":
                phone = PropertyUtility.getDataProperties("valid.driver.phone");
                password = PropertyUtility.getDataProperties("valid.driver.password");
                shouldLoginSucessful = true;
                break;
            case"valid driver 2":
                phone = PropertyUtility.getDataProperties("valid.driver2.phone");
                password = PropertyUtility.getDataProperties("valid.driver2.password");
                shouldLoginSucessful = true;
                break;
            default:
                throw new Exception("Please specify valid input");
        }
        utility.loginToDriverApp(phone, password);
        if (shouldLoginSucessful) {
         //   utility.isDriverLoginSucessful();
        }
        else {
            //TODO: specify failure here
        }


    }
}
