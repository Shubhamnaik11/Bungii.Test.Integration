using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class SignupPage
    {
        public SignupPage(IWebDriver driver)
        {
           PageFactory.InitElements(driver, this);
        }

        // Login link
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/title_login")]
        public IWebElement Link_Login { get; set; }

        //-------------Signup fields---------------------------------------------------------------------------
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_first_nam")]
        public IWebElement TextField_FirstName { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_last_name")]
        public IWebElement TextField_LastName { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_email")]
        public IWebElement TextField_Email { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_phone")]
        public IWebElement TextField_Phonenumber { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_password")]
        public IWebElement TextField_Password { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_field_prmo_code")]
        public IWebElement TextField_Referral { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_chevron_right")]
        public IWebElement Select_ReferralSource { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='Other']")]
        public IWebElement Option_ReferralSource { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button1")]
        public IWebElement Link_ReferralSourceDone { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_textview_hear_about_us_choice")]
        public IWebElement Text_ReferralSource { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signupGlobalButton")]
        public IWebElement Button_Signup { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/buttonPanel")]
        public IWebElement Popup_ReferralCode { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button2")]
        public IWebElement Button_NoReferralConfirm { get; set; }

        [FindsBy(How = How.Id, Using = "android:id/button1")]
        public IWebElement Button_NoReferralYes { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/snackbar_text")]
        public IWebElement Snackbar_Error { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/signup_textview_hear_about_us_choice")]
        public IWebElement Text_ReferralSourceAdded { get; set; }

        //--------------Sign up fields error messages-----------------------------------------------------------
        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/textinput_error' and @instance='2']")]
        public IWebElement Cust_Signup_Error_Email { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/textinput_error' and @instance='3']")]
        public IWebElement Cust_Signup_Error_Phone { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/textinput_error' and @instance='4']")]
        public IWebElement Cust_Signup_Error_Password { get; set; }

        //--------------Verification page elements---------------------------------------------------------------

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_sms_code")]
        public IWebElement Textfield_SMSCode { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/smsVerifyContinue")]
        public IWebElement Button_VerifyContinue { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.Button[@text='RESEND']")]
        public IWebElement Link_Resend { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='VERIFICATION']")]
        public IWebElement Title_Verification { get; set; }
    }
}
