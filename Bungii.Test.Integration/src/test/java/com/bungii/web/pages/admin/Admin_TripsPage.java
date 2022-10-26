package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_TripsPage extends PageBase {

    public WebElement Menu_Trips () { return findElement("//ul[@id='side-menu']/li/p/span[contains(text(),'Deliveries')]", LocatorType.XPath); }

    public WebElement Menu_CompletedTrips () { return findElement("//span[contains(text(),'All Deliveries')]", LocatorType.XPath); }

    //public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement TextBox_Search() {return findElement("SearchCriteria", LocatorType.Id); }

    public WebElement DropDown_SearchForPeriod () {return findElement("//div[text()='The following deliveries from:']/select", LocatorType.XPath); }

    //public List<WebElement> Client_names () { return findElements("//td[9]", LocatorType.XPath); }
    public List<WebElement> Client_names () { return findElements("//td[9]", LocatorType.XPath); }

    public WebElement Button_Filter () { return findElement("//button[@class='btn-filter btn btn-primary']/span", LocatorType.XPath); }

    public WebElement Button_Apply () { return  findElement("//button[contains(text(),'APPLY')]", LocatorType.XPath); }

    public WebElement CheckBox_FilterPaymentUnsuccessful () { return findElement("Payment Unsuccessful", LocatorType.Id); }

    public WebElement CheckBox_FilterPaymentSuccessful () { return findElement("Payment Successful", LocatorType.Id); }

    public WebElement CheckBox_FilterCustomerCancelled () { return findElement("Customer Canceled", LocatorType.Id); }

    public WebElement CheckBox_FilterDriverCancelled () { return findElement("Driver Canceled", LocatorType.Id); }

    public WebElement CheckBox_FilterAdminCancelled () { return findElement("Admin Canceled", LocatorType.Id); }

    public WebElement CheckBox_FilterPartnerCancelled () { return findElement("Partner Canceled", LocatorType.Id); }

    public WebElement CheckBox_FilterPickupWithError () { return findElement("Pickup with Error", LocatorType.Id); }

    public WebElement CheckBox_FilterPriceEstimated () { return findElement("Price Estimated", LocatorType.Id); }

    public WebElement CheckBox_FilterDriversNotFound () { return findElement("No Driver(s) Found", LocatorType.Id); }

    public WebElement CheckBox_FilterDriverRemoved () { return findElement("Driver Removed", LocatorType.Id); }

    public WebElement CheckBox_FilterDriverNotArrived () { return findElement("Driver Not Arrived", LocatorType.Id); }

    public WebElement CheckBox_FilterPromoterPaymentPending () { return findElement("Promoter Payment Pending", LocatorType.Id); }

    public WebElement CheckBox_FilterSolo () { return findElement("Solo", LocatorType.Id); }

    public WebElement CheckBox_FilterDuo () { return findElement("Duo", LocatorType.Id); }

    public WebElement CheckBox_FilterOnDemand () { return findElement("On-Demand", LocatorType.Id); }

    public WebElement CheckBox_FilterScheduled () { return findElement("Scheduled", LocatorType.Id); }

    public WebElement Dropdown_SearchForPeriod () { return findElement("//div[text()='The following deliveries from:']/select", LocatorType.XPath); }

    public WebElement Text_AllTripIndicator () { return findElement("//tbody[@id=\"TripListsTBody\"]/tr/td[1]/label", LocatorType.XPath); }

    public WebElement RadioButton_SoloTrip () { return findElement("PickupDetails_TripType", LocatorType.Id); }

    public WebElement RadioButton_DuoTrip () { return findElement("tripTypeDuo", LocatorType.Id); }

    public WebElement CheckBox_FilterPending () { return findElement("chkStatus-3", LocatorType.Id); }

    public WebElement Text_NoDeliveriesFound () { return findElement("//div/h5", LocatorType.XPath); }

    public WebElement Label_ReviveCustomerDetail () { return findElement("//div[contains(.,'Customer :')]/b[2]", LocatorType.XPath); }

    public WebElement Label_RevivePartnerDetail () { return findElement("//span[contains(.,'Partner')]/b", LocatorType.XPath); }

    public WebElement Label_RevivePickupOriginDetail (boolean...ignoreException) { return findElement("//div[contains(.,'Pickup Origin')]", LocatorType.XPath,ignoreException); }

    public WebElement CheckBox_AssigningDrivers () { return findElement("chkStatus-4", LocatorType.Id); }

    public WebElement Text_AllFilterOptions (int number){ return findElement(String.format("//div[@id=\"divFilter\"]/div/label[%d]",number), LocatorType.XPath); }

    public WebElement Header_Partner () { return findElement("//th[text()='Customer']/following-sibling::th[1]", LocatorType.XPath); }

    public WebElement Link_ChangePaymentStatus () { return findElement("//td/div[@class='dropdown open']/ul/li/*[contains(text(),'Change Payment Status')]", LocatorType.XPath); }

    public WebElement Text_ChangePaymentStatusMessage () { return findElement("//div[@id=\"trip-status-change-confirm-modal\"]/div/div/div[2]/p", LocatorType.XPath); }

    public WebElement Text_CurrentStatus () { return findElement("trip-status-change-current-status", LocatorType.Id); }

    public WebElement Text_NewStatus () { return findElement("trip-status-change-new-status", LocatorType.Id); }

    public WebElement Button_ConfirmPaymentStatusChange () { return findElement("//div[@id=\"tblTrips\"]/div[6]/div/div/div[3]/button[2]", LocatorType.XPath); }

    public WebElement Button_CancelPaymentStatusChange () { return findElement("//div[@id=\"tblTrips\"]/div[6]/div/div/div[3]/button[1]", LocatorType.XPath); }

    public WebElement Text_ScheduledDate(){return findElement("//td[4]/a",LocatorType.XPath);}

    public WebElement Link_DeliveryDetails() {return findElement("//a[contains(text(),'Delivery Details')]",LocatorType.XPath);}

}
