package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Partner_DashboardPage extends PageBase {

    //Partner Get Estimate Header
    public WebElement Label_Get_Estimate_Header() { return findElement("//h1[contains(text(),'Get Estimate')]", LocatorType.XPath); }

    //Start Over
    public WebElement Label_Start_Over() { return findElement("//span[contains(text(),'Start Over')]",LocatorType.XPath);}

    //Logout button
    public WebElement Button_Partner_LogOut(){ return findElement("//a[@class='header-btn logout-btn']", LocatorType.XPath); }

    //Solo Radio Button
    public WebElement RadioButton_Partner_Solo() {return findElement("solo",LocatorType.Id);}

    //Duo Radio Button
    public WebElement RadioButton_Partner_Duo() {return findElement("duo",LocatorType.Id);}

    //Pickup Edit button
    public WebElement Button_Pickup_Edit() { return findElement("//span[@class='link']",LocatorType.XPath);}

    //Pickup Clear button X
    public WebElement Button_PickupClear() { return findElement("pickupAddCloseIcon",LocatorType.Id);}

    //Pickup Address in edit
    public WebElement Dropdown_Pickup_Address() { return findElement("pickValue",LocatorType.Id);}

    //Pickup Address in non edit
    public WebElement Text_Pickup_Address() { return findElement("//label[@class='pickup form-label']/following::address",LocatorType.XPath);}

    //Pickup Address List
    public WebElement List_Pickup_Address() { return findElement("//div[contains(@class,'pac-container pac-logo')]/div[1]/span[2]",LocatorType.XPath);}

    //Set Pickup Address
    public WebElement SetPickupAddress() { return findElement("//input[@id='pickupAdd']",LocatorType.XPath);}

    //Delivery Clear button X
    public WebElement Button_DeliveryClear() { return findElement("dropoffAddCloseIcon",LocatorType.Id );}

    //Delivery Address
    public WebElement Dropdown_Delivery_Address() { return findElement("dropValue",LocatorType.Id);}

    //Delivery Address List
    public WebElement List_Delivery_Address() { return findElement("//div[contains(@class,'pac-container pac-logo')]/div[1]/span[2]",LocatorType.XPath);}

    //Driver Helper Carry checkbox
    public WebElement Checkbox_Driver_HelperCarry() { return findElement("//input[@id='DriverHelpCarryFields']",LocatorType.XPath);}

    //Set Delivery Address
    public WebElement SetDeliveryAddress() { return findElement("//input[@id='dropoffAdd']",LocatorType.XPath);}

    //Load Upload Time Dropdown
    public WebElement Dropdown_Load_Unload_Time() { return findElement("//div[contains(@class,'MuiFormControl-root load-time')]//div[contains(@class,'MuiInputBase-formControl')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_15() { return findElement("//li[contains(text(),'15 minutes')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_30() { return findElement("//li[contains(text(),'30 minutes')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_45() { return findElement("//li[contains(text(),'45 minutes')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_60() { return findElement("//li[contains(text(),'60 minutes')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_75() { return findElement("//li[contains(text(),'75 minutes')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_90() { return findElement("//li[contains(text(),'90+ minutes')]",LocatorType.XPath);}


    //Pickup Date dropdown
    public WebElement Dropdown_Pickup_Date() { return findElement("//div[@class='MuiFormControl-root pickup-date']",LocatorType.XPath);}

    //Today and 4 days
    public WebElement Pickup_Date() { return findElement("//label[contains(text(),'Pickup Date')]/following::div[1]",LocatorType.XPath);}
    public WebElement Pickup_Date_Today() { return findElement("//li[contains(@class,'MuiButtonBase-root MuiListItem-root MuiMenuItem-root Mui-selected MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button Mui-selected')]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_1() { return findElement("//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'][1]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_2() { return findElement("//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'][2]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_3() { return findElement("//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'][3]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_4() { return findElement("//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'][4]",LocatorType.XPath);}
    public WebElement Pickup_date_Tomorrow() { return findElement("//li[contains(text(),'Tomorrow')]",LocatorType.XPath);}

    //Pickup Time dropdown
    public WebElement Dropdown_Pickup_Time() { return findElement("//div[@class='MuiFormControl-root pickup-time']",LocatorType.XPath);}
    public WebElement Pickup_Time1() { return findElement("//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'][1]",LocatorType.XPath);}

    //Get Estimate Button
    public WebElement Button_Get_Estimate() { return findElement("get-estimate",LocatorType.Id);}

    //Estimated Cost label
    public WebElement Label_Estimated_Cost() { return findElement("//label[contains(text(),'Estimated Cost: ')]",LocatorType.XPath);}

    //Continue button
    public  WebElement Button_Continue() { return findElement("//a[@class='btn']",LocatorType.XPath);}

    //Blank message for pickup address
    public WebElement Message_Blank_Pickup() { return findElement("//div[contains(text(),'Pickup Address is required.')]",LocatorType.XPath);}

    //Blank message for delivery address
    public WebElement Message_Blank_Delivery() { return findElement("//div[contains(text(),'Drop Off Address is required.')]",LocatorType.XPath);}

    //Blank message for load unload time
    public WebElement Message_Blank_LoadUnload_Time() { return findElement("//div[contains(text(),'Load Time is required.')]",LocatorType.XPath);}

    //Highlighted fields message
    public WebElement Message_Highlighted_Fields() { return findElement("//div[contains(text(),'Please verify the highlighted fields above.')]",LocatorType.XPath);}

    //Information Icon What’s needed?
    public  WebElement Information_Icon_Whats_Needed() { return findElement("//label[contains(text(),'What’s needed?')]/a",LocatorType.XPath);}

    //Information Icon Delivery Address
    public  WebElement Information_Icon_Delivery_Address() { return findElement("//label[contains(text(),'Delivery Address')]/a",LocatorType.XPath);}

    //Information Icon Load + Unload Time
    public  WebElement Information_Icon_LoadUpload() { return findElement("//label[contains(text(),'Load + Unload Time')]/a",LocatorType.XPath);}

    //Information Icon Pickup Date
    public  WebElement Information_Icon_Pickup_Date() { return findElement("//label[contains(text(),'Pickup Date')]/a",LocatorType.XPath);}

    //Inner text of each Information Icon
    public WebElement InnerText_Information_Icon() { return findElement("//div[@class='tooltip-inner']",LocatorType.XPath);}

    //Partner Logout button
    public WebElement Button_Partner_Logout() { return findElement("//a[@class='header-menu-btn logout-btn']",LocatorType.XPath);}

    //Service Level information icon
    public WebElement Information_Icon__Service_Level() { return findElement("//label[contains(text(),'Service Level')]//a",LocatorType.XPath);}

    //Service Level dropdown button
    public WebElement Dropdown_Service_Level() { return findElement("//div[@id='service-level-menu']",LocatorType.XPath);}

    //No service selected text
    public WebElement Text_No_Service() { return findElement("//div[@class='service-title']",LocatorType.XPath);}

    //Service Level text
    public WebElement Text_Service_Level() { return findElement("//h2[contains(text(),'Service Level')]",LocatorType.XPath);}

    //Curbside service level
    public WebElement Text_Curbside() { return findElement("//span[contains(text(),'Curbside')]",LocatorType.XPath);}
    public WebElement Radio_Button_Curbside() { return findElement("//span[@class='MuiButtonBase-root MuiIconButton-root jss72 MuiRadio-root MuiRadio-colorSecondary jss73 Mui-checked MuiIconButton-colorSecondary']//input[@name='radio-button-demo']",LocatorType.XPath);}

    //Threshold service level
    public WebElement Text_Threshold() { return findElement("//span[contains(text(),'Threshold')]",LocatorType.XPath);}
    public WebElement Radio_Button_Threshold() { return findElement("//div[2]//div[1]//span[1]//span[1]//input[1]",LocatorType.XPath);}

    //Room of Choice
    public WebElement Text_Room_of_Choice() { return findElement("//span[contains(text(),'Room of Choice')]",LocatorType.XPath);}

    //White Glove
    public WebElement Text_White_Glove() { return findElement("//span[contains(text(),'White Glove')]",LocatorType.XPath);}

    public WebElement Dropdown_ServiceLevel(String serviceLevel) { return findElement("//span[@class='service-title' and @data-name='"+serviceLevel+"']",LocatorType.XPath);}

    //Close button on service level
    public WebElement Button_close() { return findElement("//span[@class='modal-close']",LocatorType.XPath);}

    //Selected service name
    public WebElement Text_Service_Name() { return findElement("//span[@class='service-title']",LocatorType.XPath);}

    //Delivery Cost
    public WebElement Label_Delivery_Cost() { return findElement("//div/label[contains(text(),'Delivery Cost:')]",LocatorType.XPath);}
}
