package com.bungii.ios.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.SetupManager;
import com.bungii.ios.pages.admin.LogInPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import  com.bungii.common.manager.*;
import static com.bungii.common.manager.ResultManager.pass;


public class LogInSteps extends DriverBase {
	LogInPage logInPage;
	public LogInSteps( LogInPage logInPage) {
		this.logInPage = logInPage;
	}
	
    @When("^I naviagate to admin portal$")
    public void i_naviagate_to_admin_portal() throws Throwable {

		SetupManager.getDriver().get(PropertyUtility.getProp("admin.url"));
		pass("I naviagate to admin portal" , "I should be naviagate to admin portal",
				"I naviagate to admin portal", true);
    }
    
    @And("^I log in to admin portal$")
    public void i_log_in_to_admin_portal() throws Throwable {
    	
    	//logInPage.logInToPortal(PropertyUtility.getProp("admin.user"),PropertyUtility.getProp("admin.password"));
		logInPage.TextBox_Phone().sendKeys(PropertyUtility.getDataProperties("admin.user"));
		logInPage.TextBox_Pass().sendKeys(PropertyUtility.getDataProperties("admin.password"));
		logInPage.Button_LogIn().click();
		pass("I log in to admin portal" , "Switch to  admin portal application",
				"Switch to  admin portal application", true);
		//DriverManager.getObject().showAllInstance();
    }
    
    @When("^I Switch to admin portal$")
    public void i_switch_to_admin_portal() throws Throwable {
		DriverManager.getObject().useDriverInstance("ADMIN");
		pass("Switch application admin portal" , "Switch to  admin portal application",
				"Switch to  admin portal application", true);

    }
}
