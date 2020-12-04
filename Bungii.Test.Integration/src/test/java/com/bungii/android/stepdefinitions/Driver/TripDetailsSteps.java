package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.*;
import com.bungii.android.pages.driver.TripDetailsPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class TripDetailsSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(TripDetailsSteps.class);
    ActionManager action = new ActionManager();
    TripDetailsPage tripDetailsPage = new TripDetailsPage();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    GeneralUtility utility= new GeneralUtility();
    AvailableTripsPage availableTripsPage=new AvailableTripsPage();
    ScheduledBungiiPage scheduledBungiiPage=new ScheduledBungiiPage();

    @When("I tap on \"([^\"]*)\" on driver Trip details Page")
    public void iTapOnOnDriverTripDetailsPage(String arg0) throws InterruptedException {
        try {
            switch (arg0.toUpperCase()) {
                case "ACCEPT":
                    Thread.sleep(10000);
                    if(action.isNotificationAlertDisplayed()){
                            action.click(Page_BungiiRequest.AlertButton_Cancel());
                        }
                    action.click(tripDetailsPage.Button_Accept());
                    Thread.sleep(2000);
                    break;
                default:
                        error("UnImplemented Step or incorrect button name", "UnImplemented Step");break;
            }
            log("I tap on "+arg0+" from driver available trip","I tap on "+arg0+" from driver available trip");


        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should able to see \"([^\"]*)\" available trip$")
    public void i_should_able_to_see_something_available_trip(String strArg1) throws Throwable {
        List<WebElement> listOfBungii = availableTripsPage.Image_SelectBungiis();
        switch (strArg1) {
            case "two":
                testStepVerify.isTrue(listOfBungii.size() == 2, "There should be two available trip");
                break;
            case "zero":
                testStepVerify.isTrue(listOfBungii.size() == 0, "There should be two available trip");
                break;
            default:
                throw new Exception(" UNIMPLEMENTED STEP");
        }
    }

    @Then("^I should able to see \"([^\"]*)\" scheduled trip$")
    public void i_should_able_to_see_something_scheduled_trip(String strArg1) throws Throwable {
        List<WebElement> listOfScheduledTrip = scheduledBungiiPage.List_ScheduledBungiis();
        switch (strArg1) {
            case "two":
                testStepVerify.isTrue(listOfScheduledTrip.size() == 2, "There should be two scheduled trips","There is/are "+listOfScheduledTrip.size()+" Scheduled trips");
                break;
            case "one":
                testStepVerify.isTrue(listOfScheduledTrip.size() == 1, "There should be one scheduled trip", "There is/are "+listOfScheduledTrip.size()+" Scheduled trips");
                break;
            case "zero":
                testStepVerify.isTrue(listOfScheduledTrip.size() == 0, "There should be two scheduled trip", "There is/are "+listOfScheduledTrip.size()+" Scheduled trips");
                break;
            default:
                throw new Exception(" UNIMPLEMENTED STEP");
        }
    }
}
