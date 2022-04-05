package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends PageBase {

/*	public WebElement Button_GoOnline(boolean ...ignoreException) { return findElement("GO ONLINE", PageBase.LocatorType.Name,ignoreException); }
	public WebElement Button_GoOffline(boolean ...ignoreException) { return findElement("GO OFFLINE", PageBase.LocatorType.Name,ignoreException); }
	public WebElement Text_AvailableTrips() { return findElement("Available Bungiis", PageBase.LocatorType.Name); }

	public WebElement NavigationBar_Status () { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath); }
//	public WebElement Text_NavigationBar () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }
	//public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath,ignoreException); }
	public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName,ignoreException); }

	public WebElement Button_AppMenu () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }

	public WebElement Text_DriverName() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeStaticText[2]", PageBase.LocatorType.XPath); }
	public WebElement Text_DriverInfo() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }
	public WebElement Button_DriverStatus() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeButton", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_AvailableTrip() { return findElement("//XCUIElementTypeStaticText[@name='AVAILABLE BUNGIIS']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_Home() { return findElement("//XCUIElementTypeStaticText[@name='HOME']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_Account() { return findElement("//XCUIElementTypeStaticText[@name='ACCOUNT']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_ScheduledTrip() { return findElement("//XCUIElementTypeStaticText[@name='SCHEDULED BUNGIIS']", PageBase.LocatorType.XPath); }
	public WebElement AppMenu_LogOut() { return findElement("//XCUIElementTypeStaticText[@name='LOGOUT']", PageBase.LocatorType.XPath); }*/

    //public WebElement Button_GoOnline(boolean ...ignoreException) { return findElement("GO ONLINE", LocatorType.AccessibilityId,ignoreException); }
    public WebElement Button_GoOnline(boolean ...ignoreException) { return findElement("//XCUIElementTypeStaticText[@name='OFFLINE']", LocatorType.XPath,ignoreException); }
    public WebElement Button_GoOffline(boolean ...ignoreException) { return findElement("GO OFFLINE", PageBase.LocatorType.AccessibilityId,ignoreException); }
    public WebElement Text_AvailableTrips() { return findElement("Available Bungiis", PageBase.LocatorType.AccessibilityId); }
    public WebElement Link_View_AvailableTrips() { return findElement("//XCUIElementTypeStaticText[@name='View Available Bungiis']", LocatorType.XPath); }

    //public WebElement GoOnline_Btn() { return findElement("GO ONLINE", PageBase.LocatorType.AccessibilityId); }
    public WebElement GoOnline_Btn() { return findElement("//XCUIElementTypeStaticText[@name='OFFLINE']", LocatorType.XPath); }
    //public WebElement GoOffline_Btn() { return findElement("GO OFFLINE", PageBase.LocatorType.AccessibilityId); }
    public WebElement GoOffline_Btn() { return findElement("//XCUIElementTypeStaticText[@name='ONLINE']", LocatorType.XPath); }


    public WebElement NavigationBar_Status (boolean ...ignoreException) { return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName, ignoreException); }
    //	public WebElement Text_NavigationBar () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

    //public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar", PageBase.LocatorType.XPath,ignoreException); }
    public WebElement Text_NavigationBar (boolean ...ignoreException) { return findElement("//XCUIElementTypeNavigationBar", LocatorType.XPath,ignoreException); }
    public WebElement NavigationBar_Text() {return findElement("XCUIElementTypeNavigationBar", LocatorType.ClassName); }
    public WebElement Text_Bungii_Completed() { return findElement("//XCUIElementTypeStaticText[@name='Bungii completed']",LocatorType.XPath);}
    //public WebElement Button_AppMenu () { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeButton", PageBase.LocatorType.XPath); }
    public WebElement Button_AppMenu (boolean ...ignoreException) { return findElement("**/XCUIElementTypeNavigationBar/XCUIElementTypeButton", LocatorType.ClassChain, ignoreException); }
//'**/XCUIElementTypeNavigationBar/XCUIElementTypeButton

    public WebElement Text_DriverName() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeStaticText[2]", PageBase.LocatorType.XPath); }
    public WebElement Text_DriverName1(String driverName) { return findElement("//XCUIElementTypeStaticText[@name='"+driverName+"']", PageBase.LocatorType.XPath); }
    public WebElement Text_DriverInfo() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }
    public WebElement Text_DriverInfoOnline() { return findElement("//XCUIElementTypeStaticText[@name='ONLINE']/following::XCUIElementTypeStaticText[1]", PageBase.LocatorType.XPath); }
    public WebElement Button_DriverStatus() { return findElement("//XCUIElementTypeButton[@name='Available Bungiis']/preceding-sibling::XCUIElementTypeButton", PageBase.LocatorType.XPath); }

    public WebElement AppMenu_AvailableTrip() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'AVAILABLE BUNGIIS'", LocatorType.Predicate); }
    public WebElement AppMenu_Home() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'HOME'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_Account() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'ACCOUNT'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_AccountInfo() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'ACCOUNT INFO'", PageBase.LocatorType.Predicate); }

    public WebElement Button_MenuBack() {
        //  return findElement("//XCUIElementTypeButton[@name='LOG IN']", LocatorType.XPath);
        return findElement("type == 'XCUIElementTypeButton' AND name == 'Back'", LocatorType.Predicate);

    }

    public WebElement AppMenu_ScheduledTrip() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'SCHEDULED BUNGIIS'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_FAQ() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'FAQ'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_EARNINGS() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'EARNINGS'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_TripAlertSettings() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'ALERT SETTINGS'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_PrivacyPolicy() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'PRIVACY POLICY'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_Feedback() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'FEEDBACK'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_Store() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'BUNGII STORE'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_LEADERBOARD() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'LEADERBOARD'", PageBase.LocatorType.Predicate); }
    public WebElement AppMenu_LogOut1(boolean ...ignoreException) { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'LOGOUT'", PageBase.LocatorType.Predicate,ignoreException); }
  //  public WebElement AppMenu_LogOut() { return findElement("//XCUIElementTypeStaticText[@name=\"LOGOUT\"]", PageBase.LocatorType.XPath); }
    public WebElement AppMenu_LogOut() { return findElement("//XCUIElementTypeStaticText[@name=\"LOGOUT\"]/parent::XCUIElementTypeCell", LocatorType.XPath); }

    public List<WebElement> FilledStars() {return findElements("//*[contains(@name, 'rating filled star icon')]", LocatorType.XPath);}
    public List<WebElement> HalfFilledStar() {return findElements("//*[contains(@name, 'rating half filled star icon')]", LocatorType.XPath);}
    public List<WebElement> UnselectedStars() {return findElements("//*[contains(@name, 'rating unselected star icon')]", LocatorType.XPath);}

    public WebElement Text_CommonQuestions() { return findElement("type == 'XCUIElementTypeOther' AND name == 'Common Questions'", PageBase.LocatorType.Predicate); }
    public WebElement Text_Leaderboard() { return findElement("//XCUIElementTypeOther[@name=\"Driver Of The Month\"]/following-sibling::XCUIElementTypeOther", LocatorType.XPath); }
    public WebElement Text_ScheduledBungiis() { return findElement("//XCUIElementTypeTable[@name=\"No Bungiis, You don't have any scheduled\u2028Bungiis at this time.\"]", LocatorType.XPath); }
    public WebElement Text_AvailableTripsData() { return findElement("//XCUIElementTypeStaticText[@name=\"No Bungiis available\"]", LocatorType.XPath); }
    public WebElement Text_Earnings() { return findElement("//XCUIElementTypeStaticText[@name=\"Disbursement info\"]", LocatorType.XPath); }
    public WebElement Text_TripAlertSettings() { return findElement("//XCUIElementTypeButton[@name=\"Delivery Alerts\"]", LocatorType.XPath); }
    public WebElement Text_SMSAlertSettings() { return findElement("//XCUIElementTypeButton[@name=\"SMS Alerts\"]", LocatorType.XPath); }
    public WebElement Text_Privacy() {return findElement("//XCUIElementTypeStaticText[@name=\"Privacy\"]",LocatorType.XPath);}
    public WebElement Text_NoDelivery(boolean ...ignoreException) {return findElement("//XCUIElementTypeStaticText[@value='There are no deliveries available. Try checking back later.']",LocatorType.XPath,ignoreException);}
    public WebElement Button_Back() {return findElement("//XCUIElementTypeButton[@name=\"Back\"]",LocatorType.XPath);}
    public WebElement Text_Feedback() { return findElement("//XCUIElementTypeStaticText[@name=\"Send us your feedback\"]", LocatorType.XPath); }
    public WebElement Text_Store() { return findElement("//XCUIElementTypeStaticText[@name=\"BUNGII STORE\"]", LocatorType.XPath); }
    public WebElement Text_ApplicationError(boolean ...ignoreException) { return findElement("//XCUIElementTypeStaticText[@name=\"An application error has occured.\"]", LocatorType.XPath,ignoreException); }
    public WebElement Text_MyStat() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'My Stats'", PageBase.LocatorType.Predicate); }
    public WebElement Text_TotalTrip() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'Total Trips'", PageBase.LocatorType.Predicate); }
    public WebElement Text_TripMonths() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'Trips / Month'", PageBase.LocatorType.Predicate); }
    public WebElement Text_EarningMonth() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'Earnings / Month'", PageBase.LocatorType.Predicate); }
    public WebElement Text_Total_Earnings() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'Total Earnings'", PageBase.LocatorType.Predicate); }
    public WebElement Text_Total_Tips() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'Total Tips'", PageBase.LocatorType.Predicate); }
    public WebElement Text_My_Rating() { return findElement("type == 'XCUIElementTypeStaticText' AND name == 'My Rating'", PageBase.LocatorType.Predicate); }
    public WebElement Link_Itemized_Earnings() { return findElement("type == 'XCUIElementTypeLink' AND name == 'Click here to view itemized earnings Itemized Earnings'", PageBase.LocatorType.Predicate); }


    public WebElement Application_Name(boolean ...ignoreException) {return findElement("XCUIElementTypeApplication", LocatorType.ClassName,ignoreException); }


}
