package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends PageBase {

    //------Forgot Password 1st page--------------------------------------------------------------------------
    //Forgot Password page title
    public WebElement Title_ForgotPassword() { return findElement("com.bungii.customer:id/toolbar_main_title", LocatorType.Id); }

    //Forgot Password - Phone Number field
    public WebElement TextField_ForgotPass_PhoneNumber() { return findElement("com.bungii.customer:id/field_phone", LocatorType.Id); }

    //Forgot Password - Phone Number field
    public WebElement Button_ForgotPass_Send() { return findElement("com.bungii.customer:id/button2", LocatorType.Id); }

    //------Forgot Password 2nd page--------------------------------------------------------------------------
    //SMS Code field
    public WebElement TextField_SMSCode() { return findElement("com.bungii.customer:id/field_sms_code", LocatorType.Id); }

    //Password field
    public WebElement TextField_NewPassword() { return findElement("com.bungii.customer:id/field_password", LocatorType.Id); }

    //Resend Link
    public WebElement Link_Resend() { return findElement("//android.widget.RelativeLayout/android.widget.TextView[@id='	com.bungii.customer:id/textview_dint_receive_code']/following-sibling::android.widget.Button", LocatorType.XPath); }

    //Password Error
    public WebElement Err_InvalidPassword() { return findElement("com.bungii.customer:id/textinput_error", LocatorType.Id); }

    //Continue Button
    public WebElement Button_Continue() { return findElement("com.bungii.customer:id/resetPasswordContinue", LocatorType.Id); }



}
