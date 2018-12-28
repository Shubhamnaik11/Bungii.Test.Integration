package com.bungii.android.pages.otherApps;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;

import java.util.List;

public class OtherAppsPage extends PageBase {

    //------SMS---------------------------------------------------------------------------------------
    public WebElement SMS_Samsung_RecipientNo() { return findElement("com.android.mms:id/recipients_editor_to", LocatorType.Id); }

    public WebElement SMS_Moto_RecipientNo(boolean... ignoreException) { return findElement("com.android.mms:id/recipients_editor", LocatorType.Id,ignoreException); }

    //------Call--------------------------------------------------------------------------------------
    public WebElement Call_Samsung_Number() { return findElement("com.android.contacts:id/digits", LocatorType.Id); }

    public WebElement Call_Moto_Number(boolean... ignoreException) { return findElement("com.android.dialer:id/digits", LocatorType.Id,ignoreException); }

   // public List<WebElement> Text_Notification() { return findElements("//*[@resource-id='android:id/notification_main_column']/descendant::*[@resource-id='android:id/text']", LocatorType.XPath); }
    public List<WebElement> Text_NotificationTitle(){ return findElements("//*[@resource-id='android:id/big_text' or @resource-id='android:id/text']/parent::android.widget.LinearLayout/preceding-sibling::android.widget.LinearLayout/*[@resource-id='android:id/title']", LocatorType.XPath); }

    public List<WebElement> Text_Notification() { return findElements("//*[@resource-id='android:id/big_text' or @resource-id='android:id/text']", LocatorType.XPath); }




}
