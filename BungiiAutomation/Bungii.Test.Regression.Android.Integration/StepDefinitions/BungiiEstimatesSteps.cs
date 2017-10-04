using System.Threading;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using TechTalk.SpecFlow;
using System.Configuration;
using Bungii.Test.Integration.Framework.Core.Android;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class BungiiEstimatesSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;

        LoginPage Page_CustLogin = new LoginPage(AndroidManager.androiddriver);
        SearchingPage Page_DriverSearch = new SearchingPage(AndroidManager.androiddriver);
        CustomerHomePage Page_CustHome = new CustomerHomePage(AndroidManager.androiddriver);
        EstimatePage Page_Estimate = new EstimatePage(AndroidManager.androiddriver);
        TermsPage Page_Terms = new TermsPage(AndroidManager.androiddriver);
        SignupPage Page_Signup = new SignupPage(AndroidManager.androiddriver);
        MenuPage Page_Menu = new MenuPage(AndroidManager.androiddriver);
        CameraPage Page_Camera = new CameraPage(AndroidManager.androiddriver);
        ForgotPasswordPage Page_ForgotPassword = new ForgotPasswordPage(AndroidManager.androiddriver);

        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();

        UtilityFunctions UtilFunctions = new UtilityFunctions();
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
                    UtilFunctions.LoginToCustomerApp("9999998185", Data_Customer.CustomerPassword);
                    break;
                default: break;
            }
        }
        
        [When(@"I set correct ""(.*)"" location")]
        public void WhenISetCorrectLocation(string p0)
        {
            switch(p0)
            {
                case "pickup":
                    {
                        UtilFunctions.SelectAddress(Page_CustHome.Textfield_PickupLocation, Data_Customer.currentlocation_US);
                        break;
                    }
                case "drop off":
                    {
                        Thread.Sleep(15000);
                        DriverAction.Click(Page_CustHome.Textfield_DropoffLocation);
                        UtilFunctions.SelectAddress(Page_CustHome.Textfield_DropoffLocation, Data_Customer.dropofflocation_US);
                        string s = Page_CustHome.Textfield_PickupLocation.Text;
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
                        DriverAction.Click(Page_DriverSearch.Link_CancelSearch);
                        DriverAction.Click(Page_DriverSearch.Button_CancelConfirm);
                        break;
                default: break;
            }
        }
        
        [Then(@"the ""(.*)"" page should be displayed")]
        public void ThenThePageShouldBeDisplayed(string p0)
        {
            switch (p0)
            {
                case "Get Estimate":
                    DriverAction.Click(Page_Estimate.Button_EstimateValuePromo);
                    DriverAction.NavigateBack();
                    break;
                default: break;
            }
        }

        [When(@"I Request a Bungii")]
        public void WhenIRequestABungii()
        {
            DriverAction.Click(Page_Estimate.Link_AddPhoto);
            if (DriverAction.isElementPresent(Page_Estimate.Message_CameraPermissions))
                DriverAction.Click(Page_Estimate.Permissions_CameraAllow);
            DriverAction.Click(Page_Estimate.Option_Camera);
            if (deviceType.Equals("Motorola"))
            {
                driver.Tap(1, 100, 500, 1);
                DriverAction.Click(Page_Camera.Button_Review);
            }
            if (deviceType.Equals("Samsung"))
            { 
            DriverAction.keyBoardEvent(AndroidKeyCode.Keycode_CAMERA);  
            DriverAction.Click(Page_Estimate.Button_Camera_OK);
            }
            Thread.Sleep(2000);

            if (!DriverAction.isElementPresent(Page_Estimate.Checkbox_AgreeEstimate))
            {
                UtilFunctions.ScrollToBottom();
            }
            DriverAction.Click(Page_Estimate.Checkbox_AgreeEstimate);
            DriverAction.Click(Page_Estimate.Button_RequestBungii);
            DriverAction.WaitUntilIsElementExistsAndDisplayed(Page_Estimate.Alert_ConfirmRequest);
            DriverAction.Click(Page_Estimate.Button_RequestConfirm);
        }

        [Then(@"Driver search should be cancelled")]
        public void ThenDriverSearchShouldBeCancelled()
        {
            AssertionManager.ElementDisplayed(Page_CustHome.Title_HomePage);
        }
    }
}
