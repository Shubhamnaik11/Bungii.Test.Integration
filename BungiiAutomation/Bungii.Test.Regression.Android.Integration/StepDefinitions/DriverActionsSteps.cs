using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Integration.Framework.Core.AndroidDriver;
using Bungii.Test.Regression.Android.Integration.Data;
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
        Data_Driver Data_Driver = new Data_Driver();

        public DriverActionsSteps()
        {
            AndroidManager_DriverApp.InitializeDriver();
            androiddriver_Driver = AndroidManager_DriverApp.androiddriver_Driver;
            Page_DriverLogin = new Driver_LoginPage(AndroidManager_DriverApp.androiddriver_Driver);
            Page_DriverHome = new Driver_HomePage(AndroidManager_DriverApp.androiddriver_Driver);
            Page_AvailableTrips = new Driver_AvailableTripsPage(AndroidManager_DriverApp.androiddriver_Driver);
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
                case "Accept a Bungii":
                    AssertionManager.ElementDisplayed(Page_AvailableTrips.Row_AvailableTrip_01);
                    DriverAction_DriverApp.Click(Page_AvailableTrips.Row_AvailableTrip_01);
                    DriverAction_DriverApp.Click(Page_AvailableTrips.Button_Accept);
                    break;

                default: break;
            }
        }
    }
}
