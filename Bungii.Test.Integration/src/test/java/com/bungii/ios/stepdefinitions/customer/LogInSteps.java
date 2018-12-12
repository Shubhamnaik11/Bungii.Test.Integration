package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.LoginPage;
import cucumber.api.java.en.And;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class LogInSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LogInSteps.class);
    LoginPage loginPage;
    ActionManager action = new ActionManager();
    public LogInSteps(LoginPage loginPage){
        this.loginPage=loginPage;
    }

    @And("^I enter Username :(.+) and  Password :(.+)$")
    //@And("^I enter valid (.+) and (.+) as per below table$")
    public void i_enter_valid_and_as_per_below_table(String username, String password) {
        try {
            String strUserName = username.equals("<BLANK>") ? "" : username.equals("{VALID}")? PropertyUtility.getDataProperties("customer.user"):username;
            String strPassWord = password.equals("<BLANK>") ? "" : password.equals("{VALID}")? PropertyUtility.getDataProperties("customer.password"):password;
            strUserName =username.equalsIgnoreCase("{with no card}")? PropertyUtility.getDataProperties("no.payment.card.customer.user"):strUserName;
            strPassWord = password.equals("{with no card}") ?  PropertyUtility.getDataProperties("no.payment.card.customer.password"):strPassWord;

            action.waitClearEnterText(loginPage.Textfield_PhoneNumber(),strUserName);
            action.waitClearEnterText(loginPage.Textfield_Password(),strPassWord);

            pass("I enter username and password ", "USername and Password should be added sucessfully",
                    "Username :"+ strUserName+", and password :"+strPassWord+",is added sucessfully");

        } catch (Exception e) {
            logger.error("Error performing step" + e.getMessage());
            error("I enter valid "+username+" and "+password+" as per below table", "Step  Should be sucessfull", "Error performing step,Error", true);
        }
    }
}
