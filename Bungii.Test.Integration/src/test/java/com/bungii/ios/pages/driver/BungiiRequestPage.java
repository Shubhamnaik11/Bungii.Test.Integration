package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiRequestPage extends PageBase {
	public WebElement Text_NavigationBar(boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath,ignoreException); }
	public WebElement Text_NavigationBar_Alt() { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }

	public WebElement Button_Accept() { return findElement("ACCEPT", LocatorType.Name); }
	public WebElement Button_Reject() { return findElement("REJECT", LocatorType.Name); }

	public WebElement Text_DistanceTag() { return findElement("TO PICKUP", PageBase.LocatorType.Name); }
	public WebElement 	Text_ValueDistance() { return findElement("//XCUIElementTypeStaticText[@name='Estimated Duration: ']/preceding-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement 	Text_ValueTripTime() { return findElement("//XCUIElementTypeStaticText[@name='Estimated Duration: ']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

	public WebElement Text_EstimatedEarningTag() { return findElement("EARNINGS", LocatorType.Name); }
	public WebElement Text_EstimatedEarningValue() { return findElement("//XCUIElementTypeStaticText[@name='EARNINGS']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }
	public WebElement Text_TimeToPickup() { return findElement("//XCUIElementTypeStaticText[@name='Time to Pickup']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }


	public WebElement TextBox_Pickup_LineOne() {return findElement("//*[@name='input_icon_pickup']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[1]", LocatorType.XPath); }
	public WebElement TextBox_Pickup_LineTwo(boolean ...ignoreException) {return findElement("//*[@name='input_icon_pickup']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[2]", LocatorType.XPath,ignoreException); }
	public WebElement TextBox_Drop_LineOne() {return findElement("//*[@name='input_icon_dropoff']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[1]", LocatorType.XPath); }
	public WebElement TextBox_Drop_LineTwo() {return findElement("//*[@name='input_icon_dropoff']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[2]", LocatorType.XPath); }


}
