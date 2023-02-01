package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_LoginPage  extends PageBase {

    //Admin Login - Header
    public WebElement Header_AdminLogin() { return findElement("//div[@id='login']/h2", LocatorType.XPath); }

    //Admin Login - Phone Number
    public WebElement TextBox_Phone() { return findElement("phoneno", LocatorType.Name); }

    //Admin Login - Password
    public WebElement TextBox_Password() { return findElement("password", LocatorType.Name); }

    //Admin Login - Login Button
    public WebElement Button_AdminLogin() { return findElement("login", LocatorType.Id); }

    //Admin ExtraEarnings - text
    public WebElement Label_ExtraEarnings() { return findElement("//strong[text()='Earn Extra Cash.']/parent::p", LocatorType.XPath); }

    public WebElement Button_ForgotPassword() { return findElement("//a[text()=\"forgot password?\"]", LocatorType.XPath); }

    public WebElement Header_ForgotPassword() { return findElement("//div/form/h2", LocatorType.XPath); }

    public WebElement Textbox_CellPhoneNumber() { return findElement("//label[@id=\"cellPhoneNumber\"]/following::input", LocatorType.XPath); }

    public WebElement Button_SendVerificationCode() { return findElement("//button[text()=\"Send Verification Code\"]", LocatorType.XPath); }


}
