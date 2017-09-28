using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bungii.Test.Integration.Framework.Core.Android
{
    public partial class KeyManager
    {
        protected static Int32 ImplicitlyWaitTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["ImplicitlyWaitTimeoutSeconds"]);
        protected static Int32 SetScriptTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["SetScriptTimeoutSeconds"]);
        protected static Int32 WebDriverExplictTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["SetPageLoadTimeoutSeconds"]);
        protected static Int32 ReadyStateTimeOutSeconds = Int32.Parse(ConfigurationManager.AppSettings["ReadyStateTimeOutSeconds"]);
        protected static Int32 PauseTimeMilliSeconds = Int32.Parse(ConfigurationManager.AppSettings["PauseTimeMilliSeconds"]);
        protected static Int32 PauseTimeLongerMilliSeconds = Int32.Parse(ConfigurationManager.AppSettings["PauseTimeLongerMilliSeconds"]);
        protected static string deviceType = ConfigurationManager.AppSettings["deviceType"];
        protected static string connection = ConfigurationManager.AppSettings["Database.ConnectionUri"];

    }
}
