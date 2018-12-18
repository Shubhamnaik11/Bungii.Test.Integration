package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.ScheduledBungiiPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static com.bungii.common.manager.ResultManager.error;


public class ScheduledBungiiSteps extends DriverBase {
	ScheduledBungiiPage scheduledBungiiPage;
	ActionManager action = new ActionManager();
	String Image_Solo = "bungii_type-solo";

	private static LogUtility logger = new LogUtility(ScheduledBungiiSteps.class);

	public ScheduledBungiiSteps(ScheduledBungiiPage scheduledBungiiPage) {
		this.scheduledBungiiPage = scheduledBungiiPage;
	}

	@When("^I select already scheduled bungii$")
	public void i_select_already_scheduled_bungii() {
		try {
			String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
			String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
			selectBungii(tripNoOfDriver, tripTime);

		} catch (Exception e) {
			logger.error("Error performing step" + e.getMessage());
			error( "Step  Should be sucessfull", "Error performing step,Error", true);
		}
	}


	@Then("^Bungii must be removed from \"([^\"]*)\" screen$")
	public void bungii_must_be_removed_from_something_screen(String screen) {
		try {
			String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
			String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
			Thread.sleep(20000);
			action.swipeDown();
			boolean isDeleted = isBungiiPresent(tripNoOfDriver, tripTime);
			testStepVerify.isFalse(isDeleted, "Bungii must be removed from " + screen + " screen",
					"Bungii Must be deleted", "Bungii is not deleted");
		} catch (Exception e) {
			logger.error("Error performing step" + e.getMessage());
			error( "Step  Should be sucessfull",
					"Error performing step,Error", true);
		}
	}




	/**
	 * Construct locator for bungii from given bungii information
	 *
	 * @param bungiiType
	 *            identifer for bungii type
	 * @param bungiiTime
	 *            Scheduled bungii time
	 * @return
	 */
	public WebElement getLocatorForBungii(String bungiiType, String bungiiTime) {
		String imageTag = "";
		if (bungiiType.toUpperCase().equals("SOLO")) {
			imageTag = Image_Solo;
		}
		action.swipeDown();

		//By Image_SelectBungii = MobileBy.xpath("//XCUIElementTypeStaticText[@name='" + bungiiTime+ "']/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell");

		WebElement Image_SelectBungii=scheduledBungiiPage.findElement("//XCUIElementTypeStaticText[@name='" + bungiiTime+ "']/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);

		return Image_SelectBungii;
	}

	/**
	 * select bungii
	 * @param bungiiType
	 *            identifer for bungii type
	 * @param bungiiTime
	 *            Scheduled bungii time
	 */
	public void selectBungii(String bungiiType, String bungiiTime) {
		action.click(getLocatorForBungii(bungiiType, bungiiTime));
	}

	/**
	 * Check if bungii is present
	 * @param bungiiType
	 *            Bungii Type , Solo /Duo
	 * @param bungiiTime
	 *            Scheduled bungii time
	 * @return
	 */
	public boolean isBungiiPresent(String bungiiType, String bungiiTime) {
		try{
		return scheduledBungiiPage.isElementEnabled(getLocatorForBungii(bungiiType, bungiiTime));}
		catch (Exception e){
			return  false;
		}
	}
}
