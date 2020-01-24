package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class UpdateStatusPage extends PageBase {
    public WebElement Slider() { return findElement("com.bungii.driver:id/pickup_details_slider_view", LocatorType.Id); }
}
