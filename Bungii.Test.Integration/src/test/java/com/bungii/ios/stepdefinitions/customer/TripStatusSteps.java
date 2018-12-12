package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.enums.Status;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.UpdateStatusPage;
import com.bungii.ios.pages.other.MessagesPage;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;


public class TripStatusSteps extends DriverBase {
	private UpdateStatusPage updateStatusPage;
	MessagesPage messagesPage;
	ActionManager action = new ActionManager();
	private static LogUtility logger = new LogUtility(TripStatusSteps.class);

	public TripStatusSteps(UpdateStatusPage updateStatusPage, MessagesPage messagesPage) {
		this.updateStatusPage = updateStatusPage;
		this.messagesPage = messagesPage;
	}


	@Then("^Customer should be naviagated to \"([^\"]*)\" trip status screen$")
	public void customerShouldBeNaviagatedToTripStatusScreen(String screen) {
		try {
			boolean pageFlag = false;
			if (screen.equalsIgnoreCase(Status.ARRIVED.toString()))
				pageFlag = isUpdatePage(Status.ARRIVED.toString());
			else if (screen.equals(Status.EN_ROUTE.toString()))
				pageFlag = isUpdatePage(Status.EN_ROUTE.toString());
			else if (screen.equals(Status.LOADING_ITEM.toString()))
				pageFlag = isUpdatePage(Status.LOADING_ITEM.toString());

			else if (screen.equals(Status.DRIVING_TO_DROP_OFF.toString()))
				pageFlag = isUpdatePage(Status.DRIVING_TO_DROP_OFF.toString());
			else if (screen.equals(Status.UNLOADING_ITEM.toString()))
				pageFlag = isUpdatePage(Status.UNLOADING_ITEM.toString());

			boolean activeStatusFlag = verifyStatus(screen.replace(" ", "_"));

			// verify other screen icon are not highlighted

			for (Status scr : Status.values()) {

				if (screen.equalsIgnoreCase(scr.toString()))
					continue;

				String scrValue = scr.toString().replace(" ", "_");
				boolean otherOne = verifyStatus(scrValue);
				if (otherOne == true) {

					fail("I should be naviagated to " + screen + " trip status screen",
							scr + " screen icon should not be higlighted", scr + " screen icon is higlighted", true);
				}
			}
			testStepAssert.isTrue(pageFlag && activeStatusFlag,
					"I should be naviagated to " + screen + " trip status screen",
					"I should be navigated to " + screen + "screen", "I was not navigated to" + screen);
		} catch (Exception e) {
			logger.error("Error performing step" + e.getMessage());
			error("I enter all the required fields", "Step  Should be sucessfull", "Error performing step,Error", true);
		}
	}

	@Then("^Trip Information should be correctly displayed on \"([^\"]*)\" status screen for customer$")
	public void trip_information_should_be_correctly_displayed_on_something_status_screen_for_customer(String key)
			throws Throwable {
		boolean isInfoDisplayed = false;

		logger.detail("before driver name");

		String expectedDriverName = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
		expectedDriverName = expectedDriverName.substring(0, expectedDriverName.indexOf(" ") + 2);
		boolean isDriverDisplayed = getCustomerName().equals(expectedDriverName);
		logger.detail("after driver name");

		switch (key) {
		case "EN ROUTE":
			isInfoDisplayed = validateEnRouteInfo(getTripInformation());
			break;
		case "ARRIVED":
			isInfoDisplayed = validateArrivedInfo(getTripInformation(), key);
			break;
		case "LOADING ITEM":
			isInfoDisplayed = validateArrivedInfo(getTripInformation(), key);
			break;
		case "DRIVING TO DROP OFF":
			isInfoDisplayed = validateDrivingInfo(getTripInformation());
			break;
		case "UNLOADING ITEM":
			isInfoDisplayed = validateUnloadingInfo(getTripInformation());
			break;
		default:
			break;
		}

		if (isInfoDisplayed && isDriverDisplayed) {
			pass("Trip Information should be correctly displayed on " + key + " status screen for customer",
					"Trip Information should be correctly displayed and driver name :" + expectedDriverName
							+ "should be displayed",
					"Trip Information is correctly displayed and driver name :" + expectedDriverName
							+ "is displayed correctly");
		} else {
			fail("Trip Information should be correctly displayed on " + key + " status screen for customer",
					"Trip Information should be correctly displayed and driver name :" + expectedDriverName
							+ "should be displayed",
					"Trip Information is correctly displayed and driver name :" + expectedDriverName
							+ "is displayed correctly");

		}
	}

	private boolean validateDrivingInfo(List<String> actualInfo) {
		logger.detail("customer trip info");

		boolean isTagDisplayed = actualInfo.get(0).equals("DROP OFF LOCATION"),
				isEtaDisplayed = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("minutes"),
				isDropLocationDisplayed = actualInfo.get(1)
						.equals((String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION"));

		if (isTagDisplayed && isEtaDisplayed && isDropLocationDisplayed) {
			//removed pass statement to avoid multiple screenshot and log in result
		} else {
			testStepVerify.isTrue(isTagDisplayed,
					"Trip Information should be correctly displayed on Enroute status screen for customer",
					"'DROP OFF LOCATION' Tag should correctly displayed",
					"'DROP OFF LOCATION' Tag is correctly displayed",
					"'DROP OFF LOCATION' Tag was not correctly displayed");

			testStepVerify.isTrue(isEtaDisplayed,
					"Trip Information should be correctly displayed on Enroute status screen for customer",
					"ETA should be correctly displayed",
					"'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
					"'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

			testStepVerify.isTrue(isDropLocationDisplayed,
					"Trip Information should be correctly displayed on Enroute status screen for customer",
					"Pick up location should be correctly displayed ",
					"Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + "and expected is"
							+ (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION"),
					"Pick up location was not displayed correctly, actual was is" + actualInfo.get(1)
							+ "and expected is" + (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"));
		}
		return isTagDisplayed && isEtaDisplayed && isDropLocationDisplayed;
	}

	private boolean validateUnloadingInfo(List<String> actualInfo) {
		logger.detail("customer trip info");

		boolean isTagDisplayed = actualInfo.get(0).equals("DROP OFF LOCATION"),
				isDropDisplayed = actualInfo.get(1).equals((String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION"));

		if (isTagDisplayed && isDropDisplayed) {
			//removed pass statement to avoid multiple screenshot and log in result
		} else {
			testStepVerify.isTrue(isTagDisplayed,
					"Trip Information should be correctly displayed on Enroute status screen for customer",
					"'DROP OFF LOCATION' Tag should correctly displayed",
					"'DROP OFF LOCATION' Tag is correctly displayed",
					"'PDROP OFF LOCATION' Tag was not correctly displayed");

			testStepVerify.isTrue(isDropDisplayed,
					"Trip Information should be correctly displayed on Enroute status screen for customer",
					"Pick up location should be correctly displayed ",
					"Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + "and expected is"
							+ (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION"),
					"Pick up location was not displayed correctly, actual was is" + actualInfo.get(1)
							+ "and expected is" + (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"));
		}
		return isTagDisplayed && isDropDisplayed;
	}

	private boolean validateEnRouteInfo(List<String> actualInfo) {
		logger.detail("customer trip info");

		boolean isTagDisplayed = actualInfo.get(0).equals("PICKUP LOCATION"),
				isEtaCorrect = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("minutes"),
				isPickUpCorrect = actualInfo.get(1).equals((String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"));
		if (isTagDisplayed && isEtaCorrect && isPickUpCorrect) {			
			//removed pass statement to avoid multiple screenshot and log in result
		} else {
			testStepVerify.isTrue(isTagDisplayed,
					"Trip Information should be correctly displayed on Enroute status screen for customer",
					"'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
					"'PICKUP LOCATION' Tag was not correctly displayed");

			testStepVerify.isTrue(isEtaCorrect,
					"Trip Information should be correctly displayed on Enroute status screen for customer",
					"ETA should be correctly displayed",
					"'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
					"'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

			testStepVerify.isTrue(isPickUpCorrect,
					"Trip Information should be correctly displayed on Enroute status screen for customer",
					"Pick up location should be correctly displayed ",
					"Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + "and expected is"
							+ (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"),
					"Pick up location was not displayed correctly, actual was is" + actualInfo.get(1)
							+ "and expected is" + (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"));
		}

		return isTagDisplayed && isEtaCorrect && isPickUpCorrect;
	}

	private boolean validateArrivedInfo(List<String> actualInfo, String screen) {
		logger.detail("customer trip info");

		boolean isTagDisplayed = actualInfo.get(0).equals("PICKUP LOCATION"),
				pickUpCorrect = actualInfo.get(1).equals((String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"));
		if (isTagDisplayed && pickUpCorrect) {
			//removed pass statement to avoid multiple screenshot and log in result
			} else {
			testStepVerify.isTrue(isTagDisplayed,
					"Trip Information should be correctly displayed on " + screen + " status screen for customer",
					"'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
					"'PICKUP LOCATION' Tag was not correctly displayed");

			testStepVerify.isTrue(pickUpCorrect,
					"Trip Information should be correctly displayed on " + screen + " status screen for customer",
					"Pick up location should be correctly displayed ",
					"Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + "and expected is"
							+ (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"),
					"Pick up location was not displayed correctly, actual was is" + actualInfo.get(1)
							+ "and expected is" + (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION"));
		}
		return isTagDisplayed && pickUpCorrect;
	}

	@Then("^correct details should be displayed to customer on \"([^\"]*)\" app$")
	public void correct_details_should_be_displayed_to_customer_on_something_app(String key) throws Throwable {
		switch (key.toUpperCase()) {
		case "SMS":
			clickSMSToDriver();
			validateSMSNumber(action.getValueAttribute(messagesPage.Text_ToField()));
			break;
		case "CALL":
			clickCallToDriver();
			validateCallButtonAction();
			break;
		default:
			break;
		}
	}

	private void validateSMSNumber(String actualValue) {
		String expectedNumber = PropertyUtility.getMessage("twilio.number").replace("(", "").replace(")", "").replace(" ", "")
				.replace("-", "");
		boolean isMessagePage = isMessageAppPage(), isNumberCorrect = actualValue.contains(expectedNumber);

		if (isNumberCorrect && isMessagePage) {
			pass("correct details should be displayed to customer on sms app", "I should be navigated to SMS app",
					"I was navigated to SMS app and To field contained number" + expectedNumber, true);
		} else {
			testStepVerify.isTrue(isMessagePage, "correct details should be displayed to customer on sms app",
					"I should be navigated to SMS app", "I was navigate to sms app", "I was not navigated to sms app");

			testStepVerify.isTrue(isNumberCorrect, "correct details should be displayed to customer on sms app",
					"To Field should contains " + expectedNumber,
					"To Field should contains " + expectedNumber + "and  actual value is" + actualValue,
					"To Field should contains " + expectedNumber + "and  actual value is" + actualValue);
		}
		action.click(messagesPage.Button_Cancel());
	}

	private void validateCallButtonAction() {
		action.waitForAlert();
		String actualMessage = action.getAlertMessage().replace("(", "").replace(")", "").replace(" ", "").replace("-", "")
				.replace("?", "").replace("+", "").trim();
		actualMessage = actualMessage.substring(1, actualMessage.length() - 1);
		String expectedMessage = PropertyUtility.getMessage("twilio.number").replace("(", "").replace(")", "").replace(" ", "")
				.replace("-", "").replace("+", "").trim();
		List<String> options = action.getListOfAlertButton();

		boolean isMessageCorrect = actualMessage.equals(expectedMessage),
				isOptionCorrect = options.contains("Cancel") && options.contains("Call");

		if (isMessageCorrect && isOptionCorrect) {
			pass("correct details should be displayed to customer on call app",
					"I should be alerted to call twillo number",
					"I was Alert to call twilio number and have option to cancel and call twilio number , options are"
							+ options.get(0) + " and " + options.get(1),
					true);
		} else {
			testStepVerify.isTrue(isMessageCorrect, "correct details should be displayed to customer on call app",
					"I should be alerted to call twillo number", "Twillo number was displayed in alert message",
					"Twillo number was not displayed in alert message , Actual message :" + actualMessage
							+ " , Expected Message:" + PropertyUtility.getMessage("twilio.number"));

			testStepVerify
					.isTrue(isOptionCorrect, "correct details should be displayed to customer on call app",
							"Alert should have option to cancel and call twilio number ",
							"Alert  have option to cancel and call twilio number , options are" + options.get(0)
									+ " and " + options.get(1),
							"Alert dont have option to cancel and call twilio number");
		}
		action.clickAlertButton("Cancel");
	}



	/**
	 * Check if active page is support page
	 *
	 * @param pageName
	 *            Page name of update status page
	 * @return return comparison result navigation header to expected msg from
	 *         property
	 */
	public boolean isUpdatePage(String pageName) {
		action.textToBePresentInElementName(updateStatusPage.Text_NavigationBar(), pageName);
		return action.getNameAttribute(updateStatusPage.Text_NavigationBar()).equals(pageName);

	}

	/**
	 * Get Current bungii customer name
	 * @return Name of customer
	 */
	public String getCustomerName() {
		return action.getValueAttribute(updateStatusPage.Text_DriverName());
	}

	/**
	 * Get trip information , Information while is below status icon , ex drop location etc
	 * @return Get trip information
	 */
	public List<String> getTripInformation() {
		List<String> details = new ArrayList<>();
		List<WebElement> textInfo = updateStatusPage.Text_Info();
		for (WebElement info : textInfo) {
			details.add(action.getValueAttribute(info));
		}
		return details;
	}

	/**
	 * Check If status image is displayed or not
	 * @param key Identifier for image, there should be key in image.properties with same name
	 * @return boolean value if image is present or not
	 */
	public boolean verifyStatus(String key) {
		return action.verifyImageIsPresent(key);
	}

	/**
	 * Click call button to driver
	 */
	public void clickCallToDriver() {
		action.click(updateStatusPage.Button_Call());
	}

	/**
	 * Click sms button to driver
	 */
	public void clickSMSToDriver() {
		action.click(updateStatusPage.Button_Sms());
	}

	public boolean isMessageAppPage(){
		action.textToBePresentInElementName(updateStatusPage.Text_NavigationBar(),PropertyUtility.getMessage("messages.navigation.new"));
		return action.getNameAttribute(updateStatusPage.Text_NavigationBar()).equals(PropertyUtility.getMessage("messages.navigation.new"));
	}

}
