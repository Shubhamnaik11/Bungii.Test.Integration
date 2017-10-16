using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class CustomerRegistrationSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
        LoginPage Page_Login = new LoginPage(AndroidManager.androiddriver);
        CustomerHomePage Page_CustHome = new CustomerHomePage(AndroidManager.androiddriver);
        TermsPage Page_Terms = new TermsPage(AndroidManager.androiddriver);
        SignupPage Page_Signup = new SignupPage(AndroidManager.androiddriver);
        SaveMoneyPage Page_SaveMoney = new SaveMoneyPage(AndroidManager.androiddriver);

        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();

        UtilityFunctions UtilFunctions = new UtilityFunctions();

        [When(@"I enter ""(.*)"" data in mandatory fields")]
        public void WhenIEnterDataInMandatoryFields(string p0)
        {
            switch (p0)
            {
                case "valid":
                    DriverAction.SendKeys(Page_Signup.TextField_FirstName, Data_Customer.CustomerFirstName);
                    DriverAction.SendKeys(Page_Signup.TextField_LastName, Data_Customer.CustomerLastName);
                    DriverAction.SendKeys(Page_Signup.TextField_Email, Data_Customer.Email);
                    DriverAction.SendKeys(Page_Signup.TextField_Password, Data_Customer.CustomerPassword);
                    Page_Signup.Select_ReferralSource.Click();
                    Page_Signup.Option_ReferralSource.Click();
                    Page_Signup.Link_ReferralSourceDone.Click();
                    break;

                case "blank":
                    DriverAction.Clear(Page_Signup.TextField_FirstName);
                    DriverAction.Clear(Page_Signup.TextField_LastName);
                    DriverAction.Clear(Page_Signup.TextField_Email);
                    DriverAction.Clear(Page_Signup.TextField_Password);
                    break;

                case "invalid":
                    DriverAction.SendKeys(Page_Signup.TextField_Email, Data_Customer.InvalidEmail);
                    DriverAction.SendKeys(Page_Signup.TextField_Password, Data_Customer.CustPasswordLessThan6);                    
                    Page_Signup.TextField_Referral.Click();
                    break;

                default: break;
            }
        }

        [When(@"I enter ""(.*)"" customer phone number")]
        public void WhenIEnterCustomerPhoneNumber(string p0)
        {
            switch (p0)
            {
                case "unique":
                    string CustomerPhone = Data_Customer.RandomPhoneNum();
                    FeatureContext.Current.Add("CustomerPhoneNum", CustomerPhone);
                    DriverAction.SendKeys(Page_Signup.TextField_Phonenumber, CustomerPhone);
                    break;
                case "blank":
                    DriverAction.Clear(Page_Signup.TextField_Phonenumber);
                    FeatureContext.Current.Add("CustomerPhoneNum", Page_Signup.TextField_Phonenumber);
                    break;
                case "invalid":
                    DriverAction.SendKeys(Page_Signup.TextField_Phonenumber, Data_Customer.CustomerPhoneLessThan10);
                    FeatureContext.Current.Add("CustomerPhoneNum", Data_Customer.CustomerPhoneLessThan10);
                    break;
                case "existing":
                    DriverAction.SendKeys(Page_Signup.TextField_Phonenumber, Data_Customer.CustomerPhonenumber);
                    FeatureContext.Current.Add("CustomerPhoneNum", Data_Customer.CustomerPhonenumber);
                    break;
                default: break;
            }
        }

        [When(@"I tap on the ""(.*)"" button")]
        public void WhenITapOnTheButton(string p0)
        {
            switch (p0)
            {
                case "Sign Up":
                    DriverAction.Click(Page_Signup.Button_Signup);
                    break;
                case "No, Continue":
                    DriverAction.Click(Page_Signup.Button_NoReferralConfirm);
                    break;
                case "Yes":
                    DriverAction.Click(Page_Signup.Button_NoReferralYes);
                    break;
                default: break;
            }
        }

        [When(@"I enter ""(.*)"" Verification code")]
        public void WhenIEnterVerificationCode(string p0)
        {
            switch (p0)
            {
                case "valid":
                    string SMSCode = UtilFunctions.GetVerificationCode(DriverAction.GetValueFromFeatureContextVariable("CustomerPhoneNum"));
                    DriverAction.SendKeys(Page_Signup.Textfield_SMSCode, SMSCode);
                    break;
                default: break;
            }
        }
        
        [Then(@"I should be logged in to the app")]
        public void ThenIShouldBeLoggedInToTheApp()
        {
            Page_Signup.Button_VerifyContinue.Click();
            DriverAction.Click(Page_Terms.Checkbox_Agree);
            DriverAction.Click(Page_Terms.Button_Continue);
            DriverAction.WaitUntilIsElementExistsAndDisplayed(Page_Terms.Popup_PermissionsMessage);
            DriverAction.Click(Page_Terms.Button_PermissionsAllow);
            DriverAction.WaitUntilIsElementExistsAndDisplayed(Page_CustHome.Title_HomePage);
            AssertionManager.ElementDisplayed(Page_CustHome.Title_HomePage);
        }

        [Then(@"the new user should see ""(.*)""")]
        public void ThenTheNewUserShouldSee(string p0)
        {
            switch (p0)
            {
                case "sign up button disabled":
                    AssertionManager.ElementDisabled(Page_Signup.Button_Signup);
                    break;

                case "validations for all fields":
                    AssertionManager.ElementTextEqual(Page_Signup.Cust_Signup_Error_Email, Data_Valid_Customer.Cust_Signup_Err_Email);
                    AssertionManager.ElementTextEqual(Page_Signup.Cust_Signup_Error_Phone, Data_Valid_Customer.Cust_Signup_Err_PhoneNum);
                    AssertionManager.ElementTextEqual(Page_Signup.Cust_Signup_Error_Password, Data_Valid_Customer.Cust_Signup_Err_Password);
                    break;

                case "Signup page":
                    AssertionManager.ElementDisplayed(Page_Signup.Button_Signup);
                    AssertionManager.ElementTextEqual(Page_Signup.TextField_FirstName, Data_Customer.CustomerFirstName);
                    AssertionManager.ElementTextEqual(Page_Signup.TextField_LastName, Data_Customer.CustomerLastName);
                    AssertionManager.ElementTextEqual(Page_Signup.TextField_Email, Data_Customer.Email);
                
                    var ExpectedPhoneNumber = DriverAction.GetValueFromFeatureContextVariable("CustomerPhoneNum");
                    string ActualPhoneNumber = UtilFunctions.ConvertPhoneToString(Page_Signup.TextField_Phonenumber);
                    AssertionManager.CompareStrings(ExpectedPhoneNumber, ActualPhoneNumber);

                    AssertionManager.ElementNotEmpty(Page_Signup.TextField_Password);
                    AssertionManager.ElementTextEqual(Page_Signup.Text_ReferralSource, Data_Valid_Customer.Cust_Signup_ReferralSource);
                    break;

                case "snackbar validation message":
                    AssertionManager.SnackbarTextEqual(Page_Login.Snackbar, Data_Valid_Customer.Cust_Signup_Snackbar_ExistingPhone);
                    break;  

                default: break;
            }
        }

        [When(@"I enter ""(.*)"" promo code")]
        public void WhenIEnterPromoCode(string p0)
        {
            switch (p0)
            {
                case "ValidPercent":
                    DriverAction.SendKeys(Page_Signup.TextField_Referral, Data_Customer.ValidPercentCode);
                    break;
                case "invalid":
                    DriverAction.SendKeys(Page_Signup.TextField_Referral, Data_Customer.InvalidCode);
                    break;
                default: break;
            }
        }

        [Then(@"""(.*)"" promo code should be displayed")]
        public void ThenPromoCodeShouldBeDisplayed(string p0)
        {
            switch (p0)
            {
                case "ValidPercent":
                    AssertionManager.ElementTextEqual(Page_SaveMoney.SaveMoney_PromoCode1, Data_Valid_Customer.ValidPercentPromoCode);
                    break;
            }
        }
    }
}