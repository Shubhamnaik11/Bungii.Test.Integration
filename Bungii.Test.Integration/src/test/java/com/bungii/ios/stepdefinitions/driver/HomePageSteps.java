package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.HomePage;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class HomePageSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(UpdateStatusSteps.class);
    ActionManager action = new ActionManager();
    private HomePage homepage;

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
            if (action.isAlertPresent()){ SetupManager.getDriver().switchTo().alert().dismiss();   Thread.sleep(1000);        }
            Thread.sleep(1000);
            goToAppMenu();
            Thread.sleep(1000);
            boolean flag = clickAppMenu(menuItem);
            Thread.sleep(1000);
            testStepAssert.isTrue(flag, "I should able to click " + menuItem, "Not able to select " + menuItem + " from App menu");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
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
        if (action.isElementPresent(homepage.Button_GoOnline(true)))
            action.click(homepage.Button_GoOnline());
        else if (action.isElementPresent(homepage.Button_GoOffline(true)))
            logger.warning("driver Status is already Online");
        else
            logger.error("Not able to get driver status");
    }

    /**
     * driver goes offline
     */
    public void goOffline() {
        if (action.isElementPresent(homepage.Button_GoOffline(true)))
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
        switch (appMenuItem.toUpperCase()) {
            case "AVAILABLE TRIPS":
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
            case "LOGOUT":
                action.swipeUP();
                action.click(homepage.AppMenu_LogOut());
                break;
            default:
                logger.error("Please specify valid application menu item");
                notClicked = true;
                break;
        }
        return !notClicked;
    }


}
