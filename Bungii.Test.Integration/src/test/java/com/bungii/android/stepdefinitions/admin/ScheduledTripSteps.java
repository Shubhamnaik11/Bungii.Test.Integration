/**
 *
 */
package com.bungii.android.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.android.utilityfunctions.*;
import com.bungii.web.manager.*;
import com.bungii.android.pages.admin.ScheduledTripsPage;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bungii.common.manager.ResultManager.*;

public class ScheduledTripSteps extends DriverBase {
	private ScheduledTripsPage scheduledTripsPage;
	ActionManager action = new ActionManager();
	GeneralUtility utility = new GeneralUtility();
	private static LogUtility logger = new LogUtility(com.bungii.ios.stepdefinitions.admin.ScheduledTripSteps.class);

	public ScheduledTripSteps(ScheduledTripsPage scheduledTripsPage) {
		this.scheduledTripsPage = scheduledTripsPage;
	}

	@Then("^I Cancel Bungii with following details$")
	public void i_cancel_bungii_with_following_details(DataTable cancelDetails) {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");

			String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);
			action.sendKeys(scheduledTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
			action.click(scheduledTripsPage.Button_Search());
			Thread.sleep(5000);
			//On admin panel CST time use to show
			//	getPortalTime("Dec 21, 11:15 AM IST");
			//tripDetails.put("SCHEDULED_DATE", getCstTime(bungiiTime));
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime.replace("CDT", "CST").replace("EDT", "EST").replace("MDT", "MST")));
			tripDetails.put("BUNGII_DISTANCE", tripDistance);

			Map<String, String> data = cancelDetails.transpose().asMap(String.class, String.class);
			String cancelCharge = data.get("Charge"), comments = data.get("Comments"), reason=data.get("Reason");
			int rowNumber = getTripRowNumber(tripDetails);
			// it takes max 2.5 mins to appear
			for (int i = 0; i < 5 && rowNumber == 999; i++) {
				Thread.sleep(30000);
				SetupManager.getDriver().navigate().refresh();
				scheduledTripsPage.waitForPageLoad();
				rowNumber = getTripRowNumber(tripDetails);
			}
			cancelBungii(tripDetails, cancelCharge, comments, reason);
			pass("I should able to cancel bungii", "I was able to cancel bungii");

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}

	}

	private String getCstTime(String bungiiTime) throws ParseException {
		//Dec 21, 11:15 AM IST
		Date bungiiDate = new SimpleDateFormat("MMM d, h:mm a").parse(bungiiTime);
		Date currentDate = new Date();
		bungiiDate.setYear(currentDate.getYear());

		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
		sdf.setTimeZone(TimeZone.getTimeZone("CST"));

		String formattedDate = sdf.format(bungiiDate);
		return formattedDate;
	}

	private String getPortalTime(String bungiiTime) throws ParseException {
		Calendar calendar = Calendar.getInstance();

		int intYear = calendar.get(Calendar.YEAR);

		String formattedDate = bungiiTime.substring(0, 7) + " " + intYear + bungiiTime.substring(7, 13) + ":00" + bungiiTime.substring(13, bungiiTime.length());

		return formattedDate;
	}

	@Then("^Bungii must be removed from the List$")
	public void bungii_must_be_removed_from_the_list() {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");

			scheduledTripsPage.waitForPageLoad();
			SetupManager.getDriver().navigate().refresh();
			tripDetails.put("CUSTOMER", custName);
			//On admin panel CST time use to show
			//tripDetails.put("SCHEDULED_DATE", getCstTime(bungiiTime));
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime));

			tripDetails.put("BUNGII_DISTANCE", tripDistance);
			int rowNumber = getTripRowNumber(tripDetails);
			// it takes max 2.5 mins to di
			for (int i = 0; i < 5 && rowNumber != 999; i++) {
				Thread.sleep(30000);
				SetupManager.getDriver().navigate().refresh();
				scheduledTripsPage.waitForPageLoad();
				rowNumber = getTripRowNumber(tripDetails);
			}
			testStepVerify.isTrue(rowNumber == 999,
					"Bungii should be removed from the List", "Bungii is removed from the List",
					"Bungii is not removed from the List");


		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}

	}

	@Then("^I verify status and researches Bungii with following details$")
	public void i_researches_bungii_with_following_details(DataTable cancelDetails) {

		try {
			Map<String, String> data = cancelDetails.transpose().asMap(String.class, String.class);
			String status_of_trip = data.get("Status of Trip");
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);

			action.sendKeys(scheduledTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
			action.click(scheduledTripsPage.Button_Search());
			Thread.sleep(5000);
			//On admin panel CST time use to show
			//	getPortalTime("Aug 09, 06:15 AM CDT");
			//tripDetails.put("SCHEDULED_DATE", getCstTime(bungiiTime));
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime.replace("CDT", "CST").replace("EDT", "EST").replace("MDT", "MST")));
			tripDetails.put("BUNGII_DISTANCE", tripDistance);


			int rowNumber = getTripRowNumber(tripDetails);
			// it takes max 2.5 mins to appear
			for (int i = 0; i < 5 && rowNumber == 999; i++) {
				Thread.sleep(30000);
				SetupManager.getDriver().navigate().refresh();
				scheduledTripsPage.waitForPageLoad();
				rowNumber = getTripRowNumber(tripDetails);
			}
			verifyTripStatus(tripDetails, status_of_trip);
			researchBungii(tripDetails);
			String pickupRequest = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			Thread.sleep(30000);
			cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);

			pass("I should able to verify and research bungii", "I was able to verify and research bungii");

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}

	}
	@And("^I researches Bungii$")
	public void i__researches_bungii() throws Throwable {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);

			action.click(scheduledTripsPage.Button_Research());
			scheduledTripsPage.waitForPageLoad();
			String pickupRequest = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
			pass("I researches Bungii", "I researched Bungii. New Pickup Ref is "+ pickupRequest);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}
	@And("^I remove current driver and researches Bungii$")
	public void i_remove_current_driver_and_researches_bungii() throws Throwable {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);
			Thread.sleep(5000);
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime.replace("CDT", "CST").replace("EDT", "EST").replace("MDT", "MST")));
			tripDetails.put("BUNGII_DISTANCE", tripDistance);
			String pickupRequestOld = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));

			RemoveSoloDriverAndresearchBungii(tripDetails);
			Thread.sleep(30000);

			String pickupRequest = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);

			testStepVerify.isTrue(!pickupRequestOld.equalsIgnoreCase(pickupRequest), " Pickup request should be updated, Old pickup ref:" + pickupRequestOld + " , new pickup ref:" + pickupRequest);
			pass("I remove current driver and researches Bungii and verify pickup reference ", "I removeed current driver and researches Bungii and Pickup request should be updated, Old pickup ref:" + pickupRequestOld + " , new pickup ref:" + pickupRequest);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}

	@And("^I remove current driver$")
	public void i_remove_current_driver() throws Throwable {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);

			//action.sendKeys(scheduledTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
			//action.click(scheduledTripsPage.Button_Search());
			Thread.sleep(5000);
			//On admin panel CST time use to show
			//	getPortalTime("Aug 09, 06:15 AM CDT");
			//tripDetails.put("SCHEDULED_DATE", getCstTime(bungiiTime));
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime.replace("CDT", "CST").replace("EDT", "EST").replace("MDT", "MST")));
			tripDetails.put("BUNGII_DISTANCE", tripDistance);


			int rowNumber = getTripRowNumber(tripDetails);
			// it takes max 2.5 mins to appear
			for (int i = 0; i < 5 && rowNumber == 999; i++) {
				Thread.sleep(30000);
				SetupManager.getDriver().navigate().refresh();
				scheduledTripsPage.waitForPageLoad();
				rowNumber = getTripRowNumber(tripDetails);
			}
			String pickupRequestOld = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));

			RemoveDriver(tripDetails);
			Thread.sleep(30000);

			String pickupRequest = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);

			testStepVerify.isTrue(!pickupRequestOld.equalsIgnoreCase(pickupRequest), " Pickup request should be updated, Old pickup ref:" + pickupRequestOld + " , new pickup ref:" + pickupRequest);
			pass("I remove current driver and verify new pickupref is generated", "I removed current driver and Pickup request should be updated, Old pickup ref:" + pickupRequestOld + " , new pickup ref:" + pickupRequest);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}
	@And("^I remove current driver from edit popup$")
	public void i_remove_current_driver_from_edit() throws Throwable {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);

			//action.sendKeys(scheduledTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
			//action.click(scheduledTripsPage.Button_Search());
			Thread.sleep(5000);
			//On admin panel CST time use to show
			//	getPortalTime("Aug 09, 06:15 AM CDT");
			//tripDetails.put("SCHEDULED_DATE", getCstTime(bungiiTime));
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime.replace("CDT", "CST").replace("EDT", "EST").replace("MDT", "MST")));
			tripDetails.put("BUNGII_DISTANCE", tripDistance);


			int rowNumber = getTripRowNumber(tripDetails);
			// it takes max 2.5 mins to appear
			for (int i = 0; i < 5 && rowNumber == 999; i++) {
				Thread.sleep(30000);
				SetupManager.getDriver().navigate().refresh();
				scheduledTripsPage.waitForPageLoad();
				rowNumber = getTripRowNumber(tripDetails);
			}
			String pickupRequestOld = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));

			RemoveDriverFromEditPopup(tripDetails);
			Thread.sleep(30000);

			String pickupRequest = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
			pass("I remove current driver and verify new pickupref is generated", "I removed current driver and Pickup request should be updated, Old pickup ref:" + pickupRequestOld + " , new pickup ref:" + pickupRequest);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}
	@And("^I remove assigned driver$")
	public void i_remove_assigned_driver() throws Throwable {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);
			Thread.sleep(5000);
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime.replace("CDT", "CST").replace("EDT", "EST").replace("MDT", "MST")));
			tripDetails.put("BUNGII_DISTANCE", tripDistance);

			int rowNumber = getTripRowNumber(tripDetails);
			// it takes max 2.5 mins to appear
			for (int i = 0; i < 5 && rowNumber == 999; i++) {
				Thread.sleep(30000);
				SetupManager.getDriver().navigate().refresh();
				scheduledTripsPage.waitForPageLoad();
				rowNumber = getTripRowNumber(tripDetails);
			}
			String pickupRequestOld = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));

			RemoveDriver(tripDetails);
			cucumberContextManager.setScenarioContext("PICKUP_REQUEST_OLD", pickupRequestOld);
			pass("I remove current driver", "I removed assigned driver");

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}


	@Then("^new pickuref is generated$")
	public void automatically_researched() throws Throwable {
		try {

			String pickupRequestOld = (String)cucumberContextManager.getScenarioContext("PICKUP_REQUEST_OLD");
			Thread.sleep(5000);
			String pickupRequest = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);

			testStepVerify.isTrue(!pickupRequestOld.equalsIgnoreCase(pickupRequest), " Pickup request should be updated, Old pickup ref:" + pickupRequestOld + " , new pickup ref:" + pickupRequest);
			pass("I verify new pickupref is generated", "Pickup request should be updated, Old pickup ref:" + pickupRequestOld + " , new pickup ref:" + pickupRequest);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}
	@And("^I remove \"([^\"]*)\" driver and researches Bungii$")
	public void i_remove_something_driver_and_researches_bungii(String driverType) throws Throwable {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);

			/*action.sendKeys(scheduledTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
			action.click(scheduledTripsPage.Button_Search());
			Thread.sleep(5000);
			//On admin panel CST time use to show
			//	getPortalTime("Aug 09, 06:15 AM CDT");*/
			//tripDetails.put("SCHEDULED_DATE", getCstTime(bungiiTime));
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime.replace("CDT", "CST").replace("EDT", "EST").replace("MDT", "MST")));
			tripDetails.put("BUNGII_DISTANCE", tripDistance);


			/*int rowNumber = getTripRowNumber(tripDetails);
			// it takes max 2.5 mins to appear
			for (int i = 0; i < 5 && rowNumber == 999; i++) {
				Thread.sleep(30000);
				SetupManager.getDriver().navigate().refresh();
				scheduledTripsPage.waitForPageLoad();
				rowNumber = getTripRowNumber(tripDetails);
			}*/
			String pickupRequestOld = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));

            removeDriverAndResearchDeliveryAsAdmin(tripDetails, driverType);
			Thread.sleep(30000);

			String pickupRequest = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
			testStepVerify.isTrue(!pickupRequestOld.equalsIgnoreCase(pickupRequest), " Pickup request should be updated, Old pickup ref:" + pickupRequestOld + " , new pickup ref:" + pickupRequest);
			pass("I remove driver and researches Bungii ", "I removed "+driverType+"driver and researches Bungii",
					true);
			action.click(scheduledTripsPage.Button_ClosePopUp());


		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}

	@And("^I remove \"([^\"]*)\" driver from Bungii and researches it")
	public void i_remove__driver_and_researches_bungii(String driverType) throws Throwable {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime.replace("CDT", "CST").replace("EDT", "EST").replace("MDT", "MST")));
			tripDetails.put("BUNGII_DISTANCE", tripDistance);
			String pickupRequestOld = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));

			removeDriverAndResearchDeliveryAsAdminByName(tripDetails, driverType);
			Thread.sleep(30000);

			String pickupRequest = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
			testStepVerify.isTrue(!pickupRequestOld.equalsIgnoreCase(pickupRequest), " Pickup request should be updated, Old pickup ref:" + pickupRequestOld + " , new pickup ref:" + pickupRequest);
			pass("I remove driver and researches Bungii ", "I removed "+driverType+"driver and researches Bungii",
					true);
			action.click(scheduledTripsPage.Button_ClosePopUp());


		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}

	@And("^I open the trip for \"([^\"]*)\" customer$")
	public void i_open_the_trip_for_something_customer(String strArg1) throws Throwable {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			//String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);
			Thread.sleep(15000);
			action.clearSendKeys(scheduledTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
			action.click(scheduledTripsPage.Button_Search());
			Thread.sleep(5000);
			tripDetails.put("BUNGII_DISTANCE", tripDistance);

			int rowNumber = getTripRowNumber(tripDetails);
			// it takes max 2.5 mins to appear
			for (int i = 0; i < 3 && rowNumber == 999; i++) {
				Thread.sleep(30000);
				SetupManager.getDriver().navigate().refresh();
                scheduledTripsPage.waitForPageLoad();
				rowNumber = getTripRowNumber(tripDetails);

				if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS") || PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID")) {
					String currentKey = DriverManager.getCurrentKey();
					if(DriverManager.driverArray.size()>1) {
						for (Map.Entry<String, WebDriver> entry : DriverManager.driverArray.entrySet()) {
							entry.getValue().getPageSource();
							logger.detail("Pinging : "+ entry.getKey());
						}
						DriverManager.driverArray.get(currentKey).getPageSource();
						//Ping all instances to keep them running in browserstack, used in duo scenarioss
					}
				}
			}
			String pickupRequestOld = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			WebElement editButton;
			//Thread.sleep(10000);
			if (rowNumber != 999) {
//				  action.waitUntilIsElementExistsAndDisplayed(scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row"+rowNumber+"']/td/p[@id='btnEdit']")),30L);
//				editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row"+rowNumber+"']/td/p[@id='btnEdit']"));
//				editButton.click();
				action.waitUntilIsElementExistsAndDisplayed(scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row"+rowNumber+"']/td/div/img")),30L);
				action.click(scheduledTripsPage.findElement("//tr[@id='row"+rowNumber+"']/td/div/img", PageBase.LocatorType.XPath));
				editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row"+rowNumber+"']/td/div/ul/li//p[@id='btnEdit']"));
				editButton.click();
			} else
			{
				fail("Trip should be displayed in admin scheduled list ","Trip is not displayed in Admin scheduled list",true);
			}
			//AssignDriver(tripDetails);
			//Thread.sleep(30000);

			//String pickupRequest = utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			//cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
			//testStepVerify.isTrue(!pickupRequestOld.equalsIgnoreCase(pickupRequest), " Pickup request should be updated, Old pickup ref:" + pickupRequestOld + " , new pickup ref:" + pickupRequest);
			pass("I should able to open bungii", "I was able to open bungii",
					true);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Unable to view see scheduled delivery in admin portal",
					true);
		}
	}
	@And("^I open first trip for \"([^\"]*)\" customer$")
	public void i_open_first_trip_for_something__customer(String custName) throws Throwable {
		try {
			String[] name = custName.split(" ");

			action.clearSendKeys(scheduledTripsPage.Text_SearchCriteria(), name[0]);
			action.click(scheduledTripsPage.Button_Search());

			Thread.sleep(25000);
			List<WebElement> rows = scheduledTripsPage.findElements(String.format("//td/a[contains(text(),'%s')]/ancestor::tr/td/p[@id='btnEdit']",name[0]),PageBase.LocatorType.XPath);
			if(rows.size()>0)
				rows.get(0).click();
			else {
				String xpath = String.format("//td/a[contains(text(),'%s')]/ancestor::tr/td/p[@id='btnEdit']",name[0]);
				error("I open the trip for "+custName+" customer","Not Found Bungii with XPath :" +xpath, true);
			}
			pass("I should able to open trip", "I viewed scheduled delivery",
					false);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Problem in selecting a Bungii Scheduled deliveries in admin portal for customer "+custName,
					true);
		}
	}

	@And("^I open the trip for \"([^\"]*)\" the customer$")
	public void i_open_the_trip_for_something_the_customer(String custName) throws Throwable {
		try {
			String[] name = custName.split(" ");

			action.clearSendKeys(scheduledTripsPage.Text_SearchCriteria(),name[0]);
			action.click(scheduledTripsPage.Button_Search());

			Thread.sleep(25000);
/*			List<WebElement> rows = scheduledTripsPage.findElements(String.format("//td/a[contains(text(),'%s')]/ancestor::tr/td/p[@id='btnEdit']",name[0]),PageBase.LocatorType.XPath);
			if(rows.size()>0)
			rows.get(0).click();
			else {
			    String xpath = String.format("//td/a[contains(text(),'%s')]/ancestor::tr/td/p[@id='btnEdit']",name[0]);
                error("I open the trip for "+custName+" customer","Not Found Bungii with XPath :" +xpath, true);
            }*/

			List<WebElement> rows_editicon = scheduledTripsPage.findElements(String.format("//td/a[contains(text(),'%s')]/parent::td/following-sibling::td/div/img",name[0]),PageBase.LocatorType.XPath);
			List<WebElement> rows_editlink = scheduledTripsPage.findElements(String.format("//td/a[contains(text(),'%s')]/ancestor::td/following-sibling::td/div/ul/li/p[contains(text(),'Edit')]",name[0]),PageBase.LocatorType.XPath);

			if(rows_editicon.size()>0)
			{
				rows_editicon.get(0).click();
				rows_editlink.get(0).click();
			}

			pass("I should able to open trip", "I viewed scheduled delivery",
					false);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Problem in selecting a Bungii Scheduled deliveries in admin portal for customer "+custName,
					true);
		}
	}

	@And("^I open the trip for customer using pickupref$")
	public void i_open_the_trip_for_customer() throws Throwable {
		String pickupref = "";
		String customer = "";
		try {
			 pickupref = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
			 customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");

			action.clearSendKeys(scheduledTripsPage.Text_SearchCriteria(), pickupref);
			action.click(scheduledTripsPage.Button_Search());

			Thread.sleep(10000);
			action.click(scheduledTripsPage.findElement(String.format("//a[contains(text(),'%s')]/parent::td/following-sibling::td/div/img",customer),PageBase.LocatorType.XPath));
			action.click(scheduledTripsPage.findElement(String.format("//a[contains(text(),'%s')]/parent::td/following-sibling::td/div/ul/li/p[@id='btnEdit']",customer),PageBase.LocatorType.XPath));
/*			if(rows.size()>0)
				rows.get(0).click();*/
			pass("I should able to open trip", "I viewed scheduled delivery",
					false);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Problem in selecting a Bungii Scheduled deliveries in admin portal for customer with pickupref "+ pickupref,
					true);
		}
	}

	/**
	 * Find bungii and research it
	 *
	 * @param tripDetails Trip information
	 */
	public void RemoveSoloDriverAndresearchBungii(Map<String, String> tripDetails) {
		/*int rowNumber = getTripRowNumber(tripDetails);
		testStepAssert.isFalse(rowNumber == 999, "I should able to find bungii that is to be cancelled ", "I found bungii at row number " + rowNumber, " I was not able to find bungii");
		WebElement editButton;
		if (rowNumber == 0) {
			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//p[@id='btnEdit']"));
		} else
			//vishal[1403] : Updated xpath
			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row" + rowNumber + "']/td/p[@id='btnEdit']"));
		//	editButton=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr["+rowNumber+"]/td/p[@id='btnEdit']"));
		editButton.click();*/
		action.click(scheduledTripsPage.CheckBox_Driver1());
		String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
		if (numberOfDriver.equalsIgnoreCase("duo"))
			action.click(scheduledTripsPage.CheckBox_Driver2());

		action.click(scheduledTripsPage.Button_Remove());

		scheduledTripsPage.waitForPageLoad();
		try {
			Thread.sleep(120000);
		} catch (Exception e) {
		}
		action.click(scheduledTripsPage.Button_Research());
		scheduledTripsPage.waitForPageLoad();
	}

	/**
	 * Find bungii and research it
	 *
	 * @param tripDetails Trip information
	 */
	public void RemoveDriver(Map<String, String> tripDetails) {

		action.click(scheduledTripsPage.CheckBox_Driver1());
		String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
		if (numberOfDriver.equalsIgnoreCase("duo"))
			action.click(scheduledTripsPage.CheckBox_Driver2());

		action.click(scheduledTripsPage.Button_Remove());
		scheduledTripsPage.waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}

	}
	/**
	 * Find bungii and research it
	 *
	 * @param tripDetails Trip information
	 */
	public void RemoveDriverFromEditPopup(Map<String, String> tripDetails) {

		action.click(scheduledTripsPage.CheckBox_Driver1_Edit());
		String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
		if (numberOfDriver.equalsIgnoreCase("duo"))
			action.click(scheduledTripsPage.CheckBox_Driver2_Edit());

		action.click(scheduledTripsPage.Button_Remove_Edit());
		scheduledTripsPage.waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}

	}


	public void RemoveNonControlDriverAndresearchBungii(Map<String, String> tripDetails) {
		/*int rowNumber = getTripRowNumber(tripDetails);
		testStepAssert.isFalse(rowNumber == 999, "I should able to find bungii that is to be cancelled ", "I found bungii at row number " + rowNumber, " I was not able to find bungii");
		WebElement editButton;
		if (rowNumber == 0) {
			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//p[@id='btnEdit']"));
		} else
			//vishal[1403] : Updated xpath
			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row" + rowNumber + "']/td/p[@id='btnEdit']"));

		//	editButton=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr["+rowNumber+"]/td/p[@id='btnEdit']"));
		editButton.click();*/
		action.click(scheduledTripsPage.CheckBox_Driver1());
		String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
		if (numberOfDriver.equalsIgnoreCase("duo"))
			action.click(scheduledTripsPage.CheckBox_Driver2());

		action.click(scheduledTripsPage.Button_Remove());
		scheduledTripsPage.waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}


	}


	public void RemoveDriverAndresearchBungii(Map<String, String> tripDetails, String driverType) {
		/*int rowNumber = getTripRowNumber(tripDetails);
		testStepAssert.isFalse(rowNumber == 999, "I should able to find bungii that is to be cancelled ", "I found bungii at row number " + rowNumber, " I was not able to find bungii");
		WebElement editButton;
		if (rowNumber == 0) {
			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//p[@id='btnEdit']"));
		} else
			//vishal[1403] : Updated xpath
			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row" + rowNumber + "']/td/p[@id='btnEdit']"));
		//	editButton=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr["+rowNumber+"]/td/p[@id='btnEdit']"));
		editButton.click();*/
		if (driverType.equalsIgnoreCase("control")) {
			action.click(scheduledTripsPage.CheckBox_Driver1());
		} else if (driverType.equalsIgnoreCase("noncontrol")) {
			action.click(scheduledTripsPage.CheckBox_Driver2());
		}
		action.click(scheduledTripsPage.Button_Remove());
		scheduledTripsPage.waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}

		action.click(scheduledTripsPage.Button_Research());
		scheduledTripsPage.waitForPageLoad();
	}

    public void removeDriverAndResearchDeliveryAsAdmin(Map<String, String> tripDetails, String driverType) {
        if (driverType.equalsIgnoreCase("control")) {
            action.click(scheduledTripsPage.CheckBox_Driver1());
        } else if (driverType.equalsIgnoreCase("noncontrol")) {
            action.click(scheduledTripsPage.CheckBox_Driver2());
        }
        action.click(scheduledTripsPage.Button_Remove());
        scheduledTripsPage.waitForPageLoad();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }

        action.click(scheduledTripsPage.Button_Research());
        scheduledTripsPage.waitForPageLoad();
    }
	public void removeDriverAndResearchDeliveryAsAdminByName(Map<String, String> tripDetails, String driverType) {
		if (driverType.equalsIgnoreCase("controller")) {
			action.click(scheduledTripsPage.CheckBox_DriverByName((String)cucumberContextManager.getScenarioContext("DRIVER_1")));
		} else if (driverType.equalsIgnoreCase("noncontroller")) {
			action.click(scheduledTripsPage.CheckBox_DriverByName((String)cucumberContextManager.getScenarioContext("DRIVER_2")));
		}
		action.click(scheduledTripsPage.Button_Remove());
		scheduledTripsPage.waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}

		action.click(scheduledTripsPage.Button_Research());
		scheduledTripsPage.waitForPageLoad();
	}
	/**
	 * Got to trip details from list of scheduled list
	 *
	 * @param tripDetails customer infomation
	 * @return boolean value if trip was found or not
	 */
	public boolean gotToTripDetailsPage(Map<String, String> tripDetails) {
		String custName = tripDetails.get("CUSTOMER");
		String scheduledDate = tripDetails.get("SCHEDULED_DATE"), estimatedDistance = tripDetails.get("BUNGII_DISTANCE");
		boolean isFound = false;
		List<WebElement> rows = scheduledTripsPage.Row_TripDetails();
		for (WebElement row : rows) {
			String rowCustName = row.findElement(By.xpath("//td[5]")).getText(), rowSchduledTime = row.findElement(By.xpath("//td[4]")).getText(), rowEstimatedDistance = row.findElement(By.xpath("//td[6]")).getText();

			if (rowCustName.equals(custName) && rowEstimatedDistance.equals(estimatedDistance)) {
				WebElement tripDetailsLink = row.findElement(By.xpath("//td[4]/a"));
				action.click(tripDetailsLink);
				isFound = true;
			}
		}
		return isFound;
	}


	/**
	 * @param tripDetails customer information
	 * @return row number of the customer trip
	 */
	public int getTripRowNumber(Map<String, String> tripDetails) {
		String custName = tripDetails.get("CUSTOMER");
		//String scheduledDate = tripDetails.get("SCHEDULED_DATE"),
			String estimatedDistance = tripDetails.get("BUNGII_DISTANCE");
		String label = utility.getTimeZoneBasedOnGeofence();
		/*if (!scheduledDate.contains(label))
			scheduledDate = scheduledDate + " " + label;
		scheduledDate = scheduledDate.replace(":00 "," ").replace("CDT", "CST").replace("EDT", "EST").replace("MDT", "MST").replace(" GMT+5:30", "").replace(" GMT+05:30", "");
		*/
		int rowNumber = 999;
		List<WebElement> rows = scheduledTripsPage.Row_TripDetails();
		for (int i = 1; i <= rows.size(); i++) {

			String rowCustName = SetupManager.getDriver().findElement(By.xpath("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')][" + i + "]/td[7]")).getText();
			//String rowSchduledTime = SetupManager.getDriver().findElement(By.xpath("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')][" + i + "]/td[5]")).getText();
			String rowSrNumber = SetupManager.getDriver().findElement(By.xpath("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')][" + i + "]/td[2]")).getText();
			//logger.detail("Actual Row Details : "+ rowSchduledTime +" "+ rowCustName +" on "+ rowSrNumber );
			//logger.detail("Expected Row Details : "+ scheduledDate +" "+ custName +" ");
			if (rowCustName.equalsIgnoreCase(custName) ) { //&& scheduledDate.equalsIgnoreCase(rowSchduledTime)) {
				rowNumber = Integer.parseInt(rowSrNumber);
			}

		}
		return rowNumber;
	}

	/**
	 * Find bungii and cancel it
	 *
	 * @param tripDetails  Trip information
	 * @param cancelCharge Cancel charge that is to be entered
	 * @param comments     Comment to cancel trip
	 */
	public void cancelBungii(Map<String, String> tripDetails, String cancelCharge, String comments, String reason) {
		int rowNumber = getTripRowNumber(tripDetails);
		testStepAssert.isFalse(rowNumber == 999, "I should able to find bungii that is to be cancelled ", "I found bungii at row number " + rowNumber, " I was not able to find bungii");
		WebElement editButton,editlink;
		if (rowNumber == 0) {
//			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//p[@id='btnEdit']"));
			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("/td/following-sibling::td/div/img"));
			editlink = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("/td/div/ul/li/p[contains(text(),'Edit')]"));
		} else {
			//vishal[1403] : Updated xpath
//			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row" + rowNumber + "']/following-sibling::td/div/img"));
			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row" + rowNumber + "']/td/following-sibling::td/div/img"));
			editlink = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row" + rowNumber + "']/td/div/ul/li/p[contains(text(),'Edit')]"));
		}
		editButton.click();
		editlink.click();
		action.click(scheduledTripsPage.RadioBox_Cancel());
		//scheduledTripsPage.TextBox_CancelFee().sendKeys(cancelCharge); //Richa- Commented this line as the field already contained charge as '0'
		action.click(scheduledTripsPage.TextBox_CancelFee());
		scheduledTripsPage.TextBox_Comments().sendKeys(comments);
		action.selectElementByText(scheduledTripsPage.Select_CancellationReason(), reason);
		action.click(scheduledTripsPage.Button_Submit());
		scheduledTripsPage.waitForPageLoad();
//		action.invisibilityOfElementLocated(scheduledTripsPage.Loader_Wrapper());

	}

	/**
	 * Find bungii and research it
	 *
	 * @param tripDetails Trip information
	 */
	public void AssignDriver(Map<String, String> tripDetails) throws Exception {
		int rowNumber = getTripRowNumber(tripDetails);
		testStepAssert.isFalse(rowNumber == 999, "I should able to find bungii that is to be cancelled ", "I found bungii at row number " + rowNumber, " I was not able to find bungii");
		WebElement editButton;
		if (rowNumber == 0) {
			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//p[@id='btnEdit']"));
		} else
			//vishal[1403] : Updated xpath
			editButton = scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row" + rowNumber + "']/td/p[@id='btnEdit']"));
		//	editButton=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr["+rowNumber+"]/td/p[@id='btnEdit']"));
		editButton.click();
		Thread.sleep(10000);

		action.click(scheduledTripsPage.CheckBox_Driver1());
		String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
		if (numberOfDriver.equalsIgnoreCase("duo"))
			action.click(scheduledTripsPage.CheckBox_Driver2());

		action.click(scheduledTripsPage.Button_Remove());

		scheduledTripsPage.waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
		}
		action.click(scheduledTripsPage.Button_Research());
		scheduledTripsPage.waitForPageLoad();
	}

	@Then("^\"([^\"]*)\" message should be displayed on \"([^\"]*)\" page$")
	public void something_message_should_be_displayed_on_something_page(String messageElement, String screen) {
		try {
			boolean messageDisplayed = false;

			switch (messageElement.toUpperCase()) {
				case "BUNGII CANCEL":
					Thread.sleep(35000);
					testStepAssert.isElementTextEquals(scheduledTripsPage.Label_Message(),"Pick up has been successfully cancelled.","Pick up has been successfully cancelled. should be displayed","Pick up has been successfully cancelled. is displayed","Pick up has been successfully cancelled. is not displayed");
					break;
				default:
					error("UnImplemented Step or incorrect button name", "UnImplemented Step");
					break;
			}

		} catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}



	@And("^I Select \"([^\"]*)\" option$")
	public void i_select_something_option(String option) throws Throwable {
		try {
			switch (option) {
				case "Edit Trip Details":
					Thread.sleep(10000);
					action.click(scheduledTripsPage.RadioBox_EditTrip());
					break;
				case "Research Driver":
					action.click(scheduledTripsPage.RadioBox_Research());
					break;
				case "Cancel Trip":
					action.click(scheduledTripsPage.RadioBox_Cancel());
					break;
				default:
					error("UnImplemented Step or incorrect option.", "UnImplemented Step");
					break;
			}
			pass("I should able to select option", "I was able to select option");
		} catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}
	@And("^I assign \"([^\"]*)\" driver to Bungii$")
	public void i_assign_for_the_something_trip(String tripType) throws Throwable {
		try {
			switch (tripType) {
				case "Solo":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					String driver1Name = scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER1_NAME", driver1Name);
					break;
				case "Duo":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					driver1Name = scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
					String driver2Name = scheduledTripsPage.Text_EditTrpDetailsDriver2Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER1_NAME", driver1Name);
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER2_NAME", driver2Name);
					break;
				case "controller":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					driver1Name = scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER1_NAME", driver1Name);
					break;
				case "noncontroller":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					driver2Name = scheduledTripsPage.Text_EditTrpDetailsDriver2Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER2_NAME", driver2Name);
					break;
				case "control driver":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					driver1Name = scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER1_NAME", driver1Name);
					break;

				case "current":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys(tripType);
					action.click(scheduledTripsPage.Select_TestDriver());
					driver1Name = scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER1_NAME", driver1Name);
					break;
				default:
					error("UnImplemented Step or incorrect Trip Type.", "UnImplemented Step");
					break;

			}
			log("I assign driver for the "+tripType+" trip","I assigned driver for the "+tripType+" trip", false);
		} catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}

	@And("^I assign driver for the \"([^\"]*)\" trip$")
	public void i_assign_driver_for_the_something_trip(String tripType) throws Throwable {
		try {
			switch (tripType) {
				case "Solo":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					String driver1Name = scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER1_NAME", driver1Name);
					break;
				case "Duo":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					driver1Name = scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
					String driver2Name = scheduledTripsPage.Text_EditTrpDetailsDriver2Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER1_NAME", driver1Name);
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER2_NAME", driver2Name);
					break;
				case "control":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					driver1Name = scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER1_NAME", driver1Name);
					break;
				case "noncontrol":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					driver2Name = scheduledTripsPage.Text_EditTrpDetailsDriver2Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER2_NAME", driver2Name);
					break;
				case "control driver":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys("Test");
					action.click(scheduledTripsPage.Select_TestDriver());
					driver1Name = scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER1_NAME", driver1Name);
					break;

				case "current":
					scheduledTripsPage.TextBox_DriverSearch().sendKeys(tripType);
					action.click(scheduledTripsPage.Select_TestDriver());
					driver1Name = scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
					cucumberContextManager.setScenarioContext("ASSIGNED_DRIVER1_NAME", driver1Name);
					break;
				default:
					error("UnImplemented Step or incorrect Trip Type.", "UnImplemented Step");
					break;

			}
			log("I assign driver for the "+tripType+" trip","I assigned driver for the "+tripType+" trip", false);
		} catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}

  
	@And("^\"([^\"]*)\" should not be displayed$")
	public void something_should_not_be_displayed(String option) throws Throwable {
		try {
			boolean displayed = false;

			switch (option) {
				case "Cancel button":
					displayed = action.isElementPresent(scheduledTripsPage.Button_Submit(true));
					break;
				default:
					error("UnImplemented Step or incorrect button name", "UnImplemented Step");
					break;
			}
			testStepVerify.isFalse(displayed,
					displayed + " should be displayed", displayed + " Message is Displayed",
					displayed + " Message is not Displayed");
		} catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}

  
	@Then("^I am not allowed to assign more drivers$")
	public void i_am_not_allowed_to_assign_more_drivers() throws Throwable {
		//String textBoxAttribute= scheduledTripsPage.TextBox_DriverSearch().getAttribute("disabled");
		try {
			testStepAssert.isElementNotEnabled(scheduledTripsPage.TextBox_DriverSearch(), "The textbox should be disabled.", "The textbox is disabled.", "The textbox is not disabled.");
			}
		catch(Throwable e)
		{
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}

	}

	@And("^I assign driver \"([^\"]*)\" for the trip$")
	public void i_assign_driver_something_for_the_trip(String driverName) throws Throwable {
		try{
			scheduledTripsPage.TextBox_DriverSearch().sendKeys(driverName);
			scheduledTripsPage.Select_TestDriver().click();
			String driver1Name=scheduledTripsPage.Text_EditTrpDetailsDriver1Name().getText();
			cucumberContextManager.setScenarioContext("DRIVER1_NAME",driver1Name);      
			cucumberContextManager.setScenarioContext("DRIVER2_NAME",driver1Name);
		}catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error in assigning driver "+driverName+" to the delivery by admin or viewing assigned driver slot", true);
		}
	}

	/**
	 * Find bungii and research it
	 * @param tripDetails Trip information
	 */
	public void researchBungii(Map<String,String> tripDetails){
		int rowNumber =getTripRowNumber(tripDetails);
		testStepAssert.isFalse(rowNumber==999, "I should able to find bungii that is to be cancelled ","I found bungii at row number "+rowNumber," I was not able to find bungii");
		WebElement editButton;
		if(rowNumber==0){
			editButton=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//p[@id='btnEdit']"));
		}else
			//vishal[1403] : Updated xpath
			editButton=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row"+rowNumber+"']/td/p[@id='btnEdit']"));
		//	editButton=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr["+rowNumber+"]/td/p[@id='btnEdit']"));
		editButton.click();
		action.click(scheduledTripsPage.Button_Research());
		scheduledTripsPage.waitForPageLoad();
	}

	/**
	 * Find bungii and  verify status
	 * @param tripDetails Trip information
	 */
	public void verifyTripStatus(Map<String,String> tripDetails,String status){
		int rowNumber =getTripRowNumber(tripDetails);
		testStepAssert.isFalse(rowNumber==999, "I should able to find bungii that is to be cancelled ","I found bungii at row number "+rowNumber," I was not able to find bungii");
		WebElement tripStatus;

		tripStatus=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row"+rowNumber+"']/td[11]"));
		testStepVerify.isElementTextEquals(tripStatus,status);
	}

	@And("^the \"([^\"]*)\" message is displayed$")
	public void the_something_message_is_displayed(String message) throws Throwable {
		try{
			String actualMessage = null;
			switch (message){
				case "Your changes are good to be saved.":
					actualMessage=action.getText(scheduledTripsPage.Text_VerifyChangesSavedMessage());
					break;

				case "Bungii Saved!":
					actualMessage=action.getText(scheduledTripsPage.Text_SuccessMessage());
					break;

				case "Adding a driver through this feature overrides driver assigning restrictions.":
					actualMessage=action.getText(scheduledTripsPage.Label_StaticText());
					break;

				case "It looks like customer already has a Bungii scheduled at this time. Customer can have only one Bungii at a time":
					actualMessage=action.getText(scheduledTripsPage.Text_ConflictMessageError());
					break;

				case "Please check the date/time selected. You cannot select a past date/time.":
					actualMessage=action.getText(scheduledTripsPage.Text_ConflictMessageError());
                    break;
				default:
					error("UnImplemented Step or incorrect option.", "UnImplemented Step");
					break;
			}
				testStepAssert.isTrue(actualMessage.contains(message),"Expected message is displayed.","Expected message is not displayed. Actual is : "+actualMessage );

		}catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error in viewing "+message+"message", true);
		}
	}
	@When("^I close \"([^\"]*)\" popup$")
	public void i_close_something_popup(String strArg1) throws Throwable {
		try{
			action.click(scheduledTripsPage.icon_Close());
			pass("I close "+ strArg1+" popup", "I closed "+ strArg1+" popup");

		}catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}

	}
	@And("^I change the \"([^\"]*)\" to future time$")
	public void i_change_the_something_to_future_time(String strArg1) throws Throwable {
		try{
		Thread.sleep(2000);
			String newTime ="";
			String currentTime=scheduledTripsPage.Time_EditTripDetailsTime().getAttribute("value");
		switch (strArg1) {
			case "trip time":
				action.click(scheduledTripsPage.Calendar_EditTripDetailsScheduledDate());
				Thread.sleep(2000);
				action.click(scheduledTripsPage.Calendar_NextDate());
				//String newTime = GetNewScheduledTime(currentTime);
				action.click(scheduledTripsPage.Time_EditTripDetailsTime());
				newTime = scheduledTripsPage.Time_FirstAvailable().getText();
				cucumberContextManager.setScenarioContext("NEW_TIME", newTime);
				WebElement selectTime = SetupManager.getDriver().findElement(By.xpath("//li[contains(text(),'" + newTime + "')]"));
				action.click(selectTime);
				logger.detail("I update time to "+newTime,"I updated time to "+newTime, false);

				break;
			case "particular trip time":
				newTime = (String)cucumberContextManager.getScenarioContext("OLD_BUNGII_TIME");
				Date d1=new SimpleDateFormat("hh:mm aa").parse(newTime);
				Calendar c = Calendar.getInstance();
				c.setTime(d1);
				c.add(Calendar.HOUR,1);
				Date d2 = c.getTime();

				SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm aa");
				newTime = formatter1.format(d2);
				cucumberContextManager.setScenarioContext("NEW_TIME", newTime);
				action.click(scheduledTripsPage.Time_EditTripDetailsTime());
				selectTime = SetupManager.getDriver().findElement(By.xpath("//li[contains(text(),'" + newTime + "')]"));
				action.click(selectTime);
				logger.detail("I update time to "+newTime,"I updated time to "+newTime, false);

				break;
			case "particular trip time 2 hours later":
				newTime = (String)cucumberContextManager.getScenarioContext("OLD_BUNGII_TIME");
				Date d3=new SimpleDateFormat("hh:mm aa").parse(newTime);
				Calendar c3 = Calendar.getInstance();
				c3.setTime(d3);
				c3.add(Calendar.HOUR,2);
				Date d4 = c3.getTime();

				SimpleDateFormat formatter2 = new SimpleDateFormat("hh:mm aa");
				newTime = formatter2.format(d4);
				cucumberContextManager.setScenarioContext("NEW_TIME", newTime);
				action.click(scheduledTripsPage.Time_EditTripDetailsTime());
				selectTime = SetupManager.getDriver().findElement(By.xpath("//li[contains(text(),'" + newTime + "')]"));
				action.click(selectTime);
				logger.detail("I update time to "+newTime,"I updated time to "+newTime, false);

				break;
			case "1.5 hour ahead":
			case "3 hour ahead":
				action.click(scheduledTripsPage.Time_EditTripDetailsTime());
				String firstTime = scheduledTripsPage.Time_FirstAvailable().getText();
				selectTime = scheduledTripsPage.Time_FirstAvailable().findElement(By.xpath("//following::li[3]"));
				//selectTime = SetupManager.getDriver().findElement(By.xpath("//li[contains(text(),'" + newTime + "')]"));
				action.click(selectTime);
				logger.detail("I update time to "+newTime,"I updated time to "+newTime, false);

				break;
			case "trip time to past":
				newTime = currentTime;
				DateFormat formatter = new SimpleDateFormat("hh:mm a");
				Date NT = formatter.parse(newTime);

				Calendar CL = Calendar.getInstance();
				CL.setTime(NT);
				CL.add(Calendar.MINUTE,-60);

				Date NT1 = CL.getTime();
				String newTime1 = formatter.format(NT1);

				cucumberContextManager.setScenarioContext("NEW_TIME", newTime1);
				action.click(scheduledTripsPage.Time_EditTripDetailsTime());
				selectTime = SetupManager.getDriver().findElement(By.xpath("//li[contains(text(),'" + newTime1 + "')]"));
				action.click(selectTime);
				logger.detail("I update time to "+newTime1,"I updated time to "+newTime1, false);

				break;
		}
		}catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}
	@Then("^Updated time change is displayed$")
	public void i_verify_change_is_saved() throws Throwable {
		try{
			Thread.sleep(1000);
			String expectedTime=(String) cucumberContextManager.getScenarioContext("NEW_TIME");
			String actualTime=action.getText(scheduledTripsPage.Label_ChangedScheduledTime());

			System.out.println("Expected Time: "+expectedTime);
			System.out.println("Actual Time: "+actualTime);

			DateFormat formater = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
			Date date = formater.parse(expectedTime);
			DateFormat format2 = new SimpleDateFormat("H:mm", Locale.ENGLISH);
			expectedTime = format2.format(date);

			testStepAssert.isTrue(actualTime.contains(expectedTime),"Expected time is displayed.", "Expected time is not displayed :" + expectedTime+" instead "+ actualTime +" is displayed");

		}catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}

	@Then("^I verify that time change is saved$")
	public void i_verify_that_time_change_is_saved() throws Throwable {
		try{
		Thread.sleep(1000);
		action.click(scheduledTripsPage.Button_ClosePopUp());

		String expectedTime=(String) cucumberContextManager.getScenarioContext("NEW_TIME");
			String actualTime=action.getText(scheduledTripsPage.Label_ChangedScheduledTime());

		System.out.println("Expected Time: "+expectedTime);
		System.out.println("Actual Time: "+actualTime);

			DateFormat formater = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
			Date date = formater.parse(expectedTime);
			DateFormat format2 = new SimpleDateFormat("H:mm", Locale.ENGLISH);
			 expectedTime = format2.format(date);

			testStepAssert.isTrue(actualTime.contains(expectedTime),"Expected time is displayed.", "Expected time is not displayed :" + expectedTime+" instead "+ actualTime +" is displayed");

		}catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}
	@Then("^I should see updated data time$")
	public void i_verify_that_updateddate_is_saved() throws Throwable {
		try{
			Thread.sleep(1000);

			String expectedTime=(String) cucumberContextManager.getScenarioContext("NEW_TIME");
			String actualTime=action.getText(scheduledTripsPage.Label_ChangedScheduledTime());

			System.out.println("Expected Time: "+expectedTime);
			System.out.println("Actual Time: "+actualTime);

			DateFormat formater = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
			Date date = formater.parse(expectedTime);
			DateFormat format2 = new SimpleDateFormat("H:mm", Locale.ENGLISH);
			expectedTime = format2.format(date);

			testStepAssert.isTrue(actualTime.contains(expectedTime),"Expected time is displayed.", "Expected time is not displayed :" + expectedTime+" instead "+ actualTime +" is displayed");

		}catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}
	@Then("^I verify that time change is saved correctly$")
	public void i_verify_that_time_change_is_saved_correctly() throws Throwable {
		try{
		Thread.sleep(1000);
		String expectedTime=(String) cucumberContextManager.getScenarioContext("NEW_TIME");
		String actualTime=action.getText(scheduledTripsPage.Text_BungiiTime());

		System.out.println("Expected Time: "+expectedTime);
		System.out.println("Actual Time: "+actualTime);
		if(actualTime.contains(expectedTime)){
			testStepAssert.isTrue(true,"Expected time is displayed.", "Expected time is not displayed.");
		}
		else
		{
			testStepAssert.isFail("Expected time is not displayed.");
		}
		}catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}

	@And("^I save the Bungii Time$")
	public void i_save_the_bungii_time() throws Throwable {
		try{
		String time=(String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
		String saveTime= time.substring(8,16);
		cucumberContextManager.setScenarioContext("OLD_BUNGII_TIME", saveTime);
		}catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}

	@Then("^I check that time is not displayed in UTC$")
	public void i_check_that_time_is_not_displayed_in_utc() throws Throwable
	{
		try {
			String actualScheduledBungiiTime = scheduledTripsPage.Text_ScheduledBungiiTime().getText();
			String expectedScheduledBungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
			if (actualScheduledBungiiTime.contains("IST")) {
				pass(expectedScheduledBungiiTime, actualScheduledBungiiTime, true);
			} else {
				fail(expectedScheduledBungiiTime, actualScheduledBungiiTime, true);
			}
		}
		catch (Exception e){
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}

	@And("^I verify that noncontrol driver becomes control driver$")
	public void i_verify_that_noncontrol_driver_becomes_control_driver() throws Throwable
	{

	}

	@And("^I check if a validation message \"([^\"]*)\" is shown$")
	public void i_check_if_a_validation_message_something_is_shown(String expectedMessage) throws Throwable {
		try{
		testStepAssert.isElementTextEquals(scheduledTripsPage.Label_IconTextMessage(),expectedMessage,"I check if a validation message is displayed","Validation message is displayed","Validation message is not displayed");
		}
			catch (Exception e){
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Validation message is not shown ", true);
		}
	}

	public String GetNewScheduledTime(String currentTime){
		String newTime=null,t2 = null;
		String time=currentTime.substring(0,2);
		int t=Integer.parseInt(time);
		int t1=01;
		t=t+t1;
		if(t>0 && t<10){
			t2="0"+t;
		}
		newTime=t2+currentTime.substring(2,8);

		return newTime;
	}

	@And("^I Select reason as \"([^\"]*)\" to edit datetime$")
	public void i_select_reason_as_something_to_edit_datetime(String reason) throws Throwable {
	    try {
            testStepAssert.isElementDisplayed(scheduledTripsPage.Select_EditReason(),"Select reason dropdown should be displayed on editing date/time","Select reason dropdown is displayed on editing date/time","Select reason dropdown is NOT displayed on editing date/time");
            switch (reason)
            {
                case "Partner initiated" :
                    action.selectElementByText(scheduledTripsPage.Select_EditReason(),reason);
                    break;
				case "Customer initiated" :
					action.selectElementByText(scheduledTripsPage.Select_EditReason(),reason);
					break;
            }
        }
        catch (Exception e){
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
	}
}