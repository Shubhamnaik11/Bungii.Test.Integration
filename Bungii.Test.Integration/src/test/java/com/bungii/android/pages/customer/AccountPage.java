package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class AccountPage extends PageBase  {
    public WebElement Header_AccountPage(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='ACCOUNT']", LocatorType.XPath,ignoreException); }

    public WebElement Header_AccountInfoPage(boolean...ignoreException) { return findElement("//android.widget.TextView[@text='ACCOUNT INFO']",LocatorType.XPath);}

    public WebElement Button_Cust_Navigate_Up(boolean...ignoreException) { return findElement("//android.widget.ImageButton[contains(@content-desc,'Navigate up')]",LocatorType.XPath,ignoreException);}

    public WebElement Account_Name() { return findElement("//android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/androidx.appcompat.widget.LinearLayoutCompat[1]/android.widget.TextView[2]", LocatorType.XPath); }

    public WebElement Account_Phone() { return findElement("//android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/androidx.appcompat.widget.LinearLayoutCompat[2]/android.widget.TextView[2]", LocatorType.XPath); }

    public WebElement Account_Email() { return findElement("//android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/androidx.appcompat.widget.LinearLayoutCompat[3]/android.widget.TextView[2]", LocatorType.XPath); }
}
