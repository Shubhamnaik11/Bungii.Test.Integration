using Bungii.Android.Regression.Test.Integration.Data;
using Bungii.Android.Regression.Test.Integration.Functions;
using Bungii.Android.Regression.Test.Integration.Pages.Driver;
using Bungii.Test.Integration.Framework;
using Bungii.Test.Integration.Framework.Core.Web;
using OpenQA.Selenium;
using System;
using TechTalk.SpecFlow;

namespace Bungii.Android.Regression.Test.Integration.StepDefinitions.Driver
{
    [Binding]
    public class Driver_LoginSteps
    {
        public IWebDriver webdriver = WebManager.webdriver;

        Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage(WebManager.webdriver);
        Driver_DashboardPage Page_Driver_Dashboard = new Driver_DashboardPage(WebManager.webdriver);
        Driver_LoginPagecs Page_Driver_Login = new Driver_LoginPagecs(WebManager.webdriver);

        Data_Reusable_Driver Data_Driver = new Data_Reusable_Driver();
        Data_Validation_Driver Data_valid_Driver = new Data_Validation_Driver();

        WebUtilityFunctions WebUtils = new WebUtilityFunctions();
        GeneralUtilityFunctions Functions = new GeneralUtilityFunctions();
        
        [When(@"I enter ""(.*)"" driver Phone Number on Driver portal")]
        public void WhenIEnterDriverPhoneNumberOnDriverPortal(string p0)
        {
            switch (p0)
            {
                case "valid":
                    WebDriverAction.SendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone, Data_Driver.DriverPhoneNumber);
                    break;
                case "invalid":
                    WebDriverAction.SendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone, Data_Driver.Invalid_DriverPhoneNumber);
                    break;
                default: break;
            }
        }
        
        [When(@"I enter ""(.*)"" driver Password on Driver portal")]
        public void WhenIEnterDriverPasswordOnDriverPortal(string p0)
        {
            switch (p0)
            {
                case "valid":
                    WebDriverAction.SendKeys(Page_Driver_Login.TextBox_DriverLogin_Password, Data_Driver.DriverPassword);
                    break;
                case "invalid":
                    WebDriverAction.SendKeys(Page_Driver_Login.TextBox_DriverLogin_Password, Data_Driver.Invalid_DriverPassword);
                    break;
                default: break;
            }
        }
        
        [Then(@"the driver should ""(.*)""")]
        public void ThenTheDriverShould(string p0)
        {
            switch (p0)
            {
                case "be logged in":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Dashboard.Header_Dashboard, Data_valid_Driver.DriverDashboardHeader);
                    break;
                case "see validation message for blank fields":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Login.Err_DriverLogin_Blank, Data_valid_Driver.Err_Pages_BlankFields);
                    break;
                case "see validation message for invalid phone field":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Login.Err_DriverLogin_Phone, Data_valid_Driver.Err_DriverLogin_Phone);
                    break;
                case "see validation message for incorrect credentials":
                    WebAssertionManager.ElementTextEqual(Page_Driver_Login.Err_DriverLogin_FieldValidation, Data_valid_Driver.Err_DriverLogin_IncorrectCredentials);
                    break;
                default: break;
            }
        }
    }
}
