package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.SearchingPage;
import cucumber.api.java.en.And;

public class SearchingSteps extends DriverBase {
	SearchingPage searchingPage;
	public SearchingSteps(SearchingPage searchingPage) {
		this.searchingPage=searchingPage;
	}
	ActionManager action = new ActionManager();
    @And("^I wait for SEARCHING screen to disappear$")
    public void i_wait_for_searching_screen_to_disappear() throws Throwable {
    	WaitForSearchingPageDisappear(Integer.parseInt(PropertyUtility.getProp("on.demand.search.time")));
    	testStepVerify.isFalse(isProgressBarVisible(),  "Progress bar should disappear", "Progress bar is not visible", "Progress bar is visible");
    }


	/**
	 * Get Waiting message that is displayed while searching
	 * @return return waiting message
	 */
	public String getWaitingMessage(){
		return action.getValueAttribute(searchingPage.Text_WaitingMessage());
	}

	/**
	 * Wait for searching message to disappear
	 * @param searchTime
	 * @throws InterruptedException
	 */
	public void WaitForSearchingPageDisappear(int searchTime) throws InterruptedException{
		action.hardWaitWithSwipeUp(searchTime+3);
	}

	/**
	 * Check if progress bar is visible
	 * @return
	 */
	public boolean isProgressBarVisible(){
		action.invisibilityOfElementLocated(searchingPage.Activity_ProgressBar());
		return searchingPage.isElementEnabled(searchingPage.Activity_ProgressBar());
	}
}
