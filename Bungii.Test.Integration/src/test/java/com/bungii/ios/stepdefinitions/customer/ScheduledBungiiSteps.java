package com.bungii.ios.stepdefinitions.customer;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.ScheduledBungiiPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;


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
			pass("I select already scheduled bungii", "I selected already scheduled bungii of "+tripNoOfDriver+" type and at time: " + tripTime , true);
		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
		}
	}


	@Then("^Bungii must be removed from \"([^\"]*)\" screen$")
	public void bungii_must_be_removed_from_something_screen(String screen) {
		try {
			String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
			String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));

			Thread.sleep(20000);
			action.swipeDown();
			boolean isBungiiPresent =isBungiiPresent(tripNoOfDriver, tripTime);
			//do half screen swipe if Bungii is present
			if(isBungiiPresent)
			{	halfScreenSwipe();
				isBungiiPresent = isBungiiPresent(tripNoOfDriver, tripTime);
			}
			testStepVerify.isFalse(isBungiiPresent, "Bungii must be removed from " + screen + " screen",
					"Bungii is be deleted ,there is no bungii for time:"+tripTime, "Bungii is not deleted");
		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}
	private void halfScreenSwipe(){
		Dimension size = SetupManager.getDriver().manage().window().getSize();
		int startx = (int) (size.width * 0.5);
		int endx = (int) (size.width * 0.5);
		int starty = size.height / 2;
		int endy = size.height ;

		action.dragFromToForDuration(startx, starty, endx, endy, 1);
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
