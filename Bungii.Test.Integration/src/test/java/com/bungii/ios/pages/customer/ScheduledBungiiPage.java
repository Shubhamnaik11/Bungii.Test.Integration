package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ScheduledBungiiPage extends PageBase {
    public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }


    public WebElement Button_SaveMoney() {
        return findElement("SAVE MONEY'", LocatorType.Name);
    }

    public WebElement Cell_TripInformation() {
        return findElement(
                "//XCUIElementTypeButton[@name='SAVE MONEY']/preceding-sibling::XCUIElementTypeTable/XCUIElementTypeCell", LocatorType.XPath);
    }

    public WebElement Cell_TripTimeInformation() {
        return findElement(
                "//XCUIElementTypeButton[@name='SAVE MONEY']/preceding-sibling::XCUIElementTypeTable/XCUIElementTypeOther", LocatorType.XPath);
    }

    public WebElement Image_Bungii() {
        return findElement(
                "//XCUIElementTypeButton[@name='SAVE MONEY']/preceding-sibling::XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeImage", LocatorType.XPath);
    }

    public WebElement Text_BungiiTime() {
        return findElement(
                "//XCUIElementTypeButton[@name='SAVE MONEY']/preceding-sibling::XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText", LocatorType.XPath);
    }

    public WebElement Text_BungiiStatus() {
        return findElement(
                "//XCUIElementTypeButton[@name='SAVE MONEY']/preceding-sibling::XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[1]", LocatorType.XPath);
    }

    public WebElement Table_NoBungii() {
        return findElement("//XCUIElementTypeTable", LocatorType.XPath);
    }
    public List<WebElement> List_SchBungii() {
        return findElements("//XCUIElementTypeImage[@name='disclosure-arrow-right']/preceding-sibling::XCUIElementTypeStaticText[last()]", LocatorType.XPath);
    }


}
