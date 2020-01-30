/**
 * 
 */
package com.bungii.android.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.android.pages.admin.ScheduledTripsPage;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;


public class ScheduledTripSteps extends DriverBase {
	private ScheduledTripsPage scheduledTripsPage;
	ActionManager action = new ActionManager();
	GeneralUtility utility = new GeneralUtility();
	private static LogUtility logger = new LogUtility(com.bungii.ios.stepdefinitions.admin.ScheduledTripSteps.class);

	public ScheduledTripSteps(ScheduledTripsPage scheduledTripsPage) {
		this.scheduledTripsPage = scheduledTripsPage;
	}

	@Then("^I Cancel Bungii with following details$")
	public void i_cancel_bungii_with_following_details(DataTable cancelDetails)  {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");

			String tripDistance = (String)  cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String)  cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);
			action.sendKeys(scheduledTripsPage.Text_SearchCriteria(),custName.substring(0,custName.indexOf(" ")));
			action.click(scheduledTripsPage.Button_Search());Thread.sleep(5000);
			//On admin panel CST time use to show
		//	getPortalTime("Dec 21, 11:15 AM IST");
			//tripDetails.put("SCHEDULED_DATE", getCstTime(bungiiTime));
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime.replace("CDT","CST").replace("EDT","EST").replace("MDT","MST")));
			tripDetails.put("BUNGII_DISTANCE", tripDistance);

			Map<String, String> data = cancelDetails.transpose().asMap(String.class, String.class);
			String cancelCharge = data.get("Charge"), comments = data.get("Comments");

			int rowNumber = getTripRowNumber(tripDetails);
			// it takes max 2.5 mins to appear
			for (int i = 0; i < 5 && rowNumber == 999; i++) {
				Thread.sleep(30000);
				SetupManager.getDriver().navigate().refresh();
				scheduledTripsPage.waitForPageLoad();
				rowNumber = getTripRowNumber(tripDetails);
			}
			cancelBungii(tripDetails, cancelCharge, comments);
			log( "I should able to cancel bungii", "I was able to cancel bungii",
					true);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details",
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

		int intYear=calendar.get(Calendar.YEAR);

		String formattedDate = bungiiTime.substring(0, 7)+" "+intYear+ bungiiTime.substring(7, 13) + ":00" + bungiiTime.substring(13, bungiiTime.length());

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
			error( "Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}

	}

	@Then("^I verify status and researches Bungii with following details$")
	public void i_researches_bungii_with_following_details(DataTable cancelDetails)  {

		try {
			Map<String, String> data = cancelDetails.transpose().asMap(String.class, String.class);
			String status_of_trip = data.get("Status of Trip");
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String)  cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String)  cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);

			action.sendKeys(scheduledTripsPage.Text_SearchCriteria(),custName.substring(0,custName.indexOf(" ")));
			action.click(scheduledTripsPage.Button_Search());Thread.sleep(5000);
			//On admin panel CST time use to show
			//	getPortalTime("Aug 09, 06:15 AM CDT");
			//tripDetails.put("SCHEDULED_DATE", getCstTime(bungiiTime));
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime.replace("CDT","CST").replace("EDT","EST").replace("MDT","MST")));
			tripDetails.put("BUNGII_DISTANCE", tripDistance);


			int rowNumber = getTripRowNumber(tripDetails);
			// it takes max 2.5 mins to appear
			for (int i = 0; i < 5 && rowNumber == 999; i++) {
				Thread.sleep(30000);
				SetupManager.getDriver().navigate().refresh();
				scheduledTripsPage.waitForPageLoad();
				rowNumber = getTripRowNumber(tripDetails);
			}
			verifyTripStatus(tripDetails,status_of_trip);
			researchBungii(tripDetails);
			String pickupRequest=utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			Thread.sleep(30000);
			cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickupRequest);

			log( "I should able to cancel bungii", "I was able to cancel bungii",
					true);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}

	}
	@And("^I remove current driver and researches Bungii$")
	public void i_remove_current_driver_and_researches_bungii() throws Throwable {
		try {
			Map<String, String> tripDetails = new HashMap<String, String>();
			String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String tripDistance = (String)  cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
			String bungiiTime = (String)  cucumberContextManager.getScenarioContext("BUNGII_TIME");
			tripDetails.put("CUSTOMER", custName);

			action.sendKeys(scheduledTripsPage.Text_SearchCriteria(),custName.substring(0,custName.indexOf(" ")));
			action.click(scheduledTripsPage.Button_Search());Thread.sleep(5000);
			//On admin panel CST time use to show
			//	getPortalTime("Aug 09, 06:15 AM CDT");
			//tripDetails.put("SCHEDULED_DATE", getCstTime(bungiiTime));
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime.replace("CDT","CST").replace("EDT","EST").replace("MDT","MST")));
			tripDetails.put("BUNGII_DISTANCE", tripDistance);


			int rowNumber = getTripRowNumber(tripDetails);
			// it takes max 2.5 mins to appear
			for (int i = 0; i < 5 && rowNumber == 999; i++) {
				Thread.sleep(30000);
				SetupManager.getDriver().navigate().refresh();
				scheduledTripsPage.waitForPageLoad();
				rowNumber = getTripRowNumber(tripDetails);
			}
			String pickupRequestOld=utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));

			RemoveSoloDriverAndresearchBungii(tripDetails);
			Thread.sleep(30000);

			String pickupRequest=utility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"));
			cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickupRequest);
			testStepVerify.isTrue(!pickupRequestOld.equalsIgnoreCase(pickupRequest)," Pickup request should be updated, Old pickup ref:"+pickupRequestOld+" , new pickup ref:"+pickupRequest);
			log( "I should able to cancel bungii", "I was able to cancel bungii",
					true);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}	}
	/**
	 * Find bungii and research it
	 * @param tripDetails Trip information
	 */
	public void RemoveSoloDriverAndresearchBungii(Map<String,String> tripDetails){
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
		action.click(scheduledTripsPage.CheckBox_Driver1());
		String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
		if(numberOfDriver.equalsIgnoreCase("duo"))
			action.click(scheduledTripsPage.CheckBox_Driver2());

		action.click(scheduledTripsPage.Button_Remove());
		scheduledTripsPage.waitForPageLoad();try{Thread.sleep(5000);}catch (Exception e ){}
		action.click(scheduledTripsPage.Button_Research());
		scheduledTripsPage.waitForPageLoad();
	}
	/**
	 * Got to trip details from list of scheduled list
	 * @param tripDetails customer infomation
	 * @return boolean value if trip was found or not
	 */
	public boolean gotToTripDetailsPage(Map<String,String> tripDetails){
		String custName = tripDetails.get("CUSTOMER");
		String scheduledDate= tripDetails.get("SCHEDULED_DATE"),estimatedDistance=tripDetails.get("BUNGII_DISTANCE");
		boolean isFound=false;
		List<WebElement> rows= scheduledTripsPage.Row_TripDetails();
		for(WebElement row:rows){
			String rowCustName=row.findElement(By.xpath("//td[5]")).getText(),rowSchduledTime=row.findElement(By.xpath("//td[4]")).getText(),rowEstimatedDistance=row.findElement(By.xpath("//td[6]")).getText();

			if(rowCustName.equals(custName) && rowEstimatedDistance.equals(estimatedDistance)){
				WebElement tripDetailsLink=row.findElement(By.xpath("//td[4]/a"));
				action.click(tripDetailsLink);
				isFound=true;
			}
		}
		return isFound;
	}

	/**
	 * @param tripDetails customer information
	 * @return row number of the customer trip
	 */
	public int getTripRowNumber(Map<String,String> tripDetails){
		String custName = tripDetails.get("CUSTOMER");
		String scheduledDate= tripDetails.get("SCHEDULED_DATE"),estimatedDistance=tripDetails.get("BUNGII_DISTANCE");
		String label=utility.getTimeZoneBasedOnGeofence();
		if(!scheduledDate.contains(label))
		scheduledDate=scheduledDate+" "+label;
		scheduledDate=scheduledDate.replace("CDT","CST").replace("EDT","EST").replace("MDT","MST");
		int rowNumber=999;
		List<WebElement> rows= scheduledTripsPage.Row_TripDetails();
		for(int i=1;i<=rows.size();i++){
			String rowCustName= SetupManager.getDriver().findElement(By.xpath("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')]["+i+"]/td[5]")).getText();
			String rowSchduledTime= SetupManager.getDriver().findElement(By.xpath("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')]["+i+"]/td[4]")).getText();
			String rowEstimatedDistance= SetupManager.getDriver().findElement(By.xpath("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')]["+i+"]/td[6]")).getText();
			String rowSrNumber= SetupManager.getDriver().findElement(By.xpath("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')]["+i+"]/td[1]")).getText();

			if(rowCustName.equalsIgnoreCase(custName) &&scheduledDate.equalsIgnoreCase(rowSchduledTime)){
				rowNumber=Integer.parseInt(rowSrNumber);
			}

		}
		return rowNumber;
	}

	/**
	 * Find bungii and cancel it
	 * @param tripDetails Trip information
	 * @param cancelCharge Cancel charge that is to be entered
	 * @param comments Comment to cancel trip
	 */
	public void cancelBungii(Map<String,String> tripDetails,String cancelCharge,String comments){
		int rowNumber =getTripRowNumber(tripDetails);
		testStepAssert.isFalse(rowNumber==999, "I should able to find bungii that is to be cancelled ","I found bungii at row number "+rowNumber," I was not able to find bungii");
		WebElement editButton;
		if(rowNumber==0){
			editButton=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//p[@id='btnEdit']"));
		}else
			//vishal[1403] : Updated xpath
		editButton=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row"+rowNumber+"']/td/p[@id='btnEdit']"));
		editButton.click();
		action.click(scheduledTripsPage.RadioBox_Cancel());
		scheduledTripsPage.TextBox_CancelFee().sendKeys(cancelCharge);
		scheduledTripsPage.TextBox_Comments().sendKeys(comments);
		action.click(scheduledTripsPage.Button_Submit());
		scheduledTripsPage.waitForPageLoad();
//		action.invisibilityOfElementLocated(scheduledTripsPage.Loader_Wrapper());

	}

	@Then("^\"([^\"]*)\" message should be displayed on \"([^\"]*)\" page$")
	public void something_message_should_be_displayed_on_something_page(String messageElement, String screen) {
		try {
			boolean messageDisplayed = false;

			switch (messageElement.toUpperCase()) {
				case "BUNGII CANCEL":
					Thread.sleep(35000);
					messageDisplayed = scheduledTripsPage.isElementEnabled(scheduledTripsPage.Text_Success()) && scheduledTripsPage.Text_Success().getText().equals(PropertyUtility.getMessage("admin.cancel.sucess"));
					break;
				default:
					error("UnImplemented Step or incorrect button name", "UnImplemented Step");
					break;
			}
			testStepVerify.isTrue(messageDisplayed,
					messageElement + " should be displayed", messageElement + " Message is Displayed",
					messageElement + " Message is not Displayed");
		} catch (Throwable e) {
			logger.error("Error performing step" + e);
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
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

		tripStatus=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row"+rowNumber+"']/td[9]"));
		testStepVerify.isElementTextEquals(tripStatus,status);
	}


}
