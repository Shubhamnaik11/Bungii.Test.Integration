using Bungii.Test.Integration.Framework.Core.Android;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using OpenQA.Selenium;
using OpenQA.Selenium.Appium.Interfaces;
using OpenQA.Selenium.Appium.MultiTouch;
using OpenQA.Selenium.Support.UI;
using System;
using System.Threading;
using TechTalk.SpecFlow;

namespace Bungii.Test.Integration.Framework.Core.AndroidDriver
{
    [Binding]
    public class DriverAction_DriverApp : KeyManager_DriverApp
    {
        public static void WaitUntilSnackbarExistsAndDisplayed(IWebElement element)
        {
            try
            {
                WebDriverWait wait = new WebDriverWait(AndroidManager_DriverApp.androiddriver_Driver, new TimeSpan(0, 0, 5));
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
            AndroidManager_DriverApp.androiddriver_Driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromMilliseconds(PauseTimeMilliSeconds * 10));
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
            AndroidManager_DriverApp.androiddriver_Driver.HideKeyboard();
        }

        public static void SendKeys1(IWebElement element, string text)
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

        public static void NavigateBack()
        {
            AndroidManager_DriverApp.androiddriver_Driver.Navigate().Back();
        }

        public static void HideKeyboard()
        {
            try
            {
                AndroidManager_DriverApp.androiddriver_Driver.HideKeyboard();
            }
            catch (Exception)
            {
            }
        }

        public static void SwipeLeft(IWebElement row)
        {
            int xShift = Convert.ToInt32(row.Size.Width * 0.20);
            int xStart = (row.Size.Width) - xShift;
            int xEnd = xShift;

            ITouchAction action = new TouchAction(AndroidManager_DriverApp.androiddriver_Driver)
            .Press(row, xStart, (row.Size.Height / 2))
            .Wait(1000)
            .MoveTo(row, xEnd, (row.Size.Height / 2))
            .Release();

            action.Perform();
            Thread.Sleep(2000);
        }

        public static void WaitUntilIsElementExistsAndDisplayed(IWebElement element)
        {
            try
            {
                Thread.Sleep(PauseTimeMilliSeconds * 3);
                WebDriverWait wait = new WebDriverWait(AndroidManager_DriverApp.androiddriver_Driver, new TimeSpan(0, 0, 30));
                wait.Until((driver => element.Displayed));
            }
            catch (Exception)
            {
                Assert.Fail("Following element is not displayed : " + element);
            }
        }

        public static void WaitUntilAlertDisplayed(IWebElement element)
        {
            try
            {
                Thread.Sleep(PauseTimeLongerMilliSeconds * 10);
                WebDriverWait wait = new WebDriverWait(AndroidManager_DriverApp.androiddriver_Driver, new TimeSpan(0, 8, 250));
                wait.Until((driver => element.Displayed));
            }
            catch (Exception)
            {
                Assert.Fail("Alert not received : " + element);
            }
        }
    }
}
