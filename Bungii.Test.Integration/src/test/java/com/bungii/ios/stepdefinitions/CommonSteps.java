package com.bungii.ios.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.ScheduledTripsPage;
import com.bungii.ios.pages.customer.*;
import com.bungii.ios.pages.driver.BungiiCompletedPage;
import com.bungii.ios.pages.driver.BungiiRequestPage;
import com.bungii.ios.pages.driver.DriverBungiiDetailsPage;
import com.bungii.ios.pages.driver.TripDetailsPage;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.stepdefinitions.customer.HomeSteps;
import com.bungii.ios.stepdefinitions.customer.LogInSteps;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;


public class CommonSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(CommonSteps.class);
    ActionManager action = new ActionManager();
    String Image_Solo = "bungii_type-solo", Image_Duo = "bungii_type-duo";
    private EstimatePage estimatePage;
    private HomePage homePage;
    private com.bungii.ios.pages.driver.HomePage driverHomePage;
    private BungiiDetails customerbungiiDetails;
    private DriverBungiiDetailsPage driverbungiiDetailspage;
    private SuccessPage successPage;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private TripDetailsPage tripDetails;
    private com.bungii.ios.pages.driver.UpdateStatusPage driverUpdateStatusPage;
    private com.bungii.ios.pages.customer.UpdateStatusPage customerUpdateStatusPage;
    private TripDetailsPage tripDetailsPage;
    private BungiiCompletedPage driverBungiiCompletedPage;
    private BungiiCompletePage customerBungiiCompletePage;
    private PromotionPage promotionPage;
    private ScheduledTripsPage scheduledTripsPage;
    private ScheduledBungiiPage customerScheduledBungiiPage;
    private com.bungii.ios.pages.driver.ScheduledBungiiPage driverScheduledBungiiPage;
    private FaqPage faqPage;
    private PaymentPage paymentPage;
    private SupportPage supportPage;
    private PromosPage promosPage;
    private AccountPage accountPage;
    private ScheduledBungiiPage scheduledBungiiPage;
    private InvitePage invitePage;
    private ForgotPasswordPage forgotPasswordPage;
    private VerificationPage verificationPage;
    private SearchingPage searchingPage;
    private DriverNotAvailablePage driverNotAvailablePage;
    private NotificationPage notificationPage;
    private BungiiRequestPage bungiiRequestPage;
    private BungiiAcceptedPage bungiiAcceptedPage;
    private TermsAndConditionPage termsAndConditionPage;
    private EnableNotificationPage enableNotificationPage;
    private EnableLocationPage enableLocationPage;
    private TutorialPage tutorialPage;

    public CommonSteps(FaqPage faqPage, ScheduledBungiiPage scheduledBungiiPage, AccountPage accountPage,
                       PaymentPage paymentPage, SupportPage supportPage, PromosPage promosPage, EstimatePage estimatePage,
                       HomePage homePage, LoginPage loginPage, SignupPage signupPage,
                       ScheduledBungiiPage customerScheduledBungiiPage,
                       com.bungii.ios.pages.driver.ScheduledBungiiPage driverScheduledBungiiPage, BungiiDetails bungiiDetails,
                       TripDetailsPage tripDetails, DriverBungiiDetailsPage driverbungiiDetailspage,
                       com.bungii.ios.pages.driver.UpdateStatusPage updateStatusPage, SuccessPage successPage, TripDetailsPage tripDetailsPage,
                       BungiiCompletedPage bungiiCompletedPage, BungiiCompletePage customerBungiiCompletePage,
                       PromotionPage promotionPage, ScheduledTripsPage scheduledTripsPage, InvitePage invitePage,
                       ForgotPasswordPage forgotPasswordPage, SearchingPage searchingPage,
                       DriverNotAvailablePage driverNotAvailablePage, NotificationPage notificationPage,
                       BungiiRequestPage bungiiRequestPage, com.bungii.ios.pages.customer.UpdateStatusPage customerUpdateStatusPage,
                       BungiiAcceptedPage bungiiAcceptedPage, com.bungii.ios.pages.driver.HomePage driverHomePage,
                       VerificationPage verificationPage, TermsAndConditionPage termsAndConditionPage, EnableNotificationPage enableNotificationPage, EnableLocationPage enableLocationPage, TutorialPage tutorialPage) {
        this.estimatePage = estimatePage;
        this.homePage = homePage;
        this.loginPage = loginPage;
        this.signupPage = signupPage;
        this.customerbungiiDetails = bungiiDetails;
        this.tripDetails = tripDetails;
        this.driverbungiiDetailspage = driverbungiiDetailspage;
        this.driverUpdateStatusPage = updateStatusPage;
        this.successPage = successPage;
        this.tripDetails = tripDetails;
        this.driverBungiiCompletedPage = bungiiCompletedPage;
        this.customerBungiiCompletePage = customerBungiiCompletePage;
        this.promotionPage = promotionPage;
        this.scheduledTripsPage = scheduledTripsPage;
        this.customerScheduledBungiiPage = customerScheduledBungiiPage;
        this.driverScheduledBungiiPage = driverScheduledBungiiPage;
        this.faqPage = faqPage;
        this.paymentPage = paymentPage;
        this.supportPage = supportPage;
        this.promosPage = promosPage;
        this.accountPage = accountPage;
        this.scheduledBungiiPage = scheduledBungiiPage;
        this.invitePage = invitePage;
        this.forgotPasswordPage = forgotPasswordPage;
        this.searchingPage = searchingPage;
        this.driverNotAvailablePage = driverNotAvailablePage;
        this.notificationPage = notificationPage;
        this.bungiiRequestPage = bungiiRequestPage;
        this.customerUpdateStatusPage = customerUpdateStatusPage;
        this.bungiiAcceptedPage = bungiiAcceptedPage;
        this.driverHomePage = driverHomePage;
        this.termsAndConditionPage = termsAndConditionPage;
        this.verificationPage = verificationPage;
        this.enableNotificationPage = enableNotificationPage;
        this.enableLocationPage = enableLocationPage;
        this.tutorialPage = tutorialPage;
    }


    @Then("^\"([^\"]*)\" message should be displayed on \"([^\"]*)\" page$")
    public void something_message_should_be_displayed_on_something_page(String messageElement, String screen) {
        try {
            boolean messageDisplayed = false;

            switch (messageElement.toUpperCase()) {
                case "BUNGII CANCEL":
                    messageDisplayed = scheduledTripsPage.isElementEnabled(scheduledTripsPage.Text_Success()) && scheduledTripsPage.Text_Success().getText().equals(PropertyUtility.getMessage("admin.cancel.sucess"));
                    break;
                case "ADD NEW CARD":
                    messageDisplayed = action.getValueAttribute(paymentPage.Text_AddInfo()).equals(PropertyUtility.getMessage("customer.payment.add")) && paymentPage.isElementEnabled(paymentPage.Text_AddHeader());
                    break;
                case "FORGOT PASSWORD INFORMATION":
                    messageDisplayed = action.getValueAttribute(forgotPasswordPage.Text_Info())
                            .equals(PropertyUtility.getMessage("customer.forgotpassword.info"));
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

    @Then("^\"([^\"]*)\" should be present in \"([^\"]*)\" screen$")
    public void something_should_be_present_in_something_screen(String button, String screen) {

        try {
            boolean isFound = false;
            switch (button.toUpperCase()) {
                case "GET ESTIMATE":
                    // homePage.clickEstimateButton();
                    break;
                case "INVITE REFERRALS":
                    isFound = homePage.isElementEnabled((homePage.Button_Invite()));
                    break;
                case "BUNGII CUSTOMER LOGO":
                    isFound = supportPage.isElementEnabled(supportPage.Image_CustLogo());
                    break;
                case "SUPPORT QUESTION":
                    isFound = action.getValueAttribute(supportPage.Text_SupportQuestion()).equals(PropertyUtility.getMessage("customer.support.question")) && action.getValueAttribute(supportPage.Text_SupportLabelQuestion()).equals(PropertyUtility.getMessage("customer.support.question.label"));
                    break;
                case "ADD IMAGE":
                    isFound = paymentPage.isElementEnabled(paymentPage.Image_Add());
                    break;
                case "ADD":
                    isFound = paymentPage.isElementEnabled(paymentPage.Button_ADD());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name",
                            "UnImplemented Step");
                    break;
            }

            testStepVerify.isTrue(isFound,
                    button + "should be present in " + screen + " screen",
                    button + "is present in " + screen + " screen", button + "is not present in " + screen + " screen");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" screen$")
    public void iClickButtonOnScreen(String button, String screen) {
        try {

            switch (button.toUpperCase()) {
                case "GET ESTIMATE":
                    action.click(homePage.Button_GetEstimate());
                    break;
                case "INVITE REFERRALS":
                    action.click(homePage.Button_Invite());
                    break;
                case "SHARE":
                    action.click(invitePage.Button_Share());
                    break;
                case "LOG IN":
                    if (screen.equalsIgnoreCase("log in"))
                        action.click(loginPage.Button_Login());
                    else if (screen.equalsIgnoreCase("sign up"))
                        action.click(signupPage.Button_Login());
                    else
                        System.err.println("test");
                    // none, error
                    break;
                case "SIGN UP":
                    if (screen.equalsIgnoreCase("SIGN UP")) {
                        action.hideKeyboard();
                        action.swipeUP();
                        action.click(signupPage.Button_Signup());
                    } else
                        action.click(loginPage.Button_SignUp());
                    break;
                case "DONE":
                    if (screen.equalsIgnoreCase("invite"))
                        action.click(invitePage.Button_Done());
                    else {
                        action.swipeUP();
                        if (!action.isElementPresent(successPage.Button_Done(true)))
                            action.swipeUP();
                        action.click(successPage.Button_Done());
                    }
                    break;
                case "ON TO THE NEXT ONE":
                    //sometime earning popup comes late
                    if (action.isAlertPresent()) {
                        logger.detail("Alert message" + action.getAlertMessage());
                        ;
                        SetupManager.getDriver().switchTo().alert().dismiss();
                        Thread.sleep(1000);
                    }
                    action.click(driverBungiiCompletedPage.Button_NextTrip());
                    break;
                case "I DON'T LIKE FREE MONEY":
                    takeActionOnPromotion("REJECT");
                    break;
                case "PICK UP CLEAR TEXT":
                    action.click(homePage.Button_ClearPickup());
                    break;
                case "DROP CLEAR TEXT":
                    action.click(homePage.Button_ClearDrop());
                    break;
                case "CANCEL":
                    if (screen.equalsIgnoreCase("payment"))
                        action.click(paymentPage.Button_Cancel());
                    else if (screen.equalsIgnoreCase("update"))
                        action.click(driverUpdateStatusPage.Button_Cancel());
                    else
                        action.click(estimatePage.Button_Cancel());
                    break;
                case "SEND":
                    if (screen.equalsIgnoreCase("forget password"))
                        action.click(forgotPasswordPage.Button_Send());
                    else
                        action.click(supportPage.Button_Send());
                    break;
                case "SMS":
                    if (screen.equalsIgnoreCase("customer-Update"))
                        action.click(customerUpdateStatusPage.Button_Sms());
                    else
                        action.click(driverUpdateStatusPage.Button_Sms());
                    break;
                case "CALL":
                    if (screen.equalsIgnoreCase("customer-Update"))
                        action.click(customerUpdateStatusPage.Button_Call());
                    else
                        action.click(driverUpdateStatusPage.Button_Call());
                    break;
                case "ADD":
                    if (screen.equalsIgnoreCase("Estimate"))
                        action.click(estimatePage.Button_AddPromoCode());
                    else
                        action.click(promosPage.Button_Add());
                    break;
                case "GET MORE MONEY":
                    action.click(promosPage.Button_GetMoreMoney());
                    break;
                case "ADD NEW":
                    action.click(paymentPage.Button_AddNew());
                    break;
                case "ADD PAYMENT METHOD":
                    action.click(paymentPage.Button_AddPayment());
                    break;
                case "SAVE":
                    action.click(paymentPage.Button_Save());
                    break;
                case "FORGOT PASSWORD":
                    action.click(loginPage.Button_ForgotPassword());
                    break;
                case "CONTINUE":
                    action.nextFieldKeyboard();
                    action.click(forgotPasswordPage.Button_Continue());
                    break;
                case "BACK":
                    action.click(forgotPasswordPage.Button_Back());
                    break;
                case "REQUEST BUNGII":
                    action.click(estimatePage.Button_RequestBungii());
                    break;
                case "OK":
                    if (screen.equalsIgnoreCase("BUNGII ACCEPTED"))
                        //bungiiAcceptedPage.clickOkButton();
                        bungiiAcceptedPage.Button_Ok().click();
                    else
                        action.click(driverNotAvailablePage.Button_OK());
                    break;
                case "ACCEPT":
                    action.click(bungiiRequestPage.Button_Accept());
                    break;
                case "REJECT":
                    action.click(bungiiRequestPage.Button_Reject());
                    break;
                case "SHARE ON FACEBOOK":
                case "SHARE ON TWITTER":
                case "SHARE BY EMAIL":
                case "SHARE BY TEXT MESSAGE":
                    shareInviteCode(button);
                    break;
                case"CLOSE BUTTON":
                    action.click(customerBungiiCompletePage.Button_Close());;
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

    /**
     * Accept or reject on promotion page
     *
     * @param actions Take action on promotion page
     * @return if action is taken or nots
     */
    public boolean takeActionOnPromotion(String actions) {
        boolean isClicked = false;
        switch (actions.toUpperCase()) {
            case "ACCEPT":
                promotionPage.Button_AcceptPromo().click();
                isClicked = true;
                break;
            case "REJECT":
                promotionPage.Button_IdontLikePromo().click();
                isClicked = true;
                break;
            default:
                break;
        }

        return isClicked;
    }

    /**
     * Click on Invite code button for social media platform
     *
     * @param target Identifer for share option
     */
    public void shareInviteCode(String target) {
        if (target.contains("FACEBOOK")) {
            action.click(invitePage.Button_Facebook());
        } else if (target.contains("TWITTER")) {
            action.click(invitePage.Button_Twitter());
        } else if (target.contains("EMAIL")) {
            action.click(invitePage.Button_Email());
        } else if (target.contains("TEXT")) {
            action.click(invitePage.Button_TextMessage());
        }
    }

    @Then("^I should be navigated to \"([^\"]*)\" screen$")
    public void i_should_be_naviagated_to_something_screen(String screen) {
        try {
            boolean isCorrectPage = false;

            GeneralUtility utility = new GeneralUtility();
            isCorrectPage = utility.verifyPageHeader(screen);
            testStepVerify.isTrue(isCorrectPage, "I should be naviagated to " + screen + " screen",
                    "I should be navigated to " + screen, "I was not navigated to " + screen + "screen ");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I have \"([^\"]*)\" app \"([^\"]*)\"$")
    public void i_have_something_app_something(String appName, String expectedOutcome) throws Throwable {
        try {
            boolean isAppInstalled = false;
            switch (appName.toUpperCase()) {
                case "TWITTER":
                    isAppInstalled = ((IOSDriver) (SetupManager.getDriver())).isAppInstalled(PropertyUtility.getDataProperties("twitter.bundle.ios.id"));
                    break;
                case "FACEBOOK":
                    isAppInstalled = ((IOSDriver) (SetupManager.getDriver())).isAppInstalled(PropertyUtility.getDataProperties("facebook.bundle.ios.id"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            switch (expectedOutcome.toUpperCase()) {
                case "INSTALLED":
                    testStepAssert.isTrue(isAppInstalled, appName + " should be installed", appName + " is Not installed");
                    break;
                case "NOT INSTALLED":
                    testStepAssert.isFalse(isAppInstalled, appName + " should be installed", appName + " is installed");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I am on the \"([^\"]*)\" page$")
    public void i_am_on_the_something_page(String screen) {
        try {
            String NavigationBarName = action.getNameAttribute(homePage.Text_NavigationBar());
            switch (screen.toUpperCase()) {
                case "LOG IN":
                    goToLogInPage(NavigationBarName);
                    break;
                case "SIGN UP":
                    goToSignUpPage(NavigationBarName);
                    break;
                case "HOME":
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
    }

    public void goToLogInPage(String navigationBarName) throws Throwable {
        HomeSteps homeSteps = new HomeSteps(homePage);

        if (!navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))) {

            if (navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.signup")))
                iClickButtonOnScreen("LOG IN", "sign up");
            else {
                homeSteps.i_select_something_from_customer_app_menu("LOGOUT");
            }
        }
    }

    public void goToSignUpPage(String navigationBarName) throws Throwable {
        HomeSteps homeSteps = new HomeSteps(homePage);

        if (!navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.signup"))) {

            if (navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login")))
                iClickButtonOnScreen("SIGN UP", "sign up");
            else {
                homeSteps.i_select_something_from_customer_app_menu("LOGOUT");
                iClickButtonOnScreen("SIGN UP", "sign up");

            }
        }
    }


    @When("^I Switch to \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_switch_to_something_application_on_something_devices(String appName, String device) {
        try {
            String appHeader = "";
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(1000);
            }
            //Vishal[20092019]: added terminate before switching the app, works faster
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    //action.switchApplication(PropertyUtility.getProp("bundleId_Driver"));
                    ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    appHeader = "Bungii Driver";
                    break;
                case "CUSTOMER":
                    ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    appHeader = "Bungii";
                    //action.switchApplication(PropertyUtility.getProp("bundleId_Customer"));
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }        //temp fixed
            new GeneralUtility().handleIosUpdateMessage();
            if (!action.getNameAttribute(homePage.Application_Name()).equals(appHeader)) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                        break;
                    case "CUSTOMER":
                        ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                        break;
                }

            }
            pass("Switch to " + appName + " application",
                    "Switch to " + appName + " application", true);
            cucumberContextManager.setFeatureContextContext("CURRENT_APPLICATION", appName.toUpperCase());

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);

        }
    }
    //This is same as above method except apps are not reminated before starting
    @When("^I open \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_open_something_application_on_something_devices(String appName, String device) throws Throwable {
        try {
            String appHeader = "";
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(1000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    appHeader = "Bungii Driver";
                    break;
                case "CUSTOMER":
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    appHeader = "Bungii";
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }        //temp fixed
            new GeneralUtility().handleIosUpdateMessage();
            if (!action.getNameAttribute(homePage.Application_Name()).equals(appHeader)) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                        break;
                    case "CUSTOMER":
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                        break;
                }

            }
            pass("Open " + appName + " application on "+device+" devices",
                    "Open " + appName + " application"+device+" devices", true);
            cucumberContextManager.setFeatureContextContext("CURRENT_APPLICATION", appName.toUpperCase());

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);

        }    }

    // TODO change catch to error
    @Then("^Alert message with (.+) text should be displayed$")
    public void alert_message_with_text_should_be_displayed(String message) {
        try {
            action.waitForAlert();
            String actualMessage = action.getAlertMessage();
            String expectedMessage;
            switch (message.toUpperCase()) {
                case "INVALID_PASSWORD":
                    expectedMessage = PropertyUtility.getMessage("customer.error.invalidpassword");
                    break;
                case "EMPTY_FIELD":
                    expectedMessage = PropertyUtility.getMessage("customer.error.emptyfield");
                    break;
                case "DELETE WARNING":
                    expectedMessage = PropertyUtility.getMessage("customer.payment.delete");
                    break;
                case "NO PROMO CODE":
                    expectedMessage = PropertyUtility.getMessage("customer.signup.nopromo");
                    break;
                case "SCHEDULE BUNGII OPTION":
                    expectedMessage = PropertyUtility.getMessage("customer.driver.unavailable.schedule");
                    break;
                case "ACCEPT BUNGII QUESTION":
                    expectedMessage = PropertyUtility.getMessage("driver.bungii.request.ondemand.question");
                    break;
                case "DRIVER CANCELLED":
                    expectedMessage = PropertyUtility.getMessage("customer.alert.driver.cancel");
                    break;
                case "DRIVER CANCEL BUNGII":
                    expectedMessage = PropertyUtility.getMessage("driver.cancel.bungii");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            testStepVerify.isEquals(actualMessage, expectedMessage,
                    "Alert with text" + expectedMessage + "should be displayed",
                    "Alert with text ," + expectedMessage + " should be displayed",
                    "Alert Message is not displayed, actual Message" + actualMessage + " Expected is "
                            + expectedMessage);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" on alert message$")
    public void i_click_something_on_alert_message(String buttonLabel) {
        try {
            boolean clicked = action.clickAlertButton(buttonLabel);

            testStepAssert.isTrue(clicked,
                    "Clicked on " + buttonLabel + " button", "Alert Message with " + buttonLabel + "button not displayed");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I accept Alert message$")
    public void iAcceptAlertMessage() {
        try {
            SetupManager.getDriver().switchTo().alert().accept();
            log("Alert Message should be accepted", "Alert Message is accepted");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I reject Alert message$")
    public void iRejectAlertMessage() {
        try {
            SetupManager.getDriver().switchTo().alert().dismiss();
            log("Alert Message should be reject", "Alert Message is reject");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @When("^I logged in Customer application using  \"([^\"]*)\" user$")
    public void i_logged_in_customer_application_using_something_user(String key) {
        try {
            String NavigationBarName = action.getNameAttribute(homePage.Text_NavigationBar());
            String userName = "", password = "";
            switch (key.toLowerCase()) {
                case "existing":
                    userName = PropertyUtility.getDataProperties("customer.user");
                    password = PropertyUtility.getDataProperties("customer.password");
                    break;
                case "new":
                    userName = PropertyUtility.getDataProperties("new.customer.user");
                    password = PropertyUtility.getDataProperties("new.customer.password");
                    break;
                case "referral":
                    userName = PropertyUtility.getDataProperties("referral.customer.phone");
                    password = PropertyUtility.getDataProperties("referral.customer.password");
                    break;
                case "customer-duo":
                    userName = PropertyUtility.getDataProperties("customer.phone.usedin.duo");
                    password = PropertyUtility.getDataProperties("customer.password.usedin.duo");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name.usedin.duo"));
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
            goToLogInPage(NavigationBarName);

            LogInSteps logInSteps = new LogInSteps(loginPage);
            logInSteps.i_enter_valid_and_as_per_below_table(userName, password);
            iClickButtonOnScreen("Log In", "Log In");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    @When("^I open new \"([^\"]*)\" browser for \"([^\"]*)\"$")
    public void i_open_new_something_browser_for_something_instance(String browser, String instanceName) {
        try {

            SetupManager.getObject().createNewWebdriverInstance(instanceName, browser);
            SetupManager.getObject().useDriverInstance(instanceName);
            log(
                    "I open new " + browser + " browser for " + instanceName + " instance$",
                    "I open new " + browser + " browser for " + instanceName + " instance$", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I connect to \"([^\"]*)\" using \"([^\"]*)\" instance$")
    public void i_connect_to_something_using_something_instance(String deviceId, String instanceName) {
        try {
            SetupManager.getObject().createNewAppiumInstance(instanceName, deviceId);
            SetupManager.getObject().useDriverInstance(instanceName);
            log(
                    "I connect to  " + deviceId + " using " + instanceName + " instance$",
                    "I connect to " + deviceId + "using " + instanceName + " instance$", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I switch to \"([^\"]*)\" instance$")
    public void i_switch_to_something_instance(String instanceName) {
        try {
            SetupManager.getObject().useDriverInstance(instanceName);
            log("I switch to  " + instanceName + "instance",
                    "I switch to  " + instanceName + "instance", false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I Select Trip from scheduled trip$")
    public void i_select_trip_from_scheduled_trip() {
        try {
            String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            String currentApplication = (String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION");

            //   tripNoOfDriver="DUO";tripTime="Jan 10, 07:15 PM GMT+5:30";currentApplication="DRIVER";

            if (tripTime.contains(PropertyUtility.getDataProperties("time.label")))
                tripTime = tripTime.replace(PropertyUtility.getDataProperties("time.label"), "").trim();

            if (currentApplication.equalsIgnoreCase("CUSTOMER")) {
                //customerScheduledBungiiPage.selectBungiiFromList(tripNoOfDriver, tripTime);
                String imageTag = "";
                if (tripNoOfDriver.toUpperCase().equals("SOLO")) {
                    imageTag = Image_Solo;
                } else if (tripNoOfDriver.toUpperCase().equals("DUO")) {
                    imageTag = Image_Duo;
                }

                action.swipeDown();

                WebElement Image_SelectBungii = scheduledBungiiPage.findElement("//XCUIElementTypeStaticText[@name='" + tripTime + "']/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);
                action.click(Image_SelectBungii);
            } else {
                //driverScheduledBungiiPage.selectBungiiFromList(tripNoOfDriver, tripTime);

                if (!action.isAlertPresent()) {
                    String imageTag = "";
                    if (tripNoOfDriver.toUpperCase().equals("SOLO")) {
                        imageTag = Image_Solo;
                    } else if (tripNoOfDriver.toUpperCase().equals("DUO")) {
                        imageTag = Image_Duo;
                    }

                    action.swipeDown();
                    Thread.sleep(2000);
                    //vishal[0801]:Commented due to new build
                    //WebElement Image_SelectBungii = driverScheduledBungiiPage.findElement("//XCUIElementTypeStaticText[@name=“" + tripTime + "”]/following-sibling::XCUIElementTypeImage[@name=“" + imageTag + "”]/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);
                    WebElement Image_SelectBungii = scheduledBungiiPage.findElement("//XCUIElementTypeStaticText[contains(@name,'" + tripTime + "')]/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath, true);
                    if (Image_SelectBungii == null) {
                        Thread.sleep(30000);
                        action.swipeDown();
                        Image_SelectBungii = scheduledBungiiPage.findElement("//XCUIElementTypeStaticText[contains(@name,'" + tripTime + "')]/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath, true);
                    }
                    action.click(Image_SelectBungii);
                } else {
                    //If alert is present accept it , it will automatically select Bungii
                    SetupManager.getDriver().switchTo().alert().accept();
                }

            }
            log("I Select Trip from scheduled trip ",
                    "I Selected Trip from scheduled trip", true);

        } catch (Exception e) {
            logger.error("Error performing  step" + e.getMessage());
            e.printStackTrace();
            //logger.error("Error performing step" + e.printStackTrace());

            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    public void navigateFromTermToHomeScreen() {
        action.click(termsAndConditionPage.Button_CheckOff());
        action.click(termsAndConditionPage.Button_Continue());
        if (action.isElementPresent(enableNotificationPage.Button_Sure(true))) {
            action.click(enableNotificationPage.Button_Sure());
            action.clickAlertButton("Allow");
        }

        if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
            action.click(enableLocationPage.Button_Sure());
            action.clickAlertButton("Allow");
        }

        action.click(tutorialPage.Button_Close());


    }

    @Given("^I am on Customer logged in Home page$")
    public void iAmOnCustomerLoggedInHomePage() {
        try {
            LogInSteps logInSteps = new LogInSteps(new LoginPage());
            HomeSteps homeSteps = new HomeSteps(homePage);
            String NavigationBarName = action.getNameAttribute(homePage.Text_NavigationBar());

            if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))
                    || NavigationBarName.equals("SIGN UP")) {
                if (NavigationBarName.equals("SIGN UP"))
                    iClickButtonOnScreen("LOG IN", "sign up");

                logInSteps.i_enter_valid_and_as_per_below_table(PropertyUtility.getDataProperties("customer.user"),
                        PropertyUtility.getDataProperties("customer.password"));


                iClickButtonOnScreen("Log In", "Log In");

                NavigationBarName = action.getNameAttribute(homePage.Text_NavigationBar());
                if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                    //DO Nothing
                } else if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                    navigateFromTermToHomeScreen();
                }

                //homeSteps.user_should_be_successfully_logged_in_to_the_system();
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                // do nothing
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
                iClickButtonOnScreen("CANCEL", "SEARCHING");
                iAcceptAlertMessage();
                //iRejectAlertMessage();
            } else if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                navigateFromTermToHomeScreen();
            } else if (NavigationBarName.equalsIgnoreCase("NOTIFICATIONS")) {
                action.click(enableNotificationPage.Button_Sure());
                action.clickAlertButton("Allow");
                if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
                    action.click(enableLocationPage.Button_Sure());
                    action.clickAlertButton("Allow");
                }
            }
            else {
                homeSteps.i_select_something_from_customer_app_menu("HOME");
            }
            cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer.user"));
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    //Except first time all code is fetch on fly, first time is read from file
    @SuppressWarnings("unchecked")
    public List<String> getRefferalCode(String codeType) {
        List<String> code = new ArrayList<String>();
        switch (codeType.toLowerCase()) {
            case "referral":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("REFERRAL");
                break;
            case "valid":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("VALID");
                break;
            case "promo":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("PROMO");
                break;

            case "expired":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("EXPIRED");
                break;
            case "one off":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("ONE_OFF");
                break;
            case "used one off":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("USED_ONE_OFF");
                break;
            case "unused one off":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("UNUSED_ONE_OFF");
                break;
            case "first time only":
                code = Arrays.asList(PropertyUtility.getDataProperties("promocode.firsttime"));
                break;
            default:
                code.add(codeType);
                break;
        }
        return code;
    }

    public String generateMobileNumber() {

        String phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");
        while (!DbUtility.isPhoneNumberUnique(phoneNumber)) {
            phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");

        }
        return phoneNumber;
    }

    @When("^I Enter \"([^\"]*)\" value in \"([^\"]*)\" field in \"([^\"]*)\" Page$")
    public void iEnterValueInFieldInPage(String value, String field, String screen) {

        try {
            String inputValue = RandomGeneratorUtility.getData(value, 10);

            if (!value.equalsIgnoreCase("{RANDOM_PHONE_NUM}")) {
                inputValue = value.equalsIgnoreCase("{EMPTY}") ? "     " : inputValue;
                inputValue = value.equalsIgnoreCase("{BLANK}") ? "" : inputValue;
            } else {
                inputValue = generateMobileNumber();
            }

            switch (field.toUpperCase()) {
                case "SUPPORT TEXTBOX":
                    action.clearEnterText(supportPage.TextBox_Support(), inputValue);
                    break;
                case "FIRST NAME":
                    action.clearEnterText(signupPage.Textfield_FirstName(), inputValue);
                    break;
                case "LAST NAME":
                    action.clearEnterText(signupPage.Textfield_LastName(), inputValue);
                    action.hideKeyboard();
                    break;
                case "EMAIL":
                    action.clearEnterText(signupPage.Textfield_Email(), inputValue);
                    action.hideKeyboard();
                    break;
                case "PHONE NUMBER":
                    if (screen.equalsIgnoreCase("FORGOT PASSWORD")) {
                        inputValue = value.equalsIgnoreCase("{VALID USER}") ? PropertyUtility.getDataProperties("new.customer.user") : inputValue;
                        action.clearEnterText(forgotPasswordPage.Text_InputNumber(), inputValue);
                        cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", inputValue);
                    } else {
                        inputValue = value.equalsIgnoreCase("{VALID USER}") ? PropertyUtility.getDataProperties("new.customer.user") : inputValue;
                        action.clearEnterText(signupPage.Textfield_Phonenumber(), inputValue);
                        cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", inputValue);
                    }
                    break;
                case "PASSWORD":
                    action.clearEnterText(signupPage.Textfield_Password(), inputValue);
                    break;
                case "REFERRAL CODE":
                    List<String> inputValueList = getRefferalCode(inputValue);
                    action.clearEnterText(signupPage.Textfield_PromoCode(), inputValueList.get(0));
                    // cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputValue);
                    cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputValueList.get(0));
                    break;
                case "PROMO CODE":
                    List<String> ValueList = getRefferalCode(inputValue);
                    action.clearEnterText(promosPage.TextBox_EnterCode(), ValueList.get(0));
                    //  cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputValue);
                    cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", ValueList.get(0));
                    break;
                case "SMS CODE":
                    inputValue = inputValue.equalsIgnoreCase("valid") ? (String) cucumberContextManager.getScenarioContext("SMS_CODE")
                            : "111";
                    action.clearEnterText(forgotPasswordPage.Text_SmsCode(), inputValue);
                    action.hideKeyboard();

                    break;
                case "NEW PASSWORD":
                    action.clearEnterText(forgotPasswordPage.Text_Password(), inputValue);
                    action.hideKeyboard();
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            log("I should able to Enter " + value + " value in " + field + " field in " + screen + " Page",
                    "I Entered " + inputValue + " in " + field + " field", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I install Bungii App again$")
    public void i_reset_bungii_app_data() {
        try {
            GeneralUtility utility = new GeneralUtility();
            boolean isNewInstalled = utility.installCustomerApp();
            testStepAssert.isTrue(isNewInstalled, "I should able to install bungii App again", "I was not able to install bungii app again");
            log("I install Bungii",
                    "I installed Bungii", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^user is alerted for \"([^\"]*)\"$")
    public void user_is_alerted_for_something(String key) {
        try {
            action.waitForAlert();
            if (!action.isAlertPresent())
                action.waitForAlert();
            String expectedText = "";
            switch (key.toUpperCase()) {
                case "ALREADY SCHEDULED BUNGII":
                    expectedText = PropertyUtility.getMessage("customer.alert.alreadyscheduled");
                    break;
                case "SUPPORT QUESTION SUBMITTED":
                    expectedText = PropertyUtility.getMessage("customer.support.submitted");
                    break;
                case "EMPTY SUPPORT QUESTION":
                    expectedText = PropertyUtility.getMessage("customer.support.emptyfield");
                    break;
                case "NO TWITTER INSTALLED":
                    expectedText = PropertyUtility.getMessage("customer.invite.notwitter");
                    break;
                case "EXPIRED PROMO":
                    expectedText = PropertyUtility.getMessage("customer.promos.expired");
                    break;
                case "INVALID PROMO":
                    expectedText = PropertyUtility.getMessage("customer.promos.invalid");
                    break;
                case "EMPTY SIGNUP FIELD":
                    expectedText = PropertyUtility.getMessage("customer.signup.emptyfield");
                    break;
                case "EXISTING USER":
                    expectedText = PropertyUtility.getMessage("customer.signup.existinguser");
                    break;
                case "INVALID EMAIL WHILE SIGNUP":
                    expectedText = PropertyUtility.getMessage("customer.signup.invalidemail");
                    break;
                case "INVALID PHONE WHILE SIGNUP":
                    expectedText = PropertyUtility.getMessage("customer.signup.invalidphonenumber");
                    break;
                case "INVALID PASSWORD WHILE SIGNUP":
                    expectedText = PropertyUtility.getMessage("customer.signup.invalidpassword");
                    break;
                case "INVALID PROMO WHILE SIGNUP":
                    expectedText = PropertyUtility.getMessage("customer.signup.invalidpromo");
                    break;
                case "REFERRAL FOR NEW USER":
                    expectedText = PropertyUtility.getMessage("customer.promos.referral.error");
                    break;
                case "FIRST TIME ONLY PROMO":
                    expectedText = PropertyUtility.getMessage("customer.promos.first.time.error");
                    break;
                case "ALREADY EXISTING CODE":
                    expectedText = PropertyUtility.getMessage("customer.promos.already.existing.code");
                    break;
                case "FAILED TO SEND TOKEN":
                    expectedText = PropertyUtility.getMessage("customer.forgotpassword.failed.reset");
                    break;
                case "PASSWORD CHANGE SUCCESS":
                    expectedText = PropertyUtility.getMessage("customer.forgotpassword.sucess");
                    break;
                case "INVALID SMS CODE":
                    expectedText = PropertyUtility.getMessage("customer.forgotpassword.invalid.code");
                    break;
                case "INVALID PASSWORD WHILE RESET":
                    expectedText = PropertyUtility.getMessage("customer.forgotpassword.invalid.password");
                    break;
                case "CANCEL BUNGII":
                    expectedText = PropertyUtility.getMessage("customer.alert.cancel.bungii");
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            String alertText = SetupManager.getDriver().switchTo().alert().getText();
            testStepVerify.isEquals(alertText, expectedText);
            SetupManager.getDriver().switchTo().alert().accept();
            Thread.sleep(1000);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


}
