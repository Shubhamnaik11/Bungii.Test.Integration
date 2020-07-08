package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Partner_LoginPage extends PageBase {

    //Partner Password Field
    public WebElement TextBox_PartnerLogin_Password() { return findElement("formBasicPassword", LocatorType.Id); }

    //Partner Sign In button
    public WebElement Sign_In() { return findElement("login", LocatorType.Id); }

    //Blank-Incorrect password on Sing In
    public WebElement Blank_Incorrect_Password_Msg() { return findElement("//div[@class='invalid-feedback d-block text-left p-l-18']", LocatorType.XPath);}

    //Body Tag
    public WebElement BodyTag() { return findElement("body",LocatorType.TagName);}

    //Incorrect password on Sing In
    //public WebElement Incorrect_Password_Msg() { return findElement("//div[@class='invalid-feedback d-block text-left p-l-18']", LocatorType.XPath);}

}
