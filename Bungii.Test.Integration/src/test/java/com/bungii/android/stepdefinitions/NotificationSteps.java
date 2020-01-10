package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.Then;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;
import static com.bungii.common.manager.ResultManager.error;

public class NotificationSteps extends DriverBase {
    NotificationPage notificationPage;
    private static LogUtility logger = new LogUtility(com.bungii.ios.stepdefinitions.other.NotificationSteps.class);
    ActionManager action = new ActionManager();
    public NotificationSteps(NotificationPage notificationPage){
        this.notificationPage=notificationPage;
    }
    GeneralUtility utility = new GeneralUtility();


    @Then("^I click on notification for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_click_on_notification_for_something_for_something(String appName, String expectedNotification) throws InterruptedException {
        //Thread.sleep(20000);
        Thread.sleep(10000);
        try{
            String currentApplication = (String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION");
            String appHeaderName=getAppHeader(appName);
            boolean notificationClickRetry=false;
            String bunddleId=getBundleId(currentApplication);



            cucumberContextManager.setFeatureContextContext("CURRENT_APPLICATION", appName.toUpperCase());
            ((AppiumDriver) SetupManager.getDriver()).terminateApp(bunddleId);
            action.showNotifications();

            log("Checking notifications","Checking notifications",true);

            //	logger.detail(SetupManager.getDriver().getPageSource());
            boolean notificationClick=clickNotification(appHeaderName,getExpectedNotification(expectedNotification));
            if(!notificationClick){
                Thread.sleep(80000);
                notificationClickRetry=clickNotification(appHeaderName,getExpectedNotification(expectedNotification));

            }
            if(!notificationClick &&!notificationClickRetry){
                fail("I should able to click notification for"+expectedNotification,"I was not clicked on notifications with text"+getExpectedNotification(expectedNotification),true);
                action.hideNotifications();
            }else{
                pass("I should able to click notification for"+expectedNotification,"I clicked on notifications with text"+getExpectedNotification(expectedNotification),true);

            }


            //temp fixed for iOS  device
            utility.handleIosUpdateMessage();
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
    }

    private String getExpectedNotification(String identifier){
        String text="";
        switch (identifier.toUpperCase()) {
            case "ON DEMAND TRIP":
                text=PropertyUtility.getMessage("driver.notification.ondemand");
                break;
            case "DRIVER CANCELLED":
                text=PropertyUtility.getMessage("customer.notification.driver.cancelled");
                break;
            case "DRIVER ENROUTE":
                text=PropertyUtility.getMessage("customer.notification.driver.accepted");
                break;
            case "SCHEDULED PICKUP ACCEPTED":
                text=PropertyUtility.getMessage("customer.notification.scheduled.driver.accepted");
                break;
            case "STACK TRIP":
                text=PropertyUtility.getMessage("driver.notification.stack");
                break;
            case "CUSTOMER CANCEL STACK TRIP":
                text = PropertyUtility.getMessage("driver.notification.stack.cancel");
                break;
            case "DRIVER ACCEPTED STACK BUNGII":
                text=PropertyUtility.getMessage("customer.notification.driver.accepted.stack");
                break;
            case "DRIVER STARTED STACK BUNGII":
                text=PropertyUtility.getMessage("customer.notification.driver.started.stack");
                break;
        }
        return text;
    }

    private String getAppHeader(String appName){
        String appHeaderName="";
        switch (appName.toUpperCase()) {
            case "DRIVER":
                appHeaderName="BUNGII DRIVER";
                break;
            case "CUSTOMER":
                appHeaderName="BUNGII";
                break;
            default:
                error("UnIm plemented Step or in correct app", "UnImplemented Step");
                break;
        }
        return appHeaderName;
    }

    private String getBundleId(String Identifier) {
        String bundleID = "";
        switch (Identifier.toUpperCase()) {
            case "DRIVER":
                bundleID = PropertyUtility.getProp("bundleId_Driver");
                break;
            case "CUSTOMER":
                bundleID = PropertyUtility.getProp("bundleId_Customer");
                break;
            default:
                bundleID = PropertyUtility.getProp("bundleId_Customer");
                break;

        }
        return bundleID;
    }


    /**
     * Click notification
     * @param application Application name of which notification is to clicked
     * @param Message Notification text
     * @return Did we click notification text or not
     */
    public boolean clickNotification(String application, String Message) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException, InterruptedException {
        boolean clicked = false;
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        String androidVersion = driver.getCapabilities().getCapability("platformVersion").toString();
        List<WebElement> elements = new ArrayList<WebElement>();
        if(androidVersion.contains("10")) {
            for (int i=0;i<=3;i++) {
                String xml=driver.getPageSource();
                Point p= utility.getCordinatesForNotification(xml,Message);
                if(p!=null) {
                    action.click(p);
                    clicked = true;
                    break;
                }
                Thread.sleep(10000);
            }
        }
        else
            elements = notificationPage.Cell_Notification();

        for (WebElement notifcation : elements) {
            String[] info = notifcation.getAttribute("label").split(",", 3);
            System.err.println(info[0] + " ||  " + info[2]);
            if (application.equalsIgnoreCase(info[0].trim()) && Message.equals(info[2].trim())) {
                action.swipeRight(notifcation);
                clicked = true;
                break;
            }
        }
        return clicked;

    }



}
