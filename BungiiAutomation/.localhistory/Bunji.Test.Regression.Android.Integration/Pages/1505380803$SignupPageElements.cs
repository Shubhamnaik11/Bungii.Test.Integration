using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Bunji.Test.Regression.Android.Integration.Pages
{
    class SignupPageElements
    {

        public SignupPageElements(IWebDriver driver)
       {
           PageFactory.InitElements(driver, this);
       }
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/title_login")]
        public IWebElement LoginLink { get; set; }

        //Signup fields:

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_first_nam")]
        public IWebElement Textbox_FirstName { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_last_name")]
        public IWebElement Textbox_LastName { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_email")]
        public IWebElement Textbox_Email { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_phone")]
        public IWebElement Textbox_Phonenumber { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_password")]
        public IWebElement Textbox_Password { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_prmo_code")]
        public IWebElement Textbox_Referral { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_chevron_right")]
        public IWebElement Select_ReferralSource { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='Other']")]
        public IWebElement Option_ReferralSource { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button1")]
        public IWebElement Select_ReferralSourceDone { get; set; }


        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signupGlobalButton")]
        public IWebElement Button_Signup { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.Button[@text='No, Continue']")]
        public IWebElement Button_NoReferralConfirm { get; set; }


        //Verification page elements

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_sms_code")]
        public IWebElement Textbox_SMSCode { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/smsVerifyContinue")]
        public IWebElement Button_VerifyContinue{ get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.Button[@text='RESEND']")]
        public IWebElement Link_Resend { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='VERIFICATION']")]
        public IWebElement Title_Verification { get; set; }

    }
}
