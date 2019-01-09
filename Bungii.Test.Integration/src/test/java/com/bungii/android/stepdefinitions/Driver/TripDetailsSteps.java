package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.bungiiDriver.TripDetailsPage;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class TripDetailsSteps {

    private static LogUtility logger = new LogUtility(TripDetailsSteps.class);
    ActionManager action = new ActionManager();
    TripDetailsPage tripDetailsPage = new TripDetailsPage();

    @When("I tap on {string} on driver Trip details Page")
    public void iTapOnOnDriverTripDetailsPage(String arg0) throws InterruptedException {
        try {
            switch (arg0.toUpperCase()) {
                case "ACCEPT":
                    Thread.sleep(5000);
                    action.click(tripDetailsPage.Button_Accept());
                    Thread.sleep(2000);
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
