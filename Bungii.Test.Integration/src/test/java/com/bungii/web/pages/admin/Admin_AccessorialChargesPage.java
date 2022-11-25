package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_AccessorialChargesPage extends PageBase {

    public WebElement TextBox_AccessorialAmount() { return findElement("formHorizontalAmount", LocatorType.Id); }
    public WebElement TextBox_AccessorialDriver1Cut() { return findElement("formHorizontalDriverShare", LocatorType.Id); }
    public WebElement TextBox_Comment() { return findElement("Comment", LocatorType.Name); }
    public WebElement DropDown_AccessorialFeeType() { return findElement("formHorizontalFeeType", LocatorType.Id); }
    public WebElement Button_Save() { return findElement("//button[text()='Save']", LocatorType.XPath); }
    public WebElement Button_Confirm() { return findElement("//button[text()='Confirm' and @class='save-btn btn btn-primary']", LocatorType.XPath); }
    public WebElement Header_Section(boolean...ignoreException) { return findElement("//h4[text()='Accessorial Charges']", LocatorType.XPath,ignoreException); }
    public WebElement Error_AccessoricalCharges() { return findElement("accessorial-fee-error", LocatorType.Name); }
    public WebElement Message_Mandatory() { return findElement("//div[@id='accessorial-charge']/label[2]", LocatorType.XPath); }
   public WebElement GridRow(String feeType) { return findElement(String.format("//td[text()='%s']/following-sibling::td",feeType), LocatorType.XPath); }
   public WebElement ExcessWaitTime() { return findElement("//div/h4/a[contains(text(),\"Excess Wait Time\")]", LocatorType.XPath); }
    public WebElement GridRowTotal(String total) { return findElement(String.format("//td/strong[text()='%s']/parent::td/following-sibling::td",total), LocatorType.XPath); }
    public WebElement Error_AccessorialFeeAmount() { return findElement("fee-amount-error", LocatorType.Id); }
    public WebElement Error_AccessorialFeeDriverCut() { return findElement("fee-type-driverOneShare-error", LocatorType.Id); }
    public WebElement Error_AccessorialFeeType() { return findElement("fee-type-error", LocatorType.Id); }
    public WebElement Error_AccessorialFeeComment() { return findElement("fee-comment-error", LocatorType.Id); }
    public WebElement Text_DiffAccessorial(int Index ) { return findElement(String.format("//div[%d]/div/h4/a",Index) ,LocatorType.XPath);}
    public WebElement Text_DriverCut(String AmountType) { return findElement(String.format("//div/div[@id = 'collapse-%s']/div/div/div",AmountType) ,LocatorType.XPath);}

}
