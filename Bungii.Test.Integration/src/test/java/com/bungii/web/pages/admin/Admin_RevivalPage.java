package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_RevivalPage extends PageBase {

    public WebElement Button_Confirm() { return findElement("//button[text()='Confirm']", LocatorType.XPath); }

    public WebElement Button_Cancel() { return findElement("//button[text()='Cancel']", LocatorType.XPath); }

    public WebElement Label_HeaderPopup() { return findElement("//p[text()='Are you sure you want to revive the trip?']", LocatorType.XPath); }

    public WebElement Label_PickupOrigin() { return findElement("revive-pickup-customer-type", LocatorType.Id); }

    public WebElement Label_PickupId() { return findElement("revive-pickup-id", LocatorType.Id); }

    public WebElement Label_PickupCustomer() { return findElement("revive-pickup-customer", LocatorType.Id); }

    public WebElement Label_PickupPartnerPortal() { return findElement("revive-pickup-partner-portal", LocatorType.Id); }

}
