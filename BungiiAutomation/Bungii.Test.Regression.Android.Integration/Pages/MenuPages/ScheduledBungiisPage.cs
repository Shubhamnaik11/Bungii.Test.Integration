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

        [FindsBy(How = How.Id, Using = "")]
        public IWebElement Title_ScheduledBungiis { get; set; }
    }
}
