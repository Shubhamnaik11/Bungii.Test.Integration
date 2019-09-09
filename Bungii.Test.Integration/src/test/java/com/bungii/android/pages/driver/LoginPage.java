package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class LoginPage extends PageBase {
    public WebElement Text_LoginBar(boolean ...ignoreException){return findElement("//android.view.View[@resource-id='com.bungii.driver:id/toolbarLogin']/android.widget.TextView", LocatorType.XPath,ignoreException);}
    // Phone Number field
    public WebElement TextField_PhoneNumber(boolean ...ignoreException) { return findElement("com.bungii.driver:id/field_phone", LocatorType.Id,ignoreException); }

    //Password field
    public WebElement TextField_Password() { return findElement("com.bungii.driver:id/field_password", LocatorType.Id); }

    //Log In button
    public WebElement Button_Login() { return findElement("com.bungii.driver:id/loginGlobalButton", LocatorType.Id); }

    //Password field
    public WebElement Text_LoginError() { return findElement("com.bungii.driver:id/textinput_error", LocatorType.Id); }
    public WebElement Text_LoginError2() { return findElements("com.bungii.driver:id/textinput_error", LocatorType.Id).get(1); }
    public WebElement Button_ForgotPassword(boolean ...ignoreException) { return findElement("com.bungii.driver:id/login_button_forgot_password", LocatorType.Id,ignoreException); }



}
