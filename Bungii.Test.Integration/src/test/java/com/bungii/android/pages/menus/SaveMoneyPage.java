package com.bungii.android.pages.menus;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SaveMoneyPage extends PageBase {
    public WebElement Header_SavePage () { return findElement("//android.widget.TextView[@text='SAVE MONEY']", LocatorType.XPath); }   //places_autocomplete_pickup_location is actual id

    public WebElement Textfield_PromoCode () { return findElement("com.bungii.customer:id/field_promo_code", LocatorType.Id); }

    public WebElement Button_Add () { return findElement("//TextInputLayout[@resource-id='com.bungii.customer:id/field_promo_code_layout']/following-sibling::android.widget.Button", LocatorType.XPath); }


    public WebElement Button_AddPromoPage () { return findElement("android.widget.Button", LocatorType.ClassName); }

    public WebElement Button_GetMoreMoney () { return findElement("com.bungii.customer:id/btn_get_more_money", LocatorType.Id); }

    public WebElement SaveMoney_PromoCode1 () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label'][1]", LocatorType.XPath); }

    public WebElement SaveMoney_PromoCode2 () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label'][2]", LocatorType.XPath); }

    public WebElement SaveMoney_PromoCode3 () { return findElement("//android.widget.TextView[@resource-id='com.bungii.customer:id/promo_code_label'][3]", LocatorType.XPath); }
}
