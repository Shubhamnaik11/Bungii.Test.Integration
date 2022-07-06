package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.LoginPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.android.pages.driver.*;
import com.bungii.api.utilityFunctions.AuthServices;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Map;

import static com.bungii.common.manager.ResultManager.*;

public class LoginSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    LoginPage driverLogInPage = new LoginPage();
    DriverHomePage driverHomePage =new DriverHomePage();
    AuthServices authServices = new AuthServices();

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
                    phone = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                    password = PropertyUtility.getDataProperties("valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "valid far away atlanta":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("atlanta.far.away.driver.phone");
                    password = PropertyUtility.getDataProperties("atlanta.far.away.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.far.away.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;

                case "valid kansas":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("valid.driver.kansas.phone");
                    password = PropertyUtility.getDataProperties("valid.driver.kansas.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.kansas.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "kansas driver 1":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("Kansas.driver.phone");
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "kansas driver 2":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                    password = PropertyUtility.getDataProperties("Kansas.driver2.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver2.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "non controller kansas driver 2":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                    password = PropertyUtility.getDataProperties("Kansas.driver2.password");
                    cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("Kansas.driver2.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_apple_z android_test":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("driver.1.Goa.phone");
                    password = PropertyUtility.getDataProperties("driver.1.Goa.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("driver.1.Goa.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdriver_goa_a android_test":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("driverA.phone.number");
                    password = PropertyUtility.getDataProperties("driverA.phone.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("driverA.phone.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdriver_goa_b android_test":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("driverB.phone.number");
                    password = PropertyUtility.getDataProperties("driverB.phone.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("driverB.phone.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdriver_goa_c android_test":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("driverC.phone.number");
                    password = PropertyUtility.getDataProperties("driverC.phone.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("driverC.phone.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdriver_goa_d android_test":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("driverD.phone.number");
                    password = PropertyUtility.getDataProperties("driverD.phone.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("driverD.phone.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdriver_goa_e android_test":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("driverE.phone.number");
                    password = PropertyUtility.getDataProperties("driverE.phone.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("driverE.phone.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdriver_goa_f android_test":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("driverF.phone.number");
                    password = PropertyUtility.getDataProperties("driverF.phone.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("driverF.phone.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;

                case "goa driver_1":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("goa.driver.phone1");
                    password = PropertyUtility.getDataProperties("goa.driver.password1");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("goa.driver.name1"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "driver_goa_f android_test":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("driverF.phone.number");
                    password = PropertyUtility.getDataProperties("goa.driver.password1");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("driverF.phone.number"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_rathree test":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("android.valid.driver2.phone");
                    password = PropertyUtility.getDataProperties("web.valid.common.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", "Testdrivertywd_appleks_rathree Test");
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_andro_a kansas_a":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("android.new.driver1.phone");
                    password = PropertyUtility.getDataProperties("android.new.driver1.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("android.new.driver1.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drvb kansas_b":
                    phone = PropertyUtility.getDataProperties("Kansas.driver3.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver3.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drvc kansas_c":
                    phone = PropertyUtility.getDataProperties("Kansas.driver4.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver4.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;

                case "testdrivertywd_appleks_a_drvd kansas_d":
                    phone = PropertyUtility.getDataProperties("Kansas.driver5.phone");
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver5.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;

                case "testdrivertywd_appleks_a_drva kansas_a":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("Kansas.driver6.phone");
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver6.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;

                case "testdrivertywd_appleks_a_drvh kansas_h":
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    phone = PropertyUtility.getDataProperties("Kansas.driver10.phone");
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("kansas.driver10.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;

                case "testdrivertywd_appledc_a_drvc washingtonc":
                    phone = PropertyUtility.getDataProperties("Washington.driver8.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Washington.driver8.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Testdrivertywd_appledc_a_drvs Driver"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drvu kansas_u":
                    phone = PropertyUtility.getDataProperties("Kansas.driver12.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver12.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drvv kansas_v":
                    phone = PropertyUtility.getDataProperties("Kansas.driver13.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver13.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drvw kansas_w":
                    phone = PropertyUtility.getDataProperties("Kansas.driver14.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver14.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drvaj kansas_aj":
                    phone = PropertyUtility.getDataProperties("Kansas.driver25.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver25.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drvak kansas_ak":
                    phone = PropertyUtility.getDataProperties("Kansas.driver26.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver26.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drvao kansas_ao":
                    phone = PropertyUtility.getDataProperties("Kansas.driver28.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver28.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drvap kansas_ap":
                    phone = PropertyUtility.getDataProperties("Kansas.driver29.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver29.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drvaq kansas_aq":
                    phone = PropertyUtility.getDataProperties("Kansas.driver30.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver30.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drvah kansas_ah":
                    phone = PropertyUtility.getDataProperties("Kansas.driver45.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver45.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                case "testdrivertywd_appleks_a_drval kansas_al":
                    phone = PropertyUtility.getDataProperties("Kansas.driver24.phone");
                    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                    password = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Testdrivertywd_appleks_a_drval Kansas_al"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                    shouldLoginSucessful = true;
                    break;
                default:
                    throw new Exception("Please specify valid input - Driver name sent in cucumber steps " + option);
            }
            Thread.sleep(4000);
            utility.loginToDriverApp(phone, password);
            if (shouldLoginSucessful)
                utility.isDriverLoginSucessful();
            else {
                //TODO: specify failure here
            }
            log("I should be logged in","I am logged in as driver "+ option +" [ "+ phone+" ]",true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            logger.error("PageSource", SetupManager.getDriver().getPageSource());

            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^As a driver \"([^\"]*)\" I log in$")
    public void as_a_driver_something_i_log_in(String driverAName) {
        try {
            String driverPhoneCode="1", driverPhoneNum = "", driverPassword = "";
            driverPhoneNum = com.bungii.api.stepdefinitions.BungiiSteps.getDriverPhone(driverAName);
            driverPassword = "Cci12345";
            utility.loginToDriverApp(driverPhoneNum, driverPassword);
           // authServices.driverLogin(driverPhoneCode, driverPhoneNum, driverPassword);
            log("I should be logged in", "I am logged in", true);
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
            "Entered Driver Credentials ["+ strUserName+" / "+strPassWord+"] successfully");

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

    @And("^I hit back button$")
    public void i_hit_back() throws Throwable {
        try {

            SetupManager.getDriver().navigate().back();
            log("I hit back button","I hit back button",true);

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

                case "It looks like we ran into a hiccup. Please contact support@bungii.com for more information.":
                    //testStepVerify.isEquals(action.getText(driverHomePage.Text_ErrorMessage()),"It looks like we ran into a hiccup. Please contact support@bungii.com for more information.");
                    testStepVerify.isEquals(utility.getDriverSnackBarMessage(),"It looks like we ran into a hiccup. Please contact support@bungii.com for more information.");
                    break;

                case "Your account registration is still under process.":
                    //testStepVerify.isEquals(action.getText(driverLogInPage.Text_PendingDriverLoginError()), PropertyUtility.getMessage("driver.login.payment.pending.error"));
                    testStepVerify.isEquals(utility.getDriverSnackBarMessage(), "Your account registration is still under process.");
                    break;

                case "Invalid login credentials. Your account has been locked. Please use the Forgot Password option to reset your account.":
                    testStepVerify.isEquals(utility.getDriverSnackBarMessage(), option);
                    break;

                case "Invalid login credentials. You have exhausted 3 out of 5 attempts of entering the correct credentials.":
                    testStepVerify.isEquals(utility.getDriverSnackBarMessage(), option);
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I enter phoneNumber$")
    public void i_enter_phonenumber() {
        String phone = PropertyUtility.getDataProperties("driver.locked.login.phone");
        action.sendKeys(driverLogInPage.TextField_PhoneNumber(), phone);
    }

    @And("^I enter invalid password and click on \"([^\"]*)\" button for \"([^\"]*)\" times on Log In screen on driver app$")
    public void i_enter_invalid_password_and_click_on_something_button_for_something_times_on_log_in_screen_on_driver_app(String strArg1, String count) throws Throwable {
        try {
            String password=null;
            switch(count) {
                case "5":
                    password = PropertyUtility.getDataProperties("driver.locked.login.password");
                    action.sendKeys(driverLogInPage.TextField_Password(), password);

                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(20000);
                        action.click(driverLogInPage.Button_Login());
                    }
                    break;

                case "3":
                password = PropertyUtility.getDataProperties("driver.locked.login.password");
                action.sendKeys(driverLogInPage.TextField_Password(), password);

                for (int i = 0; i < 3; i++) {
                    Thread.sleep(20000);
                    action.click(driverLogInPage.Button_Login());
                }
                break;

            case "2":
                for (int i = 0; i < 2; i++) {
                    Thread.sleep(20000);
                    action.click(driverLogInPage.Button_Login());
                }
                break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I Login as a driver and go online$")
    public void i_login_as_a_driver_and_go_online() throws Throwable {
        String phoneNumber =(String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
        String password="Cci12345";
        try {
            utility.loginToDriverApp(phoneNumber, password);
        }
        catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful",
                "Error performing step,Please check logs for more details", true);
    }
    }

}
