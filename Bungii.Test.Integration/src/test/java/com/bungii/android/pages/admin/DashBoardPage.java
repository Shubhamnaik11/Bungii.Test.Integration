package com.bungii.android.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class DashBoardPage extends PageBase {

    public WebElement Button_Trips() {
        return findElement("//div/ul[@id=\"side-menu\"]/li/p/span[text()=\"Deliveries\"][1]", LocatorType.XPath);
    }

    public WebElement Button_PromoCode() {
        return findElement("adminmenu-promocodes-menu", LocatorType.Id);
    }

    public WebElement Button_Marketing() {
        return findElement("adminmenu-marketing", LocatorType.Id);
    }
    public WebElement Link_StandardCodes() {
        return findElement("adminmenu-promocode", LocatorType.Id);
    }

    public WebElement Button_ReferralSource() {
        return findElement("adminmenu-referralsource", LocatorType.Id);
    }

    public WebElement Button_ScheduledTrips() {
        return findElement("adminmenu-scheduledtrips", LocatorType.Id);
    }

    public WebElement Button_LiveTrips() {
        return findElement("adminmenu-livetrips", LocatorType.Id);
    }

    public  WebElement Button_Customers() { return findElement("adminmenu-customers", LocatorType.Id);}
    public WebElement List_Geofence() {return findElement("dropdownMenuButton" , LocatorType.Id);}
    public WebElement TextBox_SearchGeofence() {return findElement("myInput" , LocatorType.Id);}
    public WebElement Button_ApplyGeofence() {return findElement("btnApply" , LocatorType.Id);}
    public WebElement Checkbox_Geofence(String geofence) {return findElement(String.format("//span[contains(.,'%s')]/preceding-sibling::span/label/input",geofence) , LocatorType.XPath);}
    public WebElement Message_NoCustomerFound() { return findElement("//h5[contains(text(),'No Customers found.')]",LocatorType.XPath);}
    public WebElement Message_NoDeliveriesFound() { return findElement("//h5[contains(text(),'No Deliveries found.')]",LocatorType.XPath);}
    public WebElement Text_AdminName(){return  findElement("//div[2]/p[1]/strong",LocatorType.XPath);}

}
