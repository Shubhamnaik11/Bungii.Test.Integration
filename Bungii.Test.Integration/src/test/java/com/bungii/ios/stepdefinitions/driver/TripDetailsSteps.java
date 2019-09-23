package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.TripDetailsPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;


public class TripDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(TripDetailsSteps.class);
    ActionManager action = new ActionManager();
    private TripDetailsPage tripDetailsPage;
    public TripDetailsSteps(TripDetailsPage tripDetailsPage) {
        this.tripDetailsPage = tripDetailsPage;
    }

    @When("^I accept selected Bungii$")
    public void i_accept_selected_bungii() {
        try {
            if (action.isAlertPresent()) {
                SetupManager.getDriver().switchTo().alert().dismiss();
                Thread.sleep(1000);
            }
            AcceptBungii();
            log("Bungii should be Bungii", "Bungii is sucessfully accepted");
        } catch (Exception e) {
            error("Bungii should be Bungii", "Error occured while accepting bungii" + e.getMessage());
        }

    }


    @Then("^Trip Information should be correctly displayed on TRIP DETAILS screen$")
    public void trip_information_should_be_correctly_displayed_on_something_screen() {
        try {
            action.swipeUP();
            String[] actualDetails = getTripDetails();
            cucumberContextManager.setScenarioContext("BUNGII_DRIVER_ESTIMATE", actualDetails[1]);

            String expectedTripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            //expectedTripTime="Apr 09 , 01:45 PM GMT+5:30";
            String expectedTripDistance = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
            //Leading zero is not present in time, Check if zero is present and delete it
            String timeValue = expectedTripTime.split(",")[1].trim();
            timeValue = timeValue.substring(0, 1).equals("0") ? timeValue.substring(1) : timeValue;
            String expectedDate = expectedTripTime.split(",")[0].trim();
            int leadingZero = expectedDate.indexOf(" ") + 1;
            if (expectedDate.substring(leadingZero, leadingZero + 1).startsWith("0"))
                expectedDate = expectedDate.substring(0, leadingZero) + expectedDate.substring(leadingZero + 1);
            boolean isDateCorrect = expectedDate.trim().equals(actualDetails[2].trim());
            boolean isTimeCorrect = timeValue.trim().equals(actualDetails[3].trim());
            boolean isDistanceCorrect = expectedTripDistance.equals(actualDetails[0]);

            testStepVerify.isTrue(isTimeCorrect,
                    "Trip Information should be correctly displayed on TRIP DETAILS screen",
                    "Trip Time should be correctly displayed ",
                    "Trip Time is not displayed correctly displayed ,Expected trip time:" + timeValue + "actual trip time" + actualDetails[3].trim());

            testStepVerify.isTrue(isDateCorrect,
                    "Trip Information should be correctly displayed on TRIP DETAILS screen",
                    "Trip Time should be correctly displayed ",
                    "Trip Time is not displayed correctly displayed , expected Trip date:" + expectedDate + " actual trip date:" + actualDetails[2].trim());


            testStepVerify.isTrue(isDistanceCorrect,
                    "Trip Information should be correctly displayed on TRIP DETAILS screen",
                    "Trip Distance should be correctly displayed ",
                    "Trip Distance is not displayed correctly displayed , expected Trip distance" + expectedTripDistance + " actual trip distance " + actualDetails[0]);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    /**
     * Accept Bungii
     */
    public void AcceptBungii() {
        action.click(tripDetailsPage.Button_Accept());
    }


    /**
     * Get trip details from trip details page
     *
     * @return Return array of string specifing trip details
     */
    public String[] getTripDetails() {
        String[] tripDetails = new String[4];

        try {


            tripDetails[0] = action.getValueAttribute(tripDetailsPage.Text_Distance());
            tripDetails[1] = action.getValueAttribute(tripDetailsPage.Text_EstimatedEarnings());
            tripDetails[2] = action.getValueAttribute(tripDetailsPage.Text_ScheduledDate());
            action.swipeUP();
            tripDetails[3] = action.getValueAttribute(tripDetailsPage.Text_ScheduledTime());
        } catch (Exception e) {
            if (action.isAlertPresent()) {
                SetupManager.getDriver().switchTo().alert().dismiss();
            }
            tripDetails[0] = action.getValueAttribute(tripDetailsPage.Text_Distance());
            tripDetails[1] = action.getValueAttribute(tripDetailsPage.Text_EstimatedEarnings());
            tripDetails[2] = action.getValueAttribute(tripDetailsPage.Text_ScheduledDate());
            action.swipeUP();
            tripDetails[3] = action.getValueAttribute(tripDetailsPage.Text_ScheduledTime());
        }
        return tripDetails;
    }


}
