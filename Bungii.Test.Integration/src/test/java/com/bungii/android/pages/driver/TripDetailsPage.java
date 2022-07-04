package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TripDetailsPage extends  PageBase{
    public WebElement Button_Accept() { return findElement("com.bungii.driver:id/activity_pickup_request_accept_available_pickup_button", PageBase.LocatorType.Id); }

    public WebElement Text_Pickup_Location_line1() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_Pickup_Location_line2() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[2]", LocatorType.XPath); }
    public WebElement Text_DropOff_Location_line1() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.TextView[1]", LocatorType.XPath); }
    public WebElement Text_DropOff_Location_line2() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.TextView[2]", LocatorType.XPath); }
    public WebElement Text_Total_Distance() { return findElement("//android.view.ViewGroup/android.widget.LinearLayout[2]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView[1]", LocatorType.XPath); }





}
