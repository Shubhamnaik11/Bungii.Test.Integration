using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class ScheduledSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;

        Data_Reusable_Customer Data_Customer = new Data_Reusable_Customer();
        Data_Validations_Customer Data_Valid_Customer = new Data_Validations_Customer();

        UtilityFunctions UtilFunctions = new UtilityFunctions();
        BungiiCalculations Calc = new BungiiCalculations();

        [Then(@"I should see ""(.*)"" on Scheduled List page")]
        public void ThenIShouldSeeOnScheduledListPage(string p0)
        {
            switch (p0)
            {
                case "Requested Bungii":

                    break;

                default: break;
            }
        }
    }
}
