package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BungiiCompletePage extends PageBase {



	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Button_Back() {return findElement("Back", PageBase.LocatorType.Name); }
	public List<WebElement> Button_Star() {return findElements("rating unselected star icon", PageBase.LocatorType.Name); }
	public WebElement Button_Plus() {return findElement("+", PageBase.LocatorType.Name); }
	public WebElement Button_Ok() {return findElement("OK", PageBase.LocatorType.Name); }
	public WebElement Button_Close() {return findElement("close button icon", PageBase.LocatorType.Name); }
	public WebElement Button_Minus() {return findElement("-", PageBase.LocatorType.Name); }
	public WebElement Text_TipValue() {return findElement("//XCUIElementTypeButton[@name='-']/following::XCUIElementTypeTextField", PageBase.LocatorType.XPath); }




}
