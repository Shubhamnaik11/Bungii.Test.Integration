package com.bungii.ios.pages.other;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Facebook extends PageBase {
    public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

    public WebElement Button_Next() {
        return findElement("Next", LocatorType.Name);
    }
    public WebElement Button_Share() {
        return findElement("Share", LocatorType.Name);
    }




}