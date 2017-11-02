using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    public class Driver_LoginPagecs
    {
        public Driver_LoginPagecs(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }
        
        //Log In tab
        [FindsBy(How = How.Id, Using = "tablogin")]
        public IWebElement Tab_LogIn { get; set; }

        //Driver Login - Header
        [FindsBy(How = How.XPath, Using = "//div[@id='login']/h2")]
        public IWebElement Header_DriverLogin { get; set; }

        //Driver Login - Blank fields error
        [FindsBy(How = How.Id, Using = "loginerrorsummary")]
        public IWebElement Err_DriverLogin_Blank { get; set; }

        //Driver Login - Field validation error
        [FindsBy(How = How.XPath, Using = "//p[@id='loginValidationMessage']/span")]
        public IWebElement Err_DriverLogin_FieldValidation { get; set; }

        //Driver Login - Phone Field
        [FindsBy(How = How.XPath, Using = "//div[@id='login']/form/div/input[@id='phone']")]
        public IWebElement TextBox_DriverLogin_Phone { get; set; }

        //Driver Login - Phone Field - Error
        [FindsBy(How = How.Id, Using = "phone-error")]
        public IWebElement Err_DriverLogin_Phone { get; set; }

        //Driver Login - Password Field
        [FindsBy(How = How.XPath, Using = "//div[@id='login']/form/div/input[@id='password']")]
        public IWebElement TextBox_DriverLogin_Password { get; set; }

        //Driver Login - Login Button
        [FindsBy(How = How.XPath, Using = "//form[@id='Login']/button[contains(text(), 'LOG')]")]
        public IWebElement Button_DriverLogin { get; set; }

        //Password reset success message
        [FindsBy(How = How.Id, Using = "password-reset-success-summary")]
        public IWebElement Text_PasswordResetSuccess { get; set; }
    }
}
