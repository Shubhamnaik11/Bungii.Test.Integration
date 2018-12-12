package com.bungii.ios.stepdefinitions.driver;

import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.ScheduledBungiiPage;
import org.openqa.selenium.WebElement;

public class ScheduledBungiiSteps {
	private ScheduledBungiiPage scheduledBungiipage;
	ActionManager action = new ActionManager();
	String Image_Solo="bungii_type-solo";

	public ScheduledBungiiSteps(ScheduledBungiiPage scheduledBungiipage){
    	this.scheduledBungiipage=scheduledBungiipage;

	}
/*    @And("^I Select Trip from scheduled trip$")
    public void i_select_trip_from_scheduled_trip() throws Throwable {


    	String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
		String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
    	scheduledBungiipage.selectBungiiFromList(tripNoOfDriver,tripTime);
    }*/

	/**
	 * Check if active page is Bungii scheduled details page.
	 * @return return comparison result navigation header to expected msg from
	 *         property
	 */
	public boolean isScheduledBungiiPage() {
		action.textToBePresentInElementName(scheduledBungiipage.Text_NavigationBar(), PropertyUtility.getMessage("driver.navigation.scheduledbungii"));
		return action.getNameAttribute(scheduledBungiipage.Text_NavigationBar()).equals(PropertyUtility.getMessage("driver.navigation.scheduledbungii"));

	}


/*	public void selectBungiiFromList(){
		click(Image_SelectBungii);
	}*/

	//TODO:HANDLE DUO
	/**
	 * Select bungii with details specified as input
	 * @param bungiiType Type of bungii , SOLO or DUO
	 * @param bungiiTime Scheduled Bungii time
	 */
	public void selectBungiiFromList(String bungiiType, String bungiiTime){
		String imageTag="";
		if(bungiiType.toUpperCase().equals("SOLO"))
		{
			imageTag=Image_Solo;
		}
		action.swipeDown();
		WebElement Image_SelectBungii= scheduledBungiipage.findElement("//XCUIElementTypeStaticText[@name='"+bungiiTime+"']/following-sibling::XCUIElementTypeImage[@name='"+imageTag+"']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);
		//By Image_SelectBungii = MobileBy.xpath();
		action.click(Image_SelectBungii);
	}
}
