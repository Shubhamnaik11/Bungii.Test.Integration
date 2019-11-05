package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_PromoCodesPage extends PageBase {

    public WebElement Title_PromocodesPage (boolean...ignoreException) { return findElement("//h4[text()='Promo Codes']", LocatorType.XPath,ignoreException); }

    public WebElement Menu_Marketing (boolean... ignoreException) { return findElement("adminmenu-marketing", LocatorType.Id,ignoreException); }

    public WebElement Button_NewCode () { return findElement("btnCreatePromoCode", LocatorType.Id); }

    public WebElement Button_Save () { return findElement("btnSavePromoCode", LocatorType.Id); }

    public WebElement Button_Cancel () { return findElement("//button[text()='Cancel']", LocatorType.XPath); }

    public WebElement DropDown_PromoType () { return findElement("drpPromoType", LocatorType.Id); }

    public WebElement TextBox_PromoCodeName () { return findElement("PromoCodeItem_Name", LocatorType.Id); }

    public WebElement TextBox_PromoCode () { return findElement("PromoCodeItem_Code", LocatorType.Id); }

    public WebElement TextBox_DiscountValue () { return findElement("PromoCodeItem_Value", LocatorType.Id); }

    public WebElement RadioButton_Dollars () { return findElement("//input[@id='PromoCodeItem_ValueType' and @value='2']", LocatorType.XPath); }

    public WebElement RadioButton_Percent () { return findElement("//input[@id='PromoCodeItem_ValueType' and @value='1']", LocatorType.XPath); }

    public WebElement RadioButton_DollarsDisabled () { return findElement("rdDollar", LocatorType.Id); }

    public WebElement RadioButton_PercentDisabled () { return findElement("rdPercent", LocatorType.Id); }

    public WebElement CheckBox_FirstTimeUse () { return findElement("chkIsFirstTimeUse", LocatorType.Id); }

    public WebElement Label_ErrorContainer () { return findElement("error-summary-modal", LocatorType.Id); }

    public WebElement Label_CodeErrorContainer () { return findElement("PromoCodeItem_Code-error", LocatorType.Id); }
    public WebElement Label_PromoterErrorContainer () { return findElement("drpPromoter-error", LocatorType.Id); }

    public WebElement Label_CountErrorContainer () { return findElement("PromoCodeItem_CodesCount-error", LocatorType.Id); }
    public WebElement TextBox_CodeCount () { return findElement("PromoCodeItem_CodesCount", LocatorType.Id); }

    public WebElement DropDown_Promoter () { return findElement("//form[@id='PromoCodes']//following::select[@id='drpPromoter']", LocatorType.XPath); }

    public WebElement DropDown_Promotion () { return findElement("//form[@id='PromoCodes']//following::select[@id='drpPromotion']", LocatorType.XPath); }

    public WebElement TextBox_PromotionStartDate () { return findElement("PromoCodeItem_PromotionStartDate", LocatorType.Id); }

    public WebElement TextBox_PromotionExpirationDate() { return findElement("PromoCodeItem_ExpiryDate", LocatorType.Id); }

    public WebElement TextBox_Search() { return findElement("SearchCriteria", LocatorType.Id); }

    public WebElement Button_Filter() { return findElement("btnFilter", LocatorType.Id); }

    public WebElement CheckBox_FilterAll() { return findElement("chkCodeTypeFilterAll", LocatorType.Id); }

    public WebElement CheckBox_FilterPromo() { return findElement("chkCodeTypeFilterPromo", LocatorType.Id); }

    public WebElement CheckBox_FilterReferral() { return findElement("chkCodeTypeFilterReferral", LocatorType.Id); }

    public WebElement CheckBox_FilterOneOffByAdmin() { return findElement("chkCodeTypeFilterOneOffByAdmin", LocatorType.Id); }

    public WebElement CheckBox_FilterOneOffFBShare() { return findElement("chkCodeTypeFilterOneOffFBShare", LocatorType.Id); }

    public WebElement CheckBox_FilterDeliveryChargesByPromoter() { return findElement("chkCodeTypeFilterDeliveryChargesByPromoter", LocatorType.Id); }

    public WebElement CheckBox_FilterDeliveryChargesByPromoterMultipleUse() { return findElement("chkCodeTypeFilterDeliveryChargesByPromoterMultipleUse", LocatorType.Id); }

    public WebElement CheckBox_HideExpired() { return findElement("chkHideExpired", LocatorType.Id); }

    public WebElement CheckBox_DateFilterAll() { return findElement("chkDateFilterAll", LocatorType.Id); }

    public WebElement CheckBox_DateFilterSevenDays() { return findElement("chkDateFilterSevenDays", LocatorType.Id); }

    public WebElement CheckBox_DateFilterThirtyDays() { return findElement("chkDateFilterThirtyDays", LocatorType.Id); }

    public WebElement CheckBox_DateFilterDateRange() { return findElement("chkDateFilterDateRange", LocatorType.Id); }

    public WebElement TextBox_FromDate() { return findElement("FromDate", LocatorType.Id); }

    public WebElement TextBox_ToDate() { return findElement("ToDate", LocatorType.Id); }

    public WebElement Button_Apply() { return findElement("applyFilter", LocatorType.Id); }

    public WebElement Button_Reset() { return findElement("//button[text()='Reset']", LocatorType.XPath); }

    public WebElement Label_SelectPromoCodeType() { return findElement("//label[contains(text(),'Select Promo Code Type:')]", LocatorType.XPath);}

    public WebElement Label_PromoCodeExpiryDateErrorContainer() { return findElement("PromoCodeItem_ExpiryDate-error", LocatorType.Id);}

    public WebElement Label_NoPromoCodesFound(){ return findElement("//h5[@class='margintop11'][contains(text(),'No promo codes found.')]", LocatorType.XPath);}

    //BOC
    public WebElement Button_PreviousPage() { return findElement("//a[@id='link_Prev']/span[1]", LocatorType.XPath); }

    public WebElement Button_NextPage() { return findElement("//a[@id='link_Next']/span[1]", LocatorType.XPath); }
    //EOC

}