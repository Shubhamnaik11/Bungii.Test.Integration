package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.pages.otherApps.OtherAppsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.common.manager.ResultManager.*;
import static com.bungii.common.manager.ResultManager.error;

public class NotificationSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(com.bungii.ios.stepdefinitions.other.NotificationSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    OtherAppsPage otherAppsPage=new OtherAppsPage();


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

            switch (expectedNotification)
            {
                case "SCHEDULED PICKUP AVAILABLE":
                    break;
            }
            log("Checking notifications","Checking notifications",true);

            //	logger.detail(SetupManager.getDriver().getPageSource());
           /* boolean notificationClick=clickNotification(appHeaderName,getExpectedNotification(expectedNotification));
            if(!notificationClick){
                Thread.sleep(80000);
                notificationClickRetry=clickNotification(appHeaderName,getExpectedNotification(expectedNotification));

            }
            if(!notificationClick &&!notificationClickRetry){
                fail("I should able to click notification for"+expectedNotification,"I was not clicked on notifications with text"+getExpectedNotification(expectedNotification),true);
                action.hideNotifications();
            }else{
                pass("I should able to click notification for"+expectedNotification,"I clicked on notifications with text"+getExpectedNotification(expectedNotification),true);

            }*/
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

    @When("^I clear all notification$")
    public void i_clear_all_notification() {
        String bunddleId=getBundleId((String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION"));

        try {
            boolean cleared=false;
            ((AppiumDriver)SetupManager.getDriver()).terminateApp(bunddleId);
            action.showNotifications();
            boolean isPresent=action.isElementPresent(otherAppsPage.Button_NotificationClear());
            if(isPresent==true) {
                cleared = clearAllNotifcation();
            }
            else {
                action.hideNotifications();
            }

        if (cleared)
                log( "I should able cleared all notification", "I cleared all notification");
            else
                log( "I should able cleared all notification",
                        "Not notification found on device");

            action.hideNotifications();
            ((AppiumDriver)SetupManager.getDriver()).activateApp(bunddleId);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
    }

    public boolean clearAllNotifcation() throws InterruptedException {
        boolean cleared = false;
        //click on clear button on notification page
            action.click(otherAppsPage.Button_NotificationClear());
            cleared = true;

            if(cleared==false) {
                List<WebElement> elements = otherAppsPage.Cell_Notification();

                for (WebElement notifcation : elements) {
                    action.swipeLeft(notifcation);
                    if (notifcation.isDisplayed())
                        action.swipeLeft(notifcation);
                    cleared = true;
                }

                //minimum 3 notification are shown on all iOS screen , If earlier notification had  more than 3 notification then only search for notification agiain . This will save time .
                if (elements.size() > 3) {
                    while (otherAppsPage.Cell_Notification().size() > 0)
                        clearAllNotifcation();
                }
            }

        return cleared;

    }
}
