using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    public class Driver_VerifyPhonePage
    {
        public Driver_VerifyPhonePage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }
        //---------------------------Forgot Password - Verify Your Phone-------------------------------------------------
        //Driver - Verify your phone - Header
        [FindsBy(How = How.XPath, Using = "//div[@id='verification']/h2")]
        public IWebElement Header_VerifyPhone { get; set; }

        //Driver - Verify your phone - text
        [FindsBy(How = How.XPath, Using = "//div[@id='verification']/p")]
        public IWebElement Text_Verify_PhoneNo { get; set; }

        //Verify Your Phone - Blank field validation error
        [FindsBy(How = How.Id, Using = "verificationerrorsummary")]
        public IWebElement Err_VerifyPhone_BlankField { get; set; }

        //Verify Your Phone - Resend Code
        [FindsBy(How = How.Id, Using = "btnResendSMS")]
        public IWebElement Button_ResendCode { get; set; }

        //Verify Your Phone - Code
        [FindsBy(How = How.Id, Using = "SMSVerificationCode")]
        public IWebElement Textfield_Code { get; set; }

        //Verify Your Phone - Password
        [FindsBy(How = How.Id, Using = "new-password")]
        public IWebElement Textfield_Password { get; set; }

        //Verify Your Phone - Confirm Password
        [FindsBy(How = How.Id, Using = "confirm-password")]
        public IWebElement Textfield_ConfirmPassword { get; set; }

        //Verify Your Phone - Password - Passwords dont match -error
        [FindsBy(How = How.XPath, Using = "//form[@id='ResetPassword']/button[contains(text(),'Reset')]")]
        public IWebElement Button_ResetPassword { get; set; }

        //Verify Your Phone - Blank field validation error
        [FindsBy(How = How.Id, Using = "verificationerrorsummary")]
        public IWebElement Err_VerifyPhone_BlankPasswords { get; set; }

        //Verify Your Phone - Incorrect Verification Code
        [FindsBy(How = How.XPath, Using = "//form[@id='ResetPassword']/p/span")]
        public IWebElement Err_VerifyPhone_Code_Incorrect { get; set; }

        //Verify Your Phone - Password - error
        [FindsBy(How = How.Id, Using = "new-password-error")]
        public IWebElement Err_VerifyPhone_Password_Invalid { get; set; }

        //Verify Your Phone - Password - Passwords dont match -error
        [FindsBy(How = How.Id, Using = "confirm-password-error")]
        public IWebElement Err_VerifyPhone_ConfirmPassword { get; set; }
    }
}
