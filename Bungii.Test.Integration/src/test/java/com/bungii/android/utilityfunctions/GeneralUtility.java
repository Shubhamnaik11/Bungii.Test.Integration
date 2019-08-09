package com.bungii.android.utilityfunctions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.*;
import com.bungii.android.pages.otherApps.OtherAppsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.warning;

public class GeneralUtility extends DriverBase {
    static final double MIN_COST = 39;
    ActionManager action = new ActionManager();
    LoginPage Page_Login = new LoginPage();
    SignupPage Page_Signup = new SignupPage();
    TermsPage Page_CustTerms = new TermsPage();
    HomePage homePage = new HomePage();
    MenuPage Page_Menu = new MenuPage();
    OtherAppsPage otherAppsPage = new OtherAppsPage();
    EstimatePage estimatePage = new EstimatePage();
    com.bungii.android.pages.driver.HomePage driverHomePage = new com.bungii.android.pages.driver.HomePage();
    AccountPage cutomerAccountPage = new AccountPage();
    PaymentPage paymentPage = new PaymentPage();
    FAQPage faqPage = new FAQPage();
    SupportPage supportPage = new SupportPage();
    PromosPage promosPage = new PromosPage();
    com.bungii.android.pages.driver.LoginPage driverLoginPage = new com.bungii.android.pages.driver.LoginPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    TermsPage termsPage = new TermsPage();
    SearchingPage searchingPage = new SearchingPage();

    /**
     * Launch driver application's using package and activity
     *
     * @throws MalformedURLException
     * @throws InterruptedException
     */
    public void launchDriverApplication() throws MalformedURLException, InterruptedException {
        try {
            AndroidDriver<AndroidElement> driver = (AndroidDriver<AndroidElement>) SetupManager.getDriver();

/*            //TODO: REMOVE HARD CODING, read from properties
            String appPackage = "com.bungii.driver";
            String appActivity = "com.bungii.ui.activities.splash.SplashScreenActivity";*/
            String appPackage = PropertyUtility.getProp("bundleId_Driver");
            String appActivity = PropertyUtility.getProp("driver.initial.activity");
            Activity activity = new Activity(appPackage, appActivity);
            activity.setStopApp(false);
            ((AndroidDriver<AndroidElement>) driver).startActivity(activity);
            driver.manage().timeouts().implicitlyWait(Long.parseLong(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);
            Thread.sleep(3000);
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
            driver.manage().timeouts().implicitlyWait(Long.parseLong(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);
            Thread.sleep(3000);
        } catch (Exception e) {
        }

    }

    /**
     * Check if customer application is open . check if there is application open which has element that contains customer app resource id
     * @return
     * @throws InterruptedException
     */
    public boolean isCustomerApplicationOpen() throws InterruptedException {
        if (action.isElementPresent(homePage.Generic_Element(true)))
            return true;
        else {
            Thread.sleep(5000);
            return action.isElementPresent(homePage.Generic_Element(true));
        }
    }

    /**
     * Check if driver application is open . check if there is application open which has element that contains driver app resource id
     * @return
     * @throws InterruptedException
     */
    public boolean isDriverApplicationOpen() throws InterruptedException {
        if (action.isElementPresent(driverHomePage.Generic_Element(true)))
            return true;
        else {
            Thread.sleep(5000);

            return action.isElementPresent(driverHomePage.Generic_Element(true));
        }
    }

    /**
     * Check if correct page is open
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
            case"Terms and Conditions":
                isCorrectPage=action.isElementPresent(termsPage.Header_TermsPage(true));
                break;
            case "Tutorial":
                isCorrectPage=action.isElementPresent(homePage.Text_TutorialPdf());
               // isCorrectPage=action.getText(homePage.Text_TutorialHeader()).equals(PropertyUtility.getMessage("customer.tutorial.header"));
                break;
            case"DRIVER NOT AVAILABLE":
                isCorrectPage=action.isElementPresent(searchingPage.Header_DriverNotAvailable(true));
                break;
            case "bungii.com":
                if(!action.isElementPresent(otherAppsPage.Text_ChromeUrl(true)))
                    Thread.sleep(5000);
                isCorrectPage=action.isElementPresent(otherAppsPage.Text_ChromeUrl(true)) && action.getText(otherAppsPage.Text_ChromeUrl()).contains("bungii.com/drive");
                break;
            default:
                break;
        }
        return isCorrectPage;
    }

    public void resetApp(){
        ((AndroidDriver)SetupManager.getDriver()).resetApp();
    }
    /**
     * Verification that correct page is displayed
     * @param expectedPage
     */
    public void verifyIsPageIsCorrectlyDisplayed(String expectedPage) throws InterruptedException {
        testStepAssert.isTrue(isCorrectPage(expectedPage), expectedPage + " page should be displaed ", expectedPage + " Page is successfully displayed", expectedPage + " Page is not displayed");
    }

    public String getAlertMessage(){
        return action.getText(estimatePage.Alert_ConfirmRequestMessage(true));
    }
    /**
     * Get snack bar message
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
     * @param element Web element of phone number
     * @param value expected phine number value
     */
    public void isPhoneNumbersEqual(WebElement element, String value) {
        String actualText = element.getText().replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        String expectedText = value.replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        testStepVerify.isEquals(actualText, expectedText, "Twillio Number should be correct", "Twillio number is not correct. actualText:" + actualText + "expectedText:" + expectedText);
    }

       /** Check if  phone number is correct
     * @param element Web element of phone number
     * @param value expected phine number value
     */
    public void isPhoneNumbersEqual(WebElement element, String value, String expectedMessage ,String errorMessage) {
        String actualText = element.getText().replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        String expectedText = value.replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        testStepVerify.isEquals(actualText, expectedText,expectedMessage,errorMessage);
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
        Promo=Promo.contains("ADD")?"0":Promo;
        double tripTime = Double.parseDouble(estTime);
        double actualValue = distance + loadUnloadTime + tripTime;
        double discount=0 ;
        if(Promo.contains("$"))
            discount=Double.parseDouble(Promo.replace("-$", ""));
        else if(Promo.contains("%"))
            discount=actualValue*Double.parseDouble(Promo.replace("-", "").replace("%", ""))/100;

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
    public double bungiiCustomerCost(String tripDistance,String tripTime, String Promo,String tripType) {

        double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double tripActualTime = Double.parseDouble(tripTime);
        double tripValue = distance + tripActualTime;
        if(tripType.equalsIgnoreCase("DUO"))
            tripValue=tripValue*2;
        Promo=Promo.contains("ADD")?"0":Promo;

        double discount=0 ;
        if(Promo.contains("$"))
            discount=Double.parseDouble(Promo.replace("-$", ""));
        else if(Promo.contains("%"))
            discount=tripValue*Double.parseDouble(Promo.replace("-", "").replace("%", ""))/100;

        double costToCustomer = distance +  tripActualTime - discount;
        costToCustomer = costToCustomer > MIN_COST ? costToCustomer : MIN_COST;

        return costToCustomer;
    }
    /**
     * Input value on Numeric keyboard
     * @param strNum Number that is to be input
     * @throws InterruptedException
     */
    private void inputOnNumberKeyBoard(String strNum) throws InterruptedException {
        for (char c : strNum.toCharArray()) {
            ((AndroidDriver) SetupManager.getDriver()).pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + c)));
            System.out.println("   ENTER VALUE :" + c);
            Thread.sleep(200);
        }
        try{
        ((AndroidDriver) SetupManager.getDriver()).hideKeyboard();}catch (Exception e){}
    }

    /**
     * Click button on customer menu bar
     * @param menuItem identifier for menu
     */
    public void clickCustomerMenuItem(String menuItem) {
        try{
        action.click(homePage.Button_NavigationBar());}catch (org.openqa.selenium.NoSuchElementException e){}
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
                action.click(homePage.Button_Navlogout());
                break;
            case "SCHEDULED BUNGIIS":
                action.click(homePage.Button_NavSchBungii());
                break;
            case"SIGN UP TO DRIVE":
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
        String pickUpId= DbUtility.getPickupID(custRef);
        String actualTime =DbUtility.getActualTime(pickUpId);
        return actualTime;
    }


    public void goToSignupPage() {
        clickCustomerMenuItem("LOGOUT");
        action.click(Page_Login.Link_Signup());
        // action.click(Page_Login.Link_Signup());

    }

    public void goToLoginPage() {

        if (action.isElementPresent(Page_Signup.Link_Login(true)))
            action.click(Page_Signup.Link_Login());
        else if (action.isElementPresent(Page_Login.Header_LoginPage(true))) {
            //do nothing
        } else
            clickCustomerMenuItem("LOGOUT");

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
        if (action.isElementPresent(Page_Signup.Link_Login(true))) {
            action.click(Page_Signup.Link_Login());
        }
        //
        if (isCorrectPage("Login")) {
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
            if (action.isElementPresent(Page_CustTerms.Checkbox_Agree(true))) {
                action.click(Page_CustTerms.Checkbox_Agree());
                action.click(Page_CustTerms.Button_Continue());
                if (action.isElementPresent(Page_CustTerms.Popup_PermissionsMessage(true))) {
                    action.click(Page_CustTerms.Button_GoToSetting());
                    action.click(Page_CustTerms.Button_PermissionsAllow());
                    ((AndroidDriver) DriverManager.getObject().getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                }
                if(action.isElementPresent(homePage.Button_Closetutorials(true)))
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
        if (action.isElementPresent(driverLoginPage.TextField_PhoneNumber(true))) {
            WebElement element = driverLoginPage.TextField_PhoneNumber();

            if (StringUtils.isNumeric(phone)) {
                element.click();
                inputOnNumberKeyBoard(phone);
            } else {
                action.sendKeys(driverLoginPage.TextField_PhoneNumber(), phone);
            }
            action.sendKeys(driverLoginPage.TextField_Password(), password);
            action.click(driverLoginPage.Button_Login());
        } else {
            //Not on Login page
        }
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
        List<WebElement> notificationHeader = otherAppsPage.Text_NotificationTitle();
        List<WebElement> notificationText = otherAppsPage.Text_Notification();
        System.out.println(SetupManager.getDriver().getPageSource());
        for (int i = 0; i < notificationHeader.size(); i++) {
            if (notificationHeader.get(i).getText().equalsIgnoreCase(appName)) {
                String currentNotificationText = notificationText.get(i).getText();
                if (currentNotificationText.equalsIgnoreCase(notificationMessage)) {
                    action.click(notificationText.get(i));
                    isDisplayed = true;
                    break;
                }
            }

        }

        return isDisplayed;
    }

    public String getExpectedNotification(String identifier) {
        String text = "";
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
        }
        return text;
    }

    public void selectBungiiTime() {
        action.click(estimatePage.Time());
        action.click(estimatePage.Button_Later());
        action.click(estimatePage.Button_DateConfirm());
        action.click(estimatePage.Button_TimeConfirm());

    }

    public void selectAddress(WebElement element, String searchstring) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        action.clear(element);
        element.click();
        element.sendKeys(searchstring);
        int x = element.getLocation().getX() + 32;
        int y = element.getLocation().getY() +element.getRect().getHeight()+ 10;
        Thread.sleep(2000);
        new TouchAction(driver).tap(new PointOption().withCoordinates(x, y)).release().perform();
    }

    public String getCustomerSnackBarMessage(){

        WebDriverWait wait = new WebDriverWait( SetupManager.getDriver(), Long.parseLong(PropertyUtility.getProp("WaitTime")));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated( By.id("com.bungii.customer:id/snackbar_text")));
        return action.getText(element);
    }

    public String getDriverSnackBarMessage(){

        WebDriverWait wait = new WebDriverWait( SetupManager.getDriver(), Long.parseLong(PropertyUtility.getProp("WaitTime")));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated( By.id("com.bungii.driver:id/snackbar_text")));
        return action.getText(element);
    }

    public void acceptNotificationAlert() {
        action.click(driverHomePage.Notification_AlertAccept());
    }
}
