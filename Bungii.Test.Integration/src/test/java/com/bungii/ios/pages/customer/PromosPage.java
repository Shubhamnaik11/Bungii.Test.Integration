package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PromosPage extends PageBase {

    public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

    public WebElement Button_Add() {
        return findElement("ADD", PageBase.LocatorType.Name);
    }

    public WebElement Button_GetMoreMoney() {
        return findElement("GET MORE MONEY", PageBase.LocatorType.Name);
    }

    public WebElement TextBox_EnterCode() {
        return findElement("//XCUIElementTypeTextField", PageBase.LocatorType.XPath);
    }

    public List<WebElement> List_AvailablePromoCode() {
        return findElements(
                "//XCUIElementTypeStaticText[@name='Available Promo Codes']/following::XCUIElementTypeCell/XCUIElementTypeStaticText", PageBase.LocatorType.XPath);
    }

    public WebElement Image_Promo() {
        return findElement(
                "//XCUIElementTypeButton[@name='ADD']/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeImage", PageBase.LocatorType.XPath);
    }


}
