package com.bungii.ios.utilityfunctions;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.enums.Status;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.DashBoardPage;
import com.bungii.ios.pages.admin.LogInPage;
import com.bungii.ios.pages.admin.ScheduledTripsPage;
import com.bungii.ios.pages.customer.*;
import com.bungii.ios.pages.driver.BungiiCompletedPage;
import com.bungii.ios.pages.driver.HomePage;
import com.bungii.ios.pages.driver.LoginPage;
import com.bungii.ios.pages.driver.UpdateStatusPage;
import com.bungii.ios.pages.other.MessagesPage;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.ios.stepdefinitions.admin.LogInSteps;
import com.bungii.ios.stepdefinitions.admin.ScheduledTripSteps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.datatable.DataTable;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralUtility extends DriverBase {
    private static LogUtility logger = new LogUtility(GeneralUtility.class);

    NotificationPage notificationPage = new NotificationPage();
    HomePage driverHomePage = new HomePage();
    com.bungii.ios.pages.customer.HomePage customerHomePage = new com.bungii.ios.pages.customer.HomePage();
    EstimatePage estimatePage = new EstimatePage();
    ActionManager action = new ActionManager();
    LoginPage driverLoginPage = new LoginPage();
    UpdateStatusPage driverUpdateStatusPage = new UpdateStatusPage();
    MessagesPage messagesPage = new MessagesPage();
    BungiiCompletePage bungiiCompletePage = new BungiiCompletePage();
    PromotionPage promotionPage = new PromotionPage();
    BungiiCompletedPage driverBungiiCompletedPage = new BungiiCompletedPage();
    EnableNotificationPage enableNotificationPage = new EnableNotificationPage();
    EnableLocationPage enableLocationPage = new EnableLocationPage();
    com.bungii.ios.pages.customer.UpdateStatusPage customerUpdateStatusPage = new com.bungii.ios.pages.customer.UpdateStatusPage();
    ScheduledBungiiPage scheduledBungiiPage = new ScheduledBungiiPage();
    int[][] rgb = {
            {238, 29, 55},
            {255, 169, 66},
            {169, 204, 50},
            {37, 171, 226},
            {50, 51, 255},

    };

    /**
     * @param file File object pointing to screenshot image file
     * @param x    x co-ordinate of point whose pixel color needs to be find out
     * @param y    y co-ordinate of point whose pixel color needs to be find out
     * @return RGB value of pount
     * @throws IOException
     */
    public static int[] getPixelColor(File file, int x, int y) throws IOException {

        BufferedImage image = ImageIO.read(file);
        image.getWidth();
        int deviceWidth = SetupManager.getDriver().manage().window().getSize().width;
        int retinaRatio = image.getWidth() / deviceWidth;
        // Getting pixel color by position x and y
        int clr = image.getRGB(retinaRatio * x, retinaRatio * y);
        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;
        logger.detail("Red Color value = " + red);
        logger.detail("Green Color value = " + green);
        logger.detail("Blue Color value = " + blue);

        int[] rgbValue = {red, green, blue};
        return rgbValue;
    }

    public String GetAdminUrl() {
        String adminURL = null;
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("DEV"))
            adminURL = PropertyUtility.getDataProperties("dev.admin.url");
        if (environment.equalsIgnoreCase("QA") || environment.equalsIgnoreCase("QA_AUTO"))
            adminURL = PropertyUtility.getDataProperties("qa.admin.url");
        if (environment.equalsIgnoreCase("STAGE"))
            adminURL = PropertyUtility.getDataProperties("stage.admin.url");
        return adminURL;
    }


    public void handleIosUpdateMessage(){
        if(action.isAlertPresent()) {
            String alertMessage = action.getAlertMessage();
            List<String> getListOfAlertButton = action.getListOfAlertButton();
            if (alertMessage.contains("new iOS update")) {
                if (getListOfAlertButton.contains("Close")) {
                    action.clickAlertButton("Close");
                }
            }
        }
    }

    public void recoverScenarioscheduled() {

        try {
            logger.detail("Inside recovery scenario for scheduled");

            //restart app
        ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
        action.click(customerHomePage.Button_AppMenu());
        action.click(customerHomePage.AppMenu_ScheduledTrip());
        List<WebElement> allschBungii=scheduledBungiiPage.List_SchBungii();
        ArrayList<String> detailsArray = new ArrayList<String>();
        if(allschBungii.size()>0) {
            for (int i = 0; i < allschBungii.size(); i++) {
                detailsArray.add(action.getNameAttribute(allschBungii.get(i)));
            }
            SetupManager.getObject().createNewWebdriverInstance("RECOVERY", "chrome");
            SetupManager.getObject().useDriverInstance("RECOVERY");
            SetupManager.getDriver().get(GetAdminUrl());
            new LogInSteps(new LogInPage()).i_log_in_to_admin_portal();
            new DashBoardSteps(new DashBoardPage()).i_select_something_from_admin_sidebar("scheduled trip");
            Map<String, String> tripDetails = new HashMap<String, String>();
            tripDetails.put("CUSTOMER", (String) cucumberContextManager.getScenarioContext("CUSTOMER"));
            String bungiiTime = "";
            for (int i = 0; i < detailsArray.size(); i++) {
                bungiiTime = detailsArray.get(i);
                logger.detail("bungiiTime" + bungiiTime);
                tripDetails.put("SCHEDULED_DATE", new ScheduledTripSteps(new ScheduledTripsPage()).getPortalTime(bungiiTime.replace("CDT", "CST").replace("EDT", "EST").replace("MDT", "MST")));
                tripDetails.put("BUNGII_DISTANCE", "");
                new ScheduledTripSteps(new ScheduledTripsPage()).cancelBungii(tripDetails, "5", "RECOVERY");

            }
        }
        }catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));

        }
        SetupManager.getObject().useDriverInstance("ORIGINAL");
    }

    public void recoverScenario() {
        logger.detail("Inside recovery scenario");

        if (action.isElementPresent(customerHomePage.Application_Name())) {
            //do nothing
        } else if (action.isElementPresent(notificationPage.Button_NotificationScreen(true)) || action.isElementPresent(notificationPage.Cell_Notification(true))) {
            //Remove notification screen
            action.hideNotifications();
            logger.detail("Notification page is removed");
        }
        //Handle Alert
        if (action.isAlertPresent()) {

            String alertMessage = action.getAlertMessage();
            logger.detail("Alert is present on screen,Alert message:" + alertMessage);

            List<String> getListOfAlertButton = action.getListOfAlertButton();

            if (alertMessage.contains("Software Update")) {
                if (getListOfAlertButton.contains("Later")) {
                    action.clickAlertButton("Later");
                    if (action.isElementPresent(messagesPage.Button_RemindMeLater(true)))
                        action.click(messagesPage.Button_RemindMeLater());
                }
            } else if (alertMessage.contains("new iOS update")) {
                if (getListOfAlertButton.contains("Close")) {
                    action.clickAlertButton("Close");

                }
            } else if (getListOfAlertButton.contains("Cancel")) {
                action.clickAlertButton("Cancel");
            } else {
                if (getListOfAlertButton.contains("Close"))
                    action.clickAlertButton("Close");
                else if(getListOfAlertButton.contains("Always Allow"))
                    action.clickAlertButton("Always Allow");
                else if(getListOfAlertButton.contains("Always Allow"))
                    action.clickAlertButton("Always Allow");
                else if(getListOfAlertButton.contains("Allow"))
                    action.clickAlertButton("Allow");

            }
        }
        SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
        // action.switchApplication(PropertyUtility.getProp("bundleId_Driver"));
        logger.detail("Switched to Driver in recovery scenario");

        //If we restart app then close view item page is dismissed
        //view item page
/*        if (action.isElementPresent(driverUpdateStatusPage.Button_CloseViewItems(true))) {
            action.click(driverUpdateStatusPage.Button_CloseViewItems());
            logger.detail("Clicked Close on view item screen");

        }*/
        if (action.isElementPresent(driverUpdateStatusPage.Text_NavigationBar(true))) {

            String screen = action.getNameAttribute(driverUpdateStatusPage.Text_NavigationBar());
            logger.detail("screen is " + screen);
            if (screen.equalsIgnoreCase(Status.ARRIVED.toString())) {
                logger.detail("Driver struck on arrived screen");
                action.click(driverUpdateStatusPage.Button_Cancel());
                action.clickAlertButton("Yes");
            } else if (screen.equals(Status.EN_ROUTE.toString())) {
                logger.detail("Driver struck on EN_ROUTE screen");
                action.click(driverUpdateStatusPage.Button_Cancel());
                action.clickAlertButton("Yes");
            } else if (screen.equals(Status.LOADING_ITEM.toString())) {
                logger.detail("Driver struck on LOADING_ITEM screen");
                updateStatus();
                updateStatus();
                updateStatus();
                if(action.isAlertPresent()){
                    if (action.getListOfAlertButton().contains("INITIATE")) {
                        action.clickAlertButton("INITIATE");
                    }}
                action.click(driverBungiiCompletedPage.Button_NextTrip());
            } else if (screen.equals(Status.DRIVING_TO_DROP_OFF.toString())) {
                logger.detail("Driver struck on DRIVING_TO_DROP_OFF screen");
                updateStatus();
                updateStatus();
                if(action.isAlertPresent()){
                    if (action.getListOfAlertButton().contains("INITIATE")) {
                        action.clickAlertButton("INITIATE");
                    }}
                action.click(driverBungiiCompletedPage.Button_NextTrip());
            } else if (screen.equals(Status.UNLOADING_ITEM.toString())) {
                logger.detail("Driver struck on UNLOADING_ITEM screen");
                updateStatus();
                if(action.isAlertPresent()){
                    if (action.getListOfAlertButton().contains("INITIATE")) {
                        action.clickAlertButton("INITIATE");
                    }}
                action.click(driverBungiiCompletedPage.Button_NextTrip());
            } else if (screen.equals(PropertyUtility.getMessage("driver.navigation.bungii.completed"))) {
                logger.detail("Driver struck on bungii completed screen");
                action.click(driverBungiiCompletedPage.Button_NextTrip());
            }

        }
        SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Customer"));
        // action.switchApplication(PropertyUtility.getProp("bundleId_Customer"));
        if (action.isAlertPresent()) {
            List<String> getListOfAlertButton = action.getListOfAlertButton();
            if (getListOfAlertButton.contains("OK"))
                action.clickAlertButton("OK");
        }
        String NavigationBarName = action.getNameAttribute(customerHomePage.Text_NavigationBar());
        if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
            logger.detail("Customer struck on searching screen");
            action.click(estimatePage.Button_Cancel());
            SetupManager.getDriver().switchTo().alert().accept();
        } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.bungii.complete"))) {
            logger.detail("Customer struck on bungii complete screen");
            action.click(bungiiCompletePage.Button_Close());
            ;
            action.click(promotionPage.Button_IdontLikePromo());
        } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.promotion"))) {
            logger.detail("Customer struck on promotion screen");
            action.click(promotionPage.Button_IdontLikePromo());
        }
    }

    /**
     * Slide the slider to update status
     */
    public void updateStatus() {
        //get locator rectangle is time consuming process
/*        if (initial == null)
            initial = action.getLocatorRectangle(updateStatusPage.AreaSlide());*/
        Rectangle initial;
        if (!isSliderValueContainsInContext("DRIVER")) {
            initial = action.getLocatorRectangle(driverUpdateStatusPage.AreaSlide());
            addSliderValueToFeatureContext("DRIVER", initial);

        } else {
            initial = getSliderValueFromContext("DRIVER");
        }

        action.dragFromToForDuration(0, 0, initial.getWidth(), initial.getHeight(), 1, driverUpdateStatusPage.AreaSlide());
    }

    /**
     * Calculate estimate cost of trip check if less than minimum cost then
     * return minimum cost
     *
     * @param tripDistance Trip distance
     * @param loadTime     load / unload time
     * @param estTime      estimate trip complete time
     * @param Promo        Promo
     * @return
     */
    public double bungiiEstimate(String tripDistance, String loadTime, String estTime, String Promo) {
        //get bungii type and current geofence type.
        String bungiiType = (String) cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get minimum cost,Mile value,Minutes value of Geofence
        double minCost = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.minimum.cost")),
                perMileValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.miles")),
                perMinutesValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.minutes"));

        //Get trip distance from db instead of screen
        double distance = Double.parseDouble(tripDistance.trim());
        //     double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double loadUnloadTime = Double.parseDouble(loadTime.replace(" mins", ""));
        double tripTime = Double.parseDouble(estTime);

        double estimateCost = distance * perMileValue + loadUnloadTime * perMinutesValue + tripTime * perMinutesValue;
        //check if trip is duo trip , if yes then double estimate cost
        if (bungiiType.equalsIgnoreCase("DUO"))
            estimateCost = estimateCost * 2;
        //Subtract discount value from estimate cost
        Promo = Promo.contains("ADD") ? "0" : Promo;
        double discount = 0;
        if (Promo.contains("$"))
            discount = Double.parseDouble(Promo.replace("-$", ""));
        else if (Promo.contains("%"))
            discount = estimateCost * Double.parseDouble(Promo.replace("-", "").replace("%", "")) / 100;
        estimateCost = estimateCost - discount;
        //Check if estimate is greater than min
        estimateCost = estimateCost > minCost ? estimateCost : minCost;

        return estimateCost;
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

    /**
     * Reset application defined in app capabilite
     */
    public boolean installCustomerApp() {
        boolean isInstalled = false;
        try {

            String customerIPAFile = FileUtility.getSuiteResource("", PropertyUtility.getDataProperties("ipa.file.location").replace("{ENVT}", PropertyUtility.environment));
            if (!Files.exists(Paths.get(customerIPAFile))) {
                customerIPAFile = PropertyUtility.getDataProperties("ipa.file.location").replace("{ENVT}", PropertyUtility.environment);
            }

            logger.detail("IPA file Location " + customerIPAFile);
            if (!Files.exists(Paths.get(customerIPAFile))) {
                logger.detail("IPA file doesnot exist " + customerIPAFile);

/*                warning("IPA file doesnot exist on local machine",
                        "File " + customerIPAFile, false);*/
            }
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).closeApp();
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).removeApp(PropertyUtility.getProp("bundleId_Customer"));
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).installApp(customerIPAFile);
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).launchApp();
            isInstalled = true;

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        return isInstalled;


    }


    /**
     * Reset application defined in app capabilite
     */
    public boolean installDriverApp() {
        boolean isInstalled = false;
        try {

            String driverIpaFile = FileUtility.getSuiteResource("", PropertyUtility.getDataProperties("driver.ipa.file.location").replace("{ENVT}", PropertyUtility.environment));
            if (!Files.exists(Paths.get(driverIpaFile))) {
                driverIpaFile = PropertyUtility.getDataProperties("driver.ipa.file.location").replace("{ENVT}", PropertyUtility.environment);
            }

            logger.detail("IPA file Location " + driverIpaFile);
            if (!Files.exists(Paths.get(driverIpaFile))) {
                logger.detail("IPA file doesnot exist " + driverIpaFile);

   /*             warning("IPA file doesnot exist on local machine",
                        "File " + driverIpaFile, false);*/
            }
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
            try {
                ((IOSDriver<MobileElement>) SetupManager.getDriver()).removeApp(PropertyUtility.getProp("bundleId_Driver"));

            } catch (Exception e) {
            }
            logger.detail("Trying to install app " + driverIpaFile);

            ((IOSDriver<MobileElement>) SetupManager.getDriver()).installApp(driverIpaFile);
            logger.detail("done Trying to install app " + driverIpaFile);

            ((IOSDriver<MobileElement>) SetupManager.getDriver()).launchApp();
            isInstalled = true;

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        return isInstalled;


    }

    public boolean verifyPageHeader(String key) throws InterruptedException {
        String currentApplication = (String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION");

        boolean isCorrectPage = false;
        switch (key.toUpperCase()) {
            case "BUNGII.COM":
                // waitForExpectedElement(By.xpath("//XCUIElementTypeApplication[@name='Safari']"));
                isCorrectPage = driverHomePage.isElementEnabled(driverHomePage.findElement("//XCUIElementTypeApplication[@name='Safari']", PageBase.LocatorType.XPath))
                        && action.getValueAttribute(driverHomePage.findElement("//*[@label='Address']", PageBase.LocatorType.XPath)).contains("bungii.com");
                break;

            case "HOME":
                if (currentApplication.equals("DRIVER")) {
                    String naviagationBar = action.getNameAttribute(driverHomePage.Text_NavigationBar());
                    if (naviagationBar.equals("ONLINE") || naviagationBar.equals("OFFLINE")) {
                        isCorrectPage = true;
                    } else {
                        Thread.sleep(7000);
                        isCorrectPage = action.getNameAttribute(driverHomePage.Text_NavigationBar()).equals("ONLINE") || action.getNameAttribute(driverHomePage.Text_NavigationBar()).equals("OFFLINE");
                    }
                    break;
                } else {
                    //Customer app
                }
            case "AVAILABLE TRIPS":
                if (currentApplication.equals("DRIVER")) {
//                    driverHomePage.visibilityOf(driverHomePage.Text_AvailableTrips());
                    isCorrectPage = action.getNameAttribute(driverHomePage.Text_NavigationBar()).equals("AVAILABLE TRIPS");
                    break;
                }
            default:
                String expectedMessage = getExpectedHeader(key.toUpperCase(), currentApplication);
                try {
                    if (!action.isElementPresent(driverHomePage.Text_NavigationBar(true))) {
                        Thread.sleep(9000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                action.textToBePresentInElementName(driverHomePage.Text_NavigationBar(), expectedMessage);
                isCorrectPage = action.getNameAttribute(driverHomePage.Text_NavigationBar()).equals(expectedMessage);
                if (!isCorrectPage) {
                    action.textToBePresentInElementName(driverHomePage.Text_NavigationBar(), expectedMessage);
                    isCorrectPage = action.getNameAttribute(driverHomePage.Text_NavigationBar()).equals(expectedMessage);
                }
        }
        return isCorrectPage;
    }

    private String getExpectedHeader(String screen, String currentApplication) {
        String expectedMessage = "";
        switch (screen.toUpperCase()) {
            case "BUNGII DETAILS":
                if (currentApplication.equals("CUSTOMER"))
                    expectedMessage = PropertyUtility.getMessage("customer.navigation.bungiidetails");
                else
                    expectedMessage = PropertyUtility.getMessage("driver.navigation.bungiidetails");
                break;
            case "TRIP DETAILS":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.trip.details");
                break;
            case "HOME":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.home");
                break;
            case "FAQ":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.faq");
                break;
            case "ACCOUNT":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.account");
                break;
            case "SCHEDULED BUNGIIS":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.scheduled.bungii");
                break;
            case "PAYMENT":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.payment");
                break;
            case "SUPPORT":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.support");
                break;
            case "PROMOS":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.promos");
                break;

            case "ESTIMATE":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.estimate");
                break;
            case "SUCCESS":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.success");
                break;
            case "BUNGII COMPLETED":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.bungii.completed");
                break;
            case "BUNGII COMPLETE":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.bungii.complete");
                break;
            case "PROMOTION":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.promotion");
                break;
            case "LOG IN":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.login");
                break;
            case "INVITE":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.invite");
                break;
            case "SIGN UP":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.signup");
                break;
            case "FORGOT PASSWORD":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.forgot.password");
                break;
            case "DRIVER NOT AVAILABLE":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.driver.not.found");
                break;
            case "BUNGII REQUEST":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.bungii.request");
                break;
            case "BUNGII ACCEPTED":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.bungii.accepted");
                break;
            case "VERIFICATION":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.verification");
                break;
            case "TERMS AND CONDITION":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.terms.condition");
                break;
            case "ALLOW NOTIFICATIONS":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.allow.notifications");
                break;
            case "ALLOW LOCATION":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.allow.locations");
                break;
            case "DRIVER SEARCH":
            case "SEARCHING":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.searching");
                break;
            default:
                // error("Verify Screen " + screen, "UnImplemented Step or in correct screen", "UnImplemented Step", true);
                break;
        }
        return expectedMessage;
    }


    public void grantPermissionToDriverApp(){
            action.click(enableNotificationPage.Button_Sure());
            action.clickAlertButton("Allow");
        if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
            action.click(enableLocationPage.Button_Sure());
            action.clickAlertButton("Always Allow");
        }
    }

    public void loginToDriverApp(String phone, String password) throws InterruptedException {
        String navigationBarName = action.getNameAttribute(driverHomePage.NavigationBar_Status());
        if (!(navigationBarName.equalsIgnoreCase("ONLINE") || navigationBarName.equalsIgnoreCase("OFFLINE"))) {
            if (action.isAlertPresent()) {

                List<String> getListOfAlertButton = action.getListOfAlertButton();
                if (getListOfAlertButton.contains("OK"))
                    action.clickAlertButton("OK");
            }
            if (action.isElementPresent(driverLoginPage.TextField_PhoneNumber(true))) {
                WebElement element = driverLoginPage.TextField_PhoneNumber();
                action.sendKeys(driverLoginPage.TextField_PhoneNumber(), phone);
                action.sendKeys(driverLoginPage.Textfield_Password(), password);
                action.click(driverLoginPage.Button_Login());
                Thread.sleep(2500);
                navigationBarName = action.getNameAttribute(driverHomePage.NavigationBar_Status());
                if(navigationBarName.equals("NOTIFICATIONS")){
                    grantPermissionToDriverApp();
                }
/*                else if (action.isElementPresent(enableNotificationPage.Button_Sure(true))) {
                    action.click(enableNotificationPage.Button_Sure());
                    action.clickAlertButton("Allow");
                }

                else if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
                    action.click(enableLocationPage.Button_Sure());
                    action.clickAlertButton("Always Allow");
                }*/
            } else {
                //Not on Login page
            }
        }
    }

    public Point getCordinatesForNotification(String pageSource, String expectedText) {
        String REGEX = "name=\"BUNGII DRIVER,.*y=\".{1,}\"";
        String INPUT = pageSource;
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);   // get a matcher object
        int count = 0;
        Point notificationPoint = null;
        while (m.find()) {
            count++;
            System.out.println("Match number " + count);
            System.out.println("start(): " + m.start());
            System.out.println("end(): " + m.end());
            String subString = INPUT.substring(m.start(), m.end());
            if (subString.contains(expectedText)) {
                int idx = subString.indexOf("x=\"") + 3;
                String x = subString.substring(idx, subString.indexOf("\"", idx));
                int idy = subString.indexOf("y=\"") + 3;
                String y = subString.substring(idy, subString.indexOf("\"", idy));

                int idwidth = subString.indexOf("width=\"") + 7;
                String width = subString.substring(idwidth, subString.indexOf("\"", idwidth));
                int idheight = subString.indexOf("height=\"") + 8;
                String height = subString.substring(idheight, subString.indexOf("\"", idheight));
                notificationPoint = new Point(Integer.parseInt(x) + (Integer.parseInt(width) / 2), Integer.parseInt(y) + (Integer.parseInt(height) / 2));
                break;
                //       System.out.println(x+y+idwidth+idheight);
            }

        }
        return notificationPoint;
    }

    public boolean checkRGBValue(int[] actualColor, int[] expectedRGB) {

        Color actual = new Color(actualColor[0], actualColor[1], actualColor[2]);
        Color expected = new Color(expectedRGB[0], expectedRGB[1], expectedRGB[2]);
        double diff = ColourDistance(actual, expected);
        logger.detail("Difference between actual and expected is :" + diff);

        boolean isEqual = diff < 15;
/*      for(int i =0;i<actualColor.length;i++){
          if(Math.abs(actualColor[i]-expectedRGB[i])>7){
              //if flag is not set as false then set
              if(isEqual)
                  isEqual=false;
          }
      }*/
        return isEqual;
    }

    public String getActualTime() {

        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        String custRef = com.bungii.android.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String pickUpId = com.bungii.android.utilityfunctions.DbUtility.getPickupID(custRef);
        return DbUtility.getActualTime(pickUpId);
    }

    public String getEstimateDistance() {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //   phoneNumber="9403960188";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        return com.bungii.ios.utilityfunctions.DbUtility.getEstimateDistance(custRef);
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
        logger.detail("tripDistance" + tripDistance + ".tripTime" + tripTime + "Promo" + Promo + "tripType" + tripType);
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get minimum cost,Mile value,Minutes value of Geofence
        double minCost = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.minimum.cost")),
                perMileValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.miles")),
                perMinutesValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.minutes"));

        double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double tripActualTime = Double.parseDouble(tripTime);
        double tripValue = distance * perMileValue + tripActualTime * perMinutesValue;
        if (tripType.equalsIgnoreCase("DUO"))
            tripValue = tripValue * 2;
        Promo = Promo.contains("ADD") ? "0" : Promo;

        double discount = 0;
        if (Promo.contains("$"))
            discount = Double.parseDouble(Promo.replace("-$", ""));
        else if (Promo.contains("%"))
            discount = tripValue * Double.parseDouble(Promo.replace("-", "").replace("%", "")) / 100;

        double costToCustomer = tripValue - discount;
        costToCustomer = costToCustomer > minCost ? costToCustomer : minCost;

        return costToCustomer;
    }


    public double ColourDistance(Color c1, Color c2) {
        double rmean = (c1.getRed() + c2.getRed()) / 2;
        int r = c1.getRed() - c2.getRed();
        int g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        double weightR = 2 + rmean / 256;
        double weightG = 4.0;
        double weightB = 2 + (255 - rmean) / 256;
        return Math.sqrt(weightR * r * r + weightG * g * g + weightB * b * b);
    }

    public void addStatusBarLocationToFeatureContext(String appKey, String statusKey, Point point, Dimension dimension) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey + "_" + statusKey;
        cucumberContextManager.setFeatureContextContext(key + "_POINT", point);
        cucumberContextManager.setFeatureContextContext(key + "_DIM", dimension);
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

    public boolean isContextContainsStatusKey(String appKey, String statusKey) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey + "_" + statusKey;
        return cucumberContextManager.isFeatureContextContains(key + "_POINT");
    }

    public Point getInitialLocationFromContext(String appKey, String statusKey) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey + "_" + statusKey;
        return (Point) cucumberContextManager.getFeatureContextContext(key + "_POINT");
    }

    public Dimension getDimenstionFromContext(String appKey, String statusKey) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey + "_" + statusKey;
        return (Dimension) cucumberContextManager.getFeatureContextContext(key + "_DIM");
    }

    public boolean[] checkStatusOnDriver() {
        boolean[] array = new boolean[5];
        List<Map> data = new ArrayList<>();
        Map<String, Object> info = new HashedMap();

        try {
            File srcFile = ((TakesScreenshot) SetupManager.getObject().getDriver()).getScreenshotAs(OutputType.FILE);
            Point initialLocation;
            Dimension elementSize;

            if (!isContextContainsStatusKey("DRIVER", "1")) {
                WebElement pickUpStatus1 = driverUpdateStatusPage.Image_Trip_State_1();
                initialLocation = pickUpStatus1.getLocation();
                elementSize = pickUpStatus1.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "1", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "1");
                elementSize = getDimenstionFromContext("DRIVER", "1");
            }

            System.out.println("status of element 1");
            int[] statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[0] = checkRGBValue(statusPixelValue, rgb[0]);


            if (!isContextContainsStatusKey("DRIVER", "2")) {
                WebElement pickUpStatus2 = driverUpdateStatusPage.Image_Trip_State_2();
                initialLocation = pickUpStatus2.getLocation();
                elementSize = pickUpStatus2.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "2", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "2");
                elementSize = getDimenstionFromContext("DRIVER", "2");
            }

            System.out.println("status of element 2");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[1] = checkRGBValue(statusPixelValue, rgb[1]);


            if (!isContextContainsStatusKey("DRIVER", "3")) {
                WebElement pickUpStatus3 = driverUpdateStatusPage.Image_Trip_State_3();
                initialLocation = pickUpStatus3.getLocation();
                elementSize = pickUpStatus3.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "3", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "3");
                elementSize = getDimenstionFromContext("DRIVER", "3");
            }

            System.out.println("status of element 3");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[2] = checkRGBValue(statusPixelValue, rgb[2]);


            if (!isContextContainsStatusKey("DRIVER", "4")) {
                WebElement pickUpStatus4 = driverUpdateStatusPage.Image_Trip_State_4();
                initialLocation = pickUpStatus4.getLocation();
                elementSize = pickUpStatus4.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "4", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "4");
                elementSize = getDimenstionFromContext("DRIVER", "4");
            }
            System.out.println("status of element 4");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 6), initialLocation.getY() + (elementSize.height / 2));
            array[3] = checkRGBValue(statusPixelValue, rgb[3]);


            if (!isContextContainsStatusKey("DRIVER", "5")) {
                WebElement pickUpStatus5 = driverUpdateStatusPage.Image_Trip_State_5();
                initialLocation = pickUpStatus5.getLocation();
                elementSize = pickUpStatus5.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "5", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "5");
                elementSize = getDimenstionFromContext("DRIVER", "5");
            }

            System.out.println("status of element 5");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[4] = checkRGBValue(statusPixelValue, rgb[4]);
        } catch (IOException e) {
            logger.error("Error while capturing/coping screenshot" + e.getMessage());
        }
        return array;

    }

    public boolean[] checkStatusOnCustomer() {
        boolean[] array = new boolean[5];
        try {
            File srcFile = ((TakesScreenshot) SetupManager.getObject().getDriver()).getScreenshotAs(OutputType.FILE);
            Point initialLocation;
            Dimension elementSize;
            if (!isContextContainsStatusKey("DRIVER", "1")) {
                WebElement pickUpStatus1 = customerUpdateStatusPage.Image_Trip_State_1();
                initialLocation = pickUpStatus1.getLocation();
                elementSize = pickUpStatus1.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "1", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "1");
                elementSize = getDimenstionFromContext("DRIVER", "1");
            }
            System.out.println("status of element 1");
            int[] statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[0] = checkRGBValue(statusPixelValue, rgb[0]);


            if (!isContextContainsStatusKey("DRIVER", "2")) {
                WebElement pickUpStatus2 = customerUpdateStatusPage.Image_Trip_State_2();
                initialLocation = pickUpStatus2.getLocation();
                elementSize = pickUpStatus2.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "2", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "2");
                elementSize = getDimenstionFromContext("DRIVER", "2");
            }

            System.out.println("status of element 2");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[1] = checkRGBValue(statusPixelValue, rgb[1]);


            if (!isContextContainsStatusKey("DRIVER", "3")) {
                WebElement pickUpStatus3 = customerUpdateStatusPage.Image_Trip_State_3();
                initialLocation = pickUpStatus3.getLocation();
                elementSize = pickUpStatus3.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "3", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "3");
                elementSize = getDimenstionFromContext("DRIVER", "3");
            }
            System.out.println("status of element 3");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[2] = checkRGBValue(statusPixelValue, rgb[2]);


            if (!isContextContainsStatusKey("DRIVER", "4")) {
                WebElement pickUpStatus4 = customerUpdateStatusPage.Image_Trip_State_4();
                initialLocation = pickUpStatus4.getLocation();
                elementSize = pickUpStatus4.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "4", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "4");
                elementSize = getDimenstionFromContext("DRIVER", "4");
            }
            System.out.println("status of element 4");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 6), initialLocation.getY() + (elementSize.height / 2));
            array[3] = checkRGBValue(statusPixelValue, rgb[3]);


            if (!isContextContainsStatusKey("DRIVER", "5")) {
                WebElement pickUpStatus5 = customerUpdateStatusPage.Image_Trip_State_5();

                initialLocation = pickUpStatus5.getLocation();
                elementSize = pickUpStatus5.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "5", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "5");
                elementSize = getDimenstionFromContext("DRIVER", "5");
            }
            System.out.println("status of element 5");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[4] = checkRGBValue(statusPixelValue, rgb[4]);
        } catch (IOException e) {
            logger.error("Error while capturing/coping screenshot" + e.getMessage());
        }
        return array;

    }
}
