using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bunji.Web.Regression.Test.Integration
{
    [Binding]
    public class SpecflowHooks
    {

        public static AppiumDriver<AndroidElement> driver;
        [BeforeScenario]
        public static void BungiiCustomerLaunchSamsung()
        {
            OpenQA.Selenium.Remote.DesiredCapabilities capabilities = new OpenQA.Selenium.Remote.DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, ConfigurationManager.AppSettings["BrowserName"]);
            capabilities.SetCapability("deviceName", ConfigurationManager.AppSettings["samsungdeviceName"]);
            capabilities.SetCapability("platformVersion", ConfigurationManager.AppSettings["Version"]);
            capabilities.SetCapability("platformName", ConfigurationManager.AppSettings["Platform"]);
            capabilities.SetCapability("appPackage", ConfigurationManager.AppSettings["BungiiCustomerPackage"]);
            capabilities.SetCapability("appActivity", ConfigurationManager.AppSettings["BungiiCustomerActivities"]);
            capabilities.SetCapability("app", ConfigurationManager.AppSettings["customerapp_qa"]);
            capabilities.SetCapability("newCommandTimeout", ConfigurationManager.AppSettings["Timeout"]);
            driver = new AndroidDriver<AndroidElement>(new Uri(ConfigurationManager.AppSettings["URL"]), capabilities);

            //   return driver;
        }



    }
}
