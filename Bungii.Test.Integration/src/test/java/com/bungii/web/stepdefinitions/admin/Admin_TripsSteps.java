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
        switch(page)
        {
            case "Trips":
                switch (icon)
                {
                    case "Filter_Icon" :
                        action.click(admin_TripsPage.Button_Filter());
                        break;
                }
                break;
        }
    }

    @Then("^All statuses except \"([^\"]*)\" are selected$")
    public void all_statuses_except_something_are_selected(String status) throws Throwable {

    }
}


