package com.bungii.android.pages.bungiiDriver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;


public class InProgressBungiiPages extends PageBase {

    //------Page Titles--------------------------------------------------------------
    public WebElement Title_Status() { return findElement("com.bungii.driver:id/toolbar_title", LocatorType.Id); }

    //------Cancel Bungii------------------------------------------------------------
    public WebElement Button_Cancel() { return findElement("com.bungii.driver:id/toolbar_button_cancel", LocatorType.Id); }

    public WebElement Button_Cancel_Yes() { return findElement("android:id/button1", LocatorType.Id); }

    public WebElement Button_Cancel_Cancel() { return findElement("android:id/button2", LocatorType.Id); }

    //------Slider-------------------------------------------------------------------
    public WebElement Slider() { return findElement("com.bungii.driver:id/pickup_details_slider_view", LocatorType.Id); }
    //------SMS----------------------------------------------------------------------
    public WebElement Button_Customer_SMS() { return findElement("com.bungii.driver:id/pickup_details_sms_button", LocatorType.Id); }

    //------Call---------------------------------------------------------------------
    public WebElement Button_Customer_Call() { return findElement("com.bungii.driver:id/pickup_details_call_button", LocatorType.Id); }

    //------Bungii Statuses----------------------------------------------------------
    public WebElement BungiiStatus_Enroute() { return findElement("com.bungii.driver:id/pickup_details_status_1", LocatorType.Id); }

    public WebElement BungiiStatus_Arrived() { return findElement("com.bungii.driver:id/pickup_details_status_2", LocatorType.Id); }

    public WebElement BungiiStatus_LoadingItem() { return findElement("com.bungii.driver:id/pickup_details_status_3", LocatorType.Id); }

    public WebElement BungiiStatus_DrivingToDropOff() { return findElement("com.bungii.driver:id/pickup_details_status_4", LocatorType.Id); }

    public WebElement BungiiStatus_UnloadingItem() { return findElement("com.bungii.driver:id/pickup_details_status_5", LocatorType.Id); }

    //------Location Details---------------------------------------------------------
    public WebElement Bungii_LocationTitle() { return findElement("com.bungii.driver:id/pickup_details_address_name", LocatorType.Id); }

    public WebElement Bungii_Location() { return findElement("com.bungii.driver:id/pickup_details_address_value", LocatorType.Id); }

    public WebElement Bungii_ETA() { return findElement("com.bungii.driver:id/pickup_details_estimate", LocatorType.Id); }

    //------Customer Details-----------------------------------------------------------
    public WebElement Bungii_Customer_Title() { return findElement("com.bungii.driver:id/pickup_details_caller_title", LocatorType.Id); }

    public WebElement Bungii_Customer_Name() { return findElement("com.bungii.driver:id/pickup_details_caller_name", LocatorType.Id); }
}
