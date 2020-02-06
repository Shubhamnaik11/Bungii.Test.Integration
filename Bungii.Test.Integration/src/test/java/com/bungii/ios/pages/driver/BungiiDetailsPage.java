package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiDetailsPage extends PageBase {
	public WebElement Button_StartBungii() { return findElement("START BUNGII", LocatorType.Name); }
	public WebElement Button_CancelBungii() { return findElement("//XCUIElementTypeStaticText[@name=\"CANCEL BUNGII\"]/preceding-sibling::XCUIElementTypeButton[not(contains(@name, 'START'))]", LocatorType.XPath); }

	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	public WebElement Text_ContactDriverMessage(){return findElement("//android.widget.TextView[@text='You will have the ability to contact your drivers when the Bungii begins']", LocatorType.XPath);}
	public WebElement TextBox_Pickup_LineOne() {return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[5]", LocatorType.XPath); }
	public WebElement TextBox_Pickup_LineTwo(boolean ...ignoreException) {return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[6]", LocatorType.XPath,ignoreException); }
	public WebElement TextBox_Drop_LineOne() {return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[7]", LocatorType.XPath); }
	public WebElement TextBox_Drop_LineTwo() {return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[8]", LocatorType.XPath); }


	public WebElement Text_DistanceTag() { return findElement("Estimated Duration: ", PageBase.LocatorType.Name); }
	public WebElement 	Text_ValueDistance() { return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[9]", PageBase.LocatorType.XPath); }
	public WebElement 	Text_ValueTripTime() { return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[11]", PageBase.LocatorType.XPath); }

	public WebElement Text_EstimatedEarningTag() { return findElement("EARNINGS", LocatorType.Name); }
	public WebElement Text_EstimatedEarningValue() { return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[4]", LocatorType.XPath); }
	public WebElement Text_BungiiTime() { return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[3]", PageBase.LocatorType.XPath); }
	public WebElement Text_TypeTag() { return findElement("//XCUIElementTypeStaticText[@name=\"Type\"]", PageBase.LocatorType.XPath); }
	public WebElement Text_TypeValue() { return findElement("//XCUIElementTypeStaticText[@name=\"Type\"]/preceding-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }




}
