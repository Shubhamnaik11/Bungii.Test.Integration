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

}
