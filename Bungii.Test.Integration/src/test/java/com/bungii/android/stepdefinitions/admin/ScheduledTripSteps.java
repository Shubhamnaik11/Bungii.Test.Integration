/**
 * 
 */
package com.bungii.android.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.ScheduledTripsPage;
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
			//On admin panel CST time use to show
		//	getPortalTime("Dec 21, 11:15 AM IST");
			//tripDetails.put("SCHEDULED_DATE", getCstTime(bungiiTime));
			tripDetails.put("SCHEDULED_DATE", getPortalTime(bungiiTime));
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
		int rowNumber=999;
		List<WebElement> rows= scheduledTripsPage.Row_TripDetails();
		for(int i=1;i<=rows.size();i++){
			String rowCustName= SetupManager.getDriver().findElement(By.xpath("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')]["+i+"]/td[5]")).getText();
			String rowSchduledTime= SetupManager.getDriver().findElement(By.xpath("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')]["+i+"]/td[4]")).getText();
			String rowEstimatedDistance= SetupManager.getDriver().findElement(By.xpath("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')]["+i+"]/td[6]")).getText();
			String rowSrNumber= SetupManager.getDriver().findElement(By.xpath("//table[@id='tblTripList']/tbody/tr[contains(@id,'row')]["+i+"]/td[1]")).getText();

			if(rowCustName.equals(custName) &&scheduledDate.equalsIgnoreCase(rowSchduledTime)){
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
		editButton=scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr["+rowNumber+"]/td/p[@id='btnEdit']"));
		editButton.click();
		action.click(scheduledTripsPage.RadioBox_Cancel());
		scheduledTripsPage.TextBox_CancelFee().sendKeys(cancelCharge);
		scheduledTripsPage.TextBox_Comments().sendKeys(comments);
		action.click(scheduledTripsPage.Button_Submit());
		scheduledTripsPage.waitForPageLoad();
//		action.invisibilityOfElementLocated(scheduledTripsPage.Loader_Wrapper());

	}


}
