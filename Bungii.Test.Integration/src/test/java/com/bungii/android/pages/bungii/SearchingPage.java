package com.bungii.android.pages.bungii;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SearchingPage extends PageBase {

    public WebElement Link_CancelSearch () { return findElement("com.bungii.customer:id/toolbar_button_cancel", LocatorType.Id); }

    public WebElement Button_CancelConfirm () { return findElement("android:id/button1", LocatorType.Id); }          //By.XPath("//android.widget.Button[@text='YES']")

    public WebElement Button_CloseCancel () { return findElement("android:id/button2", LocatorType.Id); }

    public WebElement PageTitle_Searching () { return findElement("//android.widget.TextView[@text='SEARCHING…']", LocatorType.XPath); }

    public WebElement Loader () { return findElement("com.bungii.customer:id/searching_view_progressbar", LocatorType.Id); }

    public WebElement Text_MsgSearching () { return findElement("com.bungii.customer:id/searching_view_subtitle", LocatorType.Id); }

    public WebElement ProgressBar () { return findElement("com.bungii.customer:id/searching_view_horizontal_progressbar", LocatorType.Id); }

    public WebElement PageTitle_DriverNotAvailable () { return findElement("//android.widget.TextView[@text='DRIVER NOT AVAILABLE']", LocatorType.XPath); }
}
