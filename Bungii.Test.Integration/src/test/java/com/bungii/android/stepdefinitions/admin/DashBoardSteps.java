package com.bungii.android.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.*;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class DashBoardSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(com.bungii.android.stepdefinitions.admin.DashBoardSteps.class);
    DashBoardPage dashBoardPage=new DashBoardPage();
    ActionManager action = new ActionManager();
    LiveTripsPage liveTripsPage=new LiveTripsPage();
    GeneralUtility utility = new GeneralUtility();

    @When("^I Select \"([^\"]*)\" from admin sidebar$")
    public void i_select_something_from_admin_sidebar(String option) {
        try {
            Thread.sleep(5000);
            switch (option.toLowerCase()) {
                case "scheduled trip":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/ScheduledTrips");
                   // action.click(dashBoardPage.Button_Trips());
                   // action.click(dashBoardPage.Button_ScheduledTrips());
                    break;
                case "live trips":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/Trips?isComplete=False");
                    //action.click(dashBoardPage.Button_Trips());
                    //action.click(dashBoardPage.Button_LiveTrips());

                    break;
                case "promo code":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/PromoCodes");
                    //action.click(dashBoardPage.Button_PromoCode());
                    //action.click(dashBoardPage.Link_StandardCodes());
                    break;
                case "referral source":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/ReferralSource");
                   // action.click(dashBoardPage.Button_Marketing());
                   // action.click(dashBoardPage.Button_ReferralSource());
                    break;
                case "customers":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/Customers");
                   // action.click(dashBoardPage.Button_Customers());
                    break;
                case "trips":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/Trips?isComplete=True");
                    //action.click(dashBoardPage.Button_Trips());
                    break;
                case "drivers":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","Admin/GetAllBungiiDrivers"));
                    break;
                case "geofence":
                    Thread.sleep(2000);
                    action.click(liveTripsPage.Menu_Geofences());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            Thread.sleep(5000);
            log("I should able to select "+option,"I Selected "+option+" on admin sidebar" ,true );
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I select \"([^\"]*)\" geofence$")
    public void i_select_something_geofence(String geofenceName) throws Throwable {
        try{
            switch (geofenceName) {
                case "Chicago":
                    action.click(liveTripsPage.Select_ChicagoGeofence());
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
                            action.click((liveTripsPage.Button_Settings()));
                            break;
                        case "Save":
                            action.click(liveTripsPage.Button_Save());
                            break;
                    }
                    break;

                case"GeofenceSettings":
                    switch(button){
                        case"Save":
                            action.click(liveTripsPage.Button_SaveGeofenceSettings());
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
    @And("^I set \"([^\"]*)\"$")
    public void i_set_something(String type) throws Throwable {
        try{
            switch (type){
                case "set referral code amount":
                    int referralAmount = Integer.parseInt(liveTripsPage.Input_ReferralAmount().getAttribute("value"));
                    int newReferralAmount=referralAmount+1;
                    action.clearSendKeys(liveTripsPage.Input_ReferralAmount(), String.valueOf(newReferralAmount));
                    cucumberContextManager.setScenarioContext("NEW_REFERRAL_AMT",newReferralAmount);
                    break;
                case "set no. of deliveries":
                    int noOfDeliveries = Integer.parseInt(liveTripsPage.Input_NoOfDeliveries().getAttribute("value"));
                    int changeNoOfDeliveries=noOfDeliveries+1;
                    action.clearSendKeys(liveTripsPage.Input_NoOfDeliveries(), String.valueOf(changeNoOfDeliveries));
                    cucumberContextManager.setScenarioContext("NEW_NUMBER_OF_DELIVERIES",changeNoOfDeliveries);
                    break;
            }
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
