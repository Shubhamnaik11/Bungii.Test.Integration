package com.bungii.android.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class EarningsPage extends PageBase {
    public WebElement Header_DriverInfo() { return findElement("//android.view.View[@text='DRIVER INFO']", LocatorType.XPath); }
    public WebElement Text_MilesDriven() { return findElement("//android.view.View[contains(@text,'MILES') and contains(@text,'DRIVEN')]", LocatorType.XPath); }
    public WebElement Text_MilesDrivenValue() { return findElement("//android.view.View[contains(@text,'MILES') and contains(@text,'DRIVEN')]/following-sibling::android.view.View", LocatorType.XPath); }


    public WebElement Text_TotalEarning() { return findElement("//android.view.View[contains(@text,'TOTAL') and contains(@text,'EARNINGS')]", LocatorType.XPath); }
    public WebElement Text_TotalEarningValue() { return findElement("//android.view.View[contains(@text,'TOTAL') and contains(@text,'EARNINGS')]/following-sibling::android.view.View", LocatorType.XPath); }



    public WebElement Text_HourWorked() { return findElement("//android.view.View[contains(@text,'HOURS ') and contains(@text,'WORKED')]", LocatorType.XPath); }
    public WebElement Text_HourWorkedValue() { return findElement("//android.view.View[contains(@text,'HOURS ') and contains(@text,'WORKED')]/following-sibling::android.view.View", LocatorType.XPath); }



    public WebElement Text_TripCompleted() { return findElement("//android.view.View[contains(@text,'TRIPS') and contains(@text,'COMPLETED')]", LocatorType.XPath); }
    public WebElement Text_TripCompletedValue() { return findElement("//android.view.View[contains(@text,'TRIPS') and contains(@text,'COMPLETED')]/following-sibling::android.view.View", LocatorType.XPath); }


    public WebElement Text_TotalTips() { return findElement("//android.view.View[contains(@text,'TOTAL') and contains(@text,'TIPS')]", LocatorType.XPath); }
    public WebElement Text_TotalEarningTips() { return findElement("//android.view.View[contains(@text,'TOTAL') and contains(@text,'TIPS')]/following-sibling::android.view.View", LocatorType.XPath); }

    public WebElement Link_ItemisedEarning() { return findElement("//android.view.View[@content-desc='Click here to view itemized earnings Itemized Earnings']", LocatorType.XPath); }


    public WebElement Link_ItemisedEarnings() { return findElement("com.bungii.driver:id/activity_earnings_itemized_earnings_btn", LocatorType.Id); }

    public WebElement Button_ItemizedEarnings() { return findElement("com.bungii.driver:id/activity_earnings_itemized_earnings_btn", LocatorType.Id); }

    //public WebElement Text_HistoryDataTotalEarnings() { return findElement("//*[@resource-id='tblDriverTrips']/android.view.View[6]", LocatorType.XPath);}
    public WebElement Text_HistoryDataTotalEarnings() { return findElement("com.bungii.driver:id/appCompatTextView21", LocatorType.Id);}

    public WebElement Button_NavigateUp(){return findElement("//android.widget.ImageButton[contains(@content-desc,\"Navigate up\")]",LocatorType.XPath);}

    public WebElement Button_BranchWallet() {
        return findElement("com.bungii.driver:id/tv_branch_label", LocatorType.Id);
    }
    public WebElement Screen_DefaultBrowser() {
        return findElement("//android.widget.TextView[@text='Sign in to find the latest Android apps, games, movies, music, & more']", LocatorType.XPath);
    }

}
