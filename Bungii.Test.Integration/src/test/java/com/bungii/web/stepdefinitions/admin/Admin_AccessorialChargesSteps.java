package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Keys;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_AccessorialChargesSteps extends DriverBase {

    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_AccessorialChargesPage admin_accessorialChargesPage= new Admin_AccessorialChargesPage();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_AccessorialChargesSteps.class);
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();

    @When("^I add following accessorial charges and save it$")
    public void i_add_following_accessorial_charges_and_save_it(DataTable data) throws Throwable {

        try {
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                String amount = DataList.get(i).get("Amount").trim();
                String feeType = DataList.get(i).get("Fee Type").trim();
                String comment = DataList.get(i).get("Comment").trim();
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialAmount(), amount);
                action.selectElementByText(admin_accessorialChargesPage.DropDown_AccessorialFeeType(), feeType);
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_Comment(), comment);
                cucumberContextManager.setScenarioContext("NOTE",comment);
                action.click(admin_accessorialChargesPage.Button_Save());
                Thread.sleep(3000);
                i++;
            }
            log("I add following accessorial charges and save it", "I added field values in accessorial charges and saved it", false);

    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see \"([^\"]*)\" section displayed$")
    public void i_should_see_something_section_displayed(String section) throws Throwable {
        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Header_Section(),section, section+" should be displayed", section+" is displayed", section+" is not displayed");
        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Message_Mandatory(),"Fields marked with * are mandatory.", "Fields marked with * are mandatory. should be displayed", "Fields marked with * are mandatory. is displayed", "Fields marked with * are mandatory. is not displayed");
    }

    @Then("^I should see following details in the Accessorial charges section$")
    public void i_should_see_following_details_in_the_accessorial_charges_section(DataTable data) throws Throwable {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String excessWaitTimeAmount = dataMap.get("Excess Wait Time").trim();
        String cancelationAmount = dataMap.get("Cancelation").trim();
        String mountainousAmount = dataMap.get("Mountainous").trim();
        String otherAmount = dataMap.get("Other").trim();
        String totalAmount = dataMap.get("Total").trim();
        cucumberContextManager.setScenarioContext("TOTAL_AMOUNT", totalAmount);

        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Excess Wait Time"),excessWaitTimeAmount, "Excess Wait Time "+excessWaitTimeAmount+" should be displayed", excessWaitTimeAmount+" is displayed", excessWaitTimeAmount+" is not displayed");
        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Cancelation"),cancelationAmount, "Cancelation "+cancelationAmount+" should be displayed", cancelationAmount+" is displayed", cancelationAmount+" is not displayed");
        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Mountainous"),mountainousAmount, "Mountainous "+mountainousAmount+" should be displayed", mountainousAmount+" is displayed", mountainousAmount+" is not displayed");
        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Other"),otherAmount, "Other "+otherAmount+" should be displayed", otherAmount+" is displayed", otherAmount+" is not displayed");
        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRowTotal("Total"),totalAmount, "Total "+totalAmount+" should be displayed", totalAmount+" is displayed", totalAmount+" is not displayed");

    }

    @And("^I search the delivery of Customer and view it$")
    public void i_search_the_delivery_of_customerAndView() throws Throwable {
        try{
        String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        Thread.sleep(15000);
        action.clearSendKeys(admin_TripsPage.TextBox_Search(),pickuprequest+Keys.ENTER);
        ///////////////////
        Thread.sleep(5000);
        utility.resetGeofenceDropdown();
        Thread.sleep(5000);
        //String status = "Payment Successful";
        action.click(admin_TripsPage.findElement(String.format("//td[contains(.,'%s')]", customerName),PageBase.LocatorType.XPath));
        log("I search the delivery of Customer and view it","I searched the delivery of Customer and viewed it",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @And("^I search the delivery of Customer \"([^\"]*)\"$")
    public void i_search_the_delivery_of_customer_(String customer) throws Throwable {
        try{
        Thread.sleep(10000);
        action.clearSendKeys(admin_TripsPage.TextBox_Search(),customer+Keys.ENTER);
        Thread.sleep(10000);
        log("I search the delivery of Customer","I searched the delivery of Customer "+ customer,false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I search the delivery of Customer$")
    public void i_search_the_delivery_of_customer() throws Throwable {
        String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        Thread.sleep(10000);
        action.clearSendKeys(admin_TripsPage.TextBox_Search(),pickuprequest+Keys.ENTER);
        Thread.sleep(10000);
        log("I search the delivery of Customer","I searched the delivery of Customer",false);
    }
    @When("^I click on \"([^\"]*)\" button on Accessorial Charges$")
    public void i_click_on_something_button_on_accessorial_charges(String button) throws Throwable {
        try{
        switch(button.toUpperCase()) {
            case "SAVE":
            action.click(admin_accessorialChargesPage.Button_Save());
            break;

        }
        log("I click on"+button+" button on Accessorial Charges","I clicked on"+button+" button on Accessorial Charges",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I should get following error for following accessorial charges fields values when saved$")
    public void i_should_get_following_error_for_following_accessorial_charges_fields_values_when_saved(DataTable data) throws Throwable {
        try{
        List<Map<String, String>> DataList = data.asMaps();
        int i = 0;
        while (i < DataList.size()) {
            String amount = DataList.get(i).get("Amount").trim();
            String feeType = DataList.get(i).get("Fee Type").trim();
            String comment = DataList.get(i).get("Comment").trim();
            String field = DataList.get(i).get("Field").trim();

            String message = DataList.get(i).get("Message").trim();

            if(amount.equalsIgnoreCase("Blank")) {
                action.clear(admin_accessorialChargesPage.TextBox_AccessorialAmount());
            }
            else{
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialAmount(), amount);
            }
            if(feeType.equalsIgnoreCase("Blank")) {
                action.selectElementByText(admin_accessorialChargesPage.DropDown_AccessorialFeeType(), "-- Select Fee Type --");
            }
            else{
                action.selectElementByText(admin_accessorialChargesPage.DropDown_AccessorialFeeType(), feeType);
            }
            if(comment.equalsIgnoreCase("Blank")) {
                action.clear(admin_accessorialChargesPage.TextBox_Comment());
            }
            else {
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_Comment(), comment);
            }
            action.click(admin_accessorialChargesPage.Button_Save());

            switch(field.toUpperCase()) {

                case "AMOUNT":
                    testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Error_AccessorialFeeAmount(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                    break;
                case "FEE TYPE":
                    testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Error_AccessorialFeeType(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                    break;
                case "COMMENT":
                    testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Error_AccessorialFeeComment(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                    break;
            }
        i++;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^\"([^\"]*)\" should show total amount in the triprequest table in Database$")
    public void something_should_show_total_amount_in_the_triprequest_table_in_database(String strArg1) throws Throwable {
        try{
        String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String expectedTotal = (String) cucumberContextManager.getScenarioContext("TOTAL_AMOUNT");

        String actualTotalAmount = dbUtility.getAccessorialCharge(pickuprequest);
        testStepAssert.isEquals("$"+actualTotalAmount,expectedTotal, "Total "+expectedTotal+" should be displayed", expectedTotal+" is displayed", expectedTotal+" is not displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^\"([^\"]*)\" should show comment without quotes in the trippaymentdetails table in Database$")
    public void something_should_show_comment_without_quotes_in_the_trippaymentdetails_table_in_database(String strArg1) throws Throwable {
        try{
        String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String note = (String) cucumberContextManager.getScenarioContext("NOTE");

        String dbnote = dbUtility.getBusinessNotes(pickuprequest);
        testStepAssert.isEquals(dbnote,note, "Total "+note+" should be displayed", note+" is displayed", note+" is not displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }



}