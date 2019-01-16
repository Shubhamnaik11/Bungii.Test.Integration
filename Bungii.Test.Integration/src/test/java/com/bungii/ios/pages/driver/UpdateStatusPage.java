package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

//import org.apache.commons.codec.binary.Base64;

public class UpdateStatusPage extends PageBase {
	public WebElement Text_Slide() { return findElement(
			"//XCUIElementTypeImage[@name='slide_to_start_button']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Button_Sms() { return findElement("//XCUIElementTypeButton[@name='btn call']/preceding-sibling::XCUIElementTypeButton", PageBase.LocatorType.XPath); }
	public WebElement Text_CustomerName() { return findElement("//XCUIElementTypeButton[@name='btn call']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

	public WebElement Text_Customer() { return findElement("//XCUIElementTypeStaticText[@name='CUSTOMER']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

	public WebElement Text_InfoForDriver() { return findElement(
			"//XCUIElementTypeImage[@name='pickup_state_1']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement AreaSlide() { return findElement(
			"//XCUIElementTypeImage[@name='slide_to_start_button']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

	public WebElement S1_P1() { return findElement(
			"//XCUIElementTypeApplication[@name='Bungii']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[1]", PageBase.LocatorType.XPath); }
	public WebElement S2_P1() { return findElement(
			"//XCUIElementTypeApplication[@name='Bungii']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]", PageBase.LocatorType.XPath); }


	public List<WebElement> Text_Info() { return findElements("//XCUIElementTypeImage[@name='pickup_state_1']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }




	public WebElement Image_Trip_State_1() { return findElement("pickup_state_1", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_2() { return findElement("pickup_state_2", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_3() { return findElement("pickup_state_3", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_4() { return findElement("pickup_state_4", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_5() { return findElement("pickup_state_5", PageBase.LocatorType.Name); }

	public WebElement Button_Slide() { return findElement("slide_to_start_button", PageBase.LocatorType.Name); }
	public WebElement Button_Cancel() { return findElement("Cancel", PageBase.LocatorType.Name); }
	public WebElement Button_Call() { return findElement("btn call", PageBase.LocatorType.Name); }


	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }


	

}
