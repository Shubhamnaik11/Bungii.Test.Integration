using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.IE;
using System;
using System.Configuration;
using System.Drawing.Imaging;
using System.IO;
using TechTalk.SpecFlow;

namespace Bungii.Test.Integration.Framework.Core.Web
{
    public static class WebManager
    {
        public static IWebDriver webdriver = null;
        private static string browser = ConfigurationManager.AppSettings["Browser"];
        private static string environment = ConfigurationManager.AppSettings["Environment"];
        private static string SnapshotsDir = ConfigurationManager.AppSettings["SnapshotsDirectory"];

        public static void InitializeDriver()
        {
            switch (browser)
            {
                case "Chrome":
                    SetupChrome();
                    break;

                case "InternetExplorer":
                    SetupInternetExplorer();
                    break;

                default:
                    SetupChrome();
                    break;
            }
        }

        #region Browser Setups

        private static void SetupInternetExplorer()
        {
            InternetExplorerOptions options = new InternetExplorerOptions
            {
                EnablePersistentHover = false,
                EnableNativeEvents = false,
                RequireWindowFocus = true
            };

            OpenQA.Selenium.Remote.DesiredCapabilities capability = OpenQA.Selenium.Remote.DesiredCapabilities.InternetExplorer();
            string BrowserDirectory = ConfigurationManager.AppSettings["BrowserDirectory"];

            webdriver = new InternetExplorerDriver(@BrowserDirectory, options);
            webdriver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(60));
            webdriver.Manage().Timeouts().SetPageLoadTimeout(TimeSpan.FromSeconds(60));
            webdriver.Manage().Timeouts().SetScriptTimeout(TimeSpan.FromSeconds(60));
            webdriver.Manage().Window.Maximize();
        }

        private static void SetupChrome()
        {
            string BrowserDirectory = ConfigurationManager.AppSettings["BrowserDirectory"];

            webdriver = new ChromeDriver(@BrowserDirectory);
            webdriver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(60));
            webdriver.Manage().Timeouts().SetPageLoadTimeout(TimeSpan.FromSeconds(60));
            webdriver.Manage().Timeouts().SetScriptTimeout(TimeSpan.FromSeconds(60));
             webdriver.Manage().deleteAllCookies();
            //webdriver.Manage().Window.Maximize();
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
            webdriver.Quit();
        }

        public static void TakeScreenshot(String filename)
        {
            ITakesScreenshot screenshotDriver = webdriver as ITakesScreenshot;
            Screenshot screenshot = screenshotDriver.GetScreenshot();
            screenshot.SaveAsFile(filename, ImageFormat.Png);
        }

        #endregion
    }
}
