using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Admin
{
    public class Admin_MenuLinksPage
    {
        public Admin_MenuLinksPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        [FindsBy(How = How.Id, Using = "adminmenu-dashboard")]
        public IWebElement Menu_Dashboard { get; set; }

        [FindsBy(How = How.Id, Using = "adminmenu-customers")]
        public IWebElement Menu_Customers { get; set; }

        [FindsBy(How = How.Id, Using = "adminmenu-drivers")]
        public IWebElement Menu_Drivers { get; set; }

        [FindsBy(How = How.Id, Using = "adminmenu-trips")]
        public IWebElement Menu_Trips { get; set; }

        [FindsBy(How = How.Id, Using = "adminmenu-completedtrips")]
        public IWebElement Menu_Trips_Trips { get; set; }

        [FindsBy(How = How.Id, Using = "adminmenu-livetrips")]
        public IWebElement Menu_Trips_LiveTrips { get; set; }

        [FindsBy(How = How.Id, Using = "adminmenu-marketing")]
        public IWebElement Menu_Marketing { get; set; }

        [FindsBy(How = How.Id, Using = "adminmenu-promocode")]
        public IWebElement Menu_Marketing_PromoCode { get; set; }

        [FindsBy(How = How.Id, Using = "adminmenu-referralsource")]
        public IWebElement Menu_Marketing_ReferralSource { get; set; }

        [FindsBy(How = How.Id, Using = "adminmenu-geofences")]
        public IWebElement Menu_Geofences { get; set; }
    }
}
