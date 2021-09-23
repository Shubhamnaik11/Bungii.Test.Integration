package com.bungii.web.stepdefinitions.admin;

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
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Keys;

import java.text.DecimalFormat;

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
            String link = String.format("//td[contains(.,'%s')]/following-sibling::td/a[@class='revive-trip-link']", customerName);
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

}