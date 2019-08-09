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
    public class Menu_SupportSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;
        
        SupportPage Page_Support = new SupportPage(AndroidManager.androiddriver);
        LoginPage Page_Login = new LoginPage(AndroidManager.androiddriver);
        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();
        UtilityFunctions UtilFunctions = new UtilityFunctions();

        [When(@"I enter ""(.*)"" text in Support field")]
        public void WhenIEnterTextInSupportField(string p0)
        {
            switch (p0)
            {
                case "valid":
                    DriverAction.SendKeys(Page_Support.TextField, Data_Customer.Support_Text);
                    break;
                case "space":
                    DriverAction.SendKeys(Page_Support.TextField, Data_Customer.BlankSpaces);
                    break;
                default: break;
            }
        }
        
        [When(@"I tap ""(.*)"" on Support page")]
        public void WhenITapOnSupportPage(string p0)
        {
            DriverAction.Click(Page_Support.Button_Send);
        }
        
        [Then(@"The user should see ""(.*)"" on Support page")]
        public void ThenTheUserShouldSeeOnSupportPage(string p0)
        {
            switch (p0)
            {
                case "snackbar validation":
                    AssertionManager.SnackbarTextEqual(Page_Login.Snackbar, Data_Valid_Customer.SupportFeedback_Snackbar);
                    break;
                case "Send button disabled":
                    AssertionManager.ElementDisabled(Page_Support.Button_Send);
                    break;
                case "validation message":
                    AssertionManager.ElementTextEqual(Page_Support.Error_Blank, Data_Valid_Customer.SupportFeeback_Error);
                    break;
                default: break;
            }
        }
    }
}
