package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.SuccessPage;
import cucumber.api.java.en.Then;

public class SuccessSteps extends DriverBase {
	private SuccessPage successPage;
	public SuccessSteps(SuccessPage successPage) {
		this.successPage =successPage;
	}
	ActionManager action = new ActionManager();
    @Then("^Bungii Posted message should be displayed$")
    public void bungii_posted_message_should_be_displayed() throws Throwable {
    	testStepAssert.isTrue(isPostedMessageDisplayed(),"Bungii Posted message should be displayed","Bungii Posted message should be displayed","Bungii Posted message is not displayed");
    }



	/**
	 * Check if bungii posted message is displayed
	 * @return boolean value if Posted message is displayed
	 */
	public boolean isPostedMessageDisplayed(){
		return successPage.isElementEnabled(successPage.Text_BungiiPosted());
	}


}
