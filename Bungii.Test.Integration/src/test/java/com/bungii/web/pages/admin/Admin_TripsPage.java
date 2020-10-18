package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_TripsPage extends PageBase {

    public WebElement Menu_Trips () { return findElement("adminmenu-trips", LocatorType.Id); }

    public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement TextBox_Search() {return findElement("SearchCriteria", LocatorType.Id); }

    public WebElement DropDown_SearchForPeriod () {return findElement("SearchForPeriod", LocatorType.Id); }

    //public List<WebElement> Client_names () { return findElements("//td[9]", LocatorType.XPath); }
    public List<WebElement> Client_names () { return findElements("//td[10]", LocatorType.XPath); }

    public WebElement Button_Filter () { return findElement("btnFilter", LocatorType.Id); }

    public WebElement Button_Apply () { return  findElement("applyFilter", LocatorType.Id); }

    public WebElement CheckBox_FilterPaymentUnsuccessful () { return findElement("chkStatus-10", LocatorType.Id); }

    public WebElement CheckBox_FilterPaymentSuccessful () { return findElement("chkStatus-11", LocatorType.Id); }

    public WebElement CheckBox_FilterCustomerCancelled () { return findElement("chkStatus-64", LocatorType.Id); }

    public WebElement CheckBox_FilterDriverCancelled () { return findElement("chkStatus-66", LocatorType.Id); }

    public WebElement CheckBox_FilterAdminCancelled () { return findElement("chkStatus-70", LocatorType.Id); }

    public WebElement CheckBox_FilterPartnerCancelled () { return findElement("chkStatus-72", LocatorType.Id); }

    public WebElement CheckBox_FilterPickupWithError () { return findElement("chkStatus-PickupwithError", LocatorType.Id); }

    public WebElement CheckBox_FilterPriceEstimated () { return findElement("chkStatus-2", LocatorType.Id); }

    public WebElement CheckBox_FilterDriversNotFound () { return findElement("chkStatus-60", LocatorType.Id); }

    public WebElement CheckBox_FilterDriverRemoved () { return findElement("chkStatus-71", LocatorType.Id); }

    public WebElement CheckBox_FilterDriverNotArrived () { return findElement("chkStatus-63", LocatorType.Id); }

    public WebElement CheckBox_FilterPromoterPaymentPending () { return findElement("chkStatus-14", LocatorType.Id); }

    public WebElement CheckBox_FilterSolo () { return findElement("chkPickupType-1", LocatorType.Id); }

    public WebElement CheckBox_FilterDuo () { return findElement("chkPickupType-2", LocatorType.Id); }

    public WebElement CheckBox_FilterOnDemand () { return findElement("chkPickupCategory-1", LocatorType.Id); }

    public WebElement CheckBox_FilterScheduled () { return findElement("chkPickupCategory-2", LocatorType.Id); }

    public WebElement Dropdown_SearchForPeriod () { return findElement("SearchForPeriod", LocatorType.Name); }
}
