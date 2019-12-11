package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.EarningsPage;
import com.bungii.android.stepdefinitions.Customer.HomeSteps;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EarningsMenuSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(HomeSteps.class);
    GeneralUtility utility = new GeneralUtility();
    EarningsPage earningsPage = new EarningsPage();
    ActionManager action = new ActionManager();

    @When("^I click on \"([^\"]*)\" hyperlink$")
    public void i_click_on_something_hyperlink(String strArg1) throws Throwable {

        action.click(earningsPage.Link_ItemisedEarnings());

    }

    @Then("^I am redirected to \"([^\"]*)\"$")
    public void i_am_redirected_to_something(String strArg1) throws Throwable {

        testStepAssert.isElementTextEquals(earningsPage.Text_HistoryData(), PropertyUtility.getDataProperties("history.data"),
                PropertyUtility.getDataProperties("history.data") + " is displayed",
                PropertyUtility.getDataProperties("history.data") + " is displayed",
                PropertyUtility.getDataProperties("history.data") + " is not displayed");
    }
}
