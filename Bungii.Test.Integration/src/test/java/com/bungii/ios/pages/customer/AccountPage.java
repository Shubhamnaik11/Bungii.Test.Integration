package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class AccountPage extends PageBase{
    //public By Text_NavigationBar =MobileBy.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeOther");
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Text_Name() { return findElement("//XCUIElementTypeStaticText[@name='Phone:']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Phone() { return findElement("//XCUIElementTypeStaticText[@name='Phone:']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
}
