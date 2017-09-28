using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Bunji.Test.Regression.Web.Integration.Pages
{
    class MenuElements
    {

        [FindsBy(How = How.ClassName, Using = "android.widget.ImageButton")]
        public IWebElement Homepage_Menu { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_password")]
        public IWebElement PasswordField { get; set; }

        //----------------Buttons--------------//

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/loginGlobalButton")]
        public IWebElement Button_Login { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/login_button_forgot_password")]
        public IWebElement Link_ForgotPassword { get; set; }

    }
}
