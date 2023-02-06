package com.bungii.ios.stepdefinitions;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.other.SafariPage;

import com.bungii.ios.stepdefinitions.customer.HomeSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Map;

import static com.bungii.common.manager.ResultManager.*;
import com.bungii.ios.stepdefinitions.customer.HomeSteps;
import org.openqa.selenium.Point;

public class MobileFriendlySteps extends DriverBase {
    private static LogUtility logger = new LogUtility(CommonSteps.class);
    ActionManager action = new ActionManager();
    SafariPage safariPage= new SafariPage();
    HomeSteps homeSteps;
    @When("^I terminate \"([^\"]*)\" app on \"([^\"]*)\" devices$")
    public void i_terminate_app(String appName, String device) {

        try {
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    ((IOSDriver<MobileElement>) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                    break;
                case "CUSTOMER":
                    ((IOSDriver<MobileElement>) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }

            pass("Terminated " + appName + " application", "Termination of " + appName + " application is successful");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);

        }

    }
    @And("I open {string} partner portal")
    public void iOpenPartnerPortal(String portal) {
        try{

            switch (portal){
                case "fixed pricing":
                    action.click(safariPage.Textbox_SafariSearch());

                    action.sendKeys(safariPage.Textbox_SafariSearchBar(),PropertyUtility.getDataProperties("qa.service_level_partner.url"));
                    action.click(safariPage.Button_Go());
                    break;
            }
            action.waitUntilIsElementExistsAndDisplayed(safariPage.Textbox_EnterPassword());
            action.clearSendKeys(safariPage.Textbox_EnterPassword(),PropertyUtility.getDataProperties("PartnerPassword"));
            action.click(safariPage.Button_SignIn());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("I verify the ui elements on {string} page for {string} partner")
    public void iVerifyTheUiElementsOnPageForPartner(String pageName, String portalName) {
        try{
            switch (pageName){
                case "get estimate":
                    action.waitUntilIsElementExistsAndDisplayed(safariPage.Icon_BungiiLogo());
                    testStepAssert.isElementDisplayed(safariPage.Icon_BungiiLogo(),
                            "Bungii Logo should be displayed",
                            "Bungii Logo is displayed",
                            "Bungii Logo is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Text_WhatsNeeded(),
                            "Whats needed section should be displayed",
                            "Whats needed section is displayed",
                            "Whats needed section is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Icon_Logout(),
                            "Log-out icon should be displayed",
                            "Log-out icon is displayed",
                            "Log-out icon is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Text_DeliveryAddress(),
                            "Delivery address section should be displayed",
                            "Delivery address section is displayed",
                            "Delivery address section is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Text_PickUpDate(),
                            "Pick-up date section should be displayed",
                            "Pick-up date section is displayed",
                            "Pick-up date section is not displayed");
                    testStepAssert.isElementDisplayed(safariPage.Text_PickUpTime(),
                            "Pick-up time section should be displayed",
                            "Pick-up time section is displayed",
                            "Pick-up time section is not displayed");
                    switch (portalName){
                        case "fixed pricing":
                            testStepAssert.isElementDisplayed(safariPage.Text_Solo(),
                                    "Solo section should be displayed",
                                    "Solo section is displayed",
                                    "Solo section is not displayed");
                            testStepAssert.isElementDisplayed(safariPage.Text_Duo(),
                                    "Duo section should be displayed",
                                    "Duo section is displayed",
                                    "Duo section is not displayed");
                            testStepAssert.isElementDisplayed(safariPage.Text_PickUpAddress(),
                                    "Pick-up address section should be displayed",
                                    "Pick-up address section is displayed",
                                    "Pick-up address section is not displayed");
                            action.swipeUP();
                            testStepAssert.isElementDisplayed(safariPage.Text_ServiceLevel(),
                                    "Service Level section should be displayed",
                                    "Service Level section is displayed",
                                    "Service Level section is not displayed");
                            break;
                    }
                    break;
//                case "success":
//                    testStepAssert.isElementDisplayed(chromePage.Text_SucessMsg(),
//                            "Success message should be displayed",
//                            "Success message is displayed",
//                            "Success message is not displayed");
//                    testStepAssert.isElementDisplayed(chromePage.Text_TrackingId(),
//                            "Tracking ID should be displayed",
//                            "Tracking ID is displayed",
//                            "Tracking ID is not displayed");
//                    testStepAssert.isElementDisplayed(chromePage.Text_Time(),
//                            "Schedule time should be displayed",
//                            "Schedule time is displayed",
//                            "Schedule time is not displayed");
//                    action.scrollToBottom();
//                    testStepAssert.isElementDisplayed(chromePage.Text_Drivers(),
//                            "Whats needed section should be displayed",
//                            "Whats needed section is displayed",
//                            "Whats needed section is not displayed");
//                    testStepAssert.isElementDisplayed(chromePage.Text_Payment(),
//                            "Payment section should be displayed",
//                            "Payment section is displayed",
//                            "Payment section is not displayed");
//                    testStepAssert.isElementDisplayed(chromePage.Button_NewBungii(),
//                            "New Bungii button should be displayed",
//                            "New Bungii button is displayed",
//                            "New Bungii button is not displayed");
//                    break;

            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @When("I enter all details on {string} for {string}")
    public void iEnterAllDetailsOnFor(String pageName, String portalName, DataTable data) {
        try{
            switch (pageName){
                case "get estimate page":
                    Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
                    if(portalName.equalsIgnoreCase("fixed pricing"))
                    {
                        action.clearSendKeys(safariPage.Textbox_PickUpAddress(),dataMap.get("Pickup_Address"));
                        action.click(safariPage.Button_Return());
                        action.clearSendKeys(safariPage.Textbox_DeliveryAddress(),dataMap.get("Delivery_Address"));
                        action.click(safariPage.Button_Return());
                    }
                    break;
                case "delivery details":
                    Map<String, String> dataMapDetails = data.transpose().asMap(String.class, String.class);
//                    if(portalName.equalsIgnoreCase("weight based portal")){
//                        action.waitUntilIsElementExistsAndDisplayed(chromePage.Textbox_Items());
//                        action.clearSendKeys(chromePage.Textbox_Items(),dataMapDetails.get("Product_Description"));
//                        action.clearSendKeys(chromePage.Textbox_Dimensions(),dataMapDetails.get("Dimensions"));
//                        action.clearSendKeys(chromePage.Textbox_Weight(),dataMapDetails.get("Weight"));
//                        action.scrollToBottom();
//                        action.clearSendKeys(chromePage.Textbox_CustomerName(),dataMapDetails.get("Customer_Name"));
//                        action.clearSendKeys(chromePage.Textbox_CustomerNumber(),dataMapDetails.get("Customer_Mobile"));
//                        action.scrollToBottom();
//                        action.clearSendKeys(chromePage.Textbox_DeliveryPurpose(),dataMapDetails.get("Delivery_Purpose"));
//                        action.clearSendKeys(chromePage.Textbox_RbNumber(),dataMapDetails.get("Rb_Sb_Number"));
//                        action.clearSendKeys(chromePage.Textbox_ScheduleBy(),dataMapDetails.get("ScheduledBy"));
//                    }
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("I select {string} service level")
    public void iSelectServiceLevel(String serviceLevel) {
        try{
            action.click(safariPage.Dropdown_ServiceLevel());
            switch (serviceLevel){
                case "Threshold":
                    action.click(safariPage.Dropdown_Values(serviceLevel));
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
//    @And("^I click on \"([^\"]*)\" button$")
//    public void i_click_on_something_button(String Name) throws Throwable {
//        try{
//            switch(Name)
//            {
//                case "Continue":
//                    action.click(safariPage.Button_Continue());
//                    break;
//            }
//            log("I click on the "+Name+" button",
//                    "I clicked the "+Name+" button", false);
//        } catch(Exception e){
//            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
//            error("Step should be successful", "Error performing step,Please check logs for more details",
//                    true);
//        }
//    }
}
