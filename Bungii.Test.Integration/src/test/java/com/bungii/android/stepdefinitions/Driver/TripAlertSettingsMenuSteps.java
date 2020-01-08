package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.PromosPage;
import com.bungii.android.pages.customer.ScheduledBungiisPage;
import com.bungii.android.pages.driver.TripAlertSettingsPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.common.manager.ResultManager.error;

public class TripAlertSettingsMenuSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    TripAlertSettingsPage tripAlertSettingsPage= new TripAlertSettingsPage();
    ScheduledBungiisPage scheduledBungiisPage=new ScheduledBungiisPage();
    PromosPage promosPage=new PromosPage();

    @And("^I click on \"([^\"]*)\" tab$")
    public void i_click_on_something_tab(String option) throws Throwable {
        try {
            switch (option) {
                case "Trip Alerts":
                    action.click(tripAlertSettingsPage.Tab_TripAlerts());
                    break;

                case "SMS Alerts":
                    action.click(tripAlertSettingsPage.Tab_SMSAlerts());
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }


    @Then("^I should be able to see \"([^\"]*)\" Text and Time$")
    public void i_should_be_able_to_see_something_text_and_time(String tab)  {
        String data=null;Boolean b;
        String time=PropertyUtility.getDataProperties("alert.time");
        try {
            switch (tab) {
                case "Trip Alerts":
                    data = action.getText(tripAlertSettingsPage.Text_TripAndSMSAlertsText());
                    testStepVerify.isEquals(data.trim(), PropertyUtility.getMessage("trip.alert.text"));
                    b = clickDriverMenu(time);
                    testStepVerify.isEquals(b.toString(), "true");
                    break;

                case "SMS Alerts":
                    data = action.getText(tripAlertSettingsPage.Text_TripAndSMSAlertsText());
                    testStepVerify.isEquals(data.trim(), PropertyUtility.getMessage("sms.alert.text"));
                    b = clickDriverMenu(time);
                    testStepVerify.isEquals(b.toString(), "true");
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I click on time and change \"([^\"]*)\" time$")
    public void i_click_on_time_and_change_something_time(String strArg1) throws Throwable {
        try {
            action.click(tripAlertSettingsPage.Image_TimeSettingsArrow());
            action.click(tripAlertSettingsPage.Text_TimeSettingsFromTime());
            action.click(tripAlertSettingsPage.TimePicker_ChangeTime());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @And("^I click on \"([^\"]*)\" button$")
    public void i_click_on_something_button(String Name) throws Throwable {
        try {
            switch (Name) {
                case "SAVE TIME":
                    action.click(tripAlertSettingsPage.TimePicker_OK());
                    break;

                case "SAVE MONEY":
                    action.click(scheduledBungiisPage.Button_SaveMoney());
                    break;

                case "GET MORE MONEY":
                    action.click(promosPage.Button_GetMoreMoney());
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify that changes in time are saved$")
    public void i_verify_that_changes_in_time_are_saved() throws Throwable {
        try {
            Boolean b = checkTimeChange("08:00 - 21:00");
            testStepVerify.isEquals(b.toString(), "true");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
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

    public boolean checkTimeChange(String time) {
        Boolean isClicked = false;
        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsTime();
        for (WebElement element : elements) {
            if (element.getText().equals(time)) {
                isClicked = true;
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
