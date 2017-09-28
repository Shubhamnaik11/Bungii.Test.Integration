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
         CustomerData _Data = new CustomerData();
         SignupPage _SignupPage = new SignupPage(AndroidManager.androiddriver);
         MenuPage _Menu = new MenuPage(AndroidManager.androiddriver);
        UtilityFunctions _UtilityFunctions = new UtilityFunctions();
        ActionManager _ActionManager = new ActionManager();
         [Given(@"I have launched the app")]
         public void GivenIHaveLaunchedTheApp()
         {

         }


         [Given(@"I am on Customer logged in Home page")]
         public void GivenIAmOnCustomerLoggedInHomePage()
         {

             driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(5));
            // _SignupPage
         
            _UtilityFunctions.LoginToCustomerApp(_Data.CustomerPhonenumber, _Data.CustomerPassword);
           
             if (_ActionManager.isElementPresent(_Terms.Checkbox_Agree))
             {
                 _Terms.Checkbox_Agree.Click();
                 _Terms.Button_Continue.Click();
                _ActionManager.WaitUntilIsElementExistsAndDisplayed(_Terms.Popup_PermissionsMessage);
               //  wait.Until(ExpectedConditions.ElementExists(By.Id("com.android.packageinstaller:id/permission_message")));

                 _Terms.Button_PermissionsAllow.Click();
             }

             AssertionManager.ElementDisplayed(_CustomerHome.Title_HomePage);  //check if home page is opened
            AssertionManager.ElementDisplayed(_CustomerHome.Link_Invite);  
         }

         [Given(@"I tap on Menu Icon")]
         public void GivenITapOnMenuIcon()
         {
             //WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
            // wait.Until(ExpectedConditions.ElementExists(By.ClassName("android.widget.ImageButton")));
            _ActionManager.WaitUntilIsElementExistsAndDisplayed(_Menu.Homepage_Menu);
             _Menu.Homepage_Menu.Click();

         }


         [Given(@"I open the slider Menu")]
         public void GivenIOpenTheSliderMenu()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.Id("com.bungii.customer:id/header_textview_username")));
             driver.FindElement(By.Id("com.bungii.customer:id/header_textview_username")).Click();
         }

         [Given(@"I tap on FAQ link")]
         public void GivenITapOnFAQLink()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.CheckedTextView[@text='FAQ']")));
             driver.FindElement(By.XPath("//android.widget.CheckedTextView[@text='FAQ']")).Click();
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
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.CheckedTextView[@text='ACCOUNT']")));
             driver.FindElement(By.XPath("//android.widget.CheckedTextView[@text='ACCOUNT']")).Click();
         }

         [When(@"I tap on Payment link")]
         public void WhenITapOnPaymentLink()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.CheckedTextView[@text='PAYMENT']")));
             driver.FindElement(By.XPath("//android.widget.CheckedTextView[@text='PAYMENT']")).Click();
         }

         [When(@"I tap on Support link")]
         public void WhenITapOnSupportLink()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.CheckedTextView[@text='SUPPORT']")));
             driver.FindElement(By.XPath("//android.widget.CheckedTextView[@text='SUPPORT']")).Click();
         }
         [When(@"I tap on Home link")]
         public void WhenITapOnHomeLink()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.CheckedTextView[@text='HOME']")));
             driver.FindElement(By.XPath("//android.widget.CheckedTextView[@text='HOME']")).Click();
         }


         [When(@"I tap on Save Money link")]
         public void WhenITapOnSaveMoneyLink()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.CheckedTextView[@text='SAVE MONEY']")));
             driver.FindElement(By.XPath("//android.widget.CheckedTextView[@text='SAVE MONEY']")).Click();
         }

         [When(@"I tap on Logout Link")]
         public void WhenITapOnLogoutLink()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.CheckedTextView[@text='LOGOUT']")));
             driver.FindElement(By.XPath("//android.widget.CheckedTextView[@text='LOGOUT']")).Click();
         }

         [Then(@"FAQ page should be opened")]
         public void ThenFAQPageShouldBeOpened()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='FAQ']")));
           //  Assert.IsTrue(isElementPresent(By.XPath("//android.widget.TextView[@text='FAQ']")));
         }

         [Then(@"Account page should be opened")]
         public void ThenAccountPageShouldBeOpened()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='ACCOUNT']")));
           //  Assert.IsTrue(isElementPresent(By.XPath("//android.widget.TextView[@text='ACCOUNT']")));
         }

         [Then(@"Home page should be opened")]
         public void ThenHomePageShouldBeOpened()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             //         wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='HOME']")));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='BUNGII']")));
            // Assert.IsTrue(isElementPresent(By.XPath("//android.widget.TextView[@text='BUNGII']")));
         }

         [Then(@"Payment page should be opened")]
         public void ThenPaymentPageShouldBeOpened()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='PAYMENT']")));
            // Assert.IsTrue(isElementPresent(By.XPath("//android.widget.TextView[@text='PAYMENT']")));
         }

         [Then(@"Support page should be opened")]
         public void ThenSupportPageShouldBeOpened()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='SUPPORT']")));
           //  Assert.IsTrue(isElementPresent(By.XPath("//android.widget.TextView[@text='SUPPORT']")));
         }

         [Then(@"Save money page should be opened")]
         public void ThenSaveMoneyPageShouldBeOpened()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='SAVE MONEY']")));
        //     Assert.IsTrue(isElementPresent(By.XPath("//android.widget.TextView[@text='SAVE MONEY']")));
         }

         [Then(@"Customer should be logged out")]
         public void ThenCustomerShouldBeLoggedOut()
         {
             WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
             wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='LOGIN']")));
           //  Assert.IsTrue(isElementPresent(By.XPath("//android.widget.TextView[@text='LOGIN']")));
             driver.Quit();
         }
    }
}
