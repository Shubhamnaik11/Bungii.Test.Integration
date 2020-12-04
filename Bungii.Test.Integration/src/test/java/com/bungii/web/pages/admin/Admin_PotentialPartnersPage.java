package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_PotentialPartnersPage extends PageBase {

    public WebElement Menu_PotentialPartners () { return findElement("adminmenu-potentialpartners", LocatorType.Id); }

    public WebElement Menu_AssignPartner() { return findElement("//li[@id='adminmenu-assigncluster']/a", LocatorType.XPath); }

    public WebElement Menu_PartnerSearch() { return findElement("adminmenu-editcluster", LocatorType.Id); }

    public WebElement Dropdown_Geofence () { return findElement("//select[@id='drpGeofence']", LocatorType.XPath); }

    public WebElement Button_ApplyGeofenceFilter () { return findElement("btnApplyGeofence", LocatorType.Id); }

    public WebElement Label_PointOfInterest() {return findElement("//div[@class='panel-body']/div/label[contains(@text, '')]", LocatorType.XPath);}

    public WebElement Text_PickupsNumberInCluster() { return findElement("//p[3]/span[@class='text-gray']/following-sibling::text()", LocatorType.XPath);}

    public WebElement Text_PickupCountUnderClusterTrips() { return findElement("//strong[@id='sTripCount']", LocatorType.XPath);}

    public WebElement Hyperlink_ViewTrips() { return findElement("//p[3]/a[@class='btn btn-link']/u", LocatorType.XPath);}

    public WebElement RadioBox_EditTrip() {return findElement("//label[contains(@class,'adminEditTrip')]/span", LocatorType.XPath);}
    public WebElement Text_EditTripType() {return findElement("//div[@class='CancelComments row']/p[2]", LocatorType.XPath);}
    public WebElement Calendar_EditTripDetailsScheduledDate() {return findElement("PickupDetails_ScheduledDate", LocatorType.Id);}
    public WebElement Time_EditTripDetailsTime(){return findElement("PickupDetails_ScheduledTime", LocatorType.Id);}
    public WebElement TextBox_DriverSearch() {return findElement("txtDriverSearch", LocatorType.Id);}
    public WebElement Button_VerifyDriver(){return findElement("//button[contains(text(),'VERIFY')]", LocatorType.XPath);}
    public WebElement Select_TestDriver(){return findElement("//div[@id='divDriversResult']/div[@class='pac-item'][1]", LocatorType.XPath);}

    //public WebElement Text_EditTrpDetailsDriver1Name(){return findElement("//table[@id='editTripDrivers']/tbody/tr[1]/td[@class='no-padding']/table[@class='bg-gray mt5 border-radius5']/tbody/tr/td[2]", LocatorType.XPath);}
    //public WebElement Text_EditTrpDetailsDriver2Name(){return findElement("//table[@id='editTripDrivers']/tbody/tr[1]/td[@class='no-padding']/table[@class='bg-gray mt5 border-radius5']/tbody/tr/td[2]", LocatorType.XPath);}
    public WebElement Text_EditTrpDetailsDriver1Name(){return findElement("//table[@id='editTripDrivers']/tbody/tr[1]/td/table/tbody/tr/td[3]", LocatorType.XPath);}
    public WebElement Text_EditTrpDetailsDriver2Name(){return findElement("//table[@id='editTripDrivers']/tbody/tr[2]/td/table/tbody/tr/td[3]", LocatorType.XPath);}

    public WebElement Text_Driver1Name(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tv_drivername_value']", LocatorType.XPath).get(0);}
    public WebElement Text_Driver2Name(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tv_drivername_value']", LocatorType.XPath).get(1);}
    public WebElement Text_VerifyChangesSavedMessage() {return findElement("//p[@id='verified-message']/i[2]", LocatorType.XPath);}
    public WebElement Button_SaveChanges(){return findElement("//button[@class='btn btn-primary ml15 saveTrip']", LocatorType.XPath);}
    public WebElement Text_SuccessMessage(){return findElement("//p[@id='success-message']/i[2]", LocatorType.XPath);}
    public WebElement Label_IconTextMessage(){return findElement("//div[@class='addDriver ml15 mt20']/small[@class='font-size-79percent']/em", LocatorType.XPath);}
    public WebElement Label_ChangedScheduledTime(){return findElement("//p[contains(text(),'Schedule Time:')]/following-sibling::p", LocatorType.XPath);}
    public WebElement Button_ClosePopUp(){return findElement("//button[@class='close']/span", LocatorType.XPath);}


    public WebElement Text_DriversListScheduledTrips() {return findElement("//tr[1]/td[9]", LocatorType.XPath);}
}
