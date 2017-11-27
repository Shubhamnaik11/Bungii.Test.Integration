using System;
using System.Configuration;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium;
using TechTalk.SpecFlow;
using System.Drawing.Imaging;
using System.IO;

namespace Bungii.Test.Integration.Framework.Core.Android
{
    public static class AndroidManager
    {
        public static AppiumDriver<AndroidElement> androiddriver = null;

        private static string samsungS6DeviceName = ConfigurationManager.AppSettings["samsungS6DeviceName"];
        private static string samsungS6Version = ConfigurationManager.AppSettings["samsungS6Version"];
        private static string samsungS5DeviceName = ConfigurationManager.AppSettings["samsungS5DeviceName"];
        private static string samsungS5Version = ConfigurationManager.AppSettings["samsungS5Version"];
        private static string motorolaDeviceName = ConfigurationManager.AppSettings["motorolaDeviceName"];
        private static string motorolaVersion = ConfigurationManager.AppSettings["motorolaVersion"];
        private static string platform = ConfigurationManager.AppSettings["platform"];
        private static string browserName = ConfigurationManager.AppSettings["browserName"];
        private static string listner = ConfigurationManager.AppSettings["ListnerURL"];
        private static string timeout = ConfigurationManager.AppSettings["Timeout"];
        private static string package = ConfigurationManager.AppSettings["BungiiCustomerPackage"];
        private static string activity = ConfigurationManager.AppSettings["BungiiCustomerActivities"];
        private static string deviceType = ConfigurationManager.AppSettings["deviceType"];
        private static string environment = ConfigurationManager.AppSettings["environment"];
        private static string SnapshotsDir = ConfigurationManager.AppSettings["SnapshotsDirectory"];
        public static string appPath = null;

        public static void InitializeDriver()
        {
            SetAppPath();
            switch (deviceType)
            {
                case "SamsungS6":
                    SamsungS6Setup();
                    break;
                case "SamsungS5":
                    SamsungS5Setup();
                    break;
                case "Motorola":
                    MotorolaSetup();
                    break;
                default:
                    SamsungS5Setup();
                    break;
            }
        }
        private static void SetAppPath()
        {
            switch (environment)
            {
                case "QA":
                    appPath = ConfigurationManager.AppSettings["app_QaPath"];
                    break;
                case "Stage":
                    appPath = ConfigurationManager.AppSettings["app_StagePath"];
                    break;
                default:
                    appPath = ConfigurationManager.AppSettings["app_QaPath"];
                    break;
            }
        }
        private static void SamsungS6Setup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, browserName);
            capabilities.SetCapability("deviceName", samsungS6DeviceName);
            capabilities.SetCapability("platformVersion", samsungS6Version);
            capabilities.SetCapability("platformName", platform);
            capabilities.SetCapability("appPackage", package);
            capabilities.SetCapability("appActivity", activity);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            //capabilities.SetCapability("unicodeKeyboard", false);
            //capabilities.SetCapability("resetKeyboard", false);
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver(capabilities);
        }

        private static void SamsungS5Setup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, browserName);
            capabilities.SetCapability("deviceName", samsungS5DeviceName);
            capabilities.SetCapability("platformVersion", samsungS5Version);
            capabilities.SetCapability("platformName", platform);
            capabilities.SetCapability("appPackage", package);
            capabilities.SetCapability("appActivity", activity);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            //capabilities.SetCapability("unicodeKeyboard", false);
            //capabilities.SetCapability("resetKeyboard", false);
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver(capabilities);
        }

        private static void MotorolaSetup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, browserName);
            capabilities.SetCapability("deviceName", motorolaDeviceName);
            capabilities.SetCapability("platformVersion", motorolaVersion);
            capabilities.SetCapability("platformName", platform);
            capabilities.SetCapability("appPackage", package);
            capabilities.SetCapability("appActivity", activity);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            //capabilities.SetCapability("resetKeyboard", true);
            //capabilities.SetCapability("unicodeKeyboard", true);
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver(capabilities);
        }

        private static void InitializeAndroidDriver(DesiredCapabilities capabilities)
        {
            androiddriver = new AndroidDriver<AndroidElement>(new Uri(listner), capabilities);
        }

        public static void Quit(ScenarioContext scenarioContext)
        {
            if (ScenarioContext.Current.TestError != null)
            {
                string Date = DateTime.Now.ToString("dd-MM-yyyy");
                string path = SnapshotsDir + "/BungiiAndroid_" + Date;
                if (!Directory.Exists(path))
                {
                    System.IO.Directory.CreateDirectory(path);
                }
                String filenname = scenarioContext.ScenarioInfo.Title;
                TakeScreenshot(path + "/" + filenname + ".png");
            }
            androiddriver.Quit();
        }

        public static void TakeScreenshot(String filename)
        {
            ITakesScreenshot screenshotDriver = androiddriver as ITakesScreenshot;
            Screenshot screenshot = screenshotDriver.GetScreenshot();
            screenshot.SaveAsFile(filename, ImageFormat.Png);
        }
    }
}
