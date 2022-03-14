package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_EditScheduledBungiiPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_ReasonCodeSteps extends DriverBase {

    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_TripsSteps.class);
    Admin_EditScheduledBungiiPage admin_EditScheduledBungiiPage = new Admin_EditScheduledBungiiPage();


    @And("^I click on \"([^\"]*)\" in the dropdown$")
    public void i_click_on_something_in_the_dropdown(String dropdown) throws Throwable {
        try{
        switch (dropdown) {
            case "Customer initiated":
                    Select selectCustomer = new Select((WebElement) admin_EditScheduledBungiiPage.Dropdown_Result());
                    selectCustomer.selectByVisibleText("Customer initiated");
                    break;
            case "Partner initiated":
                    Select selectPartner = new Select((WebElement) admin_EditScheduledBungiiPage.Dropdown_Result());
                    selectPartner.selectByVisibleText("Partner initiated");
                    break;
            case "Delivery Details":
                    action.click(admin_EditScheduledBungiiPage.Button_Delivery_Details());
                    break;

            default: break;
        }
            log("I view "+dropdown+" in the dropdown",
                    "I could see "+dropdown+" in the dropdown", false);
        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @And("^I click on the \"([^\"]*)\" and select future time$")
    public void i_click_on_the_something_and_select_future_time(String scheduleDate) throws Throwable {
        try{
        switch (scheduleDate) {
            case "Time":
                    action.click(admin_EditScheduledBungiiPage.TimePicker_Time());
                    Thread.sleep(1000);
                    action.click(admin_EditScheduledBungiiPage.Dropdown_ScheduledDate_Time());
                    String timeChanged = admin_EditScheduledBungiiPage.TimePicker_Time().getText();
                    cucumberContextManager.setScenarioContext("Time_Changed", timeChanged);
                    break;
            case "Date":
                    action.click(admin_EditScheduledBungiiPage.DatePicker_ScheduledDate());
                    Thread.sleep(1000);
                    action.click(admin_EditScheduledBungiiPage.Changed_Date());
                    String dateChanged = admin_EditScheduledBungiiPage.DatePicker_ScheduledDate().getText();
                    cucumberContextManager.setScenarioContext("Date_Changed", dateChanged);
                    break;

            default: break;
        }
            log("I can select future time/date",
                    "I was able to change time/date to future time/date", false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }

    }

    @And("^I click on \"([^\"]*)\" for change time$")
    public void i_click_on_something_for_change_time(String strArg1) throws Throwable {

        try {
            action.click(admin_EditScheduledBungiiPage.Dropdown_Result());

            log("I can click on reason dropdown",
                    "I clicked on reason dropdown", false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @And("^I check if the \"([^\"]*)\" field is hidden$")
    public void i_check_if_the_something_field_is_hidden(String strArg1) throws Throwable {
        try {
            testStepAssert.isFalse(admin_EditScheduledBungiiPage.Dropdown_Result().isDisplayed(), "Reason dropdown should not be displayed", "Reason dropdown is displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on \"([^\"]*)\" for change time and check reason dropdown values$")
    public void i_click_on_something_for_change_time_and_check_reason_dropdown_values(String strArg1) throws Throwable {
        try {
            List<String> expectedOptions = new ArrayList() {{
                add("Select reason");
                add("Partner initiated");
                add("Customer initiated");
                add("No drivers available");
            }};
            action.click(admin_EditScheduledBungiiPage.Dropdown_Result());
            Select select = new Select(admin_EditScheduledBungiiPage.Dropdown_Result());
            List<String> Options = new ArrayList();
            List<WebElement> actualOptions = select.getOptions();
            int size = actualOptions.size();
            for(int i =0; i<size ; i++){
                String options = actualOptions.get(i).getText();
                Options.add(options);
            }
            testStepAssert.isTrue(Options.containsAll(expectedOptions),"Correct reasons need to be displayed","Correct reasons are displayed","Incorrect reasons are displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I check if the \"([^\"]*)\" field is present$")
    public void i_check_if_the_something_field_is_present(String strArg1) throws Throwable {
        try {
            testStepAssert.isFalse(action.isElementPresent(admin_EditScheduledBungiiPage.Dropdown_Result(true)),"Reasons should be displayed","Reasons is displayed", "Reasons is not displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on \"([^\"]*)\" and add \"([^\"]*)\" driver$")
    public void i_click_on_something_and_add_something_driver(String strArg1, String driverName) throws Throwable {
        try{
        action.click(admin_EditScheduledBungiiPage.TextBox_DriverSearch());
        action.sendKeys(admin_EditScheduledBungiiPage.TextBox_DriverSearch(),driverName);
        Thread.sleep(1000);
        action.sendKeys(admin_EditScheduledBungiiPage.TextBox_DriverSearch()," ");
        action.JavaScriptClick(admin_EditScheduledBungiiPage.Dropdown_Driver_Result(driverName));
        Thread.sleep(1000);

            log("I can add a driver on edit delivery page",
                    "I added a driver on edit delivery page", false);
        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I click \"([^\"]*)\" on scheduled delivery details page$")
    public void i_click_something_on_scheduled_delivery_details_page(String strArg1) throws Throwable {
        try {
            action.click(admin_EditScheduledBungiiPage.Changed_Time());
            Thread.sleep(5000);

            log("I can click on Change Time tab",
                    "I clicked on change time tab", false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^the updated time should be displayed on delivery details page$")
    public void the_updated_time_should_be_displayed_on_delivery_details_page() throws Throwable {
        try {
            String expectedTime = (String) cucumberContextManager.getScenarioContext("Time_Changed");
            String actualTime = action.getText(admin_EditScheduledBungiiPage.Changed_Time());
            testStepAssert.isTrue(actualTime.contains(expectedTime), "Correct time need to be display", "Correct time is display", "Incorrect time is displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^the updated date should be displayed on delivery details page$")
    public void the_updated_date_should_be_displayed_on_delivery_details_page() throws Throwable {
        try {
            String expectedDate = (String) cucumberContextManager.getScenarioContext("Date_Changed");
            String actualDate = action.getText(admin_EditScheduledBungiiPage.Changed_Time());
            testStepAssert.isTrue(actualDate.contains(expectedDate), "Correct date need to be display", "Correct date is display", "Incorrect date is displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

}
