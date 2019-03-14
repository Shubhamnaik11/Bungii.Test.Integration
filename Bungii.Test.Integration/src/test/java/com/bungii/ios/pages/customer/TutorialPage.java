package com.bungii.ios.pages.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class TutorialPage extends PageBase {

    public WebElement Button_Close() {
        return findElement("Button", PageBase.LocatorType.Name);
    }
    public WebElement Text_ToutorialHeader(boolean... ignoreException){return findElement("SO WHAT IS BUNGII?", LocatorType.Name,ignoreException);}
}
