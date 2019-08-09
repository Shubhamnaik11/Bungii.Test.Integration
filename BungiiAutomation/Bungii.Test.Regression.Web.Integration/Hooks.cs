using Bungii.Test.Integration.Framework.Core.Web;
using OpenQA.Selenium;
using TechTalk.SpecFlow;

namespace Bungii.Android.Regression.Test.Integration
{
    [Binding]
    public class Hooks
    {
        public static IWebDriver webdriver = null;

        [BeforeScenario]
        public static void Initialise()
        {
            WebManager.InitializeDriver();
        }

        [AfterScenario]
        public static void TearDown()
        {
            WebManager.Quit(ScenarioContext.Current);
        }
    }
}
