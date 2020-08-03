package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.FileUtility;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.web.utilityfunctions.DbUtility.*;
import static com.bungii.web.utilityfunctions.DbUtility.getGeofenceAttributes;

public class Admin_GeofenceSteps extends DriverBase {

    Admin_GeofencePage admin_GeofencePage = new Admin_GeofencePage();

    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();
    Admin_CustomerPage admin_CustomerPage = new Admin_CustomerPage();
    Admin_DriversPage admin_DriversPage = new Admin_DriversPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();
    Admin_PartnersPage admin_PartnersPage = new Admin_PartnersPage();
    Admin_GeofencePage admin_geofencePage=new Admin_GeofencePage();
    Admin_PotentialPartnersPage admin_potentialPartnersPage = new Admin_PotentialPartnersPage();
    Admin_GeofenceAtrributesPage admin_geofenceAtrributesPage =  new Admin_GeofenceAtrributesPage();

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
                    case "Settings":
                        action.click((admin_GeofencePage.Button_Settings()));
                        break;
                }
            break;

            case "Geofence Settings":
                switch (button) {
                    case "Save":
                        action.click(admin_GeofencePage.Button_SaveGeofenceSettings());
                }
                break;
            case"GeofenceAttributes":
                switch(button){
                    case "Save":
                        action.click(admin_geofenceAtrributesPage.Button_Save());
                        break;
                    case "Cancel":
                        action.click(admin_geofenceAtrributesPage.Button_Cancel());
                        break;
                }
                break;

        }
        log("And I click on the "+button+" Button on "+screen ,
                "I have clicked on the "+button+" Button on " +screen, true);
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
                            case "Potential Partners" :
                                action.click(admin_PartnersPage.Menu_Partners());
                                action.click(admin_PartnersPage.Assign_Partners());
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

    @And("^I select \"([^\"]*)\" geofence$")
    public void i_select_something_geofence(String geofenceName) throws Throwable {
        try{
            switch (geofenceName) {
                case "Kansas":
                   // action.click(admin_potentialPartnersPage.Dropdown_Geofence());
                    action.selectElementByText(admin_potentialPartnersPage.Dropdown_Geofence(),"Kansas");
                    break;

                case "Goa":
                   // action.click(admin_potentialPartnersPage.Dropdown_Geofence());
                    action.selectElementByText(admin_potentialPartnersPage.Dropdown_Geofence(), "Goa");
                    break;

            }
        } catch (Throwable e) {
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

    @When("^I go to \"([^\"]*)\" page$")
    public void i_go_to_something_page(String screen) throws Throwable {
        testStepAssert.isElementDisplayed(admin_GeofencePage.Header_Attributes(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
    }

    @Then("^I verify that the default settings are displayed$")
    public void i_verify_that_the_default_settings_are_displayed() throws Throwable {
        String CustomerFAQLink = PropertyUtility.getDataProperties("customer.faq.link");
        String DriverFAQLink = PropertyUtility.getDataProperties("driver.faq.link");
        String MinTimeForDuoTrip = PropertyUtility.getDataProperties("Min.time.Duo.trip");
        String MinTimeForSoloTrip = PropertyUtility.getDataProperties("Min.time.Solo.trip");
        String MinTripCost=PropertyUtility.getDataProperties("Minimum.trip.cost");
        String SurveyEmailLink=PropertyUtility.getDataProperties("Survey.email.link");
        String TripCostPerMile=PropertyUtility.getDataProperties("Trip.cost.per.mile");
        String TripCostPerMinute=PropertyUtility.getDataProperties("Trip.cost.per.minute");

        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_CustomerFAQLink(), CustomerFAQLink, CustomerFAQLink+" should be displayed", CustomerFAQLink+" is displayed", CustomerFAQLink+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_DriverFAQLink(), DriverFAQLink ,DriverFAQLink+" should be displayed", DriverFAQLink+" is displayed", DriverFAQLink+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_MinTimeForDuoTrip(), MinTimeForDuoTrip,MinTimeForDuoTrip+" should be displayed", MinTimeForDuoTrip+" is displayed", MinTimeForDuoTrip+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_MinTimeForSoloTrip(), MinTimeForSoloTrip,MinTimeForSoloTrip+" should be displayed", MinTimeForSoloTrip+" is displayed", MinTimeForSoloTrip+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_MinTripCost(), MinTripCost,MinTripCost+" should be displayed", MinTripCost+" is displayed", MinTripCost+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_SurveyEmailLink(), SurveyEmailLink,SurveyEmailLink+" should be displayed", SurveyEmailLink+" is displayed", SurveyEmailLink+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_TripCostPerMile(), TripCostPerMile,TripCostPerMile+" should be displayed", TripCostPerMile+" is displayed", TripCostPerMile+" is not displayed");
        testStepAssert.isElementTextEquals(admin_GeofencePage.Label_TripCostPerMinute(), TripCostPerMinute,TripCostPerMinute+" should be displayed", TripCostPerMinute+" is displayed", TripCostPerMinute+" is not displayed");
    }

    @Then("^I cannot uncheck \"([^\"]*)\" for \"([^\"]*)\" settings when \"([^\"]*)\" is checked$")
    public void i_cannot_uncheck_something_for_something_settings_when_something_is_checked(String strArg1, String strArg2, String strArg3) throws Throwable {
        testStepAssert.isElementEnabled(admin_GeofencePage.Checkbox_Solo(),"Solo Checkbox should be enabled","Solo Checkbox is enabled","Solo Checkbox is not enabled");
    }

    @When("^I \"([^\"]*)\" option \"([^\"]*)\" for Scheduled trip$")
    public void i_something_option_something_for_scheduled_trip(String action1, String trip_type) throws Throwable {
        switch (trip_type){
            case "Duo":
            {
                switch (action1)
                {
                    case "check":
                        if(!admin_GeofencePage.Checkbox_Duo().isSelected())
                            action.click(admin_GeofencePage.Checkbox_Duo());
                        break;
                    case "uncheck":
                        if(admin_GeofencePage.Checkbox_Duo().isSelected())
                            action.click(admin_GeofencePage.Checkbox_Duo());
                        break;
                }
            }
            break;

            case "Solo":
            {
                switch (action1)
                {
                    case "check":
                        if(!admin_GeofencePage.Checkbox_Solo().isSelected())
                            action.click(admin_GeofencePage.Checkbox_Solo());
                        break;
                    case "uncheck":
                        if(admin_GeofencePage.Checkbox_Solo().isSelected())
                            action.click(admin_GeofencePage.Checkbox_Solo());
                        break;
                }
            }
            break;

            case "Ondemand":
            {
                switch(action1)
                {
                    case "uncheck":
                        if(admin_GeofencePage.Checkbox_OnDemand().isSelected())
                            action.click((admin_GeofencePage.Checkbox_OnDemand()));
                }
            }
            break;
        }
        log("I "+action1+" option "+trip_type+" for Scheduled trip" ,
                "I have "+action1+" option "+trip_type+" for Scheduled trip", true);
    }


    @Then("^I can deselect \"([^\"]*)\" option for Scheduled trip$")
    public void i_can_deselect_something_option_for_scheduled_trip(String strArg1)  {
        testStepAssert.isElementEnabled(admin_GeofencePage.Checkbox_Solo(),"Solo is enabled","Solo is enabled","Solo is disbled");
        action.click(admin_GeofencePage.Checkbox_Solo());
    }

    @When("^I check \"([^\"]*)\" option$")
    public void i_check_something_option(String strArg1) throws Throwable {
        action.click(admin_GeofencePage.Checkbox_Duo());
    }

    @Then("^The \"([^\"]*)\" gets selected automatically$")
    public void the_something_gets_selected_automatically(String strArg1) throws Throwable {
        testStepVerify.isElementSelected(admin_GeofencePage.Checkbox_Solo(),"Solo should be selected","Pass","Fail");
    }

    @When("^I click on the geofence \"([^\"]*)\"$")
    public void i_click_on_the_geofence_something(String GeofenceName) throws Throwable {
        String Xpath =String.format("//td[contains(text(),'%s')]/following-sibling::td[text()='%s']",GeofenceName,"Active");
        action.click( SetupManager.getDriver().findElement(By.xpath(Xpath)));
        log("I click on the geofence" ,
                "I have clicked on the geofence", true);

    }

    @When("^I uncheck both on demand and Scheduled for a geofence$")
    public void i_uncheck_both_on_demand_and_scheduled_for_a_geofence() throws Throwable {
        i_something_option_something_for_scheduled_trip("uncheck","Duo");
        i_something_option_something_for_scheduled_trip("uncheck","Solo");
        i_something_option_something_for_scheduled_trip("uncheck","Ondemand");
        log("I uncheck both on demand and Scheduled for a geofence" ,
                "I have unchecked both on demand and Scheduled for a geofence", true);
    }

    @Then("^The validation error message is displayed$")
    public void the_validation_error_message_is_displayed() throws Throwable {
          testStepAssert.isElementDisplayed(admin_GeofencePage.Label_SettingsError(),"Active geofence should allow either Scheduled or On demand trip. - message is displayed","Pass","fail");
    }

    @Then("^check if error message is displayed for \"([^\"]*)\"$")
    public void check_if_error_message_is_displayed_for_something(String bungiiType) throws Throwable {
        String duoTime= (String) cucumberContextManager.getScenarioContext("MIN_TIME_DUO");

        String soloTime= (String) cucumberContextManager.getScenarioContext("MIN_TIME_SOLO");


        int dbValFromTime= Integer.parseInt(PropertyUtility.getDataProperties("schedule.pickup.from.time"));
        int dbValToTime=Integer.parseInt(PropertyUtility.getDataProperties("schedule.pickup.to.time"));
        int dbValMaxProcessTime=Integer.parseInt(PropertyUtility.getDataProperties("schedule.pickup.max.processing.time"));
        try{
            switch(bungiiType){
                case "duo trip":
                    int duoTimeValue=Integer.parseInt(duoTime);
                    /*if(duoTimeValue <= dbValFromTime || duoTimeValue >= dbValToTime || duoTimeValue < dbValMaxProcessTime) {
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should be displayed.", "Validation message is displayed ->"+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText(),"Validation message is not displayed.");
                    }
                    else{
                        testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText());
                    }*/
                    if(duoTimeValue >= dbValFromTime && duoTimeValue <= dbValToTime) {
                        testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText());
                        //testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should be displayed.", "Validation message is displayed ->"+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText(),"Validation message is not displayed.");
                    }
                    else{
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should be displayed.", "Validation message is displayed ->"+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText(),"Validation message is not displayed.");
                        //testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForDuo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForDuo().getText());
                    }
                    break;

                case "solo trip":
                    int soloTimeValue=Integer.parseInt(soloTime);
                    /*if(soloTimeValue < dbValFromTime || soloTimeValue > dbValToTime  || soloTimeValue < dbValMaxProcessTime) {
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should be displayed.", "Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText(),"Validation message is not displayed.");
                    }
                    else{
                        testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText());
                    }*/
                    if(soloTimeValue >= dbValFromTime && soloTimeValue <= dbValToTime) {
                        testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText());
                        //testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should be displayed.", "Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText(),"Validation message is not displayed.");
                    }
                    else{
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should be displayed.", "Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText(),"Validation message is not displayed.");
                        //testStepAssert.isNotElementDisplayed(admin_GeofencePage.Text_ErrorScheduleTimeForSolo(),"Validation message should not be displayed.", "Validation message is not displayed.","Validation message is displayed -> "+ admin_GeofencePage.Text_ErrorScheduleTimeForSolo().getText());
                    }
                    break;
            }
            log("And I verified the time for  "+ bungiiType,
                    "And I have verified the time for  "+bungiiType,true);
        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^Enter value should get saved and error message is not displayed$")
    public void check_if_error_message_is_not_display_for_something() throws Throwable {
                        testStepAssert.isElementDisplayed(admin_GeofencePage.Button_Settings(),"Setting button should be displayed.", "Setting button is displayed ->"+ admin_GeofencePage.Button_Settings().getText(),"Setting button is not displayed.");

    }

    @And("^I change the value of \"([^\"]*)\" to \"([^\"]*)\" minutes$")
    public void i_change_the_value_of_something_to_something_minutes(String type, String timeValue) throws Throwable {
        try{
            switch(type){

                case "Minimum scheduled time for Duo trip":
                    action.clearSendKeys(admin_GeofencePage.TextBox_MinimumScheduledtimeforduo(), timeValue);
                    cucumberContextManager.setScenarioContext("MIN_TIME_DUO", timeValue);
                    break;

                case "Minimum scheduled time for Solo trip":
                    action.clearSendKeys(admin_GeofencePage.TextBox_MinimumScheduledtimeforsolo(), timeValue);
                    cucumberContextManager.setScenarioContext("MIN_TIME_SOLO", timeValue);
                    break;
            }
            log("And I enter the text "+timeValue,
                    "And I have entered the text "+timeValue,true);
        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("I load Geofence Attributes Page and Click on New Attributes button")
    public void i_load_geofence_attributes_page_and_click_on_new_attributes_button() throws Throwable {
        Thread.sleep(2000);
        String loadGeoFenceAttributesUrl = PropertyUtility.getDataProperties("qa.attributes.url").concat("/GetSecuredGeofenceAttributes");

        action.navigateTo(loadGeoFenceAttributesUrl);
        action.click(admin_geofenceAtrributesPage.Button_NewAttribute());
    }

    @Then("^the \"([^\"]*)\" message is displayed  in geofence popup")
    public void the_something_message_is_displayed_in_geofence_popup(String message) throws Throwable {
        testStepAssert.isEquals(action.getText(admin_geofenceAtrributesPage.Label_ErrorTextOnEmpty()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
    }

    @Then("the error message is displayed")
    public void the_error_message_is_displayed(String message) throws Throwable {
        testStepAssert.isEquals(action.getText(admin_geofenceAtrributesPage.Label_ErrorTextOnEmpty()), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
    }

    @When("I search by Name {string} in {string} page geofence")
    public void iSearchByNameInPageGeofence(String arg0, String arg1) throws Throwable {
        Thread.sleep(2000);
        String Name = (String) cucumberContextManager.getScenarioContext("GF_ATTR_LABEL");
        action.clearSendKeys(admin_geofenceAtrributesPage.TextBox_SearchCriteria(),Name + Keys.ENTER);
        log("I search by name in Geo fence New attributes page",
                "I searched by name in Geo fence New attributes page", true);
    }

//    @When("^I search by Name \"([^\"]*)\" in \"([^\"]*)\" page geofence$")
//    public void i_search_by_name_something_in_something_page_geofence(String strArg1, String strArg2) throws Throwable {
//        Thread.sleep(2000);
//        String Name = (String) cucumberContextManager.getScenarioContext("GF_ATTR_LABEL");
//        action.clearSendKeys(admin_geofenceAtrributesPage.TextBox_SearchCriteria(),Name + Keys.ENTER);
//        log("I search by name in Geo fence New attributes page",
//                "I searched by name in Geo fence New attributes page", true);
//    }

    @Then("The geofence Attributes gets saved successfully and it is displayed in the grid")
    public void the_geofence_attributes_gets_saved_successfully_and_it_is_displayed_in_the_grid() throws Throwable {

        String Key = (String) cucumberContextManager.getScenarioContext("GF_ATTR_KEY");
        String DefaultValue = (String) cucumberContextManager.getScenarioContext("GF_ATTR_DEFAULT_VALUE");
        String Description = (String) cucumberContextManager.getScenarioContext("GF_ATTR_DESCRIPTION");
        String Label = (String) cucumberContextManager.getScenarioContext("GF_ATTR_LABEL");
        boolean checkIfCountEquals = false;
        List getListOfGeoFence = getListOfGeoFenceIds();
        for(int i = 0; i < getListOfGeoFence.size(); i++)
        {
            HashMap<String, Object> tmpData = (HashMap<String, Object>) getListOfGeoFence.get(i);
            Set<String> key = tmpData.keySet();
            Iterator it = key.iterator();
            while (it.hasNext()) {
                String hmKey = (String)it.next();
                Integer hmData = (Integer) tmpData.get(hmKey);
                int getIfExists =  getGeofenceSettingsVersions(hmData);
                int getGeofenceSettings = getGeofenceSettings(getIfExists);
                int getAttributeTableCount = getGeofenceAttributes();
                if(getGeofenceSettings == getAttributeTableCount) {
                    checkIfCountEquals = true;
                }
                if(checkIfCountEquals) {
                    String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]",DefaultValue, Label);
                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Geofence attributes should be listed in grid", "Geofence attributes is listed in grid","Geofence attributes is not listed in grid");
                }
                System.out.println("Key: "+hmKey +" & Data: "+hmData);
                it.remove(); // avoids a ConcurrentModificationException
            }

        }
//        List getListOfGeoFence = fetchAllDataForGeoFence();
//        int getAttributeTableCount = getGeofenceAttributes();
//
//        for(int i=0;i<getListOfGeoFence.size();i++) {
//
//                if(getAttributeTableCount == getListOfGeoFence[i]) checkIfCountEquals = true;
//
//        }
//        for(int i=0;i<getListOfGeoFence.size();i++) {
//            int newGEoFenceSetting = getGeofenceSettingsVersions((Integer) getListOfGeoFence.get(i));
//
//            if(newGEoFenceSetting > 0) {
//                int getIfExists =  getGeofenceSettingsVersions(newGEoFenceSetting);
//                int getAttributeTableCount = getGeofenceAttributes();
//                if(getIfExists == getAttributeTableCount) {
//                    checkIfCountEquals = true;
//                }
//                if(checkIfCountEquals) {
//                    String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]",Label, Description);
//                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Geofence attributes should be listed in grid", "Geofence attributes is listed in grid","Geofence attributes is not listed in grid");
//                }
//            }
//        }
        String Xpath =String.format("//tr/td[contains(.,'%s')]",DefaultValue);
        cucumberContextManager.setScenarioContext("G_XPATH", Xpath );
        testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),"Geofence attributes should be listed in grid", "Geofence attributes is listed in grid","Geofence attributes is not listed in grid");
    }

    @And("I check the Searched result is displayed correctly")
    public void i_check_the_searched_result_is_displayed_correctly() throws Throwable {
        Thread.sleep(2000);
        String xpath = (String)cucumberContextManager.getScenarioContext("G_XPATH");
        testStepAssert.isElementDisplayed(action.getElementByXPath(xpath),xpath +"Element should be displayed",xpath+ "Element is displayed", xpath+ "Element is not displayed");
    }
}
