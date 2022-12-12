package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_DriverResendApplicationSteps extends DriverBase {
    PageBase pageBase = new PageBase();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_DriverResendApplicationSteps.class);


    @When("^I verify and reject the invalid verification fields$")
    public void i_verify_and_reject_the_invalid_verification_fields() throws Throwable {
        try{
            action.click(pageBase.getTextElement_DriverVerification("Driver Picture",true));
            action.click(pageBase.getTextElement_DriverVerification("First Name",true));
            action.click(pageBase.getTextElement_DriverVerification("Last Name",true));
            action.click(pageBase.getTextElement_DriverVerification("Street address",true));
            action.click(pageBase.getTextElement_DriverVerification("City",true));
            action.click(pageBase.getTextElement_DriverVerification("State",true));
            action.click(pageBase.getTextElement_DriverVerification("Zip code",true));
            //  action.click(admin_DriverVerificationPage.Verify_Approve_DriverSSN());

            action.click(pageBase.getTextElement_DriverVerification("Birthday",false));
            action.sendKeys(pageBase.getInputElement_DriverVerification("Birthday","AcceptedRejected"), "Invalid DOB");
            action.click(pageBase.getTextElement_DriverVerification("Pickup images",false));
            action.sendKeys(pageBase.getInputElement_DriverVerification("Pickup images","AcceptedRejected"), "Invalid Pickup Images");

            action.click(pageBase.getTextElement_DriverVerification("Pickup make",true));
            action.click(pageBase.getTextElement_DriverVerification("Pickup model",true));
            action.click(pageBase.getTextElement_DriverVerification("Pickup year",true));
            action.click(pageBase.getTextElement_DriverVerification("Pickup license number",true));
            action.click(pageBase.getTextElement_DriverVerification("License image",true));
            action.click(pageBase.getTextElement_DriverVerification("License number",true));
            action.click(pageBase.getTextElement_DriverVerification("License expiration",true));
            action.click(pageBase.getTextElement_DriverVerification("Insurance image",true));
            action.click(pageBase.getTextElement_DriverVerification("Insurance Expiration",true));

            if(action.isElementPresent(pageBase.getTextElement_DriverVerification("Routing Number",true, true))) {
                action.click(pageBase.getTextElement_DriverVerification("Routing Number",true));
            }
            if(action.isElementPresent(pageBase.getTextElement_DriverVerification("Account Number",true,true))) {
                action.click(pageBase.getTextElement_DriverVerification("Account Number",true));
            }

            log("I verify and reject the invalid verification fields",
                    "I have verified and reject the invalid verification fields", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
