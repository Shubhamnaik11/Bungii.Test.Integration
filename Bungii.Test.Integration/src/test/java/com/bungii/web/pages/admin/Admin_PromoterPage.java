package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_PromoterPage extends PageBase {

    public WebElement Title_PromoterPage (boolean...ignoreException) { return findElement("//h4[text()='Partners']", LocatorType.XPath,ignoreException); }

    public WebElement Menu_Promotion (boolean... ignoreException) { return findElement("//span[text()='Promo Codes']", LocatorType.XPath,ignoreException); }

    public WebElement Menu_Promotion_Free_Delivery (boolean... ignoreException) { return findElement("//a[text()='Free Delivery Codes']", LocatorType.XPath,ignoreException); }

    public WebElement Menu_PromoterPayment (boolean... ignoreException) { return findElement("adminmenu-promoters-payment", LocatorType.Id,ignoreException); }

    public WebElement Button_NewPromoter () { return findElement("btnCreatePromoter", LocatorType.Id); }

    public WebElement Button_SavePromoter () { return findElement("btnSavePromoter", LocatorType.Id); }

    public WebElement Button_Cancel () { return findElement("//button[text()='Cancel']", LocatorType.XPath); }

    public WebElement TextBox_PromoterName () { return findElement("PromoterItem_PromotionName", LocatorType.Id); }

    public WebElement TextBox_CodeInitials () { return findElement("PromoterItem_CodeInitials", LocatorType.Id); }

    public WebElement TextBox_Discription () { return findElement("PromoterItem_Description", LocatorType.Id); }

    public WebElement DropDown_Status () { return findElement("Status", LocatorType.Id); }

    public WebElement Button_NewPromotion () { return findElement("btnCreatePromotion", LocatorType.Id); }

    public WebElement Button_SavePromotion () { return findElement("btnSavePromotion", LocatorType.Id); }

    public WebElement Button_SavePayment () { return findElement("btnSave", LocatorType.Id); }

    public WebElement TextBox_PromotionName () { return findElement("PromotionDetailItem_Name", LocatorType.Id); }

    public WebElement TextBox_PromoStartDate () { return findElement("PromotionDetailItem_StartDate", LocatorType.Id); }

    public WebElement TextBox_PromoEndDate () { return findElement("PromotionDetailItem_ExpiryDate", LocatorType.Id); }

    public WebElement DropDown_SelectPromoter() { return findElement("AddPaymentMethodRequestModel_Reference", LocatorType.Id); }

    public WebElement TextBox_DiscountValue () { return findElement("PromotionDetailItem_Value", LocatorType.Id); }

    public WebElement RadioButton_PercentSelected () { return findElement("//input[@id='PromotionDetailItem_ValueType' and @value='1']/parent::label[contains(.,'Percent')]", LocatorType.XPath); }

    public WebElement Button_SavePromotionYes () { return findElement("//button[text()='Yes']", LocatorType.XPath); }

    public WebElement Button_SavePromotionNo () { return findElement("//button[text()='No']", LocatorType.XPath); }

    public WebElement TextBox_Search () { return findElement("SearchCriteria", LocatorType.Id); }

    public WebElement Label_GeneratePromocodeMessageContainer() { return findElement("//span[text()='Do you wish to generate promo codes?']", LocatorType.XPath); }

    public WebElement Label_SuccessMessage( ) { return findElement("successMessage", LocatorType.Id); }

    public WebElement Label_CodeInitialsContainer( ) { return findElement("PromoterItem_CodeInitials-error", LocatorType.Id); }

    public WebElement TextBox_CreditCardNumber( ) { return findElement("credit-card-number", LocatorType.Id); }

    public WebElement TextBox_ExpirationDate( ) { return findElement("expiration", LocatorType.Id); }

    public WebElement TextBox_CVV( ) { return findElement("cvv", LocatorType.Id); }

    public WebElement TextBox_PostalCode( ) { return findElement("postal-code", LocatorType.Id); }

    public WebElement Header_Name() { return findElement("//th[text()='Name']", LocatorType.XPath); }

    public WebElement Header_Created () { return findElement("//th[contains(text(),'Created')]", LocatorType.XPath); }

    public WebElement Header_CodeInitials () { return findElement("//th[contains(text(),'Code Initials')]", LocatorType.XPath); }

}