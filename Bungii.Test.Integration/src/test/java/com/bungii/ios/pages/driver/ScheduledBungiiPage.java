package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ScheduledBungiiPage extends PageBase{
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Image_SelectBungii () { return findElement("//XCUIElementTypeImage[@name='disclosure-arrow-right']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath); }


}
