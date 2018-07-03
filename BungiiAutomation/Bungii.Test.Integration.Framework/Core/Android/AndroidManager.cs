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

        private static string ApplicationCustomerUrl = ConfigurationManager.AppSettings["ApplicationCustomerUrl"];
        private static string Emulator5_0DeviceName = ConfigurationManager.AppSettings["Emulator5_0DeviceName"];
        private static string Emulator5_0Version = ConfigurationManager.AppSettings["Emulator5_0Version"];
        private static string Emulator5_1DeviceName = ConfigurationManager.AppSettings["Emulator5_1DeviceName"];
        private static string Emulator5_1Version = ConfigurationManager.AppSettings["Emulator5_1Version"];
        private static string SamsungS6DeviceName = ConfigurationManager.AppSettings["SamsungS6DeviceName"];
        private static string SamsungS6Version = ConfigurationManager.AppSettings["SamsungS6Version"];
        private static string SamsungS5DeviceName = ConfigurationManager.AppSettings["SamsungS5DeviceName"];
        private static string SamsungS5Version = ConfigurationManager.AppSettings["SamsungS5Version"];
        private static string MotoG4DeviceName = ConfigurationManager.AppSettings["MotoG4DeviceName"];
        private static string MotoG4Version = ConfigurationManager.AppSettings["MotoG4Version"];
        private static string MotoGDeviceName = ConfigurationManager.AppSettings["MotoGDeviceName"];
        private static string MotoGVersion = ConfigurationManager.AppSettings["MotoGVersion"];
        private static string NokiaDeviceName = ConfigurationManager.AppSettings["NokiaDeviceName"];
        private static string NokiaVersion = ConfigurationManager.AppSettings["NokiaVersion"];
        private static string Platform = ConfigurationManager.AppSettings["Platform"];
        private static string BrowserName = ConfigurationManager.AppSettings["BrowserName"];
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
                case "MotoG4":
                    MotoG4Setup();
                    break;
                case "MotoG":
                    MotoGSetup();
                    break;
                case "Nokia6":
                    Nokia6Setup();
                    break;
                case "Emulator5_0":
                    Emulator5_0Setup();
                    break;
                case "Emulator5_1":
                    Emulator5_1Setup();
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
                    appPath = ConfigurationManager.AppSettings["app_customerQaPath"];
                    break;
                case "Stage":
                    appPath = ConfigurationManager.AppSettings["app_customerStagePath"];
                    break;
                default:
                    appPath = ConfigurationManager.AppSettings["app_customerQaPath"];
                    break;
            }
        }

        private static void Emulator5_0Setup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, BrowserName);
            capabilities.SetCapability("deviceName", Emulator5_0DeviceName);
            capabilities.SetCapability("platformVersion", Emulator5_1Version);
            capabilities.SetCapability("platformName", Platform);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver(capabilities);
        }

        private static void Emulator5_1Setup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, BrowserName);
            capabilities.SetCapability("deviceName", Emulator5_1DeviceName);
            capabilities.SetCapability("platformVersion", Emulator5_1Version);
            capabilities.SetCapability("platformName", Platform);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver(capabilities);
        }

        private static void SamsungS6Setup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();            
            capabilities.SetCapability(CapabilityType.BrowserName, BrowserName);
            capabilities.SetCapability("automationName", "UiAutomator2");
            capabilities.SetCapability("deviceName", SamsungS6DeviceName);
            capabilities.SetCapability("platformVersion", SamsungS6Version);
            capabilities.SetCapability("platformName", Platform);
            //capabilities.SetCapability("appPackage", package);
            //capabilities.SetCapability("appActivity", activity);
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
            capabilities.SetCapability(CapabilityType.BrowserName, BrowserName);
            capabilities.SetCapability("deviceName", SamsungS5DeviceName);
            capabilities.SetCapability("platformVersion", SamsungS5Version);
            capabilities.SetCapability("platformName", Platform);
            //capabilities.SetCapability("appPackage", package);
            //capabilities.SetCapability("appActivity", activity);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver(capabilities);
        }

        private static void MotoG4Setup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, BrowserName);
            capabilities.SetCapability("deviceName", MotoG4DeviceName);
            capabilities.SetCapability("platformVersion", MotoG4Version);
            capabilities.SetCapability("platformName", Platform);
            //capabilities.SetCapability("appPackage", package);
            //capabilities.SetCapability("appActivity", activity);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver(capabilities);
        }

        private static void MotoGSetup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, BrowserName);
            capabilities.SetCapability("deviceName", MotoGDeviceName);
            capabilities.SetCapability("platformVersion", MotoGVersion);
            capabilities.SetCapability("platformName", Platform);
            //capabilities.SetCapability("appPackage", package);
            //capabilities.SetCapability("appActivity", activity);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver(capabilities);
        }

        private static void Nokia6Setup()
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.SetCapability(CapabilityType.BrowserName, BrowserName);
            capabilities.SetCapability("deviceName", NokiaDeviceName);
            capabilities.SetCapability("platformVersion", NokiaVersion);
            capabilities.SetCapability("platformName", Platform);
            //capabilities.SetCapability("appPackage", package);
            //capabilities.SetCapability("appActivity", activity);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "false");
            capabilities.SetCapability("full-reset", "true");
            //capabilities.SetCapability("unicodeKeyboard", false);
            //capabilities.SetCapability("resetKeyboard", false);
            capabilities.SetCapability("autoWebView", "true");
            InitializeAndroidDriver(capabilities);
        }

        private static void InitializeAndroidDriver(DesiredCapabilities capabilities)
        {
            androiddriver = new AndroidDriver<AndroidElement>(new Uri(ApplicationCustomerUrl), capabilities);
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
