package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.LoginPage;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.exception.ExceptionUtils;

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
    public void i_enter_valid_and_as_per_below_table(String username, String password) {
        try {
            String strUserName = username.equals("<BLANK>") ? "" : username.trim().equals("{VALID}")? PropertyUtility.getDataProperties("customer.user"):username;
            String strPassWord = password.equals("<BLANK>") ? "" : password.equals("{VALID}")? PropertyUtility.getDataProperties("customer.password"):password;
            strUserName =username.equalsIgnoreCase("{with no card}")? PropertyUtility.getDataProperties("no.payment.card.customer.user"):strUserName;
            strPassWord = password.equals("{with no card}") ?  PropertyUtility.getDataProperties("no.payment.card.customer.password"):strPassWord;

            action.clearEnterText(loginPage.Textfield_PhoneNumber(),strUserName);
            action.clearEnterText(loginPage.Textfield_Password(),strPassWord);
         //   cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", strUserName);
            pass( "Username and Password should be added sucessfully",
                    "Username :"+ strUserName+", and password :"+strPassWord+",is added successfully");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
