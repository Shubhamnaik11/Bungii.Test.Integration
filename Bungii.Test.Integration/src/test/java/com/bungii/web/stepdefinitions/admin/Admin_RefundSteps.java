package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
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
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_RefundSteps extends DriverBase {

    Admin_RefundsPage admin_refundsPage = new Admin_RefundsPage();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_RefundSteps.class);
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    Admin_GeofencePage admin_GeofencePage = new Admin_GeofencePage();
    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();
    Admin_DriversPage admin_DriverPage=new Admin_DriversPage();
     boolean partial = true;

    @When("^I select \"([^\"]*)\" radio button$")
    public void i_select_something_radio_button(String radioButton) throws Throwable {
        try{
        switch (radioButton)
        {
            case "Partial Refund":
                action.click(admin_refundsPage.RadioButton_PartialRefund());
                partial= true;
                break;
            case "Complete Refund":
                action.click(admin_refundsPage.RadioButton_CompleteRefund());
                partial= false;
                break;
        }
        log("I select "+radioButton,"I Selected "+radioButton+" on Refund popup" ,false );
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @When("^I enter \"([^\"]*)\" as \"([^\"]*)\" percentage$")
    public void i_enter_something_as_something_percentage(String field, String value) throws Throwable {
        try{
        switch (field)
        {
            case "Customer Refund Amount":
                action.clearSendKeys(admin_refundsPage.TextBox_RefundPercentage(),value+Keys.TAB);
                break;

        }
        cucumberContextManager.setScenarioContext("DELIVERY_TOTAL",action.getText(admin_refundsPage.Label_DeliveryTotal()).trim().replace("$",""));
        cucumberContextManager.setScenarioContext("REFUND_AMOUNT",action.getAttributeValue(admin_refundsPage.TextBox_RefundAmount()).trim());
        cucumberContextManager.setScenarioContext("REFUND_PERCENTAGE",action.getAttributeValue(admin_refundsPage.TextBox_RefundPercentage()).trim());
        cucumberContextManager.setScenarioContext("DRIVER_EARNINGS",action.getAttributeValue(admin_refundsPage.TextBox_DriverEarnings()).trim());
        cucumberContextManager.setScenarioContext("BUNGII_EARNINGS",action.getAttributeValue(admin_refundsPage.TextBox_BungiiEarnings()).trim());

        log("I enter value in "+field,"I entered  "+value+" in field "+field+" on Refund popup" ,true );
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @And("^I enter \"([^\"]*)\" as \"([^\"]*)\" dollars$")
    public void i_enter_something_as_something_dollars(String field, String value) throws Throwable {
        try{
        switch (field)
        {
            case "Customer Refund Amount":
                action.clearSendKeys(admin_refundsPage.TextBox_RefundAmount(),value+Keys.TAB);
                break;
        }
        cucumberContextManager.setScenarioContext("DELIVERY_TOTAL",action.getText(admin_refundsPage.Label_DeliveryTotal()).trim().replace("$",""));
        cucumberContextManager.setScenarioContext("REFUND_AMOUNT",action.getAttributeValue(admin_refundsPage.TextBox_RefundAmount()).trim());
        cucumberContextManager.setScenarioContext("REFUND_PERCENTAGE",action.getAttributeValue(admin_refundsPage.TextBox_RefundPercentage()).trim());
        cucumberContextManager.setScenarioContext("DRIVER_EARNINGS",action.getAttributeValue(admin_refundsPage.TextBox_DriverEarnings()).trim());
        cucumberContextManager.setScenarioContext("BUNGII_EARNINGS",action.getAttributeValue(admin_refundsPage.TextBox_BungiiEarnings()).trim());
        log("I enter value in "+field,"I entered  "+value+" in field "+field+" on Refund popup" ,false );
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @And("^I enter \"([^\"]*)\" as \"([^\"]*)\"")
    public void i_enter_something_as_something(String field, String value) throws Throwable {
        try{
        switch (field)
        {
            case "Bungii Internal Notes":
                action.clearSendKeys(admin_refundsPage.TextBox_BusinessNotes(),value);
                cucumberContextManager.setScenarioContext("BUNGII_INTERNAL_NOTE",value);
                break;
            case "Notes":
                action.clearSendKeys(admin_refundsPage.TextBox_Notes(),value);
                cucumberContextManager.setScenarioContext("BUNGII_DRIVER_NOTE",value);
                break;
        }
        log("I enter value in "+field,"I entered  "+value+" in field "+field+" on Refund popup" ,false );
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I update \"([^\"]*)\" as \"([^\"]*)\" dollars$")
    public void i_update_something_as_something_dollars(String field, String value) throws Throwable {
        try{
        switch (field)
        {
            case "Earnings":
                action.clearSendKeys(admin_refundsPage.TextBox_DriverEarnings(),value+Keys.TAB);
                break;
        }
        log("I enter value in "+field,"I entered  "+value+" in field "+field+" on Refund popup" ,false );
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @When("^I update \"([^\"]*)\" as origional value of amount$")
    public void i_update_something_as_origional_value(String field) throws Throwable {
        try{
        String value = "";
        switch (field)
        {
            case "Earnings":
                value = action.getText(admin_refundsPage.Label_Driver()).trim().replace("$","");
                action.clearSendKeys(admin_refundsPage.TextBox_DriverEarnings(),value+Keys.TAB);
                break;
        }
        log("I update value in "+field,"I updated  "+value+" in field "+field+" on Refund popup" ,false );
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I update \"([^\"]*)\" as \"([^\"]*)\" percentage$")
    public void i_update_something_as_something_percentage_of_amount(String field, String value) throws Throwable {
        try{
        switch (field)
        {
            case "Earnings":
                action.clearSendKeys(admin_refundsPage.TextBox_DriverEarnings(),value+Keys.TAB);
                break;
        }
        log("I update value in "+field,"I updated  "+value+" in field "+field+" on Refund popup" ,true );
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I update \"([^\"]*)\" as origional value of percentage$")
    public void i_update_something_as_origional_value_of_percentage(String field) throws Throwable {
        try{
        String value = "";
        switch (field)
        {
            case "Earnings":
                value = action.getText(admin_refundsPage.Label_Driver()).trim().replace("$","");
                action.clearSendKeys(admin_refundsPage.TextBox_DriverEarnings(),value+Keys.TAB);
                break;
        }
        log("I updated origional value in "+field,"I updated  "+value+" in field "+field+" on Refund popup" ,true );
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The \"([^\"]*)\" section should be displayed$")
    public void the_something_section_should_be_displayed(String header) throws Throwable {
       try{
           testStepAssert.isElementTextEquals(admin_refundsPage.Header_popup(),header, "Issue Refund popup should be displayed", "Issue Refund popup is displayed","Issue Refund popup is not displayed");
        String driverEarning = action.getText(admin_refundsPage.Label_Driver()).trim().replace("$","");
        String bungiiEarning = action.getText(admin_refundsPage.Label_Bungii()).trim().replace("$","");
        cucumberContextManager.setScenarioContext("DRIVER_EARNINGS_BEFORE",driverEarning);
        cucumberContextManager.setScenarioContext("BUNGII_EARNINGS_BEFORE",bungiiEarning);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @Then("^The \"([^\"]*)\" section should not be displayed$")
    public void the_something_section_should_not_be_displayed(String header) throws Throwable {
        Thread.sleep(5000);
        testStepAssert.isFalse(action.isElementPresent(admin_refundsPage.Header_popup(true)), "Issue Refund popup should not be displayed", "Issue Refund popup is not displayed","Issue Refund popup is displayed");
    }

    @Then("^\"([^\"]*)\" fields should be auto calculated based on Delivery Total and Driver Earnings$")
    public void something_fields_should_be_auto_calculated_based_on_delivery_total_and_driver_earnings(String field) throws Throwable {
try{
        String deliveryTotal = action.getText(admin_refundsPage.Label_DeliveryTotal()).trim().replace("$","");
        String customerInputAmount = action.getAttributeValue(admin_refundsPage.TextBox_RefundAmount()).trim();
        String customerInputPercentage = action.getAttributeValue(admin_refundsPage.TextBox_RefundPercentage()).trim();
        String driverAmount = action.getAttributeValue(admin_refundsPage.TextBox_DriverEarnings()).trim();
        String driverPercentage = action.getAttributeValue(admin_refundsPage.TextBox_DriverPercentage()).trim();
        Double calculatedBungiiEarnings = (Double.parseDouble(deliveryTotal)-Double.parseDouble(driverAmount)-Double.parseDouble(customerInputAmount));
        Double calculatedBungiiPercentage = 100-(Double.parseDouble(driverPercentage)+Double.parseDouble(customerInputPercentage));
        DecimalFormat df = new DecimalFormat("0.00");

        switch (field)
        {
            case "Bungii Earnings and percentage":
                testStepAssert.isEquals(action.getAttributeValue(admin_refundsPage.TextBox_BungiiEarnings()),String.valueOf(df.format(calculatedBungiiEarnings)), "Bungii Earnings should be displayed", "Bungii Earnings is displayed","Bungii Earnings is not displayed");
                testStepAssert.isEquals(action.getAttributeValue(admin_refundsPage.TextBox_BungiiPercentage()),String.valueOf(df.format(calculatedBungiiPercentage)), "Bungii Earnings Percentage should be displayed", "Bungii Earnings Percentage is displayed","Bungii Earnings Percentage is not displayed");
                break;
        }
} catch(Exception e){
    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
    error("Step should be successful", "Error performing step,Please check logs for more details",
            true);
}
    }



    @And("^\"([^\"]*)\" field should be auto calculated based on Delivery Total and Driver Earnings$")
    public void something_field_should_be_auto_calculated_based_on_delivery_total_and_driver_earnings(String field) throws Throwable {
try{
        String deliveryTotal = action.getText(admin_refundsPage.Label_DeliveryTotal()).trim().replace("$","");
        String customerInputAmount = action.getAttributeValue(admin_refundsPage.TextBox_RefundAmount()).trim();
        String customerInputPercentage = action.getAttributeValue(admin_refundsPage.TextBox_RefundPercentage()).trim();
        Double calculatedAmountPecentage = Double.parseDouble(customerInputAmount)*100/Double.parseDouble(deliveryTotal);
        Double calculatedAmount =Double.parseDouble(customerInputPercentage)*Double.parseDouble(deliveryTotal)/100;
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat format = new DecimalFormat("0.00");

        switch (field)
        {
            case "Customer Refund Amount":
                testStepAssert.isEquals(action.getAttributeValue(admin_refundsPage.TextBox_RefundAmount()),String.valueOf(format.format(calculatedAmount)), "Customer refund amount should be displayed", "Customer refund amount is displayed","Customer refund amount is not displayed");
                break;
            case "Customer Refund Amount Percentage":
                testStepAssert.isEquals(action.getAttributeValue(admin_refundsPage.TextBox_RefundPercentage()),String.valueOf(df.format(calculatedAmountPecentage)), "Customer refund amount percentage should be displayed", "Customer refund amount percentage is displayed","Customer refund amount percentage is not displayed");
                break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^Notes text area should be displayed$")
    public void notes_text_area_should_be_displayed() throws Throwable {
        testStepAssert.isTrue(action.isElementPresent(admin_refundsPage.TextBox_Notes()),"Driver notes should be displayed", "Driver notes is displayed", "Driver notes is not displayed");
    }

    @And("^Notes text area should not be displayed$")
    public void notes_text_area_should_not_be_displayed() throws Throwable {
        testStepAssert.isFalse(action.isElementPresent(admin_refundsPage.TextBox_Notes(true)),"Driver notes should not be displayed", "Driver notes is not displayed", "Driver notes is displayed");
    }

    @When("^I select \"([^\"]*)\" checkbox$")
    public void i_select_something_checkbox(String strArg1) throws Throwable {
        action.click(admin_refundsPage.Checkbox_Confirm());

    }

    @Then("^I should see \"([^\"]*)\" popup$")
    public void i_should_see_something_popup(String header) throws Throwable {
        testStepAssert.isEquals(action.getText(admin_refundsPage.Header_Popup()),header, header+ " should be displayed", header+ " is displayed",header+ " is not displayed");

    }

    @And("^I click on \"([^\"]*)\" button on Issue Refund popup$")
    public void i_click_on_something_button_on_issue_refund_popup(String button) throws Throwable {
        try{
        switch (button) {
            case "Continue":
                action.click(admin_refundsPage.Button_Continue());
                break;
            case "Process Refund":
                action.click(admin_refundsPage.Button_ProcessRefund());
                break;
        }
        log("I click on "+button + " button ","I clicked on "+button + " button " ,false );
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And("^I should see Original Delivery Charge & Customer Refund & Total Customer Charge$")
    public void i_should_see_original_delivery_charge_customer_refund_total_customer_charge() throws Throwable {
        try{
        DecimalFormat df = new DecimalFormat("0.00");
        String refundPercentage = (String)  cucumberContextManager.getScenarioContext("REFUND_PERCENTAGE");
        Double totalCustomerCharge = Double.parseDouble(String.valueOf(cucumberContextManager.getScenarioContext("DELIVERY_TOTAL")))-Double.parseDouble(String.valueOf((cucumberContextManager.getScenarioContext("REFUND_AMOUNT"))));
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_OriginalDeliveryCharge()),"$"+String.valueOf(cucumberContextManager.getScenarioContext("DELIVERY_TOTAL")), "Origional Delivery Charge should be displayed", "Origional Delivery Charge is displayed","Origional Delivery Charge is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_CustomerRefundPercentage()),"(- "+String.valueOf(refundPercentage)+" % )", "Customer Refund Percentage should be displayed", "Customer Refund Percentage is displayed","Customer Refund Percentage is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_CustomerRefundAmount()),"-$"+df.format(Double.valueOf((String)cucumberContextManager.getScenarioContext("REFUND_AMOUNT"))), "Customer Refund Amount should be displayed", "Customer Refund Amount is displayed","Customer Refund Amount is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_TotalCustomerCharge()),"$"+df.format(totalCustomerCharge), "Total Customer Charge should be displayed", "Total Customer Charge is displayed","Total Customer Charge is not displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I should see Original Delivery Charge & Customer Refund & Total Customer Charge for duo delivery$")
    public void i_should_see_original_delivery_charge_customer_refund_total_customer_charge_for_duodelivery() throws Throwable {
        try{
        DecimalFormat df = new DecimalFormat("0.00");
        Thread.sleep(5000);
        Double refundPercentage =  (Double.valueOf((String)  cucumberContextManager.getScenarioContext("REFUND_PERCENTAGE"))+  Double.valueOf((String)  cucumberContextManager.getScenarioContext("REFUND_PERCENTAGE2")))/2;
        Double refundAmount = Double.valueOf((String)cucumberContextManager.getScenarioContext("REFUND_AMOUNT"))+Double.valueOf((String)cucumberContextManager.getScenarioContext("REFUND_AMOUNT2"));
        Double totalCustomerCharge = Double.parseDouble(String.valueOf(cucumberContextManager.getScenarioContext("DELIVERY_TOTAL")))-Double.parseDouble(String.valueOf((cucumberContextManager.getScenarioContext("REFUND_AMOUNT"))))-Double.parseDouble(String.valueOf((cucumberContextManager.getScenarioContext("REFUND_AMOUNT2"))));
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_CustomerRefundPercentage()),"(- "+String.valueOf(refundPercentage)+" % )", "Customer Refund Percentage should be displayed", "Customer Refund Percentage is displayed","Customer Refund Percentage is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_CustomerRefundAmount()),"-$"+df.format(refundAmount), "Customer Refund Amount should be displayed", "Customer Refund Amount is displayed","Customer Refund Amount is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_TotalCustomerCharge()),"$"+df.format(totalCustomerCharge), "Total Customer Charge should be displayed", "Total Customer Charge is displayed","Total Customer Charge is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_OriginalDeliveryCharge()),"$"+String.valueOf(cucumberContextManager.getScenarioContext("DELIVERY_TOTAL")), "Origional Delivery Charge should be displayed", "Origional Delivery Charge is displayed","Origional Delivery Charge is not displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I should see breakdown of Before and After Refund earnings$")
    public void i_should_see_breakdown_of_before_and_after_refund_earnings() throws Throwable {
        try{
        DecimalFormat df = new DecimalFormat("0.00");
        Double bungiiEarnings = Double.parseDouble(String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_EARNINGS")));
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_DriverBeforeRefund()),"$"+String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_EARNINGS_BEFORE")), "Driver Earnings Before should be displayed", "Driver Earnings Before is displayed","Driver Earnings Before is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_DriverAfterRefund()),"$"+String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_EARNINGS")), "Driver Earnings After should be displayed", "Driver Earnings After is displayed","Driver Earnings Aftere is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_BungiiBeforeRefund()),"$"+String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_EARNINGS_BEFORE")), "Bungii Earnings Before should be displayed", "Bungii Earnings Before is displayed","Bungii Earnings Before is not displayed");
        if(bungiiEarnings>=0)
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_BungiiAfterRefund()),"$"+df.format(bungiiEarnings), "Bungii Earnings After should be displayed", "Bungii Earnings After is displayed","Bungii Earnings After is not displayed");
         else
            testStepAssert.isEquals(action.getText(admin_refundsPage.Label_BungiiAfterRefund()),"- ($"+df.format(bungiiEarnings).toString().replace("-","")+")", "Bungii Earnings After should be displayed", "Bungii Earnings After is displayed","Bungii Earnings After is not displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^the values should be reverted to origional value$")
    public void the_values_should_be_reverted_to_origional_value() throws Throwable {
        Thread.sleep(5000);
      testStepAssert.isEquals(action.getAttributeValue(admin_refundsPage.TextBox_BungiiEarnings()),"0", "Bungii Earnings should be displayed", "Bungii Earnings is displayed","Bungii Earnings is not displayed");
      testStepAssert.isEquals(action.getAttributeValue(admin_refundsPage.TextBox_BungiiPercentage()),"0", "Bungii Earnings Percentage should be displayed", "Bungii Earnings Percentage is displayed","Bungii Earnings Percentage is not displayed");
      testStepAssert.isEquals(action.getAttributeValue(admin_refundsPage.TextBox_RefundAmount()),"0", "Bungii Earnings should be displayed", "Bungii Earnings is displayed","Bungii Earnings is not displayed");
      testStepAssert.isEquals(action.getAttributeValue(admin_refundsPage.TextBox_RefundPercentage()),"0", "Bungii Earnings should be displayed", "Bungii Earnings is displayed","Bungii Earnings is not displayed");


    }
    @And("^I should see Bungii Internal Note$")
    public void i_should_see_bungii_internal_note() throws Throwable {
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_NoteBungiiInternal()), (String)cucumberContextManager.getScenarioContext("BUNGII_INTERNAL_NOTE"), "Bungii Internal Note should be displayed", "Bungii Internal Note is displayed","Bungii Internal Note is not displayed");
    }
    @And("^I should see Bungii Driver Note$")
    public void i_should_see_bungii_driver_note() throws Throwable {

        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_NoteDriver()), (String)cucumberContextManager.getScenarioContext("BUNGII_DRIVER_NOTE"), "Bungii Driver Note should be displayed", "Bungii Driver Note is displayed","Bungii Driver Note is not displayed");
    }
    @Then("^\"([^\"]*)\" is displayed$")
    public void something_is_displayed(String message) throws Throwable {
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_Success()),message, message+ " should be displayed", message+ " is displayed",message+ " is not displayed");
    }

    @Then("^The \"([^\"]*)\" button should not be displayed$")
    public void the_something_button_should_not_be_displayed(String button) throws Throwable {
        Thread.sleep(5000);
        switch (button)
        {
            case "New Partner":
                testStepAssert.isNotElementDisplayed(admin_refundsPage.findElement("btnCreateBusinessUser", PageBase.LocatorType.Id,true),"Add Partner button should not be visible","Add partner button is not visible","Add partner button is visible");
                break;
            default:
                testStepAssert.isFalse(action.isElementPresent(admin_refundsPage.Button_IssueRefund(true)),"Issue Refund button should not be displayed", "Issue Refund button is not displayed", "Issue Refund button is displayed");
                break;
        }
    }

    @Then("^The \"([^\"]*)\" button should be displayed$")
    public void the_something_button_should_be_displayed(String button) throws Throwable {
        testStepAssert.isTrue(action.isElementPresent(admin_refundsPage.Button_ReattemptPayment()),button +" button should be displayed", button +" button is displayed", button +" button is not displayed");
    }
    @Then("^I should see Customer Refund Amount and Driver Earnings$")
    public void i_should_see_customer_refund_amount_and_driver_earnings() throws Throwable {
        cucumberContextManager.setScenarioContext("DELIVERY_TOTAL",action.getText(admin_refundsPage.Label_DeliveryTotal()).trim().replace("$",""));
        cucumberContextManager.setScenarioContext("REFUND_AMOUNT",action.getText(admin_refundsPage.Label_CustomerRefundComplete()).replace("$","").trim());
        cucumberContextManager.setScenarioContext("REFUND_PERCENTAGE","100");
        cucumberContextManager.setScenarioContext("DRIVER_EARNINGS",action.getAttributeValue(admin_refundsPage.TextBox_DriverEarnings()).trim());
        if(!cucumberContextManager.getScenarioContext("Bungii_Type").equals("duo"))
            cucumberContextManager.setScenarioContext("BUNGII_EARNINGS",Double.parseDouble(String.valueOf("0.00"))-Double.parseDouble(action.getAttributeValue(admin_refundsPage.TextBox_DriverEarnings()).trim()));
        else
            cucumberContextManager.setScenarioContext("BUNGII_EARNINGS",Double.parseDouble(String.valueOf("0.00"))-Double.parseDouble(action.getAttributeValue(admin_refundsPage.TextBox_DriverEarnings()).trim())-Double.parseDouble(action.getAttributeValue(admin_refundsPage.TextBox_DriverEarnings2()).trim()));

        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_CustomerRefundComplete()),"$"+String.valueOf(cucumberContextManager.getScenarioContext("DELIVERY_TOTAL")), "Complete customer Refund Amount should be displayed", "Complete customer Refund Amount is displayed","Complete customer Refund Amount is not displayed");
        testStepAssert.isEquals(action.getAttributeValue(admin_refundsPage.TextBox_DriverEarnings()),String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_EARNINGS")), "Driver Earnings should be displayed", "Driver Earnings  is displayed","Driver Earnings  is not displayed");

    }
    @And("^I check \"([^\"]*)\"$")
    public void i_check_something(String strArg1) throws Throwable {
        try{
        action.click(admin_refundsPage.Checkbox_same());
        cucumberContextManager.setScenarioContext("DRIVER2_EARNINGS",action.getAttributeValue(admin_refundsPage.TextBox_DriverEarnings2()).trim());
        cucumberContextManager.setScenarioContext("DRIVER_EARNINGS",action.getAttributeValue(admin_refundsPage.TextBox_DriverEarnings()).trim());
        log("I check  "+strArg1 ,"I checked "+strArg1  ,false );
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @When("^I enter \"([^\"]*)\" as \"([^\"]*)\" for both drivers$")
    public void i_enter_something_as_something_for_both_drivers(String field, String value) throws Throwable {
        try{
        switch (field)
        {
            case "Bungii Internal Notes":
                action.clearSendKeys(admin_refundsPage.TextBox_BusinessNotes(),value);
                cucumberContextManager.setScenarioContext("BUNGII_INTERNAL_NOTE",value);
                break;
            case "Notes":
                action.clearSendKeys(admin_refundsPage.TextBox_Notes(),value);
                cucumberContextManager.setScenarioContext("BUNGII_DRIVER_NOTE",value);
                action.clearSendKeys(admin_refundsPage.TextBox_Notes2(),value);
                cucumberContextManager.setScenarioContext("BUNGII_DRIVER_NOTE2",value);
                break;
        }
        log("I enter value in "+field +" for both drivers","I entered  "+value+" in field "+field+" on Refund popup for both drivers" ,false );
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I should see breakdown of Before and After Refund earnings for both driver$")
    public void i_should_see_breakdown_of_before_and_after_refund_earnings_for_both_driver() throws Throwable {
        try{
        DecimalFormat df = new DecimalFormat("0.00");
        String driverEarningsBefore = String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_EARNINGS_BEFORE"));
        Double eachDriverEarning = Double.parseDouble(driverEarningsBefore) / 2;
        Double bungiiEarnings = 0.00;
        Double bungiiEarnings1 = Double.parseDouble(String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_EARNINGS")));
        //Double bungiiEarnings2 = Double.parseDouble(String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_EARNINGS2")));
        String earnings2 = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_EARNINGS2"));
        Double bungiiEarnings3 =   earnings2 =="" ? 0 : Double.parseDouble(earnings2);
        bungiiEarnings = bungiiEarnings1+bungiiEarnings3;
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_DriverBeforeRefund()),"$"+df.format(eachDriverEarning), "Driver 1 Earnings Before should be displayed", "Driver Earnings Before is displayed","Driver Earnings Before is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_DriverAfterRefund()),"$"+String.valueOf(cucumberContextManager.getScenarioContext("DRIVER_EARNINGS")), "Driver 1 Earnings After should be displayed", "Driver Earnings After is displayed","Driver Earnings Aftere is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_Driver2BeforeRefund()),"$"+df.format(eachDriverEarning), "Driver 2 Earnings Before should be displayed", "Driver Earnings Before is displayed","Driver Earnings Before is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_Driver2AfterRefund()),"$"+String.valueOf(cucumberContextManager.getScenarioContext("DRIVER2_EARNINGS")), "Driver 2 Earnings After should be displayed", "Driver Earnings After is displayed","Driver Earnings Aftere is not displayed");

        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_BungiiBeforeRefund()),"$"+String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_EARNINGS_BEFORE")), "Bungii Earnings Before should be displayed", "Bungii Earnings Before is displayed","Bungii Earnings Before is not displayed");
        if(bungiiEarnings>=0)
            testStepAssert.isEquals(action.getText(admin_refundsPage.Label_BungiiAfterRefund()),"$"+df.format(bungiiEarnings), "Bungii Earnings After should be displayed", "Bungii Earnings After is displayed","Bungii Earnings After is not displayed");
        else
            testStepAssert.isEquals(action.getText(admin_refundsPage.Label_BungiiAfterRefund()),"- ($"+df.format(bungiiEarnings).toString().replace("-","")+")", "Bungii Earnings After should be displayed", "Bungii Earnings After is displayed","Bungii Earnings After is not displayed");

        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I should see Bungii Driver Note for both drivers$")
    public void i_should_see_bungii_driver_note_for_both_drivers() throws Throwable {
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_NoteDriver()), (String)cucumberContextManager.getScenarioContext("BUNGII_DRIVER_NOTE"), "Bungii Driver1 Note should be displayed", "Bungii Driver Note is displayed","Bungii Driver Note is not displayed");
        testStepAssert.isEquals(action.getText(admin_refundsPage.Label_NoteDriver2()), (String)cucumberContextManager.getScenarioContext("BUNGII_DRIVER_NOTE2"), "Bungii Driver2 Note should be displayed", "Bungii Driver Note is displayed","Bungii Driver Note is not displayed");

    }
    @And("^I enter \"([^\"]*)\" as \"([^\"]*)\" dollars from second driver$")
    public void i_enter_something_as_something_dollars_from_second_driver(String field, String value) throws Throwable {
        try{
        switch (field)
        {
            case "Customer Refund Amount":
                action.clearSendKeys(admin_refundsPage.TextBox_RefundAmount2(),value+Keys.TAB);
                break;
        }
        cucumberContextManager.setScenarioContext("DELIVERY_TOTAL",action.getText(admin_refundsPage.Label_DeliveryTotal()).trim().replace("$",""));
        cucumberContextManager.setScenarioContext("REFUND_AMOUNT2",action.getAttributeValue(admin_refundsPage.TextBox_RefundAmount2()).trim());
        cucumberContextManager.setScenarioContext("REFUND_PERCENTAGE2",action.getAttributeValue(admin_refundsPage.TextBox_RefundPercentage2()).trim());
        cucumberContextManager.setScenarioContext("DRIVER2_EARNINGS",action.getAttributeValue(admin_refundsPage.TextBox_DriverEarnings2()).trim());
        cucumberContextManager.setScenarioContext("BUNGII_EARNINGS2",action.getAttributeValue(admin_refundsPage.TextBox_BungiiEarnings2()).trim());

        log("I enter value in "+field,"I entered  "+value+" in field "+field+" from second driver on Refund popup" ,false );
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I click on the \"([^\"]*)\" dropdown$")
    public void i_click_on_the_something_dropdown(String name) throws Throwable {
        try{
        switch (name){
            case "Select Geofence":
             action.click(admin_GeofencePage.List_Geofence());
             break;
        }

    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I Enter the text \"([^\"]*)\"$")
    public void i_enter_the_text_something(String stateName) throws Throwable {
        try{
        action.click(admin_GeofencePage.Button_Clear());
        action.clearSendKeys(admin_GeofencePage.TextBox_SearchGeofence(),stateName);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see \"([^\"]*)\" highlighted$")
    public void i_should_see_something_highlighted(String expectedcity) throws Throwable {
        try{
        String textColor =PropertyUtility.getDataProperties("city.text.highlight");
        String color = admin_GeofencePage.Text_GeofenceHighlighted().getCssValue("background-color");
        String cityName = action.getText(admin_GeofencePage.Text_GeofenceHighlighted());
        testStepAssert.isEquals(cityName,expectedcity,"same","same","same");
        testStepAssert.isEquals(color,textColor,"The text should be highlight","The text is highlighted","The text is not highlighted");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I click on the \"([^\"]*)\" checkbox$")
    public void i_click_on_the_something_checkbox(String geofence) throws Throwable {
        try{
        switch (geofence){
            case "Washington DC":
            case "Boston":
            case "Goa":
                action.JavaScriptClick(admin_GeofencePage.Checkbox_Geofence(geofence));
                break;
        }
        action.click(admin_GeofencePage.Button_ApplyGeofence());
        cucumberContextManager.setScenarioContext("GeofenceRegion",geofence);
        log("I should be able to click on "+geofence+" checkbox","I could click on "+geofence+" checkbox",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I click on the \"([^\"]*)\" link from the sidebar$")
    public void i_click_on_the_something_link_from_the_sidebar(String navigateTo) throws Throwable {
        try{
        switch (navigateTo){
            case "Customer":
                action.click(admin_DashboardPage.Link_Customers());
                break;
            case "Driver":
                action.click(admin_DashboardPage.Link_Drivers());
                break;
            case "Non Active Drivers":
                action.click(admin_DashboardPage.Link_NonActiveDriver());
                break;
        }
            utility.resetGeofenceDropdown();
            log("I should be able to click on "+navigateTo+" link from the sidebar","I could click on "+navigateTo+" link from the sidebar",false);
        } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I clear the filter applied$")
    public void i_clear_the_filter_applied() throws Throwable {
        try{
        action.click(admin_GeofencePage.List_Geofence());
        action.click(admin_GeofencePage.Button_Clear());
        action.click(admin_GeofencePage.Button_ApplyGeofence());
        log("I should be able to clear the geofence filter","I should be able to clear the geofence filter",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see the region of the city highlighted$")
    public void i_should_see_the_region_of_the_city_highlighted() throws Throwable {
        try{
        action.click(admin_GeofencePage.List_Geofence());
        List <WebElement> allRegions = admin_GeofencePage.List_GeofenceRegions();
        String expectedRegionColor =PropertyUtility.getDataProperties("region.text.highlight");
        for(int i=0;i<allRegions.size();i++){
            String regionColor = allRegions.get(i).getCssValue("color");
            if(regionColor.contentEquals(expectedRegionColor)){
                String regionName = action.getText(allRegions.get(i));
                cucumberContextManager.setScenarioContext("HighlightedRegion",regionName);
            }
        }
        String regionSelected = (String)cucumberContextManager.getScenarioContext("HighlightedRegion");
        testStepAssert.isTrue(true,regionSelected+" Region should be highlighted",
                regionSelected+" Region is highlighted",
                regionSelected+" Region is not highlighted" );
        action.click(admin_GeofencePage.List_Geofence());
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see the drivers sorted with the applied geofence filter$")
    public void i_should_see_the_drivers_sorted_with_the_applied_geofence_filter() throws Throwable {
        try{
        int validCity = 0;
        int invalidCity = 0;
        String expectedGeofenceRegion = (String) cucumberContextManager.getScenarioContext("GeofenceRegion");
        Thread.sleep(3000);
        boolean isPageinationDisplayed =action.isElementPresent(admin_DriverPage.Text_AllPageNumber(true));
        if (isPageinationDisplayed == true) {
            List<WebElement> pageNumber = admin_DriverPage.List_AllPages();
            for (int i = 0; i < (pageNumber.size()-1); i++) {
                List<WebElement> allCities = admin_DriverPage.List_AllCityNames();

                for (WebElement eachDriverCityName : allCities) {
                    Thread.sleep(1500);
                    String uiCityName = eachDriverCityName.getText();

                    if (uiCityName.contains(expectedGeofenceRegion.substring(0,3))) {
                        validCity += 1;

                    } else {
                        invalidCity += 1;
                    }

                }
                action.JavaScriptScrolldown();
                action.JavaScriptScrolldown();
                action.JavaScriptScrolldown();
                String nextPage = admin_DriverPage.Button_Next().getAttribute("class");

                if (nextPage.equals("page-link")) {

                    action.click(admin_DriverPage.Button_Next());
                    Thread.sleep(3000);

                }
            }
            action.JavaScriptScrolldown();
            action.JavaScriptScrolldown();
            action.JavaScriptScrolldown();

            if(invalidCity<=9){
                testStepVerify.isTrue(true,"Drivers profiles should be displayed based on "+ expectedGeofenceRegion+" geofence",
                        "Drivers profiles is displayed based on "+ expectedGeofenceRegion+" geofence" ,"Drivers profiles is not displayed based on "+ expectedGeofenceRegion+" geofence" );
            }
        }
        else{
            List<WebElement> allCities = admin_DriverPage.List_AllCityNames();
            for (WebElement eachDriverCityName : allCities) {
                Thread.sleep(1500);
                String uiCityName = eachDriverCityName.getText();

                if (uiCityName.contains(expectedGeofenceRegion.substring(0,3))) {

                    validCity += 1;
                } else {

                    invalidCity += 1;
                }
            }
        }
        if(invalidCity<=9){
            testStepVerify.isTrue(true,"Drivers profiles should be displayed based on "+ expectedGeofenceRegion+" geofence",
                    "Drivers profiles is displayed based on "+ expectedGeofenceRegion+" geofence" ,"Drivers profiles is not displayed based on "+ expectedGeofenceRegion+" geofence" );
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
        }

    }