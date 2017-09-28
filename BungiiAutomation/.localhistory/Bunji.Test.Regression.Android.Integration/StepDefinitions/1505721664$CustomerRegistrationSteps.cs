using System;
using Bungii.Test.Integration.Framework.Core;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class CustomerRegistrationSteps
    {

        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
        LoginPage _LoginPage = new LoginPage(AndroidManager.androiddriver);
        SearchingPage _SearchingDriver = new SearchingPage(AndroidManager.androiddriver);
        CustomerHomePage _CustomerHome = new CustomerHomePage(AndroidManager.androiddriver);
        EstimatePage _Estimate = new EstimatePage(AndroidManager.androiddriver);
        TermsPage _Terms = new TermsPage(AndroidManager.androiddriver);
        CustomerData _Data = new CustomerData();
        SignupPage _SignupPage = new SignupPage(AndroidManager.androiddriver);
        MenuPage _Menu = new MenuPage(AndroidManager.androiddriver);
        UtilityFunctions _UtilityFunctions = new UtilityFunctions();

        [When(@"I enter the ""(.*)"" mandatory fields")]
        public void WhenIEnterTheMandatoryFields(string p0)
        {
            switch (p0)
            {
                case "valid":
                    {
                        _SignupPage.Textbox_FirstName.SendKeys(_Data.CustomerFirstName);
                        _SignupPage.Textbox_LastName.SendKeys(_Data.CustomerLastName);
                        _SignupPage.Textbox_Email.SendKeys(_Data.Email);
                        var CustomerPhoneNum = _Data.GetRandomTelNo();
                        _SignupPage.Textbox_Phonenumber.SendKeys(CustomerPhoneNum);
                        DriverAction.AddValueToScenarioContextVariable("CustomerPhoneNum", CustomerPhoneNum);
                        _SignupPage.Textbox_Password.SendKeys(_Data.CustomerPassword);
                        _UtilityFunctions.HideKeyboard();
                        _SignupPage.Select_ReferralSource.Click();
                        _SignupPage.Option_ReferralSource.Click();
                        _SignupPage.Select_ReferralSourceDone.Click();
                        AssertionManager.ElementEnabled(_SignupPage.Button_Signup);
                        break;
                    }
                default: break;
            }
        }
        
        [When(@"I tap on the ""(.*)"" button")]
        public void WhenITapOnTheButton(string p0)
        {
            DriverAction.Click(_SignupPage.Button_Signup);
            DriverAction.Click(_SignupPage.Button_NoReferralConfirm);
        }
        
        [When(@"I enter ""(.*)"" code")]
        public void WhenIEnterCode(string p0)
        {
            string SMSCode = _UtilityFunctions.GetVerificationCode(DriverAction.GetValueFromScenarioContextVariable("CustomerPhoneNum"));
            DriverAction.SendKeys(_SignupPage.Textbox_SMSCode, SMSCode);
            _SignupPage.Button_VerifyContinue.Click();
        }
        
        [Then(@"I should be logged in to the app")]
        public void ThenIShouldBeLoggedInToTheApp()
        {       
            DriverAction.Click(_Terms.Checkbox_Agree);
            DriverAction.Click(_Terms.Button_Continue);
            DriverAction.WaitUntilIsElementExistsAndDisplayed(_Terms.Popup_PermissionsMessage);
            DriverAction.Click(_Terms.Button_PermissionsAllow);
            DriverAction.WaitUntilIsElementExistsAndDisplayed(_CustomerHome.Title_HomePage);
            AssertionManager.ElementDisplayed(_CustomerHome.Title_HomePage);
        }

    }
}
