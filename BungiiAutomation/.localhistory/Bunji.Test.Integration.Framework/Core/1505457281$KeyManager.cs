using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bungii.Test.Integration.Framework.Core
{
    public partial class KeyManager
    {
        public static Int32 ImplicitlyWaitTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["ImplicitlyWaitTimeoutSeconds"]);
        public static Int32 SetScriptTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["SetScriptTimeoutSeconds"]);
        public static Int32 WebDriverExplictTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["SetPageLoadTimeoutSeconds"]);
        public static Int32 ReadyStateTimeOutSeconds = Int32.Parse(ConfigurationManager.AppSettings["ReadyStateTimeOutSeconds"]);
        public static Int32 PauseTimeMilliSeconds = Int32.Parse(ConfigurationManager.AppSettings["PauseTimeMilliSeconds"]);
        public static Int32 PauseTimeLongerMilliSeconds = Int32.Parse(ConfigurationManager.AppSettings["PauseTimeLongerMilliSeconds"]);
    }
}
