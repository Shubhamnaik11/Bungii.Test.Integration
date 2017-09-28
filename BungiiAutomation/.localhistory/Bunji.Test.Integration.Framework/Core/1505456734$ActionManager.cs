﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using OpenQA.Selenium.Support;
using OpenQA.Selenium;

namespace Bungii.Test.Integration.Framework.Core
{
   public class ActionManager
    {
        public bool isElementPresent(By locatorKey)
        {
            try
            {
                AndroidManager.androiddriver.FindElement(locatorKey);
                return true;
            }
            catch (NoSuchElementException e)
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

            catch (Exception e)
            {
            }

        }
    }
}
