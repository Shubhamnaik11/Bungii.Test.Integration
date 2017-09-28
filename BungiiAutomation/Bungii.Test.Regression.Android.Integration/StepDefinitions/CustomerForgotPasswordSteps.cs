using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using System;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class Cust_ForgotPasswordSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
        ForgotPasswordPage Page_ForgotPassword = new ForgotPasswordPage(AndroidManager.androiddriver);

        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();

        UtilityFunctions UtilFunctions = new UtilityFunctions();

        [When(@"I enter ""(.*)"" Phone Number")]
        public void WhenIEnterPhoneNumber(string p0)
        {
            switch (p0)
            {
                case "valid":
                    DriverAction.SendKeys(Page_ForgotPassword.TextField_ForgotPass_PhoneNumber, Data_Customer.CustomerPhonenumber);
                    break;
                case "invalid":
                    DriverAction.SendKeys(Page_ForgotPassword.TextField_ForgotPass_PhoneNumber, Data_Customer.InvalidCustomerPhone);
                    break;
                case "less than 10 digit":
                    DriverAction.SendKeys(Page_ForgotPassword.TextField_ForgotPass_PhoneNumber, Data_Customer.CustomerPhoneLessThan10);
                    break;
                default: break;
            }
        }
        
        [When(@"I enter ""(.*)"" SMS code")]
        public void WhenIEnterSMSCode(string p0)
        {
            switch (p0)
            {
                case "valid":
                    string SMSCode = UtilFunctions.GetVerificationCode(Data_Customer.CustomerPhonenumber);
                    DriverAction.SendKeys(Page_ForgotPassword.TextField_SMSCode, SMSCode);
                    break;
                case "invalid":
                    DriverAction.SendKeys(Page_ForgotPassword.TextField_SMSCode, Data_Customer.IncorrectVerificationCode);
                    break;
                default: break;
            }
        }

        [When(@"I enter customers new ""(.*)"" Password")]
        public void WhenIEnterCustomersNewPassword(string p0)
        {
            switch (p0)
            {
                case "valid":
                    DriverAction.SendKeys(Page_ForgotPassword.TextField_NewPassword, Data_Customer.CustomerPassword);
                    break;
                case "invalid":
                    DriverAction.SendKeys(Page_ForgotPassword.TextField_NewPassword, Data_Customer.CustPasswordLessThan6);
                    break;
                default: break;
            }
        }

    }
}
