using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class EstimatePage
    {
        public EstimatePage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        //------Header-------------------------------------------------------------------------------
        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='ESTIMATE']")]
        public IWebElement Header_Estimate { get; set; }

        //------Locations----------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/estimate_label_pickup_location")]
        public IWebElement Text_PickupLocation { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/estimate_label_dropoff_location")]
        public IWebElement Text_DropOffLocation { get; set; }

        //------Trip Details-------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/estimate_value_time")]
        public IWebElement Text_TripTime { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/estimate_value_distance")]
        public IWebElement Text_TripDistance { get; set; }
        
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/estimate_value_total")]
        public IWebElement Text_TotalEstimate { get; set; }

        //------loading/unloading time---------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_estimate_textview_loadunloadtime")]
        public IWebElement Link_LoadingUnloadingTime { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='0']")]
        public IWebElement LoadingUnloadingTime_15 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='1']")]
        public IWebElement LoadingUnloadingTime_30 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='2']")]
        public IWebElement LoadingUnloadingTime_45 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='3']")]
        public IWebElement LoadingUnloadingTime_60 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='4']")]
        public IWebElement LoadingUnloadingTime_75 { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@resource-id='android:id/text1' and @instance='5']")]
        public IWebElement LoadingUnloadingTime_90 { get; set; }

        //------Promo Code--------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/estimate_value_promo")]
        public IWebElement Link_Promo { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label' and @text='$10.00 - NEW01']")]
        public IWebElement Select_PromoCode { get; set; }

        //------Payment Details---------------------------------------------------------------------
        [FindsBy(How = How.XPath, Using = "com.bungii.customer:id/estimate_value_pay_mode")]
        public IWebElement Select_PaymentMode { get; set; } 

        //------Date and Time------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/date_time_picker_textview_selectedtime")]
        public IWebElement Time { get; set; }
        
        [FindsBy(How = How.XPath, Using = "//android.widget.DatePicker/android.widget.LinearLayout/android.widget.ViewAnimator/android.widget.ListView/android.view.View/android.view.View[@enabled='true' and @selected='true']")]
        public IWebElement Samsung_CurrentSelectedDate { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.Button[@resource-id='android:id/button1' and @text='OK']")]
        public IWebElement Samsung_Date_OK { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.Button[@resource-id='android:id/button2' and @text='Cancel']")]
        public IWebElement Samsung_Date_Cancel { get; set; }
        
        [FindsBy(How = How.XPath, Using = "//android.widget.EditText[@resource-id='android:id/numberpicker_input' and @instance='0']")]
        public IWebElement Samsung_SetTime_Hour_Current { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.NumberPicker[@instance='0']/android.widget.Button[@instance='0']")]
        public IWebElement Samsung_SetTime_Hour_Prev { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.NumberPicker[@instance='0']/android.widget.Button[@instance='1']")]
        public IWebElement Samsung_SetTime_Hour_Next { get; set; }

        [FindsBy(How = How.Id, Using = "//android.widget.EditText[@resource-id='android:id/numberpicker_input' and @instance='1'")]
        public IWebElement Samsung_SetTime_Min_Current { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.NumberPicker[@instance='1']/android.widget.Button[@instance='0']")]
        public IWebElement Samsung_SetTime_Min_Prev { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.NumberPicker[@instance='1']/android.widget.Button[@instance='1']")]
        public IWebElement Samsung_SetTime_Min_Next { get; set; }

        [FindsBy(How = How.Id, Using = "//android.widget.EditText[@resource-id='android:id/numberpicker_input' and @instance='2'")]
        public IWebElement Samsung_SetTime_AmPm_Current { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/timepicker_okay")]
        public IWebElement Samsung_Time_OK { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/timepicker_cancel")]
        public IWebElement Samsung_Time_Cancel { get; set; }

        //------Photo--------------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/imagesHorizontalScrollContainer")]
        public IWebElement Row_Images { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/imageview_row_image_add_item_photo")]
        public IWebElement Link_AddPhoto { get; set; }

        [FindsBy(How = How.Id, Using = "com.android.packageinstaller:id/permission_message")]
        public IWebElement Message_CameraPermissions { get; set; }

        [FindsBy(How = How.Id, Using = "com.android.packageinstaller:id/permission_allow_button")]
        public IWebElement Permissions_CameraAllow { get; set; }

        [FindsBy(How = How.Id, Using = "com.android.packageinstaller:id/permission_deny_button")]
        public IWebElement Permissions_CameraDeny { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/alertTitle")]
        public IWebElement Alert_ChooseItemPhoto_Title { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='android:id/text1' and @text='Camera']")]
        public IWebElement Option_Camera { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='android:id/text1' and @text='Gallery']")]   
        public IWebElement Option_Gallery { get; set; }

        [FindsBy(How = How.Id, Using = "com.sec.android.app.camera:id/okay")]
        public IWebElement Button_Camera_OK { get; set; }

        [FindsBy(How = How.Id, Using = "com.sec.android.app.camera:id/retry")] 
        public IWebElement Button_Camera_Retry { get; set; }

        [FindsBy(How = How.Id, Using = "com.motorola.camera:id/review_approve")]
        public IWebElement Button_Review { get; set; }

        //------Confirmations---------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/estimate_agree_disclaimer")]
        public IWebElement Checkbox_AgreeEstimate { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.Button[@text='REQUEST BUNGII']")]
        public IWebElement Button_RequestBungii { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/message")]
        public IWebElement Alert_ConfirmRequestMessage { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button1")]
        public IWebElement Button_RequestConfirm { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button2")]
        public IWebElement Button_RequestConfirmCancel { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='android:id/message' and @text='Oops! Since there has been a delay in requesting this trip, the scheduled time selected is no longer valid. Please recheck and submit your request.']")]
        public IWebElement Alert_DelayRequestingTrip { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button1")]
        public IWebElement Button_DelayRequestingTrip_OK { get; set; }
    }
}
