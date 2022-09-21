package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.android.pages.admin.LiveTripsPage;
import com.bungii.api.utilityFunctions.GoogleMaps;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.*;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.partner.Partner_Done;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.InputEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;


import static com.bungii.common.manager.ResultManager.*;
import static com.bungii.web.utilityfunctions.DbUtility.*;

public class Admin_TripsSteps extends DriverBase {
    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();
    Admin_CustomerPage admin_CustomerPage = new Admin_CustomerPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();
    Admin_EditScheduledBungiiPage admin_EditScheduledBungiiPage = new Admin_EditScheduledBungiiPage();
    LiveTripsPage liveTripsPage = new LiveTripsPage();
    Admin_BusinessUsersSteps admin_businessUsersSteps = new Admin_BusinessUsersSteps();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_TripsSteps.class);
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    Admin_RevivalPage admin_revivalPage = new Admin_RevivalPage();
    Admin_AccessorialChargesPage admin_accessorialChargesPage= new Admin_AccessorialChargesPage();
    Admin_RefundsPage admin_refundsPage = new Admin_RefundsPage();

    Admin_TripsPage adminTripsPage = new Admin_TripsPage();
    Admin_DriversPage admin_DriverPage=new Admin_DriversPage();
    Partner_Done Page_Partner_Done = new Partner_Done();
    Admin_GeofencePage admin_GeofencePage = new Admin_GeofencePage();
    Admin_DriversPage admin_Driverspage = new Admin_DriversPage();


    @And("^I view the Customer list on the admin portal$")
    public void i_view_the_customer_list_on_the_admin_portal() throws Throwable {
        try{
        Thread.sleep(120000); //Wait for 2 minutes
        SetupManager.getDriver().navigate().refresh();
        //  action.click(admin_DashboardPage.Menu_Dashboard());
        log("I view the Customer list on the admin portal",
                "I viewed the Customer list on the admin portal", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I view the Deliveries list on the admin portal$")
    public void i_view_the_trips_list_on_the_admin_portal() throws Throwable {
        try{
        action.click(admin_TripsPage.Menu_Trips());
        action.click(admin_TripsPage.Menu_CompletedTrips());

        Thread.sleep(5000);
        SetupManager.getDriver().navigate().refresh();
        action.selectElementByText(admin_TripsPage.Dropdown_SearchForPeriod(), "The Beginning of Time");
        log("I view the Deliveries list on the admin portal",
                "I viewed the Deliveries list on the admin portal", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("^I view the Live Deliveries list on the admin portal$")
    public void i_view_the_live_deliveries_list_on_the_admin_portal() throws Throwable {
        try{
        action.click(admin_TripsPage.Menu_Trips());
        action.click(admin_LiveTripsPage.Menu_LiveTrips());
        String Pickup_Ref = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

        action.clearSendKeys(admin_LiveTripsPage.TextBox_Search_Field(),Pickup_Ref);
        action.click(admin_LiveTripsPage.Button_Search());
        //  SetupManager.getDriver().navigate().refresh();
        log("I view the Live Deliveries list on the admin portal",
                "I have viewed the Live Deliveries list on the admin portal", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I view the Scheduled Deliveries list on the admin portal$")
    public void i_view_the_scheduled_deliveries_list_on_the_admin_portal() throws Throwable {
        try{
        action.click(admin_TripsPage.Menu_Trips());
        action.click(admin_ScheduledTripsPage.Menu_ScheduledTrips());
        action.selectElementByText(admin_ScheduledTripsPage.Dropdown_SearchForPeriod(), "All");
        //action.click(admin_ScheduledTripsPage.Order_Initial_Request());//change by gopal for partner portal
        // SetupManager.getDriver().navigate().refresh();
        log("I view the Scheduled Deliveries list on the admin portal",
                "I have viewed the Scheduled Deliveries list on the admin portal", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I view the all Scheduled Deliveries list on the admin portal$")
    public void i_view_the_all_scheduled_trips_list_on_the_admin_portal() throws Throwable {
        try{
        action.click(admin_TripsPage.Menu_Trips());
        action.click(admin_ScheduledTripsPage.Menu_ScheduledTrips());
        action.selectElementByText(admin_ScheduledTripsPage.Dropdown_SearchForPeriod(), "All");

        log("I view the Scheduled Deliveries list on the admin portal",
                "I have viewed the Scheduled Deliveries list on the admin portal", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @When("^I change filter to \"([^\"]*)\" on Scheduled deliveries$")
    public void i_change_filter_to_something_on_scheduled_deliveries(String filter) throws Throwable {
        try{
        action.selectElementByText(admin_ScheduledTripsPage.Dropdown_SearchForPeriod(), filter);
        Thread.sleep(5000);
        log("I select filter from the Scheduled Trips list on the admin portal",
                "I selected filter "+filter+" from the  Scheduled Trips list on the admin portal", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And("^I view the partner portal Scheduled Trips list on the admin portal$")
    public void i_view_the_partner_portal_trips_on_the_admin_portal() throws Throwable{
        try{
        action.click(admin_TripsPage.Menu_Trips());
        action.click(admin_ScheduledTripsPage.Menu_ScheduledTrips());
        action.selectElementByText(admin_ScheduledTripsPage.Dropdown_SearchForPeriod(), "All");
        action.clear(admin_ScheduledTripsPage.Textbox_Search());

        log("I view the Partner portal Scheduled Trips list on the admin portal",
                "I viewed the Partner portal Scheduled Trips list on the admin portal", true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should be able to see the Trip Requested count incremented in Customers Grid$")
    public void i_should_be_able_to_see_the_trip_requested_count_incremented_in_customers_grid() throws Throwable {
        try{
        String[] name = cucumberContextManager.getScenarioContext("CUSTOMER_NAME").toString().split(" ");
        action.clearSendKeys(admin_CustomerPage.TextBox_SearchCustomer(), name[1] + Keys.ENTER);

        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[3]", cucumberContextManager.getScenarioContext("CUSTOMER_NAME"));
        String XPath2 = String.format("//td[contains(.,'%s')]/following-sibling::td[4]", cucumberContextManager.getScenarioContext("CUSTOMER_NAME"));
        Thread.sleep(3000);
        String tripRequestedCount = action.getText(SetupManager.getDriver().findElement(By.xpath(XPath)));
        String tripEstimatedCount = action.getText(SetupManager.getDriver().findElement(By.xpath(XPath2)));
        String oldtripRequestedCount = (String)cucumberContextManager.getScenarioContext("TRIP_REQUESTEDCOUNT");
        String oldtripEstimatedCount = (String)cucumberContextManager.getScenarioContext("TRIP_ESTIMATEDCOUNT");

        int reqCount = Integer.parseInt(oldtripRequestedCount) + 1;
        testStepAssert.isEquals(tripRequestedCount, String.valueOf(reqCount), "Newer trip should reflect in Requested count", "Newer trip is reflected in Requested count", "DATA SYNCH ISSUE | Newer trip is not reflected in Requested count");
        testStepAssert.isEquals(tripEstimatedCount, oldtripEstimatedCount, "Newer trip should reflect in Requested count", "Newer trip is reflected in Requested count", "DATA SYNCH ISSUE | Newer trip is not reflected in Requested count");
        cucumberContextManager.setScenarioContext("XPATH", XPath);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I note the Trip Requested count of Customer \"([^\"]*)\"$")
    public void i_note_the_trip_requested_count_of_customer_something(String customer) throws Throwable {
        try{
            utility.resetGeofenceDropdown();
        String[] name = customer.split(" ");
        action.clearSendKeys(admin_DashboardPage.TextBox_SearchCustomer(), name[1] + Keys.ENTER);

        String XPath = String.format("//td[contains(.,\"%s\")]/following-sibling::td[3]", customer);
        String XPath2 = String.format("//td[contains(.,\"%s\")]/following-sibling::td[4]", customer);

        String tripRequestedCount = SetupManager.getDriver().findElement(By.xpath(XPath)).getText();
        String tripEstimatedCount = SetupManager.getDriver().findElement(By.xpath(XPath2)).getText();
        cucumberContextManager.setScenarioContext("TRIP_REQUESTEDCOUNT", tripRequestedCount);
        cucumberContextManager.setScenarioContext("TRIP_ESTIMATEDCOUNT", tripEstimatedCount);
        cucumberContextManager.setScenarioContext("CUSTOMER_NAME", customer);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I view the customer details page of Customer \"([^\"]*)\"$")
    public void i_view_the_customer_details_page_of_customer_something(String strArg1) throws Throwable {
        try{
        String xpath = (String) cucumberContextManager.getScenarioContext("XPATH");
        action.click(SetupManager.getDriver().findElement(By.xpath(xpath)));
        log("I view the customer details page of Customer",
                "I viewed the customer details page of Customer "+strArg1, false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @Then("^Trip should be listed in the grid$")
    public void trip_should_be_listed_in_the_grid() throws Throwable {
        try{
        String tripType = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        tripType = tripType.split(" ")[0];
        String status = "Assigning Driver(s)";
        String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER_NAME");
        action.selectElementByText(admin_CustomerPage.Dropdown_TimeFrame(), "The Beginning of Time");
        Thread.sleep(5000);
        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]", tripType, customer, status);
        testStepAssert.isElementDisplayed(action.getElementByXPath(XPath), "Trip should be displayed", "Trip is displayed", "DATA SYNCH ISSUE | Trip is not displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }

    @Then("^I should be able to see the business user requested bungii with the below status$")
    public void i_should_be_able_to_see_the_business_user_requested_bungii_with_the_below_status(DataTable data) throws Throwable {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String status = dataMap.get("Status").trim();
        String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        String tripType[] = tripTypeAndCategory.split(" ");
        String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
        String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
        String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

        String geofenceName = getGeofence(geofence);
        //action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
        //action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
        utility.selectGeofenceDropdown(geofenceName);

        cucumberContextManager.setScenarioContext("STATUS", status);
        String driver = driver1;
        if (tripType[0].equalsIgnoreCase("duo"))
            driver = driver1 + "," + driver2;
        if (status.equalsIgnoreCase("Scheduled") || status.equalsIgnoreCase("Searching Drivers")) {
            String xpath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[5]", tripType[0].toUpperCase(), customer);
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
                    utility.reApplyGeofenceDropdown();

                    retrycount--;
                    retry = true;
                }

            }
            int retryCount = 1;
            while (!action.getText(SetupManager.getDriver().findElement(By.xpath(xpath))).equalsIgnoreCase(status)) {
                if (retryCount >= 20) break;
                Thread.sleep(15000); //Wait for 15 seconds
                retryCount++;
                SetupManager.getDriver().navigate().refresh();
            }
            cucumberContextManager.setScenarioContext("XPATH", xpath);
            testStepAssert.isElementTextEquals(action.getElementByXPath(xpath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

        } else {
            char[] delimiters = { ' ', '_' };
            String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : WordUtils.capitalizeFully(tripType[0], delimiters), driver, customer);
            int retrycount = 10;
            boolean retry = true;
            while (retry == true && retrycount > 0) {
                try {
                    WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                    retry = false;
                } catch (Exception ex) {
                    SetupManager.getDriver().navigate().refresh();
                   // action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
                    //action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
                    utility.reApplyGeofenceDropdown();
                    retrycount--;
                    retry = true;
                }

            }
            int retryCount = 1;
            while (!action.getText(SetupManager.getDriver().findElement(By.xpath(XPath))).equalsIgnoreCase(status)) {
                if (retryCount >= 20) break;
                Thread.sleep(15000); //Wait for 15 seconds
                retryCount++;
                SetupManager.getDriver().navigate().refresh();
            }
            cucumberContextManager.setScenarioContext("XPATH", XPath);
            testStepAssert.isElementTextEquals(action.getElementByXPath(XPath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
        }
    }

    @Then("^I should be able to see the respective bungii with the below status$")
    public void i_should_be_able_to_see_the_respective_bungii_with_the_below_status(DataTable data) throws Throwable {
       String TripPath = "";
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String status = dataMap.get("Status").trim();
            String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            String tripType[] = tripTypeAndCategory.split(" ");
            String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
            String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

            String geofenceName = getGeofence(geofence);
            //action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
            //action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
            utility.selectGeofenceDropdown(geofenceName);

            String pageName = action.getText(admin_LiveTripsPage.Text_Page_Header());

            cucumberContextManager.setScenarioContext("STATUS", status);
            String driver = driver1;
            if (tripType[0].equalsIgnoreCase("duo"))
                driver = driver1 + "," + driver2;
            if (status.equalsIgnoreCase("Scheduled") || (status.equalsIgnoreCase("Assigning Driver(s)") && pageName.contains("Scheduled")) || status.equalsIgnoreCase("Driver Removed")|| status.equalsIgnoreCase("Driver(s) Not Found")||status.equalsIgnoreCase("Pending")) {
                String xpath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[5]", tripType[0].toUpperCase(), customer);
                String costPath =  String.format("//td[contains(.,'%s')]/preceding-sibling::td[1]/span", customer);

                TripPath= xpath;
                int retrycount = 10;
                action.clearSendKeys(admin_ScheduledTripsPage.Textbox_Search(), customer.substring(0, customer.indexOf(" ")));
                action.click(admin_ScheduledTripsPage.Button_Search());

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
                        utility.reApplyGeofenceDropdown();

                        retrycount--;
                        retry = true;
                    }

                }
                Thread.sleep(5000);
                int retryCount = 1;
                while (!action.getText(SetupManager.getDriver().findElement(By.xpath(xpath))).equalsIgnoreCase(status)) {
                    if (retryCount >= 20) break;
                    action.refreshPage();
                    Thread.sleep(15000); //Wait for 15 seconds
                    retryCount++;
                }
                cucumberContextManager.setScenarioContext("XPATH", xpath);
                cucumberContextManager.setScenarioContext("COSTPATH", costPath);
                cucumberContextManager.setScenarioContext("COST", action.getText(action.getElementByXPath(costPath)).replace("/ $",""));
                testStepAssert.isElementTextEquals(action.getElementByXPath(xpath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

            } else {
                //String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[3]", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : StringUtils.capitalize(tripType[0]), driver, customer);
                char[] delimiters = { ' ', '_' };
//                String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[3]", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : WordUtils.capitalizeFully(tripType[0], delimiters), driver, customer);
                String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[4]", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : WordUtils.capitalizeFully(tripType[0], delimiters), driver, customer);
                TripPath= XPath;
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
                       // action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
                        utility.reApplyGeofenceDropdown();

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

            testStepAssert.isElementDisplayed(admin_ScheduledTripsPage.Label_Delivery_Portal(),"Delivery Portal column should be shown.","Delivery Portal column is shown.","Delivery Portal Column is not shown.");
            testStepAssert.isElementDisplayed(admin_ScheduledTripsPage.Label_Market(),"Market column should be shown.","Market column is shown.","Market Column is not shown.");
        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Bungii is not displayed in Admin Scheduled Delivery List : "+ TripPath,
                    true);

        }

    }
    @Then("^I should be able to see the respective bungii with the below Delivery Type$")
    public void i_should_be_able_to_see_the_respective_bungii_with_the_below_delivery_type(DataTable data) throws Throwable {
          String TripPath = "";
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String type = dataMap.get("Type").trim().toUpperCase();
            String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            String tripType[] = tripTypeAndCategory.split(" ");
            String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

            String geofenceName = getGeofence(geofence);
            //action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
            //action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
              utility.selectGeofenceDropdown(geofenceName);
            String xpath = String.format("//td[contains(.,'%s')]/preceding-sibling::td[contains(.,'%s')]",  customer,type);
            TripPath = xpath;
                cucumberContextManager.setScenarioContext("XPATH", xpath);
                testStepAssert.isElementTextEquals(action.getElementByXPath(xpath), type, "Delivery Type " + type + " should be updated", "Delivery Type " + type + " is updated", "Delivery Type " + type + " is not updated");


        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Bungii is not displayed in Admin Scheduled Delivery List : "+ TripPath,
                    true);

        }

    }
    @And("^I select the scheduled trip on scheduled delivery$")
    public void i_select_the_scheduled_trip_on_scheduled_delivery(){
        try{
        //String Xpath = (String) cucumberContextManager.getScenarioContext("XPATH");
        String ST = (String) cucumberContextManager.getScenarioContext("Scheduled_Time");
        String BT = (String) cucumberContextManager.getScenarioContext("Bungii_Type");
        BT = BT.replace("Solo Scheduled","SOLO");
        BT = BT.toUpperCase();
        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/parent::tr", BT, ST);

        //action.getElementByXPath(XPath).click();
            action.click(SetupManager.getDriver().findElement(By.xpath(XPath)).findElement(By.xpath("td/div/img")));
            action.click(admin_ScheduledTripsPage.List_ViewDeliveries());
        log("I select the scheduled trip on scheduled delivery",
                "I have selected the scheduled trip on scheduled delivery", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I select the partner portal scheduled trip on scheduled delivery$")
    public void i_select_the_parter_portal_scheduled_trip_on_scheduled_delivery() throws ParseException {
        try{
        //String Xpath = (String) cucumberContextManager.getScenarioContext("XPATH");
        String ST = (String) cucumberContextManager.getScenarioContext("ActualPickupDateTime");
        ST = ST.replace("at","");
        DateFormat dft = new SimpleDateFormat("MMM dd, yyyy hh:mm a z",Locale.ENGLISH);
       // String geoLabel = utility.getTimeZoneBasedOnGeofenceId();
        String geoLabel = utility.getTimeZoneBasedOnGeofence();
        dft.setTimeZone(TimeZone.getTimeZone(geoLabel));
        Date dt2 = dft.parse(ST);

        String Schedule_Time = dft.format(dt2);

        cucumberContextManager.setScenarioContext("Scheduled_Time",Schedule_Time);


        String BT = (String) cucumberContextManager.getScenarioContext("Bungii_Type");
        BT = BT.replace("Solo Scheduled","SOLO");
        BT = BT.toUpperCase();

        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/parent::tr", BT, Schedule_Time);

        //action.getElementByXPath(XPath).click();
            action.click(SetupManager.getDriver().findElement(By.xpath(XPath)).findElement(By.xpath("td/div/img")));
            action.click(admin_ScheduledTripsPage.List_ViewDeliveries());
        log("I should able to select the partner portal scheduled trip on scheduled delivery",
                "I am able to select the partner portal scheduled trip on scheduled delivery", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I select the scheduled trip on live delivery$")
    public void i_select_the_scheduled_trip_on_live_delivery(){
        try{
        //String Xpath = (String) cucumberContextManager.getScenarioContext("XPATH");
        String ST = (String) cucumberContextManager.getScenarioContext("Scheduled_Time");
        String BT = (String) cucumberContextManager.getScenarioContext("Bungii_Type");
        String Client = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        BT = BT.replace("Solo Scheduled","Solo");
       // String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/preceding-sibling::td/a", BT, ST,Client);
         String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/parent::tr", BT, ST,Client);

            // action.getElementByXPath(XPath).click();
            action.click(SetupManager.getDriver().findElement(By.xpath(XPath)).findElement(By.xpath("td/div/img")));
            action.click(admin_ScheduledTripsPage.List_ViewDeliveries());
        log("I should able to select the scheduled trip on live delivery",
                "I am able to select the scheduled trip on live delivery", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I select the scheduled trip on live delivery for customer$")
    public void i_select_the_scheduled_trip_on_live_delivery_for_customer(){
        try{
        //String Xpath = (String) cucumberContextManager.getScenarioContext("XPATH");
        String ST = (String) cucumberContextManager.getScenarioContext("BUNGII_INITIAL_SCH_TIME");
        String BT = (String) cucumberContextManager.getScenarioContext("Bungii_Type");
        String Client = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        BT = BT.replace("Solo Scheduled","Solo");
        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/parent::tr", BT, ST,Client);

       // action.getElementByXPath(XPath).click();
            action.click(SetupManager.getDriver().findElement(By.xpath(XPath)).findElement(By.xpath("td/div/img")));
            action.click(admin_ScheduledTripsPage.List_ViewDeliveries());

        log("I should able to select the scheduled trip on live delivery for customer",
                "I am able to select the scheduled trip on live delivery for customer", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I view All Deliveries list on the admin portal$")
    public void i_view_all_deliveries_list_on_the_admin_portal() throws Throwable {
        try{
            //Thread.sleep(120000);
            action.click(admin_TripsPage.Menu_Trips());
            action.click(liveTripsPage.Menu_AllDeliveries());
            //action.click(admin_LiveTripsPage.Menu_LiveTrips());
            SetupManager.getDriver().navigate().refresh();
            action.selectElementByText(liveTripsPage.Dropdown_SearchForPeriod(),"The Beginning of Time");
            Thread.sleep(2000);
            log("I view All Deliveries on the admin portal",
                    "I viewed All Deliveries on the admin portal", true);
        }
        catch (Throwable e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Delivery should be shown on All Deliveries", "Delivery is not shown on All Deliveries",
                    true);

        }
    }
    @When("^I change filter to \"([^\"]*)\" on All deliveries$")
    public void i_change_filter_to_something_on_all_deliveries(String filter) throws Throwable {
        try{
        Thread.sleep(5000);
        action.selectElementByText(liveTripsPage.Dropdown_SearchForPeriod(),filter);
        Thread.sleep(5000);
        log("I select filter from All Deliveries on the admin portal",
                "I selected filter "+filter+" from All Deliveries on the admin portal", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I select the scheduled trip on All Deliveries$")
    public void i_select_the_scheduled_trip_on_all_deliveries() throws Throwable {
        try {
            //String Xpath = (String) cucumberContextManager.getScenarioContext("XPATH");
            String ST = (String) cucumberContextManager.getScenarioContext("Scheduled_Time");
            String BT = (String) cucumberContextManager.getScenarioContext("Bungii_Type");
            String Client = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            BT = BT.replace("Solo Scheduled", "Solo");
          String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/parent::tr", BT, ST, Client);

           // action.getElementByXPath(XPath).click();
            Thread.sleep(5000);
            action.click(SetupManager.getDriver().findElement(By.xpath(XPath)).findElement(By.xpath("td/div/img")));
            action.click(admin_ScheduledTripsPage.List_ViewDeliveries());

            log("I select the scheduled trip on All Deliveries",
                    "I have selected the scheduled trip on All Deliveries", false);
        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Delivery should get selected.", "Delivery is not selected ",
                    true);
        }
    }

    @When("^I view the delivery details$")
    public void i_view_the_trip_details() throws Throwable {

        try{
        String xpath = (String) cucumberContextManager.getScenarioContext("XPATH");
        if(action.isElementPresent(action.getElementByXPath(xpath))){
           // action.click(admin_TripDetailsPage.Schedule_Date_Row());
            action.click(SetupManager.getDriver().findElement(By.xpath((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr")).findElement(By.xpath("td/div/img")));
            action.click(admin_ScheduledTripsPage.List_ViewDeliveries());

        }
        log("I should able to view the delivery details",
                "I am able to viewed the delivery details", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the amount is calculated and shown to admin$")
    public void the_amount_is_calculated_and_shown_to_admin() throws Throwable {

    }

    @And("^Enter the End Date and Time$")
    public void enter_the_end_date_time() throws Throwable {
        try{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY");


        String RequestTime = SetupManager.getDriver().findElement(By.xpath("//td[contains(text(),'Initial Request')]/following-sibling::td/strong")).getText();
        String[] splitedDate = RequestTime.split(" ");
        LocalDateTime now = LocalDateTime.now();
        String[] splitedTime = splitedDate[2].split(":");
        DecimalFormat formatter = new DecimalFormat("00");
        int minutes = Integer.parseInt(splitedTime[1]) + 20;
        int hours = Integer.parseInt(splitedTime[0]);
        if (minutes > 60) {
            hours = hours + 1;
            minutes = minutes - 20;
        }


        // ZonedDateTime zonedNZ = ZonedDateTime.of(now,ZoneId.of("5:00"));


        action.clearSendKeys(admin_TripDetailsPage.Textbox_PickupEndDate(), dtf.format(now));
        action.clearSendKeys(admin_TripDetailsPage.Textbox_PickupEndTime(), formatter.format(hours) + ":" + formatter.format(minutes));
        action.selectElementByText(admin_TripDetailsPage.Dropdown_ddlpickupEndTime(), splitedDate[3]);
        log("I enter the End Date and Time",
                "I have entered the End Date and Time", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And("^Click on \"([^\"]*)\" button$")
    public void click_on_something_button(String button) throws Throwable {
try{
        switch (button) {
            case "Calculate Cost":
                action.click(admin_TripDetailsPage.Button_CalculateCost());

                break;
            case "Confirm":
                action.click(admin_TripDetailsPage.Button_Confirm());
                break;
            case "Cancel":
                action.click(admin_TripDetailsPage.Button_Cancel());
                break;
            case "Edit":
                action.click(admin_EditScheduledBungiiPage.Button_Edit());
                break;
        }
        log("I click on " + button+" button",
                "I have clicked on " + button+" button", false);
} catch(Exception e){
    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
    error("Step should be successful", "Error performing step,Please check logs for more details",
            true);
}
    }

    @Then("^the Bungii details is displayed successfully$")
    public void the_bungii_details_is_displayed_successfully() throws Throwable {
try{
        String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
        String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String status = (String) cucumberContextManager.getScenarioContext("STATUS");
        String scheduled_time = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
        String timezone = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        String Bunggi_Type = (String) cucumberContextManager.getScenarioContext("Bungii_Type");

        if(!scheduled_time.equalsIgnoreCase("NOW")) {
            TimeZone.setDefault(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofence()));
            DateFormat formatter = new SimpleDateFormat("MMM dd, h:mm a");
            formatter.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofence()));
            Date bungiiDate = formatter.parse(scheduled_time);
            Date inputdate = new SimpleDateFormat("MMM dd, hh:mm a z").parse(scheduled_time);
            String formattedDate = new SimpleDateFormat("MMM dd,  hh:mm:ss a z").format(inputdate).replace("am", "AM").replace("pm", "PM");
            String xpath_scheduled_time = "//td[contains(text(),'Scheduled Time')]/following-sibling::td/strong[text()='"+ formattedDate + "']";

            //Verify that the time the customer scheduled the trip for is added to Trip Details page
            testStepAssert.isElementDisplayed(admin_TripDetailsPage.Label_ScheduledTime(xpath_scheduled_time), "Bungii Scheduled Time should be displayed correctly", "Pass", "Fail");
        }
        String pickupLine = (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1") + " " + (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2");
        String dropOffLine = (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1") + " " + (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2");
        pickupLine = pickupLine.replace(",", "");
        dropOffLine = dropOffLine.replace(",", "");
        testStepAssert.isElementTextEquals(admin_TripDetailsPage.Label_TripDetails("Client"), customer, "Client " + customer + " should be updated", "Client " + customer + " is updated", "Client " + customer + " is not updated");
        testStepAssert.isTrue(admin_TripDetailsPage.Label_TripDetails("Pickup Location").getText().contains(pickupLine), "Pickup Location " + pickupLine + " should be updated", "Pickup Location " + pickupLine + " is updated", "Pickup Location " + pickupLine + " is not updated");
        testStepAssert.isTrue(admin_TripDetailsPage.Label_TripDetails("Drop Off Location").getText().contains(dropOffLine), "Drop Off Location " + dropOffLine + " should be updated", "Drop Off Location " + dropOffLine + " is updated", "Drop Off Location " + dropOffLine + " is not updated");
        testStepAssert.isElementTextEquals(admin_TripDetailsPage.Label_TripDetails("Status"), status, "Status " + status + " should be updated", "Status " + status + " is updated", "Status " + status + " is not updated");
        // testStepAssert.isElementTextEquals(admin_TripDetailsPage.Label_TripDetails("Trip Distance"), customer, "Trip Distance " + customer + " should be updated", "Trip Distance " + customer + " is updated", "Trip Distance " + customer + " is not updated");
        // testStepAssert.isElementTextEquals(admin_TripDetailsPage.Label_TripDetails("Loading + Unloading Time"), customer, "Loading + Unloading Time " + customer + " should be updated", "Loading + Unloading Time " + customer + " is updated", "Loading + Unloading Time " + customer + " is not updated");

        Bunggi_Type = Bunggi_Type.replace("Solo Ondemand","Solo").replace("Duo Ondemand","Duo");
        if(Bunggi_Type.equalsIgnoreCase("Solo")|| Bunggi_Type.equalsIgnoreCase("Solo Scheduled")){
            String xpath = String.format("//td/strong[contains(text(),'%s')]",driver1);
            testStepAssert.isElementDisplayed(action.getElementByXPath(xpath)," Driver " + driver1 + " should be displayed", " Driver " + driver1 + " is displayed", " Driver " + driver1 + " is not displayed");
        }
        else {
            String xpath = String.format("option[text()='%s']", driver1);
            testStepAssert.isElementDisplayed(admin_TripDetailsPage.Dropdown_Drivers().findElement(By.xpath(xpath)), " Driver " + driver1 + " should be displayed", " Driver " + driver1 + " is displayed", " Driver " + driver1 + " is not displayed");
        }

    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @When("^I click on \"([^\"]*)\" link beside scheduled bungii$")
    public void i_click_on_something_link_beside_scheduled_bungii(String link) throws Throwable {
        try{
        Thread.sleep(4000);
        action.click(SetupManager.getDriver().findElement(By.xpath((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr")).findElement(By.xpath("td/div/img")));
        action.click(SetupManager.getDriver().findElement(By.xpath((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr")).findElement(By.xpath("td/div/ul/li/p[contains(text(),'Edit')]")));
            log(" I click on Edit link besides the scheduled bungii",
                "I have clicked on Edit link besides the scheduled bungii", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I click on \"([^\"]*)\" link beside live delivery$")
    public void i_click_on_something_link_beside_live_delivery(String link) throws Throwable {
        try{
        Thread.sleep(4000);
           // action.click(SetupManager.getDriver().findElement(By.xpath((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr")).findElement(By.xpath("td/p[@id='btnLiveEdit']")));
            action.click(SetupManager.getDriver().findElement(By.xpath((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr")).findElement(By.xpath("//td/div/img")));
            action.click(SetupManager.getDriver().findElement(By.xpath((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr")).findElement(By.xpath("//p[contains(text(),'Edit')]")));

            log(" I click on Edit link besides the live delivery",
                "I have clicked on Edit link besides the live delivery", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^the updated drop off address should be displayed on delivery details page$")
    public void the_updated_drop_off_address_should_be_displayed_on_delivery_details_page() throws Throwable {
        try{
        String Expected_Change_DropOff = (String)cucumberContextManager.getScenarioContext("Change_Drop_Off");
        Expected_Change_DropOff = Expected_Change_DropOff.replace(",","");
        String Display_Change_DropOff = action.getText(admin_TripDetailsPage.Text_DropOff_Location());
        //testStepVerify.isEquals(Expected_Change_DropOff,Display_Change_DropOff);
        testStepAssert.isTrue(Display_Change_DropOff.contains(Expected_Change_DropOff),"Correct address need to display","Correct address is display","Incorrect address is displayed");
        testStepAssert.isFalse(Expected_Change_DropOff.contentEquals((String) cucumberContextManager.getScenarioContext("OLD_DROPOFF_LOCATION")),"Dropoff Address should be changed","Dropoff address is changed","Dropoff address is not changed");
        log(" I confirm the change drop off address on delivery details page",
                "I have confirmed the change drop off address on delivery details page", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the change service level should be displayed on delivery details page$")
    public void the_change_service_level_should_be_displayed_on_delivery_details_page() throws Throwable {
        try{
            String changeServiceLevel = (String) cucumberContextManager.getScenarioContext("Change_service");
            String displayServiceLevel = action.getText(admin_TripDetailsPage.Text_Service_Level());

            if(changeServiceLevel.equalsIgnoreCase("White Glove"))
            {
                changeServiceLevel = PropertyUtility.getDataProperties("change.service.description");
            }

            testStepVerify.isEquals(changeServiceLevel,displayServiceLevel,"the change service level " +changeServiceLevel+ "should be same as service level display " +displayServiceLevel+ "on delivery details page","the change service level " +changeServiceLevel+ " is not same as service level displayed " +displayServiceLevel+ "on delivery details page");

        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to see the changed service level on delivery details page",
                    true);
        }
    }

    @Then("^the price for the delivery shown as per the changed service level$")
    public void the_price_for_the_delivery_shown_as_per_the_changed_service_level() throws Throwable {
        try{
            String Alias_Name= (String) cucumberContextManager.getScenarioContext("Alias");
            String Change_Service =(String) cucumberContextManager.getScenarioContext("Change_service");
            String Trip_Type = (String) cucumberContextManager.getScenarioContext("Partner_Bungii_type");
            int Driver_Number=1;

            if(Trip_Type.equalsIgnoreCase("Duo")){
                Driver_Number=2;
            }

            String Display_Price = action.getText(admin_TripDetailsPage.Text_Estimated_Charge());
            //action.getElementByXPath("//h2[text()='Delivery Cost']//following::span/strong").getText();
            Display_Price = Display_Price.substring(1);

            String Estimate_distance = dbUtility.getEstimateDistance(Alias_Name);
            double Estimate_distance_value = Double.parseDouble(Estimate_distance);

            String Last_Tier_Milenge_Min_Range = dbUtility.getMaxMilengeValue(Alias_Name,Change_Service);
            double Last_Tier_Milenge_Min_Range_value = Double.parseDouble(Last_Tier_Milenge_Min_Range);

            String Price="";
            if(Estimate_distance_value <= Last_Tier_Milenge_Min_Range_value) {
                Price = dbUtility.getServicePrice(Alias_Name, Driver_Number, Estimate_distance, Change_Service);
            }
            else{
                Price = dbUtility.getServicePriceLastTier(Alias_Name, Driver_Number, Estimate_distance, Change_Service);
            }

            testStepVerify.isEquals(Display_Price,Price);

            log("The price for the delivery should be shown as per the changed service level",
                    "The price for the delivery is shown as per the changed service level", false);
        }
        catch(Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "The price for the delivery is not shown as per the chnaged service level",
                    true);
        }
    }


    @Then("^I confirm the change pickup address on delivery details page$")
    public void i_confirm_the_change_pickup_address_on_delivery_details_page() throws Throwable {
        try{
        String Expected_Change_Pickup = (String)cucumberContextManager.getScenarioContext("Change_Pickup");
        Expected_Change_Pickup = Expected_Change_Pickup.replace(",","");
        String Display_Change_Pickup = action.getText(admin_TripDetailsPage.Text_Pickup_Location());
        //testStepVerify.isEquals(Expected_Change_DropOff,Display_Change_DropOff);
        testStepAssert.isTrue(Display_Change_Pickup.contains(Expected_Change_Pickup),"Correct address need to display","Correct address is display","Incorrect address is displayed");
        log(" I confirm the change drop off address on delivery details page",
                "I have confirm the change drop off address on delivery details page", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I confirm Pickup note is updated$")
    public void i_confirm_pickup_note_is_updated() throws Throwable {
        try{
        String Change_Pickup_Note = (String) cucumberContextManager.getScenarioContext("Change_Pickup_Note");
        String Display_Pickup_Note = action.getText(admin_EditScheduledBungiiPage.Text_Pickup_Note());
        testStepVerify.isEquals(Change_Pickup_Note,Display_Pickup_Note);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I confirm Pickup note is \"([^\"]*)\"$")
    public void i_confirm_pickup_note_is_something(String noteStatus) throws Throwable {
        try{
            String Display_Pickup_Note = action.getText(admin_EditScheduledBungiiPage.Text_Pickup_Note());
            String Change_Pickup_Note = (String) cucumberContextManager.getScenarioContext("Change_Pickup_Note");
            switch (noteStatus){
                case "Updated":
                    Thread.sleep(1000);
                    testStepVerify.isEquals(Change_Pickup_Note,Display_Pickup_Note);
                    testStepAssert.isFalse(Display_Pickup_Note.contentEquals((String) cucumberContextManager.getScenarioContext("OLD_ADDITION_NOTE")),"Edited note should be displayed","Edited note should be displayed","Edited note is not displayed");
                    break;
                case "Added":
                    Thread.sleep(1000);
                    testStepAssert.isEquals(Change_Pickup_Note,Display_Pickup_Note,"Admin added note should be displayed","Admin added note is  displayed","Admin added note is not displayed");
                    break;
                case "Deleted":
                    Thread.sleep(1000);
                    testStepAssert.isFalse(Display_Pickup_Note.contentEquals((String) cucumberContextManager.getScenarioContext("OLD_ADDITION_NOTE")),"Note should be deleted","Note is deleted","Note is not deleted");
                    break;
            }

        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on \"([^\"]*)\" radiobutton$")
    public void i_click_on_something_radiobutton(String radiobutton) throws Throwable {
try{
        switch (radiobutton) {
            case "Cancel entire Bungii and notify driver(s)":
                action.click(admin_ScheduledTripsPage.RadioButton_CancelBungii());
                break;
            case "Remove driver(s) and re-search":
                action.click(admin_ScheduledTripsPage.RadioButton_RemoveDriver());
                break;
            case "Edit Trip Details":
                action.click(admin_EditScheduledBungiiPage.RadioButton_EditTripDetails());
                Thread.sleep(3000);
                break;
            case "Edit Delivery Status":
                action.click(admin_LiveTripsPage.RadioButton_EditDeliveryStatus());
                Thread.sleep(1000);
                break;
            case "Delivery Canceled":
                action.click(admin_LiveTripsPage.RadioButton_DeliveryCanceled());
                Thread.sleep(1000);
                break;
            default:
                break;
        }
        log("I click "+ radiobutton,
                "I have clicked on "+ radiobutton, false);
} catch(Exception e){
    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
    error("Step should be successful", "Error performing step,Please check logs for more details",
            true);
}
    }

    @And("^I enter delivery completion date and time as per geofence$")
    public void i_enter_delivery_completion_date_and_time_as_per_geofence() throws Throwable {
        try{
            String strDate="";
            String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
            String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
            Calendar calendar = Calendar.getInstance();
            DateFormat formatter = new SimpleDateFormat("MMddYYYY-hh:mm-a");
            formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
           // calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)-1);
            //calendar.add(Calendar.MINUTE,-5);

            strDate = formatter.format(calendar.getTime());
            String[] dateTime = strDate.split("-");
            String date = dateTime[0];
            String time = dateTime[1];
            String meridian = dateTime[2];

            action.clearSendKeys(liveTripsPage.Textbox_PickupEndDate(),date);
            action.clearSendKeys(liveTripsPage.Textbox_PickupEndTime(),time);
           // action.click(liveTripsPage.Dropdown_ddlpickupEndTime());
            action.selectElementByText(liveTripsPage.Dropdown_ddlpickupEndTime(),meridian);
            log("Correct date= "+date+" and time= "+time+meridian+" should be enter for the "+geofence+" geofence.","Correct date= "+date+" and time= "+time+meridian+" is enter for the "+geofence+" geofence.",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I change delivery type from \"([^\"]*)\"")
    public void i_change_on_something_radiobutton(String radiobutton) throws Throwable {
try{
        switch (radiobutton) {
            case "Solo to Duo":
                action.click(admin_EditScheduledBungiiPage.RadioButton_Duo());
                cucumberContextManager.setScenarioContext("BUNGII_TYPE","DUO");
                break;
            case "Duo to Solo":
                action.click(admin_EditScheduledBungiiPage.RadioButton_Solo());
                cucumberContextManager.setScenarioContext("BUNGII_TYPE","SOLO");
                break;
        }
        log("I change delivery type from  "+ radiobutton,
                "I changed delivery type from "+ radiobutton, false);
} catch(Exception e){
    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
    error("Step should be successful", "Error performing step,Please check logs for more details",
            true);
}
    }
    @And("^I get the new pickup reference generated$")
    public void i_get_the_new_pickup_reference_generated() throws Throwable {

        try {
            String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            pickupRequest = getLinkedPickupRef(pickupRequest);
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
            log("I get the new pickup reference generated",
                    "Pickupref is " + pickupRequest, false);
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "New pickup reference is not generated",
                    true);
        }

    }

    @And("^I edit the drop off address$")
    public void i_edit_the_drop_off_address() throws Throwable {
        try{
        testStepAssert.isElementDisplayed(admin_ScheduledTripsPage.Label_Drop_Off_Location(),"Drop off location should display","Drop off location is display","Drop off location is not display");
        action.click(admin_ScheduledTripsPage.Button_Edit_Drop_Off_Address());
        Thread.sleep(1000);
        cucumberContextManager.setScenarioContext("OLD_DROPOFF_LOCATION",action.getText(admin_ScheduledTripsPage.DropOff_Address()));
        if(action.isElementPresent(admin_EditScheduledBungiiPage.Text_Additional_Note(true))) {
            cucumberContextManager.setScenarioContext("OLD_ADDITION_NOTE", action.getText(admin_EditScheduledBungiiPage.Text_Additional_Note()));
        }
        log("I edit the drop off address ",
                "I have edited the dropoff address ");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I edit the pickup address$")
    public void i_edit_the_pickup_address() throws Throwable {
        try{
        testStepAssert.isElementDisplayed(admin_ScheduledTripsPage.Label_Pickup_Location(),"Pickup location should display","Pickup location is display","Pickup location is not display");
        action.click(admin_ScheduledTripsPage.Button_Edit_Pickup_Address());
        log("I edit the pickup address.",
                "I have edited the pickup address.");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @Then("^I change the drop off address to \"([^\"]*)\"$")
    public void i_change_the_drop_off_address_to_something(String arg1) throws Throwable {

        try{
        action.sendKeys(admin_ScheduledTripsPage.Textbox_Drop_Off_Location(),arg1);
        //action.click(admin_ScheduledTripsPage.Textbox_Drop_Off_Location());
        Thread.sleep(1000);
//        action.sendKeys(admin_ScheduledTripsPage.Textbox_Drop_Off_Location()," ");

        //action.click(admin_ScheduledTripsPage.DropdownResult(arg1));
        action.JavaScriptClick(admin_ScheduledTripsPage.DropdownResult(arg1));
        Thread.sleep(1000);
        String Change_Address = action.getText(admin_ScheduledTripsPage.DropOff_Address());
        cucumberContextManager.setScenarioContext("Change_Drop_Off",Change_Address);

        log("I change the dropoff address to "+arg1,
                "I have changed the dropoff address to "+arg1);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I change the pickup address to \"([^\"]*)\"$")
    public void i_change_the_pickup_address_to_something(String arg1) throws Throwable {

        try{
        action.sendKeys(admin_ScheduledTripsPage.Textbox_Pickup_Location(),arg1);
        //action.click(admin_ScheduledTripsPage.Textbox_Drop_Off_Location());
        Thread.sleep(1000);
        action.sendKeys(admin_ScheduledTripsPage.Textbox_Pickup_Location()," ");

        //action.click(admin_ScheduledTripsPage.DropdownResult(arg1));
        action.JavaScriptClick(admin_ScheduledTripsPage.DropdownPickupResult(arg1));
        Thread.sleep(1000);
        String Change_Address = action.getText(admin_ScheduledTripsPage.Pickup_Address());
        cucumberContextManager.setScenarioContext("Change_Pickup",Change_Address);

        log("I change the pickup address to "+arg1,
                "I have changed the pickup address to "+arg1);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I change the customer note to \"([^\"]*)\"$")
    public void i_change_the_customer_note(String arg1) throws Throwable {
        try{
        cucumberContextManager.setScenarioContext("Change_Pickup_Note",arg1);
        action.clearSendKeys(admin_EditScheduledBungiiPage.Text_Additional_Note(),arg1);

        log("I change the customer note to"+arg1,
                "I have changed the customer note to "+arg1);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I view the delivery details in admin portal$")
    public void i_view_the_delivery_details_in_admin() throws Throwable {
        try{
            SetupManager.getDriver().navigate().refresh();
            Thread.sleep(5000);
            String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String xpath = String.format("//td[contains(.,'%s')]/preceding::td[2]", customer);
            //String xpath=  (String)cucumberContextManager.getScenarioContext("XPATH");
           // action.click(admin_EditScheduledBungiiPage.findElement(xpath,PageBase.LocatorType.XPath));
            action.click(admin_TripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/img", customer),PageBase.LocatorType.XPath));
            action.click(admin_TripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/ul/li/*[contains(text(),'Delivery Details')]", customer),PageBase.LocatorType.XPath));


            log("I view the delivery details in admin portal",
                    "I viewed delivery details in admin portal", false);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I view the partner portal delivery details in admin portal$")
    public void i_view_the_partner_portal_delivery_details_in_admin() throws Throwable {
        try{
            SetupManager.getDriver().navigate().refresh();
            Thread.sleep(5000);
            String customer = (String) cucumberContextManager.getScenarioContext("Customer_Name");
            String xpath = String.format("//td[contains(.,'%s')]/preceding::td[2]", customer);
            //String xpath=  (String)cucumberContextManager.getScenarioContext("XPATH");
            action.click(admin_EditScheduledBungiiPage.findElement(xpath,PageBase.LocatorType.XPath));
            log("I view the delivery details in admin portal",
                    "I viewed delivery details in admin portal", false);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @When("^I open the live delivery details in admin portal$")
    public void i_open_the_live_delivery_details_in_admin() throws Throwable {
        try{
            SetupManager.getDriver().navigate().refresh();
            String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String driver = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            //String xpath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/preceding::td[4]", driver,customer);
            action.click(SetupManager.getDriver().findElement(By.xpath((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr")).findElement(By.xpath("//td/div/img")));
            action.click(SetupManager.getDriver().findElement(By.xpath((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr")).findElement(By.xpath("//a[contains(text(),'Delivery Details')]")));
            //String xpath=  (String)cucumberContextManager.getScenarioContext("XPATH");
            //action.click(SetupManager.getDriver().findElement(By.xpath(xpath)));

            log("I open the live delivery details in admin portal",
                    "I have opened the live delivery details in admin portal");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I check the price for delivery$")
    public void i_check_the_price_for_delivery() throws Throwable {
        try{
        String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String xpath = String.format("//td[contains(.,'')]/following-sibling::td[contains(.,'%s')]/preceding::td[1]", customer);
        String trip_Price = action.getText(SetupManager.getDriver().findElement(By.xpath(xpath)));
        String[] splited_price =trip_Price.split("/",2);
        String actual_price = splited_price[1];
        actual_price = actual_price.replace(" ","");
        cucumberContextManager.setScenarioContext("Price_Before",actual_price);

        log("I check the price for delivery",
                "I have checked the price for delivery");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^Delivery price is recalculated based on updated value of drop off address$")
    public void delivery_price_is_recalculated_based_on_updated_value_of_drop_off_address() throws Throwable {
        try{
        String new_Price = action.getText(admin_EditScheduledBungiiPage.Text_Estimated_Price());
        new_Price = new_Price.replace("$","");
        new_Price = new_Price.replace(" ","");
        String old_Price = (String) cucumberContextManager.getScenarioContext("Price_Before");
        old_Price = old_Price.replace("$","");
        testStepVerify.isNotEquals(new_Price,old_Price);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I enter cancellation fee and Comments$")
    public void i_enter_cancellation_fee_and_comments() throws Throwable {
        try{
        action.clearSendKeys(admin_ScheduledTripsPage.Textbox_CancellationFee(), "0");
        action.clearSendKeys(admin_ScheduledTripsPage.Textbox_CancellationComment(), "Cancelling");
        action.selectElementByText(admin_ScheduledTripsPage.Dropdown_CancellationReason(), "Other");
        log("I enter cancellation fee amount and comments",
                "I have entered cancellation fee amount and comments", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @When("^I select reason as \"([^\"]*)\"$")
    public void i_enter_reason(String reason) throws Throwable {
        try{
            action.click(admin_ScheduledTripsPage.Dropdown_Reason());
            action.selectElementByText(admin_ScheduledTripsPage.Dropdown_Reason(), reason);
            log("I enter reason as "+reason,
                    "I have entered reason as "+reason, false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    /* Moved to BusinessUsers
    @And("^I click on \"([^\"]*)\" button$")
        public void i_click_on_something_button(String button) throws Throwable {
            switch (button)
            {
                case "Submit":
                    action.click(admin_ScheduledTripsPage.Button_Submit());
                    break;

            }

        }*/
    @Then("^The \"([^\"]*)\" message should be displayed$")
    public void the_something_message_should_be_displayed(String message) throws Throwable {
        testStepAssert.isElementTextEquals(admin_ScheduledTripsPage.Label_CancelSuccessMessage(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
    }

    @Then("^The \"([^\"]*)\" message should be displayed for live delivery$")
    public void the_something_message_should_be_displayed_for_live_delivery(String message) throws Throwable {
        if(message.equalsIgnoreCase("Pick up has been successfully updated.")){
            testStepAssert.isElementTextEquals(admin_ScheduledTripsPage.Label_DeliverySuccessMessageLive(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
        }
        else {
            testStepAssert.isElementTextEquals(admin_ScheduledTripsPage.Label_CancelSuccessMessageLive(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
        }
    }

    @Then("^Pickup should be unassigned from the driver$")
    public void pickup_should_be_unassigned_from_the_driver() throws Throwable {

    }

    @And("^I select the first driver$")
    public void i_select_the_first_driver() throws Throwable {
        try{
        //String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
        //action.click(admin_ScheduledTripsPage.Checkbox_driver(driver1));
        action.click(admin_ScheduledTripsPage.Checkbox_driver());
        log("I select the driver",
                "I selected the driver", true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    public String getGeofence(String geofence) {
        String geofenceName = "";
        switch (geofence) {
            case "washingtondc":
                geofenceName = "Washington DC";
                break;
            case "Kansas":
                geofenceName = "Kansas";
                break;

        }
        return geofenceName;
    }

    @Then("^Partner firm should receive \"([^\"]*)\" email$")
    public void partner_firm_should_receive_something_email(String emailSubject) throws Throwable {

        String emailBody = utility.GetSpecificPlainTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"), PropertyUtility.getEmailProperties("email.client.id"), emailSubject);
        if (emailBody == null) {
             testStepAssert.isFail("Email : " + emailSubject + " not received");
        }
        emailBody=emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ","");
        logger.detail("Email Body (Actual): "+ emailBody);
        String supportNumber = PropertyUtility.getDataProperties("support.phone.number");
        String firmName = PropertyUtility.getDataProperties("washington.Partner.Firm.Name");
        String driverName = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
        String driverPhone = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
        String driverLicencePlate = PropertyUtility.getDataProperties("partnerfirm.driver1.LicencePlate");
        String name = (String) cucumberContextManager.getScenarioContext("BUSINESSUSER_NAME");
        String customerName = null;
        String customerPhone = null;
        String customerEmail = null;
        boolean hasDST=false;

        if (!name.isEmpty()) {
            customerName = (String) cucumberContextManager.getScenarioContext("BUSINESSUSER_NAME") + " Business User";
            customerPhone = getCustomerPhone((String) cucumberContextManager.getScenarioContext("BUSINESSUSER_NAME"), "Business User");
            customerEmail = getCustomerEmail((String) cucumberContextManager.getScenarioContext("BUSINESSUSER_NAME"), "Business User");
        } else {
            customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String[] Name = customerName.split(" ");
            customerPhone = getCustomerPhone(Name[0], Name[1]);
            customerEmail = getCustomerEmail(Name[0], Name[1]);
        }

        String pickupdate = (String) cucumberContextManager.getScenarioContext("PICKUP_TIME");
        if (pickupdate == "") {

            pickupdate = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");

            if (pickupdate == "" || pickupdate == "NOW") {
                pickupdate = getOndemandStartTime((String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST"));
                TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
                Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(pickupdate);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.MINUTE, 30);
                int min = calendar.getTime().getMinutes();
                int remainder = (min % 15);
                int minutes = (15 - remainder);
                calendar.add(Calendar.MINUTE, minutes);
                TimeZone.setDefault(TimeZone.getTimeZone(utility.getTripTimezone((String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE"))));
                Date date1 = calendar.getTime();

                TimeZone zone = TimeZone.getTimeZone("America/New_York");

                hasDST = zone.observesDaylightTime();

                if(hasDST){

                    int hr1 = date1.getHours() + 1;
                    date1.setHours(hr1);
                    pickupdate = new SimpleDateFormat("EEEE, MMMM d, yyyy h:mm a z").format(date1).toString();
                    //pickupdate.replaceAll("EST","EDT");
                    //emailBody.replaceAll("EST","EDT");
                }
                else{
                    pickupdate = new SimpleDateFormat("EEEE, MMMM d, yyyy h:mm a z").format(date1).toString();
                }
               // pickupdate = new SimpleDateFormat("EEEE, MMMM d, yyyy hh:mm a z").format(date1).toString();

            } else {
                TimeZone.setDefault(TimeZone.getTimeZone(utility.getTripTimezone((String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE"))));
                Date date = new SimpleDateFormat("MMM dd, hh:mm a z").parse(pickupdate);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                date.setYear(date.getYear()+(year-date.getYear()));
                pickupdate = new SimpleDateFormat("EEEE, MMMM d, yyyy hh:mm a z").format(date).toString();
            }

        }
        String message = null;
        switch (emailSubject) {
            case "Bungii Delivery Pickup Scheduled":
               // message = utility.getExpectedPartnerFirmScheduledEmailContent(pickupdate, customerName, customerPhone, customerEmail, driverName, driverPhone, driverLicencePlate, supportNumber, firmName);
                if(hasDST){
                    message = utility.getExpectedPartnerFirmScheduledEmailContent(pickupdate, customerName, customerPhone, customerEmail, driverName, driverPhone, driverLicencePlate, supportNumber, firmName);
                    message= message.replaceAll("EST","EDT");
                }else {
                    message = utility.getExpectedPartnerFirmScheduledEmailContent(pickupdate, customerName, customerPhone, customerEmail, driverName, driverPhone, driverLicencePlate, supportNumber, firmName);
                }
                break;
            case "Bungii Delivery Pickup Updated":
                if(hasDST){
                    message = utility.getExpectedPartnerFirmUpdatedEmailContent(pickupdate, customerName, customerPhone, customerEmail, driverName, driverPhone, driverLicencePlate, supportNumber, firmName);
                    message= message.replaceAll("EST","EDT");
                }else {
                    message = utility.getExpectedPartnerFirmUpdatedEmailContent(pickupdate, customerName, customerPhone, customerEmail, driverName, driverPhone, driverLicencePlate, supportNumber, firmName);
                }
              //  message = utility.getExpectedPartnerFirmUpdatedEmailContent(pickupdate, customerName, customerPhone, customerEmail, driverName, driverPhone, driverLicencePlate, supportNumber, firmName);
                break;
            case "Bungii Delivery Pickup Canceled":
                if(hasDST){
                    message = utility.getExpectedPartnerFirmCanceledEmailContent(customerName, customerPhone, customerEmail, driverName, supportNumber, firmName);
                    message= message.replaceAll("EST","EDT");
                }else {
                    message = utility.getExpectedPartnerFirmCanceledEmailContent(customerName, customerPhone, customerEmail, driverName, supportNumber, firmName);
                }
                //message = utility.getExpectedPartnerFirmCanceledEmailContent(customerName, customerPhone, customerEmail, driverName, supportNumber, firmName);
                break;
        }
        message= message.replaceAll(" ","");
        //message= message.replaceAll("EST","EDT");
        logger.detail("Email Body (Expected): "+message);
          testStepAssert.isEquals(emailBody, message,"Email "+ message+" content should match with Actual", "Email  "+emailBody+" content matches with Expected", "Email "+emailBody+"  content doesn't match with Expected");

    }

    @Then("^Admin should receive the \"([^\"]*)\" email$")
    public void admin_should_receive_the_something_email(String emailSubject) throws Throwable {

        try{
        String emailBody = utility.GetSpecificPlainTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"), PropertyUtility.getEmailProperties("email.client.id"), emailSubject);
        if (emailBody == null) {
            testStepAssert.isFail("Email : " + emailSubject + " not received");
        }
        emailBody=emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ","");
        logger.detail("Email Body (Actual): "+ emailBody);
        //String supportNumber = PropertyUtility.getDataProperties("support.phone.number");
        //String firmName = PropertyUtility.getDataProperties("washington.Partner.Firm.Name");

        String name = (String) cucumberContextManager.getScenarioContext("BUSINESSUSER_NAME");
        String customerName = null;
        String customerPhone = null;
        String customerEmail = null;
        boolean hasDST=false;

         String Partner_Name = "";
        String PPSite = (String) cucumberContextManager.getScenarioContext("SiteUrl");
        if(PPSite.equalsIgnoreCase("Normal")){
            Partner_Name ="MRFM, San Francisco CA";
        }

         String Scheduled_Date = (String) cucumberContextManager.getScenarioContext("Partner_Schedule_Time");
        String Scheduled_Date_Split[] = Scheduled_Date.split("at ");
        String Str1 = Scheduled_Date_Split[0];
        String Str2 = Scheduled_Date_Split[1];
         //Scheduled_Date =Scheduled_Date.replaceAll("at ","");
        // SimpleDateFormat sdfd = new SimpleDateFormat("MMM dd, YYYY HH:mm aa z",Locale.ENGLISH);
        SimpleDateFormat sdfd = new SimpleDateFormat("HH:mm aa z",Locale.ENGLISH);
         sdfd.setTimeZone(TimeZone.getTimeZone("UTC"));
         SimpleDateFormat edfd = new SimpleDateFormat("HH:mm aa");
         String geofenceLabel =utility.getTimeZoneBasedOnGeofence();
         edfd.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

         Date date = sdfd.parse(Str2);
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         //cal.add(Calendar.MINUTE, 15);
         String New_Scheduled_Date = edfd.format(cal.getTime());
         String FSD = Str1.concat(New_Scheduled_Date);
        StringBuffer str = new StringBuffer(FSD);
        str.insert(13,"at ");
         New_Scheduled_Date = str.toString();

         String Pickup_Address = (String) cucumberContextManager.getScenarioContext("EmailPickupAddress");
         String Dropup_Address = (String) cucumberContextManager.getScenarioContext("EmailDeliveryAddress");
         String Customer_Name = (String) cucumberContextManager.getScenarioContext("Customer_Name");
         String Customer_Phone = (String) cucumberContextManager.getScenarioContext("CustomerPhone");
         String Driver_Name = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
         String Driver_Phone = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
         String Driver_Licence_Plate = null;
         if(!Driver_Name.isEmpty()) {
             Driver_Licence_Plate = PropertyUtility.getDataProperties("email.driver.LicencePlate");
         }

         String Items_To_Deliver = (String) cucumberContextManager.getScenarioContext("Item_Name");
         String Pickup_Contact_Name = (String) cucumberContextManager.getScenarioContext("PickupContactName");
         String Pickup_Contact_Phone = (String) cucumberContextManager.getScenarioContext("PickupContactPhone");

        String message = null;
        switch (emailSubject) {
            case "Partner Delivery Canceled!":
                if(Driver_Name.isEmpty()) {
                    message = utility.getExpectedPartnerPortalCanceledEmailContentWithoutDriver(Partner_Name, New_Scheduled_Date, Pickup_Address, Dropup_Address, Customer_Name, Customer_Phone, Items_To_Deliver, Pickup_Contact_Name, Pickup_Contact_Phone);
                }
                else{
                    message = utility.getExpectedPartnerPortalCanceledEmailContentWithDriver(Partner_Name, New_Scheduled_Date, Pickup_Address, Dropup_Address, Customer_Name, Customer_Phone, Driver_Name, Driver_Phone, Driver_Licence_Plate, Items_To_Deliver, Pickup_Contact_Name, Pickup_Contact_Phone);

                }
                break;
        }
        message= message.replaceAll(" ","");
        //message= message.replaceAll("EST","EDT");
        logger.detail("Email Body (Expected): "+message);
        testStepAssert.isEquals(emailBody, message,"Email "+ message+" content should match with Actual", "Email  "+emailBody+" content matches with Expected", "Email "+emailBody+"  content doesn't match with Expected");

        }catch(Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Admin unable to received the " +emailSubject+ "email",
                    true);
             }
    }

    @And("^Customer should receive \"([^\"]*)\" email$")
    public void customer_should_receive_something_email(String emailSubject) throws Throwable {
        try {
            String emailBody = utility.GetSpecificURLs(PropertyUtility.getEmailProperties("email.from.address"), PropertyUtility.getEmailProperties("email.client.id"), emailSubject);
            if (emailBody.equals("")||emailBody==null) {
                testStepAssert.isFail("Email " + emailSubject + " with link is not received.");
            } else {
                action.navigateTo(emailBody);
                String url = action.getCurrentURL();
                String survey_link = PropertyUtility.getDataProperties("washington.survey.email.link");
                testStepAssert.isTrue(url.contains(survey_link), "Survey Email link should be " + survey_link, "Survey email link is " + survey_link, "Survey email link is " + url);
                log("Customer should receive " + emailSubject + " email",
                        "Customer received " + emailSubject + " email", true);
            }
        }
        catch(Exception e)
            {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step  Should be successful", "Error in fetching email",
                        true);
            }

       }

    @And("^I note the Pickupref of trip$")
    public void i_note_the_pickupref_of_trip() throws Throwable {

        try{
        String customerRef = (String) cucumberContextManager.getScenarioContext("CUSTOMER_REF");
        String pickupref= new DbUtility().getLatestPickupRefOfCustomer(customerRef);
        cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickupref);
        log("I note the Pickupref of delivery",
                "Pickupref is "+pickupref, false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^Partner firm should not receive \"([^\"]*)\" email$")
    public void partner_firm_should_not_receive_something_email(String emailSubject) throws Throwable {
        String emailBody = utility.GetSpecificPlainTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"), PropertyUtility.getEmailProperties("email.client.id"), emailSubject);
        if (emailBody != null) {
            testStepAssert.isFail("Email : " + emailSubject + " received to partner firm though required number of drivers not accepted the trip");
        }
    }

    @And("^I remove non control driver \"([^\"]*)\"$")
    public void i_remove_non_control_driver_something(String driver) throws Throwable {
        try{
        action.click(admin_ScheduledTripsPage.Checkbox_NonControlDriver());
        action.click(admin_ScheduledTripsPage.Button_RemoveDrivers());
        log("I remove non control driver",
                "I have removed non control driver", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I remove control driver \"([^\"]*)\"$")
    public void i_remove_control_driver_something(String driver) throws Throwable {
        try{
        action.click(admin_ScheduledTripsPage.Checkbox_ControlDriver());
        action.click(admin_ScheduledTripsPage.Button_RemoveDrivers());
        log("I remove control driver",
                "I have removed control driver", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I remove non control driver \"([^\"]*)\" on edit popup$")
    public void i_remove_non_control_driver_somethingOnEdit(String driver) throws Throwable {
        try{
        action.click(admin_ScheduledTripsPage.Checkbox_NonControlDriverEdit());
        action.click(admin_ScheduledTripsPage.Button_RemoveDriversEdit());
        log("I remove non control driver on edit popup",
                "I have removed non control driver on edit popup", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I remove control driver \"([^\"]*)\" on edit popup$")
    public void i_remove_control_driver_somethingOnEdit(String driver) throws Throwable {
        try{
        action.click(admin_ScheduledTripsPage.Checkbox_ControlDriverEdit());
        action.click(admin_ScheduledTripsPage.Button_RemoveDriversEdit());
        log("I remove control driver on edit popup",
                "I have removed control driver on edit popup", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @Then("^The driver should get removed successfully$")
    public void the_driver_should_get_removed_successfully() throws Throwable {
        try{
        testStepAssert.isElementDisplayed(admin_ScheduledTripsPage.Label_DriverRemovalSuccessMessage(), "Driver(s) removed successfully", "Pass", "Fail");
        action.click((admin_ScheduledTripsPage.Button_Close()));
        log("I click close button",
                "I have clicked close button ", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I search by client name \"([^\"]*)\"$")
    public void i_search_by_client_name_something(String searchString) throws Throwable {
        try{
        action.selectElementByText(admin_TripsPage.DropDown_SearchForPeriod(), "The Beginning of Time");
        action.sendKeys(admin_TripsPage.TextBox_Search(), searchString + Keys.ENTER);
        log("I search " + searchString + "Client Name",
                "I have on searched " + searchString + " Client Name", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^All the clients named \"([^\"]*)\" should be displayed on the delivery list grid$")
    public void all_the_clients_named_something_should_be_displayed_on_the_trip_list_grid(String searchString) throws Throwable {
        try{
        Thread.sleep(10000);
        List<WebElement> customerNames = admin_TripsPage.Client_names();
        testStepAssert.isTrue(customerNames.size() > 0, "Search using customer firstname should retrieve correct records","Search using customer name retrives 0 records");
        try { for (WebElement e : customerNames) {
                testStepAssert.isTrue(e.getText().contains(searchString), "Client Name contains " + searchString, "Client Name is " + e.getText());
            }
            action.clear(admin_TripsPage.TextBox_Search());


        } catch (StaleElementReferenceException e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        action.clear(admin_TripsPage.TextBox_Search());

        log("I clear search box",
                "I have cleared search box ", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I click on \"([^\"]*)\" icon on \"([^\"]*)\" Page$")
    public void i_click_on_something_icon_on_something_page(String icon, String page) throws Throwable {
        try{
        action.selectElementByText(admin_TripsPage.DropDown_SearchForPeriod(), "The Beginning of Time");
        action.clear(admin_TripsPage.TextBox_Search());
        switch (page) {
            case "All Deliveries":
                switch (icon) {
                    case "Filter":
                        action.click(admin_TripsPage.Button_Filter());
                        break;
                }
                break;
        }
        log("I click on " + icon + " on " + page + " page",
                "I have clicked on " + icon + " on " + page + " page", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^All statuses except \"([^\"]*)\" are selected$")
    public void all_statuses_except_something_are_selected(String status) throws Throwable {
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterPaymentUnsuccessful().isSelected(), "Checkbox Status Payment Successful should be selected", "Checkbox Status Payment Successful is selected", "Checkbox Status Payment Successful is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterPaymentSuccessful().isSelected(), "Checkbox Status Payment Unsuccessful should be selected", "Checkbox Status Payment Unsuccessful is selected", "Checkbox Status Payment Unsuccessful is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterCustomerCancelled().isSelected(), "Checkbox Status Customer Canceled should be selected", "Checkbox Status Customer Canceled is selected", "Checkbox Status Customer Canceled is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterDriverCancelled().isSelected(), "Checkbox Status Driver Canceled should be selected", "Checkbox Status Driver Canceled is selected", "Checkbox Status Driver Canceled is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterAdminCancelled().isSelected(), "Checkbox Status Admin Canceled should be selected", "Checkbox Status Admin Canceled is selected", "Checkbox Status Admin Canceled is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterPickupWithError().isSelected(), "Checkbox Status Pickup With Error should be selected", "Checkbox Status Pickup With Error is selected", "Checkbox Status Pickup With Error is NOT selected");
        testStepAssert.isFalse(admin_TripsPage.CheckBox_FilterPriceEstimated().isSelected(), "Checkbox Status Price Estimated should NOT be selected", "Checkbox Status Price Estimated is NOT selected", "Checkbox Status Price Estimated is selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterDriversNotFound().isSelected(), "Checkbox Status Drivers Not Found should be selected", "Checkbox Status Drivers Not Found is selected", "Checkbox Status Drivers Not Found is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterDriverNotArrived().isSelected(), "Checkbox Status Drivers Not Arrived should be selected", "Checkbox Status Drivers Not Arrived is selected", "Checkbox Status Drivers Not Arrived is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterDriverRemoved().isSelected(), "Checkbox Status Drivers Removed should be selected", "Checkbox Status Drivers Removed is selected", "Checkbox Status Drivers Not Removed is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterPromoterPaymentPending().isSelected(), "Checkbox Status Promoter Payment Pending should be selected", "Checkbox Status Promoter Payment Pending is selected", "Checkbox Status Promoter Payment Pending is NOT selected");
    }

    @Then("^All types and categories are selected$")
    public void all_types_and_categories_are_selected() throws Throwable {
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterSolo().isSelected(), "Type Solo should be selected", "Type Solo is selected", "Type Solo is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterDuo().isSelected(), "Type Duo should be selected", "Type Duo is selected", "Type Duo is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterOnDemand().isSelected(), "Category On-Demand should be selected", "Category On-Demand is selected", "Category On-Demand is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterScheduled().isSelected(), "Category Scheduled should be selected", "Category Scheduled is selected", "Category Scheduled is NOT selected");
    }

    void uncheck_all_statuses() {
        if (admin_TripsPage.CheckBox_FilterPaymentUnsuccessful().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterPaymentUnsuccessful());
        if (admin_TripsPage.CheckBox_FilterPaymentSuccessful().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterPaymentSuccessful());
        if (admin_TripsPage.CheckBox_FilterCustomerCancelled().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterCustomerCancelled());
        if (admin_TripsPage.CheckBox_FilterAdminCancelled().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterAdminCancelled());
        if (admin_TripsPage.CheckBox_FilterDriverCancelled().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterDriverCancelled());
        if (admin_TripsPage.CheckBox_FilterPartnerCancelled().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterPartnerCancelled());
        if (admin_TripsPage.CheckBox_FilterPickupWithError().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterPickupWithError());
        if (admin_TripsPage.CheckBox_FilterPriceEstimated().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterPriceEstimated());
        if (admin_TripsPage.CheckBox_FilterDriversNotFound().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterDriversNotFound());
        if (admin_TripsPage.CheckBox_FilterDriverNotArrived().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterDriverNotArrived());
        if (admin_TripsPage.CheckBox_FilterDriverRemoved().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterDriverRemoved());
        if (admin_TripsPage.CheckBox_FilterPromoterPaymentPending().isSelected())
            action.click(admin_TripsPage.CheckBox_FilterPromoterPaymentPending());

        log("I uncheck all filter from trips page",
                "I have unchecked all filter from trips page", true);
    }

    @When("^I select filter \"([^\"]*)\" as \"([^\"]*)\"$")
    public void i_select_filter_something_as_something(String filter, String value) throws Throwable {
        try{
        Thread.sleep(5000);
        switch (filter)
        {
            case "Statuses" :
                switch (value){
                    case "Payment Unsuccessful":
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterPaymentUnsuccessful());
                        break;
                    case ("Payment Successful"):
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterPaymentSuccessful());
                        break;
                    case ("Customer Canceled"):
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterCustomerCancelled());
                        break;
                    case ("Driver Canceled"):
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterDriverCancelled());
                        break;
                    case "Admin Canceled":
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterAdminCancelled());
                        break;
                    case "Pickup with Error":
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterPickupWithError());
                        break;
                    case "Price Estimated":
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterPriceEstimated());
                        break;
                    case "No Driver(s) Found":
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterDriversNotFound());
                        break;
                    case "Driver Not Arrived":
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterDriverNotArrived());
                        break;
                    case "Driver Removed":
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterDriverRemoved());;
                        break;
                    case "Promoter Payment Pending":
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterPromoterPaymentPending());;
                        break;
                    case "Pending":
                        action.click(admin_TripsPage.Button_Filter());
                        action.click(admin_TripsPage.CheckBox_FilterPending());
                        break;
                    case "Assigning Driver(s)":
                        action.click(admin_TripsPage.Button_Filter());
                        action.click(admin_TripsPage.CheckBox_AssigningDrivers());
                        break;
                }
                break;

            case "Type":
                switch (value) {
                    case "Solo":
                        action.click(admin_TripsPage.Button_Filter());
                        action.click(admin_TripsPage.CheckBox_FilterPaymentSuccessful());
                        action.click(admin_TripsPage.CheckBox_FilterDuo());
                        break;
                    case "Duo":
                        action.click(admin_TripsPage.Button_Filter());
                        action.click(admin_TripsPage.CheckBox_FilterSolo());
                        action.click(admin_TripsPage.CheckBox_FilterDuo());
                        break;
                }
                break;

            case "Category":
                switch (value) {
                    case "On-Demand":
                        action.click(admin_TripsPage.Button_Filter());
                        action.click(admin_TripsPage.CheckBox_FilterSolo());
                        action.click(admin_TripsPage.CheckBox_FilterScheduled());
                        break;
                    case "Scheduled":
                        action.click(admin_TripsPage.Button_Filter());
                        action.click(admin_TripsPage.CheckBox_FilterScheduled());
                        action.click(admin_TripsPage.CheckBox_FilterOnDemand());
                        break;
                }
                break;
            case "Vehicle Type":
                action.click(admin_TripsPage.Button_Filter());
                switch (value){
                    case "Box Truck":
                        action.click(admin_DriverPage.Checkbox_BoxTruck());
                        break;
                    case "Moving Van":
                        action.click(admin_DriverPage.Checkbox_MovingVan());
                        break;
                    case "Pickup Truck":
                        action.click(admin_DriverPage.Checkbox_PickupTruck());
                        break;
                    case "SUV":
                        action.click(admin_DriverPage.Checkbox_SUV());
                        break;
                }
                break;
        }
        log("I select filter " +filter+" as " + value ,
                "I have selected filter " +filter+" as " + value, false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^the triplist grid shows the results by type \"([^\"]*)\"$")
    public void the_triplist_grid_shows_the_results_by_type_something(String filter) throws Throwable {
        try{
        Thread.sleep(4000);
        if(SetupManager.getDriver().getPageSource().contains("No Deliveries found.")) {
            testStepAssert.isTrue(true, "No Deliveries found.", "No Deliveries found.");
        }
        else{
            Thread.sleep(5000);
            String xpath = null;
            List<WebElement> rowswithstatus = null;
            List<WebElement> rows = null;
            switch (filter) {
                case "Payment Unsuccessful Status":
                    xpath = String.format("//td[contains(.,'Payment Pending')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Payment Successful Status":
                    xpath = String.format("//td[contains(.,'Payment Successful')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Customer Canceled Status":
                    xpath = String.format("//td[contains(.,'Customer Canceled')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Driver Canceled Status":
                    xpath = String.format("//td[contains(.,'Driver Canceled')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Admin Canceled Status":
                    xpath = String.format("//td[contains(.,'Admin Canceled')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Pickup with Error Status":
                    xpath = String.format("//td[contains(.,'Pickup with Error') | contains(.,'Unable To Estimate')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Price Estimated Status":
                    xpath = String.format("//td[contains(.,'Price Estimated')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "No Driver(s)Found Status":
                    xpath = String.format("//td[contains(.,'No Driver(s) Found')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Driver Not Arrived Status":
                    xpath = String.format("//td[contains(.,'Driver Not Arrived')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Driver Removed Status":
                    xpath = String.format("//td[contains(.,'Driver Removed')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Promoter Payment Pending Status":
                    xpath = String.format("//td[contains(.,'Promoter Payment Pending')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Solo Type":
                    //xpath = String.format("//td[3][text()='Solo']");
                    xpath = String.format("//td/span[contains(.,'Solo')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Duo Type":
                    //xpath = String.format("//td[3][text()='Duo']");
                    xpath = String.format("//td/span[contains(.,'Duo')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "On-Demand Category":
                    //xpath = String.format("//td[4][text()='On-Demand']");
                    xpath = String.format("//td[contains(.,'On-Demand')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Scheduled Category":
                    //xpath = String.format("//td[4][text()='Scheduled']");
                    xpath = String.format("//td[contains(.,'Scheduled')]");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
            }
        }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @And("^I refresh the page$")
    public void i_refresh_the_page() throws Throwable {
        try{
       action.refreshPage();
        log("I refresh page",
                "I have refreshed page ", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^Tick mark should be displayed beside driver and scheduled date$")
    public void tick_mark_should_be_displayed_beside_driver_and_scheduled_date() throws Throwable {
      //  testStepAssert.isElementDisplayed(admin_EditScheduledBungiiPage.TickMarkDate());
          //      testStepAssert.isElementDisplayed(admin_EditScheduledBungiiPage.TickMarkDriver(""));

    }

    @Then("^\"([^\"]*)\" message should be displayed$")
    public void something_message_should_be_displayed(String message) throws Throwable {
try{
        switch(message) {

            case "Your changes are good to be saved.":
            testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_VerifiedMessage(), message, message +" should be displayed", message +" is displayed",message +" is not displayed");
            break;
            case "Bungii Saved":
                testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_SuccessMessage(), message, message +" should be displayed", message +" is displayed",message +" is not displayed");
                break;
            case "Pickup request is being processed. You may have to refresh the page.":
                testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_InfoMessage(), message,message +" should be displayed", message +" is displayed",message +" is not displayed");
                break;
            case "Bungii Saved!":
                testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_SuccessMessage(), message, message +" should be displayed", message +" is displayed",message +" is not displayed");
                action.click(admin_EditScheduledBungiiPage.Button_Close());
                break;
            case "Oops! It looks like this trip is a little outside our scope.":
                testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_VerifyError(), message,message +" should be displayed", message +" is displayed",message +" is not displayed");
                action.click(admin_EditScheduledBungiiPage.Button_Close());
                break;
        }
} catch(Exception e){
    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
    error("Step should be successful", "Error performing step,Please check logs for more details",
            true);
}

    }

    @And("^I update the Scheduled date of the trip by 15 minutes$")
    public void i_update_the_scheduled_date_of_the_trip_by_15_minutes()  {
        try{
        String value = admin_EditScheduledBungiiPage.TimePicker_Time().getAttribute("value");
            action.click(admin_EditScheduledBungiiPage.TimePicker_Time());
            LocalTime time= LocalTime.parse(value, DateTimeFormatter.ofPattern("hh:mm a"));
        value = time.plusMinutes(15).format(DateTimeFormatter.ofPattern("hh:mm a")).toString();
        action.click(admin_EditScheduledBungiiPage.List_TimeFrame(value));
        log("I update the Scheduled date of the trip by 15 minutes",
                "I have updated the Scheduled date of the trip by 15 minutes", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I remove driver \"([^\"]*)\" and add the new driver \"([^\"]*)\"$")
    public void i_remove_driver_something_and_add_the_new_driver_something(String driverRemove, String driverAdd)  {
        try{
        action.click(admin_EditScheduledBungiiPage.Checkbox_Driver(driverRemove));
        action.click(admin_EditScheduledBungiiPage.Link_RemoveDriver());
        action.clearSendKeys(admin_EditScheduledBungiiPage.TextBox_DriverSearch(),driverAdd);
        action.click(admin_EditScheduledBungiiPage.List_DriverSearchResult(driverAdd));
        log("I remove driver "+driverRemove+" and add the new driver "+driverAdd,
                "I have removed driver "+driverRemove+" and add the new driver "+driverAdd, false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I click on \"([^\"]*)\" button on Edit Scheduled bungii popup$")
    public void i_click_on_something_button_on_edit_scheduled_bungii_popup(String button) throws InterruptedException {
        try{

        switch (button) {
            case "Save":
                action.click(admin_EditScheduledBungiiPage.Button_Save());
                break;
            case "Verify":
                action.click(admin_EditScheduledBungiiPage.Button_Verify());
                break;
            case "Undo":
                action.click(admin_EditScheduledBungiiPage.Button_Undo());
                break;
            case "Remove Driver":
                action.click(admin_EditScheduledBungiiPage.Link_RemoveDriver());
                break;
        }
        log("I click on "+button+" button on Edit Scheduled bungii popup",
                "I have clicked on "+button+" button on Edit Scheduled bungii popup", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^Under Drivers: for both Driver 1 and 2 : \"([^\"]*)\" should be displayed$")
    public void under_drivers_for_both_driver_1_and_2_something_should_be_displayed(String scenario) throws Throwable {
        testStepAssert.isElementDisplayed(admin_EditScheduledBungiiPage.Label_Driver1MessageOnResearch(), "Driver 1: Bungii driver is being searched should be displayed","Driver 1: Bungii driver is being searched is displayed", "Driver 1: Bungii driver is being searched is not displayed");
        testStepAssert.isElementDisplayed(admin_EditScheduledBungiiPage.Label_Driver2MessageOnResearch(), "Driver 2: Bungii driver is being searched should be displayed","Driver 2: Bungii driver is being searched is displayed", "Driver 2: Bungii driver is being searched is not displayed");


    }
    @Then("^Under Drivers: for both Driver 1 : \"([^\"]*)\" and 2 : \"([^\"]*)\" should be displayed$")
    public void under_drivers_for_both_driver_1_aand_2_asomething_should_be_displayed(String scenario1, String scenario2) throws Throwable {
            testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_Driver1NameOnResearch(),scenario1, "Driver 1: "+scenario1+" should be displayed","Driver 1: "+scenario1+" is displayed", "Driver 1: "+scenario1+" is not displayed");
            testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_Driver2NameOnResearch(),scenario2, "Driver 1: "+scenario2+" should be displayed","Driver 1: "+scenario2+" is displayed", "Driver 1: "+scenario2+" is not displayed");
    }
    @Then("^Under Drivers: for Driver 1: \"([^\"]*)\" should be displayed$")
    public void under_drivers_for_both_driver_1_something_should_be_displayed(String scenario) throws Throwable {
        if (scenario.equalsIgnoreCase("Bungii driver is being searched")) {
            testStepAssert.isElementDisplayed(admin_EditScheduledBungiiPage.Label_Driver1MessageOnResearch(), "Driver 1: Bungii driver is being searched should be displayed", "Driver 1: Bungii driver is being searched is displayed", "Driver 1: Bungii driver is being searched is not displayed");
            testStepAssert.isFalse(action.isElementPresent(admin_EditScheduledBungiiPage.Label_Driver2MessageOnResearch(true)), "Driver 2: Bungii driver is being searched should not be displayed", "Driver 2: Bungii driver is being searched is not displayed", "Driver 2: Bungii driver is being searched is displayed");
        }
           else
        {
                testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_Driver1NameOnResearch(),scenario, "Driver 1: "+scenario+" should be displayed","Driver 1: "+scenario+" is displayed", "Driver 1: "+scenario+" is not displayed");
        }
    }
    @Then("^I should see Bungii Type as \"([^\"]*)\" in \"([^\"]*)\" section$")
    public void i_should_see_bungii_type_as_something_in_something_section(String type, String section) throws Throwable {
        switch (section) {
            case "Research Scheduled Bungii":
                testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_DeliveryTypeOnResearch(), type,type +" should be displayed in Research Scheduled Bungii section",type +" is displayed in Research Scheduled Bungii section", type +" is not displayed in Research Scheduled Bungii section");
                break;
            case "Cancel entire Bungii and notify driver(s)":
                testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_DeliveryTypeOnCancel(), type,type +" should be displayed in Cancel entire Bungii and notify driver(s) section",type +" is displayed in Cancel entire Bungii and notify driver(s) section", type +" is not displayed in Cancel entire Bungii and notify driver(s) section");
                break;
        }
    }

    @Then("^Under Driver Details: for both Driver 1 and 2 : \"([^\"]*)\" should be displayed$")
    public void under_driver_details_for_both_driver_1_and_2_something_should_be_displayed(String strArg1) throws Throwable {
        testStepAssert.isElementDisplayed(admin_EditScheduledBungiiPage.Label_Driver1MessageOnEdit(), "Driver 1: Add driver below or Bungii driver search will continue should be displayed","Driver 1: Add driver below or Bungii driver search will continue is displayed", "Driver 1: Add driver below or Bungii driver search will continue is not displayed");
        testStepAssert.isElementDisplayed(admin_EditScheduledBungiiPage.Label_Driver2MessageOnEdit(), "Driver 2: Add driver below or Bungii driver search will continue should be displayed","Driver 2: Add driver below or Bungii driver search will continue is displayed", "Driver 2: Add driver below or Bungii driver search will continue is not displayed");

    }

    @Then("^Under Driver Details: for both Driver 1 : \"([^\"]*)\" and 2 : \"([^\"]*)\" should be displayed$")
    public void under_driver_details_for_both_driver_1_aand_2_asomething_should_be_displayed(String scenario1, String scenario2) throws Throwable {
        testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_Driver1NameOnEdit(),scenario1, "Driver 1: "+scenario1+" should be displayed","Driver 1: "+scenario1+" is displayed", "Driver 1: "+scenario1+" is not displayed");
        testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_Driver2NameOnEdit(),scenario2, "Driver 1: "+scenario2+" should be displayed","Driver 1: "+scenario2+" is displayed", "Driver 1: "+scenario2+" is not displayed");

    }

    @Then("^Under Driver Details: for Driver 1: \"([^\"]*)\" should be displayed$")
    public void under_driver_details_for_both_driver_1something_should_be_displayed(String scenario) throws Throwable {

        if (scenario.equalsIgnoreCase("Add driver below or Bungii driver search will continue")) {
            testStepAssert.isElementDisplayed(admin_EditScheduledBungiiPage.Label_Driver1MessageOnEdit(), "Driver 1: Add driver below or Bungii driver search will continue should be displayed","Driver 1: Add driver below or Bungii driver search will continue is displayed", "Driver 1: Add driver below or Bungii driver search will continue is not displayed");
           testStepAssert.isFalse(action.isElementPresent(admin_EditScheduledBungiiPage.Label_Driver2MessageOnEdit(true)), "Driver 2: Add driver below or Bungii driver search will continue should not be displayed","Driver 2: Add driver below or Bungii driver search will continue is not displayed", "Driver 2: Add driver below or Bungii driver search will continue is displayed");
    }
           else
    {
        testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_Driver1NameOnEdit(),scenario, "Driver 1: "+scenario+" should be displayed","Driver 1: "+scenario+" is displayed", "Driver 1: "+scenario+" is not displayed");
    }
    }

    @Then("^I should see \"([^\"]*)\" message on edit popup$")
    public void i_should_see_something_message_on_edit_popup(String message) throws Throwable {
        testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_ErrorMessage(),message, message+" should be displayed",message+" is displayed", message+" is not displayed");
    }


    @And("^the cost of the delivery should be doubled$")
    public void the_cost_of_the_delivery_should_be_doubled() throws Throwable {
        try{
        String costxpath = (String) cucumberContextManager.getScenarioContext("COSTPATH");
        DecimalFormat df = new DecimalFormat("0.00");
        Double orgcost = 0.00;
        try {
            String cost = (String) cucumberContextManager.getScenarioContext("COST");
             orgcost = Double.parseDouble(cost);
            orgcost = orgcost * 2;
        }
        catch(Exception ex)
        {
            logger.detail("Exception "+ ex.getLocalizedMessage());
        }
        Thread.sleep(1000);
        String actual = action.getText(action.getElementByXPath(costxpath)).replace("/ $","");
        testStepVerify.isEquals(actual, df.format(orgcost),orgcost+" should be displayed",orgcost+" is displayed", orgcost+" is not displayed instead "+ actual + " is displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^the cost of the delivery should be halved$")
    public void the_cost_of_the_delivery_should_be_halved() throws Throwable {
        try{
        String costxpath = (String) cucumberContextManager.getScenarioContext("COSTPATH");
        DecimalFormat df = new DecimalFormat("0.00");
        Double orgcost = 0.00;
        try {
            String cost = (String) cucumberContextManager.getScenarioContext("COST");
            orgcost = Double.parseDouble(cost);
            orgcost= orgcost/2;
    }
        catch(Exception ex)
    {
        logger.detail("Exception "+ ex.getLocalizedMessage());
    }
        Thread.sleep(1000);
        String actual = action.getText(action.getElementByXPath(costxpath)).replace("/ $","");
        testStepVerify.isEquals(actual, df.format(orgcost),orgcost+" should be displayed",orgcost+" is displayed", orgcost+" is not displayed instead "+ actual + " is displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^the cost of the delivery should be zero$")
    public void the_cost_of_the_delivery_should_be_zero() throws Throwable {
        try {
            String costxpath = (String) cucumberContextManager.getScenarioContext("COSTPATH");
            DecimalFormat df = new DecimalFormat("0.00");
            String cost = (String) cucumberContextManager.getScenarioContext("COST");
            Double orgcost = Double.parseDouble(cost);

            Thread.sleep(1000);
            // testStepVerify.isEquals(action.getText(action.getElementByXPath(costxpath)).replace("/ $",""), df.format(orgcost),orgcost+" should be displayed",orgcost+" is displayed", orgcost+" is not displayed");
            testStepAssert.isEquals(action.getText(action.getElementByXPath(costxpath)).replace("/ $", ""), df.format(orgcost), orgcost + " should be displayed", orgcost + " is displayed", orgcost + " is not displayed");
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Cost of the delivery is not shown as zero",
                    true);
        }
    }

    @And("^I view the searched delivery$")
    public void i_view_the_searched_delivery() throws Throwable {
        try {
            //action.click();
            Thread.sleep(4000);
            action.click(admin_ScheduledTripsPage.findElement((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr/td/div/img",PageBase.LocatorType.XPath));
            action.click(admin_ScheduledTripsPage.findElement((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr/td/div/ul/li/p[contains(text(),'Delivery Details')]",PageBase.LocatorType.XPath));
            //action.click(admin_ScheduledTripsPage.Link_Grid_First_Row());
            log("I should able to view searched delivery.", "I have viewed the searched delivery", false);

        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to view the searched delivery",
                    true);
        }
    }

    @Then("^I should see \"([^\"]*)\" field empty$")
    public void i_should_see_something_field_empty(String notetype) throws Throwable {
        try{
            switch (notetype){
                case "Additional Notes":
                    Thread.sleep(1000);
                    String addNote = action.getText(admin_EditScheduledBungiiPage.Text_Additional_Note());
                    testStepAssert.isTrue(addNote.length() <1,"Additional notes field should be empty","Additional notes field is empty","Additional notes field is not empty");
                    break;
                case "Additional Instructions":
                    String addInstruction = action.getText(admin_EditScheduledBungiiPage.Text_Additional_Instructions());
                    testStepAssert.isTrue(addInstruction.length() <1,"Additional notes field should be empty","Additional notes field is empty","Additional notes field is not empty");
                    String additionalNotes = "Additional Notes";
                    Thread.sleep(1000);
                    String expectedAdditionalNotesTitle = action.getText(admin_EditScheduledBungiiPage.Label_AdditionalNotes());
                    testStepAssert.isFalse(expectedAdditionalNotesTitle.contentEquals(additionalNotes),"Special Instructions should be displayed", "Special Instructions is displayed","Special Instructions is not displayed");
                    Thread.sleep(1000);
                    break;
            }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I remove the customer note$")
    public void i_remove_the_customer_note() throws Throwable {
        try{
            Thread.sleep(1000);
            action.clear(admin_EditScheduledBungiiPage.Text_Additional_Note());
            log("I should be able to remove the customer note","I could remove the customer note",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I change the delivery type from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void i_change_the_delivery_type_from_something_to_something(String initialTripTypeStatus, String expectedTripTypeStatus) throws Throwable {
        try {
            switch (expectedTripTypeStatus) {
                case "Solo":
                    Thread.sleep(1000);
                    action.click(admin_TripsPage.RadioButton_SoloTrip());
                    break;
                case "Duo":
                    Thread.sleep(1000);
                    action.click(admin_TripsPage.RadioButton_DuoTrip());
                    break;
            }
            log("I should be able to change delivery type to " + expectedTripTypeStatus,"I could change delivery type to " + expectedTripTypeStatus);
        } catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Unable to view the searched delivery",
                    true);
        }
    }

    @And("^I get the new \"([^\"]*)\"$")
    public void i_get_the_new_something(String strArg1) throws Throwable {
        try{
            String customerRef = (String) cucumberContextManager.getScenarioContext("CUSTOMER_REF");
            String pickupref = new DbUtility().getLatestPickupRefOfCustomer(customerRef);
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupref);
            String tripType = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            if (tripType.contains("Solo")) {
                cucumberContextManager.setScenarioContext("BUNGII_TYPE", "Duo Scheduled");
            } else if (tripType.contains("Duo")) {
                cucumberContextManager.setScenarioContext("BUNGII_TYPE", "Solo Scheduled");
            }
            utility.resetGeofenceDropdown();
            Thread.sleep(1000);
            log("I should be able to get the new pickup reference","I could get the new pickup reference",false);
        }catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Unable to view the searched delivery",
                    true);
        }
    }


    @And("^I get the latest \"([^\"]*)\"$")
    public void i_get_the_latest_something(String strArg1) throws Throwable {
        try{
            String customerRef = (String) cucumberContextManager.getScenarioContext("CUSTOMER_REF");
            String pickupref = new DbUtility().getLatestPickupRefOfCustomer(customerRef);
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupref);
            utility.resetGeofenceDropdown();
            log("I should be able to get the latest pickup reference","I could get the latest pickup reference",false);
        }catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to view the searched delivery",
                    true);
        }
    }

    @Then("^Confirmation message on edit live delivery pop up should be displayed$")
    public void confirmation_message_on_edit_live_delivery_pop_up_should_be_displayed() throws Throwable  {
        try
        {
            String expectedMessage = PropertyUtility.getMessage("admin.complete.confirm");
            String actualMessage = action.getText(admin_LiveTripsPage.Message_AdminCompleteConfirm());
            testStepAssert.isEquals(actualMessage, expectedMessage, expectedMessage + "should be displayed", expectedMessage + "is displayed", actualMessage + "is displayed");
        }

        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I should see the \"([^\"]*)\" background colour$")
    public void i_should_see_the_something_background_colour(String color) throws Throwable {
        try{
            switch (color){
                case "orange":
                    String expectedHighlightColor = PropertyUtility.getDataProperties("background.colour.orange");
                    Thread.sleep(1000);
                    String actualHighlightColor =  admin_LiveTripsPage.Text_DeliveryHighlight().getCssValue("background-color");
                    testStepAssert.isEquals(actualHighlightColor,expectedHighlightColor,"Delivery should be highlighted with orange color","Delivery is highlighted with orange color","Delivery is not highlighted with orange color");
                    break;
            }
            log("I should be able to see the correct background colour",
                    "I am able to see the correct background colour",false);
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^The delivery should be in \"([^\"]*)\" state$")
    public void the_delivery_should_be_in_something_state(String deliveryStatus) throws Throwable {
        try{
      switch (deliveryStatus){
          case "Assigning Driver(s)":
              Thread.sleep(5000);
              boolean isInDriverSearchState = admin_LiveTripsPage.Icon_LoadingIconSearching().isDisplayed();
              String deliveryState = action.getText(admin_LiveTripsPage.Text_DeliveryStatusScheduledDeliveriesAndLiveDeliveries());
              testStepAssert.isEquals(deliveryState,deliveryStatus,"Delivery should be in "+deliveryStatus +" state","Delivery is in "+deliveryStatus +" state","Delivery is not in "+deliveryStatus +" state");
              testStepAssert.isTrue(isInDriverSearchState,"Loading Animation should be displayed","Loading animation is displayed","Loading animation is not displayed");
              break;
          case "Admin Canceled - No Driver(s) Found":
              Thread.sleep(5000);
              String deliveryStatusForAdminCancel = action.getText(admin_LiveTripsPage.Text_DeliveryStatusAllDeliveries());
              testStepAssert.isEquals(deliveryStatusForAdminCancel,deliveryStatus,"Delivery should be in "+deliveryStatus +" state","Delivery is in "+deliveryStatus +" state","Delivery is not in "+deliveryStatus +" state");
              break;
          case "Assigning Driver(s) with no loader":
              Thread.sleep(2000);
              testStepAssert.isNotElementDisplayed(admin_LiveTripsPage.Icon_LoadingIconSearching(true),"Loading animation should not be displayed","Loading animation is not  displayed","Loading animation is displayed");
              boolean isInDriverNoSearchingState = admin_LiveTripsPage.Icon_LoadingIconStoppedSearching().isDisplayed();
              testStepAssert.isTrue(isInDriverNoSearchingState,"No Loading animation should be displayed","No Loading animation is displayed","No Loading animation is not displayed");
              break;
          case "No Driver(s) Found":
              Thread.sleep(2000);
              String noDriverFound = action.getText(admin_LiveTripsPage.Text_DeliveryStatusAllDeliveries());
              testStepAssert.isEquals(noDriverFound,deliveryStatus,"Delivery should be in "+deliveryStatus +" state","Delivery is in "+noDriverFound +" state","Delivery is not in "+deliveryStatus +" state");
              break;
      }
    }	catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I click on the filter link and should see \"([^\"]*)\" checkbox displayed$")
    public void i_click_on_the_filter_link_and_should_see_something_checkbox_displayed(String filterBy) throws Throwable {
      try{
        action.click(admin_TripsPage.Button_Filter());
        switch (filterBy){
            case "Assigning Driver(s)":
                boolean isAssigningDriversCheckboxDisplayed =  admin_TripsPage.CheckBox_AssigningDrivers().isDisplayed();
                String expectedFilterText = action.getText(admin_TripsPage.Text_AllFilterOptions(4));
                testStepAssert.isTrue(isAssigningDriversCheckboxDisplayed,filterBy +" filter checkbox should be displayed" ,filterBy +" filter checkbox is displayed",filterBy +" filter checkbox is not displayed");
                testStepAssert.isEquals(expectedFilterText,filterBy,filterBy +" Text should be displayed" ,expectedFilterText +" text  is displayed",filterBy +" text is not displayed");
                break;
            case "No Driver(s) Found":
                boolean isDriversNotFoundCheckboxDisplayed =  admin_TripsPage.CheckBox_FilterDriversNotFound().isDisplayed();
                testStepAssert.isTrue(isDriversNotFoundCheckboxDisplayed,filterBy +" filter checkbox should be displayed" ,filterBy +" filter checkbox is displayed",filterBy +" filter checkbox is not displayed");
                String expectedText = action.getText(admin_TripsPage.Text_AllFilterOptions(10)).trim();
                testStepAssert.isEquals(expectedText,filterBy,filterBy +" Text should be displayed" ,expectedText +" text  is displayed",filterBy +" text is not displayed");
                break;

        }
    }	catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @Then("^I should see the changes done by admin$")
    public void i_should_see_the_changes_done_by_admin(DataTable data) throws Throwable {
        try{
        Thread.sleep(3000);
        List<Map<String, String>> dataMap = data.asMaps();
        for(int i=0;i<dataMap.size();i++) {
            String status = dataMap.get(i).get("Event").trim();
            switch(status){
                case "Pickup Address Change":
                    String oldpickupAddress = dataMap.get(i).get("Old Value").trim();
                    String newPickupAddress = dataMap.get(i).get("New Value").trim();
                    String expectedOldPickUpaddress = action.getText(admin_ScheduledTripsPage.Text_HistoryTabInformation(2,2));
                    String expectedNewPickupAddress =action.getText(admin_ScheduledTripsPage.Text_HistoryTabInformation(2,3));
                    testStepAssert.isEquals(expectedOldPickUpaddress,oldpickupAddress,"Old delivery pickup address should be "+oldpickupAddress,"Old delivery pickup address is "+expectedOldPickUpaddress,"Old delivery pickup address is not "+oldpickupAddress);
                    testStepAssert.isEquals(expectedNewPickupAddress,newPickupAddress,"New delivery pickup address should be "+newPickupAddress,"New delivery pickup address is "+expectedNewPickupAddress,"New delivery pickup address is not "+newPickupAddress);
                    break;
                case "Dropoff Address Change":
                    String oldDropOffAddress = dataMap.get(i).get("Old Value").trim().toLowerCase();
                    String  newDropOffAddress = dataMap.get(i).get("New Value").trim().toLowerCase();
                    String expectedOldDropOffddress = action.getText(admin_ScheduledTripsPage.Text_HistoryTabInformation(3,2)).toLowerCase().trim();
                    System.out.println(expectedOldDropOffddress);
                    System.out.println(oldDropOffAddress);
                    String expectedNewDropOffAddress =action.getText(admin_ScheduledTripsPage.Text_HistoryTabInformation(3,3)).toLowerCase().trim();
                    testStepAssert.isEquals(expectedOldDropOffddress,oldDropOffAddress,"Old dropoff address should be "+oldDropOffAddress,"Old dropoff address is "+expectedOldDropOffddress,"Old dropoff address is not "+newDropOffAddress);
                    testStepAssert.isEquals(expectedNewDropOffAddress,newDropOffAddress,"New dropoff address should be "+newDropOffAddress,"New dropoff address is "+expectedNewDropOffAddress,"New dropoff address is not "+newDropOffAddress);
                    break;
                case "Duo To Solo":
                    String oldDeliveryType = dataMap.get(i).get("Old Value").trim();
                    String newdDeliveryType = dataMap.get(i).get("New Value").trim();
                    String expectedOldDeliveryType = action.getText(admin_ScheduledTripsPage.Text_HistoryTabInformation(4,2));
                    String expectedNewDeliveryType =action.getText(admin_ScheduledTripsPage.Text_HistoryTabInformation(4,3));
                    testStepAssert.isEquals(expectedOldDeliveryType,oldDeliveryType,"Old delivery type should be "+oldDeliveryType,"Old delivery type is "+expectedOldDeliveryType,"Old delivery type is not "+oldDeliveryType);
                    testStepAssert.isEquals(expectedNewDeliveryType,newdDeliveryType,"New delivery type should be "+newdDeliveryType,"New delivery type is "+expectedNewDeliveryType,"New delivery type is not "+newdDeliveryType);
                    break;

            }
        }
    }	catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @Then("^The edit option should not be displayed for live deliveries$")
    public void the_edit_option_should_not_be_displayed_for_live_deliveries() throws Throwable {
        try{
       Thread.sleep(3000);
        action.click(admin_LiveTripsPage.Dropdown_Icon());
        Thread.sleep(1000);
        testStepAssert.isElementDisplayed(admin_LiveTripsPage.Dropdown_Icon(),"Edit option should not be displayed","Edit option is not displayed","Edit option is displayed");
    }	catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I stop searching driver$")
    public void i_stop_searching_driver() throws Throwable {
        try{
            action.click(admin_ScheduledTripsPage.Button_StopSearching());
            Thread.sleep(3000);
            testStepAssert.isElementDisplayed(admin_ScheduledTripsPage.Text_ConfirmationPopUp(),
                    "The confirmation pop-up should be displayed",
                    "The confirmation pop-up is displayed",
                    "The confirmation pop-up is not displayed");
            action.click(admin_ScheduledTripsPage.Button_ConfirmStopSearching());
            Thread.sleep(2000);
            testStepAssert.isElementDisplayed(admin_ScheduledTripsPage.Text_SuccessPopUp(),
                    "The stop searching driver success pop-up should be displayed",
                    "The stop searching driver success pop-up is displayed",
                    "The stop searching driver success pop-up is not displayed");
            action.click(admin_ScheduledTripsPage.Button_CloseConfirm());
            Thread.sleep(2000);
            action.click(admin_ScheduledTripsPage.Button_Ok());
            Thread.sleep(1000);

            log("I should be able to stop searching driver",
                    "I am able to stop searching driver",
                    false);

        }	catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The delivery should show \"([^\"]*)\" status on delivery details$")
    public void the_delivery_should_show_something_status_on_delivery_details(String expectedDeliveryStatus) throws Throwable {
        try{
        Thread.sleep(3000);
        String currentDeliveryStatus = action.getText(admin_ScheduledTripsPage.Text_DeliveryDetailsStatus());
        testStepAssert.isEquals(currentDeliveryStatus,expectedDeliveryStatus,"The delivery should be in " +expectedDeliveryStatus+" state in delivery details page","The delivery is in " +currentDeliveryStatus+" state in delivery details page","The delivery is not in " +expectedDeliveryStatus+" state in delivery details page");
    }	catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I should see field name as partner on delivery listing screen$")
    public void i_should_see_field_name_as_partner_on_delivery_listing_screen() throws Throwable {
        try {
            String expectedHeader = PropertyUtility.getMessage("PartnerColumnHeader");
            String actualHeader = action.getText(admin_TripsPage.Header_Partner());
            testStepAssert.isEquals(actualHeader, expectedHeader, expectedHeader + " should be displayed", expectedHeader + "is displayed", expectedHeader + " is not displayed");
        }

        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I should see field name as partner on delivery detail screen$")
    public void i_should_see_field_name_as_partner_on_delivery_detail_screen() throws Throwable{
        try {
            String expectedLabel = PropertyUtility.getMessage("PartnerColumnHeader");
            String actualLabel = action.getText(admin_TripDetailsPage.Label_Partner());
            testStepAssert.isEquals(actualLabel, expectedLabel, expectedLabel + " should be displayed", expectedLabel + "is displayed", expectedLabel + " is not displayed");
        }
        catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I view the delivery details for live deliveries$")
    public void i_view_the_delivery_details_for_live_deliveries() throws Throwable {
      try{
          Thread.sleep(3000);
          action.click(admin_ScheduledTripsPage.List_ViewDeliveries());
          log("I should be able to view delivery details for live deliveries","I am able to view delivery details for live deliveries",false);
      }
      catch(Exception e){
          logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
          error("Step should be successful", "Error performing step,Please check logs for more details",
                  true);
      }
    }

    @Then("^I check if error is shown when admin stop search again before its status is synced$")
    public void i_check_if_error_is_shown_when_admin_stop_search_again_before_its_status_is_synced() throws Throwable {
        try{
            action.click(admin_ScheduledTripsPage.Button_StopSearching());
            Thread.sleep(3000);
            action.click(admin_ScheduledTripsPage.Button_ConfirmStopSearching());
            Thread.sleep(3000);
            testStepAssert.isElementDisplayed(admin_ScheduledTripsPage.Text_ErrorPopUp(),
                    "Error pop up should be displayed.",
                    "Error pop up is displayed.",
                    "Error pop up is not displayed.");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see all the information in the change payment status modal$")
    public void i_should_see_all_the_information_in_the_change_payment_status_modal() throws Throwable {
        try{
        String currentStatus = action.getText(admin_TripsPage.Text_CurrentStatus());
        String newStatus = action.getText(admin_TripsPage.Text_NewStatus());
        testStepAssert.isEquals(currentStatus, "Payment Unsuccessful", "The current delivery status should be in Payment Unsuccessful state",
                "The current delivery status is in " + currentStatus + " state",
                "The current delivery status is not  in Payment Unsuccessful state");
        testStepAssert.isEquals(newStatus, "Payment Successful", "The new status text message should display the text  Payment Successful",
                "The new status text message displays the text  " + newStatus,
                "The new status text message doesnt display the text  Payment Successful");
        testStepAssert.isTrue(action.isElementPresent(admin_TripsPage.Button_ConfirmPaymentStatusChange()), "Confirm button should be displayed",
                "Confirm button is displayed",
                "Confirm button is not displayed");
        testStepAssert.isTrue(action.isElementPresent(admin_TripsPage.Button_CancelPaymentStatusChange()), "Cancel button should be displayed",
                "Cancel button is displayed",
                "Cancel button is not displayed");
    }    catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The \"([^\"]*)\" \"([^\"]*)\" should not be displayed$")
    public void the_something_something_should_not_be_displayed(String element, String strArg2) throws Throwable {
        try{
        switch (element){
            case "Issue Refund":
                testStepAssert.isNotElementDisplayed(admin_refundsPage.Button_IssueRefund(true),
                        "Issue refund button should not be displayed",
                        "Issue refund button is not displayed",
                        "Issue refund button is displayed");
                break;
            case "Testdrivertywd_appleks_a_drvbd Kansas_bd":
            case "Testdrivertywd_appleks_a_drvbc Kansas_bc":
                Thread.sleep(4000);
                testStepAssert.isNotElementDisplayed(admin_DriverPage.Text_DriverName(true),
                        "Driver Name should not be displayed",
                        "Driver Name is not displayed",
                        "Driver Name is displayed");
                break;
        }
    }    catch(Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I set the pickup address for \"([^\"]*)\"$")
    public void i_set_the_pickup_address_for_something(String address) throws Throwable {
        try {
            switch (address) {
                case "Warehouse":
                    cucumberContextManager.setScenarioContext("WarehouseCity", "Catonsville");
                    break;
                case "Store":
                    cucumberContextManager.setScenarioContext("StoreCity", "MD");
                    break;
                case "Equip-bid in phoenix geofence":
                    cucumberContextManager.setScenarioContext("PhoenixEquip-bid", "phoenix");
                    break;
            }
            log("I should be able to set the pickup address", "I could set the pickup address", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The delivery status should be in \"([^\"]*)\" state in db$")
    public void the_delivery_status_should_be_in_something_state_in_db(String deliveryStatusMessage) throws Throwable {
        try{
        switch (deliveryStatusMessage){
            case "Payment Successful":
                String pickupRef= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                String deliveryStatusInDb = dbUtility.getDeliveryStatus(pickupRef);
                testStepAssert.isTrue(deliveryStatusInDb.contentEquals("11"),"Delivery Should be in payment successfull state",
                        "Delivery is in payment successfull state",
                        "Delivery is not in payment successfull state");
                break;
        }
    }    catch(Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see the transaction charges \"([^\"]*)\" changing delivery Status$")
    public void i_should_see_the_transaction_charges_something_changing_delivery_status(String checkingPeriod) throws Throwable {
        try{
        switch (checkingPeriod){
            case "before":
                Thread.sleep(2000);
                cucumberContextManager.setScenarioContext("OriginalDeliveryChargeBeforeStatusChange",action.getText(admin_refundsPage.Label_OriginalDeliveryCharge()));
                cucumberContextManager.setScenarioContext("TotalCustomerChargeBeforeStatusChange", action.getText(admin_refundsPage.Label_TotalCustomerCharge()));
                cucumberContextManager.setScenarioContext("BungiiBeforeRefundBeforeStatusChange", action.getText(admin_refundsPage.Label_BungiiBeforeRefund()));
                break;
            case "after":
                Thread.sleep(3000);
                String originalDeliveryChargeBeforeStatusChange = (String) cucumberContextManager.getScenarioContext("OriginalDeliveryChargeBeforeStatusChange");
                String totalCustomerChargeBeforeStatusChange = (String) cucumberContextManager.getScenarioContext("TotalCustomerChargeBeforeStatusChange");
                String bungiiBeforeRefundBeforeStatusChange = (String) cucumberContextManager.getScenarioContext("BungiiBeforeRefundBeforeStatusChange");

                String originalDeliveryChargeAfterStatusChange =action.getText(admin_refundsPage.Label_OriginalDeliveryCharge());
                String totalCustomerChargeAfterStatusChange =action.getText(admin_refundsPage.Label_TotalCustomerCharge());
                String bungiiBeforeRefundAfterStatusChange =action.getText(admin_refundsPage.Label_BungiiBeforeRefund());

                testStepAssert.isFalse(originalDeliveryChargeAfterStatusChange.contentEquals(originalDeliveryChargeBeforeStatusChange),"Delivery charges should be present","Delivery charges is present","Delivery charges is not present");
                testStepAssert.isFalse(totalCustomerChargeAfterStatusChange.contentEquals(totalCustomerChargeBeforeStatusChange),"Total customer charges should be present","Total customer charges is present","Total customer charges is not present");
                testStepAssert.isFalse(bungiiBeforeRefundAfterStatusChange.contentEquals(bungiiBeforeRefundBeforeStatusChange),"Bungii before hand  charges should be present","Bungii before hand  charges is present","Bungii before hand  charges is not present");
                break;
        }
    }    catch(Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I search the delivery based on customer \"([^\"]*)\"$")
    public void i_search_the_delivery_based_on_customer_something(String text) throws Throwable {
        try {
            String customerFullName[] = cucumberContextManager.getScenarioContext("CUSTOMER").toString().split(" ");
            switch (text) {
                case "first name":
                    String onlyCustomerLastName = customerFullName[0];
                    action.clearSendKeys(adminTripsPage.TextBox_Search(), onlyCustomerLastName + Keys.ENTER);
                    break;
                case "first name with space in front and back":
                    String onlyCustomerLastNameWithSpace = " " + customerFullName[0] + " ";
                    action.clearSendKeys(adminTripsPage.TextBox_Search(), onlyCustomerLastNameWithSpace + Keys.ENTER);
                    break;
            }
            log("I should be able to search the delivery based on customers "+text,
                    "I could  search the delivery based on customers "+text,false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^The timezone should be \"([^\"]*)\" on \"([^\"]*)\" page$")
    public void the_timezone_should_be_something_on_something_page(String timezone, String page) throws Throwable{
        try{
            Thread.sleep(5000);
            String scheduledDate;
            switch (page){
                case "Scheduled Deliveries":
                    String initialRequestDate = action.getText(admin_ScheduledTripsPage.Text_InitialRequestDate());
                    scheduledDate = action.getText(admin_ScheduledTripsPage.Text_ScheduledDate());
                    testStepVerify.isTrue(initialRequestDate.contains(timezone),"Timezone should be "+ timezone, "Time zone is not "+ timezone);
                    testStepVerify.isTrue(scheduledDate.contains(timezone),"Timezone should be "+ timezone, "Time zone is not "+ timezone);
                    break;
                case "Live Deliveries":
                    scheduledDate = action.getText(admin_LiveTripsPage.Text_ScheduledDate());
                    testStepVerify.isTrue(scheduledDate.contains(timezone),"Timezone should be "+ timezone, "Time zone is not "+ timezone);
                    break;
                case "All Deliveries":
                    scheduledDate = action.getText(admin_TripsPage.Text_ScheduledDate());
                    testStepVerify.isTrue(scheduledDate.contains(timezone),"Timezone should be "+ timezone, "Time zone is not "+ timezone);
                    break;
            }
        }catch (Exception e){
             logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
             error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I check if miles are updated for \"([^\"]*)\" in \"([^\"]*)\"$")
    public void i_check_if_miles_are_updated_for_something_in_something(String edit, String stage) throws Throwable {
        try{
            String reference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

            switch (stage){
                case "enroute":
                    switch (edit){
                        case "drop-off":
                            String[] pickup1Locations = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                            String[] pickup2Locations = DbUtility.getLatAndLonPickupAndDropLocation(reference);

                            String[] dropLocation = new String[2];
                            dropLocation[0] = pickup1Locations[2];
                            dropLocation[1] = pickup1Locations[3];
                            String[] newPickupLocations = new String[2];
                            newPickupLocations[0] = pickup2Locations[0];
                            newPickupLocations[1] = pickup2Locations[1];

                            String expectedMiles = new GoogleMaps().getMilesWithLatLong(newPickupLocations, dropLocation);
                            String actualMiles= admin_ScheduledTripsPage.Text_DeliveryMiles().getText();
                            cucumberContextManager.setScenarioContext("MILES",actualMiles.substring(0,5));
                            testStepVerify.isEquals(actualMiles.substring(0,4), expectedMiles,
                                    "The miles displayed should be correct after admin edit.",
                                    "The miles displayed is incorrect after admin edit.");
                            break;
                    }
                    break;
                case "arrived":
                    switch (edit){
                        case "pick-up":
                            String[] newLocations = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                            String[] newPickupLocations = new String[2];
                            newPickupLocations[0] = newLocations[0];
                            newPickupLocations[1] = newLocations[1];
                            String[] oldPickupLocations = new String[2];
                            oldPickupLocations[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_PICKUP");
                            oldPickupLocations[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_PICKUP");
                            String newPickupToOldPickup = new GoogleMaps().getMilesWithLatLong(oldPickupLocations, newPickupLocations);

                            String[] oldDropLocation = new String[2];
                            oldDropLocation[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_DROPOFF");
                            oldDropLocation[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_DROPOFF");
                            String newPickupToOldDropOff = new GoogleMaps().getMilesWithLatLong(newPickupLocations, oldDropLocation);

                            float distance = Float.parseFloat(newPickupToOldPickup) + Float.parseFloat(newPickupToOldDropOff);
                            String actualMiles= admin_ScheduledTripsPage.Text_DeliveryMiles().getText();
                            cucumberContextManager.setScenarioContext("MILES",actualMiles.substring(0,5));
                            testStepVerify.isEquals(actualMiles.substring(0,4), String.valueOf(distance),
                                    "The miles displayed should be correct after admin edit.",
                                    "The miles displayed is incorrect after admin edit.");

                            break;
                        case "pick-up and drop-off":
//                            Distance formula: P1 to P2+ P2 to D2
                            String[] newLocation = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                            String[] newPickupLocation = new String[2];
                            newPickupLocation[0] = newLocation[0];
                            newPickupLocation[1] = newLocation[1];
                            String[] oldPickupLocation = new String[2];
                            oldPickupLocation[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_PICKUP");
                            oldPickupLocation[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_PICKUP");
                            String[] newDropLocation = new String[2];
                            newDropLocation[0] = newLocation[2];
                            newDropLocation[1] = newLocation[3];
                            String dist1 = new GoogleMaps().getMilesWithLatLong(oldPickupLocation, newPickupLocation);
                            String dist2 = new GoogleMaps().getMilesWithLatLong(newPickupLocation, newDropLocation);
                            float dist = Float.parseFloat(dist1) + Float.parseFloat(dist2);
                            String actualDist= admin_ScheduledTripsPage.Text_DeliveryMiles().getText();
                            cucumberContextManager.setScenarioContext("MILES",actualDist.substring(0,5));
                            testStepVerify.isEquals(actualDist.substring(0,4), String.valueOf(dist),
                                    "The miles displayed should be correct after admin edit.",
                                    "The miles displayed is incorrect after admin edit.");
                            break;
                    }
                    break;
                case "unloading":
                    switch (edit){
                        case "drop-off":
//                            Distance formula: P1 to D1+ D1 to D2
                            String[] newLocations = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                            String[] newDropOff = new String[2];
                            newDropOff[0] = newLocations[2];
                            newDropOff[1] = newLocations[3];
                            String[] oldPickupLocations = new String[2];
                            oldPickupLocations[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_PICKUP");
                            oldPickupLocations[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_PICKUP");
                            String[] oldDropLocation = new String[2];
                            oldDropLocation[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_DROPOFF");
                            oldDropLocation[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_DROPOFF");
                            String oldPickToDrop = new GoogleMaps().getMilesWithLatLong(oldPickupLocations, oldDropLocation);
                            String oldDropToNewDrop = new GoogleMaps().getMilesWithLatLong(oldDropLocation, newDropOff);
                            float distance = Float.parseFloat(oldPickToDrop) + Float.parseFloat(oldDropToNewDrop);
                            String actualMiles= admin_ScheduledTripsPage.Text_DeliveryMiles().getText();
                            cucumberContextManager.setScenarioContext("MILES",actualMiles.substring(0,5));
                            testStepVerify.isEquals(actualMiles.substring(0,4), String.valueOf(distance),
                                    "The miles displayed should be correct after admin edit.",
                                    "The miles displayed is incorrect after admin edit.");

                            break;
                    }
                    break;
                case "stack":
                    switch (edit){
                        case "drop-off":
                            String[] pickup1Locations = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                            String[] dropLocation = new String[2];
                            dropLocation[0] = pickup1Locations[2];
                            dropLocation[1] = pickup1Locations[3];
                            String[] newPickupLocations = new String[2];
                            newPickupLocations[0] = pickup1Locations[0];
                            newPickupLocations[1] = pickup1Locations[1];

                            String expectedMiles = new GoogleMaps().getMilesWithLatLong(newPickupLocations, dropLocation);
                            String actualMiles= admin_ScheduledTripsPage.Text_DeliveryMiles().getText();
                            cucumberContextManager.setScenarioContext("MILES",actualMiles.substring(0,5));
                            testStepVerify.isEquals(actualMiles.substring(0,4), expectedMiles,
                                    "The miles displayed should be correct after admin edit.",
                                    "The miles displayed is incorrect after admin edit.");
                            break;
                    }
                    break;
            }

            log("I should be able to see updated miles.","I am able to see updated miles.",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I check if correct \"([^\"]*)\" is displayed on delivery details$")
    public void i_check_if_correct_something_is_displayed_on_delivery_details(String costType) throws Throwable {
        try{
            Float miles= Float.parseFloat((String) cucumberContextManager.getScenarioContext("MILES"));
            String reference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String loadUnload = PropertyUtility.getDataProperties("partner.load.unload.time");
            String driverCut = PropertyUtility.getDataProperties("kansas.partner.driver.cut");
            float loadUnloadTime = Float.parseFloat(loadUnload)/60000;
            DecimalFormat df = new DecimalFormat("0.00");
            switch (costType){
                case "customer price-enroute":
                    String[] pickup1Locations = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                    String[] pickup2Locations = DbUtility.getLatAndLonPickupAndDropLocation(reference);

                    String[] dropLocation = new String[2];
                    dropLocation[0] = pickup1Locations[2];
                    dropLocation[1] = pickup1Locations[3];
                    String[] newPickupLocations = new String[2];
                    newPickupLocations[0] = pickup2Locations[0];
                    newPickupLocations[1] = pickup2Locations[1];

                    long[] timeToCoverDistance = new GoogleMaps().getDurationInTraffic(newPickupLocations, dropLocation);
                    logger.detail("timeToCoverDistance [google api call] "+timeToCoverDistance[0]+" and "+timeToCoverDistance[1]);

                    float time= Float.parseFloat(df.format(timeToCoverDistance[0]/60));
                    float cost= (miles*1+time*1)+loadUnloadTime;
                    String actualCost= admin_EditScheduledBungiiPage.Text_Estimated_Price().getText();
                    testStepVerify.isEquals(actualCost.substring(1,6), String.valueOf(cost),
                            "The estimated cost  displayed should be correct after admin edit.",
                            "The estimated cost displayed is incorrect after admin edit.");
                    float transFeeSolo= (float) Math.round((cost*0.029)+0.30);
                    float driverCutFee=Math.round(cost* Float.parseFloat(driverCut));
                    float driverEarning = driverCutFee-transFeeSolo;
                    String actualDriverEarning= admin_TripDetailsPage.Text_Driver_Est_Earnings_Customer_Delivery().getText();
                    testStepVerify.isEquals(actualDriverEarning.substring(1,6), String.valueOf(df.format(driverEarning)),
                            "The driver earnings  displayed should be correct after admin edit.",
                            "The driver earnings displayed is incorrect after admin edit.");
                    break;

                case "customer price-arrived":
//                    Time=P1 to P2 + P2 to D1
                    String[] newLocations = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                    String[] newPickupLocation = new String[2];
                    newPickupLocation[0] = newLocations[0];
                    newPickupLocation[1] = newLocations[1];
                    String[] oldPickupLocations = new String[2];
                    oldPickupLocations[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_PICKUP");
                    oldPickupLocations[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_PICKUP");
                    long[] timeToCoverDistance1  = new GoogleMaps().getDurationInTraffic(oldPickupLocations, newPickupLocation);

                    String[] oldDropLocation = new String[2];
                    oldDropLocation[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_DROPOFF");
                    oldDropLocation[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_DROPOFF");
                    long[] timeToCoverDistance2  = new GoogleMaps().getDurationInTraffic(newPickupLocation, oldDropLocation);
                    float time1=Float.parseFloat(df.format(timeToCoverDistance1[0]/60+timeToCoverDistance2[0]/60));
                    float cost1= (miles*1+time1*1)+loadUnloadTime;
                    String actualCost1= admin_EditScheduledBungiiPage.Text_Estimated_Price().getText();
                    testStepVerify.isEquals(actualCost1.substring(1), String.valueOf(cost1),
                            "The estimated cost  displayed should be correct after admin edit.",
                            "The estimated cost displayed is incorrect after admin edit.");
                    float transFeeSolo1= (float) Math.round((cost1*0.029)+0.30);
                    float driverCutFee1=Math.round(cost1* Float.parseFloat(driverCut));
                    float driverEarning1= driverCutFee1-transFeeSolo1;
                    String actualDriverEarning1= admin_TripDetailsPage.Text_Driver_Est_Earnings_Customer_Delivery().getText();
                    testStepVerify.isEquals(actualDriverEarning1.substring(1), String.valueOf(df.format(driverEarning1)),
                            "The driver earnings  displayed should be correct after admin edit.",
                            "The driver earnings displayed is incorrect after admin edit.");
                    break;

                case "customer price-unloading":
//                    Time=P1 to D1+ D1 to D2
                    String[] newLocations1 = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                    String[] newDropLocation = new String[2];
                    newDropLocation[0] = newLocations1[2];
                    newDropLocation[1] = newLocations1[3];
                    String[] oldPickupLocation = new String[2];
                    oldPickupLocation[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_PICKUP");
                    oldPickupLocation[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_PICKUP");
                    String[] oldDropLocations = new String[2];
                    oldDropLocations[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_DROPOFF");
                    oldDropLocations[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_DROPOFF");
                    long[] distance1  = new GoogleMaps().getDurationInTraffic(oldPickupLocation, oldDropLocations);
                    long[] distance2  = new GoogleMaps().getDurationInTraffic(oldDropLocations, newDropLocation);

                    float time2=Float.parseFloat(df.format(distance1[0]/60+distance2[0]/60));
                    float cost2= (miles*1+time2*1)+loadUnloadTime;
                    String actualCost2= admin_EditScheduledBungiiPage.Text_Estimated_Price().getText();
                    testStepVerify.isEquals(actualCost2.substring(1), String.valueOf(cost2),
                            "The estimated cost  displayed should be correct after admin edit.",
                            "The estimated cost displayed is incorrect after admin edit.");
                    float transFeeSolo2= (float) Math.round((cost2*0.029)+0.30);
                    float driverCutFee2=Math.round(cost2* Float.parseFloat(driverCut));
                    float driverEarning2= driverCutFee2-transFeeSolo2;
                    String actualDriverEarning2= admin_TripDetailsPage.Text_Driver_Est_Earnings_Customer_Delivery().getText();
                    testStepVerify.isEquals(actualDriverEarning2.substring(1), String.valueOf(df.format(driverEarning2)),
                            "The driver earnings  displayed should be correct after admin edit.",
                            "The driver earnings displayed is incorrect after admin edit.");
                    break;

                case "customer price-stack":
                    String[] pickupLocations = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                    String[] dropoffLocation = new String[2];
                    dropoffLocation[0] = pickupLocations[2];
                    dropoffLocation[1] = pickupLocations[3];
                    String[] pickupLocation = new String[2];
                    pickupLocation[0] = pickupLocations[0];
                    pickupLocation[1] = pickupLocations[1];
                    long[] time3  = new GoogleMaps().getDurationInTraffic(pickupLocation, dropoffLocation);

                    float cost3= (miles*1)+(time3[0]/60*1+loadUnloadTime);
                    String actualCost3= admin_EditScheduledBungiiPage.Text_Estimated_Price().getText();
                    testStepVerify.isEquals(actualCost3.substring(1), String.valueOf(cost3),
                            "The estimated cost  displayed should be correct after admin edit.",
                            "The estimated cost displayed is incorrect after admin edit.");
                    float transFeeSolo3= (float) Math.round((cost3*0.029)+0.30);
                    float driverCutFee3=Math.round(cost3* Float.parseFloat(driverCut));
                    float driverEarning3= driverCutFee3-transFeeSolo3;
                    String actualDriverEarning3= admin_TripDetailsPage.Text_Driver_Est_Earnings_Customer_Delivery().getText();
                    testStepVerify.isEquals(actualDriverEarning3.substring(1), String.valueOf(df.format(driverEarning3)),
                            "The driver earnings  displayed should be correct after admin edit.",
                            "The driver earnings displayed is incorrect after admin edit.");
                    break;

                case "customer price-duo":
                    String[] newLocation = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                    String[] newPickupLoc = new String[2];
                    newPickupLoc[0] = newLocation[0];
                    newPickupLoc[1] = newLocation[1];
                    String[] oldPickupLoc= new String[2];
                    oldPickupLoc[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_PICKUP");
                    oldPickupLoc[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_PICKUP");
                    String[] newDropLoc = new String[2];
                    newDropLoc[0] = newLocation[2];
                    newDropLoc[1] = newLocation[3];
                    long[] tim1 = new GoogleMaps().getDurationInTraffic(oldPickupLoc, newPickupLoc);
                    long[] tim2 = new GoogleMaps().getDurationInTraffic(newPickupLoc, newDropLoc);
                    float Cost= (miles*1)+(tim1[0]/60)+(tim2[0]/60);
                    float Cost1= Cost*2+2*loadUnloadTime;
                    String actuaCost= admin_EditScheduledBungiiPage.Text_Estimated_Price().getText();
                    testStepVerify.isEquals(actuaCost.substring(1), String.valueOf(Cost1),
                            "The estimated cost  displayed should be correct after admin edit.",
                            "The estimated cost displayed is incorrect after admin edit.");
                    float transFeeSolo4= (float) Math.round(((Cost+loadUnloadTime)*0.029)+0.30);
                    float driverCutFee4=Math.round((Cost+loadUnloadTime)* Float.parseFloat(driverCut));
                    float driverEarning4= driverCutFee4-transFeeSolo4;
                    String actualDriverEarning4= admin_TripDetailsPage.Text_Driver_Est_Earnings_Customer_Delivery().getText();
                    testStepVerify.isEquals(actualDriverEarning4.substring(1), String.valueOf(df.format(driverEarning4)),
                            "The driver earnings  displayed should be correct after admin edit.",
                            "The driver earnings displayed is incorrect after admin edit.");
                    break;
            }
            log("I should be able to see the correct estimated charge and driver charge.","I am able to see the correct estimated charge and driver charge.",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I get the old values of pickup and drop off$")
    public void i_get_the_old_values_of_pickup_and_drop_off() throws Throwable {
       try{
           String reference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
           String[] locations = DbUtility.getLatAndLonPickupAndDropLocation(reference);

           String[] oldPickupLocations = new String[2];
           oldPickupLocations[0] = locations[0];
           oldPickupLocations[1] = locations[1];
           String[] oldDropLocation = new String[2];
           oldDropLocation[0] = locations[2];
           oldDropLocation[1] = locations[3];

           cucumberContextManager.setScenarioContext("OLD_LAT_PICKUP",oldPickupLocations[0]);
           cucumberContextManager.setScenarioContext("OLD_LONG_PICKUP",oldPickupLocations[1]);
           cucumberContextManager.setScenarioContext("OLD_LAT_DROPOFF",oldDropLocation[0]);
           cucumberContextManager.setScenarioContext("OLD_LONG_DROPOFF",oldDropLocation[1]);

        log("I should be able to get old latitude and longitude values of addresses.","I am able to get old latitude and longitude values of addresses.",false);
       }
       catch(Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }
    @And("^I select the live trip for \"([^\"]*)\" customer$")
    public void i_select_the_live_trip_for_something_customer(String custName) throws Throwable {
        try {
            String pickupReference= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            action.clearSendKeys(admin_ScheduledTripsPage.Textbox_Search(),pickupReference);

            if(custName.equalsIgnoreCase("Ondemand"))
            {
                String pickupReferenceOndemand=(String) cucumberContextManager.getScenarioContext("ONDEMAND_PICKUP_ID");
                action.clearSendKeys(admin_ScheduledTripsPage.Textbox_Search(),pickupReferenceOndemand);
            }

            action.click(admin_ScheduledTripsPage.Button_Search());

            Thread.sleep(25000);

            action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
            action.click(admin_ScheduledTripsPage.List_ViewEdit());


            pass("I should able to open trip", "I viewed live delivery",
                    false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Problem in selecting Live delivery in admin portal for customer "+custName,
                    true);
        }
    }
    @And("^I select the live trip for \"([^\"]*)\" customer for delivery details$")
    public void i_select_the_live_trip_for_something_customer_for_delivery_details(String cust) throws Throwable {
        try {
            String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            action.sendKeys(admin_ScheduledTripsPage.Textbox_Search(), custName.substring(0, custName.indexOf(" ")));
            action.click(admin_ScheduledTripsPage.Button_Search());
            Thread.sleep(5000);
            if(cust.equalsIgnoreCase("Duo")){
                action.click(admin_ScheduledTripsPage.Link_DeliveryDetails());
                action.click(admin_ScheduledTripsPage.Dropdown_LiveDelivery_Details());
            }
            else {
                action.click(admin_ScheduledTripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/img", custName), PageBase.LocatorType.XPath));
                action.click(admin_ScheduledTripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/ul/li/*[contains(text(),'Delivery Details')]", custName), PageBase.LocatorType.XPath));
            }
            log("I should be able to open delivery details for the customer",
                    "I am able to open delivery details for the customer",false);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("^I \"([^\"]*)\" all the \"([^\"]*)\" checkboxes from the filter$")
    public void i_something_all_the_something_checkboxes_from_the_filter(String checkboxSelectOrUnselect, String element) throws Throwable {
       try{
        switch (checkboxSelectOrUnselect){
           case "Unselect":
               action.click(admin_TripsPage.Button_Filter());
               switch (element){

                   case "Equipment":
                       utility.clearEquipment();
                       break;
                   case "Vehicle Type":
                       utility.clearVehicleType();
                       break;
               }
               action.click(Page_Partner_Done.Button_Apply());
               break;
       }
       log("I should be able "+checkboxSelectOrUnselect+" all the "+element+" checkboxes from the filter",
               "I could "+checkboxSelectOrUnselect+" all the "+element+" checkboxes from the filter",
               false);
    }
        catch(Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I change the driver status to \"([^\"]*)\"$")
    public void i_change_the_driver_status_to_something(String driverStatus) throws Throwable {
        try{
        action.click(admin_GeofencePage.Dropdown_Status());
        switch (driverStatus){
            case "Inactive":
                action.selectElementByText(admin_GeofencePage.Dropdown_Status(),"Inactive");
                break;
            case "Suspended":
                action.selectElementByText(admin_GeofencePage.Dropdown_Status(),"Suspended");
                break;
        }
        action.click(admin_DriverPage.TextBox_DriverStatusChangeComment());
        action.clearSendKeys(admin_DriverPage.TextBox_DriverStatusChangeComment(),"For Testing");
        log("I should be able to change the driver status to "+driverStatus,"I could change the driver status to "+driverStatus,false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);

    }
    }

    @When("^I select a driver \"([^\"]*)\" whose status is \"([^\"]*)\"$")
    public void i_select_a_driver_something_whose_status_is_something(String driverPhone, String driverStatus) throws Throwable {
        try{
        Thread.sleep(9000);
        action.clearSendKeys(admin_Driverspage.Textbox_SearchCriteria(),driverPhone+ Keys.ENTER);
        Thread.sleep(3000);
        cucumberContextManager.setScenarioContext("DRIVER",action.getText(admin_DriverPage.Text_DriverName()));
        Thread.sleep(3000);
        String statusOfDriver = action.getText(admin_DriverPage.Text_DriverApplicationStatus());
        testStepAssert.isEquals(statusOfDriver,driverStatus,"The driver application should be "+driverStatus ,"The driver application is "+driverStatus,"The driver application is not "+driverStatus);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);

    }
    }

    @And("^Driver icon should be displayed on the map for \"([^\"]*)\"$")
    public void Driver_icon_should_be_displayed_on_the_map_for_something(String driver) throws Throwable {
        try{
        Thread.sleep(3000);
        testStepAssert.isElementDisplayed(admin_Driverspage.Icon_Driver1OnMap(),"Icon should be present on the map",
                        "Icon should be present on the map","Icon should be present on the map" );
        action.click(admin_Driverspage.Icon_Driver1OnMap());
        Thread.sleep(3000);
        log("I should be able to click on driver icon displayed on the map","I could click on the driver icon displayed on the map",false);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);

    }
        }

    @Then("^The driver having status \"([^\"]*)\" should not be present in active driver map$")
    public void the_driver_having_status_something_should_not_be_present_in_active_driver_map(String strArg1) throws Throwable {
        try{
        Thread.sleep(12000);
        String expectedDriversName = (String) cucumberContextManager.getScenarioContext("DRIVER");
        List <WebElement> allDrivers = admin_Driverspage.List_AllDriversInActiveMap();
        for(WebElement name:allDrivers){
            if(name.getText().contentEquals(expectedDriversName)) {
                testStepAssert.isFail("Driver " + name.getText() + " is present in the list of all drivers");
                break;
            }
        }
        testStepAssert.isTrue(true,"Driver "+expectedDriversName+" should not be  present in the list of all drivers","Driver "+expectedDriversName+" is not present in the list of all drivers",
                "Driver "+expectedDriversName+" is present in the list of all drivers");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);

    }
}


    @Then("^The drivers name \"([^\"]*)\" phone number \"([^\"]*)\" and vehicle type \"([^\"]*)\" should be displayed$")
    public void the_drivers_name_something_phone_number_something_and_vehicle_type_something_should_be_displayed(String driverName, String DriverPhone, String DriverVehicleType) throws Throwable {
     try{
      Thread.sleep(3000);
      String driverNameAlongWithNameText[] =  action.getText(admin_Driverspage.Text_DetailsPopupInformation(2)).trim().split(":");
      String name =driverNameAlongWithNameText[1].trim();
      String phoneNoWithAdditionalData []= action.getText(admin_Driverspage.Text_DetailsPopupInformation(3)).trim().split(":");
      String vehicleWithAdditionalData []=action.getText(admin_Driverspage.Text_DetailsPopupInformation(5)).trim().split(":");
      String phoneNo =  phoneNoWithAdditionalData[1].trim();
      String vehicle =  vehicleWithAdditionalData[1].trim();
        testStepAssert.isEquals(name.toLowerCase(),driverName.toLowerCase(),"The driver name should be "+driverName ,"The driver name is "+name,"The driver name is not "+driverName);
        testStepAssert.isEquals(phoneNo,DriverPhone,"The driver phone number should be "+DriverPhone ,"The driver phone number is "+phoneNo,"The driver phone number is not "+DriverPhone);
        testStepAssert.isEquals(vehicle.toLowerCase(),DriverVehicleType.toLowerCase(),"The driver application should be "+DriverVehicleType ,"The driver application is "+vehicle,"The driver application is not "+DriverVehicleType);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);

    }
    }


}
