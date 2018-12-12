package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TripDetailsPage extends PageBase {
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	public WebElement Button_Accept() { return findElement("ACCEPT", LocatorType.Name); }
	public WebElement Text_Distance() { return findElement("//XCUIElementTypeStaticText[@name='Trip Distance']/following::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_EstimatedEarnings() { return findElement("//XCUIElementTypeStaticText[@name='Estimated Earnings']/following::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_ScheduledDate() { return findElement("//XCUIElementTypeStaticText[@name='Trip Scheduled Date']/following::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_ScheduledTime () { return findElement("//XCUIElementTypeStaticText[@name='Trip Scheduled Time']/following::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }


}
