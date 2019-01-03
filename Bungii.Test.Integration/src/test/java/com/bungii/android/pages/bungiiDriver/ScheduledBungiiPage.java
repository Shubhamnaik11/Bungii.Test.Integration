package com.bungii.android.pages.bungiiDriver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ScheduledBungiiPage extends  PageBase {

    public List<WebElement> List_ScheduledBungiis() { return findElements("com.bungii.driver:id/container_scheduled_trip_row", PageBase.LocatorType.Id); }
    public WebElement Button_Start() { return findElement("//android.widget.Button", LocatorType.XPath); }


}
