using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium.Support.UI;
using System;
using System.Threading;
using TechTalk.SpecFlow;
using Bungii.Test.Regression.Android.Integration.Pages;
using OpenQA.Selenium.Interactions;
using OpenQA.Selenium.Support.PageObjects;
using System.Collections.Generic;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Integration.Framework.Core;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class CustomerMenuSteps //: Core
    {


      public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
         LoginPage _LoginPage = new LoginPage(AndroidManager.androiddriver);
         SearchingPage _SearchingDriver = new SearchingPage(AndroidManager.androiddriver);
         CustomerHomePage _CustomerHome = new CustomerHomePage(AndroidManager.androiddriver);
         EstimatePage _Estimate = new EstimatePage(AndroidManager.androiddriver);
         TermsPage _Terms = new TermsPage(AndroidManager.androiddriver);
        SaveMoneyPage _SaveMoney = new SaveMoneyPage(AndroidManager.androiddriver);
        CustomerData _Data = new CustomerData();
         SignupPage _SignupPage = new SignupPage(AndroidManager.androiddriver);
        SupportPage _SupportPage = new SupportPage(AndroidManager.androiddriver);
        MenuPage _Menu = new MenuPage(AndroidManager.androiddriver);
        PaymentPage _PaymentPage = new PaymentPage(AndroidManager.androiddriver);
        HomePage _HomePage = new HomePage(AndroidManager.androiddriver);
        FAQPage _FAQPage = new FAQPage(AndroidManager.androiddriver);

        AccountPage _AccountPage = new AccountPage(AndroidManager.androiddriver);
        UtilityFunctions _UtilityFunctions = new UtilityFunctions();

   
         [Given(@"I have launched the app")]
         public void GivenIHaveLaunchedTheApp()
         {
            AssertionManager.ElementDisplayed(_SignupPage.Button_Signup);
         }


         [Given(@"I am on Customer logged in Home page")]
         public void GivenIAmOnCustomerLoggedInHomePage()
         {         
            _UtilityFunctions.LoginToCustomerApp(_Data.CustomerPhonenumber, _Data.CustomerPassword);
           
             if (DriverAction.isElementPresent(_Terms.Checkbox_Agree))
             {
                DriverAction.Click(_Terms.Checkbox_Agree);
                DriverAction.Click(_Terms.Button_Continue);
                if (DriverAction.isElementPresent(_Terms.Popup_PermissionsMessage))
                { 
                    DriverAction.Click(_Terms.Button_PermissionsAllow);
                }
             }

            AssertionManager.ElementDisplayed(_CustomerHome.Title_HomePage);  //check if home page is opened
            AssertionManager.ElementDisplayed(_CustomerHome.Link_Invite);  
         }

       

        [When(@"I tap on ""(.*)"" > ""(.*)"" link")]
        public void WhenITapOnLink(string p0, string p1)
        {
            DriverAction.Click(_Menu.Homepage_Menu);
            switch(p1)
            {
                case "FAQ":
                    DriverAction.Click(_Menu.Link_FAQ);
                    break;
                case "Account":
                    DriverAction.Click(_Menu.Link_Account);
                    break;
                case "Payment":
                    DriverAction.Click(_Menu.Link_Payment);
                    break;
                case "Support":
                    DriverAction.Click(_Menu.Link_Support);
                    break;
                case "Save Money":
                    DriverAction.Click(_Menu.Link_SaveMoney);
                    break;
                case "Home":
                    DriverAction.Click(_Menu.Link_Home);
                    break;
                case "Logout":
                    DriverAction.Click(_Menu.Link_Logout);
                    break;
                default: break;

            }
        }

        [Then(@"""(.*)"" page should be opened")]
        public void ThenPageShouldBeOpened(string p0)
        {
            switch (p0)
            {
                case "FAQ":
                    AssertionManager.ElementDisplayed(_FAQPage.Header_FAQPage);
                    break;
                case "Account":
                    AssertionManager.ElementDisplayed(_AccountPage.Header_AccountPage);
                    break;
                case "Payment":
                    AssertionManager.ElementDisplayed(_PaymentPage.Header_PaymentPage);
                    break;
                case "Support":
                    AssertionManager.ElementDisplayed(_SupportPage.Header_SupportPage);
                    break;
                case "Save Money":
                    AssertionManager.ElementDisplayed(_SaveMoney.Header_SavePage);
                    break;
                case "Home":
                    AssertionManager.ElementDisplayed(_HomePage.Header_HomePage);
                    break;
                case "Logout":
                    AssertionManager.ElementDisplayed(_LoginPage.Header_LoginPage);
                    break;
                default: break;
            }
        }


    }
}
