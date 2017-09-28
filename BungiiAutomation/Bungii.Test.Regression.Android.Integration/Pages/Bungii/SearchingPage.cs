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
        public IWebElement Label_Searching { get; set; }
    }
}
