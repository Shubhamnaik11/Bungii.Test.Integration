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
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.pass;
import static com.bungii.web.utilityfunctions.DbUtility.getListOfService;

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


    @Given("^I navigate to \"([^\"]*)\" portal configured for \"([^\"]*)\" URL$")
    public void i_navigate_to_something(String page, String url) throws Throwable {
        switch (page)
        {
            case "Partner":
                utility.NavigateToPartnerLogin(url);
                break;
            case "Admin":
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

    @And("^I click on close button on service level$")
    public void i_click_on_close_button_on_service_level(){
        action.click(Page_Partner_Dashboard.Button_close());
    }

    @And("^I change the service level to \"([^\"]*)\"$")
    public void i_change_the_service_level(String Service_Name) throws InterruptedException {

        String Xpath = "//span[@class='service-title' and @data-name='"+Service_Name+"']";

        WebElement Xpath1 = action.getElementByXPath(Xpath);
        action.click(Xpath1);

        cucumberContextManager.setScenarioContext("Selected_service",Service_Name);
        //String alias = (String) cucumberContextManager.getScenarioContext("Alias");
        /*if(alias.equalsIgnoreCase("Brandsmart - Kansas")){
            switch (Service_Name){
                case "Curbside":
                    action.click(Page_Partner_Dashboard.Radio_Button_Curbside());
                    break;
                case "Threshold":
                    action.click(Page_Partner_Dashboard.Radio_Button_Threshold());
                    break;
                default:break;

            }
        }*/

    }

    @And("^I click \"([^\"]*)\" button on Partner Portal$")
    public void I_Click_Some_Button_On_Partner_Portal(String str) throws InterruptedException {
        switch(str)
        {
            case "SIGN IN":
                action.click(Page_Partner_Login.Button_Sign_In());
                break;
            case "GET ESTIMATE":
                action.click(Page_Partner_Dashboard.Button_Get_Estimate());
                break;
            case "Continue":
                String Partner_Portal_Site = (String) cucumberContextManager.getScenarioContext("PP_Site");
                if(Partner_Portal_Site.equalsIgnoreCase("service level")){
                    String Price_Estimated_Page = action.getElementByXPath("//label[contains(text(),'Delivery Cost:')]//following::strong").getText();
                    Price_Estimated_Page = Price_Estimated_Page.substring(1);
                    cucumberContextManager.setScenarioContext("Price_Estimate_Page",Price_Estimated_Page);
                    action.click(Page_Partner_Dashboard.Button_Get_Estimate());
                }else {
                    action.click(Page_Partner_Dashboard.Button_Continue());
                }
                break;
            case "Schedule Bungii":
                action.JavaScriptScrolldown();
                action.click(Page_Partner_Delivery.Button_Schedule_Bungii());
                break;
            case "Track Deliveries":
                action.click(Page_Partner_Done.Dropdown_Setting());
                action.click(Page_Partner_Done.Button_Track_Deliveries());
                break;
            case "Back to Estimate":
                action.click(Page_Partner_Delivery.Link_Back_To_Estimate());
                break;
            case "Cancel Delivery link":
                action.click(Page_Partner_Delivery_List.Link_Cancel_Delivery());
                break;
            case "OK":
                action.click(Page_Partner_Delivery_List.Button_OK());
                break;
            case "OK on Delivery Cancellation Failed":
                action.click(Page_Partner_Delivery_List.Button_Ok__On_Delivery_Cancellation_Failed());
                break;
            case "Cancel Delivery":
                action.click(Page_Partner_Delivery_List.Button_Cancel_Delivery());
                break;
            case "Service Level List":
                action.click(Page_Partner_Dashboard.Dropdown_Service_Level());
                break;
            default: break;

        }
    }

    @Then("^I should \"([^\"]*)\" for \"([^\"]*)\" Alias$")
    public void i_should_something_for_something_alias(String str,String Alias){
        cucumberContextManager.setScenarioContext("Alias",Alias);
        //List Service_name = new DbUtility().getServiceName(Alias);
        List<HashMap<String,Object>> Service_name = getListOfService(Alias);
        switch (str)
        {
             case "see all the Service Level":
                if(Alias.equalsIgnoreCase("Biglots")){

                    for(int i=0;i<Service_name.size();i++) {
                        String Db_Service_Name = Service_name.get(i).values().toString();
                        Db_Service_Name = Db_Service_Name.substring(1, Db_Service_Name.length() - 1);
                        String Xpath = "//span[contains(text(),'"+Db_Service_Name+"')]";
                        String Display_Service_name= action.getElementByXPath(Xpath).getText();
                        testStepAssert.isElementDisplayed(action.getElementByXPath(Xpath),"Service Name:-"+Db_Service_Name+" should be shown","Service Name:-"+Db_Service_Name+" is shown","Service Name-"+Db_Service_Name+" is not shown");
                        //testStepVerify.isEquals(Display_Service_name, Db_Service_Name);

                    }
                    log("All service for "+Alias+" should be listed ","All service for "+Alias+" are listed ", true);
                }
                break;
            default:
                break;
        }
    }

    @Then("^I should \"([^\"]*)\"$")
    public void IShould(String str) throws ParseException {
        switch (str) {
            case "be logged in":
                //testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.Header_Dashboard()), PropertyUtility.getMessage("DriverDashboardHeader"));
                //action.waitUntilIsElementExistsAndDisplayed(Page_Partner_Dashboard.Label_Get_Estimate_Header(), (long) 2000);
                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_Start_Over()), PropertyUtility.getMessage("Start_Over_Header"));
                break;
            case "see validations message for blank password field":
                testStepVerify.isEquals(action.getText(Page_Partner_Login.Message_Blank_Incorrect_Password()), PropertyUtility.getMessage("Blank_Password"));
                break;
            case "see validations message for incorrect password field":
                testStepVerify.isEquals(action.getText(Page_Partner_Login.Message_Blank_Incorrect_Password()), PropertyUtility.getMessage("Incorrect_Password"));
                break;
            case "see Delivery Details screen":
                String PP_Site = (String) cucumberContextManager.getScenarioContext("SiteUrl");
                if (PP_Site.equalsIgnoreCase("normal")) {
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Text_Delivery_Details_Header()), PropertyUtility.getMessage("Delivery_Details_Header"));
                }
                else if(PP_Site.equalsIgnoreCase("kiosk mode")) {
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Text_Delivery_Details_Header()), PropertyUtility.getMessage("Delivery_Details_Header"));
                }
                else if(PP_Site.equalsIgnoreCase("service level")) {
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Text_Delivery_Details_Header()), PropertyUtility.getMessage("Service_Delivery_Details_Header"));
                }
                    String PickupDateTime = action.getText(Page_Partner_Delivery.Text_Pickup_DateTime());


                String[] splitDate = PickupDateTime.split(" ", 2);
                String Month = splitDate[0].substring(0, 3);
                splitDate[0] = Month + " ";
                PickupDateTime = splitDate[0] + splitDate[1];
                cucumberContextManager.setScenarioContext("ActualPickupDateTime", PickupDateTime); //This will be used in Track Delivery List

                char ch = PickupDateTime.charAt(4);
                if (PickupDateTime.charAt(4) == '0') {
                    StringBuilder sb = new StringBuilder(PickupDateTime);
                    sb.deleteCharAt(4);
                    PickupDateTime = sb.toString();
                }
                PickupDateTime = PickupDateTime.replaceAll("[()]", "");
                cucumberContextManager.setScenarioContext("PickupDateTime", PickupDateTime); //This will be used further

                break;
            case "see Done screen":
                String Customer_Phone = (String) cucumberContextManager.getScenarioContext("CustomerPhone");
                testStepVerify.isEquals(action.getText(Page_Partner_Done.Text_Schedule_Done_Success_Header()), PropertyUtility.getMessage("Done_Success_Header"));
                String PickupRequest = new DbUtility().getPickupRef(Customer_Phone);
                //String ScheduledTime = new DbUtility().getScheduledTime(Customer_Phone);
                //String FromFormat="yyyy-mm-dd HH:mm:ss";
                //String ToFormat ="MMM dd, YYYY at HH:mm aa z";
                //String date = utility.GetDateInFormat(ScheduledTime,FromFormat,ToFormat);
                //String ST = DateFormat("MMM dd, YYYY at HH:mm aa z",ScheduledTime);
                //cucumberContextManager.setScenarioContext("Scheduled_Time",date);
                cucumberContextManager.setScenarioContext("pickupRequest",PickupRequest);
               // cucumberContextManager.setScenarioContext("PICKUP_REQUEST",PickupRequest);
                break;
            case "see the trip in the Delivery List":
                //String Customer_Name = null;
               // String DeliveryDace = (String) cucumberContextManager.getScenarioContext("Scheduled_Time");
                String Delivery_Date = (String) cucumberContextManager.getScenarioContext("ActualPickupDateTime");
                //String CustomerName = (String) cucumberContextManager.getScenarioContext("Customer_Name");

                String DeliveryAddress = (String) cucumberContextManager.getScenarioContext("Delivery_Address");

                String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]", Delivery_Date, DeliveryAddress);
                testStepAssert.isElementDisplayed(action.getElementByXPath(XPath), "Trip should be displayed on partner portal", "Trip is displayed on partner portal", "Trip is not displayed on partner portal");
                /*
                try {
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Text_Delivery_Date()), Delivery_Date);
                }
                catch (org.openqa.selenium.StaleElementReferenceException ex){
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Text_Delivery_Date()), Delivery_Date);
                }

                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Text_Customer()),CustomerName);
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Text_Delivery_Address()),DeliveryAddress);

                 */
                break;
            case "see the trip details":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Delivery_Details_Dashboard()),PropertyUtility.getMessage("Delivery_Details_Dashboard"));
                break;
            case "see the cancel delivery warning message":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Cancel_Delivery()),PropertyUtility.getMessage("Message_Cancel_Delivery"));
                break;
            case "see delivery has been cancelled message":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Cancel_Confirmation()),PropertyUtility.getMessage("Confirmation_Message_Delivery_Cancel"));
                break;
            case "see Canceled trip message":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Cancel_Trip()),PropertyUtility.getMessage("Message_Cancel_Trip"));
                break;
            case "see Get Estimate screen":
                testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Label_Get_Estimate_Header(),"Get Estimate START OVER should be shown","Get Estimate START OVER is shown","Get Estimate START OVER is not shown");
                //testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_Get_Estimate_Header()), PropertyUtility.getMessage("Get_Estimate_Header"));
                break;
            case "see five future days including today":
                Calendar calendar = Calendar.getInstance();
                TimeZone fromTimeZone = calendar.getTimeZone();
                TimeZone toTimeZone = TimeZone.getTimeZone("CST");
                break;
            case "see Delivery cancellation failed message":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Delivery_Cancellation_Failed()),PropertyUtility.getMessage("Message_Delivery_Cancellation_Failed"));
                break;
            case "see Service Level":
                //String xpath ="//div[@class='service-level form-group']/div/p";
                String Service_Name = action.getText(Page_Partner_Dashboard.Text_Service_Name());
                cucumberContextManager.setScenarioContext("Selected_service",Service_Name);
                testStepVerify.isElementDisplayed(Page_Partner_Dashboard.Information_Icon__Service_Level(),"Information icon for service level should be display","Information icon for service level is display","Information icon for service level is not display");
                //testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Text_Service_Level_Edit()),"SERVICE LEVEL EDIT");
                //testStepAssert.isElementTextEquals(Page_Partner_Dashboard.Text_Service_Level(),"Service Level","Service Level configuration is present","Service Level configuration is not present");
                break;
            case "see No service selected":
                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Text_No_Service()),"No service selected.");
                break;
            case "see the service name":
                String Service_Name1 = (String) cucumberContextManager.getScenarioContext("Selected_service");
                action.JavaScriptScrolldown();
                String Display_Service_name = action.getText(Page_Partner_Delivery_List.Text_Selected_Service());
                testStepVerify.isEquals(Display_Service_name,Service_Name1);
                break;
            default: break;
        }
    }

    @And("^I should logout from Partner Portal$")
    public void i_should_logout_from_partner_portal() throws Throwable {
        action.click(Page_Partner_Done.Dropdown_Setting());
        //Thread.sleep(5000);
        utility.PartnerLogout();

        testStepAssert.isElementDisplayed(action.getElementByXPath("//button[@id='login']"), "SIGN IN button should be displayed on partner portal", "SIGN IN button is displayed on partner portal", "SIGN IN button is not displayed on partner portal");
        //action.getElementByXPath(Page_Partner_Login.Button_Sign_In())
        log("I should be logged out from Partner Portal ","I clicked ", true);
    }

}

