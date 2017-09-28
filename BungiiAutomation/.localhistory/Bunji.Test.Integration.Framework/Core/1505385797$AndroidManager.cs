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
        public static AppiumDriver<AndroidElement> androiddriver = null;
        private static string samsungDeviceName = ConfigurationManager.AppSettings["samsungDeviceName"];
        private static string samsungVersion = ConfigurationManager.AppSettings["samsungVersion"];
        private static string platform = ConfigurationManager.AppSettings["samsungPlatform"];
        private static string browserName = ConfigurationManager.AppSettings["browserName"];
        private static string listner = ConfigurationManager.AppSettings["ListnerURL"];
        public static void InitializeDriver(string DeviceType)
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
        private static void SamsungSetup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, browserName);
            capabilities.SetCapability("deviceName", samsungDeviceName);
            capabilities.SetCapability("platformVersion", samsungVersion);
            capabilities.SetCapability("platformName", platform);
            capabilities.SetCapability("appPackage", ConfigurationManager.AppSettings["BungiiCustomerPackage"]);
            capabilities.SetCapability("appActivity", ConfigurationManager.AppSettings["BungiiCustomerActivities"]);
            capabilities.SetCapability("app", ConfigurationManager.AppSettings["customerapp_qa"]);
            capabilities.SetCapability("newCommandTimeout", ConfigurationManager.AppSettings["Timeout"]);
       
            InitializeAndroidDriver(capabilities);
        }

        private static void MotorolaSetup()
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

        private static void InitializeAndroidDriver(DesiredCapabilities capabilities)
        {
            androiddriver = new AndroidDriver<AndroidElement>(new Uri(listner), capabilities);
        }

        public static void Quit()
        {
            androiddriver.Close();
        }
    }
}
