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
	public WebElement Button_Cancel() { return findElement("Cancel delivery", LocatorType.Name); }
	public WebElement Button_ScheduledBungiis() { return findElement("Scheduled Bungiis", LocatorType.Name); }
	public WebElement Button_TakePhoto() { return findElement("Take photo", LocatorType.Name); }
	public WebElement Button_Call() { return findElement("//XCUIElementTypeButton[@name='Call']", LocatorType.XPath); }
	public WebElement Button_Sms() { return findElement("//XCUIElementTypeButton[@name='Text']", LocatorType.XPath); }
	public WebElement Button_CallDriver() { return findElement("Call driver", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_SmsDriver() { return findElement("Text driver", PageBase.LocatorType.AccessibilityId); }
	public WebElement Button_SupportSms() { return findElement("//XCUIElementTypeImage[@name='slide_to_start_button']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[3]", LocatorType.XPath); }
	public WebElement Button_ViewItems() { return findElement("//XCUIElementTypeImage[@name='slide_to_start_button']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[2]", LocatorType.XPath); }
//	public WebElement Button_MoreOptions() { return findElement("more options", PageBase.LocatorType.Name); }
	public WebElement Button_MoreOptions() { return findElement("//XCUIElementTypeImage[@name='slide_to_start_button']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[4]", LocatorType.XPath); }
	public WebElement Tab_CancelDelivery() { return findElement("//XCUIElementTypeButton[@name=\"Cancel delivery\"]", LocatorType.XPath); }
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
	public WebElement Tab_CustomerSignature(){return findElement("//XCUIElementTypeSheet[@name=\"What do you want to do?\"]/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]",LocatorType.XPath);}
	public WebElement Text_CustomerNameOnDriverApp(){return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[2]",LocatorType.XPath);}
	public WebElement TextBox_SignedByField(){return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]",LocatorType.XPath);}
	public WebElement Button_Submit(){return findElement("Submit",LocatorType.AccessibilityId);}
	public WebElement TextBox_Signature(){return findElement("//XCUIElementTypeApplication[@name=\"Bungii Driver QAAuto\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]",LocatorType.XPath);}
	public WebElement Header_CustomerSignature(boolean...IgnoreException){return findElement("Customer signature",LocatorType.AccessibilityId,IgnoreException);}
	public WebElement Button_ClearSignature(){return findElement("Clear signature",LocatorType.AccessibilityId);}
	public WebElement Button_SkipCustomerSignature(){return findElement("Skip customer signature",LocatorType.AccessibilityId);}
	public WebElement Alert_DropOffInstructionsGotIt(){return findElement("Got it",LocatorType.AccessibilityId);}
	public WebElement Label_DeliverySuccessMessageLive()  { return findElement("//p[@id='delivery-completed-success-message']/i[2]", LocatorType.XPath); }
	public WebElement Label_CancelSuccessMessageLive () { return findElement("//p[@id='delivery-cancelled-success-message']/i[2]", LocatorType.XPath); }
	public WebElement Message_AdminCompleteConfirm() {return findElement("//div[@id='editEndTime']//following::div[2]/p[1]",LocatorType.XPath);}
	public WebElement Button_CalculateCost() { return findElement("//button[@id='btnCost']",LocatorType.XPath);}
	public WebElement Textbox_PickupEndDate () { return findElement("pickupEndDate", LocatorType.Id); }
	public WebElement Textbox_PickupEndTime () { return findElement("pickupEndTime", LocatorType.Id); }
	public WebElement Dropdown_ddlpickupEndTime () { return findElement("ddlpickupEndTime", LocatorType.Id); }
	public WebElement RadioButton_EditDeliveryStatus() { return findElement("//span[contains(text(),'Edit Delivery Status')]",LocatorType.XPath);}
	public WebElement Header_DeliveryDetails(){return findElement("DELIVERY DETAILS",LocatorType.AccessibilityId);}
	public WebElement Button_CloseDeliveryDetails() { return findElement("//XCUIElementTypeNavigationBar[@name=\"Bungii_Driver.NewPickupDetail\"]/XCUIElementTypeButton[1]",LocatorType.XPath);}
	public WebElement Icon_DeliveryInstructions(){return findElement("//XCUIElementTypeImage[@name='slide_to_start_button']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther[1]",LocatorType.XPath);}
	public WebElement Icon_Call(){return findElement("icon-text-large",LocatorType.AccessibilityId);}
	public WebElement Icon_Text(){return findElement("icon-call-large",LocatorType.AccessibilityId);}
	public WebElement Icon_Pickup(){return findElement("//XCUIElementTypeImage[@name=\"icon-stop-pickup\"]",LocatorType.XPath);}
	public WebElement Icon_DropOff(){return findElement("//XCUIElementTypeImage[@name=\"icon-stop-drop-off\"]",LocatorType.XPath);}
	public WebElement Text_ContactDuo(boolean...IgnoreException){return findElement("//XCUIElementTypeStaticText[@name=\"Contact DUO\"]",LocatorType.XPath,IgnoreException);}
	public WebElement Text_TeamMate(boolean... IgnoreException){return findElement("//XCUIElementTypeStaticText[@name=\"Teammate\"]",LocatorType.XPath,IgnoreException);}
	public WebElement Label_ArivalTimeAtPickup(){return findElement("Arrival time at Pickup",LocatorType.AccessibilityId);}
	public WebElement Label_ExpectedTimeAtDropOff(){return findElement("Expected time at drop-off",LocatorType.AccessibilityId);}
	public WebElement ArrivalTimeAtPickupValue(){return findElement("//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]",LocatorType.XPath);}
	public WebElement ArrivalTimeAtPickupValueForStacked(){return findElement("//XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[2]",LocatorType.XPath);}
	public WebElement Text_ExpectedTimeAtDropOffForStacked(){return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeStaticText[2]",LocatorType.XPath);}
	public WebElement Image_Barcode(){return findElement("icon-scanner",LocatorType.AccessibilityId);}
	public WebElement Text_BarcodeInstructions(){return findElement("//XCUIElementTypeStaticText[@name=\"Scan the item(s) barcode before loading & after unloading.\"]",LocatorType.XPath);}
	public WebElement Button_ScanItemBarCode(){return findElement("Scan item barcode",LocatorType.AccessibilityId);}
	public WebElement Header_BarcodeScanner(){return findElement( "//XCUIElementTypeOther[@name=\"BARCODE SCANNER\"]",LocatorType.XPath);}
	public WebElement Text_ScanBarCode(){return findElement("Scan barcode",LocatorType.AccessibilityId);}
	public WebElement Text_BarCodeScanningInstructions(){return findElement( "//XCUIElementTypeStaticText[@name=\"Hold steady and center the barcode to scan. You need to scan any one item to proceed.\"]",LocatorType.XPath);}
	public WebElement Button_SkipBarCode(){return findElement( "Skip",LocatorType.AccessibilityId);}
	public WebElement Text_NotificationTextOnLoadingItemsForBarCode(){return findElement("//XCUIElementTypeStaticText[@name=\"Please take photos and scan item(s) barcode before loading, just ‘slide to load items’ and follow the prompts.\"]",LocatorType.XPath);}
	public WebElement Text_NotificationTextOnUnloadingForBarCode(){return findElement("//XCUIElementTypeStaticText[@name=\"Please take photos and scan item(s) barcode before loading, just ‘slide to load items’ and follow the prompts.\"]",LocatorType.XPath);}

	public WebElement Text_ExpectedTimeAtDropOff(){return findElement("//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeStaticText[2]",LocatorType.XPath);}
	public WebElement Label_Pickup(){return findElement("PICKUP",LocatorType.AccessibilityId);}
	public WebElement Label_ArrivalTime(){return findElement("(Arrival time)",LocatorType.AccessibilityId);}
	public WebElement Label_DropOff(){return findElement("DROP-OFF",LocatorType.AccessibilityId);}
	public WebElement Label_ExpectedTime(){return findElement("(Expected time)",LocatorType.AccessibilityId);}
	public WebElement Text_ArrivalTimeValue(){return findElement("//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[3]",LocatorType.XPath);}
	public WebElement Text_ArrivalTimeForDifferentStates(){return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeStaticText[3]",LocatorType.XPath);}
	public WebElement Text_DropOffRangeFromDeliveryDetails(){return findElement("//XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeStaticText[1]",LocatorType.XPath);}
	public WebElement Text_DropOffRangeFromDeliveryDetailsForChanges(){return findElement("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[5]/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeStaticText[1]",LocatorType.XPath);}

	public WebElement Button_ScheduledBungiiFromMoreOptions(){return findElement("Scheduled Bungiis",LocatorType.AccessibilityId);}
}
