package com.bungii.android.stepdefinitions.Customer;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.*;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.*;

public class BungiiDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(PromosSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility=new GeneralUtility();
    BungiiDetailsPage bungiiDetailsPage=new BungiiDetailsPage();
    /*public BungiiDetailsSteps(BungiiDetailsPage bungiiDetailsPage){
        this.bungiiDetailsPage=bungiiDetailsPage;
    }*/
    @Then("^I Cancel selected Bungii$")
    public void i_cancel_selected_bungii() {
        try {
            Thread.sleep(5000);
            action.scrollToBottom();
            action.click(bungiiDetailsPage.Button_CancelBungii());
            Thread.sleep(2000);
            action.click(bungiiDetailsPage.Button_CancelAccept());
            Thread.sleep(2000);
            action.click(bungiiDetailsPage.Button_Yes());

            pass("I should able to cancel bungii","I cancelled bungii",true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I wait for 1 hour for Bungii Schedule Time$")
    public void i_wait_for_one_hour_for_bungii_start_time() {
        try {
            String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
            int mininumWaitTime = 60;
            if (!bungiiTime.equalsIgnoreCase("NOW")) {
                String geofenceLabel=utility.getTimeZoneBasedOnGeofence().toUpperCase();

                DateFormat formatter = new SimpleDateFormat("MMM d, h:mm a");
                formatter.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()));
                Date bungiiDate = formatter.parse(bungiiTime);


                Date currentDate = new Date();
                bungiiDate.setYear(currentDate.getYear());//(Integer.parseInt(currentDate.getYear()));
                long duration = bungiiDate.getTime() - currentDate.getTime();

                long diffInMinutes;
                if (duration > 0) {
                    diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration) - mininumWaitTime;
                    //1 min extra buffer
                    diffInMinutes = diffInMinutes > 0 ? diffInMinutes+1 : 0;

                } else {
                    diffInMinutes = 1;
                }
                action.hardWaitWithSwipeUp((int)diffInMinutes);
                log("I wait for "+diffInMinutes+" Minutes for Bungii Start Time ", "I waited for "+diffInMinutes+" (with Extra buffer)", true);
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I try to contact driver using \"([^\"]*)\"$")
    public void i_select_something_option_for_something(String key) throws Throwable {
        try {
            switch (key.toLowerCase()) {
                case "call driver1":
                    action.click(bungiiDetailsPage.Button_Driver1Call());
                    break;
                case "sms driver1":
                    action.click(bungiiDetailsPage.Button_Driver1SMS());
                    break;
                case "call driver2":
                    action.click(bungiiDetailsPage.Button_Driver2Call());
                    break;
                case "sms driver2":
                    action.click(bungiiDetailsPage.Button_Driver2SMS());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("cusomer should able to click on"+key,"Customer clicked on "+key, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
