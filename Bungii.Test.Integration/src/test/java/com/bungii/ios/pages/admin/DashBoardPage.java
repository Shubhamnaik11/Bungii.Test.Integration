package com.bungii.ios.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class DashBoardPage extends PageBase {

    public WebElement Button_Trips() {
        return findElement("adminmenu-trips", LocatorType.Id);
    }

    public WebElement Button_PromoCode() {
        return findElement("adminmenu-promocode", LocatorType.Id);
    }

    public WebElement Button_Marketing() {
        return findElement("adminmenu-marketing", LocatorType.Id);
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


}
