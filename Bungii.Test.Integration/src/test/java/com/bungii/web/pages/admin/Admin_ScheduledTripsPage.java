package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_ScheduledTripsPage extends PageBase {

    public WebElement Menu_ScheduledTrips () { return findElement("adminmenu-scheduledtrips", LocatorType.Id); }

    public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Button_Submit () { return findElement("CustomerCancel", LocatorType.Name); }

    public WebElement Textbox_CancellationFee () { return findElement("txtCancellationFee", LocatorType.Id); }

    public WebElement Textbox_CancellationComment () { return findElement("txtCustomerCancellationComments", LocatorType.Id); }
    //changed by Richa
    //public WebElement RadioButton_CancelBungii () { return findElement("//span[text()='Cancel entire Bungii and notify driver(s)']/preceding-sibling::input", LocatorType.XPath); }
    public WebElement RadioButton_CancelBungii () { return findElement("//span[text()='Cancel entire Bungii and notify driver(s)']/preceding-sibling::span", LocatorType.XPath); }

    public WebElement RadioButton_RemoveDriver () { return findElement("//span[text()='Remove driver(s) and re-search']/preceding-sibling::span", LocatorType.XPath); }//richa

    //public WebElement Button_RemoveDrivers () { return findElement("(//input[@value='Remove Driver(s)'])[2]", LocatorType.XPath); }
    public WebElement Button_RemoveDrivers () { return findElement("//div[@id='tripDriverDetails']//strong[contains(text(),'Remove')]", LocatorType.XPath); }//Richa

    //public WebElement Button_Research () { return findElement("(//input[@value='Re-search'])[2]", LocatorType.XPath); }
    public WebElement Button_Research () { return findElement("//div[@id='tripDriverDetails']//strong[contains(text(),'Re-search a driver')]", LocatorType.XPath); }//Richa

    //public WebElement Checkbox_driver (String driver) { return findElement("(//div/label[contains(.,'"+driver+"')])[2]/input[1]", LocatorType.XPath); }
    public WebElement Checkbox_driver () { return findElement("//div[@id='tripDriverDetails']//span[@class='checkmark'][1]", LocatorType.XPath); }//richa

    public WebElement Label_SuccessMessage () { return findElement("SuccessMessage", LocatorType.Id); }
    //changed by Richa
    public WebElement Label_CancelSuccessMessage () { return findElement("//p[@id='cancel-success-message']", LocatorType.XPath); }

    public WebElement Dropdown_SearchForPeriod () { return findElement("SearchForPeriod", LocatorType.Name); }
}
