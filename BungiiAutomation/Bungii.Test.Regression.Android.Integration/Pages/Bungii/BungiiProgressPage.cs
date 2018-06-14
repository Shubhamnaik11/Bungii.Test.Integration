using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.Bungii
{
    class BungiiProgressPage
    {
        public BungiiProgressPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        //------Page Titles--------------------------------------------------------------
        [FindsBy(How = How.XPath, Using = "com.bungii.customer:id/toolbar_title")]
        public IWebElement PageTitle { get; set; }

        //------Bungii Statuses----------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_status_1")]
        public IWebElement BungiiStatus_Enroute { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_status_2")]
        public IWebElement BungiiStatus_Arrived { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_status_3")]
        public IWebElement BungiiStatus_LoadingItem { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_status_4")]
        public IWebElement BungiiStatus_DrivingToDropOff { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_status_5")]
        public IWebElement BungiiStatus_UnloadingItem { get; set; }

        //------Location Details---------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_address_name")]
        public IWebElement Bungii_LocationTitle { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_address_value")]
        public IWebElement Bungii_Location { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_estimate")]
        public IWebElement Bungii_ETA { get; set; }

        //------Driver Details-----------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_caller_image")]
        public IWebElement Bungii_Driver_Image { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_caller_title")]
        public IWebElement Bungii_Driver_Title { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_caller_name")]
        public IWebElement Bungii_Driver_Name { get; set; }

        [FindsBy(How = How.Id, Using = "om.bungii.customer:id/ratingbarPickupDetails")]
        public IWebElement Bungii_Driver_RatingBar { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_sms_button")]
        public IWebElement Button_Bungii_Driver_SMS { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/pickup_details_call_button")]
        public IWebElement Button_Bungii_Driver_Call { get; set; }
    }
}
