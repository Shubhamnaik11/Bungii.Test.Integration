package com.bungii.ios.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class GeofencePage extends PageBase {

    public WebElement Select_ChicagoGeofence() { return findElement("//tbody[@id='NewApplicantsTBody']/tr/td[contains(text(),'Chicago')]\n", LocatorType.XPath);}

    public WebElement Button_Settings(){return findElement("btnEditSettings",LocatorType.Id);}

    public WebElement Button_SaveGeofenceSettings(){return findElement("btnCreateAttribute",LocatorType.Id);}

    public WebElement TextBox_MinimumScheduledtimeforduo(){return findElement("attributeValueEarliestScheduleTimeDuo",LocatorType.Id);}
    public WebElement TextBox_MinimumScheduledtimeforsolo(){return findElement("attributeValueEarliestScheduleTimeSolo",LocatorType.Id);}


}
