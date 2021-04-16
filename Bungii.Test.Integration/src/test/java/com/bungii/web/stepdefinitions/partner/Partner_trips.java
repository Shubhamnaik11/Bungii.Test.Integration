package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.web.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryList;
import com.bungii.web.stepdefinitions.admin.Admin_BusinessUsersSteps;
import com.bungii.web.stepdefinitions.admin.Admin_TripsSteps;
import com.bungii.web.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.StaleElementReferenceException;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.bungii.common.manager.ResultManager.*;
import static com.bungii.web.utilityfunctions.DbUtility.getActualPrice;
import static com.bungii.web.utilityfunctions.DbUtility.getBungiiRate;

public class Partner_trips extends DriverBase {
    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    Partner_DeliveryList Page_Partner_Delivery_List = new Partner_DeliveryList();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    com.bungii.web.utilityfunctions.DbUtility dbUtility = new DbUtility();
    com.bungii.web.utilityfunctions.GeneralUtility webUtility = new com.bungii.web.utilityfunctions.GeneralUtility();

    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();
    Admin_CustomerPage admin_CustomerPage = new Admin_CustomerPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();
    Admin_EditScheduledBungiiPage admin_EditScheduledBungiiPage = new Admin_EditScheduledBungiiPage();

    Admin_BusinessUsersSteps admin_businessUsersSteps = new Admin_BusinessUsersSteps();
    //ActionManager action = new ActionManager();
    //private static LogUtility logger = new LogUtility(Admin_TripsSteps.class);
    //com.bungii.web.utilityfunctions.GeneralUtility utility = new com.bungii.web.utilityfunctions.GeneralUtility();



    @When("^I request for \"([^\"]*)\" Bungii trip in partner portal$")
    public void i_request_something_bungii_trip_in_partner_portal(String Type, DataTable data) throws InterruptedException {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

        String Pickup_Address = dataMap.get("Pickup_Address");
        String Delivery_Address = dataMap.get("Delivery_Address");
        cucumberContextManager.setScenarioContext("Delivery_Address", Delivery_Address);
        String Load_Unload = dataMap.get("Load_Unload_Time");

        //int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;
        int numberOf_Driver = dataMap.get("Driver").trim().equalsIgnoreCase("duo") ? 2 :1;

        ///cucumberContextManager.setScenarioContext("GEOFENCE", geofence);


        switch (Type)
        {
            case "Solo":
                //action.click(Page_Partner_Dashboard.Partner_Solo());

                action.click(Page_Partner_Dashboard.Button_Pickup_Edit());


               action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(),Pickup_Address+ Keys.TAB);
               action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
               // Thread.sleep(2000);
                action.click(Page_Partner_Dashboard.List_Pickup_Address());

                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(),Delivery_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                //Thread.sleep(1000);
                action.click(Page_Partner_Dashboard.List_Delivery_Address());

                Thread.sleep(5000);

                action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                switch (Load_Unload) {
                    case "15 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                        break;
                    case "30 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                        break;
                    case "45 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                        break;
                    case "60 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                        break;
                    case "75 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                        break;
                    case "90+ minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                        break;
                    default:break;
                }
            break;
            case "Duo":
                action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                action.click(Page_Partner_Dashboard.Button_Pickup_Edit());

                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(),Pickup_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                Thread.sleep(2000);
                action.click(Page_Partner_Dashboard.List_Pickup_Address());

                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(),Delivery_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                Thread.sleep(2000);
                action.click(Page_Partner_Dashboard.List_Delivery_Address());

                action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                switch (Load_Unload) {
                    case "15 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                        break;
                    case "30 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                        break;
                    case "45 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                        break;
                    case "60 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                        break;
                    case "75 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                        break;
                    case "90+ minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                        break;
                    default:break;
                }
             break;
            default: break;
        }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting pickup and drop off address.", true);
        }

    }

    @When("^I clear the existing pickup address details$")
    public void i_clear_the_existing_pickup_address_details(){

        action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
        action.click(Page_Partner_Dashboard.Button_PickupClear());
    }

       @When("^I click on Pickup date$")
    public  void i_click_on_pickup_date(){

        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Date());
    }

    @Then("^I should see five future days including today$")
    public void i_should_see_five_future_days_including_today() {

        testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Pickup_Date_Today(),"Today date should be display","Today date is display.","Today day is not displayed.");
        testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Pickup_date_Today_1(),"Second day should be display","Second day is display.","Second day is not displayed.");
        testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Pickup_date_Today_2(),"Third day should be display","Third day is display.","Third day is not displayed.");
        testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Pickup_date_Today_3(),"Fourth day should be display","Fourth day is display.","Fourth day is not displayed.");
        testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Pickup_date_Today_4(),"Fifth day should be display","Fifth day is display.","Fifth day is not displayed.");

        action.click(Page_Partner_Dashboard.Pickup_date_Today_1());
    }



    @And("^I select Pickup Date and Pickup Time$")
    public  void i_select_pickupdate_time(DataTable data) throws Throwable {

        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String PickupDate =dataMap.get("PickUp_Date");
        String PickUpTime =dataMap.get("PickUp_Time");
        String strTime = "";

        if (PickupDate.equalsIgnoreCase("NEXT_POSSIBLE")) {
            strTime = enterTime(PickUpTime);
            strTime=strTime.replace("am","AM").replace("pm","PM");
        }

        cucumberContextManager.setScenarioContext("ScheduledDate",strTime);

    }

    @And("^I select Pickup Date and Pickup Time on partner portal$")
    public  void i_select_pickupdate_time_on_partner_portal(DataTable data) throws Throwable {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String PickupDate =dataMap.get("PickUp_Date");
        String PickUpTime =dataMap.get("PickUp_Time");
        String strTime = "";


        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Date());

        switch(PickupDate){
            case "Today":
                action.click(Page_Partner_Dashboard.Pickup_Date_Today());
                strTime = action.getText(Page_Partner_Dashboard.Pickup_Date());
                break;
            case "Today+1":
                action.click(Page_Partner_Dashboard.Pickup_date_Tomorrow());
                strTime = action.getText(Page_Partner_Dashboard.Pickup_Date());
                break;
            case "Today+2":
                action.click(Page_Partner_Dashboard.Pickup_date_Today_2());
                strTime = action.getText(Page_Partner_Dashboard.Pickup_Date());
                break;
            case "Today+3":
                action.click(Page_Partner_Dashboard.Pickup_date_Today_3());
                strTime = action.getText(Page_Partner_Dashboard.Pickup_Date());
                break;
            case "Today+4":
                action.click(Page_Partner_Dashboard.Pickup_date_Today_4());
                strTime = action.getText(Page_Partner_Dashboard.Pickup_Date());
                break;
            default:break;

        }
        cucumberContextManager.setScenarioContext("ScheduledDate",strTime);
        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Time());

        if(!PickUpTime.equalsIgnoreCase("")) {
            Thread.sleep(2000);
            action.getElementByXPath("//li[contains(text(),'"+PickUpTime+"')]").click();
        }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting pickup and drop off address.", true);
        }

    }

    @And("^I select Next Possible Pickup Date and Pickup Time$")
    public  void i_select_next_possible_pickupdate_time(DataTable data) throws Throwable {

        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        //String PickupDate =dataMap.get("PickUp_Date");
        String Next_PickUpTime =dataMap.get("Trip_Time");
        String strTime = "";

        //if (Next_PickUpTime.equalsIgnoreCase("NEXT_POSSIBLE")) {
            strTime = enterTime(Next_PickUpTime);
            strTime=strTime.replace("am","AM").replace("pm","PM");

        cucumberContextManager.setScenarioContext("Scheduled_Time", strTime);


    }

    @And("^I check correct price is shown for selected service$")
    public void i_check_correct_price_is_shown_for_selected_service(){
        String Alias_Name= (String) cucumberContextManager.getScenarioContext("Alias");
        String Selected_Service =(String) cucumberContextManager.getScenarioContext("Selected_service");
        String Trip_Type = (String) cucumberContextManager.getScenarioContext("Partner_Bungii_type");
        int Driver_Number=1;

        if(Trip_Type.equalsIgnoreCase("Duo")){
            Driver_Number=2;
        }

        String Display_Price = action.getText(Page_Partner_Dashboard.Label_DeliveryCostDelivery());
                //action.getElementByXPath("//h2[text()='Delivery Cost']//following::span/strong").getText();
        Display_Price = Display_Price.substring(1);

        String Estimate_distance = dbUtility.getEstimateDistance(Alias_Name);
        double Estimate_distance_value = Double.parseDouble(Estimate_distance);

        String Last_Tier_Milenge_Min_Range = dbUtility.getMaxMilengeValue(Alias_Name,Selected_Service);
        double Last_Tier_Milenge_Min_Range_value = Double.parseDouble(Last_Tier_Milenge_Min_Range);

        String Price="";
        if(Estimate_distance_value <= Last_Tier_Milenge_Min_Range_value) {
            Price = dbUtility.getServicePrice(Alias_Name, Driver_Number, Estimate_distance, Selected_Service);
        }
        else{
            Price = dbUtility.getServicePriceLastTier(Alias_Name, Driver_Number, Estimate_distance, Selected_Service);
        }

        String Estimated_Price = (String) cucumberContextManager.getScenarioContext("Price_Estimate_Page");



        testStepVerify.isEquals(Display_Price,Estimated_Price);
        testStepVerify.isEquals(Display_Price,Price);
        log("For Selected "+Selected_Service+" service correct price should be shown.","For Selected "+Selected_Service+" service correct price is shown.", true);
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void i_should_see_something_on_screen(String str){
        try{
        switch (str)
        {
            case "Estimated Cost":
                String Total_Estimated_Cost = action.getText(Page_Partner_Dashboard.Label_Estimated_Cost());
                //String Estimated_Cost_Label = Total_Estimated_Cost.substring(0,Total_Estimated_Cost.indexOf(':'));
                String[] Split_Total_estimated_Cost = Total_Estimated_Cost.split(": ");
                String Estimated_Cost_Label = Split_Total_estimated_Cost[0];
                String Estimated_Cost = Split_Total_estimated_Cost[1];
                cucumberContextManager.setScenarioContext("Estimated_Cost",Estimated_Cost);
                testStepVerify.isEquals(Estimated_Cost_Label, PropertyUtility.getMessage("Estimated_Cost_Label"));
                break;
            case "see validation message for mandatory fields":
                String Blank_Pickup_Address = PropertyUtility.getMessage("Message_Blank_Pickup");
                String Blank_Delivery_Address = PropertyUtility.getMessage("Message_Blank_Delivery");
                String Blank_Load_Unload_Time = PropertyUtility.getMessage("Message_Blank_Load_Unload_Time");
                String Highlighted_Fields = PropertyUtility.getMessage("Message_Highlighted_Fileds");

                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Message_Blank_Pickup()),Blank_Pickup_Address);
                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Message_Blank_Delivery()),Blank_Delivery_Address);
                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Message_Blank_LoadUnload_Time()),Blank_Load_Unload_Time);
                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Message_Highlighted_Fields()),Highlighted_Fields);
                break;
            default: break;
        }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting pickup and drop off address.", true);
        }
    }

    @And("^I select the Scheduled Bungii from Delivery List$")
    public void i_select_scheduled_bungii_from_delivery_list(){
        String scheduled_time =(String) cucumberContextManager.getScenarioContext("Partner_Schedule_Time");;
        String customer =(String) cucumberContextManager.getScenarioContext("Customer_Name");
        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]", scheduled_time, customer);

        action.getElementByXPath(XPath).click();
        //action.click(Page_Partner_Delivery_List.Record1());
        log("I should able to select the Scheduled Bungii from Delivery List","Scheduled Bungii from Delivery List get selected.",true);
    }

    @And("^I close the Trip Delivery Details page$")
    public void i_close_the_trip_delivery_details_page(){
        action.click(Page_Partner_Delivery_List.Button_Close());
        log("I should able to close the Trip Delivery Details page.","I am able to closed the Trip Delivery Details page.", true);
    }

    @When("^I request for \"([^\"]*)\" Bungii trip in partner portal in \"([^\"]*)\" $")
    public void i_request_something_bungii_trip_in_partner_portal_for_some_geofence(String Type,String geofence, DataTable data) throws InterruptedException {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String Pickup_Address;
        String Delivery_Address;

        //cucumberContextManager.setScenarioContext("BUNGII_TYPE", Type);
        cucumberContextManager.setScenarioContext("Partner_Bungii_type",Type);
        if(geofence.equalsIgnoreCase("washingtondc")) {
            //String Pickup_Address = dataMap.get("Pickup_Address");
            Pickup_Address = PropertyUtility.getDataProperties("partner.pickup.washingtondc");
            //String Delivery_Address = dataMap.get("Delivery_Address");
            Delivery_Address = PropertyUtility.getDataProperties("partner.drop.washingtondc");
            cucumberContextManager.setScenarioContext("Delivery_Address", Delivery_Address);
        }
        else{
            Pickup_Address = dataMap.get("Pickup_Address");
            Delivery_Address = dataMap.get("Delivery_Address");
            cucumberContextManager.setScenarioContext("Delivery_Address", Delivery_Address);
        }
        String Load_Unload = dataMap.get("Load_Unload_Time");

        //int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;
        int numberOf_Driver = dataMap.get("Driver").trim().equalsIgnoreCase("duo") ? 2 :1;

        cucumberContextManager.setScenarioContext("GEOFENCE", geofence);

        switch (Type)
        {
            case "Solo":
                //action.click(Page_Partner_Dashboard.Partner_Solo());

                action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
                action.click(Page_Partner_Dashboard.Button_PickupClear());

                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(),Pickup_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                Thread.sleep(1000);
                action.click(Page_Partner_Dashboard.List_Pickup_Address());

                Thread.sleep(2000);
                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(),Delivery_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                //Thread.sleep(1000);
                action.click(Page_Partner_Dashboard.List_Delivery_Address());

                Thread.sleep(5000);

                action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                switch (Load_Unload) {
                    case "15 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",15);
                        break;
                    case "30 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",30);
                        break;
                    case "45 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",45);
                        break;
                    case "60 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",60);
                        break;
                    case "75 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",75);
                        break;
                    case "90+ minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",90);
                        break;
                    default:break;
                }
                break;
            case "Duo":
                action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
                action.click(Page_Partner_Dashboard.Button_PickupClear());

                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(),Pickup_Address+ Keys.TAB);
                //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                Thread.sleep(1000);
                action.click(Page_Partner_Dashboard.List_Pickup_Address());

                Thread.sleep(2000);
                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(),Delivery_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                //Thread.sleep(1000);
                action.click(Page_Partner_Dashboard.List_Delivery_Address());

                action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());

                switch (Load_Unload) {
                    case "15 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                        break;
                    case "30 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                        break;
                    case "45 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                        break;
                    case "60 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                        break;
                    case "75 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                        break;
                    case "90+ minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                        break;
                    default:break;
                }
                break;
            default: break;
        }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting pickup and drop off address.", true);
        }
    }

    @When("^I click on \"([^\"]*)\" information icon and verify its text contents$")
    public void i_click_on_some_information_icon(String Information_Icon){
        String expectedMessage = "", actualMessage = "";
        switch (Information_Icon){
            case "WHAT’S NEEDED?":
                action.click(Page_Partner_Dashboard.Information_Icon_Whats_Needed());
                expectedMessage = PropertyUtility.getMessage("Partner_What_Needed_Info");
                actualMessage = action.getText(Page_Partner_Dashboard.InnerText_Information_Icon());
                action.click(Page_Partner_Dashboard.Information_Icon_Whats_Needed());
                break;
            case "Delivery Address":
                action.click(Page_Partner_Dashboard.Information_Icon_Delivery_Address());
                expectedMessage = PropertyUtility.getMessage("Partner_Delivery_Address_Info");
                actualMessage = action.getText(Page_Partner_Dashboard.InnerText_Information_Icon());
                action.click(Page_Partner_Dashboard.Information_Icon_Delivery_Address());
                break;
            case "Load/Unload Time":
                action.click(Page_Partner_Dashboard.Information_Icon_LoadUpload());
                expectedMessage = PropertyUtility.getMessage("Partner_LoadUnload_Info");
                actualMessage = action.getText(Page_Partner_Dashboard.InnerText_Information_Icon());
                action.click(Page_Partner_Dashboard.Information_Icon_LoadUpload());
                break;
            case "PickUp Date":
                action.click(Page_Partner_Dashboard.Information_Icon_Pickup_Date());
                expectedMessage = PropertyUtility.getMessage("Partner_Pickup_Date_Info");
                actualMessage = action.getText(Page_Partner_Dashboard.InnerText_Information_Icon());
                action.click(Page_Partner_Dashboard.Information_Icon_Pickup_Date());
                break;
            default:break;
        }
        testStepVerify.isEquals(expectedMessage,actualMessage);
        //SetupManager.getDriver().switchTo().alert().accept();
        log("I click on Information Icon "+ Information_Icon +"and verify it text contents",
                "I have clicked on Information Icon "+ Information_Icon +" and verified its test contents",true);

    }

    @And("^I change the \"([^\"]*)\" and click on Get Estimate button$")
    public void i_change_something_and_click_on_get_estimate_button(String str,DataTable data) throws InterruptedException {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String Load_Unload = dataMap.get("Load_Unload_Time");

        switch (str){
            case "Load Unload Time":
                action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                switch (Load_Unload) {
                    case "15 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                        break;
                    case "30 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                        break;
                    case "45 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                        break;
                    case "60 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                        break;
                    case "75 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                        break;
                    case "90+ minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                        break;
                    default:break;
                }
                break;
            case "Delivery Address":
                String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");

                String Delivery_Address = dataMap.get("Delivery_Address");

                action.click(Page_Partner_Dashboard.Button_DeliveryClear());
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(),Delivery_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                action.click(Page_Partner_Dashboard.List_Delivery_Address());
                Thread.sleep(2000);

                break;
            default:break;

        }
        action.click(Page_Partner_Dashboard.Button_Get_Estimate());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting pickup and drop off address.", true);
        }
    }

    @And("^I clear the Pickup Address on Get Estimate screen of Partner Portal$")
    public void i_clear_the_pickup_address_on_get_estimate_screen(){

        action.click(Page_Partner_Dashboard.Button_PickupClear());

    }

    @Then("^I check that Address field on Get Estimate screen get clear$")
    public void i_check_that_Address_field_on_get_estimate_screen_get_clear(){
        try{
        String Delivery_Address = action.getText(Page_Partner_Dashboard.Dropdown_Pickup_Address());
        testStepVerify.isEquals(Delivery_Address, "", "Address field on estimate should be clear.", "Address field on estimate page is cleared.", "Address field on estimate is not cleared.");
        } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
        }

    }

    @Then("^Estimate Cost should get recalculate$")
    public void Estimate_Cost_get_recalculate(){
        String Total_Estimated_Cost = action.getText(Page_Partner_Dashboard.Label_Estimated_Cost());
        //String Estimated_Cost_Label = Total_Estimated_Cost.substring(0,Total_Estimated_Cost.indexOf(':'));
        String[] Split_Total_estimated_Cost = Total_Estimated_Cost.split(":");
        //String Estimated_Cost_Label = Split_Total_estimated_Cost[0];
        String New_Estimated_Cost = Split_Total_estimated_Cost[1];
        String Old_Estimated_Cost = (String)cucumberContextManager.getScenarioContext("Estimated_Cost");

        testStepVerify
                .isFalse(New_Estimated_Cost.equals(Old_Estimated_Cost),
                        "total Estimated cost should be recalculated",
                        "Total Estimate cost is recalculated , previous cost is" + Old_Estimated_Cost + " , new cost is" + New_Estimated_Cost,
                        "Total Estimate cost was not recalculated");
        Old_Estimated_Cost = New_Estimated_Cost;

    }

    @Then("^I check correct estimated price calculated on Partner Portal$")
    public void i_should_see_correct_estimated_price(){
        try {
           // String Alias_Name= (String) cucumberContextManager.getScenarioContext("Alias");
            String estimate = (String)cucumberContextManager.getScenarioContext("Estimated_Cost");
            //estimate = estimate.replace("~$", "");
            //String LT = (String)cucumberContextManager.getScenarioContext("LoadUnload_Time");
            //String loadTime = String.valueOf(LT);
            String loadTime = String.valueOf(cucumberContextManager.getScenarioContext("LoadUnload_Time"));

            com.bungii.web.utilityfunctions.GeneralUtility utility = new com.bungii.web.utilityfunctions.GeneralUtility();
            String partnerRef = (String)cucumberContextManager.getScenarioContext("PARTNERREF");

            //TODO: verify DB and phone value
            String totalDistance = dbUtility.getEstimateDistanceByPartnerReference(partnerRef);
            String totalEstimateTime = dbUtility.getEstimateTimeByPartnerReference(partnerRef);

            double expectedValue = utility.bungiiEstimate(totalDistance, loadTime, totalEstimateTime, "");

            String expectedEstimatedCost = String.valueOf(expectedValue);

            String actualValue = estimate.substring(0, estimate.length() - 1);
            String truncValue = new DecimalFormat("#.00").format(expectedValue);

            testStepVerify.isEquals(expectedEstimatedCost,truncValue.trim(), "Estimate value for trip should be properly displayed.(NOTE: Failure might me due to truncation)", "Expected Estimate value for bungii is " + truncValue + " and Actual value is" + actualValue + ",(Truncate to single float point)", "Expected Estimate value for bungii is" + truncValue + " and Actual value is" + estimate);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }


    }

    @Then("^I should be able to see the respective partner portal trip with \"([^\"]*)\" state$")
    public void i_should_be_able_to_see_the_respective_partner_portal_trip_with_something_state(String strArg1) throws Throwable {
        String status = strArg1;
        String ST = (String) cucumberContextManager.getScenarioContext("Scheduled_Time");
        String BT = (String) cucumberContextManager.getScenarioContext("Bungii_Type");
        String Client = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        BT = BT.replace("Solo Scheduled","Solo");
        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]", ST, BT,Client,status);

        int retrycount = 12;

        boolean retry = true;
        while (retry == true && retrycount > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                retry = false;

            } catch (Exception ex) {
                SetupManager.getDriver().navigate().refresh();
                retrycount--;
                retry = true;
            }

        }

        testStepAssert.isElementTextEquals(action.getElementByXPath(XPath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

    }

    @Then("^I should be able to see the respective bungii partner portal trip with the below status$")
    public void i_should_be_able_to_see_the_respective_bungii_partner_portal_trip_with_the_below_status(DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String status = dataMap.get("Status").trim();
            String tripType = (String) cucumberContextManager.getScenarioContext("Partner_Bungii_type");
            String customer = (String) cucumberContextManager.getScenarioContext("Customer_Name");
            String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");
            String pickupRef = (String) cucumberContextManager.getScenarioContext("pickupRequestPartner");


            String geofenceName = getGeofence(geofence);
            action.clearSendKeys(admin_LiveTripsPage.TextBox_Search_Field(),pickupRef);
            action.click(admin_LiveTripsPage.Button_Search());

            cucumberContextManager.setScenarioContext("STATUS", status);

            if (status.equalsIgnoreCase("Scheduled") || status.equalsIgnoreCase("Searching Drivers") || status.equalsIgnoreCase("Driver Removed")) {
                String xpath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[5]", tripType.toUpperCase(), customer);
                int retrycount = 10;

                boolean retry = true;
                while (retry == true && retrycount > 0) {
                    try {
                        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                        retry = false;

                    } catch (Exception ex) {
                        SetupManager.getDriver().navigate().refresh();
                        retrycount--;
                        retry = true;
                    }

                }

                Thread.sleep(3000);
                int retryCount = 1;
                String str1="";
                do{
                    if (retryCount >= 20) break;
                    action.refreshPage();
                    str1 = action.getElementByXPath(xpath).getText();
                    Thread.sleep(15000); //Wait for 15 seconds
                    retryCount++;
                }while (!str1.equalsIgnoreCase(status));

                cucumberContextManager.setScenarioContext("XPATH", xpath);
                String St2 = status;
                String St1 = action.getElementByXPath(xpath).getText();
                testStepAssert.isElementTextEquals(action.getElementByXPath(xpath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

        } else {

                String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[2]", tripType, customer);
                int retrycount = 10;

                boolean retry = true;
                while (retry == true && retrycount > 0) {
                    try {
                        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                        retry = false;
                    } catch (Exception ex) {
                        SetupManager.getDriver().navigate().refresh();
                        retrycount--;
                        retry = true;
                    }

                }
                Thread.sleep(3000);
                int retryCount = 1;
                String str1="";
                do{
                    if (retryCount >= 20) break;
                    action.refreshPage();
                    str1 = action.getElementByXPath(XPath).getText();
                    Thread.sleep(15000); //Wait for 15 seconds
                    retryCount++;
                }while (!str1.equalsIgnoreCase(status));

                cucumberContextManager.setScenarioContext("XPATH", XPath);
                testStepAssert.isElementTextEquals(action.getElementByXPath(XPath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

            }
            //tripType = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);

        }

    }

    @Then("^I view the correct Driver Est. Earnings for geofence based pricing model$")
    public void i_view_the_correct_Driver_Est_Earnings(){
        String DriverEstEarning= action.getElementByXPath("//td[text()='Driver Est. Earnings']/following::td[1]").getText();
        //DriverEstEarning=DriverEstEarning.substring(1,DriverEstEarning.length());
        DriverEstEarning=DriverEstEarning.substring(1,DriverEstEarning.length());

        String ExpectedDriverEstEarning= webUtility.calDriverEstEarning();

        testStepVerify.isEquals(ExpectedDriverEstEarning, DriverEstEarning.trim(), "Driver Est. Earning value for trip should be properly displayed.(NOTE: Failure might me due to truncation)", "Expected Driver Est. Value for bungii is" + ExpectedDriverEstEarning + " and Actual value is" + DriverEstEarning + ",(Truncate to single float point)", "Expected Est. Earning value for bungii is" + ExpectedDriverEstEarning + " and Actual value is" + DriverEstEarning);
        action.getElementByXPath("//div[@id='btnOk']").click();
        log("I should able to view the correct Driver Est. Earnings for geofence based pricing model","I am able to viewed the correct Driver Est. Earnings for geofence based pricing model", true);
    }

    @Then("^I view the correct Driver Earnings for geofence based pricing model$")
    public void i_view_the_correct_Driver_Earnings(){
        String DriverEarning= action.getElementByXPath("//td[text()='Driver Earnings']/following::td[1]").getText();
        //To remove $ sign
        DriverEarning=DriverEarning.substring(1,DriverEarning.length());

        String ExpectedDriverEarning= utility.calDriverEarning();

        testStepVerify.isEquals(ExpectedDriverEarning, DriverEarning.trim(), "Driver Est. Earning value for trip should be properly displayed.(NOTE: Failure might me due to truncation)", "Expected Driver Est. Value for bungii is" + ExpectedDriverEarning + " and Actual value is" + DriverEarning + ",(Truncate to single float point)", "Expected Est. Earning value for bungii is" + ExpectedDriverEarning + " and Actual value is" + DriverEarning);
        action.click(Page_Partner_Delivery_List.Button_OK_Admin_Portal());

        log("I should able to view the correct Driver Earnings for geofence based pricing model.","I am able to viewed the correct Driver Earnings for geofence based pricing model", true);
    }


    @And("^I navigate to partner portal$")
    public void i_navigate_to_partner_portal(){
        ArrayList<String> tabs = new ArrayList<String> (SetupManager.getDriver().getWindowHandles());
        SetupManager.getDriver().switchTo().window(tabs.get(0));
        action.refreshPage();
        log("I should able to navigate to partner portal.","I am able to navigate to partner portal.", true);
    }

    @And("^I navigate to partner portal and view the Trip status with below status$")
    public void i_view_the_scheduled_trips_list_on_the_partner_portal_with_some_status(DataTable data) throws InterruptedException {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String Partner_Status = dataMap.get("Partner_Status").trim();
        ArrayList<String> tabs = new ArrayList<String> (SetupManager.getDriver().getWindowHandles());
        SetupManager.getDriver().switchTo().window(tabs.get(0));

        String Delivery_Date = (String) cucumberContextManager.getScenarioContext("PickupDateTime");
        String CustomerName = (String) cucumberContextManager.getScenarioContext("Customer_Name");
        String DeliveryAddress = (String) cucumberContextManager.getScenarioContext("Delivery_Address");

        if(Partner_Status.equalsIgnoreCase("Completed")){
            action.click(Page_Partner_Delivery_List.Dropdown_Partner_Status());
            action.click(Page_Partner_Delivery_List.Checkbox_Completed_Status());
            action.click(Page_Partner_Delivery_List.Button_Apply());
            Thread.sleep(2000);
            action.click(Page_Partner_Delivery_List.Dropdown_Partner_Status());
        }else if(Partner_Status.equalsIgnoreCase("Canceled")){
            action.click(Page_Partner_Delivery_List.Dropdown_Partner_Status());
            action.click(Page_Partner_Delivery_List.Checkbox_Canceled_Status());
            action.click(Page_Partner_Delivery_List.Button_Apply());
            Thread.sleep(2000);
            action.click(Page_Partner_Delivery_List.Dropdown_Partner_Status());

        }

        String xpath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[4]", Delivery_Date, CustomerName);
        if(!Partner_Status.equalsIgnoreCase("Canceled")) {
            if(!Partner_Status.equalsIgnoreCase("Completed")) {
                action.refreshPage();
            }
        }
        Thread.sleep(1000);
        testStepAssert.isElementTextEquals(action.getElementByXPath(xpath), Partner_Status, "Trip Status " + Partner_Status + " should be updated", "Trip Status " + Partner_Status + " is updated", "Trip Status " + Partner_Status + " is not updated");
            if (!Partner_Status.equalsIgnoreCase("Canceled")) {
                if (!Partner_Status.equalsIgnoreCase("Completed")) {
                    SetupManager.getDriver().switchTo().window(tabs.get(1));
                }
            }
        log("I should able to navigate to partner portal and view the Trip status with status as "+Partner_Status,"I get navigate to partner portal and viewed the Trip status with status as "+Partner_Status, true);
    }

    public String getGeofence(String geofence) {
        String geofenceName = "";
        switch (geofence) {
            case "washingtondc":
                geofenceName = "Washington DC";
                break;

        }
        return geofenceName;
    }

    public String enterTime(String time) throws ParseException {
        String strTime = "";

        if (time.equalsIgnoreCase("NEXT_POSSIBLE")) {
            Date date = getNextScheduledBungiiTime();
           // String[] dateScroll = bungiiTimeForScroll(date);
            strTime = bungiiTimeDisplayInTextArea(date);


           // selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);

            //action.click(Page_Partner_Dashboard.Dropdown_Pickup_Time());
            //action.click(Page_Partner_Dashboard.Pickup_Time1());
        }
        return strTime;
    }

        /**
         * Read property file for minimum difference for next bunii time
         *
         * @return next possible valid bungii time
         */
        public Date getNextScheduledBungiiTime() {
            return getFormatedTime();
        }

    public Date getFormatedTime() {
        Date date1 = Calendar.getInstance().getTime();
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(getDateForTimeZone());
            System.out.println("\t" + date1);
        } catch (Exception e) {
        }

        return date1;
    }

    public String getDateForTimeZone() {
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        int nextTripTime = Integer.parseInt(PropertyUtility.getProp("scheduled.bungii.time"));
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + nextTripTime);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.MINUTE, (15 - unroundedMinutes % 15));

        String strdate = formatter.format(calendar.getTime());
        return strdate;
    }

    /**
     * Format input date and return in required format
     *
     * @param date input date
     * @return formated date
     */
    public String bungiiTimeDisplayInTextArea(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mm a");
        String formattedDate = sdf.format(date);
        //After sprint 27 /26 IST is being added in scheduled page
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

        if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase(""))
            formattedDate = formattedDate + " " + PropertyUtility.getDataProperties("time.label");
        else
            formattedDate = formattedDate + " " + utility.getTimeZoneBasedOnGeofence();
        return formattedDate;
    }

    /**
     * Format input date and return in required format
     *
     * @param date input date
     * @return formated date
     */
    public String[] bungiiTimeForScroll(Date date) {
        //get timezone
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM|h|mm|a");
        String formattedDate = sdf.format(date);
        String[] SplitDate = formattedDate.split("\\|");
        if (DateUtils.isSameDay(date, new Date())) {
            SplitDate[0] = "Today";
        }
        return SplitDate;
    }


}
