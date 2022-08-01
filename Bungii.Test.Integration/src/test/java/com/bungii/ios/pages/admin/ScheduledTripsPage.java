package com.bungii.ios.pages.admin;

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
        return       findElement("//p[@id='cancel-success-message']/i[last()]", LocatorType.XPath);
    }
    public WebElement TextBox_Phone() {
        return findElement("PhoneNo", LocatorType.Name);
    }
    public List<WebElement> Row_TripDetails() { return findElements("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')]", LocatorType.XPath); }

    public WebElement TableBody_TripDetails() {
        return findElement("TripListsTBody", LocatorType.Id);
    }
    public WebElement Dropdown_CancellationReason () { return findElement("txtCancellationRemark", LocatorType.Id); }

    public WebElement RadioBox_Cancel() {return findElement("//label[contains(@class,'customerCancel')]/input", LocatorType.XPath);}

    public WebElement RadioBox_EditTrip() {return findElement("//label[contains(@class,'adminEditTrip')]/span", LocatorType.XPath);}

    public WebElement RadioBox_Research() {return findElement("//label[contains(@class,'driverCancel')]/input", LocatorType.XPath); }
 //   public WebElement Button_Research() {return findElement("//*[contains(@id,'tripDriverDetails')]//button[2]", LocatorType.XPath); }
    public WebElement Button_Research() {return findElement("//*[contains(@id,'tripDriverDetails')]//*[contains(text(),'Re-search a driver')]/parent::button", LocatorType.XPath); }

    public WebElement TextBox_CancelFee() {
        return findElement("txtCancellationFee", LocatorType.Id);
    }

    public WebElement TextBox_Comments() {
        return findElement("txtCustomerCancellationComments", LocatorType.Id);
    }

    public WebElement Button_Submit() {
        return findElement("CustomerCancel", LocatorType.Name);
    }


    public WebElement Button_ScheduledDateSort(){return  findElement("span-ScheduledDate",LocatorType.Id);}
    public WebElement Text_SearchCriteria(){return  findElement("SearchCriteria",LocatorType.Id);}

    public WebElement Button_Search(){return  findElement("btnSearch",LocatorType.Id);}
    public WebElement Icon_Dropdown(){return  findElement("//div/img[@id='dLabel']",LocatorType.XPath);}
    public WebElement Option_Edit(){return  findElement("btnLiveEdit",LocatorType.Id);}
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
    public WebElement Button_VerifyDriver(){return findElement("//button[contains(text(),'VERIFY')]", LocatorType.XPath);}
    public WebElement Text_VerifyChangesSavedMessage() {return findElement("//p[@id='verified-message']/i[2]", LocatorType.XPath);}
    public WebElement Button_SaveChanges(){return findElement("//button[@class='btn btn-primary ml15 saveTrip']", LocatorType.XPath);}
    public WebElement Text_SuccessMessage(){return findElement("//p[@id='success-message']/i[2]", LocatorType.XPath);}


    public void waitForLoadingToDisappear(){
        waitForLoadingToDisappear();
    }

    public WebElement CheckBox_Driver1() {return findElement("//div[@class='tripDrivers row']//label[@class='custom-input checkboxDiv mt0 pull-left']/span", LocatorType.XPath); }
    public WebElement CheckBox_Driver2() {return findElements("//div[@class='tripDrivers row']//label[@class='custom-input checkboxDiv mt0 pull-left']/span", LocatorType.XPath).get(1); }
    public WebElement Button_Remove() {return findElement("//*[contains(@id,'tripDriverDetails')]//button[1]", LocatorType.XPath); }
    public WebElement TextBox_DriverSearch() {return findElement("txtDriverSearch", LocatorType.Id);}
    public WebElement Select_TestDriver(){return findElement("//div[@id='divDriversResult']/div[@class='pac-item'][1]", LocatorType.XPath);}
    public WebElement Text_EditTrpDetailsDriver1Name(){return findElement("//table[@id='editTripDrivers']/tbody/tr[1]/td/table/tbody/tr/td[3]", LocatorType.XPath);}

    public WebElement Time_EditTripDetailsTime(){return findElement("PickupDetails_ScheduledTime", LocatorType.Id);}
    public WebElement Select_EditReason() {return findElement("ddEditDeliveryRemark",LocatorType.Id);}
    public WebElement Button_ReviveTrip() { return findElement("//a[@class='revive-trip-link']/img",LocatorType.XPath);}
    public WebElement Button_Confirm() { return findElement("//button[text()='Confirm']", LocatorType.XPath); }
    public WebElement Link_DeliveryDetails(){return  findElement("dLabel",LocatorType.Id);}
    public WebElement List_ViewDeliveries(){return  findElement("//td/div[@class='dropdown open']/ul/li/*[contains(text(),'Delivery Details')]",LocatorType.XPath);}
    public WebElement Label_CustomerSignature(boolean...ignoreException){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]",LocatorType.XPath,ignoreException);}
    public WebElement Link_ChangeDeliveryStatus(boolean...ignoreException) { return findElement("//tr/td/a/img", LocatorType.XPath,ignoreException); }
    public WebElement DropDown_DeliveryStatus() { return findElement("txtNewStatus", LocatorType.Id); }
    public WebElement Text_DeliveryStatus(String status) { return findElement(String.format("//select/option[text() =\"%s\"]",status), LocatorType.XPath); }
    public WebElement DropDown_DeliveryStatusReason() { return findElement("txtNewStatusReason", LocatorType.Id);}
    public WebElement Text_DeliveryStatusReason(String statusReason) { return findElement(String.format("//div/select/option[text() =\"%s\"]",statusReason), LocatorType.XPath); }
    public WebElement Button_ConfirmStatus() { return findElement("//div[@class=\"modal-footer\"]/p/following-sibling::button[2]", LocatorType.XPath);}
    public WebElement Button_CloseStatus() { return findElement("//div[@id=\"edit-status-success-modal\"]/div/div/div[2]/button", LocatorType.XPath);}
    public WebElement Label_CustomerSignatureNA(){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]/following-sibling::td/strong",LocatorType.XPath);}
    public WebElement Button_ClosePopUp(){return findElement("//button[@class='close']/span", LocatorType.XPath);}
    public WebElement Image_CustomerSignature(){return  findElement("//div/table/tbody/tr/td[text() =\"Customer Signature\"]/following-sibling::td/img",LocatorType.XPath);}
    public WebElement Checkbox_driver () { return findElement("//div[@id='tripDriverDetails']//span[@class='checkmark'][1]", LocatorType.XPath); }//richa
    public WebElement Button_RemoveDrivers () { return findElement("//div[@id='tripDriverDetails']//strong[contains(text(),'Remove')]", LocatorType.XPath); }//Richa
    public WebElement  Button_Edit() {return findElement("//p[@id='btnEdit']",LocatorType.XPath);}
    public WebElement TextBox_Search() {return findElement("SearchCriteria", LocatorType.Id); }


}
