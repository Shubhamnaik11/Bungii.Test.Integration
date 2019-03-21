package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.AccountsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class AccountSteps extends DriverBase {
    AccountsPage accountPage;
    ActionManager action = new ActionManager();
    public AccountSteps(AccountsPage accountPage) {
        this.accountPage=accountPage;
    }
    private static LogUtility logger = new LogUtility(com.bungii.ios.stepdefinitions.driver.AccountSteps.class);

    @Then("^I get driver account details for driver 1$")
    public void i_get_driver_account_details_for_driver_1() {
        try {
            String[] details = getDriverDetails();
            String phone = details[1].replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
            cucumberContextManager.setScenarioContext("DRIVER_1", details[0]);
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
            log( "I get driver account details for driver 1", "driver 1 name is " + details[0] + " and Phone Number is "+ details[1],true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    /**
     * Get driver details from account page
     * @return String array containing driver name , Phone number anad email id
     */
    public String[] getDriverDetails(){
        String[] details = new String [3];
        details[0]=action.getText(accountPage.Text_Name());
        details[1]=action.getText(accountPage.Text_Phone());
        details[2]=action.getText(accountPage.Text_Email());
        return details;
    }
}
