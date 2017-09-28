using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using System.Data.SqlClient;
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
using System.Runtime.InteropServices;
using Bungii.Test.Integration.Framework.Core;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{


    [Binding]
    public class LoginSteps //:Core
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
     
        [Given(@"I am on Sign up page")]
        public void GivenIAmOnSignUpPage()
        {
           DriverAction.WaitUntilIsElementExistsAndDisplayed(_SignupPage.Button_Signup);
           AssertionManager.ElementDisplayed(_SignupPage.Button_Signup);
        }


        [When(@"I set correct pickup location")]
        public void WhenISetCorrectPickupLocation()
        {
            WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 60));
            wait.Until(ExpectedConditions.ElementExists(By.Id("places_autocomplete_pickup_location")));  //here i used actual id, it does not throw error
            DriverAction.Click(_CustomerHome.Button_MyLocation);
           // driver.FindElementByAccessibilityId("My Location").Click();
            var Pickuplocation = _CustomerHome.Textbox_PickupLocation.Text;  //had to use the apium inspector generated id here as the actual one didnot work 
            Assert.IsTrue(_CustomerHome.Textbox_PickupLocation.Displayed);

            if (Pickuplocation == _Data.currentlocation)    //assuming current location is in Verna (office location)
            {
                DriverAction.Click(_CustomerHome.Textbox_ActualDropoffLocation);
               // driver.FindElement(By.Id("places_autocomplete_dropoff_location")).Click();
            }
           
        }

        [When(@"I set correct drop off location")]
        public void WhenISetCorrectDropOffLocation()
        {
            
            new WebDriverWait(driver, TimeSpan.FromSeconds(30)).Until(Iwebdriver => _CustomerHome.Textbox_ActualDropoffLocation.Displayed);


            DriverAction.Clear(_CustomerHome.Textbox_ActualDropoffLocation);
            var Dropofflocation = _CustomerHome.Textbox_ActualDropoffLocation.Text;
            
            int count = 0;
            while (Dropofflocation == "" || Dropofflocation == "Set Drop Off Location" && count <= 5)
            {
                Thread.Sleep(1000);
                _CustomerHome.Textbox_ActualDropoffLocation.Clear();
                //driver.HideKeyboard();
                DriverAction.Click(_CustomerHome.Button_MyLocation);
                //driver.FindElementByAccessibilityId("My Location").Click();
                IList<AndroidElement> els = driver.FindElementsByClassName("android.widget.FrameLayout");
                var loc1 = els[4].Location;
                AppiumWebElement target = els[1];
                var loc2 = target.Location;
                driver.Swipe(loc1.X, loc1.Y, loc2.X, loc2.Y, 800); //this action includes almost all touch actions

               // bool ETApresent = isElementPresent(By.Id("com.bungii.customer:id/eta_bar_textview_estimate"));
                _CustomerHome.Button_ETASet.Click();
                Thread.Sleep(3000);
                Dropofflocation = _CustomerHome.Textbox_DropoffLocation.Text;
                var allelements = driver.FindElements(By.Id("com.bungii.customer:id/autocomplete_textview"));
                var Dropofflocation2 = "";
                foreach (var elem in allelements)
                {
                    Dropofflocation2 = elem.Text;
                }

                if (Dropofflocation2 != "")
                {
                    Dropofflocation = Dropofflocation2;
                }


                count++;
            }

        }

        [When(@"I tap on Get Estimate button")]
        public void WhenITapOnGetEstimateButton()
        {
           // WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 60));
           // wait.Until(ExpectedConditions.ElementExists(By.Id("pickup_location_get_estimate_button")));
           // new WebDriverWait(driver, TimeSpan.FromSeconds(30)).Until(Iwebdriver => _CustomerHome.Button_GetEstimate.Displayed);
            DriverAction.Click(_CustomerHome.Button_GetEstimate);
        }

        [When(@"I Request a Bungii")]
        public void WhenIRequestABungii()
        {

            DriverAction.Click(_Estimate.Link_AddPhoto);
           // bool camerapermissions = _Estimate.Message_CameraPermissions.Displayed;
            if (DriverAction.isElementPresent(_Estimate.Message_CameraPermissions))
            {
                DriverAction.Click(_Estimate.Permissions_CameraAllow);
            }
            DriverAction.Click(_Estimate.Option_Camera);
            Thread.Sleep(2000);

            DriverAction.keyBoardEvent(AndroidKeyCode.Keycode_CAMERA);  //doesnot work in Moto G
            Thread.Sleep(2000);


            //driver.FindElement(By.Id("com.sec.android.app.camera:id/retry")).Click();   //uncomment to retry
            DriverAction.Click(_Estimate.Button_Camera_OK);
            Thread.Sleep(5000);
            // bool requestbutton = _Estimate.Button_RequestBungii.Displayed;           // .displayed not able to find element by XPath provided
           // bool requestbutton = false;//= isElementPresent(By.XPath("//android.widget.Button"));
            if (!DriverAction.isElementPresent(driver.FindElement(By.XPath("//android.widget.Button"))))
            {

                _UtilityFunctions.ScrollToBottom();

            }
            DriverAction.Click(_Estimate.Checkbox_AgreeEstimate);
            DriverAction.Click(_Estimate.Button_RequestBungii);
            new WebDriverWait(driver, TimeSpan.FromSeconds(30)).Until(Iwebdriver => _Estimate.Alert_ConfirmRequest.Displayed);
            // wait.Until(ExpectedConditions.ElementExists(By.Id("android:id/message")));
            DriverAction.Click(_Estimate.Button_RequestConfirm);
        }

        
        [When(@"I enter Customer Phone Number and Password")]
        public void WhenIEnterCustomerPhoneNumberAndPassword()
        {
            DriverAction.SendKeys(_LoginPage.PhoneNumber,_Data.CustomerPhonenumber);
            DriverAction.SendKeys(_LoginPage.PasswordField, _Data.CustomerPassword);
        }

        [When(@"I click on the cancel link")]
        public void WhenIClickOnTheCancelLink()
        {
            WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
            wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='SEARCHING…']")));
            DriverAction.Click(_SearchingDriver.Link_CancelSearch);
            DriverAction.Click(_SearchingDriver.Button_CancelConfirm);
        }

    

        [Then(@"Get Estimate page should be displayed")]
        public void ThenGetEstimatePageShouldBeDisplayed()
        {
            WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
            wait.Until(ExpectedConditions.ElementIsVisible(By.Id("com.bungii.customer:id/toolbarEstimate")));
            wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='ESTIMATE']")));
            //click to add promo code
            wait.Until(ExpectedConditions.ElementIsVisible(By.Id("com.bungii.customer:id/estimate_value_promo")));
            driver.FindElement(By.Id("com.bungii.customer:id/estimate_value_promo")).Click();
            driver.Navigate().Back();
        }

        
      

        [Then(@"Driver search should be cancelled")]
        public void ThenDriverSearchShouldBeCancelled()
        {
            WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
            wait.Until(ExpectedConditions.ElementIsVisible(By.Id("com.bungii.customer:id/toolbar_main_title")));
            AssertionManager.ElementDisplayed(_CustomerHome.Title_HomePage); 
        }
        [When(@"I enter mandatory fields")]
        public void WhenIEnterMandatoryFields()
        {
            _SignupPage.Textbox_FirstName.SendKeys(_Data.CustomerFirstName);
            _SignupPage.Textbox_LastName.SendKeys(_Data.CustomerLastName);
            _SignupPage.Textbox_Email.SendKeys(_Data.Email);
            var CustomerPhoneNum = _Data.GetRandomTelNo();
             _SignupPage.Textbox_Phonenumber.SendKeys(CustomerPhoneNum);
             DriverAction.AddValueToScenarioContextVariable("CustomerPhoneNum", CustomerPhoneNum);
            _SignupPage.Textbox_Password.SendKeys(_Data.CustomerPassword);
            _UtilityFunctions.HideKeyboard();
            //_SignupPage.Textbox_Referral.SendKeys(_Data.ReferralCode);
            _SignupPage.Select_ReferralSource.Click();
            _SignupPage.Option_ReferralSource.Click();
            _SignupPage.Select_ReferralSourceDone.Click();
            AssertionManager.ElementEnabled(_SignupPage.Button_Signup);
           
        }

        [When(@"I tap on Sign Up")]
        public void WhenITapOnSignUp()
        {
            DriverAction.Click(_SignupPage.Button_Signup);
            DriverAction.Click(_SignupPage.Button_NoReferralConfirm);           
        }
     

        [When(@"I enter Verification code")]
        public void WhenIEnterVerificationCode()
        {
            string SMSCode = _UtilityFunctions.GetVerificationCode(DriverAction.GetValueFromScenarioContextVariable("CustomerPhoneNum"));
            DriverAction.SendKeys(_SignupPage.Textbox_SMSCode,SMSCode);
            _SignupPage.Button_VerifyContinue.Click();

        }
        [Then(@"I should be logged in")]
        public void ThenIShouldBeLoggedIn()
        {
            // WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
            DriverAction.Click(_Terms.Checkbox_Agree);
            DriverAction.Click(_Terms.Button_Continue);
            // wait.Until(ExpectedConditions.ElementExists(By.Id("com.android.packageinstaller:id/permission_message")));
            DriverAction.WaitUntilIsElementExistsAndDisplayed(_Terms.Popup_PermissionsMessage);
            DriverAction.Click(_Terms.Button_PermissionsAllow);
            DriverAction.WaitUntilIsElementExistsAndDisplayed(_CustomerHome.Title_HomePage);
            AssertionManager.ElementDisplayed(_CustomerHome.Title_HomePage);
        }

        [When(@"I tap on the ""(.*)"" Link")]
        public void WhenITapOnTheLink(string p0)
        {
            switch (p0)
            {
                case "Login":
                    DriverAction.Click(_SignupPage.LoginLink);
                    break;
                default: break;
            }
        }

        [When(@"I enter customers ""(.*)"" Phone Number")]
        public void WhenIEnterCustomersPhoneNumber(string p0)
        {
            switch (p0)
            {
                case "valid":
                    DriverAction.SendKeys(_LoginPage.PhoneNumber, _Data.CustomerPhonenumber);
                    break;
                case "invalid":
                    DriverAction.SendKeys(_LoginPage.PhoneNumber, _Data.InvalidCustomerPhonenumber);
                    break;
                default: break;
            }
        }

        [When(@"I enter customers ""(.*)"" Password")]
        public void WhenIEnterCustomersPassword(string p0)
        {
            switch (p0)
            {
                case "valid":
                    DriverAction.SendKeys(_LoginPage.PasswordField, _Data.CustomerPassword);
                    break;
                case "invalid":
                    DriverAction.SendKeys(_LoginPage.PasswordField, _Data.InvalidCustomerPassword);
                    break;
                default: break;
            }
        }

        [When(@"I tap on the ""(.*)"" Button")]
        public void WhenITapOnTheButton(string p0)
        {
            switch (p0)
            {
                case "Log in":
                    DriverAction.Click(_LoginPage.Button_Login);
                    break;
                default: break;
            }
            }

        [Then(@"The user should be logged in")]
        public void ThenTheUserShouldBeLoggedIn()
        {
            if (DriverAction.isElementPresent(_Terms.Checkbox_Agree))
            {
                DriverAction.Click(_Terms.Checkbox_Agree);
                DriverAction.Click(_Terms.Button_Continue);
                if (DriverAction.isElementPresent(_Terms.Popup_PermissionsMessage))
                {
                    DriverAction.Click(_Terms.Button_PermissionsAllow);
                }
                DriverAction.Click(_Terms.Button_PermissionsAllow);
            }

            AssertionManager.ElementDisplayed(_CustomerHome.Title_HomePage);  //check if home page is opened
            AssertionManager.ElementDisplayed(_CustomerHome.Link_Invite);
        }

        [Then(@"The user should receive ""(.*)"" message")]
        public void ThenTheUserShouldReceiveMessage(string p0)
        {
            ScenarioContext.Current.Pending();
        }



    }
}
