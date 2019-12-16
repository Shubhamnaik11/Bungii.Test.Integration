package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class EarningsPage extends PageBase {

    public WebElement Link_ItemisedEarnings() { return findElement("//android.view.View[@text='Click here to view itemized earnings Itemized Earnings']", LocatorType.XPath); }

    public WebElement Text_HistoryDataTotalEarnings() { return findElement("//*[@resource-id='tblDriverTrips']/android.view.View[6]", LocatorType.XPath);}

}
