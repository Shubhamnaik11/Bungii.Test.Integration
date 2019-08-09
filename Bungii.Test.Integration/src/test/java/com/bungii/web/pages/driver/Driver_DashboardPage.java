package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_DashboardPage extends PageBase {

//Header - Dashboard
    public WebElement Header_Dashboard () { return findElement("//input[@id='DriverCurrentStatus']/following-sibling::h4", LocatorType.XPath); }

    public WebElement Link_Logout(){return  findElement("//a[.='log out']", LocatorType.XPath);}
}
