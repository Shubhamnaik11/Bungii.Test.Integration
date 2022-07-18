package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ScheduledBungiiPage extends  PageBase {

    public WebElement Text_PageTitle() {return findElement("com.bungii.driver:id/toolbar_main_title", LocatorType.Id);}
    //public List<WebElement> List_ScheduledBungiis() { return findElements("com.bungii.driver:id/container_scheduled_trip_row", PageBase.LocatorType.Id); }
    public List<WebElement> List_ScheduledBungiis() { return findElements("com.bungii.driver:id/scheduled_trip_row_container", PageBase.LocatorType.Id); }
    public WebElement Button_Start() { return findElement("//android.widget.Button[@text='START BUNGII']", LocatorType.XPath); }

    public WebElement Cell_FirstTrip() { return findElement("(//android.widget.TextView[@resource-id='com.bungii.customer:id/item_my_bungii_tv_date'])[1]", LocatorType.XPath);}

    public WebElement Text_ScheduledBungiiStatus() { return  findElement("com.bungii.driver:id/scheduled_row_textview_status", LocatorType.Id);}

    public WebElement Button_ThreeDot() { return findElement("//android.view.ViewGroup[@resource-id='com.bungii.driver:id/appCompatImageView37']/android.widget.ImageView",LocatorType.XPath);}

    public WebElement Link_ScheduledBungiis() { return findElement("//android.widget.Button[@resource-id='com.bungii.driver:id/activity_more_options_btn_scheduled_bungiis']",LocatorType.XPath);}

    public WebElement Customer_ScheduledDelivery() { return findElement("//android.widget.TextView[@resource-id='com.bungii.driver:id/scheduled_row_textview_scheduleddatetime']",LocatorType.XPath);}
}
