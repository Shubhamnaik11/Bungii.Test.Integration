package com.bungii.web.stepdefinitions.driver;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.driver.Driver_RegistrationPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.bungii.common.manager.ResultManager.log;

public class Driver_LoginSteps extends DriverBase {

    Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage();
    Driver_DashboardPage Page_Driver_Dashboard = new Driver_DashboardPage();
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    ActionManager action = new ActionManager();


    @When("^I enter \"([^\"]*)\" driver Phone Number on Driver portal$")
    public void WhenIEnterDriverPhoneNumberOnDriverPortal(String p0)
    {
        switch (p0)
        {
            case "valid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), PropertyUtility.getDataProperties("DriverPhoneNumber"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), PropertyUtility.getDataProperties("Invalid_DriverPhoneNumber"));
                break;
            default: break;
        }
        log("I should able to enter "+p0+" driver phone number on Driver portal","I entered "+p0 +" driver phone number on Driver portal", true);
    }

    @And("^I enter \"([^\"]*)\" driver Password on Driver portal$")
    public void WhenIEnterDriverPasswordOnDriverPortal(String p0)
    {
        switch (p0)
        {
            case "valid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("DriverPassword"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("Invalid_DriverPassword"));
                break;
            default: break;
        }
        log("I should able to enter "+p0+" driver Password on Driver portal","I entered "+p0 +" driver Password on Driver portal", true);

    }

    @Then("^the driver should \"([^\"]*)\"$")
    public void ThenTheDriverShould(String p0)
    {
        switch (p0)
        {
            case "be logged in":
                testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.Header_Dashboard()), PropertyUtility.getMessage("DriverDashboardHeader"));
                break;
            case "see validation message for blank fields":
                testStepVerify.isEquals(action.getText(Page_Driver_Login.Err_DriverLogin_Blank()), PropertyUtility.getMessage("Err_Pages_BlankFields"));
                break;
            case "see validation message for invalid phone field":
                testStepVerify.isEquals(action.getText(Page_Driver_Login.Err_DriverLogin_Phone()), PropertyUtility.getMessage("Err_DriverLogin_Phone"));
                break;
            case "see validation message for incorrect credentials":
                testStepVerify.isEquals(action.getText(Page_Driver_Login.Err_DriverLogin_FieldValidation()), PropertyUtility.getMessage("Err_DriverLogin_IncorrectCredentials"));
                break;
            default: break;
        }
    }
}
