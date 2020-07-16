package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_DriversPage extends PageBase {

    public WebElement Menu_Drivers () { return findElement("//img[@alt='Drivers']", LocatorType.XPath); }

    public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Label_DriversPageHeader () { return findElement("//h4[text()='Drivers']",LocatorType.XPath);}

    public WebElement Icon_DriverTrips (String driver) {return findElement(driver, LocatorType.XPath);}

    public WebElement Textbox_SearchCriteria () {return findElement("SearchCriteria", LocatorType.Id);}

    public WebElement Label_TripListHeader () { return  findElement("//h4[text()='Trip List']", LocatorType.XPath);}

    public WebElement Grid_TripList () { return findElement("tblTripList",LocatorType.Id);}

    public WebElement Dropdown_SearchForPeriod () { return  findElement("SearchForPeriod", LocatorType.Id);}

    public WebElement Label_SuccessTripCount () {return  findElement("//h1[@class='text-success']",LocatorType.XPath); }

    public WebElement Link_ViewProfile() {return findElement("//a[contains(text(),'View Profile')]",LocatorType.XPath);}

    public WebElement Label_SuccessTripCount (String xpath) {return  findElement(xpath,LocatorType.XPath); }


}
