package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_DashboardPage extends PageBase {

//Header - Dashboard
    public WebElement Header_Dashboard () { return findElement("//input[@id='DriverCurrentStatus']/following-sibling::h4", LocatorType.XPath); }
    public WebElement SideNavigationSetting () { return findElement("//ul[@class='sidebar-nav']/li[3]", LocatorType.XPath); }
    public WebElement SideNavigationGeneral () { return findElement("//ul[@class='sidebar-nav']/li[1]", LocatorType.XPath); }

    public WebElement Link_Logout(){return  findElement("//a[.='log out']", LocatorType.XPath);}

    public WebElement Link_DriverDetails(){return  findElement("driver-details", LocatorType.Id);}

    public WebElement Link_PickupInfo(){return  findElement("pickup-info", LocatorType.Id);}

    public WebElement Button_Update(){return  findElement("btnUpdate", LocatorType.Id);}

    public WebElement Button_Submit(){return  findElement("btnsend", LocatorType.Id);}

    public WebElement Button_Yes(){return  findElement("btnsendagree", LocatorType.Id);}

    public WebElement Link_RemoveFile1(){return  findElement("//div[@id='dropzone1']/div[2]/a[text()='Remove file']", LocatorType.XPath);}
    public WebElement Link_RemoveFile2(){return  findElement("//div[@id='dropzone1']/div[3]/a[text()='Remove file']]", LocatorType.XPath);}
    public WebElement Link_RemoveFile3(){return  findElement("//div[@id='dropzone1']/div[4]/a[text()='Remove file']", LocatorType.XPath);}

    public WebElement TextBox_DOB(){return  findElement("DateOfBirth", LocatorType.Id);}
}
