using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    public class Driver_ForgotPasswordPage
    {
        public Driver_ForgotPasswordPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        //---------------------------Forgot Password--------------------------------------------------------------------
        //Driver Login - Forgot Password Link
        [FindsBy(How = How.XPath, Using = "//form[@id='Login']/div/a[text()='Forgot Password?']")]
        public IWebElement Link_ForgotPassword { get; set; }

        //Driver Forgot Password - Header
        [FindsBy(How = How.XPath, Using = "//div[@id='forgot-pwd']/h2")]
        public IWebElement Header_ForgotPassword { get; set; }

        //Driver Forgot Password - Blank field validation error
        [FindsBy(How = How.Id, Using = "forgotpassworderrorsummary")]
        public IWebElement Err_ForgotPass_BlankField { get; set; }

        //Driver Forgot Password - Phone Number field
        [FindsBy(How = How.XPath, Using = "//form[@id='ForgotPassword']/div/input[@id='PhoneNo']")]
        public IWebElement Textfield_ForgotPass_Phone { get; set; }

        //Driver Forgot Password - Phone Number field Error
        [FindsBy(How = How.Id, Using = "PhoneNo-error")]
        public IWebElement Err_ForgotPass_Phone { get; set; }

        //Driver Forgot Password - Send Verification Code button
        [FindsBy(How = How.XPath, Using = "//form[@id='ForgotPassword']/button[contains(text(),'Code')]")]
        public IWebElement Button_SendVerifCode { get; set; }

        //Driver Forgot Password - Back to Login link
        [FindsBy(How = How.XPath, Using = "//form[@id='ForgotPassword']/div/a[text()='Back to Login']")]
        public IWebElement Link_BackToLogin { get; set; }
    }
}
