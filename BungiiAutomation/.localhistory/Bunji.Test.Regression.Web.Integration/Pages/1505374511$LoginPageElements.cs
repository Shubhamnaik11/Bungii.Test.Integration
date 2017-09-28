using OpenQA.Selenium;
using OpenQA.Selenium.Appium;
using OpenQA.Selenium.Appium.Android;
using OpenQA.Selenium.Support.PageObjects;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BungiiProject1.PageElements
{
   public class LoginPageElements
    {

        //Login Page Elements
      // public static AppiumDriver<AndroidElement> driver= Core.driver;
       public LoginPageElements(IWebDriver driver)
       {
           PageFactory.InitElements(driver, this);
       }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_phone")]
        public IWebElement PhoneNumber { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_password")]
        public IWebElement PasswordField { get; set; }

       //----------------Buttons--------------//

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/loginGlobalButton")]
        public IWebElement Button_Login { get; set; }

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/login_button_forgot_password")]
        public IWebElement Link_ForgotPassword { get; set; }

       

        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/menu_signup")]
        public IWebElement SignupLink { get; set; }
    }
}
