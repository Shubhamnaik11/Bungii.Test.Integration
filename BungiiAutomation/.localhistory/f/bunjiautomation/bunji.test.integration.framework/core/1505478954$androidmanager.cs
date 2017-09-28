using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Configuration;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Remote;
using OpenQA.Selenium;
using TechTalk.SpecFlow;
using System.Drawing.Imaging;
using System.IO;

namespace Bungii.Test.Integration.Framework.Core
{
   public static class AndroidManager
    {
        public static AppiumDriver<AndroidElement> androiddriver = null;
        private static string samsungDeviceName = ConfigurationManager.AppSettings["samsungDeviceName"];
        private static string samsungVersion = ConfigurationManager.AppSettings["samsungVersion"];
        private static string motorolaDeviceName = ConfigurationManager.AppSettings["motorolaDeviceName"];
        private static string motorolaVersion = ConfigurationManager.AppSettings["motorolaVersion"];
        private static string platform = ConfigurationManager.AppSettings["platform"];
        private static string browserName = ConfigurationManager.AppSettings["browserName"];
        private static string listner = ConfigurationManager.AppSettings["ListnerURL"];
        private static string timeout = ConfigurationManager.AppSettings["Timeout"];
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
            capabilities.SetCapability(CapabilityType.BrowserName, browserName);
            capabilities.SetCapability("deviceName", samsungDeviceName);
            capabilities.SetCapability("platformVersion", samsungVersion);
            capabilities.SetCapability("platformName", platform);
            capabilities.SetCapability("appPackage", package);
            capabilities.SetCapability("appActivity", activity);
            capabilities.SetCapability("app", appPath);
            capabilities.SetCapability("newCommandTimeout", timeout);
            capabilities.SetCapability("no-reset", "true");
            capabilities.SetCapability("full-reset", "False");
            InitializeAndroidDriver(capabilities);
        }

        private static void MotorolaSetup(string package, string activity, string appPath)
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
                string path = "F:/Snapshots/BungiiAndroid_" + Date;
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
