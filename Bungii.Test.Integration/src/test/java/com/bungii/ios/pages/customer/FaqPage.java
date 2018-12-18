package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class FaqPage extends PageBase {

	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

//	public WebElement Text_NavigationBar() {return FindElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", LocatorType.XPath); }
	public WebElement Button_Twitter() {return findElement("//XCUIElementTypeOther[@name='navigation']/XCUIElementTypeLink[1]", LocatorType.XPath); }
	public WebElement Button_Instagram() {return findElement("//XCUIElementTypeOther[@name='navigation']/XCUIElementTypeLink[2]", LocatorType.XPath); }
	public WebElement Button_Facebook () {return findElement("//XCUIElementTypeOther[@name='navigation']/XCUIElementTypeLink[3]", LocatorType.XPath); }
	public WebElement Text_FirstAnswer() {return findElement("//*[contains(@name,'an app similar to other popular ridesharing apps')]", LocatorType.XPath); }

	public WebElement Text_FirstQuestion () {return findElement("So what exactly is Bungii?", LocatorType.Name); }
	public WebElement Image_Questions() {return findElement("smartmockups0.png", LocatorType.Name); }
	public WebElement Text_Faq () {return findElement("APP FAQ", LocatorType.Name); }
	public WebElement Text_SocialMediaHeader() {return findElement("WE'RE SOCIAL", LocatorType.Name); }
}
