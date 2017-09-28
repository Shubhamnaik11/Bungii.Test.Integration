using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Bungii.Test.Regression.Android.Integration.Pages
{
    class MenuPage
    {
        public MenuPage(IWebDriver driver)
        {
            PageFactory.InitElements(driver, this);
        }
        [FindsBy(How = How.ClassName, Using = "android.widget.ImageButton")]
        public IWebElement Homepage_Menu { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_password")]
        public IWebElement PasswordField { get; set; }

        //----------------Buttons--------------//

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/loginGlobalButton")]
        public IWebElement Button_Login { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/login_button_forgot_password")]
        public IWebElement Link_ForgotPassword { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/header_textview_username")]
        public IWebElement TextBox_UserName { get; set; }

        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='LOGOUT']")]
        public IWebElement Link_Logout { get; set; }
        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='ACCOUNT']")]
        public IWebElement Link_Account { get; set; }
        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='PAYMENT']")]
        public IWebElement Link_Payment{ get; set; }
        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='SUPPORT']")]
        public IWebElement Link_Support { get; set; }
        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='HOME']")]
        public IWebElement Link_Home { get; set; }
      
        [FindsBy(How = How.XPath, Using = "//android.widget.CheckedTextView[@text='SAVE MONEY']")]
        public IWebElement Link_SaveMoney { get; set; }
    }
}
