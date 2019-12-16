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
                testStepVerify.isEquals(data, PropertyUtility.getMessage("trip.alert.text"));
                 b=clickDriverMenu(time);
                testStepVerify.isEquals(b.toString(), "true");
                break;

            case "SMS Alerts":
                data=action.getText(tripAlertSettingsPage.Text_TripAndSMSAlertsText());
                testStepVerify.isEquals(data, PropertyUtility.getMessage("sms.alert.text"));
                 b=clickDriverMenu(time);
                testStepVerify.isEquals(b.toString(), "true");
                break;
        }
    }


    @And("^I click on time and change \"([^\"]*)\" time$")
    public void i_click_on_time_and_change_something_time(String strArg1) throws Throwable {

        action.click(tripAlertSettingsPage.Image_TimeSettingsArrow());
        action.click(tripAlertSettingsPage.Text_TimeSettingsFromTime());
        action.click(tripAlertSettingsPage.TimePicker_ChangeTime());

    }

    @And("^I click on \"([^\"]*)\" button$")
    public void i_click_on_something_button(String Name) throws Throwable {
        switch(Name)
        {
            case "SAVE TIME":
                action.click(tripAlertSettingsPage.TimePicker_OK());
                break;
        }
    }

    @Then("^I verify that changes in time are saved$")
    public void i_verify_that_changes_in_time_are_saved() throws Throwable {
        Boolean b=clickDriverMenu("08:00 - 21:00");
        testStepVerify.isEquals(b.toString(), "true");
    }

    public boolean checkTime(String time) {
        Boolean isClicked = false;
        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsTime();
        for (WebElement element : elements) {
            if (element.getText().equals(time)) {
                isClicked = true;
                break;
            }
        }
        return isClicked;
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
