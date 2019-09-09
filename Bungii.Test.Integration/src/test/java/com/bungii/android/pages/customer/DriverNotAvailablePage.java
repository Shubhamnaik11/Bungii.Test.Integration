package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class DriverNotAvailablePage extends  PageBase {

    public WebElement Header_DriverNotAvailable(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='DRIVER NOT AVAILABLE']", PageBase.LocatorType.XPath,ignoreException); }

    public WebElement Button_Ok() { return findElement("com.bungii.customer:id/driver_unavailaible_button_ok", PageBase.LocatorType.Id); }
    public WebElement Alert_ConfirmRequestMessage () { return findElement("android:id/message", LocatorType.Id); }
    public WebElement Alert_ScheduleBungii (boolean...ignoreException) { return findElement("//android.widget.Button[@text='Schedule Estimate and Customer Cancel']", LocatorType.XPath,ignoreException); }


}
