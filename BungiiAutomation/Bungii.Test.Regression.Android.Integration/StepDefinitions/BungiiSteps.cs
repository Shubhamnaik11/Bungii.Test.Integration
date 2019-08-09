using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Pages;
using Bungii.Test.Regression.Android.Integration.Pages.Bungii;
using Bungii.Test.Regression.Android.Integration.Pages.OtherApps;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using System.Configuration;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class BungiiSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
        SearchingPage Page_BungiiSearch = new SearchingPage(AndroidManager.androiddriver);
        BungiiAcceptedPage Page_BungiiAccepted = new BungiiAcceptedPage(AndroidManager.androiddriver);
        BungiiProgressPage Page_BungiiProgress = new BungiiProgressPage(AndroidManager.androiddriver);
        OtherAppsPage Page_OtherApps = new OtherAppsPage(AndroidManager.androiddriver);

        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();
        private static string deviceType = ConfigurationManager.AppSettings["deviceType"];

        [Then(@"for a Bungii I should see ""(.*)""")]
        public void ThenForABungiiIShouldSee(string p0)
        {
            switch (p0)
            {
                case "Bungii Estimate page with all details":
                    break;

                case "Bungii Home page with locations":
                    break;

                case "Bungii search screen":
                    AssertionManager.ElementDisplayed(Page_BungiiSearch.Loader);
                    AssertionManager.ElementDisplayed(Page_BungiiSearch.Text_MsgSearching);
                    AssertionManager.ElementDisplayed(Page_BungiiSearch.ProgressBar);
                    break;

                case "Bungii accepted":
                    AssertionManager.ElementDisplayed(Page_BungiiAccepted.PageTitle_BungiiAccepted);
                    AssertionManager.ElementTextEqual(Page_BungiiAccepted.Label_BungiiAccepted, Data_Valid_Customer.BungiiAccepted);
                    AssertionManager.ElementTextEqual(Page_BungiiAccepted.Label_DriverEnRoute, Data_Valid_Customer.DriverEnRoute);
                    AssertionManager.ElementDisplayed(Page_BungiiAccepted.Image_Driver);
                    AssertionManager.ElementDisplayed(Page_BungiiAccepted.DriverRatingBar);
                    AssertionManager.ElementTextEqual(Page_BungiiAccepted.Label_DriverName, Data_Valid_Customer.DriverName);
                    break;

                case "Enroute screen":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.PageTitle, Data_Valid_Customer.PageTitle_Enroute);
                    AssertionManager.ElementSelected(Page_BungiiProgress.BungiiStatus_Enroute);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem);                    
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem);
                    
                    //AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Location, Data_Valid_Customer.ETAPickup);
                    break;

                case "Arrived screen":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Title, Data_Valid_Customer.PageTitle_Arrived);

                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute);
                    AssertionManager.ElementSelected(Page_BungiiProgress.BungiiStatus_Arrived);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem);

                    break;

                case "Loading Item screen":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Title, Data_Valid_Customer.PageTitle_Loading);

                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived);
                    AssertionManager.ElementSelected(Page_BungiiProgress.BungiiStatus_LoadingItem);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem);
                    break;

                case "Driving to DropOff screen":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Title, Data_Valid_Customer.PageTitle_Driving);

                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem);
                    AssertionManager.ElementSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem);

                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Location, Data_Valid_Customer.ETAPickup);
                    break;

                case "Unloading Item screen":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Title, Data_Valid_Customer.PageTitle_Unloading);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Enroute);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_Arrived);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_LoadingItem);
                    AssertionManager.ElementNotSelected(Page_BungiiProgress.BungiiStatus_DrivingToDropOff);
                    AssertionManager.ElementSelected(Page_BungiiProgress.BungiiStatus_UnloadingItem);
                    break;

                case "Pickup location details":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_LocationTitle, Data_Valid_Customer.LocationTitlePickup);
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Location, Data_Customer.PickupLocation_AT);
                    break;

                case "Dropoff location details":
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_LocationTitle, Data_Valid_Customer.LocationTitleDropOff);
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Location, Data_Customer.DropoffLocation_AT);
                    break;

                case "Driver Details":
                    AssertionManager.ElementDisplayed(Page_BungiiProgress.Bungii_Driver_Image);
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Title, Data_Valid_Customer.DriverTitle);
                    AssertionManager.ElementTextEqual(Page_BungiiProgress.Bungii_Driver_Name, Data_Valid_Customer.DriverName);
                    AssertionManager.ElementDisplayed(Page_BungiiProgress.Bungii_Driver_RatingBar);
                    AssertionManager.ElementEnabled(Page_BungiiProgress.Button_Bungii_Driver_SMS);
                    AssertionManager.ElementEnabled(Page_BungiiProgress.Button_Bungii_Driver_Call);
                    break;

                default: break;
            }
        }

        [When(@"I tap ""(.*)"" during a Bungii")]
        public void WhenITapDuringABungii(string p0)
        {
            switch (p0)
            {
                case "OK on Driver Accepted screen":
                    DriverAction.WaitUntilIsElementExistsAndDisplayed(Page_BungiiAccepted.Button_OK);
                    DriverAction.Click(Page_BungiiAccepted.Button_OK);
                    break;

                case "SMS for a solo driver":
                    DriverAction.Click(Page_BungiiProgress.Button_Bungii_Driver_SMS);
                    break;

                case "Call for a solo driver":
                    DriverAction.Click(Page_BungiiProgress.Button_Bungii_Driver_Call);
                    break;

                default: break;
            }
        }

        [Then(@"correct details should be displayed on ""(.*)"" app")]
        public void ThenCorrectDetailsShouldBeDisplayedOnApp(string p0)
        {
            switch (p0)
            {
                case "SMS":
                    if (deviceType.Equals("MotoG"))
                        AssertionManager.PhoneNumbersEqual(Page_OtherApps.SMS_Moto_RecipientNo, Data_Customer.Twilio_01);
                    if (deviceType.Equals("SamsungS5") || deviceType.Equals("SamsungS6"))
                        AssertionManager.PhoneNumbersEqual(Page_OtherApps.SMS_Samsung_RecipientNo, Data_Customer.Twilio_01);                    
                    break;

                case "Calling":
                    if (deviceType.Equals("MotoG"))
                        AssertionManager.PhoneNumbersEqual(Page_OtherApps.Call_Moto_Number, Data_Customer.Twilio_01);
                    if (deviceType.Equals("SamsungS5") || deviceType.Equals("SamsungS6"))
                        AssertionManager.PhoneNumbersEqual(Page_OtherApps.Call_Samsung_Number, Data_Customer.Twilio_01);
                    break;
                    
                default: break;
            }
            while(!DriverAction.isElementPresent(Page_BungiiProgress.PageTitle))
                DriverAction.keyBoardEvent(AndroidKeyCode.Back);
        }
    }
}
