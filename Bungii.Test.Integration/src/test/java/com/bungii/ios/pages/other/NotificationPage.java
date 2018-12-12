package com.bungii.ios.pages.other;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NotificationPage extends PageBase{
	public List<WebElement> Cell_Notification() { return findElements("NotificationCell",LocatorType.XPath.Name);};



}
