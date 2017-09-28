using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;


namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class MenuPage
    {
        public MenuPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }
        //---------Menu Button---------------------------------------------------------------------------
        [FindsBy(How = How.ClassName, Using = "android.widget.ImageButton")]
        public IWebElement Button_Menu { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/header_textview_email")]
        public IWebElement CustMenu_Email { get; set; }

        //---------Customer Menu-------------------------------------------------------------------------
        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='HOME']")]
        public IWebElement Menu_Home { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='FAQ']")]
        public IWebElement Menu_FAQ { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='ACCOUNT']")]
        public IWebElement Menu_Account { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='PAYMENT']")]
        public IWebElement Menu_Payment { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='SUPPORT']")]
        public IWebElement Menu_Support { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='SAVE MONEY']")]
        public IWebElement Menu_SaveMoney { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='LOGOUT']")]
        public IWebElement Menu_Logout { get; set; }

        //---------Page Titles---------------------------------------------------------------------
        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='BUNGII']")]
        public IWebElement PageTitle_Home { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='FAQ']")]
        public IWebElement PageTitle_FAQ { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='ACCOUNT']")]
        public IWebElement PageTitle_Account { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='PAYMENT']")]
        public IWebElement PageTitle_Payment { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='SUPPORT']")]
        public IWebElement PageTitle_Support { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='SAVE MONEY']")]
        public IWebElement PageTitle_SaveMoney { get; set; }

        //---------Page Elements---------------------------------------------------------------------
        //----------------FAQ-----------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/promo_code_label")]
        public IWebElement FAQ_BungiiLogo { get; set; }

        [FindsBy(How = How.Id, Using = "")]
        public IWebElement FAQ_Image { get; set; }

        [FindsBy(How = How.Id, Using = "")]
        public IWebElement FAQ_TitleImage { get; set; }

        [FindsBy(How = How.Id, Using = "")]
        public IWebElement FAQ_AppFAQTitle { get; set; }

        [FindsBy(How = How.Id, Using = "")]
        public IWebElement FAQ_LastQuestion { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][1]")]
        public IWebElement FAQ_TwitterLogo { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][2]")]
        public IWebElement FAQ_InstagramLogo { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][3]")]
        public IWebElement FAQ_FBLogo { get; set; }
        //----------------Save Money----------------------------------------------
        [FindsBy(How = How.Id, Using = "")]
        public IWebElement SaveMoney_PromoCode { get; set; }
    }
}
