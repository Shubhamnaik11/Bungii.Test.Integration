package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class LocationPage extends PageBase {
    public WebElement Header_Location(boolean ...ignoreException) {        return findElement("//android.view.ViewGroup[@resource-id='com.bungii.customer:id/toolbar_location_permission']/android.widget.TextView", LocatorType.XPath,ignoreException);};
    //Wherever you need us, we’ll be there. We use your location to find drivers near you.
    public WebElement Text_Info(boolean ...ignoreException) {        return findElement("com.bungii.customer:id/textView6", LocatorType.Id,ignoreException);}
    public WebElement Image_Compass(boolean ...ignoreException) {        return findElement("com.bungii.customer:id/imageView2", LocatorType.Id,ignoreException);}
    public WebElement Button_Sure(boolean ...ignoreException) {        return findElement("com.bungii.customer:id/button_location_permission_sure", LocatorType.Id,ignoreException);}
    public WebElement CheckBox_DontAskAgain(boolean ...ignoreException) {        return findElement("com.android.packageinstaller:id/do_not_ask_checkbox", LocatorType.Id,ignoreException);}
    //Allow Bungii to access this device's location?
    public WebElement Alert_Text(boolean ...ignoreException) {        return findElement("com.android.packageinstaller:id/permission_message", LocatorType.Id,ignoreException);}
    public WebElement Button_Allow(boolean ...ignoreException) {        return findElement("com.android.packageinstaller:id/permission_allow_button", LocatorType.Id,ignoreException);}
    public WebElement Button_Deny(boolean ...ignoreException) {        return findElement("com.android.packageinstaller:id/permission_deny_button", LocatorType.Id,ignoreException);}
    public WebElement Subheader_FAQPage(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='Where to?']", LocatorType.XPath,ignoreException); }
    public WebElement Option_Chrome(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='Chrome']", LocatorType.XPath,ignoreException); }
    public WebElement Button_Always(boolean ...ignoreException) {        return findElement("android:id/button_always", LocatorType.Id,ignoreException);}
    public WebElement Header_DrivePage() { return findElement("//android.widget.TextView[@text='Bungii: The Ultimate Side Hustle.']", LocatorType.XPath); }



}
