package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiDetails extends PageBase {


	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Text_Driver1Name() { return findElement("//XCUIElementTypeCell[2]/XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Driver2Name() { return findElement("//XCUIElementTypeCell[3]/XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Button_CancelBungii() {return findElement("//XCUIElementTypeStaticText[@name='CANCEL BUNGII']/preceding-sibling::XCUIElementTypeButton", LocatorType.XPath); }
	public WebElement Text_PickUpLocationLine1() {return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(4); }
	public WebElement Text_PickUpLocationLine2() {return findElements("XCUIElementTypeStaticText", PageBase.LocatorType.ClassName).get(5); }

	public WebElement Text_DropLocationLine1() {return findElements("XCUIElementTypeStaticText", PageBase.LocatorType.ClassName).get(6); }
	public WebElement Text_DropLocationLine2() {return findElements("XCUIElementTypeStaticText", PageBase.LocatorType.ClassName).get(7); }

	public WebElement Text_Time() {return findElement("**/XCUIElementTypeStaticText[3]", LocatorType.ClassChain); }
	public WebElement Text_TotalEstimate() {return findElement("**/XCUIElementTypeStaticText[4]", LocatorType.ClassChain); }
	public WebElement Text_Driver1Status(boolean ...ignoreException) {return findElement("//XCUIElementTypeStaticText[@name='Driver #1']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath,ignoreException); }
	public WebElement Text_Driver2Status() {return findElement("//XCUIElementTypeStaticText[@name='Driver #2']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

	public WebElement Text_Time_iOS11_2() {return findElement("**/XCUIElementTypeStaticText[3]", LocatorType.ClassChain); }
	public WebElement Text_TotalEstimate_iOS11_2() {return findElement("**/XCUIElementTypeStaticText[4]", LocatorType.ClassChain); }
	public WebElement Text_Driver1Status_iOS11_2() {return findElement("//XCUIElementTypeStaticText[@name='Driver #1']/preceding-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Driver1Status_iOS11_Tag() {return findElement("Driver #1", LocatorType.Name); }
	public WebElement Text_Driver2Status_iOS11_2() {return findElement("//XCUIElementTypeStaticText[@name='Driver #2']/preceding-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Driver2Status_iOS11_Tag() {return findElement("Driver #2", LocatorType.Name); }


	public WebElement Row_TimeSelect() {return findElement("//XCUIElementTypeStaticText[@name='Time']/parent::XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	public WebElement Button_Back() {return findElement("Back", LocatorType.Name); }



}
