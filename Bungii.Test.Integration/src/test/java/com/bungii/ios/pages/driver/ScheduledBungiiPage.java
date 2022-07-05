package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ScheduledBungiiPage extends PageBase{
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Image_SelectBungii () { return findElement("//XCUIElementTypeImage[@name='disclosure-arrow-right']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
	public WebElement Text_Trip_DateTime(){return findElement( "//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[1]",LocatorType.XPath);}


}
