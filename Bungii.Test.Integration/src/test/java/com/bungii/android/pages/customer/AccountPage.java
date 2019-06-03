package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class AccountPage extends PageBase  {
    public WebElement Header_AccountPage(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='ACCOUNT']", LocatorType.XPath,ignoreException); }

    public WebElement Account_Name() { return findElement("com.bungii.customer:id/account_info_textview_name", LocatorType.Id); }

    public WebElement Account_Phone() { return findElement("//android.widget.LinearLayout[@resource-id='com.bungii.customer:id/account_info_layout_phone']/android.widget.TextView[2]", LocatorType.XPath); }

    public WebElement Account_Email() { return findElement("com.bungii.customer:id/account_info_textview_email", LocatorType.Id); }
}
