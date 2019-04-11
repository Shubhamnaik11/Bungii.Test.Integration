package com.bungii.ios.utilityfunctions;

import com.bungii.SetupManager;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.HomePage;
import com.bungii.ios.pages.driver.LoginPage;
import com.bungii.ios.pages.driver.UpdateStatusPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneralUtility extends DriverBase {
    NotificationPage notificationPage = new NotificationPage();
    static final double MIN_COST = 39;
    private static LogUtility logger = new LogUtility(GeneralUtility.class);
    HomePage driverHomePage = new HomePage();
    ActionManager action = new ActionManager();
    LoginPage driverLoginPage = new LoginPage();
    UpdateStatusPage driverUpdateStatusPage = new UpdateStatusPage();
    com.bungii.ios.pages.customer.UpdateStatusPage customerUpdateStatusPage = new com.bungii.ios.pages.customer.UpdateStatusPage();
    int[][] rgb = {
            {238, 29, 55},
            {255, 169, 66},
            {169, 204, 50},
            {37, 171, 226},
            {50, 51, 255},

    };

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
        System.out.println("Red Color value = " + red);
        System.out.println("Green Color value = " + green);
        System.out.println("Blue Color value = " + blue);

        int[] rgbValue = {red, green, blue};
        return rgbValue;
    }

    public String GetAdminUrl() {
        String adminURL = null;
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("DEV"))
            adminURL = PropertyUtility.getDataProperties("dev.admin.url");
        if (environment.equalsIgnoreCase("QA"))
            adminURL = PropertyUtility.getDataProperties("qa.admin.url");
        if (environment.equalsIgnoreCase("STAGE"))
            adminURL = PropertyUtility.getDataProperties("stage.admin.url");
        return adminURL;
    }
    public void recoverScenario() {
        //Remove notification screen
        if(action.isElementPresent(notificationPage.Button_NotificationScreen(true))){
            action.hideNotifications();
        }
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
        double tripTime = Double.parseDouble(estTime);

        double estimate = distance + loadUnloadTime + tripTime;
        estimate = estimate > MIN_COST ? estimate : MIN_COST;

        return estimate;
    }

    /**
     *
     * Reset application defined in app capabilite
     */
    public boolean installCustomerApp(){
        boolean isInstalled=false;
        try {

        String customerIPAFile=FileUtility.getSuiteResource("",PropertyUtility.getDataProperties("ipa.file.location").replace("{ENVT}",PropertyUtility.environment));
        if(!Files.exists(Paths.get(customerIPAFile)))
        {
            customerIPAFile=PropertyUtility.getDataProperties("ipa.file.location").replace("{ENVT}",PropertyUtility.environment);
        }

            logger.detail("IPA file Location "+customerIPAFile);
        if(Files.exists(Paths.get(customerIPAFile))) {
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).closeApp();
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).removeApp(PropertyUtility.getProp("bundleId_Customer"));
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).installApp(customerIPAFile);
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).launchApp();
            isInstalled=true;
        }else{
            isInstalled=false;
        }
        }catch (Exception e){

        }
    return isInstalled;


    }

    public boolean verifyPageHeader(String key) {
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
                    driverHomePage.visibilityOf(driverHomePage.Text_AvailableTrips());
                    isCorrectPage = action.getNameAttribute(driverHomePage.Text_NavigationBar()).equals("ONLINE") || action.getNameAttribute(driverHomePage.Text_NavigationBar()).equals("OFFLINE");
                    break;
                } else {
                }
            default:
                String expectedMessage = getExpectedHeader(key.toUpperCase(), currentApplication);
                action.textToBePresentInElementName(driverHomePage.Text_NavigationBar(), expectedMessage);
                isCorrectPage = action.getNameAttribute(driverHomePage.Text_NavigationBar()).equals(expectedMessage);

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
            case"ALLOW NOTIFICATIONS":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.allow.notifications");
                break;
            case"ALLOW LOCATION":
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

    public void loginToDriverApp(String phone, String password) throws InterruptedException {
        if (action.isElementPresent(driverLoginPage.TextField_PhoneNumber(true))) {
            WebElement element = driverLoginPage.TextField_PhoneNumber();
            action.sendKeys(driverLoginPage.TextField_PhoneNumber(), phone);
            action.sendKeys(driverLoginPage.Textfield_Password(), password);
            action.click(driverLoginPage.Button_Login());
        } else {
            //Not on Login page
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

        boolean isEqual = diff < 30;
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
        //   phoneNumber="9403960188";
        String custRef = com.bungii.android.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String pickUpId= com.bungii.android.utilityfunctions.DbUtility.getPickupID(custRef);
        String actualTime = DbUtility.getActualTime(pickUpId);
        return actualTime;
    }

    public String getEstimateDistance() {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //   phoneNumber="9403960188";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String distance= com.bungii.ios.utilityfunctions.DbUtility.getEstimateDistance(custRef);
        return distance;
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
            if (!isContextContainsStatusKey("CUSTOMER", "1")) {
                WebElement pickUpStatus1 = customerUpdateStatusPage.Image_Trip_State_1();
                initialLocation = pickUpStatus1.getLocation();
                elementSize = pickUpStatus1.getSize();
                addStatusBarLocationToFeatureContext("CUSTOMER", "1", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("CUSTOMER", "1");
                elementSize = getDimenstionFromContext("CUSTOMER", "1");
            }
            System.out.println("status of element 1");
            int[] statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[0] = checkRGBValue(statusPixelValue, rgb[0]);


            if (!isContextContainsStatusKey("CUSTOMER", "2")) {
                WebElement pickUpStatus2 = customerUpdateStatusPage.Image_Trip_State_2();
                initialLocation = pickUpStatus2.getLocation();
                elementSize = pickUpStatus2.getSize();
                addStatusBarLocationToFeatureContext("CUSTOMER", "2", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("CUSTOMER", "2");
                elementSize = getDimenstionFromContext("CUSTOMER", "2");
            }

            System.out.println("status of element 2");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[1] = checkRGBValue(statusPixelValue, rgb[1]);


            if (!isContextContainsStatusKey("CUSTOMER", "3")) {
                WebElement pickUpStatus3 = customerUpdateStatusPage.Image_Trip_State_3();
                initialLocation = pickUpStatus3.getLocation();
                elementSize = pickUpStatus3.getSize();
                addStatusBarLocationToFeatureContext("CUSTOMER", "3", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("CUSTOMER", "3");
                elementSize = getDimenstionFromContext("CUSTOMER", "3");
            }
            System.out.println("status of element 3");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[2] = checkRGBValue(statusPixelValue, rgb[2]);



            if (!isContextContainsStatusKey("CUSTOMER", "4")) {
                WebElement pickUpStatus4 = customerUpdateStatusPage.Image_Trip_State_4();
                initialLocation = pickUpStatus4.getLocation();
                elementSize = pickUpStatus4.getSize();
                addStatusBarLocationToFeatureContext("CUSTOMER", "4", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("CUSTOMER", "4");
                elementSize = getDimenstionFromContext("CUSTOMER", "4");
            }
            System.out.println("status of element 4");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 6), initialLocation.getY() + (elementSize.height / 2));
            array[3] = checkRGBValue(statusPixelValue, rgb[3]);



            if (!isContextContainsStatusKey("CUSTOMER", "5")) {
                WebElement pickUpStatus5 = customerUpdateStatusPage.Image_Trip_State_5();

                initialLocation = pickUpStatus5.getLocation();
                elementSize = pickUpStatus5.getSize();
                addStatusBarLocationToFeatureContext("CUSTOMER", "5", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("CUSTOMER", "5");
                elementSize = getDimenstionFromContext("CUSTOMER", "5");
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
