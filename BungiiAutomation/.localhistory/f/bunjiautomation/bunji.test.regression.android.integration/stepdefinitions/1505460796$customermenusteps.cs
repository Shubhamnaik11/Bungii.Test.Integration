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
        ActionManager _ActionManager = new ActionManager();

   
         [Given(@"I have launched the app")]
         public void GivenIHaveLaunchedTheApp()
         {

         }


         [Given(@"I am on Customer logged in Home page")]
         public void GivenIAmOnCustomerLoggedInHomePage()
         {         
            _UtilityFunctions.LoginToCustomerApp(_Data.CustomerPhonenumber, _Data.CustomerPassword);
           
             if (_ActionManager.isElementPresent(_Terms.Checkbox_Agree))
             {
                 _Terms.Checkbox_Agree.Click();
                 _Terms.Button_Continue.Click();
                _ActionManager.WaitUntilIsElementExistsAndDisplayed(_Terms.Popup_PermissionsMessage);
                 _Terms.Button_PermissionsAllow.Click();
             }

            AssertionManager.ElementDisplayed(_CustomerHome.Title_HomePage);  //check if home page is opened
            AssertionManager.ElementDisplayed(_CustomerHome.Link_Invite);  
         }

         [Given(@"I tap on Menu Icon")]
         public void GivenITapOnMenuIcon()
         {
            _ActionManager.WaitUntilIsElementExistsAndDisplayed(_Menu.Homepage_Menu);
            _Menu.Homepage_Menu.Click();
         }


         [Given(@"I open the slider Menu")]
         public void GivenIOpenTheSliderMenu()
         {
            AssertionManager.ElementDisplayed(_HomePage.Link_Slider);
            _HomePage.Link_Slider.Click();
         }

         [Given(@"I tap on FAQ link")]
         public void GivenITapOnFAQLink()
         {
            AssertionManager.ElementDisplayed(_Menu.Link_FAQ);
            _Menu.Link_FAQ.Click();
        }

         [When(@"I tap on Menu Icon")]
         public void WhenITapOnMenuIcon()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementExists(By.ClassName("android.widget.ImageButton")));
             driver.FindElement(By.ClassName("android.widget.ImageButton")).Click();
         }

         [When(@"I tap on Account link")]
         public void WhenITapOnAccountLink()
         {
            AssertionManager.ElementDisplayed(_Menu.Link_Account);
            _Menu.Link_Account.Click();
        }

         [When(@"I tap on Payment link")]
         public void WhenITapOnPaymentLink()
         {
            AssertionManager.ElementDisplayed(_Menu.Link_Payment);
            _Menu.Link_Payment.Click();
        }

         [When(@"I tap on Support link")]
         public void WhenITapOnSupportLink()
         {
            AssertionManager.ElementDisplayed(_Menu.Link_Support);
            _Menu.Link_Support.Click();
        }
         [When(@"I tap on Home link")]
         public void WhenITapOnHomeLink()
         {
            AssertionManager.ElementDisplayed(_Menu.Link_Home);
            _Menu.Link_Home.Click();
        }


         [When(@"I tap on Save Money link")]
         public void WhenITapOnSaveMoneyLink()
         {
            AssertionManager.ElementDisplayed(_Menu.Link_SaveMoney);
            _Menu.Link_SaveMoney.Click();
        }

         [When(@"I tap on Logout Link")]
         public void WhenITapOnLogoutLink()
         {
            AssertionManager.ElementDisplayed(_Menu.Link_Logout);
            _Menu.Link_Logout.Click();
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
            AssertionManager.ElementDisplayed(_PaymentPage.Header_SupportPage);
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
