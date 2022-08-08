package com.bungii.ios.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class GeofencePage extends PageBase {

    public WebElement Select_ChicagoGeofence() { return findElement("//tbody[@id='NewApplicantsTBody']/tr/td[contains(text(),'Chicago')]\n", LocatorType.XPath);}

    public WebElement Button_Settings(){return findElement("btnEditSettings",LocatorType.Id);}

    public WebElement Button_SaveGeofenceSettings(){return findElement("btnCreateAttribute",LocatorType.Id);}

    public WebElement TextBox_MinimumScheduledtimeforduo(){return findElement("attributeValueEarliestScheduleTimeDuo",LocatorType.Id);}
    public WebElement TextBox_MinimumScheduledtimeforsolo(){return findElement("attributeValueEarliestScheduleTimeSolo",LocatorType.Id);}
    public WebElement Dropdown_Status() { return findElement("drpStatus", LocatorType.Id); }

    public WebElement Button_Scale() { return findElement("btnAddGeofence", LocatorType.Id); }

    public WebElement Button_Save() { return findElement("btnSave", LocatorType.Id); }
    public WebElement Text_ErrorScheduleTimeForDuo() { return findElement("attributeValueEarliestScheduleTimeDuo-error", LocatorType.Id);}
    public WebElement Text_ErrorScheduleTimeForSolo() { return findElement("attributeValueEarliestScheduleTimeSolo-error", LocatorType.Id);}

    public WebElement Button_Cancel() { return findElement("btnCancel", LocatorType.Id);}

    public WebElement Button_Edit() { return findElement("btnEdit", LocatorType.Id); }

    public WebElement Input_ReferralAmount() { return findElement("attributeValueDriverReferralBonusAmount", LocatorType.Id); }
    public WebElement Input_NoOfDeliveries() { return findElement("attributeValueDriverReferralBonusPayoutDeliveries", LocatorType.Id); }

}
