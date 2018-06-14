using Bungii.Android.Regression.Test.Integration.Data;
using Bungii.Android.Regression.Test.Integration.Functions;
using Bungii.Android.Regression.Test.Integration.Pages.Driver;
using Bungii.Test.Integration.Framework;
using Bungii.Test.Integration.Framework.Core.Web;
using OpenQA.Selenium;
using System;
using TechTalk.SpecFlow;

namespace Bungii.Android.Regression.Test.Integration.StepDefinitions
{
    [Binding]
    public class Driver_ForgotPasswordSteps
    {
        public IWebDriver webdriver = WebManager.webdriver;

        Driver_LoginPagecs Page_Driver_Login = new Driver_LoginPagecs(WebManager.webdriver);
        Driver_ForgotPasswordPage Page_ForgotPassword = new Driver_ForgotPasswordPage(WebManager.webdriver);
        Driver_VerifyPhonePage Page_VerifyPhone = new Driver_VerifyPhonePage(WebManager.webdriver);
        Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage(WebManager.webdriver);
        Driver_DashboardPage Page_Driver_Dashboard = new Driver_DashboardPage(WebManager.webdriver);

        Data_Reusable_Driver Data_Driver = new Data_Reusable_Driver();
        Data_Validation_Driver Data_valid_Driver = new Data_Validation_Driver();

        WebUtilityFunctions WebUtils = new WebUtilityFunctions();
        GeneralUtilityFunctions Functions = new GeneralUtilityFunctions();

        [When(@"I enter ""(.*)"" Phone Number on Forgot password page")]
        public void WhenIEnterPhoneNumberOnForgotPasswordPage(string p0)
        {
            switch (p0)
            {
                case "valid":
                    WebDriverAction.SendKeys(Page_ForgotPassword.Textfield_ForgotPass_Phone, Data_Driver.DriverPhoneNumber);
                    break;
                case "invalid":
                    WebDriverAction.SendKeys(Page_ForgotPassword.Textfield_ForgotPass_Phone, Data_Driver.Invalid_DriverPhoneNumber);
                    break;
                default: break;
            }
        }

        [When(@"I enter ""(.*)"" code on Verify your phone page")]
        public void WhenIEnterCodeOnVerifyYourPhonePage(string p0)
        {
            switch (p0)
            {
                case "valid":
                    string smscode = WebUtils.GetVerificationCode_Driver(Data_Driver.DriverPhoneNumber);
                    WebDriverAction.SendKeys(Page_VerifyPhone.Textfield_Code, smscode);
                    break;
                case "invalid":
                    WebDriverAction.SendKeys(Page_VerifyPhone.Textfield_Code, Data_Driver.InvalidValue);
                    break;
                default: break;
            }
        }

        [When(@"I enter ""(.*)"" new password"" on Verify your phone page")]
        public void WhenIEnterNewPasswordOnVerifyYourPhonePage(string p0)
        {
            switch (p0)
            {
                case "valid":
                    WebDriverAction.SendKeys(Page_VerifyPhone.Textfield_Password, Data_Driver.DriverPassword);
                    break;
                case "short":
                    WebDriverAction.SendKeys(Page_VerifyPhone.Textfield_Password, Data_Driver.Short_DriverPassword);
                    break;
                case "invalid":
                    WebDriverAction.SendKeys(Page_VerifyPhone.Textfield_Password, Data_Driver.Invalid_DriverPassword);
                    break;
                default: break;
            }
        }

        [When(@"I enter ""(.*)"" password in Confirm password field")]
        public void WhenIEnterPasswordInConfirmPasswordField(string p0)
        {
            switch (p0)
            {
                case "correct":
                    WebDriverAction.SendKeys(Page_VerifyPhone.Textfield_ConfirmPassword, Data_Driver.DriverPassword);
                    break;
                default: break;
            }
        }


        [Then(@"driver should see ""(.*)"" during phone verification")]
        public void ThenDriverShouldSeeDuringPhoneVerification(string p0)
        {
            switch (p0)
            {
                case "validation for invalid phone":
                    WebAssertionManager.ElementTextEqual(Page_ForgotPassword.Err_ForgotPass_Phone, Data_valid_Driver.Err_DriverLogin_Phone);
                    break;
                case "correct phone number":
                    string phone = Data_Driver.DriverPhoneNumber;
                    string PhoneLast4 = Data_valid_Driver.VerifyPhoneText1 + "******" + phone.Substring(phone.Length - 3) + Data_valid_Driver.VerifyPhoneText2;
                    WebAssertionManager.ElementTextEqual(Page_VerifyPhone.Text_Verify_PhoneNo, PhoneLast4);
                    break;
                case "new verification code":
                    string Code_Initial = WebDriverAction.GetValueFromScenarioContextVariable("Code_Initial");
                    string Code_New = WebUtils.GetVerificationCode_Driver(Data_Driver.DriverPhoneNumber);
                    WebAssertionManager.StringsNotEqual(Code_Initial, Code_New);
                    break;
                case "validation for invalid code":
                    WebAssertionManager.ElementTextEqual(Page_VerifyPhone.Err_VerifyPhone_Code_Incorrect, Data_valid_Driver.VerifCode_Err_Invalid);
                    break;
                case "validations for password fields":
                    WebAssertionManager.ElementTextEqual(Page_VerifyPhone.Err_VerifyPhone_Password_Invalid, Data_valid_Driver.DReg_Password_Short);
                    WebAssertionManager.ElementTextEqual(Page_VerifyPhone.Err_VerifyPhone_ConfirmPassword, Data_valid_Driver.DReg_ConfirmPassword_Incorrect);
                    break;
                case "validation for invalid password":
                    WebAssertionManager.ElementTextEqual(Page_VerifyPhone.Err_VerifyPhone_Password_Invalid, Data_valid_Driver.DReg_Password_Invalid);
                    break;
                case "success message driver login page":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Login.Text_PasswordResetSuccess, Data_valid_Driver.Msg_PasswordResetSuccess);
                    break;
                default: break;
            }
        }
    }
}
