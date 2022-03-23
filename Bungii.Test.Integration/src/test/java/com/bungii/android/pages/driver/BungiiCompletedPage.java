package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiCompletedPage extends PageBase {

    public WebElement Title_Status() { return findElement("com.bungii.driver:id/pickup_summary_toolbar_title", LocatorType.Id); }

    public WebElement Text_TotalTimeLabel(){return  findElement("//*[@text='Total Time']",LocatorType.XPath);}

    public WebElement Text_TotalTime() { return findElement("com.bungii.driver:id/pickup_summary_text_total_time", LocatorType.Id); }

    public WebElement Text_TotalDistanceLabel(){return  findElement("//*[@text='Total Distance']",LocatorType.XPath);}

    public WebElement Text_TotalDistance() { return findElement("com.bungii.driver:id/pickup_summary_text_total_distance", LocatorType.Id); }

    //public WebElement Text_TotalEarningsLabel(){return  findElement("//*[@text='Total Earnings']",LocatorType.XPath);}
    public WebElement Text_TotalEarningsLabel(){return  findElement("//*[@resource-id='com.bungii.driver:id/activity_pickup_summary_rl_top']/android.widget.TextView",LocatorType.XPath);}
    public WebElement Text_TotalEarnings() { return findElement("com.bungii.driver:id/appCompatTextView43", LocatorType.Id); }

    //public WebElement Button_OnToTheNext(boolean ...ignoreException) { return findElement("com.bungii.driver:id/pickup_summary_button_close_summary", LocatorType.Id,ignoreException); }
    public WebElement Button_OnToTheNext(boolean ...ignoreException) { return findElement("com.bungii.driver:id/pickup_summary_button_next_bungii", LocatorType.Id,ignoreException); }

    public WebElement Text_Label(){return  findElement("//*[@text='Cha-Ching!']",LocatorType.XPath);}
    public WebElement Image_Dollar(){return  findElement("//*[@text=\"Cha-Ching!\"]/preceding-sibling::android.widget.ImageView",LocatorType.XPath);}

    public WebElement Text_BungiiStatus(){return findElement("//android.widget.TextView[@text='Bungii completed']",LocatorType.XPath);}
    public WebElement Button_NextBungii(boolean ...ignoreException) { return findElement("com.bungii.driver:id/appCompatButton4", LocatorType.Id,ignoreException); }
}
