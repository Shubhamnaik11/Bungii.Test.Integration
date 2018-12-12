package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class HomePage extends PageBase {

    public WebElement Text_NavigationBar() {return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
    public WebElement BUTTON_SET() {return findElement("//XCUIElementTypeButton[@name='SET']", PageBase.LocatorType.XPath); }
    public WebElement TextBox_Pickup() {return findElement("//XCUIElementTypeStaticText[@name='PICKUP LOCATION']/following-sibling::XCUIElementTypeTextField", PageBase.LocatorType.XPath); }
    public WebElement Link_PickUpSuggestion() {return findElement("//XCUIElementTypeStaticText[@name='PICKUP LOCATION']/following::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
    public WebElement TextBox_Drop() {return findElement("//XCUIElementTypeStaticText[@name=\"DROP OFF LOCATION\"]/following-sibling::XCUIElementTypeTextField", PageBase.LocatorType.XPath); }
    public WebElement Link_DropSuggestion() {return findElement("//XCUIElementTypeStaticText[@name='DROP OFF LOCATION']/following::XCUIElementTypeCell", PageBase.LocatorType.XPath); }
    public WebElement Button_ClearPickup() {return findElement("//XCUIElementTypeStaticText[@name='PICKUP LOCATION']/following-sibling::XCUIElementTypeTextField/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
    public WebElement Button_ClearDrop() {return findElement("//XCUIElementTypeStaticText[@name=\"DROP OFF LOCATION\"]/following-sibling::XCUIElementTypeTextField/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
    public WebElement Text_EtaDropHeader() {return findElement("//XCUIElementTypeButton[@name='SET']/preceding-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }
    public WebElement Text_EtaPickupHeader() {return findElement("//XCUIElementTypeButton[@name='SET']/preceding-sibling::XCUIElementTypeStaticText[2]", PageBase.LocatorType.XPath); }
    public WebElement Text_EtaTime() {return findElement("//XCUIElementTypeButton[@name='SET']/preceding-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }
    public WebElement Button_GetEstimate() {return findElement("//XCUIElementTypeButton[@name='GET ESTIMATE']", PageBase.LocatorType.XPath); }
    public WebElement Scroll_SoloToDuo() {return findElement("//XCUIElementTypeStaticText[@name='1']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther", PageBase.LocatorType.XPath); }
    public WebElement Button_AppMenu() {return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
    public WebElement AppMenu_Home() {return findElement("//XCUIElementTypeStaticText[@name='HOME']", PageBase.LocatorType.XPath); }
    public WebElement AppMenu_FAQ() {return findElement("//XCUIElementTypeStaticText[@name='FAQ']", PageBase.LocatorType.XPath); }
    public WebElement AppMenu_Account() {return findElement("//XCUIElementTypeStaticText[@name='ACCOUNT']", PageBase.LocatorType.XPath); }
    public WebElement AppMenu_ScheduledTrip() {return findElement("//XCUIElementTypeStaticText[@name='SCHEDULED BUNGIIS']", PageBase.LocatorType.XPath); }
    public WebElement AppMenu_Payment() {return findElement("//XCUIElementTypeStaticText[@name='PAYMENT']", PageBase.LocatorType.XPath); }
    public WebElement AppMenu_Support() {return findElement("//XCUIElementTypeStaticText[@name='SUPPORT']", PageBase.LocatorType.XPath); }
    public WebElement AppMenu_Promos() {return findElement("//XCUIElementTypeStaticText[@name='PROMOS']", PageBase.LocatorType.XPath); }
    public WebElement AppMenu_DriveWithBungii() {return findElement("//XCUIElementTypeStaticText[@name='DRIVE WITH BUNGII']", PageBase.LocatorType.XPath); }


    public WebElement AppMenu_LogOut() {return findElement("LOGOUT", PageBase.LocatorType.Name); }
   // public WebElement Indicator_Loading() {return findElement("In progress", PageBase.LocatorType.Name); }
    public WebElement Text_HeaderPickup() {return findElement("PICKUP LOCATION", PageBase.LocatorType.Name); }
    public WebElement Text_HeaderDrop() {return findElement("DROP OFF LOCATION", PageBase.LocatorType.Name); }
    public WebElement Button_SoloActive() {return findElement("bungii solo active", PageBase.LocatorType.Name); }
    public WebElement Button_SoloDeActive() {return findElement("bungii solo deactive", PageBase.LocatorType.Name); }
    public WebElement Button_DuoActive() {return findElement("bungii duo active", PageBase.LocatorType.Name); }
    public WebElement Button_DuoDeActive() {return findElement("bungii duo deactive", PageBase.LocatorType.Name); }
    public WebElement Image_Loading() {return findElement("In progress", PageBase.LocatorType.Name); }
    public WebElement Image_eta_bar() {return findElement("eta_bar_bg", PageBase.LocatorType.Name); }
    public WebElement Button_Invite() {return findElement("menu icon invite referrals", PageBase.LocatorType.Name); }

}
