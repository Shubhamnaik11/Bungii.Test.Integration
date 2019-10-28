package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_GetAllBungiiDriversPage extends PageBase {

    public WebElement TextBox_Search () { return findElement("SearchCriteria", LocatorType.Name); }

    public WebElement Button_Search () { return findElement("btnSearch", LocatorType.Id); }

    public WebElement GridRow_BungiiActiveDriver (String name) { return findElement("//td[contains(text(),'"+name+"')]/following-sibling::td[text()='Active']", LocatorType.XPath); }
    public WebElement GridRow_BungiiRejectedDriver (String name) { return findElement("//td[contains(text(),'"+name+"')]/following-sibling::td[text()='Rejected']", LocatorType.XPath); }
    public WebElement GridRow_BungiiResentToDriver (String name) { return findElement("//td[contains(text(),'"+name+"')]/following-sibling::td[text()='Re-sent to Driver']", LocatorType.XPath); }
    public WebElement GridRow_BungiiPendingVerification (String name) { return findElement("//td[contains(text(),'"+name+"')]/following-sibling::td[text()='Pending Verification']", LocatorType.XPath); }

    public WebElement GridRow_PendingVerificationLink (String name) { return findElement("//td[contains(text(),'"+name+"')]/following-sibling::td[text()='Pending Verification']/following-sibling::td[2]/a", LocatorType.XPath); }

}
