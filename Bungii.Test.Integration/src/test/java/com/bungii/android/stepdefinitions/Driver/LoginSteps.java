package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.Given;

public class LoginSteps extends DriverBase {
    ActionManager action = new ActionManager();
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
            default:
                throw new Exception("Please specify valid input");
        }
        utility.loginToDriverApp(phone, password);
        if (shouldLoginSucessful)
            utility.isDriverLoginSucessful();
        else {
            //TODO: specify failure here
        }


    }

}
