package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.pages.customer.ForgotPasswordPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;

import static com.bungii.common.manager.ResultManager.error;

public class ForgotPasswordSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(VerificationSteps.class);

    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();

    @And("^I Get SMS CODE for \"([^\"]*)\" number$")
    public void i_get_sms_code_for_(String strArg1) {
        try {

            String phoneNumber= strArg1.equalsIgnoreCase("{VALID USER}")? PropertyUtility.getDataProperties("new.customer.user"):strArg1;
            forgotPasswordPage.visibilityOf(forgotPasswordPage.Button_Continue());

            String smsCode = DbUtility.getVerificationCode(phoneNumber);

            cucumberContextManager.setScenarioContext("SMS_CODE", smsCode);
            testStepAssert.isFalse(smsCode.equals(""),
                    "I should able to fetch value for sms code", "SMS CODE for " + strArg1 + " is " + smsCode,
                    "Not able to fetch sms code for " + strArg1);

        } catch (Throwable e) {
            logger.error("Error performing step" + e.getMessage());
            error( "Step  Should be sucessfull", "Error performing step,Error", true);
        }
    }
}
