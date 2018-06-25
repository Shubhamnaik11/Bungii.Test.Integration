﻿using System;
using System.Configuration;

namespace Bungii.Test.Integration.Framework.Core.AndroidDriver
{
    public class KeyManager_DriverApp
    {
        protected static Int32 ImplicitlyWaitTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["ImplicitlyWaitTimeoutSeconds"]);
        protected static Int32 SetScriptTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["SetScriptTimeoutSeconds"]);
        protected static Int32 WebDriverExplictTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["SetPageLoadTimeoutSeconds"]);
        protected static Int32 ReadyStateTimeOutSeconds = Int32.Parse(ConfigurationManager.AppSettings["ReadyStateTimeOutSeconds"]);
        protected static Int32 PauseTimeMilliSeconds = Int32.Parse(ConfigurationManager.AppSettings["PauseTimeMilliSeconds"]);
        protected static Int32 PauseTimeLongerMilliSeconds = Int32.Parse(ConfigurationManager.AppSettings["PauseTimeLongerMilliSeconds"]);
        protected static string deviceType = ConfigurationManager.AppSettings["DriverAppdeviceType"];
        protected static string connection = ConfigurationManager.AppSettings["Database.ConnectionUri"];
    }
}
