package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

//import org.apache.commons.codec.binary.Base64;

public class UpdateStatusPage extends PageBase {
	public WebElement Text_Slide() { return findElement(
			"//XCUIElementTypeImage[@name='slide_to_start_button']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	//public WebElement Button_Sms() { return findElement("//XCUIElementTypeButton[@name='btn call']/preceding-sibling::XCUIElementTypeButton", PageBase.LocatorType.XPath); }
	public WebElement Text_CustomerName() { return findElement("//XCUIElementTypeButton[@name='btn call']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

//	public WebElement Text_Customer() { return findElement("//XCUIElementTypeStaticText[@name='CUSTOMER']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_Customer() { return findElement("**/XCUIElementTypeStaticText[-1]", LocatorType.ClassChain); }

	public WebElement Text_InfoForDriver() { return findElement(
			"//XCUIElementTypeImage[@name='pickup_state_1']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

	public List<WebElement> Text_Info() { return findElements("//XCUIElementTypeStaticText[@name]", LocatorType.XPath); }

	public WebElement CallNumeberValue_iOS10(boolean ...ignoreException) { return findElement("XCUIElementTypeStaticText", LocatorType.ClassName,ignoreException); }
	public WebElement EndCall_iOS10(boolean ...ignoreException) { return findElement("End call", LocatorType.AccessibilityId,ignoreException); }
	public WebElement ButtonCancelCall_iOS10(boolean ...ignoreException) { return findElement("Cancel", LocatorType.AccessibilityId,ignoreException); }
	public WebElement Text_Header(String screen) {return findElement("//XCUIElementTypeStaticText[@name='"+screen+"']",LocatorType.XPath);}


	public WebElement Image_Trip_State_1() { return findElement("pickup_state_1", LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_2() { return findElement("pickup_state_2", PageBase.LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_3() { return findElement("pickup_state_3", PageBase.LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_4() { return findElement("pickup_state_4", PageBase.LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_5() { return findElement("pickup_state_5", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Slide() { return findElement("slide_to_start_button", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Cancel() { return findElement("Cancel", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Call() { return findElement("//XCUIElementTypeButton[@name='Call']", LocatorType.XPath); }
	public WebElement Button_Sms() { return findElement("//XCUIElementTypeButton[@name='Text']", LocatorType.XPath); }
	public WebElement Button_CallDriver() { return findElement("Call driver", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_SmsDriver() { return findElement("Text driver", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_SupportSms() { return findElement("//XCUIElementTypeImage[@name='slide_to_start_button']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[3]", LocatorType.XPath); }
	public WebElement Button_ViewItems() { return findElement("//XCUIElementTypeImage[@name='slide_to_start_button']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[2]", LocatorType.XPath); }
//	public WebElement Button_MoreOptions() { return findElement("more options", PageBase.LocatorType.Name); }
	public WebElement Button_MoreOptions() { return findElement("//XCUIElementTypeImage[@name='slide_to_start_button']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[4]", LocatorType.XPath); }
	public WebElement Text_NavigationBarScreen(String screen) { return findElement("//XCUIElementTypeStaticText[@name='"+screen+"']", LocatorType.XPath); }
	public WebElement Text_NavigationBar(boolean ...ignoreException) { return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName,ignoreException); }

	public WebElement Button_CloseViewItems(boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar[@name='ITEM DETAILS']/XCUIElementTypeButton", LocatorType.XPath); }
	public WebElement AreaSlide() { return findElement(
			"type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'SLIDE '", LocatorType.Predicate); }
	public WebElement Image_TripItem() { return findElement("//XCUIElementTypeButton[@name='close btn white icon']/following::XCUIElementTypeImage", PageBase.LocatorType.XPath); }
	public WebElement PageIndicator_Page1() { return findElement("//XCUIElementTypeStaticText[@name='Photos']", LocatorType.XPath); }
	public WebElement Header_Item_Details() { return findElement("//XCUIElementTypeNavigationBar[@name='ITEM DETAILS']",LocatorType.XPath);}
	public WebElement Button_DuoMoreOptions1() { return findElements("//XCUIElementTypeImage[@name='more']", LocatorType.XPath).get(0); }
	public WebElement Button_DuoMoreOptions2() { return findElements("//XCUIElementTypeImage[@name='more']", LocatorType.XPath).get(1); }


	public WebElement Activity_loader(boolean ...ignoreException) { return findElement("In progress", LocatorType.Name,ignoreException); }
	public WebElement Text_WaitingForDriver(){return findElement("//XCUIElementTypeStaticText[@name=\"Waiting for the other driver to end Bungii.\"]",LocatorType.XPath);}

	//STACK
	public WebElement Text_NextLabel(boolean ...ignoreException) { return findElement("NEXT CUSTOMER", LocatorType.Name,ignoreException); }
	//public WebElement Text_OnDeckLabel(boolean ...ignoreException) { return findElement("ON DECK", LocatorType.Name,ignoreException); }
	public WebElement Text_OnDeckLabel(boolean ...ignoreException) { return findElement("//XCUIElementTypeStaticText[contains(@name,'Bungii on deck, try to finish up by')]", LocatorType.XPath); }
	public WebElement Text_StackCustomer(boolean ...ignoreException) { return findElement("//XCUIElementTypeStaticText[@name='NEXT CUSTOMER']/following-sibling::XCUIElementTypeStaticText[1]", LocatorType.XPath,ignoreException); }
	public WebElement Text_StackInfo(){return findElement("//XCUIElementTypeStaticText[contains(@name,'try to finish up by')]",LocatorType.XPath);}
	public WebElement Button_Info(){return findElement("//XCUIElementTypeStaticText[contains(@label,'Try to finish by')]/following-sibling::XCUIElementTypeButton",LocatorType.XPath);}
	public WebElement Text_ETAValue(){return findElement("//XCUIElementTypeStaticText[contains(@name,\"ETA:\")]",LocatorType.XPath);}

}
