package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_DriverVerificationPage;
import com.bungii.web.pages.admin.Admin_MenuLinksPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;

public class Admin_DriverVerificationSteps extends DriverBase {
    Admin_DriverVerificationPage admin_DriverVerificationPage = new Admin_DriverVerificationPage();
    Admin_MenuLinksPage admin_MenuLinksPage = new Admin_MenuLinksPage();

    GeneralUtility utility = new GeneralUtility();
    ActionManager action = new ActionManager();

    @And("^I check if each field has an \"([^\"]*)\" option$")
    public void i_check_if_each_field_has_an_something_option(String p0) throws Throwable {
        switch(p0)
        {
            case "accept":
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverPic(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverFirstName(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverLastName(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverStreetAddress(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverCity(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverState(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverZip(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverSSN(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverBirthday(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverPickupImages(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverPickupMake(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverPickupModel(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverPickupYear(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverPickupLicense(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverLicenseImage(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverLicenseNumber(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverLicenseExpiration(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverInsuranceImage(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverInsurationExpiration(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverRoutingNumber(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Approve_DriverAccountNumber(),"","","");
                break;

            case "reject":
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_DriverPicture(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_FirstName(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_LastName(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_StreetAddress(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_City(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_State(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_ZipCode(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_SSN(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_Birthday(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_DriverPickupImages(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_PickupMake(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_PickupModel(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_PickupYear(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_PickupLicenseNumber(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_LicenseImage(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_LicenseNumber(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_LicenseExpiration(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_InsuranceImage(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_InsuranceExpiration(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_RoutingNumber(),"","","");
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Verify_Reject_AccountNumber(),"","","");
                break;
        }    }

    @And("^I verify and approve the \"([^\"]*)\" field$")
    public void i_verify_and_approve_the_something_field(String strArg1) throws Throwable {
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPic());
    }

    @And("^I verify and reject the \"([^\"]*)\" field$")
    public void i_verify_and_reject_the_something_field(String strArg1) throws Throwable {
        switch(strArg1)
        {
            case "Birthday":
                action.click(admin_DriverVerificationPage.Verify_Reject_Birthday());
                break;
            case "Driver Picture":
                action.click( admin_DriverVerificationPage.Verify_Reject_DriverPicture());
                break;
        }    }

    @And("^I check if the status has been changed to \"([^\"]*)\"$")
    public void i_check_if_the_status_has_been_changed_to_something(String strArg1) throws Throwable {
        switch(strArg1)
        {
            case "accepted":
                testStepAssert.isElementDisplayed( admin_DriverVerificationPage.Status_Accepted(),"","","");
                break;

            case "rejected":
                action.sendKeys(admin_DriverVerificationPage.Textinput_ReasonforRejection_Birthday(),"Invalid DOB");
                break;
        }    }


    @And("^I click and reset the status of \"([^\"]*)\" field$")
    public void i_click_and_reset_the_status_of_something_field(String strArg1) throws Throwable {
        action.click(admin_DriverVerificationPage.Verify_Reject_DriverPicture());
    }

    @And("^I check if the status of \"([^\"]*)\" field has been changed to rejected$")
    public void i_check_if_the_status_of_something_field_has_been_changed_to_rejected(String strArg1) throws Throwable {
        action.sendKeys(admin_DriverVerificationPage.Textinput_ReasonforRejection_DriverPicture(),"Clear picture needed");
    }

    @And("^I click and reset the Rejected status of \"([^\"]*)\" field$")
    public void i_click_and_reset_the_rejected_status_of_something_field(String strArg1) throws Throwable {
        action.click(admin_DriverVerificationPage.Verify_Reject_DriverPicture());
    }

    @And("^I verify that the status has been reset$")
    public void i_verify_that_the_status_has_been_reset() throws Throwable {
        testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Status_Accepted(),"","","");
    }

    @And("^I check if the Save and cancel buttons are seen by default$")
    public void i_check_if_the_save_and_cancel_buttons_are_seen_by_default() throws Throwable {
        testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Button_Save(),"","","");
        testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Button_Cancel(),"","","");

    }

    @And("^I check if a Cancel confirmation message is shown$")
    public void i_check_if_a_cancel_confirmation_message_is_shown() throws Throwable {
        testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Popup_ConfirmCancelDriverVerificationApplication(),"","","");
    }
    @And("^I check if a Cancel confirmation message is not shown$")
    public void i_check_if_a_cancel_confirmation_message_is_not_shown() throws Throwable {
        testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Popup_ConfirmCancelDriverVerificationApplication(),"","","");
    }

    @And("^I check if admin gets directed to dashboard$")
    public void i_check_if_admin_gets_directed_to_dashboard() throws Throwable {
        testStepAssert.isElementDisplayed(admin_MenuLinksPage.Menu_Dashboard(),"","","");
    }


}
