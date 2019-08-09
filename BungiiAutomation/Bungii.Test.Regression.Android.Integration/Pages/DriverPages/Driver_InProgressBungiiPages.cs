using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.DriverPages
{
    class Driver_InProgressBungiiPages
    {
        public Driver_InProgressBungiiPages(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        //------Page Titles--------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/toolbar_title")]
        public IWebElement Title_Status { get; set; }

        //------Cancel Bungii------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/toolbar_button_cancel")]
        public IWebElement Button_Cancel { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button1")]
        public IWebElement Button_Cancel_Yes { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button2")]
        public IWebElement Button_Cancel_Cancel { get; set; }

        //------Slider-------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_slider_view")]
        public IWebElement Slider { get; set; }

        //------SMS----------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_sms_button")]
        public IWebElement Button_Customer_SMS { get; set; }

        //------Call---------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_call_button")]
        public IWebElement Button_Customer_Call { get; set; }

        //------Bungii Statuses----------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_status_1")]
        public IWebElement BungiiStatus_Enroute { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_status_2")]
        public IWebElement BungiiStatus_Arrived { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_status_3")]
        public IWebElement BungiiStatus_LoadingItem { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_status_4")]
        public IWebElement BungiiStatus_DrivingToDropOff { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_status_5")]
        public IWebElement BungiiStatus_UnloadingItem { get; set; }

        //------Location Details---------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_address_name")]
        public IWebElement Bungii_LocationTitle { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_address_value")]
        public IWebElement Bungii_Location { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_estimate")]
        public IWebElement Bungii_ETA { get; set; }

        //------Customer Details-----------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_caller_title")]
        public IWebElement Bungii_Customer_Title { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_caller_name")]
        public IWebElement Bungii_Customer_Name { get; set; }
    }
}
