package com.bungii.android.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class DashBoardPage extends PageBase {

    public WebElement Button_Trips() {
        return findElement("adminmenu-trips", LocatorType.Id);
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
    public WebElement Checbox_Geofence(String geofence) {return findElement(String.format("//span[text()='%s']/preceding-sibling::span/label/input",geofence) , LocatorType.Id);}

}
