using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class FAQPage
    {
        public FAQPage(IWebDriver driver)
        {
           PageFactory.InitElements(driver, this);
        }
        //------Title-------------------------------------------------------------------------------
        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='FAQ']")]
        public IWebElement Header_FAQPage { get; set; }

        //------Page Elements------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/promo_code_label")]
        public IWebElement FAQ_BungiiLogo { get; set; }

        [FindsBy(How = How.Id, Using = "")]
        public IWebElement FAQ_Image { get; set; }

        [FindsBy(How = How.Id, Using = "")]
        public IWebElement FAQ_TitleImage { get; set; }

        [FindsBy(How = How.Id, Using = "")]
        public IWebElement FAQ_AppFAQTitle { get; set; }

        [FindsBy(How = How.Id, Using = "yui_3_17_2_1_1506508160885_326")]
        public IWebElement FAQ_FirstQuestion { get; set; }

        [FindsBy(How = How.Id, Using = "We are more than happy to help.Please text/call customer support at(913) 353-6683.")]
        public IWebElement FAQ_FirstAnswer { get; set; }

        [FindsBy(How = How.Id, Using = "")]
        public IWebElement FAQ_LastQuestion { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][1]")]
        public IWebElement FAQ_TwitterLogo { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][2]")]
        public IWebElement FAQ_InstagramLogo { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][3]")]
        public IWebElement FAQ_FBLogo { get; set; }
    }
}
