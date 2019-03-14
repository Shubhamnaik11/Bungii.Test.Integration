package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiDetailsPage extends PageBase {

    public WebElement Button_CancelBungii() { return findElement("com.bungii.customer:id/scheduled_bungii_details_tv_cancel_bungii",LocatorType.Id); }
    public WebElement Button_CancelAccept () { return findElement("android:id/button2", LocatorType.Id); }
    public WebElement Button_Yes () { return findElement("android:id/button1", LocatorType.Id); }
}
