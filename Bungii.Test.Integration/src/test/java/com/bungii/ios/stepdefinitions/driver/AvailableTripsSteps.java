package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.enums.Rejection_Reason;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.AvailableTripsPage;
import com.bungii.ios.pages.driver.BungiiDetailsPage;
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
	private BungiiDetailsPage bungiiDetailsPage;
	public AvailableTripsSteps(AvailableTripsPage availableTripsPage,BungiiDetailsPage bungiiDetailsPage) {
		this.availableTripsPage = availableTripsPage;
		this.bungiiDetailsPage = bungiiDetailsPage;
	}
	String Image_Solo="bungii_type-solo",Image_Duo="bungii_type-duo";

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

	@Then("^I should see service level information displayed for \"([^\"]*)\" address$")
	public void i_should_see_service_level_information_displayed_for_something_address(String centre) throws Throwable {
		try{
		Thread.sleep(5000);
		if(centre.equalsIgnoreCase("Store")){
			String expectedStoreAddress = PropertyUtility.getDataProperties("baltimore.store.address");
			String addressLine1 = action.getText(availableTripsPage.Text_PickupAddressLineOneDriverApp()).replace(", ","");;
			String addressLine2 = action.getText(availableTripsPage.Text_PickupAddressLineTwoDriverApp()).replace(",","");
			String properAddress = addressLine1 +" "+ addressLine2;

			testStepAssert.isEquals(properAddress, expectedStoreAddress, "Proper Store address should be displayed", "Proper Store address is displayed", " Store address displayed is wrong");

		}
		else{
			String expectedWarehouseAddress = PropertyUtility.getDataProperties("baltimore.warehouse.address");
			String addressLine1 = action.getText(availableTripsPage.Text_PickupAddressLineOneDriverApp()).replace(", ","");;
			String addressLine2 = action.getText(availableTripsPage.Text_PickupAddressLineTwoDriverApp()).replace(",","");
			String properAddress = addressLine1 +" "+ addressLine2;

			testStepAssert.isEquals(properAddress, expectedWarehouseAddress, "Proper warehouse address should be displayed", "Proper warehouse address is displayed", " Warehouse address displayed is wrong");

		}
		String servicePickupInstruction = PropertyUtility.getDataProperties("baltimore.pickup.instructions");
		String serviceDropOffInstruction = PropertyUtility.getDataProperties("baltimore.dropoff.instructions");

		boolean pickupInstructions = availableTripsPage.Label_PickupInstructions().isDisplayed();
		boolean dropOffInstructions = availableTripsPage.Label_DropOffInstructions().isDisplayed();

		String expectedServicePickupInstructions = action.getText(availableTripsPage.Text_PickupInstructions());
		String expectedServiceDropOffInstructions = action.getText(availableTripsPage.Text_DropOffInstructions());

		testStepAssert.isTrue(pickupInstructions, "Pickup instruction should be displayed", "Pickup instruction is displayed", "Pickup instruction is not displayed");
		testStepAssert.isTrue(dropOffInstructions, "Dropoff instruction should be displayed", "Dropoff instruction is displayed", "Dropoff instruction is not displayed");

		testStepAssert.isEquals(expectedServicePickupInstructions, servicePickupInstruction, servicePickupInstruction + "service instructions should be displayed", expectedServicePickupInstructions + "service instructions is displayed", servicePickupInstruction + "service instructions is displayed");
		testStepAssert.isEquals(expectedServiceDropOffInstructions, serviceDropOffInstruction, serviceDropOffInstruction + "service instructions should be displayed", expectedServiceDropOffInstructions + "service instructions is displayed", serviceDropOffInstruction + "service instructions is displayed");
	} catch (Exception e) {
		logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
		error("Step should be successful", "Error performing step,Please check logs for more details",
				true);
	}
	}


	@Then("^The service level information should be displayed$")
	public void the_service_level_information_should_be_displayed() throws Throwable {
		try{
		String expectedPickupServiceProvided = PropertyUtility.getDataProperties("baltimore.pickup.instructions");
		String expectedDropOffServiceProvided = PropertyUtility.getDataProperties("baltimore.dropoff.instructions");
		Thread.sleep(6000);
		boolean isPickupInstructionsOnScheduleBungiiDisplayed = availableTripsPage.Label_PickupInstructions().isDisplayed();
		boolean isDropOffInstructionsOnScheduleBungiiDisplayed = availableTripsPage.Label_DropOffInstructions().isDisplayed();

		String pickupInstructions = action.getText(availableTripsPage.Text_PickupInstructionsScheduleBungii());
		String dropOffInstructions = action.getText(availableTripsPage.Text_DropOffInstructionsScheduleBungii());

		testStepAssert.isTrue(isPickupInstructionsOnScheduleBungiiDisplayed, "Driver pickup instructions label should be displayed", "Driver pickup instructions label is displayed", "Driver pickup instructions label is not displayed");
		testStepAssert.isTrue(isDropOffInstructionsOnScheduleBungiiDisplayed, "Driver dropoff instructions label should be displayed", "Driver dropoff instructions label is displayed", "Driver dropoff instructions label is not displayed");

		testStepAssert.isEquals(pickupInstructions, expectedPickupServiceProvided, expectedPickupServiceProvided + "Service should be provided for pickup", pickupInstructions + "Service is provided for pickup", expectedPickupServiceProvided + "Service is not provided for pickup");
		testStepAssert.isEquals(dropOffInstructions, expectedDropOffServiceProvided, expectedDropOffServiceProvided + "Service should be provided for dropoff", dropOffInstructions + "Service is provided for dropoff", expectedDropOffServiceProvided + "Service is not provided for dropoff");
	} catch (Exception e) {
		logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
		error("Step should be successful", "Error performing step,Please check logs for more details",
				true);
	}
	}

	@Then("^The delivery details on \"([^\"]*)\" deliveries should have proper pickup \"([^\"]*)\" location and service level instructions displayed$")
	public void the_delivery_details_on_something_deliveries_should_have_proper_pickup_something_location_and_service_level_instructions_displayed(String deliveryStatus, String centre) throws Throwable {
		try{
		Thread.sleep(3000);
		if (centre.equalsIgnoreCase("Store")) {
			String expectedStoreAddress = PropertyUtility.getDataProperties("baltimore.store.address.with.zipcode");
			String StoreLocation = action.getText(availableTripsPage.Text_PickupLocationAdminPortal());

			testStepAssert.isEquals(StoreLocation, expectedStoreAddress, "Store address should be " + expectedStoreAddress, "Store address is " + StoreLocation, "Store address is not " + expectedStoreAddress);
		} else {
			String expectedWarehouseAddress = PropertyUtility.getDataProperties("baltimore.warehouse.address.with.zipcode");
			String warehouseLocation = action.getText(availableTripsPage.Text_PickupLocationAdminPortal());

			testStepAssert.isEquals(warehouseLocation, expectedWarehouseAddress, "Warehouse address should be " + expectedWarehouseAddress, "Warehouse address is " + warehouseLocation, "Warehouse address is not " + expectedWarehouseAddress);
		}
		switch (deliveryStatus) {
			case "Live":
				boolean isDriverPickupInstructionsDisplayed = availableTripsPage.Label_DriverPickupInstructionsAdminPortal().isDisplayed();
				boolean isDriverDropOffInstructionsDisplayed = availableTripsPage.Label_DriverDropOffInstructionsAdminPortal().isDisplayed();

				String expectedPickupServiceProvided = PropertyUtility.getDataProperties("baltimore.pickup.instructions");
				String expectedDropOffServiceProvided = PropertyUtility.getDataProperties("baltimore.dropoff.instructions");
				String pickupServiceProvided = action.getText(availableTripsPage.Text_DriverPickupInstructionsServiceAdminPortal());
				String dropOffServiceProvided = action.getText(availableTripsPage.Text_DriverDropOffInstructionsServiceAdminPortal());

				testStepAssert.isTrue(isDriverPickupInstructionsDisplayed, "Driver pickup instructions label should be displayed", "Driver pickup instructions label is displayed", "Driver pickup instructions label is not displayed");
				testStepAssert.isTrue(isDriverDropOffInstructionsDisplayed, "Driver dropoff instructions label should be displayed", "Driver dropoff instructions label is displayed", "Driver dropoff instructions label is not displayed");

				testStepAssert.isEquals(pickupServiceProvided, expectedPickupServiceProvided, expectedPickupServiceProvided + "Service should be provided for pickup", pickupServiceProvided + "Service is provided for pickup", expectedPickupServiceProvided + "Service is not provided for pickup");
				testStepAssert.isEquals(dropOffServiceProvided, expectedDropOffServiceProvided, expectedDropOffServiceProvided + "Service should be provided for dropoff", dropOffServiceProvided + "Service is provided for dropoff", expectedDropOffServiceProvided + "Service is not provided for dropoff");

				break;
		}
	} catch (Exception e) {
		logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
		error("Step should be successful", "Error performing step,Please check logs for more details",
				true);
	}
	}

	@And("^I click on the \"([^\"]*)\" link beside scheduled bungii for \"([^\"]*)\"$")
	public void i_click_on_the_something_link_beside_scheduled_bungii_for_something(String strArg1, String deliveryType) throws Throwable {
		try {
			switch (deliveryType) {
				case "Completed Deliveries":
					Thread.sleep(4000);
					action.click(availableTripsPage.Link_DeliveryDetails());
					Thread.sleep(2000);
					action.click(availableTripsPage.List_ViewDeliveries());
					break;
			}
			log("I should be able to click the button next to "+deliveryType,"I could  click the button next to "+deliveryType,false);
		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}

	@And("^I click on start Bungii for service based delivery$")
	public void i_click_on_start_bungii_for_service_based_delivery() throws Throwable {
		try{
		action.swipeUP();
		action.click(bungiiDetailsPage.Button_StartBungii());
		Thread.sleep(2000);
		if(action.isElementPresent(bungiiDetailsPage.Text_General_Instruction(true))) {
			action.click(bungiiDetailsPage.Button_General_Instruction_Got_It());
		}
		log("I start selected Bungii ", "I started selected Bungii", true);
	} catch (Exception e) {
		logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
		error("Step  Should be successful", "Error in Starting Bungii as Driver", true);
	}
	}
	@And("^I set the pickup address for \"([^\"]*)\"$")
	public void i_set_the_pickup_address_for_something(String address) throws Throwable {
		try {
			switch (address) {
				case "Warehouse":
					cucumberContextManager.setScenarioContext("WarehouseCity", "Catonsville");
					break;
				case "Store":
					cucumberContextManager.setScenarioContext("OfficeCity", "MD");
					break;
			}
			log("I should be able to set the pickup address", "I could set the pickup address", false);
		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}

}
