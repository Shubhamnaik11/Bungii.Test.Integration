package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends PageBase {

    public WebElement Generic_Element (boolean... ignoreException) { return findElement("//*[contains(@resource-id,\"com.bungii.driver\")]", LocatorType.XPath,ignoreException); }
    public WebElement Generic_HeaderElement (boolean... ignoreException) { return findElement("//android.view.View[@resource-id='com.bungii.driver:id/toolbar' or 'com.bungii.driver:id/toolbarLogin']/android.widget.TextView | //android.view.ViewGroup[@resource-id='com.bungii.driver:id/toolbar' or 'com.bungii.driver:id/toolbarLogin']/android.widget.TextView", LocatorType.XPath,ignoreException); }

    public WebElement Title_Status (boolean ... ignoreException) { return findElement("com.bungii.driver:id/toolbar_main_title", LocatorType.Id ,ignoreException    ); }

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


}
