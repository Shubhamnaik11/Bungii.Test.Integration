package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.*;
import com.bungii.web.pages.admin.*;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.driver.Driver_RegistrationPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.web.utilityfunctions.DbUtility.*;

public class Admin_TripsSteps extends DriverBase {
    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();
    Admin_CustomerPage admin_CustomerPage = new Admin_CustomerPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();
    Admin_EditScheduledBungiiPage admin_EditScheduledBungiiPage = new Admin_EditScheduledBungiiPage();

    Admin_BusinessUsersSteps admin_businessUsersSteps = new Admin_BusinessUsersSteps();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_TripsSteps.class);
    GeneralUtility utility = new GeneralUtility();

    @And("^I view the Customer list on the admin portal$")
    public void i_view_the_customer_list_on_the_admin_portal() throws Throwable {
        Thread.sleep(120000); //Wait for 2 minutes
        SetupManager.getDriver().navigate().refresh();
        //  action.click(admin_DashboardPage.Menu_Dashboard());
    }
    @And("^I view the Trips list on the admin portal$")
    public void i_view_the_trips_list_on_the_admin_portal() throws Throwable {
        action.click(admin_TripsPage.Menu_Trips());
        Thread.sleep(5000);
        SetupManager.getDriver().navigate().refresh();
        action.selectElementByText(admin_TripsPage.Dropdown_SearchForPeriod(), "The Beginning of Time");
        log("I view the Trips list on the admin portal",
                "I viewed the Trips list on the admin portal", true);

    }
    @And("^I view the Live Trips list on the admin portal$")
    public void i_view_the_live_trips_list_on_the_admin_portal() throws Throwable {
        action.click(admin_TripsPage.Menu_Trips());
        action.click(admin_LiveTripsPage.Menu_LiveTrips());

        //  SetupManager.getDriver().navigate().refresh();
        log("I view the Live Trips list on the admin portal",
                "I viewed the Live Trips list on the admin portal", true);
    }
    @And("^I view the Scheduled Trips list on the admin portal$")
    public void i_view_the_scheduled_trips_list_on_the_admin_portal() throws Throwable {
        action.click(admin_TripsPage.Menu_Trips());
        action.click(admin_ScheduledTripsPage.Menu_ScheduledTrips());
        action.selectElementByText(admin_ScheduledTripsPage.Dropdown_SearchForPeriod(), "Today");

        // SetupManager.getDriver().navigate().refresh();
        log("I view the Scheduled Trips list on the admin portal",
                "I viewed the Scheduled Trips list on the admin portal", true);
    }
    @Then("^I should be able to see the Trip Requested count incremented in Customers Grid$")
    public void i_should_be_able_to_see_the_trip_requested_count_incremented_in_customers_grid() throws Throwable {
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
        testStepAssert.isEquals(tripRequestedCount, String.valueOf(reqCount), "Newer trip should reflect in Requested count", "Newer trip is reflected in Requested count", "Newer trip is not reflected in Requested count");
        testStepAssert.isEquals(tripEstimatedCount, oldtripEstimatedCount, "Newer trip should reflect in Requested count", "Newer trip is reflected in Requested count", "Newer trip is not reflected in Requested count");
        cucumberContextManager.setScenarioContext("XPATH", XPath);
    }

    @And("^I note the Trip Requested count of Customer \"([^\"]*)\"$")
    public void i_note_the_trip_requested_count_of_customer_something(String customer) throws Throwable {
        String[] name = customer.split(" ");
        action.clearSendKeys(admin_DashboardPage.TextBox_SearchCustomer(), name[1] + Keys.ENTER);

        String XPath = String.format("//td[contains(.,\"%s\")]/following-sibling::td[3]", customer);
        String XPath2 = String.format("//td[contains(.,\"%s\")]/following-sibling::td[4]", customer);

        String tripRequestedCount = SetupManager.getDriver().findElement(By.xpath(XPath)).getText();
        String tripEstimatedCount = SetupManager.getDriver().findElement(By.xpath(XPath2)).getText();
        cucumberContextManager.setScenarioContext("TRIP_REQUESTEDCOUNT", tripRequestedCount);
        cucumberContextManager.setScenarioContext("TRIP_ESTIMATEDCOUNT", tripEstimatedCount);
        cucumberContextManager.setScenarioContext("CUSTOMER_NAME", customer);
    }

    @When("^I view the customer details page of Customer \"([^\"]*)\"$")
    public void i_view_the_customer_details_page_of_customer_something(String strArg1) throws Throwable {
        String xpath = (String) cucumberContextManager.getScenarioContext("XPATH");
        action.click(SetupManager.getDriver().findElement(By.xpath(xpath)));
    }
    @Then("^Trip should be listed in the grid$")
    public void trip_should_be_listed_in_the_grid() throws Throwable {
        String tripType = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        String status = "Processing Confirmation";
        String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER_NAME");
        action.selectElementByText(admin_CustomerPage.Dropdown_TimeFrame(), "The Beginning of Time");
        Thread.sleep(5000);
        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]", tripType, customer, status);
        testStepAssert.isElementDisplayed(action.getElementByXPath(XPath), "Trip should be displayed", "Trip is displayed", "Trip is not displayed");
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
        String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");

        String geofenceName = getGeofence(geofence);
        action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
        action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());

        cucumberContextManager.setScenarioContext("STATUS", status);
        String driver = driver1;
        if (tripType[0].equalsIgnoreCase("duo"))
            driver = driver1 + "," + driver2;
        if (status.equalsIgnoreCase("Scheduled") || status.equalsIgnoreCase("Searching Drivers")) {
            String xpath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[4]", tripType[0].toUpperCase(), customer);
            int retrycount = 10;

            boolean retry = true;
            while (retry == true && retrycount > 0) {
                try {
                    WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                    retry = false;
                } catch (Exception ex) {
                    SetupManager.getDriver().navigate().refresh();
                    action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
                    action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
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
            String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : StringUtils.capitalize(tripType[0]), driver, customer);
            int retrycount = 10;
            boolean retry = true;
            while (retry == true && retrycount > 0) {
                try {
                    WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                    retry = false;
                } catch (Exception ex) {
                    SetupManager.getDriver().navigate().refresh();
                    action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
                    action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
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

        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String status = dataMap.get("Status").trim();
            String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            String tripType[] = tripTypeAndCategory.split(" ");
            String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
            String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");

            String geofenceName = getGeofence(geofence);
            action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
            action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());

            cucumberContextManager.setScenarioContext("STATUS", status);
            String driver = driver1;
            if (tripType[0].equalsIgnoreCase("duo"))
                driver = driver1 + "," + driver2;
            if (status.equalsIgnoreCase("Scheduled") || status.equalsIgnoreCase("Searching Drivers") || status.equalsIgnoreCase("Driver Removed")) {
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
                        action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
                        action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
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
                String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[2]", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : StringUtils.capitalize(tripType[0]), driver, customer);

                int retrycount = 10;
                boolean retry = true;
                while (retry == true && retrycount > 0) {
                    try {
                        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                        retry = false;
                    } catch (Exception ex) {
                        SetupManager.getDriver().navigate().refresh();
                        action.selectElementByText(admin_LiveTripsPage.Dropdown_Geofence(), geofenceName);
                        action.click(admin_LiveTripsPage.Button_ApplyGeofenceFilter());
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

    @When("^I view the trip details$")
    public void i_view_the_trip_details() throws Throwable {

        String xpath = (String) cucumberContextManager.getScenarioContext("XPATH");
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


    }

    @And("^Click on \"([^\"]*)\" button$")
    public void click_on_something_button(String button) throws Throwable {

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
        }

    }

    @Then("^the Bungii details is displayed successfully$")
    public void the_bungii_details_is_displayed_successfully() throws Throwable {

        String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
        String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String status = (String) cucumberContextManager.getScenarioContext("STATUS");
        String scheduled_time = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
        String timezone = (String) cucumberContextManager.getScenarioContext("GEOFENCE");

        if(!scheduled_time.equalsIgnoreCase("NOW")) {
            TimeZone.setDefault(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofence()));
            DateFormat formatter = new SimpleDateFormat("MMM dd, h:mm a");
            formatter.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofence()));
            Date bungiiDate = formatter.parse(scheduled_time);
            Date inputdate = new SimpleDateFormat("MMM dd, hh:mm a z").parse(scheduled_time);
            String formattedDate = new SimpleDateFormat("MMM dd,  hh:mm:ss a z").format(inputdate);
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
        String xpath = String.format("option[text()='%s']", driver1);
        testStepAssert.isElementDisplayed(admin_TripDetailsPage.Dropdown_Drivers().findElement(By.xpath(xpath)), " Driver " + driver1 + " should be displayed", " Driver " + driver1 + " is displayed", " Driver " + driver1 + " is not displayed");


    }

    @When("^I click on \"([^\"]*)\" link beside scheduled bungii$")
    public void i_click_on_something_link_beside_scheduled_bungii(String link) throws Throwable {
        Thread.sleep(4000);
        action.click(SetupManager.getDriver().findElement(By.xpath((String)cucumberContextManager.getScenarioContext("XPATH")+"/parent::tr")).findElement(By.xpath("td/p[@id='btnEdit']")));
        log(" I click on Edit link besides the scheduled bungii",
                "I have clicked on Edit link besides the scheduled bungii", true);
    }

    @And("^I click on \"([^\"]*)\" radiobutton$")
    public void i_click_on_something_radiobutton(String radiobutton) throws Throwable {

        switch (radiobutton) {
            case "Cancel entire Bungii and notify driver(s)":
                action.click(admin_ScheduledTripsPage.RadioButton_CancelBungii());
                break;
            case "Remove driver(s) and re-search":
                action.click(admin_ScheduledTripsPage.RadioButton_RemoveDriver());
                break;
            case "Edit Trip Details":
                action.click(admin_EditScheduledBungiiPage.RadioButton_EditTripDetails());
                break;
        }
        log("I click on Remove driver(s) and re-search radio button",
                "I have clicked on Remove driver(s) and re-search radio button", true);
    }

    @And("^I enter cancellation fee and Comments$")
    public void i_enter_cancellation_fee_and_comments() throws Throwable {
        action.clearSendKeys(admin_ScheduledTripsPage.Textbox_CancellationFee(), "0");
        action.clearSendKeys(admin_ScheduledTripsPage.Textbox_CancellationComment(), "Cancelling");
        log("I enter cancellation fee amount and comments",
                "I have entered cancellation fee amount and comments", true);
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
    @Then("^Pickup should be unassigned from the driver$")
    public void pickup_should_be_unassigned_from_the_driver() throws Throwable {

    }

    @And("^I select the first driver$")
    public void i_select_the_first_driver() throws Throwable {
        //String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
        //action.click(admin_ScheduledTripsPage.Checkbox_driver(driver1));
        action.click(admin_ScheduledTripsPage.Checkbox_driver());
        log("I select the driver",
                "I selected the driver", true);
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
                TimeZone.setDefault(TimeZone.getTimeZone(utility.getTripTimezone((String) cucumberContextManager.getScenarioContext("GEOFENCE"))));
                Date date1 = calendar.getTime();
                pickupdate = new SimpleDateFormat("EEEE, MMMM d, yyyy hh:mm a z").format(date1).toString();

            } else {
                TimeZone.setDefault(TimeZone.getTimeZone(utility.getTripTimezone((String) cucumberContextManager.getScenarioContext("GEOFENCE"))));
                Date date = new SimpleDateFormat("MMM dd, hh:mm a z").parse(pickupdate);
                int year = Calendar.getInstance().get(Calendar.YEAR);
                date.setYear(date.getYear()+(year-date.getYear()));
                pickupdate = new SimpleDateFormat("EEEE, MMMM d, yyyy hh:mm a z").format(date).toString();
            }

        }
        String message = null;
        switch (emailSubject) {
            case "Bungii Delivery Pickup Scheduled":
                message = utility.getExpectedPartnerFirmScheduledEmailContent(pickupdate, customerName, customerPhone, customerEmail, driverName, driverPhone, driverLicencePlate, supportNumber, firmName);
                break;
            case "Bungii Delivery Pickup Updated":
                message = utility.getExpectedPartnerFirmUpdatedEmailContent(pickupdate, customerName, customerPhone, customerEmail, driverName, driverPhone, driverLicencePlate, supportNumber, firmName);
                break;
            case "Bungii Delivery Pickup Canceled":
                message = utility.getExpectedPartnerFirmCanceledEmailContent(customerName, customerPhone, customerEmail, driverName, supportNumber, firmName);
                break;
        }
        message= message.replaceAll(" ","");
        logger.detail("Email Body (Expected): "+message);
          testStepAssert.isEquals(emailBody, message,"Email "+ message+" content should match with Actual", "Email  "+emailBody+" content matches with Expected", "Email "+emailBody+"  content doesn't match with Expected");

    }

    @And("^Customer should receive \"([^\"]*)\" email$")
    public void customer_should_receive_something_email(String emailSubject) throws Throwable {
        String emailBody = utility.GetSpecificURLs(PropertyUtility.getEmailProperties("email.from.address"), PropertyUtility.getEmailProperties("email.client.id"), emailSubject);
       if(emailBody.equals("")){
           testStepAssert.isFail("Email "+ emailSubject +" with link is not received.");
       }
        action.navigateTo(emailBody);
        String url = action.getCurrentURL();
        String survey_link =  PropertyUtility.getDataProperties("washington.survey.email.link");
        testStepAssert.isTrue(url.contains(survey_link),"Survey Email link should be "+survey_link,"Survey email link is "+ survey_link,"Survey email link is "+ url);
    }

    @And("^I note the Pickupref of trip$")
    public void i_note_the_pickupref_of_trip() throws Throwable {

        String customerRef = (String) cucumberContextManager.getScenarioContext("CUSTOMER_REF");
        cucumberContextManager.setScenarioContext("PICKUP_REQUEST", new DbUtility().getLatestPickupRefOfCustomer(customerRef));

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
        action.click(admin_ScheduledTripsPage.Checkbox_NonControlDriver());
        action.click(admin_ScheduledTripsPage.Button_RemoveDrivers());
        log("I remove non control driver",
                "I have removed non control driver", true);
    }

    @Then("^The driver should get removed successfully$")
    public void the_driver_should_get_removed_successfully() throws Throwable {
        testStepAssert.isElementDisplayed(admin_ScheduledTripsPage.Label_DriverRemovalSuccessMessage(), "Driver(s) removed successfully", "Pass", "Fail");
        action.click((admin_ScheduledTripsPage.Button_Close()));
    }

    @When("^I search by client name \"([^\"]*)\"$")
    public void i_search_by_client_name_something(String searchString) throws Throwable {
        action.selectElementByText(admin_TripsPage.DropDown_SearchForPeriod(), "The Beginning of Time");
        action.sendKeys(admin_TripsPage.TextBox_Search(), searchString + Keys.ENTER);
        log("I search " + searchString + "Client Name",
                "I have on searched " + searchString + " Client Name", true);
    }

    @Then("^All the clients named \"([^\"]*)\" should be displayed on the trip list grid$")
    public void all_the_clients_named_something_should_be_displayed_on_the_trip_list_grid(String searchString) throws Throwable {
        Thread.sleep(4000);
        try {
            for (WebElement e : admin_TripsPage.Client_names()) {
                testStepAssert.isTrue(e.getText().contains(searchString), "Client Name contains " + searchString, "Client Name is " + e.getText());
            }
            action.clear(admin_TripsPage.TextBox_Search());


        } catch (StaleElementReferenceException e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        action.clear(admin_TripsPage.TextBox_Search());


    }

    @When("^I click on \"([^\"]*)\" icon on \"([^\"]*)\" Page$")
    public void i_click_on_something_icon_on_something_page(String icon, String page) throws Throwable {
        action.selectElementByText(admin_TripsPage.DropDown_SearchForPeriod(), "The Beginning of Time");
        action.clear(admin_TripsPage.TextBox_Search());
        switch (page) {
            case "Trips":
                switch (icon) {
                    case "Filter":
                        action.click(admin_TripsPage.Button_Filter());
                        break;
                }
                break;
        }
        log("I click on " + icon + " on " + page + " page",
                "I have clicked on " + icon + " on " + page + " page", true);
    }

    @Then("^All statuses except \"([^\"]*)\" are selected$")
    public void all_statuses_except_something_are_selected(String status) throws Throwable {
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterPaymentUnsuccessful().isSelected(), "Checkbox Status Payment Successful should be selected", "Checkbox Status Payment Successful is selected", "Checkbox Status Payment Successful is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterPaymentSuccessful().isSelected(), "Checkbox Status Payment Unsuccessful should be selected", "Checkbox Status Payment Unsuccessful is selected", "Checkbox Status Payment Unsuccessful is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterCustomerCancelled().isSelected(), "Checkbox Status Customer Cancelled should be selected", "Checkbox Status Customer Cancelled is selected", "Checkbox Status Customer Cancelled is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterDriverCancelled().isSelected(), "Checkbox Status Driver Cancelled should be selected", "Checkbox Status Driver Cancelled is selected", "Checkbox Status Driver Cancelled is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterAdminCancelled().isSelected(), "Checkbox Status Admin Cancelled should be selected", "Checkbox Status Admin Cancelled is selected", "Checkbox Status Admin Cancelled is NOT selected");
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
                    case ("Customer Cancelled"):
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterCustomerCancelled());
                        break;
                    case ("Driver Cancelled"):
                        action.click(admin_TripsPage.Button_Filter());
                        uncheck_all_statuses();
                        action.click(admin_TripsPage.CheckBox_FilterDriverCancelled());
                        break;
                    case "Admin Cancelled":
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
                    case "Driver(s) Not Found":
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

        }
        log("I select filter " +filter+" as " + value ,
                "I have selected filter " +filter+" as " + value, true);
    }

    @Then("^the triplist grid shows the results by type \"([^\"]*)\"$")
    public void the_triplist_grid_shows_the_results_by_type_something(String filter) throws Throwable {
        Thread.sleep(4000);
        if(SetupManager.getDriver().getPageSource().contains("No trips found.")) {
            testStepAssert.isTrue(true, "No trips found.", "No trips found.");
        }
        else{
            String xpath = null;
            List<WebElement> rowswithstatus = null;
            List<WebElement> rows = null;
            switch (filter) {
                case "Payment Unsuccessful Status":
                    xpath = String.format("//td[text()='Payment Pending']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Payment Successful Status":
                    xpath = String.format("//td[text()='Payment Successful']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Customer Cancelled Status":
                    xpath = String.format("//td[text()='Customer Canceled']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Driver Cancelled Status":
                    xpath = String.format("//td[text()='Driver Canceled']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Admin Cancelled Status":
                    xpath = String.format("//td[text()='Admin Canceled']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Pickup with Error Status":
                    xpath = String.format("//td[9][text()='Pickup with Error' | text() = 'Unable To Estimate']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Price Estimated Status":
                    xpath = String.format("//td[text()='Price Estimated']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Driver(s) Not Found Status":
                    xpath = String.format("//td[text()='Driver(s) Not Found']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Driver Not Arrived Status":
                    xpath = String.format("//td[text()='Driver Not Arrived']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Driver Removed Status":
                    xpath = String.format("//td[text()='Driver Removed']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Promoter Payment Pending Status":
                    xpath = String.format("//td[text()='Promoter Payment Pending']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Solo Type":
                    //xpath = String.format("//td[3][text()='Solo']");
                    xpath = String.format("//td[4][text()='Solo']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Duo Type":
                    //xpath = String.format("//td[3][text()='Duo']");
                    xpath = String.format("//td[4][text()='Duo']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "On-Demand Category":
                    //xpath = String.format("//td[4][text()='On-Demand']");
                    xpath = String.format("//td[5][text()='On-Demand']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
                case "Scheduled Category":
                    //xpath = String.format("//td[4][text()='Scheduled']");
                    xpath = String.format("//td[5][text()='Scheduled']");
                    rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                    rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                    testStepAssert.isEquals(String.valueOf(rows.size() - 1), String.valueOf(rowswithstatus.size()), filter + " records should be displayed", filter + " records is displayed", filter + " records is not displayed");
                    break;
            }
        }

    }
    @And("^I refresh the page$")
    public void i_refresh_the_page() throws Throwable {
       action.refreshPage();
    }

    @Then("^Tick mark should be displayed beside driver and scheduled date$")
    public void tick_mark_should_be_displayed_beside_driver_and_scheduled_date() throws Throwable {
      //  testStepAssert.isElementDisplayed(admin_EditScheduledBungiiPage.TickMarkDate());
          //      testStepAssert.isElementDisplayed(admin_EditScheduledBungiiPage.TickMarkDriver(""));

    }

    @Then("^\"([^\"]*)\" message should be displayed$")
    public void something_message_should_be_displayed(String message) throws Throwable {

        switch(message) {

            case "Your changes are good to be saved.":
            testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_VerifiedMessage(), message, message +" should be displayed", message +" is displayed",message +" is not displayed");
            break;
            case "Bungii Saved!":
                testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_SuccessMessage(), message, message +" should be displayed", message +" is displayed",message +" is not displayed");
                break;
            case "Pickup request is being processed. You may have to refresh the page.":
                testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_InfoMessage(), message,message +" should be displayed", message +" is displayed",message +" is not displayed");
                break;
            case "":
                testStepAssert.isElementTextEquals(admin_EditScheduledBungiiPage.Label_VerifyError(), message,message +" should be displayed", message +" is displayed",message +" is not displayed");
                break;
        }

    }

    @And("^I update the Scheduled date of the trip by 15 minutes$")
    public void i_update_the_scheduled_date_of_the_trip_by_15_minutes()  {
        String value = admin_EditScheduledBungiiPage.TimePicker_Time().getAttribute("value");
        LocalTime time= LocalTime.parse(value, DateTimeFormatter.ofPattern("hh:mm a"));
        value = time.plusMinutes(15).format(DateTimeFormatter.ofPattern("hh:mm a")).toString();
        action.click(admin_EditScheduledBungiiPage.List_TimeFrame(value));
    }

    @And("^I remove driver \"([^\"]*)\" and add the new driver \"([^\"]*)\"$")
    public void i_remove_driver_something_and_add_the_new_driver_something(String driverRemove, String driverAdd)  {
        action.click(admin_EditScheduledBungiiPage.Checkbox_Driver(driverRemove));
        action.click(admin_EditScheduledBungiiPage.Link_RemoveDriver());
        action.clearSendKeys(admin_EditScheduledBungiiPage.TextBox_DriverSearch(),driverAdd);
        action.click(admin_EditScheduledBungiiPage.List_DriverSearchResult(driverAdd));
    }

    @And("^I click on \"([^\"]*)\" button on Edit Scheduled bungii popup$")
    public void i_click_on_something_button_on_edit_scheduled_bungii_popup(String button) {

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
    }
}