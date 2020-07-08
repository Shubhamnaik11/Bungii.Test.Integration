package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
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

}
