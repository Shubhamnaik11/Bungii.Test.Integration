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
    class AndroidManager
    {

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
                default:

            }
        }
        public static void SamsungSetup()
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
        }

        public static void MotorolaSetup()
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
        }

        public static void
    }
}
