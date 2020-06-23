package com.bungii.web.stepdefinitions.partner;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.LogInSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_LoginPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.pass;

public class Partner_LoginSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LogInSteps.class);
    Partner_LoginPage Page_Partner_Login = new Partner_LoginPage();
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();

    @Given("^I navigate to \"([^\"]*)\" URL$")
    public void i_navigate_to_something(String page) throws Throwable {
        switch (page)
        {
            case "Bungii Partner Portal":
                utility.NavigateToPartnerLogin();
                break;

        }
        pass("I should be navigate to " + page,
                "I am navigate to " + page, true);
    }


    @When("I enter \"([^\"]*)\" password on Partner Portal")
    public void WhenIEnterDriverPasswordOnPartnerPortal(String str)
    {
        switch (str)
        {
            case "valid":
                action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("Invalid_PartnerPassword"));
                break;
            default: break;
        }
        log("I should able to enter "+str+" driver Password on Partner portal","I entered "+str +" partner Password on Partner portal", true);

    }

    @And("^I click \"([^\"]*)\" button on Partner Portal$")
    public void I_Click_Some_Button_On_Partner_Portal(String str)
    {
        switch(str)
        {
            case "SIGN IN":
                action.click(Page_Partner_Login.Sign_In());
                break;
            default: break;

        }
    }

    @Then("^I should \"([^\"]*)\"$")
    public void IShould(String str)
    {
        switch (str)
        {
            case "be logged in":
                //        testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.Header_Dashboard()), PropertyUtility.getMessage("DriverDashboardHeader"));
                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Get_Estimate_Header()), PropertyUtility.getMessage("Get_Estimate_Header"));
                break;
            case "see validations message for blank password field":
                testStepVerify.isEquals(action.getText(Page_Partner_Login.Blank_Incorrect_Password_Msg()), PropertyUtility.getMessage("Blank_Password"));
                break;
            case "see validations message for incorrect password field":
                testStepVerify.isEquals(action.getText(Page_Partner_Login.Blank_Incorrect_Password_Msg()), PropertyUtility.getMessage("Incorrect_Password"));
                break;
             default: break;
        }
    }

    @Then("^I should logout from Partner Portal$")
    public void i_should_logout_from_partner_portal() throws Throwable {
        utility.PartnerLogout();
        log("I should be logged out from Partner Portal ","I clicked ", true);
    }

}

