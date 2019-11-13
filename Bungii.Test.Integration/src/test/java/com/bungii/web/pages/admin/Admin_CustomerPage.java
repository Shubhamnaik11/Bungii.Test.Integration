package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_CustomerPage extends PageBase {

    public WebElement Menu_Customers () { return findElement("adminmenu-customers", LocatorType.Id); }

    public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Dropdown_TimeFrame () { return findElement("SearchForPeriod", LocatorType.Id); }

    public WebElement TextBox_SearchCustomer() { return findElement("SearchCriteria", LocatorType.Id); }

}
