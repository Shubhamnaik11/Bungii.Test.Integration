package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;



public class Admin_EditScheduledBungiiPage extends PageBase {

    public WebElement Changed_Date() { return findElement("//td[@class=' ui-datepicker-days-cell-over  ui-datepicker-current-day ui-datepicker-today']/following::td[2]/a", LocatorType.XPath); }

    public WebElement Changed_Time() { return findElement("//tr[@id='row1']/td[5]/a", LocatorType.XPath); }

    public WebElement Dropdown_ScheduledDate_Time() { return findElement("//li[@class='ui-timepicker-am ui-timepicker-selected']/following-sibling::li[3]", LocatorType.XPath); }

    public WebElement Dropdown_Result (boolean ...ignoreException) { return findElement("ddEditDeliveryRemark",LocatorType.Id, ignoreException); }

    public WebElement Dropdown_Driver_Result (String driverName) { return findElement(String.format("//div[@id='divDriversResult']/div[contains(.,'%s')]",driverName),LocatorType.XPath);}

    public WebElement DatePicker_ScheduledDate () { return findElement("PickupDetails_ScheduledDate", LocatorType.Id); }

    public WebElement TimePicker_Time () { return findElement("PickupDetails_ScheduledTime", LocatorType.Id); }

    public WebElement List_TimeFrame (String time) { return findElement("//div/ul/li[text()='"+time+"']", LocatorType.XPath); }

    public WebElement Link_RemoveDriver() { return findElement("btnRemoveDriver", LocatorType.Id); }

    public WebElement TextBox_DriverSearch() { return findElement("txtDriverSearch", LocatorType.Id); }

    public WebElement Button_Verify() { return findElement("//button[text()='VERIFY']", LocatorType.XPath); }

    public WebElement Button_Save() { return findElement("//button[text()='SAVE']", LocatorType.XPath); }

    public WebElement Button_Undo() { return findElement("//button[@onclick='UndoTripChanges()']", LocatorType.Id); }

    public WebElement Checkbox_Driver (String driverName) { return findElement("//td[text()='"+driverName+"']/ancestor::tr/td/label/input", LocatorType.XPath); }

    public WebElement Label_VerifyError() { return findElement("verify-error", LocatorType.Id); }

    public WebElement Label_VerifiedMessage() { return findElement("verified-message", LocatorType.Id); }

    public WebElement Label_SuccessMessage() { return findElement("success-message", LocatorType.Id); }

    public WebElement Label_InfoMessage() { return findElement("info-message", LocatorType.Id); }

    public WebElement RadioButton_EditTripDetails() { return findElement("//span[text()='Edit Delivery Details']/preceding-sibling::span", LocatorType.XPath); }

    public WebElement List_DriverSearchResult (String driverName) { return findElement("//div[@id='divDriversResult']/div[contains(.,'"+driverName+"')]", LocatorType.XPath); }

    public WebElement TickMarkDate () { return findElement("//i[@class='fa fa-check dateValidity text-green-alt success-icon']", LocatorType.XPath); }

    public WebElement TickMarkDriver (String driverName) { return findElement("//td[text()='"+driverName+"']/ancestor::tr/td/i[@class='fa fa-check text-green-alt']", LocatorType.XPath); }

    public WebElement  Button_Edit() {return findElement("//p[@id='btnEdit']",LocatorType.XPath);}

    public WebElement  Button_Delivery_Details() {return findElement("//p[@class='clickable-row']",LocatorType.XPath);}


    public WebElement Button_Close() { return findElement("//button[@class='close']/span",LocatorType.XPath);}

    public WebElement Text_Estimated_Price() { return findElement("//td[text()='Estimated Charge']/following-sibling::td",LocatorType.XPath);}

    public WebElement Text_Pickup_Note() { return findElement("//td[text()='Pickup Note']/following-sibling::td",LocatorType.XPath);}

    public WebElement Text_Additional_Note(boolean ...ignoreException) { return findElement("PickupDetails_PickupNote",LocatorType.Id,ignoreException);}

    public WebElement Text_Additional_Instructions() { return findElement("PickupDetails_SpecialInstructions",LocatorType.Id);}

    public WebElement RadioButton_Solo() { return findElement("//input[@value='Solo']", LocatorType.XPath); }
    public WebElement RadioButton_Duo() { return findElement("//input[@value='Duo']", LocatorType.XPath); }

    public WebElement Label_Driver1MessageOnResearch() { return findElement("//div[@id='tripDriverDetails']/div/span[contains(.,'Driver 1: Bungii driver is being searched')]", LocatorType.XPath); }

    public WebElement Label_Driver2MessageOnResearch(boolean...ignoreException) { return findElement("//div[@id='tripDriverDetails']/div/span[contains(.,'Driver 2: Bungii driver is being searched')]", LocatorType.XPath,ignoreException); }

    public WebElement Label_Driver1MessageOnEdit() { return findElement("//tr/td[contains(.,'Driver 1: Add driver below or Bungii driver search will continue')]", LocatorType.XPath); }

    public WebElement Label_Driver2MessageOnEdit(boolean...ignoreException) { return findElement("//tr/td[contains(.,'Driver 2: Add driver below or Bungii driver search will continue')]", LocatorType.XPath,ignoreException); }

    public WebElement Label_DeliveryTypeOnResearch() { return findElement("//div[@class='tripDrivers row']/p[contains(text(),'Type')]/following-sibling::p[1]", LocatorType.XPath); }

    public WebElement Label_DeliveryTypeOnCancel() { return findElement("//div[@class='cancelFees row']/p[contains(text(),'Type')]/following-sibling::p[1]", LocatorType.XPath); }

    public WebElement Label_Driver1NameOnResearch() { return findElement("//div[@id='tripDriverDetails']/div[1]/span[1]", LocatorType.XPath); }

    public WebElement Label_Driver2NameOnResearch(boolean...ignoreException) { return findElement("//div[@id='tripDriverDetails']/div[2]/span[1]", LocatorType.XPath,ignoreException); }

    public WebElement Label_Driver1NameOnEdit() { return findElement("//table[@id='editTripDrivers']/tbody/tr[1]/td/table/tbody/tr/td[2]", LocatorType.XPath); }

    public WebElement Label_Driver2NameOnEdit(boolean...ignoreException) { return findElement("//table[@id='editTripDrivers']/tbody/tr[2]/td/table/tbody/tr/td[2]", LocatorType.XPath,ignoreException); }

    public WebElement Label_ErrorMessage() { return findElement("verify-error", LocatorType.Id); }

    public WebElement Label_AdditionalNotes() { return findElement("//div/p[text() =\"Special Instructions:\"]", LocatorType.XPath); }
}
