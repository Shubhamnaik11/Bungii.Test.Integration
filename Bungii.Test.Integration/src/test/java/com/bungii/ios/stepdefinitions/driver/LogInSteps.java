package com.bungii.ios.stepdefinitions.driver;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.LoginPage;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.*;

public class LogInSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(com.bungii.ios.stepdefinitions.customer.LogInSteps.class);
    LoginPage loginPage = new LoginPage();
    ActionManager action = new ActionManager();
  /*  public LogInSteps(LoginPage loginPage){
        this.loginPage=loginPage;
    }*/

    GeneralUtility utility = new GeneralUtility();
    @Given("^I am logged in as \"([^\"]*)\" driver$")
    public void i_am_logged_in_as_something_driver(String option) {
        try {
        String phone, password;
        boolean shouldLoginSucessful;
        switch (option.toLowerCase()) {
            case "valid":
                phone = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                password = PropertyUtility.getDataProperties("ios.valid.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case"valid driver 2":
                SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                phone = PropertyUtility.getDataProperties("ios.valid.driver2.phone");
                password = PropertyUtility.getDataProperties("ios.valid.driver2.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("ios.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", phone);
                break;
            case"valid duo driver 1":
                phone = PropertyUtility.getDataProperties("ios.valid.driver.duo.phone");
                password = PropertyUtility.getDataProperties("ios.valid.driver.duo.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.duo.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid miami":
                phone = PropertyUtility.getDataProperties("miami.driver.phone");
                password = PropertyUtility.getDataProperties("miami.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("miami.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid nashville":
                phone = PropertyUtility.getDataProperties("nashville.driver.phone");
                password = PropertyUtility.getDataProperties("nashville.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("nashville.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid denver":
                phone = PropertyUtility.getDataProperties("denver.driver.phone");
                password = PropertyUtility.getDataProperties("denver.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid denver driver 2":
                phone = PropertyUtility.getDataProperties("denver.driver2.phone");
                password = PropertyUtility.getDataProperties("denver.driver2.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "new driver":
                phone = PropertyUtility.getDataProperties("new.driver.phone");
                password = PropertyUtility.getDataProperties("new.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("new.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            default:
                throw new Exception("Please specify valid input");
        }
        utility.loginToDriverApp(phone, password);
        if (shouldLoginSucessful) {
         //   utility.isDriverLoginSucessful();
        }
        else {
            //TODO: specify failure here
        }
            new GeneralUtility().logDriverDeviceToken(phone);
            log("I am logged in as"+option+"driver","I am logged in using"+phone+"/"+password,true);
             } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I enter phoneNumber :(.+) and  Password :(.+)$")
    public void i_enter_valid_credentials(String username, String password) {
        try {
            String strUserName = username.equals("<BLANK>") ? "" : username.trim().equals("{VALID}")? PropertyUtility.getDataProperties("ios.valid.driver.phone"):username;
            String strPassWord = password.equals("<BLANK>") ? "" : password.equals("{VALID}")? PropertyUtility.getDataProperties("ios.valid.driver.password"):password;
//            strUserName =username.equalsIgnoreCase("{with no card}")? PropertyUtility.getDataProperties("no.payment.card.customer.user"):strUserName;
//            strPassWord = password.equals("{with no card}") ?  PropertyUtility.getDataProperties("no.payment.card.customer.password"):strPassWord;

            action.clearEnterText(loginPage.TextField_PhoneNumber(),strUserName);
            action.clearEnterText(loginPage.Textfield_Password(),strPassWord);

            pass( "Username and Password should be added successfully",
                    "Entered Driver Credentials ["+ strUserName+" / "+strPassWord+"] successfully");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

}
