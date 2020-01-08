package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiDetailsPage extends PageBase {
	public WebElement Button_StartBungii() { return findElement("START BUNGII", LocatorType.Name); }
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	public WebElement Text_ContactDriverMessage(){return findElement("//android.widget.TextView[@text='You will have the ability to contact your drivers when the Bungii begins']", LocatorType.XPath);}


}
