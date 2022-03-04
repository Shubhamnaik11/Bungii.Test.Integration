package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_PartnersPage extends PageBase {

   // public WebElement Menu_Partners () { return findElement("adminmenu-partners", LocatorType.Id); }
    public WebElement Menu_Partners () { return findElement("adminmenu-potentialpartners", LocatorType.Id); }

    public WebElement Assign_Partners () { return findElement("adminmenu-assigncluster", LocatorType.Id); }

    public WebElement Text_Invalid_Password_Message () { return findElement("//div[@class='form-group']/div", LocatorType.XPath); }

    public WebElement Label_Unlock_Partners () { return findElement("  //div[@id='unlock-partners']/h4",LocatorType.XPath);}
        //  public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Button_Unlock (String lockedPartner,boolean...ignoreException) { return findElement("//tr/td/p[contains(text(),'"+lockedPartner+"')]/following::td/button[contains(text(),'Unlock')]", LocatorType.XPath,ignoreException); }


}
