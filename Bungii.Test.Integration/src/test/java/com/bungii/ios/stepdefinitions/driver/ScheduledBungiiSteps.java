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

    public WebElement getLocatorForNotification(String notificationMessage) {
		WebElement element;
		element = scheduledBungiipage.findElement("//*[@text='" + notificationMessage + "']", PageBase.LocatorType.XPath);

		if (action.isElementPresent(element) == true) {
			element = scheduledBungiipage.findElement("//*[@text='" + notificationMessage + "']", PageBase.LocatorType.XPath);
		}

		return  element;
    }
}
