package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.TutorialPage;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class TutorialSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(TutorialSteps.class);
    ActionManager action = new ActionManager();
    TutorialPage tutorialPage = new TutorialPage();

    @Then("^I close tutorial Page$")
    public void i_close_tutorial_page() throws Throwable {
        try {
            action.click(tutorialPage.Button_Close());
            pass("I close tutorial Page", "I clicked on close button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }
}
