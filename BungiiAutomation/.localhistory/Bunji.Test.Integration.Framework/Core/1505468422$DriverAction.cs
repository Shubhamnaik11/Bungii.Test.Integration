using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using OpenQA.Selenium.Support;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TechTalk.SpecFlow;

namespace Bungii.Test.Integration.Framework.Core
{
    [Binding]
    public class ActionManager : KeyManager
    {

        #region Waits
        public static void WaitUntilIsElementExistsAndDisplayed(IWebElement element)
        {
            try
            {
                Thread.Sleep(PauseTimeLongerMilliSeconds);
                WebDriverWait wait = new WebDriverWait(AndroidManager.androiddriver, new TimeSpan(0, 0, WebDriverExplictTimeoutSeconds));
                wait.Until((driver => element.Displayed));

            }
            catch (Exception ex)
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
                WebDriverWait wait = new WebDriverWait(AndroidManager.androiddriver, new TimeSpan(0, 0, 60));
                wait.Until((driver => element.Displayed));
                return true;

            }
            catch (Exception ex)
            {
                return true;
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

            catch (Exception e)
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
    }
    }

