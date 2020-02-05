package com.bungii.ios.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
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
    public void i_select_something_geofence(String strArg1) throws Throwable {
        try{
        action.click(geofencePage.Select_ChicagoGeofence());
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

            case "Minimum scheduled time for Duo tr":
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

    @And("^I select pickup time$")
    public void i_select_pickup_time() throws Throwable {

    }

    @Then("^correct next available scheduled time should be displayed$")
    public void correct_next_available_scheduled_time_should_be_displayed() throws Throwable {

    }

}
