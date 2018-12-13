package com.bungii.android.pages.menus;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class SupportPage extends PageBase {

    public WebElement Header_SupportPage() { return findElement("//android.widget.TextView[@text='SUPPORT']", LocatorType.XPath); }

    public WebElement Title() { return findElement("com.bungii.customer:id/feedback_text_view_title", LocatorType.Id); }

    public WebElement TextField() { return findElement("com.bungii.customer:id/feedback_edit_text", LocatorType.Id); }

    public WebElement Button_Send() { return findElement("com.bungii.customer:id/feedback_send_button", LocatorType.Id); }

    public WebElement Error_Blank() { return findElement("com.bungii.customer:id/textinput_error", LocatorType.Id); }

}
