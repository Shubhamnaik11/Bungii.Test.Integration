package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

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
   public WebElement Text_DeliveryDate(){return findElements("//android.widget.TextView[@resource-id='com.bungii.customer:id/item_my_bungii_tv_date']", LocatorType.XPath).get(0);}

    public WebElement Text_TripCost(){return findElement("com.bungii.customer:id/completed_bungii_tv_estimated_cost_label", LocatorType.Id);}
    public WebElement Text_DriverNotRated(){return findElement("com.bungii.customer:id/driver_details_row_driver_not_rated_info", LocatorType.Id);}

    public WebElement Text_Driver1NameTrip1(){return findElements("//*[@resource-id='com.bungii.customer:id/item_my_bungii_tv_driver2name']", LocatorType.XPath).get(2);}
    public WebElement Text_Driver1NameTrip2(){return findElements("//*[@resource-id='com.bungii.customer:id/item_my_bungii_tv_driver2name']", LocatorType.XPath).get(1);}
    public WebElement Text_Driver1NameTrip3(){return findElements("//*[@resource-id='com.bungii.customer:id/item_my_bungii_tv_driver2name']", LocatorType.XPath).get(0);}
    public WebElement Text_Driver2NameTrip3(){return findElements("//*[@resource-id='com.bungii.customer:id/item_my_bungii_tv_driver2name']", LocatorType.XPath).get(0);}

    public WebElement Text_TotalEarnings(){return findElement("com.bungii.driver:id/appCompatTextView6", LocatorType.Id);}
    public WebElement Text_TotalTips(){return findElement("com.bungii.driver:id/appCompatTextView6", LocatorType.Id);}
    public WebElement Button_ItemizedEarnings(){return findElement("//android.widget.RelativeLayout/android.widget.LinearLayout/android.view.ViewGroup[4]/android.widget.Button", LocatorType.XPath);}
    public WebElement Text_ItemizedEarnings(){return findElement("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[2]", LocatorType.XPath);}
    public WebElement Icon_DriverEarnings(){return findElement("//tbody[@id='NewApplicantsTBody']/tr/td/a/img[@title='Driver Earnings']", LocatorType.XPath);}
    public WebElement Link_ViewTrips(){return findElement("//div/a[text()='View']", LocatorType.XPath);}
    public WebElement Text_DriverEarnings(){return findElement("//div[@class='info-box']/h1[@class='text-info']", LocatorType.XPath);}
    public WebElement Button_Back(){return findElement("//android.widget.ImageButton[contains(@content-desc,\"Navigate up\")]", LocatorType.XPath);}

}
