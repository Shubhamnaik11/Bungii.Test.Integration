package com.bungii.ios.stepdefinitions.driver;


import com.bungii.common.core.DriverBase;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.TripDetailsPage;
import cucumber.api.java.en.When;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;


public class TripDetailsSteps extends DriverBase {
	private TripDetailsPage tripDetailsPage;
	public TripDetailsSteps(TripDetailsPage tripDetailsPage){
		this.tripDetailsPage=tripDetailsPage;
	}
	ActionManager action = new ActionManager();
    @When("^I accept selected Bungii$")
    public void i_accept_selected_bungii() {
    	try{
    	AcceptBungii();
    	log("I accept selected Bungii","Bungii should be Bungii","Bungii is sucessfully accepted");
    	}catch(Exception e){
    		error("I accept selected Bungii","Bungii should be Bungii","Error occured while accepting bungii"+e.getMessage());
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
