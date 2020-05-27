package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SetPickupTimePage extends PageBase {

    public WebElement Link_CancelOnDemandBungii() {return findElement("com.bungii.customer:id/toolbar_button_cancel", LocatorType.Id);}

    public WebElement Text_SetPickupTimeTitle(){return findElement("com.bungii.customer:id/toolbar_title", LocatorType.Id);}

    public WebElement Text_DriversBusyMessage(){return findElement("com.bungii.customer:id/searching_view_driver_unavailable_subtitle", LocatorType.Id);}

    public WebElement Icon_PickupTimeInfo(){return findElement("com.bungii.customer:id/activity_schedule_bungii_iv_info", LocatorType.Id);}

    public WebElement Icon_PickupTimeInfoMessage(){return findElement("android:id/message", LocatorType.Id);}

    public WebElement Button_Ok(){return findElement("android:id/button1", LocatorType.Id);}

    public WebElement Text_DateTime(){return findElement("com.bungii.customer:id/date_time_picker_textview_selectedtime", LocatorType.Id);}

    public WebElement Button_ScheduleBungii(){return findElement("com.bungii.customer:id/fragment_pickup_searching_driver_btn_schedule", LocatorType.Id);}

    public WebElement Text_MessageOnTimeSelector(){return findElement("com.bungii.customer:id/layout_timepicker_tv_customerinfo", LocatorType.Id);}

    public WebElement Text_SelectHours(){return findElements("//*[@resource-id='android:id/numberpicker_input']", LocatorType.XPath).get(0);}

    public WebElement Text_SelectMinutes(){return findElements("//*[@resource-id='android:id/numberpicker_input']", LocatorType.XPath).get(1);}

    public WebElement Button_TimePickerOK(){return findElement("com.bungii.customer:id/timepicker_okay", LocatorType.Id);}

    public WebElement RadioButton_INeededItRightAway() {return findElements("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_recycler_view']/android.widget.LinearLayout", LocatorType.XPath).get(0);}

    public WebElement RadioButton_IFoundAnAlternative() {return findElements("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_recycler_view']/android.widget.LinearLayout", LocatorType.XPath).get(1);}

    public WebElement RadioButton_IDontNeedItAnymore() {return findElements("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_recycler_view']/android.widget.LinearLayout", LocatorType.XPath).get(2);}

    public WebElement RadioButton_Others() {return findElements("//*[@resource-id='com.bungii.customer:id/alert_cancellation_reason_recycler_view']/android.widget.LinearLayout", LocatorType.XPath).get(3);}

    public WebElement Button_SubmitCancellationReason(){return findElement("com.bungii.customer:id/alert_cancellation_reason_tv_list_submit", LocatorType.Id);}

    public WebElement Button_GoBack(){return findElement("com.bungii.customer:id/alert_cancellation_reason_tv_list_try_again", LocatorType.Id);}


}
