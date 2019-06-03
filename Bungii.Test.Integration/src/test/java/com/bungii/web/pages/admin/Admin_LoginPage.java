package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_LoginPage  extends PageBase {

    //Admin Login - Header
    public WebElement Header_AdminLogin() { return findElement("//div[@id='login']/h2", LocatorType.XPath); }

    //Admin Login - Phone Number
    public WebElement TextBox_Phone() { return findElement("phone", LocatorType.Id); }

    //Admin Login - Password
    public WebElement TextBox_Password() { return findElement("password", LocatorType.Id); }

    //Admin Login - Login Button
    public WebElement Button_AdminLogin() { return findElement("//form[@id='Login']/button[contains(text(),'LOG IN')]", LocatorType.XPath); }

}
