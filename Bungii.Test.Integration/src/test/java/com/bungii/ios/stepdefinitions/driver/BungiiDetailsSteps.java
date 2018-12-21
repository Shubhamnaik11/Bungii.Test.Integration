package com.bungii.ios.stepdefinitions.driver;


import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.BungiiDetailsPage;
import com.bungii.ios.stepdefinitions.customer.SignupSteps;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.*;

public class BungiiDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    ActionManager action = new ActionManager();
    private BungiiDetailsPage bungiiDetailsPage;

    public BungiiDetailsSteps(BungiiDetailsPage bungiiDetailsPage) {
        this.bungiiDetailsPage = bungiiDetailsPage;

    }

    @When("^I start selected Bungii$")
    public void i_start_selected_bungii() {
        try {
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
            int mininumWaitTime = Integer.parseInt(PropertyUtility.getProp("scheduled.min.start.time"));
            if (!bungiiTime.equalsIgnoreCase("NOW")) {
                Date bungiiDate = new SimpleDateFormat("MMM d, h:mm a").parse(bungiiTime);
                Date currentDate = new Date();
                bungiiDate.setYear(currentDate.getYear());//(Integer.parseInt(currentDate.getYear()));
                long duration = bungiiDate.getTime() - currentDate.getTime();

                long diffInMinutes;
                if (duration > 0) {
                    diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration) - mininumWaitTime;
                    diffInMinutes = diffInMinutes > 0 ? diffInMinutes : 0;
                } else {
                    diffInMinutes = 0;
                }
                action.hardWaitWithSwipeUp((int) diffInMinutes + 1);
                log("I wait for "+diffInMinutes+1+" Minutes for Bungii Start Time ", "I waited for "+diffInMinutes+1, true);
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
