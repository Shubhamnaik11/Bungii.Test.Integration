using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.Simulator
{
    public class SimulatorPage
    {
        public SimulatorPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }

        //Start Button
        [FindsBy(How = How.Id, Using = "btnStart")]
        public IWebElement DSim_Btn_Start { get; set; }

        // Message
        [FindsBy(How = How.Id, Using = "lblMessage")]
        public IWebElement DSim_Text_Msg { get; set; }

        //Accept Button
        [FindsBy(How = How.Id, Using = "btnAccept")]
        public IWebElement DSim_Btn_Accept { get; set; }

        //Reject Button
        [FindsBy(How = How.Id, Using = "btnReject")]
        public IWebElement DSim_Btn_Reject { get; set; }

        //Arrived Button
        [FindsBy(How = How.Id, Using = "btnArrived")]
        public IWebElement DSim_Btn_Arrived { get; set; }

        //Cancel Button
        [FindsBy(How = How.Id, Using = "btnCancel")]
        public IWebElement DSim_Btn_Cancel { get; set; }

        //Loading Button
        [FindsBy(How = How.Id, Using = "btnLoading")]
        public IWebElement DSim_Btn_Loading { get; set; }

        //Driving To Dropoff Button
        [FindsBy(How = How.Id, Using = "btnDrivingToDropoff")]
        public IWebElement DSim_Btn_DrivingToDropoff { get; set; }

        //Unloading Button
        [FindsBy(How = How.Id, Using = "btnUnloading")]
        public IWebElement DSim_Btn_Unloading { get; set; }

        //Complete Button
        [FindsBy(How = How.Id, Using = "btnComplete")]
        public IWebElement DSim_Btn_Complete { get; set; }

        //Click here to test again Link
        [FindsBy(How = How.XPath, Using = "//div[@id='Driver-Actions-Complete']/div/h4/a[text()='here']")]
        public IWebElement Link_Restart { get; set; }
    }
}
