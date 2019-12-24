package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.BungiiDetailsPage;
import com.bungii.ios.stepdefinitions.customer.SignupSteps;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.*;

public class BungiiDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    ActionManager action = new ActionManager();
    private BungiiDetailsPage bungiiDetailsPage;
    GeneralUtility utility = new GeneralUtility();

    public BungiiDetailsSteps(BungiiDetailsPage bungiiDetailsPage) {
        this.bungiiDetailsPage = bungiiDetailsPage;

    }

    @When("^I start selected Bungii$")
    public void i_start_selected_bungii() {
        try {
            if(action.isAlertPresent())
                SetupManager.getDriver().switchTo().alert().accept();

            action.click(bungiiDetailsPage.Button_StartBungii());
            log("I start selected Bungii ", "I started selected Bungii", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I wait for Minimum duration for Bungii Start Time$")
    public void i_wait_for_minimum_duration_for_bungii_start_time() {
        try {
            String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
        //    bungiiTime="Aug 09, 12:45 AM CDT";
            int mininumWaitTime = Integer.parseInt(PropertyUtility.getProp("scheduled.min.start.time"));
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
    @And("^I wait for Minimum duration for \"([^\"]*)\" Bungii to be in Driver not accepted state$")
    public void i_wait_for_minimum_duration_for_something_bungii_to_be_in_driver_not_accepted_state(String strArg1) {
        try {
            long initialTime = (long) cucumberContextManager.getFeatureContextContext("BUNGII_INITIAL_SCH_TIME" + "_" + strArg1);
            long currentTime = System.currentTimeMillis() / 1000L;
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(currentTime - initialTime);
            if(diffInMinutes>30){
                //do nothing
            }else{
                // minimum wait of 30 mins
                action.hardWaitWithSwipeUp(30-(int) diffInMinutes);

            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
