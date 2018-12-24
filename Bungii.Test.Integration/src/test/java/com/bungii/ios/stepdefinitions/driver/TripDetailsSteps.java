package com.bungii.ios.stepdefinitions.driver;


import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.TripDetailsPage;
import com.bungii.ios.stepdefinitions.customer.HomeSteps;
import com.bungii.ios.stepdefinitions.customer.VerificationSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;


public class TripDetailsSteps extends DriverBase {
	private TripDetailsPage tripDetailsPage;
	public TripDetailsSteps(TripDetailsPage tripDetailsPage){
		this.tripDetailsPage=tripDetailsPage;
	}
	ActionManager action = new ActionManager();
	private static LogUtility logger = new LogUtility(TripDetailsSteps.class);

	@When("^I accept selected Bungii$")
    public void i_accept_selected_bungii() {
    	try{
    	AcceptBungii();
    	log("Bungii should be Bungii","Bungii is sucessfully accepted");
    	}catch(Exception e){
    		error("Bungii should be Bungii","Error occured while accepting bungii"+e.getMessage());
    	}
    	
    }


	@Then("^Trip Information should be correctly displayed on TRIP DETAILS screen$")
	public void trip_information_should_be_correctly_displayed_on_something_screen()  {
		try {
			String[] actualDetails = getTripDetails();
			cucumberContextManager.setScenarioContext("BUNGII_DRIVER_ESTIMATE", actualDetails[1]);

			String expectedTripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
			String expectedTripDistance = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
			//Leading zero is not present in time, Check if zero is present and delete it
			String timeValue = expectedTripTime.split(",")[1];
			timeValue = timeValue.substring(0, 1).equals("0") ? timeValue.substring(1) : timeValue;

			boolean isTimeCorrect = expectedTripTime.split(",")[0].trim().equals(actualDetails[2].trim())
					&& timeValue.trim().equals(actualDetails[3].trim());
			boolean isDistanceCorrect = expectedTripDistance.equals(actualDetails[0]);

			testStepVerify.isTrue(isTimeCorrect && isDistanceCorrect,
					"Trip Information should be correctly displayed on TRIP DETAILS screen",
					"Trip Information should be correctly displayed ",
					expectedTripDistance + expectedTripTime + "" + actualDetails[0] + actualDetails[1] + actualDetails[2]);
		} catch (Throwable e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
		}

	}
	/**
	 * Accept Bungii
	 */
	public void AcceptBungii(){
		action.click(tripDetailsPage.Button_Accept());
	}


	/**
	 * Get trip details from trip details page
	 * @return Return array of string specifing trip details
	 */
	public String[] getTripDetails(){
		String [] tripDetails= new String [4];
		tripDetails[0] = action.getValueAttribute(tripDetailsPage.Text_Distance());
		tripDetails[1] = action.getValueAttribute(tripDetailsPage.Text_EstimatedEarnings());
		tripDetails[2] = action.getValueAttribute(tripDetailsPage.Text_ScheduledDate());
		tripDetails[3] =action. getValueAttribute(tripDetailsPage.Text_ScheduledTime());
		return tripDetails;
	}
}
