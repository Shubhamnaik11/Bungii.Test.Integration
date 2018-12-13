package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_DashboardPage extends PageBase {

    public WebElement RecentDriverRegistrations() { return findElement("//*[@id='GeofenceDashboard']//h4/text()='Recent Driver Registrations'", LocatorType.XPath); }

    public WebElement PendingVerification() { return findElement("//*[@id='NewApplicantsTBody']//a[@href='/Admin/DriverDetailsByReference?driverReference=a93e6bcb-a076-41ed-ad31-78a42d891820 ']", LocatorType.XPath); }

}
