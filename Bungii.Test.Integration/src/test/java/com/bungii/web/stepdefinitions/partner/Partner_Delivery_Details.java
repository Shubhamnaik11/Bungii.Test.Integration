package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.JavascriptExecutor;


import java.util.Map;

public class Partner_Delivery_Details extends DriverBase {

    private static LogUtility logger = new LogUtility(DashBoardSteps.class);

    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();
    ActionManager action = new ActionManager();

    @When("^I enter following details on \"([^\"]*)\" partner screen$")
    public void i_enter_following_details_on_some_partner_screen(String str, DataTable data){
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

        String Items_deliver = dataMap.get("Items_To_Deliver").trim();
        String CustomerName = dataMap.get("Customer_Name").trim();
        cucumberContextManager.setScenarioContext("Customer_Name", CustomerName);
        //cucumberContextManager.setScenarioContext("Customer", CustomerName);
        String CustomerMobile = dataMap.get("Customer_Mobile").trim();
        cucumberContextManager.setScenarioContext("CustomerPhone", CustomerMobile);
        String PickupContactName = dataMap.get("Pickup_Contact_Name").trim();
        String PickupContactPhone = dataMap.get("Pickup_Contact_Phone").trim();

        switch(str){
            case "Delivery Details":
                action.clearSendKeys(Page_Partner_Delivery.Item_To_deliver(),Items_deliver);
                action.clearSendKeys(Page_Partner_Delivery.Customer_Name(),CustomerName);
                //cucumberContextManager.setScenarioContext("CUSTOMER_MOBILE", CustomerMobile);
                action.click(Page_Partner_Delivery.Customer_Mobile());
                action.clearSendKeys(Page_Partner_Delivery.Customer_Mobile(),CustomerMobile);

                //JavascriptExecutor executor = (JavascriptExecutor) SetupManager.getDriver();
                //executor.executeScript("arguments[0].value=CustomerMobile;", Page_Partner_Delivery.Customer_Mobile());


                action.clearSendKeys(Page_Partner_Delivery.Pickup_Contact_Name(),PickupContactName);
                action.click(Page_Partner_Delivery.Pickup_Contact_Phone());
                action.clearSendKeys(Page_Partner_Delivery.Pickup_Contact_Phone(),PickupContactPhone);
                break;
            default:break;
        }


    }

    @Then("^I should \"([^\"]*)\" on Delivery Details screen$")
    public void i_should_see_something_on_delivery_details_screen(String str){

        switch (str){
            case "see validations message for blank Items To Deliver field":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.message_Black_Item_To_deliver()),PropertyUtility.getMessage("Message_Blank_Item_To_Deliver"));
                break;
            case "see validations message for blank Customer Name field":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.message_Black_Customer_Name()),PropertyUtility.getMessage("Message_Blank_Customer"));
                break;
            case "see validations message for blank Customer Mobile field":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.message_Blank_Customer_Mobile()),PropertyUtility.getMessage("Message_Blank_CustomerMobile"));
                break;
            case "see validations message for blank Pickup Contact Name field":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.message_Blank_Pickup_Contact_Name()),PropertyUtility.getMessage("Message_Blank_Pickup_Contact_Name"));
                break;
            case "see validations message for blank Pickup Contact Phone field":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.message_Blank_Pickup_Contact_Phone()),PropertyUtility.getMessage("Message_Blank_Pickup_Contact_Phone"));
                break;
            default:break;
        }
    }

}
