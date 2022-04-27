package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.AvailableTripsPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;


public class AvailableTripsSteps extends DriverBase {
	AvailableTripsPage availableTripsPage;
	private static LogUtility logger = new LogUtility(AvailableTripsSteps.class);
	ActionManager action = new ActionManager();
	DbUtility dbUtility = new DbUtility();
	public AvailableTripsSteps(AvailableTripsPage availableTripsPage) {
		this.availableTripsPage = availableTripsPage;
	}
	String Image_Solo="bungii_type-solo",Image_Duo="bungii_type-duo";

	@And("^I Select Trip from available trip$")
	public void i_select_trip_from_available_trip() {
		try {
			Thread.sleep(5000);

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
				case "EN ROUTE":
				case "ARRIVED":
				case "LOADING ITEM":
				case "DRIVING TO DROP OFF":
				case "UNLOADING ITEM":
					String partnerName = availableTripsPage.Partner_Name().getText();
					String partnerNameExpected = (String) cucumberContextManager.getScenarioContext("Partner_Portal_Name");
					//testStepVerify.isEquals(partnerName,partnerNameExpected);
					testStepAssert.isEquals(partnerName, partnerNameExpected, "Partner Portal name should be display in " + Screen + " section", "Partner Portal name is displayed in " + Screen + " section", "Partner Portal name is not displayed in " + Screen + " section");
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

	@And("^I click back on Bungii details$")
	public void i_click_back_on_bungii_details() throws Throwable {
		try {
//			action.swipeLeft(availableTripsPage.Page_Bungii_Details());
		}
		catch (Exception ex){
			logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
			error("Step should be successful", "Could not navigate back to available bungii",
					true);
		}
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
				add("Too far away");
				add("Earnings");
				add("Labor requirements");
				add("Type of item(s)");
				add("Not enough information");
				add("Not available");
			}};
			for (int j =0;j<6;j++){
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
}
