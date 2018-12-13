package com.bungii.android.pages.bungiiCustomer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TermsPage extends PageBase {

//Terms Page Elements

    //Checkboxes
    public WebElement Checkbox_Agree() { return findElement("com.bungii.customer:id/checkbox_accept_terms", LocatorType.Id); }
    //----------------Buttons--------------//
    public WebElement Button_Continue() { return findElement("com.bungii.customer:id/btn_terms_continue", LocatorType.Id); }
    //Permissions popup
    public WebElement Button_PermissionsAllow() { return findElement("com.android.packageinstaller:id/permission_allow_button", LocatorType.Id); }
    public WebElement Button_PermissionsDeny() { return findElement("com.android.packageinstaller:id/permission_deny_butto", LocatorType.Id); }
    public WebElement Popup_PermissionsMessage() { return findElement("com.android.packageinstaller:id/permission_message", LocatorType.Id); }

}
