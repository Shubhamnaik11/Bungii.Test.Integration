package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_DriverVerificationPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

import static com.bungii.common.manager.ResultManager.log;

public class Admin_DriverResendApplicationSteps extends DriverBase {
    Admin_DriverVerificationPage admin_DriverVerificationPage = new Admin_DriverVerificationPage();
    ActionManager action = new ActionManager();


    @When("^I verify and reject the invalid verification fields$")
    public void i_verify_and_reject_the_invalid_verification_fields() throws Throwable {
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPic());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverFirstName());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverLastName());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverStreetAddress());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverCity());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverState());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverZip());
      //  action.click(admin_DriverVerificationPage.Verify_Approve_DriverSSN());

        action.click(admin_DriverVerificationPage.Verify_Reject_Birthday());
        action.sendKeys(admin_DriverVerificationPage.Textinput_ReasonforRejection_Birthday(),"Invalid DOB");
        action.click(admin_DriverVerificationPage.Verify_Reject_DriverPickupImages());
        action.sendKeys(admin_DriverVerificationPage.Textinput_ReasonforRejection_PickupImages(),"Invalid Pickup Images");

        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPickupMake());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPickupModel());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPickupYear());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPickupLicense());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverLicenseImage());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverLicenseNumber());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverLicenseExpiration());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverInsuranceImage());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverInsurationExpiration());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverRoutingNumber());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverAccountNumber());
        log("I verify and reject the invalid verification fields",
                "I have verified and reject the invalid verification fields", false);
    }
}
