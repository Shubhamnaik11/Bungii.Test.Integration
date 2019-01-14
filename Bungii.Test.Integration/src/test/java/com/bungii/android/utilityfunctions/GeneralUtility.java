package com.bungii.android.utilityfunctions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.bungii.CustomerHomePage;
import com.bungii.android.pages.bungii.EstimatePage;
import com.bungii.android.pages.bungiiCustomer.LoginPage;
import com.bungii.android.pages.bungiiCustomer.SignupPage;
import com.bungii.android.pages.bungiiCustomer.TermsPage;
import com.bungii.android.pages.bungiiDriver.HomePage;
import com.bungii.android.pages.menus.AccountPage;
import com.bungii.android.pages.menus.MenuPage;
import com.bungii.android.pages.otherApps.OtherAppsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.utilityfunctions.DbUtility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GeneralUtility extends DriverBase {
    static final double MIN_COST = 39;
    ActionManager action = new ActionManager();
    LoginPage Page_Login = new LoginPage();
    SignupPage Page_Signup = new SignupPage();
    TermsPage Page_CustTerms = new TermsPage();
    CustomerHomePage Page_CustHome = new CustomerHomePage();
    MenuPage Page_Menu = new MenuPage();
    OtherAppsPage otherAppsPage = new OtherAppsPage();
    EstimatePage estimatePage = new EstimatePage();
    HomePage driverHomePage = new HomePage();
    AccountPage cutomerAccountPage= new AccountPage();
    com.bungii.android.pages.bungiiDriver.LoginPage driverLoginPage = new com.bungii.android.pages.bungiiDriver.LoginPage();

    public void launchDriverApplication() throws MalformedURLException, InterruptedException {
        AndroidDriver<AndroidElement> driver = (AndroidDriver<AndroidElement>) SetupManager.getDriver();

        //TODO: REMOVE HARD CODING, read from properties
        String appPackage = "com.bungii.driver";
/*
        String appActivity="com.bungii.ui.activities.splash.SplashScreenActivity";
*/
        String appActivity = "com.bungii.ui.activities.splash.SplashScreenActivity";
        Activity activity = new Activity(appPackage, appActivity);
        activity.setStopApp(false);
        ((AndroidDriver<AndroidElement>) driver).startActivity(activity);
        driver.manage().timeouts().implicitlyWait(Long.parseLong(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);
        Thread.sleep(3000);
    }

    public void launchCustomerApplication() throws MalformedURLException, InterruptedException {
        AndroidDriver<AndroidElement> driver = (AndroidDriver<AndroidElement>) SetupManager.getDriver();

        //TODO: REMOVE HARD CODING, read from properties
        String appPackage = "com.bungii.customer";
        String appActivity = "com.bungii.ui.activities.splash.SplashScreenActivity";
        Activity activity = new Activity(appPackage, appActivity);
        activity.setStopApp(false);
        ((AndroidDriver<AndroidElement>) driver).startActivity(activity);
        driver.manage().timeouts().implicitlyWait(Long.parseLong(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);
        Thread.sleep(3000);

    }


    public void isPhoneNumbersEqual(WebElement element, String value) {
        String actualText = element.getText().replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        String expectedText = value.replace(" ", "").replace("-", "").replace(",", "").replace("(", "").replace(")", "").replace("+", "");
        testStepVerify.isEquals(actualText, expectedText, "Twillio Number should be correct", "Twillio number is not correct. actualText:" + actualText + "expectedText:" + expectedText);
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
        double PromoCode = Double.parseDouble(Promo.replace("-$",""));

        double tripTime = Double.parseDouble(estTime);

        double estimate = distance + loadUnloadTime + tripTime-PromoCode;
        estimate = estimate > MIN_COST ? estimate : MIN_COST;

        return estimate;
    }

    private void inputOnNumberKeyBoard(String strNum) throws InterruptedException {
        for (char c : strNum.toCharArray()) {
            ((AndroidDriver) SetupManager.getDriver()).pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + c)));
            System.out.println("   ENTER VALUE :"+c);
            Thread.sleep(200);
        }
        ((AndroidDriver) SetupManager.getDriver()).hideKeyboard();
    }

    public void clickCustomerMenuItem(String menuItem){
        switch(menuItem.toUpperCase()){
            case "HOME":
                action.click(Page_CustHome.Button_NavHome());
                break;
            case "FAQ":
                action.click(Page_CustHome.Button_NavHome());
                break;
            case "ACCOUNT":
                action.click(Page_CustHome.Button_NavAccount());
                break;
            case "PAYMENT":
                action.click(Page_CustHome.Button_NavPayment());
                break;
            case "SUPPORT":
                action.click(Page_CustHome.Button_NavSupport());
                break;
            case "PROMOS":
                action.click(Page_CustHome.Button_NavPromos());
                break;
            case "LOGOUT":
                action.click(Page_CustHome.Button_Navlogout());
                break;
        }
    }
    public String getEstimateTime() {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        return DbUtility.getEstimateTime(custRef);
    }


    public void loginToCustomerApp(String phone, String password) throws InterruptedException {
        if (action.isElementPresent(Page_Signup.Link_Login(true))) {
            action.click(Page_Signup.Link_Login());
            //

            WebElement element = Page_Login.TextField_PhoneNumber();
            if (StringUtils.isNumeric(phone)) {
                element.sendKeys();
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
            }
        } else {
            //I am not on Login screen
        }
        //AssertionManager.ElementDisplayed(Page_CustHome.Title_HomePage);
        //AssertionManager.ElementDisplayed(Page_CustHome.Link_Invite);
    }

    public void LogoutCustomerApp() {
        if (action.isElementPresent(Page_CustHome.Link_Menu())) {
            action.click(Page_CustHome.Link_Menu());
            action.click(Page_Menu.Menu_Logout());
        }
    }

    public void loginToDriverApp(String phone, String password) throws InterruptedException {
        if (action.isElementPresent(driverLoginPage.TextField_PhoneNumber(true))) {
            WebElement element=driverLoginPage.TextField_PhoneNumber();

            if (StringUtils.isNumeric(phone)) {
                element.sendKeys();
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
        int y = element.getLocation().getY() + 176;
        Thread.sleep(2000);
        new TouchAction(driver).tap(new PointOption().withCoordinates(x, y)).release().perform();
    }

    public void acceptNotificationAlert() {
        action.click(driverHomePage.Notification_AlertAccept());
    }
}
