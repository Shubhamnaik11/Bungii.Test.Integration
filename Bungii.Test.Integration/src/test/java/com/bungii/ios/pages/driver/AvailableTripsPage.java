package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AvailableTripsPage extends PageBase {
	public WebElement NavigationBar_Status() { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }
	public WebElement Image_SelectBungii() { return findElement("//XCUIElementTypeImage[@name='disclosure-arrow-right']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
	public List<WebElement> Image_SelectBungiis() { return findElements("//XCUIElementTypeImage[@name='disclosure-arrow-right']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
	public WebElement Partner_Name() { return findElement("//XCUIElementTypeOther/XCUIElementTypeStaticText", LocatorType.XPath);}
	public WebElement Text_FromHomeMiles() { return findElement("//XCUIElementTypeCell/XCUIElementTypeStaticText[2]", LocatorType.XPath);}
	public WebElement Text_PartnerName(String text) { return findElement("//XCUIElementTypeStaticText[@name=\""+text+"\"]",LocatorType.XPath);}

	public WebElement Button_Back() { return findElement("//XCUIElementTypeNavigationBar[@name=\"BUNGII DETAILS\"]/XCUIElementTypeButton", LocatorType.XPath);}
	public WebElement Text_RejectionPopup(boolean...ignoreException) { return findElement("//XCUIElementTypeStaticText[@name=\"What's your reason for rejecting?\"]", LocatorType.XPath,ignoreException);}
	public WebElement Text_RejectionReasons(String name) { return findElement("//XCUIElementTypeButton[@name=\""+name+"\"]", LocatorType.XPath);}
	public WebElement Button_Cancel() { return findElement("//XCUIElementTypeButton[@name=\"Cancel\"]", LocatorType.XPath);}
	public WebElement Button_Submit() { return findElement("//XCUIElementTypeButton[@name=\"Submit\"]", LocatorType.XPath);}

	public List<WebElement> List_AllAvailableDeliveriesDriverApp() { return findElements("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell", LocatorType.XPath);}

	public List<WebElement> List_AllAvailableDeliveriesCustomerApp() { return findElements("//XCUIElementTypeApplication[@name=\"Bungii QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell", LocatorType.XPath);}

	public WebElement Text_DeliveryTime(int number) { return findElement(String.format("//XCUIElementTypeApplication[@name=\"Bungii QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[%d]/XCUIElementTypeStaticText",number), LocatorType.XPath);}

	public WebElement Text_CustomerName(int number) { return findElement(String.format("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[%d]/XCUIElementTypeStaticText",number), LocatorType.XPath);}

}
