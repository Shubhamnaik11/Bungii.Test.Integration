package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.EnableNotificationPage;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class EnabledNotificationSteps extends DriverBase {
    ActionManager action = new ActionManager();
    EnableNotificationPage enableNotificationPage = new EnableNotificationPage();
    private static LogUtility logger = new LogUtility(EnabledNotificationSteps.class);

    @Then("^I allow access of Notification from Bungii application$")
    public void i_allow_access_of_notification_from_bungii_application() throws Throwable {
        try {
            action.click(enableNotificationPage.Button_Sure());
            action.clickAlertButton("Allow");
            pass("I allow access of Notification from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }
}
