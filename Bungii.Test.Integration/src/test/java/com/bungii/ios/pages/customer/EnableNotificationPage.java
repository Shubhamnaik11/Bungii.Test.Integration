package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class EnableNotificationPage extends PageBase {
    public WebElement Button_Sure(boolean ...ignoreException) {
        return findElement("SURE", PageBase.LocatorType.Name,ignoreException);
    }


}
