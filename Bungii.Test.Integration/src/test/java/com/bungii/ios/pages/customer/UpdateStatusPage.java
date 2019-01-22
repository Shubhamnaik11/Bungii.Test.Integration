package com.bungii.ios.pages.customer;

//import org.apache.commons.codec.binary.Base64;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UpdateStatusPage extends PageBase {
	//public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }

	public WebElement Button_Call() {return findElement("btn call", LocatorType.Name); }
	public WebElement  Button_Sms () {return findElement("//XCUIElementTypeButton[@name='btn call']/preceding-sibling::XCUIElementTypeButton", LocatorType.XPath); }
	public WebElement  Text_DriverName() {return findElement("//XCUIElementTypeButton[@name='btn call']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }
	public List<WebElement>  Text_Info() {return findElements("//XCUIElementTypeImage[@name='pickup_state_1']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }
	public WebElement Image_Trip_State_1() { return findElement("pickup_state_1", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_2() { return findElement("pickup_state_2", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_3() { return findElement("pickup_state_3", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_4() { return findElement("pickup_state_4", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_5() { return findElement("pickup_state_5", PageBase.LocatorType.Name); }
}
