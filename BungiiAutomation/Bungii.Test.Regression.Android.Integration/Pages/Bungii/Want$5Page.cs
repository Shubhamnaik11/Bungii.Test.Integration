using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.Bungii
{
    class Want_5Page
    {
        public Want_5Page(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        [FindsBy(How = How.Id, Using = "	com.bungii.customer:id/postTripShareNoFreeMoney")]
        public IWebElement Button_NoFreeMoney { get; set; }
    }
}
