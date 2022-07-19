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

    public WebElement Text_Driver_Est_Earnings_Customer_Delivery() { return findElement("//td[text()='Driver Earnings']/following::td[1]/strong",LocatorType.XPath);}

    public WebElement Text_Driver_Est_Eranings() { return findElement("//td[text()=' Driver Fixed Earnings']/following::td[1]/strong",LocatorType.XPath);}

    public WebElement Text_Driver_Est_Eranings_Fnd(String weight) { return findElement("//td[text()=' Driver Fixed Earnings - Pallet 1("+weight+" lbs)']/following::td[1]/strong",LocatorType.XPath);}

    public WebElement Icon_Price_Override(){ return findElement("//a[@class='tooltip-overlay price-override']/img",LocatorType.XPath);}

    public WebElement Dropdown_Driver_Result (String driverName) { return findElement(String.format("//div[@id='divDriversResult']/div[contains(.,'%s')]",driverName),LocatorType.XPath);}

    public WebElement Text_Partner_Delivery_Cost() { return findElement("//h2[contains(text(),'Delivery Cost')]/span/strong",LocatorType.XPath);}

    public WebElement Button_Ok() { return findElement("//div[@id='btnOk']",LocatorType.XPath);}

    public WebElement Button_Price_Override(boolean...ignoreException) { return findElement("//td/a[@id='btnAdminOverride']",LocatorType.XPath,ignoreException);}

    public WebElement Textbox_Override_Customer_Price() { return findElement("delivaryCost",LocatorType.Id);}

    public WebElement Textbox_Override_Driver_Cut() { return findElement("//div/input[@id='driverOneShare']",LocatorType.XPath);}

    public WebElement Textbox_Override_Driver_Cut_Duo() { return findElement("//div/input[@id='driverTwoShare']",LocatorType.XPath);}

    public WebElement Dropdown_Reason_Override_Customer_Price() { return findElement("delivaryCostReason",LocatorType.Id);}

    public WebElement Dropdown_Reason_Override_Driver_Cut() { return findElement("//div/select[@id='driverEarningsReason']",LocatorType.XPath);}

    public WebElement Label_Price_Override() { return findElement("exampleModalLongTitle",LocatorType.Id);}

    public WebElement Button_Save() { return findElement("saveAdminOverride",LocatorType.Id);}

    public WebElement Button_Success_Ok() { return findElement("//div[@class='modal-footer']/button[text()='Ok']",LocatorType.XPath);}

    public WebElement Button_Override_Cancel() { return findElement("//input[@value='Cancel']",LocatorType.XPath);}

    public WebElement Text_Driver_Cut_Error() { return findElement("errorMessageFields",LocatorType.Id);}

    public WebElement Text_DriverOneEarnings() { return findElement("//tr[19]/td/following-sibling::td/strong",LocatorType.XPath);}
    public WebElement Text_DriverTwoEarnings() { return findElement("//tr[20]/td/following-sibling::td/strong",LocatorType.XPath);}


}
