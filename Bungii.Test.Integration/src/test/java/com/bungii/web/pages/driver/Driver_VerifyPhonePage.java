package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_VerifyPhonePage extends PageBase {

    //---------------------------Forgot Password - Verify Your Phone-------------------------------------------------
    //Driver - Verify your phone - Header
    public WebElement Header_VerifyPhone () { return findElement("//div[@id='verification']/h2", LocatorType.XPath); }

    //Driver - Verify your phone - text
    public WebElement Text_Verify_PhoneNo () { return findElement("//div[@id='verification']/p", LocatorType.XPath); }

    //Verify Your Phone - Blank field validation error
    public WebElement Err_VerifyPhone_BlankField () { return findElement("verificationerrorsummary", LocatorType.Id); }

    //Verify Your Phone - Resend Code
    public WebElement Button_ResendCode () { return findElement("btnResendSMS", LocatorType.Id); }

    //Verify Your Phone - Code
    public WebElement Textfield_Code () { return findElement("SMSVerificationCode", LocatorType.Id); }

    //Verify Your Phone - Password
    public WebElement Textfield_Password () { return findElement("new-password", LocatorType.Id); }

    //Verify Your Phone - Confirm Password
    public WebElement Textfield_ConfirmPassword () { return findElement("confirm-password", LocatorType.Id); }

    //Verify Your Phone - Password - Passwords dont match -error
    public WebElement Button_ResetPassword () { return findElement("//form[@id='ResetPassword']/button[contains(text(),'Reset')]", LocatorType.XPath); }

    //Verify Your Phone - Blank field validation error
    public WebElement Err_VerifyPhone_BlankPasswords () { return findElement("verificationerrorsummary", LocatorType.Id); }

    //Verify Your Phone - Incorrect Verification Code
    public WebElement Err_VerifyPhone_Code_Incorrect () { return findElement("//form[@id='ResetPassword']/p/span", LocatorType.XPath); }

    //Verify Your Phone - Password - error
    public WebElement Err_VerifyPhone_Password_Invalid () { return findElement("new-password-error", LocatorType.Id); }

    //Verify Your Phone - Password - Passwords dont match -error
    public WebElement Err_VerifyPhone_ConfirmPassword () { return findElement("confirm-password-error", LocatorType.Id); }
}
