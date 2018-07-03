using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.DriverPages
{
    class Driver_BungiiCompletedPage
    {
        public Driver_BungiiCompletedPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_summary_toolbar_title")]
        public IWebElement Title_Status { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_summary_text_total_time")]
        public IWebElement Text_TotalTime { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_summary_text_total_distance")]
        public IWebElement Text_TotalDistance { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_summary_text_total_earnings")]
        public IWebElement Text_TotalEarnings { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_summary_button_close_summary")]
        public IWebElement Button_OnToTheNext { get; set; }
    }
}
