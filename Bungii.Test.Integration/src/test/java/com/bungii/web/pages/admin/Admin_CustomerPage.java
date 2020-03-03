package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_CustomerPage extends PageBase {

    public WebElement Menu_Customers () { return findElement("//li[@id='adminmenu-customers']/a", LocatorType.XPath); }

    public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Dropdown_TimeFrame () { return findElement("SearchForPeriod", LocatorType.Id); }

    public WebElement TextBox_SearchCustomer() { return findElement("SearchCriteria", LocatorType.Id); }

    public WebElement Label_CustomerList(){ return findElement("//div[@class='col-sm-8']/h4", LocatorType.XPath);}

    public WebElement Header_Name(){ return findElement("//span[@id='span-Name']", LocatorType.XPath);}

    public WebElement Header_TripsRequested(){ return findElement("//span[@id='span-NbrOfRequestedTrips']", LocatorType.XPath);}

    public WebElement Header_TripsEstimated(){ return findElement("//span[@id='span-NbrOfEstimatedTrips']", LocatorType.XPath);}

    public WebElement Header_CustomerJoinDate(){ return findElement("//span[@id='span-JoinDate']", LocatorType.XPath);}

    public WebElement Header_LastActivity(){ return findElement("//span[@id='span-LastTripDate']", LocatorType.XPath);}

    public WebElement Header_Spent(){ return findElement("//span[@id='span-AmountSpent']", LocatorType.XPath);}


}
