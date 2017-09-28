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
using Bungii.Test.Integration.Framework;

namespace Bungii.Web.Regression.Test.Integration
{
    [Binding]
    public class Hooks
    {

        public static AppiumDriver<AndroidElement> driver;
        [BeforeScenario]
        public static void Initialize()
        {
           AndroidManager.
        }
        [AfterScenario]
        public static void TearDown()
        {

        }


    }
}
