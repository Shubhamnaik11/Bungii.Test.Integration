package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_LoginPage extends PageBase {

    //Log In tab
    public WebElement Tab_LogIn() { return findElement("tablogin", LocatorType.Id); }

    //driver Login - Header
    public WebElement Header_DriverLogin() { return findElement("//div[@id='login']/h2", LocatorType.XPath); }

    //driver Login - Blank fields error
    public WebElement Err_DriverLogin_Blank() { return findElement("loginerrorsummary", LocatorType.Id); }

    //driver Login - Field validation error
    public WebElement Err_DriverLogin_FieldValidation() { return findElement("//p[@id='loginValidationMessage']/span", LocatorType.XPath); }

    //driver Login - Phone Field
    public WebElement TextBox_DriverLogin_Phone() { return findElement("//div[@id='login']/form/div/input[@id='phone']", LocatorType.XPath); }

    //driver Login - Phone Field - Error
    public WebElement Err_DriverLogin_Phone() { return findElement("phone-error", LocatorType.Id); }

    //driver Login - Password Field
    public WebElement TextBox_DriverLogin_Password() { return findElement("//div[@id='login']/form/div/input[@id='password']", LocatorType.XPath); }

    //driver Login - Login Button
    public WebElement Button_DriverLogin() { return findElement("//form[@id='Login']/div/button[contains(text(),'LOG IN')]", LocatorType.XPath); }

    //Password reset success message
    public WebElement Text_PasswordResetSuccess() { return findElement("password-reset-success-summary", LocatorType.Id); }

    //Driver login show password
    public WebElement Link_Login_OpenEye() { return findElement("//form/div[2]/div/span[@class =\"close-eye\"]", LocatorType.XPath); }

}
