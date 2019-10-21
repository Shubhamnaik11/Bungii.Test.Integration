package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.BungiiRequest;
import com.bungii.android.pages.driver.HomePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;

public class HomePageSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(HomePageSteps.class);
    ActionManager action = new ActionManager();
    HomePage homePage = new HomePage();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    GeneralUtility utility = new GeneralUtility();

    @And("^I Select \"([^\"]*)\" from driver App menu$")
    public void i_select_something_from_driver_app_memu(String menuItem) {
        try {
/*
            if (action.isNotificationAlertDisplayed()) {
                action.click(Page_BungiiRequest.AlertButton_Cancel(true));
                Thread.sleep(1000);
            }
*/

            if (action.isNotificationAlertDisplayed()) {
                if (action.getText(Page_BungiiRequest.Alert_Msg()).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))) {
                    utility.acceptNotificationAlert();
                } else {
                    action.click(Page_BungiiRequest.AlertButton_Cancel());
                }

            }
            boolean isClicked = false;
            action.click(homePage.Button_NavigationBar());
            List<WebElement> elements = homePage.Button_NavigationBarText();
            for (WebElement element : elements) {
                if (element.getText().equalsIgnoreCase(menuItem)) {
                    action.click(element);
                    isClicked = true;
                    break;
                }
            }
            testStepAssert.isTrue(isClicked, "I should able to click " + menuItem, "Not able to select " + menuItem + " from App menu");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I should be navigated to Home screen on driver app$")
    public void i_should_be_navigated_to_home_screen_on_driver_app() throws Throwable {
        try {
            Thread.sleep(10000);
            String getNaviagationText = action.getText(homePage.Generic_HeaderElement());
            if(getNaviagationText.trim().equals("")) {
                Thread.sleep(25000);getNaviagationText = action.getText(homePage.Generic_HeaderElement());
            }
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
                    action.click(homePage.Button_OnlineOffline());
                    Thread.sleep(4000);
                    break;
                case "Go Offline":
                    action.click(homePage.Button_OnlineOffline());
                    break;
                case "Available Trips":
                    action.click(homePage.Link_AvailableTrips());
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
                    testStepVerify.isEquals(action.getText(homePage.Title_Status()), PropertyUtility.getMessage("driver.home.title.offline"));
                    testStepVerify.isEquals(action.getText(homePage.Button_OnlineOffline()), PropertyUtility.getMessage("driver.home.goonline"));
                    break;
                case "ONLINE":
                    testStepVerify.isEquals(action.getText(homePage.Title_Status()), PropertyUtility.getMessage("driver.home.title.online"));
                    testStepVerify.isEquals(action.getText(homePage.Button_OnlineOffline()), PropertyUtility.getMessage("driver.home.gooffline"));
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
                    testStepVerify.isEquals(action.getText(homePage.Text_DriverName()), driverName);
                    break;
                case "vehicle info":
                    testStepVerify.isEquals(action.getText(homePage.Text_DriverInfo()), driverVehicle);
                    break;
                case "rating":
                    testStepVerify.isElementEnabled(homePage.Text_RattingBar(), "Ratting bar Should be correctly displayed");
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
                    testStepVerify.isEquals(action.getText(homePage.Button_OnlineOffline()), buttonTitle.toUpperCase());
                    break;
                case "GO OFFLINE":
                    testStepVerify.isEquals(action.getText(homePage.Button_OnlineOffline()), buttonTitle.toUpperCase());
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
            testStepVerify.isEquals(action.getText(homePage.Text_DriverName()), PropertyUtility.getMessage("DriverStatusInfo"));
            testStepVerify.isEquals(action.getText(homePage.Text_DriverInfo()), PropertyUtility.getMessage("DriverInfo.android"));
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
                    testStepVerify.isEquals(action.getText(homePage.Title_Status()), PropertyUtility.getMessage("driver.home.title.online"));
                    break;
                case "OFFLINE":
                    testStepVerify.isEquals(action.getText(homePage.Title_Status()), PropertyUtility.getMessage("driver.home.title.offline"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
