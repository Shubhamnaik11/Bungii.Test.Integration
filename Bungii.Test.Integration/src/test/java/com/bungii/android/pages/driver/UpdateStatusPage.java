package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class UpdateStatusPage extends PageBase {
    public WebElement Text_WaitingForDriver(boolean ignoreException){return findElement("com.bungii.driver:id/progress_message",LocatorType.Id, ignoreException);}
    public WebElement Activity_loader(boolean ignoreException){return findElement("com.bungii.driver:id/progress_bar",LocatorType.Id, ignoreException);}
}
