package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_DriverVerificationPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class Admin_DriverRejectSteps extends DriverBase {
    Admin_DriverVerificationPage admin_DriverVerificationPage = new Admin_DriverVerificationPage();
    Driver_LoginPage driver_LoginPage= new Driver_LoginPage();

    ActionManager action = new ActionManager();
    @When("^I click on \"([^\"]*)\" link$")
    public void i_click_on_something_link(String link) throws Throwable {
        switch(link) {

            case "Reject Application":
            action.click(admin_DriverVerificationPage.Link_RejectApplication());
            break;
            case "Login":
                action.click(driver_LoginPage.Tab_LogIn());
                break;
        }
    }

    @And("^I reject the \"([^\"]*)\"confirm action$")
    public void i_reject_the_somethingconfirm_action(String strArg1) throws Throwable {
        action.click(admin_DriverVerificationPage.Button_DriverConfirmReject_No());
    }

    @And("^I check if a validation message \"([^\"]*)\" is shown$")
    public void i_check_if_a_validation_message_something_is_shown(String strArg1) throws Throwable {
        testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Validation_Message_PleaseAddRejectionReason(),"I check if a validation message is displayed","Validation message is displayed","Validation message is not displayed");
///remove
    }

    @When("^I do not enter the reject reason$")
    public void i_do_not_enter_the_reject_reason() throws Throwable {
        admin_DriverVerificationPage.Textinput_ReasonforRejectDriverApplication().clear();
    }

    @And("^I check if \"([^\"]*)\" confirm action is shown$")
    public void i_check_if_something_confirm_action_is_shown(String strArg1) throws Throwable {
        switch(strArg1)
        {
            case "Driver Reject Application":
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Popup_RejectApplication(), "Driver Reject Application message should be available","Driver Reject Application message is displayed","Driver Reject Application message is not available");
                break;
        }      }
}
