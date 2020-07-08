package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Partner_DeliveryList extends PageBase {

    //Customer
    public WebElement Customer() { return findElement("//tr[@class='MuiTableRow-root jss171 jss172 MuiTableRow-hover']/td[4]",LocatorType.XPath);}

    //Delivery Address
    public WebElement Delivery_Address() {return findElement("//tr[@class='MuiTableRow-root jss171 jss172 MuiTableRow-hover']/td[6]",LocatorType.XPath);}

    //Latest delivery record
    public WebElement Record1() { return findElement("//tr[@id='MUIDataTableBodyRow-0']",LocatorType.XPath);}

    //Delivery Details
    public WebElement Delivery_Details_Dashboard() { return findElement("//h2[contains(text(),'Delivery Details')]",LocatorType.XPath);}

    //Cancel Delivery link
    public WebElement link_Cancel_Delivery() { return findElement("//button[@class='btn cancel-delivery']",LocatorType.XPath);}

    //Cancel Delivery Message
    public WebElement message_Cancel_Delivery() { return findElement("//p[contains(text(),'Are you sure you want to cancel this delivery?')]",LocatorType.XPath);}

    //Cancel Delivery button
    public WebElement button_Cancel_Delivery() { return findElement("//button[@class='btn btn-clear btn-clear-red']",LocatorType.XPath);}

    //Cancel Confirmation
    public  WebElement message_Cancel_confirmation() { return findElement("//p[contains(text(),'Your delivery has been canceled.')]",LocatorType.XPath);}

    //Cancel OK Button
    public WebElement botton_OK() { return findElement("//button[@class='btn']",LocatorType.XPath);}

    //Cancel message
    public WebElement message_Cancel_Trip() { return findElement("//p[@class='trip-Canceled-text']",LocatorType.XPath);}

}
