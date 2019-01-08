package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.pages.bungii.CustomerHomePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.ios.manager.ActionManager;
import cucumber.api.java.en.When;

public class HomeSteps extends DriverBase
{   GeneralUtility utility = new GeneralUtility();
    CustomerHomePage customerHomePage = new CustomerHomePage();
    ActionManager action = new ActionManager();

    @When("^I Select \"([^\"]*)\" from customer app menu list$")
    public void i_select_something_from_customer_app_menu_list(String strArg1) throws Throwable {
        action.click(customerHomePage.Button_NavigationBar());
        utility.clickCustomerMenuItem(strArg1);
    }

    @When("^I tap on \"([^\"]*)\" > \"([^\"]*)\" link$")
    public void i_tap_on_something_something_link(String strArg1, String strArg2) throws Throwable {
        action.click(customerHomePage.Button_NavigationBar());
        utility.clickCustomerMenuItem(strArg2);    }


}
