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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.StaleElementReferenceException;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

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

        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Time());
        action.click(Page_Partner_Dashboard.Pickup_Time3_());

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
                cucumberContextManager.setScenarioContext("OldEstimatedCost",Estimated_Cost);
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
        cucumberContextManager.setScenarioContext("BUNGII_TYPE", Type);

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
        String Old_Estimated_Cost = (String)cucumberContextManager.getScenarioContext("OldEstimatedCost");

        testStepVerify
                .isFalse(New_Estimated_Cost.equals(Old_Estimated_Cost),
                        "total Estimated cost is calculated considering  Loading/unloading time",
                        "Total Estimate cost is recalculated , previous cost is" + Old_Estimated_Cost + " , new cost is" + New_Estimated_Cost,
                        "Total Estimate cost was not recalculated");
        Old_Estimated_Cost = New_Estimated_Cost;

    }

    @Then("^I should be able to see the respective bungii partner portal trip with the below status$")
    public void i_should_be_able_to_see_the_respective_bungii_partner_portal_trip_with_the_below_status(DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String status = dataMap.get("Status").trim();
            //String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            //String tripType[] = tripTypeAndCategory.split(" ");
            String tripType = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
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
            String driver = driver1;
            if (tripType.equalsIgnoreCase("duo"))
                driver = driver1 + "," + driver2;
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
                        action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
                        //action.click(admin_ScheduledTripsPage.Order_Initial_Request());
                        retrycount--;
                        retry = true;
                    }

                }
                Thread.sleep(3000);
                int retryCount = 1;
                while (!action.getText(SetupManager.getDriver().findElement(By.xpath(xpath))).equalsIgnoreCase(status)) {
                    if (retryCount >= 20) break;
                    action.refreshPage();
                    Thread.sleep(15000); //Wait for 15 seconds
                    retryCount++;
                }
                cucumberContextManager.setScenarioContext("XPATH", xpath);
                testStepAssert.isElementTextEquals(action.getElementByXPath(xpath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

            } else {
                //String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[3]", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : StringUtils.capitalize(tripType[0]), driver, customer);
                String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[2]", StringUtils.capitalize(tripType).equalsIgnoreCase("ONDEMAND") ? "Solo" : StringUtils.capitalize(tripType), driver, customer);

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
                        action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
                        //action.click(admin_ScheduledTripsPage.Order_Initial_Request());
                        retrycount--;
                        retry = true;
                    }

                }
                Thread.sleep(3000);
                int retryCount = 1;
                while (!action.getText(SetupManager.getDriver().findElement(By.xpath(XPath))).equalsIgnoreCase(status)) {
                    if (retryCount >= 20) break;
                    action.refreshPage();
                    Thread.sleep(15000); //Wait for 15 seconds
                    retryCount++;
                }
                cucumberContextManager.setScenarioContext("XPATH", XPath);
                testStepAssert.isElementTextEquals(action.getElementByXPath(XPath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
            }
        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);

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



}
