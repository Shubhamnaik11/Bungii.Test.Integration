﻿using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Integration.Framework.Core.AndroidDriver;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages.DriverPages;
using Bungii.Test.Regression.Android.Integration.Pages.OtherApps;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using System.Configuration;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class DriverActionsSteps
    {
        public AppiumDriver<AndroidElement> androiddriver_Driver = null;
        Driver_LoginPage Page_DriverLogin = null;
        Driver_HomePage Page_DriverHome = null;
        Driver_AvailableTripsPage Page_AvailableTrips = null;
        BungiiRequest Page_BungiiRequest = null;
        Driver_InProgressBungiiPages Page_BungiiProgress = null;
        Driver_BungiiCompletedPage Page_BungiiComplete = null;
        OtherAppsPage Page_OtherApps = null;
        Driver_PermissionsPage Page_Driver_Permissions = null;
        Data_Driver Data_Driver = new Data_Driver();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();
        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        private static string DriverAppdeviceType = ConfigurationManager.AppSettings["DriverAppdeviceType"];

        public DriverActionsSteps()
        {
            AndroidManager_DriverApp.InitializeDriver();
            androiddriver_Driver = AndroidManager_DriverApp.androiddriver_Driver;
            Page_DriverLogin = new Driver_LoginPage(AndroidManager_DriverApp.androiddriver_Driver);
            Page_DriverHome = new Driver_HomePage(AndroidManager_DriverApp.androiddriver_Driver);
            Page_AvailableTrips = new Driver_AvailableTripsPage(AndroidManager_DriverApp.androiddriver_Driver);
            Page_BungiiRequest = new BungiiRequest(AndroidManager_DriverApp.androiddriver_Driver);
            Page_BungiiProgress = new Driver_InProgressBungiiPages(AndroidManager_DriverApp.androiddriver_Driver);
            Page_BungiiComplete = new Driver_BungiiCompletedPage(AndroidManager_DriverApp.androiddriver_Driver);
            Page_OtherApps = new OtherAppsPage(AndroidManager_DriverApp.androiddriver_Driver);
            Page_Driver_Permissions = new Driver_PermissionsPage(AndroidManager_DriverApp.androiddriver_Driver);
        }

        [Given(@"I am logged in as ""(.*)"" driver")]
        public void GivenIAmLoggedInAsDriver(string p0)
        {
            switch (p0)
            {
                case "existing":
                    DriverAction_DriverApp.SendKeys(Page_DriverLogin.TextField_PhoneNumber, Data_Driver.DriverPhoneNumber);
                    DriverAction_DriverApp.SendKeys(Page_DriverLogin.TextField_Password, Data_Driver.DriverPassword);
                    DriverAction_DriverApp.Click(Page_DriverLogin.Button_Login);
                    if (DriverAction_DriverApp.isElementPresent(Page_Driver_Permissions.Text_Location_text1))
                    {
                        //AssertionManager.ElementDisplayed(Page_Driver_Permissions.Text_Location_text1);
                        AssertionManager.ElementDisplayed(Page_Driver_Permissions.Text_Location_text2);
                        DriverAction_DriverApp.Click(Page_Driver_Permissions.Button_Location_Sure);
                        DriverAction_DriverApp.Click(Page_Driver_Permissions.Button_Location_Allow);
                    }
                    break;
                default: break;
            }
        }

        [When(@"I tap on ""(.*)"" on Driver Home page")]
        public void WhenITapOnOnDriverHomePage(string p0)
        {
            switch (p0)
            {
                case "Online/Offline button":
                    DriverAction_DriverApp.Click(Page_DriverHome.Button_OnlineOffline);
                    break;

                case "Available Trips link":
                    DriverAction_DriverApp.Click(Page_DriverHome.Link_AvailableTrips);
                    break;

                default: break;
            }
        }

        [When(@"I ""(.*)"" from Available Trips")]
        public void WhenIFromAvailableTrips(string p0)
        {
            switch (p0)
            {
                case "Accept Bungii":
                    AssertionManager.ElementDisplayed(Page_AvailableTrips.Row_AvailableTrip_01);
                    DriverAction_DriverApp.Click(Page_AvailableTrips.Row_AvailableTrip_01);
                    DriverAction_DriverApp.Click(Page_AvailableTrips.Button_Accept);
                    break;

                default: break;
            }
        }

        [When(@"Bungii Driver ""(.*)"" request")]
        public void WhenBungiiDriverRequest(string p0)
        {
            DriverAction_DriverApp.WaitUntilAlertDisplayed(Page_BungiiRequest.Alert_Msg);
            if (DriverAction_DriverApp.isElementPresent(Page_BungiiRequest.Alert_Msg))
            {
                DriverAction_DriverApp.Click(Page_BungiiRequest.AlertButton_View);
                switch (p0)
                {
                    case "accepts On Demand Bungii":
                        DriverAction_DriverApp.Click(Page_BungiiRequest.Button_Accept);
                        break;

                    case "rejects Bungii":
                        DriverAction_DriverApp.Click(Page_BungiiRequest.Button_Reject);
                        break;
                }
            }
        }

        [When(@"Bungii Driver ""(.*)""")]
        public void WhenBungiiDriver(string p0)
        {
            switch (p0)
            {
                case "cancels Bungii":
                    DriverAction_DriverApp.Click(Page_BungiiProgress.Button_Cancel);
                    DriverAction_DriverApp.Click(Page_BungiiProgress.Button_Cancel_Yes);
                    break;

                case "slides to the next state":
                    DriverAction_DriverApp.SwipeLeft(Page_BungiiProgress.Slider);
                    break;

                case "completes Bungii":
                    DriverAction_DriverApp.Click(Page_BungiiComplete.Button_OnToTheNext);                    
                    break;

                default: break;
            }
        }

        [Then(@"Bungii driver should see ""(.*)""")]
        public void ThenBungiiDriverShouldSee(string p0)
        {
            switch (p0)
            {
                case "Home screen":
                    AssertionManager.ElementTextEqual(Page_DriverHome.Title_Status, Data_Driver.Title_Online);
                    AssertionManager.ElementTextEqual(Page_DriverHome.Button_OnlineOffline, Data_Driver.Button_GoOffline);
                    break;

                case "Enroute screen":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Title_Status, Data_Valid_Customer.PageTitle_Enroute);
                    AssertionManager.ElementSelected(Page_BungiiProgress.BungiiStatus_Enroute);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem);                    
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem);
                    break;

                case "Arrived screen":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Title_Status, Data_Valid_Customer.PageTitle_Arrived);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute);
                    AssertionManager.ElementSelected(Page_BungiiProgress.BungiiStatus_Arrived);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem);
                    break;

                case "Loading Item screen":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Title_Status, Data_Valid_Customer.PageTitle_Loading);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived);
                    AssertionManager.ElementSelected(Page_BungiiProgress.BungiiStatus_LoadingItem);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem);
                    break;

                case "Driving to DropOff screen":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Title_Status, Data_Valid_Customer.PageTitle_Driving);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem);
                    AssertionManager.ElementSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem);
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Location, Data_Valid_Customer.ETAPickup);
                    break;

                case "Unloading Item screen":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Title_Status, Data_Valid_Customer.PageTitle_Unloading);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff);
                    AssertionManager.ElementSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem);
                    break;

                default: break;
            }
        }


        [When(@"Bungii Driver taps ""(.*)"" during a Bungii")]
        public void WhenBungiiDriverTapsDuringABungii(string p0)
        {
            switch (p0)
            {
                case "SMS for a customer":
                    DriverAction_DriverApp.Click(Page_BungiiProgress.Button_Customer_SMS);
                    break;

                case "Call for a solo driver":
                    DriverAction_DriverApp.Click(Page_BungiiProgress.Button_Customer_Call);
                    break;

                default: break;
            }
        }

        [Then(@"correct details should be displayed to driver on ""(.*)"" app")]
        public void ThenCorrectDetailsShouldBeDisplayedToDriverOnApp(string p0)
        {
            switch (p0)
            {
                case "SMS":
                    if (DriverAppdeviceType.Equals("MotoG"))
                        AssertionManager.PhoneNumbersEqual(Page_OtherApps.SMS_Moto_RecipientNo, Data_Customer.Twilio_01);
                    if (DriverAppdeviceType.Equals("SamsungS5") || DriverAppdeviceType.Equals("SamsungS6"))
                        AssertionManager.PhoneNumbersEqual(Page_OtherApps.SMS_Samsung_RecipientNo, Data_Customer.Twilio_01);
                    break;

                case "Calling":
                    if (DriverAppdeviceType.Equals("MotoG"))
                        AssertionManager.PhoneNumbersEqual(Page_OtherApps.Call_Moto_Number, Data_Customer.Twilio_01);
                    if (DriverAppdeviceType.Equals("SamsungS5") || DriverAppdeviceType.Equals("SamsungS6"))
                        AssertionManager.PhoneNumbersEqual(Page_OtherApps.Call_Samsung_Number, Data_Customer.Twilio_01);
                    break;

                default: break;
            }
            while (!DriverAction_DriverApp.isElementPresent(Page_BungiiProgress.Title_Status))
                DriverAction_DriverApp.keyBoardEvent(AndroidKeyCode.Back);
        }

        [When(@"Quit Bungii Driver app")]
        public void WhenQuitBungiiDriverApp()
        {
            AndroidManager_DriverApp.Quit_DriverApp(ScenarioContext.Current);
        }
    }
}
