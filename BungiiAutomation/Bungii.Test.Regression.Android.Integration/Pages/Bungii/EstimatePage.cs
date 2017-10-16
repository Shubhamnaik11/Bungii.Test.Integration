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

        //------Payment Details---------------------------------------------------------------------
        [FindsBy(How = How.XPath, Using = "com.bungii.customer:id/estimate_value_pay_mode")]
        public IWebElement Select_PaymentMode { get; set; }

        //------Promo Code--------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/estimate_value_promo")]
        public IWebElement Link_Promo { get; set; }

        [FindsBy(How =How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label' and @text='$10.00 - NEW01']")]
        public IWebElement Select_PromoCode { get; set; }

        //------Photo--------------------------------------------------------------------------------
        [FindsBy(How = How.XPath, Using = "//android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")]
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
    }
}
