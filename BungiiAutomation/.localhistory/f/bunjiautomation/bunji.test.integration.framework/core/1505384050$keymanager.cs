using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Configuration;

namespace Bungii.Test.Integration.Framework.Core
{
    class KeyManager
    {

            public static string samsungDeviceName =ConfigurationManager.AppSettings["samsungDeviceName"];
            public static string version = ConfigurationManager.AppSettings["samsungVersion"];
            public static string platform = ConfigurationManager.AppSettings["samsungPlatform"];

            capabilities.SetCapability("appPackage", ConfigurationManager.AppSettings["BungiiCustomerPackage"]);
            capabilities.SetCapability("appActivity", ConfigurationManager.AppSettings["BungiiCustomerActivities"]);
            capabilities.SetCapability("app", ConfigurationManager.AppSettings["customerapp_qa"]);
            capabilities.SetCapability("newCommandTimeout", ConfigurationManager.AppSettings["Timeout"]);
    }
}
