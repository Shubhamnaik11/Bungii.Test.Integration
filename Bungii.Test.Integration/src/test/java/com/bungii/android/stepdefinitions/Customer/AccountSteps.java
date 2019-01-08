package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.menus.AccountPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class AccountSteps extends DriverBase {
    ActionManager action = new ActionManager();
    AccountPage accountPage = new AccountPage();
    private static LogUtility logger = new LogUtility(AccountSteps.class);

    /**
     * Read customer details and store it in scenario context
     *
     */
    @Then("^I get customer account details$")
    public void i_get_customer_account_details() {
        try {
            String[] details = new String[2];
            details[0]=action.getText(accountPage.Account_Name());
            details[1]=action.getText(accountPage.Account_Phone());

            //replace all character
            String phone =details[1].replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
            //store customer name and phone in scenario context
            cucumberContextManager.setScenarioContext("CUSTOMER", details[0]);
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE",phone );

            logger.detail("I get customer account details , Customer name is " + details[0]+ " and Phone Number is "+ details[1]);
            log( "I get customer account details", "Customer name is " + details[0] + " and Phone Number is "+ details[1],
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
