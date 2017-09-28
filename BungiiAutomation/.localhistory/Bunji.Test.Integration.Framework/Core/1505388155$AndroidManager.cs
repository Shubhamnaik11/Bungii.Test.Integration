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

        public static void InitializeDriver(string DeviceType, string package, string activity, string appPath)
        {
            switch (DeviceType)
            {
                case "Samsung":
                    SamsungSetup(package, activity, appPath);
                    break;
                case "Motorola":
                    MotorolaSetup(package, activity, appPath);
                    break;
                default :
                    SamsungSetup(package, activity, appPath);
                    break;

            }
        }
        private static void SamsungSetup(string package, string activity, string appPath)
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, ConfigurationManager.AppSettings["browserName"]);
            capabilities.SetCapability("deviceName", ConfigurationManager.AppSettings["samsungDeviceName"]);
            capabilities.SetCapability("platformVersion", ConfigurationManager.AppSettings["samsungVersion"]);
            capabilities.SetCapability("platformName", ConfigurationManager.AppSettings["samsungPlatform"]);
            capabilities.SetCapability("appPackage", package);
            capabilities.SetCapability("appActivity", activity);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", ConfigurationManager.AppSettings["Timeout"]);
       
            InitializeAndroidDriver(capabilities);
        }

        private static void MotorolaSetup(string package, string activity, string appPath)
        {
            string b = ConfigurationManager.AppSettings["browserName"];
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, ConfigurationManager.AppSettings["browserName"]);
            capabilities.SetCapability("deviceName", ConfigurationManager.AppSettings["samsungDeviceName"]);
            capabilities.SetCapability("platformVersion", ConfigurationManager.AppSettings["samsungVersion"]);
            capabilities.SetCapability("platformName", ConfigurationManager.AppSettings["samsungPlatform"]);
            capabilities.SetCapability("appPackage", package);
            capabilities.SetCapability("appActivity", activity);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", ConfigurationManager.AppSettings["Timeout"]);
            InitializeAndroidDriver(capabilities);
        }

        private static void InitializeAndroidDriver(DesiredCapabilities capabilities)
        {
            androiddriver = new AndroidDriver<AndroidElement>(new Uri(ConfigurationManager.AppSettings["ListnerURL"]), capabilities);
        }

        public static void Quit()
        {
            androiddriver.Close();
        }
    }
}
