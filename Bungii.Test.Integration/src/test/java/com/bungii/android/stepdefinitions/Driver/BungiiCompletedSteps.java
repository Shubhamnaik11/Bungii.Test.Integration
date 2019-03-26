package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.AvailableTripsPage;
import com.bungii.android.pages.driver.BungiiCompletedPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class BungiiCompletedSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(AvailableTripsSteps.class);
    BungiiCompletedPage bungiiCompletedSteps = new BungiiCompletedPage();
    ActionManager action = new ActionManager();

    @Then("^Bungii driver should see \"([^\"]*)\" on Bungii completed page$")
    public void bungii_driver_should_see_something_on_bungii_completed_page(String identifier) throws Throwable {
        try {
            switch (identifier.toLowerCase()) {
                case "correct details":
                    verifyBungiiCompletedPage();
                    verifyTripValue();
                    break;
                default:
                        error("UnImplemented Step or incorrect button name", "UnImplemented Step");break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }    }

    /**
     * Verify Static texts on Bungii Completed page
     */
        public void verifyBungiiCompletedPage(){
            testStepVerify.isElementTextEquals(bungiiCompletedSteps.Title_Status(),"Bungii Completed");
            testStepVerify.isElementEnabled(bungiiCompletedSteps.Image_Dollar(),"'Dollar Image' should be displayed on Summary page","'Dollar Image' is displayed","'Dollar Image' is not displayed");
            testStepVerify.isElementEnabled(bungiiCompletedSteps.Text_Label(),"'Cha-Ching' should be displayed on Summary page","'Cha-Ching' is displayed","'Cha-Ching' is not displayed");
            testStepVerify.isElementEnabled(bungiiCompletedSteps.Text_TotalTimeLabel(),"Total Time label should be displayed on Summary page");
            testStepVerify.isElementEnabled(bungiiCompletedSteps.Text_TotalDistanceLabel(),"Total Distance label should be displayed on Summary page");
            testStepVerify.isElementEnabled(bungiiCompletedSteps.Text_TotalEarningsLabel(),"Total Earning label should be displayed on Summary page");
        }

    /**
     * Verify variable texts in Bungii Complete Page
     */
        public void verifyTripValue(){
            String totalTime=action.getText(bungiiCompletedSteps.Text_TotalTime()),totalDistance=action.getText(bungiiCompletedSteps.Text_TotalDistance()),toatlEarning=action.getText(bungiiCompletedSteps.Text_TotalEarnings());
            testStepVerify.isTrue(totalTime.contains("minutes"),"Total time should contains minute");
            testStepVerify.isTrue(totalDistance.contains("miles"),"Total time should contains minute");
            testStepVerify.isTrue(toatlEarning.contains("$"),"Total time should contains minute");


        }
}
