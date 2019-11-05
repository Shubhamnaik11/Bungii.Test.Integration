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

}
