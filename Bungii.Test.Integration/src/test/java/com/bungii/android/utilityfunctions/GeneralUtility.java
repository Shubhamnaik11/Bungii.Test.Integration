package com.bungii.android.utilityfunctions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.*;
import com.bungii.android.pages.driver.BungiiCompletedPage;
import com.bungii.android.pages.driver.DriverHomePage;
import com.bungii.android.pages.driver.InProgressBungiiPages;
import com.bungii.android.pages.otherApps.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.ios.enums.Status;
import com.bungii.ios.pages.driver.BungiiRequestPage;
import com.bungii.ios.stepdefinitions.driver.ScheduledBungiiSteps;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.function.Function;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.warning;
import static com.google.common.base.Preconditions.checkArgument;
import static java.util.concurrent.TimeUnit.SECONDS;

public class GeneralUtility extends DriverBase {
    static final double MIN_COST = 39;

    private static LogUtility logger;
    ActionManager action = new ActionManager();
    LoginPage Page_Login = new LoginPage();
    SignupPage Page_Signup = new SignupPage();
    TermsPage Page_CustTerms = new TermsPage();
    HomePage homePage = new HomePage();
    DriverHomePage driverHomePage = new DriverHomePage();
    BungiiCompletePage customerBungiiCompletePage = new BungiiCompletePage();
    MenuPage Page_Menu = new MenuPage();
    OtherAppsPage otherAppsPage = new OtherAppsPage();
    EstimatePage estimatePage = new EstimatePage();
    AccountPage cutomerAccountPage = new AccountPage();
    PaymentPage paymentPage = new PaymentPage();
    FAQPage faqPage = new FAQPage();
    SupportPage supportPage = new SupportPage();
    PromosPage promosPage = new PromosPage();
    com.bungii.android.pages.driver.LoginPage driverLoginPage = new com.bungii.android.pages.driver.LoginPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    TermsPage termsPage = new TermsPage();
    SearchingPage searchingPage = new SearchingPage();
    InProgressBungiiPages driverBungiiProgressPage = new InProgressBungiiPages();
    BungiiCompletedPage bungiiCompletedPage = new BungiiCompletedPage();
    WantDollar5Page wantDollar5Page = new WantDollar5Page();
    InProgressBungiiPages Page_DriverBungiiProgress = new InProgressBungiiPages();
    BungiiDetailsPage bungiiDetailsPage=new BungiiDetailsPage();
    BungiiRequestPage bungiiRequestPage=new BungiiRequestPage();
    ScheduledBungiiSteps sbs = new ScheduledBungiiSteps();

    ScheduledBungiisPage scheduledBungiisPage=new ScheduledBungiisPage();
    InvitePage invitePage=new InvitePage();

    /**
     * Launch driver application's using package and activity
     *
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    public void launchDriverApplication() throws MalformedURLException, InterruptedException {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            driver.manage().timeouts().implicitlyWait(Long.parseLong(PropertyUtility.getProp("implicit.wait")), SECONDS);

/*            //TODO: REMOVE HARD CODING, read from properties
            String appPackage = "com.bungii.driver";
            String appActivity = "com.bungii.ui.activities.splash.SplashScreenActivity";*/
            String appPackage = PropertyUtility.getProp("bundleId_Driver");
            String appActivity = PropertyUtility.getProp("driver.initial.activity");
            Activity activity = new Activity(appPackage, appActivity);
            activity.setStopApp(false);
            ((AndroidDriver<MobileElement>) driver).startActivity(activity);
            //   Thread.sleep(3000);
        } catch (Exception e) {

        }
    }

    /**
     * Launch customer application's using package and activity
     *
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    public void launchCustomerApplication() throws MalformedURLException, InterruptedException {
        try {
            AndroidDriver<AndroidElement> driver = (AndroidDriver<AndroidElement>) SetupManager.getDriver();
/*
        //TODO: REMOVE HARD CODING, read from properties
        String appPackage = "com.bungii.customer";
        String appActivity = "com.bungii.ui.activities.splash.SplashScreenActivity";
*/
            String appPackage = PropertyUtility.getProp("bundleId_Customer");
            String appActivity = PropertyUtility.getProp("customer.initial.activity");

            Activity activity = new Activity(appPackage, appActivity);
            activity.setStopApp(false);
            ((AndroidDriver<AndroidElement>) driver).startActivity(activity);
            driver.manage().timeouts().implicitlyWait(Long.parseLong(PropertyUtility.getProp("implicit.wait")), SECONDS);
            Thread.sleep(3000);
        } catch (Exception e) {
        }

    }

    /**
     * Check if customer application is open . check if there is application open which has element that contains customer app resource id
     *
     * @return
     * @throws InterruptedException
     */
    public boolean isCustomerApplicationOpen() throws InterruptedException {
        //new method introduced in recent appium version to check application state
      //  if( (((AndroidDriver)SetupManager.getDriver()).queryAppState("com.bungii.customer")).equals(ApplicationState.RUNNING_IN_FOREGROUND))
       //     return true;
        if (action.isElementPresent(homePage.Generic_Element(true)))
            return true;
        else {
            // Thread.sleep(5000);
            logger.detail(SetupManager.getDriver().getPageSource());
            return false;
            // return action.isElementPresent(homePage.Generic_Element(true));
        }
    }

    /**
     * Check if driver application is open . check if there is application open which has element that contains driver app resource id
     *
     * @return
     * @throws InterruptedException
     */
    public boolean isDriverApplicationOpen() throws InterruptedException {
        //new method introduced in recent appium version to check application state
      //  if( (((AndroidDriver)SetupManager.getDriver()).queryAppState("com.bungii.driver")).equals(ApplicationState.RUNNING_IN_FOREGROUND))
      //      return true;
        if (action.isElementPresent(driverHomePage.Generic_Element(true)))
            return true;
        else {
            logger.detail(SetupManager.getDriver().getPageSource());
            //Thread.sleep(5000);
            return false;
            //return action.isElementPresent(driverHomePage.Generic_Element(true));
        }
    }

    /**
     * Check if correct page is open
     *
     * @param p0 identifier for page
     * @return
     */
    public boolean isCorrectPage(String p0) throws InterruptedException {
        boolean isCorrectPage = false;
        switch (p0) {
            case "FAQ":
                isCorrectPage = action.isElementPresent(faqPage.Header_FAQPage(true));
                break;
            case "Account":
                isCorrectPage = action.isElementPresent(cutomerAccountPage.Header_AccountPage(true));
                break;
            case "MY BUNGIIS":
                isCorrectPage=action.isElementPresent(scheduledBungiisPage.Title_ScheduledBungiis());
                break;
            case "Payment":
                isCorrectPage = action.isElementPresent(paymentPage.Header_PaymentPage(true));
                break;
            case "Support":
                isCorrectPage = action.isElementPresent(supportPage.Header_SupportPage(true));
                break;
            case "Promos":
                isCorrectPage = action.isElementPresent(promosPage.Header_SavePage(true));
                break;
            case "Home":
                isCorrectPage = action.isElementPresent(homePage.Header_HomePage(true));
                break;
            case "Estimate":
                isCorrectPage = action.isElementPresent(estimatePage.Header_Estimate(true));
                break;
            case "Login":
            case "Logout":
                isCorrectPage = action.isElementPresent(Page_Login.Header_LoginPage(true));
                break;
            case "Signup":
                isCorrectPage = action.isElementPresent(Page_Signup.Header_SignUp(true));
                break;
            case "Terms and Conditions":
                isCorrectPage = action.isElementPresent(termsPage.Header_TermsPage(true));
                break;
            case "Tutorial":
                isCorrectPage = action.isElementPresent(homePage.Text_TutorialPdf());
                // isCorrectPage=action.getText(homePage.Text_TutorialHeader()).equals(PropertyUtility.getMessage("customer.tutorial.header"));
                break;
            case "DRIVER NOT AVAILABLE":
                isCorrectPage = action.isElementPresent(searchingPage.Header_DriverNotAvailable(true));
                break;
            case "bungii.com":
                if (!action.isElementPresent(otherAppsPage.Text_ChromeUrl(true)))
                    Thread.sleep(5000);
                isCorrectPage = action.isElementPresent(otherAppsPage.Text_ChromeUrl(true)) && action.getText(otherAppsPage.Text_ChromeUrl()).contains("bungii.com/drive");
                break;
            case "Enroute screen":
                Thread.sleep(5000);
                String currentPage = action.getText(Page_DriverBungiiProgress.Title_Status_Generic(true));
                if(!currentPage.equalsIgnoreCase(""))
                    isCorrectPage=action.getText(Page_DriverBungiiProgress.Title_Status_Generic()).equals(Status.EN_ROUTE.toString());
                else
                    isCorrectPage=action.getText(Page_DriverBungiiProgress.Title_Status_Generic_Alt()).equals(Status.EN_ROUTE.toString());
                break;
            case "Arrived screen":
                Thread.sleep(5000);
                if(!action.getText(Page_DriverBungiiProgress.Title_Status_Generic(true)).equalsIgnoreCase(""))
                    isCorrectPage=action.getText(Page_DriverBungiiProgress.Title_Status_Generic()).equals(Status.ARRIVED.toString());
                else
                    isCorrectPage=action.getText(Page_DriverBungiiProgress.Title_Status_Generic_Alt()).equals(Status.ARRIVED.toString());
                break;
            case "Loading Item screen":

                Thread.sleep(5000);
                if(!action.getText(Page_DriverBungiiProgress.Title_Status_Generic(true)).equalsIgnoreCase(""))
                    isCorrectPage=action.getText(Page_DriverBungiiProgress.Title_Status_Generic()).equals(Status.LOADING_ITEM.toString());
                else
                    isCorrectPage=action.getText(Page_DriverBungiiProgress.Title_Status_Generic_Alt()).equals(Status.LOADING_ITEM.toString());
                break;
            case "Driving to DropOff screen":
                Thread.sleep(5000);
                if(!action.getText(Page_DriverBungiiProgress.Title_Status_Generic(true)).equalsIgnoreCase(""))
                    isCorrectPage=action.getText(Page_DriverBungiiProgress.Title_Status_Generic()).equals(Status.DRIVING_TO_DROP_OFF.toString());
                else
                    isCorrectPage=action.getText(Page_DriverBungiiProgress.Title_Status_Generic_Alt()).equals(Status.DRIVING_TO_DROP_OFF.toString());
                break;
            case "Unloading Item screen":
                Thread.sleep(5000);
                if(!action.getText(Page_DriverBungiiProgress.Title_Status_Generic(true)).equalsIgnoreCase(""))
                    isCorrectPage=action.getText(Page_DriverBungiiProgress.Title_Status_Generic()).equals(Status.UNLOADING_ITEM.toString());
                else
                    isCorrectPage=action.getText(Page_DriverBungiiProgress.Title_Status_Generic_Alt()).equals(Status.UNLOADING_ITEM.toString());
                break;
            case "INVITE":
                isCorrectPage=action.isElementPresent(invitePage.Header_Invite());
                break;
            default:
                break;
        }
        return isCorrectPage;
    }

    public void resetApp() {
        ((AndroidDriver) SetupManager.getDriver()).resetApp();
    }

    /**
     * Verification that correct page is displayed
     *
     * @param expectedPage
     */
    public void verifyIsPageIsCorrectlyDisplayed(String expectedPage) throws InterruptedException {
        testStepAssert.isTrue(isCorrectPage(expectedPage), expectedPage + " page should be displaed ", expectedPage + " Page is successfully displayed", expectedPage + " Page is not displayed");
    }

    public String getAlertMessage() {
        return action.getText(estimatePage.Alert_ConfirmRequestMessage(true));
    }

    /**
     * Get snack bar message
     *
     * @return return snack bar message
     */
    public String getSnackBarMessage() {
        WebElement snackBar = forgotPasswordPage.Snackbar_ForgotPassword(true);
        String actualMessage = "";
        if (snackBar == null) {
            warning("Snackbar message for success should be displayed", "Snackbar message was not displayed or was displayed for small amount of time to capture snackbar message text");
        } else {
            actualMessage = snackBar.getText();
        }
        return actualMessage;
    }

    /**
     * Check if  phone number is correct
     *
     * @param element Web element of phone number
     * @param value   expected phine number value
     */
    public void isPhoneNumbersEqual(WebElement element, String value) {
        String actualText = element.getText().replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        String expectedText = value.replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        if (!actualText.equalsIgnoreCase(expectedText)) {
            if (expectedText.startsWith("1")) expectedText = expectedText.replaceFirst("1", "");
        }
        testStepVerify.isEquals(actualText, expectedText, "Twillio Number should be correct", "Twillio number is not correct. actualText:" + actualText + "expectedText:" + expectedText);
    }

    /**
     * Check if  phone number is correct
     *
     * @param element Web element of phone number
     * @param value   expected phine number value
     */
    public void isPhoneNumbersEqual(WebElement element, String value, String expectedMessage, String errorMessage) {
        String actualText = element.getText().replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        String expectedText = value.replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        if (!actualText.equalsIgnoreCase(expectedText)) {
            if (expectedText.startsWith("1")) expectedText = expectedText.replaceFirst("1", "");
        }
        testStepVerify.isEquals(actualText, expectedText, expectedMessage, errorMessage);
    }

    /**
     * Calculate estimate cost of trip check if less than minimum cost then
     * return minimum cost
     *
     * @param tripDistance
     * @param loadTime
     * @param estTime
     * @param Promo
     * @return
     */
    public double bungiiEstimate(String tripDistance, String loadTime, String estTime, String Promo) {

        double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double loadUnloadTime = Double.parseDouble(loadTime.replace(" mins", ""));
        Promo = Promo.contains("ADD") ? "0" : Promo;
        double tripTime = Double.parseDouble(estTime);
        double actualValue = distance + loadUnloadTime + tripTime;
        double discount = 0;
        if (Promo.contains("$"))
            discount = Double.parseDouble(Promo.replace("-$", ""));
        else if (Promo.contains("%"))
            discount = actualValue * Double.parseDouble(Promo.replace("-", "").replace("%", "")) / 100;

        double estimate = distance + loadUnloadTime + tripTime - discount;
        estimate = estimate > MIN_COST ? estimate : MIN_COST;

        return estimate;
    }

    /**
     * Calculate  cost of trip check if less than minimum cost then
     * return minimum cost
     *
     * @param tripDistance
     * @param Promo
     * @return
     */
    public double bungiiCustomerCost(String tripDistance, String tripTime, String Promo, String tripType) {

        double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double tripActualTime = Double.parseDouble(tripTime);
        double tripValue = distance + tripActualTime;
        if (tripType.equalsIgnoreCase("DUO"))
            tripValue = tripValue * 2;
        Promo = Promo.contains("ADD") ? "0" : Promo;

        double discount = 0;
        if (Promo.contains("$"))
            discount = Double.parseDouble(Promo.replace("-$", ""));
        else if (Promo.contains("%"))
            discount = tripValue * Double.parseDouble(Promo.replace("-", "").replace("%", "")) / 100;

        double costToCustomer = distance + tripActualTime - discount;
        costToCustomer = costToCustomer > MIN_COST ? costToCustomer : MIN_COST;

        return costToCustomer;
    }

    /**
     * Input value on Numeric keyboard
     *
     * @param strNum Number that is to be input
     * @throws InterruptedException
     */
    public void inputOnNumberKeyBoard(String strNum) throws InterruptedException {
        for (char c : strNum.toCharArray()) {
            ((AndroidDriver) SetupManager.getDriver()).pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + c)));
            System.out.println("   ENTER VALUE :" + c);
            Thread.sleep(200);
        }
        try {
            ((AndroidDriver) SetupManager.getDriver()).hideKeyboard();
        } catch (Exception e) {
        }
    }

    /**
     * Click button on customer menu bar
     *
     * @param menuItem identifier for menu
     */
    public void clickCustomerMenuItem(String menuItem) {
        try {
            action.click(homePage.Button_NavigationBar());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            if (action.isElementPresent(homePage.Button_NavigationBarCompleter(true))) {

                WebElement Button_NavigationBar = homePage.Button_NavigationBarCompleter();
                int xAxisStartPoint = Button_NavigationBar.getLocation().getX() + 20;
                int yAxis = Button_NavigationBar.getLocation().getY() + Button_NavigationBar.getRect().getHeight() / 2;
                action.click(new Point(xAxisStartPoint, yAxis));
            }
        }
        switch (menuItem.toUpperCase()) {
            case "HOME":
                action.click(homePage.Button_NavHome());
                break;
            case "FAQ":
                action.click(homePage.Button_NavFAQ());
                break;
            case "ACCOUNT":
                action.click(homePage.Button_NavAccount());
                break;
            case "MY BUNGIIS":
                action.click(homePage.Button_NavSchBungii());
                break;
            case "PAYMENT":
                action.click(homePage.Button_NavPayment());
                break;
            case "SUPPORT":
                action.click(homePage.Button_NavSupport());
                break;
            case "PROMOS":
                action.click(homePage.Button_NavPromos());
                break;
            case "LOGOUT":
                action.scrollToTop();
                action.click(homePage.Button_Navlogout());
                break;
            case "SIGN UP TO DRIVE":
                action.click(homePage.Button_NavDrives());
                break;
        }
    }


    public String getEstimateTime() {

        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //   phoneNumber="9999996170";
        String custRef = com.bungii.android.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        return DbUtility.getEstimateTime(custRef);
    }

    public String getEstimateDistance() {

        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //   phoneNumber="9999996170";
        String custRef = com.bungii.android.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        return DbUtility.getEstimateTime(custRef);
    }

    public String getActualTime() {

        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //   phoneNumber="9403960188";
        String custRef = com.bungii.android.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String pickUpId = DbUtility.getPickupID(custRef);
        String actualTime = DbUtility.getActualTime(pickUpId);
        return actualTime;
    }

    public String getPickupRef(String phoneNumber) {

        String custRef = com.bungii.android.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String pickupReff = DbUtility.getPickupReff(custRef);
        return pickupReff;
    }

    public void goToSignupPage() {
        action.waitUntilIsElementExistsAndDisplayed(Page_Signup.GenericHeader(true));

        String currentPage = action.getText(Page_Signup.GenericHeader(true));
        switch (currentPage.toUpperCase()) {
            case "BUNGII":
            case "FAQ":
            case "ACCOUNT":
            case "SCHEDULED BUNGIIS":
            case "PAYMENT":
            case "SUPPORT":
            case "PROMOS":
                clickCustomerMenuItem("LOGOUT");
                action.click(Page_Login.Link_Signup());
                break;
            case "SIGN UP":
                break;
            case "LOGIN":
                action.click(Page_Login.Link_Signup());
                break;
        }

    }

    public void goToLoginPage() {
        action.waitUntilIsElementExistsAndDisplayed(Page_Signup.GenericHeader(true));

        boolean skipNormalFlow = false;
        //    System.out.println("Page"+SetupManager.getDriver().getPageSource());
        String currentPage = action.getText(Page_Signup.GenericHeader(true));
        switch (currentPage.toUpperCase()) {
            case "BUNGII":
            case "FAQ":
            case "ACCOUNT":
            case "SCHEDULED BUNGIIS":
            case "PAYMENT":
            case "SUPPORT":
            case "PROMOS":
                clickCustomerMenuItem("LOGOUT");
                skipNormalFlow = true;
                break;
            case "SIGN UP":
                action.click(Page_Signup.Link_Login());
                skipNormalFlow = true;

                break;
            case "LOGIN":
                //   action.click(Page_Signup.Link_Login());
                skipNormalFlow = true;

                break;

        }
        if (!skipNormalFlow) {
            if (action.isElementPresent(Page_Signup.Link_Login(true)))
                action.click(Page_Signup.Link_Login());
            else if (action.isElementPresent(Page_Login.Header_LoginPage(true))) {
                //do nothing
            } else
                action.scrollToBottom();
                clickCustomerMenuItem("LOGOUT");
        }
    }


    public String trimString(String stringtext) {
        stringtext = stringtext.trim().replace("\t", "").replace("\n", "").replace("\r", "");
        return stringtext;
    }

    /**
     * Check if promo code is present in list of available promocode in promocode page
     *
     * @param promoCode promocode that is to be checked
     * @return
     */
    public boolean isPromoCodePresent(String promoCode) {
        List<WebElement> listOfPromoCode = promosPage.List_PromoCode();
        boolean isPromoCodePresent = false;
        for (WebElement lstPromo : listOfPromoCode) {
            if (action.getText(lstPromo).contains(promoCode)) {
                isPromoCodePresent = true;
                break;
            }
        }
        return isPromoCodePresent;
    }

    public String generateMobileNumber() {

        String phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");
        //  phoneNumber="9999993248";
        while (!DbUtility.isPhoneNumberUnique(phoneNumber)) {
            phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");

        }
        return phoneNumber;
    }

    public void loginToCustomerApp(String phone, String password) throws InterruptedException {
        boolean isNextScreenLogIN = false;
        //   System.out.println(SetupManager.getDriver().getPageSource());
        action.waitUntilIsElementExistsAndDisplayed(Page_Signup.GenericHeader(true));

        String currentPage = action.getText(Page_Signup.GenericHeader(true));
        switch (currentPage.toUpperCase()) {
            case "BUNGII":
            case "FAQ":
            case "ACCOUNT":
            case "SCHEDULED BUNGIIS":
            case "PAYMENT":
            case "SUPPORT":
            case "PROMOS":
                clickCustomerMenuItem("LOGOUT");
                isNextScreenLogIN = true;
                break;
            case "SIGN UP":
                action.click(Page_Signup.Link_Login());
                isNextScreenLogIN = true;
                break;
            case "TERMS & CONDITIONS":
                action.click(Page_CustTerms.Checkbox_Agree());
                action.click(Page_CustTerms.Button_Continue());
                if (action.isElementPresent(Page_CustTerms.Header_PermissionsLocation(true))) {
                    // action.click(Page_CustTerms.Button_GoToSetting());
                    action.click(Page_CustTerms.Button_PermissionsSure());
                    action.click(Page_CustTerms.Button_PermissionsAllow());
                    // ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                }
                if (action.isElementPresent(homePage.Button_Closetutorials(true)))
                    action.click(homePage.Button_Closetutorials());
                break;

        }
        if (currentPage.equalsIgnoreCase("LOGIN") || isNextScreenLogIN) {
            WebElement element = Page_Login.TextField_PhoneNumber();
            if (StringUtils.isNumeric(phone)) {
                //element.sendKeys();
                element.click();
                inputOnNumberKeyBoard(phone);
            } else {
                action.clearSendKeys(Page_Login.TextField_PhoneNumber(), phone);
            }

            action.clearSendKeys(Page_Login.TextField_Password(), password);
            action.click(Page_Login.Button_Login());
            Thread.sleep(4000);
            //   action.invisibilityOfElementLocated(Page_Login.Button_Login(true));
            String nextPage = "";
            try {
                action.waitUntilIsElementExistsAndDisplayed(Page_Signup.GenericHeader(true));
                nextPage = action.getText(Page_Signup.GenericHeader(true));
            } catch (Exception e) {
                nextPage = action.getText(Page_Signup.GenericHeader(true));
            }

            if (nextPage.equalsIgnoreCase("TERMS & CONDITIONS")) {
                action.click(Page_CustTerms.Checkbox_Agree());
                action.click(Page_CustTerms.Button_Continue());
                if (action.isElementPresent(Page_CustTerms.Header_PermissionsLocation(true))) {
                    action.click(Page_CustTerms.Button_PermissionsSure());
                    action.click(Page_CustTerms.Button_PermissionsAllow());
                    //((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                }
                if (action.isElementPresent(homePage.Button_Closetutorials(true)))
                    action.click(homePage.Button_Closetutorials());
            }
        } else {
            //I am not on Login screen
        }
        //AssertionManager.ElementDisplayed(homePage.Title_HomePage);
        //AssertionManager.ElementDisplayed(homePage.Link_Invite);
    }

    public void LogoutCustomerApp() {
        if (action.isElementPresent(homePage.Link_Menu())) {
            action.click(homePage.Link_Menu());
            action.click(Page_Menu.Menu_Logout());
        }
    }

    public void loginToDriverApp(String phone, String password) throws InterruptedException {
        action.waitUntilIsElementExistsAndDisplayed(driverHomePage.Generic_HeaderElement(true));
        String currentPage = action.getText(driverHomePage.Generic_HeaderElement(true));
        if (currentPage.equals("LOGIN")) {
            // if (action.isElementPresent(driverLoginPage.TextField_PhoneNumber(true))) {
            WebElement element = driverLoginPage.TextField_PhoneNumber();

            if (StringUtils.isNumeric(phone)) {
                element.click();
                inputOnNumberKeyBoard(phone);
            } else {
                action.sendKeys(driverLoginPage.TextField_PhoneNumber(), phone);
            }
            action.sendKeys(driverLoginPage.TextField_Password(), password);
            action.click(driverLoginPage.Button_Login());
            Thread.sleep(2000);
            try {
                action.waitUntilIsElementExistsAndDisplayed(driverHomePage.Generic_HeaderElement(true));
                currentPage = action.getText(driverHomePage.Generic_HeaderElement(true));
            }catch (StaleElementReferenceException ex){
                Thread.sleep(4000);
                WebElement header=driverHomePage.Generic_HeaderElement();
                currentPage=header.getText();
            }
            if (currentPage.equals("ONLINE") || currentPage.equals("OFFLINE") || currentPage.equals("EN ROUTE")|| currentPage.equals("ARRIVED")|| currentPage.equals("LOADING ITEM")|| currentPage.equals("DRIVING TO DROP OFF")|| currentPage.equals("UNLOADING ITEM")) {

            } else if (currentPage.equals("LOCATION")) {
                action.click(driverLoginPage.Button_Sure());
                action.click(driverLoginPage.Button_Allow());
            } else if (action.isElementPresent(driverLoginPage.Header_Location(true))) {
                action.click(driverLoginPage.Button_Sure());
                action.click(driverLoginPage.Button_Allow());
            }
        } else {
            //Not on Login page
        }
    }

    public void enterDriverPhoneAndPassword(String phone, String password) throws InterruptedException {
        if (action.isElementPresent(driverLoginPage.TextField_PhoneNumber(true))) {
            WebElement element = driverLoginPage.TextField_PhoneNumber();

            if (StringUtils.isNumeric(phone)) {
                element.click();
                inputOnNumberKeyBoard(phone);
            } else {
                action.sendKeys(driverLoginPage.TextField_PhoneNumber(), phone);
            }
            action.sendKeys(driverLoginPage.TextField_Password(), password);
        } else {
            //Not on Login page
        }
    }

    public void goToDriverLoginPage() {
        String currentPage = action.getText(driverHomePage.Generic_HeaderElement(true));

        if (currentPage.equals("LOGIN")) {
        } else if(currentPage.equals("ONLINE")||currentPage.equals("OFFLINE")){ clickDriverMenuItem("LOGOUT");}
        else if (action.isElementEnabled(driverLoginPage.Button_ForgotPassword(true))) {
        } else clickDriverMenuItem("LOGOUT");

    }

    public boolean clickDriverMenu(String menuItem) {
        Boolean isClicked = false;
        List<WebElement> elements = driverHomePage.Button_NavigationBarText();
        for (WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(menuItem)) {
                action.click(element);
                isClicked = true;
                break;
            }
        }
        return isClicked;
    }

    public void clickDriverMenuItem(String menuItem) {
        action.click(driverHomePage.Button_NavigationBar());
        boolean isClicked = clickDriverMenu(menuItem);

        if (!isClicked) {
            action.scrollToBottom();
            isClicked = clickDriverMenu(menuItem);
        }
        testStepAssert.isTrue(isClicked, "I should able to click " + menuItem, "Not able to select " + menuItem + " from App menu");
    }

    public boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9]*$";
        return s.matches(pattern);
    }

    public void isDriverLoginSucessful() {
        testStepAssert.isElementEnabled(driverHomePage.Title_Status(true), "driver should be sucessfully login in", "driver was logged in sucessfuly and driver status is" + action.getText(driverHomePage.Title_Status()), "driver was logged in successfuly");
    }

    public boolean clickOnNofitication(String appName, String notificationMessage) {
        boolean isDisplayed = false;
        try {
            //FIX FOR APPIUM 1.42
            if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.notification.ondemand"))) {
                if (action.isElementPresent(otherAppsPage.Notification_OnDemand(true))) {
                    action.click(otherAppsPage.Notification_OnDemand());
                    isDisplayed = true;
                }
            }
            else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.notification.stack"))) {
                if (action.isElementPresent(otherAppsPage.Notification_Stack(true))) {
                    action.click(otherAppsPage.Notification_Stack());
                    isDisplayed = true;
                }

            }
            else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.notification.stack.cancel"))) {
                if (action.isElementPresent(otherAppsPage.Notification_StackCustomerCancel(true))) {
                    action.click(otherAppsPage.Notification_StackCustomerCancel());
                    isDisplayed = true;
                }

            }
            else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.driver.accepted.stack"))) {
                if (action.isElementPresent(otherAppsPage.Notification_StackDriverAccepted(true))) {
                    action.click(otherAppsPage.Notification_StackDriverAccepted());
                    isDisplayed = true;
                }

            }
            else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.driver.started.stack"))) {
                if (action.isElementPresent(otherAppsPage.Notification_StackDriverStarted(true))) {
                    action.click(otherAppsPage.Notification_StackDriverStarted());
                    isDisplayed = true;
                }

            }
            else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("customer.notification.driver.bungii.accepted.stack"))) {
                if (action.isElementPresent(otherAppsPage.Notification_StackDriverAccepted1(true))) {
                    action.click(otherAppsPage.Notification_StackDriverAccepted1());
                    isDisplayed = true;
                }
            }
            else if (notificationMessage.equalsIgnoreCase(PropertyUtility.getMessage("driver.notification.scheduled.urgent"))) {
                Thread.sleep(10000);
                if (action.isElementPresent(otherAppsPage.Notification_ScheduledUrgent(true))) {
                    action.click(otherAppsPage.Notification_ScheduledUrgent());
                    isDisplayed = true;
                }
            }
            else if (notificationMessage.equalsIgnoreCase(notificationMessage)) {
                try {
                    Thread.sleep(12000);
                    if (action.isElementPresent(sbs.getLocatorForNotification(notificationMessage)) == true) {
                        action.click(sbs.getLocatorForNotification(notificationMessage));
                        isDisplayed = true;
                    }
                } catch (Exception e) {
                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                    error("Step  Should be successful", "Error performing step, Please check logs for more details",
                            true);
                }
            }

                }

        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);

        }
        return isDisplayed;
    }

    public WebElement getLocatorForNotification(String notificationMessage) {
        WebElement element;
        element = estimatePage.findElement("//*[@text='" + notificationMessage + "']", PageBase.LocatorType.XPath);

        if (action.isElementPresent(element) == true) {
            element = estimatePage.findElement("//*[@text='" + notificationMessage + "']", PageBase.LocatorType.XPath);
        }

        return  element;
    }

    public String getExpectedNotification(String identifier) {
        String text = "";
        try {

            switch (identifier.toUpperCase()) {
                case "ON DEMAND TRIP":
                    text = PropertyUtility.getMessage("driver.notification.ondemand");
                    break;
                case "DRIVER CANCELLED":
                    text = PropertyUtility.getMessage("customer.notification.driver.cancelled");
                    break;
                case "DRIVER ENROUTE":
                    text = PropertyUtility.getMessage("customer.notification.driver.accepted");
                    break;
                case "STACK TRIP":
                    text = PropertyUtility.getMessage("driver.notification.stack");
                    break;
                case "CUSTOMER CANCEL STACK TRIP":
                    text = PropertyUtility.getMessage("driver.notification.stack.cancel");
                    break;
                case "CUSTOMER -DRIVER ACCEPTED STACK BUNGII":
                    text = PropertyUtility.getMessage("customer.notification.driver.accepted.stack");
                    break;
                case "CUSTOMER -DRIVER STARTED STACK BUNGII":
                    text = PropertyUtility.getMessage("customer.notification.driver.started.stack");
                    break;
                case "SCHEDULED PICKUP ACCEPTED":
                    text = PropertyUtility.getMessage("customer.notification.driver.bungii.accepted.stack");
                    break;

                case "URGENT SCHEDULED PICKUP AVAILABLE":
                    text = PropertyUtility.getMessage("driver.notification.scheduled.urgent");
                    break;

                case "SCHEDULED PICKUP AVAILABLE":
                    text=PropertyUtility.getMessage("driver.notification.scheduled");
                    //	$<Day>, $<MONTH> <$Date>

                    String schDate=(String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
                    String geofenceLabel = getTimeZoneBasedOnGeofenceId();

                    //DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyyy", Locale.ENGLISH);
                    DateFormat format = new SimpleDateFormat("MMM dd, HH:mm a zzz", Locale.ENGLISH);
                    try {
                        format.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

                        Date date = format.parse(schDate);
                        Date currentDate = new Date();
                        //int year=currentDate.getYear()+1900;
                        date.setYear(currentDate.getYear());
                        int month = date.getMonth();
                        String strMonth=getMonthForInt(month);
                        int dayOfMonth = date.getDate();
                        String dayOfMonthStr = String.valueOf(dayOfMonth)+getDayOfMonthSuffix(dayOfMonth);

                        SimpleDateFormat  simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
                        String dayOfWeek=simpleDateformat.format(date);

                        text=text.replace("$<Day>",dayOfWeek);
                        text=text.replace("$<MONTH>",strMonth);
                        text=text.replace("$<Date>",dayOfMonthStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        return text;
    }

    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }
    String getDayOfMonthSuffix(final int n) {checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }
    public void selectBungiiTime() {
        action.click(estimatePage.Time());
        action.click(estimatePage.Button_Later());
        action.click(estimatePage.Button_DateConfirm());
        action.click(estimatePage.Button_TimeConfirm());

    }

    /**
     * Get geofence data from properties file
     *
     * @param geofenceName Geofence name
     * @param partialKey   this is partial value that is to be searched in properties file
     * @return get message from Geofence propertiese file
     */
    public String getGeofenceData(String geofenceName, String partialKey) {
        geofenceName = (geofenceName.isEmpty() || geofenceName.equals("")) ? PropertyUtility.getGeofenceData("current.geofence") : geofenceName.toLowerCase();
        String actualKey = geofenceName + "." + partialKey;
        return PropertyUtility.getGeofenceData(actualKey);
    }

    public void selectAddress(WebElement element, String searchstring) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        action.clear(element);
        element.click();
        element.sendKeys(searchstring);
        int x = element.getLocation().getX() + 32;
        int y = element.getLocation().getY() + element.getRect().getHeight() + 10;
        Thread.sleep(2000);
        new TouchAction(driver).tap(new PointOption().withCoordinates(x, y)).release().perform();
        Thread.sleep(2000);

    }

    /**
     * Get timezone for geofence, read it from properties file and conver into Time zone object
     *
     * @return
     */
    public String getTimeZoneBasedOnGeofence() {
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get timezone value of Geofence
        String getGeofenceTimeZone = getGeofenceData(currentGeofence, "geofence.timezone");
        return getGeofenceTimeZone;
    }

    /**
     * Get timezone for geofence, read it from properties file and conver into Time zone object
     *
     * @return
     */
    public String getTimeZoneBasedOnGeofenceId() {
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        // currentGeofence="kansas";
        //get timezone value of Geofence
        String getGeofenceTimeZone = getGeofenceData(currentGeofence, "geofence.timezone.id");
        return getGeofenceTimeZone;
    }

    public String getCustomerSnackBarMessage() {

        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), Long.parseLong(PropertyUtility.getProp("WaitTime")));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.bungii.customer:id/snackbar_text")));
        return action.getText(element);
    }

    public String getCustomerPromoInfoMessage() {

        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), Long.parseLong(PropertyUtility.getProp("WaitTime")));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/message")));
        return action.getText(element);
    }

    public String getDriverSnackBarMessage() {

        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), Long.parseLong(PropertyUtility.getProp("WaitTime")));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.bungii.driver:id/snackbar_text")));
        return action.getText(element);
    }

    public void addSliderValueToFeatureContext(String appKey, Rectangle rectangle) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey;
        cucumberContextManager.setFeatureContextContext(key + "_RECT", rectangle);
    }

    public boolean isSliderValueContainsInContext(String appKey) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey;
        return cucumberContextManager.isFeatureContextContains(key + "_RECT");
    }

    public Rectangle getSliderValueFromContext(String appKey) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey;
        return (Rectangle) cucumberContextManager.getFeatureContextContext(key + "_RECT");
    }

    public void waitForSnackbarMessage() {
        FluentWait<AndroidDriver> wait = new FluentWait<AndroidDriver>((AndroidDriver) SetupManager.getDriver());
        wait.pollingEvery(Duration.ofMillis(1000));
        wait.withTimeout(Duration.ofSeconds(90));
        wait.ignoring(NoSuchElementException.class); // We need to ignore this exception.


        Function<AndroidDriver, MobileElement> function = new Function<AndroidDriver, MobileElement>() {
            public MobileElement apply(AndroidDriver arg0) {
                System.out.println("Checking for the object!!");
                MobileElement element = (MobileElement) arg0.findElement(MobileBy.xpath("//*[text()='Password is successfully reset.']"));
                if (element != null) {
                    System.out.println("A new dynamic object is found." + element.getText());
                }
                return element;
            }
        };

        wait.until(function);
    }

    public boolean isForgotPasswordMessageCorrect(){
        final FluentWait<WebDriver> wait = new FluentWait<>(SetupManager.getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(10))
                .ignoring(NoSuchElementException.class);
        boolean isMessageCorrect=wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    final WebElement webElement = webDriver.findElement(By.id("com.bungii.customer:id/snackbar_text"));
                    return webElement.getText().equals(PropertyUtility.getMessage("customer.forgotpassword.success.android"));

                } catch (final StaleElementReferenceException | TimeoutException e) {
                    return false;
                }
            }
        });
    return isMessageCorrect;
    }
    public void acceptNotificationAlert() {
        action.click(driverHomePage.Notification_AlertAccept());
    }

    public boolean verifyPageHeader(String key) {
        boolean isCorrectPage = false;
        switch (key)
        {
            case "BUNGII REQUEST":
                testStepVerify.isElementTextEquals(bungiiDetailsPage.Text_BungiiRequestAccepted(),key);
                isCorrectPage=true;
                break;

            case "SCHEDULED BUNGII":
                testStepVerify.isElementTextEquals(bungiiDetailsPage.Text_ScheduledBungiis(),key);
                isCorrectPage=true;
                break;

            case "AVAILABLE TRIPS":
                testStepVerify.isElementTextEquals(bungiiDetailsPage.Text_AvailableTrips(),key);
                isCorrectPage=true;
                break;

            case "BUNGII DETAILS":
                testStepVerify.isElementTextEquals(bungiiDetailsPage.Text_BungiiDetails(),key);
                isCorrectPage=true;
                break;
        }
        return isCorrectPage;
    }

    public boolean Acceptbungii(){
        boolean isAccepted = false;
        action.click(bungiiRequestPage.Button_Accept());
        return isAccepted;
    }


    public void recoverScenario() {
        logger.detail("Inside recovery scenario");

        try {
            SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));

            logger.detail("Switched to Driver in recovery scenario");
            Thread.sleep(1000);

        } catch (Exception e) {
        }


        try {


            //   if (action.isElementPresent(driverBungiiProgressPage.Title_Status(true))) {
            if (action.isElementPresent(driverHomePage.Generic_HeaderElement(true))) {
                String screen = action.getText(driverHomePage.Generic_HeaderElement());
                logger.detail("Driver struck screen" + screen);
                if (screen.equalsIgnoreCase(Status.ARRIVED.toString())) {
                    logger.detail("Driver struck on arrived screen");
                    action.click(driverBungiiProgressPage.Button_Cancel());
                    action.click(driverBungiiProgressPage.Button_Cancel_Yes());
                    launchCustomerApplication();
                    action.click(estimatePage.Button_OK());

                } else if (screen.equals("LOGIN")||screen.equals("ONLINE")||screen.equals("OFFLINE")){
                    //do nothing
                }
                    else if (screen.equals(Status.EN_ROUTE.toString())) {
                    logger.detail("Driver struck on EN_ROUTE screen");
                    action.click(driverBungiiProgressPage.Button_Cancel());
                    action.click(driverBungiiProgressPage.Button_Cancel_Yes());
                    launchCustomerApplication();
                    action.click(estimatePage.Button_OK());
                } else if (screen.equals(Status.LOADING_ITEM.toString())) {
                    logger.detail("Driver struck on LOADING_ITEM screen");
                    action.swipeRight(driverBungiiProgressPage.Slider());

                if(action.isElementPresent(driverBungiiProgressPage.Alert_Message(true))){
                    if(action.getText(driverBungiiProgressPage.Alert_Message()).equalsIgnoreCase(PropertyUtility.getMessage("bungii.duo.driver.pickup"))) {
                        action.getText(driverBungiiProgressPage.Alert_Message());
                        action.click(driverBungiiProgressPage.Alert_Accept());
                    }
                }
                    action.swipeRight(driverBungiiProgressPage.Slider());
                    action.swipeRight(driverBungiiProgressPage.Slider());

                if(action.isElementPresent(driverBungiiProgressPage.Alert_Message(true))){
                    if(action.getText(driverBungiiProgressPage.Alert_Message()).equalsIgnoreCase(PropertyUtility.getMessage("bungii.duo.driver.drop"))) {
                        action.getText(driverBungiiProgressPage.Alert_Message());
                        action.click(driverBungiiProgressPage.Alert_Accept());
                    }
                }
                    action.click(bungiiCompletedPage.Button_OnToTheNext());
                } else if (screen.equals(Status.DRIVING_TO_DROP_OFF.toString())) {
                    logger.detail("Driver struck on DRIVING_TO_DROP_OFF screen");
                    action.swipeRight(driverBungiiProgressPage.Slider());
                    action.swipeRight(driverBungiiProgressPage.Slider());

                if(action.isElementPresent(driverBungiiProgressPage.Alert_Message(true))){
                    if(action.getText(driverBungiiProgressPage.Alert_Message()).equalsIgnoreCase(PropertyUtility.getMessage("bungii.duo.driver.drop"))) {
                        action.getText(driverBungiiProgressPage.Alert_Message());
                        action.click(driverBungiiProgressPage.Alert_Accept());
                    }
                }
                    action.click(bungiiCompletedPage.Button_OnToTheNext());
                } else if (screen.equals(Status.UNLOADING_ITEM.toString())) {
                    logger.detail("Driver struck on UNLOADING_ITEM screen");
                    action.swipeRight(driverBungiiProgressPage.Slider());

                if(action.isElementPresent(driverBungiiProgressPage.Alert_Message(true))){
                    if(action.getText(driverBungiiProgressPage.Alert_Message()).equalsIgnoreCase(PropertyUtility.getMessage("bungii.duo.driver.drop"))) {
                        action.getText(driverBungiiProgressPage.Alert_Message());
                        action.click(driverBungiiProgressPage.Alert_Accept());
                    }
                }
                    action.click(bungiiCompletedPage.Button_OnToTheNext());
                } else if (screen.equals("BUNGII COMPLETED")) {
                    action.click(bungiiCompletedPage.Button_OnToTheNext());

                }
            } else if (action.isElementPresent(bungiiCompletedPage.Button_OnToTheNext(true))) {
                logger.detail("Driver struck on bungii completed screen");
                action.click(bungiiCompletedPage.Button_OnToTheNext());
            }else if (action.isElementPresent(estimatePage.Button_OK(true))) {
                logger.detail("Driver struck on  Popup message ");
                action.click(estimatePage.Button_OK());
            }

        } catch (Exception e) {
        }
        SetupManager.getObject().restartApp();
        logger.detail("Switched to customer in recovery scenario");
        String appHeader = "";
        try {
            appHeader = action.getText(Page_Signup.GenericHeader(true));
        } catch (Exception e) {
        }
        {
        }
        if (appHeader.equalsIgnoreCase("BUNGII") || appHeader.equalsIgnoreCase("SIGN UP") || appHeader.equalsIgnoreCase("LOGIN")) {
            //do nothing
        } else if (action.isElementPresent(searchingPage.ProgressBar(true))) {
            logger.detail("customer struck on searching screen");
            action.click(searchingPage.Link_CancelSearch());
            action.click(searchingPage.Button_CancelConfirm());
        } else if (action.isElementPresent(customerBungiiCompletePage.PageTitle_BungiiComplete(true))) {
            logger.detail("customer struck on bungii complete screen");
            action.click(customerBungiiCompletePage.CloseRateTipPage());
            action.click(wantDollar5Page.Button_NoFreeMoney());
        } else if (action.isElementPresent(wantDollar5Page.Titlebar_WantDollar5Page(true))) {
            logger.detail("Customer struck on promotion screen");
            action.click(wantDollar5Page.Button_NoFreeMoney());
        }


    }
}
