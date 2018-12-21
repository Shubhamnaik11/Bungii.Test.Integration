package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.SearchingPage;
import com.bungii.ios.stepdefinitions.driver.TripDetailsSteps;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class SearchingSteps extends DriverBase {
	SearchingPage searchingPage;
	public SearchingSteps(SearchingPage searchingPage) {
		this.searchingPage=searchingPage;
	}
	ActionManager action = new ActionManager();
	private static LogUtility logger = new LogUtility(TripDetailsSteps.class);

	@And("^I wait for SEARCHING screen to disappear$")
    public void i_wait_for_searching_screen_to_disappear() {
    	try{
    	WaitForSearchingPageDisappear(Integer.parseInt(PropertyUtility.getProp("on.demand.search.time")));
    	testStepVerify.isFalse(isProgressBarVisible(),  "Progress bar should disappear", "Progress bar is not visible", "Progress bar is visible");
		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);

		}
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
