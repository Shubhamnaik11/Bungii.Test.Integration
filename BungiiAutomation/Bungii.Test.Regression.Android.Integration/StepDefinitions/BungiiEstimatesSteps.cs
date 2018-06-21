using System.Threading;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using TechTalk.SpecFlow;
using System.Configuration;
using Bungii.Test.Integration.Framework.Core.Android;
using System;
using Bungii.Test.Regression.Android.Integration.Pages.Bungii;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class BungiiEstimatesSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
        
        SearchingPage Page_DriverSearch = new SearchingPage(AndroidManager.androiddriver);
        CustomerHomePage Page_CustHome = new CustomerHomePage(AndroidManager.androiddriver);
        EstimatePage Page_Estimate = new EstimatePage(AndroidManager.androiddriver);
        BungiiCompletePage Page_BungiiComplete = new BungiiCompletePage(AndroidManager.androiddriver);
        Want_5Page Page_Want5 = new Want_5Page(AndroidManager.androiddriver);

        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();

        UtilityFunctions UtilFunctions = new UtilityFunctions();
        BungiiCalculations Calc = new BungiiCalculations();
        private static string deviceType = ConfigurationManager.AppSettings["deviceType"];        

        [Given(@"I am logged in as ""(.*)"" customer")]
        public void GivenIAmLoggedInAsCustomer(string p0)
        {
            switch (p0)
            {
                case "existing":
                    UtilFunctions.LoginToCustomerApp(Data_Customer.CustomerPhonenumber, Data_Customer.CustomerPassword);
                    break;
                case "newly registered":
                    UtilFunctions.LoginToCustomerApp(Data_Customer.CustPhn_NewlyRegistered, Data_Customer.CustomerPassword);
                    break;
                case "already having bungiis":
                    UtilFunctions.LoginToCustomerApp(Data_Customer.CustPhn_WithBungiis, Data_Customer.CustomerPassword);
                    break;
                case "having referral code":
                    UtilFunctions.LoginToCustomerApp(Data_Customer.CustPhn_HavingReferral, Data_Customer.CustomerPassword);
                    break;
                case "my":
                    UtilFunctions.LoginToCustomerApp("8888882021", Data_Customer.CustomerPassword);
                    break;
                case "stage":
                    UtilFunctions.LoginToCustomerApp("9999999991", "Cci12345");
                    break;
                default: break;
            }
        }

        [When(@"I enter ""(.*)"" on Bungii estimate")]
        public void WhenIEnterOnBungiiEstimate(string p0)
        {
            switch (p0)
            {
                case "valid pickup and dropoff locations":
                    UtilFunctions.SelectAddress(Page_CustHome.Textfield_PickupLocation, Data_Customer.PickupLocation_OP);
                    Thread.Sleep(2000);
                    UtilFunctions.SelectAddress(Page_CustHome.Textfield_DropoffLocation, Data_Customer.DropoffLocation_OP);
                    break;
                case "Atlanta pickup and dropoff locations":
                    UtilFunctions.SelectAddress(Page_CustHome.Textfield_PickupLocation, Data_Customer.PickupLocation_AT);
                    Thread.Sleep(2000);
                    UtilFunctions.SelectAddress(Page_CustHome.Textfield_DropoffLocation, Data_Customer.DropoffLocation_AT);
                    break;
                case "current location in pickup and dropoff fields":
                    //string a = driver.PageSource;
                    DriverAction.Click(Page_CustHome.Button_Locator);
                    Thread.Sleep(2500);
                    if(DriverAction.isElementPresent(Page_CustHome.Text_ETAvalue))
                        {
                        int ETA = Convert.ToInt32(Page_CustHome.Text_ETAvalue.Text.Replace(" MINS", ""));
                        if (ETA <= 30)
                            DriverAction.Click(Page_CustHome.Button_ETASet);
                        }                    
                    DriverAction.Click(Page_CustHome.Button_Locator);
                    DriverAction.Click(Page_CustHome.Button_ETASet);
                    break;
                default: break;
            }
        }

        [When(@"I tap on ""(.*)"" on Bungii estimate")]
        public void WhenITapOnOnBungiiEstimate(string p0)
        {
            switch (p0)
            {
                case "two drivers selector":
                    DriverAction.Click(Page_CustHome.Selector_Duo);
                    break;

                case "Get Estimate button":
                    while (DriverAction.isElementPresent(Page_CustHome.Button_GetEstimate) == false)
                        WhenIEnterOnBungiiEstimate("current location in pickup and dropoff fields");
                    //if (DriverAction.isElementPresent(Page_CustHome.Button_GetEstimate))
                        DriverAction.Click(Page_CustHome.Button_GetEstimate);
                    break;

                case "Cancel during search":
                    DriverAction.Click(Page_DriverSearch.Link_CancelSearch);
                    DriverAction.Click(Page_DriverSearch.Button_CancelConfirm);
                    break;

                case "Promo Code":
                    DriverAction.Click(Page_Estimate.Link_Promo);
                    break;

                case "desired Promo Code":
                    DriverAction.Click(Page_Estimate.Select_PromoCode);
                    break;

                case "Payment Mode":
                    DriverAction.Click(Page_Estimate.Select_PaymentMode);
                    break;

                case "Request Bungii":
                    if (!DriverAction.isElementPresent(Page_Estimate.Checkbox_AgreeEstimate))
                        UtilFunctions.ScrollToBottom();
                    DriverAction.Click(Page_Estimate.Checkbox_AgreeEstimate);

                    if (!DriverAction.isElementPresent(Page_Estimate.Button_RequestBungii))
                        UtilFunctions.ScrollToBottom();
                    DriverAction.Click(Page_Estimate.Button_RequestBungii);
                    break;

                case "Yes on HeadsUp pop up":
                    DriverAction.WaitUntilIsElementExistsAndDisplayed(Page_Estimate.Alert_ConfirmRequestMessage);
                    DriverAction.Click(Page_Estimate.Button_RequestConfirm);

                    //If time has passed. *to be worked on*
                    /*if (DriverAction.isElementPresent(Page_Estimate.Alert_DelayRequestingTrip))
                    {
                        if (deviceType.Equals("SamsungS5") || deviceType.Equals("SamsungS6"))
                        {
                            DriverAction.Click(Page_Estimate.Button_DelayRequestingTrip_OK);
                            DriverAction.Click(Page_Estimate.Time);

                            //choose current date
                            DriverAction.Click(Page_Estimate.Samsung_CurrentSelectedDate);
                            DriverAction.Click(Page_Estimate.Samsung_Date_OK);

                            //set time with 15 mins delay
                            DriverAction.Click(Page_Estimate.Samsung_SetTime_Min_Next);
                            UtilFunctions.ScrollUp(Page_Estimate.Samsung_SetTime_Min_Next);
                            if (Page_Estimate.Samsung_SetTime_Min_Current.Text == "00" || Page_Estimate.Samsung_SetTime_Min_Current.Text == "15" || Page_Estimate.Samsung_SetTime_Min_Current.Text == "30")
                                DriverAction.Click(Page_Estimate.Samsung_SetTime_Hour_Next);

                            DriverAction.Click(Page_Estimate.Button_RequestConfirm);
                        }
                        Thread.Sleep(2000);
                    }*/
                    break;

                case "Done after requesting a Scheduled Bungii":
                    if (!DriverAction.isElementPresent(Page_CustHome.Button_Done))
                        UtilFunctions.ScrollToBottom();
                    DriverAction.Click(Page_CustHome.Button_Done);
                    break;

                case "Cancel on HeadsUp pop up":
                    DriverAction.WaitUntilIsElementExistsAndDisplayed(Page_Estimate.Alert_ConfirmRequestMessage);
                    DriverAction.Click(Page_Estimate.Button_RequestConfirmCancel);
                    break;

                case "X on complete":
                    DriverAction.Click(Page_BungiiComplete.CloseRateTipPage);
                    break;

                case "No free money":
                    DriverAction.Click(Page_Want5.Button_NoFreeMoney);
                    break;

                default: break;
            }
        }

        int i = 0;
        [When(@"I add ""(.*)"" photos to the Bungii")]
        public void WhenIAddPhotosToTheBungii(int p0)
        {
            do
            {
                if (!DriverAction.isElementPresent(Page_Estimate.Link_AddPhoto))
                    UtilFunctions.SwipeLeft(Page_Estimate.Row_Images);

                DriverAction.Click(Page_Estimate.Link_AddPhoto);

                if (DriverAction.isElementPresent(Page_Estimate.Message_CameraPermissions))
                    DriverAction.Click(Page_Estimate.Permissions_CameraAllow);

                DriverAction.Click(Page_Estimate.Option_Camera);

                if (deviceType.Equals("MotoG"))
                {
                    driver.Tap(1, 100, 500, 1);
                    DriverAction.Click(Page_Estimate.Button_Review);
                }
                if (deviceType.Equals("SamsungS5") || deviceType.Equals("SamsungS6"))
                {
                    DriverAction.keyBoardEvent(AndroidKeyCode.Keycode_CAMERA);
                    Thread.Sleep(2000);
                    DriverAction.Click(Page_Estimate.Button_Camera_OK);
                }
                Thread.Sleep(2000);
                i++;
            } while (i < p0);

            if(DriverAction.isElementPresent(Page_Estimate.Text_PickupLocation))
            {
                //code to be added incase of invalid image error
            }
        }

        [When(@"I add loading/unloading time of ""(.*)""")]
        public void WhenIAddLoadingUnloadingTimeOf(string p0)
        {
            DriverAction.Click(Page_Estimate.Link_LoadingUnloadingTime);
            switch (p0)
            {
                case "15 mins":
                    DriverAction.Click(Page_Estimate.LoadingUnloadingTime_15);
                    break;

                case "30 mins":
                    DriverAction.Click(Page_Estimate.LoadingUnloadingTime_30);
                    break;

                case "45 mins":
                    DriverAction.Click(Page_Estimate.LoadingUnloadingTime_45);
                    break;

                case "60 mins":
                    DriverAction.Click(Page_Estimate.LoadingUnloadingTime_60);
                    break;

                case "75 mins":
                    DriverAction.Click(Page_Estimate.LoadingUnloadingTime_75);
                    break;

                case "90+ mins":
                    DriverAction.Click(Page_Estimate.LoadingUnloadingTime_90);
                    break;

                default: break;
            }
        }


        [Then(@"I should see ""(.*)"" on Bungii estimate")]
        public void ThenIShouldSeeOnBungiiEstimate(string p0)
        {
            switch (p0)
            {
                case "two drivers selected":
                    AssertionManager.ElementTextEqual(Page_CustHome.Switch_SoloDuo, "2");
                    //AssertionManager.ElementEnabled(Page_CustHome.Selector_Duo);
                    //AssertionManager.ElementDisabled(Page_CustHome.Selector_Solo);
                    break;

                case "all elements":
                    AssertionManager.ElementDisplayed(Page_Estimate.Header_Estimate);
                    AssertionManager.ElementTextEqual(Page_Estimate.Text_PickupLocation, Data_Customer.PickupLocation_OP);
                    AssertionManager.ElementTextEqual(Page_Estimate.Text_DropOffLocation, Data_Customer.DropoffLocation_OP);

                    double ExpectedTotalEstimate = Calc.TotalEstimate(Page_Estimate.Text_TripTime.Text, Page_Estimate.Text_TripDistance.Text, Page_Estimate.Link_Promo.Text);
                    double ActualTotalEstimate = Convert.ToDouble(Page_Estimate.Text_TotalEstimate.Text.Replace("$", ""));

                    Equals(ExpectedTotalEstimate, ActualTotalEstimate);
                    break;

                case "driver cancelled":
                    AssertionManager.ElementDisplayed(Page_CustHome.Title_HomePage);
                    AssertionManager.ElementEnabled(Page_CustHome.Button_GetEstimate);
                    break;

                case "Bungii posted Success page":
                    //AssertionManager.ElementDisplayed(Page_CustHome.Title_Success);
                    AssertionManager.ElementDisplayed(Page_CustHome.Image_Tick);
                    break;

                default: break;
            }
        }
    }
}
