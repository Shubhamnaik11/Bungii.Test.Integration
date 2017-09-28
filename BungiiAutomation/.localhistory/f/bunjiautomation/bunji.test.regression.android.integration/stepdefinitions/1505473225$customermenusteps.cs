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
                DriverAction.WaitUntilIsElementExistsAndDisplayed(_Terms.Popup_PermissionsMessage);
                DriverAction.Click(_Terms.Button_PermissionsAllow);
             }

            AssertionManager.ElementDisplayed(_CustomerHome.Title_HomePage);  //check if home page is opened
            AssertionManager.ElementDisplayed(_CustomerHome.Link_Invite);  
         }

         [Given(@"I tap on Menu Icon")]
         public void GivenITapOnMenuIcon()
         {
            DriverAction.Click(_Menu.Homepage_Menu);
         }


         [Given(@"I open the slider Menu")]
         public void GivenIOpenTheSliderMenu()
         {
            DriverAction.Click(_HomePage.Link_Slider);
         }

         [Given(@"I tap on FAQ link")]
         public void GivenITapOnFAQLink()
         {
            DriverAction.Click(_Menu.Link_FAQ);
        }

         [When(@"I tap on Menu Icon")]
         public void WhenITapOnMenuIcon()
         {
            DriverAction.Click(_HomePage.Link_Menu);         
         }

         [When(@"I tap on Account link")]
         public void WhenITapOnAccountLink()
         {
            DriverAction.Click(_Menu.Link_Account);
        }

         [When(@"I tap on Payment link")]
         public void WhenITapOnPaymentLink()
         {
            DriverAction.Click(_Menu.Link_Payment);
        }

         [When(@"I tap on Support link")]
         public void WhenITapOnSupportLink()
         {
            DriverAction.Click(_Menu.Link_Support);
        }
         [When(@"I tap on Home link")]
         public void WhenITapOnHomeLink()
         {
            DriverAction.Click(_Menu.Link_Home);
        }


         [When(@"I tap on Save Money link")]
         public void WhenITapOnSaveMoneyLink()
         {
            DriverAction.Click(_Menu.Link_SaveMoney);
        }

         [When(@"I tap on Logout Link")]
         public void WhenITapOnLogoutLink()
         {
            DriverAction.Click(_Menu.Link_Logout);
         }

         [Then(@"FAQ page should be opened")]
         public void ThenFAQPageShouldBeOpened()
         {
            AssertionManager.ElementDisplayed(_FAQPage.Header_FAQPage);

         }

         [Then(@"Account page should be opened")]
         public void ThenAccountPageShouldBeOpened()
         {
            AssertionManager.ElementDisplayed(_AccountPage.Header_AccountPage);
         }

         [Then(@"Home page should be opened")]
         public void ThenHomePageShouldBeOpened()
         {
            AssertionManager.ElementDisplayed(_HomePage.Header_HomePage);
         }

         [Then(@"Payment page should be opened")]
         public void ThenPaymentPageShouldBeOpened()
         {
            AssertionManager.ElementDisplayed(_PaymentPage.Header_PaymentPage);
         }

         [Then(@"Support page should be opened")]
         public void ThenSupportPageShouldBeOpened()
         {
            AssertionManager.ElementDisplayed(_SupportPage.Header_SupportPage);
         }

         [Then(@"Save money page should be opened")]
         public void ThenSaveMoneyPageShouldBeOpened()
         {
            AssertionManager.ElementDisplayed(_SaveMoney.Header_SavePage);            
         }

         [Then(@"Customer should be logged out")]
         public void ThenCustomerShouldBeLoggedOut()
         {
            AssertionManager.ElementDisplayed(_LoginPage.Header_LoginPage);
         }
    }
}
