package com.bungii.ios.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class LiveTripsPage extends PageBase{
    public WebElement Text_SearchCriteria(){return  findElement("SearchCriteria", PageBase.LocatorType.Id);}

    public WebElement Button_Search(){return  findElement("btnSearch", PageBase.LocatorType.Id);}
    public WebElement Button_StartDateSort(){return  findElement("span-StartDate", PageBase.LocatorType.Id);}
    public WebElement Button_ManuallyEndBungii(boolean ...ignoreException){return  findElement("btnEndPickup", PageBase.LocatorType.Id,ignoreException);}
    public WebElement Button_RowOne(){return  findElement("clickable-row", LocatorType.ClassName);}
    public WebElement Text_TripStatus(){return  findElement("//td[text()='Status']/following-sibling::td", LocatorType.XPath);}
}
