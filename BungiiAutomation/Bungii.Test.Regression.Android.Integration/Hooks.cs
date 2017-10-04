using TechTalk.SpecFlow;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium;
using Bungii.Test.Integration.Framework.Core.Android;

namespace Bungii.Test.Regression.Android.Integration
{
    [Binding]
    public class Hooks
    {
        public static AppiumDriver<AndroidElement> driver = null;

        [BeforeScenario]
        public static void Initialize()
        {    
            AndroidManager.InitializeDriver();
        }

        [AfterScenario]
        public static void TearDown()
        {
            AndroidManager.Quit(ScenarioContext.Current);
        }
    }
}
