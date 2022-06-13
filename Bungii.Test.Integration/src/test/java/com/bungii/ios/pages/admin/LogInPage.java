package com.bungii.ios.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;


public class LogInPage extends PageBase {

    public WebElement TextBox_Phone() {
        return findElement("//label[text()='Cell Phone number']/following-sibling::input[@name='PhoneNo']", LocatorType.XPath);
    }

    public WebElement TextBox_Phoned(boolean ... ignoreException) {
        return findElement("Phone7No", LocatorType.Name,ignoreException[0]);
    }

    public WebElement TextBox_Pass() {
        return findElement("//label[text()='Password']/following-sibling::input[@name='Password']", LocatorType.XPath);
    }

    public WebElement Button_LogIn() {
        return findElement("//button[text()='LOG IN']", LocatorType.XPath);
    }

}
