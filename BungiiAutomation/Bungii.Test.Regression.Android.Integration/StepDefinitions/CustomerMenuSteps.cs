using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using TechTalk.SpecFlow;
using Bungii.Test.Regression.Android.Integration.Pages;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Integration.Framework.Core.Android;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class CustomerMenuSteps
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
        SaveMoneyPage Page_SaveMoney = new SaveMoneyPage(AndroidManager.androiddriver);
        SupportPage Page_Support = new SupportPage(AndroidManager.androiddriver);
        PaymentPage Page_Payment = new PaymentPage(AndroidManager.androiddriver);
        HomePage Page_Home = new HomePage(AndroidManager.androiddriver);
        FAQPage Page_FAQ = new FAQPage(AndroidManager.androiddriver);
        AccountPage Page_Account = new AccountPage(AndroidManager.androiddriver);

        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();

        UtilityFunctions UtilFunctions = new UtilityFunctions();
   
         [Given(@"I have launched the app")]
         public void GivenIHaveLaunchedTheApp()
         {
            AssertionManager.ElementDisplayed(Page_Signup.Button_Signup);
         }

         [Given(@"I am on Customer logged in Home page")]
         public void GivenIAmOnCustomerLoggedInHomePage()
         {
            UtilFunctions.LoginToCustomerApp(Data_Customer.CustomerPhonenumber, Data_Customer.CustomerPassword);
         }   

        [Then(@"Customer should be logged out")]
         public void ThenCustomerShouldBeLoggedOut()
        {
            AssertionManager.ElementDisplayed(Page_CustLogin.Header_LoginPage);
        }

        [When(@"I tap on ""(.*)"" > ""(.*)"" link")]
        public void WhenITapOnLink(string p0, string p1)
        {
            DriverAction.Click(Page_Menu.Button_Menu);
            switch(p1)
            {
                case "FAQ":
                    DriverAction.Click(Page_Menu.Menu_FAQ);
                    break;
                case "Account":
                    DriverAction.Click(Page_Menu.Menu_Account);
                    break;
                case "Payment":
                    DriverAction.Click(Page_Menu.Menu_Payment);
                    break;
                case "Support":
                    DriverAction.Click(Page_Menu.Menu_Support);
                    break;
                case "Save Money":
                    DriverAction.Click(Page_Menu.Menu_SaveMoney);
                    break;
                case "Home":
                    DriverAction.Click(Page_Menu.Menu_Home);
                    break;
                case "Logout":
                    DriverAction.Click(Page_Menu.Menu_Logout);
                    break;
                default: break;
            }
        }

        [Then(@"""(.*)"" page should be opened")]
        public void ThenPageShouldBeOpened(string p0)
        {
            switch (p0)
            {
                case "FAQ":
                    AssertionManager.ElementDisplayed(Page_FAQ.Header_FAQPage);
                    break;
                case "Account":
                    AssertionManager.ElementDisplayed(Page_Account.Header_AccountPage);
                    break;
                case "Payment":
                    AssertionManager.ElementDisplayed(Page_Payment.Header_PaymentPage);
                    break;
                case "Support":
                    AssertionManager.ElementDisplayed(Page_Support.Header_SupportPage);
                    break;
                case "Save Money":
                    AssertionManager.ElementDisplayed(Page_SaveMoney.Header_SavePage);
                    break;
                case "Home":
                    AssertionManager.ElementDisplayed(Page_Home.Header_HomePage);
                    break;
                case "Logout":
                    AssertionManager.ElementDisplayed(Page_CustLogin.Header_LoginPage);
                    break;
                default: break;
            }
        }

        [Then(@"All contents of ""(.*)"" page should be displayed")]
        public void ThenAllContentsOfPageShouldBeDisplayed(string p0)
        {
            switch (p0)
            {
                case "FAQ":
                    DriverAction.Click(Page_FAQ.FAQ_FirstQuestion);
                    AssertionManager.ElementPresent(Page_FAQ.FAQ_FirstAnswer);
                    UtilFunctions.ScrollToBottom();
                    Page_Menu.FAQ_TwitterLogo.Click();
                    DriverAction.keyBoardEvent(AndroidKeyCode.Keycode_BACK);
                    Page_Menu.FAQ_InstagramLogo.Click();
                    DriverAction.keyBoardEvent(AndroidKeyCode.Keycode_BACK);
                    Page_Menu.FAQ_FBLogo.Click();
                    DriverAction.keyBoardEvent(AndroidKeyCode.Keycode_BACK);
                    break;
                default: break;
            }
        }

        [Then(@"logged in Customer details should be displayed")]
        public void ThenLoggedInCustomerDetailsShouldBeDisplayed()
        {
            AssertionManager.ElementTextEqual(Page_Account.Account_Name, Data_Customer.CustomerFirstName + " " + Data_Customer.CustomerLastName);
            string ActualPhone = UtilFunctions.ConvertPhoneToString(Page_Account.Account_Phone);
            AssertionManager.CompareStrings(Data_Customer.CustomerPhonenumber, ActualPhone);
            AssertionManager.ElementTextEqual(Page_Account.Account_Email, Data_Customer.Email);
        }
    }
}
