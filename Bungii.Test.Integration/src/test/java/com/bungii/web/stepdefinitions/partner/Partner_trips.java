package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
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

public class Partner_trips extends DriverBase {
    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    Partner_DeliveryList Page_Partner_Delivery_List = new Partner_DeliveryList();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    com.bungii.web.utilityfunctions.DbUtility dbUtility = new DbUtility();

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

        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Date());

        switch(PickupDate){
            case "Today":
                action.click(Page_Partner_Dashboard.Pickup_Date_Today());
                break;
            case "Today+1":
                action.click(Page_Partner_Dashboard.Pickup_date_Today_1());
                break;
            case "Today+2":
                action.click(Page_Partner_Dashboard.Pickup_date_Today_2());
                break;
            case "Today+3":
                action.click(Page_Partner_Dashboard.Pickup_date_Today_3());
                break;
            case "Today+4":
                action.click(Page_Partner_Dashboard.Pickup_date_Today_4());
                break;
            default:break;

        }

       // action.click(Page_Partner_Dashboard.Dropdown_Pickup_Time());
       // action.click(Page_Partner_Dashboard.Pickup_Time3_());

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


    }

    @Then("^I should see \"([^\"]*)\"$")
    public void i_should_see_something_on_screen(String str){
        switch (str)
        {
            case "Estimated Cost":
                String Total_Estimated_Cost = action.getText(Page_Partner_Dashboard.Label_Estimated_Cost());
                //String Estimated_Cost_Label = Total_Estimated_Cost.substring(0,Total_Estimated_Cost.indexOf(':'));
                String[] Split_Total_estimated_Cost = Total_Estimated_Cost.split(":");
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
    }

    @And("^I select the Scheduled Bungii from Delivery List$")
    public void i_select_scheduled_bungii_from_delivery_list(){

        action.click(Page_Partner_Delivery_List.Record1());
    }


    @When("^I request for \"([^\"]*)\" Bungii trip in partner portal in \"([^\"]*)\" geofence$")
    public void i_request_something_bungii_trip_in_partner_portal_for_some_geofence(String Type,String geofence, DataTable data) throws InterruptedException {
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
                String Delivery_Address;
                if(geofence.equalsIgnoreCase("washingtondc")) {

                    Delivery_Address = PropertyUtility.getDataProperties("partner.change.drop.washingtondc");

                }
                else{
                    Delivery_Address = dataMap.get("Delivery_Address");

                }

               // action.click(Page_Partner_Dashboard.Delivery_Address());
                //Thread.sleep(1000);
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
                        "total Estimated cost is calculated considering  Loading/unloading time",
                        "Total Estimate cost is recalculated , previous cost is" + Old_Estimated_Cost + " , new cost is" + New_Estimated_Cost,
                        "Total Estimate cost was not recalculated");
        Old_Estimated_Cost = New_Estimated_Cost;

    }

    @Then("^I should see correct estimated price$")
    public void i_should_see_correct_estimated_price(){
        try {
            //String distance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
            //String estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
            String estimate = (String)cucumberContextManager.getScenarioContext("Estimated_Cost");
            //estimate = estimate.replace("~$", "");
            String loadTime = (String) cucumberContextManager.getScenarioContext("BUNGII_LOADTIME");
            //com.bungii.ios.utilityfunctions.GeneralUtility utility = new com.bungii.ios.utilityfunctions.GeneralUtility();
            com.bungii.web.utilityfunctions.GeneralUtility utility = new com.bungii.web.utilityfunctions.GeneralUtility();
            //get data from DB instead of Phone Screen
            //TODO: verify DB and phone value
            String totalDistance = dbUtility.getEstimateDistance();
            String totalEstimateTime = dbUtility.getEstimateTime();


            double expectedValue = utility.bungiiEstimate(totalDistance, loadTime, totalEstimateTime, "");

            String actualValue = estimate.substring(0, estimate.length() - 1);
            String truncValue = new DecimalFormat("#.00").format(expectedValue);
            //  String truncValue = new DecimalFormat("#.##").format(expectedValue);
            testStepVerify.isEquals(estimate.trim(), truncValue.trim(), "Estimate value for trip should be properly displayed.(NOTE: Failure might me due to truncation)", "Expected Estimate value for bungii is" + truncValue + " and Actual value is" + actualValue + ",(Truncate to single float point)", "Expected Estimate value for bungii is" + truncValue + " and Actual value is" + estimate);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }


    }

    @Then("^I should be able to see the respective bungii partner portal trip with the below status$")
    public void i_should_be_able_to_see_the_respective_bungii_partner_portal_trip_with_the_below_status(DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String status = dataMap.get("Status").trim();
            //String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            //String tripType[] = tripTypeAndCategory.split(" ");
            String tripType = (String) cucumberContextManager.getScenarioContext("Partner_Bungii_type");
   //         String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
     //       String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
            String customer = (String) cucumberContextManager.getScenarioContext("Customer_Name");
            String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");
            String pickupRef = (String) cucumberContextManager.getScenarioContext("pickupRequest");


            String geofenceName = getGeofence(geofence);
            //action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
            //action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
            //action.click(admin_LiveTripsPage.TextBox_Search_Field());
            action.clearSendKeys(admin_LiveTripsPage.TextBox_Search_Field(),pickupRef);
            action.click(admin_LiveTripsPage.Button_Search());

            cucumberContextManager.setScenarioContext("STATUS", status);
       //     String driver = driver1;
         //   if (tripType.equalsIgnoreCase("duo"))
           //     driver = driver1 + "," + driver2;
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
                        //action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
                        //action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
                        //action.click(admin_ScheduledTripsPage.Order_Initial_Request());
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


                /*
                Thread.sleep(3000);
                int retryCount = 1;
                while (!action.getText(SetupManager.getDriver().findElement(By.xpath(xpath))).equalsIgnoreCase(status)) {
                    if (retryCount >= 20) break;
                    action.refreshPage();
                    Thread.sleep(15000); //Wait for 15 seconds
                    retryCount++;
                }
                */

                cucumberContextManager.setScenarioContext("XPATH", xpath);
                String St2 = status;
                String St1 = action.getElementByXPath(xpath).getText();
                testStepAssert.isElementTextEquals(action.getElementByXPath(xpath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

        } else {
                //String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[3]", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : StringUtils.capitalize(tripType[0]), driver, customer);
                //String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[2]", StringUtils.capitalize(tripType).equalsIgnoreCase("ONDEMAND") ? "Solo" : StringUtils.capitalize(tripType), driver, customer);
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
                        //action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
                        //action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
                        //action.click(admin_ScheduledTripsPage.Order_Initial_Request());
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

                /*
                while (!action.getText(SetupManager.getDriver().findElement(By.xpath(XPath))).equalsIgnoreCase(status)) {
                    if (retryCount >= 20) break;
                    action.refreshPage();
                    Thread.sleep(15000); //Wait for 15 seconds
                    retryCount++;
                }

                 */
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
        }

        String xpath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[4]", Delivery_Date, CustomerName);
        if(!Partner_Status.equalsIgnoreCase("Completed")) {
            action.refreshPage();
        }
        Thread.sleep(1000);
        testStepAssert.isElementTextEquals(action.getElementByXPath(xpath), Partner_Status, "Trip Status " + Partner_Status + " should be updated", "Trip Status " + Partner_Status + " is updated", "Trip Status " + Partner_Status + " is not updated");
        if(!Partner_Status.equalsIgnoreCase("Completed")) {
            SetupManager.getDriver().switchTo().window(tabs.get(1));
        }
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

            action.click(Page_Partner_Dashboard.Dropdown_Pickup_Time());
            action.click(Page_Partner_Dashboard.Pickup_Time1());
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
