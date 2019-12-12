package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.TripAlertSettingsPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TripAlertSettingsMenuSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    TripAlertSettingsPage tripAlertSettingsPage= new TripAlertSettingsPage();

    @And("^I click on \"([^\"]*)\" tab$")
    public void i_click_on_something_tab(String option) throws Throwable {
        switch (option){
            case "Trip Alerts":
                action.click(tripAlertSettingsPage.Tab_TripAlerts());
                break;

            case "SMS Alerts":
                action.click(tripAlertSettingsPage.Tab_SMSAlerts());
                break;
        }

    }


    @Then("^I should be able to see \"([^\"]*)\" Text and Time$")
    public void i_should_be_able_to_see_something_text_and_time(String tab)  {
        String data=null;Boolean b;
        String time=PropertyUtility.getDataProperties("alert.time");
        switch (tab){
            case "Trip Alerts":
                data=action.getText(tripAlertSettingsPage.Text_TripAndSMSAlertsText());
                testStepVerify.isEquals(data, PropertyUtility.getDataProperties("trip.alert.text"));
                 b=clickDriverMenu(time);
                testStepVerify.isEquals(b.toString(), "true");
                break;

            case "SMS Alerts":
                data=action.getText(tripAlertSettingsPage.Text_TripAndSMSAlertsText());
                testStepVerify.isEquals(data, PropertyUtility.getDataProperties("sms.alert.text"));
                 b=clickDriverMenu(time);
                testStepVerify.isEquals(b.toString(), "true");
                break;
        }
    }

    public boolean clickDriverMenu(String time) {
        Boolean isClicked = false;
        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsTime();
        for (WebElement element : elements) {
            if (element.getText().equals(time)) {
                isClicked = true;
                 }
        }
        return isClicked;
    }
}
