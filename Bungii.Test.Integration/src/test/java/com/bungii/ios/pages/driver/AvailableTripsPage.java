package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class AvailableTripsPage extends PageBase {
	public WebElement NavigationBar_Status() { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }
	public WebElement Image_SelectBungii() { return findElement("//XCUIElementTypeImage[@name='disclosure-arrow-right']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath); }

	

}
