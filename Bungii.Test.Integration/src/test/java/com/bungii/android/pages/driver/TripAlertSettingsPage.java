package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TripAlertSettingsPage extends PageBase {
    //Scheduled Trip Alerts notify you when there are scheduled Bungiis available in you area. Here you can select the times you'd like to receive scheduled deliveries. NOTE: If your schedule is fairly open, we recommend leaving this setting 'ON' for all days.
    //SMS Alerts notify you when an unfulfilled trip is pending in your area. Here you can select the times you'd like to be notified via text message.
    public WebElement TripAlertHeader() { return findElement("com.bungii.driver:id/text_settings_heading", LocatorType.Id); }
   //checked	=false/true
    public WebElement Radio_TripAlert() { return findElement("com.bungii.driver:id/text_settings_radio_trip_alerts", LocatorType.Id); }
    public WebElement Radio_SmsAlert() { return findElement("com.bungii.driver:id/text_settings_radio_sms_alerts", LocatorType.Id); }

}
