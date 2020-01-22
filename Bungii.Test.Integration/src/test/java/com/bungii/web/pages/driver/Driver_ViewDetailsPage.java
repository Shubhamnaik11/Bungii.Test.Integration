package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_ViewDetailsPage extends PageBase {

    public WebElement Label_Header_MyStats () {return findElement("//div[@class='panel panel-default getDriveApp my-stats']/div/h4[text()='My Stats']", LocatorType.XPath);}

    public WebElement Label_Statistics (String xpath) {return  findElement(xpath, LocatorType.XPath);}

    public  WebElement Label_Statistics_Total_Earnings_Per_Year () {return findElement("//h1[contains(text(),'Total Earnings per year')]/parent::div/div/div/h2", LocatorType.XPath); }

    public WebElement Label_TotalTripsCount () {return findElement("//p[contains(text(),'Total trips')]/following-sibling::h3", LocatorType.XPath);}

    public WebElement Label_TotalTripsCount (String xpath) {return findElement(xpath, LocatorType.XPath);}

}
