package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_ScheduledTripsPage extends PageBase {

    public WebElement Menu_ScheduledTrips () { return findElement("adminmenu-scheduledtrips", LocatorType.Id); }

    public WebElement Menu_AllTrips () { return findElement("adminmenu-completedtrips", LocatorType.Id); }

    //public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Button_Submit () { return findElement("CustomerCancel", LocatorType.Name); }

    public WebElement Textbox_CancellationFee () { return findElement("txtCancellationFee", LocatorType.Id); }

    public WebElement Label_Drop_Off_Location () { return findElement("//p[contains(text(),'Drop Off Location:')]",LocatorType.XPath);}

    public WebElement Label_Pickup_Location () { return findElement("//p[contains(text(),'Pickup Location:')]",LocatorType.XPath);}

    public WebElement Button_Edit_Drop_Off_Address () { return findElement("//img[@title='Edit drop off location']",LocatorType.XPath);}

    public WebElement Button_Edit_Pickup_Address () { return findElement("//img[@title='Edit pickup location']",LocatorType.XPath);}


    //public WebElement Admin_Dropdown_ServiceLevel(String serviceLevel) { return findElement("//li/div/div/span[@class='service-title' and @data-name='"+serviceLevel+"']",LocatorType.XPath);}
    public WebElement Admin_Dropdown_ServiceLevel() { return findElement("ddServiceLevelOption",LocatorType.Id);}

    public WebElement Link_Grid_First_Row() { return findElement("//tr[@id='row1']/td[4]/a",LocatorType.XPath);}
    public WebElement Textbox_Drop_Off_Location () { return findElement("PickupDetails_DestinationAddress",LocatorType.Id);}
    public WebElement Textbox_Pickup_Location () { return findElement("PickupDetails_PickupOriginAddress",LocatorType.Id);}

    //public WebElement FirstAddressDropdownResult () { return findElement("//div[@id='divPlacesResult']/div[1]",LocatorType.XPath);}

    public WebElement DropdownResult (String address) { return findElement(String.format("//div[@id='divPlacesResult']/div[contains(.,'%s')]",address),LocatorType.XPath);}

    public WebElement DropdownPickupResult (String address) { return findElement(String.format("//div[@id='divPickupPlacesResult']/div[contains(.,'%s')]",address),LocatorType.XPath);}

    public WebElement DropOff_Address() { return findElement("//label[@id='lblDestinationAddress']",LocatorType.XPath);}

    public WebElement Pickup_Address() { return findElement("//label[@id='lblPickupAddress']",LocatorType.XPath);}

    public WebElement Textbox_CancellationComment () { return findElement("txtCustomerCancellationComments", LocatorType.Id); }

    public WebElement Dropdown_CancellationReason () { return findElement("txtCancellationRemark", LocatorType.Id); }

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

    public WebElement Checkbox_NonControlDriver () { return findElement( "//div[@id='tripDriverDetails']/div[1]/label[1]/input", LocatorType.XPath);}

    public WebElement Checkbox_ControlDriver () { return findElement( "//div[@id='tripDriverDetails']/div[2]/label[1]/input", LocatorType.XPath);}

    public WebElement Checkbox_ControlDriverEdit () { return findElement( "//*[@id='editTripDrivers']/tbody/tr[1]/td//input", LocatorType.XPath);}

    public WebElement Checkbox_NonControlDriverEdit () { return findElement( "//*[@id='editTripDrivers']/tbody/tr[2]/td//input", LocatorType.XPath);}

    public WebElement Button_RemoveDriversEdit () { return findElement("btnRemoveDriver", LocatorType.Id); }//Richa

    public WebElement Label_DriverRemovalSuccessMessage () { return findElement( "//p/i[text()='Driver(s) removed successfully']" , LocatorType.XPath); }

    public WebElement Button_Close () { return findElement("//button[@class='close']", LocatorType.XPath);}

    public WebElement Estimated_Distance_Cost () { return findElement("",LocatorType.XPath);}

    public WebElement Textbox_Search () { return findElement("SearchCriteria", LocatorType.Id); }
    public WebElement Button_Search(){return  findElement("btnSearch",LocatorType.Id);}

    public WebElement List_ViewDeliveries(){return  findElement("//td/div[@class='dropdown open']/ul/li/p[contains(text(),'View Delivery Details')]",LocatorType.XPath);}

}
