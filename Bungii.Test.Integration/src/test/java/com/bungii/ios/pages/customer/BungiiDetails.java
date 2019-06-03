package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiDetails extends PageBase {


	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Button_CancelBungii() {return findElement("//XCUIElementTypeStaticText[@name='CANCEL BUNGII']/preceding-sibling::XCUIElementTypeButton", LocatorType.XPath); }
	public WebElement Text_PickUpLocation() {return findElement("//XCUIElementTypeStaticText[@name='PICKUP LOCATION']/following-sibling::XCUIElementTypeTextField", PageBase.LocatorType.XPath); }
	public WebElement Text_DropLocation() {return findElement("//XCUIElementTypeStaticText[@name='DROP OFF LOCATION']/following-sibling::XCUIElementTypeTextField", PageBase.LocatorType.XPath); }
	public WebElement Text_Time() {return findElement("//XCUIElementTypeStaticText[@name='Time']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath); }
	public WebElement Text_TotalEstimate() {return findElement("//XCUIElementTypeStaticText[@name='Total estimate']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Driver1Status() {return findElement("//XCUIElementTypeStaticText[@name='Driver #1']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Driver2Status() {return findElement("//XCUIElementTypeStaticText[@name='Driver #2']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Row_TimeSelect() {return findElement("//XCUIElementTypeStaticText[@name='Time']/parent::XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Button_Back() {return findElement("Back", LocatorType.Name); }



}
