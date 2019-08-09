using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Regression.Android.Integration.Data;
using Bungii.Test.Regression.Android.Integration.Functions;
using Bungii.Test.Regression.Android.Integration.Pages.MenuPages;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class ScheduledSteps
    {
        public AppiumDriver<AndroidElement> driver = AndroidManager.androiddriver;

        ScheduledBungiisPage Page_ScheduledBungiis = new ScheduledBungiisPage(AndroidManager.androiddriver);

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
                    AssertionManager.ElementDisplayed(Page_ScheduledBungiis.ScheduledBungii_01);
                    AssertionManager.ElementTextEqual(Page_ScheduledBungiis.Text_BungiiStatus_01, Data_Valid_Customer.Status_Contacting);
                    break;

                case "Bungii is accepted":
                    if (DriverAction.isElementPresent(Page_ScheduledBungiis.Alert_ScheduledTripAccepted))
                        DriverAction.Click(Page_ScheduledBungiis.Button_OK);
                    AssertionManager.ElementTextEqual(Page_ScheduledBungiis.Text_BungiiStatus_01, "~$45.00");
                    break;

                default: break;
            }
        }
    }
}
