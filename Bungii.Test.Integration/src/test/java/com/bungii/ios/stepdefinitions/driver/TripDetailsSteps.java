package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.TripDetailsPage;
import cucumber.api.java.en.And;
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
    @And("^I check if variable sign is shown under \"([^\"]*)\"$")
    public void i_check_if_variable_sign_is_shown_under_something(String page) throws Throwable {
        try{
            switch (page){
                case "available bungii details":
                    Thread.sleep(2000);
                    String driverEarnings = tripDetailsPage.Text_EstimatedEarnings().getText();
                    testStepAssert.isTrue(driverEarnings.contains("~"),
                            "The variable sign (~) should be present",
                            "The variable sign (~) is not present");
                    break;
                case "schedule bungii details":
                    Thread.sleep(2000);
                    String driverEarningsSchedulePage = tripDetailsPage.Text_EstimatedEarningsSchedule().getText();
                    testStepAssert.isTrue(driverEarningsSchedulePage.contains("~"),
                            "The variable sign (~) should be present",
                            "The variable sign (~) is not present");
                    break;
            }
            log("I should be able to check the variable sign","I was able to check the variable sign",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I select \"([^\"]*)\" from items$")
    public void i_select_something_from_items(String pallet) throws Throwable {
        try{
            switch (pallet){
                case "Pallet-1":
                    action.clickBy2Points(47,650);
                    break;

                case "Pallet-2":
                    action.clickBy2Points(45,756);
                    break;
            }
            log("I should be able to select the pallet",
                    "I am able to select the pallet",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I check inadequate payload pop up is displayed$")
    public void i_check_inadequate_payload_pop_up_is_displayed() throws Throwable {
      try{
            testStepAssert.isElementDisplayed(tripDetailsPage.PopUp_InadequatePayload(),
                    "The pop up for inadequate payload should be displayed",
                    "The pop up for inadequate payload is displayed",
                    "The pop up for inadequate payload is not displayed");

            String expectedPopUpMesssage = PropertyUtility.getMessage("low.payload.capacity.message");
            String actualPopUpMesssage = tripDetailsPage.PopUp_InadequatePayload().getText();
            testStepAssert.isEquals(actualPopUpMesssage,expectedPopUpMesssage,
                    "The pop up message displayed should be correct.",
                    "The pop up message displayed is correct.",
                    "The pop up message displayed is incorrect.");
      }
      catch (Exception e) {
          logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
          error("Step  Should be successful",
                  "Error performing step,Please check logs for more details", true);
      }
    }
    @Then("^I check information of both the pallets are displayed separately$")
    public void i_check_information_of_both_the_pallets_are_displayed_separately() throws Throwable {
        try{
            testStepAssert.isElementDisplayed(tripDetailsPage.Text_PalletOne(),
                    "The pallet one information should be displayed",
                    "The pallet one information is displayed",
                    "The pallet one information is not displayed");

            testStepAssert.isElementDisplayed(tripDetailsPage.Text_PalletTwo(),
                    "The pallet two information should be displayed",
                    "The pallet two information is displayed",
                    "The pallet two information is not displayed");

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I click \"([^\"]*)\" button on alert message$")
    public void i_click_something_button_on_alert_message(String button) throws Throwable {
        try {
            switch (button) {
                case "OK":
                    action.click(tripDetailsPage.Button_Ok());
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }
    @And("^I check if variable sign is not shown under \"([^\"]*)\"$")
    public void i_check_if_variable_sign_is_not_shown_under_something(String page) throws Throwable {
        try{
            switch (page){
                case "available bungii details":
                    Thread.sleep(2000);
                    String driverEarnings = tripDetailsPage.Text_EstimatedEarnings().getText();
                    testStepAssert.isFalse(driverEarnings.contains("~"),
                            "The variable sign (~) should not be present",
                            "The variable sign (~) is present");
                    break;
                case "schedule bungii details":
                    Thread.sleep(2000);
                    String driverEarningsSchedulePage = tripDetailsPage.Text_EstimatedEarningsSchedule().getText();
                    testStepAssert.isFalse(driverEarningsSchedulePage.contains("~"),
                            "The variable sign (~) should not be present",
                            "The variable sign (~) is present");
                    break;
            }
            log("I should be able to check if the variable sign is absent","I was able to check if the variable sign is absent",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Driver Bungii Information should be correctly displayed on BUNGII DETAILS screen$")
    public void driver_information_should_be_correctly_displayed_on_somethingBUNGIIDETAILS_screen() {
        try {
            action.swipeUP();
            String[] actualDetails = getTripDetails();
            cucumberContextManager.setScenarioContext("BUNGII_DRIVER_ESTIMATE", actualDetails[1]);
            String expectedTripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_FORMATTED")); ///Added to compare formatted time and not BUNGII_TIME
            String expectedTripDistance = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
            String timeValue = expectedTripTime.split(",")[1].trim().replace("am","AM").replace("pm","PM");
            String expectedDate = expectedTripTime.split(",")[0].trim();
            boolean isDateCorrect = actualDetails[2].split("\\|")[0].trim().contains(expectedDate.trim());
            String deliveryTime = actualDetails[2].split("\\|")[1].trim().split(" ")[0].trim();
            boolean isTimeCorrect = timeValue.split(" ")[0].trim().equals(deliveryTime);

            boolean isDistanceCorrect = actualDetails[0].contains(expectedTripDistance.split(" ")[0].trim());

            testStepVerify.isTrue(isTimeCorrect,
                    "Driver Trip Information should be correctly displayed on BUNGII DETAILS screen",
                    "Driver Time is correctly displayed [Excluding timezone] : "+ deliveryTime,
                    "Driver Time is not displayed correctly displayed ,Expected trip time:" + timeValue + " actual trip time " + actualDetails[2].trim());

            testStepVerify.isTrue(isDateCorrect,
                    "Driver Information should be correctly displayed on BUNGII DETAILS screen",
                    "Driver Time should be correctly displayed ",
                    "Driver Time is not displayed correctly displayed , expected Trip date:" + expectedDate + " actual trip date:" + actualDetails[2].trim());


            testStepVerify.isTrue(isDistanceCorrect,
                    "Driver Information should be correctly displayed on BUNGII DETAILS screen",
                    "Driver Distance should be correctly displayed ",
                    "Driver Distance is not displayed correctly displayed , expected Trip distance" + expectedTripDistance + " actual trip distance " + actualDetails[0]);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Driver Bungii Information is not correctly displayed on BUNGII DETAILS screen", true);
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
            tripDetails[2] = action.getValueAttribute(tripDetailsPage.Text_ScheduledDateTime());
          //  action.swipeUP();
           // tripDetails[3] = action.getValueAttribute(tripDetailsPage.Text_ScheduledTime());
        } catch (Exception e) {
            if (action.isAlertPresent()) {
                SetupManager.getDriver().switchTo().alert().dismiss();
            }
            tripDetails[0] = action.getValueAttribute(tripDetailsPage.Text_Distance());
            tripDetails[1] = action.getValueAttribute(tripDetailsPage.Text_EstimatedEarnings());
            tripDetails[2] = action.getValueAttribute(tripDetailsPage.Text_ScheduledDateTime());
       //     action.swipeUP();
       //     tripDetails[3] = action.getValueAttribute(tripDetailsPage.Text_ScheduledTime());
        }
        return tripDetails;
    }


}
