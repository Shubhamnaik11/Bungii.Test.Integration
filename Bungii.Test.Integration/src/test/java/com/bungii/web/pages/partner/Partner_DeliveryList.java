package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Partner_DeliveryList extends PageBase {

    //Delivery Date Text
    public WebElement Text_Delivery_Date() { return findElement("//tr[@class='MuiTableRow-root jss171 jss172 MuiTableRow-hover']/td[2]",LocatorType.XPath);}

    //Customer Name Text
    public WebElement Text_Customer() { return findElement("//tr[@class='MuiTableRow-root jss171 jss172 MuiTableRow-hover']/td[4]",LocatorType.XPath);}

    //Delivery Address Text
    public WebElement Text_Delivery_Address() {return findElement("//tr[@class='MuiTableRow-root jss171 jss172 MuiTableRow-hover']/td[6]",LocatorType.XPath);}

    //Latest delivery record
    public WebElement Record1() { return findElement("//tr[@id='MUIDataTableBodyRow-0']",LocatorType.XPath);}

    //Delivery Details
    public WebElement Delivery_Details_Dashboard() { return findElement("//h2[contains(text(),'Delivery Details')]",LocatorType.XPath);}

    //Cancel Delivery link
    public WebElement Link_Cancel_Delivery() { return findElement("//button[@class='btn cancel-delivery']",LocatorType.XPath);}

    //Cancel Delivery Message
    public WebElement Message_Cancel_Delivery() { return findElement("//p[contains(text(),'Are you sure you want to cancel this delivery?')]",LocatorType.XPath);}

    //Cancel Delivery button
    public WebElement Button_Cancel_Delivery() { return findElement("//button[@class='btn btn-clear btn-clear-red']",LocatorType.XPath);}

    //Cancel Confirmation
    public  WebElement Message_Cancel_Confirmation() { return findElement("//p[contains(text(),'Your delivery has been canceled.')]",LocatorType.XPath);}

    //Cancel OK Button
    public WebElement Button_OK() { return findElement("//button[@class='btn']",LocatorType.XPath);}

    //OK button on Delivery Cancellation Failed
    public WebElement Button_Ok__On_Delivery_Cancellation_Failed() { return findElement("//button[@class='btn btn btn-primary']",LocatorType.XPath);}

    //Close button on Trip Delivery Details
    public WebElement Button_Close() { return findElement("//span[@class='modal-close']",LocatorType.XPath);}

    //Cancel message
    public WebElement Message_Cancel_Trip() { return findElement("//p[@class='trip-Canceled-text']",LocatorType.XPath);}

    //
    public WebElement Message_Delivery_Cancellation_Failed() { return findElement("//h2[contains(text(),'Delivery cancellation failed!')]/following::p",LocatorType.XPath);}

    //Status Filter dropdown
    public WebElement Dropdown_Partner_Status() { return findElement("//a[@class='caret']",LocatorType.XPath);}

    //Completed Status Checkbox
    public WebElement Checkbox_Completed_Status() { return findElement("//label[contains(text(),'Completed')]",LocatorType.XPath);}

    //Canceled Status Checkbox
    public WebElement Checkbox_Canceled_Status() { return findElement("//label[contains(text(),'Canceled')]",LocatorType.XPath);}

    //Apply button for status filter
    public WebElement Button_Apply() { return findElement("//button[@class='btn']",LocatorType.XPath);}

    //Selected service name
    public WebElement Text_Selected_Service() { return findElement("//label[contains(text(),'Service Level')]//following::p/span",LocatorType.XPath);}

}