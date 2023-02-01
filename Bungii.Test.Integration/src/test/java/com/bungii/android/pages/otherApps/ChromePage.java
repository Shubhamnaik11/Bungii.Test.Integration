package com.bungii.android.pages.otherApps;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class ChromePage extends PageBase {
    //Mobile Actions
    public WebElement Icon_Swipe() { return findElement("//android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View[1]/android.view.View", LocatorType.XPath);}
    public WebElement Swipe_EndPoint() { return findElement("//android.view.ViewGroup/android.view.ViewGroup/com.android.launcher3.widget.LauncherAppWidgetHostView[1]/android.widget.RelativeLayout/android.widget.RelativeLayout", LocatorType.XPath);}
    public WebElement Textbox_SearchBar() { return findElement("//android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.EditText", LocatorType.XPath);}
    public WebElement Icon_Chrome() { return findElement("//android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.TextView[1]", LocatorType.XPath);}
    public WebElement Textbox_GoogleSearchBar() { return findElement("//android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.EditText", LocatorType.XPath);}
    public WebElement DropDown_FirstValue() { return findElement("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.LinearLayout", LocatorType.XPath);}

    //Partner Login
    public WebElement Textbox_EnterPassword() { return findElement("//android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText", LocatorType.XPath);}
    public WebElement Button_SignIn() { return findElement("//android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.Button", LocatorType.XPath);}
///hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.widget.Button
}
