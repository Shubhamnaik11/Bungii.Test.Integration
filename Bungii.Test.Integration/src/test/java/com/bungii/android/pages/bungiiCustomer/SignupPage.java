package com.bungii.android.pages.bungiiCustomer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SignupPage extends PageBase {

    // Login link
    public WebElement Link_Login() { return findElement("com.bungii.customer:id/title_login", LocatorType.Id); }
    //-------------Signup fields---------------------------------------------------------------------------
    public WebElement TextField_FirstName() { return findElement("com.bungii.customer:id/signup_field_first_name", LocatorType.Id); }

    public WebElement TextField_LastName() { return findElement("com.bungii.customer:id/signup_field_last_name", LocatorType.Id); }

    public WebElement TextField_Email() { return findElement("com.bungii.customer:id/signup_field_email", LocatorType.Id); }

    public WebElement TextField_Phonenumber() { return findElement("com.bungii.customer:id/signup_field_phone", LocatorType.Id); }

    public WebElement TextField_Password() { return findElement("com.bungii.customer:id/signup_field_password", LocatorType.Id); }

    public WebElement TextField_Referral() { return findElement("com.bungii.customer:id/signup_field_prmo_code", LocatorType.Id); }

    public WebElement Select_ReferralSource() { return findElement("com.bungii.customer:id/signup_chevron_right", LocatorType.Id); }

    public WebElement Option_ReferralSource() { return findElement("//android.widget.CheckedTextView[@text='Other']", LocatorType.XPath); }

    public WebElement Link_ReferralSourceDone() { return findElement("android:id/button1", LocatorType.Id); }

    public WebElement Text_ReferralSource() { return findElement("com.bungii.customer:id/signup_textview_hear_about_us_choice", LocatorType.Id); }

    public WebElement Button_Signup() { return findElement("com.bungii.customer:id/signupGlobalButton", LocatorType.Id); }

    public WebElement Popup_ReferralCode() { return findElement("com.bungii.customer:id/buttonPanel", LocatorType.Id); }

    public WebElement Button_NoReferralConfirm() { return findElement("android:id/button2", LocatorType.Id); }

    public WebElement Button_NoReferralYes() { return findElement("android:id/button1", LocatorType.Id); }

    public WebElement Snackbar_Error() { return findElement("com.bungii.customer:id/snackbar_text", LocatorType.Id); }

    public WebElement Text_ReferralSourceAdded() { return findElement("com.bungii.customer:id/signup_textview_hear_about_us_choice", LocatorType.Id); }

    //--------------Sign up fields error messages-----------------------------------------------------------
    public WebElement Cust_Signup_Error_Email() { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/textinput_error' and @instance='2']", LocatorType.XPath); }

    public WebElement Cust_Signup_Error_Phone() { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/textinput_error' and @instance='3']", LocatorType.XPath); }

    public WebElement Cust_Signup_Error_Password() { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/textinput_error' and @instance='4']", LocatorType.XPath); }

    //--------------Verification page elements---------------------------------------------------------------

    public WebElement Textfield_SMSCode() { return findElement("com.bungii.customer:id/field_sms_code", LocatorType.Id); }

    public WebElement Button_VerifyContinue() { return findElement("com.bungii.customer:id/smsVerifyContinue", LocatorType.Id); }

    public WebElement Link_Resend() { return findElement("//android.widget.Button[@text='RESEND']", LocatorType.XPath); }

    public WebElement Title_Verification() { return findElement("//android.widget.TextView[@text='VERIFICATION']", LocatorType.XPath); }
}
