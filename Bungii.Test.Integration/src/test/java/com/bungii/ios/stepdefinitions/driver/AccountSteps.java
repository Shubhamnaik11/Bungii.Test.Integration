package com.bungii.ios.stepdefinitions.driver;


import com.bungii.common.core.DriverBase;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.AccountPage;
import cucumber.api.java.en.Then;

public class AccountSteps extends DriverBase {
	AccountPage accountPage;
	ActionManager action = new ActionManager();
	public AccountSteps(AccountPage accountPage) {
		this.accountPage=accountPage;
	}
	
    @Then("^I get driver account details for driver 1$")
    public void i_get_driver_account_details_for_driver_1() {
    	String[] details =getDriverDetails();
    	String phone=details[1].replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
    	cucumberContextManager.setScenarioContext("DRIVER_1", details[0]);
		cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
    }

	/**
	 * Get driver details from account page
	 * @return String array containing driver name , Phone number anad email id
	 */
	public String[] getDriverDetails(){
		String[] details = new String [3];
		details[0]=action.getNameAttribute(accountPage.Text_Name());
		details[1]=action.getNameAttribute(accountPage.Text_Phone());
		details[2]=action.getNameAttribute(accountPage.Text_Email());
		return details;
	}
}
