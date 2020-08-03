package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class Admin_CustomersPageSteps extends DriverBase {
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_BusinessUsersSteps.class);
    Admin_CustomerPage admin_customerPage = new Admin_CustomerPage();
    Admin_DriversPage admin_driversPage = new Admin_DriversPage();
    Admin_TripsPage admin_tripsPage = new Admin_TripsPage();
    Admin_DashboardPage admin_dashboardPage = new Admin_DashboardPage();
    Admin_LiveTripsPage admin_liveTripsPage = new Admin_LiveTripsPage();
    Admin_ScheduledTripsPage admin_scheduledTripsPage = new Admin_ScheduledTripsPage();

    @When("^I enter \"([^\"]*)\" \"([^\"]*)\" in the \"([^\"]*)\" box$")
    public void i_enter_something_something_in_the_something_box(String strArg1, String strArg2, String strArg3) throws Throwable {
        String customerFirstName = PropertyUtility.getDataProperties("web.customer.firstname");
        cucumberContextManager.setScenarioContext("CUSTFIRSTNAME", customerFirstName);
        String customerLastName = PropertyUtility.getDataProperties("web.customer.lastname");
        cucumberContextManager.setScenarioContext("CUSTLASTNAME", customerLastName);

        String driverFirstName = PropertyUtility.getDataProperties("web.driver.firstname");
        cucumberContextManager.setScenarioContext("DRIVERFIRSTNAME", driverFirstName);
        String driverLastName = PropertyUtility.getDataProperties("web.driver.lastname");
        cucumberContextManager.setScenarioContext("DRIVERLASTNAME", driverLastName);

        switch (strArg3) {
            case "Customers search":
                switch (strArg2) {
                    case "first name":
                        action.clearSendKeys(admin_customerPage.TextBox_SearchCustomer(), customerFirstName + Keys.ENTER);
                        Thread.sleep(2000);
                        break;

                    case "last name":
                        action.clearSendKeys(admin_customerPage.TextBox_SearchCustomer(), customerLastName + Keys.ENTER);
                        Thread.sleep(2000);
                        break;
                }
                break;

            case "Drivers search":

                switch (strArg2) {
                    case "first name":
                        action.clearSendKeys(admin_driversPage.Textbox_SearchCriteria(), driverFirstName + Keys.ENTER);
                        Thread.sleep(2000);
                        break;

                    case "last name":
                        action.clearSendKeys(admin_driversPage.Textbox_SearchCriteria(), driverLastName + Keys.ENTER);
                        Thread.sleep(2000);
                        break;
                }
                break;

            case "Trips search":
                Select dropdown = new Select(admin_tripsPage.DropDown_SearchForPeriod());
                dropdown.selectByVisibleText("The Beginning of Time");
                switch (strArg2) {
                    case "first name":
                        if (strArg1.equalsIgnoreCase("customers")) {
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), customerFirstName + Keys.ENTER);
                        } else {
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), driverFirstName + Keys.ENTER);
                        }
                        Thread.sleep(2000);
                        break;

                    case "last name":
                        if (strArg1.equalsIgnoreCase("customers")) {
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), customerLastName + Keys.ENTER);
                        } else {
                            action.clearSendKeys(admin_tripsPage.TextBox_Search(), driverLastName + Keys.ENTER);
                        }
                        Thread.sleep(2000);
                        break;
                }
                break;
            case "Dashboard search":
                switch (strArg2){
                    case "first name":
                        if(strArg1.equalsIgnoreCase("customers")){
                            action.clearSendKeys(admin_dashboardPage.TextBox_SearchCustomer(),customerFirstName + Keys.ENTER);
                        }else {
                            action.clearSendKeys(admin_dashboardPage.Textbox_DriverSearch(), driverFirstName + Keys.ENTER);
                        }
                        Thread.sleep(2000);
                        break;
                    case "last name":
                        if(strArg1.equalsIgnoreCase("customers")){
                            action.clearSendKeys(admin_customerPage.TextBox_SearchCustomer(), customerLastName + Keys.ENTER);
                        }else {
                            action.clearSendKeys(admin_driversPage.Textbox_SearchCriteria(), driverLastName + Keys.ENTER);
                        }
                        Thread.sleep(1000);
                        break;
                }
                break;
                }
        }


    @Then("^I should see \"([^\"]*)\" listed on the \"([^\"]*)\" page$")
    public void i_should_see_something_listed_on_the_something_page(String strArg1, String page) throws Throwable {
        String Xpath =null;
        if (page.equalsIgnoreCase("Customers")) {
            switch (strArg1) {
                case "customer first name":
                    String custFirstName = (String) cucumberContextManager.getScenarioContext("CUSTFIRSTNAME");
                    Xpath = String.format("//tr[@class='clickable-row'][1]/td[contains(.,'%s')]", custFirstName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Customer's first name should be listed in grid.",
                            "Customer's first name is listed in grid.",
                            "Customer's first name is not listed in grid.");
                    break;

                case "customer last name":
                    String custLastName = (String) cucumberContextManager.getScenarioContext("CUSTLASTNAME");
                    Xpath = String.format("//tr[@class='clickable-row'][1]/td[contains(.,'%s')]", custLastName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Customer's last name should be listed in grid.",
                            "Customer's last name is listed in grid.",
                            "Customer's last name is not listed in grid.");
                    break;
            }
        }
        else if (page.equalsIgnoreCase("Drivers"))
        {
            switch (strArg1) {
                case "driver first name":
                    String driverFirstName = (String) cucumberContextManager.getScenarioContext("DRIVERFIRSTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", driverFirstName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Driver's first name should be listed in grid.",
                            "Driver's first name is listed in grid.",
                            "Driver's first name is not listed in grid.");
                    break;

                case "driver last name":
                    String driverLastName = (String) cucumberContextManager.getScenarioContext("DRIVERLASTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", driverLastName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Driver's last name should be listed in grid.",
                            "Driver's last name is listed in grid.",
                            "Driver's last name is not listed in grid.");
                    break;
            }
        }
        else if (page.equalsIgnoreCase("Trips"))
        {
            switch (strArg1) {
                case "customer first name":
                    String custFirstName = (String) cucumberContextManager.getScenarioContext("CUSTFIRSTNAME");
                    Xpath = String.format("//tbody[@id='TripListsTBody']/tr[@class='clickable-row'][1]/td[contains(.,'%s')]", custFirstName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Customer's first name should be listed in grid.",
                            "Customer's first name is listed in grid.",
                            "Customer's first name is not listed in grid.");
                    break;

                case "customer last name":
                    String custLastName = (String) cucumberContextManager.getScenarioContext("CUSTLASTNAME");
                    Xpath = String.format("//tbody[@id='TripListsTBody']/tr[@class='clickable-row'][1]/td[contains(.,'%s')]", custLastName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Customer's last name should be listed in grid.",
                            "Customer's last name is listed in grid.",
                            "Customer's last name is not listed in grid.");
                    break;

                case "driver first name":
                    String driverFirstName = (String) cucumberContextManager.getScenarioContext("DRIVERFIRSTNAME");
                    Xpath = String.format("//tr[@class='clickable-row'][1]/td[contains(.,'%s')]", driverFirstName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Driver's first name should be listed in grid.",
                            "Driver's first name is listed in grid.",
                            "Driver's first name is not listed in grid.");
                    break;

                case "driver last name":
                    String driverLastName = (String) cucumberContextManager.getScenarioContext("DRIVERLASTNAME");
                    Xpath = String.format("//tr[@class='clickable-row'][1]/td[contains(.,'%s')]", driverLastName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Driver's last name should be listed in grid.",
                            "Driver's last name is listed in grid.",
                            "Driver's last name is not listed in grid.");
                    break;
            }
        }
        else if (page.equalsIgnoreCase("Dashboard"))
        {
            switch (strArg1) {
                case "customer first name":
                    String custFirstName = (String) cucumberContextManager.getScenarioContext("CUSTFIRSTNAME");
                    Xpath = String.format("//tr[@class='clickable-row'][1]/td[contains(.,'%s')]", custFirstName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Customer's first name should be listed in grid.",
                            "Customer's first name is listed in grid.",
                            "Customer's first name is not listed in grid.");
                    break;

                case "customer last name":
                    String custLastName = (String) cucumberContextManager.getScenarioContext("CUSTLASTNAME");
                    Xpath = String.format("//tr[@class='clickable-row'][1]/td[contains(.,'%s')]", custLastName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Customer's last name should be listed in grid.",
                            "Customer's last name is listed in grid.",
                            "Customer's last name is not listed in grid.");
                    break;

                case "driver first name":
                    String driverFirstName = (String) cucumberContextManager.getScenarioContext("DRIVERFIRSTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", driverFirstName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Driver's first name should be listed in grid.",
                            "Driver's first name is listed in grid.",
                            "Driver's first name is not listed in grid.");
                    break;

                case "driver last name":
                    String driverLastName = (String) cucumberContextManager.getScenarioContext("DRIVERLASTNAME");
                    Xpath = String.format("//tr[1]/td[contains(.,'%s')]", driverLastName);

                    testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)),
                            "Driver's last name should be listed in grid.",
                            "Driver's last name is listed in grid.",
                            "Driver's last name is not listed in grid.");
                    break;
            }
        }
    }
}
