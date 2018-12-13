package com.bungii.android.pages.bungii;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;

public class BungiiProgressPage extends PageBase {

    //------Page Titles--------------------------------------------------------------
    public WebElement PageTitle () { return findElement("com.bungii.customer:id/toolbar_title",LocatorType.Id); }

    //------Bungii Statuses----------------------------------------------------------
    public WebElement BungiiStatus_Enroute () { return findElement("com.bungii.customer:id/pickup_details_status_1",LocatorType.Id); }

    public WebElement BungiiStatus_Arrived () { return findElement("com.bungii.customer:id/pickup_details_status_2",LocatorType.Id); }

    public WebElement BungiiStatus_LoadingItem () { return findElement("com.bungii.customer:id/pickup_details_status_2",LocatorType.Id); }

    public WebElement BungiiStatus_DrivingToDropOff () { return findElement("com.bungii.customer:id/pickup_details_status_4",LocatorType.Id); }

    public WebElement BungiiStatus_UnloadingItem () { return findElement("com.bungii.customer:id/pickup_details_status_5",LocatorType.Id); }

    //------Location Details---------------------------------------------------------
    public WebElement Bungii_LocationTitle () { return findElement("com.bungii.customer:id/pickup_details_address_name",LocatorType.Id); }

    public WebElement Bungii_Location () { return findElement("com.bungii.customer:id/pickup_details_address_value",LocatorType.Id); }

    public WebElement Bungii_ETA () { return findElement("com.bungii.customer:id/pickup_details_estimate",LocatorType.Id); }

    //------Driver Details-----------------------------------------------------------
    public WebElement Bungii_Driver_Image () { return findElement("com.bungii.customer:id/pickup_details_caller_image",LocatorType.Id); }

    public WebElement Bungii_Driver_Title () { return findElement("com.bungii.customer:id/pickup_details_caller_title",LocatorType.Id); }

    public WebElement Bungii_Driver_Name () { return findElement("com.bungii.customer:id/pickup_details_caller_name",LocatorType.Id); }

    public WebElement Bungii_Driver_RatingBar () { return findElement("com.bungii.customer:id/ratingbarPickupDetails",LocatorType.Id); }

    public WebElement Button_Bungii_Driver_SMS () { return findElement("com.bungii.customer:id/pickup_details_sms_button",LocatorType.Id); }

    public WebElement Button_Bungii_Driver_Call () { return findElement("com.bungii.customer:id/pickup_details_call_button",LocatorType.Id); }
}
