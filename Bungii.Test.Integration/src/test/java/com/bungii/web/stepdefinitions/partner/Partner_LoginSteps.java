package com.bungii.web.stepdefinitions.partner;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.LogInSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partner.*;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Calendar;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.pass;

public class Partner_LoginSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LogInSteps.class);
    Partner_LoginPage Page_Partner_Login = new Partner_LoginPage();
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();
    Partner_Done Page_Partner_Done = new Partner_Done();
    Partner_DeliveryList Page_Partner_Delivery_List = new Partner_DeliveryList();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    //DbUtility dbUtility = new DbUtility();


    @Given("^I navigate to \"([^\"]*)\" URL$")
    public void i_navigate_to_something(String page) throws Throwable {
        switch (page)
        {
            case "Bungii Partner Portal":
                utility.NavigateToPartnerLogin();
                break;
            case "Bungii Admin Portal in new tab":
                utility.AdminLoginFromPartner();
                break;
            default:break;
        }
        pass("I should be navigate to " + page,
                "I am navigate to " + page, true);
    }



    @When("^I enter \"([^\"]*)\" password on Partner Portal$")
    public void WhenIEnterPasswordOnPartnerPortal(String str)
    {
        switch (str)
        {
            case "valid":
                action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("Invalid_PartnerPassword"));
                break;
            default: break;
        }
        log("I should able to enter "+str+" driver Password on Partner portal","I entered "+str +" partner Password on Partner portal", true);

    }


    @And("^I click \"([^\"]*)\" button on Partner Portal$")
    public void I_Click_Some_Button_On_Partner_Portal(String str) throws InterruptedException {
        switch(str)
        {
            case "SIGN IN":
                action.click(Page_Partner_Login.Sign_In());
                break;
            case "GET ESTIMATE":
                action.click(Page_Partner_Dashboard.Get_Estimate_button());
                break;
            case "Continue":
                action.click(Page_Partner_Dashboard.Continue());
                break;
            case "Schedule Bungii":
                //Thread.sleep(2000);
                action.click(Page_Partner_Delivery.Schedule_Bungii());
                break;
            case "Track Deliveries":
                action.click(Page_Partner_Done.Track_Deliveries());
                break;
            case "Back to Estimate":
                action.click(Page_Partner_Delivery.Back_To_Estimate());
                break;
            case "Cancel Delivery link":
                action.click(Page_Partner_Delivery_List.link_Cancel_Delivery());
                break;
            case "OK":
                action.click(Page_Partner_Delivery_List.botton_OK());
                break;
            case "Cancel Delivery":
                action.click(Page_Partner_Delivery_List.button_Cancel_Delivery());
                break;
            default: break;

        }
    }

    @Then("^I should \"([^\"]*)\"$")
    public void IShould(String str)
    {
        switch (str)
        {
            case "be logged in":
                //testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.Header_Dashboard()), PropertyUtility.getMessage("DriverDashboardHeader"));
                action.waitUntilIsElementExistsAndDisplayed(Page_Partner_Dashboard.Get_Estimate_Header(), (long) 2000);
                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Get_Estimate_Header()), PropertyUtility.getMessage("Get_Estimate_Header"));
                break;
            case "see validations message for blank password field":
                testStepVerify.isEquals(action.getText(Page_Partner_Login.Blank_Incorrect_Password_Msg()), PropertyUtility.getMessage("Blank_Password"));
                break;
            case "see validations message for incorrect password field":
                testStepVerify.isEquals(action.getText(Page_Partner_Login.Blank_Incorrect_Password_Msg()), PropertyUtility.getMessage("Incorrect_Password"));
                break;
            case "see Delivery Details screen":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Delivery_Details_Header()), PropertyUtility.getMessage("Delivery_Details_Header"));
                break;
            case "see Done screen":
                String Customer_Phone = (String) cucumberContextManager.getScenarioContext("CustomerPhone");
                testStepVerify.isEquals(action.getText(Page_Partner_Done.Schedule_Done_Success_Header()), PropertyUtility.getMessage("Done_Success_Header"));
                String PickupRequest = new DbUtility().getPickupRef(Customer_Phone);
                cucumberContextManager.setScenarioContext("pickupRequest",PickupRequest);
                cucumberContextManager.setScenarioContext("PICKUP_REQUEST",PickupRequest);
                break;
            case "see the trip in the Delivery List":
                //String Customer_Name = null;
                String CustomerName = (String) cucumberContextManager.getScenarioContext("Customer_Name");
                String DeliveryAddress = (String) cucumberContextManager.getScenarioContext("Delivery_Address");
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Customer()),CustomerName);
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Delivery_Address()),DeliveryAddress);
                break;
            case "see the trip details":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Delivery_Details_Dashboard()),PropertyUtility.getMessage("Delivery_Details_Dashboard"));
                break;
            case "see the cancel delivery warning message":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.message_Cancel_Delivery()),PropertyUtility.getMessage("Message_Cancel_Delivery"));
                break;
            case "see delivery has been cancelled message":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.message_Cancel_confirmation()),PropertyUtility.getMessage("Confirmation_Message_Delivery_Cancel"));
                break;
            case "see Canceled trip message":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.message_Cancel_Trip()),PropertyUtility.getMessage("Message_Cancel_Trip"));
                break;
            case "see Get Estimate screen":
                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Get_Estimate_Header()), PropertyUtility.getMessage("Get_Estimate_Header"));
               // String PickupAddress_On_Estimate = (String) cucumberContextManager.getScenarioContext("PickupAddress");
               // String DeliveryAddress_On_Estimate = (String) cucumberContextManager.getScenarioContext("Delivery_Address");
               // testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Text_Pickup_Address()),PickupAddress_On_Estimate);
               // testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Delivery_Address()),DeliveryAddress_On_Estimate);
                break;
            case "see five future days including today":
                Calendar calendar = Calendar.getInstance();
                TimeZone fromTimeZone = calendar.getTimeZone();
                TimeZone toTimeZone = TimeZone.getTimeZone("CST");
                break;
            default: break;
        }
    }

    @Then("^I should logout from Partner Portal$")
    public void i_should_logout_from_partner_portal() throws Throwable {
        utility.PartnerLogout();
        log("I should be logged out from Partner Portal ","I clicked ", true);
    }

}

