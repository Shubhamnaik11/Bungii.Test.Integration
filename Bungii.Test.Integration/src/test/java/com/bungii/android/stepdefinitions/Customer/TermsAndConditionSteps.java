package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.TermsPage;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class TermsAndConditionSteps {
    TermsPage termsAndConditionPage;
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(TermsAndConditionSteps.class);

    public TermsAndConditionSteps(TermsPage termsAndConditionPage) {
        this.termsAndConditionPage = termsAndConditionPage;
    }

    @Then("^I accept Term and Condition agreement$")
    public void i_accept_term_and_condition_agreement() {
        try {
            action.click(termsAndConditionPage.Checkbox_Agree());
            action.click(termsAndConditionPage.Button_Continue());
            pass("I accept Term and Condition agreement", "I selected terms and condition checkbox and selected Continue Button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
}
