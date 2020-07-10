package com.bungii.ios.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.ScheduledTripsPage;
import com.bungii.ios.pages.customer.EnableLocationPage;
import com.bungii.ios.pages.driver.*;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.stepdefinitions.driver.HomePageSteps;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.List;

import static com.bungii.common.manager.ResultManager.*;


public class CommonStepsDriver extends DriverBase {
    private static LogUtility logger = new LogUtility(CommonSteps.class);
    ActionManager action = new ActionManager();
    String Image_Solo = "bungii_type-solo", Image_Duo = "bungii_type-duo";
    private TripAlertSettingsPage tripAlertSettingsPage = new TripAlertSettingsPage();
    private BungiiCompletedPage driverBungiiCompletedPage= new BungiiCompletedPage();
    private com.bungii.ios.pages.driver.HomePage driverHomePage;
    private com.bungii.ios.pages.driver.LoginPage driverLoginPage;
    private com.bungii.ios.pages.driver.UpdateStatusPage driverUpdateStatusPage;
    private ScheduledTripsPage scheduledTripsPage;
    private com.bungii.ios.pages.driver.ForgotPasswordPage driverForgotPasswordPage;
    private EnableLocationPage enableLocationPage;

    public CommonStepsDriver(
                       com.bungii.ios.pages.driver.UpdateStatusPage updateStatusPage,
                       ScheduledTripsPage scheduledTripsPage,
                       BungiiRequestPage bungiiRequestPage,
                        com.bungii.ios.pages.driver.HomePage driverHomePage,
                       com.bungii.ios.pages.driver.ForgotPasswordPage driverForgotPasswordPage,  com.bungii.ios.pages.driver.LoginPage driverLoginPage, com.bungii.ios.pages.customer.EnableLocationPage enableLocationPage) {

        this.driverUpdateStatusPage = updateStatusPage;
        this.scheduledTripsPage = scheduledTripsPage;
        this.driverHomePage = driverHomePage;
        this.driverLoginPage=driverLoginPage;
        this.driverForgotPasswordPage=driverForgotPasswordPage;
        this.enableLocationPage=enableLocationPage;

    }

    @Then("^\"([^\"]*)\" message should be displayed on \"([^\"]*)\" page on driverApp$")
    public void something_message_should_be_displayed_on_something_page_driverApp(String messageElement, String screen) {
        try {
            boolean messageDisplayed = false;

            switch (messageElement.toUpperCase()) {
                case "BUNGII CANCEL":
                    messageDisplayed = scheduledTripsPage.isElementEnabled(scheduledTripsPage.Text_Success()) && scheduledTripsPage.Text_Success().getText().equals(PropertyUtility.getMessage("admin.cancel.sucess"));
                    break;
                case "FORGOT PASSWORD INFORMATION":
                    messageDisplayed = action.getValueAttribute(driverForgotPasswordPage.Text_Info())
                            .equals(PropertyUtility.getMessage("driver.forgotpassword.info"));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            testStepVerify.isTrue(messageDisplayed,
                    messageElement + " should be displayed", messageElement + " Message is Displayed",
                    messageElement + " Message is not Displayed");
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }



    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" screen on driverApp$")
    public void iClickButtonOnScreenDriverApp(String button, String screen) {
        try {
            action.hideKeyboard();
            switch (button.toUpperCase()) {
                case "LOG IN":
                    if (screen.equalsIgnoreCase("log in"))
                        action.click(driverLoginPage.Button_Login());
                    else
                        System.err.println("test");
                    // none, error
                    break;
                case "SEND":
                    if (screen.equalsIgnoreCase("forgot password"))
                        action.click(driverForgotPasswordPage.Button_Send());
                    break;
                case "SMS":
                    action.click(driverUpdateStatusPage.Button_Sms());
                    break;
                case "CALL":
                    action.click(driverUpdateStatusPage.Button_Call());
                    break;
                case "FORGOT PASSWORD":
                    action.click(driverLoginPage.Button_ForgotPassword());
                    break;
                case "CONTINUE":
                    action.swipeUP();
                    action.nextFieldKeyboard();
                    action.click(driverForgotPasswordPage.Button_Continue());
                    break;
                case "BACK":
                    action.click(driverForgotPasswordPage.Button_Back());
                    break;
                case "GO ONLINE":
                    action.click(driverHomePage.GoOnline_Btn());
                    break;
                case "GO OFFLINE":
                    action.click(driverHomePage.GoOffline_Btn());
                    break;
                case "AVAILABLE TRIPS":
                    action.click(driverHomePage.Text_AvailableTrips());
                    break;
                case "SMS ALERT":
                    action.click(tripAlertSettingsPage.Button_SMSAlerts());
                    break;
                case "TRIP ALERT":
                    action.click(tripAlertSettingsPage.Button_TripAlerts());
                    break;
                case "ITEMIZED EARNINGS":
                    action.click(driverHomePage.Link_Itemized_Earnings());
                        break;
                default:
                    error("UnImplemented Step or incorrect button name",
                            "UnImplemented Step");
                    break;

            }

            log("Click " + button + " button ",
                    "Clicked " + button + " button", true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should be navigated to \"([^\"]*)\" screen on driverApp$")
    public void i_should_be_naviagated_to_something_screen(String screen) {
        try {
            boolean isCorrectPage = false;

            GeneralUtility utility = new GeneralUtility();
            isCorrectPage = utility.verifyPageHeader(screen);
            testStepAssert.isTrue(isCorrectPage, "I should be naviagated to " + screen + " screen",
                    "I have navigated to " + screen, "I didnt navigate to " + screen + " screen ");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    public String generateMobileNumber() {

        String phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");
        while (!DbUtility.isPhoneNumberUnique(phoneNumber)) {
            phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");

        }
        return phoneNumber;
    }

    @When("^I Enter \"([^\"]*)\" value in \"([^\"]*)\" field in \"([^\"]*)\" Page on driverApp$")
    public void iEnterValueInFieldInPageDriverApp(String value, String field, String screen) {

        try {
            String inputValue = RandomGeneratorUtility.getData(value, 10);

            if (!value.equalsIgnoreCase("{RANDOM_PHONE_NUM}")) {
                inputValue = value.equalsIgnoreCase("{EMPTY}") ? "     " : inputValue;
                inputValue = value.equalsIgnoreCase("{BLANK}") ? "" : inputValue;
            } else {
                inputValue = generateMobileNumber();
            }

            switch (field.toUpperCase()) {
                case "PHONE NUMBER":
                        if (screen.equalsIgnoreCase("FORGOT PASSWORD")) {
                            inputValue = value.equalsIgnoreCase("{VALID USER}") ? PropertyUtility.getDataProperties("ios.valid.driver.phone") : inputValue;
                            action.clearEnterText(driverForgotPasswordPage.Text_InputNumber(), inputValue);
                            cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", inputValue);
                        }
                    break;
                case "SMS CODE":
                    inputValue = inputValue.equalsIgnoreCase("valid") ? (String) cucumberContextManager.getScenarioContext("SMS_CODE")
                            : "111";
                    action.clearEnterText(driverForgotPasswordPage.Text_SmsCode(), inputValue);
                    break;
                case "NEW PASSWORD":
                    action.clearEnterText(driverForgotPasswordPage.Text_Password(), inputValue);
                    break;
                case "CONFIRM PASSWORD":
                    action.clearEnterText(driverForgotPasswordPage.Text_Confirm_Password(), inputValue);
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            action.hideKeyboard();

            log("I should able to Enter " + value + " value in " + field + " field in " + screen + " Page",
                    "I Entered " + inputValue + " in " + field + " field", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @Then("^user is alerted for \"([^\"]*)\" on driverApp$")
    public void user_is_alerted_for_something_driverApp(String key) {
        try {
            action.waitForAlert();
            if (!action.isAlertPresent())
                action.waitForAlert();
            String expectedText = "";
            switch (key.toUpperCase()) {

                case "FAILED TO SEND TOKEN":
                    expectedText = PropertyUtility.getMessage("driver.forgotpassword.failed.reset");
                    break;
                case "PASSWORD CHANGE SUCCESS":
                    expectedText = PropertyUtility.getMessage("driver.forgotpassword.success");
                    break;
                case "INVALID SMS CODE":
                    expectedText = PropertyUtility.getMessage("driver.forgotpassword.invalid.code");
                    break;
                case "INVALID PASSWORD WHILE RESET":
                    expectedText = PropertyUtility.getMessage("driver.forgotpassword.invalid.password");
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            String alertText = SetupManager.getDriver().switchTo().alert().getText();
            testStepVerify.isEquals(alertText, expectedText);
            SetupManager.getDriver().switchTo().alert().accept();
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Alert message with (.+) text should be displayed on driverApp$")
    public void alert_message_with_text_should_be_displayed_driverApp(String message) {
        try {
            action.waitForAlert();
            String actualMessage = action.getAlertMessage();
            String expectedMessage;
            switch (message.toUpperCase()) {
                case "INVALID_PASSWORD":
                    expectedMessage = PropertyUtility.getMessage("driver.error.invalidpassword");
                    break;
                case "EMPTY_FIELD":
                    expectedMessage = PropertyUtility.getMessage("driver.error.emptyfield");
                    break;
                case "YOUR ACCOUNT REGISTRATION IS STILL UNDER PROCESS.":
                    expectedMessage = PropertyUtility.getMessage("driver.error.pending.status");
                    break;
                case "INVALID_PASSWORD_3_TIMES":
                    expectedMessage=PropertyUtility.getMessage("driver.error.invalidpassword.three.times");
                    break;
                case "INVALID_PASSWORD_5_TIMES":
                    expectedMessage=PropertyUtility.getMessage("driver.error.invalidpassword.five.times");
                    break;
                case "HICCUP MESSAGE":
                    expectedMessage=PropertyUtility.getMessage("driver.error.payment.status.pending");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            testStepVerify.isEquals(actualMessage, expectedMessage,
                    "Alert : " + expectedMessage + "should be displayed",
                    "Alert : " + actualMessage + " is displayed",
                    "Alert is not displayed | Actual Message " + actualMessage + " Expected is "
                            + expectedMessage);
        } catch (Throwable e) {
            logger.error("Invalid Password Alert Not Displayed", ExceptionUtils.getStackTrace(e));
            fail("Step should be successful",
                    "Invalid Password Alert Not Displayed", true);
        }
    }

    @And("^I accept Alert message on driverApp$")
    public void iAcceptAlertMessage_DriverApp() {
        try {
            SetupManager.getDriver().switchTo().alert().accept();
            log("Alert Message should be accepted", "Alert Message is accepted");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @Given("^I am on the \"([^\"]*)\" page on driverApp$")
    public void i_am_on_the_something_page_on_driverApp(String screen) {
        try {
            //adding temp page source , can remove later
          //  logger.error("Page source", SetupManager.getDriver().getPageSource());
            String navigationBarName =  action.getNameAttribute(driverHomePage.NavigationBar_Text());
            switch (screen.trim().toUpperCase()) {
                case "LOG IN":
                    goToDriverLogInPage(navigationBarName);
                    break;
                case "HOME":
                    testStepVerify.isEquals(navigationBarName, PropertyUtility.getMessage("driver.home.title.offline"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
    }

    public void goToDriverLogInPage(String navigationBarName) throws Throwable {
        HomePageSteps homeSteps = new HomePageSteps(driverHomePage);
        if (action.isAlertPresent()) {
            String alertMessage = action.getAlertMessage();
            logger.detail("Alert is present on screen, Alert message:" + alertMessage);
            List<String> getListOfAlertButton = action.getListOfAlertButton();
            if (getListOfAlertButton.contains("Done"))
                action.clickAlertButton("Done");

        }
        if(navigationBarName.equalsIgnoreCase("Bungii Completed")){
            action.click(driverBungiiCompletedPage.Button_NextTrip());
            //homeSteps.i_select_something_from_driver_app_memu("LOGOUT");
        }

        if (!navigationBarName.equals(PropertyUtility.getMessage("driver.navigation.login"))) {
            if (navigationBarName.equals("LOCATION"))
            {
                action.click(enableLocationPage.Button_Sure());
                action.clickAlertButton("Always Allow");
            }
                homeSteps.i_select_something_from_driver_app_memu("LOGOUT");
        }


    }


}
