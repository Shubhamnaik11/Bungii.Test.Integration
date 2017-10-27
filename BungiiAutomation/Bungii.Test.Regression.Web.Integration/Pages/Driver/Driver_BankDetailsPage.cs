using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    public class Driver_BankDetailsPage
    {
        public Driver_BankDetailsPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        //Header - Bank Details
        [FindsBy(How = How.XPath, Using = "//div[@id='tab-title']/h3")]
        public IWebElement Header_BankPage { get; set; }

        //Bank Details - Routing number
        [FindsBy(How = How.Id, Using = "BankDetails_BankRoutingNumber")]
        public IWebElement TextBox_RoutingNumber { get; set; }

        //Bank Details - Bank Account Number
        [FindsBy(How = How.Id, Using = "BankDetails_BankAccountNumber")]
        public IWebElement TextBox_BankAccNumber { get; set; }

        //Bank Details - Next Button
        [FindsBy(How = How.Id, Using = "btnBankInfo")]
        public IWebElement Button_BankNext { get; set; }
    }
}
