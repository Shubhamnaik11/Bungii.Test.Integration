package com.bungii.android.pages.menus;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class FAQPage extends PageBase {

    //------Title-------------------------------------------------------------------------------
    public WebElement Header_FAQPage() { return findElement("//android.widget.TextView[@text='FAQ']", LocatorType.XPath); }

    //------Page Elements------------------------------------------------------------------------
    public WebElement FAQ_BungiiLogo() { return findElement("com.bungii.customer:id/promo_code_label", LocatorType.Id); }

    public WebElement FAQ_Image() { return findElement("", LocatorType.Id); }

    public WebElement FAQ_TitleImage() { return findElement("", LocatorType.Id); }

    public WebElement FAQ_AppFAQTitle() { return findElement("", LocatorType.Id); }

    public WebElement FAQ_FirstQuestion() { return findElement("//android.view.View[@index='2' and @content-desc='I have a question. + ' and @instance='20']", LocatorType.XPath); }

    public WebElement FAQ_FirstAnswer() { return findElement("//android.view.View[@content-desc='We are more than happy to help. Please text/call customer support at (913) 353-6683.' and @instance='23']", LocatorType.XPath); }

    public WebElement FAQ_LastQuestion() { return findElement("-h2-data-preserve-html-node-true-style-font-size-20px-what-if-there-s-an-issue-during-my-bungii-trip-h-data-preserve-html-node-true-style-float-right-h-hr-data-preserve-html-node-true-style-width-100-h2-", LocatorType.Id); }

    public WebElement FAQ_LastAnswer() { return findElement("//android.view.View[@content-desc='Please contact text support at (913) 353-6683 or email us at support@bungii.com immediately.' and @instance='54']", LocatorType.XPath); }

    public WebElement FAQ_TwitterLogo() { return findElement("//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][1]", LocatorType.XPath); }

    public WebElement FAQ_InstagramLogo () { return findElement("//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][2]", LocatorType.XPath); }

    public WebElement FAQ_FBLogo () { return findElement("//android.webkit.WebView[@content-desc='App FAQ']/android.view.View[4]/android.view.View[5]/android.view.View/android.view.View[@content-desc='bungiiapp'][3]", LocatorType.XPath); }
}
