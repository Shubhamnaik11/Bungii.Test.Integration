package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class AccountsPage extends PageBase{
    public WebElement Header_AccountPage(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='ACCOUNT']", PageBase.LocatorType.XPath,ignoreException); }

    public WebElement Text_Name() { return findElement("com.bungii.driver:id/account_info_textview_name", PageBase.LocatorType.Id); }

    public WebElement Text_Phone() { return findElement("//android.widget.LinearLayout[@resource-id='com.bungii.driver:id/account_info_layout_phone']/android.widget.TextView[2]", PageBase.LocatorType.XPath); }

    public WebElement Text_Email() { return findElement("com.bungii.driver:id/account_info_textview_email", PageBase.LocatorType.Id); }
}
