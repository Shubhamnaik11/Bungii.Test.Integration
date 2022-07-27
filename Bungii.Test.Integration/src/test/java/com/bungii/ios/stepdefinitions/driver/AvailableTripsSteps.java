package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.ios.enums.Rejection_Reason;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.ScheduledTripsPage;
import com.bungii.ios.pages.driver.AvailableTripsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;
import com.bungii.ios.utilityfunctions.DbUtility;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;


public class AvailableTripsSteps extends DriverBase {
	AvailableTripsPage availableTripsPage;
	private static LogUtility logger = new LogUtility(AvailableTripsSteps.class);
	DbUtility dbUtility = new DbUtility();
	ActionManager action = new ActionManager();
	public AvailableTripsSteps(AvailableTripsPage availableTripsPage) {
		this.availableTripsPage = availableTripsPage;
	}
	String Image_Solo="bungii_type-solo",Image_Duo="bungii_type-duo";
	ScheduledTripsPage scheduledTripsPage = new ScheduledTripsPage();

	@And("^I Select Trip from available trip$")
	public void i_select_trip_from_available_trip() {
		try {
			Thread.sleep(5000);
			String milesText  = action.getText(availableTripsPage.Text_FromHomeMiles());
			Thread.sleep(2000);
			boolean isMilesPresent = (milesText.contains("miles") || milesText.contains("mile") )? true : false;

			testStepAssert.isTrue(isMilesPresent,"Text should be updated to miles","Text is updated to miles","Text is not updated to miles");

			if (action.isAlertPresent()){ SetupManager.getDriver().switchTo().alert().dismiss();   Thread.sleep(1000);        }

			String customerName=(String) cucumberContextManager.getScenarioContext("CUSTOMER");

			String numberOfDriver=(String)cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");
		//	customerName="Vishal B";numberOfDriver="DUO";
			//selectBungiiFromList(numberOfDriver,customerName.substring(0, customerName.indexOf(" ")+2));--removing this since now full name is displaying
			selectBungiiFromList(numberOfDriver,customerName.substring(0, customerName.indexOf(" ") + 2));

		//	selectBungiiFromList("DUO","Vishal B");


			log( "I Select Trip from available trip", "I selected trip for customer " +customerName + " of "+ numberOfDriver +" type",true);
		} catch (Exception e) {
			String customerName=(String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String numberOfDriver=(String)cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Trip Should be listed in Available Bungiis", "Trip is not displayed in available Bungii for customer " + customerName + " of "+ numberOfDriver +" type", true);
		}
	}

	@And("^I Select Partner portal Trip from available trip$")
	public void i_select_partner_portal_trip_from_available_trip() {
		try {
			Thread.sleep(5000);
			String expectedText  = action.getText(availableTripsPage.Text_FromHomeMiles());
			boolean textDisplayed = expectedText.contains("miles");
			testStepAssert.isTrue(textDisplayed,"Text should be updated to miles","Text is updated to miles","Text is not updated to miles");

			if (action.isAlertPresent()){ SetupManager.getDriver().switchTo().alert().dismiss();   Thread.sleep(1000);        }

			String customerName=(String) cucumberContextManager.getScenarioContext("CUSTOMER");

			String numberOfDriver=(String)cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");

			//selectBungiiFromList(numberOfDriver,customerName.substring(0, customerName.indexOf(" ")+2));--removing this since now full name is displaying
			selectBungiiFromList(numberOfDriver,customerName);

			log( "I Select Partner portal Trip from available trip of driver", "I selected trip for partner portal customer " +customerName + " of "+ numberOfDriver +" type",true);
		} catch (Exception e) {
			String customerName=(String) cucumberContextManager.getScenarioContext("CUSTOMER");
			String numberOfDriver=(String)cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Partner portal Trip Should be listed in Available Bungiis", "Partner portal Trip is not displayed in available Bungii for customer " + customerName + " of "+ numberOfDriver +" type", true);
		}
	}

	@Then("^Partner Portal name should be displayed in \"([^\"]*)\" section$")
	public void partner_portal_name_should_be_display_in_something_section(String Screen) throws Throwable {
		try {
			switch (Screen) {
				case "AVAILABLE BUNGIIS":
				case "SCHEDULED BUNGIIS":
                    String partnerName = availableTripsPage.Partner_Name().getText();
                    String partnerNameExpected = (String) cucumberContextManager.getScenarioContext("Partner_Portal_Name");
                    //testStepVerify.isEquals(partnerName,partnerNameExpected);
                    testStepAssert.isEquals(partnerName, partnerNameExpected, "Partner Portal name should be display in " + Screen + " section", "Partner Portal name is displayed in " + Screen + " section", "Partner Portal name is not displayed in " + Screen + " section");
                    break;

				case "EN ROUTE":
				case "ARRIVED":
				case "LOADING ITEMS":
					String portal = (String) cucumberContextManager.getScenarioContext("Portal_Name");
					String namePartner= availableTripsPage.Text_PartnerName(portal).getText();
					String expectedName = (String) cucumberContextManager.getScenarioContext("Partner_Portal_Name");
					testStepAssert.isEquals(namePartner, expectedName, "Partner Portal name should be display in " + Screen + " section", "Partner Portal name is displayed in " + Screen + " section", "Partner Portal name is not displayed in " + Screen + " section");
					break;

                case "DRIVING TO DROP-OFF":
                case "UNLOADING ITEMS"://
					String expectedCustomerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
					String customerNamehalf = expectedCustomerName.substring(0,28);
					String nameCustomer= action.getText(availableTripsPage.Text_PartnerName(customerNamehalf));
                    testStepAssert.isEquals(nameCustomer, customerNamehalf, "Customer name  should be display in " + Screen + " section", "Customer name is displayed in " + Screen + " section", "Customer name is not displayed in " + Screen + " section");
                    break;
				default:
					log("Pass correct screen", "Wrong screen has been Pass", true);
					break;
			}
		}
		catch (Exception ex){
			logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
			error("Step should be successful", "Partner Portal name is not displayed on "+Screen,
					true);
		}
	}

	@Then("^I should able to see \"([^\"]*)\" available trip$")
	public void i_should_able_to_see_something_available_trip(String strArg1) throws Throwable {
		try {
			Thread.sleep(5000);
			List<WebElement> listOfBungii=availableTripsPage.Image_SelectBungiis();
			switch (strArg1) {
				case "two":
					testStepAssert.isTrue(listOfBungii.size()==3,"There should be two available deliveries","There are two available deliveries", "There are no or more than two available deliveries");
					break;
				case "one":
					testStepAssert.isTrue(listOfBungii.size()==2,"There should be one available deliveries","There are one available deliveries", "There are no or more than one available deliveries");
					break;
				case "zero":
					testStepAssert.isTrue(listOfBungii.size()==1,"There should be zero available deliveries","There are zero available deliveries", "There are more then one available deliveries");
					break;
				default:
					throw new Exception(" UNIMPLEMENTED STEP");
					//NOTE: COUNT = 3 if delivery is 2 and 1 if delivery is 0 since Account tab hass arrow next to it.
			}
		} catch (Throwable e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			fail("Step  Should be successful",
					"Error in viewing trips from available list. Count error", true);
		}	}
	/**
	 * Select first bungii from the list
	 */
	public void selectBungii(){
		action.click(availableTripsPage.Image_SelectBungii());
	}

	public void selectBungiiFromList(){
		action.click(availableTripsPage.Image_SelectBungii());
	}

	//TODO: HANDLE duo trip
	/**
	 * Select bungii from list matching with details given as input
	 * @param bungiiType Identifier for Bungii type , Solo or duo
	 * @param customerName Customer name whoes bungii is to be selected
	 */
	public void selectBungiiFromList(String bungiiType, String customerName){
		action.swipeDown();
		String imageTag="";
		if(bungiiType.toUpperCase().equals("SOLO"))
		{
			imageTag=Image_Solo;
		}else{
			imageTag=Image_Duo;
		}
		if(action.isAlertPresent()) SetupManager.getDriver().switchTo().alert().dismiss();
		WebElement Image_SelectBungii = availableTripsPage.findElement("//XCUIElementTypeStaticText[@name='"+customerName+"']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);
		action.click(Image_SelectBungii);

		//sometime row is not getting clicked due to alert
		if(action.isAlertPresent()) SetupManager.getDriver().switchTo().alert().dismiss();
		if(action.isElementPresent(availableTripsPage.findElement("//XCUIElementTypeStaticText[@name='"+customerName+"']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath,true)))
			action.click(availableTripsPage.findElement("//XCUIElementTypeStaticText[@name='"+customerName+"']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath,true));
	}

	@And("^I click on the back button and verify the rejection popup$")
	public void i_click_on_the_back_button_and_verify_the_rejection_popup() throws Throwable {
		try{
			action.click(availableTripsPage.Button_Back());
			Thread.sleep(3000);
			testStepAssert.isElementDisplayed(availableTripsPage.Text_RejectionPopup(),"Rejection Reason pop-up must be displayed","Rejection Reason pop-up is displayed","Rejection Reason pop-up is not displayed");

		}
		catch (Exception ex){
			logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
			error("Step should be successful", "I cannot click on back button",
					true);
		}
	}

	@And("^I click on \"([^\"]*)\" button on rejection popup$")
	public void i_click_on_something_button_on_rejection_popup(String button) throws Throwable {
		try {
			switch (button){
				case "CANCEL":
					action.click(availableTripsPage.Button_Cancel());
					break;
				case "SUBMIT":
					action.click(availableTripsPage.Button_Submit());
					break;
			}
			log("I should be able to click on "+button+" button",
					"I am able to click on "+button+" button",
					false);
		}
		catch (Exception ex){
			logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
			error("Step should be successful", "I cannot click on "+button+" button",
					true);
		}
	}
	@And("^I check if all reasons are displayed on rejection popup$")
	public void i_check_if_all_reasons_are_displayed_on_rejection_popup() throws Throwable {
		try{
			List<String> expectedOptions = new ArrayList() {{
				Rejection_Reason.TOO_FAR_AWAY.toString();
				Rejection_Reason.EARNINGS.toString();
				Rejection_Reason.LABOR_REQUIREMENTS.toString();
				Rejection_Reason.TYPE_OF_ITEM.toString();
				Rejection_Reason.NOT_ENOUGH_INFORMATION.toString();
				Rejection_Reason.NOT_AVAILABLE.toString();
			}};
			for (int j =0;j<expectedOptions.size();j++){
				String expectedReason= expectedOptions.get(j);
				String actualReason = availableTripsPage.Text_RejectionReasons(expectedReason).getAttribute("name");
				testStepAssert.isEquals(actualReason,expectedReason,"The actual and expected reasons should be same","The actual and expected reasons are the same","The actual and expected reasons are not the same");
			}

		}
		catch (Exception e){
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step Should be successful", "Error in viewing result set",
					true);
		}
	}
	@Then("^I check if the reason is saved in db$")
	public void i_check_if_the_reason_is_saved_in_db() throws Throwable {
		try{
			String driverNumber = (String) cucumberContextManager.getScenarioContext("DRIVER_PHONE_NUMBER");
			String reason = dbUtility.checkRejectionReason(driverNumber);
			if(!(reason.isEmpty()))
			{
				testStepAssert.isTrue(true,"The rejection reason is saved in db","The rejection reason is not saved in db");
			}
			else{
				testStepAssert.isTrue(false,"The rejection reason is saved in db","The rejection reason is not saved in db");
			}
		}
		catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}
	@And("^I click on the back button and verify that rejection popup is absent$")
	public void i_click_on_the_back_button_and_verify_that_rejection_popup_is_absent() throws Throwable {
		try{
			action.click(availableTripsPage.Button_Back());
			Thread.sleep(2000);

			testStepAssert.isFalse(action.isElementPresent(availableTripsPage.Text_RejectionPopup(true)),"Rejection Reason pop-up must not be displayed","Rejection Reason pop-up is not displayed", "Rejection Reason pop-up is displayed");

		}
		catch (Exception ex){
			logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
			error("Step should be successful", "I cannot click on back button",
					true);
		}
	}
	@Then("^I verify the rejection popup is displayed$")
	public void i_verify_the_rejection_popup_is_displayed() throws Throwable {
		try{
			testStepAssert.isElementDisplayed(availableTripsPage.Text_RejectionPopup(),"Rejection Reason pop-up must be displayed","Rejection Reason pop-up is displayed","Rejection Reason pop-up is not displayed");
		}
		catch (Exception ex){
			logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
			error("Step should be successful", "I cannot click on back button",
					true);
		}
	}

	@Then("^The trip should be present in my bungiis$")
	public void the_trip_should_be_present_in_my_bungiis() throws Throwable {
		try{
		Thread.sleep(3000);
		boolean isTripPresent = false;
		String scheduled_time = cucumberContextManager.getScenarioContext("BUNGII_TIME").toString().substring(0, 17).toUpperCase();
		List<WebElement> availableDeliveries = availableTripsPage.List_AllAvailableDeliveriesCustomerApp();
		if (availableDeliveries.size() == 0) {
			testStepAssert.isFail("Delivery is not present in available bungiis");
		} else {
			for (int i = 1; i < (availableDeliveries.size()+1); i++) {
				String deliveryTime = action.getText(availableTripsPage.Text_DeliveryTime(i));
				Thread.sleep(1000);
				String  deliveryTimeAndDate= deliveryTime.substring(0, 17).toUpperCase();
				if (deliveryTimeAndDate.contentEquals(scheduled_time)) {
					testStepAssert.isPass("Delivery is present in available bungiis");
					isTripPresent = true;
					break;
				}
			}
			if (isTripPresent ==false) {
				testStepAssert.isFail("Delivery is not present in available bungiis");
			}
		}
	} catch (Throwable e) {
		logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
		error("Step  Should be successful",
				"Error performing step,Please check logs for more details", true);
	}
	}
	@Then("^The trip should not be present in available bungiis$")
	public void the_trip_should_not_be_present_in_available_bungiis() throws Throwable {
		try{
		boolean isDeliveryPresentInDriverApp = true;
        cucumberContextManager.setScenarioContext("CUSTOMER","Testcustomertywd_appleZTDafc Stark");
		String fullCustomerName = cucumberContextManager.getScenarioContext("CUSTOMER").toString().substring(0,27);
		List<WebElement> availableDeliveriesDriverApp =availableTripsPage.List_AllAvailableDeliveriesDriverApp();
		if(availableDeliveriesDriverApp.size()==0){
			testStepAssert.isPass("Delivery is not present in available bungiis");
		}
		else{
			for(int i=0;i<availableDeliveriesDriverApp.size();i++){
				String deliveryTime =action.getText(availableTripsPage.Text_CustomerName(i+1)).substring(0,27);
				if(deliveryTime.contentEquals(fullCustomerName)){
					testStepAssert.isFail("Delivery is present in available bungiis");
					isDeliveryPresentInDriverApp=false;
				}
			}
			if(isDeliveryPresentInDriverApp==true) {
				testStepAssert.isPass("Delivery is not present in available bungiis");
			}
		}
	} catch (Throwable e) {
		logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
		error("Step  Should be successful",
				"Error performing step,Please check logs for more details", true);
	}
	}

	@Then("^I should see \"([^\"]*)\" message on popup with PickupId anad Pickup Origin$")
	public void i_should_see_something_message_on_popup_with_pickupid_anad_pickup_origin(String message) throws Throwable {

		try{
			testStepAssert.isTrue(action.isElementPresent(scheduledTripsPage.Label_HeaderPopup()), message + " should be displayed", message + " is displayed", message + " is not displayed");
			String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
			String pickupId = dbUtility.getLinkedPickupRef(pickuprequest);
			String source = "Customer Delivery";
			String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");

			testStepAssert.isElementTextEquals(scheduledTripsPage.Label_PickupId(),pickupId, pickupId +" should be displayed", pickupId +" is displayed", pickupId+" is not displayed");
			testStepAssert.isElementTextEquals(scheduledTripsPage.Label_PickupCustomer(),customerName, customerName +" should be displayed", customerName +" is displayed", customerName+" is not displayed");
		} catch(Exception e){
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}


}
