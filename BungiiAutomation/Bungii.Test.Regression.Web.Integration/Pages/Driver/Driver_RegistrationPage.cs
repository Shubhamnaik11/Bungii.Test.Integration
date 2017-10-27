using OpenQA.Selenium;
using OpenQA.Selenium.Support.PageObjects;

namespace Bungii.Android.Regression.Test.Integration.Pages.Driver
{
    public class Driver_RegistrationPage
    {
        public Driver_RegistrationPage(IWebDriver webdriver)
        {
            PageFactory.InitElements(webdriver, this);
        }        
        //------------------------------Log In Tab-------------------------------------------------------------------------
        //Log In tab
        [FindsBy(How = How.Id, Using = "tablogin")]
        public IWebElement Tab_LogIn { get; set; }

        //Driver Login - Phone Field
        [FindsBy(How = How.XPath, Using = "//div[@id='login']/form/div/input[@id='phone']")]
        public IWebElement TextBox_Phone { get; set; }

        //Driver Login - Password Field
        [FindsBy(How = How.XPath, Using = "//div[@id='login']/form/div/input[@id='password']")]
        public IWebElement TextBox_Password { get; set; }

        //Driver Login - Login Button
        [FindsBy(How = How.XPath, Using = "//form[@id='Login']/button[contains(text(), 'LOG')]")]
        public IWebElement Button_Login { get; set; }

        //------------------------------Driver Registration---------------------------------------------------------------
        //Driver Registration - Header
        [FindsBy(How = How.XPath, Using = "//div[@id='signup']/h2")]
        public IWebElement Header_DriverRegistration { get; set; }

        //Driver Registration - Click Here link
        [FindsBy(How = How.Id, Using = "signup")]
        public IWebElement Link_ClickHere { get; set; }

        //Driver Registration - First Name 
        [FindsBy(How = How.Id, Using = "first-name")]
        public IWebElement TextBox_FirstName { get; set; }

        //Driver Registration - Last Name
        [FindsBy(How = How.Id, Using = "LastName")]
        public IWebElement TextBox_LastName { get; set; }

        //Driver Registration - Email
        [FindsBy(How = How.Id, Using = "email")]
        public IWebElement TextBox_Email { get; set; }

        //Driver Registration - Phone number
        [FindsBy(How = How.Id, Using = "phone")]
        public IWebElement TextBox_PhoneNumber { get; set; }

        //Driver Registration - Create password
        [FindsBy(How = How.Id, Using = "password")]
        public IWebElement TextBox_CreatePassword { get; set; }

        //Driver Registration - Confirm password
        [FindsBy(How = How.Id, Using = "confirmpassword")]
        public IWebElement TextBox_ConfirmPassword { get; set; }

        //Driver Registration - Sign Up Button
        [FindsBy(How = How.Id, Using = "btnRegister")]
        public IWebElement Button_SignUp { get; set; }

        //------------------ERRORS----------------------------------
        //ERROR - First Name
        [FindsBy(How = How.Id, Using = "first-name-error")]
        public IWebElement ERR_FirstName { get; set; }

        //ERROR - Last Name
        [FindsBy(How = How.Id, Using = "LastName-error")]
        public IWebElement ERR_LastName { get; set; }

        //ERROR - Email
        [FindsBy(How = How.Id, Using = "email-error")]
        public IWebElement ERR_Email { get; set; }

        //ERROR - Phone Number
        [FindsBy(How = How.Id, Using = "phone-error")]
        public IWebElement ERR_Phone { get; set; }

        //ERROR - Create password
        [FindsBy(How = How.Id, Using = "password-error")]
        public IWebElement ERR_CreatePassword { get; set; }

        //ERROR - Confirm password
        [FindsBy(How = How.Id, Using = "confirmpassword-error")]
        public IWebElement ERR_ConfirmPassword { get; set; }

        //ERROR - Blank fields
        [FindsBy(How = How.Id, Using = "summary")]
        public IWebElement ERR_BlankFields { get; set; }

        //------------------------------SMS Verification Page---------------------------------------------------------------

        //Driver Registration - Success message on verify phone page
        [FindsBy(How = How.XPath, Using = "//div/h3[contains(text(), 'Verify your phone')]/following-sibling::p[1]")]
        public IWebElement Text_Verification { get; set; }

        //Driver Registration - Verification code field
        [FindsBy(How = How.Id, Using = "SMSVerificationCode")]
        public IWebElement TextBox_VerificationCode { get; set; }

        //Driver Registration - Error Verification Code - blank
        [FindsBy(How = How.Id, Using = "validation-summary")]
        public IWebElement ERR_VerifiCode_Blank { get; set; }

        //Driver Registration - Error Verification Code - invalid
        [FindsBy(How = How.XPath, Using = "//p[@id='validation-summary']/following-sibling::p/span")]
        public IWebElement ERR_VerifiCode_Invlid { get; set; }

        //Driver Registration - Resend Code Button
        [FindsBy(How = How.Id, Using = "btnResendSMS")]
        public IWebElement Button_ResendCode { get; set; }

        //Driver Registration - Submit button
        [FindsBy(How = How.Id, Using = "btnSMSVerification")]
        public IWebElement Button_SubmitVerification { get; set; }

        //------------------------------Driver Verification---------------------------------------------------------------

        //Driver Verification Successful Header
        [FindsBy(How = How.XPath, Using = "//div[@class = 'row']/div/img/following-sibling::h3")]
        public IWebElement Header_VerificationSuccess { get; set; }

        //Driver Verification Driver Name
        [FindsBy(How = How.XPath, Using = "//p/span[contains (text(), 'Welcome')]/following-sibling::strong")]
        public IWebElement Text_DriverName { get; set; }

        //Driver Verification Successful - Continue Button
        [FindsBy(How = How.XPath, Using = "//div[@class = 'row']/div/h3/following-sibling::a[contains (text(), 'Continue')]")]
        public IWebElement Button_ContinueReg { get; set; }

        //Logout Link
        [FindsBy(How = How.XPath, Using = "//div[@class='pull-left info']/p/a[contains(text()='log out')]")]
        public IWebElement Link_Logout { get; set; }
    }
}
