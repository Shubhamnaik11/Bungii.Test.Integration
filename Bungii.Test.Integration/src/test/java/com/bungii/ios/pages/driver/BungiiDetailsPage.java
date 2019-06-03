package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiDetailsPage extends PageBase {
	public WebElement Button_StartBungii() { return findElement("START BUNGII", LocatorType.Name); }
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }



}
