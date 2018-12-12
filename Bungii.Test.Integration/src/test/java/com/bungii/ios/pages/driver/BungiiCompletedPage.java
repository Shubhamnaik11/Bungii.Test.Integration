package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiCompletedPage extends PageBase {
	public WebElement Button_NextTrip() {return findElement("On To The Next One", PageBase.LocatorType.Name); }

	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }


/*	*//**
	 * Check if active page is Bungii completed page
	 * 
	 * @return return comparison result navigation header to expected msg from
	 *         property
	 *//*
	public boolean isBungiiCompletedPage() {
		textToBePresentInElementName(Text_NavigationBar,PropertyUtility.getMessage("driver.navigation.bungiicompleted"));
		return getNameAttribute(Text_NavigationBar).equals(PropertyUtility.getMessage("driver.navigation.bungiicompleted"));

	}
	
	*//**
	 * Click on Next trip button
	 *//*
	public void clickNextTrip(){
		click(Button_NextTrip);
	}*/
}
