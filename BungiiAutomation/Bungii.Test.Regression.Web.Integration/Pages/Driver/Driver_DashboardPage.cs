using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    public class Driver_DashboardPage
    {
        public Driver_DashboardPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        //Header - Dashboard
        [FindsBy(How = How.XPath, Using = "//input[@id='DriverCurrentStatus']/following-sibling::h4")]
        public IWebElement Header_Dashboard { get; set; }
    }
}
