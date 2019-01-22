package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiCompletePage extends PageBase {


    public WebElement PageTitle_BungiiComplete() { return findElement("//android.widget.TextView[@text='BUNGII COMPLETE']",LocatorType.XPath); }

    public WebElement CloseRateTipPage() { return findElement("//android.view.View[@id='com.bungii.customer:id/toolbar']/android.widget.ImageView",LocatorType.XPath); }

    public WebElement Title_RateYourDriver() { return findElement("//android.widget.TextView[@text ='Rate Your driver']",LocatorType.XPath); }

    public WebElement Image_Driver() { return findElement("com.bungii.customer:id/driver_profile_image",LocatorType.Id); }

    public WebElement DriverName() { return findElement("//android.widget.TextView[@instance='2']",LocatorType.XPath); }

    public WebElement RatingBar() { return findElement("com.bungii.customer:id/rating_bar",LocatorType.Id); }

    public WebElement Title_Tip() { return findElement("//android.widget.TextView[@text='Tip']",LocatorType.XPath); }

    public WebElement TipAmount() { return findElement("com.bungii.customer:id/tip_value_label",LocatorType.Id); }

    public WebElement Button_IncreaseTip() { return findElement("com.bungii.customer:id/tip_increase_button",LocatorType.Id); }

    public WebElement Button_DecreaseTip() { return findElement("com.bungii.customer:id/tip_decrease_button",LocatorType.Id); }

    public WebElement BungiiTime() { return findElement("com.bungii.customer:id/summary_value_time",LocatorType.Id); }

    public WebElement Distance() { return findElement("com.bungii.customer:id/summary_value_distance",LocatorType.Id); }

    public WebElement FinalCost() { return findElement("com.bungii.customer:id/summary_value_distance",LocatorType.Id);}

    public WebElement Button_OK() { return findElement("com.bungii.customer:id/pickup_success_ok_button",LocatorType.Id); }
}
