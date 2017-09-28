using System;
using System.Collections.Generic;
using System.Threading;
using Bungii.Test.Integration.Framework.Core;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Support.UI;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class BungiiEstimatesSteps
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

        [Given(@"I am logged in as a customer")]
        public void GivenIAmLoggedInAsACustomer()
        {
             _UtilityFunctions.LoginToCustomerApp(_Data.CustomerPhonenumber, _Data.CustomerPassword);

        }
        
        [When(@"I set correct ""(.*)"" location")]
        public void WhenISetCorrectLocation(string p0)
        {
            switch(p0)
            {
                case "pickup":
                    {
                       // WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 60));
                       // wait.Until(ExpectedConditions.ElementExists(By.Id("places_autocomplete_pickup_location")));  //here i used actual id, it does not throw error
                        DriverAction.Click(_CustomerHome.Button_MyLocation);
                        Assert.IsTrue(_CustomerHome.Textbox_PickupLocation.Displayed);
                        if (_CustomerHome.Textbox_PickupLocation.Text == _Data.currentlocation)    //assuming current location is in Verna (office location)
                        {
                            DriverAction.Click(_CustomerHome.Textbox_ActualDropoffLocation);
                        }
                        break;
                    }
                case "drop off":
                    {
                        new WebDriverWait(driver, TimeSpan.FromSeconds(30)).Until(Iwebdriver => _CustomerHome.Textbox_ActualDropoffLocation.Displayed);


                        DriverAction.Clear(_CustomerHome.Textbox_ActualDropoffLocation);
                        var Dropofflocation = _CustomerHome.Textbox_ActualDropoffLocation.Text;

                        int count = 0;
                        while (Dropofflocation == "" || Dropofflocation == "Set Drop Off Location" && count <= 5)
                        {
                            Thread.Sleep(1000);
                            _CustomerHome.Textbox_ActualDropoffLocation.Clear();
                            DriverAction.Click(_CustomerHome.Button_MyLocation);
                            IList<AndroidElement> els = driver.FindElementsByClassName("android.widget.FrameLayout");
                            var loc1 = els[4].Location;
                            AppiumWebElement target = els[1];
                            var loc2 = target.Location;
                            driver.Swipe(loc1.X, loc1.Y, loc2.X, loc2.Y, 800); //this action includes almost all touch actions

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
                        break;
                    }
                default: break;
            }
        }
        
        [When(@"I click on the ""(.*)"" link")]
        public void WhenIClickOnTheLink(string p0)
        {
            switch (p0)
            {
                case "cancel":
                    {
                        WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
                        wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='SEARCHING…']")));
                        DriverAction.Click(_SearchingDriver.Link_CancelSearch);
                        DriverAction.Click(_SearchingDriver.Button_CancelConfirm);
                        break;
                    }
                default: break;

                      }
                    }
        
        [Then(@"the ""(.*)"" page should be displayed")]
        public void ThenThePageShouldBeDisplayed(string p0)
        {
            switch (p0)
            {
                case "Get Estimate":
                    {
                        WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
                        wait.Until(ExpectedConditions.ElementIsVisible(By.Id("com.bungii.customer:id/toolbarEstimate")));
                        wait.Until(ExpectedConditions.ElementIsVisible(By.XPath("//android.widget.TextView[@text='ESTIMATE']")));
                        wait.Until(ExpectedConditions.ElementIsVisible(By.Id("com.bungii.customer:id/estimate_value_promo")));
                        driver.FindElement(By.Id("com.bungii.customer:id/estimate_value_promo")).Click();
                        driver.Navigate().Back();
                        break;
                    }
                default: break;
            }
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
            DriverAction.Click(_Estimate.Button_RequestConfirm);
        }

        [Then(@"Driver search should be cancelled")]
        public void ThenDriverSearchShouldBeCancelled()
        {
           // WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
           // wait.Until(ExpectedConditions.ElementIsVisible(By.Id("com.bungii.customer:id/toolbar_main_title")));
            AssertionManager.ElementDisplayed(_CustomerHome.Title_HomePage);
        }
    }
}
