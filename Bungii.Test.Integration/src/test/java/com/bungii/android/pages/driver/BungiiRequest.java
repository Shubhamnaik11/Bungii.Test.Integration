package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BungiiRequest extends PageBase {

    public WebElement Navigation_Header() { return findElement("//*[@resource-id='com.bungii.driver:id/pickup_request_toolbar']/android.widget.LinearLayout/android.widget.TextView", LocatorType.XPath); }


    //------On Demand Request Alert
    public WebElement Alert_Msg(boolean ignoreException) { return findElement("com.bungii.driver:id/notification_alert_message", LocatorType.Id,ignoreException); }

    public WebElement Alert_Msg(boolean ...ignoreexception) { return findElement("com.bungii.driver:id/notification_alert_message", LocatorType.Id, ignoreexception); }

    public WebElement AlertButton_View() { return findElement("com.bungii.driver:id/notification_alert_button_positive", LocatorType.Id); }

    public WebElement AlertButton_Cancel(boolean ...ignoreException) { return findElement("com.bungii.driver:id/notification_alert_button_negative", LocatorType.Id,ignoreException); }

    //------On Demand Request
    public WebElement Button_Accept() { return findElement("com.bungii.driver:id/activity_pickup_request_accept_bungii_button", LocatorType.Id); }

    public WebElement Button_Reject(boolean ...ignoreException) { return findElement("com.bungii.driver:id/activity_pickup_request_reject_bungii_button", LocatorType.Id,ignoreException); }


    public WebElement Alert_MsgTitle() { return findElement("com.bungii.driver:id/notification_alert_title", LocatorType.Id); }

    public WebElement Text_ValueEarning() { return findElement("//android.widget.TextView[@text='EARNINGS']/following-sibling::android.widget.TextView", LocatorType.XPath); }
  //REMOVE THIS
    public WebElement Text_Earning() { return findElement("//android.widget.TextView[@text='EARNINGS']", LocatorType.XPath); }

    public WebElement Text_Distance() { return findElement("//*[contains(@text,'TO PICKUP')]", LocatorType.XPath); }
    public WebElement Text_ValueDistance() { return findElement("//android.widget.TextView[@resource-id='com.bungii.driver:id/pickup_request_tv_estimated_duration']/preceding-sibling::android.widget.TextView", LocatorType.XPath); }

    public WebElement Text_PickupLocation_LineOne () { return findElement("//android.widget.ImageView[@resource-id='com.bungii.driver:id/pickup_request_iv_pickup']/following-sibling::android.widget.LinearLayout/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_PickupLocation_LineTwo () { return findElement("//android.widget.ImageView[@resource-id='com.bungii.driver:id/pickup_request_iv_pickup']/following-sibling::android.widget.LinearLayout/android.widget.TextView[2]", LocatorType.XPath); }

    public WebElement Text_DropOffLocation_LineOne () { return findElement("//android.widget.ImageView[@resource-id='com.bungii.driver:id/pickup_request_iv_dropoff']/following-sibling::android.widget.LinearLayout/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_DropOffLocation_LineTwo () { return findElement("//android.widget.ImageView[@resource-id='com.bungii.driver:id/pickup_request_iv_dropoff']/following-sibling::android.widget.LinearLayout/android.widget.TextView[2]", LocatorType.XPath); }


}
