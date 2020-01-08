package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DriverHomePage extends PageBase {

    public WebElement Generic_Element (boolean... ignoreException) { return findElement("//*[contains(@resource-id,\"com.bungii.driver\")]", LocatorType.XPath,ignoreException); }
    public WebElement Generic_HeaderElement (boolean... ignoreException) { return findElement("//android.view.View[@resource-id='com.bungii.driver:id/toolbar' or 'com.bungii.driver:id/toolbarLogin' or 'com.bungii.driver:id/toolbar_title']/android.widget.TextView | //android.view.ViewGroup[@resource-id='com.bungii.driver:id/toolbar' or 'com.bungii.driver:id/toolbarLogin']/android.widget.TextView", LocatorType.XPath,ignoreException); }

   // public WebElement Title_Status (boolean ... ignoreException) { return findElement("//*[@resource-id='com.bungii.driver:id/toolbar_main_title' or 'com.bungii.driver:id/toolbar_title']", LocatorType.XPath ,ignoreException    ); }
    public WebElement Title_Status (boolean ... ignoreException) { return findElement("//*[@resource-id='com.bungii.driver:id/toolbar_main_title'] | //*[@resource-id='com.bungii.driver:id/toolbar_title']", LocatorType.XPath ,ignoreException    ); }

    public WebElement Button_NavigationBar () { return findElement("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]", LocatorType.XPath); }
    public List<WebElement> Button_NavigationBarText () { return findElements("//*[@resource-id='com.bungii.driver:id/design_menu_item_text']", LocatorType.XPath); }



    public WebElement Image_DriverProfilePhoto () { return findElement("com.bungii.driver:id/home_driver_profile_image", LocatorType.Id); }

    public WebElement Button_OnlineOffline () { return findElement("com.bungii.driver:id/home_button_go_online", LocatorType.Id); }

    public WebElement Link_AvailableTrips (boolean ...ignoreException) { return findElement("com.bungii.driver:id/home_textview_available_trips", LocatorType.Id,ignoreException); }


    public WebElement Alert_NewBungii () { return findElement("com.bungii.driver:id/notification_alert_message", LocatorType.Id); }
    public WebElement Text_DriverInfo () { return findElements("android.widget.TextView", LocatorType.ClassName).get(2); }
    public WebElement Text_DriverName () { return findElements("android.widget.TextView", LocatorType.ClassName).get(1); }
    public WebElement Text_RattingBar () { return findElement("android.widget.RatingBar", LocatorType.ClassName); }

    public WebElement Notification_AlertAccept () { return findElement("com.bungii.driver:id/notification_alert_button_positive", LocatorType.Id); }
    public WebElement Notification_AlertReject () { return findElement("com.bungii.driver:id/notification_alert_button_negative", LocatorType.Id); }

    public WebElement Text_CommonQuestions () { return findElement("//android.view.View[@text='WHAT IS THIS PAGE FOR?']",LocatorType.XPath); }
    public WebElement Text_Leaderboard () { return findElement("//*[@resource-id='content']/android.view.View[2]/descendant::android.view.View[last()]", LocatorType.XPath); }
    public WebElement Text_ScheduledBungiis () { return findElement("//android.widget.TextView[@text='No Bungiis']",LocatorType.XPath); }
    public WebElement Text_AvailableTrips () { return findElement("//android.widget.TextView[@text='No Trips Available']",LocatorType.XPath); }
    public WebElement Text_Earnings () { return findElement("//android.view.View[@text='DRIVER INFO']",LocatorType.XPath); }
    public WebElement Text_Account () { return findElement("//*[@resource-id='com.bungii.driver:id/account_info_textview_name']",LocatorType.XPath); }
    public WebElement Text_TripAlertSettings () { return findElement("//*[@resource-id='com.bungii.driver:id/text_settings_radio_trip_alerts']",LocatorType.XPath); }
    public WebElement Text_Feedback() { return findElement("//*[@resource-id='com.bungii.driver:id/feedback_text_view_title']",LocatorType.XPath); }
    public WebElement Text_Store () { return findElement("//android.view.View[@text='BUNGII STORE']",LocatorType.XPath); }
    public WebElement Text_Logout () { return findElement("//android.widget.TextView[@text='LOGIN']",LocatorType.XPath); }

    public WebElement Text_ErrorMessage(){ return findElement("//android.widget.TextView[@text='It looks like we ran into a hiccup. Please contact support@bungii.com for more information.']", LocatorType.XPath);}
}
