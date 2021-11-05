package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Partner_DeliveryPage extends PageBase {

    //Delivery Details Header Text
    public WebElement Text_Delivery_Details_Header() { return findElement("//h1[contains(text(),'Delivery Details')]", LocatorType.XPath); }

    //Delivery List Text
    public WebElement Text_Delivery_List() { return findElement("//h6[contains(text(),'Delivery List')]",LocatorType.XPath);}

    //Pickup DateTime Text
    public WebElement Text_Pickup_DateTime() { return findElement("//label[contains(text(),'Pickup Date & Time:')]/following::p[1]",LocatorType.XPath);}

    //Back To Estimate Link
    public WebElement Link_Back_To_Estimate() { return findElement("//span[@class='back']",LocatorType.XPath);}

    //Items to Deliver
    public WebElement TextBox_Item_To_Deliver() { return findElement("pickUpNote",LocatorType.Name);}

    //SKU Text fiels
    public WebElement TextBox_SKU() { return findElement("skuNumber",LocatorType.Name);}

    //SKU Add
    public WebElement Button_SKU_Add() { return findElement("SKU",LocatorType.Id);}

    //Blank Item to deliver message
    public WebElement Message_Black_Item_To_Deliver() { return findElement("//div[contains(text(),'Items to deliver is required.')]",LocatorType.XPath);}

    //Special Instruction
    public WebElement TextBox_Special_Intruction() { return findElement("specialInstructions",LocatorType.Name);}

    //Customer Name
    public WebElement TextBox_Customer_Name() { return findElement("customerName",LocatorType.Name);}

    //Blank Customer Name
    public WebElement Message_Black_Customer_Name() { return findElement("//div[contains(text(),'Customer name is required.')]",LocatorType.XPath);}

    //Customer Mobile
    public WebElement TextBox_Customer_Mobile() { return findElement("customerMobile",LocatorType.Name);}

    //Blank Customer Mobile
    public WebElement Message_Blank_Customer_Mobile() { return findElement("//div[contains(text(),'Customer mobile is required.')]",LocatorType.XPath);}

    //Pickup Contact Name
    //public WebElement TextBox_Pickup_Contact_Name() { return findElement("f2bd9004-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);
    public WebElement TextBox_Pickup_Contact_Name() { return findElement("//label[text()='Pickup contact name']/following::input[1]",LocatorType.XPath);}

    //Blank Pickup Contact Name
    public WebElement Message_Blank_Pickup_Contact_Name() { return findElement("//div[contains(text(),'Pickup contact name is required.')]",LocatorType.XPath);}

    //Pickup Contact Phone
    //public WebElement TextBox_Pickup_Contact_Phone() { return findElement("f2bd908c-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);}
    public WebElement TextBox_Pickup_Contact_Phone() { return findElement("//label[text()='Pickup contact phone']/following::input[1]",LocatorType.XPath);}

    //Scheduled By
    public WebElement TextBox_Scheduled_By() { return findElement("//label[text()='Scheduled By']/following::input[1]",LocatorType.XPath);}

    //Pickup Date Time
    public WebElement Label_Pickup_Date_Time() { return findElement("//label[contains(text(),'Pickup Date & Time:')]/following-sibling::p",LocatorType.XPath);}

    //Blank Pickup Contact Phone
    public WebElement Message_Blank_Pickup_Contact_Phone() { return findElement("//div[contains(text(),'Pickup contact phone is required.')]",LocatorType.XPath);}

    //Drop Off Contact Name
    public WebElement TextBox_Drop_Off_Contact_Name() { return findElement("f2bd90b3-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);}

    //Drop Off Contact Phone
    public WebElement TextBox_Drop_Off_Contact_Phone() { return findElement("f2bd90d3-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);}

    //Receipt Number
    public WebElement TextBox_Receipt_Number() { return findElement("f2bd91b2-6757-11ea-a4a3-00155d0a8706",LocatorType.Id);}

    //Order Number
    public WebElement TextBox_Order_Number() { return findElement("//input[@data-field='Order Number']",LocatorType.XPath);}

    //Sold Buy
    public WebElement Dropdown_SoldBuy() { return findElement("//div[@class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-formControl']",LocatorType.XPath);}

    public WebElement List_StoreAssociate(String storeAssociate) { return findElement(String.format("//li[text()='%s']",storeAssociate),LocatorType.XPath);}

    //Customer Card Payment
    public WebElement Radio_Button_Customer_Card() { return findElement("//label[contains(text(),'Customer Card')]",LocatorType.XPath);}

    //Card Number
    public WebElement TextBox_Card_Number() { return findElement("//*[@id='credit-card-number']",LocatorType.XPath);}

     //Invalid Card number message
    public WebElement Message_Invalid_CardNumber() { return  findElement("//div[contains(text(),'This card number is not valid.')]",LocatorType.XPath);}

    //Invalid Card number message
    public WebElement Message_Toast() { return  findElement("//div[@role='alert']",LocatorType.XPath);}

    //Expiry Date
    public WebElement TextBox_Expiry_Date() { return findElement("//*[@id='expiration']",LocatorType.XPath);}

    //Invalid Expired date
    public WebElement Message_Invalid_ExpiredDate() { return findElement("//div[contains(text(),'This expiration date is not valid.')]",LocatorType.XPath);}

    //CVV
    public WebElement TextBox_CVV() { return  findElement("//*[@id='cvv']",LocatorType.XPath);}

    //Invalid CVV
    public WebElement Message_Invalid_CVV() { return findElement("//div[contains(text(),'This security code is not valid.')]",LocatorType.XPath);}

    //Postal Code
    public WebElement TextBox_Postal_Code() { return findElement("//*[@id='postal-code']",LocatorType.XPath);}

    //Invalid Postal code
    public WebElement Message_Invalid_Postal_Code() { return findElement("//div[contains(text(),'This postal code is not valid.')]",LocatorType.XPath);}

    //Partner Pay Radio button
    public WebElement Radio_Button_Partner_Pay() { return findElement("//label[contains(text(),'Partner Pay')]",LocatorType.XPath);}

    //Schedule Bungii button
    //public WebElement Button_Schedule_Bungii() { return findElement("submit-details",LocatorType.Id);}
    public WebElement Button_Schedule_Bungii() { return findElement("//button[@id='submit-details']",LocatorType.XPath);}

    //Driver and truck text in summary
    public WebElement Text_Driver_Truck() { return findElement("//label[contains(text(),'s needed:')]/following-sibling::p",LocatorType.XPath);}

    //Pick up address text in summary
    public WebElement Text_Pick_Address() { return findElement("//label[contains(text(),'Pickup Address:')]/following-sibling::p",LocatorType.XPath);}

    //Delivery Address text in summary
    public WebElement Text_Delivery_Address() { return findElement("//label[contains(text(),'Delivery Address:')]/following-sibling::p",LocatorType.XPath);}

    //Estimated cost in summary
    public WebElement Text_Estiated_Cost() { return findElement("//h2[@class='estimate-label']/span",LocatorType.XPath);}

    //Delivery Cost
    public WebElement Label_Delivery_Cost() { return findElement("//h2[contains(text(),'Delivery Cost')]",LocatorType.XPath);}

    //Product Description Text fields
    public WebElement TextBox_Product_Description() { return findElement("Name",LocatorType.Name);}

    //Dimensions Text fields
    public WebElement TextBox_Dimensions() { return findElement("Dimensions",LocatorType.Name);}

    //Weight Text fields
    public WebElement TextBox_Weight() { return findElement("Weight",LocatorType.Name);}

    //Delivery Purpose Text fields
    public WebElement TextBox_Delivery_Purpose() { return findElement("//input[@data-field='Delivery purpose']",LocatorType.XPath);}

    //RB/SB NUMBER Text fields
    public WebElement TextBox_Rb_Sb_Number() { return findElement("//label[contains(text(),'RB/SB Number')]//following-sibling::input",LocatorType.XPath);}

    //SoldBy Text fields
    public WebElement TextBox_SoldBy() { return findElement("//label[contains(text(),'Scheduled By')]//following-sibling::input",LocatorType.XPath);}

}
