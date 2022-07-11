package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_RefundsPage;
import com.bungii.web.pages.admin.Admin_RevivalPage;
import com.bungii.web.pages.admin.Admin_TripsPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_RevivalSteps extends DriverBase {

    Admin_RevivalPage admin_RevivalPage = new Admin_RevivalPage();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_RevivalSteps.class);
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_RevivalPage admin_revivalPage = new Admin_RevivalPage();


    @Then("^Revive button should be displayed beside the trip$")
    public void revive_button_should_be_displayed_beside_the_trip() throws Throwable {
        try {
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            SetupManager.getDriver().manage().window().maximize();
            SetupManager.getDriver().manage().window().setSize(new Dimension(1900, 1280));

            String link = String.format("//td[contains(.,'%s')]/following-sibling::td/a[@class='revive-trip-link']/img", customerName);
            testStepAssert.isTrue(action.isElementPresent(admin_TripsPage.findElement(link, PageBase.LocatorType.XPath)), "Revive button should be displayed", "Revive button is displayed", "Revive button is not displayed");
            cucumberContextManager.setScenarioContext("REVIVE_LINK", link);
        } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @Then("^I should see \"([^\"]*)\" message on popup with PickupId anad Pickup Origin$")
    public void i_should_see_something_message_on_popup_with_pickupid_anad_pickup_origin(String message) throws Throwable {

        try{
        testStepAssert.isTrue(action.isElementPresent(admin_RevivalPage.Label_HeaderPopup()),message+" should be displayed", message+" is displayed", message+" is not displayed");
        String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String pickupId = dbUtility.getPickupIdFromFactPickup(pickuprequest);
        String source = "Customer Delivery";
        String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");

        testStepAssert.isElementTextEquals(admin_RevivalPage.Label_PickupId(),pickupId, pickupId +" should be displayed", pickupId +" is displayed", pickupId+" is not displayed");
        //testStepAssert.isElementTextEquals(admin_RevivalPage.Label_PickupOrigin(),source, source +" should be displayed", source +" is displayed", source+" is not displayed");
        testStepAssert.isElementTextEquals(admin_RevivalPage.Label_PickupCustomer(),customerName, customerName +" should be displayed", customerName +" is displayed", customerName+" is not displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @Then("^I should see \"([^\"]*)\" message on popup with PickupId, Pickup Origin and Partner Name$")
    public void i_should_see_something_message_on_popup_with_pickupid_pickup_origin_and_partner_name(String message) throws Throwable {
        try{
            testStepAssert.isTrue(action.isElementPresent(admin_RevivalPage.Label_HeaderPopup()),message+" should be displayed", message+" is displayed", message+" is not displayed");
            String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String pickupId = dbUtility.getPickupIdFromFactPickup(pickuprequest);
            String source = "Customer Delivery";
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");

            testStepAssert.isElementTextEquals(admin_RevivalPage.Label_PickupId(),pickupId, pickupId +" should be displayed", pickupId +" is displayed", pickupId+" is not displayed");
            testStepAssert.isElementDisplayed(admin_RevivalPage.Label_PickupPartnerPortal(),"Pickup Partner portal is displayed","Pickup Partner portal is displayed","Pickup Partner portal is not displayed");

        }catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I click on \"([^\"]*)\" button on Revival Popup$")
    public void i_click_on_something_button_on_revival_popup(String button) throws Throwable {
        try{
        switch(button)
        {
            case "Confirm":
                action.click(admin_revivalPage.Button_Confirm());
                Thread.sleep(10000);
                String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                pickuprequest = dbUtility.getLinkedPickupRef(pickuprequest);
                cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickuprequest);
                break;
            case "Cancel":
                action.click(admin_revivalPage.Button_Cancel());
                break;
        }
        log("I click on the "+button+" button on Revival Popup",
                "I have clicked the "+button+" button on Revival Popup", true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I select \"([^\"]*)\" from the dropdown$")
    public void i_select_something_from_the_dropdown(String status) throws Throwable {
        try{
        Thread.sleep(5000);
        action.click(admin_RevivalPage.Link_ChangeDeliveryStatus());
        Thread.sleep(4000);
        action.click(admin_RevivalPage.DropDown_DeliveryStatus());
        switch (status){
            case "Admin Canceled":
            case "Partner Canceled":
            case "Driver Canceled":
            case "Customer Canceled":
                action.click(admin_RevivalPage.Text_DeliveryStatus(status));
                break;
        }
        log("I should be able to click on "+status+" link", "I could click on "+status+" link",false);
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @Then("^I should see the change status link \"([^\"]*)\"$")
    public void i_should_see_the_change_status_link_something(String changeStatusLink) throws Throwable {
        try{
        switch (changeStatusLink){
            case "Not Displayed":
                testStepVerify.isElementNotDisplayed(admin_RevivalPage.Link_ChangeDeliveryStatus(true),"Element should not be displayed","Element is not displayed","Element is displayed");
                break;
            case "Is Displayed":
                testStepAssert.isElementDisplayed(admin_RevivalPage.Link_ChangeDeliveryStatus(),"Element should be displayed","Element is displayed","Element is not displayed");
                break;
        }
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I select \"([^\"]*)\" as the reason from the reason dropdown$")
    public void i_select_something_as_the_reason_from_the_reason_dropdown(String changestatusreason) throws Throwable {
        try{
        action.click(admin_RevivalPage.DropDown_DeliveryStatusReason());
        switch (changestatusreason){
            case "Driver initiated":
            case "Customer initiated - other reason":
            case "Outside of delivery scope":
            case "Solo: Driver not found":
            case "Other":
                action.click(admin_RevivalPage.Text_DeliveryStatusReason(changestatusreason));
                break;
        }
            log("I should be able to click on "+changestatusreason+" option", "I could click on "+changestatusreason+" option",false);
        }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @Then("^I should be able to see the comment textbox displayed$")
    public void i_should_be_able_to_see_the_comment_textbox_displayed() throws Throwable {
        try{
        testStepVerify.isElementDisplayed(admin_RevivalPage.Textbox_CommentForStatus(),"Textbox should be displayed","Textbox is displayed","Textbox is not  displayed");
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I enter the text \"([^\"]*)\" in the textarea$")
    public void i_enter_the_text_something_in_the_textarea(String textmessage) throws Throwable {
        try{
        action.clearSendKeys(admin_RevivalPage.Textbox_CommentForStatus(), textmessage+ Keys.ENTER);
        Thread.sleep(3000);
        log("I should be able to enter the text "+textmessage+" in the textarea",
                "I could enter the text "+textmessage+" in the textarea",false);
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @Then("^The \"([^\"]*)\" should be in \"([^\"]*)\" state$")
    public void the_something_should_be_in_something_state(String adminPage ,String deliveryStatus) throws Throwable {
        try{
            switch (adminPage){
                case "Scheduled Deliveries":
                    switch (deliveryStatus){
                        case "Assigning Driver(s)":
                            Thread.sleep(3000);
                            String status = action.getText(admin_RevivalPage.Text_DeliveryStatus(12));
                            testStepVerify.isEquals(status,deliveryStatus,"Delivery Should be in " +deliveryStatus+ " state",
                                    "Delivery is  in " +status+ " state",
                                    "Delivery is not in " +deliveryStatus+ " state");
                            break;
                    }
                    break;
                case "All Deliveries":
                    switch (deliveryStatus){
                        case "Admin Canceled - No Driver(s) Found":
                        case "Partner Canceled":
                        case "Driver Canceled":
                            Thread.sleep(3000);
                            String status = action.getText(admin_RevivalPage.Text_DeliveryStatus(11));
                            testStepVerify.isEquals(status,deliveryStatus,"Delivery Should be in " +deliveryStatus+ " state",
                                    "Delivery is  in " +status+ " state",
                                    "Delivery is not in " +deliveryStatus+ " state");
                            break;
                    }
                    break;
            }
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
}

    @Then("^The amount should be \"([^\"]*)\" and in \"([^\"]*)\" state$")
    public void the_amount_should_be_something_and_in_something_state(String strArg1, String deliveryState) throws Throwable {
        try{
        String PickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        int paymentStatusTransaction = Integer.parseInt(new DbUtility().getPaymentTransactionType(PickupRequest));
        String paymentStatus = new DbUtility().getStatusMessage(PickupRequest);
        String isDriverPaid = new DbUtility().getDriverPaid(PickupRequest);
        testStepAssert.isTrue(isDriverPaid.equals("0"),"Driver paid should be false", "Driver paid is false","Driver paid is not in false state");
        testStepAssert.isTrue(paymentStatusTransaction==5,"Amount should be refunded", "Amount is refunded","Amount is not refunded");
        testStepAssert.isTrue(paymentStatus.equals(deliveryState),"Delivery should be in voided state", "Delivery is in voided state","Delivery is not in voided state");
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
}

    @Then("^The Below accessorial charges should be present in the db$")
    public void the_below_accessorial_charges_should_be_present_in_the_db(DataTable data) throws Throwable {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String excessWaitTimeAmount = dataMap.get("Excess Wait Time").trim();
        String cancelationAmount = dataMap.get("Cancelation").trim();
        String mountainousAmount = dataMap.get("Mountainous").trim();
        String otherAmount = dataMap.get("Other").trim();
        String PickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

        List<HashMap<String,Object>> isDriverPaid = new  DbUtility().getAccessorialAmount(PickupRequest);
        String DB_ExcessWaitTime_Amount = isDriverPaid.get(4).get("Amount").toString();
        String DB_Cancellation_Amount =isDriverPaid.get(3).get("Amount").toString();
        String DB_Mountainious_Amount =isDriverPaid.get(2).get("Amount").toString();
        String DB_Other_Amount =isDriverPaid.get(1).get("Amount").toString();

        testStepAssert.isEquals(DB_ExcessWaitTime_Amount,excessWaitTimeAmount,"Excess wait time charges should be present and not refunded","Excess wait time charges is present and not refunded","Excess wait time charges is not present in db");
        testStepAssert.isEquals(DB_Cancellation_Amount,cancelationAmount,"Cancellation charges should be present and not refunded","Cancellation charges is present and not refunded","Cancellation charges is not present in db");
        testStepAssert.isEquals(DB_Mountainious_Amount,mountainousAmount,"Mountainious charges should be present and not refunded","Mountainious charges is present and not refunded","Mountainious charges is not present in db");
        testStepAssert.isEquals(DB_Other_Amount,otherAmount,"Other charges should be present and not refunded","Other charges is present and not refunded","Other charges is not present in db");
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
}

}