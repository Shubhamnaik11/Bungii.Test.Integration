package com.bungii.android.utilityfunctions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.bungii.*;
import com.bungii.android.pages.bungiiCustomer.*;
import com.bungii.android.pages.menus.*;
import com.bungii.common.core.DriverBase;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;

public class GeneralUtility extends DriverBase {
    static final double MIN_COST = 39;
    ActionManager action = new ActionManager();
    LoginPage Page_Login = new LoginPage();
    SignupPage Page_Signup = new SignupPage();
    TermsPage Page_CustTerms = new TermsPage();
    CustomerHomePage Page_CustHome = new CustomerHomePage();
    MenuPage Page_Menu = new MenuPage();
    /**
     * Calculate estimate cost of trip check if less than minimum cost then
     * return minimum cost
     *
     * @param tripDistance
     * @param loadTime
     * @param estTime
     * @param Promo
     * @return
     */
    public double bungiiEstimate(String tripDistance, String loadTime, String estTime, String Promo) {

        double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double loadUnloadTime = Double.parseDouble(loadTime.replace(" mins", ""));
        double tripTime = Double.parseDouble(estTime);

        double estimate = distance + loadUnloadTime + tripTime;
        estimate = estimate > MIN_COST ? estimate : MIN_COST;

        return estimate;
    }

    public void loginToCustomerApp(String phone, String password)
    {
        action.click(Page_Signup.Link_Login());
        action.sendKeys(Page_Login.TextField_PhoneNumber(), phone);
        action.sendKeys(Page_Login.TextField_Password(), password);
        action.click(Page_Login.Button_Login());
        if (action.isElementPresent(Page_CustTerms.Checkbox_Agree()))
        {
            action.click(Page_CustTerms.Checkbox_Agree());
            action.click(Page_CustTerms.Button_Continue());
            if (action.isElementPresent(Page_CustTerms.Popup_PermissionsMessage()))
            {
                action.click(Page_CustTerms.Button_PermissionsAllow());
            }
        }
        //AssertionManager.ElementDisplayed(Page_CustHome.Title_HomePage);
        //AssertionManager.ElementDisplayed(Page_CustHome.Link_Invite);
    }

    public void LogoutCustomerApp()
    {
        if(action.isElementPresent(Page_CustHome.Link_Menu()))
        {
            action.click(Page_CustHome.Link_Menu());
            action.click(Page_Menu.Menu_Logout());
        }
    }
    public void selectAddress(WebElement element, String searchstring)
    {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        action.clear(element);
        element.click();
        element.sendKeys(searchstring);
        int x = element.getLocation().getX()+32;
        int y = element.getLocation().getY()+176;

        new TouchAction(driver).tap(new PointOption().withCoordinates(x, y)).release().perform();
    }
}
