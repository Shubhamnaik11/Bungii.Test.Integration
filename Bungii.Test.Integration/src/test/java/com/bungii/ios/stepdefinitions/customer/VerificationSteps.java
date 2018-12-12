package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.VerificationPage;
import cucumber.api.java.en.When;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;


public class VerificationSteps extends DriverBase {
	VerificationPage verificationPage;
	private static LogUtility logger = new LogUtility(VerificationSteps.class);
	ActionManager action = new ActionManager();
	public VerificationSteps(VerificationPage verificationPage) {
		this.verificationPage = verificationPage;
	}

	@When("^I enter \"([^\"]*)\" Verification code$")
	public void i_enter_something_verification_code(String strArg1) {
		try {
			String smsCode = (String) cucumberContextManager.getScenarioContext("SMS_CODE");
			action.waitClearEnterText(verificationPage.TextBox_SmsCode(),smsCode);
			action.click(verificationPage.Button_Verify());

			pass("I enter "+strArg1+" Verification code", "I should able to enter verification code",
					"I entered verification code : " + smsCode +"in sms code field", true);
			//TODO:REMOVE THIS
			Thread.sleep(20000);

		} catch (Exception e) {
			logger.error("Error performing step" + e.getMessage());
			error("I enter "+strArg1+" Verification code", "Step  Should be sucessfull", "Error performing step,Error", true);
		}
	}



}
