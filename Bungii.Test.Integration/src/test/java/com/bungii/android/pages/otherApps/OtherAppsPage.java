package com.bungii.android.pages.otherApps;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;

import java.util.List;

public class OtherAppsPage extends PageBase {

    //------SMS---------------------------------------------------------------------------------------
    public WebElement SMS_Samsung_RecipientNo() { return findElement("com.android.mms:id/recipients_editor_to", LocatorType.Id); }

    public WebElement Text_ChromeUrl(boolean... ignoreException) { return findElement("com.android.chrome:id/url_bar", LocatorType.Id,ignoreException); }

    public WebElement SMS_Moto_RecipientNo(boolean... ignoreException) { return findElement("com.android.mms:id/recipients_editor", LocatorType.Id,ignoreException); }
    public WebElement SMS_Moto_RecipientNo_And7(boolean... ignoreException) { return findElement("com.google.android.apps.messaging:id/conversation_title", LocatorType.Id,ignoreException); }



    //------Call--------------------------------------------------------------------------------------
    public WebElement Call_Samsung_Number() { return findElement("com.android.contacts:id/digits", LocatorType.Id); }

    public WebElement Call_Moto_Number(boolean... ignoreException) { return findElement("com.android.dialer:id/digits", LocatorType.Id,ignoreException); }

   // public List<WebElement> Text_Notification() { return findElements("//*[@resource-id='android:id/notification_main_column']/descendant::*[@resource-id='android:id/text']", LocatorType.XPath); }
    public List<WebElement> Text_NotificationTitle(){ return findElements("//*[@resource-id='android:id/big_text' or @resource-id='android:id/text']/parent::android.widget.LinearLayout/preceding-sibling::android.widget.LinearLayout/*[@resource-id='android:id/title']", LocatorType.XPath); }

    public List<WebElement> Text_Notification() { return findElements("//*[@resource-id='android:id/big_text' or @resource-id='android:id/text']", LocatorType.XPath); }


    //FB
    public WebElement Button_Options() { return findElement("//android.view.View[@content-desc=\"Selected, More, Tab 5 of 5\"]", LocatorType.XPath); }
    public WebElement Link_ViewProfile() { return findElement("//android.view.View[@content-desc=\"Your Profile\"]", LocatorType.XPath); }
    public WebElement Shared_Link(boolean... ignoreException) { return findElement("//android.view.View[contains(@content-desc,\"Shared Link: Check this out!, I just summoned a truck from my phone. You should download this new app called Bungii.\")]", LocatorType.XPath,ignoreException); }

    public WebElement Shared_Post(boolean... ignoreException){return findElement("//android.view.View[contains(@content-desc,\" You should download this new app called Bungii.\")]/parent::android.view.View/parent::android.view.View/preceding-sibling::android.view.View[1]",LocatorType.XPath,ignoreException);}



    public WebElement Text_TweeterPost() { return findElement("com.twitter.android:id/tweet_text", LocatorType.Id); }
    public WebElement Button_Tweet() { return findElement("com.twitter.android:id/button_tweet", LocatorType.Id); }



}
