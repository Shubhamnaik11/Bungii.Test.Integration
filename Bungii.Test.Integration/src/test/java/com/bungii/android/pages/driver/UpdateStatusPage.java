package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class UpdateStatusPage extends PageBase {
    public WebElement Text_WaitingForDriver(boolean ignoreException){return findElement("com.bungii.driver:id/progress_message",LocatorType.Id, ignoreException);}
    public WebElement Activity_loader(boolean ignoreException){return findElement("com.bungii.driver:id/progress_bar",LocatorType.Id, ignoreException);}

    public WebElement Tab_AddPhoto(){return findElement("//android.widget.TextView[@text='Tap to add photo']",LocatorType.XPath);}
    public WebElement Button_Save(){return findElement("//android.widget.Button[@text='Save']",LocatorType.XPath);}
    public WebElement Button_CLick(){return findElement("//*[@resource-id='com.motorola.camera2:id/controls']",LocatorType.XPath);}

    //Driver Rates Driver
    public WebElement Header_RateTeamate() {return findElement("//android.widget.TextView[@text=\"Rate duo teammate\"]", LocatorType.XPath);}
    public WebElement Star_Rating() {return findElement("//android.widget.RatingBar", LocatorType.XPath);}
    public WebElement Text_ChooseRating() {return findElement("//android.widget.TextView[@text='Choose your rating']", LocatorType.XPath);}
    public WebElement Text_DriverExperience() {return findElement("//android.widget.TextView[@text='Tell us about the other driver']", LocatorType.XPath);}
    public WebElement Textbox_AdditionalFeedback() {return findElement("//android.widget.EditText[@text='Any additional feedback (Optional)']", LocatorType.XPath);}
    public WebElement Button_DriverSubmit() {return findElement("//android.widget.Button[@text='Submit']", LocatorType.XPath);}
    public WebElement Text_Additional() {return findElement("//android.widget.TextView[@text='Additional']", LocatorType.XPath);}
    public WebElement RatingBar(){return findElement("//*[@resource-id='com.bungii.driver:id/rate_participants_rating_bar_customer']",LocatorType.XPath);}

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
    public WebElement Label_CustomerSignature(boolean...IgnoreException){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]",LocatorType.XPath,IgnoreException);}
    public WebElement Alert_DropOffInstructionsGotIt(){return findElement("com.bungii.driver:id/view_instructions_btn_next",LocatorType.Id);}

    public WebElement Button_CloseDeliveryDetails() { return findElement("com.bungii.driver:id/appCompatImageView14",LocatorType.Id);}
    public WebElement Icon_Call(){return findElement("com.bungii.driver:id/view_sliding_bottom_sheet_expanded_iv_call_customer",LocatorType.Id);}
    public WebElement Icon_Text(){return findElement("com.bungii.driver:id/appCompatImageView18",LocatorType.Id);}
    public WebElement Icon_Pickup(){return findElement("//android.view.ViewGroup/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.ImageView",LocatorType.XPath);}
    public WebElement Icon_DropOff(){return findElement("//android.view.ViewGroup/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.ImageView",LocatorType.XPath);}
    public WebElement Text_ContactDuo(boolean...IgnoreException){return findElement("com.bungii.driver:id/tv_contact_duo_label",LocatorType.Id,IgnoreException);}
    public WebElement Text_TeamMate(boolean... IgnoreException){return findElement("com.bungii.driver:id/tv_teammate_label",LocatorType.Id,IgnoreException);}

}
