package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.LoginPage;
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

public class LoginSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    LoginPage loginPage = new LoginPage();
    GeneralUtility utility = new GeneralUtility();

    @Given("^I am on customer Log in page$")
    public void i_am_on_customer_log_in_page() throws Throwable {
        try {
            utility.launchCustomerApplication();
            utility.goToLoginPage();
            testStepVerify.isElementDisplayed(loginPage.Header_LoginPage(true), "Signup button should be displayed ", "Sign up button is displayed", "Sign up button is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I enter customers \"([^\"]*)\" Phone Number$")
    public void i_enter_customers_something_phone_number(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "valid":
                    action.sendKeys(loginPage.TextField_PhoneNumber(), PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                    break;
                case "invalid":
                    action.sendKeys(loginPage.TextField_PhoneNumber(), PropertyUtility.getDataProperties("customer_Invalid.phonenumber"));
                    break;
                case "blank":
                    action.sendKeys(loginPage.TextField_PhoneNumber(), "");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I enter customers \"([^\"]*)\" Password$")
    public void i_enter_customers_something_password(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "valid":
                    action.sendKeys(loginPage.TextField_Password(), PropertyUtility.getDataProperties("customer_generic.password"));
                    break;
                case "invalid":
                    action.sendKeys(loginPage.TextField_Password(), PropertyUtility.getDataProperties("customer_LessThan6.password"));
                    break;
                case "blank":
                    action.sendKeys(loginPage.TextField_Password(), "");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I tap on the \"([^\"]*)\" Button on Login screen$")
    public void i_tap_on_the_something_button(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Log in":
                    action.click(loginPage.Button_Login());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The user should see \"([^\"]*)\" on log in page$")
    public void the_user_should_see_something_on_log_in_page(String strArg1) throws Throwable {
        try {
            String errorMessage = "", actualMessage = "", expectedMessage = "";
            switch (strArg1) {
                case "snackbar validation message invalid password":
                    testStepVerify.isElementTextEquals(loginPage.Snackbar(), PropertyUtility.getMessage("customer.error.invalidpassword"));
                    break;
                case "field validations for password":
                    actualMessage = utility.trimString(action.getText(loginPage.Error_EnterPassword()));
                    expectedMessage = PropertyUtility.getMessage("customer.login.password.error");
                    testStepVerify.isEquals(actualMessage, expectedMessage);
                    break;
                case "field validations for phone number":
                    actualMessage = utility.trimString(action.getText(loginPage.Error_EnterPhone()));
                    expectedMessage = PropertyUtility.getMessage("customer.login.phone.error");
                    testStepVerify.isEquals(actualMessage, expectedMessage);
                    break;
                case "login button disabled":
                    testStepVerify.isElementNotEnabled(loginPage.Button_Login(), "Login button should not be enabled", "Login button is not enabled", "Login button is Enabled");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
