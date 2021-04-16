package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_EditScheduledBungiiPage extends PageBase {

    public WebElement DatePicker_ScheduledDate () { return findElement("PickupDetails_ScheduledDate", LocatorType.Id); }

    public WebElement TimePicker_Time () { return findElement("PickupDetails_ScheduledTime", LocatorType.Id); }

    public WebElement List_TimeFrame (String time) { return findElement("//div/ul/li/a[text()='"+time+"']", LocatorType.XPath); }

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
}
