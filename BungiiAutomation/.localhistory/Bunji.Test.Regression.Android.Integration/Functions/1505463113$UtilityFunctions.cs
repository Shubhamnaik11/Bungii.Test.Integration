using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Bungii.Test.Integration.Framework.Core;
using Bungii.Test.Regression.Android.Integration.Pages;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;

namespace Bungii.Test.Regression.Android.Integration.Functions
{
    public class UtilityFunctions
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
        LoginPage _LoginPage = new LoginPage(AndroidManager.androiddriver);
        SignupPage _SignupPage = new SignupPage(AndroidManager.androiddriver);
        ActionManager _ActionManager = new ActionManager();
        public void LoginToCustomerApp(string username, string password)
        {
            _ActionManager.WaitUntilIsElementExistsAndDisplayed(_SignupPage.LoginLink);
            _SignupPage.LoginLink.Click();
            _ActionManager.WaitUntilIsElementExistsAndDisplayed(_LoginPage.PhoneNumber);
            _LoginPage.PhoneNumber.Clear();
            _LoginPage.PhoneNumber.SendKeys(username);
            _LoginPage.PasswordField.Clear();
            _LoginPage.PasswordField.SendKeys(password);
            _LoginPage.Button_Login.Click();
        }
    }
}
