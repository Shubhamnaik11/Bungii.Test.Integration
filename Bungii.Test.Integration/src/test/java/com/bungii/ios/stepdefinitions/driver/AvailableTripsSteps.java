package com.bungii.ios.stepdefinitions.driver;


import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.AvailableTripsPage;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;


public class AvailableTripsSteps extends DriverBase {
	AvailableTripsPage availableTripsPage;
	private static LogUtility logger = new LogUtility(AvailableTripsSteps.class);
	ActionManager action = new ActionManager();
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
			selectBungiiFromList(numberOfDriver,customerName.substring(0, customerName.indexOf(" ")+2));

		//	selectBungiiFromList("DUO","Vishal B");


			log( "I Select Trip from available trip", "I selected trip for customer " +customerName + " of "+ numberOfDriver +" type",true);
		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
		}
	}

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
		//By Image_SelectBungii = MobileBy.xpath("//XCUIElementTypeStaticText[@name='"+customerName+"']/following-sibling::XCUIElementTypeImage[@name='"+imageTag+"']/parent::XCUIElementTypeCell");
		//WebElement Image_SelectBungii = availableTripsPage.findElement("//XCUIElementTypeStaticText[@name='"+customerName+"']/following-sibling::XCUIElementTypeImage[@name='"+imageTag+"']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);
		WebElement Image_SelectBungii = availableTripsPage.findElement("//XCUIElementTypeStaticText[@name='"+customerName+"']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);
		action.click(Image_SelectBungii);
		//sometime row is not getting clicked due to alert
		if(action.isAlertPresent()) SetupManager.getDriver().switchTo().alert().dismiss();
		if(action.isElementPresent(availableTripsPage.findElement("//XCUIElementTypeStaticText[@name='"+customerName+"']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath,true)))
			action.click(availableTripsPage.findElement("//XCUIElementTypeStaticText[@name='"+customerName+"']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath,true));




}


}
