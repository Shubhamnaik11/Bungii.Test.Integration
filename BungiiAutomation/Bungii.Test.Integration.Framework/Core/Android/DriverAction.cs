using System;
using System.Threading;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TechTalk.SpecFlow;

namespace Bungii.Test.Integration.Framework.Core.Android
{
    [Binding]
    public class DriverAction : KeyManager
    {
        #region Waits        

        public static void WaitUntilSnackbarExistsAndDisplayed(IWebElement element)
        {
            try
            {
                WebDriverWait wait = new WebDriverWait(AndroidManager.androiddriver, new TimeSpan(0, 0, 5));
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
            AndroidManager.androiddriver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromMilliseconds(PauseTimeMilliSeconds * 10));
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

        public static void keyBoardEvent(int eventNumber)
        {
            try
            {
                string strCmdText;
                strCmdText = "cmd /C adb shell input keyevent " + eventNumber;
                System.Diagnostics.Process.Start("CMD.exe", strCmdText);
                Thread.Sleep(3000);
            }
            catch (Exception)
            {
            }
        }

        public static void AddValueToScenarioContextVariable(string VariableName, string value)
        {
            ScenarioContext.Current.Add(VariableName, value);
        }

        public static string GetValueFromScenarioContextVariable(string VariableName)
        {
            return ScenarioContext.Current.Get<String>(VariableName);
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
            AndroidManager.androiddriver.HideKeyboard();
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

        public static void NavigateBack()
        {
            AndroidManager.androiddriver.Navigate().Back();
        }

        public static void WaitUntilIsElementExistsAndDisplayed(IWebElement element)
        {
            try
            {
                Thread.Sleep(PauseTimeMilliSeconds * 3);
                WebDriverWait wait = new WebDriverWait(AndroidManager.androiddriver, new TimeSpan(0, 0, 30));
                wait.Until((driver => element.Displayed));
            }
            catch (Exception)
            {
                Assert.Fail("Following element is not displayed : " + element);
            }
        }
    }
}