package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_DashboardPage;
import com.bungii.web.pages.admin.Admin_DriverVerificationPage;
import com.bungii.web.pages.admin.Admin_LoginPage;
import com.bungii.web.pages.admin.Admin_MenuLinksPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Admin_DriverApprovalSteps extends DriverBase {
    Admin_LoginPage adminLoginPage = new Admin_LoginPage();
    Admin_MenuLinksPage adminMenuLinksPage = new Admin_MenuLinksPage();
    Admin_DashboardPage adminDashboardPage = new Admin_DashboardPage();
    Admin_DriverVerificationPage admin_DriverVerificationPage = new Admin_DriverVerificationPage();

    GeneralUtility utility = new GeneralUtility();
    ActionManager action = new ActionManager();

    @Given("^I am logged in as Admin$")
    public void i_am_logged_in_as_admin() throws Throwable {
        utility.AdminLogin();
    }

    @And("^there is a pending driver verification$")
    public void there_is_a_pending_driver_verification() throws Throwable {
        testStepAssert.isElementDisplayed(adminMenuLinksPage.Menu_Dashboard(true), "I should be naviagate to Admin Dashboard", "I was navigated to admin Dashboard", "Admin Dashboard is not visible");
        //WebAssertionManager.ElementDisplayed(adminDashboardPage.RecentDriverRegistrations);
        testStepAssert.isElementDisplayed(adminDashboardPage.PendingVerification(true), "There should be Pending application", "There is Pending application", "There is not Pending application");
    }

    @When("^I click \"([^\"]*)\" button against the applicant name$")
    public void i_click_something_button_against_the_applicant_name(String strArg1) throws Throwable {
        switch (strArg1) {
            case "Verify":
                action.click(adminDashboardPage.PendingVerification());
                break;
        }

    }

    @Then("^I should be directed to \"([^\"]*)\"$")
    public void i_should_be_directed_to_something(String screen) throws Throwable {
        testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Title_DriverVerificationPage(true), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
    }

    @And("^I verify and approve all the verification fields$")
    public void i_verify_and_approve_all_the_verification_fields() throws Throwable {
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPic());
        action.click( admin_DriverVerificationPage.Verify_Approve_DriverFirstName());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverLastName());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverStreetAddress());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverCity());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverState());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverZip());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverSSN());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverBirthday());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPickupImages());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPickupMake());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPickupModel());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPickupYear());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPickupLicense());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverLicenseImage());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverLicenseNumber());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverLicenseExpiration());;
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverInsuranceImage());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverInsurationExpiration());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverRoutingNumber());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverAccountNumber());   }

    @And("^I click on \"([^\"]*)\" button$")
    public void i_click_on_something_button(String strArg1) throws Throwable {
        switch (strArg1)
        {
            case "Approve Application":
                action.click(admin_DriverVerificationPage.Button_DriverApproveApplication());
                Thread.sleep(5000);
                break;

            case "Resend Application":
                action.click(admin_DriverVerificationPage.Button_DriverResentButton());
                break;
        }    }

    @And("^I confirm the \"([^\"]*)\" action$")
    public void i_confirm_the_something_action(String strArg1) throws Throwable {
        switch (strArg1)
        {
            case "Driver Application Approval":
                action.click(admin_DriverVerificationPage.Button_DriverConfirmApproval());
                break;

            case "Driver Resend Application":
                action.click(admin_DriverVerificationPage.Button_DriverConfirmResend());
                break;

            case "Driver Reject Application":
                action.click(admin_DriverVerificationPage.Button_DriverConfirmReject_Yes());
                break;
        }    }
    @And("^I check if \"([^\"]*)\" button is visible$")
    public void i_check_if_something_button_is_visible(String strArg1) throws Throwable {
        switch (strArg1)
        {
            case "Approve Application":
                testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Button_DriverApproveApplication(),"Approve Application button should be displayed","Approve Application button is displayed","Approve Application button is not displayed");
                break;
            case "Resend Application":
                testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Button_DriverResentButton(),"Resend Application button should be displayed","Resend Application button is displayed","Resend Application button is not displayed");
                break;
        }    }
    @And("^I check if I can change the driver application status $")
    public void i_check_if_i_can_change_the_driver_application_status() throws Throwable {
        throw new PendingException();
    }
}
