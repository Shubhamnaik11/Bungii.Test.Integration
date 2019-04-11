package com.bungii.ios.pages.other;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NotificationPage extends PageBase{
	public List<WebElement> Cell_Notification() { return findElements("NotificationCell",LocatorType.Name);};
	public List<WebElement> Cell_Notitication_ios10(){return findElements("//XCUIElementTypeCell",LocatorType.XPath);}
	public List<WebElement> Cell_Notitication_ios10_2(){return findElements("//*[contains(@label,'BUNGII DRIVER,')]",LocatorType.XPath);}
	public WebElement Button_NotificationScreen(boolean... ignoreException) { return findElement("Press Home to open",LocatorType.Name,ignoreException);};


}
