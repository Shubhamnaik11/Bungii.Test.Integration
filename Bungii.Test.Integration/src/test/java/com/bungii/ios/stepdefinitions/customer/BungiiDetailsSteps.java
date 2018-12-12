package com.bungii.ios.stepdefinitions.customer;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.BungiiDetails;
import cucumber.api.java.en.Then;

import static com.bungii.common.manager.ResultManager.error;

public class BungiiDetailsSteps extends DriverBase {
	private static LogUtility logger = new LogUtility(BungiiDetailsSteps.class);
	ActionManager action = new ActionManager();

	BungiiDetails bungiiDetails;

	public BungiiDetailsSteps(BungiiDetails bungiiDetails) {
		this.bungiiDetails = bungiiDetails;
	}


	@Then("^I Cancel selected Bungii$")
	public void i_cancel_selected_bungii() {
		try {
			action.click(bungiiDetails.Button_CancelBungii());
			action.waitForAlert();
			SetupManager.getDriver().switchTo().alert().accept();
		} catch (Exception e) {
			logger.error("Error performing step" + e.getMessage());
			error("I Cancel selected Bungii", "Step  Should be sucessfull", "Error performing step,Error", true);
		}
	}


	/**
	 * Get Bungii pickup/drop information on Bungii details page
	 * @return details for bungii
	 */
	public String[] getSoloBungiiLocationInformation(){

		String [] locationInformation = new String[5];
		locationInformation[0]=action.getValueAttribute(bungiiDetails.Text_PickUpLocation());
		locationInformation[1]=action.getValueAttribute(bungiiDetails.Text_DropLocation());
		locationInformation[2]=action.getValueAttribute(bungiiDetails.Text_Driver1Status());
		locationInformation[3]=action.getValueAttribute(bungiiDetails.Text_Time());
		locationInformation[4]=action.getValueAttribute(bungiiDetails.Text_TotalEstimate());
		return locationInformation;

	}

	/**
	 * Get driver 2 status
	 * @return return status of driver 2
	 */
	public String getDriver2Status(){
		return action.getValueAttribute(bungiiDetails.Text_Driver2Status());
	}



}
