package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AvailableTripsPage extends PageBase {


    public List<WebElement> List_AvailableBungiis() { return findElements("//*[@resource-id='']/android.widget.RelativeLayout", LocatorType.XPath); }

    public WebElement NavigationBar_Text (boolean ... ignoreException) { return findElement("com.bungii.driver:id/toolbar_main_title", LocatorType.Id ,ignoreException    ); }

    //------Available Trips Page--------------------------------------------------------------------
    public WebElement Row_AvailableTrip_01() { return findElement("com.bungii.driver:id/available_trips_recyclerview_list", LocatorType.Id); }

    public WebElement Trip01_CustomerName() { return findElement("com.bungii.driver:id/row_available_pickup_drivername", LocatorType.Id); }

    public WebElement Trip01_TimeFromHome() { return findElement("com.bungii.driver:id/row_available_pickup_textview_time_home", LocatorType.Id); }

    //------Trip Details Page-----------------------------------------------------------------------
    public WebElement Image() { return findElement("com.bungii.driver:id/pickup_item_detail_image", LocatorType.Id); }

    public WebElement Text_TimeToPickUp() { return findElement("com.bungii.driver:id/activity_pickup_request_time_to_pickup_textview", LocatorType.Id); }

    public WebElement Text_TripDistance() { return findElement("com.bungii.driver:id/activity_pickup_request_trip_distance_textview", LocatorType.Id); }

    public WebElement Text_ScheduledDate() { return findElement("com.bungii.driver:id/activity_pickup_request_scheduled_date_textview", LocatorType.Id); }

    public WebElement Text_ScheduledTime() { return findElement("com.bungii.driver:id/activity_pickup_request_scheduled_time_textview", LocatorType.Id); }

    public WebElement Button_Accept() { return findElement("com.bungii.driver:id/activity_pickup_request_accept_available_pickup_button", LocatorType.Id); }

    public WebElement Button_Back() { return findElement("//android.widget.ImageButton[@content-desc='Navigate up']", LocatorType.XPath); }

    public WebElement Row_AvailableTrip() {return findElements("//*[@resource-id='com.bungii.driver:id/row_available_pickup_imageview_arrow']",LocatorType.XPath).get(0);}
}
