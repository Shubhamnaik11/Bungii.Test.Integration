package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_BusinessUsersPage extends PageBase {

    public WebElement Menu_BusinessUsers () { return findElement("//span[contains(text(),'Bulk Delivery Upload')]", LocatorType.XPath); }

    public WebElement Menu_BusinessUsersPayment () { return findElement("//a[text()='Partner Payment']", LocatorType.XPath); }

    public WebElement Menu_BulkTrips () { return findElement("//a[text()='Upload Deliveries']", LocatorType.XPath); }

    public WebElement Header_BusinessUsers () { return findElement("//h4[contains(text(),'Partners')]", LocatorType.XPath); }

    public WebElement Button_CreateBusinessUser ( ) { return findElement("//button[contains(text(),'New Partner')]", LocatorType.XPath); }

    public WebElement TextBox_Search ( ) { return findElement("SearchCriteria", LocatorType.Id); }

    public WebElement Button_Cancel ( ) { return findElement("//button[contains(text(),'Cancel')]", LocatorType.XPath); }

    public WebElement Button_Save ( ) { return findElement("//button[contains(text(),'Save')]", LocatorType.XPath); }

    public WebElement TextBox_BusinessUserName( ) { return findElement("//label[contains(text(),'Name:')]/parent::div/div/div/input", LocatorType.XPath); }

    public WebElement DropDown_BusinessUserIsActive( ) { return findElement("//label[contains(text(),'Status:')]/parent::div/select", LocatorType.XPath); }

    public WebElement TextBox_BusinessUserPhoneNo ( ) { return findElement("//label[contains(text(),'Phone number:')]/following-sibling::div/div/input", LocatorType.XPath); }

    public WebElement TextBox_BusinessUserEmailAddress( ) { return findElement("//label[contains(text(),'Email:')]/following-sibling::div/div/input", LocatorType.XPath); }

    //public WebElement Label_ErrorContainer( ) { return findElement("error-summary-modal", LocatorType.Id); }
    public WebElement Label_ErrorContainer( ) { return findElement("//p[text()=' Phone number already exists']", LocatorType.XPath); }

    public WebElement Label_ErrorContainerPhone( ) { return findElement("BusinessUser_PhoneNo-error", LocatorType.Id); }

    public WebElement Label_ErrorContainerEmail( ) { return findElement("BusinessUser_EmailAddress-error", LocatorType.Id); }

    public WebElement DropDown_BusinessUser( ) { return findElement("//select[@class='form-select']", LocatorType.XPath); }

    public WebElement DropDown_AddBusinessUserPayment( ) { return findElement("//select[@class='form-select']", LocatorType.XPath); }

    public WebElement Button_RequestPayment( ) { return findElement("//button[text()='Add Payment Method']", LocatorType.XPath); }

    public WebElement TextBox_CreditCardNumber( ) { return findElement("credit-card-number", LocatorType.Id); }

    public WebElement TextBox_ExpirationDate( ) { return findElement("expiration", LocatorType.Id); }

    public WebElement TextBox_CVV( ) { return findElement("cvv", LocatorType.Id); }

    public WebElement TextBox_PostalCode( ) { return findElement("postal-code", LocatorType.Id); }

    public WebElement Button_PaymentSave( ) { return findElement("//button[text()='Save']", LocatorType.XPath); }

    public WebElement Button_PaymentCancel( ) { return findElement("btnCancel", LocatorType.Id); }

    public WebElement CheckBox_Default( ) { return findElement("AddBusinessUserPaymentRequestModel_IsDefault", LocatorType.Id); }

    public WebElement Label_SuccessMessage( ) { return findElement("//span[text()='Payment details added successfully for partner.']", LocatorType.XPath); }

    public WebElement Frame_Braintree( ) { return findElement("braintree-hosted-field-number", LocatorType.Id); }

    public WebElement Label_BulkTripSuccess( ) { return findElement("error", LocatorType.Id); }

    public WebElement Button_Ok( ) { return findElement("//button[text()='OK']", LocatorType.XPath); }

    public WebElement Button_Confirm( ) { return findElement("btnConfirm", LocatorType.Id); }

    public WebElement Button_Upload( ) { return findElement("//input[@value='Upload']", LocatorType.XPath); }

    public WebElement Input_DataFile( ) { return findElement("dataFile", LocatorType.Id); }

    public WebElement Input_ImageFile( ) { return findElement("imageFile", LocatorType.Id); }

//BOC
    public WebElement Label_PayWithCard(){ return  findElement("//div[@class='braintree-sheet__text']", LocatorType.XPath);}

    public WebElement Label_NoBusinessUsersFound(){ return findElement("//h5[@class='margintop11'][contains(text(),'No Partners found.')]", LocatorType.XPath);}

    public WebElement Label_ErrorContainerCarNumber(){ return  findElement("//div[contains(text(),'Please fill out a card number.')]", LocatorType.XPath);}

    public WebElement Label_ErrorContainerExpiryDate(){ return  findElement("//div[contains(text(),'Please fill out an expiration date.')]", LocatorType.XPath);}

    public WebElement Label_ErrorContainerCVV(){ return  findElement("//div[contains(text(),'Please fill out a CVV.')]", LocatorType.XPath);}
    //BOC
    public WebElement Link_DownloadFailedCSVFile( ) { return findElement("//div[@id='validatedBulkTrips']/a[contains(text(),'Click here to download deliveries which failed validation')]", LocatorType.XPath); }

    public WebElement Label_ErrorContainerPostalCode(){ return  findElement("Please fill out a postal code.')]", LocatorType.XPath);}

    public WebElement Label_PaymentMethodSavedMessage(){ return  findElement("successMessage", LocatorType.Id);}

//EOC
    public WebElement Button_BulkTripCancel( ) { return findElement("//a[@class='btn btn-danger']", LocatorType.XPath); }

    public WebElement Label_ErrorOnBulkTripsPage(){ return findElement("//div[@class='error mb10']", LocatorType.XPath);}
    //EOC

}