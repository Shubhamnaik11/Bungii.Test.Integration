using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Integration.Framework.Core.AndroidDriver;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages.DriverPages;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
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
        Data_Driver Data_Driver = new Data_Driver();

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

                case "arrives at pickup location":
                    break;

                case "starts loading items":
                    break;

                case "starts driving to dropoff":
                    break;

                case "starts unloading items":
                    break;

                case "completes Bungii":
                    DriverAction_DriverApp.Click(Page_BungiiComplete.Button_OnToTheNext);                    
                    break;

                default: break;
            }
        }

        [When(@"Quit Bungii Driver app")]
        public void WhenQuitBungiiDriverApp()
        {
            AndroidManager_DriverApp.Quit_DriverApp(ScenarioContext.Current);
        }
    }
}
