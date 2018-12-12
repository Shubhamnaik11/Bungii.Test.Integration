package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiAcceptedPage extends PageBase {
    public WebElement Text_NavigationBar() {
        return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath);
    }

    public WebElement Button_Ok() {
        return findElement("OK", LocatorType.Name);
    }


}
