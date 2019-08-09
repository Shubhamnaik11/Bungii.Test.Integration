using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    public class Driver_TermsPage
    {
        public Driver_TermsPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        //Terms & Conditions - Text
        [FindsBy(How = How.XPath, Using = ".//*[@id='divStep5']/div[3]/div/div/div/table/tbody/tr[9]/td[2]")]
        public IWebElement Text_Terms { get; set; }

        //Terms & Conditions - Error - Agree unchecked
        [FindsBy(How = How.Id, Using = "summary5")]
        public IWebElement Err_Terms { get; set; }

        //Terms & Conditions - H5
        [FindsBy(How = How.XPath, Using = "//div[@id='divStep5']/div[2]/div/h5")]
        public IWebElement Terms_H5 { get; set; }

        //Terms & Conditions - Agree checkbox
        [FindsBy(How = How.XPath, Using = "//input[@id='TermsnConditions.HasAgreedToTerms']/following-sibling::label")]
        public IWebElement CheckBox_Agree_Click { get; set; }

        //Terms & Conditions - Agree checkbox
        [FindsBy(How = How.XPath, Using = "//input[@id='TermsnConditions.HasAgreedToTerms']")]
        public IWebElement CheckBox_Agree { get; set; }

        //Terms & Conditions - Next Button
        [FindsBy(How = How.Id, Using = "btnTerms")]
        public IWebElement Button_TermsNext { get; set; }
    }
}
