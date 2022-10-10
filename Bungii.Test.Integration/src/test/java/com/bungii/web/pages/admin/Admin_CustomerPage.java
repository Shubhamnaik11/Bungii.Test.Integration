package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_CustomerPage extends PageBase {

    public WebElement Menu_Customers () { return findElement("//span[contains(text(),'Customers')]", LocatorType.XPath); }

   // public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Dropdown_TimeFrame () { return findElement("SearchForPeriod", LocatorType.Id); }

    public WebElement TextBox_SearchCustomer() { return findElement("SearchCriteria", LocatorType.Id); }

    //public WebElement Label_CustomerList(){ return findElement("//div[@class='col-sm-8']/h4", LocatorType.XPath);}
    public WebElement Label_CustomerList(){ return findElement("//h4[contains(text(),'Customer list')]", LocatorType.XPath);}

    public WebElement Header_Name(){ return findElement("//span[@id='span-Name']", LocatorType.XPath);}

    public WebElement Header_TripsRequested(){ return findElement("//span[@id='span-NbrOfRequestedTrips']", LocatorType.XPath);}

    public WebElement Header_TripsEstimated(){ return findElement("//span[@id='span-NbrOfEstimatedTrips']", LocatorType.XPath);}

    public WebElement Header_CustomerJoinDate(){ return findElement("//span[@id='span-JoinDate']", LocatorType.XPath);}

    public WebElement Header_LastActivity(){ return findElement("//span[@id='span-LastTripDate']", LocatorType.XPath);}

    public WebElement Header_Spent(){ return findElement("//span[@id='span-AmountSpent']", LocatorType.XPath);}

    public WebElement Label_NoCustomerFound(){return findElement("//td[contains(text(),'No Data.')]", LocatorType.XPath);}

    public WebElement TextBox_Phone() { return findElement("//div[@class='col-sm-2']/input", LocatorType.XPath); }

    public WebElement TextBox_Email() { return findElement("//input[@class='usertrip-form-control-email form-control']", LocatorType.XPath); }

    public WebElement Icon_EditPhone() { return findElement("phone-edit", LocatorType.Id); }

    public WebElement Icon_EditEmail() { return findElement("//img[@title='Edit Email']", LocatorType.XPath); }

    public WebElement Button_SavePhone() { return findElement("//img[@class='save-icon']", LocatorType.XPath); }

    public WebElement Button_SaveEmail() { return findElement("//img[@title='save Phone']", LocatorType.XPath); }

    public WebElement Button_CancelPhone() { return findElement("//img[@class='cancel-icon']", LocatorType.XPath); }

    public WebElement Button_CancelEmail() { return findElement("//img[@title='cancel Phone']", LocatorType.XPath); }

    public WebElement TextBox_Comment() { return findElement("update-comment", LocatorType.Id); }

    public WebElement Button_Save() { return findElement("save-detail", LocatorType.Id); }

    public WebElement Button_Cancel() { return findElement("cancel-update", LocatorType.Id); }

    public WebElement Label_CustomerPhone() { return findElement("customer-phone-text", LocatorType.Id); }

    public WebElement Label_CustomerEmail() { return findElement("customer-email-text", LocatorType.Id); }

    public WebElement Label_CustomerPhoneMessage() { return findElement("phone-update-message", LocatorType.Id); }

    public WebElement Label_CustomerEmailMessage() { return findElement("email-update-message", LocatorType.Id); }

}
