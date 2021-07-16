package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_RefundsPage extends PageBase {

    public WebElement Button_IssueRefund(boolean... ignoreException) { return findElement("btnIssueRefund", LocatorType.Id,ignoreException); }

    public WebElement DropDown_Payment() { return findElement("drpPayments", LocatorType.Id); }

    public WebElement TextBox_RefundAmount() { return findElement("TripDetails_0__RefundAmount", LocatorType.Id); }

    public WebElement TextBox_RefundPercentage() { return findElement("TripDetails_0__RefundPercentage", LocatorType.Id); }

    public WebElement TextBox_DriverEarnings() { return findElement("TripDetails_0__Driver_Earnings", LocatorType.Id); }

    public WebElement TextBox_DriverPercentage() { return findElement("TripDetails_0__Driver_EarningsPercentage", LocatorType.Id); }

    public WebElement TextBox_BungiiEarnings() { return findElement("TripDetails_0__PostRefundBusinessAmount", LocatorType.Id); }

    public WebElement TextBox_BungiiPercentage() { return findElement("TripDetails_0__PostRefundBusinessPercentage", LocatorType.Id); }

    public WebElement TextBox_BusinessNotes() { return findElement("BusinessNotes", LocatorType.Id); }

    public WebElement TextBox_Notes(boolean... ignoreException) { return findElement("TripDetails_0__Driver_Notes", LocatorType.Id,ignoreException); }

    public WebElement Button_Save() { return findElement("//button[text()='Continue']", LocatorType.XPath); }

    public WebElement Button_Reset() { return findElement("//button[text()='Reset']", LocatorType.XPath); }

    public WebElement RadioButton_PartialRefund() { return findElement("//span[text()='Partial Refund']/preceding-sibling::input", LocatorType.XPath); }

    public WebElement RadioButton_CompleteRefund() { return findElement("//span[text()='Complete Refund']/preceding-sibling::input", LocatorType.XPath); }

    public WebElement Label_Bungii() { return findElement("//div[contains(text(),'Bungii :')]/following-sibling::div", LocatorType.XPath); }

    public WebElement Label_DeliveryTotal() { return findElement("//div[contains(text(),'Delivery Total :')]/following-sibling::div", LocatorType.XPath); }

    public WebElement Label_Driver() { return findElement("//div[contains(text(),'Driver :')]/following-sibling::div", LocatorType.XPath); }

    public WebElement Header_popup(boolean... ignoreException) { return findElement("//h5[contains(text(),'Issue Refund')]", LocatorType.XPath, ignoreException); }

    public WebElement Checkbox_Confirm() { return findElement("//span[text()='Are you sure you want to proceed with refund request ?']", LocatorType.XPath); }

    public WebElement Button_Continue() { return findElement("//button[text()='Continue']", LocatorType.XPath); }

    public WebElement Button_ProcessRefund() { return findElement("//button[text()='Process Refund']", LocatorType.XPath); }

    public WebElement Label_OriginalDeliveryCharge() { return findElement("//div[text()='Original Delivery Charge:']/following-sibling::div", LocatorType.XPath); }

    public WebElement Label_CustomerRefundPercentage() { return findElement("//div[text()='Customer Refund:']/following-sibling::div[1]/div/div[1]", LocatorType.XPath); }

    public WebElement Label_CustomerRefundAmount() { return findElement("//div[text()='Customer Refund:']/following-sibling::div[1]/div/div[2]", LocatorType.XPath); }

    public WebElement Label_TotalCustomerCharge() { return findElement("//div[text()='Total Customer Charge:']/following-sibling::div", LocatorType.XPath); }

    public WebElement Label_DriverBeforeRefund() { return findElement("//h5/b[text()='Earnings']/parent::h5/parent::div/div[2]/div[contains(text(),'Driver')]/following-sibling::div[1]", LocatorType.XPath); }

    public WebElement Label_DriverAfterRefund() { return findElement("//h5/b[text()='Earnings']/parent::h5/parent::div/div[2]/div[contains(text(),'Driver')]/following-sibling::div[2]", LocatorType.XPath); }

    public WebElement Label_Driver2BeforeRefund() { return findElement("//h5/b[text()='Earnings']/parent::h5/parent::div/div[3]/div[contains(text(),'Driver')]/following-sibling::div[1]", LocatorType.XPath); }

    public WebElement Label_Driver2AfterRefund() { return findElement("//h5/b[text()='Earnings']/parent::h5/parent::div/div[3]/div[contains(text(),'Driver')]/following-sibling::div[2]", LocatorType.XPath); }

    public WebElement Label_BungiiBeforeRefund() { return findElement("//h5/b[text()='Earnings']/ancestor::div/div/div[contains(text(),'Bungii')]/following-sibling::div[1]", LocatorType.XPath); }

    public WebElement Label_BungiiAfterRefund() { return findElement("//h5/b[text()='Earnings']/ancestor::div/div/div[contains(text(),'Bungii')]/following-sibling::div[2]", LocatorType.XPath); }

    public WebElement Label_DriverName() { return findElement("//b[text()='Driver Name: ']/span", LocatorType.XPath); }

    public WebElement Label_NoteBungiiInternal() { return findElement("//b[text()='Bungii Internal:']/parent::p/parent::div/parent::div/following-sibling::div", LocatorType.XPath); }

    public WebElement Label_NoteDriver() { return findElement("//b[contains(text(),'Driver')]/parent::p/parent::div/parent::div/parent::div/div[2]", LocatorType.XPath); }

    public WebElement Label_NoteDriver2() { return findElement("//b[contains(text(),'Driver')]/parent::p/parent::div/parent::div/parent::div/div[4]", LocatorType.XPath); }

    public WebElement Header_Popup() { return findElement("//h5[text()='Issue Refund - Confirm Details']", LocatorType.XPath); }

    public WebElement Label_Success() { return findElement("//div[@id='modalSuccess']/div/div//div/p", LocatorType.XPath); }

    public WebElement Button_OK() { return findElement("//button[text()='Ok']", LocatorType.XPath); }

    public WebElement Label_CustomerRefundComplete() { return findElement("//div[text()='Customer Refund Amount: ']/following-sibling::div", LocatorType.XPath); }

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


}
