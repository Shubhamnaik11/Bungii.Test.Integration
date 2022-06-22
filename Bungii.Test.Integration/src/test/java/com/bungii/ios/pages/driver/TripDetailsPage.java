package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TripDetailsPage extends PageBase {
//	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	public WebElement Button_Accept() { return findElement("ACCEPT", LocatorType.AccessibilityId); }
	public WebElement Text_Distance() { return findElement("//XCUIElementTypeStaticText[contains(@name,'miles')]", PageBase.LocatorType.XPath); }
	public WebElement Text_EstimatedEarnings() { return findElement("//XCUIElementTypeStaticText[@name='EARNINGS']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_ScheduledDateTime() { return findElement("//XCUIElementTypeStaticText[@name='WHEN']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
//	public WebElement Text_ScheduledTime () { return findElement("//XCUIElementTypeStaticText[@name='Trip Scheduled Time']/following::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }


	public WebElement Text_DriverVehicleModel() { return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[3]", LocatorType.XPath); }

	public WebElement Text_DriverVehicleLicenseNumber() { return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText", LocatorType.XPath); }


}
