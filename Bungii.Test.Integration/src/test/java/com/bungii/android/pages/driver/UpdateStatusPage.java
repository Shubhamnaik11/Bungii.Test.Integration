package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class UpdateStatusPage extends PageBase {
    public WebElement Text_WaitingForDriver(boolean ignoreException){return findElement("com.bungii.driver:id/progress_message",LocatorType.Id, ignoreException);}
    public WebElement Activity_loader(boolean ignoreException){return findElement("com.bungii.driver:id/progress_bar",LocatorType.Id, ignoreException);}
    public WebElement Text_CustomerNameOnDriverApp( ){return findElement("//android.view.ViewGroup/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[2]",LocatorType.XPath);}
    public WebElement TextBox_SignedByField( ){return findElement("com.bungii.driver:id/activity_signature_et_signed_by",LocatorType.Id);}
    public WebElement TextBox_Signature( ){return findElement("com.bungii.driver:id/activity_signature_signature_pad",LocatorType.Id);}
    public WebElement Button_ClearSignature( ){return findElement("//android.view.ViewGroup/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[2]",LocatorType.XPath);}
    public WebElement Button_Submit(){return findElement("Submit",LocatorType.AccessibilityId);}
    public WebElement Header_CustomerSignature(boolean...IgnoreException){return findElement("//android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView",LocatorType.XPath,IgnoreException);}
    public WebElement Button_SkipCustomerSignature(){return findElement("com.bungii.driver:id/appCompatTextView65",LocatorType.Id);}
    public WebElement Label_DeliverySuccessMessageLive()  { return findElement("//p[@id='delivery-completed-success-message']/i[2]", LocatorType.XPath); }
    public WebElement Label_CancelSuccessMessageLive () { return findElement("//p[@id='delivery-cancelled-success-message']/i[2]", LocatorType.XPath); }
    public WebElement Message_AdminCompleteConfirm() {return findElement("//div[@id='editEndTime']//following::div[2]/p[1]",LocatorType.XPath);}
    public WebElement Button_CalculateCost() { return findElement("//button[@id='btnCost']",LocatorType.XPath);}
    public WebElement Textbox_PickupEndDate () { return findElement("pickupEndDate", LocatorType.Id); }
    public WebElement Textbox_PickupEndTime () { return findElement("pickupEndTime", LocatorType.Id); }
    public WebElement Dropdown_ddlpickupEndTime () { return findElement("ddlpickupEndTime", LocatorType.Id); }
    public WebElement RadioButton_EditDeliveryStatus() { return findElement("//span[contains(text(),'Edit Delivery Status')]",LocatorType.XPath);}
    public WebElement Text_Details() { return findElement("//android.view.ViewGroup/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView[1]",LocatorType.XPath);}
    public WebElement Button_ConfirmStatus() { return findElement("//div[@class=\"modal-footer\"]/p/following-sibling::button[2]", LocatorType.XPath);}
    public WebElement Button_CloseStatus() { return findElement("//div[@id=\"edit-status-success-modal\"]/div/div/div[2]/button", LocatorType.XPath);}
    public WebElement Button_RemoveDrivers () { return findElement("//div[@id='tripDriverDetails']//strong[contains(text(),'Remove')]", LocatorType.XPath); }//Richa
    public WebElement Button_Confirm() { return findElement("//button[text()='Confirm']", LocatorType.XPath); }
    public WebElement Header_CustomerSignature(){return findElement("//android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView",LocatorType.XPath);}
    public WebElement Label_CustomerSignature(){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]",LocatorType.XPath);}
    public WebElement Alert_DropOffInstructionsGotIt(){return findElement("com.bungii.driver:id/view_instructions_btn_next",LocatorType.Id);}

}
