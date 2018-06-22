using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Integration.Framework.Core.AndroidDriver;
using Bungii.Test.Regression.Android.Integration.Pages.DriverPages;
using OpenQA.Selenium;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;

namespace Bungii.Test.Regression.Android.Integration.Functions
{
    public class DriverAppFunctions
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager_DriverApp.androiddriver_Driver;
        Driver_LoginPage Page_DriverLogin = new Driver_LoginPage(AndroidManager_DriverApp.androiddriver_Driver);

        public void LoginToDriverApp(string phone, string password)
        {
            driver.FindElement(By.Id("com.bungii.driver:id/field_phone")).SendKeys("8888881010");
            DriverAction_DriverApp.SendKeys(Page_DriverLogin.TextField_PhoneNumber, phone);
            DriverAction_DriverApp.SendKeys(Page_DriverLogin.TextField_Password, password);
            DriverAction_DriverApp.Click(Page_DriverLogin.Button_Login);

        }
    }
}
