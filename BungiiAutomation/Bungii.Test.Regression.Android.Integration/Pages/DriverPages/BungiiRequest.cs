using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.DriverPages
{
    class BungiiRequest
    {
        public BungiiRequest(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        //------On Demand Request Alert
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/notification_alert_message")]
        public IWebElement Alert_Msg { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/notification_alert_button_positive")]
        public IWebElement AlertButton_View { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/notification_alert_button_negative")]
        public IWebElement AlertButton_Cancel { get; set; }

        //------On Demand Request
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/activity_pickup_request_accept_bungii_button")]
        public IWebElement Button_Accept { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/activity_pickup_request_reject_bungii_button")]
        public IWebElement Button_Reject { get; set; }
    }
}
