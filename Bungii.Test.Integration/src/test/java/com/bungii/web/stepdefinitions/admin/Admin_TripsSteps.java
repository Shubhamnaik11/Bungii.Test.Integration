package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.driver.Driver_RegistrationPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import static com.bungii.common.manager.ResultManager.log;

public class Admin_TripsSteps extends DriverBase {
    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();
    Admin_CustomerPage admin_CustomerPage = new Admin_CustomerPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();
Admin_ScheduledTripsPage admin_ScheduledTripsPage= new Admin_ScheduledTripsPage();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();
    ActionManager action = new ActionManager();

    @And("^I view the Customer list on the admin portal$")
    public void i_view_the_customer_list_on_the_admin_portal() throws Throwable {
        Thread.sleep(120000); //Wait for 2 minutes
        SetupManager.getDriver().navigate().refresh();
      //  action.click(admin_DashboardPage.Menu_Dashboard());
    }
    @And("^I view the Trips list on the admin portal$")
    public void i_view_the_trips_list_on_the_admin_portal() throws Throwable {
        action.click(admin_TripsPage.Menu_Trips());
        SetupManager.getDriver().navigate().refresh();
        action.selectElementByText(admin_TripsPage.Dropdown_SearchForPeriod(),"The Beginning of Time");
    }
    @And("^I view the Live Trips list on the admin portal$")
    public void i_view_the_live_trips_list_on_the_admin_portal() throws Throwable {
        action.click(admin_TripsPage.Menu_Trips());
        action.click(admin_LiveTripsPage.Menu_LiveTrips());

      //  SetupManager.getDriver().navigate().refresh();
    }
    @And("^I view the Scheduled Trips list on the admin portal$")
    public void i_view_the_scheduled_trips_list_on_the_admin_portal() throws Throwable {
        action.click(admin_TripsPage.Menu_Trips());
        action.click(admin_ScheduledTripsPage.Menu_ScheduledTrips());
        action.selectElementByText(admin_ScheduledTripsPage.Dropdown_SearchForPeriod(),"Today");

       // SetupManager.getDriver().navigate().refresh();
    }
    @Then("^I should be able to see the Trip Requested count incremented in Customers Grid$")
    public void i_should_be_able_to_see_the_trip_requested_count_incremented_in_customers_grid() throws Throwable {
        String [] name = cucumberContextManager.getScenarioContext("CUSTOMER_NAME").toString().split(" ");
        action.clearSendKeys(admin_CustomerPage.TextBox_SearchCustomer(),name[1]+Keys.ENTER);

        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[2]",cucumberContextManager.getScenarioContext("CUSTOMER_NAME"));
        String XPath2 = String.format("//td[contains(.,'%s')]/following-sibling::td[3]",cucumberContextManager.getScenarioContext("CUSTOMER_NAME"));

        String tripRequestedCount = SetupManager.getDriver().findElement(By.xpath(XPath)).getText();
        String tripEstimatedCount = SetupManager.getDriver().findElement(By.xpath(XPath2)).getText();
        String oldtripRequestedCount = (String)cucumberContextManager.getScenarioContext("TRIP_REQUESTEDCOUNT");
        String oldtripEstimatedCount = (String)cucumberContextManager.getScenarioContext("TRIP_ESTIMATEDCOUNT");

        int reqCount = Integer.parseInt(oldtripRequestedCount)+1;
        testStepAssert.isEquals(tripRequestedCount,String.valueOf(reqCount) ,"Newer trip should reflect in Requested count","Newer trip is reflected in Requested count", "Newer trip is not reflected in Requested count");
        testStepAssert.isEquals(tripEstimatedCount,oldtripEstimatedCount ,"Newer trip should reflect in Requested count","Newer trip is reflected in Requested count", "Newer trip is not reflected in Requested count");
        cucumberContextManager.setScenarioContext("XPATH",XPath);
    }

    @And("^I note the Trip Requested count of Customer \"([^\"]*)\"$")
    public void i_note_the_trip_requested_count_of_customer_something(String customer) throws Throwable {
        String [] name = customer.split(" ");
        action.clearSendKeys(admin_DashboardPage.TextBox_SearchCustomer(),name[1]+Keys.ENTER);

        String XPath = String.format("//td[contains(.,\"%s\")]/following-sibling::td[2]",customer);
        String XPath2 = String.format("//td[contains(.,\"%s\")]/following-sibling::td[3]",customer);

        String tripRequestedCount = SetupManager.getDriver().findElement(By.xpath(XPath)).getText();
        String tripEstimatedCount = SetupManager.getDriver().findElement(By.xpath(XPath2)).getText();
        cucumberContextManager.setScenarioContext("TRIP_REQUESTEDCOUNT",tripRequestedCount);
        cucumberContextManager.setScenarioContext("TRIP_ESTIMATEDCOUNT",tripEstimatedCount);
        cucumberContextManager.setScenarioContext("CUSTOMER_NAME",customer);
    }

    @When("^I view the customer details page of Customer \"([^\"]*)\"$")
    public void i_view_the_customer_details_page_of_customer_something(String strArg1) throws Throwable {
        String xpath = (String)cucumberContextManager.getScenarioContext("XPATH");
        action.click(SetupManager.getDriver().findElement(By.xpath(xpath)));
    }
    @Then("^Trip should be listed in the grid$")
    public void trip_should_be_listed_in_the_grid() throws Throwable {
        String tripType = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        String status = "Processing Confirmation";
        String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER_NAME");
        action.selectElementByText(admin_CustomerPage.Dropdown_TimeFrame(),"The Beginning of Time");

        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]",tripType,customer,status );
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(XPath)), "Trip should be displayed", "Trip is displayed","Trip is not displayed");
    }
    @Then("^I should be able to see the respective bungii with the below status$")
    public void i_should_be_able_to_see_the_respective_bungii_with_the_below_status(DataTable data) throws Throwable {

        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String status = dataMap.get("Status").trim();
        String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        String tripType[] = tripTypeAndCategory.split(" ");
        String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
        String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
        String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");

        String geofenceName = getGeofence(geofence);
        action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(),geofenceName);
        action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());

        cucumberContextManager.setScenarioContext("STATUS",status);
        String driver = driver1;
        if (tripType[0].equalsIgnoreCase("duo"))
            driver = driver1 + "," + driver2;
        if (status.equalsIgnoreCase("Scheduled") ||status.equalsIgnoreCase("Searching Drivers")) {
            String xpath= String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[4]", tripType[0].toUpperCase(), customer);
            int retrycount =10;

            boolean retry = true;
            while (retry == true && retrycount >0) {
                try {
                    WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                    retry = false;
                } catch (Exception ex) {
                    SetupManager.getDriver().navigate().refresh();
                    action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(),geofenceName);
                    action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
                    retrycount--;
                    retry = true;
                }

            }
            int retryCount = 1;
            while (!SetupManager.getDriver().findElement(By.xpath(xpath)).getText().equalsIgnoreCase(status)) {
                if (retryCount >= 20) break;
                Thread.sleep(15000); //Wait for 15 seconds
                retryCount++;
                SetupManager.getDriver().navigate().refresh();
            }
            cucumberContextManager.setScenarioContext("XPATH",xpath);
            testStepAssert.isElementTextEquals(SetupManager.getDriver().findElement(By.xpath(xpath)), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

        } else {
            String XPath= String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND")?"Solo":StringUtils.capitalize(tripType[0]), driver, customer);
            int retrycount =10;
            boolean retry = true;
            while (retry == true && retrycount >0) {
                try {
                    WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                    retry = false;
                } catch (Exception ex) {
                    SetupManager.getDriver().navigate().refresh();
                    action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(),geofenceName);
                    action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
                    retrycount--;
                    retry = true;
                }

            }
            int retryCount = 1;
            while (!SetupManager.getDriver().findElement(By.xpath(XPath)).getText().equalsIgnoreCase(status)) {
                if (retryCount >= 20) break;
                Thread.sleep(15000); //Wait for 15 seconds
                retryCount++;
                SetupManager.getDriver().navigate().refresh();
            }
            cucumberContextManager.setScenarioContext("XPATH",XPath);
            testStepAssert.isElementTextEquals(SetupManager.getDriver().findElement(By.xpath(XPath)), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
        }


    }
        @When("^I view the trip details$")
        public void i_view_the_trip_details() throws Throwable {

            String xpath=  (String)cucumberContextManager.getScenarioContext("XPATH");
            action.click(SetupManager.getDriver().findElement(By.xpath(xpath)));

        }

        @Then("^the amount is calculated and shown to admin$")
        public void the_amount_is_calculated_and_shown_to_admin() throws Throwable {

        }

        @And("^Enter the End Date and Time$")
        public void enter_the_end_date_time() throws Throwable {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY");


            String RequestTime = SetupManager.getDriver().findElement(By.xpath("//td[contains(text(),'Initial Request')]/following-sibling::td/strong")).getText();
            String[] splitedDate = RequestTime.split(" ");
            LocalDateTime now = LocalDateTime.now();
            String[] splitedTime = splitedDate[2].split(":");
            DecimalFormat formatter = new DecimalFormat("00");
            int minutes = Integer.parseInt(splitedTime[1])+20;
            int hours = Integer.parseInt(splitedTime[0]);
            if (minutes > 60) {
                hours = hours + 1;
                minutes = minutes -20;
            }


           // ZonedDateTime zonedNZ = ZonedDateTime.of(now,ZoneId.of("5:00"));


            action.clearSendKeys(admin_TripDetailsPage.Textbox_PickupEndDate(),dtf.format(now));
        action.clearSendKeys(admin_TripDetailsPage.Textbox_PickupEndTime(),formatter.format(hours)+":"+formatter.format(minutes));
        action.selectElementByText(admin_TripDetailsPage.Dropdown_ddlpickupEndTime(),splitedDate[3]);


        }

        @And("^Click on \"([^\"]*)\" button$")
        public void click_on_something_button(String button) throws Throwable {

        switch (button)
        {
            case "Calculate Cost":
                action.click(admin_TripDetailsPage.Button_CalculateCost());

                break;
            case "Confirm":
            action.click(admin_TripDetailsPage.Button_Confirm());
            break;
            case "Cancel":
                action.click(admin_TripDetailsPage.Button_Cancel());
                break;
        }

        }
    @Then("^the Bungii details is displayed successfully$")
    public void the_bungii_details_is_displayed_successfully() throws Throwable {

        String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
        String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String status = (String) cucumberContextManager.getScenarioContext("STATUS");
        String scheduled_time = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
        String timezone = (String) cucumberContextManager.getScenarioContext("BUNGII_TIMEZONE");

        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
        Date inputdate = new SimpleDateFormat("MMM d, hh:mm a z").parse(scheduled_time);
        String formattedDate = new SimpleDateFormat("MMM d,  hh:mm:ss a z").format(inputdate);
        String xpath_scheduled_time = "//td[contains(text(),'Scheduled Time')]/following-sibling::td/strong[text()='"+formattedDate+"']";

        String pickupLine = (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1") +" "+ (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2");
        String dropOffLine = (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")+" "+ (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2");
        pickupLine = pickupLine.replace(",","");
        dropOffLine =  dropOffLine.replace(",","");

        //Verify that the time the customer scheduled the trip for is added to Trip Details page
        testStepAssert.isElementDisplayed(admin_TripDetailsPage.Label_ScheduledTime(xpath_scheduled_time),"Bungii Scheduled Time should be displayed correctly","Pass","Fail");

        testStepAssert.isElementTextEquals(admin_TripDetailsPage.Label_TripDetails("Client"), customer, "Client " + customer + " should be updated", "Client " + customer + " is updated", "Client " + customer + " is not updated");
        testStepAssert.isTrue(admin_TripDetailsPage.Label_TripDetails("Pickup Location").getText().contains(pickupLine), "Pickup Location " + pickupLine + " should be updated", "Pickup Location " + pickupLine + " is updated", "Pickup Location " + pickupLine + " is not updated");
        testStepAssert.isTrue(admin_TripDetailsPage.Label_TripDetails("Drop Off Location").getText().contains(dropOffLine), "Drop Off Location " + dropOffLine + " should be updated", "Drop Off Location " + dropOffLine + " is updated", "Drop Off Location " + dropOffLine + " is not updated");
        testStepAssert.isElementTextEquals(admin_TripDetailsPage.Label_TripDetails("Status"), status, "Status " + status + " should be updated", "Status " + status + " is updated", "Status " + status + " is not updated");
       // testStepAssert.isElementTextEquals(admin_TripDetailsPage.Label_TripDetails("Trip Distance"), customer, "Trip Distance " + customer + " should be updated", "Trip Distance " + customer + " is updated", "Trip Distance " + customer + " is not updated");
       // testStepAssert.isElementTextEquals(admin_TripDetailsPage.Label_TripDetails("Loading + Unloading Time"), customer, "Loading + Unloading Time " + customer + " should be updated", "Loading + Unloading Time " + customer + " is updated", "Loading + Unloading Time " + customer + " is not updated");
        String xpath = String.format("option[text()='%s']",driver1);
        testStepAssert.isElementDisplayed(admin_TripDetailsPage.Dropdown_Drivers().findElement(By.xpath(xpath))," Driver "+ driver1+" should be displayed", " Driver "+ driver1+" is displayed", " Driver "+ driver1+" is not displayed");


    }

    @When("^I click on \"([^\"]*)\" link beside scheduled bungii$")
    public void i_click_on_something_link_beside_scheduled_bungii(String link) throws Throwable {

     action.click(SetupManager.getDriver().findElement(By.xpath((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr")).findElement(By.xpath("td/p[@id='btnEdit']")));

    }

    @And("^I click on \"([^\"]*)\" radiobutton$")
    public void i_click_on_something_radiobutton(String radiobutton) throws Throwable {

        switch (radiobutton)
        {
            case "Cancel entire Bungii and notify driver(s)":
        action.click(admin_ScheduledTripsPage.RadioButton_CancelBungii());
        break;
            case "Remove driver(s) and re-search":
                action.click(admin_ScheduledTripsPage.RadioButton_RemoveDriver());
                break;
        }
    }

    @And("^I enter cancellation fee and Comments$")
    public void i_enter_cancellation_fee_and_comments() throws Throwable {
        action.clearSendKeys(admin_ScheduledTripsPage.Textbox_CancellationFee(),"0");
        action.clearSendKeys(admin_ScheduledTripsPage.Textbox_CancellationComment(),"Cancelling");
    }


    @Then("^The \"([^\"]*)\" message should be displayed$")
    public void the_something_message_should_be_displayed(String message) throws Throwable {
        testStepAssert.isElementTextEquals(admin_ScheduledTripsPage.Label_CancelSuccessMessage(),message,message+" should be displayed",message+" is displayed",message+" is not displayed");
    }
    @Then("^Pickup should be unassigned from the driver$")
    public void pickup_should_be_unassigned_from_the_driver() throws Throwable {

    }

    @And("^I select the first driver$")
    public void i_select_the_first_driver() throws Throwable {
        //String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
        //action.click(admin_ScheduledTripsPage.Checkbox_driver(driver1));
        action.click(admin_ScheduledTripsPage.Checkbox_driver());
    }

    public String getGeofence(String geofence)
    {
        String geofenceName = "";
      switch(geofence) {
          case "washingtondc":
              geofenceName = "Washington DC";
                break;

      }
        return geofenceName;
    }


}
