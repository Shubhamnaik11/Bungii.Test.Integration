package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.enums.REFERRAL_SOURCE;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.SignupPage;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.EnumUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class SignupSteps extends DriverBase {
	private SignupPage signupPage;
	private static LogUtility logger = new LogUtility(SignupSteps.class);
	ActionManager action = new ActionManager();
	public SignupSteps(SignupPage signupPage) {
		this.signupPage = signupPage;
	}



	@And("^I Select Referral source as \"([^\"]*)\"$")
	public void iSelectReferralSourceAs(String source) {

		if (source.equals("{BLANK}")) {
			action.swipeUP();
			pass("I Select Referral source as " + source, "I leave referral source as empty",
					" I left  referral source as empty", true);
		} else {
			try {
				action.swipeUP();
				action.click(signupPage.Button_Chevron());
						String key = source.toUpperCase().replaceAll(" ", "_");
				if (EnumUtils.isValidEnum(REFERRAL_SOURCE.class, source.toUpperCase().replaceAll(" ", "_"))) {
					String value = REFERRAL_SOURCE.valueOf(key).toString();
					clickReferralSource(value);
					action.click(signupPage.Button_Done());
					pass("I Select Referral source as " + source, "I should able to entered referral source as "+source,
							"I entered referral source as "+source, true);
				} else {

					error("I Select Referral source as " + source, "Please enter valid referral source",
							"Error performing step,Error", true);

				}
			} catch (Exception e) {
				logger.error("Error performing step" + e.getMessage());
				error("I Select Referral source as " + source, "Step  Should be sucessfull",
						"Error performing step,Error", true);
			}
		}
	}


	/**
	 * Click on referral source base on input key
	 * @param source Source  of referral
	 */
	public void clickReferralSource(String source){

		action.click(signupPage.findElement("//XCUIElementTypeStaticText[@value='"+source+"']", PageBase.LocatorType.XPath));

	}

}
