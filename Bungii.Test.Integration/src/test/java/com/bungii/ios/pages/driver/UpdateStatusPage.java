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
/*	public WebElement AreaSlide() { return findElement(
			"//XCUIElementTypeImage[@name='slide_to_start_button']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }*/





//	public List<WebElement> Text_Info() { return findElements("//XCUIElementTypeImage[@name='pickup_state_1']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

	public List<WebElement> Text_Info() { return findElements("**/XCUIElementTypeStaticText", LocatorType.ClassChain); }


/*	public WebElement Image_Trip_State_1() { return findElement("pickup_state_1", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_2() { return findElement("pickup_state_2", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_3() { return findElement("pickup_state_3", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_4() { return findElement("pickup_state_4", PageBase.LocatorType.Name); }
	public WebElement Image_Trip_State_5() { return findElement("pickup_state_5", PageBase.LocatorType.Name); }
	public WebElement Button_Slide() { return findElement("slide_to_start_button", PageBase.LocatorType.Name); }
	public WebElement Button_Cancel() { return findElement("Cancel", PageBase.LocatorType.Name); }
	public WebElement Button_Call() { return findElement("Call customer", PageBase.LocatorType.Name); }
	public WebElement Button_Sms() { return findElement("Text customer", PageBase.LocatorType.Name); }
	public WebElement Button_CallDriver() { return findElement("Call driver", PageBase.LocatorType.Name); }
	public WebElement Button_SmsDriver() { return findElement("Text driver", PageBase.LocatorType.Name); }
	public WebElement Button_SupportSms() { return findElement("Text Bungii support", PageBase.LocatorType.Name); }
	public WebElement Button_ViewItems() { return findElement("View items", PageBase.LocatorType.Name); }
	public WebElement Button_MoreOptions() { return findElement("more options", PageBase.LocatorType.Name); }

	public WebElement Text_NavigationBar(boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath,ignoreException); }


	public WebElement Button_CloseViewItems(boolean ...ignoreException) { return findElement("close btn white icon", PageBase.LocatorType.Name,ignoreException); }

	public WebElement Image_TripItem() { return findElement("//XCUIElementTypeButton[@name='close btn white icon']/following::XCUIElementTypeImage", PageBase.LocatorType.XPath); }
	public WebElement PageIndicator_Page1() { return findElement("//XCUIElementTypePageIndicator[@value='page 1 of 1']", PageBase.LocatorType.XPath); }

	public WebElement Button_DuoMoreOptions1() { return findElement("(//XCUIElementTypeImage[@name=\"more\"])[1]", PageBase.LocatorType.XPath); }
	public WebElement Button_DuoMoreOptions2() { return findElement("(//XCUIElementTypeImage[@name=\"more\"])[2]", PageBase.LocatorType.XPath); }*/

	public WebElement CallNumeberValue_iOS10(boolean ...ignoreException) { return findElement("XCUIElementTypeStaticText", LocatorType.ClassName,ignoreException); }
	public WebElement EndCall_iOS10(boolean ...ignoreException) { return findElement("End call", LocatorType.AccessibilityId,ignoreException); }
	public WebElement ButtonCancelCall_iOS10(boolean ...ignoreException) { return findElement("Cancel", LocatorType.AccessibilityId,ignoreException); }



	public WebElement Image_Trip_State_1() { return findElement("pickup_state_1", LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_2() { return findElement("pickup_state_2", PageBase.LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_3() { return findElement("pickup_state_3", PageBase.LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_4() { return findElement("pickup_state_4", PageBase.LocatorType.AccessibilityId); }
	public WebElement Image_Trip_State_5() { return findElement("pickup_state_5", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Slide() { return findElement("slide_to_start_button", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Cancel() { return findElement("Cancel", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Call() { return findElement("Call customer", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_Sms() { return findElement("Text customer", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_CallDriver() { return findElement("Call driver", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_SmsDriver() { return findElement("Text driver", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_SupportSms() { return findElement("Text Bungii support", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_ViewItems() { return findElement("View items", PageBase.LocatorType.AccessibilityId); }
//	public WebElement Button_MoreOptions() { return findElement("more options", PageBase.LocatorType.Name); }
	public WebElement Button_MoreOptions() { return findElement("more options", LocatorType.AccessibilityId); }
	public WebElement Text_NavigationBar(boolean ...ignoreException) { return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName,ignoreException); }

	public WebElement Button_CloseViewItems(boolean ...ignoreException) { return findElement("close btn white icon", PageBase.LocatorType.AccessibilityId,ignoreException); }
	public WebElement AreaSlide() { return findElement(
			"type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'SLIDE '", LocatorType.Predicate); }
	public WebElement Image_TripItem() { return findElement("//XCUIElementTypeButton[@name='close btn white icon']/following::XCUIElementTypeImage", PageBase.LocatorType.XPath); }
	public WebElement PageIndicator_Page1() { return findElement("type == 'XCUIElementTypePageIndicator' AND value == 'page 1 of 1'", LocatorType.Predicate); }
	//public WebElement Button_DuoMoreOptions1() { return findElement("(//XCUIElementTypeImage[@name=\"more\"])[1]", PageBase.LocatorType.XPath); }
	//public WebElement Button_DuoMoreOptions2() { return findElement("(//XCUIElementTypeImage[@name=\"more\"])[2]", PageBase.LocatorType.XPath); }	public WebElement Button_DuoMoreOptions1() { return findElement("(//XCUIElementTypeImage[@name=\"more\"])[1]", PageBase.LocatorType.XPath); }
	//public WebElement Button_DuoMoreOptions2() { return findElements("more", PageBase.LocatorType.AccessibilityId).get(1); }
	//public WebElement Button_DuoMoreOptions1() { return findElements("more", PageBase.LocatorType.AccessibilityId).get(0); }
	public WebElement Button_DuoMoreOptions2() { return findElement("**/XCUIElementTypeOther/XCUIElementTypeButton[`name != \"Return to Bungii\"`][`name != \"Return to Bungii Driver\"`][2]", LocatorType.ClassChain); }
	public WebElement Button_DuoMoreOptions1() { return findElement("**/XCUIElementTypeOther/XCUIElementTypeButton[`name != \"Return to Bungii\"`][`name != \"Return to Bungii Driver\"`][1]", LocatorType.ClassChain); }

}
