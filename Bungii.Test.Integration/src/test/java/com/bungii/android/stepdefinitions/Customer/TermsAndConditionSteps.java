package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.TermsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.*;

public class TermsAndConditionSteps extends DriverBase {
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

    @Then("^I should see \"([^\"]*)\" on Term and Condition agreement$")
    public void i_should_see_something_on_term_and_condition_agreement(String identifier) throws Throwable {

        try {

            switch (identifier.toLowerCase()) {
                case "all details":
                    testStepVerify.isTrue(action.getAttribute(termsAndConditionPage.Checkbox_Agree(),"checked").equalsIgnoreCase("false"),"Terms and condition checkbox should be unchecked");
                    testStepVerify.isElementEnabled(termsAndConditionPage.Text_Label(true),"'TERMS OF USE' lable should be displayed");
                    testStepVerify.isElementTextEquals(termsAndConditionPage.Text_Accept(),PropertyUtility.getMessage("customer.terms.accept"));
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

}
