using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.Bungii
{
    class BungiiAcceptedPage
    {
        public BungiiAcceptedPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='BUNGII ACCEPTED']")]
        public IWebElement PageTitle_BungiiAccepted { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.LinearLayout[@id='com.bungii.customer:id/searching_view_bungii_accepted_container']/android.widget.TextView[@instance='1']")]
        public IWebElement Label_BungiiAccepted { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/bungii_accepted_enroute")]
        public IWebElement Label_DriverEnRoute { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/bungii_accepted_driver_image")]
        public IWebElement Image_Driver { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/rating_bar")]
        public IWebElement DriverRatingBar { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/bungii_accepted_driver_name")]
        public IWebElement Label_DriverName { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/bungii_accepted_button_ok")]
        public IWebElement Button_OK { get; set; }
    }
}
