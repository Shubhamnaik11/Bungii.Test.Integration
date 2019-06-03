package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class HomePage extends PageBase {

    public WebElement Generic_Element (boolean... ignoreException) { return findElement("//*[contains(@resource-id,\"com.bungii.customer\")]", LocatorType.XPath,ignoreException); }


    public WebElement Button_NavigationBar () { return findElement("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]", LocatorType.XPath); }

    public WebElement Textfield_PickupLocation () { return findElements("//android.widget.EditText[@resource-id='com.bungii.customer:id/autocomplete_textview']",LocatorType.XPath).get(0); }

    public WebElement Textfield_DropoffLocation () { return findElements("//android.widget.EditText[@resource-id='com.bungii.customer:id/autocomplete_textview']",LocatorType.XPath).get(1); }

    public WebElement Textfield_ActualDropoffLocation () { return findElement("places_autocomplete_dropoff_location",LocatorType.Id); }

    //------Title-----------------------------------------------------------------------------------------
    public WebElement Title_HomePage (boolean ...ignoreException) { return findElement("com.bungii.customer:id/toolbar_main_title",LocatorType.Id,ignoreException); }

    //------Text-----------------------------------------------------------------------------------------
    public WebElement Text_ETAvalue (boolean ... ignoreException) { return findElement("com.bungii.customer:id/eta_bar_textview_estimate",LocatorType.Id,ignoreException); }

    //------Buttons---------------------------------------------------------------------------------------
    public WebElement Link_Invite () { return findElement("com.bungii.customer:id/menu_invite",LocatorType.Id); }

    public WebElement Button_Locator () { return findElement("//android.widget.ImageView[@content-desc='My Location']",LocatorType.XPath); }

    public WebElement Button_GetEstimate (boolean ...ignoreException) { return findElement("com.bungii.customer:id/pickup_location_get_estimate_button",LocatorType.Id,ignoreException); }

    public WebElement Switch_SoloDuo () { return findElement("com.bungii.customer:id/home_switch_noofdrivers",LocatorType.Id); }

    public WebElement Selector_Solo () { return findElement("com.bungii.customer:id/home_imageview_solo",LocatorType.Id); }

    public WebElement Selector_Duo () { return findElement("com.bungii.customer:id/home_imageview_duo",LocatorType.Id); }

    public WebElement Button_MyLocation () { return findElement("My Location",LocatorType.Id); }

    public WebElement MapPanningArea () { return findElement("android.widget.FrameLayout",LocatorType.ClassName); }

    public WebElement Button_ETASet () { return findElement("com.bungii.customer:id/eta_bar_button_set",LocatorType.Id); }
    public WebElement Text_ETAHeader(){return findElement("com.bungii.customer:id/eta_bar_title",LocatorType.Id);}
    public WebElement Header_HomePage (boolean ... ignoreException) { return findElement("//android.widget.TextView[@text='BUNGII']",LocatorType.XPath,ignoreException); }

    public WebElement Link_Slider () { return findElement("com.bungii.customer:id/header_textview_username",LocatorType.Id); }

    public WebElement Link_Menu () { return findElement("android.widget.ImageButton",LocatorType.ClassName); }

    public WebElement Button_Closetutorials (boolean ...ignoreException) { return findElement("com.bungii.customer:id/tutorials_alert_iv_cancel",LocatorType.Id,ignoreException); }
    public WebElement Text_TutorialPdf(){return findElement("com.bungii.customer:id/pdfView",LocatorType.Id);}
    //------Bungii Posted Success page---------------------------------------------------------------------
    public WebElement Title_Success () { return findElement("//android.view.View[@id='com.bungii.customer:id/action_bar']/android.widget.TextView[@text='Success!']",LocatorType.XPath); }

    public WebElement Image_Tick () { return findElement("//android.widget.FrameLayout[@id='android:id/content']/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView",LocatorType.XPath); }

    public WebElement Button_Done (boolean ... ignoreException) { return findElement("com.bungii.customer:id/bungii_posted_button_done",LocatorType.Id,ignoreException); }

    public WebElement Button_NavHome() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(0); }
    public WebElement Button_NavFAQ() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(1); }
    public WebElement Button_NavAccount() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(2); }
    public WebElement Button_NavSchBungii() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(3); }
    public WebElement Button_NavPayment() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(4); }
    public WebElement Button_NavSupport() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(5); }
    public WebElement Button_NavPromos() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(6); }
    public WebElement Button_NavDrives() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(7); }
    public WebElement Button_Navlogout() { return findElements("//*[@resource-id='com.bungii.customer:id/design_menu_item_text']",LocatorType.XPath).get(8); }


}
