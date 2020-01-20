package com.bungii.android.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.CustomersPage;
import com.bungii.android.pages.admin.DashBoardPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.bungii.common.manager.ResultManager.error;

public class CustomersSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(com.bungii.android.stepdefinitions.admin.DashBoardSteps.class);

    ActionManager action = new ActionManager();
    DashBoardPage dashBoardPage=new DashBoardPage();
    CustomersPage customersPage=new CustomersPage();


    @And("^I Search for customer$")
    public void i_search_for_customer() throws Throwable {
        try {
            action.sendKeys(customersPage.TextBox_SearchCustomer(), (String) cucumberContextManager.getScenarioContext("FIRST_NAME") + Keys.ENTER);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I verify the trip count$")
    public void i_verify_the_trip_count() throws Throwable {
        try {
            String firstName= (String) cucumberContextManager.getScenarioContext("FIRST_NAME");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            LocalDateTime now = LocalDateTime.now();
            int count=1;
            String Xpath =String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')][1]",firstName,now,count);
            testStepAssert.isElementDisplayed(SetupManager.getDriver().findElement(By.xpath(Xpath)), Xpath + "Element should be displayed", Xpath + "Element is displayed", Xpath + "Element is not displayed");
            action.click(dashBoardPage.Button_Customers());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
