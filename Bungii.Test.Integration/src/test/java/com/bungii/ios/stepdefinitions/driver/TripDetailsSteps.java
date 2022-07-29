package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.ScheduledTripsPage;
import com.bungii.ios.pages.driver.TripDetailsPage;
import com.bungii.ios.pages.driver.UpdateStatusPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import static com.bungii.common.manager.ResultManager.*;


public class TripDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(TripDetailsSteps.class);
    ActionManager action = new ActionManager();
    private TripDetailsPage tripDetailsPage;
    private UpdateStatusPage updateStatusPage;
    private ScheduledTripsPage scheduledTripsPage;


    public TripDetailsSteps(TripDetailsPage tripDetailsPage,UpdateStatusPage updateStatusPage,ScheduledTripsPage scheduledTripsPage) {
        this.tripDetailsPage = tripDetailsPage;
        this.updateStatusPage = updateStatusPage;
        this.scheduledTripsPage = scheduledTripsPage;
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
    @Then("^I should see the customers name under the customer name field$")
    public void i_should_see_the_customers_name_under_the_customer_name_field() throws Throwable {
        String deliveryCreatedCustomerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String customerName = action.getText(updateStatusPage.Text_CustomerNameOnDriverApp());
        testStepVerify.isEquals(customerName,deliveryCreatedCustomerName);
    }

    @And("^I should be able to add the text \"([^\"]*)\" in the signed by field$")
    public void i_should_be_able_to_add_the_text_something_in_the_signed_by_field(String text) throws Throwable {
        Thread.sleep(1000);
        action.clearSendKeys(updateStatusPage.TextBox_SignedByField(),text);
    }

    @And("^I should be able to add customer signature$")
    public void i_should_be_able_to_add_customer_signature() throws Throwable {
        Thread.sleep(2000);
        action.click(updateStatusPage.TextBox_Signature());
        DrawSignature();
        Thread.sleep(5000);
    }
    public void DrawSignature() throws InterruptedException {
//        action.dragFromToForDuration(160,335,130,464,1);
        IOSDriver<MobileElement> driver = (IOSDriver<MobileElement>) SetupManager.getDriver();


        int startX =160;
        int startY=335;
        int endX =130;
        int endY=461;
        new TouchAction(driver)
                .press(new PointOption<>().withCoordinates(startX, startY))
                .moveTo(new PointOption<>().withCoordinates(endX, endY))
                .moveTo(new PointOption<>().withCoordinates(endX+50, endY+50))
                .release()
                .perform();

        int startX1 =157;
        int startY2=390;
        int endX3 =107;
        int endY4=338;
        new TouchAction(driver)
                .press(new PointOption<>().withCoordinates(startX1, startY2))
                .moveTo(new PointOption<>().withCoordinates(endX3, endY4))
                .moveTo(new PointOption<>().withCoordinates(endX3+50, endY4+50))
                .release()
                .perform();
    }

    @And("^I click on the \"([^\"]*)\" link beside scheduled bungii for \"([^\"]*)\"$")
    public void i_click_on_the_something_link_beside_scheduled_bungii_for_something(String strArg1, String deliveryType) throws Throwable {
        try{
            switch (deliveryType){
                case "Completed Deliveries":
                    Thread.sleep(4000);
                    action.click(scheduledTripsPage.Link_DeliveryDetails());
                    Thread.sleep(2000);
                    action.click(scheduledTripsPage.List_ViewDeliveries());
                    break;
            }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the customer signature row present in admin portal all delivery details page$")
    public void i_should_see_the_customer_signature_row_present_in_admin_portal_all_delivery_details_page() throws Throwable {
        boolean isCustomerSignatureDisplayed = scheduledTripsPage.Label_CustomerSignature().isDisplayed();
        testStepVerify.isTrue(isCustomerSignatureDisplayed,"cus displayed");
    }

    @And("^I select \"([^\"]*)\" from the dropdown$")
    public void i_select_something_from_the_dropdown(String status) throws Throwable {
        try{
            Thread.sleep(5000);
            action.click(scheduledTripsPage.Link_ChangeDeliveryStatus());
            Thread.sleep(4000);
            action.click(scheduledTripsPage.DropDown_DeliveryStatus());
            switch (status){
                case "Admin Canceled":
                case "Partner Canceled":
                case "Driver Canceled":
                case "Customer Canceled":
                    action.click(scheduledTripsPage.Text_DeliveryStatus(status));
                    break;
            }
            log("I should be able to click on "+status+" link", "I could click on "+status+" link",false);
        }catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I select \"([^\"]*)\" as the reason from the reason dropdown$")
    public void i_select_something_as_the_reason_from_the_reason_dropdown(String changestatusreason) throws Throwable {
        try {
            action.click(scheduledTripsPage.DropDown_DeliveryStatusReason());
            switch (changestatusreason) {
                case "Driver initiated":
                case "Customer initiated - other reason":
                case "Outside of delivery scope":
                case "Solo: Driver not found":
                case "Other":
                    action.click(scheduledTripsPage.Text_DeliveryStatusReason(changestatusreason));
                    break;
            }
            log("I should be able to click on " + changestatusreason + " option", "I could click on " + changestatusreason + " option", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

}
