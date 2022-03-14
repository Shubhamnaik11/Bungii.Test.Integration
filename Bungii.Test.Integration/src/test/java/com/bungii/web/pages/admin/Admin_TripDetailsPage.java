package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_TripDetailsPage extends PageBase {

    public WebElement Link_ManuallyEndBungii () { return findElement("btnEndPickup", LocatorType.Id); }

    public WebElement Button_CalculateCost () { return findElement("btnCost", LocatorType.Id); }

    public WebElement Button_Cancel () { return findElement("btnCancel", LocatorType.Id); }

    public WebElement Button_Confirm () { return findElement("btnConfirm", LocatorType.Id); }

    public WebElement Textbox_PickupEndDate () { return findElement("pickupEndDate", LocatorType.Id); }

    public WebElement Textbox_PickupEndTime () { return findElement("pickupEndTime", LocatorType.Id); }

    public WebElement Dropdown_ddlpickupEndTime () { return findElement("ddlpickupEndTime", LocatorType.Id); }

    public WebElement Label_TripDetails (String label) { return findElement("//td[contains(text(),'"+ label+"')]/following-sibling::td/strong", LocatorType.XPath); }

    public WebElement Dropdown_Drivers () { return findElement("Drivers", LocatorType.Id); }

    public WebElement Label_ScheduledTime(String xpath_scheduled_time) { return findElement( xpath_scheduled_time, LocatorType.XPath);}

    public WebElement Schedule_Date_Row() {return findElement("//td[3]/a",LocatorType.XPath);}

    public WebElement Text_DropOff_Location() { return findElement("//td[text()='Drop Off Location']/following::td[1]/strong",LocatorType.XPath);}

    public WebElement Text_Pickup_Location() { return findElement("//td[text()='Pickup Location']/following::td[1]",LocatorType.XPath);}

    public WebElement Text_Service_Level() { return findElement("//td[contains(text(),'Service Level')]/following-sibling::td/strong",LocatorType.XPath);}

    public WebElement Text_Estimated_Charge() { return findElement("//td[contains(text(),'Estimated Charge')]/following-sibling::td/strong",LocatorType.XPath);}

    public WebElement Text_Driver_Est_Eranings() { return findElement("//td[text()='Driver Earnings']/following::td[1]",LocatorType.XPath);}

    public WebElement Button_Ok() { return findElement("//div[@id='btnOk']",LocatorType.XPath);}
}
