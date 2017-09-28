using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class AccountPage
    {
        public AccountPage(IWebDriver driver)
        {
           PageFactory.InitElements(driver, this);
        }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='ACCOUNT']")]
        public IWebElement Header_AccountPage { get; set; }

        [FindsBy(How = How.Id, Using = "public IWebElement Account_Email { get; set; }")]
        public IWebElement Account_Name { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.LinearLayout[@resource-id='com.bungii.customer:id/account_info_layout_phone']/android.widget.TextView[2]")]
        public IWebElement Account_Phone { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/account_info_textview_email")]
        public IWebElement Account_Email { get; set; }
    }
}
