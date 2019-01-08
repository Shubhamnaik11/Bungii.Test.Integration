package com.bungii.android.pages.bungiiDriver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends PageBase {

    public WebElement Title_Status (boolean ... ignoreException) { return findElement("com.bungii.driver:id/toolbar_main_title", LocatorType.Id ,ignoreException    ); }

    public WebElement Button_NavigationBar () { return findElement("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]", LocatorType.XPath); }
    public List<WebElement> Button_NavigationBarText () { return findElements("//*[@resource-id='com.bungii.driver:id/design_menu_item_text']", LocatorType.XPath); }



    public WebElement Image_DriverProfilePhoto () { return findElement("com.bungii.driver:id/home_driver_profile_image", LocatorType.Id); }

    public WebElement Button_OnlineOffline () { return findElement("com.bungii.driver:id/home_button_go_online", LocatorType.Id); }

    public WebElement Link_AvailableTrips (boolean ...ignoreException) { return findElement("com.bungii.driver:id/home_textview_available_trips", LocatorType.Id,ignoreException); }


    public WebElement Alert_NewBungii () { return findElement("com.bungii.driver:id/notification_alert_message", LocatorType.Id); }
    public WebElement Notification_AlertAccept () { return findElement("com.bungii.driver:id/notification_alert_button_positive", LocatorType.Id); }
    public WebElement Notification_AlertReject () { return findElement("com.bungii.driver:id/notification_alert_button_negative", LocatorType.Id); }


}
