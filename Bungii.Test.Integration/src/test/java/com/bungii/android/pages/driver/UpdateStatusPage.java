package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class UpdateStatusPage extends PageBase {
    public WebElement Text_WaitingForDriver(boolean ignoreException){return findElement("com.bungii.driver:id/progress_message",LocatorType.Id, ignoreException);}
    public WebElement Activity_loader(boolean ignoreException){return findElement("com.bungii.driver:id/progress_bar",LocatorType.Id, ignoreException);}

    public WebElement Tab_AddPhoto(){return findElement("//android.widget.TextView[@text='Tap to add photo']",LocatorType.XPath);}
    public WebElement Button_Save(){return findElement("//android.widget.Button[@text='Save']",LocatorType.XPath);}
    public WebElement Button_CLick(){return findElement("//*[@resource-id='com.motorola.camera2:id/controls']",LocatorType.XPath);}

    //Driver Rates Driver
    public WebElement Header_RateTeamate() {return findElement("//android.widget.TextView[@text=\"Rate duo teammate\"]", LocatorType.XPath);}
    public WebElement Star_Rating() {return findElement("//android.widget.RatingBar", LocatorType.XPath);}
    public WebElement Text_ChooseRating() {return findElement("//android.widget.TextView[@text='Choose your rating']", LocatorType.XPath);}
    public WebElement Text_DriverExperience() {return findElement("//android.widget.TextView[@text='Tell us about the other driver']", LocatorType.XPath);}
    public WebElement Textbox_AdditionalFeedback() {return findElement("//XCUIElementTypeTextView[@value='Any additional feedback (Optional)']", LocatorType.XPath);}
    public WebElement Button_Submit() {return findElement("//android.widget.Button[@text='Submit']", LocatorType.XPath);}
    public WebElement Text_Additional() {return findElement("//android.widget.TextView[@text='Additional']", LocatorType.XPath);}
    public WebElement RatingBar(){return findElement("//*[@resource-id='com.bungii.driver:id/rate_participants_rating_bar_customer']",LocatorType.XPath);}

}
