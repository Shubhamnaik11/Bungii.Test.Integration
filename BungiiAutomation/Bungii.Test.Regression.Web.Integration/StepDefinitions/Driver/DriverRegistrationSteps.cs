using OpenQA.Selenium;
using Bungii.Test.Integration.Framework.Core.Web;
using System;
using TechTalk.SpecFlow;
using System.Configuration;
using Bungii.Android.Regression.Test.Integration.Data;
using Bungii.Android.Regression.Test.Integration.Pages.Driver;
using Bungii.Test.Integration.Framework;
using Bungii.Android.Regression.Test.Integration.Functions;

namespace Bungii.Android.Regression.Test.Integration.StepDefinitions.Driver
{
    [Binding]
    public class DriverRegistrationSteps
    {
        public IWebDriver webdriver = WebManager.webdriver;

        Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage(WebManager.webdriver);
        Driver_DetailsPage Page_Driver_Details = new Driver_DetailsPage(WebManager.webdriver);
        Driver_PickUpInfoPage Page_Driver_Pickup = new Driver_PickUpInfoPage(WebManager.webdriver);
        Driver_DocumentationPage Page_Driver_Documentation = new Driver_DocumentationPage(WebManager.webdriver);
        Driver_BankDetailsPage Page_Driver_BankDetails = new Driver_BankDetailsPage(WebManager.webdriver);
        Driver_TermsPage Page_Driver_Terms = new Driver_TermsPage(WebManager.webdriver);
        Driver_VideoTrainingPage Page_Driver_Video = new Driver_VideoTrainingPage(WebManager.webdriver);
        Driver_FinishPage Page_Driver_Finish = new Driver_FinishPage(WebManager.webdriver);
        Driver_DashboardPage Page_Driver_Dashboard = new Driver_DashboardPage(WebManager.webdriver);

        Data_Reusable_Driver Data_Driver = new Data_Reusable_Driver();
        Data_Validation_Driver Data_valid_Driver = new Data_Validation_Driver();

        WebUtilityFunctions WebUtils = new WebUtilityFunctions();
        GeneralUtilityFunctions Functions = new GeneralUtilityFunctions();

        [Given(@"I am logged in as driver")]
        public void GivenIAmLoggedInAsDriver()
        {
            WebUtils.DriverLogin(Data_Driver.DriverPhoneNumber, Data_Driver.DriverPassword);
        }


        [Given(@"I navigate to ""(.*)""")]
        public void GivenINavigateTo(string p0)
        {
            switch (p0)
            {
                case "Bungii Driver URL":
                    WebDriverAction.NavigateToUrl(ConfigurationManager.AppSettings["Driver_URL_QA"]);
                    break;
                default: break;
            }
        }

        [Then(@"I should be directed to ""(.*)"" on Driver portal")]
        public void ThenIShouldBeDirectedToOnDriverPortal(string p0)
        {
            switch (p0)
            {
                case "signup tab":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.Header_DriverRegistration, Data_valid_Driver.DriverRegistrationHeader);
                    break;
                case "phone verification page":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.Text_Verification, Data_valid_Driver.RegSuccess);
                    break;
                case "Verification Successful page":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.Header_VerificationSuccess, Data_valid_Driver.SMSVerifSuccess);
                    break;
                case "Driver Details page":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.DriverReg_AllPagesHeader, Data_valid_Driver.DriverDetailsHeader);
                    break;
                case "Pickup Info page":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.DriverReg_AllPagesHeader, Data_valid_Driver.PickupInfoHeader);
                    break;
                case "Documentation page":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.DriverReg_AllPagesHeader, Data_valid_Driver.DocHeader);
                    break;
                case "Bank Details page":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.DriverReg_AllPagesHeader, Data_valid_Driver.BankDetHeader);
                    break;
                case "Terms & Conditions":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.DriverReg_AllPagesHeader, Data_valid_Driver.TermsHeader);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Terms.Terms_H5, Data_valid_Driver.TermsSubHeader);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Terms.Text_Terms, Data_valid_Driver.TermsText);
                    break;
                case "Video Training":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.DriverReg_AllPagesHeader, Data_valid_Driver.VideoHeader);
                    WebAssertionManager.ElementDisplayed(Page_Driver_Video.Screen_Video);
                    break;
                case "Finish":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Details.DriverReg_AllPagesHeader, Data_valid_Driver.FinishHeader);
                    break;
                case "Dashboard":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Dashboard.Header_Dashboard, Data_valid_Driver.DriverDashboardHeader);
                    break;
                default: break;
            }
        }

        [When(@"I enter ""(.*)"" details on Signup page")]
        public void WhenIEnterDetailsOnSignupPage(string p0)
        {
            switch (p0)
            {
                case "valid":
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_FirstName, Data_Driver.DriverFirstName);
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_LastName, Data_Driver.DriverLastName);
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_Email, Data_Driver.DriverEmail);
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_CreatePassword, Data_Driver.DriverPassword);
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_ConfirmPassword, Data_Driver.DriverPassword);
                    break;
                case "invalid":
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_FirstName, Data_Driver.Invalid_DriverName);
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_LastName, Data_Driver.Invalid_DriverName);
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_Email, Data_Driver.Invalid_DriverEmail);
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_CreatePassword, Data_Driver.Invalid_DriverPassword);
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_ConfirmPassword, Data_Driver.Short_DriverPassword);
                    break;
                case "short password":
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_CreatePassword, Data_Driver.Short_DriverPassword);
                    break;
                default: break;
            }
        }

        [When(@"I enter ""(.*)"" driver phone number on Signup page")]
        public void WhenIEnterDriverPhoneNumberOnSignupPage(string p0)
        {
            switch (p0)
            {
                case "unique":  
                    string DriverPhone;
                    do
                        DriverPhone = Functions.RandomPhoneNum();
                    while (Functions.IsPhoneUnique(DriverPhone) == false);
                    WebDriverAction.AddValueToScenarioContextVariable("DriverPhone", DriverPhone);
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_PhoneNumber, DriverPhone);
                    break;
                case "invalid":
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_PhoneNumber, Data_Driver.Invalid_DriverPhoneNumber);
                    break;
                case "existing":
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_PhoneNumber, Data_Driver.Existing_DriverPhoneNumber);
                    break;
                default: break;
            }
        }

        [When(@"I click ""(.*)"" on driver portal")]
        public void WhenIClickOnDriverPortal(string p0)
        {
            switch (p0)
            {
                case "Signup button":
                    WebDriverAction.Click(Page_Driver_Reg.Button_SignUp);
                    break;
                case "Resend verification code":
                    string DriverPhone = WebDriverAction.GetValueFromScenarioContextVariable("DriverPhone");
                    string VerifCode_Initial = WebUtils.GetVerificationCode_Driver(DriverPhone);
                    WebDriverAction.AddValueToScenarioContextVariable("VerifCode_Initial", VerifCode_Initial);
                    WebDriverAction.Click(Page_Driver_Reg.Button_ResendCode);
                    break;
                case "Submit verification code":
                    WebDriverAction.Click(Page_Driver_Reg.Button_SubmitVerification);
                    break;
                case "Continue Registration":
                    WebDriverAction.Click(Page_Driver_Reg.Button_ContinueReg);
                    break;
                case "I agree to the Terms and Conditions":
                    if (Page_Driver_Terms.CheckBox_Agree.Selected == false)
                        WebDriverAction.Click(Page_Driver_Terms.CheckBox_Agree_Click);
                    break;
                case "I have viewed the entire video":
                    if (Page_Driver_Video.CheckBox_Viewed.Selected == false)
                        WebDriverAction.Click(Page_Driver_Video.CheckBox_Viewed_Click);
                    break;
                case "Continue on Finish page":
                    WebDriverAction.Click(Page_Driver_Finish.Button_FinishContinue);
                    break;
                default: break;
            }
        }

        [Then(@"I should see ""(.*)"" on Driver Registration")]
        public void ThenIShouldSeeOnSignupPage(string p0)
        {
            switch (p0)
            {
                case "new verification code":
                    string VerifCode_Initial = WebDriverAction.GetValueFromScenarioContextVariable("VerifCode_Initial");
                    string DriverPhone = WebDriverAction.GetValueFromScenarioContextVariable("DriverPhone");
                    string VerifCode_Updated = WebUtils.GetVerificationCode_Driver(DriverPhone);
                    WebDriverAction.AddValueToScenarioContextVariable("VerificationCode", VerifCode_Updated);
                    WebAssertionManager.StringsNotEqual(VerifCode_Updated, VerifCode_Initial);
                    break;
                case "Logged in user name":
                    string UserName = Data_Driver.DriverFirstName + " " + Data_Driver.DriverLastName;
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.Text_DriverName, UserName);
                    break;
                case "correct field validations":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.ERR_FirstName, Data_valid_Driver.DReg_FirstName_Invalid);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.ERR_LastName, Data_valid_Driver.DReg_LastName_Invalid);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.ERR_Email, Data_valid_Driver.DReg_Email_Invalid);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.ERR_Phone, Data_valid_Driver.DReg_Phone_Invalid);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.ERR_CreatePassword, Data_valid_Driver.DReg_Password_Invalid);
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.ERR_ConfirmPassword, Data_valid_Driver.DReg_ConfirmPassword_Incorrect);
                    break;
                case "Global validation message":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.ERR_BlankFields, Data_valid_Driver.Err_Pages_BlankFields);
                    break;
                case "existing phone error":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.ERR_Phone, Data_valid_Driver.DReg_Phone_Exists);
                    break;
                case "field validation for short password":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.ERR_CreatePassword, Data_valid_Driver.DReg_Password_Short);
                    break;
                case "validation for blank verification code":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.ERR_VerifiCode_Blank, Data_valid_Driver.VerifCode_Err_Blank);
                    break;
                case "validation for incorrect verification code":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Reg.ERR_VerifiCode_Invlid, Data_valid_Driver.VerifCode_Err_Invalid);
                    break;
                default: break;
            }
        }

        [When(@"I enter ""(.*)"" verification code")]
        public void WhenIEnterVerificationCode(string p0)
        {
            switch (p0)
            {
                case "correct":
                    string DriverPhone = WebDriverAction.GetValueFromScenarioContextVariable("DriverPhone");
                    string VerificationCode = WebUtils.GetVerificationCode_Driver(DriverPhone);
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_VerificationCode, VerificationCode);
                    break;
                case "empty":
                    WebDriverAction.Clear(Page_Driver_Reg.TextBox_VerificationCode);
                    break;
                case "incorrect":
                    WebDriverAction.SendKeys(Page_Driver_Reg.TextBox_VerificationCode, Data_Driver.Invalid_DriverPhoneNumber);
                    break;
                default: break;
            }
        }

    }
}
