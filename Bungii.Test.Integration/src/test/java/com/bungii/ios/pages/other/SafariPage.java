package com.bungii.ios.pages.other;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SafariPage extends PageBase {
    public WebElement Icon_Safari() { return findElement("//XCUIElementTypeIcon[contains(@name,'Safari')]", LocatorType.XPath);}
    public WebElement Textbox_SafariSearch() { return findElement("//XCUIElementTypeTextField[contains(@name,'TabBarItemTitle')]", LocatorType.XPath);}
    public WebElement Textbox_SafariSearchBar() { return findElement("//XCUIElementTypeTextField[contains(@name,'URL')]", LocatorType.XPath);}

    public WebElement Button_Go() { return findElement("//XCUIElementTypeButton[contains(@name,'Go')]", LocatorType.XPath);}

    //Partner Login
    public WebElement Textbox_EnterPassword() { return findElement("//XCUIElementTypeSecureTextField[contains(@value,'Enter Password')]", LocatorType.XPath);}
    public WebElement Button_SignIn() { return findElement("//XCUIElementTypeButton[contains(@name,'SIGN IN')]", LocatorType.XPath);}

    public WebElement Icon_BungiiLogo() { return findElement("//XCUIElementTypeImage[@name=\"Bungii Logo\"]", LocatorType.XPath);}
    public WebElement Icon_Logout() { return findElement("//XCUIElementTypeImage[@name=\"Logout\"]", LocatorType.XPath);}
    public WebElement Text_WhatsNeeded() { return findElement("//XCUIElementTypeStaticText[@name=\"WHAT'S NEEDED?\"] | //android.widget.TextView[@text=\"WHAT'S NEEDED?\"]", LocatorType.XPath);}
    public WebElement Text_PickUpAddress() { return findElement("//XCUIElementTypeStaticText[@name=\"PICKUP ADDRESS\"]", LocatorType.XPath);}
    public WebElement Text_DeliveryAddress() { return findElement("//XCUIElementTypeStaticText[@name=\"DELIVERY ADDRESS\"] | //android.view.View[@text=\"DELIVERY ADDRESS\"]", LocatorType.XPath);}
    public WebElement Text_PickUpDate() { return findElement("//XCUIElementTypeStaticText[@name=\"PICKUP DATE\"]", LocatorType.XPath);}
    public WebElement Text_PickUpTime() { return findElement("//XCUIElementTypeStaticText[contains(@name,'PICKUP TIME')]", LocatorType.XPath);}
    public WebElement Text_SucessMsg() { return findElement("//android.widget.TextView[contains(@text,'Your delivery has been scheduled.')]", LocatorType.XPath);}
    public WebElement Text_TrackingId() { return findElement("//android.view.View[contains(@text,'TRACKING ID:')]", LocatorType.XPath);}
    public WebElement Text_Time() { return findElement("//android.view.View[contains(@text,'PICKUP DATE & TIME:')]", LocatorType.XPath);}
    public WebElement Text_Drivers() { return findElement("//android.view.View[contains(@text,'WHAT’S NEEDED:')]", LocatorType.XPath);}
    public WebElement Text_Payment() { return findElement("//android.view.View[contains(@text,'PAYMENT:')]", LocatorType.XPath);}
    public WebElement Button_NewBungii() { return findElement("//android.widget.Button[contains(@text,'NEW BUNGII')]", LocatorType.XPath);}


    //Fixed pricing Portal
    public WebElement Text_Solo() { return findElement("//XCUIElementTypeOther[@name=\"Solo\"]", LocatorType.XPath);}
    public WebElement Text_Duo() { return findElement("//XCUIElementTypeOther[@name=\"Duo\"]", LocatorType.XPath);}
    public WebElement Text_ServiceLevel() { return findElement("//XCUIElementTypeStaticText[@name=\"SERVICE LEVEL\"]", LocatorType.XPath);}
    public WebElement Textbox_PickUpAddress() { return findElement("//XCUIElementTypeTextField[contains(@value,'Enter pickup address')]", LocatorType.XPath);}
    public WebElement Textbox_DeliveryAddress() { return findElement("//XCUIElementTypeTextField[contains(@value,'Enter dropoff address')]", LocatorType.XPath);}
    public WebElement Dropdown_ServiceLevel() { return findElement("//XCUIElementTypeOther[contains(@name,'SERVICE LEVEL')]/following::XCUIElementTypeOther[1]", LocatorType.XPath);}
    public WebElement Dropdown_Values(String serviceLevel) { return findElement("//XCUIElementTypeOther[contains(@name,'"+serviceLevel+"')]", LocatorType.XPath);}
    public WebElement Button_Return() { return findElement("//XCUIElementTypeButton[contains(@name,'Return')]", LocatorType.XPath);}
    public WebElement Button_Continue() { return findElement("//XCUIElementTypeButton[contains(@name,'Submit')]", LocatorType.XPath);}

    public WebElement Textbox_Items() { return findElement("//android.widget.EditText[contains(@resource-id,'Name_0')]", LocatorType.XPath);}
    public WebElement Textbox_Dimensions() { return findElement("//android.widget.EditText[contains(@resource-id,'Dimensions_0')]", LocatorType.XPath);}
    public WebElement Textbox_Weight() { return findElement("//android.widget.EditText[contains(@resource-id,'Weight_0')]", LocatorType.XPath);}
    public WebElement Textbox_CustomerName() { return findElement("//android.view.View[contains(@text,'CUSTOMER NAME (FIRST & LAST)*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}
    public WebElement Textbox_CustomerNumber() { return findElement("//android.view.View[contains(@text,'CUSTOMER MOBILE*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}
    public WebElement Textbox_DeliveryPurpose() { return findElement("//android.view.View[contains(@text,'DELIVERY PURPOSE*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}
    public WebElement Textbox_RbNumber() { return findElement("//android.view.View[contains(@text,'RB/SB NUMBER*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}
    public WebElement Textbox_ScheduleBy() { return findElement("//android.view.View[contains(@text,'SCHEDULED BY*')]/following-sibling::android.widget.EditText", LocatorType.XPath);}



}