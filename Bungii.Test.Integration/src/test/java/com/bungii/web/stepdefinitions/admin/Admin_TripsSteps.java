package com.bungii.web.stepdefinitions.admin;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;


import java.util.List;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_TripsSteps extends DriverBase{

    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_BusinessUsersSteps.class);
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();

    @When("^I search by client name \"([^\"]*)\"$")
    public void i_search_by_client_name_something(String searchString) throws Throwable {
        action.selectElementByText(admin_TripsPage.DropDown_SearchForPeriod() , "The Beginning of Time" );
        action.sendKeys(admin_TripsPage.TextBox_Search() , searchString + Keys.ENTER);
        log("I search "+ searchString + "Client Name" ,
                "I have on searched "+ searchString+" Client Name", true);
    }

    @Then("^All the clients named \"([^\"]*)\" should be displayed on the trip list grid$")
    public void all_the_clients_named_something_should_be_displayed_on_the_trip_list_grid(String searchString) throws Throwable {
        try{
            for (WebElement e : admin_TripsPage.Client_names())
            {
                testStepAssert.isTrue(e.getText().contains(searchString),"Client Name contains "+ searchString, "Client Name is " + e.getText());
            }
        }
        catch (StaleElementReferenceException e){
        }
    }

    @When("^I click on \"([^\"]*)\" icon on \"([^\"]*)\" Page$")
    public void i_click_on_something_icon_on_something_page(String icon, String page) throws Throwable {
        action.selectElementByText(admin_TripsPage.DropDown_SearchForPeriod() , "The Beginning of Time" );
        switch(page)
        {
            case "Trips":
                switch (icon)
                {
                    case "Filter" :
                        action.click(admin_TripsPage.Button_Filter());
                        break;
                }
                break;
        }
    }

    @Then("^All statuses except \"([^\"]*)\" are selected$")
    public void all_statuses_except_something_are_selected(String status) throws Throwable {
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterPaymentUnsuccessful().isSelected(),"Checkbox Status Payment Successful should be selected","Checkbox Status Payment Successful is selected","Checkbox Status Payment Successful is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterPaymentSuccessful().isSelected(),"Checkbox Status Payment Unsuccessful should be selected","Checkbox Status Payment Unsuccessful is selected","Checkbox Status Payment Unsuccessful is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterCustomerCancelled().isSelected(),"Checkbox Status Customer Cancelled should be selected","Checkbox Status Customer Cancelled is selected","Checkbox Status Customer Cancelled is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterDriverCancelled().isSelected(),"Checkbox Status Driver Cancelled should be selected","Checkbox Status Driver Cancelled is selected","Checkbox Status Driver Cancelled is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterAdminCancelled().isSelected(),"Checkbox Status Admin Cancelled should be selected","Checkbox Status Admin Cancelled is selected","Checkbox Status Admin Cancelled is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterPickupWithError().isSelected(),"Checkbox Status Pickup With Error should be selected","Checkbox Status Pickup With Error is selected","Checkbox Status Pickup With Error is NOT selected");
        testStepAssert.isFalse(admin_TripsPage.CheckBox_FilterPriceEstimated().isSelected(),"Checkbox Status Price Estimated should NOT be selected","Checkbox Status Price Estimated is NOT selected","Checkbox Status Price Estimated is selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterDriversNotFound().isSelected(),"Checkbox Status Drivers Not Found should be selected","Checkbox Status Drivers Not Found is selected","Checkbox Status Drivers Not Found is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterDriverNotArrived().isSelected(),"Checkbox Status Drivers Not Arrived should be selected","Checkbox Status Drivers Not Arrived is selected","Checkbox Status Drivers Not Arrived is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterDriverRemoved().isSelected(),"Checkbox Status Drivers Removed should be selected","Checkbox Status Drivers Removed is selected","Checkbox Status Drivers Not Removed is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterPromoterPaymentPending().isSelected(),"Checkbox Status Promoter Payment Pending should be selected","Checkbox Status Promoter Payment Pending is selected","Checkbox Status Promoter Payment Pending is NOT selected");
    }

    @Then("^All types and categories are selected$")
    public void all_types_and_categories_are_selected() throws Throwable {
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterSolo().isSelected(),"Type Solo should be selected","Type Solo is selected","Type Solo is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterDuo().isSelected(),"Type Duo should be selected","Type Duo is selected","Type Duo is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterOnDemand().isSelected(),"Category On-Demand should be selected","Category On-Demand is selected","Category On-Demand is NOT selected");
        testStepAssert.isTrue(admin_TripsPage.CheckBox_FilterScheduled().isSelected(),"Category Scheduled should be selected","Category Scheduled is selected","Category Scheduled is NOT selected");
    }

    void uncheck_all_statuses()
    {
        if(admin_TripsPage.CheckBox_FilterPaymentUnsuccessful().isSelected())
            admin_TripsPage.CheckBox_FilterPaymentUnsuccessful().click();
        if(admin_TripsPage.CheckBox_FilterPaymentSuccessful().isSelected())
            admin_TripsPage.CheckBox_FilterPaymentSuccessful().click();
        if(admin_TripsPage.CheckBox_FilterCustomerCancelled().isSelected())
            admin_TripsPage.CheckBox_FilterCustomerCancelled().click();
        if(admin_TripsPage.CheckBox_FilterAdminCancelled().isSelected())
            admin_TripsPage.CheckBox_FilterAdminCancelled().click();
        if(admin_TripsPage.CheckBox_FilterDriverCancelled().isSelected())
            admin_TripsPage.CheckBox_FilterDriverCancelled().click();
        if(admin_TripsPage.CheckBox_FilterPickupWithError().isSelected())
            admin_TripsPage.CheckBox_FilterPickupWithError().click();
        if(admin_TripsPage.CheckBox_FilterPriceEstimated().isSelected())
            admin_TripsPage.CheckBox_FilterPriceEstimated().click();
        if(admin_TripsPage.CheckBox_FilterDriversNotFound().isSelected())
            admin_TripsPage.CheckBox_FilterDriversNotFound().click();
        if(admin_TripsPage.CheckBox_FilterDriverNotArrived().isSelected())
            admin_TripsPage.CheckBox_FilterDriverNotArrived().click();
        if(admin_TripsPage.CheckBox_FilterDriverRemoved().isSelected())
            admin_TripsPage.CheckBox_FilterDriverRemoved().click();
        if(admin_TripsPage.CheckBox_FilterPromoterPaymentPending().isSelected())
            admin_TripsPage.CheckBox_FilterPromoterPaymentPending().click();
    }

    @When("^I select filter \"([^\"]*)\" as \"([^\"]*)\"$")
    public void i_select_filter_something_as_something(String filter, String value) throws Throwable {
        switch (filter)
        {
            case "Statuses" :
                switch (value){
                    case "Payment Unsuccessful":
                        uncheck_all_statuses();
                        admin_TripsPage.CheckBox_FilterPaymentUnsuccessful().click();
                        break;
                    case("Payment Successful"):
                        admin_TripsPage.Button_Filter().click();
                        uncheck_all_statuses();
                        admin_TripsPage.CheckBox_FilterPaymentSuccessful().click();
                        break;
                    case ("Customer Cancelled"):
                        admin_TripsPage.Button_Filter().click();
                        uncheck_all_statuses();
                        admin_TripsPage.CheckBox_FilterCustomerCancelled().click();
                        break;
                    case ("Driver Cancelled"):
                        admin_TripsPage.Button_Filter().click();
                        uncheck_all_statuses();
                        admin_TripsPage.CheckBox_FilterDriverCancelled().click();
                        break;
                    case "Admin Cancelled":
                        admin_TripsPage.Button_Filter().click();
                        uncheck_all_statuses();
                        admin_TripsPage.CheckBox_FilterAdminCancelled().click();
                        break;
                    case "Pickup with Error":
                        admin_TripsPage.Button_Filter().click();
                        uncheck_all_statuses();
                        admin_TripsPage.CheckBox_FilterPickupWithError().click();
                        break;
                    case "Price Estimated":
                        admin_TripsPage.Button_Filter().click();
                        uncheck_all_statuses();
                        admin_TripsPage.CheckBox_FilterPriceEstimated().click();
                        break;
                    case "Driver(s) Not Found":
                        admin_TripsPage.Button_Filter().click();
                        uncheck_all_statuses();
                        admin_TripsPage.CheckBox_FilterDriversNotFound().click();
                        break;
                    case "Driver Not Arrived":
                        admin_TripsPage.Button_Filter().click();
                        uncheck_all_statuses();
                        admin_TripsPage.CheckBox_FilterDriverNotArrived().click();;
                        break;
                    case "Driver Removed":
                        admin_TripsPage.Button_Filter().click();
                        uncheck_all_statuses();
                        admin_TripsPage.CheckBox_FilterDriverRemoved().click();;
                        break;
                    case "Promoter Payment Pending":
                        admin_TripsPage.Button_Filter().click();
                        uncheck_all_statuses();
                        admin_TripsPage.CheckBox_FilterPromoterPaymentPending().click();;
                        break;
                }
                break;

            case "Type":
                switch (value){
                    case "Solo":
                        admin_TripsPage.Button_Filter().click();
                        admin_TripsPage.CheckBox_FilterPaymentSuccessful().click();
                        admin_TripsPage.CheckBox_FilterDuo().click();
                        break;
                    case "Duo" :
                        admin_TripsPage.Button_Filter().click();
                        admin_TripsPage.CheckBox_FilterSolo().click();
                        admin_TripsPage.CheckBox_FilterDuo().click();
                        break;
                }
                break;

            case "Category":
                switch (value){
                    case "On-Demand":
                        admin_TripsPage.Button_Filter().click();
                        admin_TripsPage.CheckBox_FilterSolo().click();
                        admin_TripsPage.CheckBox_FilterScheduled().click();
                        break;
                    case "Scheduled":
                        admin_TripsPage.Button_Filter().click();
                        admin_TripsPage.CheckBox_FilterScheduled().click();
                        admin_TripsPage.CheckBox_FilterOnDemand().click();
                        break;
                }
                break;

        }
    }

    @Then("^the triplist grid shows the results by type \"([^\"]*)\"$")
    public void the_triplist_grid_shows_the_results_by_type_something(String filter) throws Throwable {
        String xpath = null;
        List<WebElement> rowswithstatus = null;
        List<WebElement> rows = null;
        switch (filter){
            case "Payment Unsuccessful Status" :
                xpath = String.format("//td[text()='Payment Pending']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Payment Successful Status" :
                xpath = String.format("//td[text()='Payment Successful']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Customer Cancelled Status" :
                xpath = String.format("//td[text()='Customer Cancelled']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Driver Cancelled Status":
                xpath = String.format("//td[text()='Driver Cancelled']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Admin Cancelled Status":
                xpath = String.format("//td[text()='Admin Cancelled']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Pickup with Error Status":
                xpath = String.format("//td[9][text()='Pickup with Error' | text() = 'Unable To Estimate']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Price Estimated Status":
                xpath = String.format("//td[text()='Price Estimated']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Driver(s) Not Found Status":
                xpath = String.format("//td[text()='Driver(s) Not Found']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Driver Not Arrived Status":
                xpath = String.format("//td[text()='Driver Not Arrived']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Driver Removed Status":
                xpath = String.format("//td[text()='Driver Removed']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Promoter Payment Pending Status":
                xpath = String.format("//td[text()='Promoter Payment Pending']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Solo Type" :
                xpath = String.format("//td[3][text()='Solo']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Duo Type" :
                xpath = String.format("//td[3][text()='Duo']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "On-Demand Category" :
                xpath = String.format("//td[4][text()='On-Demand']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
            case "Scheduled Category" :
                xpath = String.format("//td[4][text()='Scheduled']");
                rowswithstatus = SetupManager.getDriver().findElements(By.xpath(xpath));
                rows = SetupManager.getDriver().findElements(By.xpath("//tr"));
                testStepAssert.isEquals(String.valueOf(rows.size()-1),String.valueOf(rowswithstatus.size()),filter + " records should be displayed",filter + " records is displayed", filter + " records is not displayed");
                break;
        }
    }
}


