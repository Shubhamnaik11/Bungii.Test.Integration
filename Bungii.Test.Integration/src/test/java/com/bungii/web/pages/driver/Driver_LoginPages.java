package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_LoginPages extends PageBase {

    //Log In tab
    public WebElement Tab_LogIn() { return findElement("tablogin", LocatorType.Id); }

    //Driver Login - Header
    public WebElement Header_DriverLogin() { return findElement("//div[@id='login']/h2", LocatorType.XPath); }

    //Driver Login - Blank fields error
    public WebElement Err_DriverLogin_Blank() { return findElement("loginerrorsummary", LocatorType.Id); }

    //Driver Login - Field validation error
    public WebElement Err_DriverLogin_FieldValidation() { return findElement("//p[@id='loginValidationMessage']/span", LocatorType.XPath); }

    //Driver Login - Phone Field
    public WebElement TextBox_DriverLogin_Phone() { return findElement("//div[@id='login']/form/div/input[@id='phone']", LocatorType.XPath); }

    //Driver Login - Phone Field - Error
    public WebElement Err_DriverLogin_Phone() { return findElement("phone-error", LocatorType.Id); }

    //Driver Login - Password Field
    public WebElement TextBox_DriverLogin_Password() { return findElement("//div[@id='login']/form/div/input[@id='password']", LocatorType.XPath); }

    //Driver Login - Login Button
    public WebElement Button_DriverLogin() { return findElement("//form[@id='Login']/button[contains(text(), 'LOG')]", LocatorType.Id); }

    //Password reset success message
    public WebElement Text_PasswordResetSuccess() { return findElement("password-reset-success-summary", LocatorType.Id); }
}
