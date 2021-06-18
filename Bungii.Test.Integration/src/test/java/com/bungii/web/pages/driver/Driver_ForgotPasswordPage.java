package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_ForgotPasswordPage extends PageBase {

    //---------------------------Forgot Password--------------------------------------------------------------------
    //driver Login - Forgot Password Link
    public WebElement Link_ForgotPassword () { return findElement("//form[@id='Login']/div/a[text()='Forgot Password?']", LocatorType.XPath); }

    //driver Forgot Password - Header
    public WebElement Header_ForgotPassword () { return findElement("//div[@id='forgot-pwd']/h2", LocatorType.XPath); }

    //driver Forgot Password - Blank field validation error
    public WebElement Err_ForgotPass_BlankField () { return findElement("forgotpassworderrorsummary", LocatorType.Id); }

    //driver Forgot Password - Phone Number field
    public WebElement Textfield_ForgotPass_Phone() { return findElement("//form[@id='ForgotPassword']/div/input[@id='PhoneNo']", LocatorType.XPath); }

    //driver Forgot Password - Phone Number field Error
    public WebElement Err_ForgotPass_Phone() { return findElement("PhoneNo-error", LocatorType.Id); }

    //driver Forgot Password - Send Verification Code button
    public WebElement Button_SendVerifCode() { return findElement("//form[@id='ForgotPassword']/div/button[contains(text(),'Code')]", LocatorType.XPath); }

    //driver Forgot Password - Back to Login link
    public WebElement Link_BackToLogin() { return findElement("//form[@id='ForgotPassword']/div/a[text()='Back to Login']", LocatorType.XPath); }
}
