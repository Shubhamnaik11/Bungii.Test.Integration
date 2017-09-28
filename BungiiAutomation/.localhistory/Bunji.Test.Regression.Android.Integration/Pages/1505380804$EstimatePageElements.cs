using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Bunji.Test.Regression.Android.Integration.Pages
{
    class EstimatePageElements
    {

        public EstimatePageElements(IWebDriver driver)
       {
           PageFactory.InitElements(driver, this);
       }

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

        [FindsBy(How = How.Id, Using = "android:id/text1")]
        public IWebElement Option_Camera { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/text1")]   ///same id as camera
        public IWebElement Option_Gallery { get; set; }

        [FindsBy(How = How.Id, Using = "com.sec.android.app.camera:id/okay")]   ///same id as camera
        public IWebElement Button_Camera_OK { get; set; }

        [FindsBy(How = How.Id, Using = "com.sec.android.app.camera:id/retry")]   ///same id as camera
        public IWebElement Button_Camera_Retry { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/estimate_agree_disclaimer")]   ///same id as camera
        public IWebElement Checkbox_AgreeEstimate { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/message")]
        public IWebElement Alert_ConfirmRequest { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.Button[@text='REQUEST BUNGII']")]
        public IWebElement Button_RequestBungii { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button1")]
        public IWebElement Button_RequestConfirm { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button2")]
        public IWebElement Button_RequestConfirmCancel { get; set; }
    }
}
