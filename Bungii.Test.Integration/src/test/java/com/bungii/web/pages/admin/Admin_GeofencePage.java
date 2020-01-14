package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_GeofencePage extends PageBase {

    public WebElement Menu_Geofences () { return findElement("adminmenu-geofences", LocatorType.Id); }

    public WebElement Header_Geofences() { return findElement("//div/h4", LocatorType.XPath); }

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

    public WebElement Checkbox_Solo() {return  findElement("//label[normalize-space()=\"1 Driver\"][1]/input[@name='attributeValueScheduledNoOfDrivers'][1]",LocatorType.XPath);}

    public WebElement Checkbox_Duo() {return  findElement("//label[normalize-space()=\"2 Drivers\"][1]/input[@name='attributeValueScheduledNoOfDrivers'][1]", LocatorType.XPath);}

    public WebElement Checkbox_OnDemand() {return findElement("//label[normalize-space()=\"1 Driver\"][1]/input[@name='attributeValueOnDemandNoOfDrivers'][1]", LocatorType.XPath);}

    public WebElement Label_SettingsError() {return  findElement("//p[text()=\"Active geofence should allow either Scheduled or On demand trip.\"]", LocatorType.XPath);}

    public WebElement Button_SaveGeofenceSettings() {return findElement("btnCreateAttribute" , LocatorType.Id);}

}
