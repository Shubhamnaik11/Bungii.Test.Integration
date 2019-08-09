using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class SupportPage
    {
        public SupportPage(IWebDriver driver)
        {
           PageFactory.InitElements(driver, this);
        }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='SUPPORT']")]
        public IWebElement Header_SupportPage { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/feedback_text_view_title")]
        public IWebElement Title { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/feedback_edit_text")]
        public IWebElement TextField { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/feedback_send_button")]
        public IWebElement Button_Send { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/textinput_error")]
        public IWebElement Error_Blank { get; set; }
    }
}
