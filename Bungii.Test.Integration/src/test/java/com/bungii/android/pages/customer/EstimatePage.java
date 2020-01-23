package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EstimatePage extends PageBase {

    //------Header-------------------------------------------------------------------------------
    public WebElement Header_Estimate (boolean...ignoreException) { return findElement("//android.widget.TextView[@text='ESTIMATE']", LocatorType.XPath,ignoreException); }
  public WebElement GenericHeader(boolean ...ignoreException) { return findElement("//android.view.View[@resource-id='com.bungii.customer:id/toolbar' or 'com.bungii.customer:id/action_bar']/android.widget.TextView", LocatorType.XPath,ignoreException); }

    //------Locations----------------------------------------------------------------------------
  //  public WebElement Text_PickupLocation () { return findElement("com.bungii.customer:id/estimate_label_pickup_location", LocatorType.Id); }
    public WebElement Text_PickupLocation_LineOne () { return findElement("//android.widget.ImageView[@resource-id='com.bungii.customer:id/pickup_estimate_iv_address_type']/following-sibling::android.widget.LinearLayout/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_PickupLocation_LineTwo () { return findElement("//android.widget.ImageView[@resource-id='com.bungii.customer:id/pickup_estimate_iv_address_type']/following-sibling::android.widget.LinearLayout/android.widget.TextView[2]", LocatorType.XPath); }

  //  public WebElement Text_DropOffLocation () { return findElement("com.bungii.customer:id/estimate_label_dropoff_location", LocatorType.Id); }
    public WebElement Text_DropOffLocation_LineOne () { return findElement("//android.widget.LinearLayout[@resource-id='com.bungii.customer:id/pickup_request_dopoff_container']/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_DropOffLocation_LineTwo () { return findElement("//android.widget.LinearLayout[@resource-id='com.bungii.customer:id/pickup_request_dopoff_container']/android.widget.TextView[2]", LocatorType.XPath); }

    //------Trip Details-------------------------------------------------------------------------
    public WebElement Text_TripTime () { return findElement("com.bungii.customer:id/estimate_value_time", LocatorType.Id); }

    public WebElement Text_TripDistance () { return findElement("//android.widget.LinearLayout[@resource-id='com.bungii.customer:id/estimate_ll_distance_container']/android.widget.TextView[2]", LocatorType.XPath); }

    public WebElement Text_TotalEstimate () { return findElement("//android.widget.LinearLayout[@resource-id='com.bungii.customer:id/estimate_ll_estimated_cost_container']/android.widget.TextView[2]", LocatorType.XPath); }

    //------loading/unloading time---------------------------------------------------------------
    public WebElement Link_LoadingUnloadingTime () { return findElement("com.bungii.customer:id/pickup_estimate_textview_loadunloadtime", LocatorType.Id); }

    public WebElement LoadingUnloadingTime_15 (boolean...ignoreException) { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @index='0']", LocatorType.XPath,ignoreException); }

    public WebElement LoadingUnloadingTime_30 () { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @index='1']", LocatorType.XPath); }

    public WebElement LoadingUnloadingTime_45 () { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @index='2']", LocatorType.XPath); }

    public WebElement LoadingUnloadingTime_60 () { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @index='3']", LocatorType.XPath); }

    public WebElement LoadingUnloadingTime_75 () { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @index='4']", LocatorType.XPath); }

    public WebElement LoadingUnloadingTime_90 () { return findElement("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @index='5']", LocatorType.XPath); }

    public List<WebElement> LoadingUnloadingTime () { return findElements("//android.widget.ListView[@resource-id='com.bungii.customer:id/select_dialog_listview']/android.widget.CheckedTextView[@resource-id='android:id/text1']", LocatorType.XPath); }

    //------Promo Code--------------------------------------------------------------------------
    public WebElement Link_Promo (boolean ignoreException) { return findElement("com.bungii.customer:id/estimate_value_promo", LocatorType.Id,ignoreException); }

    public WebElement Select_PromoCode () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label' and @text='$10.00 - NEW01']", LocatorType.XPath); }

    //------Payment Details---------------------------------------------------------------------
    public WebElement Select_PaymentMode () { return findElement("com.bungii.customer:id/estimate_value_pay_mode", LocatorType.Id); }

    //------Date and Time------------------------------------------------------------------------
    public WebElement Time () { return findElement("com.bungii.customer:id/date_time_picker_textview_selectedtime", LocatorType.Id); }
    public WebElement Text_TimeZoneLabel () { return findElement("com.bungii.customer:id/date_time_picker_textview_timezone_label", LocatorType.Id); }
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

    public WebElement Link_AddPhoto (boolean ... ignoreException) { return findElement("com.bungii.customer:id/imageview_row_image_add_item_photo", LocatorType.Id,ignoreException); }

    public WebElement Message_CameraPermissions (boolean ... ignoreException) { return findElement("com.android.packageinstaller:id/permission_message", LocatorType.Id,ignoreException); }

    public WebElement Permissions_CameraAllow () { return findElement("com.android.packageinstaller:id/permission_allow_button", LocatorType.Id); }

    public WebElement Permissions_CameraDeny () { return findElement("com.android.packageinstaller:id/permission_deny_button", LocatorType.Id); }

    public WebElement Alert_ChooseItemPhoto_Title () { return findElement("android:id/alertTitle", LocatorType.Id); }

    public WebElement Option_Camera (boolean ...ignoreException) { return findElement("//android.widget.TextView[@resource-id='android:id/text1' and @text='Camera']", LocatorType.XPath,ignoreException); }

    public WebElement Option_Gallery () { return findElement("//android.widget.TextView[@resource-id='android:id/text1' and @text='Gallery']", LocatorType.XPath); }
    public WebElement Option_OverLayPhotos () { return findElement("//android.widget.TextView[@resource-id='android:id/text1' and @text='Photos']", LocatorType.XPath); }
    public WebElement Option_LARGEIMAGEFOLDER() { return findElement("//android.widget.TextView[@resource-id='com.google.android.apps.photos:id/title' and @text='ALARGE_IMAGE']", LocatorType.XPath); }
    public WebElement IMAGE_LOCATOR() { return findElement("//android.view.View[contains(@content-desc,\"Photo taken on \")]", LocatorType.XPath); }

    public WebElement Button_Camera_ClickAlternate () { return findElement("com.sec.android.app.camera:id/camera_preview", LocatorType.Id); }

    public WebElement Button_Camera_OK () { return findElement("com.sec.android.app.camera:id/okay", LocatorType.Id); }

    public WebElement Button_Camera_Retry () { return findElement("com.sec.android.app.camera:id/retry", LocatorType.Id); }

    public WebElement Button_Review (boolean ...ignoreException) { return findElement("com.motorola.camera:id/review_approve", LocatorType.Id,ignoreException); }
    public WebElement Button_SelectedImage () { return findElement("com.bungii.customer:id/selected_image", LocatorType.Id); }



    //------Confirmations---------------------------------------------------------------------------
    public WebElement Checkbox_AgreeEstimate (boolean ...ignoreException) { return findElement("com.bungii.customer:id/estimate_agree_disclaimer", LocatorType.Id,ignoreException); }

    public WebElement Button_RequestBungii (boolean ...ignoreException) { return findElement("//android.widget.Button[@text='REQUEST BUNGII']", LocatorType.XPath,ignoreException); }

    public WebElement Alert_ConfirmRequestMessage (boolean ...ignoreException) { return findElement("android:id/message", LocatorType.Id,ignoreException); }

    public WebElement Button_RequestConfirm () { return findElement("android:id/button1", LocatorType.Id); }

    public WebElement Button_RequestConfirmCancel () { return findElement("android:id/button2", LocatorType.Id); }

    public WebElement Button_OK (boolean ...ignoreException) { return findElement("//android.widget.Button[@resource-id='android:id/button1' and @text='OK']", LocatorType.XPath,ignoreException); }
    public WebElement Button_Cancel (boolean ...ignoreException) { return findElement("//android.widget.Button[@resource-id='android:id/button2' and @text='Cancel']", LocatorType.XPath,ignoreException); }
      public WebElement Button_Proceed (boolean ...ignoreException) { return findElement("//android.widget.Button[@resource-id='android:id/button1' and @text='PROCEED']", LocatorType.XPath,ignoreException); }


    public WebElement Alert_DelayRequestingTrip () { return findElement("//android.widget.TextView[@resource-id='android:id/message' and @text='Oops! Since there has been a delay in requesting this trip, the scheduled time selected is no longer valid. Please recheck and submit your request.']", LocatorType.XPath); }

    public WebElement Button_DelayRequestingTrip_OK () { return findElement("android:id/button1", LocatorType.Id); }


    public WebElement Button_InfoLoadTime () { return findElement("com.bungii.customer:id/moreInfoLoadUnload", LocatorType.Id); }
    public WebElement Button_InfoEstimate () { return findElement("//*[@resource-id='com.bungii.customer:id/estimate_value_total']/preceding-sibling::android.widget.ImageView", LocatorType.XPath); }
    public WebElement Button_InfoTime () { return findElement("//*[@resource-id='com.bungii.customer:id/estimate_datetime_container']/android.widget.ImageView", LocatorType.XPath); }
    public WebElement Button_AcceptPopup () { return findElement("//*[@resource-id='android:id/button1' and @text='OK']", LocatorType.XPath); }


    public WebElement Text_BungiiTime() { return findElement("com.bungii.customer:id/item_my_bungii_tv_date", LocatorType.Id);}

    public WebElement Button_SystemCalenderOK() { return  findElement("android:id/button1", LocatorType.Id);}
    public WebElement Text_TimeHourPicker() { return  findElements("//android.widget.NumberPicker/android.widget.EditText", LocatorType.XPath).get(0);}
    public WebElement Text_TimeMinutesPicker() { return  findElements("//android.widget.NumberPicker/android.widget.EditText", LocatorType.XPath).get(1);}
    public WebElement Text_TimeMeridian() { return  findElements("//android.widget.NumberPicker/android.widget.EditText", LocatorType.XPath).get(2);}
    public WebElement Button_OKOnTimePicker(){ return findElement("com.bungii.customer:id/timepicker_okay", LocatorType.Id);}
    public WebElement Text_Month() {return findElement("android:id/date_picker_month", LocatorType.Id);}
    public WebElement Text_Year() {return findElement("android:id/date_picker_year", LocatorType.Id);}
    public WebElement Button_DoneOnSuccess(){return  findElement("com.bungii.customer:id/bungii_posted_button_done", LocatorType.Id);}


  public WebElement Link_PromoValue(boolean ignoreException) { return findElement("//*[@resource-id='com.bungii.customer:id/estimate_promocode_container']/child::android.widget.ImageView", LocatorType.XPath,ignoreException);}
  public WebElement Text_GetDistance() {return findElements("//*[@resource-id='com.bungii.customer:id/estimate_ll_distance_container']/child::android.widget.TextView", LocatorType.XPath).get(1);}
  public WebElement Text_GetCost() {return findElements("//*[@resource-id='com.bungii.customer:id/estimate_ll_estimated_cost_container']/child::android.widget.TextView", LocatorType.XPath).get(1);}
  public WebElement Button_Back(boolean ignoreException) {return findElement("//android.widget.ImageButton[@content-desc=\"Navigate up\"]", LocatorType.XPath,ignoreException);}
}
