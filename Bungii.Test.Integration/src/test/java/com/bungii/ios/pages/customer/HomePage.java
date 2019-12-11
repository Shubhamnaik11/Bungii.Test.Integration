package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends PageBase {
//    public WebElement Text_NavigationBar() {return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }


/*    public WebElement TextBox_Pickup() {return findElement("//XCUIElementTypeStaticText[@name='PICKUP LOCATION']/following-sibling::XCUIElementTypeTextField", PageBase.LocatorType.XPath); }
    public WebElement Link_PickUpSuggestion() {return findElement("//XCUIElementTypeStaticText[@name='PICKUP LOCATION']/following::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
    public WebElement TextBox_Drop() {return findElement("//XCUIElementTypeStaticText[@name=\"DROP OFF LOCATION\"]/following-sibling::XCUIElementTypeTextField", PageBase.LocatorType.XPath); }
    public WebElement Link_DropSuggestion() {return findElement("//XCUIElementTypeStaticText[@name='DROP OFF LOCATION']/following::XCUIElementTypeCell", PageBase.LocatorType.XPath); }*/
  //  public WebElement Button_ClearPickup(boolean ...ignoreException) {return findElement("//XCUIElementTypeStaticText[@name='PICKUP LOCATION']/following-sibling::XCUIElementTypeTextField/XCUIElementTypeButton", PageBase.LocatorType.XPath,ignoreException); }
// public WebElement Button_ClearDrop() {return findElement("//XCUIElementTypeStaticText[@name=\"DROP OFF LOCATION\"]/following-sibling::XCUIElementTypeTextField/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
    //Sprint 29
    public WebElement Button_ClearPickup(boolean ...ignoreException) {return findElement("input icon cancel", LocatorType.AccessibilityId,ignoreException); }
    public WebElement Button_ClearDrop() {return findElement("(//XCUIElementTypeButton[@name=\"input icon cancel\"])[2]", PageBase.LocatorType.XPath); }


    public WebElement Text_EtaDropHeader() {return findElement("//XCUIElementTypeButton[@name='SET']/preceding-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }
  //  public WebElement Text_EtaPickupHeader() {return findElement("//XCUIElementTypeButton[@name='SET']/preceding-sibling::XCUIElementTypeStaticText[2]", PageBase.LocatorType.XPath); }
    public WebElement Text_EtaPickupHeader() {return findElement("//XCUIElementTypeButton[@name='SET']/preceding-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

    public WebElement Text_EtaTime() {return findElement("//XCUIElementTypeButton[@name='SET']/preceding-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }

    public WebElement Scroll_SoloToDuo() {return findElement("//XCUIElementTypeStaticText[@name='1']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther", PageBase.LocatorType.XPath); }

  //  public WebElement Button_AppMenu() {return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
 //   public WebElement AppMenu_Home() {return findElement("//XCUIElementTypeStaticText[@name='HOME']", PageBase.LocatorType.XPath); }
//    public WebElement AppMenu_Account() {return findElement("ACCOUNT", PageBase.LocatorType.AccessibilityId); }
    // public WebElement AppMenu_Payment() {return findElement("//XCUIElementTypeStaticText[@name='PAYMENT']", PageBase.LocatorType.XPath); }
 //   public WebElement AppMenu_Support() {return findElement("//XCUIElementTypeStaticText[@name='SUPPORT']", PageBase.LocatorType.XPath); }
 //   public WebElement AppMenu_Promos() {return findElement("//XCUIElementTypeStaticText[@name='PROMOS']", PageBase.LocatorType.XPath); }
 //   public WebElement AppMenu_DriveWithBungii() {return findElement("//XCUIElementTypeStaticText[@name='SIGN UP TO DRIVE']", PageBase.LocatorType.XPath); }
 //   public WebElement AppMenu_ScheduledTrip() {return findElement("//XCUIElementTypeStaticText[@name='SCHEDULED BUNGIIS']", PageBase.LocatorType.XPath); }
 //   public WebElement AppMenu_LogOut() {return findElement("LOGOUT", PageBase.LocatorType.Name); }
 //   public WebElement Text_NavigationBar() {return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }
//    public WebElement Indicator_Loading(boolean ... ignoreError) {return findElement("In progress", PageBase.LocatorType.Name,ignoreError); }
//    public WebElement Text_HeaderPickup() {return findElement("PICKUP LOCATION", PageBase.LocatorType.Name); }
//    public WebElement Text_HeaderDrop() {return findElement("DROP OFF LOCATION", PageBase.LocatorType.Name); }
//    public WebElement Button_SoloActive() {return findElement("bungii solo active", PageBase.LocatorType.Name); }
//    public WebElement Button_SoloDeActive() {return findElement("bungii solo deactive", PageBase.LocatorType.Name); }
//    public WebElement Button_DuoActive() {return findElement("bungii duo active", PageBase.LocatorType.Name); }
//    public WebElement Button_DuoDeActive() {return findElement("bungii duo deactive", PageBase.LocatorType.Name); }
//    public WebElement Image_Loading(boolean ...ignoreException) {return findElement("In progress", PageBase.LocatorType.Name,ignoreException); }
//    public WebElement Image_eta_bar() {return findElement("eta_bar_bg", PageBase.LocatorType.Name); }
//    public WebElement Button_Invite() {return findElement("menu icon invite referrals", PageBase.LocatorType.Name); }
//    public WebElement AppMenu_FAQ() {return findElement("//XCUIElementTypeStaticText[@name='FAQ']", PageBase.LocatorType.XPath); }
 //   public WebElement AppMenu_Account() {return findElement("//XCUIElementTypeStaticText[@name='ACCOUNT']", PageBase.LocatorType.XPath); }
//    public WebElement Button_GetEstimate() {return findElement("//XCUIElementTypeButton[@name='GET ESTIMATE']", PageBase.LocatorType.XPath); }
//    public WebElement Button_GetEstimate() {return findElement("//XCUIElementTypeButton[@name='GET ESTIMATE']", PageBase.LocatorType.XPath); }
//    public WebElement BUTTON_SET() {return findElement("//XCUIElementTypeButton[@name='SET']", PageBase.LocatorType.XPath); }
//  public WebElement Link_PickUpSuggestion() {return findElement("//XCUIElementTypeStaticText[@name='PICKUP LOCATION']/following::XCUIElementTypeCell", PageBase.LocatorType.XPath); }

    public WebElement Button_AppMenu () { return findElement("**/XCUIElementTypeNavigationBar/XCUIElementTypeButton", LocatorType.ClassChain); }
    public WebElement TextBox_Pickup(boolean ...ignoreException) {return findElement("**/XCUIElementTypeTextField[1]", LocatorType.ClassChain,ignoreException); }
    public WebElement TextBox_Pickup_LineOne() {return findElement("//*[@name='input_icon_pickup']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[1]", LocatorType.XPath); }
    public WebElement TextBox_Pickup_LineTwo(boolean ...ignoreException) {return findElement("//*[@name='input_icon_pickup']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[2]", LocatorType.XPath,ignoreException); }

    public WebElement Link_PickUpSuggestion() {return findElement("**/XCUIElementTypeTable[2]/XCUIElementTypeCell", LocatorType.ClassChain); }
    public WebElement TextBox_Drop(boolean ...ignoreException) {return findElement("//*[@value='Set Drop Off Location']", LocatorType.XPath,ignoreException); }
   // public WebElement TextBox_Drop() {return findElement("**/XCUIElementTypeTextField[2]", PageBase.LocatorType.ClassChain); }

    public WebElement Link_DropSuggestion() {return findElement("**/XCUIElementTypeTable[2]/XCUIElementTypeCell", PageBase.LocatorType.ClassChain); }
    public WebElement TextBox_Drop_LineOne() {return findElement("//*[@name='input_icon_dropoff']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[1]", LocatorType.XPath); }
    public WebElement TextBox_Drop_LineTwo() {return findElement("//*[@name='input_icon_dropoff']/parent:: XCUIElementTypeOther/following-sibling:: XCUIElementTypeOther/XCUIElementTypeStaticText[2]", LocatorType.XPath); }
    public List<WebElement> TextBox_AddressGeneric() {return findElements("**/XCUIElementTypeOther/XCUIElementTypeStaticText[`visible == 1`]", LocatorType.ClassChain); }


    public WebElement Button_GetEstimate(boolean ...ignoreException) {return findElement("type == 'XCUIElementTypeButton' AND name == 'GET ESTIMATE'", PageBase.LocatorType.Predicate,ignoreException); }
    public WebElement BUTTON_SET() {return findElement("SET", PageBase.LocatorType.AccessibilityId); }
    public WebElement AppMenu_FAQ() {return findElement("type == 'XCUIElementTypeStaticText' AND name == 'FAQ'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_Account() {return findElement("type == 'XCUIElementTypeStaticText' AND name == 'ACCOUNT'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_Support() {return findElement("type == 'XCUIElementTypeStaticText' AND name == 'SUPPORT'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_Promos() {return findElement("type == 'XCUIElementTypeStaticText' AND name == 'PROMOS'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_DriveWithBungii() {return findElement("type == 'XCUIElementTypeStaticText' AND name == 'SIGN UP TO DRIVE'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_Payment() {return findElement("type == 'XCUIElementTypeStaticText' AND name == 'PAYMENT'", LocatorType.Predicate); }
    //public WebElement AppMenu_ScheduledTrip() {return findElement("type == 'XCUIElementTypeStaticText' AND name == 'SCHEDULED BUNGIIS'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_MyBungiisTrip() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'MY BUNGIIS'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_LogOut() {return findElement("LOGOUT", LocatorType.AccessibilityId); }
    public WebElement Text_NavigationBar() {return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName); }
    public WebElement Indicator_Loading(boolean ... ignoreError) {return findElement("In progress", PageBase.LocatorType.AccessibilityId,ignoreError); }
    public WebElement Text_HeaderPickup() {return findElement("PICKUP LOCATION", PageBase.LocatorType.AccessibilityId); }
    public WebElement Text_HeaderDrop() {return findElement("DROP OFF LOCATION", PageBase.LocatorType.AccessibilityId); }
    public WebElement Button_SoloActive() {return findElement("bungii solo active", PageBase.LocatorType.AccessibilityId); }
    public WebElement Button_SoloDeActive() {return findElement("bungii solo deactive", PageBase.LocatorType.AccessibilityId); }
    public WebElement Button_DuoActive() {return findElement("bungii duo active", PageBase.LocatorType.AccessibilityId); }
    public WebElement Button_DuoDeActive() {return findElement("bungii duo deactive", PageBase.LocatorType.AccessibilityId); }
    public WebElement Image_Loading(boolean ...ignoreException) {return findElement("In progress", PageBase.LocatorType.AccessibilityId,ignoreException); }
    public WebElement Image_eta_bar() {return findElement("eta_bar_bg", PageBase.LocatorType.AccessibilityId); }
    public WebElement Button_Invite() {return findElement("menu icon invite referrals", PageBase.LocatorType.AccessibilityId); }
    public WebElement AppMenu_Home() {return findElement("HOME", LocatorType.AccessibilityId); }
    public WebElement Application_Name(boolean ...ignoreException) {return findElement("XCUIElementTypeApplication", LocatorType.ClassName,ignoreException); }

    public WebElement Image_PickupIcon() {return findElement("input_icon_pickup", LocatorType.AccessibilityId); }
    public WebElement Image_DropOffIcon() {return findElement("input_icon_dropoff", LocatorType.AccessibilityId); }



}
