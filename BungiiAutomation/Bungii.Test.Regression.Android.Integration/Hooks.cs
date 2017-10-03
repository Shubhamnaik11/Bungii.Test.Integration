using TechTalk.SpecFlow;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium;
using System.Threading;
using Bungii.Test.Integration.Framework.Core.Android;
using Bungii.Test.Regression.Android.Integration.Pages;

namespace Bungii.Test.Regression.Android.Integration
{
    [Binding]
    public class Hooks
    {
        public static AppiumDriver<AndroidElement> driver = null;

        HomePage Page_Home = new HomePage(AndroidManager.androiddriver);
        MenuPage Page_Menu = new MenuPage(AndroidManager.androiddriver);

        [BeforeScenario]
        public static void Initialize()
        {    
            AndroidManager.InitializeDriver();
        }

        [AfterScenario]
        public static void TearDown()
        {
            Hooks hooks = new Hooks();
            hooks.Logout();
            AndroidManager.Quit(ScenarioContext.Current);
        }

        public void Logout()
        {
            if (DriverAction.isElementPresent(Page_Home.Link_Menu))
            {
                DriverAction.Click(Page_Home.Link_Menu);
                DriverAction.Click(Page_Menu.Menu_Logout);
            }
        }

        //[AfterStep]
        //public static void AfterStep()
        //{
        //    Thread.Sleep(1000);
        //}
    }
}
