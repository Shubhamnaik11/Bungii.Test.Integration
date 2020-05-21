package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_DriverVerificationPage;
import com.bungii.web.pages.admin.Admin_DriversPage;
import com.bungii.web.pages.admin.Admin_TripDetailsPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.log;

public class Admin_DriverDetails extends DriverBase{
    Admin_DriverVerificationPage admin_DriverVerificationPage = new Admin_DriverVerificationPage();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();
    Admin_DriversPage admin_Driverspage = new Admin_DriversPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();

    @When("^I search driver \"([^\"]*)\"$")
    public void i_search_driver_something(String driver) throws Throwable {
        action.clearSendKeys(admin_Driverspage.Textbox_SearchCriteria(),driver+ Keys.ENTER);
        cucumberContextManager.setScenarioContext("DRIVER",driver);
        log("I search driver " + driver,
                "I have searched driver " + driver, true);

    }

    @Then("^The \"([^\"]*)\" page should be displayed$")
    public void the_something_page_should_be_displayed(String page) throws Throwable {
        switch (page){
            case "Trip List":
                testStepAssert.isElementDisplayed(admin_Driverspage.Label_TripListHeader(),"Drivers Trip List page should be displayed","Drivers Trip List page is displayed", "Drivers Trip List page is not displayed");
                break;
        }
    }
    @And("^List of trips completed by the driver should be displayed on the Trip List Page$")
    public void list_of_trips_completed_by_the_driver_should_be_displayed_on_the_trip_list_page() throws Throwable {
        action.selectElementByText(admin_Driverspage.Dropdown_SearchForPeriod(), "The Beginning of Time");
        if(!action.getPagesource().contains("No trips found."))
        testStepAssert.isElementDisplayed(admin_Driverspage.Grid_TripList(),"Trip List grid should be displayed","Trip List grid is displayed", "Trip List grid is not displayed");
    }

    @Then("^The Trip List page should display the trip in \"([^\"]*)\" state$")
    public void the_trip_list_page_should_display_the_trip_in_something_state(String status) throws Throwable {
        String scheduled_time = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
        String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");
        String timezone = utility.getTripTimezone(geofence);
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
        String XPath = "";
        if (!scheduled_time.equalsIgnoreCase("NOW")) {
            Date inputdate = new SimpleDateFormat("MMM dd, hh:mm a z").parse(scheduled_time);
            inputdate.setYear(new Date().getYear());
            ZoneId zoneId = TimeZone.getDefault().toZoneId();
            if(TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date()))
            {
                if (timezone=="EST" || timezone=="CST")
                inputdate.setHours(inputdate.getHours()+1);
            }

            String formattedDate = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a z").format(inputdate);
            XPath = String.format("//td[text()='%s']/following-sibling::td[text()='%s']", formattedDate, status);
        }
        else
        {
            String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            String tripType[] = tripTypeAndCategory.split(" ");
            String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
            String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String driver = driver1;
            if (tripType[0].equalsIgnoreCase("duo"))
                driver = driver1 + "," + driver2;
            XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[2]", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : StringUtils.capitalize(tripType[0]), driver, customer);

        }

        int retrycount =10;
        boolean retry = true;
        while (retry == true && retrycount >0) {
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

        cucumberContextManager.setScenarioContext("XPATH",XPath);
        testStepAssert.isElementTextEquals(action.getElementByXPath(XPath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
    }

    @Then("^The Driver Trip List page should display the trip in \"([^\"]*)\" state$")
    public void the_driver_trip_list_page_should_display_the_trip_in_something_state(String status) throws Throwable {
        String scheduled_time = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
        String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");
        String timezone = utility.getTripTimezone(geofence);
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
        String XPath = "";
        if (!scheduled_time.equalsIgnoreCase("NOW")) {
            Date inputdate = new SimpleDateFormat("MMM dd, hh:mm a z").parse(scheduled_time);
            inputdate.setYear(new Date().getYear());
            if(TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date()))
            {
                if (timezone=="EST" || timezone=="CST")
                    inputdate.setHours(inputdate.getHours()+1);
            }
            String formattedDate = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a z").format(inputdate);
            XPath = String.format("//td[text()='%s']/following-sibling::td[text()='%s']", formattedDate, status);
        }
        else
        {
            String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            String tripType[] = tripTypeAndCategory.split(" ");
            String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
            String customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String driver = driver1;
            if (tripType[0].equalsIgnoreCase("duo"))
                driver = driver1 + "," + driver2;
            XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[2]", StringUtils.capitalize(tripType[0]).equalsIgnoreCase("ONDEMAND") ? "Solo" : StringUtils.capitalize(tripType[0]), driver, customer);

        }

        int retrycount =10;
        boolean retry = true;
        while (retry == true && retrycount >0) {
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

        cucumberContextManager.setScenarioContext("XPATH",XPath);
        testStepAssert.isElementTextEquals(action.getElementByXPath(XPath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
    }
}
