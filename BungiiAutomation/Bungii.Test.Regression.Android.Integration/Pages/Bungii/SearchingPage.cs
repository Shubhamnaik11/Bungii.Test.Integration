using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class SearchingPage
    {
        public SearchingPage(IWebDriver driver)
       {
           PageFactory.InitElements(driver, this);
       }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/toolbar_button_cancel")]
        public IWebElement Link_CancelSearch { get; set; }

        [FindsBy(How = How.Id, Using ="android:id/button1")]
        public IWebElement Button_CancelConfirm { get; set; }          //By.XPath("//android.widget.Button[@text='YES']")

        [FindsBy(How = How.Id, Using = "android:id/button2")]
        public IWebElement Button_CloseCancel { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='SEARCHING…']")]
        public IWebElement PageTitle_Searching { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/searching_view_progressbar")]
        public IWebElement Loader { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/searching_view_subtitle")]
        public IWebElement Text_MsgSearching { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/searching_view_horizontal_progressbar")]
        public IWebElement ProgressBar { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='DRIVER NOT AVAILABLE']")]
        public IWebElement PageTitle_DriverNotAvailable { get; set; }
    }
}
