package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.bungiiDriver.TripDetailsPage;
import com.bungii.common.core.PageBase;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

public class TripDetailsSteps {

ActionManager action = new ActionManager();
TripDetailsPage tripDetailsPage = new TripDetailsPage();

    @When("I tap on {string} on driver Trip details Page")
    public void iTapOnOnDriverTripDetailsPage(String arg0) throws InterruptedException {
    switch (arg0.toUpperCase()){
        case "ACCEPT":
            Thread.sleep(4000);
            action.click(tripDetailsPage.Button_Accept());
            Thread.sleep(2000);
            break;
    }
    }
}
