package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_ReferralSourcePage extends PageBase {

    public WebElement Header_Source () { return findElement("//th[text()='Source']", LocatorType.XPath); }

    public WebElement Header_AccountsCreated () { return findElement("//th[text()='Accounts Created']", LocatorType.XPath); }

    public WebElement Header_PercentageOfTotalAC () { return findElement("//tr/th[3][text()='Percentage of total']", LocatorType.XPath); }

    public WebElement Header_TripsCompleted () { return findElement("//th[text()='Trips Completed']", LocatorType.XPath); }

    public WebElement Header_PercentageOfTotalTC () { return findElement("//tr/th[5][text()='Percentage of total']", LocatorType.XPath); }

    public WebElement Menu_ReferralSource(boolean... ignoreException) { return findElement("adminmenu-referralsource", LocatorType.Id,ignoreException); }

    public WebElement Title_ReferralSourcePage (boolean...ignoreException) { return findElement("//h4[@class='page-header' and text()='Referral Source']", LocatorType.XPath,ignoreException); }

    public WebElement Button_Search ( ) { return findElement("//button[text()='Search']", LocatorType.XPath); }

    public WebElement TextBox_FromDate ( ) { return findElement("FromDate", LocatorType.Name); }

    public WebElement TextBox_ToDate ( ) { return findElement("ToDate", LocatorType.Name); }

    public WebElement Label_FromDateError ( ) { return findElement("FromDate-error", LocatorType.Id); }

    public WebElement Label_ToDateError ( ) { return findElement("ToDate-error", LocatorType.Id); }

}