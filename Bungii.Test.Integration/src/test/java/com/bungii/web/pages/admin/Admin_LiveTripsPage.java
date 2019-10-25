package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_LiveTripsPage extends PageBase {

    public WebElement Menu_LiveTrips () { return findElement("adminmenu-livetrips", LocatorType.Id); }

    public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

}
