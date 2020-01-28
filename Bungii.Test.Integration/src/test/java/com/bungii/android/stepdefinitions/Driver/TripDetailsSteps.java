package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.*;
import com.bungii.android.pages.driver.TripDetailsPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class TripDetailsSteps {

    private static LogUtility logger = new LogUtility(TripDetailsSteps.class);
    ActionManager action = new ActionManager();
    TripDetailsPage tripDetailsPage = new TripDetailsPage();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    GeneralUtility utility= new GeneralUtility();
    @When("I tap on {string} on driver Trip details Page")
    public void iTapOnOnDriverTripDetailsPage(String arg0) throws InterruptedException {
        try {
            switch (arg0.toUpperCase()) {
                case "ACCEPT":
                    Thread.sleep(5000);
                    if(action.isNotificationAlertDisplayed()){
                            action.click(Page_BungiiRequest.AlertButton_Cancel());
                        }
                    action.click(tripDetailsPage.Button_Accept());
                    Thread.sleep(2000);
                    break;
                default:
                        error("UnImplemented Step or incorrect button name", "UnImplemented Step");break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
