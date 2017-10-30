using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    class Driver_FinishPage
    {
        public Driver_FinishPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        //Finish - Continue Button
        [FindsBy(How = How.XPath, Using = "//div[@id='divStep7']/button[contains(text(),'Continue')]")]
        public IWebElement Button_FinishContinue { get; set; }
    }
}
