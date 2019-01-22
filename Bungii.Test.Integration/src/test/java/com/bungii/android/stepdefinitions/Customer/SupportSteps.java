package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.SupportPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.bungii.common.manager.ResultManager.log;

public class SupportSteps extends DriverBase {
    SupportPage supportPage = new SupportPage();
    ActionManager action = new ActionManager();

    @When("^I enter \"([^\"]*)\" text in Support field$")
    public void i_enter_something_text_in_support_field(String p0) throws Throwable {
        String textValue = "";
        switch (p0) {
            case "valid":
                textValue = PropertyUtility.getDataProperties("support.text");
                break;
            case "space":
                textValue = "          ";
                break;
            default:
                break;
        }
        action.sendKeys(supportPage.TextField(), textValue);
        log("I should able to enter " + p0 + " ", "I enter " + textValue + " in support field");

    }

    @Then("^The user should see \"([^\"]*)\" on Support page$")
    public void the_user_should_see_something_on_support_page(String strArg1) throws Throwable {
        switch (strArg1) {
            case "snackbar validation":
                testStepAssert.isElementTextEquals(supportPage.Snackbar(), PropertyUtility.getMessage("customer.support.submitted"), "Support message submitted should be displayed ", "Support message submitted is displayed", "Support message submitted is not displayed");
                break;
            case "Send button disabled":
                testStepAssert.isElementDisplayed(supportPage.Button_Send(), "Send button should be disabled", "Send button is disabled", "Send button is enabled");
                break;
            case "validation message":
                action.hideKeyboard();
                testStepAssert.isEquals(action.getText(supportPage.Error_Blank()), PropertyUtility.getMessage("customer.support.emptyfield"), "Proper validation message should be displayed", "'" + PropertyUtility.getMessage("customer.support.emptyfield") + "'message is displayed", "'" + PropertyUtility.getMessage("customer.support.emptyfield") + "'message is not displayed");
                break;
            default:
                break;
        }
    }

    @And("^I tap \"([^\"]*)\" on Support page$")
    public void i_tap_something_on_support_page(String strArg1) throws Throwable {
        action.click(supportPage.Button_Send());
        log("I should tap" + strArg1 + " ", "I tapped " + strArg1 + " in support field");

    }
}