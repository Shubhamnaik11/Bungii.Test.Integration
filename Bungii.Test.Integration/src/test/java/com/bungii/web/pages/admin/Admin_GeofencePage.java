package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public WebElement Dropdown_Region() { return findElement("drpRegion", LocatorType.Id); }

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
    public WebElement Button_Next() {return  findElement("//a/span[text()='Next']", LocatorType.XPath);}

    public WebElement Checkbox_OnDemand() {return findElement("//label[normalize-space()=\"1 Driver\"][1]/input[@name='attributeValueOnDemandNoOfDrivers'][1]", LocatorType.XPath);}

    public WebElement Label_SettingsError() {return  findElement("//p[text()=\"Active geofence should allow either Scheduled or On demand trip.\"]", LocatorType.XPath);}

    public WebElement Button_SaveGeofenceSettings() {return findElement("btnCreateAttribute" , LocatorType.Id);}

    public WebElement Select_ChicagoGeofence() { return findElement("//tbody[@id='NewApplicantsTBody']/tr/td[contains(text(),'Chicago')]", LocatorType.XPath);}

    public WebElement TextBox_MinimumScheduledtimeforduo(){return findElement("attributeValueEarliestScheduleTimeDuo",LocatorType.Id);}
    public WebElement TextBox_MinimumScheduledtimeforsolo(){return findElement("attributeValueEarliestScheduleTimeSolo",LocatorType.Id);}


    public WebElement Text_ErrorScheduleTimeForDuo() { return findElement("attributeValueEarliestScheduleTimeDuo-error", LocatorType.Id);}
    public WebElement Text_ErrorScheduleTimeForSolo() { return findElement("attributeValueEarliestScheduleTimeSolo-error", LocatorType.Id);}

    public WebElement Select_KansasGeofence() { return findElement("//tbody[@id='NewApplicantsTBody']/tr/td[contains(text(),'Kansas')]\n", LocatorType.XPath);}

    public WebElement Select_GoaGeofence() { return findElement("//tbody[@id='NewApplicantsTBody']/tr/td[contains(text(),'Goa')]\n", LocatorType.XPath);}

    public WebElement TextBox_Bunggi_Cut_Rate() { return findElement("attributeValueBungiiCutPerDelivery",LocatorType.Id);}
    public WebElement TextBox_Driver_Cut_Rate() { return findElement("attributeValueDiverCutPerDelivery",LocatorType.Id);}

    public WebElement TextError_BunggiCut() { return findElement("//label[@id='attributeValueBungiiCutPerDelivery-error']",LocatorType.XPath);}
    public WebElement TextError_General() { return findElement("//p[contains(text(),'Oops! It looks like you missed something. Please fill out all fields before proceeding.')]",LocatorType.XPath);}

    public WebElement Checkbox_Active_Geofences() { return findElement("activeGeofenceOnly",LocatorType.Id);}

    public WebElement Row_geofenceList(String Name,String Status,String Timezone) {return  findElement(String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]",Name,Status,Timezone), LocatorType.XPath);}

    public WebElement List_Geofence() {return findElement("dropdownMenuButton" , LocatorType.Id);}
    public WebElement TextBox_SearchGeofence() {return findElement("myInput" , LocatorType.Id);}
    public WebElement Button_ApplyGeofence() {return findElement("btnApply" , LocatorType.Id);}
    public WebElement Button_Clear() {return findElement("clearAll" , LocatorType.Id);}

    public WebElement Checkbox_Geofence(String geofence , boolean... ignoreException) {return findElement(String.format("//span[contains(.,'%s')]/preceding-sibling::span/label/input",geofence) , LocatorType.XPath, ignoreException);}
    public WebElement Checkbox_GeofenceLabel(String geofence , boolean... ignoreException) {return findElement(String.format("//span[contains(.,'%s')]",geofence) , LocatorType.XPath, ignoreException);}

    public WebElement Button_DownloadZipCodes() {return findElement("btnDownloadZipcodes" , LocatorType.Id);}
    public List<WebElement> List_RowCount() {return findElements("//tbody[@id='NewApplicantsTBody']/tr[@class='clickable-row']/td[2]" , LocatorType.XPath);}
    public WebElement List_ActiveGeofence(int i) {return findElement("//tbody[@id='NewApplicantsTBody']/tr["+i+"]/td[2]" , LocatorType.XPath);}

    public WebElement Row_GeofenceName(String geoName) { return findElement("//td[contains(text(),'"+geoName+"')]",LocatorType.XPath);}
    public WebElement Text_GeoHistory() {return findElement("//h4[contains(text(),'Geo-History')]",LocatorType.XPath);}
    public List<WebElement> Rows_GeoHistoryLogs() { return findElements("//tbody[@id='GeofenceHistoryTBody']/tr[@class='geo-tr']",LocatorType.XPath);}
    public WebElement Text_SrNo() { return findElement("//th[contains(text(),'Sr.No.')]",LocatorType.XPath);}
    public WebElement Text_ModifiedDate() { return findElement("//th[contains(text(),'Modified Date')]",LocatorType.XPath);}
    public WebElement Text_ModifiedBy() { return findElement("//th[contains(text(),'Modified By')]",LocatorType.XPath);}
    public WebElement Text_Phone() { return findElement("//th[contains(text(),'Phone')]",LocatorType.XPath);}
    public WebElement Text_Changes() { return findElement("//th[contains(text(),'Changes')]",LocatorType.XPath);}
    public WebElement Button_GeofenceCancel() { return findElement("//div[@id='btnCancel']",LocatorType.XPath);}
    public WebElement Link_Changes() { return findElement("//tbody/tr[@id='historyID0']/td[6]/a",LocatorType.XPath);}
    public WebElement Text_Fields() { return findElement("//div[@id='historyLog_historyID0']/table/thead/tr/th[contains(text(),'Fields')]",LocatorType.XPath);}
    public WebElement Text_OldValue() { return findElement("//div[@id='historyLog_historyID0']/table/thead/tr/th[contains(text(),'Old Value')]",LocatorType.XPath);}
    public WebElement Text_NewValue() { return findElement("//div[@id='historyLog_historyID0']/table/thead/tr/th[contains(text(),'New Value')]",LocatorType.XPath);}

    public WebElement Value_ModifiedDate() { return findElement("//tbody/tr[@id='historyID0']/td[3]",LocatorType.XPath);}
    public WebElement Value_ModifiedBy() { return findElement("//tbody/tr[@id='historyID0']/td[4]",LocatorType.XPath);}
    public WebElement Value_Phone() { return findElement("//tbody/tr[@id='historyID0']/td[5]",LocatorType.XPath);}
    public WebElement Value_Changes() { return findElement("//tbody/tr[@id='historyID0']/td[6]/a",LocatorType.XPath);}

    public WebElement Text_GeofenceHighlighted() { return findElement("//ul/li/div/span/mark", LocatorType.XPath);}

    public List<WebElement> List_GeofenceRegions() { return findElements("//div[@id=\"tree\"]/ul/li/div/span[4]", LocatorType.XPath);}

}
