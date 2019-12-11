package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends PageBase {

/*	public WebElement Button_GoOnline(boolean ...ignoreException) { return findElement("GO ONLINE", PageBase.LocatorType.Name,ignoreException); }
	public WebElement Button_GoOffline(boolean ...ignoreException) { return findElement("GO OFFLINE", PageBase.LocatorType.Name,ignoreException); }
	public WebElement Text_AvailableTrips() { return findElement("Available Trips", PageBase.LocatorType.Name); }

	public WebElement NavigationBar_Status () { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }
//	public WebElement Text_NavigationBar () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	//public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath,ignoreException); }
	public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName,ignoreException); }

	public WebElement Button_AppMenu () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }

	public WebElement Text_DriverName() { return findElement("//XCUIElementTypeButton[@name='Available Trips']/preceding-sibling::XCUIElementTypeStaticText[2]", PageBase.LocatorType.XPath); }
	public WebElement Text_DriverInfo() { return findElement("//XCUIElementTypeButton[@name='Available Trips']/preceding-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }
	public WebElement Button_DriverStatus() { return findElement("//XCUIElementTypeButton[@name='Available Trips']/preceding-sibling::XCUIElementTypeButton", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_AvailableTrip() { return findElement("//XCUIElementTypeStaticText[@name='AVAILABLE TRIPS']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_Home() { return findElement("//XCUIElementTypeStaticText[@name='HOME']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_Account() { return findElement("//XCUIElementTypeStaticText[@name='ACCOUNT']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_ScheduledTrip() { return findElement("//XCUIElementTypeStaticText[@name='SCHEDULED BUNGIIS']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_LogOut() { return findElement("//XCUIElementTypeStaticText[@name='LOGOUT']", PageBase.LocatorType.XPath); }*/

    public WebElement Button_GoOnline(boolean ...ignoreException) { return findElement("GO ONLINE", LocatorType.AccessibilityId,ignoreException); }
    public WebElement Button_GoOffline(boolean ...ignoreException) { return findElement("GO OFFLINE", PageBase.LocatorType.AccessibilityId,ignoreException); }
    public WebElement Text_AvailableTrips() { return findElement("Available Trips", PageBase.LocatorType.AccessibilityId); }

    public WebElement GoOnline_Btn() { return findElement("GO ONLINE", PageBase.LocatorType.AccessibilityId); }
    public WebElement GoOffline_Btn() { return findElement("GO OFFLINE", PageBase.LocatorType.AccessibilityId); }


    public WebElement NavigationBar_Status () { return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName); }
    //	public WebElement Text_NavigationBar () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

    //public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath,ignoreException); }
    public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName,ignoreException); }
    public WebElement NavigationBar_Text() {return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName); }

    //public WebElement Button_AppMenu () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
    public WebElement Button_AppMenu () { return findElement("**/XCUIElementTypeNavigationBar/XCUIElementTypeButton", LocatorType.ClassChain); }
//'**/XCUIElementTypeNavigationBar/XCUIElementTypeButton

    public WebElement Text_DriverName() { return findElement("//XCUIElementTypeButton[@name='Available Trips']/preceding-sibling::XCUIElementTypeStaticText[2]", PageBase.LocatorType.XPath); }
    public WebElement Text_DriverInfo() { return findElement("//XCUIElementTypeButton[@name='Available Trips']/preceding-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }
    public WebElement Button_DriverStatus() { return findElement("//XCUIElementTypeButton[@name='Available Trips']/preceding-sibling::XCUIElementTypeButton", PageBase.LocatorType.XPath); }

    public WebElement AppMenu_AvailableTrip() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'AVAILABLE TRIPS'", LocatorType.Predicate); }
    public WebElement AppMenu_Home() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'HOME'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_Account() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'ACCOUNT'", PageBase.LocatorType.Predicate); }


    public WebElement AppMenu_ScheduledTrip() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'SCHEDULED BUNGIIS'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_LogOut1(boolean ...ignoreException) { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'LOGOUT'", PageBase.LocatorType.Predicate,ignoreException); }
  //  public WebElement AppMenu_LogOut() { return findElement("//XCUIElementTypeStaticText[@name=\"LOGOUT\"]", PageBase.LocatorType.XPath); }
    public WebElement AppMenu_LogOut() { return findElement("//XCUIElementTypeStaticText[@name=\"LOGOUT\"]/parent::XCUIElementTypeCell", LocatorType.XPath); }

    public List<WebElement> FilledStars() {return findElements("//*[contains(@name, 'rating filled star icon')]", LocatorType.XPath);}
    public List<WebElement> HalfFilledStar() {return findElements("//*[contains(@name, 'rating half filled star icon')]", LocatorType.XPath);}
    public List<WebElement> UnselectedStars() {return findElements("//*[contains(@name, 'rating unselected star icon')]", LocatorType.XPath);}


}
