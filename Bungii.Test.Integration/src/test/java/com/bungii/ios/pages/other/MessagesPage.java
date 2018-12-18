package com.bungii.ios.pages.other;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class MessagesPage extends PageBase {
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	public WebElement Button_Cancel() {
		return findElement("Cancel", LocatorType.Name);
	}
	public WebElement Text_ToField() {
		return findElement("To:", LocatorType.Name);
	}


	

}
