package com.bungii.ios.pages.other;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class FacebookPage extends PageBase {
    public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar[@name='Facebook']", PageBase.LocatorType.XPath); }
    public WebElement Text_Input() { return findElement("//XCUIElementTypeTextView/XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

    public WebElement Button_Next() {
        return findElement("Next", LocatorType.Name);
    }
    public WebElement Button_Share() {
        return findElement("Share", LocatorType.Name);
    }

    //TODO: check if this is to be removed
    public WebElement Button_ShareLink(boolean...ignoreException) {
        return findElement("promosrvqa.bungii.ccigoa", LocatorType.Name,ignoreException);
    }




}