package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiDetailsPage extends PageBase {

    public WebElement Button_CancelBungii() { return findElement("com.bungii.customer:id/scheduled_bungii_details_tv_cancel_bungii",LocatorType.Id); }
    public WebElement Button_CancelAccept () { return findElement("android:id/button2", LocatorType.Id); }
    public WebElement Button_Yes () { return findElement("android:id/button1", LocatorType.Id); }

    public WebElement Text_Driver1Status(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_container_no_drivers']/descendant::android.widget.TextView", LocatorType.XPath).get(1);}
    public WebElement Text_Driver2Status(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_container_no_drivers']/descendant::android.widget.TextView", LocatorType.XPath).get(3);}
    public WebElement Text_Driver1StatusTag(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_container_no_drivers']/descendant::android.widget.TextView", LocatorType.XPath).get(0);}
    public WebElement Text_Driver2StatusTag(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_container_no_drivers']/descendant::android.widget.TextView", LocatorType.XPath).get(2);}

    public WebElement Text_Driver2Status1() { return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_container_no_drivers']/descendant::android.widget.TextView", LocatorType.XPath).get(1);}
    public WebElement Text_Driver2StatusTag1() { return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_container_no_drivers']/descendant::android.widget.TextView", LocatorType.XPath).get(0);}

    public WebElement Text_Driver1Name(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tv_drivername_value']", LocatorType.XPath).get(0);}
    public WebElement Text_Driver2Name(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tv_drivername_value']", LocatorType.XPath).get(1);}

}
