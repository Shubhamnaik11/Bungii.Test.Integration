package com.bungii.android.pages.bungii;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiAcceptedPage extends PageBase {

    public WebElement PageTitle_BungiiAccepted()
    {
        return findElement("//android.widget.TextView[@text='BUNGII ACCEPTED']", LocatorType.XPath);
    }

    public WebElement Label_BungiiAccepted()
    {
        return findElement("//android.widget.LinearLayout[@id='com.bungii.customer:id/searching_view_bungii_accepted_container']/android.widget.TextView[@instance='1']", LocatorType.XPath);
    }
    public WebElement Label_DriverEnRoute()
    {
        return findElement("com.bungii.customer:id/bungii_accepted_enroute", LocatorType.Id);
    }

    public WebElement Image_Driver()
    {
        return findElement("com.bungii.customer:id/bungii_accepted_driver_image", LocatorType.Id);
    }
    public WebElement DriverRatingBar()
    {
        return findElement("com.bungii.customer:id/rating_bar", LocatorType.Id);
    }
    public WebElement Label_DriverName()
    {
        return findElement("com.bungii.customer:id/bungii_accepted_driver_name", LocatorType.Id);
    }
    public WebElement Button_OK()
    {
        return findElement("com.bungii.customer:id/bungii_accepted_button_ok", LocatorType.Id);
    }

}
