package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_PartnersPage extends PageBase {

   // public WebElement Menu_Partners () { return findElement("adminmenu-partners", LocatorType.Id); }
    public WebElement Menu_Partners () { return findElement("adminmenu-potentialpartners", LocatorType.Id); }

    public WebElement Assign_Partners () { return findElement("adminmenu-assigncluster", LocatorType.Id); }

  //  public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

}
