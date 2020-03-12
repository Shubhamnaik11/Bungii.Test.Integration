package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_GeofencePage extends PageBase {

    public WebElement Menu_Geofences () { return findElement("adminmenu-geofences", LocatorType.Id); }

    public WebElement Menu_Attributes(){return findElement("//a[contains(text(),'Attributes')]", LocatorType.XPath);}

    public WebElement Header_Geofences() { return findElement("//div/h4", LocatorType.XPath); }

    public WebElement Header_Attributes() { return findElement("//div/h4", LocatorType.XPath); }

    public WebElement TextBox_Primary() { return findElement("PolylineForServiceArea", LocatorType.Id); }

    public WebElement TextBox_Secondary() { return findElement("PolylineForSecondaryGeofence", LocatorType.Id); }

    public WebElement TextBox_GeoName() { return findElement("Name", LocatorType.Id); }

    public WebElement Label_GeoName(boolean...ignoreException) { return findElement("GeofenceSettings_0__Name", LocatorType.Id,ignoreException); }

    public WebElement Dropdown_Timezone() { return findElement("drpTimeZone", LocatorType.Id); }

    public WebElement Dropdown_Status() { return findElement("drpStatus", LocatorType.Id); }

    public WebElement Button_Scale() { return findElement("btnAddGeofence", LocatorType.Id); }

    public WebElement Button_Save() { return findElement("btnSave", LocatorType.Id); }

    public WebElement Button_Cancel() { return findElement("btnCancel", LocatorType.Id); }

    public WebElement Label_GeneralErrorContainer() { return findElement("error-summary", LocatorType.Id); }

    public WebElement Label_PrimaryErrorContainer() { return findElement("PolylineForServiceArea-error", LocatorType.Id); }

    public WebElement Label_SecondaryErrorContainer() { return findElement("PolylineForSecondaryGeofence-error", LocatorType.Id); }

    public WebElement Label_NameErrorContainer() { return findElement("Name-error", LocatorType.Id); }

    public WebElement Button_Edit() { return findElement("btnEdit", LocatorType.Id); }

    public WebElement Button_Settings() { return findElement("btnEditSettings", LocatorType.Id); }


    public WebElement Label_CustomerFAQLink() { return findElement("//td[contains(text(),'Customer FAQ link')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_DriverFAQLink() { return findElement("//td[contains(text(),'Driver FAQ link')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_MinTimeForDuoTrip() { return findElement("//td[contains(text(),'Minimum scheduled time for Duo trip')]//following-sibling::td[2]/label", LocatorType.XPath); }

    public WebElement Label_MinTimeForSoloTrip() { return findElement("//td[contains(text(),'Minimum scheduled time for Solo trip')]//following-sibling::td[2]/label", LocatorType.XPath); }

    public WebElement Label_MinTripCost() { return findElement("//td[contains(text(),'Minimum trip cost')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_SurveyEmailLink() { return findElement("//td[contains(text(),'Survey email link')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_TripCostPerMile() { return findElement("//td[contains(text(),'Trip cost per mile')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_TripCostPerMinute() { return findElement("//td[contains(text(),'Trip cost per minute')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Checkbox_Solo() {return  findElement("//label[normalize-space()=\"1 Driver\"][1]/input[@name='attributeValueScheduledNoOfDrivers'][1]",LocatorType.XPath);}

    public WebElement Checkbox_Duo() {return  findElement("//label[normalize-space()=\"2 Drivers\"][1]/input[@name='attributeValueScheduledNoOfDrivers'][1]", LocatorType.XPath);}

    public WebElement Checkbox_OnDemand() {return findElement("//label[normalize-space()=\"1 Driver\"][1]/input[@name='attributeValueOnDemandNoOfDrivers'][1]", LocatorType.XPath);}

    public WebElement Label_SettingsError() {return  findElement("//p[text()=\"Active geofence should allow either Scheduled or On demand trip.\"]", LocatorType.XPath);}

    public WebElement Button_SaveGeofenceSettings() {return findElement("btnCreateAttribute" , LocatorType.Id);}

    public WebElement Select_ChicagoGeofence() { return findElement("//tbody[@id='NewApplicantsTBody']/tr/td[contains(text(),'Chicago')]", LocatorType.XPath);}

    public WebElement TextBox_MinimumScheduledtimeforduo(){return findElement("attributeValueEarliestScheduleTimeDuo",LocatorType.Id);}
    public WebElement TextBox_MinimumScheduledtimeforsolo(){return findElement("attributeValueEarliestScheduleTimeSolo",LocatorType.Id);}


    public WebElement Text_ErrorScheduleTimeForDuo() { return findElement("attributeValueEarliestScheduleTimeDuo-error", LocatorType.Id);}
    public WebElement Text_ErrorScheduleTimeForSolo() { return findElement("attributeValueEarliestScheduleTimeSolo-error", LocatorType.Id);}


}
