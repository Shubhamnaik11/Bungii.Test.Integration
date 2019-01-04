package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_DashboardPage extends PageBase {

    public WebElement RecentDriverRegistrations() { return findElement("//*[@id='GeofenceDashboard']//h4/text()='Recent driver Registrations'", LocatorType.XPath); }

    public List<WebElement> PendingVerification() { return findElements("//td[text()='Pending Verification']/following-sibling::td[2]/a", LocatorType.XPath); }

}
