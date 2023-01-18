package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_RefundsPage extends PageBase {

    public WebElement Button_IssueRefund(boolean... ignoreException) { return findElement("//button[contains(text(),'Issue Refund')]", LocatorType.XPath,ignoreException); }

    public WebElement DropDown_Payment() { return findElement("drpPayments", LocatorType.Id); }

    public WebElement TextBox_RefundAmount() { return findElement("//label[@id='dollar-icon']/div/div/input[@id='customerRefundAmtD1']", LocatorType.XPath); }

    public WebElement TextBox_RefundPercentage() { return findElement("//label[@id='percentage-icon']/div/div/input[@id='customerRefundAmtD1']", LocatorType.XPath); }

    public WebElement TextBox_DriverEarnings() { return findElement("//label[@id='dollar-icon']/div/div/input[@id='driver1Earning']", LocatorType.XPath); }

    public WebElement TextBox_DriverPercentage() { return findElement("//p[text()='Earnings']/parent::div/following-sibling::div/div/div/label/input", LocatorType.XPath); }

    public WebElement TextBox_BungiiEarnings() { return findElement("//p[text()='Bungii Earnings']/parent::div/following-sibling::div/div/div/label[@id='dollar-icon']/input", LocatorType.XPath); }

    public WebElement TextBox_BungiiPercentage() { return findElement("//p[text()='Bungii Earnings']/parent::div/following-sibling::div/div/div/label[@id='percentage-icon']/input", LocatorType.XPath); }

    public WebElement TextBox_BusinessNotes() { return findElement("notes", LocatorType.Id); }

    public WebElement TextBox_Notes(boolean... ignoreException) { return findElement("notes", LocatorType.Id,ignoreException); }

    public WebElement Button_Save() { return findElement("//button[text()='Continue']", LocatorType.XPath); }

    public WebElement Button_Reset() { return findElement("//button[text()='Reset']", LocatorType.XPath); }

    public WebElement RadioButton_PartialRefund() { return findElement("//label[text()='Partial Refund']/preceding-sibling::input", LocatorType.XPath); }

    public WebElement RadioButton_CompleteRefund() { return findElement("//label[text()='Complete Refund']/preceding-sibling::input", LocatorType.XPath); }

    public WebElement Label_Bungii() { return findElement("//p[contains(text(),'Bungii Earnings')]/parent::div/following-sibling::div/div/div[1]/label/input", LocatorType.XPath); }

    public WebElement Label_DeliveryTotal() { return findElement("//div[contains(text(),'Delivery Total :')]/following-sibling::div", LocatorType.XPath); }

    public WebElement Label_Driver() { return findElement("driver1Earning", LocatorType.Id); }

    public WebElement Header_popup(boolean... ignoreException) { return findElement("//div[contains(text(),'Issue Refund')]", LocatorType.XPath, ignoreException); }

    public WebElement Checkbox_Confirm() { return findElement("//label[text()='Are you sure you want to proceed with refund request ?']/preceding::input[1]", LocatorType.XPath); }

    public WebElement Button_Continue() { return findElement("//button[text()='Continue']", LocatorType.XPath); }

    public WebElement Button_ProcessRefund() { return findElement("//button[contains(text(),'Process Refund')]", LocatorType.XPath); }

    public WebElement Label_OriginalDeliveryCharge() { return findElement("//span[text()='Original Delivery Charge:']/following::div[1]/span", LocatorType.XPath); }

    public WebElement Label_CustomerRefundPercentage() { return findElement("//div/span[contains(text(),'Customer Refund:')]/following::div[1]/div/div[1]", LocatorType.XPath); }

    public WebElement Label_CustomerRefundAmount() { return findElement("//div/span[contains(text(),'Customer Refund:')]/following::div[1]/div/div[2]/span", LocatorType.XPath); }

    public WebElement Label_TotalCustomerCharge() { return findElement("//div/span[contains(text(),'Total Customer Charge:')]/following::div[1]/span", LocatorType.XPath); }

    public WebElement Label_DriverBeforeRefund() { return findElement("//div[contains(text(),'Before Refund')]/parent::div/div/following::div[3]/div[2]/span", LocatorType.XPath); }

    public WebElement Label_DriverAfterRefund() { return findElement("//div[contains(text(),'Before Refund')]/parent::div/div/following::div[3]/div[3]/span", LocatorType.XPath); }

    public WebElement Label_Driver2BeforeRefund() { return findElement("//h5/b[text()='Earnings']/parent::h5/parent::div/div[3]/div[contains(text(),'Driver')]/following-sibling::div[1]", LocatorType.XPath); }

    public WebElement Label_Driver2AfterRefund() { return findElement("//h5/b[text()='Earnings']/parent::h5/parent::div/div[3]/div[contains(text(),'Driver')]/following-sibling::div[2]", LocatorType.XPath); }

    public WebElement Label_BungiiBeforeRefund() { return findElement("//div/span[contains(text(),'Bungii')]/following::div[1]/span", LocatorType.XPath); }

    public WebElement Label_BungiiAfterRefund() { return findElement("//div/span[contains(text(),'Bungii')]/following::div[2]/span", LocatorType.XPath); }

    public WebElement Label_DriverName() { return findElement("//b[text()='Driver Name: ']/span", LocatorType.XPath); }

    public WebElement Label_NoteBungiiInternal() { return findElement("//b[contains(text(),'Bungii Internal:')]/following-sibling::span", LocatorType.XPath); }

    public WebElement Label_NoteDriver() { return findElement("//b[contains(text(),'Driver')]/parent::p/parent::div/parent::div/parent::div/div[2]", LocatorType.XPath); }

    public WebElement Label_NoteDriver2() { return findElement("//b[contains(text(),'Driver')]/parent::p/parent::div/parent::div/parent::div/div[4]", LocatorType.XPath); }

    public WebElement Header_Popup() { return findElement("//div[contains(text(),'Issue Refund - Confirm Details')]", LocatorType.XPath); }

    public WebElement Label_Success() { return findElement("//div[@id='modalSuccess']/div/div//div/p", LocatorType.XPath); }

    public WebElement Button_OK() { return findElement("//button[text()='Ok']", LocatorType.XPath); }

    public WebElement Label_CustomerRefundComplete() { return findElement("//div[contains(text(),'Customer Refund Amount:')]/following-sibling::div/span", LocatorType.XPath); }

    public WebElement Button_GoBack() { return findElement("//button[text()='< Go Back']", LocatorType.XPath); }

    public WebElement Button_ReattemptPayment() { return findElement("btnReattempt", LocatorType.Id); }

    public WebElement Button_Close() { return findElement("//h5[contains(text(),'Issue Refund')]/following-sibling::button[@class='close']", LocatorType.XPath); }

    public WebElement Checkbox_same() { return findElement("chkReplicateData", LocatorType.Id); }

    public WebElement TextBox_RefundAmount2() { return findElement("TripDetails_1__RefundAmount", LocatorType.Id); }

    public WebElement TextBox_RefundPercentage2() { return findElement("TripDetails_1__RefundPercentage", LocatorType.Id); }

    public WebElement TextBox_DriverEarnings2() { return findElement("TripDetails_1__Driver_Earnings", LocatorType.Id); }

    public WebElement TextBox_DriverPercentage2() { return findElement("TripDetails_1__Driver_EarningsPercentage", LocatorType.Id); }

    public WebElement TextBox_BungiiEarnings2() { return findElement("TripDetails_1__PostRefundBusinessAmount", LocatorType.Id); }

    public WebElement TextBox_BungiiPercentage2() { return findElement("TripDetails_1__PostRefundBusinessPercentage", LocatorType.Id); }

    public WebElement TextBox_Notes2() { return findElement("TripDetails_1__Driver_Notes", LocatorType.Id); }

    public WebElement Button_TransactionDetails() { return findElement("//div/h4[text()='Transaction history']/following::div/a[text()='View Details']", LocatorType.XPath); }

    public WebElement Text_SoloDriverEarnings() {return findElement("//td[contains(text(),'Driver Fixed Earnings')]/following-sibling::td/strong", LocatorType.XPath);}

}
