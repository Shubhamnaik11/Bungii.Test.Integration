package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TermsPage extends PageBase {

//Terms Page Elements

    //Checkboxes
    public WebElement Checkbox_Agree(boolean ...ignoreException) { return findElement("com.bungii.customer:id/checkbox_accept_terms", LocatorType.Id,ignoreException); }
    //----------------Buttons--------------//
    public WebElement Button_Continue() { return findElement("com.bungii.customer:id/btn_terms_continue", LocatorType.Id); }
    //Permissions popup

    public WebElement Button_PermissionsAllow() { return findElement(" com.android.settings:id/switch_widget", LocatorType.Id); }
    public WebElement Button_GoToSetting() { return findElement("android:id/button1", LocatorType.Id); }

    //  public WebElement Button_PermissionsAllow() { return findElement("com.android.packageinstaller:id/permission_allow_button", LocatorType.Id); }
    public WebElement Button_PermissionsDeny() { return findElement("com.android.packageinstaller:id/permission_deny_butto", LocatorType.Id); }
 //   public WebElement Popup_PermissionsMessage(boolean... ignore) { return findElement("com.android.packageinstaller:id/permission_message", LocatorType.Id,ignore[0]); }
    public WebElement Popup_PermissionsMessage(boolean... ignore) { return findElement("android:id/message", LocatorType.Id,ignore[0]); }

}
