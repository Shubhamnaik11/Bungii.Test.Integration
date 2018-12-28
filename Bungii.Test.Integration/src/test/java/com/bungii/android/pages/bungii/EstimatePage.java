package com.bungii.android.pages.bungii;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class EstimatePage extends PageBase {

    //------Header-------------------------------------------------------------------------------
    public WebElement Header_Estimate () { return findElement("//android.widget.TextView[@text='ESTIMATE']", LocatorType.XPath); }

    //------Locations----------------------------------------------------------------------------
    public WebElement Text_PickupLocation () { return findElement("com.bungii.customer:id/estimate_label_pickup_location", LocatorType.Id); }

    public WebElement Text_DropOffLocation () { return findElement("com.bungii.customer:id/estimate_label_dropoff_location", LocatorType.Id); }

    //------Trip Details-------------------------------------------------------------------------
    public WebElement Text_TripTime () { return findElement("com.bungii.customer:id/estimate_value_time", LocatorType.Id); }

    public WebElement Text_TripDistance () { return findElement("com.bungii.customer:id/estimate_value_distance", LocatorType.Id); }

    public WebElement Text_TotalEstimate () { return findElement("com.bungii.customer:id/estimate_value_total", LocatorType.Id); }

    //------loading/unloading time---------------------------------------------------------------
    public WebElement Link_LoadingUnloadingTime () { return findElement("com.bungii.customer:id/pickup_estimate_textview_loadunloadtime", LocatorType.Id); }

    public WebElement LoadingUnloadingTime_15 () { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='0']", LocatorType.XPath); }

    public WebElement LoadingUnloadingTime_30 () { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='1']", LocatorType.XPath); }

    public WebElement LoadingUnloadingTime_45 () { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='2']", LocatorType.XPath); }

    public WebElement LoadingUnloadingTime_60 () { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='3']", LocatorType.XPath); }

    public WebElement LoadingUnloadingTime_75 () { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='4']", LocatorType.XPath); }

    public WebElement LoadingUnloadingTime_90 () { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='5']", LocatorType.XPath); }

    //------Promo Code--------------------------------------------------------------------------
    public WebElement Link_Promo () { return findElement("com.bungii.customer:id/estimate_value_promo", LocatorType.Id); }

    public WebElement Select_PromoCode () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label' and @text='$10.00 - NEW01']", LocatorType.XPath); }

    //------Payment Details---------------------------------------------------------------------
    public WebElement Select_PaymentMode () { return findElement("com.bungii.customer:id/estimate_value_pay_mode", LocatorType.Id); }

    //------Date and Time------------------------------------------------------------------------
    public WebElement Time () { return findElement("com.bungii.customer:id/date_time_picker_textview_selectedtime", LocatorType.Id); }
    public WebElement Button_Later () { return findElement("com.bungii.customer:id/alert_schedule_bungii_textview_later", LocatorType.Id); }
    public WebElement Button_DateConfirm () { return findElement("android:id/button1", LocatorType.Id); }
    public WebElement Button_TimeConfirm () { return findElement("com.bungii.customer:id/timepicker_okay", LocatorType.Id); }

    public WebElement Samsung_CurrentSelectedDate () { return findElement("//android.widget.DatePicker/android.widget.LinearLayout/android.widget.ViewAnimator/android.widget.ListView/android.view.View/android.view.View[@enabled='true' and @selected='true']", LocatorType.XPath); }

    public WebElement Samsung_Date_OK () { return findElement("//android.widget.Button[@resource-id='android:id/button1' and @text='OK']", LocatorType.XPath); }

    public WebElement Samsung_Date_Cancel () { return findElement("//android.widget.Button[@resource-id='android:id/button2' and @text='Cancel']", LocatorType.XPath); }

    public WebElement Samsung_SetTime_Hour_Current () { return findElement("//android.widget.EditText[@resource-id='android:id/numberpicker_input' and @instance='0']", LocatorType.XPath); }

    public WebElement Samsung_SetTime_Hour_Prev () { return findElement("//android.widget.NumberPicker[@instance='0']/android.widget.Button[@instance='0']", LocatorType.XPath); }

    public WebElement Samsung_SetTime_Hour_Next () { return findElement("//android.widget.NumberPicker[@instance='0']/android.widget.Button[@instance='1']", LocatorType.XPath); }

    public WebElement Samsung_SetTime_Min_Current () { return findElement("//android.widget.EditText[@resource-id='android:id/numberpicker_input' and @instance='1']", LocatorType.XPath); }

    public WebElement Samsung_SetTime_Min_Prev () { return findElement("//android.widget.NumberPicker[@instance='1']/android.widget.Button[@instance='0']", LocatorType.XPath); }

    public WebElement Samsung_SetTime_Min_Next () { return findElement("//android.widget.NumberPicker[@instance='1']/android.widget.Button[@instance='1']", LocatorType.XPath); }

    public WebElement Samsung_SetTime_AmPm_Current () { return findElement("//android.widget.EditText[@resource-id='android:id/numberpicker_input' and @instance='2']", LocatorType.XPath); }

    public WebElement Samsung_Time_OK () { return findElement("com.bungii.customer:id/timepicker_okay", LocatorType.Id); }

    public WebElement Samsung_Time_Cancel () { return findElement("com.bungii.customer:id/timepicker_cancel", LocatorType.Id); }

    //------Photo--------------------------------------------------------------------------------
    public WebElement Row_Images () { return findElement("com.bungii.customer:id/imagesHorizontalScrollContainer", LocatorType.Id); }

    public WebElement Link_AddPhoto () { return findElement("com.bungii.customer:id/imageview_row_image_add_item_photo", LocatorType.Id); }

    public WebElement Message_CameraPermissions (boolean ... ignoreException) { return findElement("com.android.packageinstaller:id/permission_message", LocatorType.Id,ignoreException); }

    public WebElement Permissions_CameraAllow () { return findElement("com.android.packageinstaller:id/permission_allow_button", LocatorType.Id); }

    public WebElement Permissions_CameraDeny () { return findElement("com.android.packageinstaller:id/permission_deny_button", LocatorType.Id); }

    public WebElement Alert_ChooseItemPhoto_Title () { return findElement("android:id/alertTitle", LocatorType.Id); }

    public WebElement Option_Camera () { return findElement("//android.widget.TextView[@resource-id='android:id/text1' and @text='Camera']", LocatorType.XPath); }

    public WebElement Option_Gallery () { return findElement("//android.widget.TextView[@resource-id='android:id/text1' and @text='Gallery']", LocatorType.XPath); }

    public WebElement Button_Camera_ClickAlternate () { return findElement("com.sec.android.app.camera:id/camera_preview", LocatorType.Id); }

    public WebElement Button_Camera_OK () { return findElement("com.sec.android.app.camera:id/okay", LocatorType.Id); }

    public WebElement Button_Camera_Retry () { return findElement("com.sec.android.app.camera:id/retry", LocatorType.Id); }

    public WebElement Button_Review () { return findElement("com.motorola.camera:id/review_approve", LocatorType.Id); }
    public WebElement Button_SelectedImage () { return findElement("com.bungii.customer:id/selected_image", LocatorType.Id); }



    //------Confirmations---------------------------------------------------------------------------
    public WebElement Checkbox_AgreeEstimate (boolean ...ignoreException) { return findElement("com.bungii.customer:id/estimate_agree_disclaimer", LocatorType.Id,ignoreException); }

    public WebElement Button_RequestBungii (boolean ...ignoreException) { return findElement("//android.widget.Button[@text='REQUEST BUNGII']", LocatorType.XPath,ignoreException); }

    public WebElement Alert_ConfirmRequestMessage () { return findElement("android:id/message", LocatorType.Id); }

    public WebElement Button_RequestConfirm () { return findElement("android:id/button1", LocatorType.Id); }

    public WebElement Button_RequestConfirmCancel () { return findElement("android:id/button2", LocatorType.Id); }

    public WebElement Alert_DelayRequestingTrip () { return findElement("//android.widget.TextView[@resource-id='android:id/message' and @text='Oops! Since there has been a delay in requesting this trip, the scheduled time selected is no longer valid. Please recheck and submit your request.']", LocatorType.XPath); }

    public WebElement Button_DelayRequestingTrip_OK () { return findElement("android:id/button1", LocatorType.Id); }






}
