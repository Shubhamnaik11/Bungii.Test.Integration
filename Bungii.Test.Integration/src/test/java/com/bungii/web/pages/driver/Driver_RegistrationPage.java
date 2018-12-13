package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_RegistrationPage extends PageBase {

    //------------------------------Driver Registration---------------------------------------------------------------
    //Driver Registration - Header
    public WebElement Header_DriverRegistration () { return findElement("//div[@id='signup']/h2", LocatorType.XPath); }

    //Driver Registration - Click Here link
    public WebElement Link_ClickHere () { return findElement("signup", LocatorType.Id); }

    //Driver Registration - First Name
    public WebElement TextBox_FirstName () { return findElement("first-name", LocatorType.Id); }

    //Driver Registration - Last Name
    public WebElement TextBox_LastName () { return findElement("LastName", LocatorType.Id); }

    //Driver Registration - Email
    public WebElement TextBox_Email () { return findElement("email", LocatorType.Id); }

    //Driver Registration - Phone number
    public WebElement TextBox_PhoneNumber () { return findElement("phone", LocatorType.Id); }

    //Driver Registration - Create password
    public WebElement TextBox_CreatePassword () { return findElement("password", LocatorType.Id); }

    //Driver Registration - Confirm password
    public WebElement TextBox_ConfirmPassword () { return findElement("confirmpassword", LocatorType.Id); }

    //Driver Registration - Sign Up Button
    public WebElement Button_SignUp () { return findElement("btnRegister", LocatorType.Id); }

    //------------------ERRORS----------------------------------
    //ERROR - First Name
    public WebElement ERR_FirstName () { return findElement("first-name-error", LocatorType.Id); }

    //ERROR - Last Name
    public WebElement ERR_LastName () { return findElement("LastName-error", LocatorType.Id); }

    //ERROR - Email
    public WebElement ERR_Email () { return findElement("email-error", LocatorType.Id); }

    //ERROR - Phone Number
    public WebElement ERR_Phone () { return findElement("phone-error", LocatorType.Id); }

    //ERROR - Create password
    public WebElement ERR_CreatePassword () { return findElement("password-error", LocatorType.Id); }

    //ERROR - Confirm password
    public WebElement ERR_ConfirmPassword () { return findElement("confirmpassword-error", LocatorType.Id); }

    //ERROR - Blank fields
    public WebElement ERR_BlankFields () { return findElement("summary", LocatorType.Id); }

    //------------------------------SMS Verification Page---------------------------------------------------------------

    //Driver Registration - Success message on verify phone page
    public WebElement Text_Verification () { return findElement("//div/h3[contains(text(), 'Verify your phone')]/following-sibling::p[1]", LocatorType.XPath); }

    //Driver Registration - Verification code field
    public WebElement TextBox_VerificationCode () { return findElement("SMSVerificationCode", LocatorType.Id); }

    //Driver Registration - Error Verification Code - blank
    public WebElement ERR_VerifiCode_Blank () { return findElement("validation-summary", LocatorType.Id); }

    //Driver Registration - Error Verification Code - invalid
    public WebElement ERR_VerifiCode_Invlid () { return findElement("//p[@id='validation-summary']/following-sibling::p/span", LocatorType.XPath); }

    //Driver Registration - Resend Code Button
    public WebElement Button_ResendCode () { return findElement("btnResendSMS", LocatorType.Id); }

    //Driver Registration - Submit button
    public WebElement Button_SubmitVerification () { return findElement("btnSMSVerification", LocatorType.Id); }

    //------------------------------Driver Verification---------------------------------------------------------------

    //Driver Verification Successful Header
    public WebElement Header_VerificationSuccess () { return findElement("//div[@class = 'row']/div/img/following-sibling::h3", LocatorType.XPath); }

    //Driver Verification Driver Name
    public WebElement Text_DriverName () { return findElement("//p/span[contains (text(), 'Welcome')]/following-sibling::strong", LocatorType.XPath); }

    //Driver Verification Successful - Continue Button
    public WebElement Button_ContinueReg () { return findElement("//div[@class = 'row']/div/h3/following-sibling::a[contains (text(), 'Continue')]", LocatorType.XPath); }

    //Logout Link
    public WebElement Link_Logout () { return findElement("//div[@class='pull-left info']/p/a[contains(text()='log out')]", LocatorType.XPath); }
}
