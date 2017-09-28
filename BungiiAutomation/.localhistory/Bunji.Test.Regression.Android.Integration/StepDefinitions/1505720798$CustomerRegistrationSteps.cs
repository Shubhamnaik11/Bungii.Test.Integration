using System;
using Bungii.Test.Integration.Framework.Core;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class CustomerRegistrationSteps
    {
        private object _SignupPage;

        [When(@"I enter the ""(.*)"" mandatory fields")]
        public void WhenIEnterTheMandatoryFields(string p0)
        {
            ScenarioContext.Current.Pending();
        }
        
        [When(@"I tap on the ""(.*)"" button")]
        public void WhenITapOnTheButton(string p0)
        {
            ScenarioContext.Current.Pending();
        }
        
        [When(@"I enter ""(.*)"" code")]
        public void WhenIEnterCode(string p0)
        {
            ScenarioContext.Current.Pending();
        }
        
        [Then(@"I should be logged in to the app")]
        public void ThenIShouldBeLoggedInToTheApp()
        {
            ScenarioContext.Current.Pending();
        }

        [When(@"I enter mandatory fields")]
        public void WhenIEnterMandatoryFields()
        {
            _SignupPage.Textbox_FirstName.SendKeys(_Data.CustomerFirstName);
            _SignupPage.Textbox_LastName.SendKeys(_Data.CustomerLastName);
            _SignupPage.Textbox_Email.SendKeys(_Data.Email);
            var CustomerPhoneNum = _Data.GetRandomTelNo();
            _SignupPage.Textbox_Phonenumber.SendKeys(CustomerPhoneNum);
            DriverAction.AddValueToScenarioContextVariable("CustomerPhoneNum", CustomerPhoneNum);
            _SignupPage.Textbox_Password.SendKeys(_Data.CustomerPassword);
            _UtilityFunctions.HideKeyboard();
            //_SignupPage.Textbox_Referral.SendKeys(_Data.ReferralCode);
            _SignupPage.Select_ReferralSource.Click();
            _SignupPage.Option_ReferralSource.Click();
            _SignupPage.Select_ReferralSourceDone.Click();
            AssertionManager.ElementEnabled(_SignupPage.Button_Signup);

        }

        [When(@"I tap on Sign Up")]
        public void WhenITapOnSignUp()
        {
            DriverAction.Click(_SignupPage.Button_Signup);
            DriverAction.Click(_SignupPage.Button_NoReferralConfirm);
        }


        [When(@"I enter Verification code")]
        public void WhenIEnterVerificationCode()
        {
            string SMSCode = _UtilityFunctions.GetVerificationCode(DriverAction.GetValueFromScenarioContextVariable("CustomerPhoneNum"));
            DriverAction.SendKeys(_SignupPage.Textbox_SMSCode, SMSCode);
            _SignupPage.Button_VerifyContinue.Click();

        }
        [Then(@"I should be logged in")]
        public void ThenIShouldBeLoggedIn()
        {
            // WebDriverWait wait = new WebDriverWait(driver, new TimeSpan(0, 0, 120));
            DriverAction.Click(_Terms.Checkbox_Agree);
            DriverAction.Click(_Terms.Button_Continue);
            // wait.Until(ExpectedConditions.ElementExists(By.Id("com.android.packageinstaller:id/permission_message")));
            DriverAction.WaitUntilIsElementExistsAndDisplayed(_Terms.Popup_PermissionsMessage);
            DriverAction.Click(_Terms.Button_PermissionsAllow);
            DriverAction.WaitUntilIsElementExistsAndDisplayed(_CustomerHome.Title_HomePage);
            AssertionManager.ElementDisplayed(_CustomerHome.Title_HomePage);
        }

    }
}
