package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AvailableTripsPage extends PageBase {
	public WebElement NavigationBar_Status() { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }
	public WebElement Image_SelectBungii() { return findElement("//XCUIElementTypeImage[@name='disclosure-arrow-right']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
	public List<WebElement> Image_SelectBungiis() { return findElements("//XCUIElementTypeImage[@name='disclosure-arrow-right']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
	public WebElement Partner_Name() { return findElement("//XCUIElementTypeOther/XCUIElementTypeStaticText", LocatorType.XPath);}
	//public WebElement Page_Bungii_Details() { return findElement("//XCUIElementTypeApplication[@name=Bungii Driver QAAuto]/XCUIElementTypeWindow[1]/XCUIElementTypeOther", LocatorType.XPath);}



}
