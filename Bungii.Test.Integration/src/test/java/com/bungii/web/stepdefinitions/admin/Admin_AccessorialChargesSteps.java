package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_DetailsPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.admin.Admin_AccessorialChargesPage;
import com.bungii.web.pages.admin.Admin_LiveTripsPage;
import com.bungii.web.pages.admin.Admin_TripsPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_AccessorialChargesSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(Admin_AccessorialChargesSteps.class);
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_LiveTripsPage admin_liveTripsPage = new Admin_LiveTripsPage();
    Admin_AccessorialChargesPage admin_accessorialChargesPage= new Admin_AccessorialChargesPage();
    Admin_TripsPage adminTripsPage = new Admin_TripsPage();
    Driver_DetailsPage Page_Driver_Details = new Driver_DetailsPage();
    Driver_DashboardPage driver_DashboardPage = new  Driver_DashboardPage();
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    Admin_LoginPage Page_AdminLogin = new Admin_LoginPage();
    ActionManager action = new ActionManager();
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
                String driver_cut = DataList.get(i).get("Driver Cut").trim();
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialAmount(), amount);
                action.selectElementByText(admin_accessorialChargesPage.DropDown_AccessorialFeeType(), feeType);
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialDriver1Cut(),driver_cut);
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_Comment(), comment);
                cucumberContextManager.setScenarioContext("NOTE",comment);
                action.click(admin_accessorialChargesPage.Button_Save());
                action.click(admin_accessorialChargesPage.Button_Confirm());
                Thread.sleep(7000);
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
        //removed in Sprint47
        //testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Message_Mandatory(),"Fields marked with * are mandatory.", "Fields marked with * are mandatory. should be displayed", "Fields marked with * are mandatory. is displayed", "Fields marked with * are mandatory. is not displayed");
    }
    @Then("^I should see the following fee type displayed$")
    public void i_should_see_the_following_fee_type_displayed(DataTable data) throws Throwable {
        try {
            Thread.sleep(1000);
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                Thread.sleep(1000);
                String feeType = DataList.get(i).get("Fee Type").trim();
                switch (feeType) {
                    case "Excess Wait Time":
                        Thread.sleep(2000);
                        String[] excessTimeCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(1)).split("-");
                        String excessWaitTimeText = excessTimeCharge[0].trim();
                        testStepAssert.isEquals(excessWaitTimeText,feeType,"Excess Wait time charges should match","Excess Wait time charges  match","Excess Wait time charges dont match");
                        break;
                    case "Cancelation":
                        Thread.sleep(2000);
                        String [] cancellationTimeCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(2)).split("-");
                        String cancelationText = cancellationTimeCharge[0].trim();
                        testStepAssert.isEquals(cancelationText,feeType,"Cancellation charges should match","Cancellation charges match","Cancellation charges dont should match");
                        break;
                    case "Mountainous":
                        Thread.sleep(2000);
                        String [] mountainiousCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(3)).split("-");
                        String mountainiousChargeText = mountainiousCharge[0].trim();
                        testStepAssert.isEquals(mountainiousChargeText,feeType,"Mountainious charges should match","Mountainious charges match","Mountainious charges dont match");
                        break;
                    case "Other":
                        Thread.sleep(2000);
                        String[] otherCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(4)).split("-");
                        String otherChargeText = otherCharge[0].trim();
                        testStepAssert.isEquals(otherChargeText,feeType,"Other charges should match","Other charges match","Other charges dont match");
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

    @Then("^I should see following details in the Accessorial charges section$")
    public void i_should_see_following_details_in_the_accessorial_charges_section(DataTable data) throws Throwable {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String excessWaitTimeAmount = dataMap.get("Excess Wait Time").trim();
        String cancelationAmount = dataMap.get("Cancelation").trim();
        String mountainousAmount = dataMap.get("Mountainous").trim();
        String otherAmount = dataMap.get("Other").trim();
        String totalAmount = dataMap.get("Total").trim();
        cucumberContextManager.setScenarioContext("TOTAL_AMOUNT", totalAmount);

        String[] excessTimeCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(1)).split("-");
        String actualExcessWaitTime = excessTimeCharge[1].trim();
        String [] cancellationTimeCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(2)).split("-");
        String actualCancelation = cancellationTimeCharge[1].trim();
        String [] mountainiousCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(3)).split("-");
        String actualMountainiousCharge = mountainiousCharge[1].trim();
        String[] otherCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(4)).split("-");
        String actualOtherCharge = otherCharge[1].trim();
        Thread.sleep(2000);
        testStepAssert.isEquals(actualExcessWaitTime,excessWaitTimeAmount,"Expected excess time charges should match the Actual excess time charges","Expected excess time charges matches the Actual excess time charges","Expected excess time charges doesnt match the Actual excess time charges");
        testStepAssert.isEquals(actualCancelation,cancelationAmount,"Expected cancellation charges should match the Actual cancellation charges","Expected cancellation charges matches match the Actual cancellation charges","Expected cancellation charges doesnt match the Actual cancellation charges");
        testStepAssert.isEquals(actualMountainiousCharge,mountainousAmount,"Expected mountainious charges should match the Actual mountainious charges","Expected mountainious charges matches the Actual mountainious charges","Expected mountainious charges doesnt match the Actual mountainious charges");
        testStepAssert.isEquals(actualOtherCharge,otherAmount,"Expected other charges should match the Actual other charges","Expected other charges matches the Actual other charges","Expected other charges doesnt match the Actual other charges");

//        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Excess Wait Time"),excessWaitTimeAmount, "Excess Wait Time "+excessWaitTimeAmount+" should be displayed", excessWaitTimeAmount+" is displayed", excessWaitTimeAmount+" is not displayed");
//        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Cancelation"),cancelationAmount, "Cancelation "+cancelationAmount+" should be displayed", cancelationAmount+" is displayed", cancelationAmount+" is not displayed");
//        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Mountainous"),mountainousAmount, "Mountainous "+mountainousAmount+" should be displayed", mountainousAmount+" is displayed", mountainousAmount+" is not displayed");
//        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Other"),otherAmount, "Other "+otherAmount+" should be displayed", otherAmount+" is displayed", otherAmount+" is not displayed");
//        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRowTotal("Total"),totalAmount, "Total "+totalAmount+" should be displayed", totalAmount+" is displayed", totalAmount+" is not displayed");

    }

    @And("^I click on the Accessorial Charges links and I should see the Drivers cut displayed$")
    public void i_click_on_the_accessorial_charges_links_and_i_should_see_the_drivers_cut_displayed(DataTable data) throws Throwable {
        try {
            Thread.sleep(1000);
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                String feeType = DataList.get(i).get("Fee Type").trim();
                String driverCut = DataList.get(i).get("Driver Cut").trim();
                switch (feeType) {
                    case "Excess Wait Time":
                        String excessWaitTime = feeType.replace(" ", "");
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(1));
                        Thread.sleep(2000);
                        cucumberContextManager.setScenarioContext("ExcessWaitCut", action.getText(admin_accessorialChargesPage.Text_DriverCut(excessWaitTime)).replace("Testdrivertywd_appledc_a_drva Driver's cut: $", ""));
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("ExcessWaitCut"), "Excess Wait time driver cut charges should match", "Excess Wait time driver cut charges match", "Excess Wait time driver cut charges dont match");
                        String entireDriverCutForExcessWaitime[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(excessWaitTime)).split("\\$");

                        String  properDriverCutForExcessWaitTime =entireDriverCutForExcessWaitime[1];
                        cucumberContextManager.setScenarioContext("ExcessWaitCut",properDriverCutForExcessWaitTime.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("ExcessWaitCut"), "Excess Wait time driver cut charges should match","Excess Wait time driver cut charges match","Excess Wait time driver cut charges dont match");
                        break;
                    case "Cancelation":
                        String cancellation= feeType.replace(" ", "");
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(2));
                        Thread.sleep(2000);

                        String entireDriverCutForCancelation[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(cancellation)).split("\\$");
                        String  properDriverCutForCancelation=entireDriverCutForCancelation[1];
                        cucumberContextManager.setScenarioContext("CancellationCut",properDriverCutForCancelation.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("CancellationCut"), "Cancelation driver cut charges should match","Cancelation driver cut charges match","Cancelation driver cut charges dont match");
                        break;
                    case "Mountainious":
                        String mountainous= feeType.replace(" ", "");
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(3));
                        Thread.sleep(2000);

                        String entireDriverCutForMountainous[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(mountainous)).split("\\$");
                        String properDriverCutForMountainous=entireDriverCutForMountainous[1];
                        cucumberContextManager.setScenarioContext("MountainousCut",properDriverCutForMountainous.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("MountainousCut"), "Mountainous driver cut charges should match","Mountainous driver cut charges match","Mountainous driver cut charges dont match");
                        break;
                    case "Other" :
                        String other= feeType.replace(" ", "") +"s";
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(4));
                        Thread.sleep(2000);

                        String entireDriverCutForOther[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(other)).split("\\$");
                        String properDriverCutValueForOther =entireDriverCutForOther[1];
                        cucumberContextManager.setScenarioContext("OtherCut",properDriverCutValueForOther.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("OtherCut"), "Other driver cut charges should match","Other driver cut charges match","Other driver cut charges dont match");
                        break;
                }
                i++;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @And("^I search the delivery of Customer and view it$")
    public void i_search_the_delivery_of_customerAndView() throws Throwable {
        try {
            String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            Thread.sleep(15000);
            action.clearSendKeys(admin_TripsPage.TextBox_Search(), pickuprequest + Keys.ENTER);
            ///////////////////
            Thread.sleep(5000);
            utility.resetGeofenceDropdown();
            Thread.sleep(5000);
            //String status = "Payment Successful";
            action.click(admin_TripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/img", customerName), PageBase.LocatorType.XPath));
            action.click(admin_TripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/ul/li/p[contains(text(),'Delivery Details')]", customerName), PageBase.LocatorType.XPath));

            log("I search the delivery of Customer and view it", "I searched the delivery of Customer and viewed it", false);
        } catch (Exception e) {
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
            String driver_cut = DataList.get(i).get("Driver Cut").trim();


            String message = DataList.get(i).get("Message").trim();

            if(amount.equalsIgnoreCase("Blank")) {
                action.clear(admin_accessorialChargesPage.TextBox_AccessorialAmount());
            }
            else{
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialAmount(), amount);
            }
            if(driver_cut.equalsIgnoreCase("Blank")){
                action.clear(admin_accessorialChargesPage.TextBox_AccessorialDriver1Cut());
            }else{
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialDriver1Cut(),driver_cut);
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
                case "Driver Amount":
                    testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Error_AccessorialFeeDriverCut(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
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

    @Then("^I should see the delivery highlighted in \"([^\"]*)\"$")
    public void i_should_see_the_delivery_highlighted_in_something(String color) throws Throwable {
        try {
            String expectedHighlightColor="";
            switch(color.toLowerCase()){
                case "red":
                 expectedHighlightColor = "rgba(254, 201, 166, 1)";
                    break;
                case "blue":
                     expectedHighlightColor = "rgba(228, 242, 255, 1)";
                    break;
            }
        Thread.sleep(1000);
        boolean liveDeliveryhighlightDisplayed =  admin_liveTripsPage.Text_DeliveryHighlight().isDisplayed();
        String liveDeliveryHighlightColor =  admin_liveTripsPage.Text_DeliveryHighlight().getCssValue("background-color");

        testStepAssert.isTrue(liveDeliveryhighlightDisplayed,"Highlight should be displayed","Highlight is displayed","Highlight is not displayed");
        testStepAssert.isEquals(liveDeliveryHighlightColor,expectedHighlightColor,"Delivery should be highlighted with "+color+" color","Delivery is highlighted with "+color+" color","Delivery is not  highlighted with "+color+" color");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see \"([^\"]*)\" tooltip beside the bungii$")
    public void i_should_see_something_tooltip_beside_the_bungii(String expectedTooltipText) throws Throwable {
        try {
            action.refreshPage();
            Thread.sleep(10000);
            action.Hover(admin_liveTripsPage.Icon_Hover());
            String actualTooltiptext = action.getText(admin_liveTripsPage.Label_Tooltip());

            testStepAssert.isEquals(actualTooltiptext,expectedTooltipText,"Tooltip "+expectedTooltipText+" should be displayed","Tooltip "+expectedTooltipText+" is displayed","Tooltip "+expectedTooltipText+" is not displayed");
            String actualWidth = admin_liveTripsPage.Label_Tooltip().getCssValue("width");
            String expectedWidth = "95px";

            testStepAssert.isEquals(actualWidth,expectedWidth,"Tool tip width should be 95px","Tool tip width is 95px","Tool tip width is not 95px");


        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }

    @And("^The delivery should not be highlighted in \"([^\"]*)\" for \"([^\"]*)\"$")
    public void the_delivery_should_not_be_highlighted_in_something_for_something(String strArg1, String deliveryType) throws Throwable {
        try {
        String expectedHighlightColor = "rgba(228, 242, 255, 1)";
       switch (deliveryType){
           case "Scheduled Deliveries":
           case "Live Deliveries":
               Thread.sleep(1000);
               boolean scheduledDeliveryhighlightDisplayed =  admin_liveTripsPage.Text_DeliveryHighlight().isDisplayed();
               String scheduledDeliveryHighlightColor =  admin_liveTripsPage.Text_DeliveryHighlight().getCssValue("background-color");

               testStepAssert.isTrue(scheduledDeliveryhighlightDisplayed,"Highlight should be displayed","Highlight is displayed","Highlight is not displayed");
               testStepAssert.isFalse(scheduledDeliveryHighlightColor.contentEquals(expectedHighlightColor),"Delivery should not be highlighted with blue color","Delivery is not highlighted with blue color","Delivery is highlighted with blue color");

               break;
           case "All Deliveries":
               Thread.sleep(1000);
               boolean allDeliveryhighlightDisplayed =  admin_liveTripsPage.Text_AllDeliveryHighlight().isDisplayed();
               String allDeliveryHighlightColor =  admin_liveTripsPage.Text_AllDeliveryHighlight().getCssValue("background-color");

               testStepAssert.isTrue(allDeliveryhighlightDisplayed,"Highlight should be displayed","Highlight is displayed","Highlight is not displayed");
               testStepAssert.isFalse(allDeliveryHighlightColor.contentEquals(expectedHighlightColor),"Delivery should not be highlighted with blue color","Delivery is not highlighted with blue color","Delivery is highlighted with blue color");
               break;
       }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }



    @Then("^the Accessorial Charges should not be displayed$")
    public void the_accessorial_charges_should_not_be_displayed() throws Throwable {
     try {
    Thread.sleep(1000);
     testStepAssert.isFalse(action.isElementPresent(admin_accessorialChargesPage.Header_Section(true)),"Accessorial Charges should not be displayed","Accessorial Charges is not displayed","Accessorial Charges is displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I use the old pickup reference to search the driver cancelled trip$")
    public void i_use_the_old_pickup_reference_to_search_the_driver_cancelled_trip() throws Throwable {
     try{
        String oldPickupRef = (String) cucumberContextManager.getScenarioContext("OLD_PICKUP_REQUEST");
        Thread.sleep(2000);
        action.clearSendKeys(adminTripsPage.TextBox_Search(), oldPickupRef + Keys.ENTER);
        log("I should be able to search the delivery using the old pickup reference",
                "I could search the delivery using the old pickup reference",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I click on \"([^\"]*)\" link to get the total driver earnings value screen and navigate back to admin portal$")
    public void i_click_on_something_link_to_get_the_total_driver_earnings_value_screen_and_navigate_back_to_admin_portal(String strArg1) throws Throwable {
        try{
        Thread.sleep(1000);
        action.click(driver_DashboardPage.Link_DriverBasicInfo());
        String entireDriverEarning = action.getText(Page_Driver_Details.Text_DriverTotalEarnings()).replace("\\$","");
        cucumberContextManager.setScenarioContext("Initial_Driver_Total_Earning",entireDriverEarning);
        ArrayList<String> tabs = new ArrayList<String> (SetupManager.getDriver().getWindowHandles());
        SetupManager.getDriver().switchTo().window(tabs.get(0));
        action.refreshPage();

        Thread.sleep(2000);
        action.sendKeys(Page_AdminLogin.TextBox_Phone(), PropertyUtility.getDataProperties("admin.user"));
        action.sendKeys(Page_AdminLogin.TextBox_Password(), PropertyUtility.getDataProperties("admin.password"));
        action.click(Page_AdminLogin.Button_AdminLogin());
        log("I should be able to click on the Delivery details link and get the total earning value and navigate back to admin portal",
                "I could click on the Delivery details link and get the total earning value and navigate back to admin portal");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @And("^I login to driver portal on a new tab with driver phone number \"([^\"]*)\"$")
    public void i_login_to_driver_portal_on_a_new_tab_with_driver_phone_number_something(String phone) throws Throwable {
        try{
        action.openNewTab();
        utility.NavigateToDriverLogin();
        Thread.sleep(1000);

        action.click(Page_Driver_Login.Tab_LogIn());
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), phone);
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("web.valid.common.driver.password"));
        Thread.sleep(1000);
        action.click(Page_Driver_Login.Button_DriverLogin());
        log("I should be able to open new tab and login to driver portal using driver phone number "+ phone ,
                "I could open new tab and login to driver portal using driver phone number " + phone,false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The accessorial charges cut should be displayed in total earnings$")
    public void The_accessorial_charges_cut_should_be_displayed_in_total_earnings() throws Throwable {
        try {
        Thread.sleep(1000);
        action.click(driver_DashboardPage.Link_DriverBasicInfo());

        Float entireDriverEarning = Float.parseFloat(action.getText(Page_Driver_Details.Text_DriverTotalEarnings()).replace("$", ""));
        String initialDriverTotal =(String)  cucumberContextManager.getScenarioContext("Initial_Driver_Total_Earning").toString().replace("$","");
        String driverExcessWaitCutAmount =(String) cucumberContextManager.getScenarioContext("ExcessWaitCut").toString().replace("$","");
        String driverCancellationCutAmount =(String) cucumberContextManager.getScenarioContext("CancellationCut").toString().replace("$","");
        String driverMountainousCutAmount =(String)cucumberContextManager.getScenarioContext("MountainousCut").toString().replace("$","");
        String driverOtherCutAmount =(String) cucumberContextManager.getScenarioContext("OtherCut").toString().replace("$","");
        Float newDriverTotalEarning = Float.parseFloat(driverExcessWaitCutAmount) + Float.parseFloat(driverCancellationCutAmount) +Float.parseFloat(driverMountainousCutAmount) + Float.parseFloat(driverOtherCutAmount) +Float.parseFloat(initialDriverTotal);

        testStepAssert.isTrue(newDriverTotalEarning>Float.parseFloat(initialDriverTotal),"Accessorial charges cut should be displayed in total earning","Accessorial charges cut is displayed in total earning","Accessorial charges cut is not displayed in total earning");
        testStepAssert.isEquals(String.format("%.1f",newDriverTotalEarning),String.valueOf(entireDriverEarning),"Proper driver cut amount should be displayed","Proper driver cut amount is displayed","Proper driver cut amount is not displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }





}