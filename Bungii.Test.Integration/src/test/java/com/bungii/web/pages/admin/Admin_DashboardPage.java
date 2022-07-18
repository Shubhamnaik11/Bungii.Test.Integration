package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_DashboardPage extends PageBase {

    public WebElement RecentDriverRegistrations() { return findElement("//*[@id='GeofenceDashboard']//h4/text()='Recent driver Registrations'", LocatorType.XPath); }

    public List<WebElement> PendingVerification() { return findElements("//td[text()='Pending Verification']/following-sibling::td[2]/a", LocatorType.XPath); }

    public WebElement Link_ViewAllDriverRegistrations () { return findElement("//a[text()='View All Driver Registrations']", LocatorType.XPath); }

    public WebElement TextBox_Search() { return findElement("DriverListViewResponseModel_SearchCriteria']", LocatorType.Id); }


    public WebElement GridRow_PendingVerificationLink (String LastName) { return findElement("//td[text()='James "+LastName+"']/following-sibling::td[text()='Pending Verification']/following-sibling::td[2]/a", LocatorType.XPath); }

    public WebElement Menu_Dashboard () { return findElement("//li[@id='adminmenu-dashboard']/a", LocatorType.XPath); }
    public WebElement Icon_Search() { return findElement("btnSearchDriver", LocatorType.Id); }

   // public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement TextBox_SearchCustomer() { return findElement("txtSearchCustomer", LocatorType.Id); }

    public WebElement Textbox_DriverSearch () { return findElement("txtSearchDriver",LocatorType.Id);}
    public WebElement Button_Drivers() { return findElement("//*[@id='adminmenu-drivers-menu']/a", LocatorType.XPath); }
    public WebElement Button_Customers() { return findElement("//*[@id='adminmenu-customers']/a", LocatorType.XPath); }
}
