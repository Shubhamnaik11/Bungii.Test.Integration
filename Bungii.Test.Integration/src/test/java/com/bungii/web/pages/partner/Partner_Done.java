package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Partner_Done extends PageBase {

    //Partner Password Field
    public WebElement Schedule_Done_Success_Header() { return findElement("//h1[@class='heading']", LocatorType.XPath); }

    //Track Deliveries
    public WebElement Track_Deliveries() { return findElement("track-deliveries",LocatorType.Id);}

}

