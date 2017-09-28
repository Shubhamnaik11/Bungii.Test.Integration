using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TechTalk.SpecFlow;
using System.Configuration;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Remote;
using Bungii.Test.Integration.Framework.Core;
using System.Threading;

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

        [AfterStep]
        public static void AfterStep()
        {
            Thread.Sleep(1000);
        }
    }
}
