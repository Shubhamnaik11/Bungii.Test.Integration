package com.bungii.android.pages.bungii;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class WantDollar5Page extends PageBase {

    public WebElement Button_Take5 () { return findElement("com.bungii.customer:id/postTripShareAcceptButton", LocatorType.Id); }

    public WebElement Button_NoFreeMoney (boolean ... ignoreException) { return findElement("com.bungii.customer:id/postTripShareNoFreeMoney", LocatorType.Id,ignoreException); }
}
