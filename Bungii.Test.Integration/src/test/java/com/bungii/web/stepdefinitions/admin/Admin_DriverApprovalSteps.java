package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.driver.Driver_PickUpInfoPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_DriverApprovalSteps extends DriverBase {
    Admin_LoginPage adminLoginPage = new Admin_LoginPage();
    Admin_MenuLinksPage adminMenuLinksPage = new Admin_MenuLinksPage();
    Admin_DashboardPage adminDashboardPage = new Admin_DashboardPage();
    Admin_DriverVerificationPage admin_DriverVerificationPage = new Admin_DriverVerificationPage();
    Admin_GetAllBungiiDriversPage admin_GetAllBungiiDriversPage = new Admin_GetAllBungiiDriversPage();
    Admin_PromoCodesPage admin_PromoCodesPage = new Admin_PromoCodesPage();
    Admin_ReferralSourcePage admin_ReferralSourcePage = new Admin_ReferralSourcePage();
    Admin_BusinessUsersPage admin_BusinessUsersPage = new Admin_BusinessUsersPage();
    Admin_PromoterPage admin_PromoterPage = new Admin_PromoterPage();
    Admin_GeofencePage admin_GeofencePage = new Admin_GeofencePage();
    Admin_CustomerPage admin_customerPage=new Admin_CustomerPage();
    Admin_DriversPage admin_DriverPage=new Admin_DriversPage();
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    Driver_DashboardPage driver_DashboardPage = new  Driver_DashboardPage();
    Driver_PickUpInfoPage Page_Driver_PickupInfo = new Driver_PickUpInfoPage();
    Admin_PartnerPortalPage admin_partnerPortalPage = new Admin_PartnerPortalPage();
    Admin_PartnersPage admin_partnersPage=new Admin_PartnersPage();
    private static LogUtility logger = new LogUtility(Admin_DriverApprovalSteps.class);

    GeneralUtility utility = new GeneralUtility();
    ActionManager action = new ActionManager();

    @Given("^I am logged in as Admin$")
    public void i_am_logged_in_as_admin() throws Throwable {
        utility.AdminLogin();
    }
    @Given("^I am logged in as TestAdmin$")
    public void i_am_logged_in_as_Testadmin() throws Throwable {
        utility.TestAdminLogin();
    }
    @And("^there is a pending application for driver verification$")
    public void there_is_a_pending_driver_verification() throws Throwable {
        testStepAssert.isElementDisplayed(adminMenuLinksPage.Menu_Dashboard(true), "I should be naviagate to Admin Dashboard", "I was navigated to admin Dashboard", "Admin Dashboard is not visible");
        //WebAssertionManager.ElementDisplayed(adminDashboardPage.RecentDriverRegistrations);
        testStepAssert.isElementDisplayed(adminDashboardPage.PendingVerification().get(0), "There should be Pending application", "There is Pending application", "There is not Pending application");
    }

    @When("^I click \"([^\"]*)\" button against the applicant name$")
    public void i_click_something_button_against_the_applicant_name(String button) throws Throwable {

       // cucumberContextManager.setScenarioContext("LASTNAME", "KSqc");
    try{
        //Search code
       action.click(adminDashboardPage.Link_ViewAllDriverRegistrations());
        String Lastname =  (String) cucumberContextManager.getScenarioContext("LASTNAME");
        action.clearSendKeys(admin_GetAllBungiiDriversPage.TextBox_Search(),Lastname);
        action.click(admin_GetAllBungiiDriversPage.Button_Search());
        Thread.sleep(4000);
        switch (button) {
            case "Verify":
                action.click(admin_GetAllBungiiDriversPage.GridRow_PendingVerificationLink(Lastname));
                break;
        }
        log("I click on "+button+" button against the applicant name",
                "I have clicked on "+button+" button against the applicant name", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @When("^I click \"([^\"]*)\" button against the \"([^\"]*)\" applicant$")
    public void i_click_something_button_against_the_something_applicant(String strArg1, String applicantName) throws Throwable {
        try{
        action.click(adminDashboardPage.Link_ViewAllDriverRegistrations());
        String[] name =  applicantName.split(" ");
        //action.clearSendKeys(admin_GetAllBungiiDriversPage.TextBox_Search(),name[1]);
        action.clearSendKeys(admin_GetAllBungiiDriversPage.TextBox_Search(),applicantName);
        cucumberContextManager.setScenarioContext("FIRSTNAME",name[0]);
        cucumberContextManager.setScenarioContext("LASTNAME",name[1]);
        action.click(admin_GetAllBungiiDriversPage.Button_Search());
        Thread.sleep(4000);
        switch (strArg1) {
            case "Verify":
                action.click(admin_GetAllBungiiDriversPage.GridRow_PendingVerificationLink(applicantName));
                break;
        }
        log("I should be able to click "+strArg1+" against " + applicantName,"I click "+strArg1+ " against "+ applicantName, false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I click \"([^\"]*)\" button for the \"([^\"]*)\" driver$")
    public void i_click_something_button_for_the_something_driver(String strArg1, String applicantName) throws Throwable {
        try{
        switch (strArg1) {
            case "Profile":
                action.click(admin_GetAllBungiiDriversPage.Driver_Profile(applicantName));
                break;
            case "Edit":
                String Old_Phone_Number = action.getAttributeValue(admin_GetAllBungiiDriversPage.Driver_Phone());
                cucumberContextManager.setScenarioContext("Old_Phone",Old_Phone_Number);
                action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Edit());
                break;
            case "Save":
                action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Save());
                break;
            case "Cancel":
                action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Cancel());
                break;

        }
        log("I should able to click "+strArg1+" against " + applicantName,"I have clicked "+strArg1+ " against "+ applicantName, false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I change the \"([^\"]*)\" phone number$")
    public void i_enter_confirm_comment_for_edited_phone_and_something_it(String strArg1) throws Throwable {

        action.clearSendKeys(admin_GetAllBungiiDriversPage.Driver_Phone(),PropertyUtility.getDataProperties("driver.mobile.change"));
        //action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Save());
        log("I should able to change "+strArg1+" phone number.","I have changed "+strArg1+" phone number.",true);
    }

    @And("^I enter confirm comment for edited phone and \"([^\"]*)\" it$")
    public void i_confirm_the_edited_phone(String State) throws Throwable {
        try{
        //action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_updated_comment());
        switch (State) {
            case "Save":
                action.clearSendKeys(admin_GetAllBungiiDriversPage.Driver_Mobile_Updated_Comment(), "Driver phone updated");
                action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Updated_Comment_Save());
                break;
            case "Cancel":
                action.clearSendKeys(admin_GetAllBungiiDriversPage.Driver_Mobile_Updated_Comment(), "Driver phone updated");
                action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Updated_Comment_Cancel());
        }
        log("I should able to enter confirm comment for edited phone and "+State+" it","I have entered confirm comment for the edited phone and "+State+" it",true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I see updated driver phone number$")
    public void i_see_updated_phone_number() throws Throwable {
        try{
        Thread.sleep(2000);
        testStepAssert.isElementNotEnabled(admin_GetAllBungiiDriversPage.Driver_Phone(),"Driver phone field should be not enabled.","Driver phone field is not enabled.","Driver phone field is enabled");
        String Edited_Phone_Number = action.getAttributeValue(admin_GetAllBungiiDriversPage.Driver_Phone());

        testStepVerify.isEquals(Edited_Phone_Number,PropertyUtility.getDataProperties("driver.mobile.change"));
        log("Driver phone number should get update.","Driver phone number is updated.",true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I see unchanged driver phone number$")
    public void i_see_unchanged_phone_number() throws Throwable {
        try{
        testStepAssert.isElementNotEnabled(admin_GetAllBungiiDriversPage.Driver_Phone(),"Driver phone field should not be enabled.","Driver phone field is not enabled.","Driver phone field is enabled");
        String Display_Phone_Number = action.getAttributeValue(admin_GetAllBungiiDriversPage.Driver_Phone());

        testStepVerify.isEquals(Display_Phone_Number,(String) cucumberContextManager.getScenarioContext("Old_Phone"));
        log("Driver phone number should remain un change.","Driver phone number is unchanged.",true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I cancel the edited phone number$")
    public void i_cancel_the_edited_phone_number() throws Throwable {
        try{
        action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Cancel());

        log("I should able to cancel the edited phone number","I have cancelled the edited phone number.",true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should be directed to \"([^\"]*)\"$")
    public void i_should_be_directed_to_something(String screen) throws Throwable {
        try{
        switch (screen) {
            case "Driver Verification Page":
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Title_DriverVerificationPage(true), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                break;
            case "Promo Code Page":
                testStepAssert.isElementDisplayed(admin_PromoCodesPage.Title_PromocodesPage(true), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                break;
            case "Standard Codes Page":
                testStepAssert.isElementDisplayed(admin_PromoCodesPage.Title_StandardcodesPage(true), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                break;
            case "Referral Source Page":
                testStepAssert.isElementDisplayed(admin_ReferralSourcePage.Title_ReferralSourcePage(true), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                break;
//            case "Business Users Page":
            case "Partners Page":
                testStepAssert.isElementDisplayed(admin_BusinessUsersPage.Header_BusinessUsers(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                break;
            case "Promoters Page":
                testStepAssert.isElementDisplayed(admin_PromoterPage.Title_PromoterPage(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                break;
            case "Geofences Page":
                testStepAssert.isElementDisplayed(admin_GeofencePage.Header_Geofences(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                break;
            case "Attributes Page":
                testStepAssert.isElementDisplayed(admin_GeofencePage.Header_Attributes(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                break;
            case "Customers Page":
                testStepAssert.isElementDisplayed(admin_customerPage.Label_CustomerList(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                break;
            case "Drivers Page":
                testStepAssert.isElementDisplayed(admin_DriverPage.Label_DriversPageHeader(),"I should be navigated to "+screen, "I am navigated to "+ screen, "I am not navigates to "+ screen);
                break;
            case "Partner Portal Page":
                testStepAssert.isElementDisplayed(admin_partnerPortalPage.Label_PartnerListHeader(),"I should be navigated to "+screen, "I am navigated to "+ screen, "I am not navigates to "+ screen);
                break;
            case "Unlock Partners Page":
                testStepAssert.isElementDisplayed(admin_partnersPage.Label_Unlock_Partners(),"I should be navigated to "+screen, "I am navigated to "+ screen, "I am not navigates to "+ screen);
                break;

        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @Then("^I should see \"([^\"]*)\" submenu$")
    public void i_should_see_something_submenu(String submenu) throws Throwable {
        try{
            testStepAssert.isElementDisplayed(admin_partnerPortalPage.Menu_UnlockPartners(),"I should see "+submenu+" submenu", "I see "+submenu+" submenu", "I do not see "+submenu+" submenu");


        } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I verify and approve all the verification fields$")
    public void i_verify_and_approve_all_the_verification_fields() throws Throwable {
        try{
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPic());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverFirstName());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverLastName());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverStreetAddress());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverCity());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverState());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverZip());
       // action.click(admin_DriverVerificationPage.Verify_Approve_DriverSSN());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverBirthday());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPickupImages());
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
        log("I verify and approve all the verification fields",
                "I have verified and approved all the verification fields", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I click on the \"([^\"]*)\" Button$")
    public void iClickOnTheButton(String arg0) throws Throwable {
        try{
        String Name = null, xpath=null;
        switch (arg0)
        {
            case "Approve Application":
                action.click(admin_DriverVerificationPage.Button_DriverApproveApplication());
                Thread.sleep(5000);
                break;
            case "Submit":
                action.click(admin_DriverVerificationPage.Button_Submit());
                Thread.sleep(5000);
                break;
            case "Resend Application":
                action.click(admin_DriverVerificationPage.Button_DriverResentButton());
                break;
            case "Cancel":
            action.click(admin_DriverVerificationPage.Button_Cancel());
            break;
            case "New Code":
                action.click(admin_PromoCodesPage.Button_NewCode());
                break;
            case "Apply":
                action.click(admin_PromoCodesPage.Button_Apply());
                Thread.sleep(2000);
                break;
            case "Reset":
                action.click(admin_PromoCodesPage.Button_Reset());
                Thread.sleep(2000);
                break;
            case "Save":
                Thread.sleep(5000);
                action.click(admin_PromoCodesPage.Button_Save());
                break;
//            case "New Business User":
            case "New Partner":
                action.click(admin_BusinessUsersPage.Button_CreateBusinessUser());
                break;
            case "New Partners":
                action.click(admin_PromoterPage.Button_NewPromoter());
                break;
            case "New Portal Parter":
                action.click(admin_partnerPortalPage.Button_NewPartner());
                break;
            case "Edit":
                Name = (String)cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
                xpath = String.format("//tr[1]/td[text()='%s']/following-sibling::td/button[contains(text(),'Edit')]",Name);
                cucumberContextManager.setScenarioContext("XPATH", xpath );
                SetupManager.getDriver().findElement(By.xpath(xpath)).click();
                break;
//BOC
            case "Add Payment Method":
                action.click(admin_BusinessUsersPage.Button_RequestPayment());
                break;

//EOC

            case "Scale":
                action.click(admin_GeofencePage.Button_Scale());
                break;
        }
        log("I click on the "+arg0+ " button" ,
                "I have clicked on the "+arg0+ " button");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I confirm the \"([^\"]*)\" action$")
    public void i_confirm_the_something_action(String strArg1) throws Throwable {
        try{
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
        }
        log("I can confirm " + strArg1 + " action" ,
                "I have confirmed " + strArg1 + " action");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^the \"([^\"]*)\" button is not visible$")
    public void i_check_if_something_button_is_visible(String strArg1) throws Throwable {
        try{
        switch (strArg1)
        {
            case "Approve Application":
                testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Button_DriverApproveApplication(),"Approve Application button should be displayed","Approve Application button is displayed","Approve Application button is not displayed");
                break;
            case "Resend Application":
                testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Button_DriverResentButton(),"Resend Application button should be displayed","Resend Application button is displayed","Resend Application button is not displayed");
                break;
        }

    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
}




        @Then("^the status of the driver application should be marked as \"([^\"]*)\"$")
        public void theStatusOfTheDriverApplicationShouldBeMarkedAs(String arg0) throws Throwable {

        try{
        //Search code
            String Lastname =  (String) cucumberContextManager.getScenarioContext("LASTNAME");
            action.clearSendKeys(admin_GetAllBungiiDriversPage.TextBox_Search(),Lastname);
            action.click(admin_GetAllBungiiDriversPage.Button_Search());
            Thread.sleep(3000);
            switch (arg0)
            {
                case "Active":
                  testStepAssert.isElementDisplayed(admin_GetAllBungiiDriversPage.GridRow_BungiiActiveDriver(Lastname),"Active Status should be displayed","Active Status is displayed","Active Status is not displayed");
                    break;
                case "Rejected":
                    testStepAssert.isElementDisplayed(admin_GetAllBungiiDriversPage.GridRow_BungiiRejectedDriver(Lastname),"Rejected Status should be displayed","Rejected Status is displayed","Rejected Status is not displayed");
                    break;
                case "Re-sent to Driver":
                    testStepAssert.isElementDisplayed(admin_GetAllBungiiDriversPage.GridRow_BungiiResentToDriver(Lastname),"Re-sent to Driver Status should be displayed","Re-sent to Driver Status is displayed","Re-sent to Driver Status is not displayed");
                    break;
                case "Pending Verification":
                    testStepAssert.isElementDisplayed(admin_GetAllBungiiDriversPage.GridRow_BungiiPendingVerification(Lastname),"Pending Verification Status should be displayed","Pending Verification Status is displayed","Pending Verification Status is not displayed");
                    break;
            }

        } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
         }


    @Then("^the validation message \"([^\"]*)\" is displayed$")
    public void theValidationMessageIsDisplayed(String arg0) throws Throwable {
        testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Validation_Message_PleaseAddRejectionReason(),"I check if a validation message is displayed","Validation message is displayed","Validation message is not displayed");

    }

    @When("^I enter the reject reason$")
    public void iEnterTheRejectReason() throws Throwable {
        try{
        action.clearSendKeys(admin_DriverVerificationPage.Textinput_ReasonforRejectDriverApplication(),"Invalid values found. Please review and resend the application");
        log("I enter the reject reason" ,
                "I have I entered the reject reason");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the status of the field changes to \"([^\"]*)\"$")
    public void theStatusOfTheFieldChangesTo(String arg0) throws Throwable {
        try{
        switch (arg0) {
            case "Accepted":
                testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Status_Accepted(),"I check status of the field ","Status is accepted" , "Field is not accepted");
            break;
                case "Rejected":
                testStepAssert.isElementValueEquals(admin_DriverVerificationPage.Status_Accepted(), "","I check status of the field ","Status is rejected" , "Field is not rejected");
        break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the status of the field resets to default$")
    public void theStatusOfTheFieldResetsToDefault() throws Throwable {
        testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Status_Accepted(),"I check status field ","Element is not displayed" , "Element is displayed");

    }

    @And("^I verify all the fields except \"([^\"]*)\"$")
    public void i_verify_all_the_fields_except_something(String strArg1) throws Throwable {
        try{
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPic());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverFirstName());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverLastName());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverStreetAddress());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverCity());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverState());
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverZip());
       // action.click(admin_DriverVerificationPage.Verify_Approve_DriverSSN());
        action.click(admin_DriverVerificationPage.Verify_Reject_Birthday());
        action.sendKeys(admin_DriverVerificationPage.Textinput_ReasonforRejection_Birthday(),"Invalid DOB");
        action.click(admin_DriverVerificationPage.Verify_Approve_DriverPickupImages());
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
        log("I can verify all the fields except DOB" ,
                "I have verified all the fields except DOB");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error in approving/rejecting driver application fields",
                true);
    }
    }

    @When("^I login as driver \"([^\"]*)\"$")
    public void i_login_as_driver_something(String driverName) throws Throwable {
        try{
        utility.NavigateToDriverLogin();
        action.click(Page_Driver_Login.Tab_LogIn());
        switch(driverName) {
            case "John PxLK":
            action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), PropertyUtility.getDataProperties("web.valid.driver21.phone"));
            break;
        }
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("web.valid.common.driver.password"));
        action.click(Page_Driver_Login.Button_DriverLogin());
        log("I login as driver" ,
                "I have logged in as driver");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @Then("^Admin receives \"([^\"]*)\" email$")
    public void admin_receives_something_email(String strArg1) {
      //  throw new PendingException();
    }

    @And("^Correct the fields and resubmit$")
    public void correct_the_fields_and_resubmit() {
        try {
            action.click(driver_DashboardPage.Link_DriverDetails());
            action.clearSendKeys(driver_DashboardPage.TextBox_DOB(), "12/12/1991");
            action.click(driver_DashboardPage.Button_Update());

            action.click(driver_DashboardPage.Link_PickupInfo());
            action.click(driver_DashboardPage.Link_RemoveFile3());
            action.click(driver_DashboardPage.Link_RemoveFile2());
            action.click(driver_DashboardPage.Link_RemoveFile1());

            utility.addImageInDropZone(Page_Driver_PickupInfo.DropZoneHiddenFileTag_TruckImage(), getTruckImages());
            action.invisibilityOfElementLocated(Page_Driver_PickupInfo.Wrapper_Spinner());
            int size = Page_Driver_PickupInfo.Div_UploadedImages().size();
            int count = 0;
            while (size != 3) {
                Thread.sleep(2000);
                if (count >= 20)
                    break;

                size = Page_Driver_PickupInfo.Div_UploadedImages().size();
                count++;
            }


            action.click(driver_DashboardPage.Button_Update());
            action.click(driver_DashboardPage.Button_Submit());
            action.click(driver_DashboardPage.Button_Yes());
            log("I correct the fields and resubmit" ,
                    "I have correct the fields and resubmited");
        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    public String[] getTruckImages() {
        String[] truckImageList = new String[3];
        truckImageList[0] = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("TRUCK1_IMAGE"));
        truckImageList[1] = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("TRUCK2_IMAGE"));
        truckImageList[2] = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("TRUCK3_IMAGE"));
        return truckImageList;
    }
}
