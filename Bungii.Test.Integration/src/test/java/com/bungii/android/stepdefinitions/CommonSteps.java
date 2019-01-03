package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class CommonSteps {
    private static LogUtility logger = new LogUtility(com.bungii.ios.stepdefinitions.CommonSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();

    @When("^I Switch to \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_switch_to_something_application_on_something_devices(String appName, String device)  {
        try {

            SetupManager.getObject().useDriverInstance("ORIGINAL");
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    utility.launchDriverApplication();
                    break;
                case "CUSTOMER":
                    utility.launchCustomerApplication();
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            pass("Switch to " + appName + " application",
                    "Switch to " + appName + " application", true);
        //    cucumberContextManager.setFeatureContextContext("CURRENT_APPLICATION", appName.toUpperCase());

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
/*            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);*/

        }
    }
}
