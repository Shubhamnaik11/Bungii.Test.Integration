using System;
using System.Text;
using System.Threading;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TechTalk.SpecFlow;
using OpenQA.Selenium.Interactions;
using iTextSharp.text.pdf;
using iTextSharp.text.pdf.parser;

namespace Bungii.Test.Integration.Framework.Core.Web
{
    [Binding]
    public class WebDriverAction : WebKeyManager
    {
        #region Waits
        public static void WaitUntilIsElementExistsAndDisplayed(IWebElement element)
        {
            try
            {
                Thread.Sleep(PauseTimeMilliSeconds * 3);
                WebDriverWait wait = new WebDriverWait(WebManager.webdriver, new TimeSpan(0, 0, 10));
                wait.Until((driver => element.Displayed));
            }
            catch (Exception)
            {
                Assert.Fail("Following element is not displayed : " + element);
            }
        }

        public static bool isElementPresent(IWebElement element)
        {
            //Set the timeout to something low
            WebManager.webdriver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromMilliseconds(PauseTimeMilliSeconds * 10));
            try
            {
                bool isdisplayed = element.Displayed;
                return isdisplayed;
            }
            catch (Exception)
            {
                return false;
            }
        }

        #endregion

        public static void AddValueToScenarioContextVariable(string VariableName, string value)
        {
            ScenarioContext.Current.Add(VariableName, value);
        }

        public static string GetValueFromScenarioContextVariable(string VariableName)
        {
            return ScenarioContext.Current.Get<String>(VariableName);
        }

        public static void AddValueToFeatureContextVariable(string VariableName, string value)
        {
            FeatureContext.Current.Add(VariableName, value);
        }

        public static string GetValueFromFeatureContextVariable(string VariableName)
        {
            return FeatureContext.Current.Get<String>(VariableName);
        }

        public static void SendKeys(IWebElement element, string text)
        {
            Clear(element);
            element.Click();
            element.SendKeys(text);
        }

        public static void Clear(IWebElement element)
        {
            WaitUntilIsElementExistsAndDisplayed(element);
            element.Clear();
            Thread.Sleep(PauseTimeLongerMilliSeconds * 2);
        }

        public static void Click(IWebElement element)
        {
            WaitUntilIsElementExistsAndDisplayed(element);
            element.Click();
        }

        public static void ClickByReference(IWebElement element, By ActionLocator)
        {
            WaitUntilIsElementExistsAndDisplayed(element);
            element.FindElement(ActionLocator).Click();
        }

        public static void HoverAndClick(IWebElement element)
        {
            WaitUntilIsElementExistsAndDisplayed(element);
            Actions actions = new Actions(WebManager.webdriver);
            actions.MoveToElement(element);
            actions.Click().Perform();
            actions.Release().Perform();
        }

        public static void Hover(IWebElement element)
        {
            WaitUntilIsElementExistsAndDisplayed(element);
            Actions actions = new Actions(WebManager.webdriver);
            actions.MoveToElement(element);
            actions.Build().Perform();
        }

        public static void JsExecutorClick(IWebElement element, string javascript)
        {
            ((IJavaScriptExecutor)WebManager.webdriver).ExecuteScript(javascript, element);
        }

        public static void DismissAlert()
        {
            IAlert alert = WebManager.webdriver.SwitchTo().Alert();
            alert.Dismiss();
        }

        public static void AcceptAlert()
        {
            IAlert alert = WebManager.webdriver.SwitchTo().Alert();
            alert.Accept();
        }

        public static Boolean isAlertPresent()
        {
            try
            {
                WebManager.webdriver.SwitchTo().Alert();
                return true;
            }
            catch (NoAlertPresentException)
            {
                return false;
            }
        }

        public static void SelectElementByText(IWebElement element, string text)
        {
            WaitUntilIsElementExistsAndDisplayed(element);
            new SelectElement(element).SelectByText(text);
        }

        public static void SelectElementByValue(IWebElement element, string value)
        {
            WaitUntilIsElementExistsAndDisplayed(element);
            new SelectElement(element).SelectByValue(value);
        }

        public static void NavigateToUrl(string url)
        {
            WebManager.webdriver.Navigate().GoToUrl(url);
        }

        public static string ExtractTextFromPdf(IWebDriver driver)
        {
            WebDriverWait wait = new WebDriverWait(WebManager.webdriver, new TimeSpan(0, 0, WebDriverExplictTimeoutSeconds));

            Thread.Sleep(PauseTimeLongerMilliSeconds * 5);
            using (PdfReader reader = new PdfReader(driver.Url))
            {
                StringBuilder text = new StringBuilder();
                for (int i = 1; i <= reader.NumberOfPages; i++)
                {
                    text.Append(PdfTextExtractor.GetTextFromPage(reader, i));
                }
                return text.ToString();
            }
        }
    }
}

