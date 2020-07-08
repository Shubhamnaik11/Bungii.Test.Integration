package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Partner_DeliveryPage extends PageBase {

    //Delivery Details Header
    public WebElement Delivery_Details_Header() { return findElement("//h1[contains(text(),'Delivery Details')]", LocatorType.XPath); }

    //Back To Estimate
    public WebElement Back_To_Estimate() { return findElement("//span[@class='back']",LocatorType.XPath);}

    //Items to Deliver
    public WebElement Item_To_deliver() { return findElement("pickUpNote",LocatorType.Name);}

    //Blank Item to deliver message
    public WebElement message_Black_Item_To_deliver() { return findElement("//div[contains(text(),'Items to deliver is required.')]",LocatorType.XPath);}

    //Customer Name
    public WebElement Customer_Name() { return findElement("customerName",LocatorType.Name);}

    //Blank Customer Name
    public WebElement message_Black_Customer_Name() { return findElement("//div[contains(text(),'Customer name is required.')]",LocatorType.XPath);}

    //Customer Mobile
    public WebElement Customer_Mobile() { return findElement("customerMobile",LocatorType.Name);}

    //Blank Customer Mobile
    public WebElement message_Blank_Customer_Mobile() { return findElement("//div[contains(text(),'Customer mobile is required.')]",LocatorType.XPath);}

    //Pickup Contact Name
    public WebElement Pickup_Contact_Name() { return findElement("f2bd9004-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);}

    //Blank Pickup Contact Name
    public WebElement message_Blank_Pickup_Contact_Name() { return findElement("//div[contains(text(),'Pickup contact name is required.')]",LocatorType.XPath);}

    //Pickup Contact Phone
    public WebElement Pickup_Contact_Phone() { return findElement("f2bd908c-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);}

    //Blank Pickup Contact Phone
    public WebElement message_Blank_Pickup_Contact_Phone() { return findElement("//div[contains(text(),'Pickup contact phone is required.')]",LocatorType.XPath);}

    //Customer Card Payment
    public WebElement Customer_Card() { return findElement("//label[contains(text(),'Customer Card')]",LocatorType.XPath);}

    //Card Number
    public WebElement Card_Number() { return findElement("//*[@id='credit-card-number']",LocatorType.XPath);}

    //Invalid Card number message
    public WebElement messageInvalidCardNumber() { return  findElement("//div[contains(text(),'This card number is not valid.')]",LocatorType.XPath);}

    //Expiry Date
    public WebElement Expiry_Date() { return findElement("//*[@id='expiration']",LocatorType.XPath);}

    //Invalid Expired date
    public WebElement messageInvalidExpiredDate() { return findElement("//div[contains(text(),'This expiration date is not valid.')]",LocatorType.XPath);}

    //CVV
    public WebElement CVV() { return  findElement("//*[@id='cvv']",LocatorType.XPath);}

    //Invalid CVV
    public WebElement messageInvalidCVV() { return findElement("//div[contains(text(),'This security code is not valid.')]",LocatorType.XPath);}

    //Postal Code
    public WebElement Postal_Code() { return findElement("//*[@id='postal-code']",LocatorType.XPath);}

    //Invalid Postal code
    public WebElement messageInvalidPostal_Code() { return findElement("//div[contains(text(),'This postal code is not valid.')]",LocatorType.XPath);}

    //Partner Pay
    public WebElement Partner_Pay() { return findElement("//label[contains(text(),'Partner Pay')]",LocatorType.XPath);}

    //Schedule Bungii
    public WebElement Schedule_Bungii() { return findElement("submit-details",LocatorType.Id);}

}
