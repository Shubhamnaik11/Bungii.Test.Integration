package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiRequestPage extends PageBase {
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	public WebElement Button_Accept() { return findElement("ACCEPT", LocatorType.Name); }
	public WebElement Button_Reject() { return findElement("REJECT", LocatorType.Name); }
	public WebElement Text_TripDistance() { return findElement("//XCUIElementTypeStaticText[@name='Trip Distance']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_EstimatedEarning() { return findElement("//XCUIElementTypeStaticText[@name='Estimated Earnings']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_TimeToPickup() { return findElement("//XCUIElementTypeStaticText[@name='Time to Pickup']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }




}
