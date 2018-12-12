package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.TermsAndConditionPage;
import cucumber.api.java.en.Then;

public class TermsAndConditionSteps extends DriverBase {
	TermsAndConditionPage termsAndConditionPage;
	ActionManager action = new ActionManager();
	public TermsAndConditionSteps(TermsAndConditionPage termsAndConditionPage) {
		this.termsAndConditionPage=termsAndConditionPage;
	}
    @Then("^I accept Term and Condition agreement$")
    public void i_accept_term_and_condition_agreement() throws Throwable {
		action.click(termsAndConditionPage.Button_CheckOff());
		action.click(termsAndConditionPage.Button_Continue());    }


}
