package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.HomePage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;

import java.math.BigDecimal;

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
        if (action.isElementPresent(homepage.Button_GoOffline(true)))
            logger.warning("driver Status is already Online");
        else if (action.isElementPresent(homepage.Button_GoOnline(true)))
            action.click(homepage.Button_GoOnline());
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
        try {
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
                    Thread.sleep(1000);
                    action.click(homepage.AppMenu_LogOut1());
                    if(action.isElementPresent(homepage.AppMenu_LogOut1(true)))
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

    @Then("The navigation title should change to {string}")
    public void the_navigation_title_should_change_to(String navTitle) {
        // Write code here that turns the phrase above into concrete actions
        try {

            switch (navTitle.toUpperCase()) {
                case "ONLINE":
                    testStepVerify.isEquals(action.getNameAttribute(homepage.Text_NavigationBar()), PropertyUtility.getMessage("driver.home.title.online"));
                    break;
                case "OFFLINE":
                    testStepVerify.isEquals(action.getNameAttribute(homepage.Text_NavigationBar()), PropertyUtility.getMessage("driver.home.title.offline"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");


            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("the status of the driver should be {string}")
    public void the_status_of_the_driver(String status) {
        try {
            switch (status.toUpperCase()) {
                case "OFFLINE":
                    testStepVerify.isEquals(action.getNameAttribute(homepage.NavigationBar_Status()), PropertyUtility.getMessage("driver.home.title.offline"));
                    testStepVerify.isEquals(action.getNameAttribute(homepage.GoOnline_Btn()), PropertyUtility.getMessage("driver.home.goonline"));
                    break;
                case "ONLINE":
                    testStepVerify.isEquals(action.getNameAttribute(homepage.NavigationBar_Status()), PropertyUtility.getMessage("driver.home.title.online"));
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

    @Given("The {string} for {string} driver should be correctly displayed")
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


}
