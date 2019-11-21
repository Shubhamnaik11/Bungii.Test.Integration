package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.LoginPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.*;

public class LoginSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    LoginPage driverLogInPage = new LoginPage();

    @Given("^I am logged in as \"([^\"]*)\" driver$")
    public void i_am_logged_in_as_something_driver(String option) throws Throwable {
        try {
            String phone, password;
            boolean shouldLoginSucessful;
            switch (option.toLowerCase()) {
                case "valid":
                    phone = PropertyUtility.getDataProperties("valid.driver.phone");
                    password = PropertyUtility.getDataProperties("valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "valid driver 2":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("valid.driver2.phone");
                    password = PropertyUtility.getDataProperties("valid.driver2.password");
                    cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("valid.driver2.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "valid boston":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("boston.driver.phone");
                    password = PropertyUtility.getDataProperties("boston.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("boston.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "valid baltimore":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("baltimore.driver.phone");
                    password = PropertyUtility.getDataProperties("baltimore.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("baltimore.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "valid atlanta":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("atlanta.driver.phone");
                    password = PropertyUtility.getDataProperties("atlanta.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "valid ios":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone =PropertyUtility.getDataProperties("ios.valid.driver.phone");
                    password = PropertyUtility.getDataProperties("valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                default:
                    throw new Exception("Please specify valid input");
            }
            utility.loginToDriverApp(phone, password);
            if (shouldLoginSucessful)
                utility.isDriverLoginSucessful();
            else {
                //TODO: specify failure here
            }
            log("I should be logged in","I am logged in",true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            logger.error("PageSource", SetupManager.getDriver().getPageSource());

            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I am on the LOG IN page on driver app$")
    public void i_am_on_the_log_in_page_on_driver_app() {
        try {
            utility.goToDriverLoginPage();
            log("I should be on the LOG IN page on driver app","I am on the LOG IN page on driver app",true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            logger.error("Page Source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I enter phoneNumber :(.+) and  Password :(.+)$")
    public void i_enter_valid_credentials(String username, String password) {
        try {
            String strUserName = username.equals("<BLANK>") ? "" : username.trim().equals("{VALID}") ? PropertyUtility.getDataProperties("valid.driver.phone") : username;
            String strPassWord = password.equals("<BLANK>") ? "" : password.equals("{VALID}") ? PropertyUtility.getDataProperties("valid.driver.password") : password;

            utility.enterDriverPhoneAndPassword(strUserName, strPassWord);

            pass("Username and Password should be added successfully",
                    "Username :" + strUserName + ", and password :" + strPassWord + ",is added successfully");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" button on Log In screen on driver app$")
    public void i_click_something_button_on_log_in_screen_on_driver_app(String option) throws Throwable {
        try {
            switch (option.toUpperCase()) {
                case "LOG IN":
                    action.click(driverLogInPage.Button_Login());
                    break;
                case "FORGOT PASSWORD":
                    action.click(driverLogInPage.Button_ForgotPassword());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I click "+option+ " button on Log In screen on driver app","I clicked"+option+ " button on Log In screen on driver app",true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on Log In screen on driver app$")
    public void i_should_see_something_on_log_in_screen_on_driver_app(String option) throws Throwable {
        try {
            switch (option) {
                case "SNACK BAR VALIDATION FOR INVALID PASSWORD":
                    testStepVerify.isEquals(utility.getDriverSnackBarMessage(), PropertyUtility.getMessage("driver.error.invalidpassword"));
                    break;
                case "LOGIN BUTTON DISABLED":
                    testStepVerify.isTrue(!action.isElementEnabled(driverLogInPage.Button_Login()), "Login button should be disabled");
                    break;
                case "LOGIN BUTTON ENABLED":
                    testStepVerify.isTrue(action.isElementEnabled(driverLogInPage.Button_Login()), "Login button should be ENABLED");
                    break;
                case "Empty Phone Error":
                    testStepVerify.isEquals(action.getText(driverLogInPage.Text_LoginError()), PropertyUtility.getMessage("driver.login.phone.error"));
                    break;
                case "Empty Password Error":
                    testStepVerify.isEquals(action.getText(driverLogInPage.Text_LoginError()), PropertyUtility.getMessage("driver.login.password.error"));
                    break;
                case "Empty Phone and Password Error":
                    testStepVerify.isEquals(action.getText(driverLogInPage.Text_LoginError()), PropertyUtility.getMessage("driver.login.phone.error"));
                    testStepVerify.isEquals(action.getText(driverLogInPage.Text_LoginError2()), PropertyUtility.getMessage("driver.login.password.error"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


}
