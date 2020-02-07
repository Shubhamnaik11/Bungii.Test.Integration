package com.bungii.android.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class LiveTripsPage extends PageBase {
    public WebElement Text_SearchCriteria(){return  findElement("SearchCriteria", PageBase.LocatorType.Id);}
    public WebElement Text_SearchPeroid(){return  findElement("SearchForPeriod", PageBase.LocatorType.Id);}

    public WebElement Button_Search(){return  findElement("btnSearch", PageBase.LocatorType.Id);}
    public WebElement Button_StartDateSort(){return  findElement("span-StartDate", PageBase.LocatorType.Id);}
    public WebElement Button_ManuallyEndBungii(boolean ...ignoreException){return  findElement("btnEndPickup", PageBase.LocatorType.Id,ignoreException);}
    public WebElement Button_RowOne(){return  findElement("clickable-row", LocatorType.ClassName);}
    public WebElement Text_TripStatus(){return  findElement("//td[text()='Status']/following-sibling::td", LocatorType.XPath);}
    public WebElement Text_Code(){return  findElement("//td[text()='Code']/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_CodeType(){return  findElement("//td[text()='Code Type']/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_CodeValue(){return  findElement("//td[text()='Code Value']/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_PromoCode(){return  findElement("//td[text()='Promo Value']/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_EstimatedCharge(){return  findElement("//td[text()='Estimated Charge']/following-sibling::td/strong", LocatorType.XPath);}
    public WebElement Text_TripPayment(){return  findElement("//td[text()='Trip Payment']/following-sibling::td/strong", LocatorType.XPath);}
}
