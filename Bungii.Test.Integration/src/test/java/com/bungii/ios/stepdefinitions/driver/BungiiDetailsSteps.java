package com.bungii.ios.stepdefinitions.driver;


import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.BungiiDetailsPage;
import cucumber.api.java.en.When;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BungiiDetailsSteps extends DriverBase {
    ActionManager action = new ActionManager();
    private BungiiDetailsPage bungiiDetailsPage;

    public BungiiDetailsSteps(BungiiDetailsPage bungiiDetailsPage) {
        this.bungiiDetailsPage = bungiiDetailsPage;

    }

    @When("^I start selected Bungii$")
    public void i_start_selected_bungii() throws Throwable {
        action.click(bungiiDetailsPage.Button_StartBungii());
    }

    @When("^I wait for Minimum duration for Bungii Start Time$")
    public void i_wait_for_minimum_duration_for_bungii_start_time() throws Throwable {

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
        }
    }

}
