using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Admin
{
    public class Admin_ReferralSourcePage
    {
        public Admin_ReferralSourcePage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        [FindsBy(How = How.XPath, Using = "//td[text()='Apt Complex']/following-sibling::td[1]")]
        public IWebElement AptComplex_AccCreated { get; set; }

        [FindsBy(How = How.XPath, Using = "//td[text()='Apt Complex']/following-sibling::td[2]")]
        public IWebElement AptComplex_PercentAccCreated { get; set; }
        //------------------
        [FindsBy(How = How.XPath, Using = "//td[text()='Blimp']/following-sibling::td[1]")]
        public IWebElement Blimp_AccCreated { get; set; }

        [FindsBy(How = How.XPath, Using = "//td[text()='Blimp']/following-sibling::td[2]")]
        public IWebElement Blimp_PercentAccCreated { get; set; }
        //------------------
        [FindsBy(How = How.XPath, Using = "//td[text()='Craigslist']/following-sibling::td[1]")]
        public IWebElement Craigslist_AccCreated { get; set; }

        [FindsBy(How = How.XPath, Using = "//td[text()='Craigslist']/following-sibling::td[2]")]
        public IWebElement Craigslist_PercentAccCreated { get; set; }
        //------------------
        [FindsBy(How = How.XPath, Using = "//td[text()='Estate Sale']/following-sibling::td[1]")]
        public IWebElement EstateSale_AccCreated { get; set; }

        [FindsBy(How = How.XPath, Using = "//td[text()='Estate Sale']/following-sibling::td[2]")]
        public IWebElement EstateSale_PercentAccCreated { get; set; }
        //------------------
        [FindsBy(How = How.XPath, Using = "//td[text()='Event']/following-sibling::td[1]")]
        public IWebElement Event_AccCreated { get; set; }

        [FindsBy(How = How.XPath, Using = "//td[text()='Event']/following-sibling::td[2]")]
        public IWebElement Event_PercentAccCreated { get; set; }
        //------------------
        [FindsBy(How = How.XPath, Using = "//td[text()='Facebook']/following-sibling::td[1]")]
        public IWebElement Facebook_AccCreated { get; set; }

        [FindsBy(How = How.XPath, Using = "//td[text()='Facebook']/following-sibling::td[2]")]
        public IWebElement Facebook_PercentAccCreated { get; set; }
        //------------------
        [FindsBy(How = How.XPath, Using = "//td[text()='Google']/following-sibling::td[1]")]
        public IWebElement Google_AccCreated { get; set; }

        [FindsBy(How = How.XPath, Using = "//td[text()='Google']/following-sibling::td[2]")]
        public IWebElement Google_PercentAccCreated { get; set; }
        //------------------
        [FindsBy(How = How.XPath, Using = "//td[text()='News Story']/following-sibling::td[1]")]
        public IWebElement NewsStory_AccCreated { get; set; }

        [FindsBy(How = How.XPath, Using = "//td[text()='News Story']/following-sibling::td[2]")]
        public IWebElement NewsStory_PercentAccCreated { get; set; }
        //------------------
        [FindsBy(How = How.XPath, Using = "//td[text()='Other']/following-sibling::td[1]")]
        public IWebElement Other_AccCreated { get; set; }

        [FindsBy(How = How.XPath, Using = "//td[text()='Other']/following-sibling::td[2]")]
        public IWebElement Other_PercentAccCreated { get; set; }
        //------------------
        [FindsBy(How = How.XPath, Using = "//td[text()='Store']/following-sibling::td[1]")]
        public IWebElement Store_AccCreated { get; set; }

        [FindsBy(How = How.XPath, Using = "//td[text()='Store']/following-sibling::td[2]")]
        public IWebElement Store_PercentAccCreated { get; set; }
        //------------------
        [FindsBy(How = How.XPath, Using = "//td[text()='Word Of Mouth']/following-sibling::td[1]")]
        public IWebElement WordOfMouth_AccCreated { get; set; }

        [FindsBy(How = How.XPath, Using = "//td[text()='Word Of Mouth']/following-sibling::td[2]")]
        public IWebElement WordOfMouth_PercentAccCreated { get; set; }
    }
}
