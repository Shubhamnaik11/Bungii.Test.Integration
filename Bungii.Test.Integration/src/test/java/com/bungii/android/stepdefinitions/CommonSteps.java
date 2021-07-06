package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.DashBoardPage;
import com.bungii.android.pages.admin.LogInPage;
import com.bungii.android.pages.customer.*;
import com.bungii.android.pages.customer.LocationPage;
import com.bungii.android.pages.driver.*;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.EmailUtility;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.google.common.collect.ImmutableMap;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.common.manager.ResultManager.*;

public class CommonSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(CommonSteps.class);
    private static String ANDROID_PHOTO_PATH = "/sdcard/Pictures";
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    EmailUtility emailUtility = new EmailUtility();
    EstimatePage estimatePage = new EstimatePage();
    HomePage homePage = new HomePage();
    DriverHomePage driverHomePage = new DriverHomePage();
    InProgressBungiiPages inProgressBungiiPages = new InProgressBungiiPages();
    DriverNotAvailablePage driverNotAvailablePage = new DriverNotAvailablePage();
    BungiiDetailsPage bungiiDetailsPage = new BungiiDetailsPage();
    BungiiRequest bungiiRequest = new BungiiRequest();
    BungiiAcceptedPage bungiiAcceptedPage = new BungiiAcceptedPage();
    LocationPage locationPage = new LocationPage();
    SignupPage Page_Signup= new SignupPage();
    private DbUtility dbUtility = new DbUtility();
    com.bungii.android.pages.driver.LoginPage driverLoginPage = new com.bungii.android.pages.driver.LoginPage();
    LogInPage logInPage=  new LogInPage();
    DashBoardPage dashBoardPage=new DashBoardPage();
    PhonePage phonePage = new PhonePage();

    @Given("^I have Large image on my device$")
    public void i_have_large_image_on_my_device() throws Throwable {
        List<String> removePicsArgs = Arrays.asList(

                "/sdcard/ALARGE_IMAGE/"
        );
        Map<String, Object> removePicsCmd = ImmutableMap.of(
                "command", "ls ",
                "args", removePicsArgs
        );
        ((AndroidDriver) SetupManager.getDriver()).executeScript("mobile: shell", removePicsCmd);

        String pickupImage = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"), PropertyUtility.getImageLocations("LARGE_IMAGE"));

        File img = new File(pickupImage);

        ((AndroidDriver) SetupManager.getDriver()).pushFile(ANDROID_PHOTO_PATH + "/" + img.getName(), img);

    }
    @When("^I terminate \"([^\"]*)\" app on \"([^\"]*)\" devices$")
    public void i_terminate_app(String appName, String device) {
        boolean isApplicationIsInForeground = false;

        try {
            if(action.isElementPresent(phonePage.Container_Notification(true)))
            {
                ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                logger.detail("Attempted to hide container");

            }
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(5000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    ((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                    break;
                case "CUSTOMER":
                    ((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }


                pass("Terminated " + appName + " application", "Termination of " + appName + " application is successful");

            //    Thread.sleep(5000);
            //     testStepVerify.isTrue(isApplicationIsInForeground, "Switch to " + appName + " application", "Switch to " + appName + " application is successful", "Switch to " + appName + " application was not successfull");
        } catch (Throwable e) {
        }

    }
    @When("^I Switch to \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_switch_to_something_application_on_something_devices(String appName, String device) {
        boolean isApplicationIsInForeground = false;

        try {
            if(action.isElementPresent(phonePage.Container_Notification(true)))
            {
                ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                logger.detail("Attempted to hide container");

            }
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(3000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    //((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));

                    ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));

                    //  utility.launchDriverApplication();
                    Thread.sleep(5000);
                    isApplicationIsInForeground = utility.isDriverApplicationOpen();
                    break;
                case "CUSTOMER":
                    //  utility.launchCustomerApplication();
                    //((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));

                    ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    Thread.sleep(5000);
                    isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        try {
            //if switch was unsucessfull, try to switch again
            if (!isApplicationIsInForeground) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        utility.launchDriverApplication();
                        //SetupManager.getObject().launchApp(PropertyUtility.getProp("bundleId_Driver"));
                        Thread.sleep(5000);
                        isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        }
                        break;
                    case "CUSTOMER":
                        utility.launchCustomerApplication();
                        // SetupManager.getObject().restartApp();
                        Thread.sleep(4000);
                        isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        }
                        break;
                    default:
                        error("UnImplemented Step or in correct app", "UnImplemented Step");
                        break;
                }
            }
            Thread.sleep(2000);
            if (!isApplicationIsInForeground)
                warning("Switch to " + appName + " application", "Not able to currently verify if " + appName + " application was not successfull");
            if(action.isElementPresent(phonePage.Container_Notification(true)))
            {
                ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                logger.detail("Attempted to hide container");

            }
            else
                pass("Switch to " + appName + " application", "Switch to " + appName + " application is successful");

            //    Thread.sleep(5000);
            //     testStepVerify.isTrue(isApplicationIsInForeground, "Switch to " + appName + " application", "Switch to " + appName + " application is successful", "Switch to " + appName + " application was not successfull");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }
    @When("^I go to \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_switch_to_application_on_something_devices(String appName, String device) {
        boolean isApplicationIsInForeground = false;

        try {
            if(action.isElementPresent(phonePage.Container_Notification(true)))
            {
                ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                logger.detail("Attempted to hide container");

            }
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(5000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                   // ((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));

                    ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));

                    //  utility.launchDriverApplication();
                    Thread.sleep(5000);
                    isApplicationIsInForeground = utility.isDriverApplicationOpen();
                    break;
                case "CUSTOMER":
                    //  utility.launchCustomerApplication();
                   // ((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));

                    ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    Thread.sleep(5000);
                    isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        try {
            //if switch was unsucessfull, try to switch again
            if (!isApplicationIsInForeground) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        utility.launchDriverApplication();
                        //SetupManager.getObject().launchApp(PropertyUtility.getProp("bundleId_Driver"));
                        Thread.sleep(5000);
                        isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        }
                        break;
                    case "CUSTOMER":
                        utility.launchCustomerApplication();
                        // SetupManager.getObject().restartApp();
                        Thread.sleep(4000);
                        isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        }
                        break;
                    default:
                        error("UnImplemented Step or in correct app", "UnImplemented Step");
                        break;
                }
            }
            Thread.sleep(2000);
            if (!isApplicationIsInForeground)
                warning("Switch to " + appName + " application", "Not able to currently verify if " + appName + " application was not successfull");
            if(action.isElementPresent(phonePage.Container_Notification(true)))
            {
                ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                logger.detail("Attempted to hide container");

            }
            else
                pass("Switch to " + appName + " application", "Switch to " + appName + " application is successful");

            //    Thread.sleep(5000);
            //     testStepVerify.isTrue(isApplicationIsInForeground, "Switch to " + appName + " application", "Switch to " + appName + " application is successful", "Switch to " + appName + " application was not successfull");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }
    //open app without restart
    @When("^I Open \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_open_to_something_application_on_something_devices(String appName, String device) {
        boolean isApplicationIsInForeground = false;

        try {
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(5000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    //  ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));

                    utility.launchDriverApplication();
                    Thread.sleep(4000);
                    isApplicationIsInForeground = utility.isDriverApplicationOpen();
                    break;
                case "CUSTOMER":
                    utility.launchCustomerApplication();
                    // ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    Thread.sleep(4000);
                    isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        try {
            //if switch was unsucessfull, try to switch again
            if (!isApplicationIsInForeground) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        utility.launchDriverApplication();
                        //SetupManager.getObject().launchApp(PropertyUtility.getProp("bundleId_Driver"));
                        isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        }
                        break;
                    case "CUSTOMER":
                        utility.launchCustomerApplication();
                        // SetupManager.getObject().restartApp();
                        isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        }
                        break;
                    default:
                        error("UnImplemented Step or in correct app", "UnImplemented Step");
                        break;
                }
            }
            Thread.sleep(2000);
            if (!isApplicationIsInForeground)
                warning("Switch to " + appName + " application", "Not able to currently verify if " + appName + " application was not successfull");
            else
                pass("Switch to " + appName + " application", "Switch to " + appName + " application is successful");

            //    Thread.sleep(5000);
            //     testStepVerify.isTrue(isApplicationIsInForeground, "Switch to " + appName + " application", "Switch to " + appName + " application is successful", "Switch to " + appName + " application was not successfull");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
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
                    isAppInstalled = ((AndroidDriver) (SetupManager.getDriver())).isAppInstalled(PropertyUtility.getDataProperties("twitter.bundle.id"));
                    break;
                case "FACEBOOK":
                    SetupManager.getObject().terminateApp(PropertyUtility.getDataProperties("facebook.bundle.id"));
                    // ((AndroidDriver) (SetupManager.getDriver())).terminateApp(PropertyUtility.getDataProperties("facebook.bundle.id"));
                    isAppInstalled = ((AndroidDriver) (SetupManager.getDriver())).isAppInstalled(PropertyUtility.getDataProperties("facebook.bundle.id"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            switch (expectedOutcome.toUpperCase()) {
                case "INSTALLED":
                    testStepAssert.isTrue(isAppInstalled, appName + " should be installed", appName + " is Not installed");
                    break;
                case "NOT INSTALLED":
                    testStepAssert.isFalse(isAppInstalled, appName + " should be installed", appName + " is Not installed");
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

    @Given("^I have device which has location permission$")
    public void i_have_device_which_has_location_permission() throws Throwable {
        try {
            String deviceApiLevel = ((AndroidDriver) SetupManager.getDriver()).getCapabilities().getCapability("deviceApiLevel").toString();
            testStepAssert.isFalse(Integer.parseInt(deviceApiLevel)<23,"Device api level should be or above 23","Device level is "+deviceApiLevel);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
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
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/ScheduledTrips");

                    //action.click(dashBoardPage.Button_Trips());
                    //action.click(dashBoardPage.Button_ScheduledTrips());
                    break;
                case "live deliveries":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/Trips?isComplete=False");

                    //action.click(dashBoardPage.Button_Trips());
                    //action.click(dashBoardPage.Button_LiveTrips());
                    break;
                case "promo code":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/PromoCodes");
                    //action.click(dashBoardPage.Button_PromoCode());
                   // action.click(dashBoardPage.Link_StandardCodes());
                    break;
                case "referral source":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/ReferralSource");

                   // action.click(dashBoardPage.Button_Marketing());
                    //action.click(dashBoardPage.Button_ReferralSource());
                    break;
                case "customers":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/Customers");

                   // action.click(dashBoardPage.Button_Customers());
                    break;
                case "deliveries":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/Trips?isComplete=True");
                    //action.click(dashBoardPage.Button_Trips());
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

    @When("^I open new \"([^\"]*)\" browser for \"([^\"]*)\"$")
    public void i_open_new_something_browser_for_something_instance(String browser, String instanceName) {
        try {
            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS") || PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID")) {
                String currentKey = DriverManager.getCurrentKey();
                if(DriverManager.driverArray.size()>1) {
                    for (Map.Entry<String, WebDriver> entry : DriverManager.driverArray.entrySet()) {
                        entry.getValue().getPageSource();
                        logger.detail("Pinging : "+ entry.getKey());
                    }
                    DriverManager.driverArray.get(currentKey).getPageSource();
                    //Ping all instances to keep them running in browserstack, used in duo scenarioss
                }
            }
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

    @When("^I connect to \"([^\"]*)\" using \"([^\"]*)\" instance$")
    public void i_connect_to_something_using_something_instance(String deviceId, String instanceName) {
        try {
            SetupManager.getObject().createNewAndroidInstance(instanceName, deviceId);
            SetupManager.getObject().useDriverInstance(instanceName);
            log("I should be connected to " + deviceId,
                    "I am connected to " + deviceId + " device and assigned session name " + instanceName, true);
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
            log("I switch to  " + instanceName + " instance session",
                    "I switch to  " + instanceName + "instance session", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" page should be opened$")
    public void ThenPageShouldBeOpened(String page) {
        try {
            switch(page)
            {
                case "bungii.com":
                    action.click(locationPage.Option_Chrome());
                    action.click(locationPage.Button_Always());
            }
            boolean isCorrectPage = utility.isCorrectPage(page);
            testStepAssert.isTrue(isCorrectPage, page + " should be displayed", page + " is displayed correctly  ", page + " is not displayed correct");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on allow location screen$")
    public void i_should_see_something_on_allow_location_screen(String identifier) throws Throwable {
        try {

            switch (identifier.toLowerCase()) {
                case "all details":
                    testStepVerify.isEquals(action.getText(locationPage.Subheader_FAQPage()), PropertyUtility.getMessage("customer.navigation.allow.location.header"));
                    testStepVerify.isEquals(action.getText(locationPage.Text_Info()), PropertyUtility.getMessage("customer.navigation.allow.location.text"));
                    testStepVerify.isElementEnabled(locationPage.Image_Compass(), " Compass image should be displayed");
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

    @Then("^I verify and allow access of Location from Bungii application$")
    public void i_allow_access_of_location_from_bungii_application() throws Throwable {
        try {
            action.click(locationPage.Button_Sure());
            testStepVerify.isEquals(action.getText(locationPage.Alert_Text()), "Allow Bungii to access this device's location?");
            action.click(locationPage.Button_Allow());
            pass("I allow access of Location from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify and deny access of Location from Bungii application$")
    public void i_deny_access_of_location_from_bungii_application() throws Throwable {
        try {
            action.click(locationPage.Button_Sure());
            testStepVerify.isEquals(action.getText(locationPage.Alert_Text()), "Allow Bungii to access this device's location?");
            action.click(locationPage.Button_Deny());
            pass("I allow access of Location from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify and allow access of Location upon reasking from Bungii application$")
    public void i_asdaccess_of_location_from_bungii_application() throws Throwable {
        try {
            testStepVerify.isEquals(action.getText(locationPage.Alert_Text()), "Allow Bungii to access this device's location?");
            testStepVerify.isEquals(action.getText(locationPage.CheckBox_DontAskAgain()), "Don't ask again");
            action.click(locationPage.Button_Allow());
            pass("I allow access of Location from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify and allow access of Location from Bungii driver application$")
    public void i_deny_access_of_location_from_bungiidriver_application() throws Throwable {
        try {
            com.bungii.android.pages.driver.LocationPage locationPage = new com.bungii.android.pages.driver.LocationPage();

            action.click(locationPage.Button_Sure());
            testStepVerify.isEquals(action.getText(locationPage.Alert_Text()), "Allow Bungii Driver to access this device's location?");
            action.click(locationPage.Button_Allow());
            pass("I allow access of Location from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on allow location driver screen$")
    public void i_should_see_something_on_allow_locationdriver_screen(String identifier) throws Throwable {
        try {
            com.bungii.android.pages.driver.LocationPage locationPage = new com.bungii.android.pages.driver.LocationPage();
            switch (identifier.toLowerCase()) {
                case "all details":
                    testStepVerify.isEquals(action.getText(locationPage.Subheader_FAQPage()), PropertyUtility.getMessage("customer.navigation.allow.location.header"));
                    testStepVerify.isEquals(action.getText(locationPage.Text_Info()), PropertyUtility.getMessage("driver.navigation.allow.location.text"));
                    testStepVerify.isElementEnabled(locationPage.Image_Compass(), " Compass image should be displayed");
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

    @And("^I check that \"([^\"]*)\" pages of turotial are present$")
    public void i_check_that_something_pages_of_turotial_are_present(String strArg1) throws Throwable {
        try {
            List<WebElement> xpath = homePage.Button_PdfPages();
            int xpathCount = xpath.size();
            if (xpathCount == 5) {
                testStepAssert.isTrue(true, "There should be 5 pdf pages", "There are 5 pdf pages.");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I check that if i can swipe the pages$")
    public void i_check_that_if_i_can_swipe_the_pages() throws Throwable {
        try {
            List<WebElement> xpath = homePage.Button_PdfPages();
            WebDriver driver = null;
            WebElement ele = null;
            int xpathCount = xpath.size();
            boolean isClicked = false, isSwiped = false;
            for (WebElement tutorialPage : xpath) {
                action.click(tutorialPage);
                isClicked = true;
            }
            testStepAssert.isTrue(isClicked, "5 pages are present.", "5 pages are not present.");
            action.click(homePage.Text_TutorialPdfPage1());
            for (int i = 0; i < xpathCount - 1; i++) {
                action.swipeLeft(homePage.Text_TutorialPdf());
                isSwiped = true;
            }

            testStepAssert.isTrue(isSwiped, "Swiped through the pages.", "Couldn't swipe through the pages.");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I tap the \"([^\"]*)\" button is present on last page$")
    public void i_tap_the_something_button_is_present_on_last_page(String strArg1) throws Throwable {
        try {
            testStepVerify.isElementEnabled(homePage.Button_StartApp(), "START button enabled", "START button enabled", "START button not enabled");
            action.click(homePage.Button_StartApp());
            testStepAssert.isElementTextEquals(homePage.Title_HomePage(), "BUNGII", "Expected Text is present.", "Expected Text is present.", "Expected Text is not present.");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I verify that the tutorial is displayed only once$")
    public void i_verify_that_the_tutorial_is_displayed_only_once() throws Throwable {
        try {
            testStepAssert.isElementTextEquals(homePage.Title_HomePage(), "BUNGII", "Expected Text is present.", "Expected Text is present.", "Expected Text is not present.");

            // testStepAssert.isNotElementDisplayed(homePage.Text_TutorialPdf(), "Tutorials should not be displayed.", "Tutorials should not be displayed.", "Tutorials are displayed.");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @Then("^Alert message with (.+) text should be displayed$")
    public void alert_message_with_text_should_be_displayed(String message) {
        try {
            String actualMessage = "";
            Thread.sleep(10000);
            if (action.isElementPresent(estimatePage.Alert_ConfirmRequestMessage(true))) {
                actualMessage = estimatePage.Alert_ConfirmRequestMessage(true).getText();
            } else if (actualMessage.equals("")) {
                actualMessage = action.getText(driverHomePage.Alert_NewBungii(true));
            } else {
                actualMessage = bungiiRequest.Alert_Msg(true).getText();
            }
            String expectedMessage = null;
            switch (message.toUpperCase()) {
                case "DRIVER CANCELLED":
                    expectedMessage = PropertyUtility.getMessage("customer.alert.driver.cancel");
                    break;
                case "TRIP CANNOT BE CANCELED AS CONTROL DRIVER NOT STARTED":
                    expectedMessage = PropertyUtility.getMessage("driver.alert.noncontrol.cancel.before.control");
                    logger.detail("PAGE SOURCE" + SetupManager.getDriver().getPageSource());
                    break;
                case "OOPS! WE FOCUS ON LOCAL DELIVERIES WITHIN 150 MILES OF PICKUP. IT LOOKS LIKE THIS TRIP IS A LITTLE OUTSIDE OUR SCOPE.":
                    expectedMessage = PropertyUtility.getMessage("customer.alert.long.haul");
                    break;
                case "HMM, IT LOOKS LIKE YOU ALREADY HAVE A BUNGII SCHEDULED. AT THIS TIME, OUR SYSTEM ONLY ALLOWS ONE BUNGII AT A TIME.":
                    expectedMessage = PropertyUtility.getMessage("customer.alert.alreadyscheduled");
                    action.click(estimatePage.Button_SystemCalenderOK());
                    break;
                case "ACCEPT BUNGII QUESTION":
                    expectedMessage = PropertyUtility.getMessage("driver.bungii.request.ondemand.question");
                    break;
                case "DRIVER CANCEL BUNGII":
                    expectedMessage = PropertyUtility.getMessage("driver.cancel.bungii");
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
                case "DELETE WARNING":
                    expectedMessage = PropertyUtility.getMessage("customer.payment.delete");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }

            testStepAssert.isEquals(actualMessage, expectedMessage,
                    "Alert with text " + expectedMessage + "should be displayed",
                    "Alert with text ," + expectedMessage + " should be displayed",
                    "Alert Message is not displayed, actual Message " + actualMessage + " Expected is "
                            + expectedMessage);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step, error in fetching alert : "+ message, true);
        }
    }

    @Then("^user is alerted for \"([^\"]*)\"$")
    public void user_is_alerted_for_something(String key) {
        try {
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
                case "FOR EMERGENCY CONTACT SUPPORT LINE":
                    expectedText = PropertyUtility.getMessage("driver.cancel.support.contact");
                    break;
                case "PICKUP REQUEST NO LONGER AVAILABLE":
                    expectedText=PropertyUtility.getMessage("driver.request.unavailable");
                    break;
                case "60 MINS BEFORE SCHEDULE TRIP TIME":
                    expectedText = PropertyUtility.getMessage("driver.start.60.mins.before");
                    break;
                case "PICKUP ALREADY ACCEPTED BY YOU":
                    expectedText = PropertyUtility.getMessage("driver.request.already.accepted");
                    break;
                case "REQUIRED DRIVER NOT ACCEPTED":
                    expectedText = PropertyUtility.getMessage("driver.required.not.accepted");
                    break;
                case "CUSTOMER HAS ONGOING BUNGII":
                    expectedText = PropertyUtility.getMessage("driver.start.customer.ongoing");
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            String alertText = driverNotAvailablePage.Alert_ConfirmRequestMessage().getText();
            testStepVerify.isEquals(alertText, expectedText);
            action.click(bungiiDetailsPage.Button_Yes());
            Thread.sleep(1000);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^User should see message \"([^\"]*)\" text on the screen$")
    public void user_should_see_message_something_text_on_the_screen(String message) throws Throwable {
        try {
            String actualMessage = "";//utility.getSnackBarMessage();
            String expectedMessage;
            switch (message.toUpperCase()) {
                case "OUTSIDE BUISSNESS HOUR":
                    actualMessage = utility.getCustomerSnackBarMessage();
                    expectedMessage = "We’re only able to schedule Bungii’s between 12:15 AM - 11:30 PM. Please choose a time in that range."; //PropertyUtility.getMessage("customer.alert.outsidebuissnesshour.android");
                    action.click(estimatePage.Samsung_Time_Cancel());
                    break;

                case "DELETE WARNING":
                    actualMessage = utility.getCustomerSnackBarMessage();
                    expectedMessage = PropertyUtility.getMessage("customer.payment.delete");
                    break;
                case "60 MINS BEFORE SCHEDULE TRIP TIME":
                    actualMessage = utility.getDriverSnackBarMessage();
                    expectedMessage=PropertyUtility.getMessage("driver.start.60.mins.before");
                    break;
                case "Please install a browser in order to access this link.":
                    actualMessage = utility.getCustomerSnackBarMessage();
                    expectedMessage = PropertyUtility.getMessage("browser.uninstalled.message");
                    action.click(inProgressBungiiPages.Button_Cancel_Yes());
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
    public void i_click_something_on_alert_message(String strArg1) throws Throwable {
        try {
            if (strArg1.equalsIgnoreCase("cancel"))
                action.click(estimatePage.Button_Cancel());
            else if(strArg1.equalsIgnoreCase("View"))
                action.click(estimatePage.Button_AcceptRequest());
            else
                action.click(estimatePage.Button_OK());


            log("I should able to click " + strArg1 + "on Alert Message",
                    "I clicked " + strArg1 + "on Alert Message", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I click \"([^\"]*)\" button on alert message$")
    public void i_click_something_button_on_alert_message(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "YES":
                    action.click(bungiiRequest.AlertButton_View());
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^Alert should have \"([^\"]*)\" button$")
    public void alert_should_have_something_button(String list) throws Throwable {
        switch (list) {
            case "cancel,proceed":
                testStepVerify.isElementEnabled(estimatePage.Button_Cancel(true), "Cancel button should be displayed");
                testStepVerify.isElementEnabled(estimatePage.Button_Proceed(true), " Proceed button should be displayed");
                break;

            default:
                throw new Exception(" UNIMPLEMENTED STEP");
        }
    }

    @Given("^I newly installed \"([^\"]*)\" app$")
    public void i_newly_installed_something_app(String strArg1) throws Throwable {
        try {
            GeneralUtility utility = new GeneralUtility();
            utility.resetApp();
            log("I reset Cancel App Data",
                    "I reset Estimate App Data", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I click on device \"([^\"]*)\" button$")
    public void i_click_on_device_something_button(String strArg1) throws Throwable {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            driver.navigate().back();
            log("I tap on device back button",
                    "I tapped on device back button", false);
        } catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" on Confirmation Popup$")
    public void i_click_something_on_confirmation_popup(String option) throws Throwable {
        try {
            switch (option) {
                case "Yes":
                    action.click(inProgressBungiiPages.Button_Cancel_Yes());
                    break;
            }
        } catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I get TELET time of of the current trip$")
    public void i_get_telet_time_of_of_the_current_trip() throws Throwable {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //    phoneNumber="8888889907";
        String custRef = dbUtility.getCustomerRefference(phoneNumber);
        String teletTime = dbUtility.getTELETfromDb(custRef);

        cucumberContextManager.setScenarioContext("TELET", teletTime);
        logger.detail("TELET Time of Trip of customer : "+ phoneNumber + " is " + teletTime );
    }

    @And("^I get TELET time of currrent trip of customer 2$")
    public void i_get_telet_time_of_of_the_currewnt_trip() throws Throwable {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
        //    phoneNumber="8888889907";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String teletTime = dbUtility.getTELETfromDb(custRef);

        cucumberContextManager.setScenarioContext("TELET", teletTime);
        logger.detail("TELET Time of Trip of customer 2 : "+ phoneNumber + " is " + teletTime );
    }

    @Then("^Telet time of current trip should be correctly calculated$")
    public void telet_time_of_current_trip_should_be_correctly_calculated() throws Throwable {
        com.bungii.android.utilityfunctions.GeneralUtility utility = new com.bungii.android.utilityfunctions.GeneralUtility();
        String teletTimeLocal = utility.calculateTeletTime();
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
        testStepVerify.isEquals(strdateDB, strdatelocal);

    }

    @Then("^Telet time of research trip should be not be same as previous trips$")
    public void telet_time_of_current_trip_should_be_correctly_calculatedtrip() throws Throwable {
        String previousTelet = (String) cucumberContextManager.getScenarioContext("TELET");
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //    phoneNumber="8888889907";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String newTeletTime = dbUtility.getTELETfromDb(custRef);
        testStepAssert.isTrue(!previousTelet.equals(newTeletTime), "Research trip time should not be same as Telet Time", "Research trip time is same as Telet Time");

    }

    @And("^I tap on \"([^\"]*)\" button of android mobile$")
    public void i_tap_on_something_button_of_android_mobile(String strArg1) throws Throwable {
        action.NavigateBack();
        log("I tap back button "  ,"I tapped back button",
                true);
    }

    @And("^I tap on \"([^\"]*)\" icon of page$")
    public void i_tap_on_something_icon_of_page(String strArg1) throws Throwable {
        if (!action.isElementPresent(estimatePage.Button_Back(true))) {
            action.NavigateBack();
        } else {
            action.click(estimatePage.Button_Back(true));
        }
        log("I tap back button "  ,"I tapped back button",
                 true);
    }

    @Then("^I wait for \"([^\"]*)\" mins$")
    public void i_wait_for_something_mins(String strArg1) throws Throwable {
        action.hardWaitWithSwipeUp(Integer.parseInt(strArg1));
    }

    @Then("^I save customer phone and referral code in feature context$")
    public void i_save_customer_phone_and_referral_code_in_feature_context() throws Throwable {
        try {
            cucumberContextManager.setFeatureContextContext("INVITE_CODE", (String) cucumberContextManager.getScenarioContext("INVITE_CODE"));
            cucumberContextManager.setFeatureContextContext("CUSTOMER_HAVING_REF_CODE", (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER"));
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I have customer with referral code$")
    public void i_save_customer_phone_and_referral_code_iADDED_PROMO_CODEn_feature_context() throws Throwable {
        try {
            String refCode = (String) cucumberContextManager.getFeatureContextContext("INVITE_CODE");//refCode="119W5";
            String phoneNumber = (String) cucumberContextManager.getFeatureContextContext("CUSTOMER_HAVING_REF_CODE");//phoneNumber="9999992799";
           // phoneNumber = (String) cucumberContextManager.getFeatureContextContext("NEW_USER_NUMBER");//phoneNumber="9999992799";
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", refCode);
            cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", phoneNumber);
            testStepAssert.isTrue(refCode.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
            testStepAssert.isTrue(phoneNumber.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I click \"([^\"]*)\" on the alert message$")
    public void i_click_something_on_the_alert_message(String strArg1) throws Throwable {
        try {
            Thread.sleep(2000);
            switch (strArg1) {
                case "OK":
                    action.click(bungiiAcceptedPage.Button_OK());
                    break;
                case "YES":
                    action.click(inProgressBungiiPages.Button_Cancel_Yes());
                    break;

            }
            log("I click "+strArg1+" on the alert message","I clicked "+strArg1+" on the alert message",false);
        } catch (Exception e) {
            log("I should able to click " + strArg1 + "on Alert Message",
                    "I clicked " + strArg1 + "on Alert Message", true);
        }
    }

    @Then("^Driver should see \"([^\"]*)\" message$")
    public void driver_should_see_something_message(String strArg1) throws Throwable {
        try {
            testStepAssert.isEquals(action.getText(locationPage.Alert_Text()), strArg1, strArg1 +" should be displayed.", strArg1 +" is displayed.", strArg1 +" is not displayed.");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in viewing alert", true);
        }
    }

    @And("^I Enter \"([^\"]*)\" value in \"([^\"]*)\" field in \"([^\"]*)\" Page$")
    public void i_enter_something_value_in_something_field_in_something_page(String value, String field, String screen, DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String referralCode=dataMap.get("Referral Code").trim();

            switch (field.toUpperCase()) {
                case "REFERRAL CODE":
                    action.click(Page_Signup.CheckBox_Promo());
                    action.click(Page_Signup.TextField_Referral());
                    action.sendKeys(Page_Signup.TextField_Referral(),referralCode);
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            log("I should able to Enter " + referralCode + " value in " + field + " field in " + screen + " Page",
                    "I Entered " + referralCode + " in " + field + " field", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should be navigated to \"([^\"]*)\" screen$")
    public void i_should_be_naviagated_to_something_screen(String screen) {
        try {
            boolean isCorrectPage = false;
            isCorrectPage = utility.isCorrectPage(screen);
            testStepVerify.isTrue(isCorrectPage, "I should be naviagated to " + screen + " screen",
                    "I should be navigated to " + screen, "I was not navigated to " + screen + " screen ");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    public String[] bungiiTimeForScroll(Date date) {
        //get timezone
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM|h|mm|a");
        String formattedDate = sdf.format(date);
        String[] SplitDate = formattedDate.split("\\|");
        if (DateUtils.isSameDay(date, new Date())) {
            SplitDate[0] = "Today";
        }
        return SplitDate;
    }

    @And("^Customer should receive \"([^\"]*)\" email$")
    public void customer_should_receive_something_email(String emailSubject) throws Throwable {
        String emailBody = utility.GetSpecificURLs(PropertyUtility.getEmailProperties("email.from.address"), PropertyUtility.getEmailProperties("email.client.id"), emailSubject);
        String url = "";
        if(emailBody!=null) {
            action.navigateTo(emailBody);
            url = action.getCurrentURL();
        }
        else
            testStepAssert.isTrue(false,"Email should be received","Email is received :"+ emailSubject,"Email is not received : "+ emailSubject);

        String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        String survey_link = null;
        switch(geofence)
        {
            case "atlanta" :
                survey_link = PropertyUtility.getDataProperties("atlanta.survey.email.link");
                break;
            case "baltimore":
                survey_link = PropertyUtility.getDataProperties("atlanta.survey.email.link");
                break;
        }
        testStepAssert.isTrue(url.contains(survey_link),"Survey Email link should be "+survey_link,"Survey email link is "+ survey_link,"Survey email link is "+ url);
    }
    @And("^Customer should receive \"([^\"]*)\" receipt email$")
    public void customer_should_receive_something_receipt_email(String strArg1) throws Throwable {
        GeneralUtility utility = new GeneralUtility();
        String emailSubject="Your Bungii Receipt";
        //  String emailBody = utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"), "POOR DRIVER RATING");

        String emailBody  =  utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"),emailSubject);
        String driverName=(String) cucumberContextManager.getScenarioContext("DRIVER_1");/*driverName="Testdrivertywd_appledv_b_matt Stark_dvOnE";*/
        String customerName=(String)cucumberContextManager.getScenarioContext("CUSTOMER");/*customerName="Testcustomertywd_appleZTDafc Stark";*/
        String bungiiDate="";
        String pickupAddress=(String)cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")+cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2");
        String dropAddress=(String)cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")+cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2");
        String bungiiDuration=utility.getActualTime();
        String bungiiCost=(String)cucumberContextManager.getScenarioContext("BUNGII_COST_CUSTOMER");

        String estimatedTime=(String)cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE_TIME");
        String actualLoadUnloadTime="";
        String estimatedLoadUnloadTime=(String)cucumberContextManager.getScenarioContext("BUNGII_LOADTIME");/*ratingValue="3";*/
        if(emailBody== "")
        {
            testStepAssert.isFail("Email : "+ emailSubject + " not received");
        }
        String tripDetailsLink=extractUrls(emailBody).get(0);
        String message = null;
       // message = utility.getExpectedPoorRatingMail(driverName, customerName, ratingValue, tripDetailsLink);
        testStepAssert.isEquals(emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ",""), message.replaceAll(" ",""),"Email "+emailBody+" content should match", "Email  "+emailBody+" content matches", "Email "+emailBody+"  content doesn't match");
    }

    @Then("^Customer should receive signup email$")
    public void partner_firm_should_receive_something_email() throws Throwable {
        String emailSubject="New to Bungii? Good.";
/*        cucumberContextManager.setScenarioContext("NEW_USER_EMAIL_ADDRESS","bungiiauto+obKm@gmail.com");
        cucumberContextManager.setScenarioContext("FIRST_NAME","TestCustomertywdappleMzr");*/

        String emailBody = utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.welcome.from.address"), (String)cucumberContextManager.getScenarioContext("NEW_USER_EMAIL_ADDRESS"), emailSubject);
        List<String> tripDetailsLinks=extractUrls(emailBody);
        utility.getCustomerSignupTemplate((String)cucumberContextManager.getScenarioContext("NEW_USER_EMAIL_ADDRESS"));
        if (emailBody == "") {
            testStepAssert.isFail("Email : " + emailSubject + " not received");
        }
        else{

            boolean isEmailCorrect=utility.validateCustomerSignupEmail(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath())+"\\EmailTemplate\\CustomerSignup.txt",emailBody, (String)cucumberContextManager.getScenarioContext("NEW_USER_FIRST_NAME"),tripDetailsLinks.get(0),tripDetailsLinks.get(1),tripDetailsLinks.get(2),tripDetailsLinks.get(3),tripDetailsLinks.get(4),tripDetailsLinks.get(5),tripDetailsLinks.get(6),tripDetailsLinks.get(7),tripDetailsLinks.get(8));
            testStepAssert.isTrue(isEmailCorrect,"Email should be correct","Email is not correct , check logs for more details");
        }
    }
    @Then("^poor driver ratting should be sent to customer$")
    public void poor_driver_ratting_should_be_sent_to_customer() throws Throwable {
        GeneralUtility utility = new GeneralUtility();
        String emailSubject="POOR DRIVER RATING";
        //  String emailBody = utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"), "POOR DRIVER RATING");

        String emailBody  =  utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"),emailSubject);
        String driverName=(String) cucumberContextManager.getScenarioContext("DRIVER_1");/*driverName="Testdrivertywd_appledv_b_matt Stark_dvOnE";*/
        String customerName=(String)cucumberContextManager.getScenarioContext("CUSTOMER");/*customerName="Testcustomertywd_appleZTDafc Stark";*/
        String ratingValue=(String)cucumberContextManager.getScenarioContext("RATING_VALUE");/*ratingValue="3";*/
        String tripDetailsLink="";
        if(emailBody!=null)
            tripDetailsLink = extractUrls(emailBody).get(0);
        if(emailBody== null)
        {
            testStepAssert.isFail("Email : "+ emailSubject + " not received");
        }
        String message = null;
        message = utility.getExpectedPoorRatingMail(driverName, customerName, ratingValue, tripDetailsLink);
        testStepAssert.isEquals(emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ",""), message.replaceAll(" ",""),"Email "+emailBody+" content should match", "Email  "+emailBody+" content matches", "Email "+emailBody+"  content doesn't match");
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

    @Then("^I accept \"([^\"]*)\" and \"([^\"]*)\" permission if exist$")
    public void I_acceptNotificationAndLocationPermissionIfExist(String Notification, String Location) throws Throwable {
        try {
            GeneralUtility utility = new GeneralUtility();
            String pageName = utility.getPageHeader();
            if(pageName.equalsIgnoreCase("OFFLINE")|| pageName.equalsIgnoreCase("ONLINE")) {
                //do nothing
                logger.detail("Driver app is on Home Screen");
            }
            else{
                if (action.isElementPresent(driverHomePage.Button_Sure(true))) {
                    action.click(driverLoginPage.Button_Sure());
                    action.click(driverLoginPage.Button_Allow());
                    Thread.sleep(15000);
                }

                pageName = utility.getPageHeader();
                if (action.isElementPresent(driverHomePage.Button_Sure(true))) {
                    action.click(driverLoginPage.Button_Sure());
                    action.click(driverLoginPage.Button_Allow());
                }
            }

        } catch (Exception e) {

        }

    }
}
