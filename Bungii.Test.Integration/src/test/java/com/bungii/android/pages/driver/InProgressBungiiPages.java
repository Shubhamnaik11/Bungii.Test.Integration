package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;


public class InProgressBungiiPages extends PageBase {

    //------Page Titles--------------------------------------------------------------
    public WebElement Title_Status(boolean ... ignoreException) { return findElement("com.bungii.driver:id/toolbar_title", LocatorType.Id,ignoreException); }

    //------Cancel Bungii------------------------------------------------------------
    public WebElement Button_Cancel() { return findElement("com.bungii.driver:id/toolbar_button_cancel", LocatorType.Id); }

    public WebElement Button_Cancel_Yes() { return findElement("android:id/button1", LocatorType.Id); }

    public WebElement Button_Cancel_Cancel() { return findElement("android:id/button2", LocatorType.Id); }

    //------Slider-------------------------------------------------------------------
    public WebElement Slider() { return findElement("com.bungii.driver:id/pickup_details_slider_view", LocatorType.Id); }


    public WebElement Button_More() { return findElement("com.bungii.driver:id/pickup_details_ellipsise", LocatorType.Id); }


    //More incas of duo
    public WebElement Button_DuoMore(boolean...ignoreException) { return findElement("com.bungii.driver:id/pickup_details_contact_options", LocatorType.Id,ignoreException); }

    public WebElement Button_DuoMore1() { return findElements("com.bungii.driver:id/pickup_details_contact_options", LocatorType.Id).get(0); }
    public WebElement Button_DuoMore2() { return findElements("com.bungii.driver:id/pickup_details_contact_options", LocatorType.Id).get(1); }
    public WebElement Button_DuoCustomer_SMS(boolean...ignoreException) { return findElement("com.bungii.driver:id/caller_communication_mode_sms_textview", LocatorType.Id,ignoreException); }

    public WebElement Button_DuoCustomer_Call(boolean...ignoreException) { return findElement("com.bungii.driver:id/caller_communication_mode_phone_call_textview", LocatorType.Id,ignoreException); }

    public WebElement Button_DuoCustomer_ViewItem(boolean...ignoreException) { return findElement("com.bungii.driver:id/caller_communication_mode_view_pickup_items_images_textview", LocatorType.Id,ignoreException); }

    public WebElement Button_DuoCustomer_CallSupport(boolean...ignoreException) { return findElement("com.bungii.driver:id/caller_communication_mode_contact_driver_support_textview", LocatorType.Id,ignoreException); }
    public WebElement Button_DuoCancel() { return findElement("com.bungii.driver:id/caller_cancel", LocatorType.Id); }



    //------SMS----------------------------------------------------------------------
   // public WebElement Button_Customer_SMS() { return findElement("com.bungii.driver:id/pickup_details_sms_button", LocatorType.Id); }
    public WebElement Button_Customer_SMS() { return findElement("//*[@resource-id='android:id/select_dialog_listview']/android.widget.TextView[3]", LocatorType.XPath); }

    //------Call---------------------------------------------------------------------
  //  public WebElement Button_Customer_Call() { return findElement("com.bungii.driver:id/pickup_details_call_button", LocatorType.Id); }
    public WebElement Button_Customer_Call() { return findElement("//*[@resource-id='android:id/select_dialog_listview']/android.widget.TextView[2]", LocatorType.XPath); }

    public WebElement Button_Customer_ViewItem() { return findElement("//*[@resource-id='android:id/select_dialog_listview']/android.widget.TextView[1]", LocatorType.XPath); }

    public WebElement Button_Customer_CallSupport() { return findElement("//*[@resource-id='android:id/select_dialog_listview']/android.widget.TextView[4]", LocatorType.XPath); }

    public WebElement Button_CancelImage() { return findElement("com.bungii.driver:id/activity_view_all_images_iv_cancel", LocatorType.Id); }
    public WebElement Image_BungiiItem() { return findElement("com.bungii.driver:id/viewpager_sliding_item_imageview_item", LocatorType.Id); }





    //------Bungii Statuses----------------------------------------------------------
    public WebElement BungiiStatus_Enroute(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_status_1", LocatorType.Id,ignoreException); }

    public WebElement BungiiStatus_Arrived(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_status_2", LocatorType.Id,ignoreException); }

    public WebElement BungiiStatus_LoadingItem(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_status_3", LocatorType.Id,ignoreException); }

    public WebElement BungiiStatus_DrivingToDropOff(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_status_4", LocatorType.Id,ignoreException); }

    public WebElement BungiiStatus_UnloadingItem(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_status_5", LocatorType.Id,ignoreException); }

    //------Location Details---------------------------------------------------------
    public WebElement Bungii_LocationTitle() { return findElement("com.bungii.driver:id/pickup_details_address_name", LocatorType.Id); }

    public WebElement Bungii_Location() { return findElement("com.bungii.driver:id/pickup_details_address_value", LocatorType.Id); }

    public WebElement Bungii_ETA(boolean ... ignoreException) { return findElement("com.bungii.driver:id/pickup_details_estimate", LocatorType.Id,ignoreException); }

    //------Driver Details-----------------------------------------------------------
    public WebElement Bungii_Customer_Title() { return findElement("com.bungii.driver:id/pickup_details_caller_title", LocatorType.Id); }

    public WebElement Bungii_Customer_Name() { return findElement("com.bungii.driver:id/pickup_details_caller_name", LocatorType.Id); }
    public WebElement Alert_Message(){return findElement("android:id/message",LocatorType.Id);}
    public WebElement Alert_Accept(){return  findElement("android:id/button1",LocatorType.Id);}

    //DUO
    public WebElement Text_DuoCustomer_Title() { return findElements("com.bungii.driver:id/pickup_details_person_title", LocatorType.Id).get(0); }
    public WebElement Text_DuoCustomer_Name() { return findElements("com.bungii.driver:id/pickup_details_person_name", LocatorType.Id).get(0); }
    public WebElement Text_DuoDriver_Title() { return findElements("com.bungii.driver:id/pickup_details_person_title", LocatorType.Id).get(1); }
    public WebElement Text_DuoDriver_Name() { return findElements("com.bungii.driver:id/pickup_details_person_name", LocatorType.Id).get(1); }

}
