package com.bungii.android.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ScheduledTripsPage extends PageBase {
    public By Text_Success = By.id("SuccessMessage");//"modal fade loader", LocatorType.ClassName); }
   // public By Loader_Wrapper = By.className("modal fade loader");//"modal fade loader", LocatorType.ClassName); }
    public WebElement Loader_Wrapper() {
        return findElement("modal fade loader", LocatorType.ClassName);
    }
    public WebElement Text_Success() {
        return findElement("//p[@id='cancel-success-message']/i[last()]", LocatorType.XPath);

    }
    public WebElement TextBox_Phone() {
        return findElement("PhoneNo", LocatorType.Name);
    }
    public List<WebElement> Row_TripDetails() { return findElements("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')]", LocatorType.XPath); }

    public WebElement TableBody_TripDetails() {
        return findElement("TripListsTBody", LocatorType.Id);
    }

    public WebElement RadioBox_Cancel() {return findElement("//label[contains(@class,'customerCancel')]/span", LocatorType.XPath);}

    public WebElement RadioBox_Research() {return findElement("//label[contains(@class,'vertical-middle mb20 custom-input driverCancel')]/span", LocatorType.XPath); }

    public WebElement TextBox_CancelFee() {
        return findElement("txtCancellationFee", LocatorType.Id);
    }

    public WebElement TextBox_Comments() {
        return findElement("txtCustomerCancellationComments", LocatorType.Id);
    }

    public WebElement Button_Submit(boolean ...ignoreException) {
        return findElement("CustomerCancel", LocatorType.Name, ignoreException);
    }
    public WebElement Label_Drop_Off_Location () { return findElement("//p[contains(text(),'Drop Off Location:')]",LocatorType.XPath);}
    public WebElement Button_Edit_Drop_Off_Address () { return findElement("//img[@title='Edit drop off location']",LocatorType.XPath);}
    public WebElement Textbox_Drop_Off_Location () { return findElement("PickupDetails_DestinationAddress",LocatorType.Id);}
    public WebElement DropdownResult (String address) { return findElement(String.format("//div[@id='divPlacesResult']/div[contains(.,'%s')]",address),LocatorType.XPath);}
    public WebElement DropOff_Address() { return findElement("//label[@id='lblDestinationAddress']",LocatorType.XPath);}
    public WebElement Label_Pickup_Location () { return findElement("//p[contains(text(),'Pickup Location:')]",LocatorType.XPath);}
    public WebElement Button_Edit_Pickup_Address () { return findElement("//img[@title='Edit pickup location']",LocatorType.XPath);}
    public WebElement Textbox_Pickup_Location () { return findElement("PickupDetails_PickupOriginAddress",LocatorType.Id);}
    public WebElement DropdownPickupResult (String address) { return findElement(String.format("//div[@id='divPickupPlacesResult']/div[contains(.,'%s')]",address),LocatorType.XPath);}
    public WebElement Pickup_Address() { return findElement("//label[@id='lblPickupAddress']",LocatorType.XPath);}


    public void waitForLoadingToDisappear(){
        waitForLoadingToDisappear();
    }
    public WebElement Text_SearchCriteria(){return  findElement("SearchCriteria",LocatorType.Id);}
    public WebElement Label_Message(){return  findElement("//p[@id='cancel-success-message']/i[2]",LocatorType.XPath);}
    public WebElement Icon_Dropdown(){return  findElement("//div/img[@id='dLabel']",LocatorType.XPath);}
    public WebElement Option_Edit(){return  findElement("btnLiveEdit",LocatorType.Id);}

    public WebElement Button_Search(){return  findElement("btnSearch",LocatorType.Id);}
    public WebElement Button_Research() {return findElement("//*[contains(@id,'tripDriverDetails')]//button[2]", LocatorType.XPath); }
    // wait
    //public WebElement CheckBox_Driver1() {return findElement("//div[@class='tripDrivers row']//label[@class='custom-input checkboxDiv mt0 pull-left']/span", LocatorType.XPath); }
    public WebElement CheckBox_DriverByName(String Name) {return findElement(String.format("//div[@id='tripDriverDetails']/div[@class='driver-research row']/span[contains(text(),'%s')]/preceding-sibling::label/span",Name), LocatorType.XPath); }

    public WebElement CheckBox_Driver1() {return findElements("//div[@class='tripDrivers row']//label[@class='custom-input checkboxDiv mt0 pull-left']/span", LocatorType.XPath).get(0); }
    public WebElement CheckBox_Driver2() {return findElements("//div[@class='tripDrivers row']//label[@class='custom-input checkboxDiv mt0 pull-left']/span", LocatorType.XPath).get(1); }
    public WebElement Button_Remove() {return findElement("//*[contains(@id,'tripDriverDetails')]//button[1]", LocatorType.XPath); }

    public WebElement CheckBox_Driver1_Edit() {return findElement("//tr[1]/td/label/input", LocatorType.XPath); }
    public WebElement CheckBox_Driver2_Edit() {return findElement("//tr[2]/td/label/input", LocatorType.XPath); }
    public WebElement Button_Remove_Edit() {return findElement("btnRemoveDriver", LocatorType.Id); }
    public WebElement RadioBox_EditTrip() {return findElement("//label[contains(@class,'adminEditTrip')]/span", LocatorType.XPath);}
    public WebElement Text_EditTripType() {return findElement("//div[@class='CancelComments row']/p[2]", LocatorType.XPath);}
    public WebElement Calendar_EditTripDetailsScheduledDate() {return findElement("PickupDetails_ScheduledDate", LocatorType.Id);}
    public WebElement Calendar_NextDate() {return findElement("//td[@data-handler='selectDay'][1]/following-sibling::td[1]/a", LocatorType.XPath);}
    public WebElement Time_FirstAvailable(){return findElement("//ul[@class='ui-timepicker-list']/li[1]", LocatorType.XPath);}
    public WebElement icon_Close(){return findElement("//button[@class='close']", LocatorType.XPath);}

    public WebElement Time_EditTripDetailsTime(){return findElement("PickupDetails_ScheduledTime", LocatorType.Id);}
    public WebElement TextBox_DriverSearch() {return findElement("txtDriverSearch", LocatorType.Id);}
    public WebElement Button_VerifyDriver(){return findElement("//button[contains(text(),'VERIFY')]", LocatorType.XPath);}
    public WebElement Select_TestDriver(){return findElement("//div[@id='divDriversResult']/div[@class='pac-item'][1]", LocatorType.XPath);}

    public WebElement Text_EditTrpDetailsDriver1Name(){return findElement("//table[@id='editTripDrivers']/tbody/tr[1]/td/table/tbody/tr/td[3]", LocatorType.XPath);} //3 is correct index
    public WebElement Text_EditTrpDetailsDriver2Name(){return findElement("//table[@id='editTripDrivers']/tbody/tr[2]/td/table/tbody/tr/td[3]", LocatorType.XPath);} //3 is correct index

    public WebElement Text_EditTrpDetailsDriver1NamePrefilled(){return findElement("//table[@id='editTripDrivers']/tbody/tr[1]/td/table/tbody/tr/td[2]", LocatorType.XPath);} //2 is correct index for name for prefilled
    public WebElement Text_EditTrpDetailsDriver2NamePrefilled(){return findElement("//table[@id='editTripDrivers']/tbody/tr[2]/td/table/tbody/tr/td[2]", LocatorType.XPath);} //2 is correct index for name for preifilled


    public WebElement Text_Driver1Name(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tv_drivername_value']", LocatorType.XPath).get(0);}
    public WebElement Text_Driver2Name(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tv_drivername_value']", LocatorType.XPath).get(1);}
    public WebElement Text_VerifyChangesSavedMessage() {return findElement("//p[@id='verified-message']/i[2]", LocatorType.XPath);}
    public WebElement Button_SaveChanges(){return findElement("//button[@class='btn btn-primary ml15 saveTrip']", LocatorType.XPath);}
    public WebElement Text_SuccessMessage(){return findElement("//p[@id='success-message']/i[2]", LocatorType.XPath);}
    public WebElement Label_IconTextMessage(){return findElement("//table[@id='editTripDrivers']/tbody/tr/td/table/tbody/tr/td", LocatorType.XPath);}
    public WebElement Label_StaticText(){return findElement("//em", LocatorType.XPath);}

    public WebElement Label_ChangedScheduledTime(){return findElement("//div[@class='tripDrivers row']//p[contains(text(),'Schedule Time:')]/following-sibling::p[1]", LocatorType.XPath);}
    public WebElement Button_ClosePopUp(){return findElement("//button[@class='close']/span", LocatorType.XPath);}

    public WebElement Text_BungiiTime(){return findElements("//android.widget.RelativeLayout/android.widget.TextView", LocatorType.XPath).get(2);}
    public WebElement Text_ConflictMessageError() {return findElement("//p[@id='conflict-message']/strong/i[@id='verify-error']", LocatorType.XPath);}

    public WebElement Text_ScheduledBungiiTime(){return findElement("//div[@class='tripDrivers row']/p[@class='col-sm-7 col-md-8 col-lg-9 lblScheduledTime']", LocatorType.XPath);}
    public WebElement Text_DriverNames(){return findElement("//div[@id='tripDriverDetails']/div/span[1]", LocatorType.XPath);}

    public WebElement Select_CancellationReason() {return findElement("txtCancellationRemark", LocatorType.Id);}

    public WebElement Select_EditReason() {return findElement("ddEditDeliveryRemark",LocatorType.Id);}


    public WebElement Button_StopSearching() {return findElement("btnStopSearch",LocatorType.Id);}

    public WebElement Button_ConfirmStopSearching() {return findElement("btnConfirm",LocatorType.Id);}

    public WebElement Button_CloseConfirm() {return findElement("//div[@id='stop-search-success-modal']/div/div/div/button[text()='Close']",LocatorType.XPath);}

    public WebElement Text_BungiiStatus(){return findElement("//td[text()='Status']/following::td[1]/strong", LocatorType.XPath);}

    public WebElement Button_Price_Override() {return findElement("//td/a[@id='btnAdminOverride']",LocatorType.XPath);}
    public WebElement Text_Driver_Est_Earnings() {return findElement("//td[text()=' Driver Fixed Earnings - Pallet 1(1111 lbs)']/following::td[1]/strong",LocatorType.XPath);}
    public WebElement Textbox_Override_Driver_Cut() {return findElement("//div/input[@id='driverOneShare']",LocatorType.XPath);}
    public WebElement Dropdown_Reason_Override_Driver_Cut() { return findElement("//div/select[@id='driverEarningsReason']",LocatorType.XPath);}
    public WebElement Button_Save() { return findElement("saveAdminOverride",LocatorType.Id);}
    public WebElement Button_Success_Ok() { return findElement("//div[@class='modal-footer']/button[text()='Ok']",LocatorType.XPath);}

    public WebElement Button_Ok() { return findElement("//div[@id='btnOk']",LocatorType.XPath);}

    public WebElement Button_ReviveTrip() { return findElement("//a[@class='revive-trip-link']/img",LocatorType.XPath);}
    public WebElement Label_HeaderPopup() { return findElement("//p[text()='Are you sure you want to revive the trip?']", LocatorType.XPath); }
    public WebElement Label_PickupId() { return findElement("revive-pickup-id", LocatorType.Id); }
    public WebElement Label_PickupCustomer() { return findElement("revive-pickup-customer", LocatorType.Id); }
    public WebElement Button_Confirm() { return findElement("//button[text()='Confirm']", LocatorType.XPath); }
    public WebElement Textbox_CancellationFee () { return findElement("txtCancellationFee", LocatorType.Id); }
    public WebElement Textbox_CancellationComment () { return findElement("txtCustomerCancellationComments", LocatorType.Id); }
    public WebElement Dropdown_CancellationReason () { return findElement("txtCancellationRemark", LocatorType.Id); }
    public WebElement Label_CancelSuccessMessage () { return findElement("//p[@id='cancel-success-message']/i[2]", LocatorType.XPath); }
    public WebElement Button_ReviveTrip (boolean... IgnoreException) { return findElement("//tbody/tr/td[11]/a/img", LocatorType.XPath,IgnoreException); }
    public WebElement TextBox_Search() {return findElement("SearchCriteria", LocatorType.Id); }
    public WebElement Button_Cancel() { return findElement("//button[text()='Cancel']", LocatorType.XPath); }
    public WebElement Button_Submit () { return findElement("CustomerCancel", LocatorType.Name); }


}
