package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TermsAndConditionPage  extends PageBase {
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Button_CheckOff() {return findElement("check box off", LocatorType.Name); }
	public WebElement Button_Continue () {return findElement("CONTINUE", LocatorType.Name); }


}
