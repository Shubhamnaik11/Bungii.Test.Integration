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

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/toolbar_title")]
        public IWebElement Title_Status { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/toolbar_button_cancel")]
        public IWebElement Button_Cancel { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button1")]
        public IWebElement Button_Cancel_Yes { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button2")]
        public IWebElement Button_Cancel_Cancel { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_slider_view")]
        public IWebElement Slider { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_sms_button")]
        public IWebElement Button_SMS { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/pickup_details_call_button")]
        public IWebElement Button_Call { get; set; }
    }
}
