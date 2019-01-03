package com.bungii.android.pages.bungiiDriver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TripDetailsPage extends  PageBase{
    public WebElement Button_Accept() { return findElement("com.bungii.driver:id/activity_pickup_request_accept_available_pickup_button", PageBase.LocatorType.Id); }

}
