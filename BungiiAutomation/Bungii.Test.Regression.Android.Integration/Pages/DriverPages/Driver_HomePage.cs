using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.DriverPages
{
    class Driver_HomePage
    {
        public Driver_HomePage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }
        
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/toolbar_main_title")]
        public IWebElement Title_Status { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/home_driver_profile_image")]
        public IWebElement Image_DriverProfilePhoto { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/home_button_go_online")]
        public IWebElement Button_OnlineOffline { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/home_textview_available_trips")]
        public IWebElement Link_AvailableTrips { get; set; }
    }
}
