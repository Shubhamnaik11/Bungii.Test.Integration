package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_DriversPage extends PageBase {

    public WebElement Menu_Drivers () { return findElement("//img[@alt='Drivers']", LocatorType.XPath); }

    //public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Label_DriversPageHeader () { return findElement("//h4[text()='Drivers']",LocatorType.XPath);}

    public WebElement Icon_DriverTrips (String driver) {return findElement(driver, LocatorType.XPath);}

    public WebElement Textbox_SearchCriteria () {return findElement("SearchCriteria", LocatorType.Id);}

    public WebElement Label_TripListHeader () { return  findElement("//h4[text()='Delivery List']", LocatorType.XPath);}

    public WebElement Grid_TripList () { return findElement("tblTripList",LocatorType.Id);}

    public WebElement Dropdown_SearchForPeriod () { return  findElement("SearchForPeriod", LocatorType.Id);}

    public WebElement Label_SuccessTripCount () {return  findElement("//h1[@class='text-success']",LocatorType.XPath); }

    public WebElement Link_ViewProfile() {return findElement("//a[contains(text(),'View Profile')]",LocatorType.XPath);}

    public WebElement Label_SuccessTripCount (String xpath) {return  findElement(xpath,LocatorType.XPath); }

    public WebElement Button_Next() {return findElement("//div/nav/ul/li/a[@aria-label=\"Next\"]",LocatorType.XPath);}

    public WebElement Link_SortCity() {return findElement("span-City",LocatorType.Id);}

    public List<WebElement> List_AllCityNames() {return findElements("//tbody[@id=\"NewApplicantsTBody\"]/tr/td[5]",LocatorType.XPath);}

    public List<WebElement> List_AllPages() {return findElements("//li[@class=\"page-item\"]/a",LocatorType.XPath);}

    public WebElement Text_AllPageNumber(boolean...ignoreException) {return findElement("//li[@class=\"page-item\"]/a",LocatorType.XPath,ignoreException);}

    public WebElement Link_ActiveDriverMap() {return findElement("adminmenu-nonactivedriversy",LocatorType.Id);}

    public WebElement Image_Map() {return findElement("map",LocatorType.Id);}

}
