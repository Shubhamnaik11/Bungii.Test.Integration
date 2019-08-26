package com.bungii.ios.stepdefinitions.customer;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.BungiiDetails;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

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
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
		}
	}
	//TODO: Add duo
	@Then("^Trip Information should be correctly displayed on BUNGII DETAILS screen$")
	public void trip_information_should_be_correctly_displayed_on_something_screen()  {
		try {

			String[] tripInfo = getSoloBungiiLocationInformation();
			String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME")),
					estimate = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
			String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

			String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",","").trim();
			String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",","").trim();

			String dropOffLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",","").trim();
			String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",","").trim();

			boolean isPickUpAddressCorrect = tripInfo[0].equals(pickUpLocationLineOne)&& tripInfo[1].equals(pickUpLocationLineTwo),
					isDropAddressCorrect = tripInfo[5].equals(dropOffLocationLineOne) &&tripInfo[6].equals(dropOffLocationLineTwo),
					isTimeCorrect = tripInfo[3].equals(tripTime),
					isEstimateCorrect = tripInfo[4].equals(estimate);

			if (!tripNoOfDriver.toUpperCase().equals("SOLO")) {
				// customerbungiiDetails.getDriver2Status()
			}

			testStepVerify.isTrue(isPickUpAddressCorrect,
					"Pick up address should be " + pickUpLocationLineOne+pickUpLocationLineTwo, "Pick up address is " + pickUpLocationLineOne+pickUpLocationLineTwo,
					"Expected pickup address is " + pickUpLocationLineOne+pickUpLocationLineTwo + ", but actual is" + tripInfo[0]+tripInfo[1]);
			testStepVerify.isTrue(isDropAddressCorrect,
					"Drop address should be " + dropOffLocationLineOne+dropOffLocationLineTwo, "Drop address is " + dropOffLocationLineOne+dropOffLocationLineTwo,
					"Expected Drop address is " + dropOffLocationLineOne+dropOffLocationLineTwo + ", but actual is" + tripInfo[1]);
			testStepVerify.isTrue(isTimeCorrect,
					"Trip time should be " + tripTime, "Trip time is " + tripTime,
					"Expected Trip time is " + tripTime + ", but actual is" + tripInfo[5]+tripInfo[6]);
			testStepVerify.isTrue(isEstimateCorrect,
					"Trip Estimate should be " + estimate, "Trip time is " + estimate,
					"Expected Trip Estimate is " + estimate + ", but actual is" + tripInfo[4]);
		} catch (Throwable e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
		}

	}

	/**
	 * Get Bungii pickup/drop information on Bungii details page
	 * @return details for bungii
	 */
	public String[] getSoloBungiiLocationInformation(){

		String [] locationInformation = new String[7];
		locationInformation[0]=action.getValueAttribute(bungiiDetails.Text_PickUpLocationLine1()).replace(",","").trim();
		locationInformation[1]=action.getValueAttribute(bungiiDetails.Text_PickUpLocationLine2()).replace(",","").trim();
		locationInformation[2]=action.getValueAttribute(bungiiDetails.Text_Driver1Status());
		locationInformation[3]=action.getValueAttribute(bungiiDetails.Text_Time());
		locationInformation[4]=action.getValueAttribute(bungiiDetails.Text_TotalEstimate());

		locationInformation[5]=action.getValueAttribute(bungiiDetails.Text_DropLocationLine1()).replace(",","").trim();
		locationInformation[6]=action.getValueAttribute(bungiiDetails.Text_DropLocationLine2()).replace(",","").trim();
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
