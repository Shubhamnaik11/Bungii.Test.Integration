package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiAcceptedPage extends PageBase {
    public WebElement Text_NavigationBar() {
        return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath);
    }

/*    public WebElement Button_Ok() {
        return findElement("OK", LocatorType.Name);
    }*/
public WebElement Button_Ok() {
    return findElement("OK", LocatorType.AccessibilityId);
}


    //STACK
    public WebElement Text_StackInfo() { return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(2); }
    public WebElement Text_BungiiAccepted() { return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(0); }
    public WebElement Image_RattingBar() { return findElement("rating filled star icon", LocatorType.Name); }
    public WebElement Label_DriverName(){ return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(1); }

    public WebElement Textlabel_DriverNearby() { return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(0); }
    public WebElement Textlabel_StackSubtitle() { return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(1); }
    public WebElement Textlabel_ProjectedTimeValue(){ return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(3); }
    public WebElement Textlabel_ProjectedTime() { return findElements("XCUIElementTypeStaticText", LocatorType.ClassName).get(2); }
    public WebElement Button_CancelBungii() { return findElement("Cancel Bungii", LocatorType.Name); }

}
