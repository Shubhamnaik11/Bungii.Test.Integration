using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages.MenuPages
{
    class InvitePage
    {
        public InvitePage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        //------Invite Page Elements-----------------------------------------------------------------
        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='INVITE']")]
        public IWebElement Header_Invite { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/invite_referral_imageview")]
        public IWebElement Invite_Logo { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/invite_referral_title")]
        public IWebElement Invite_Title { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/invite_referral_description")]
        public IWebElement Invite_Text { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/invite_referral_code_textview")]
        public IWebElement Invite_Code { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/invite_referral_share_button")]
        public IWebElement Button_Share { get; set; }

        //------Share to---------------------------------------------------------------------------
        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='Share on Facebook']")]
        public IWebElement Share_Facebook { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='Share on Twitter']")]
        public IWebElement Share_Twitter { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='Share by Email']")]
        public IWebElement Share_Email { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='Share by Text Message']")]
        public IWebElement Share_TextMessage { get; set; }

        //------Share - Facebook App---------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.facebook.katana:id/title_view")]
        public IWebElement FBApp_Title { get; set; }

        [FindsBy(How = How.Id, Using = "com.facebook.katana:id/status_text")]
        public IWebElement FBApp_StatusText { get; set; }

        [FindsBy(How = How.Id, Using = "com.facebook.katana:id/link_attachment_context_text")]
        public IWebElement FBApp_PreviewText { get; set; }

        [FindsBy(How = How.Id, Using = "com.facebook.katana:id/button_share")]
        public IWebElement FBApp_PostLink { get; set; }

        //------Share - Samsung Msg App-------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.android.mms:id/edit_text_bottom")]
        public IWebElement Samsung_TextMsg_TextField { get; set; }

        //------Share - Gmail App-------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.google.android.gm:id/subject")]
        public IWebElement Gmail_Referral_Subject { get; set; }

        [FindsBy(How = How.Id, Using = "com.google.android.gm:id/body")]
        public IWebElement Gmail_Referral_Body { get; set; }

        //------Share - Twitter App-----------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "status")]
        public IWebElement Twitter_Referral_Body { get; set; }
    }
}
