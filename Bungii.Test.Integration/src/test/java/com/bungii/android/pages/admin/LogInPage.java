package com.bungii.android.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;


public class LogInPage extends PageBase {

    public WebElement TextBox_Phone() {
        return findElement("PhoneNo", LocatorType.Name);
    }

    public WebElement TextBox_Phoned(boolean ... ignoreException) {
        return findElement("Phone7No", LocatorType.Name,ignoreException[0]);
    }

    public WebElement TextBox_Pass() {
        return findElement("Password", LocatorType.Name);
    }

    public WebElement Button_LogIn() {
        return findElement("//button[text()='LOG IN']", LocatorType.XPath);
    }

}
