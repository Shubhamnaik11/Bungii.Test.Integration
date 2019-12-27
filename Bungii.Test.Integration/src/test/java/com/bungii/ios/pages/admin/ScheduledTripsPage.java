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

    public WebElement RadioBox_Cancel() {return findElement("//label[contains(@class,'customerCancel')]/input", LocatorType.XPath);}

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


    public void waitForLoadingToDisappear(){
        waitForLoadingToDisappear();
    }

    public WebElement CheckBox_Driver1() {return findElement("//div[@class='tripDrivers row']//label[@class='custom-input checkboxDiv mt0 pull-left']/span", LocatorType.XPath); }
    public WebElement Button_Remove() {return findElement("//*[contains(@id,'tripDriverDetails')]//button[1]", LocatorType.XPath); }

}
