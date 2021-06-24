package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.EstimatePage;
import com.bungii.android.pages.driver.*;
import com.bungii.android.pages.driver.TripAlertSettingsPage;
import com.bungii.android.pages.driver.DriverHomePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class HomePageSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(HomePageSteps.class);
    ActionManager action = new ActionManager();
    DriverHomePage driverHomePage = new DriverHomePage();
    AccountsPage driverAccountPage = new AccountsPage();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    GeneralUtility utility = new GeneralUtility();
    TripAlertSettingsPage tripAlertSettingsPage = new TripAlertSettingsPage();
    EstimatePage estimatePage = new EstimatePage();

    @And("^I Select \"([^\"]*)\" from driver App menu$")
    public void i_select_something_from_driver_app_memu(String menuItem) {
        try {
            Thread.sleep(15000);
            if (action.isAlertPresent()) {
                if (action.getText(Page_BungiiRequest.Alert_Msg(true)).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))) {
                    utility.acceptNotificationAlert();
                    if (action.isAlertPresent()) {
                        if (action.isElementPresent(estimatePage.Button_OK(true)))
                            action.click(estimatePage.Button_OK());
                    }
                } else {
                    action.click(Page_BungiiRequest.AlertButton_Cancel());
                }

            }
            Thread.sleep(3000);
            boolean isClicked = false;
            if(action.isElementPresent(driverHomePage.Button_NavigationBar(true)))
            action.click(driverHomePage.Button_NavigationBar());
            else{
                if (action.isElementPresent(estimatePage.Alert_ConfirmRequestMessage(true))) {
                    action.click(estimatePage.Button_RequestConfirmCancel());
                    logger.detail("Push notification alert was shown on driver dashboard");
                }
                action.click(driverHomePage.Button_NavigationBar());
            }
            List<WebElement> elements = driverHomePage.Button_NavigationBarText();
            if (action.isAlertPresent()) {
                if (action.getText(Page_BungiiRequest.Alert_Msg(true)).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))) {
                    utility.acceptNotificationAlert();
                    if (action.isAlertPresent()) {
                        if (action.isElementPresent(estimatePage.Button_OK(true)))
                            action.click(estimatePage.Button_OK());
                    }
                } else {
                    action.click(Page_BungiiRequest.AlertButton_Cancel());
                }

            }
            for (WebElement element : elements) {
                if (element.getText().equalsIgnoreCase(menuItem)) {
                    action.click(element);
                    isClicked = true;
                    break;
                }
            }
            if (!isClicked) {
                action.scrollToBottom();
                isClicked = utility.clickDriverMenu(menuItem);
            }
            testStepAssert.isTrue(isClicked, "I should able to click " + menuItem, "Not able to select " + menuItem + " from App menu");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I Select \"([^\"]*)\" from ACCOUNT menu$")
    public void i_select_something_from_account_menu(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "ACCOUNT INFO":
                    action.click(driverAccountPage.Link_Account_Info());
                    break;
                case "ALERT SETTINGS":
                    action.click(driverAccountPage.Link_Account_Settings());
                    break;
                case "PRIVACY POLICY":
                    action.click(driverAccountPage.Link_Privarcy_Policy());
                    break;
                case "LOGOUT":
                    action.click(driverAccountPage.Link_Logout());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I should be navigated to Home screen on driver app$")
    public void i_should_be_navigated_to_home_screen_on_driver_app() throws Throwable {
        try {
            Thread.sleep(2000);
            action.waitUntilIsElementExistsAndDisplayed(driverHomePage.Generic_HeaderElement(true));
            String getNaviagationText = action.getText(driverHomePage.Generic_HeaderElement());
            boolean isHomePage = getNaviagationText.equals("OFFLINE") || getNaviagationText.equals("ONLINE");
            testStepAssert.isTrue(isHomePage, "I should be navigated to Driver home page", "I am not navigated to home page, Title is" + getNaviagationText);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I click \"([^\"]*)\" button on Home screen on driver app$")
    public void i_click_something_button_on_home_screen_on_driver_app(String button) throws Throwable {
        try {
            switch (button) {
                case "Go Online":
                    Thread.sleep(4000);
                    action.click(driverHomePage.Button_OnlineOffline());
                    Thread.sleep(4000);
                    break;
                case "Go Offline":
                    Thread.sleep(4000);
                    action.click(driverHomePage.Button_OnlineOffline());
                    break;
                case "Available Bungiis":
                    action.click(driverHomePage.Link_AvailableTrips());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I should able to click"+button,"I was able to click "+button,true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^the status of the driver should be \"([^\"]*)\"$")
    public void the_status_of_the_driver_should_be_something(String status) throws Throwable {
        try {
            switch (status.toUpperCase()) {
                case "OFFLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Generic_HeaderElement()), PropertyUtility.getMessage("driver.home.title.offline"));
                    testStepVerify.isEquals(action.getText(driverHomePage.Button_OnlineOffline()), PropertyUtility.getMessage("driver.home.goonline"));
                    break;
                case "ONLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Generic_HeaderElement()), PropertyUtility.getMessage("driver.home.title.online"));
                    testStepVerify.isEquals(action.getText(driverHomePage.Button_OnlineOffline()), PropertyUtility.getMessage("driver.home.gooffline"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^The \"([^\"]*)\" for \"([^\"]*)\" driver should be correctly displayed$")
    public void the_something_for_something_driver_should_be_correctly_displayed(String info, String driver) throws Throwable {
        try {
            String driverPhoneNumber, driverName, driverVehicle;
            if (driver.toLowerCase().equals("valid")) {
                driverPhoneNumber = PropertyUtility.getDataProperties("valid.driver.phone");
                driverName = PropertyUtility.getDataProperties("valid.driver.name");
                driverVehicle = PropertyUtility.getDataProperties("android.driver.vehicle");
            } else {
                driverPhoneNumber = PropertyUtility.getDataProperties("ios.valid.driver2.phone");
                driverName = PropertyUtility.getDataProperties("ios.driver2.name");
                driverVehicle = PropertyUtility.getDataProperties("ios.driver2.vehicle");
            }

            switch (info.toLowerCase()) {
                case "name":
                    testStepVerify.isEquals(action.getText(driverHomePage.Text_DriverName()), driverName);
                    break;
                case "vehicle info":
                    testStepVerify.isEquals(action.getText(driverHomePage.Text_DriverInfo()), driverVehicle);
                    break;
                case "rating":
                    testStepVerify.isElementEnabled(driverHomePage.Text_RattingBar(), "Ratting bar Should be correctly displayed");
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");

            }
        } catch (
                Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^The title of button should change to \"([^\"]*)\" on driver app$")
    public void the_title_of_button_should_change_to_something_on_driver_app(String buttonTitle) throws Throwable {
        try {
            switch (buttonTitle.toUpperCase()) {
                case "GO ONLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Button_OnlineOffline()), buttonTitle.toUpperCase());
                    break;
                case "GO OFFLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Button_OnlineOffline()), buttonTitle.toUpperCase());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");


            }

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^Info text should be updated$")
    public void info_text_should_be_updated() throws Throwable {
        try {
            testStepVerify.isEquals(action.getText(driverHomePage.Text_DriverName()), PropertyUtility.getMessage("DriverStatusInfo"));
            testStepVerify.isEquals(action.getText(driverHomePage.Text_DriverInfo()), PropertyUtility.getMessage("DriverInfo.android"));
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^The navigation title should change to \"([^\"]*)\"$")
    public void the_navigation_title_should_change_to_something(String navTitle) throws Throwable {
        try {

            switch (navTitle.toUpperCase()) {
                case "ONLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Generic_HeaderElement()), PropertyUtility.getMessage("driver.home.title.online"));
                    break;
                case "OFFLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Generic_HeaderElement()), PropertyUtility.getMessage("driver.home.title.offline"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I update sms setting of \"([^\"]*)\" to \"([^\"]*)\" to \"([^\"]*)\"$")
    public void i_update_sms_setting_of_sunday_to_something_to_something(String strArg0, String strArg1, String strArg2) {
        try {
            switch (strArg0.toUpperCase()) {
                case "SUNDAY":
                    action.click(tripAlertSettingsPage.Text_Sunday());
                    break;
                case "TODAY":

                    String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat  simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
                    simpleDateformat.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
                    String dayOfWeek=simpleDateformat.format(calendar.getTime());
                    WebElement element=SetupManager.getDriver().findElement(By.xpath("//*[@resource-id='com.bungii.driver:id/text_settings_row_text_day' and @text='"+dayOfWeek+"']"));
                    action.click(element);
                    break;
                default:
                    String from = (strArg1.split(":")[0]);
                    from = from.startsWith("0") ? from.substring(1) : from;
                    String test=((strArg1.split(" ")[0]).split(":")[1]).trim();
                    String test1=(strArg1.split(" ")[1]).trim();

                    String toHour = (strArg2.split(":")[0]);
                    toHour = toHour.startsWith("0") ? toHour.substring(1) : toHour;
                    String tohour1=((strArg2.split(" ")[0]).split(":")[1]).trim();
                    String tohour2=(strArg2.split(" ")[1]).trim();

                    WebElement element1=SetupManager.getDriver().findElement(By.xpath("//*[@resource-id='com.bungii.driver:id/text_settings_row_text_day' and @text='"+strArg0+"']"));
                    action.click(element1);
                    break;
            }
            String from = (strArg1.split(":")[0]);
            from = from.startsWith("0") ? from.substring(1) : from;
            String test=((strArg1.split(" ")[0]).split(":")[1]).trim();
            String test1=(strArg1.split(" ")[1]).trim();

            String toHour = (strArg2.split(":")[0]);
            toHour = toHour.startsWith("0") ? toHour.substring(1) : toHour;
            String tohour1=((strArg2.split(" ")[0]).split(":")[1]).trim();
            String tohour2=(strArg2.split(" ")[1]).trim();
            log("Updated setting of" + strArg0 + " , to " + strArg1 + "-" + strArg2, "Updated settings of" + strArg0 + " , to " + strArg1 + "-" + strArg2, true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I update trip setting of \"([^\"]*)\" to \"([^\"]*)\" to \"([^\"]*)\"$")
    public void i_update_trip_setting_of_sunday_to_something_to_something(String strArg0, String strArg1, String strArg2) {

        i_update_sms_setting_of_sunday_to_something_to_something(strArg0, strArg1, strArg2);
    }

    @And("^I update kansas driver todays trip alert setting to outside current time$")
    public void i_update_todays_trip_alert_setting_of_today_to_outside_current_time() throws Throwable {
        cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE","Kansas");
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        Calendar calendar = Calendar.getInstance();
        //current time plus 60 mins
        calendar.add(Calendar.MINUTE, +60);
        DateFormat formatter = new SimpleDateFormat("hh:mm aa");
        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
        String strdate = formatter.format(calendar.getTime());
        SimpleDateFormat  simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        simpleDateformat.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

        String dayOfWeek=simpleDateformat.format(calendar.getTime());
        i_update_sms_setting_of_sunday_to_something_to_something(dayOfWeek,strdate,"11:59 PM");
        Thread.sleep(5000);
    }

}