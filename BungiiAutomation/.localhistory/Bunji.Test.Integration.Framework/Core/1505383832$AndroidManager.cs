using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Configuration;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Remote;

namespace Bungii.Test.Integration.Framework.Core
{
   public static class AndroidManager
    {
        public static AppiumDriver<AndroidElement> driver = null;
        public static string samsungDeviceName = ConfigurationManager.AppSettings["samsungDeviceName"];
        public static string samsungVersion = ConfigurationManager.AppSettings["samsungVersion"];
        public static string Platform = ConfigurationManager.AppSettings["samsungPlatform"];
        public static string browserName = ConfigurationManager.AppSettings["browserName"];
        public static void DriverInitialize(string DeviceType)
        {
            switch (DeviceType)
            {
                case "Samsung":
                    SamsungSetup();
                    break;
                case "Motorola":
                    MotorolaSetup();
                    break;
                default :
                    SamsungSetup();
                    break;

            }
        }
        public static void SamsungSetup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, ConfigurationManager.AppSettings["BrowserName"]);
            capabilities.SetCapability("deviceName", samsungDeviceName);
            capabilities.SetCapability("platformVersion", version);
            capabilities.SetCapability("platformName", platform);
            capabilities.SetCapability("appPackage", ConfigurationManager.AppSettings["BungiiCustomerPackage"]);
            capabilities.SetCapability("appActivity", ConfigurationManager.AppSettings["BungiiCustomerActivities"]);
            capabilities.SetCapability("app", ConfigurationManager.AppSettings["customerapp_qa"]);
            capabilities.SetCapability("newCommandTimeout", ConfigurationManager.AppSettings["Timeout"]);
            InitializeAndroidDriver(capabilities);
        }

        public static void MotorolaSetup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, ConfigurationManager.AppSettings["BrowserName"]);
            capabilities.SetCapability("deviceName", ConfigurationManager.AppSettings["samsungdeviceName"]);
            capabilities.SetCapability("platformVersion", ConfigurationManager.AppSettings["Version"]);
            capabilities.SetCapability("platformName", ConfigurationManager.AppSettings["Platform"]);
            capabilities.SetCapability("appPackage", ConfigurationManager.AppSettings["BungiiCustomerPackage"]);
            capabilities.SetCapability("appActivity", ConfigurationManager.AppSettings["BungiiCustomerActivities"]);
            capabilities.SetCapability("app", ConfigurationManager.AppSettings["customerapp_qa"]);
            capabilities.SetCapability("newCommandTimeout", ConfigurationManager.AppSettings["Timeout"]);
            InitializeAndroidDriver(capabilities);
        }

        public static void InitializeAndroidDriver(DesiredCapabilities capabilities)
        {
            driver = new AndroidDriver<AndroidElement>(new Uri(ConfigurationManager.AppSettings["URL"]), capabilities);
        }
    }
}
