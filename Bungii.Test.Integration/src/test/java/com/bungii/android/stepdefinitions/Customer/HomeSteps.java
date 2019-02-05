package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.HomePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class HomeSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(HomeSteps.class);
    GeneralUtility utility = new GeneralUtility();
    HomePage homePage = new HomePage();
    ActionManager action = new ActionManager();

    @When("^I Select \"([^\"]*)\" from customer app menu list$")
    public void i_select_something_from_customer_app_menu_list(String strArg1) throws Throwable {
        try {
            //    action.click(homePage.Button_NavigationBar());
            utility.clickCustomerMenuItem(strArg1);
            log(" I Select " + strArg1 + " from customer app menu list", " I tapped on " + strArg1 + " from customer app menu list");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I tap on \"([^\"]*)\" > \"([^\"]*)\" link$")
    public void i_tap_on_something_something_link(String strArg1, String strArg2) throws Throwable {
        try {
            //     action.click(homePage.Button_NavigationBar());
            utility.clickCustomerMenuItem(strArg2);
            log(" I should able to tap on " + strArg2, " I tapped on " + strArg2);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I tap \"([^\"]*)\" on Home page$")
    public void i_tap_something_on_home_page(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Referral Invite link":
                    action.click(homePage.Link_Invite());
                    break;

            }
            log(" I should able to tap on " + strArg1 + " on Home page", " I tapped on " + strArg1 + " on Home page");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Given("^I am on Customer logged in Home page$")
    public void iAmOnCustomerLoggedInHomePage() {
        try {

/*            String NavigationBarName = action.getText(homePage.Title_HomePage(true));

            if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))
                    || NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.signup"))) {
                utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer.user"),
                        PropertyUtility.getDataProperties("customer.password"));
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                // do nothing
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
              //  iClickButtonOnScreen("CANCEL", "SEARCHING");
               // iRejectAlertMessage();
            } else {
                i_tap_on_something_something_link(NavigationBarName,"HOME");
            }*/

            //   String NavigationBarName = action.getText(homePage.Title_HomePage(true));

            if (utility.isCorrectPage("Signup") || utility.isCorrectPage("Login")) {
                utility.loginToCustomerApp(PropertyUtility.getDataProperties("valid.customer.phone"), PropertyUtility.getDataProperties("valid.customer.password"));
            } else if (utility.isCorrectPage("Home")) {
                // do nothing
            } else if (utility.isCorrectPage("Searching")) {
                //  iClickButtonOnScreen("CANCEL", "SEARCHING");
                // iRejectAlertMessage();
            } else {
                i_tap_on_something_something_link("Menu", "HOME");
            }
            log(" I am on Customer logged in Home page", "");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        } catch (Throwable throwable) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(throwable));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
