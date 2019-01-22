package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiRequest extends PageBase {

//------On Demand Request Alert
    public WebElement Alert_Msg() { return findElement("com.bungii.driver:id/notification_alert_message", LocatorType.Id); }

    public WebElement AlertButton_View() { return findElement("com.bungii.driver:id/notification_alert_button_positive", LocatorType.Id); }

    public WebElement AlertButton_Cancel(boolean ...ignoreException) { return findElement("com.bungii.driver:id/notification_alert_button_negative", LocatorType.Id,ignoreException); }

    //------On Demand Request
    public WebElement Button_Accept() { return findElement("com.bungii.driver:id/activity_pickup_request_accept_bungii_button", LocatorType.Id); }

    public WebElement Button_Reject(boolean ...ignoreException) { return findElement("com.bungii.driver:id/activity_pickup_request_reject_bungii_button", LocatorType.Id,ignoreException); }

}
