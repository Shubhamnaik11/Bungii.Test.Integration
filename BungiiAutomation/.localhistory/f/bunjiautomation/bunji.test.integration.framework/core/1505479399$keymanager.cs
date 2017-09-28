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
        private static Int32 ImplicitlyWaitTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["ImplicitlyWaitTimeoutSeconds"]);
        private static Int32 SetScriptTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["SetScriptTimeoutSeconds"]);
        private static Int32 WebDriverExplictTimeoutSeconds = Int32.Parse(ConfigurationManager.AppSettings["SetPageLoadTimeoutSeconds"]);
        private static Int32 ReadyStateTimeOutSeconds = Int32.Parse(ConfigurationManager.AppSettings["ReadyStateTimeOutSeconds"]);
        private static Int32 PauseTimeMilliSeconds = Int32.Parse(ConfigurationManager.AppSettings["PauseTimeMilliSeconds"]);
        private static Int32 PauseTimeLongerMilliSeconds = Int32.Parse(ConfigurationManager.AppSettings["PauseTimeLongerMilliSeconds"]);
    }
}
