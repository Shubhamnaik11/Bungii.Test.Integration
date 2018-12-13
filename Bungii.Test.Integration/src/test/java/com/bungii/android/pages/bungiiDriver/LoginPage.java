package com.bungii.android.pages.bungiiDriver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase {

    // Phone Number field
    public WebElement TextField_PhoneNumber() { return findElement("com.bungii.driver:id/field_phone", LocatorType.Id); }

    //Password field
    public WebElement TextField_Password() { return findElement("com.bungii.driver:id/field_password", LocatorType.Id); }

    //Log In button
    public WebElement Button_Login() { return findElement("com.bungii.driver:id/loginGlobalButton", LocatorType.Id); }
}
