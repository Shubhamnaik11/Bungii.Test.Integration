package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class MyBungiisPage extends PageBase {
    public WebElement Select_PastTrip1(){return findElements("//*[@resource-id='com.bungii.customer:id/item_my_bungii_iv_arrow']", LocatorType.XPath).get(0);}
    public WebElement Select_PastTrip2(){return findElements("//*[@resource-id='com.bungii.customer:id/item_my_bungii_iv_arrow']", LocatorType.XPath).get(1);}
    public WebElement Select_PastTrip3(){return findElements("//*[@resource-id='com.bungii.customer:id/item_my_bungii_iv_arrow']", LocatorType.XPath).get(2);}
    public WebElement Text_MyNote(){return findElement("//*[@resource-id='com.bungii.customer:id/completed_bungii_details_recyclerview_images']/following::android.widget.TextView[2]", LocatorType.XPath);}
    public WebElement Button_TipAndRate(){return findElement("com.bungii.customer:id/driver_details_row_tip_driver_action", LocatorType.Id);}

    public WebElement Text_FirstDriverName(){return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tip_drivername']", LocatorType.XPath).get(0);}
    public WebElement Text_SecondDriverName(){return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tip_drivername']", LocatorType.XPath).get(1);}
    public WebElement DropDown_ShowAddress(){return findElement("com.bungii.customer:id/completed_bungii_details_iv_map_state_icon", LocatorType.Id);}
    public WebElement DropDown_HideAddress(){return findElement("com.bungii.customer:id/completed_bungii_details_iv_map_state_icon", LocatorType.Id);}

    public WebElement Text_PickUp_Location1(){return findElements("//*[@resource-id='com.bungii.customer:id/completed_bungii_iv_pickup_icon']/following::android.widget.LinearLayout/android.widget.TextView", LocatorType.XPath).get(0);}
    public WebElement Text_PickUp_Location2(){return findElements("//*[@resource-id='com.bungii.customer:id/completed_bungii_iv_pickup_icon']/following::android.widget.LinearLayout/android.widget.TextView", LocatorType.XPath).get(1);}
    public WebElement Text_DropOff_Location1(){return findElements("//*[@resource-id='com.bungii.customer:id/completed_bungii_iv_pickup_icon']/following::android.widget.LinearLayout/android.widget.TextView", LocatorType.XPath).get(2);}
    public WebElement Text_DropOff_Location2(){return findElements("//*[@resource-id='com.bungii.customer:id/completed_bungii_iv_pickup_icon']/following::android.widget.LinearLayout/android.widget.TextView", LocatorType.XPath).get(3);}

    public WebElement Text_TripScheduledDate(){return findElement("com.bungii.customer:id/completed_bungii_tv_scheduled_date", LocatorType.Id);}
    public WebElement Text_TripCost(){return findElement("com.bungii.customer:id/completed_bungii_tv_estimated_cost_label", LocatorType.Id);}
    public WebElement Text_DriverNotRated(){return findElement("com.bungii.customer:id/driver_details_row_driver_not_rated_info", LocatorType.Id);}

    public WebElement Text_Driver1NameTrip1(){return findElements("//*[@resource-id='com.bungii.customer:id/item_my_bungii_tv_driver2name']", LocatorType.XPath).get(2);}
    public WebElement Text_Driver1NameTrip2(){return findElements("//*[@resource-id='com.bungii.customer:id/item_my_bungii_tv_driver2name']", LocatorType.XPath).get(1);}
    public WebElement Text_Driver1NameTrip3(){return findElements("//*[@resource-id='com.bungii.customer:id/item_my_bungii_tv_driver2name']", LocatorType.XPath).get(0);}
    public WebElement Text_Driver2NameTrip3(){return findElements("//*[@resource-id='com.bungii.customer:id/item_my_bungii_tv_driver2name']", LocatorType.XPath).get(0);}

}
