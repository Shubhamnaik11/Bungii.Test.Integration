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
using Bungii.Test.Integration.Framework.Core.Web;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class BungiiEstimatesSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
        
        SearchingPage Page_DriverSearch = new SearchingPage(AndroidManager.androiddriver);
        CustomerHomePage Page_CustHome = new CustomerHomePage(AndroidManager.androiddriver);
        EstimatePage Page_Estimate = new EstimatePage(AndroidManager.androiddriver);

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
                    UtilFunctions.LoginToCustomerApp("9999998181", Data_Customer.CustomerPassword);
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
                default: break;
            }
        }

        [When(@"I tap on ""(.*)"" on Bungii estimate")]
        public void WhenITapOnOnBungiiEstimate(string p0)
        {
            switch (p0)
            {
                case "Get Estimate button":
                    DriverAction.Click(Page_CustHome.Button_GetEstimate);
                    break;

                case "Cancel":
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

                case "Add photo":
                    DriverAction.Click(Page_Estimate.Link_AddPhoto);

                    if (DriverAction.isElementPresent(Page_Estimate.Message_CameraPermissions))
                        DriverAction.Click(Page_Estimate.Permissions_CameraAllow);

                    DriverAction.Click(Page_Estimate.Option_Camera);

                    if (deviceType.Equals("Motorola"))
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
                    break;

                case "Request Bungii":
                    if (!DriverAction.isElementPresent(Page_Estimate.Checkbox_AgreeEstimate))
                        UtilFunctions.ScrollToBottom();
                    DriverAction.Click(Page_Estimate.Checkbox_AgreeEstimate);

                    if (!DriverAction.isElementPresent(Page_Estimate.Button_RequestBungii))
                        UtilFunctions.ScrollToBottom();
                    DriverAction.Click(Page_Estimate.Button_RequestBungii);

                    DriverAction.WaitUntilIsElementExistsAndDisplayed(Page_Estimate.Alert_ConfirmRequestMessage);
                    DriverAction.Click(Page_Estimate.Button_RequestConfirm);
                    break;

                default: break;
            }
        }

        [Then(@"I should see ""(.*)"" on Bungii estimate")]
        public void ThenIShouldSeeOnBungiiEstimate(string p0)
        {
            switch (p0)
            {
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

                default: break;
            }
        }
    }
}
