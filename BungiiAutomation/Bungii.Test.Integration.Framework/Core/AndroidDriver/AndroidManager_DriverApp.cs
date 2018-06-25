using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Remote;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bungii.Test.Integration.Framework.Core.Android
{
    public class AndroidManager_DriverApp
    {
        public static AppiumDriver<AndroidElement> androiddriver_Driver = null;

        private static string ApplicationDriverUrl = ConfigurationManager.AppSettings["ApplicationDriverUrl"];
        private static string SamsungS6DeviceName = ConfigurationManager.AppSettings["SamsungS6DeviceName"];
        private static string SamsungS6Version = ConfigurationManager.AppSettings["SamsungS6Version"];
        private static string SamsungS5DeviceName = ConfigurationManager.AppSettings["SamsungS5DeviceName"];
        private static string SamsungS5Version = ConfigurationManager.AppSettings["SamsungS5Version"];
        private static string MotoGDeviceName = ConfigurationManager.AppSettings["MotoGDeviceName"];
        private static string MotoGVersion = ConfigurationManager.AppSettings["MotoGVersion"];
        private static string Platform = ConfigurationManager.AppSettings["Platform"];
        private static string BrowserName = ConfigurationManager.AppSettings["BrowserName"];
        private static string timeout = ConfigurationManager.AppSettings["Timeout"];
        private static string DriverAppdeviceType = ConfigurationManager.AppSettings["DriverAppdeviceType"];
        private static string environment = ConfigurationManager.AppSettings["environment"];
        private static string SnapshotsDir = ConfigurationManager.AppSettings["SnapshotsDirectory"];
        public static string appPath = null;

        public static void InitializeDriver()
        {
            SetAppPath();
            switch (DriverAppdeviceType)
            {
                case "SamsungS6":
                    SamsungS6DriverAppSetup();
                    break;
                case "SamsungS5":
                    SamsungS5DriverAppSetup();
                    break;
                case "MotoG":
                    MotoGDriverAppSetup();
                    break;
                default:
                    SamsungS6DriverAppSetup();
                    break;
            }
        }

        private static void SetAppPath()
        {
            switch (environment)
            {
                case "QA":
                    appPath = ConfigurationManager.AppSettings["app_driverQaPath"];
                    break;
                case "Stage":
                    appPath = ConfigurationManager.AppSettings["app_driverStagePath"];
                    break;
                default:
                    appPath = ConfigurationManager.AppSettings["app_driverQaPath"];
                    break;
            }
        }

        private static void SamsungS5DriverAppSetup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, BrowserName);
            capabilities.SetCapability("deviceName", SamsungS5DeviceName);
            capabilities.SetCapability("platformVersion", SamsungS5Version);
            capabilities.SetCapability("platformName", Platform);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver_DriverApp(capabilities);
        }

        private static void SamsungS6DriverAppSetup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, BrowserName);
            capabilities.SetCapability("deviceName", SamsungS6DeviceName);
            capabilities.SetCapability("platformVersion", SamsungS6Version);
            capabilities.SetCapability("platformName", Platform);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver_DriverApp(capabilities);
        }

        private static void MotoGDriverAppSetup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, BrowserName);
            capabilities.SetCapability("deviceName", MotoGDeviceName);
            capabilities.SetCapability("platformVersion", MotoGVersion);
            capabilities.SetCapability("platformName", Platform);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver_DriverApp(capabilities);
        }

        private static void InitializeAndroidDriver_DriverApp(DesiredCapabilities capabilities)
        {
            androiddriver_Driver = new AndroidDriver<AndroidElement>(new Uri(ApplicationDriverUrl), capabilities);
        }
    }
}
