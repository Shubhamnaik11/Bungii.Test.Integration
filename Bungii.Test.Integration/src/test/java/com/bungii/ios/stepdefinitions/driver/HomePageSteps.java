package com.bungii.ios.stepdefinitions.driver;


import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.HomePage;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class HomePageSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(UpdateStatusSteps.class);
    ActionManager action = new ActionManager();
    private HomePage homepage;

    public HomePageSteps(HomePage homepage) {
        this.homepage = homepage;
    }


    @And("^I Select \"([^\"]*)\" from driver App menu$")
    public void i_select_something_from_driver_app_memu(String menuItem) {
        try {
            goToAppMenu();
            boolean flag = clickAppMenu(menuItem);
            testStepAssert.isTrue(flag, "I should able to click " + menuItem, "Not able to select " + menuItem + " from App menu");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    /**
     * Driver goes online
     */
    public void goOnline() {
        if (homepage.isElementEnabled(homepage.Button_GoOnline()))
            action.click(homepage.Button_GoOnline());
        else if (homepage.isElementEnabled(homepage.Button_GoOffline()))
            logger.warning("Driver Status is already Online");
        else
            logger.error("Not able to get driver status");
    }

    /**
     * Driver goes offline
     */
    public void goOffline() {
        if (homepage.isElementEnabled(homepage.Button_GoOffline()))
            action.click(homepage.Button_GoOffline());
        else if (homepage.isElementEnabled(homepage.Button_GoOnline()))
            logger.warning("Driver Status is already offline");
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
     * @return String array of Driver status information
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
