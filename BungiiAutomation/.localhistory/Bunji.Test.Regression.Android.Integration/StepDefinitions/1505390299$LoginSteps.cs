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

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{


    [Binding]
    public class LoginSteps //:Core
    {
        
        
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
        LoginPageElements _LoginPage = new LoginPageElements(AndroidManager.androiddriver);
        SearchingPageElements _SearchingDriver = new SearchingPageElements(AndroidManager.androiddriver);
        CustomerHomePageElements _CustomerHome = new CustomerHomePageElements(AndroidManager.androiddriver);
        EstimatePageElements _Estimate = new EstimatePageElements(AndroidManager.androiddriver);
        TermsPageElements _Terms = new TermsPageElements(AndroidManager.androiddriver);
        CustomerData _Data = new CustomerData();
        SignupPageElements _SignupPage = new SignupPageElements(AndroidManager.androiddriver);
        MenuElements _Menu = new MenuElements(AndroidManager.androiddriver);


        [Given(@"I have launched the customer app")]
        public void GivenIHaveLaunchedTheCustomerApp()
        {
           // driver = Core.BungiiCustomerLaunchSamsung();

            //initializing page objects
            //PageFactory.InitElements(driver, _LoginPage);
            PageFactory.InitElements(driver, _SignupPage);
            PageFactory.InitElements(driver, _Terms);
            PageFactory.InitElements(driver, _CustomerHome);
           // PageFactory.InitElements(driver, _Estimate);
            PageFactory.InitElements(driver, _SearchingDriver);
        }

        

        [Given(@"I am on Sign up page")]
        public void GivenIAmOnSignUpPage()
        {
           driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(5));
           // _SignupPage
           Assert.IsTrue(_SignupPage.Button_Signup.Displayed);

        }

        [When(@"I tap on Login Link")]
        public void WhenITapOnLoginLink()
        {
           
            _SignupPage.LoginLink.Click();
        }

        [When(@"I set correct pickup location")]
        public void WhenISetCorrectPickupLocation()
        {
            WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 60));
            wait.Until(ExpectedConditions.ElementExists(By.Id("places_autocomplete_pickup_location")));  //here i used actual id, it does not throw error
            _CustomerHome.Button_MyLocation.Click();
           // driver.FindElementByAccessibilityId("My Location").Click();
            var Pickuplocation = _CustomerHome.Textbox_PickupLocation.Text;  //had to use the apium inspector generated id here as the actual one didnot work 
            Assert.IsTrue(_CustomerHome.Textbox_PickupLocation.Displayed);

            if (Pickuplocation == _Data.currentlocation)    //assuming current location is in Verna (office location)
            {
                _CustomerHome.Textbox_ActualDropoffLocation.Click();
               // driver.FindElement(By.Id("places_autocomplete_dropoff_location")).Click();
            }
           
        }

        [When(@"I set correct drop off location")]
        public void WhenISetCorrectDropOffLocation()
        {
            
            new WebDriverWait(driver, TimeSpan.FromSeconds(30)).Until(Iwebdriver => _CustomerHome.Textbox_ActualDropoffLocation.Displayed);
                     
           
             _CustomerHome.Textbox_ActualDropoffLocation.Clear();
            var Dropofflocation = _CustomerHome.Textbox_ActualDropoffLocation.Text;
            
            int count = 0;
            while (Dropofflocation == "" || Dropofflocation == "Set Drop Off Location" && count <= 5)
            {
                Thread.Sleep(1000);
                _CustomerHome.Textbox_ActualDropoffLocation.Clear();
                driver.HideKeyboard();
                _CustomerHome.Button_MyLocation.Click();
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
            WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 60));
           // wait.Until(ExpectedConditions.ElementExists(By.Id("pickup_location_get_estimate_button")));
            new WebDriverWait(driver, TimeSpan.FromSeconds(30)).Until(Iwebdriver => _CustomerHome.Button_GetEstimate.Displayed);
            _CustomerHome.Button_GetEstimate.Click();
        }

        [When(@"I Request a Bungii")]
        public void WhenIRequestABungii()
        {
            WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 60));

            _Estimate.Link_AddPhoto.Click();
            bool camerapermissions = _Estimate.Message_CameraPermissions.Displayed;
            if (camerapermissions == true)
            {
                _Estimate.Permissions_CameraAllow.Click();
            }
            _Estimate.Option_Camera.Click();
            Thread.Sleep(2000);
            var x = AndroidKeyCode.Keycode_CAMERA;
            Core.keyBoardEvent(x);  //doesnot work in Moto G
            Thread.Sleep(2000);


            //driver.FindElement(By.Id("com.sec.android.app.camera:id/retry")).Click();   //uncomment to retry
            _Estimate.Button_Camera_OK.Click();

            // bool requestbutton = _Estimate.Button_RequestBungii.Displayed;           // .displayed not able to find element by XPath provided
            bool requestbutton = false;//= isElementPresent(By.XPath("//android.widget.Button"));
            if (requestbutton == false)
            {

                var dimensions = driver.Manage().Window.Size;
                //Console.WriteLine(dimensions);
                Double screenHeightStart = dimensions.Height * 0.5;
                int scrollstart = Convert.ToInt32(screenHeightStart);
                Double screenHeightEnd = dimensions.Height * 0.2;
                int scrollend = Convert.ToInt32(screenHeightEnd);
                driver.Swipe(0, scrollstart, 0, scrollend, 1000);

            }
            _Estimate.Checkbox_AgreeEstimate.Click();
            _Estimate.Button_RequestBungii.Click();
            new WebDriverWait(driver, TimeSpan.FromSeconds(30)).Until(Iwebdriver => _Estimate.Alert_ConfirmRequest.Displayed);
           // wait.Until(ExpectedConditions.ElementExists(By.Id("android:id/message")));
            _Estimate.Button_RequestConfirm.Click();
        }

        
        [When(@"I enter Customer Phone Number and Password")]
        public void WhenIEnterCustomerPhoneNumberAndPassword()
        {
            _LoginPage.PhoneNumber.Clear();
            _LoginPage.PhoneNumber.SendKeys(_Data.CustomerPhonenumber);
            _LoginPage.PasswordField.Clear();
            _LoginPage.PasswordField.SendKeys(_Data.CustomerPassword);
            
        
        }

        [When(@"I click on the cancel link")]
        public void WhenIClickOnTheCancelLink()
        {
            WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
            wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='SEARCHING…']")));
            _SearchingDriver.Link_CancelSearch.Click();
            _SearchingDriver.Button_CancelConfirm.Click();
        }

        
        [When(@"I tap on Log in Button")]
        public void WhenITapOnLogInButton()
        {
          
            _LoginPage.Button_Login.Click();
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

        
        [Then(@"The user should login")]
        public void ThenTheUserShouldLogin()
        {
            WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 60));
            Thread.Sleep(5000);
            bool firstlaunch = _Terms.Checkbox_Agree.Displayed;

            if (firstlaunch == true)
            {
                _Terms.Checkbox_Agree.Click();
                _Terms.Button_Continue.Click();
                 wait.Until(ExpectedConditions.ElementExists(By.Id("com.android.packageinstaller:id/permission_message")));
                
                _Terms.Button_PermissionsAllow.Click();
            }
           
            Assert.IsTrue(_CustomerHome.Title_HomePage.Displayed);  //check if home page is opened
            Assert.IsTrue(_CustomerHome.Link_Invite.Displayed);      //check if invite link is present
        }

        [Then(@"Driver search should be cancelled")]
        public void ThenDriverSearchShouldBeCancelled()
        {
            WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
            wait.Until(ExpectedConditions.ElementIsVisible(By.Id("com.bungii.customer:id/toolbar_main_title")));
            Assert.IsTrue(_CustomerHome.Title_HomePage.Displayed); 
        }
        [When(@"I enter mandatory fields")]
        public void WhenIEnterMandatoryFields()
        {
            _SignupPage.Textbox_FirstName.SendKeys(_Data.CustomerFirstName);
            _SignupPage.Textbox_LastName.SendKeys(_Data.CustomerLastName);
            _SignupPage.Textbox_Email.SendKeys(_Data.Email);
            var CustomerPhoneNum = _Data.GetRandomTelNo();
             _SignupPage.Textbox_Phonenumber.SendKeys(CustomerPhoneNum);
             ScenarioContext.Current.Add("CustomerPhoneNum", CustomerPhoneNum);
            _SignupPage.Textbox_Password.SendKeys(_Data.CustomerPassword);
            driver.HideKeyboard();
            //_SignupPage.Textbox_Referral.SendKeys(_Data.ReferralCode);
            _SignupPage.Select_ReferralSource.Click();
            _SignupPage.Option_ReferralSource.Click();
            _SignupPage.Select_ReferralSourceDone.Click();
            Assert.IsTrue(_SignupPage.Button_Signup.Enabled);
           
        }

        [When(@"I tap on Sign Up")]
        public void WhenITapOnSignUp()
        {
            _SignupPage.Button_Signup.Click();
            _SignupPage.Button_NoReferralConfirm.Click();
            
        }
        //[Then(@"Verifcation page should open")]
        //public void ThenVerifcationPageShouldOpen()
        //{
        //    ScenarioContext.Current.Pending();
        //}

        [When(@"I enter Verification code")]
        public void WhenIEnterVerificationCode()
        {
            //getting code from db
            var PhoneNumber = ScenarioContext.Current.Get<String>("CustomerPhoneNum");
            //---connecting to database to get verification sms code--------------//
            string SMSCode = string.Empty;
            using (SqlConnection conn = new SqlConnection())
            {
                conn.ConnectionString = "Data Source=MANDOVI\\MSSQLSERVER2K14;Initial Catalog=BungiiQA;Persist Security Info=True;User ID=for_bungii;Password=forbungii290416";
                conn.Open();
                // Creating the command
                SqlCommand command = new SqlCommand("SELECT SmsVerificationCode FROM Customer WHERE Phone = @Phone", conn);
                // Adding the parameters.
                command.Parameters.Add(new SqlParameter("Phone", PhoneNumber));
                // Creating new SqlDataReader object and read data from the command.
                using (SqlDataReader reader = command.ExecuteReader())
                {
                    // while there is another record present
                    while (reader.Read())
                    {
                        SMSCode = reader["SmsVerificationCode"].ToString();
                    }
                }
                conn.Close();
            }
            _SignupPage.Textbox_SMSCode.Clear();
            _SignupPage.Textbox_SMSCode.SendKeys(SMSCode);
            _SignupPage.Button_VerifyContinue.Click();

        }
        [Then(@"I should be logged in")]
        public void ThenIShouldBeLoggedIn()
        {
            WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
            _Terms.Checkbox_Agree.Click();
            _Terms.Button_Continue.Click();
            wait.Until(ExpectedConditions.ElementExists(By.Id("com.android.packageinstaller:id/permission_message")));

            _Terms.Button_PermissionsAllow.Click();
            Assert.IsTrue(_CustomerHome.Title_HomePage.Displayed);
        }

        [Then(@"App closes")]
        public void ThenAppCloses()
        {
            driver.Quit();
        }
        */
    }
}
