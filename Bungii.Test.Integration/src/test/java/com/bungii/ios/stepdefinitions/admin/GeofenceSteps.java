package com.bungii.ios.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.DashBoardPage;
import com.bungii.ios.pages.admin.GeofencePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.*;

public class GeofenceSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    DashBoardPage dashBoardPage;
    ActionManager action = new ActionManager();
    GeofencePage geofencePage=new GeofencePage();

    @And("^I select \"([^\"]*)\" geofence$")
    public void i_select_something_geofence(String geofenceName) throws Throwable {
        try{
            switch (geofenceName) {
                case "Chicago":
                    action.click(geofencePage.Select_ChicagoGeofence());
                    break;

            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I activate \"([^\"]*)\" geofence$")
    public void i_activate_something_geofence(String geofenceName) throws Throwable {
        try{
            switch (geofenceName) {
                case "Chicago":
                    action.click(geofencePage.Button_Edit());
                    action.selectElementByText(geofencePage.Dropdown_Status(),"Active");
                    action.click(geofencePage.Button_Save());
                    break;

            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I click on the \"([^\"]*)\" Button on \"([^\"]*)\" Screen$")
    public void i_click_on_the_something_button_on_something_screen(String button,String screen)throws Throwable{
        try{
        switch(screen){

            case"Geofence":
                switch(button){
                    case"Settings":
                        action.click((geofencePage.Button_Settings()));
                        break;
                    case "Save":
                        action.click(geofencePage.Button_Save());
                        break;
                    case "Cancel":
                        action.click(geofencePage.Button_Cancel());
                        break;
                }
                break;

            case"GeofenceSettings":
                switch(button){
                    case"Save":
                        action.click(geofencePage.Button_SaveGeofenceSettings());
                        break;
                }
        }
        log("AndIclickonthe"+button+"Buttonon"+screen,
                "Ihaveclickedonthe"+button+"Buttonon"+screen,true);
    } catch (Throwable e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I change the value of \"([^\"]*)\" to \"([^\"]*)\" minutes$")
    public void i_change_the_value_of_something_to_something_minutes(String type, String timeValue) throws Throwable {
        try{
        switch(type){

            case "Minimum scheduled time for Duo trip":
                action.clearEnterText(geofencePage.TextBox_MinimumScheduledtimeforduo(), timeValue);
                cucumberContextManager.setScenarioContext("MIN_TIME_DUO", timeValue);
                break;

            case "Minimum scheduled time for Solo trip":
                action.clearEnterText(geofencePage.TextBox_MinimumScheduledtimeforsolo(), timeValue);
                cucumberContextManager.setScenarioContext("MIN_TIME_SOLO", timeValue);
                break;
        }
        log("And I enter the text "+timeValue,
                "And I have entered the text "+timeValue,true);
    } catch (Throwable e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
            }
    }

    @When("^I select \"([^\"]*)\" as \"([^\"]*)\"$")
    public void i_select_something_as_something1(String CodeType, String geoStatus) throws Throwable {
        try{
        action.selectElementByText(geofencePage.Dropdown_Status(),geoStatus);

        log("I select "+geoStatus+" in Geo-Status " ,
                "I have selected "+geoStatus+" in Geo-Status ", true);
        } catch (Throwable e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
         }
    }

    @Then("^check if error message is displayed for \"([^\"]*)\"$")
    public void check_if_error_message_is_displayed_for_something(String bungiiType) throws Throwable {
        String duoTime= (String) cucumberContextManager.getScenarioContext("MIN_TIME_DUO");
        int duoTimeValue=Integer.parseInt(duoTime);
        String soloTime= (String) cucumberContextManager.getScenarioContext("MIN_TIME_SOLO");
        int soloTimeValue=Integer.parseInt(duoTime);

        int dbValFromTime= Integer.parseInt(PropertyUtility.getDataProperties("schedule.pickup.from.time"));
        int dbValToTime=Integer.parseInt(PropertyUtility.getDataProperties("schedule.pickup.to.time"));
        int dbValMaxProcessTime=Integer.parseInt(PropertyUtility.getDataProperties("schedule.pickup.max.processing.time"));
        try{
            switch(bungiiType){
                case "duo trip":
                    if(duoTimeValue < dbValFromTime || duoTimeValue > dbValToTime || duoTimeValue < dbValMaxProcessTime) {
                        testStepAssert.isElementDisplayed(geofencePage.Text_ErrorScheduleTimeForDuo(),"Error message should be displayed.", "Error message is displayed.","Error message is not displayed.");
                    }
                    else{
                        testStepVerify.isElementNotDisplayed(geofencePage.Text_ErrorScheduleTimeForDuo(),"Error message should not be displayed.", "Error message is not displayed.","Error message is displayed.");
                    }
                    break;

                case "solo trip":
                    if(soloTimeValue < dbValFromTime || soloTimeValue > dbValToTime  || soloTimeValue < dbValMaxProcessTime) {
                        testStepAssert.isElementDisplayed(geofencePage.Text_ErrorScheduleTimeForSolo(),"Error message should be displayed.", "Error message is displayed.","Error message is not displayed.");
                    }
                    else{
                        testStepVerify.isElementNotDisplayed(geofencePage.Text_ErrorScheduleTimeForSolo(),"Error message should not be displayed.", "Error message is not displayed.","Error message is displayed.");
                    }
                    break;
            }
            log("And I verified the time for  "+bungiiType,
                    "And I have verified the time for  "+bungiiType,true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @When("^I edit the geofence \"([^\"]*)\"$")
    public void i_edit_the_geofence(String geofenceName) throws Throwable {
        try{
        action.click(geofencePage.Button_Edit());
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

}
