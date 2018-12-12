package com.bungii.ios.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class BungiiRequestPage extends PageBase {
	public WebElement Text_NavigationBar() { return findElement("//XCUIElementTypeNavigationBar/XCUIElementTypeOther", PageBase.LocatorType.XPath); }

	public WebElement Button_Accept() { return findElement("ACCEPT", LocatorType.Name); }
	public WebElement Button_Reject() { return findElement("REJECT", LocatorType.Name); }
	public WebElement Text_TripDistance() { return findElement("//XCUIElementTypeStaticText[@name='Trip Distance']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_EstimatedEarning() { return findElement("//XCUIElementTypeStaticText[@name='Estimated Earnings']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }
	public WebElement Text_TimeToPickup() { return findElement("//XCUIElementTypeStaticText[@name='Time to Pickup']/following-sibling::XCUIElementTypeStaticText", PageBase.LocatorType.XPath); }

/*	*//**
	 * Check if active page is Bungii request details page. 
	 * This page appears when we click yes on ondemand request popup (Clicked from notification)
	 * @return return comparison result navigation header to expected msg from
	 *         property
	 *//*
	public boolean isBungiiRequestPage() {
		textToBePresentInElementName(Text_NavigationBar, PropertyUtility.getMessage("driver.navigation.bungiirequest"));
		return getNameAttribute(Text_NavigationBar).equals(PropertyUtility.getMessage("driver.navigation.bungiirequest"));

	}
	
	*//**
	 * Click accept bungii
	 *//*
	public void clickAccept() {
		click(Button_Accept);
	}

	*//**
	 * Click reject bungii
	 *//*
	public void clickReject() {
		click(Button_Reject);
	}
	
	*//**
	 * Get trip details
	 * @return array of string containing trip details
	 *//*
	public String[] getTripDetails(){
		String [] detail = new String [3];
		detail[0]= getValueAttribute(Text_TimeToPickup);
		detail[1]= getValueAttribute(Text_TripDistance);
		detail[2]= getValueAttribute(Text_EstimatedEarning);

		return detail;
	}*/



}
