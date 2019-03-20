package com.bungii.ios.pages.customer;

import com.bungii.common.core.PageBase;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EstimatePage extends PageBase {

    public By DatePicker_BungiiTime = MobileBy.xpath("//XCUIElementTypeDatePicker");
    public By DateWheel_BungiiTime = MobileBy.xpath("//XCUIElementTypePickerWheel");

/*    public WebElement Indicator_Loading() {
        return findElement("In progress", LocatorType.Name);
    }*/

    public WebElement NaviagetionBar() {
        return findElement("//XCUIElementTypeNavigationBar[@name='ESTIMATE']", LocatorType.XPath);
    }

    public WebElement Text_NavigationBar() {
        return findElement("//XCUIElementTypeNavigationBar[@name='ESTIMATE']/XCUIElementTypeOther", LocatorType.XPath);
    }

    public WebElement Wheel_LoadTime() {
        return findElement("XCUIElementTypePickerWheel", LocatorType.ClassName);
    }

    public WebElement Select_Time() {
        return findElement(
                "//XCUIElementTypeStaticText[@name='SELECT']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther", LocatorType.XPath);
    }

    public WebElement Select_TimeRow() {
        return findElement("//XCUIElementTypeStaticText[@name='Load + Unload Time']/preceding-sibling::XCUIElementTypeOther", LocatorType.XPath);
    }

    public WebElement Text_PickUpLocation() {
        return findElement("//XCUIElementTypeStaticText[@name='PICKUP LOCATION']/following::XCUIElementTypeTextField", LocatorType.XPath);
    }

    public WebElement Text_DropOffLocation() {
        return findElement("//XCUIElementTypeStaticText[@name='DROP OFF LOCATION']/following::XCUIElementTypeTextField", LocatorType.XPath);
    }

    public WebElement Text_Distance() {
        return findElement("Trip Distance", LocatorType.Name);
    }

    public WebElement Text_DistanceValue() {
        return findElement("//XCUIElementTypeStaticText[@name='Trip Distance']/following::XCUIElementTypeStaticText", LocatorType.XPath);
    }

    public WebElement Text_LoadUnLoadTime() {
        return findElement("Load + Unload Time", LocatorType.Name);
    }

    public WebElement Text_LoadUnLoadTimeValue() {
        return findElement("//XCUIElementTypeStaticText[@name='Load + Unload Time']/preceding-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText", LocatorType.XPath);
    }

    public WebElement Text_PromoCode() {
        return findElement("Promo Code", LocatorType.Name);
    }
    public WebElement Row_PromoCode(){
        return findElement("//XCUIElementTypeStaticText[@name=\"Promo Code\"]/parent:: XCUIElementTypeOther",LocatorType.XPath);
    }

    public WebElement Text_PromoCodeValue() {
        return findElement("//XCUIElementTypeStaticText[@name='Promo Code']/following-sibling::XCUIElementTypeButton", LocatorType.XPath);
    }

    public WebElement Text_Estimate() {
        return findElement("Total Estimate", LocatorType.Name);
    }

    public WebElement Text_EstimateValue() {
        return findElement("//XCUIElementTypeStaticText[@name='Total Estimate']/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath);
    }

    public WebElement Text_PaymentMethod() {
        return findElement("Payment Mode", LocatorType.Name);
    }

    public WebElement Text_PaymentMethodValue() {
        return findElement("//XCUIElementTypeStaticText[@name='Payment Mode']/following-sibling::XCUIElementTypeButton", LocatorType.XPath);
    }

    public WebElement Text_Time() {
        return findElement("Time", LocatorType.Name);
    }

    public WebElement Text_TimeValue() {
        return findElement("//XCUIElementTypeStaticText[@name='Time']/preceding-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText", LocatorType.XPath);
    }

    public WebElement Button_InfoLoadingTime() {
        return findElement("//XCUIElementTypeStaticText[@name='Load + Unload Time']/following-sibling::XCUIElementTypeButton[@name='info']", LocatorType.XPath);
    }

    public WebElement Button_InfoTotalEstimate() {
        return findElement("//XCUIElementTypeStaticText[@name='Total Estimate']/following-sibling::XCUIElementTypeButton[@name='info']", LocatorType.XPath);
    }

    public WebElement Button_InfoTime() {
        return findElement("//XCUIElementTypeStaticText[@name='Time']/following-sibling::XCUIElementTypeButton[@name='info']", LocatorType.XPath);
    }

    public WebElement Button_AddPhoto() {
        return findElement("ADD ITEM PHOTO", LocatorType.Name);
    }
    public  WebElement Button_AddSecondPhoto(){
        return  findElement("2",LocatorType.Name);
    }

    public  WebElement Button_AddThirdPhoto(){
        return  findElement("3",LocatorType.Name);
    }
    public  WebElement Button_AddFourthPhoto(){
        return  findElement("4",LocatorType.Name);
    }
    public WebElement Button_Gallary() {
        return findElement("Gallery", LocatorType.Name);
    }

    public WebElement PhotosFolder() {
        return findElement("Camera Roll", LocatorType.Name);
    }

    public List<WebElement> Cell_Photo() {
        return findElements("//XCUIElementTypeCollectionView[@name='PhotosGridView']/XCUIElementTypeCell", LocatorType.XPath);
    }

    public WebElement CheckBoxOff_Terms() {
        return findElement("check box off", LocatorType.Name);
    }

    public WebElement CheckBoxOn_Terms() {
        return findElement("check box on", LocatorType.Name);
    }
    public WebElement Button_AddPromoCode() {
        return findElement("ADD", LocatorType.Name);
    }

    public WebElement CheckBox_Value() {
        return findElement("//XCUIElementTypeButton[contains(@name,'check box')]/following-sibling::XCUIElementTypeStaticText", LocatorType.XPath);
    }

    public WebElement Button_RequestBungii() {
        return findElement("REQUEST BUNGII", LocatorType.Name);
    }

    public WebElement Button_Cancel() {
        return findElement("Cancel", LocatorType.Name);
    }

/*    public WebElement Text_LoadTime() {
        return findElement("//XCUIElementTypeStaticText[@name='Load + Unload Time']/parent::XCUIElementTypeOther", LocatorType.XPath);
    }*/

    public WebElement Text_LoadTime() {
        return findElement("//XCUIElementTypeStaticText[@name='Load + Unload Time']/preceding-sibling::XCUIElementTypeButton", LocatorType.XPath);
    }

    public WebElement Button_Set(){return findElement("Set",LocatorType.Name);}
    public WebElement DateWheel_BungiiTime() {
        return findElement("//XCUIElementTypePickerWheel", LocatorType.XPath);
    }

    public WebElement Button_Time() {
        return findElement(
                "//XCUIElementTypeStaticText[@name='Now']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther", LocatorType.XPath);
    }

    public WebElement Row_TimeSelect() {
        return findElement(
                "//XCUIElementTypeStaticText[@name='Time']/parent::XCUIElementTypeOther", LocatorType.XPath);
    }

    public WebElement Button_Now() {
        return findElement("Set time for now", LocatorType.Name);
    }


}
