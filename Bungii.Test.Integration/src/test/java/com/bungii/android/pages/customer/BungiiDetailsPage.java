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

    public WebElement Button_Driver1Call(){return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_ib_call']", LocatorType.XPath).get(0);}
    public WebElement Button_Driver2Call(){return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_ib_call']", LocatorType.XPath).get(1);}
    public WebElement Button_Driver1SMS(){return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_bt_sms']", LocatorType.XPath).get(0);}
    public WebElement Button_Driver2SMS(){return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_bt_sms']", LocatorType.XPath).get(1);}

    public WebElement Text_ContactDriverMessage(){return findElement("//android.widget.TextView[@text='You will have the ability to contact your drivers when the Bungii begins']", LocatorType.XPath);}
    public WebElement Text_BungiiRequestAccepted(){return findElement("//android.widget.TextView[@text='BUNGII REQUEST']", LocatorType.XPath);}
    public WebElement Text_ScheduledBungiis(){return findElement("//android.widget.TextView[@text='SCHEDULED BUNGIIS']", LocatorType.XPath);}
    public WebElement Text_AvailableTrips(){return findElement("//android.widget.TextView[@text='AVAILABLE TRIPS']", LocatorType.XPath);}
    public WebElement Text_BungiiDetails(){return findElement("//android.widget.TextView[@text='BUNGII DETAILS']", LocatorType.XPath);}

    public WebElement Text_snackbarmessage() {return findElement("com.bungii.driver:id/snackbar_text", LocatorType.Id);}
}
