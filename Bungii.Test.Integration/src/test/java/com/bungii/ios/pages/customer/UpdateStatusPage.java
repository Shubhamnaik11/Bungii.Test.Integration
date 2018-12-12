package com.bungii.ios.pages.customer;

//import org.apache.commons.codec.binary.Base64;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UpdateStatusPage extends PageBase {
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Button_Call() {return findElement("btn call", LocatorType.Name); }
	public WebElement  Button_Sms () {return findElement("//XCUIElementTypeButton[@name='btn call']/preceding-sibling::XCUIElementTypeButton", LocatorType.XPath); }
	public WebElement  Text_DriverName() {return findElement("//XCUIElementTypeButton[@name='btn call']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }
	public List<WebElement>  Text_Info() {return findElements("//XCUIElementTypeImage[@name='pickup_state_1']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }

}
