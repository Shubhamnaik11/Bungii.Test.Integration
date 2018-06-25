using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.MenuPages
{
    class ScheduledBungiisPage
    {
        public ScheduledBungiisPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        //------Scheduled Bungii page---------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/toolbar_main_title")]
        public IWebElement Title_ScheduledBungiis { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.LinearLayout[@id='com.bungii.customer:id/scheduled_bungii_list_rl_container_nobungii']/android.widget.TextView[@instance='1']")]
        public IWebElement Text_NoBungiis { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.LinearLayout[@id='com.bungii.customer:id/scheduled_bungii_list_rl_container_nobungii']/android.widget.TextView[@instance='2']")]
        public IWebElement Text_NoBungiis_msg { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/scheduled_bungii_list_button_savemoney")]
        public IWebElement Button_SaveMoney { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/fragment_scheduled_bungii_textview_hours")]
        public IWebElement Text_TimeToNextBungii { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/message")]
        public IWebElement Alert_ScheduledTripAccepted { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button1")]
        public IWebElement Button_OK { get; set; }

        //------Scheduled Bungii Details-------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/container_scheduled_trip_row")]
        public IWebElement ScheduledBungii_01 { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/scheduled_row_textview_scheduleddatetime")]
        public IWebElement Text_BungiiScheduledTime_01 { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/scheduled_row_textview_status")]
        public IWebElement Text_BungiiStatus_01 { get; set; }
    }
}
