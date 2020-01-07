package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ScheduledBungiiPage extends  PageBase {

    public List<WebElement> List_ScheduledBungiis() { return findElements("com.bungii.driver:id/container_scheduled_trip_row", PageBase.LocatorType.Id); }
    public WebElement Button_Start() { return findElement("//android.widget.Button", LocatorType.XPath); }

    public WebElement Cell_FirstTrip() { return findElement("com.bungii.driver:id/container_scheduled_trip_row", LocatorType.Id);}

    public WebElement Text_ScheduledBungiiStatus() { return  findElement("com.bungii.driver:id/scheduled_row_textview_status", LocatorType.Id);}

}
