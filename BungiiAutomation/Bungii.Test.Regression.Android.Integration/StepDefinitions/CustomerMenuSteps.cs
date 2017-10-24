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
        SaveMoneyPage Page_SaveMoney = new SaveMoneyPage(AndroidManager.androiddriver);
        SupportPage Page_Support = new SupportPage(AndroidManager.androiddriver);
        PaymentPage Page_Payment = new PaymentPage(AndroidManager.androiddriver);
        CustomerHomePage Page_Home = new CustomerHomePage(AndroidManager.androiddriver);
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

        [Then(@"logged in Customer details should be displayed")]
        public void ThenLoggedInCustomerDetailsShouldBeDisplayed()
        {
            AssertionManager.ElementTextEqual(Page_Account.Account_Name, Data_Customer.CustomerFirstName + " " + Data_Customer.CustomerLastName);
            string ActualPhone = UtilFunctions.ConvertPhoneToString(Page_Account.Account_Phone);
            AssertionManager.CompareStrings(Data_Customer.CustomerPhonenumber, ActualPhone);
            AssertionManager.ElementTextEqual(Page_Account.Account_Email, Data_Customer.Email);
        }

        [When(@"I tap on ""(.*)"" on FAQ page")]
        public void WhenITapOnOnFAQPage(string p0)
        {
            switch (p0)
            {
                case "first question":
                    DriverAction.Click(Page_FAQ.FAQ_FirstQuestion);
                    break;
                default: break;
            }
        }

        [Then(@"I should see ""(.*)"" on FAQ page")]
        public void ThenIShouldSeeOnFAQPage(string p0)
        {
            switch (p0)
            {
                case "first answer dropdown open":
                    AssertionManager.ElementDisplayed(Page_FAQ.FAQ_FirstAnswer);
                    break;
                case "first answer dropdown close":
                    AssertionManager.ElementNotDisplayed(Page_FAQ.FAQ_FirstAnswer);
                    break;
                case "last question":
                    AssertionManager.ElementDisplayed(Page_FAQ.FAQ_LastQuestion);
                    break;
                case "social media links":
                    AssertionManager.ElementDisplayed(Page_FAQ.FAQ_TwitterLogo);
                    AssertionManager.ElementDisplayed(Page_FAQ.FAQ_InstagramLogo);
                    AssertionManager.ElementDisplayed(Page_FAQ.FAQ_FBLogo);
                    break;
                default: break;
            }
        }
    }
}
