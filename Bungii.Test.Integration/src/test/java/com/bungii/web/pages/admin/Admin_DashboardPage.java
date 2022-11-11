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

    public WebElement Menu_Dashboard () { return findElement("//span[contains(text(),'Dashboard')]", LocatorType.XPath); }
    public WebElement Icon_Search() { return findElement("btnSearchDriver", LocatorType.Id); }
    public WebElement Icon_DriverSearch() { return findElement("//h4[text()='Recent Driver Registrations']/ancestor::div[@class='col-sm-5']/following-sibling::div/div/div/input/following-sibling::button", LocatorType.XPath); }

   // public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement TextBox_SearchCustomer() { return findElement("SearchCriteria", LocatorType.Id); }

    public WebElement Textbox_DriverSearch () { return findElement("//h4[text()='Recent Driver Registrations']/ancestor::div[@class='col-sm-5']/following-sibling::div/div/div/input",LocatorType.XPath);}
    public WebElement Link_Drivers() { return findElement("//*[@id='adminmenu-drivers-menu']/a", LocatorType.XPath); }
    public WebElement Link_Customers() { return findElement("//*[@id='adminmenu-customers']/a", LocatorType.XPath); }
    public WebElement Link_NonActiveDriver() { return findElement("//*[@id='adminmenu-customers']/a", LocatorType.XPath); }

}
