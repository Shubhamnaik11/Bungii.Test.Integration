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
    public class LoginSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;

        LoginPage Page_CustLogin = new LoginPage(AndroidManager.androiddriver);
        CustomerHomePage Page_CustHome = new CustomerHomePage(AndroidManager.androiddriver);
        TermsPage Page_CustTerms = new TermsPage(AndroidManager.androiddriver);
        SignupPage Page_Signup = new SignupPage(AndroidManager.androiddriver);
        ForgotPasswordPage Page_ForgotPassword = new ForgotPasswordPage(AndroidManager.androiddriver);
        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();        
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();
        UtilityFunctions UtilFunctions = new UtilityFunctions();
     
        [Given(@"I am on Sign up page")]
        public void GivenIAmOnSignUpPage()
        {
           DriverAction.WaitUntilIsElementExistsAndDisplayed(Page_Signup.Button_Signup);
           AssertionManager.ElementDisplayed(Page_Signup.Button_Signup);
        }
       
        [When(@"I tap on the ""(.*)"" Link")]
        public void WhenITapOnTheLink(string p0)
        {
            switch (p0)
            {
                case "Login":
                    DriverAction.Click(Page_Signup.Link_Login);
                    break;
                case "Forgot Password":
                    DriverAction.Click(Page_CustLogin.Link_ForgotPassword);
                    break;
                case "Send":
                    DriverAction.Click(Page_ForgotPassword.Button_ForgotPass_Send);
                    break;
                case "Continue":
                    DriverAction.Click(Page_ForgotPassword.Button_Continue);
                    break;
                case "Sign Up":
                    DriverAction.Click(Page_Signup.Button_Signup);
                    break;
                case "Verification Continue":
                    DriverAction.Click(Page_Signup.Button_VerifyContinue);
                    break;
                default: break;
            }
        }

        [When(@"I enter customers ""(.*)"" Phone Number")]
        public void WhenIEnterCustomersPhoneNumber(string p0)
        {
            switch (p0)
            {
                case "valid":
                    DriverAction.SendKeys(Page_CustLogin.TextField_PhoneNumber, Data_Customer.CustomerPhonenumber);
                    break;
                case "invalid":
                    DriverAction.SendKeys(Page_CustLogin.TextField_PhoneNumber, Data_Customer.InvalidCustomerPhone);
                    break;
                case "blank":
                    DriverAction.Clear(Page_CustLogin.TextField_PhoneNumber);
                    break;
                default: break;
            }
        }

        [When(@"I enter customers ""(.*)"" Password")]
        public void WhenIEnterCustomersPassword(string p0)
        {
            switch (p0)
            {
                case "valid":
                    DriverAction.SendKeys(Page_CustLogin.TextField_Password, Data_Customer.CustomerPassword);
                    break;
                case "invalid":
                    DriverAction.SendKeys(Page_CustLogin.TextField_Password, Data_Customer.CustPasswordLessThan6);
                    break;
                case "blank":
                    DriverAction.Clear(Page_CustLogin.TextField_Password);
                    break;
                default: break;
            }
        }

        [When(@"I tap on the ""(.*)"" Button")]
        public void WhenITapOnTheButton(string p0)
        {
            switch (p0)
            {
                case "Log in":
                    DriverAction.Click(Page_CustLogin.Button_Login);
                    break;
                default: break;
            }
        }

        [Then(@"The user should be logged in")]
        public void ThenTheUserShouldBeLoggedIn()
        {
            if (DriverAction.isElementPresent(Page_CustTerms.Checkbox_Agree))
            {
                DriverAction.Click(Page_CustTerms.Checkbox_Agree);
                DriverAction.Click(Page_CustTerms.Button_Continue);
                if (DriverAction.isElementPresent(Page_CustTerms.Popup_PermissionsMessage))
                {
                    DriverAction.Click(Page_CustTerms.Button_PermissionsAllow);
                }
            }
            AssertionManager.ElementDisplayed(Page_CustHome.Title_HomePage);
            AssertionManager.ElementDisplayed(Page_CustHome.Link_Invite);
        }

        [Then(@"The user should see ""(.*)""")]
        public void ThenTheUserShouldSee(string p0)
        {
            switch (p0)
            {
                case "snackbar validation message":
                    AssertionManager.SnackbarTextEqual(Page_CustLogin.Snackbar_IncorrectPassword, Data_Valid_Customer.Cust_Login_Snackbar_IncorrectPassword);
                    break;

                case "login button disabled":
                    AssertionManager.ElementDisabled(Page_CustLogin.Button_Login);
                    break;

                case "field validations":
                    string PhoneError = UtilFunctions.TrimString(Page_CustLogin.Error_EnterPhone.Text);
                    AssertionManager.CompareStrings(Data_Valid_Customer.Cust_Login_Err_IncorrectPhone, PhoneError);
                    string PasswordError = UtilFunctions.TrimString(Page_CustLogin.Error_EnterPassword.Text);
                    AssertionManager.CompareStrings(Data_Valid_Customer.Cust_Login_Err_BlankPassword, PasswordError);
                    break;

                case "Send button disabled":
                    AssertionManager.ElementDisabled(Page_ForgotPassword.Button_ForgotPass_Send);
                    break;

                case "validation for incorrect password":
                    string NewPasswordError = UtilFunctions.TrimString(Data_Valid_Customer.Cust_ForgotPassword_Err_InvalidPassword);
                    AssertionManager.CompareStrings(Page_ForgotPassword.Err_InvalidPassword.Text, NewPasswordError);
                    break;

                default: break;
            }
        }
    }
}
