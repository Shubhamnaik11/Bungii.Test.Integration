package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.ScreenshotUtility;
import com.bungii.ios.stepdefinitions.admin.LogInSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_ScheduledTripsPage;
import com.bungii.web.pages.partner.*;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
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
    Admin_ScheduledTripsPage Page_Admin_ScheduledTrips = new Admin_ScheduledTripsPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    //DbUtility dbUtility = new DbUtility();
    Kiosk_Page Page_Kiosk = new Kiosk_Page();
    Driver_RatingPage Page_DriverRating = new Driver_RatingPage();

    @Given("^I navigate to \"([^\"]*)\" portal configured for \"([^\"]*)\" URL$")
    public void i_navigate_to_something(String page, String url) throws Throwable {
      try{  switch (page)
        {
            case "Partner":
                String partnerUrl =  utility.NavigateToPartnerLogin(url);
                cucumberContextManager.setScenarioContext("IS_PARTNER","TRUE");
                pass("I should be navigate to " + page + " portal configured for "+ url ,
                        "I navigated to " + page + " portal configured for "+ url +" ["+partnerUrl+"]", true);
                break;
            case "Admin":
                utility.AdminLoginFromPartner();
                pass("I should be navigate to " + page  ,
                        "I navigated to " + page , true);
                break;
            default:break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @When("^I enter \"([^\"]*)\" password on Partner Portal$")
    public void WhenIEnterPasswordOnPartnerPortal(String str)
    {
        try{
        SetupManager.getObject().manage().window().maximize();
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
        log("I should able to enter "+str+" driver Password on Partner portal","I entered "+str +" partner Password on Partner portal", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on close button on service level$")
    public void i_click_on_close_button_on_service_level(){
        try{
        action.click(Page_Partner_Dashboard.Button_close());
        log("I should able to click on close button on service level.","Service level should get close on clicked on close button." , false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I change the service level to \"([^\"]*)\" in \"([^\"]*)\" portal$")
    public void i_change_the_service_level_to_something_in_something_portal(String Service_Name, String Site_Name) throws Throwable {
        try {
            switch (Site_Name) {
                case "Partner":
                    action.click(Page_Partner_Dashboard.Dropdown_ServiceLevel(Service_Name));
                    cucumberContextManager.setScenarioContext("Selected_service", Service_Name);
                    break;
                case "Admin":
                    //action.click(Page_Admin_ScheduledTrips.Admin_Dropdown_ServiceLevel(Service_Name));
                    action.selectElementByText(Page_Admin_ScheduledTrips.Admin_Dropdown_ServiceLevel(), Service_Name);
                    cucumberContextManager.setScenarioContext("Change_service", Service_Name);
                    break;
                default:
                    logger.error("Wrong site name is pass.Please Pass correct site.");
            }
            log("I should able to change the service level to " + Service_Name, "Service name should get changed to " + Service_Name, true);

        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to change the service " + Service_Name + "for" + Site_Name + "portal",
                    true);
        }
    }

    @And("^I change the service level to \"([^\"]*)\"$")
    public void i_change_the_service_level(String Service_Name) throws InterruptedException {
        try{
        action.click(Page_Partner_Dashboard.Dropdown_ServiceLevel(Service_Name));
        cucumberContextManager.setScenarioContext("Selected_service",Service_Name);
        log("I should able to change the service level to "+Service_Name,"Service name should get changed to "+Service_Name , false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I click \"([^\"]*)\" button on Partner Portal$")
    public void I_Click_Some_Button_On_Partner_Portal(String str) throws InterruptedException {
        try {
            switch (str) {
                case "SIGN IN":
                    action.click(Page_Partner_Login.Button_Sign_In());
                    break;
                case "GET ESTIMATE":
                    action.click(Page_Partner_Dashboard.Button_Get_Estimate());
                    break;
                case "Continue":
                    String Partner_Portal_Site = (String) cucumberContextManager.getScenarioContext("PP_Site");
                    if (Partner_Portal_Site.equalsIgnoreCase("service level")) {

                        String Price_Estimated_Page = action.getText(Page_Partner_Dashboard.Label_DeliveryCostEstimate());
                                //action.getElementByXPath("//label[contains(text(),'Delivery Cost:')]//following::strong").getText();
                        Price_Estimated_Page = Price_Estimated_Page.substring(1);
                        cucumberContextManager.setScenarioContext("Price_Estimate_Page", Price_Estimated_Page);
                        String Estimate_distance = action.getText(Page_Partner_Dashboard.Label_Distance()).replace(" miles","");//calculate values as per the displayed miles value to avoid mismatch in calculation
                        cucumberContextManager.setScenarioContext("Distance_Estimate_Page", Estimate_distance);

                        action.click(Page_Partner_Dashboard.Button_Get_Estimate());
                    } else {
                        action.click(Page_Partner_Dashboard.Button_Continue());
                    }
                    break;
                case "Schedule Bungii":
                    action.JavaScriptScrolldown();
                    action.click(Page_Partner_Delivery.Button_Schedule_Bungii());
                    break;
                case "Track Deliveries":
                    Thread.sleep(5000);
                    action.click(Page_Partner_Done.Dropdown_Setting());
                    Thread.sleep(5000);
                    action.click(Page_Partner_Done.Button_Track_Deliveries());
                    Thread.sleep(5000);
                    if(action.getCurrentURL().contains("login")|| action.getCurrentURL().contains("Login"))
                    {
                        //Workaround for app getting logged out when run in parallel
                        action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                        action.click(Page_Partner_Login.Button_Sign_In());
                        Thread.sleep(5000);
                        testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_Start_Over()), PropertyUtility.getMessage("Start_Over_Header"));
                        Thread.sleep(5000);
                        if(!action.isElementPresent(Page_Partner_Done.Dropdown_Setting(true))) {
                            action.click(Page_Kiosk.Link_Setting());
                            action.clearSendKeys(Page_Kiosk.Textbox_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                            action.click(Page_Kiosk.Button_Continue());
                        }
                            action.click(Page_Partner_Done.Dropdown_Setting());
                            action.click(Page_Partner_Done.Button_Track_Deliveries());

                    }
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
                default:
                    break;

            }
            log("I click on "+str+ " button ", "I clicked on "+str+ " button ", true);

        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step , I Should "+ str,
                    true);
        }
    }

    @Then("^I should \"([^\"]*)\" for \"([^\"]*)\" Alias$")
    public void i_should_something_for_something_alias(String str,String Alias){
        try {
            cucumberContextManager.setScenarioContext("Alias", Alias);
            //List Service_name = new DbUtility().getServiceName(Alias);
            List<HashMap<String, Object>> Service_name = getListOfService(Alias);
            switch (str) {
                case "see all the Service Level":
                    if (Alias.equalsIgnoreCase("Biglots")) {

                        for (int i = 0; i < Service_name.size(); i++) {
                            String Db_Service_Name = Service_name.get(i).values().toString();
                            Db_Service_Name = Db_Service_Name.substring(1, Db_Service_Name.length() - 1);
                            String Xpath = "//span[contains(text(),'" + Db_Service_Name + "')]";
//                        String Display_Service_name= action.getElementByXPath(Xpath).getText();
                            testStepAssert.isElementDisplayed(action.getElementByXPath(Xpath), "Service Name:-" + Db_Service_Name + " should be shown", "Service Name:-" + Db_Service_Name + " is shown", "Service Name-" + Db_Service_Name + " is not shown");
                            //testStepVerify.isEquals(Display_Service_name, Db_Service_Name);

                        }
                        log("All service for " + Alias + " should be listed ", "All service for " + Alias + " are listed ", true);
                    }
                    break;
                case "see correct Estimation Duration":
                    long total_Estimation_Duration;
                    String subDomain = Alias;
                    String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    String service_name = (String) cucumberContextManager.getScenarioContext("Selected_service");
                    long db_EST_Time = dbUtility.getEstimateTimeforPickup(pickupRequest);
                    long default_Pickup_Time = dbUtility.getDefaultPickupTime(service_name, subDomain);
                    default_Pickup_Time = utility.Milliseconds_To_Minutes(default_Pickup_Time);
                    long default_Dropoff_time = dbUtility.getDefaultDropoffTime(service_name, subDomain);
                    default_Dropoff_time = utility.Milliseconds_To_Minutes(default_Dropoff_time);

                    total_Estimation_Duration = db_EST_Time + default_Pickup_Time + default_Dropoff_time;

                    String display_Estimation_Duration = action.getText(Page_Partner_Delivery_List.Text_Estimated_Duration());
                    String estimated_Duration[] = display_Estimation_Duration.split(":");
                    String hours = estimated_Duration[0];
                    String minutes = estimated_Duration[1];

                    long hrs = Long.parseLong(hours);
                    long mins = hrs * 60;

                    long total_Display_Estimated_Duration = mins + Long.parseLong(minutes);
                    testStepVerify.isEquals(String.valueOf(total_Display_Estimated_Duration), String.valueOf(total_Estimation_Duration), "Correct Total estimated duration is shown.", "Wrong Total estimated duration is shown");
                    log("Expected Estimated Duration= " + total_Estimation_Duration, "Actual Estimated Duration= " + total_Display_Estimated_Duration, true);
                    break;
                default:
                    break;
            }
        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step , I Should "+ str,
                    true);
        }
    }

    @Then("^I should \"([^\"]*)\"$")
    public void IShould(String str) throws ParseException {
        try {
            switch (str) {
                case "be logged in":
                    //testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.Header_Dashboard()), PropertyUtility.getMessage("DriverDashboardHeader"));
                    //action.waitUntilIsElementExistsAndDisplayed(Page_Partner_Dashboard.Label_Get_Estimate_Header(), (long) 2000);
                    testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_Start_Over()), PropertyUtility.getMessage("Start_Over_Header"));
                    break;
                case "see 1 pallet and 2 pallets":
                    testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_1Pallet()), PropertyUtility.getDataProperties("1Pallet"));
                    testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_2Pallets()), PropertyUtility.getDataProperties("2Pallets"));
                    break;
                case "see validations message for blank password field":
                    testStepVerify.isEquals(action.getText(Page_Partner_Login.Message_Blank_Incorrect_Password()), PropertyUtility.getMessage("Blank_Password"));
                    break;
                case "see validations message for incorrect password field":
                    testStepVerify.isEquals(action.getText(Page_Partner_Login.Message_Blank_Incorrect_Password()), PropertyUtility.getMessage("Incorrect_Password"));
                    break;
                case "see Delivery Details screen":
                    /////////////////////////////WorkAround to eliminate logout on Track deliveries/////////////////////////////////////
                    Thread.sleep(5000);
                    if(SetupManager.getObject().getCurrentUrl().contains("login"))
                    {
                        action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                        action.click(Page_Partner_Login.Button_Sign_In());
                        action.click(Page_Partner_Done.Dropdown_Setting());
                        action.click(Page_Partner_Done.Button_Track_Deliveries());
                        logger.detail("PARTNER RELOGIN AS A WORKAROUND TO ELIMINATE FALSE KICKOUT");
                    }
                    /////////////////////////////WorkAround Ends/////////////////////////////////////

                    String PP_Site = (String) cucumberContextManager.getScenarioContext("SiteUrl");
                    if (PP_Site.equalsIgnoreCase("normal")) {
                        testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Text_Delivery_Details_Header()), PropertyUtility.getMessage("Delivery_Details_Header"));
                    } else if (PP_Site.equalsIgnoreCase("kiosk mode")) {
                        testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Text_Delivery_Details_Header()), PropertyUtility.getMessage("Delivery_Details_Header"));
                    } else if (PP_Site.equalsIgnoreCase("service level")) {
                        testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Text_Delivery_Details_Header()), PropertyUtility.getMessage("Service_Delivery_Details_Header"));
                    }
                    String PickupDateTime = action.getText(Page_Partner_Delivery.Text_Pickup_DateTime());


                    String[] splitDate = PickupDateTime.split(" ", 2);
                    String Month = splitDate[0].substring(0, 3);
                    splitDate[0] = Month + " ";
                    PickupDateTime = splitDate[0] + splitDate[1];
                    cucumberContextManager.setScenarioContext("ActualPickupDateTime", PickupDateTime.replaceAll("[()]", "")); //This will be used in Track Delivery List

                    char ch = PickupDateTime.charAt(4);
                    if (PickupDateTime.charAt(4) == '0') {
                        StringBuilder sb = new StringBuilder(PickupDateTime);
                       // sb.deleteCharAt(4); this is not required as day is displayed in xx format
                        PickupDateTime = sb.toString();
                    }
                    PickupDateTime = PickupDateTime.replaceAll("[()]", "");
                    cucumberContextManager.setScenarioContext("PickupDateTime", PickupDateTime); //This will be used further

                    break;
                case "see Done screen":
                    String Customer_Phone="";
                    String site = (String) cucumberContextManager.getScenarioContext("Site");
                    if(site.equalsIgnoreCase("service level")){
                        Customer_Phone = (String) cucumberContextManager.getScenarioContext("Customer_Mobile");
                    }
                    else {
                        Customer_Phone = (String) cucumberContextManager.getScenarioContext("CustomerPhone");
                    }

                    testStepVerify.isEquals(action.getText(Page_Partner_Done.Text_Schedule_Done_Success_Header()), PropertyUtility.getMessage("Done_Success_Header"));
                    String PickupRequest = new DbUtility().getPickupRef(Customer_Phone);
                    //String ScheduledTime = new DbUtility().getScheduledTime(Customer_Phone);
                    //String FromFormat="yyyy-mm-dd HH:mm:ss";
                    //String ToFormat ="MMM dd, YYYY at HH:mm aa z";
                    //String date = utility.GetDateInFormat(ScheduledTime,FromFormat,ToFormat);
                    //String ST = DateFormat("MMM dd, YYYY at HH:mm aa z",ScheduledTime);
                    //cucumberContextManager.setScenarioContext("Scheduled_Time",date);
                    //cucumberContextManager.setScenarioContext("pickupRequestPartner", PickupRequest);
                    cucumberContextManager.setScenarioContext("PICKUP_REQUEST",PickupRequest);
                    break;
                case "see the trip in the Delivery List":
                    String scheduled_time =(String) cucumberContextManager.getScenarioContext("Schedule_Date_Time");
                    scheduled_time =scheduled_time.replace("at","").replace("(","").replace(")","").replace("  "," ");
                    DateTimeFormatter dft = DateTimeFormatter.ofPattern("MMMM dd, yyyy h:mm a z", Locale.ENGLISH);//for checking the MMMM month format
                    DateTimeFormatter dft1 = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a z",Locale.ENGLISH);//for converting to MMM month format
                    String geoLabel = utility.getTimeZoneBasedOnGeofenceId();
                    TimeZone zone = TimeZone.getTimeZone(geoLabel);
                    ZonedDateTime abc = LocalDateTime.parse(scheduled_time,dft).atZone(zone.toZoneId());
                    scheduled_time = dft1.format(abc);
                    scheduled_time = utility.getbungiiDayLightTimeValue(scheduled_time);

                    StringBuilder sb = new StringBuilder(scheduled_time);
                    sb.insert(13, "at ");
                    scheduled_time = sb.toString();
                    logger.detail("ScheduledTime : "+scheduled_time);
                    cucumberContextManager.setScenarioContext("Partner_Schedule_Time",scheduled_time);
                    String customer =(String) cucumberContextManager.getScenarioContext("Customer_Name");
                    action.clear(Page_Partner_Delivery_List.Textbox_Search());
                    logger.detail("Xpath : "+String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]", scheduled_time, customer));
                    testStepAssert.isElementDisplayed(Page_Partner_Delivery_List.Row_DeliveryList(scheduled_time,customer), "Trip for "+ customer +"[Scheduled Time :"+scheduled_time+"] should be displayed on partner portal", "Trip is displayed on partner portal", "Trip is not displayed on partner portal");
                    break;
                case "see the trip details":
                    String emailPickupAddress = action.getText(Page_Partner_Delivery.Text_Pick_Address());
                    cucumberContextManager.setScenarioContext("EmailPickupAddress",emailPickupAddress);
                    String emailDeliveryAddress = action.getText(Page_Partner_Delivery.Text_Delivery_Address());
                    cucumberContextManager.setScenarioContext("EmailDeliveryAddress",emailDeliveryAddress);

                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Delivery_Details_Dashboard()), PropertyUtility.getMessage("Delivery_Details_Dashboard"));
                    break;
                case "see the cancel delivery warning message":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Cancel_Delivery()), PropertyUtility.getMessage("Message_Cancel_Delivery"));
                    break;
                case "see delivery has been cancelled message":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Cancel_Confirmation()), PropertyUtility.getMessage("Confirmation_Message_Delivery_Cancel"));
                    break;
                case "see Canceled trip message":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Cancel_Trip()), PropertyUtility.getMessage("Message_Cancel_Trip"));
                    break;
                case "see Get Estimate screen":
                    testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Label_Get_Estimate_Header(), "Get Estimate START OVER should be shown", "Get Estimate START OVER is shown", "Get Estimate START OVER is not shown");
                    //testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_Get_Estimate_Header()), PropertyUtility.getMessage("Get_Estimate_Header"));
                    break;
                case "see five future days including today":
                    Calendar calendar = Calendar.getInstance();
                    TimeZone fromTimeZone = calendar.getTimeZone();
                    TimeZone toTimeZone = TimeZone.getTimeZone("CST");
                    break;
                case "see Delivery cancellation failed message":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Delivery_Cancellation_Failed()), PropertyUtility.getMessage("Message_Delivery_Cancellation_Failed"));
                    break;
                case "Your delivery has been canceled message":
                    Thread.sleep(2000);
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Pop_Message_Delivery_Cancelled()), PropertyUtility.getMessage("Message_Delivery_Cancelled"));
                    break;
                case "see Service Level":
                    //String xpath ="//div[@class='service-level form-group']/div/p";
                    String Service_Name = action.getText(Page_Partner_Dashboard.Text_Service_Name());
                    cucumberContextManager.setScenarioContext("Selected_service", Service_Name);
                    testStepVerify.isElementDisplayed(Page_Partner_Dashboard.Information_Icon__Service_Level(), "Information icon for service level should be display", "Information icon for service level is display", "Information icon for service level is not display");
                    //testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Text_Service_Level_Edit()),"SERVICE LEVEL EDIT");
                    //testStepAssert.isElementTextEquals(Page_Partner_Dashboard.Text_Service_Level(),"Service Level","Service Level configuration is present","Service Level configuration is not present");
                    break;
                case "see No service selected":
                    testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Text_No_Service()), "No service selected.");
                    break;
                case "see the service name":
                    String Service_Name1 = (String) cucumberContextManager.getScenarioContext("Selected_service");
                    action.JavaScriptScrolldown();
                    String Display_Service_name = action.getText(Page_Partner_Delivery_List.Text_Selected_Service());
                    testStepVerify.isEquals(Display_Service_name, Service_Name1);
                    break;
                case "see Delivery Cost: N/A":
                    String Display_Delivery_Cost = "Delivery Cost: N/A";
                    String NA_Delivery_Cost = action.getText(Page_Partner_Dashboard.Label_Delivery_Cost());
                    testStepVerify.isEquals(NA_Delivery_Cost,Display_Delivery_Cost);
                    break;
                case "see Delivery Cost: N/A on Delivery Details screen":
                    String Shown_Delivery_Cost = "Delivery Cost\nN/A";
                    String NA_Delivery_cost = action.getText(Page_Partner_Delivery.Label_Delivery_Cost());
                    testStepVerify.isEquals(NA_Delivery_cost,Shown_Delivery_Cost);
                    break;
                case "see Ratings submitted successfully message":
                    testStepVerify.isEquals(action.getText(Page_DriverRating.Text_Rating_Submitted_Successfully()),PropertyUtility.getMessage("Ratings_submitted_successfully"));
                    break;
                default:
                    break;
            }
        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step , I Should "+ str,
                    true);
        }
    }

    @And("^I should logout from Partner Portal$")
    public void i_should_logout_from_partner_portal() throws Throwable {
        try{
        action.click(Page_Partner_Done.Dropdown_Setting());
        //Thread.sleep(5000);
        utility.PartnerLogout();

        testStepAssert.isElementDisplayed(action.getElementByXPath("//button[@id='login']"), "SIGN IN button should be displayed on partner portal", "SIGN IN button is displayed on partner portal", "SIGN IN button is not displayed on partner portal");
        //action.getElementByXPath(Page_Partner_Login.Button_Sign_In())
        log("I should be logged out from Partner Portal ","I clicked ", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should be navigated to Login screen$")
    public void i_should_be_navigated_to_login_screen() throws Throwable {
        try{
            testStepAssert.isElementDisplayed(action.getElementByXPath("//button[@id='login']"), "SIGN IN button should be displayed on partner portal", "SIGN IN button is displayed on partner portal", "SIGN IN button is not displayed on partner portal");
            //action.getElementByXPath(Page_Partner_Login.Button_Sign_In())
            log("I should be on Partner Portal login page","I should be on Partner Portal login page.", true);
        }
        catch (Exception e){
            logger.error("Error performing step navigate to login screen", ExceptionUtils.getStackTrace(e));
            error("I should be on Partner Portal login page", "I am not on Partner Portal login page",
                    true);
        }
    }

    @And("^I Clear the browser local storage and refresh the Page$")
    public void i_clear_the_browser_local_storage_and_refresh_the_page() throws Throwable {
        try{
            JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
            js.executeScript(String.format("window.localStorage.clear();"));
            Thread.sleep(5000);
            SetupManager.getDriver().navigate().refresh();
            log("I Clear the browser local storage and refresh the Page","I have cleared the browser local storage and refresh the Page", false);

        }
        catch (Exception e){
            logger.error("Error performing step clearing local storag", ExceptionUtils.getStackTrace(e));
            error("Browser local storage should get clear.", "Browser local storage is not get cleared.",
                    true);
        }
    }

    @And("^I click on Filter and select check/unchecked all checkbox$")
    public void i_click_on_filter() throws Throwable {
        //throw new PendingException();
        try{
        action.click(Page_Partner_Done.Dropdown_Filter());
        action.click(Page_Partner_Done.Checkbox_Check_UnCheck_All());
        log("I click on Filter and select check/unchecked all checkbox","I have clicked on Filter and select check/unchecked all checkbox", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And("^I click on Apply button on Filter$")
    public void i_click_on_apply_button_on_filter() throws Throwable {
        try{
        //throw new PendingException();
        action.click(Page_Partner_Done.Button_Apply());

        log("I click on Apply button on Filter","I have clicked on Apply button on Filter", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should not able to see Filter screen$")
    public void i_should_not_be_able_to_see_filter_screen() throws Throwable {
        //throw new PendingException();
        testStepAssert.isNotElementDisplayed(Page_Partner_Done.Button_Apply(),"Filter window should close and Apply button shouldn't be shown","Filter window is close and Apply button is not shown","Filter window is not close and Apply button is shown");

    }

    @Then("^I open the link to provide driver rating$")
    public void i_open_the_link_to_provide_driver_rating() throws Throwable {
        try{
            utility.NavigateDriverRatingWebLink();

        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Link should get open for providing the driver rating", "Error performing step,Please check logs for more details",
                    true);

        }
    }

    @Then("^I check details on link page open for driver rating$")
    public void i_check_details_on_link_page_open_for_driver_rating() throws Throwable {
        try {
            String BungiiType = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");

            testStepVerify.isElementDisplayed(Page_DriverRating.Label_Delivery_Status(), "Delivery status should be display", "Delivery status is display", "Delivery status is not display");
            testStepVerify.isElementDisplayed(Page_DriverRating.Text_Successfully_Completed(), "Successfully Completed! text should be display", "Successfully Completed! text is display", "Successfully Completed! text is not display");
            testStepVerify.isElementTextEquals(Page_DriverRating.Text_Sucessfully_Completed_Para(), PropertyUtility.getMessage("Driver_Rating_Successfully_Completed_Text"));
            String Driver_Name = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            testStepVerify.isElementDisplayed(Page_DriverRating.Label_Driver_Name(Driver_Name), "Driver1 name should be display", "Driver1 name is display", "Driver1 name is not display");
            testStepVerify.isElementDisplayed(Page_DriverRating.Image_All_Stars(), "All rating stars for driver1 should be display", "All rating stars for driver1 is display", "All rating stars for driver1 is not display");


            if(BungiiType.equalsIgnoreCase("Solo")){
                WebElement Image_All_Ok = Page_DriverRating.Image_All_Ok_Solo();
                String expectedImage = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"), PropertyUtility.getImageLocations("ALL_OK"));
                String expectedImageSource = utility.encodeImage(expectedImage);
                String actualImageSource = Image_All_Ok.getAttribute("src");

                testStepVerify.isEquals(actualImageSource, expectedImageSource);
            }
            if(BungiiType.equalsIgnoreCase("Duo")){
                String Driver_Name2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
                testStepVerify.isElementDisplayed(Page_DriverRating.Label_Driver_Name(Driver_Name2), "Driver2 name should be display", "Driver2 name is display", "Driver2 name is not display");
                testStepVerify.isElementDisplayed(Page_DriverRating.Image_All_Stars(), "All rating stars for driver2 should be display", "All rating stars for driver 2 is display", "All rating stars for driver 2 is not display");
                WebElement Image_All_Ok = Page_DriverRating.Image_All_Ok_Duo();
                String expectedImage = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"), PropertyUtility.getImageLocations("ALL_OK"));
                String expectedImageSource = utility.encodeImage(expectedImage);
                String actualImageSource = Image_All_Ok.getAttribute("src");

                testStepVerify.isEquals(actualImageSource, expectedImageSource);
            }
            //ALl Ok Image base64 encoded comparison


        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("I check that the details on link page open for driver rating", "Error performing step,Please check logs for more details",
                    true);

        }

    }

    @Then("^I check that rating stars are not shown submission$")
    public void i_check_that_rating_stars_are_not_shown_submission() throws Throwable {
        try{
                testStepVerify.isElementNotDisplayed(Page_DriverRating.Image_All_Stars(true),"All rating stars should not be display","All rating stars is not display", "All rating stars is display");
                testStepVerify.isElementNotDisplayed(Page_DriverRating.Button_Submit(true),"Submit button should not be shown.","Submit button is not shown.","Submit button is shown.");

        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("I check that the rating stars are not shown submission", "Error performing step,Please check logs for more details",
                    true);

        }
    }

    @Then("^I change the rating to \"([^\"]*)\" stars for \"([^\"]*)\"$")
    public void i_change_the_rating_to_something_stars_for_something(String Rating, String DriverType) throws Throwable {
        try{
            if(DriverType.equalsIgnoreCase("Driver1")){
            int i;
            String driverRating="";
            switch (Rating){
                case "4":
                    i=4;
                    action.JavaScriptClick(Page_DriverRating.Image_Stars(i));
                    Thread.sleep(1000);
                    driverRating="4";
                    break;
                case "3":
                    i=3;
                    action.JavaScriptClick(Page_DriverRating.Image_Stars(i));
                    Thread.sleep(1000);
                    driverRating="3";
                    break;
                case "2":
                    i=2;
                    action.JavaScriptClick(Page_DriverRating.Image_Stars(i));
                    Thread.sleep(1000);
                    driverRating="2";
                    break;
                case "1":
                    i=1;
                    action.JavaScriptClick(Page_DriverRating.Image_Stars(i));
                    Thread.sleep(1000);
                    driverRating="1";
                    break;
                default:
                    i=5;
                    action.JavaScriptClick(Page_DriverRating.Image_Stars(i));
                    Thread.sleep(1000);
                    driverRating="5";
                    break;
            }
            cucumberContextManager.setScenarioContext("Driver_Rating",driverRating);
            }
            else if(DriverType.equalsIgnoreCase("Driver2")){
                int i;
                String driver2Rating="";
                switch (Rating){
                    case "4":
                        i=4;
                        action.JavaScriptClick(Page_DriverRating.Image_Stars2(i));
                        Thread.sleep(1000);
                        driver2Rating="4";
                        break;
                    case "3":
                        i=3;
                        action.JavaScriptClick(Page_DriverRating.Image_Stars2(i));
                        Thread.sleep(1000);
                        driver2Rating="3";
                        break;
                    case "2":
                        i=2;
                        action.JavaScriptClick(Page_DriverRating.Image_Stars2(i));
                        Thread.sleep(1000);
                        driver2Rating="2";
                        break;
                    case "1":
                        i=1;
                        action.JavaScriptClick(Page_DriverRating.Image_Stars2(i));
                        Thread.sleep(1000);
                        driver2Rating="1";
                        break;
                    default:
                        i=5;
                        action.JavaScriptClick(Page_DriverRating.Image_Stars2(i));
                        Thread.sleep(1000);
                        driver2Rating="5";
                        break;
                }
                cucumberContextManager.setScenarioContext("Driver2_Rating",driver2Rating);
            }

        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Driver rating should get change to "+Rating, "Driver rating is not change to "+Rating,
                    true);

        }
    }

    @And("^I click on \"([^\"]*)\" button on Driver Rating Page$")
    public void i_click_on_something_button_on_driver_rating_page(String buttonType) throws Throwable {
        try{
            action.click(Page_DriverRating.Button_Submit());

        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("I should able to click "+buttonType+" button", "I am not able to click "+buttonType+" button",
                    true);

        }
    }

    @Then("^I verify the submitted driver rating value in the database$")
    public void i_verify_the_submitted_driver_rating_value_in_the_database() throws Throwable {
        try{
            String Bungii_Type= (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            String PickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String PickupId=dbUtility.getPickupId(PickupRef);

            List <HashMap<String,Object>>dbDriverRating= dbUtility.getDriverRating(PickupId);

            if(Bungii_Type.equalsIgnoreCase("Solo")||Bungii_Type.equalsIgnoreCase("Solo Scheduled")) {
                String Driver1Rating = dbDriverRating.get(0).toString();
                testStepVerify.isEquals((String) cucumberContextManager.getScenarioContext("Driver_Rating"),Driver1Rating);

            }else if(Bungii_Type.equalsIgnoreCase("Duo")) {
                String Driver1Rating = dbDriverRating.get(0).toString();
                String Driver2Rating = dbDriverRating.get(1).toString();
                testStepVerify.isEquals((String) cucumberContextManager.getScenarioContext("Driver_Rating"),Driver1Rating);
                testStepVerify.isEquals((String) cucumberContextManager.getScenarioContext("Driver2_Rating"),Driver2Rating);
            }



        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Correct submitted driver rating value shoud be shown in database", "Correct submitted driver rating value is not shown in database",
                    true);

        }
    }

}

