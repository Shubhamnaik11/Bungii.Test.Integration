using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Test.Regression.Android.Integration.Pages
{
   public class LoginPage
    {
       public LoginPage(IWebDriver driver)
       {
           PageFactory.InitElements(driver, this);
       }
        
       //Header
       [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@text='LOGIN']")]
        public IWebElement Header_LoginPage { get; set; }

       // Phone Number field
       [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_phone")]
       public IWebElement TextField_PhoneNumber { get; set; }

       //Password field
       [FindsBy(How = How.Id, Using = "com.bungii.customer:id/field_password")]
       public IWebElement TextField_Password { get; set; }
        
       //Log In button
       [FindsBy(How = How.Id, Using = "com.bungii.customer:id/loginGlobalButton")]
       public IWebElement Button_Login { get; set; }

       // Forgot Password link
       [FindsBy(How = How.Id, Using = "com.bungii.customer:id/login_button_forgot_password")]
       public IWebElement Link_ForgotPassword { get; set; }

       // Sign up Link
       [FindsBy(How = How.Id, Using = "com.bungii.customer:id/menu_signup")]
       public IWebElement Link_Signup { get; set; }

       //Snackbar - Invalid Password
       [FindsBy(How = How.Id, Using = "com.bungii.customer:id/snackbar_text")]
       public IWebElement Snackbar_IncorrectPassword { get; set; }

       //Error message - Phone Number
       [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/textinput_error' and @instance='2']")]
       public IWebElement Error_EnterPhone { get; set; }

       //Error message - Password
       [FindsBy(How = How.XPath, Using = "//android.widget.TextView[@resource-id='com.bungii.customer:id/textinput_error' and @instance='3']")]
       public IWebElement Error_EnterPassword { get; set; }

        //Snackbar - Invalid Password
        [FindsBy(How = How.Id, Using = "com.bungii.customer:id/snackbar_text")]
        public IWebElement Snackbar { get; set; }
    }
}
