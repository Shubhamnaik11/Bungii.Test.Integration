package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_BusinessUsersPage extends PageBase {

    public WebElement Menu_BusinessUsers () { return findElement("adminmenu-businessuser", LocatorType.Id); }

    public WebElement Menu_BusinessUsersPayment () { return findElement("adminmenu-businessusers-payment", LocatorType.Id); }

    public WebElement Menu_BulkTrips () { return findElement("adminmenu-businessusers-bulkbungii", LocatorType.Id); }

    public WebElement Header_BusinessUsers () { return findElement("//h4[text()='Business Users']", LocatorType.XPath); }

    public WebElement Button_CreateBusinessUser ( ) { return findElement("btnCreateBusinessUser", LocatorType.Id); }

    public WebElement TextBox_Search ( ) { return findElement("SearchCriteria", LocatorType.Id); }

    public WebElement Button_Cancel ( ) { return findElement("//button[text()='Cancel']", LocatorType.XPath); }

    public WebElement Button_Save ( ) { return findElement("btnSaveBusinessUser", LocatorType.Id); }

    public WebElement TextBox_BusinessUserName( ) { return findElement("BusinessUser_Name", LocatorType.Id); }

    public WebElement DropDown_BusinessUserIsActive( ) { return findElement("BusinessUser_IsActive", LocatorType.Id); }

    public WebElement TextBox_BusinessUserPhoneNo ( ) { return findElement("BusinessUser_PhoneNo", LocatorType.Id); }

    public WebElement TextBox_BusinessUserEmailAddress( ) { return findElement("BusinessUser_EmailAddress", LocatorType.Id); }

    public WebElement Label_ErrorContainer( ) { return findElement("error-summary-modal", LocatorType.Id); }

    public WebElement Label_ErrorContainerPhone( ) { return findElement("BusinessUser_PhoneNo-error", LocatorType.Id); }

    public WebElement Label_ErrorContainerEmail( ) { return findElement("BusinessUser_EmailAddress-error", LocatorType.Id); }

    public WebElement DropDown_BusinessUser( ) { return findElement("Reference", LocatorType.Id); }

    public WebElement DropDown_AddBusinessUserPayment( ) { return findElement("AddBusinessUserPaymentRequestModel_Reference", LocatorType.Id); }

    public WebElement Button_RequestPayment( ) { return findElement("btnRequestPayment", LocatorType.Id); }

    public WebElement TextBox_CreditCardNumber( ) { return findElement("credit-card-number", LocatorType.Id); }

    public WebElement TextBox_ExpirationDate( ) { return findElement("expiration", LocatorType.Id); }

    public WebElement TextBox_CVV( ) { return findElement("cvv", LocatorType.Id); }

    public WebElement TextBox_PostalCode( ) { return findElement("postal-code", LocatorType.Id); }

    public WebElement Button_PaymentSave( ) { return findElement("btnSave", LocatorType.Id); }

    public WebElement Button_PaymentCancel( ) { return findElement("btnCancel", LocatorType.Id); }

    public WebElement CheckBox_Default( ) { return findElement("AddBusinessUserPaymentRequestModel_IsDefault", LocatorType.Id); }

    public WebElement Label_SuccessMessage( ) { return findElement("successMessage", LocatorType.Id); }

    public WebElement Frame_Braintree( ) { return findElement("braintree-hosted-field-number", LocatorType.Id); }

    public WebElement Label_BulkTripSuccess( ) { return findElement("error", LocatorType.Id); }

    public WebElement Button_Ok( ) { return findElement("//button[text()='OK']", LocatorType.XPath); }

    public WebElement Button_Confirm( ) { return findElement("btnConfirm", LocatorType.Id); }

    public WebElement Button_Upload( ) { return findElement("//input[@value='Upload']", LocatorType.XPath); }

    public WebElement Input_DataFile( ) { return findElement("dataFile", LocatorType.Id); }

    public WebElement Input_ImageFile( ) { return findElement("imageFile", LocatorType.Id); }

//BOC
    public WebElement Label_PayWithCard(){ return  findElement("//div[@class='braintree-sheet__text']", LocatorType.XPath);}

    public WebElement Label_NoBusinessUsersFound(){ return findElement("//h5[@class='margintop11'][contains(text(),'No Business users found.')]", LocatorType.XPath);}

    public WebElement Label_ErrorContainerCarNumber(){ return  findElement("//div[contains(text(),'Please fill out a card number.')]", LocatorType.XPath);}

    public WebElement Label_ErrorContainerExpiryDate(){ return  findElement("//div[contains(text(),'Please fill out an expiration date.')]", LocatorType.XPath);}

    public WebElement Label_ErrorContainerCVV(){ return  findElement("//div[contains(text(),'Please fill out a CVV.')]", LocatorType.XPath);}

    public WebElement Label_ErrorContainerPostalCode(){ return  findElement("Please fill out a postal code.')]", LocatorType.XPath);}

    public WebElement Label_PaymentMethodSavedMessage(){ return  findElement("successMessage", LocatorType.Id);}

//EOC

}