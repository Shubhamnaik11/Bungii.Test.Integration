package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_LoginPage extends PageBase {

    //Log In tab
    public WebElement Tab_LogIn() { return findElement("uncontrolled-tab-example-tab-login", LocatorType.Id); }

    //driver Login - Header
    public WebElement Header_DriverLogin() { return findElement("//h2[contains(text(),'Driver Login')]", LocatorType.XPath); }

    //driver Login - Blank fields error
    public WebElement Err_DriverLogin_Blank() { return findElement("//p[contains(text(),'Oops! It looks like you missed something. Please f')]", LocatorType.XPath); }

    //driver Login - Field validation error
    public WebElement Err_DriverLogin_FieldValidation() { return findElement("//p[contains(text(),'Incorrect cell phone number or password')]", LocatorType.XPath); }

    //driver Login - Phone Field
    public WebElement TextBox_DriverLogin_Phone() { return findElement("(//div/label[text()='Phone number'])[2]/following-sibling::input", LocatorType.XPath); }

    //driver Login - Phone Field - Error
    public WebElement Err_DriverLogin_Phone() { return findElement("//div[@id='root']/div/div/div/div/aside/div/div[2]/form/div[1]/div[1]", LocatorType.XPath); }

    //driver Login - Password Field
    public WebElement TextBox_DriverLogin_Password() { return findElement("//input[@class='form-control password form-control is-valid']", LocatorType.XPath); }

    //driver Login - Login Button
    public WebElement Button_DriverLogin() { return findElement("//button[@class='btn btn-primary']", LocatorType.XPath); }

    //Password reset success message
    public WebElement Text_PasswordResetSuccess() { return findElement("//p[text()='Your password has been reset successfully.']", LocatorType.XPath); }

    //Driver login show password
    public WebElement Link_Login_OpenEye() { return findElement("//form/div[2]/div/span[@class =\"close-eye\"]", LocatorType.XPath); }

    public WebElement Link_Terms() { return findElement("//a[contains(text(),'Terms')]", LocatorType.XPath); }

    public WebElement Link_PrivacyPolicy() { return findElement("//a[contains(text(),'Privacy Policy')]", LocatorType.XPath); }

}
