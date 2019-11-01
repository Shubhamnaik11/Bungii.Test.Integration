package com.bungii.ios.stepdefinitions.other;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;


public class NotificationSteps extends DriverBase {
	NotificationPage notificationPage;
	private static LogUtility logger = new LogUtility(NotificationSteps.class);
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

		String bunddleId=getBundleId(currentApplication);



			cucumberContextManager.setFeatureContextContext("CURRENT_APPLICATION", appName.toUpperCase());
			((AppiumDriver)SetupManager.getDriver()).terminateApp(bunddleId);
			action.showNotifications();
		//	logger.detail(SetupManager.getDriver().getPageSource());
		boolean notificationClick=clickNotification(appHeaderName,getExpectedNotification(expectedNotification));
		if(!notificationClick){
			Thread.sleep(80000);
			boolean notificationClickRetry=clickNotification(appHeaderName,getExpectedNotification(expectedNotification));

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
		case "STACK TRIP":
			text=PropertyUtility.getMessage("driver.notification.stack");
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

    @Then("^Notification for \"([^\"]*)\" for \"([^\"]*)\" should be displayed$")
    public void notification_for_something_for_something_should_be_displayed(String actor, String actionToPerfrom)  {
		try{
		String bunddleId=getBundleId((String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION"));
		((AppiumDriver)SetupManager.getDriver()).terminateApp(bunddleId);
		action.showNotifications();
		
    	String expectedMessage=getExpectedNotification(actionToPerfrom);
    	boolean isDisplayed=checkNotification(getAppHeader(actor),expectedMessage );

    	testStepVerify.isTrue(isDisplayed, actor+" should be notified for "+expectedMessage, actor+" was notified for "+expectedMessage, "Not able to get notification with text for '"+expectedMessage +"' for"+actor );
		action.hideNotifications();
    	action.switchApplication(bunddleId);
			// fixed for iOS device where update is prompted
			utility.handleIosUpdateMessage();
	} catch (Exception e) {
		logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
		error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);

	}
    	}
    
	@When("^I clear all notification$")
	public void i_clear_all_notification() {
		String bunddleId=getBundleId((String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION"));

		try {
			((AppiumDriver)SetupManager.getDriver()).terminateApp(bunddleId);
			action.showNotifications();

			
			boolean cleared = clearAllNotifcation();
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
	 * Get all notification list,
	 * @return List of all notifications
	 */
	public List<String> getNotifcationList() {
		List<String> notificationList = new ArrayList<String>();
		List<WebElement> elements = notificationPage.Cell_Notification();
		for (WebElement notifcation : elements) {
			notificationList.add(notifcation.getAttribute("label"));
		}
		return notificationList;
	}

	/**
	 * Click notification
	 * @param application Application name of which notification is to clicked
	 * @param Message Notification text
	 * @return Did we click notification text or not
	 */
	public boolean clickNotification(String application, String Message) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException, InterruptedException {
		boolean clicked = false;
		IOSDriver<MobileElement> driver = (IOSDriver<MobileElement>) SetupManager.getDriver();

		String iosVersion = driver.getCapabilities().getCapability("platformVersion").toString();
		List<WebElement> elements = new ArrayList<WebElement>();
		if(iosVersion.contains("10")) {
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
	/**
	 * Check if given notification is displayed
	 * @param application Application name of which notification is to checked
	 * @param Message Notification text
	 * @return Did we find notification text or not
	 */
	public boolean checkNotification(String application, String Message) {
		List<String> notificationList = getNotifcationList();
		boolean isFound = false;

		for (String notification : notificationList) {
			String[] info = notification.split(",", 3);

			if (application.equalsIgnoreCase(info[0].trim()) && Message.equals(info[2].trim())) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}

	public boolean clearAllNotifcation() {
		boolean cleared = false;
		//click on clear button on notification page
		List<WebElement> clearButtons = notificationPage.Button_NotificationClear();
		for (WebElement clearButton : clearButtons) {
			action.click(clearButton);
			action.click(notificationPage.Button_NotificationClearConfirm(true));
			cleared = true;
		}

		List<WebElement> elements = notificationPage.Cell_Notification();

		for (WebElement notifcation : elements) {
			String notificationText = notifcation.getAttribute("label");
			logger.detail("Cleared notification :" + notificationText);

			action.swipeLeft(notifcation);
			if (notifcation.isDisplayed())
				action.swipeLeft(notifcation);
			cleared = true;

		}
		//minimum 3 notification are shown on all iOS screen , If earlier notification had  more than 3 notification then only search for notification agiain . This will save time .
		if(elements.size()>3) {
			while (notificationPage.Cell_Notification().size() > 0)
				clearAllNotifcation();
		}
		return cleared;

	}
}
