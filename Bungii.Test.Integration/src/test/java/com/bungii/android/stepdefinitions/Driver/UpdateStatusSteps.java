package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.pages.driver.InProgressBungiiPages;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.otherApps.*;
import com.bungii.android.pages.driver.UpdateStatusPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class UpdateStatusSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(com.bungii.ios.stepdefinitions.driver.UpdateStatusSteps.class);

    Rectangle initial;
    ActionManager action = new ActionManager();
    UpdateStatusPage updateStatusPage = new UpdateStatusPage();
    InProgressBungiiPages inProgressBungiiPages = new InProgressBungiiPages();
    GeneralUtility utility = new GeneralUtility();

    @When("^I slide update button on \"([^\"]*)\" Screen$")
    public void i_start_selected_bungii(String screen) {
        try {
            String expectedMessage = "";
            switch (screen.toUpperCase()) {
                case "EN ROUTE":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.enroute");
                    break;
                case "ARRIVED":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.arrived");
                    break;
                case "LOADING ITEM":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.loading");
                    break;
                case "DRIVING TO DROP OFF":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.drop.off");
                    break;
                case "UNLOADING ITEM":
                    expectedMessage = PropertyUtility.getMessage("driver.slide.unloading");
                    break;
                default:
                    break;
            }
            //String actualValue = updateStatusPage.getSliderText();

            updateStatus();
            Thread.sleep(5000);
            log("I slide update button on " + screen + " screen", "I slide update button on " + screen + " screen", true);

		/*testStepVerify.isEquals(actualValue, expectedMessage,
				"I slide update button on " + screen + " Screen",
				"Slider value should be" + expectedMessage + "and actual is" + actualValue,
				"Slider value should be" + expectedMessage + "and actual is" + actualValue);*/
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    /**
     * Slide the slider to update status
     */
    public void updateStatus() throws InterruptedException {
        action.swipeRight(updateStatusPage.Slider());
    }

    @Then("^non control driver should see \"([^\"]*)\" screen$")
    public void non_control_driver_should_see_something_screen(String strArg1) throws Throwable {
        try{
            //testStepVerify.isElementEnabled(updateStatusPage.Activity_loader(true)," Driver should be shown loader screen");
           // testStepVerify.isElementTextEquals(updateStatusPage.Text_WaitingForDriver(),"Waiting for the other driver to end Bungii.");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I check ETA of \"([^\"]*)\"$")
    public void i_check_eta_of_something(String strArg1){
        try {
            switch (strArg1.toLowerCase()) {
                case "control driver":
                    cucumberContextManager.setScenarioContext("ETA_VALUE",action.getText(inProgressBungiiPages.Bungii_ETA()));
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" eta should be displayed to customer$")
    public void something_eta_should_be_displayed_to_customer(String strArg1) throws Throwable {
        try {
            switch (strArg1.toLowerCase()) {
                case "control driver":
                    String controlDriverEta=(String) cucumberContextManager.getScenarioContext("ETA_VALUE");
                    testStepVerify.isTrue(action.getText(inProgressBungiiPages.Bungii_ETA()).equals(controlDriverEta),controlDriverEta+" should be displayed");
                    break;
                default:
                    throw new Exception("Not Implemented");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }    }
}