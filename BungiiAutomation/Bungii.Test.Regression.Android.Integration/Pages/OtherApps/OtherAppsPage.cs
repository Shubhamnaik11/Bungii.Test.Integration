using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.OtherApps
{
    class OtherAppsPage
    {
        public OtherAppsPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        //------SMS---------------------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.android.mms:id/recipients_editor_to")]
        public IWebElement SMS_Samsung_RecipientNo { get; set; }

        [FindsBy(How = How.Id, Using = "com.android.mms:id/recipients_editor")]
        public IWebElement SMS_Moto_RecipientNo { get; set; }

        //------Call--------------------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.android.contacts:id/digits")]
        public IWebElement Call_Samsung_Number { get; set; }

        [FindsBy(How = How.Id, Using = "com.android.dialer:id/digits")]
        public IWebElement Call_Moto_Number { get; set; }
    }
}