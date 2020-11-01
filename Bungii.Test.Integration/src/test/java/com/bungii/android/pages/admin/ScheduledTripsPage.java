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

    public void waitForLoadingToDisappear(){
        waitForLoadingToDisappear();
    }
    public WebElement Text_SearchCriteria(){return  findElement("SearchCriteria",LocatorType.Id);}
    public WebElement Label_Message(){return  findElement("//p[@id='cancel-success-message']/i[2]",LocatorType.XPath);}

    public WebElement Button_Search(){return  findElement("btnSearch",LocatorType.Id);}
    public WebElement Button_Research() {return findElement("//*[contains(@id,'tripDriverDetails')]//button[2]", LocatorType.XPath); }
   // wait
   public WebElement CheckBox_Driver1() {return findElement("//div[@class='tripDrivers row']//label[@class='custom-input checkboxDiv mt0 pull-left']/span", LocatorType.XPath); }
    public WebElement CheckBox_Driver2() {return findElements("//div[@class='tripDrivers row']//label[@class='custom-input checkboxDiv mt0 pull-left']/span", LocatorType.XPath).get(1); }
    public WebElement Button_Remove() {return findElement("//*[contains(@id,'tripDriverDetails')]//button[1]", LocatorType.XPath); }

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

    public WebElement Text_EditTrpDetailsDriver1Name(){return findElement("//table[@id='editTripDrivers']/tbody/tr[1]/td/table/tbody/tr/td[3]", LocatorType.XPath);}
    public WebElement Text_EditTrpDetailsDriver2Name(){return findElement("//table[@id='editTripDrivers']/tbody/tr[2]/td/table/tbody/tr/td[3]", LocatorType.XPath);}

    public WebElement Text_Driver1Name(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tv_drivername_value']", LocatorType.XPath).get(0);}
    public WebElement Text_Driver2Name(){ return findElements("//*[@resource-id='com.bungii.customer:id/driver_details_row_tv_drivername_value']", LocatorType.XPath).get(1);}
    public WebElement Text_VerifyChangesSavedMessage() {return findElement("//p[@id='verified-message']/i[2]", LocatorType.XPath);}
    public WebElement Button_SaveChanges(){return findElement("//button[@class='btn btn-primary ml15 saveTrip']", LocatorType.XPath);}
    public WebElement Text_SuccessMessage(){return findElement("//p[@id='success-message']/i[2]", LocatorType.XPath);}
    public WebElement Label_IconTextMessage(){return findElement("//table[@id='editTripDrivers']/tbody/tr/td/table/tbody/tr/td", LocatorType.XPath);}

    public WebElement Label_ChangedScheduledTime(){return findElement("//p[contains(text(),'Schedule Time:')]/following-sibling::p", LocatorType.XPath);}
    public WebElement Button_ClosePopUp(){return findElement("//button[@class='close']/span", LocatorType.XPath);}

    public WebElement Text_BungiiTime(){return findElements("//android.widget.RelativeLayout/android.widget.TextView", LocatorType.XPath).get(2);}
    public WebElement Text_ConflictMessageError() {return findElement("//p[@id='conflict-message']/strong/i[@id='verify-error']", LocatorType.XPath);}

    public WebElement Text_ScheduledBungiiTime(){return findElement("//div[@class='tripDrivers row']/p[@class='col-sm-7 col-md-8 col-lg-9 lblScheduledTime']", LocatorType.XPath);}
    public WebElement Text_DriverNames(){return findElement("//div[@id='tripDriverDetails']/div/span[1]", LocatorType.XPath);}

    public WebElement Select_CancellationReason() {return findElement("txtCancellationRemark", LocatorType.Id);}

}
