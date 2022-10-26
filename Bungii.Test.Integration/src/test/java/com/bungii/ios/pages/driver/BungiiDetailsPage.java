package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BungiiDetailsPage extends PageBase {
	public WebElement Button_StartBungii(boolean... ignoreException) { return findElement("Start Bungii", LocatorType.Name,ignoreException); }
	public WebElement Button_CancelBungii() { return findElement("//XCUIElementTypeStaticText[@name=\"CANCEL BUNGII\"]/preceding-sibling::XCUIElementTypeButton[not(contains(@name, 'START'))]", LocatorType.XPath); }
	public WebElement Button_General_Instruction_Got_It() { return findElement("//XCUIElementTypeButton[@label='Got it']",LocatorType.XPath);}
	public WebElement Text_General_Instruction(boolean... ignoreException) { return findElement("//XCUIElementTypeStaticText[@name='General instructions']",LocatorType.XPath,ignoreException);}
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	//Photo Verification by driver
	public WebElement Text_PickupInstructions(){return findElement("//XCUIElementTypeStaticText[@name='Pickup instructions']",LocatorType.XPath);}
	public WebElement Text_PhotoVerification(){return findElement("//XCUIElementTypeNavigationBar[@name='PHOTO VERIFICATION']",LocatorType.XPath);}
	public WebElement Tab_AddPhoto(boolean...ignoreException){return findElement("//XCUIElementTypeStaticText[@name='Tap to add photo']",LocatorType.XPath,ignoreException);}
	public WebElement Button_SavePhotos(){return findElement("//XCUIElementTypeButton[@name='Save']",LocatorType.XPath);}
	public WebElement Text_DropOffInstructions(){return findElement("//XCUIElementTypeStaticText[@name='Drop-off instructions']",LocatorType.XPath);}
	//Photo tab on admin portal
	public List<WebElement> List_Photos(){return  findElements("//h5/following-sibling::div",LocatorType.XPath);}

	public WebElement Text_ContactDriverMessage(){return findElement("//android.widget.TextView[@text='You will have the ability to contact your drivers when the Bungii begins']", LocatorType.XPath);}
	public WebElement TextBox_Pickup_LineOne() {return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[6]", LocatorType.XPath); }
	public WebElement TextBox_Pickup_LineTwo(boolean ...ignoreException) {return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[7]", LocatorType.XPath,ignoreException); }
	public WebElement TextBox_Drop_LineOne() {return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[8]", LocatorType.XPath); }
	public WebElement TextBox_Drop_LineTwo() {return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[9]", LocatorType.XPath); }


	public WebElement Text_DistanceTag() { return findElement("Estimated Duration: ", PageBase.LocatorType.Name); }
	public WebElement 	Text_ValueDistance() { return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[10]", PageBase.LocatorType.XPath); }
	public WebElement 	Text_ValueTripTime() { return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[12]", PageBase.LocatorType.XPath); }

	public WebElement Text_EstimatedEarningTag() { return findElement("EARNINGS", LocatorType.Name); }

	public WebElement Text_EstimatedEarningValue() { return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[5]", LocatorType.XPath); }
	public WebElement Text_BungiiTime() { return findElement("//XCUIElementTypeOther[@name=\"WHEN\"]/XCUIElementTypeStaticText[4]", PageBase.LocatorType.XPath); }

	public WebElement Text_TypeTag() { return findElement("//XCUIElementTypeStaticText[@name=\"Type\"]", PageBase.LocatorType.XPath); }
	public WebElement Text_TypeValue() { return findElement("//XCUIElementTypeStaticText[@name=\"Type\"]/preceding-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }


}
