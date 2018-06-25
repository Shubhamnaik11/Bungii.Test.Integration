using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.DriverPages
{
    public class Driver_LoginPage
    {
        public Driver_LoginPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        // Phone Number field
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/field_phone")]
        public IWebElement TextField_PhoneNumber { get; set; }

        //Password field
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/field_password")]
        public IWebElement TextField_Password { get; set; }

        //Log In button
        [FindsBy(How = How.Id, Using = "com.bungii.driver:id/loginGlobalButton")]
        public IWebElement Button_Login { get; set; }
    }
}
