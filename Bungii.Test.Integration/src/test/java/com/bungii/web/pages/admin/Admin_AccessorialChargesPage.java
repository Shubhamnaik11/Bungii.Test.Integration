package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_AccessorialChargesPage extends PageBase {

    public WebElement TextBox_AccessorialAmount() { return findElement("AccessorialFeeAmount", LocatorType.Name); }
    public WebElement TextBox_AccessorialDriver1Cut() { return findElement("fee-type-driverOneShare", LocatorType.Name); }
    public WebElement TextBox_Comment() { return findElement("Comment", LocatorType.Name); }
    public WebElement DropDown_AccessorialFeeType() { return findElement("AccessorialFeeType", LocatorType.Name); }
    public WebElement Button_Save() { return findElement("//button[text()='Save']", LocatorType.XPath); }
    public WebElement Button_Confirm() { return findElement("//button[text()='Confirm' and @class='btn btn-primary']", LocatorType.XPath); }
    public WebElement Header_Section() { return findElement("//h4[text()='Accessorial Charges']", LocatorType.XPath); }
    public WebElement Error_AccessoricalCharges() { return findElement("accessorial-fee-error", LocatorType.Name); }
    public WebElement Message_Mandatory() { return findElement("//div[@id='accessorial-charge']/label[2]", LocatorType.XPath); }
    public WebElement GridRow(String feeType) { return findElement(String.format("//td[text()='%s']/following-sibling::td",feeType), LocatorType.XPath); }
    public WebElement GridRowTotal(String total) { return findElement(String.format("//td/strong[text()='%s']/parent::td/following-sibling::td",total), LocatorType.XPath); }

    public WebElement Error_AccessorialFeeAmount() { return findElement("fee-amount-error", LocatorType.Id); }
    public WebElement Error_AccessorialFeeDriverCut() { return findElement("fee-type-driverOneShare-error", LocatorType.Id); }
    public WebElement Error_AccessorialFeeType() { return findElement("fee-type-error", LocatorType.Id); }
    public WebElement Error_AccessorialFeeComment() { return findElement("fee-comment-error", LocatorType.Id); }



}
