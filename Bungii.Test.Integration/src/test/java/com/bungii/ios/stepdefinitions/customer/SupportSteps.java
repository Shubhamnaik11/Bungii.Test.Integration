package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.SupportPage;

public class SupportSteps extends DriverBase {
	private SupportPage supportPage;
	public SupportSteps(SupportPage supportPage) {
		this.supportPage =supportPage;
	}
	ActionManager action = new ActionManager();


	/**
	 * Enter data in support text box
	 * @param inputData Text that is to be entered on Support text box
	 */
	public void enterDataInSupport(String inputData){
		supportPage.TextBox_Support().sendKeys( inputData);
	}

	/**
	 * Click on send button
	 */
	public void clickSendButton(){
			action.click(supportPage.Button_Send());
	}

	/**
	 * Get support question displayed
	 * @return get valye of support question
	 */
	public String getSupportQuestion(){
		return action.getValueAttribute(supportPage.Text_SupportQuestion());
	}

	/**
	 * Check if Bungii customer logo is displayed
	 * @return boolean value that bungii customer logo is displayed on not
	 */
	public boolean isCustomerLogoPresent(){
		return supportPage.isElementEnabled(supportPage.Image_CustLogo());
	}

}
