package com.bungii.android.pages.otherApps;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;

public class OtherAppsPage extends PageBase {

    //------SMS---------------------------------------------------------------------------------------
    public WebElement SMS_Samsung_RecipientNo() { return findElement("com.android.mms:id/recipients_editor_to", LocatorType.Id); }

    public WebElement SMS_Moto_RecipientNo() { return findElement("com.android.mms:id/recipients_editor", LocatorType.Id); }

    //------Call--------------------------------------------------------------------------------------
    public WebElement Call_Samsung_Number() { return findElement("com.android.contacts:id/digits", LocatorType.Id); }

    public WebElement Call_Moto_Number() { return findElement("com.android.dialer:id/digits", LocatorType.Id); }



}
