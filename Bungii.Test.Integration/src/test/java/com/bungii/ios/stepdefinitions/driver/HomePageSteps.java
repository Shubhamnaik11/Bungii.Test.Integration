package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.ResultManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.AccountPage;
import com.bungii.ios.pages.driver.HomePage;
import com.bungii.ios.pages.driver.TripAlertSettingsPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class HomePageSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(UpdateStatusSteps.class);
    ActionManager action = new ActionManager();
    private HomePage homepage;
    private AccountPage accountPage = new AccountPage();
    private TripAlertSettingsPage tripAlertSettingsPage = new TripAlertSettingsPage();
    GeneralUtility utility= new GeneralUtility();
    public HomePageSteps(HomePage homepage) {
        this.homepage = homepage;
    }

    @Then("^I should be successfully logged in to the application$")
    public void driver_should_be_successfully_logged_in_to_the_system() {
        try {
            GeneralUtility utility = new GeneralUtility();
            boolean isHomePage = utility.verifyPageHeader("HOME");
            testStepVerify.isTrue(isHomePage, "User should be loggind in", " Home screen is displayed", "User was not logged in");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @And("^I Select \"([^\"]*)\" from driver App menu$")
    public void i_select_something_from_driver_app_memu(String menuItem) {
        try {
            if (action.isAlertPresent()) {
                SetupManager.getDriver().switchTo().alert().dismiss();
                Thread.sleep(1000);
            }
            Thread.sleep(1000);
            goToAppMenu();
            Thread.sleep(1000);
            boolean flag = clickAppMenu(menuItem);
            Thread.sleep(1000);
            testStepAssert.isTrue(flag, "I should able to click " + menuItem, "Not able to select " + menuItem + " from App menu");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful",
                    "Not able to select " + menuItem + " from App menu" , true);
        }
    }

    @Then("^I change driver status to \"([^\"]*)\"$")
    public void i_change_driver_status_to_something(String status) throws Throwable {
        try {
            switch (status.toUpperCase()) {
                case "ONLINE":
                    goOnline();
                    break;
                case "OFFLINE":
                    goOffline();
                    break;
            }
            ResultManager.log("I change driver status to" + status, "I changed driver status to " + status, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    /**
     * driver goes online
     */
    public void goOnline() {

        String navigationHeaderName = action.getNameAttribute(homepage.NavigationBar_Status());

        if (navigationHeaderName.equals("ONLINE"))
            logger.warning("driver Status is already Online");
        else if (navigationHeaderName.equals("OFFLINE")) {
            action.click(homepage.Button_GoOnline());
        } else if (action.isElementPresent(homepage.Button_GoOnline(true)))
            action.click(homepage.Button_GoOnline());
        else
            logger.error("Not able to get driver status");
    }

    /**
     * driver goes offline
     */
    public void goOffline() {
        String navigationHeaderName = action.getNameAttribute(homepage.NavigationBar_Status());

        if (navigationHeaderName.equals("OFFLINE")) {
            logger.warning("driver Status is already offline");
        } else if (navigationHeaderName.equals("ONLINE")) {
            action.click(homepage.Button_GoOffline());
        } else if (action.isElementPresent(homepage.Button_GoOffline(true)))
            action.click(homepage.Button_GoOffline());
        else if (action.isElementPresent(homepage.Button_GoOnline(true)))
            logger.warning("driver Status is already offline");
        else
            logger.error("Not able to get driver status");
    }


    /**
     * Click on App menu on side bar
     */
    public void goToAppMenu() {
        action.click(homepage.Button_AppMenu());
    }
/*	public void selectAvailableTrip(){

	}*/

    /**
     * Get driver status information
     *
     * @return String array of driver status information
     */
    public String[] getDriverInformation() {
        String[] driverInfo = new String[3];
        driverInfo[0] = action.getNameAttribute(homepage.Text_DriverName());
        driverInfo[1] = action.getNameAttribute(homepage.Text_DriverInfo());
        driverInfo[2] = action.getNameAttribute(homepage.NavigationBar_Status());
        return driverInfo;
    }

    /**
     * @param appMenuItem
     * @return true if app menu is clicked , false if app menu is not clicked
     */
    public boolean clickAppMenu(String appMenuItem) {
        boolean notClicked = false;
        try {
            switch (appMenuItem.toUpperCase()) {
                case "AVAILABLE BUNGIIS":
                    action.click(homepage.AppMenu_AvailableTrip());
                    break;
                case "HOME":
                    action.click(homepage.AppMenu_Home());
                    break;
                case "ACCOUNT":
                    action.click(homepage.AppMenu_Account());
                    break;
                case "SCHEDULED BUNGIIS":
                    action.click(homepage.AppMenu_ScheduledTrip());
                    break;
                case "FAQ":
                    action.click(homepage.AppMenu_FAQ());
                    break;
                case "EARNINGS":
                    action.click(homepage.AppMenu_EARNINGS());
                    break;
                case "ALERT SETTINGS":
                    action.click(homepage.AppMenu_TripAlertSettings());
                    break;
                case "FEEDBACK":
                    action.click(homepage.AppMenu_Feedback());
                    break;
                case "STORE":
                    action.click(homepage.AppMenu_Store());
                    break;
                case "LEADERBOARD":
                    action.click(homepage.AppMenu_LEADERBOARD());
                    break;
                case "LOGOUT":
                    action.swipeUP();
                    Thread.sleep(1000);
                    action.click(homepage.AppMenu_LogOut1());
                    if (action.isElementPresent(homepage.AppMenu_LogOut1(true)))
                        action.tapByElement(homepage.AppMenu_LogOut1());
                    //    action.click(homepage.AppMenu_LogOut());
                    //   action.tapByElement(homepage.AppMenu_LogOut());
                    break;
                default:
                    logger.error("Please specify valid application menu item");
                    notClicked = true;
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
        return !notClicked;

    }

    @Then("Info text should be updated")
    public void info_text_should_be_updated() {
        // Write code here that turns the phrase above into concrete actions
        try {
            testStepVerify.isEquals(action.getNameAttribute(homepage.Text_DriverName()), PropertyUtility.getMessage("DriverStatusInfo"));
            testStepVerify.isEquals(action.getNameAttribute(homepage.Text_DriverInfo()), PropertyUtility.getMessage("DriverInfo"));
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @And("^The navigation title should change to \"([^\"]*)\"$")
    public void the_navigation_title_should_change_to(String navTitle) {
        // Write code here that turns the phrase above into concrete actions
        try {

            switch (navTitle.toUpperCase()) {
                case "ONLINE":
                    testStepVerify.isEquals(action.getScreenHeader(homepage.Text_NavigationBar()), PropertyUtility.getMessage("driver.home.title.online"));
                    break;
                case "OFFLINE":
                    testStepVerify.isEquals(action.getScreenHeader(homepage.Text_NavigationBar()), PropertyUtility.getMessage("driver.home.title.offline"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");


            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^the status of the driver should be \"([^\"]*)\"$")
    public void the_status_of_the_driver(String status) {
        try {
            switch (status.toUpperCase()) {
                case "OFFLINE":
                    testStepVerify.isEquals(action.getScreenHeader(homepage.NavigationBar_Status()), PropertyUtility.getMessage("driver.home.title.offline"));
                    testStepVerify.isEquals(action.getNameAttribute(homepage.GoOnline_Btn()), PropertyUtility.getMessage("driver.home.goonline"));
                    break;
                case "ONLINE":
                    testStepVerify.isEquals(action.getScreenHeader(homepage.NavigationBar_Status()), PropertyUtility.getMessage("driver.home.title.online"));
                    testStepVerify.isEquals(action.getNameAttribute(homepage.GoOffline_Btn()), PropertyUtility.getMessage("driver.home.gooffline"));
                    break;
                default:
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^The title of button should change to \"([^\"]*)\" on driverApp$")
    public void the_title_of_button_should_change_to(String buttonTitle) {

        try {
            switch (buttonTitle.toUpperCase()) {
                case "GO ONLINE":
                    testStepVerify.isEquals(action.getNameAttribute(homepage.GoOnline_Btn()), buttonTitle.toUpperCase());
                    break;
                case "GO OFFLINE":
                    testStepVerify.isEquals(action.getNameAttribute(homepage.GoOffline_Btn()), buttonTitle.toUpperCase());
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
    public void the_for_driver_should_be_correctly_displayed(String info, String driver) {
        try {
            String driverPhoneNumber, driverName, driverVehicle;
            if (driver.toLowerCase().equals("valid")) {
                driverPhoneNumber = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                driverName = PropertyUtility.getDataProperties("ios.driver.name");
                driverVehicle = PropertyUtility.getDataProperties("ios.driver.vehicle");
            } else {
                driverPhoneNumber = PropertyUtility.getDataProperties("ios.valid.driver2.phone");
                driverName = PropertyUtility.getDataProperties("ios.driver2.name");
                driverVehicle = PropertyUtility.getDataProperties("ios.driver2.vehicle");
            }

            switch (info.toLowerCase()) {
                case "name":
                    testStepVerify.isEquals(action.getNameAttribute(homepage.Text_DriverName()), driverName);
                    break;
                case "vehicle info":
                    testStepVerify.isEquals(action.getNameAttribute(homepage.Text_DriverInfo()), driverVehicle);
                    break;
                case "rating":
                    String ratingString = DbUtility.getDriverRating(driverPhoneNumber);
                    cucumberContextManager.setScenarioContext("DRIVER_CURRENT_RATTING",ratingString);
                    BigDecimal bigDecimal = new BigDecimal(String.valueOf(ratingString));
                    int ratingInt = bigDecimal.intValue();
                    BigDecimal ratingDecimal = bigDecimal.subtract(new BigDecimal(ratingInt));

                    System.out.println("ratingString: " + ratingString);
                    System.out.println("Integer Part: " + ratingInt);
                    System.out.println("Decimal Part: " + ratingDecimal);

                    homepage.WaitUntilElementIsDisplayed(By.xpath("//XCUIElementTypeButton[@name=\"rating filled star icon\"])"));

                    int filledStarCount = homepage.FilledStars().size();
                    int HalfFilledStarCount = homepage.HalfFilledStar().size();

                    testStepVerify.isEquals(filledStarCount, ratingInt);

                    if (ratingDecimal.doubleValue() >= 0.5) {
                        testStepVerify.isEquals(HalfFilledStarCount, 1);
                    }
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");

            }
        } catch (
                Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should be able to see data on \"([^\"]*)\" page$")
    public void i_should_be_able_to_see_data_on_something_page(String strArg1) throws Throwable {
        try {
            Thread.sleep(5000);
            switch (strArg1) {
                case "FAQ":
                    testStepAssert.isElementNameEquals(homepage.Text_CommonQuestions(), "COMMON QUESTIONS", "COMMON QUESTIONS is displayed", "COMMON QUESTIONS is displayed", "COMMON QUESTIONS is not displayed");
                    break;

                case "LEADERBOARD":
                    testStepAssert.isElementNameEquals(homepage.Text_Leaderboard(), PropertyUtility.getMessage("driver.menu.leaderboard.text"), " is displayed", " is displayed", " is not displayed");
                    break;

                case "SCHEDULED BUNGIIS":
                    testStepAssert.isElementNameEquals(homepage.Text_ScheduledBungiis(), "No Bungiis, You don't have any scheduled\u2028Bungiis at this time.", "No Bungiis, You don't have any scheduled\u2028Bungiis at this time. is displayed", " is displayed", " is not displayed");
                    break;

                case "AVAILABLE BUNGIIS":
                    testStepAssert.isElementNameEquals(homepage.Text_AvailableTripsData(), "No Bungiis Available", " is displayed", "No Bungiis Available is displayed", " is not displayed");
                    break;

                case "EARNINGS":
                    testStepAssert.isElementNameEquals(homepage.Text_Earnings(), "DRIVER INFO", "DRIVER INFO is displayed", "DRIVER INFO is displayed", "DRIVER INFO is not displayed");
                    break;
                case "ITEMIZED EARNINGS":
                    testStepVerify.isFalse(action.isElementPresent(homepage.Text_ApplicationError(true))," Application error should not be displayed","Application error is not displayed","Application error is displayed");
                    action.swipeUP();
                    testStepVerify.isElementEnabled(homepage.Text_MyStat(),"My stats  should be displayed");
                    testStepVerify.isElementEnabled(homepage.Text_TotalTrip(),"Total Trips  should be displayed");
                    testStepVerify.isElementEnabled(homepage.Text_TripMonths(),"Trips/Months  should be displayed");
                    testStepVerify.isElementEnabled(homepage.Text_EarningMonth(),"Earnings / Month  should be displayed");
                    testStepVerify.isElementEnabled(homepage.Text_Total_Earnings(),"Total Tips should be displayed");
                    testStepVerify.isElementEnabled(homepage.Text_Total_Tips(),"Total Earnings should be displayed");
                    testStepVerify.isElementEnabled(homepage.Text_My_Rating(),"My Rating should be displayed");
                    break;
                case "ACCOUNT":
                    String accountName=action.getNameAttribute(accountPage.Text_Name());
                    testStepAssert.isEquals(accountName.replace("  "," "), (String) cucumberContextManager.getScenarioContext("DRIVER_1"), " is displayed", " is displayed", " is not displayed");
                    break;

                case "ALERT SETTINGS":
                    testStepAssert.isElementNameEquals(homepage.Text_TripAlertSettings(), "Delivery Alerts", "Delivery Alerts is displayed", "Delivery Alerts is displayed", "Delivery Alerts is not displayed");
                    testStepAssert.isElementNameEquals(homepage.Text_SMSAlertSettings(), "SMS Alerts", "SMS Alerts is displayed", "SMS Alerts is displayed", "SMS Alerts is not displayed");
                    break;

                case "FEEDBACK":
                    testStepAssert.isElementNameEquals(homepage.Text_Feedback(), "Send us your feedback", "Send us your feedback is displayed", "Send us your feedback is displayed", "Send us your feedback is not displayed");
                    break;

                case "STORE":
                    testStepAssert.isElementNameEquals(homepage.Text_Store(), "BUNGII STORE", "BUNGII STORE is displayed", "BUNGII STORE is displayed", "BUNGII STORE is not displayed");
                    break;

                case "LOGOUT":
                    testStepAssert.isElementNameEquals(homepage.NavigationBar_Status(), "LOG IN", "LOGIN is displayed", "LOGIN is displayed", "LOGIN is not displayed");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should be able to see default data on \"([^\"]*)\" page$")
    public void i_should_be_able_to_see_default_data_on_something_page(String strArg1) throws Throwable {
        try {
            Thread.sleep(5000);
            String timeRange = "07:00 AM - 09:00 PM  CST";

            //Add Code to handle daylight
            if(TimeZone.getTimeZone("CST6CDT").inDaylightTime(new Date()))
                timeRange = timeRange.replace("S","D");

            switch (strArg1) {
                case "DELIVERY ALERT":
                    List<WebElement> timeData = tripAlertSettingsPage.Row_TripTime();
                    for (WebElement row : timeData) {
                        String currentRowData = action.getNameAttribute(row);
                        testStepAssert.isEquals(currentRowData, timeRange, "default trip "+timeRange+" should be displayed", "default trip data s is displayed", "default trip data is not displayed");
                    }
                    testStepAssert.isElementNameEquals(tripAlertSettingsPage.Text_ScheduledInfo(), PropertyUtility.getMessage("driver.trip.alert.settings"), "TRIP Alerts info is displayed", "TRIP Alerts info is displayed", "TRIP Alerts info is not displayed");
                    break;
                case "SMS ALERT":
                    List<WebElement> timeDataSms = tripAlertSettingsPage.Row_TripTime();
                    for (WebElement row : timeDataSms) {
                        String currentRowData = action.getNameAttribute(row);
                        testStepAssert.isEquals(currentRowData, timeRange, "default trip "+timeRange+" should be displayed", "default trip data s is displayed", "default trip data  is not displayed");
                    }
                    testStepAssert.isElementNameEquals(tripAlertSettingsPage.Text_ScheduledInfo(), PropertyUtility.getMessage("driver.sms.alert.settings"), PropertyUtility.getMessage("driver.sms.alert.settings") + "should be displayed", "SMS Alerts info is displayed", "SMS Alerts info is not displayed");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^previous \"([^\"]*)\" data should be retained$")
    public void previous_something_data_should_be_retained(String strArg1) throws Throwable {
        try {
            int day = 0;
            switch (strArg1) {
                case "DELIVERY ALERT":
                    List<WebElement> timeData = tripAlertSettingsPage.Row_TripTime();
                    for (WebElement row : timeData) {
                        String currentRowData = action.getNameAttribute(row);
                        testStepAssert.isEquals(currentRowData, (String) cucumberContextManager.getScenarioContext("TRIP_ALERT_" + day), (String) cucumberContextManager.getScenarioContext("TRIP_ALERT_" + day) + " should be displayed for day " + day, (String) cucumberContextManager.getScenarioContext("TRIP_ALERT_" + day) + " is displayed", (String) cucumberContextManager.getScenarioContext("TRIP_ALERT_" + day) + " not displayed");
                        day++;
                    }
                    break;
                case "SMS ALERT":
                    List<WebElement> timeDataSms = tripAlertSettingsPage.Row_TripTime();
                    for (WebElement row : timeDataSms) {
                        String currentRowData = action.getNameAttribute(row);
                        testStepAssert.isEquals(currentRowData, (String) cucumberContextManager.getScenarioContext("SMS_ALERT_" + day), (String) cucumberContextManager.getScenarioContext("SMS_ALERT_" + day) + " should be displayed for day " + day, (String) cucumberContextManager.getScenarioContext("TRIP_ALERT_" + day) + " is displayed", (String) cucumberContextManager.getScenarioContext("TRIP_ALERT_" + day) + " not displayed");
                        day++;
                    }
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I save \"([^\"]*)\" settings data$")
    public void i_save_trip_alert_settings_data(String strArg1) throws Throwable {
        try {
            List<WebElement> timeData = tripAlertSettingsPage.Row_TripTime();
            int day = 0;
            switch (strArg1) {
                case "DELIVERY ALERT":
                    for (WebElement row : timeData) {
                        String currentRowData = action.getNameAttribute(row);
                        cucumberContextManager.setScenarioContext("TRIP_ALERT_" + day, currentRowData);
                        day++;
                    }
                    break;
                case "SMS ALERT":
                    for (WebElement row : timeData) {
                        String currentRowData = action.getNameAttribute(row);
                        cucumberContextManager.setScenarioContext("SMS_ALERT_" + day, currentRowData);
                        day++;
                    }
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
                    action.click((WebElement) SetupManager.getDriver().findElement(By.name(dayOfWeek)));
                    break;
                default:
                    action.click((WebElement)SetupManager.getDriver().findElement(By.name(strArg0)));
                    break;
            }
            String from = (strArg1.split(":")[0]);
            from = from.startsWith("0") ? from.substring(1) : from;
            action.click(tripAlertSettingsPage.Text_CurrentFromTime());
            action.sendKeys(tripAlertSettingsPage.Scroll_Hour(), from);
            action.sendKeys(tripAlertSettingsPage.Scroll_Min(), ((strArg1.split(" ")[0]).split(":")[1]).trim());
            action.sendKeys(tripAlertSettingsPage.Scroll_Marid(), (strArg1.split(" ")[1]).trim());
            action.click(tripAlertSettingsPage.Button_Done());

            String toHour = (strArg2.split(":")[0]);
            toHour = toHour.startsWith("0") ? toHour.substring(1) : toHour;
            action.click(tripAlertSettingsPage.Text_CurrentToTime());
            action.sendKeys(tripAlertSettingsPage.Scroll_Hour(), toHour);
            action.sendKeys(tripAlertSettingsPage.Scroll_Min(), ((strArg2.split(" ")[0]).split(":")[1]).trim());
            action.sendKeys(tripAlertSettingsPage.Scroll_Marid(), (strArg2.split(" ")[1]).trim());
            action.click(tripAlertSettingsPage.Button_Done());
            action.click(tripAlertSettingsPage.Button_Save());
            log("Updated setting of" + strArg0 + " , to " + strArg1 + "-" + strArg2, " update trip settings", true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I update trip setting of \"([^\"]*)\" to \"([^\"]*)\" to \"([^\"]*)\"$")
    public void i_update_trip_setting_of_sunday_to_something_to_something(String strArg0, String strArg1, String strArg2) {

        i_update_sms_setting_of_sunday_to_something_to_something(strArg0, strArg1, strArg2);
    }

    @And("^I update denvers driver todays trip alert setting to outside current time$")
    public void i_update_todays_trip_alert_setting_of_today_to_outside_current_time() throws Throwable {
        cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE","denver");
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
