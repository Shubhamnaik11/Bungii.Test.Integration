package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.SignupPage;
import com.bungii.android.pages.driver.HomePage;
import com.bungii.android.stepdefinitions.Customer.HomeSteps;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class DriverMenuSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(HomeSteps.class);
    GeneralUtility utility = new GeneralUtility();
    HomePage homePage = new HomePage();
    SignupPage Page_Signup = new SignupPage();
    ActionManager action = new ActionManager();

    @Given("^I am on Driver logged in Home page$")
    public void i_am_on_driver_logged_in_home_page() throws Throwable {
        String phone, password;
        boolean shouldLoginSucessful;
        try {
            phone = PropertyUtility.getDataProperties("menu.driver.login.phone");
            password = PropertyUtility.getDataProperties("menu.driver.login.password");
            shouldLoginSucessful = true;

            utility.loginToDriverApp(phone, password);
            if (shouldLoginSucessful)
                utility.isDriverLoginSucessful();
            else {
                //TODO: specify failure here
            }
            log("I should be logged in", "I am logged in", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^the \"([^\"]*)\" page is opened$")
    public void the_something_page_is_opened(String Title) throws Throwable {
                testStepVerify.isElementTextEquals(homePage.Title_Status(), Title);
    }

    @Then("^I should be able to see data on \"([^\"]*)\" page$")
    public void i_should_be_able_to_see_data_on_something_page(String strArg1) throws Throwable {
        String data = null;
        Thread.sleep(5000);
        switch (strArg1) {
            case "FAQ":
                data = action.getText(homePage.Text_CommonQuestions()).toString();
                testStepAssert.isElementTextEquals(homePage.Text_CommonQuestions(), "COMMON QUESTIONS", data + " is displayed", data + " is displayed", data + " is not displayed");
                break;

            case "LEADERBOARD":
                data = action.getText(homePage.Text_Leaderboard()).toString();
                testStepAssert.isElementTextEquals(homePage.Text_Leaderboard(), PropertyUtility.getDataProperties("menu.leaderboard.text"), data + " is displayed", data + " is displayed", data + " is not displayed");

                break;

            case "SCHEDULED BUNGIIS":
                data = action.getText(homePage.Text_ScheduledBungiis()).toString();
                testStepAssert.isElementTextEquals(homePage.Text_ScheduledBungiis(), "No Bungiis", data + " is displayed", data + " is displayed", data + " is not displayed");
                break;

            case "AVAILABLE TRIPS":
                data = action.getText(homePage.Text_AvailableTrips()).toString();
                testStepAssert.isElementTextEquals(homePage.Text_AvailableTrips(), "No Trips Available", data + " is displayed", data + " is displayed", data + " is not displayed");
                break;

            case "EARNINGS":
                data = action.getText(homePage.Text_Earnings()).toString();
                testStepAssert.isElementTextEquals(homePage.Text_Earnings(), "DRIVER INFO", data + " is displayed", data + " is displayed", data + " is not displayed");
                break;

            case "ACCOUNT":
                data = action.getText(homePage.Text_Account()).toString();
                testStepAssert.isElementTextEquals(homePage.Text_Account(), PropertyUtility.getDataProperties("driver.login.name1"), data + " is displayed", data + " is displayed", data + " is not displayed");
                break;

            case "TRIP ALERT SETTINGS":
                data = action.getText(homePage.Text_TripAlertSettings()).toString();
                testStepAssert.isElementTextEquals(homePage.Text_TripAlertSettings(), "Trip Alerts", data + " is displayed", data + " is displayed", data + " is not displayed");
                break;

            case "FEEDBACK":
                data = action.getText(homePage.Text_Feedback()).toString();
                testStepAssert.isElementTextEquals(homePage.Text_Feedback(), "Send us your feedback", data + " is displayed", data + " is displayed", data + " is not displayed");
                break;

            case "STORE":
                data = action.getText(homePage.Text_Store()).toString();
                testStepAssert.isElementTextEquals(homePage.Text_Store(), "BUNGII STORE", data + " is displayed", data + " is displayed", data + " is not displayed");
                break;

            case "LOGOUT":
                data = action.getText(homePage.Text_Logout()).toString();
                testStepAssert.isElementTextEquals(homePage.Text_Logout(), "LOGIN", data + " is displayed", data + " is displayed", data + " is not displayed");
                break;
        }
    }
}
