package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.*;

public class CommonSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(com.bungii.ios.stepdefinitions.CommonSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();

    @When("^I Switch to \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_switch_to_something_application_on_something_devices(String appName, String device)  {
        try {
            if(!device.equalsIgnoreCase("same")){i_switch_to_something_instance(device); Thread.sleep(2000);}

            // SetupManager.getObject().useDriverInstance("ORIGINAL");
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


    @When("^I connect to \"([^\"]*)\" using \"([^\"]*)\" instance$")
    public void i_connect_to_something_using_something_instance(String deviceId, String instanceName) {
        try {
            SetupManager.getObject().createNewAndroidInstance(instanceName, deviceId);
            SetupManager.getObject().useDriverInstance(instanceName);
            log("I should be connected to "+deviceId ,
                    "I connected to "+deviceId +" device and assigned session name "+instanceName, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I switch to \"([^\"]*)\" instance$")
    public void i_switch_to_something_instance(String instanceName)  {
        try {
            SetupManager.getObject().useDriverInstance(instanceName);
            log("I switch to  " + instanceName + " instance session",
                    "I switch to  " + instanceName + "instance session", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" page should be opened$")
    public void ThenPageShouldBeOpened(String page)
    {
        boolean isCorrectPage=utility.isCorrectPage(page);
        testStepAssert.isTrue(isCorrectPage,page+" should be displayed",page+" is displayed correctly  ",page+" is not displayed correct");
    }
}
