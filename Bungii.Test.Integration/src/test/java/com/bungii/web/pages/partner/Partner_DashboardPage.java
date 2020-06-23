package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Partner_DashboardPage extends PageBase {

    //Partner Password Field
    public WebElement Get_Estimate_Header() { return findElement("//h1[contains(text(),'Get Estimate')]", LocatorType.XPath); }

    //Logout button
    public WebElement Partner_LogOut(){ return findElement("//a[@class='header-btn logout-btn']", LocatorType.XPath); }
}
