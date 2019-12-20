package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TripAlertSettingsPage extends PageBase {

    public WebElement Tab_TripAlerts() { return findElement("//android.widget.RadioButton[@text='Trip Alerts']", LocatorType.XPath);}

    public WebElement Tab_SMSAlerts() { return findElement("//android.widget.RadioButton[@text='SMS Alerts']", LocatorType.XPath);}

    public WebElement Text_TripAndSMSAlertsText(){return findElement("//*[@resource-id='com.bungii.driver:id/text_settings_heading']", LocatorType.XPath);}

    public List<WebElement> Text_TripAlertsTime () { return findElements("//android.widget.TextView[@text='07:00 - 21:00']", LocatorType.XPath); }

    public WebElement Text_SMSAlertsTime(){ return findElement("//[@resource-id='com.bungii.driver:id/text_settings_row_text_time']", LocatorType.XPath);}

    public WebElement Image_TimeSettingsArrow() { return findElement("//*[@resource-id='com.bungii.driver:id/text_settings_iv_row_arrow']", LocatorType.XPath);}

    public WebElement Text_TimeSettingsFromTime() { return findElement("//*[@resource-id='com.bungii.driver:id/edit_texting_time_text_from_value']", LocatorType.XPath);}

    public WebElement Button_SaveTime() { return findElement("//*[@resource-id='com.bungii.driver:id/menu_save']", LocatorType.XPath);}

    public WebElement TimePicker_ChangeTime(){return findElement("//android.view.View[@content-desc=\"Hours circular slider: 8\"]", LocatorType.XPath);}

    public WebElement TimePicker_OK(){return findElement("//*[@resource-id='android:id/button1']", LocatorType.XPath);}


}
