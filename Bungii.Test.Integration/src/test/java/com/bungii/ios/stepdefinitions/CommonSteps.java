package com.bungii.ios.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.manager.AssertManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.DashBoardPage;
import com.bungii.ios.pages.admin.LogInPage;
import com.bungii.ios.pages.admin.PromoCodePage;
import com.bungii.ios.pages.admin.ScheduledTripsPage;
import com.bungii.ios.pages.customer.*;
import com.bungii.ios.pages.driver.BungiiCompletedPage;
import com.bungii.ios.pages.driver.BungiiRequestPage;
import com.bungii.ios.pages.driver.DriverBungiiDetailsPage;
import com.bungii.ios.pages.driver.TripDetailsPage;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.stepdefinitions.customer.HomeSteps;
import com.bungii.ios.stepdefinitions.customer.LogInSteps;
import com.bungii.ios.stepdefinitions.driver.HomePageSteps;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.bungii.ios.stepdefinitions.driver.*;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.SetupManager.setDriver;
import static com.bungii.common.manager.ResultManager.*;


public class CommonSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(CommonSteps.class);
    ActionManager action = new ActionManager();
    String Image_Solo = "bungii_type-solo", Image_Duo = "bungii_type-duo";
    private EstimatePage estimatePage;
    private com.bungii.ios.pages.customer.HomePage homePage;
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
    private PromoCodePage promosCodePage;
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
    private LogInPage logInPage;
    DashBoardPage dashBoardPage;
    private DbUtility dbUtility = new DbUtility();
    private BungiiDetails bungiiDetails = new BungiiDetails();
    private GeneralUtility utility = new GeneralUtility();
    private com.bungii.ios.pages.driver.HomePage driverhomepage;

    public CommonSteps(com.bungii.ios.pages.driver.HomePage driverhomepage, DashBoardPage dashBoardPage, LogInPage logInPage, PromoCodePage promosCodePage, FaqPage faqPage, ScheduledBungiiPage scheduledBungiiPage, AccountPage accountPage,
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
        this.promosCodePage = promosCodePage;
        this.logInPage = logInPage;
        this.dashBoardPage = dashBoardPage;
        this.driverhomepage = driverhomepage;
    }


    @Then("^\"([^\"]*)\" message should be displayed on \"([^\"]*)\" page$")
    public void something_message_should_be_displayed_on_something_page(String messageElement, String screen) {
        try {
            boolean messageDisplayed = false;

            switch (messageElement.toUpperCase()) {
                case "BUNGII CANCEL":
                    Thread.sleep(35000);
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
    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" screen for first time promocode$")
    public void iClickButtonOnScreenforFirstTime(String button, String screen) {
        try {

            switch (button.toUpperCase()) {

                case "ADD":
                        action.click(promosPage.Button_Add());
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

    @Then("^I should see \"([^\"]*)\" message$")
    public void i_should_see_something_message(String message) throws Throwable {
        try {

            switch (message) {
                case "No Mail Accounts":
                    String text= action.getAlertMessage().toString();
                    action.clickAlertButton("OK");
                    testStepAssert.isTrue(text.contains("No Mail Accounts"),"No Mail Accounts Popup should be displayed", text +" is displayed",text+" is not displayed");

            }
            log("No Mail Accounts Popup should be displayed",
                    "No Mail Accounts Popup is displayed", true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
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
                case "PROMO CODE LINE":
                    action.click(estimatePage.Row_PromoCode());
                    break;
                case "SAVE MONEY":
                    action.click(scheduledBungiiPage.Button_SaveMoney());
                    break;
                case "RESEND":
                    action.click(forgotPasswordPage.Button_Resend());
                    break;
                case "ADD-BUTTON":
                    action.click(paymentPage.Button_ADD());
                    break;
                case "INFO":
                    action.click(promosPage.Button_Info());
                    break;
                case "INVITE REFERRALS":
                    action.click(homePage.Button_Invite());
                    break;
                case "SHARE":
                    action.click(invitePage.Button_Share());
                    break;
                case "LOG IN":
                    if (screen.equalsIgnoreCase("log in")) {
                        action.click(loginPage.Button_Login());
                       // new GeneralUtility().logCustomerDeviceToken((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA"));
                    }
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
                        //action.click(signupPage.Textfield_Phonenumber()); //added to address swipe
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
                case "YES, I'LL TAKE $5":
                    takeActionOnPromotion("ACCEPT");
                    break;
                case "PICK UP CLEAR TEXT":
                    action.click(homePage.Button_ClearPickup());
                    break;
                case "PICK UP CLEAR BUTTON":
                    action.clickMiddlePoint(homePage.Button_ClearPickup());
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
                    else {
                        action.click(promosPage.Button_Add());
                        if(action.isAlertPresent()) {
                            String alertText = SetupManager.getDriver().switchTo().alert().getText();
                            if(alertText==PropertyUtility.getMessage("customer.select.other.than.first.time.code")) {
                                warning("Alert Displayed Incase First TIme promocode is present", "Alert Received: "+ alertText );
                                SetupManager.getDriver().switchTo().alert().accept();
                            }
                        }
                    }
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
                case "REQUEST YOUR CITY":
                    action.click(homePage.Text_OutOfOffice_RequestCity());
                    break;
                case "SHARE ON FACEBOOK":
                case "SHARE ON TWITTER":
                case "SHARE BY EMAIL":
                case "SHARE BY TEXT MESSAGE":
                    shareInviteCode(button);
                    break;
                case "CLOSE BUTTON":
                    action.click(customerBungiiCompletePage.Button_Close());
                    ;
                    break;
                case "TOP BACK":
                    action.click(bungiiDetails.Button_Back());
                    break;
                case "SCHEDULE BUNGII":
                    action.click(estimatePage.Button_ScheduleBungii());
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

            if (screen.equalsIgnoreCase("Home"))
                screen = "BUNGII";
            Thread.sleep(5000);
            isCorrectPage = utility.verifyPageHeader(screen);
            testStepVerify.isTrue(isCorrectPage, "I should be naviagated to " + screen + " screen",
                    "I should be navigated to " + screen, "I was not navigated to " + screen + " screen ");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "I was not navigated to " + screen + " screen ", true);
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

    @Given("^I login as \"([^\"]*)\" customer and on Home page$")
    public void i_login_as_something_customer_and_on_home_page(String key) throws Throwable {
        i_am_on_the_something_page("LOG IN");
        i_logged_in_customer_application_using_something_user(key);
        acceptCustomerPermissions("TERMS & CONDITIONS" , "ALLOW NOTIFICATIONS" , "ALLOW LOCATION");
        closeTutorial("Tutorial");
        iAmOnCustomerLoggedInHomePage();
    }
    public void acceptCustomerPermissions(String terms, String notification, String location) {
        try {
            GeneralUtility utility = new GeneralUtility();
            Thread.sleep(3000);
            String pageHeader = utility.getPageHeader();

            if(action.isElementPresent(termsAndConditionPage.Button_CheckOff())) {
                action.click(termsAndConditionPage.Button_CheckOff());
                action.click(termsAndConditionPage.Button_Continue());
                Thread.sleep(3000);
                // pageHeader = utility.getPageHeader();
            }
            if(action.isElementPresent(enableNotificationPage.Button_Sure())) {
                action.click(enableNotificationPage.Button_Sure());
                Thread.sleep(3000);
                action.clickAlertButton("Allow");
                Thread.sleep(3000);
                // pageHeader = utility.getPageHeader();
            }
            if(action.isElementPresent(enableLocationPage.Button_Sure())) {
                action.click(enableLocationPage.Button_Sure());
                Thread.sleep(3000);
                action.clickAlertButton("Always Allow");  //Customer App alert for ios 12 and below
                Thread.sleep(3000);
                // pageHeader = utility.getPageHeader();
            }

        } catch (Exception e) {
        }
    }

    public void closeTutorial(String Tutorial) throws Throwable {
        try {
            if(action.isElementPresent(tutorialPage.Button_Close())) {
                action.swipeLeft(tutorialPage.Image_Generictutorialstep());
                action.swipeLeft(tutorialPage.Image_Generictutorialstep());
                action.swipeLeft(tutorialPage.Image_Generictutorialstep());
                action.swipeLeft(tutorialPage.Image_Generictutorialstep());
                action.click(tutorialPage.Button_Start());
                if (action.isAlertPresent()) {
                    String alertMessage = action.getAlertMessage();
                    List<String> getListOfAlertButton = action.getListOfAlertButton();
                    if (alertMessage.contains("we are not operating in your area")) {
                        if (getListOfAlertButton.contains("Done")) {
                            action.clickAlertButton("Done");
                        }
                    }
                }
            }

        } catch (Exception e) {
        }

    }

    @Given("^I am on the \"([^\"]*)\" page$")
    public void i_am_on_the_something_page(String screen) {
        try {

            if (action.isAlertPresent()) {
               // if (action.getAlertMessage().equalsIgnoreCase(PropertyUtility.getMessage("customer.alert.delay.scheduled"))) {
                    warning("I see location popup", "I accepted location popup", true);
                    SetupManager.getDriver().switchTo().alert().accept();

              //  }
            }

            String NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());
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
        if (action.isAlertPresent()) {
            String alertMessage = action.getAlertMessage();
            logger.detail("Alert is present on screen, Alert message:" + alertMessage);
            List<String> getListOfAlertButton = action.getListOfAlertButton();
            if (getListOfAlertButton.contains("Done"))
                action.clickAlertButton("Done");
            else if(alertMessage.contains("Unable to find network connection"))
                action.clickAlertButton("OK");

        }
        if (!navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))) {

            if (navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.signup"))) {
                iClickButtonOnScreen("LOG IN", "sign up");
            } else if (navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
                iClickButtonOnScreen("CANCEL", "SEARCHING");
                iAcceptAlertMessage();
                homeSteps.i_selectlogout();

            } else if (navigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                new GeneralUtility().navigateFromTermToHomeScreen();
                homeSteps.i_selectlogout();
            } else if (navigationBarName.equalsIgnoreCase("NOTIFICATIONS")) {
                action.click(enableNotificationPage.Button_Sure());
                action.clickAlertButton("Allow");
                if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
                    action.click(enableLocationPage.Button_Sure());
                    action.clickAlertButton("Always Allow");
                }

                homeSteps.i_selectlogout();
            /*}else if (navigationBarName.equalsIgnoreCase("LOCATION")) {
                action.click(enableLocationPage.Button_Sure());
                action.clickAlertButton("Allow");
                if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
                    action.click(enableLocationPage.Button_Sure());
                    action.clickAlertButton("Allow");
                }
                homeSteps.i_selectlogout(); */
            } else if (navigationBarName.equals("WANT $5?")) {
                takeActionOnPromotion("REJECT");
                homeSteps.i_selectlogout();
            }

            else {
                homeSteps.i_selectlogout();

            }
            log("I should be on LOG IN page",
                    "I am on LOG IN page", true);
        }
    }

    public void goToSignUpPage(String navigationBarName) throws Throwable {
        HomeSteps homeSteps = new HomeSteps(homePage);


        if (!navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.signup"))) {

            if (navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login")))
                iClickButtonOnScreen("SIGN UP", "sign up");
            else {
                homeSteps.i_select_something_from_customer_app_menu("LOGOUT");
                Thread.sleep(10000);
                iClickButtonOnScreen("SIGN UP", "sign up");

            }
        }
    }
    private List<String> getDriverCredentials(String user) throws Throwable
    {
        List<String> credentials =  new ArrayList<String>();

        String phone, password;
        boolean shouldLoginSucessful;
        switch (user.toLowerCase()) {
            case "valid":
                phone = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                password = PropertyUtility.getDataProperties("ios.valid.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case"valid driver 2":
                SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                phone = PropertyUtility.getDataProperties("ios.valid.driver2.phone");
                password = PropertyUtility.getDataProperties("ios.valid.driver2.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("ios.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", phone);
                break;
            case"valid duo driver 1":
                phone = PropertyUtility.getDataProperties("ios.valid.driver.duo.phone");
                password = PropertyUtility.getDataProperties("ios.valid.driver.duo.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.duo.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid miami":
                phone = PropertyUtility.getDataProperties("miami.driver.phone");
                password = PropertyUtility.getDataProperties("miami.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("miami.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid nashville":
                phone = PropertyUtility.getDataProperties("nashville.driver.phone");
                password = PropertyUtility.getDataProperties("nashville.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("nashville.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid denver":
                phone = PropertyUtility.getDataProperties("denver.driver.phone");
                password = PropertyUtility.getDataProperties("denver.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid denver driver 2":
                phone = PropertyUtility.getDataProperties("denver.driver2.phone");
                password = PropertyUtility.getDataProperties("denver.driver2.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "new driver":
                phone = PropertyUtility.getDataProperties("new.driver.phone");
                password = PropertyUtility.getDataProperties("new.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("new.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            default:
                throw new Exception("Please specify valid input");
        }
        credentials.add(phone);
        credentials.add(password);
        logger.detail("Driver Credentials : " + credentials.get(0) +" / "+ credentials.get(1));
        return credentials;
    }
    @And("^I login as \"([^\"]*)\" driver on \"([^\"]*)\" device and make driver status as \"([^\"]*)\"$")
    public void i_login_as_something_driver_on_something_device_and_make_driver_status_something_as(String user, String device, String driverStatus) throws Throwable {
        try {
            String navigationBarName = "";
          //  utility.switchToApp("driver",device);
            int retry =2;
            while(retry>0) {
                if (action.isElementPresent(driverHomePage.Text_NavigationBar(true)))
                 navigationBarName = action.getScreenHeader(driverHomePage.Text_NavigationBar(true));
                else
                    Thread.sleep(5000);
                retry--;
            }
            goToDriverLogInPage(navigationBarName);

            List<String> credentials =  getDriverCredentials(user);
            utility.loginToDriverApp(credentials.get(0), credentials.get(1));
            Thread.sleep(5000);

            acceptDriverPermissions("ALLOW NOTIFICATIONS" , "ALLOW LOCATION");
           // navigationBarName =  action.getScreenHeader(driverHomePage.NavigationBar_Text());
            // new GeneralUtility().logDriverDeviceToken(credentials.get(0));
                    switch (driverStatus.toUpperCase()) {
                        case "ONLINE":
                            goOnline();
                             break;
                        case "OFFLINE":
                            goOffline();
                             break;
            }

        log("I log in as driver "+user+" and make driver status as "+ driverStatus," I am loggedin as driver using ["+credentials.get(0)+" / "+credentials.get(1)+"] and make driver status as "+ driverStatus,true);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error( "Step should be successful", "Error in login as driver and updating driver status", true);
    }
    }
    public void acceptDriverPermissions(String Notification, String Location) throws Throwable {
        try {
            GeneralUtility utility = new GeneralUtility();
            String pageName = utility.getPageHeader();
            if(action.isElementPresent(enableNotificationPage.Button_Sure())) {
                action.click(enableNotificationPage.Button_Sure());
                action.clickAlertButton("Allow");
                // pageName = utility.getPageHeader();
            }
            Thread.sleep(3000);
            if(action.isElementPresent(enableLocationPage.Button_Sure())) {
                action.click(enableLocationPage.Button_Sure());
                action.clickAlertButton("Always Allow");
                //pageName = utility.getPageHeader();
            }

        } catch (Exception e) {

        }

    }
    /**
     * driver goes online
     */
    public void goOnline() {
        String navigationHeaderName = "";
        try{
         navigationHeaderName = action.getScreenHeader(driverhomepage.NavigationBar_Status(true));
        } catch (Exception e) {

        }
        if (navigationHeaderName.equals("ONLINE"))
            logger.warning("driver Status is already Online");
        else if (navigationHeaderName.equals("OFFLINE") || navigationHeaderName.equals("")) {
            action.click(driverhomepage.Button_GoOnline());
        } else if (action.isElementPresent(driverhomepage.Button_GoOnline(true)))
            action.click(driverhomepage.Button_GoOnline());
        else
            logger.error("Not able to get driver status");
    }

    /**
     * driver goes offline
     */
    public void goOffline() {
        String navigationHeaderName = "";
        try{
            navigationHeaderName = action.getScreenHeader(driverhomepage.NavigationBar_Status(true));
        } catch (Exception e) {

        }
        if (navigationHeaderName.equals("OFFLINE")) {
            logger.warning("driver Status is already offline");
        } else if (navigationHeaderName.equals("ONLINE") || navigationHeaderName.equals("")) {
            action.click(driverhomepage.Button_GoOffline());
        } else if (action.isElementPresent(driverhomepage.Button_GoOffline(true)))
            action.click(driverhomepage.Button_GoOffline());
        else if (action.isElementPresent(driverhomepage.Button_GoOnline(true)))
            logger.warning("driver Status is already offline");
        else
            logger.error("Not able to get driver status");
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
            if (!navigationBarName.equals("SIGN UP"))
            homeSteps.i_select_something_from_driver_app_memu("LOGOUT");
            else if (navigationBarName.equals("SIGN UP"))
                action.click(signupPage.Button_Login());
        }


    }

    @When("^I Switch to \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_switch_to_something_application_on_something_devices(String appName, String device) {
        try {
            logger.detail ("*** Switching to : " + appName + " application ****");
            String appHeader = "";
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(1000);
                logger.detail ("Switched To : " + device + " device");
            }
            //Vishal[20092019]: added terminate before switching the app, works faster
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    //action.switchApplication(PropertyUtility.getProp("bundleId_Driver"));
                   ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                    Thread.sleep(5000);
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    appHeader = "Bungii Driver QAAuto";
                    break;
                case "CUSTOMER":
                    ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                    Thread.sleep(5000);
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    appHeader = "Bungii QAAuto";
                    //action.switchApplication(PropertyUtility.getProp("bundleId_Customer"));
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }        //temp fixed
            new GeneralUtility().handleIosUpdateMessage();
            new GeneralUtility().handleAppleIDVerification();
            WebElement element = homePage.Application_Name(true);
            if(element != null){
            if (!action.getAppName(element).equals(appHeader)) {
                logger.detail("Retrying to start app 2nd time ");//:Page source:", SetupManager.getDriver().getPageSource());
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
            }
            else
            {
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
            new GeneralUtility().handleIosUpdateMessage();
            new GeneralUtility().handleAppleIDVerification();
            if (!action.getScreenHeader(homePage.Application_Name()).equals(appHeader)) {
                logger.detail("Retrying to start app 3rd time ");//:Page source:", SetupManager.getDriver().getPageSource());

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
            pass("Switch to : " + appName + " application on device instance",
                    "Switched to : " + appName + " application on device instance", true);
            cucumberContextManager.setFeatureContextContext("CURRENT_APPLICATION", appName.toUpperCase());
        } catch (Throwable e) {
            logger.error("Error in switching to app "+ appName, ExceptionUtils.getStackTrace(e));
          //  logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step should be successful",
                    "Error in switching to app "+ appName, true);

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
                    appHeader = "Bungii Driver QAAuto";
                    break;
                case "CUSTOMER":
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    appHeader = "Bungii QAAuto";
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }        //temp fixed
            new GeneralUtility().handleIosUpdateMessage();
            if (!action.getScreenHeader(homePage.Application_Name()).equals(appHeader)) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                        break;
                    case "CUSTOMER":
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                        break;
                }

            }
            pass("Open " + appName + " application on " + device + " devices",
                    "Open " + appName + " application" + device + " devices", true);
            cucumberContextManager.setFeatureContextContext("CURRENT_APPLICATION", appName.toUpperCase());

        } catch (Throwable e) {
            logger.error("Error in Opening " + appName + " application" + device + " devices", ExceptionUtils.getStackTrace(e));
            //logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful",
                    "Error in Opening " + appName + " application" + device + " devices", true);

        }
    }

    // TODO change catch to error
    @Then("^Alert message with (.+) text should be displayed$")
    public void alert_message_with_text_should_be_displayed(String message) {
        try {
            Thread.sleep(4000);
            action.waitForAlert();
            String actualMessage = action.getAlertMessage();
            if(actualMessage.equalsIgnoreCase("")){Thread.sleep(30000);actualMessage = action.getAlertMessage();}
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
                case "CARD IS ASSOCIATED TO TRIP":
                    expectedMessage = PropertyUtility.getMessage("customer.payment.associated.to.trip");
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
                case "STACK TRIP REQUEST AVAILABLE":
                    expectedMessage = PropertyUtility.getMessage("driver.alert.stack.alert.message.ios");
                    break;
                case "STACK TRIP REQUEST ACCEPTED":
                    expectedMessage = PropertyUtility.getMessage("driver.alert.stack.after.current");
                    break;
                case "TRIP CANNOT BE CANCELED AS CONTROL DRIVER NOT STARTED":
                    expectedMessage = PropertyUtility.getMessage("driver.alert.noncontrol.cancel.before.control");
                    break;
                case "ACCEPT SCHEDULED BUNGII QUESTION":
                    expectedMessage = PropertyUtility.getMessage("driver.bungii.request.scheduled.question");
                    break;
                case "CUSTOMER CANCELLED SCHEDULED BUNGII":
                    expectedMessage = PropertyUtility.getMessage("driver.bungii.customer.scheduled.cancel");
                    break;
                case "OTHER DRIVER CANCELLED BUNGII":
                    expectedMessage = PropertyUtility.getMessage("driver.other.driver.bungii.cancel");
                    break;
                case "INACTIVE PROMO CODE MESSAGE":
                    expectedMessage=PropertyUtility.getMessage("customer.signup.inactivepromo.android");
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

    @Then("^Alert should have \"([^\"]*)\" button$")
    public void alert_should_have_something_button(String list) {
        try {
            action.waitForAlert();
            List<String> getListOfAlertButton = action.getListOfAlertButton();
            switch (list) {
                case "cancel,proceed":
                    testStepVerify.isTrue(getListOfAlertButton.contains("CANCEL"), "Alert should have cancel button");
                    testStepVerify.isTrue(getListOfAlertButton.contains("PROCEED"), "proceed should have cancel button");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" on alert message$")
    public void i_click_something_on_alert_message(String buttonLabel) {
        try {
            action.waitForAlert();
            boolean clicked = action.clickAlertButton(buttonLabel);

            testStepAssert.isTrue(clicked,
                    "Clicked on " + buttonLabel + " button", "Alert Message with " + buttonLabel + "button not displayed");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" on alert message if any$")
    public void i_click_something_on_alert_messageifany(String buttonLabel) {
        try {
            Thread.sleep(20000);
            if(action.isAlertPresent())
                action.clickAlertButton(buttonLabel);

            log("Alert Message should be Cancelled if any", "Alert Message should be Cancelled if any");

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
            String NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());
            String userName = "", password = "";
            switch (key.toLowerCase()) {
                case "existing":
                    userName = PropertyUtility.getDataProperties("customer.user");
                    password = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "existing app user":
                    userName = PropertyUtility.getDataProperties("customer.user.hasTrip");
                    password = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name.hasTrip"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case"newly created user":
                    userName = (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");
                    password = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
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
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid miami":
                    userName = PropertyUtility.getDataProperties("miami.customer.phone");
                    password = PropertyUtility.getDataProperties("miami.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("miami.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid miami 2":
                    userName = PropertyUtility.getDataProperties("miami.customer2.phone");
                    password = PropertyUtility.getDataProperties("miami.customer2.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("miami.customer2.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid nashville":
                    userName = PropertyUtility.getDataProperties("nashville.customer.phone");
                    password = PropertyUtility.getDataProperties("nashville.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("nashville.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid nashville first time":
                    userName = PropertyUtility.getDataProperties("nashville.common.customer.phone");
                    password = PropertyUtility.getDataProperties("nashville.common.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("nashville.common.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid denver":
                    userName = PropertyUtility.getDataProperties("denver.customer.phone");
                    password = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid customer2":
                    userName = PropertyUtility.getDataProperties("customer.phone.usedin.duo");
                    password = PropertyUtility.getDataProperties("customer.password.usedin.duo");
                    cucumberContextManager.setScenarioContext("CUSTOMER2", PropertyUtility.getDataProperties("customer.name.usedin.duo"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2_PHONE", userName);
                    break;
                case "valid chicago":
                    userName = PropertyUtility.getDataProperties("chicago.customer.phone");
                    password = PropertyUtility.getDataProperties("chicago.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("chicago.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            goToLogInPage(NavigationBarName);

            LogInSteps logInSteps = new LogInSteps(loginPage);
            logInSteps.i_enter_valid_and_as_per_below_table(userName, password);
            iClickButtonOnScreen("Log In", "Log In");
            Thread.sleep(2000);

            NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar(true));

            if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                new GeneralUtility().navigateFromTermToHomeScreen();
            }
          //  new GeneralUtility().logCustomerDeviceToken(userName);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I navigate to \"([^\"]*)\" on Admin portal$")
    public void i_navigate_to_something_on_admin_portal(String option) throws Throwable {
        try {
        i_open_new_something_browser_for_something_instance("Chrome", "ADMIN");
        SetupManager.getDriver().get(utility.GetAdminUrl());
        logInPage.TextBox_Phone().sendKeys(PropertyUtility.getDataProperties("admin.user"));
        logInPage.TextBox_Pass().sendKeys(PropertyUtility.getDataProperties("admin.password"));
        logInPage.Button_LogIn().click();

            switch (option.toLowerCase()) {
                case "scheduled trip":
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_ScheduledTrips());
                    break;
                case "promo code":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_PromoCode());
                    break;
                case "referral source":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_ReferralSource());
                    break;
                case "live trips":
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_LiveTrips());
                    break;
                case "trips":
                    action.click(dashBoardPage.Button_Trips());
                    break;
                case "customers":
                    action.click(dashBoardPage.Button_Customers());
                    break;
                case "drivers":
                    action.click(dashBoardPage.Button_Drivers());
                    break;
                case "geofence":
                    action.click(dashBoardPage.Menu_Geofences());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I should able to select "+option,"I Selected "+option+" on admin sidebar" ,true );
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I open new \"([^\"]*)\" browser for \"([^\"]*)\"$")
    public void i_open_new_something_browser_for_something_instance(String browser, String instanceName) {
        try {

            SetupManager.getObject().createNewWebdriverInstance(instanceName, browser);
            SetupManager.getObject().useDriverInstance(instanceName);
            log(
                    "I open new " + browser + " browser for " + instanceName + " instance",
                    "I open new " + browser + " browser for " + instanceName + " instance", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I get \"([^\"]*)\" promocode from the admin portal$")
    public void i_get_something_promocode_from_the_admin_portal(String codeType) throws Throwable {
        i_open_new_something_browser_for_something_instance("Chrome", "ADMIN PORTAL");
        SetupManager.getDriver().get(utility.GetAdminUrl());
        logInPage.TextBox_Phone().sendKeys(PropertyUtility.getDataProperties("admin.user"));
        logInPage.TextBox_Pass().sendKeys(PropertyUtility.getDataProperties("admin.password"));
        logInPage.Button_LogIn().click();
        action.click(dashBoardPage.Button_Marketing());
        action.click(dashBoardPage.Button_PromoCode());
        action.click(promosCodePage.Button_Filter());
        action.click(promosCodePage.CheckBox_FilterPromo());
        action.click(promosCodePage.Button_Apply());
        Thread.sleep(2000);
        cucumberContextManager.setFeatureContextContext("VALID", getPromoCode(codeType));

    }
    /**
     * Find required promocode and return list of it
     *
     * @param key type of promocode that is to be searched
     * @return list of promocode for input category
     */
    public List<String> getPromoCode(String key) throws InterruptedException {
        List<String> codeList = new ArrayList<String>();
        //Vishal[12042019]: Temp fixed , Duo to QA _ Auto , TODO: Remove this
        // if (!promosPage.Text_ActivePageNumber().getText().equals("1"))
        //      promosPage.Button_Previouspage().click();
        //   while (codeList.size() <= 5) {

        while (codeList.size() <= 1) {
            List<WebElement> codes = new ArrayList<WebElement>();
            switch (key.toLowerCase()) {
                case "referral":
                    codes = promosCodePage.Text_ReferralCode();
                    break;
                case "one off":
                    codes = promosCodePage.Text_OneOffCode();
                    break;
                case "used one off":
                    codes = promosCodePage.Text_UsedOneOffCode();
                    break;
                case "unused one off":
                    codes = promosCodePage.Text_UnUsedOneOffCode();
                    break;
                case "valid":
                case "promo":
                    codes = promosCodePage.Text_PromoCode();
                    break;
                case "expired":
                    codes = promosCodePage.Text_ExpiredPromoCode();
                    break;
                case "promo fixed":
                    codes = promosCodePage.Text_PromoCodeFixed();
                    break;
                case "{promo percent}":
                    codes = promosCodePage.Text_PromoCodePercent();
                    break;
                case"promoter_type_promo":
                    codes = promosCodePage.Text_PromoCodePromoter();
                    break;
                default:
                    break;
            }
            for (WebElement code : codes) {
                codeList.add(code.getText());
            }
            Thread.sleep(1000);
            //   action.click(promosPage.Button_Nextpage());
            //   promosPage.waitForPageLoad();
            //  action.invisibilityOfElementLocated(promosPage.Loadder());
        }
        logger.detail("Promo code list for key "+key+ " is "+String.join(", ", codeList));
        return codeList;
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
            log("I switch to " + instanceName + " device instance",
                    "I switch to  " + instanceName + " device instance", false);

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
            Thread.sleep(3000);
            if (action.isAlertPresent()) {
                SetupManager.getDriver().switchTo().alert().dismiss();
                Thread.sleep(1000);
            }
            //   tripNoOfDriver="DUO";tripTime="Jan 10, 07:15 PM GMT+5:30";currentApplication="DRIVER";

            if (tripTime.contains(PropertyUtility.getDataProperties("time.label")))
                tripTime = tripTime.replace(PropertyUtility.getDataProperties("time.label"), "").trim();

            tripTime=tripTime.replace("am","AM").replace("pm","PM");
            //code to check daylight savings required here
            if(TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date()))
            {
                tripTime=tripTime.replace("st","dt").replace("ST","DT");
                logger.detail("Daylight Savings is ON");
            }
            logger.detail("TRIP TIME [According to Daylight Savings]: "+tripTime);

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
                    if (action.isAlertPresent()) {
                        SetupManager.getDriver().switchTo().alert().dismiss();
                        Thread.sleep(1000);
                    }
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
            logger.error("Error performing  step" +  ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            //logger.error("Error performing step" + e.printStackTrace());

            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

/*
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
*/

    @Given("^I am on Customer logged in Home page$")
    public void iAmOnCustomerLoggedInHomePage() {
        try {
            LogInSteps logInSteps = new LogInSteps(new LoginPage());
            HomeSteps homeSteps = new HomeSteps(homePage);
            GeneralUtility utility = new GeneralUtility();
            String NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());

            if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))
                    || NavigationBarName.equals("SIGN UP")) {
                if (NavigationBarName.equals("SIGN UP"))
                    iClickButtonOnScreen("LOG IN", "sign up");

                logInSteps.i_enter_valid_and_as_per_below_table(PropertyUtility.getDataProperties("customer.user"),
                        PropertyUtility.getDataProperties("customer.password"));
                // cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer.user"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PUSH_PHONE", PropertyUtility.getDataProperties("customer.user"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PUSH_PWD", PropertyUtility.getDataProperties("customer.password"));

                iClickButtonOnScreen("Log In", "Log In");
                Thread.sleep(2000);
                NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());
                if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                    //DO Nothing
                } else if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                    utility.navigateFromTermToHomeScreen();
                }

                //homeSteps.user_should_be_successfully_logged_in_to_the_system();
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                // do nothing
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
                iClickButtonOnScreen("CANCEL", "SEARCHING");
                iAcceptAlertMessage();
                //iRejectAlertMessage();
            } else if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                utility.navigateFromTermToHomeScreen();
            } else if (NavigationBarName.equalsIgnoreCase("NOTIFICATIONS")) {
                action.click(enableNotificationPage.Button_Sure());
                action.clickAlertButton("Allow");
                if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
                    action.click(enableLocationPage.Button_Sure());
                    action.clickAlertButton("Always Allow");
                }
            }else if (NavigationBarName.equalsIgnoreCase("WANT $5?")){
                takeActionOnPromotion("REJECT");
            } else {
                homeSteps.i_select_something_from_customer_app_menu("HOME");
            }



            log("Given customer is logged in as customer","Customer is logged in");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Given("^I am on Customer \"([^\"]*)\" logged in Home page$")
    public void iAmOnCustomerALoggedInHomePage(String user) {
        try {
            LogInSteps logInSteps = new LogInSteps(new LoginPage());
            HomeSteps homeSteps = new HomeSteps(homePage);
            GeneralUtility utility = new GeneralUtility();
            String NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());

            if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))
                    || NavigationBarName.equals("SIGN UP")) {
                if (NavigationBarName.equals("SIGN UP"))
                    iClickButtonOnScreen("LOG IN", "sign up");

                switch (user) {
                    case "A":
                        logInSteps.i_enter_valid_and_as_per_below_table(PropertyUtility.getDataProperties("customer.ios.userA"),
                                PropertyUtility.getDataProperties("customer.password"));
                        break;
                    case "B":
                        logInSteps.i_enter_valid_and_as_per_below_table(PropertyUtility.getDataProperties("customer.ios.userB"),
                                PropertyUtility.getDataProperties("customer.password"));
                        break;
                }

                iClickButtonOnScreen("Log In", "Log In");
                Thread.sleep(2000);
                NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());
                if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                    //DO Nothing
                } else if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                    utility.navigateFromTermToHomeScreen();
                }

                //homeSteps.user_should_be_successfully_logged_in_to_the_system();
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                // do nothing
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
                iClickButtonOnScreen("CANCEL", "SEARCHING");
                iAcceptAlertMessage();
                //iRejectAlertMessage();
            } else if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                utility.navigateFromTermToHomeScreen();
            } else if (NavigationBarName.equalsIgnoreCase("NOTIFICATIONS")) {
                action.click(enableNotificationPage.Button_Sure());
                action.clickAlertButton("Allow");
                if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
                    action.click(enableLocationPage.Button_Sure());
                    action.clickAlertButton("Always Allow");
                }
            }else if (NavigationBarName.equalsIgnoreCase("WANT $5?")){
                takeActionOnPromotion("REJECT");
            } else {
                homeSteps.i_select_something_from_customer_app_menu("HOME");
            }
          //  cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
          //  cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer.user"));
            log("Given customer is logged in as customer","Customer is logged in");
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
        try{
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
                code = Arrays.asList(PropertyUtility.getDataProperties("promocode.usedoneoff"));
                //    code = (List<String>) cucumberContextManager.getFeatureContextContext("USED_ONE_OFF");
                break;
            case "unused one off":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("UNUSED_ONE_OFF");
                break;
            case "referral code":
                code = Arrays.asList((String) cucumberContextManager.getScenarioContext("INVITE_CODE"));
                break;
            case "first time only":
                code = Arrays.asList(PropertyUtility.getDataProperties("promocode.firsttime"));
                break;
            case "promocode":
                code = Arrays.asList(PropertyUtility.getDataProperties("promocode.dollar.off"));
                break;
            default:
                code.add(codeType);
                break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
        return code;
    }

    @Then("^I save customer phone and referral code in feature context$")
    public void i_save_customer_phone_and_referral_code_in_feature_context() throws Throwable {
        try {
            cucumberContextManager.setFeatureContextContext("INVITE_CODE", (String) cucumberContextManager.getScenarioContext("INVITE_CODE"));
            cucumberContextManager.setFeatureContextContext("CUSTOMER_HAVING_REF_CODE", (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER"));
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I have customer with referral code$")
    public void i_save_customer_phone_and_referral_code_iADDED_PROMO_CODEn_feature_context() throws Throwable {
        try {

            String refCode = (String) cucumberContextManager.getFeatureContextContext("INVITE_CODE");//refCode="119W5";
            String phoneNumber = (String) cucumberContextManager.getFeatureContextContext("CUSTOMER_HAVING_REF_CODE");//phoneNumber="9999992799";
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", refCode);
            cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", phoneNumber);
            testStepAssert.isTrue(refCode.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
            testStepAssert.isTrue(phoneNumber.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I note customer with referral code$")
    public void i_save_customer_phone_and_referral_code_iADDED_PROMO_CODEn_feature() throws Throwable {
        try {

            String refCode = (String) cucumberContextManager.getFeatureContextContext("INVITE_CODE");//refCode="119W5";
            String phoneNumber = (String) cucumberContextManager.getFeatureContextContext("CUSTOMER_HAVING_REF_CODE");//phoneNumber="9999992799";
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", refCode);
            cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", phoneNumber);
            testStepAssert.isTrue(refCode.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
            testStepAssert.isTrue(phoneNumber.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Given("^I have customer with referral code received$")
    public void i_save_customer_phone_and_referral_code_iADDED_PROMO_CODEreceived() throws Throwable {
        try {

            String refCode = (String) cucumberContextManager.getFeatureContextContext("INVITE_CODE");//refCode="119W5";
            String phoneNumber = (String) cucumberContextManager.getFeatureContextContext("CUSTOMER_HAVING_REF_CODE");//phoneNumber="9999992799";
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", refCode);
            cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", phoneNumber);

            if(refCode.length() <= 1)
            {

            }
            else
            testStepAssert.isTrue(refCode.length() > 1, "I Should have customer with referral code", "I dont have customer with referral code");
            testStepAssert.isTrue(phoneNumber.length() > 1, "I Should have customer with phoneNumber", "I dont have customer with phoneNumber");




        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
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
                    if(inputValue.equalsIgnoreCase("RandomTestcustomertywd_apple"))
                    {
                        String randomString= generateMobileNumber();
                        action.clearEnterText(signupPage.Textfield_FirstName(), inputValue+"_"+randomString);
                        cucumberContextManager.setScenarioContext("NEW_USER_FIRST_NAME", inputValue+"_"+randomString);
                    }
                    action.clearEnterText(signupPage.Textfield_FirstName(), inputValue);
                    cucumberContextManager.setScenarioContext("NEW_USER_FIRST_NAME", inputValue);
                    break;
                case "LAST NAME":
                    action.clearEnterText(signupPage.Textfield_LastName(), inputValue);
                    cucumberContextManager.setScenarioContext("NEW_USER_LAST_NAME", inputValue);
                    action.hideKeyboard();
                    break;
                case "EMAIL":
                    action.clearEnterText(signupPage.Textfield_Email(), inputValue);
                    action.hideKeyboard();
                    cucumberContextManager.setScenarioContext("NEW_USER_EMAIL_ADDRESS",inputValue);
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
                    action.hideKeyboard();
                    action.click(signupPage.Button_CheckBox_Referral());
                    List<String> inputValueList = getRefferalCode(inputValue);
                    action.clearEnterText(signupPage.Textfield_PromoCode(), inputValueList.get(0));
                    // cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputValue);
                    cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputValueList.get(0));
                    break;
                case "PROMO CODE":
                    List<String> ValueList = getRefferalCode(inputValue);
                    action.tapByElement(promosPage.TextBox_EnterCode());
                    action.clearEnterText(promosPage.TextBox_EnterCode(), ValueList.get(0));
                    //  cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputValue);
                    cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", ValueList.get(0));
                    break;
                case "SMS CODE":
                    inputValue = inputValue.equalsIgnoreCase("valid") || inputValue.equalsIgnoreCase("old") ? (String) cucumberContextManager.getScenarioContext("SMS_CODE")
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

    @Given("^I install Bungii Driver App again$")
    public void i_install_bungii_app_data() {
        try {
            GeneralUtility utility = new GeneralUtility();
            boolean isNewInstalled = utility.installDriverApp();
            testStepAssert.isTrue(isNewInstalled, "I should able to install bungii Driver App again", "I was not able to install bungii Driver app again");
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
                case "OUTSIDE BUISSNESS HOUR":
                    expectedText = PropertyUtility.getMessage("customer.alert.outsidebuissnesshour");
                    break;
                case "SCHEDULED ONLY 5 DAYS":
                    expectedText = PropertyUtility.getMessage("customer.alert.six.day.ahead");
                    break;
                case "LONG HAUL":
                    expectedText = PropertyUtility.getMessage("customer.alert.long.haul");
                    break;
                case "DRIVER FINISHING CURRENT BUNGII":
                    expectedText = PropertyUtility.getMessage("customer.alert.driver.bungii.inprogress");
                    break;
                case "MORE THAN 1 HOUR FROM SCHEDULED TIME":
                    expectedText = PropertyUtility.getMessage("customer.alert.more.than.one.hour");
                    break;
                case "PICKUP REQUEST NO LONGER AVAILABLE":
                    expectedText = PropertyUtility.getMessage("driver.request.unavailable");
                    break;
                case "PICKUP ALREADY ACCEPTED BY YOU":
                    expectedText = PropertyUtility.getMessage("driver.request.already.accepted");
                    break;
                case "60 MINS BEFORE SCHEDULE TRIP TIME":
                    expectedText = PropertyUtility.getMessage("driver.start.60.mins.before");
                    break;
                case "REQUIRED DRIVER NOT ACCEPTED":
                    expectedText = PropertyUtility.getMessage("driver.required.not.accepted");
                    break;
                case "CUSTOMER HAS ONGOING BUNGII":
                    expectedText = PropertyUtility.getMessage("driver.start.customer.ongoing");
                    break;
                case "FOR EMERGENCY CONTACT SUPPORT LINE":
                    expectedText = PropertyUtility.getMessage("driver.cancel.support.contact");
                    break;
                case "CONTACT SUPPORT TO CANCEL":
                    expectedText = PropertyUtility.getMessage("customer.support.contact.to.cancel");
                    break;
                case "SMS CODE SENT":
                    expectedText = PropertyUtility.getMessage("customer.sms.code.sent");
                    break;
                case "3 OUT OF 5 ATTEMPT":
                    expectedText = PropertyUtility.getMessage("customer.3.out.5.attempt.login");
                    break;
                case "USER ACCOUNT LOCKED":
                    expectedText = PropertyUtility.getMessage("customer.user.account.locked");
                    break;
                case "MINIMUM COST STILL APPLIES":
                    expectedText = PropertyUtility.getMessage("customer.promos.minimum.info");
                    break;
                case "FIRST TIME PROMO CODE":
                    expectedText = PropertyUtility.getMessage("customer.first.time.promos.info");
                    break;
                case "ADD CARD BEFORE REQUEST BUNGII":
                    expectedText = PropertyUtility.getMessage("customer.add.card.before.request");
                    break;
                case "ADD IMAGE OF ITEM":
                    expectedText = PropertyUtility.getMessage("customer.request.add.image");
                    break;
                case "CHOSSING NON FIRST TIME CODE":
                    expectedText = PropertyUtility.getMessage("customer.select.other.than.first.time.code");
                    break;
                case "PLEASE ENABLE LOCATION SERVICES":
                    expectedText = PropertyUtility.getMessage("driver.enable.location.services");
                    break;
                case "PLEASE INSTALL A BROWSER":
                    expectedText = PropertyUtility.getMessage("customer.install.browser");
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

    @And("^I get TELET time of of the current trip$")
    public void i_get_telet_time_of_of_the_current_trip() throws Throwable {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //    phoneNumber="8888889907";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String teletTime = dbUtility.getTELETfromDb(custRef);

        cucumberContextManager.setScenarioContext("TELET", teletTime);
    }
    @And("^I get TELET time of currrent trip of customer 2$")
    public void i_get_telet_time_of_of_the_currewnt_trip() throws Throwable {
        try{
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
        //    phoneNumber="8888889907";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String teletTime = dbUtility.getTELETfromDb(custRef);

        cucumberContextManager.setScenarioContext("TELET", teletTime);
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^Telet time of current trip should be correctly calculated$")
    public void telet_time_of_current_trip_should_be_correctly_calculated() throws Throwable {
        try{
        GeneralUtility utility= new GeneralUtility();
        String teletTimeLocal =utility.calculateTeletTime();
        String teletTimeDB = (String) cucumberContextManager.getScenarioContext("TELET");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //By default data is in UTC
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date Db = formatter.parse(teletTimeDB);

        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();

        DateFormat formatterForLocalTimezone = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

        String strdateDB = formatter.format(Db);
        String strdatelocal = teletTimeLocal;
        testStepVerify.isEquals(strdateDB,strdatelocal);
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^Telet time of research trip should be not be same as previous trips$")
    public void telet_time_of_current_trip_should_be_correctly_calculatedtrip() throws Throwable {
        try{
        String previousTelet = (String) cucumberContextManager.getScenarioContext("TELET");
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //    phoneNumber="8888889907";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String newTeletTime = dbUtility.getTELETfromDb(custRef);
        testStepVerify.isTrue(!previousTelet.equalsIgnoreCase(newTeletTime),"TELET TIME SHOULD ot be equal");
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^for a Bungii I should see \"([^\"]*)\"$")
    public void for_a_bungii_i_should_see_something(String strArg1) throws Throwable {
        try{
            switch (strArg1)
            {
                case "Bungii Home page with locations":
                    String addressPickUPline1= (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1");
                    String addressDropOffline1= (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1");
                    String pickUpAddress=homePage.TextBox_Pickup_LineOne().getText();
                    String DropOffAddress=homePage.TextBox_Drop_LineOne().getText();

                    if(pickUpAddress.contains(addressPickUPline1) && DropOffAddress.contains(addressDropOffline1))
                    {
                        pass(addressPickUPline1,DropOffAddress,true);
                    }
                    else{
                        fail(addressPickUPline1,DropOffAddress,true);
                    }
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }

        }
        catch (Exception e) {
            logger.error("Error performing step", e);
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I manually end bungii created by \"([^\"]*)\" with stage as \"([^\"]*)\"$")
    public void i_manually_end_bungii_created_by_something_with_stage_as_something(String customer, String bungiiStage) throws Throwable {
        try{
        String status =bungiiStage;
        String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        String tripType[] = tripTypeAndCategory.split(" ");
        customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");

        cucumberContextManager.setScenarioContext("STATUS",status);
        if (status.equalsIgnoreCase("Scheduled") ||status.equalsIgnoreCase("Searching Drivers")
                || status.equalsIgnoreCase("Driver Removed") || (status.equalsIgnoreCase("Admin Cancelled"))) {
            String xpath= String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[4]", tripType[0].toUpperCase(), customer);
            int retrycount =10;

            boolean retry = true;
            while (retry == true && retrycount >0) {
                try {
                    WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                    retry = false;
                } catch (Exception ex) {
                    SetupManager.getDriver().navigate().refresh();
                    retrycount--;
                    retry = true;
                }

            }
            int retryCount = 1;
            while (!SetupManager.getDriver().findElement(By.xpath(xpath)).getText().equalsIgnoreCase(status)) {
                if (retryCount >= 20) break;
                Thread.sleep(15000); //Wait for 15 seconds
                retryCount++;
                SetupManager.getDriver().navigate().refresh();
            }
            cucumberContextManager.setScenarioContext("XPATH",xpath);
            testStepAssert.isElementTextEquals(SetupManager.getDriver().findElement(By.xpath(xpath)), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
        }

        //Select the trip
        String xpath=  (String)cucumberContextManager.getScenarioContext("XPATH");
        action.click((WebElement) SetupManager.getDriver().findElement(By.xpath(xpath)));
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^Customer should receive signup email$")
    public void partner_firm_should_receive_something_email(){
        try{
        GeneralUtility utility = new GeneralUtility();

        String emailSubject="New to Bungii? Good.";
/*        cucumberContextManager.setScenarioContext("NEW_USER_EMAIL_ADDRESS","bungiiauto+obKm@gmail.com");
        cucumberContextManager.setScenarioContext("NEW_USER_FIRST_NAME","TestCustomertywdappleMzr");*/

        String emailBody = utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.welcome.from.address"), (String)cucumberContextManager.getScenarioContext("NEW_USER_EMAIL_ADDRESS"), emailSubject);
        List<String> tripDetailsLinks=extractUrls(emailBody);
        utility.getCustomerSignupTemplate((String)cucumberContextManager.getScenarioContext("NEW_USER_EMAIL_ADDRESS"));
        if (emailBody == "") {
            testStepAssert.isFail("Email : " + emailSubject + " is not received");
        }
        else{
            boolean isEmailCorrect=utility.validateCustomerSignupEmail(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath())+"/EmailTemplate/CustomerSignup.txt",emailBody, (String)cucumberContextManager.getScenarioContext("NEW_USER_FIRST_NAME"),tripDetailsLinks.get(0),tripDetailsLinks.get(1),tripDetailsLinks.get(2),tripDetailsLinks.get(3),tripDetailsLinks.get(4),tripDetailsLinks.get(5),tripDetailsLinks.get(6),tripDetailsLinks.get(7),tripDetailsLinks.get(8));
            testStepAssert.isTrue(isEmailCorrect,"Email should be correct","Email is not correct , check logs for more details");



        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }

    @Then("^poor driver ratting should be sent to customer$")
    public void poor_driver_ratting_should_be_sent_to_customer() {
        try{
        GeneralUtility utility = new GeneralUtility();
        String emailSubject="POOR DRIVER RATING";
      //  String emailBody = utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"), "POOR DRIVER RATING");

        String emailBody  =  utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"),emailSubject);
        String driverName=(String) cucumberContextManager.getScenarioContext("DRIVER_1");/*driverName="Testdrivertywd_appledv_b_matt Stark_dvOnE";*/
        String customerName=(String)cucumberContextManager.getScenarioContext("CUSTOMER");/*customerName="Testcustomertywd_appleZTDafc Stark";*/
        String ratingValue=(String)cucumberContextManager.getScenarioContext("RATING_VALUE");/*ratingValue="3";*/
        String tripDetailsLink=extractUrls(emailBody).get(0);
        if(emailBody== "")
        {
            testStepAssert.isFail("Email : "+ emailSubject + " is not received");
        }
        String message = null;
        message = utility.getExpectedPoorRatingMail(driverName, customerName, ratingValue, tripDetailsLink);
        testStepAssert.isEquals(emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ",""), message.replaceAll(" ",""),"Email "+emailBody+" content should match", "Email  "+emailBody+" content matches", "Email "+emailBody+"  content doesn't match");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }
    /**
     * Returns a list with all links contained in the input
     */
    public static List<String> extractUrls(String text)
    {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find())
        {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        return containedUrls;
    }

    @And("^I open Admin portal and navigate to \"([^\"]*)\" page$")
    public void i_open_admin_portal_and_navigate_to_something_page(String option) throws Throwable {
        try {
            i_open_new_something_browser_for_something_instance("CHROME","ADMIN");
            SetupManager.getDriver().get(utility.GetAdminUrl());
            logInPage.TextBox_Phone().sendKeys(PropertyUtility.getDataProperties("admin.user"));
            logInPage.TextBox_Pass().sendKeys(PropertyUtility.getDataProperties("admin.password"));
            logInPage.Button_LogIn().click();

            switch (option.toLowerCase()) {
                case "scheduled deliveries":
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_ScheduledTrips());
                    break;
                case "live deliveries":
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_LiveTrips());
                    break;
                case "promo code":
                    action.click(dashBoardPage.Button_PromoCode());
                    action.click(dashBoardPage.Link_StandardCodes());
                    break;
                case "referral source":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_ReferralSource());
                    break;
                case "customers":
                    action.click(dashBoardPage.Button_Customers());
                    break;
                case "deliveries":
                    action.click(dashBoardPage.Button_Trips());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I open Admin portal and navigate to "+option+ " page","I am on admin "+ option+" page" ,true );
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in navigating to admin portal ",
                    true);
        }
    }
}
