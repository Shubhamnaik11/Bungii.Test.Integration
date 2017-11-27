using Bungii.Test.Integration.Framework.Core.Web;
using Bungii.Test.Regression.Android.Integration.Pages.Simulator;
using OpenQA.Selenium;
using System.Configuration;
using TechTalk.SpecFlow;

namespace Bungii.Test.Regression.Android.Integration.StepDefinitions
{
    [Binding]
    public class SimulatorActionsSteps
    {        
        public IWebDriver webdriver = null;
        SimulatorPage Page_Simulator = null;        
        private static string Stage_Simulator = ConfigurationManager.AppSettings["DriverSimulator_Stage"];

        public SimulatorActionsSteps()
        {
            WebManager.InitializeDriver();
            webdriver = WebManager.webdriver;
            Page_Simulator = new SimulatorPage(WebManager.webdriver);
        }

        [When(@"simulator driver comes online")]
        public void WhenSimulatorDriverComesOnline()
        {            
            WebDriverAction.NavigateToUrl(Stage_Simulator);
            WebDriverAction.Click(Page_Simulator.DSim_Btn_Start);
        }

        [When(@"Simulator Bungii Driver ""(.*)""")]
        public void WhenSimulatorBungiiDriver(string p0)
        {
            switch (p0)
            {
                case "accepts Bungii":
                    WebDriverAction.Click(Page_Simulator.DSim_Btn_Accept);
                    break;
                case "rejects Bungii":
                    WebDriverAction.Click(Page_Simulator.DSim_Btn_Reject);
                    break;
                case "cancels Bungii":
                    WebDriverAction.Click(Page_Simulator.DSim_Btn_Cancel);
                    break;
                case "arrives at pickup location":
                    WebDriverAction.Click(Page_Simulator.DSim_Btn_Arrived);
                    break;
                case "starts loading items":
                    WebDriverAction.Click(Page_Simulator.DSim_Btn_Loading);
                    break;
                case "starts driving to dropoff":
                    WebDriverAction.Click(Page_Simulator.DSim_Btn_DrivingToDropoff);
                    break;
                case "starts unloading items":
                    WebDriverAction.Click(Page_Simulator.DSim_Btn_Unloading);
                    break;
                case "completes Bungii":
                    WebDriverAction.Click(Page_Simulator.DSim_Btn_Complete);
                    Quit();
                    break;
                default: break;
            }
        }

        public void Quit()
        {
            WebManager.Quit(ScenarioContext.Current);
        }
    }
}
