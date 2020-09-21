package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.SignupPage;

import com.bungii.android.pages.driver.DriverHomePage;
import com.bungii.android.pages.driver.EarningsPage;
import com.bungii.android.stepdefinitions.Customer.HomeSteps;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class DriverMenuSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(HomeSteps.class);
    GeneralUtility utility = new GeneralUtility();
    DriverHomePage homePage = new DriverHomePage();
    SignupPage Page_Signup = new SignupPage();
    EarningsPage earningsPage = new EarningsPage();
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
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE",phone);
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
        try {
            testStepVerify.isElementTextEquals(homePage.Title_Status(), Title);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should be able to see data on \"([^\"]*)\" page$")
    public void i_should_be_able_to_see_data_on_something_page(String strArg1) throws Throwable {
        String data = null;
        Thread.sleep(5000);
        try {
            switch (strArg1) {
                case "FAQ":
                    Thread.sleep(10000);
                    data = action.getText(homePage.Text_CommonQuestions1()).toString();
                    testStepAssert.isEquals(data, "COMMON QUESTIONS", data + " is displayed", data + " is displayed", data + " is not displayed");
                    break;

                case "LEADERBOARD":
                    data = action.getText(homePage.Text_Leaderboard()).toString();
                    testStepAssert.isElementTextEquals(homePage.Text_Leaderboard(), PropertyUtility.getMessage("menu.leaderboard.text"), data + " is displayed", data + " is displayed", data + " is not displayed");

                    break;

                case "SCHEDULED BUNGIIS":
                    data = action.getText(homePage.Text_ScheduledBungiis()).toString();
                    testStepAssert.isElementTextEquals(homePage.Text_ScheduledBungiis(), "No Bungiis", data + " is displayed", data + " is displayed", data + " is not displayed");
                    break;

                case "AVAILABLE BUNGIIS":
                    data = action.getText(homePage.Text_AvailableTrips()).toString();
                    testStepAssert.isElementTextEquals(homePage.Text_AvailableTrips(), "No Bungiis Available", data + " is displayed", data + " is displayed", data + " is not displayed");
                    break;

                case "EARNINGS":
                    Thread.sleep(10000);
                    data = action.getText(homePage.Text_Earnings()).toString();
                    testStepAssert.isElementTextEquals(homePage.Text_Earnings(), "DRIVER INFO", data + " is displayed", data + " is displayed", data + " is not displayed");
                    break;

                case "ACCOUNT":
                    data = action.getText(homePage.Text_Account()).toString();
                    testStepAssert.isElementTextEquals(homePage.Text_Account(), PropertyUtility.getDataProperties("driver.login.name1"), data + " is displayed", data + " is displayed", data + " is not displayed");
                    break;

                case "ALERT SETTINGS":
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
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I click on \"([^\"]*)\" hyperlink$")
    public void i_click_on_something_hyperlink(String strArg1) throws Throwable {
        try {
            action.click(earningsPage.Link_ItemisedEarnings());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^I am redirected to \"([^\"]*)\"$")
    public void i_am_redirected_to_something(String strArg1) throws Throwable {
        try {
            testStepAssert.isTrue(action.getText(earningsPage.Text_HistoryDataTotalEarnings()).contains(PropertyUtility.getMessage("history.data")),
                    PropertyUtility.getMessage("history.data") + " is displayed",
                    PropertyUtility.getMessage("history.data") + " is displayed",
                    PropertyUtility.getMessage("history.data") + " is not displayed");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
}
