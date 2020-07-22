package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Partner_Done extends PageBase {

    //Trip Schedule Done text
    public WebElement Text_Schedule_Done_Success_Header() { return findElement("//h1[@class='heading']", LocatorType.XPath); }

    //Setting dropdown
    public WebElement Dropdown_Setting() { return findElement("//div[@class='dropdown-menu-right dropdown']",LocatorType.XPath);}

    //Track Deliveries button
    public WebElement Button_Track_Deliveries() { return findElement("track-deliveries",LocatorType.Id);}

}

