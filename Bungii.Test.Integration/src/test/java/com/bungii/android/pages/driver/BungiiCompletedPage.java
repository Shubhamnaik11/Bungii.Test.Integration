package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiCompletedPage extends PageBase {

    public WebElement Title_Status() { return findElement("com.bungii.driver:id/pickup_summary_toolbar_title", LocatorType.Id); }

    public WebElement Text_TotalTimeLabel(){return  findElement("//*[@text='Total Time']",LocatorType.XPath);}

    public WebElement Text_TotalTime() { return findElement("com.bungii.driver:id/pickup_summary_text_total_time", LocatorType.Id); }

    public WebElement Text_TotalDistanceLabel(){return  findElement("//*[@text='Total Distance']",LocatorType.XPath);}

    public WebElement Text_TotalDistance() { return findElement("com.bungii.driver:id/pickup_summary_text_total_distance", LocatorType.Id); }

    public WebElement Text_TotalEarningsLabel(){return  findElement("//*[@text='Total Earnings']",LocatorType.XPath);}

    public WebElement Text_TotalEarnings() { return findElement("com.bungii.driver:id/pickup_summary_text_total_earnings", LocatorType.Id); }

    public WebElement Button_OnToTheNext(boolean ...ignoreException) { return findElement("com.bungii.driver:id/pickup_summary_button_close_summary", LocatorType.Id,ignoreException); }

    public WebElement Text_Label(){return  findElement("//*[@text='Cha-Ching!']",LocatorType.XPath);}
    public WebElement Image_Dollar(){return  findElement("//*[@text=\"Cha-Ching!\"]/preceding-sibling::android.widget.ImageView",LocatorType.XPath);}
}
