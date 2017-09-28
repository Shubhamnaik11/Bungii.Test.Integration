using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class ForgotPasswordPage
    {
        public ForgotPasswordPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }

        //------Forgot Password 1st page--------------------------------------------------------------------------
        //Forgot Password page title
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/toolbar_main_title")]
        public IWebElement Title_ForgotPassword { get; set; }

        //Forgot Password - Phone Number field
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_phone")]
        public IWebElement TextField_ForgotPass_PhoneNumber { get; set; }

        //Forgot Password - Phone Number field
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/button2")]
        public IWebElement Button_ForgotPass_Send { get; set; }

        //------Forgot Password 2nd page--------------------------------------------------------------------------
        //SMS Code field
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_sms_code")]
        public IWebElement TextField_SMSCode { get; set; }

        //Password field
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_password")]
        public IWebElement TextField_NewPassword { get; set; }

        //Resend Link
        [FindsBy(How = How.XPath, Using = "//android.widget.RelativeLayout/android.widget.TextView[@id='	com.bungii.customer:id/textview_dint_receive_code']/following-sibling::android.widget.Button")]
        public IWebElement Link_Resend { get; set; }

        //Password Error
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/textinput_error")]
        public IWebElement Err_InvalidPassword { get; set; }

        //Continue Button
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/resetPasswordContinue")]
        public IWebElement Button_Continue { get; set; }
    }
}
