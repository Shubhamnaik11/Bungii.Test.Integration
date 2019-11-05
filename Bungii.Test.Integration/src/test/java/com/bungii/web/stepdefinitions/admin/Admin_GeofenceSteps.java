package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
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
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_GeofenceSteps extends DriverBase {

    Admin_GeofencePage admin_GeofencePage = new Admin_GeofencePage();

    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();
    Admin_CustomerPage admin_CustomerPage = new Admin_CustomerPage();
    Admin_DriversPage admin_DriversPage = new Admin_DriversPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();
    Admin_PartnersPage admin_PartnersPage = new Admin_PartnersPage();


    private static LogUtility logger = new LogUtility(Admin_PromoCodesSteps.class);

    ActionManager action = new ActionManager();

    @When("^I click on the \"([^\"]*)\" Button on \"([^\"]*)\" Screen$")
    public void i_click_on_the_something_button_on_something_screen(String button, String screen) throws Throwable {

        switch(screen) {

            case "Geofence":
                switch(button) {
                    case "Save":
                        action.click(admin_GeofencePage.Button_Save());
                        break;
                    case "Cancel":
                        action.click(admin_GeofencePage.Button_Cancel());
                        break;
                }
            break;
        }

    }

    @When("^I click on the geofence name \"([^\"]*)\"$")
    public void i_click_on_the_geofence_name_something(String geofenceName) throws Throwable {
        String GeofenceName = (String) cucumberContextManager.getScenarioContext("GF_GEONAME");
        String Xpath =String.format("//tr/td[contains(.,'%s')]",GeofenceName);
        action.click( SetupManager.getDriver().findElement(By.xpath(Xpath)));
    }

    @When("^I edit the geofence \"([^\"]*)\"$")
    public void i_edit_the_geofence(String geofenceName) throws Throwable {
        action.click(admin_GeofencePage.Button_Edit());
    }

    @Then("^the geofence gets saved successfully and it is displayed in the \"([^\"]*)\" grid$")
    public void the_geofence_gets_saved_successfully_and_it_is_displayed_in_the_something_grid(String strArg1) throws Throwable {

        String Name = (String) cucumberContextManager.getScenarioContext("GF_GEONAME");
        String Timezone = (String) cucumberContextManager.getScenarioContext("GF_GEOTIMEZONE");
        String Status = (String) cucumberContextManager.getScenarioContext("GF_STATUS");

        String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]",Name,Status,Timezone);
        cucumberContextManager.setScenarioContext("XPATH", Xpath );
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Geofence should be listed in grid", "Geofence is listed in grid","Geofence is not listed in grid");

    }

    @Then("^Geofence data is populated correctly$")
    public void geofence_data_is_populated_correctly() throws Throwable {
        String Name = (String) cucumberContextManager.getScenarioContext("GF_GEONAME");
        String Timezone = (String) cucumberContextManager.getScenarioContext("GF_GEOTIMEZONE");
        String Status = (String) cucumberContextManager.getScenarioContext("GF_STATUS");
        String Primary = (String) cucumberContextManager.getScenarioContext("GF_PRIMARY");
        String Secondary = (String) cucumberContextManager.getScenarioContext("GF_SECONDARY");

        testStepAssert.isElementTextEquals(admin_GeofencePage.TextBox_Primary(),Primary,Primary + " should be displayed",Primary +" is displayed",Primary + " is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.TextBox_Secondary(),Secondary,Secondary + " should be displayed",Secondary +" is displayed",Secondary + " is not displayed");

        testStepAssert.isEquals(admin_GeofencePage.Label_GeoName(true).getAttribute("value"),Name,Name + " should be displayed",Name +" is displayed",Name + " is not displayed");

        testStepAssert.isElementDisplayed(admin_GeofencePage.Dropdown_Timezone().findElement(By.xpath(String.format("//option[@selected='selected' and text()='%s']",Timezone))),Timezone + " should be displayed",Timezone +" is displayed",Timezone + " is not displayed");
        testStepAssert.isElementDisplayed(admin_GeofencePage.Dropdown_Status().findElement(By.xpath(String.format("//option[@selected='selected' and text()='%s']",Status))),Status + " should be displayed",Status +" is displayed",Status + " is not displayed");

    }

    @Then("^the following timezones are listed in the \"([^\"]*)\" dropdown$")
    public void the_following_timezones_are_listed_in_the_something_dropdown(String dropdown, DataTable data) throws Throwable {

        try {
            List<Map<String, String>> DataList = data.asMaps();
            Long now = Instant.now().toEpochMilli();
            int i =0;

            switch (dropdown) {
                case "Geo-TimeZone" :
                    while (i < DataList.size()) {
                        String Timezone = DataList.get(i).get("Timezone");
                        action.selectElementByText(admin_GeofencePage.Dropdown_Timezone(), Timezone);
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Dropdown_Timezone().findElement(By.xpath(String.format("//option[text()='%s']",Timezone))),Timezone + " should be displayed",Timezone +" is displayed",Timezone + " is not displayed");
                        i++;
                    }
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^the following statuses are listed in the \"([^\"]*)\" dropdown$")
    public void the_following_statuses_are_listed_in_the_something_dropdown(String dropdown , DataTable data) throws Throwable {
        try {
            List<Map<String, String>> DataList = data.asMaps();
            Long now = Instant.now().toEpochMilli();
            int i =0;

            switch (dropdown) {
                case "Geo-Status" :
                    while (i < DataList.size()) {
                        String Status = DataList.get(i).get("Status");
                        action.selectElementByText(admin_GeofencePage.Dropdown_Status(), Status);
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Dropdown_Status().findElement(By.xpath(String.format("//option[text()='%s']",Status))),Status + " should be displayed",Status +" is displayed",Status + " is not displayed");
                        i++;
                    }
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I navigate to following pages one by one$")
    public void i_navigate_to_following_pages_one_by_one(DataTable data) throws Throwable {

        try {
            List<Map<String, String>> DataList = data.asMaps();
            Long now = Instant.now().toEpochMilli();
            int i =0;
                    while (i < DataList.size()) {
                        String Page = DataList.get(i).get("Page");
                        switch (Page) {
                            case "Dashboard" :
                                action.click(admin_DashboardPage.Menu_Dashboard().findElement(By.xpath("a")));
                                break;
                            case "Drivers" :
                                action.click(admin_DriversPage.Menu_Drivers());
                                break;
                            case "Customers" :
                                action.click(admin_CustomerPage.Menu_Customers());
                                break;
                            case "Trips" :
                                action.click(admin_TripsPage.Menu_Trips());
                                break;
                            case "Scheduled Trips" :
                                action.click(admin_TripsPage.Menu_Trips());
                                action.click(admin_ScheduledTripsPage.Menu_ScheduledTrips());
                                break;
                            case "Live Trips" :
                                action.click(admin_TripsPage.Menu_Trips());
                                action.click(admin_LiveTripsPage.Menu_LiveTrips());
                                break;
                            case "Partners" :
                                action.click(admin_PartnersPage.Menu_Partners());
                                break;

                        }

                        i_should_see_something_in_the_dropdown_on_the_something_page(Page);
                        i_should_not_see_something_in_the_dropdown_on_the_something_page(Page);
                        i++;
                    }


        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }




    }

    @Then("^I should see active zone in the dropdown on the \"([^\"]*)\" page$")
    public void i_should_see_something_in_the_dropdown_on_the_something_page(String page) throws Throwable {
        if(!page.equals("respective")) {

           String zone =  PropertyUtility.getDataProperties("active.geofence");
            action.selectElementByText(admin_DashboardPage.Dropdown_Geofence(), zone);
            testStepAssert.isElementDisplayed(admin_DashboardPage.Dropdown_Geofence().findElement(By.xpath(String.format("//option[text()='%s']", zone))), zone + " should be displayed", zone + " is displayed", zone + " is not displayed");
        }
    }

    @And("^I should not see inactive zone in the dropdown on the \"([^\"]*)\" page$")
    public void i_should_not_see_something_in_the_dropdown_on_the_something_page(String page) throws Throwable {
        if(!page.equals("respective")) {

            String zone =  PropertyUtility.getDataProperties("inactive.geofence");
            int i = 0;
            Boolean testStatus = false;
            List<WebElement> options = admin_DashboardPage.Dropdown_Geofence().findElements(By.tagName("option"));
               for (WebElement option : options)
               {
                   if(option.getText().equals(zone)) {
                       testStepAssert.isTrue( !option.getText().equals(zone), zone + " should not be displayed", zone + " is not displayed", zone + " is displayed");
                   }
               }
        }
    }
}
