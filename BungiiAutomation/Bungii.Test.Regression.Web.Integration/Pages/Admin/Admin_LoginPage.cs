using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Admin
{
    public class Admin_LoginPage
    {
        public Admin_LoginPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        //Admin Login - Header
        [FindsBy(How = How.XPath, Using = "//div[@id='login']/h2")]
        public IWebElement Header_AdminLogin { get; set; }

        //Admin Login - Phone Number
        [FindsBy(How = How.Id, Using = "phone")]
        public IWebElement TextBox_Phone { get; set; }

        //Admin Login - Password
        [FindsBy(How = How.Id, Using = "password")]
        public IWebElement TextBox_Password { get; set; }

        //Admin Login - Login Button
        [FindsBy(How = How.XPath, Using = "//form[@id='Login']/button[contains(text(),'LOG IN')]")]
        public IWebElement Button_AdminLogin { get; set; }
    }
}
