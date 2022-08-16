package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_LiveTripsPage extends PageBase {

    public WebElement Menu_LiveTrips () { return findElement("adminmenu-livetrips", LocatorType.Id); }

    //public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Button_ApplyGeofenceFilter () { return findElement("btnApplyGeofence", LocatorType.Id); }

    public WebElement TextBox_Search_Field() { return findElement("SearchCriteria",LocatorType.Id);}

    public WebElement Button_Search() { return findElement("btnSearch",LocatorType.Id);}

    public WebElement Button_UpdateBungii() { return findElement("//button[contains(text(),'UPDATE BUNGII')]",LocatorType.XPath);}

    public WebElement Button_CalculateCost() { return findElement("//button[@id='btnCost']",LocatorType.XPath);}

    public WebElement RadioButton_EditDeliveryStatus() { return findElement("//span[contains(text(),'Edit Delivery Status')]",LocatorType.XPath);}

    public WebElement RadioButton_DeliveryCanceled() { return findElement("//span[contains(text(),'Delivery Canceled')]",LocatorType.XPath);}

    public WebElement Text_Page_Header() {return findElement("//h4[contains(text(),'Scheduled')]|//h4[contains(text(),'Live')]",LocatorType.XPath);}

    public WebElement Text_DeliveryHighlight() {return findElement("row1",LocatorType.Id);}

    public WebElement Text_AllDeliveryHighlight() {return findElement("//tbody[@id =\"TripListsTBody\"]/tr[1]",LocatorType.XPath);}

    public WebElement Message_AdminCompleteConfirm() {return findElement("//div[@id='editEndTime']//following::div[2]/p[1]",LocatorType.XPath);}

    public WebElement Icon_Hover() {return findElement("//tr[1]/td/a/i[@class='fa fa-exclamation']",LocatorType.XPath);}

    public WebElement Label_Tooltip() {return findElement("//tr[1]/td/a/i[@class='fa fa-exclamation']/following-sibling::span",LocatorType.XPath);}

}
