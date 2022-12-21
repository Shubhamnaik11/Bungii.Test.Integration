package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_DriversPage extends PageBase {

    public WebElement Menu_Drivers () { return findElement("//ul[@id='side-menu']/li/p/span[contains(text(),'Drivers')]", LocatorType.XPath); }

    //public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Label_DriversPageHeader () { return findElement("//h4[text()='Drivers']",LocatorType.XPath);}

    public WebElement Icon_DriverTrips (String driver) {return findElement(driver, LocatorType.XPath);}

    public WebElement Textbox_SearchCriteria () {return findElement("SearchCriteria", LocatorType.Id);}

    public WebElement Label_TripListHeader () { return  findElement("//h4[text()='Delivery List']", LocatorType.XPath);}

    public WebElement Grid_TripList () { return findElement("tblTripList",LocatorType.Id);}

    public WebElement Dropdown_SearchForPeriod () { return  findElement("//select[@class='user-trip-dropdown form-select']", LocatorType.XPath);}

    public WebElement Label_SuccessTripCount () {return  findElement("//h1[@class='text-success']",LocatorType.XPath); }

    public WebElement Link_ViewProfile() {return findElement("//a[contains(text(),'View Profile')]",LocatorType.XPath);}

    public WebElement Label_SuccessTripCount (String xpath) {return  findElement(xpath,LocatorType.XPath); }

    public WebElement Button_Next() {return findElement("//div/nav/ul/li/a[@aria-label=\"Next\"]",LocatorType.XPath);}

    public WebElement Link_SortCity() {return findElement("span-City",LocatorType.Id);}

    public List<WebElement> List_AllCityNames() {return findElements("//tbody[@id=\"NewApplicantsTBody\"]/tr/td[5]",LocatorType.XPath);}

    public List<WebElement> List_AllPages() {return findElements("//li[@class=\"page-item\"]/a",LocatorType.XPath);}

    public WebElement Text_AllPageNumber(boolean...ignoreException) {return findElement("//li[@class=\"page-item\"]/a",LocatorType.XPath,ignoreException);}

    public WebElement Link_ActiveDriverMap() {return findElement("//li/a[text()=\"Active Driver Map\"]",LocatorType.XPath);}

    public WebElement Image_Map() {return findElement("map",LocatorType.Id);}

    public WebElement Checkbox_ApplianceDolly() {return findElement("chkEquipment-1",LocatorType.Id);}
    public WebElement Checkbox_FurnitureDolly() {return findElement("chkEquipment-2",LocatorType.Id);}
    public WebElement Checkbox_HandDolly() {return findElement("chkEquipment-3",LocatorType.Id);}
    public WebElement Checkbox_LiftGate() {return findElement("chkEquipment-5",LocatorType.Id);}
    public WebElement Checkbox_Ramp() {return findElement("chkEquipment-4",LocatorType.Id);}

    public WebElement Checkbox_BoxTruck() {return findElement("chkVehicleType-3",LocatorType.Id);}
    public WebElement Checkbox_MovingVan() {return findElement("chkVehicleType-2",LocatorType.Id);}
    public WebElement Checkbox_PickupTruck() {return findElement("chkVehicleType-1",LocatorType.Id);}
    public WebElement Checkbox_SUV() {return findElement("chkVehicleType-4",LocatorType.Id);}

    public WebElement Text_DriverName(boolean...IgnoreException) {return findElement("//div[@class=\"driver-list\"]/div",LocatorType.XPath,IgnoreException);}
    public WebElement Icon_DriverPosition() {return findElement("//div[@class=\"driver-list\"]/div/span/img",LocatorType.XPath);}

    public WebElement Label_DriverStatus() {return findElement("//div/h4[text()=\"Driver Status\"]",LocatorType.XPath);}

    public WebElement TextBox_DriverStatusChangeComment() {return findElement("Comments",LocatorType.Id);}

    public WebElement Button_ConfirmDriverStatusChange() {return findElement("//div[1]/div/div/div/button[@id='btnagree']",LocatorType.XPath);}

    public WebElement Button_OkDriverStatusChange() {return findElement("btnDriverStatusChangeOK",LocatorType.Id);}

    public WebElement Text_DriverApplicationStatus() {return findElement("//div/table/tbody/tr/td[8]",LocatorType.XPath);}

    public List<WebElement> List_AllDriversInActiveMap() {return findElements("//div[@id=\"driverList\"]/div",LocatorType.XPath);}

    public WebElement Icon_Driver1OnMap() {return findElement("//div/span[text()=\"To navigate, press the arrow keys.\"]/following-sibling::div",LocatorType.XPath);}

    public WebElement Header_Details() {return findElement("firstHeading",LocatorType.Id);}

    public WebElement Text_DetailsPopupInformation(int number) {return findElement(String.format("//div[@id=\"bodyContent\"]/p[%d]",number),LocatorType.XPath);}

    public WebElement Text_AllDriversName() {return findElement("//div/table/tbody/tr/td[2]",LocatorType.XPath);}

    public WebElement Slider_VehicleBedLength() {return findElement("bedlengthRight",LocatorType.Id);}

    public WebElement Slider_VehiclePayload() {return findElement("range-slider-payload",LocatorType.ClassName);}

    public WebElement Slider_VehicleBedLengthMin() {return findElement("bedlengthLeft",LocatorType.Id);}

    public WebElement Slider_VehiclePayloadmin() {return findElement("payloadLeft",LocatorType.Id);}

    public WebElement Button_Trailer() {return findElement("switch-slider",LocatorType.ClassName);}

    public WebElement Text_OnlineDrivers() {return findElement("//strong[text()=\"Online Drivers\"]",LocatorType.XPath);}

    public WebElement Button_OnlineDrivers() {return findElement("custom-switch",LocatorType.Id);}

    public WebElement Text_ActivatedDate() {return findElement("//strong[text()=\"Activated Date\"]",LocatorType.XPath);}

    public WebElement Textbox_ActivatedDate() {return findElement("//strong[text()=\"Activated Date\"]/parent::div/div/div/input",LocatorType.XPath);}

    public WebElement Text_MostRecentDelivery() {return findElement("//strong[text()=\"Most Recent Delivery\"]",LocatorType.XPath);}

    public WebElement Textbox_MostRecentDelivery() {return findElement("//strong[text()=\"Most Recent Delivery\"]/parent::div/div/div/input",LocatorType.XPath);}

}
