package com.bungii.android.pages.bungiiDriver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class HomePage extends PageBase {

    public WebElement Title_Status () { return findElement("com.bungii.driver:id/toolbar_main_title", LocatorType.Id); }

    public WebElement Image_DriverProfilePhoto () { return findElement("com.bungii.driver:id/home_driver_profile_image", LocatorType.Id); }

    public WebElement Button_OnlineOffline () { return findElement("com.bungii.driver:id/home_button_go_online", LocatorType.Id); }

    public WebElement Link_AvailableTrips () { return findElement("com.bungii.driver:id/home_textview_available_trips", LocatorType.Id); }


}
