package com.bungii.web.stepdefinitions.driver;

import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.driver.*;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.pass;

public class DriverRegistrationSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(DriverRegistrationSteps.class);
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    Driver_ForgotPasswordPage Page_ForgotPassword = new Driver_ForgotPasswordPage();
    Driver_VerifyPhonePage Page_VerifyPhone = new Driver_VerifyPhonePage();
    Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage();
    Driver_DetailsPage Page_Driver_Details = new Driver_DetailsPage();
    Driver_PickUpInfoPage Page_Driver_Pickup = new Driver_PickUpInfoPage();
    Driver_DocumentationPage Page_Driver_Documentation = new Driver_DocumentationPage();
    Driver_BankDetailsPage Page_Driver_BankDetails = new Driver_BankDetailsPage();
    Driver_TermsPage Page_Driver_Terms = new Driver_TermsPage();
    Driver_VideoTrainingPage Page_Driver_Video = new Driver_VideoTrainingPage();
    Driver_FinishPage Page_Driver_Finish = new Driver_FinishPage();
    Driver_DashboardPage Page_Driver_Dashboard = new Driver_DashboardPage();
    GeneralUtility utility = new GeneralUtility();
    ActionManager action = new ActionManager();

    @Given("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to_something(String p0) throws Throwable {
        utility.NavigateToDriverLogin();
        pass("I should be navigate to " + p0,
                "I am navigate to " + p0, true);
    }

    @Given("^I am logged in as driver$")
    public void GivenIAmLoggedInAsDriver() {
       //  utility.DriverLogin("9999999113", "Cci12345");

        utility.DriverLogin(PropertyUtility.getDataProperties("DriverPhoneNumber"), PropertyUtility.getDataProperties("DriverPassword"));
        pass("I am logged in as driver",
                "I was logged in as driver using " + PropertyUtility.getDataProperties("DriverPhoneNumber") + " phone number", true);
    }

    @Then("^the driver logout from dashboard$")
    public void the_driver_logout_from_dashboard() throws Throwable {
        utility.DriverLogout();
        log("I should be logged out from dashboard ","I clicked ", true);
    }

    @When("^I enter \"([^\"]*)\" details on Signup page$")
    public void i_enter_something_details_on_signup_page(String strArg1) throws Throwable {
        switch (strArg1) {
            case "valid":
                action.clearSendKeys(Page_Driver_Reg.TextBox_FirstName(), PropertyUtility.getDataProperties("DriverFirstName"));
                String Lastname = utility.GetUniqueLastName();
                action.clearSendKeys(Page_Driver_Reg.TextBox_LastName(),Lastname);
                cucumberContextManager.setScenarioContext("LASTNAME", Lastname);

                action.clearSendKeys(Page_Driver_Reg.TextBox_Email(), PropertyUtility.getDataProperties("DriverEmail"));
                action.clearSendKeys(Page_Driver_Reg.TextBox_CreatePassword(), PropertyUtility.getDataProperties("DriverPassword"));
                action.clearSendKeys(Page_Driver_Reg.TextBox_ConfirmPassword(), PropertyUtility.getDataProperties("DriverPassword"));
                action.selectElementByText(Page_Driver_Reg.Dropdown_Location(),PropertyUtility.getDataProperties("DriverLocation"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Driver_Reg.TextBox_FirstName(), PropertyUtility.getDataProperties("Invalid_DriverName"));
                action.clearSendKeys(Page_Driver_Reg.TextBox_LastName(), PropertyUtility.getDataProperties("Invalid_DriverName"));
                action.clearSendKeys(Page_Driver_Reg.TextBox_Email(), PropertyUtility.getDataProperties("Invalid_DriverEmail"));
                action.clearSendKeys(Page_Driver_Reg.TextBox_CreatePassword(), PropertyUtility.getDataProperties("Invalid_DriverPassword"));
                action.clearSendKeys(Page_Driver_Reg.TextBox_ConfirmPassword(), PropertyUtility.getDataProperties("Short_DriverPassword"));
                action.selectElementByText(Page_Driver_Reg.Dropdown_Location(),PropertyUtility.getDataProperties("DriverLocation"));
                break;
            case "short password":
                action.clearSendKeys(Page_Driver_Reg.TextBox_CreatePassword(), PropertyUtility.getDataProperties("Short_DriverPassword"));
                action.selectElementByText(Page_Driver_Reg.Dropdown_Location(),PropertyUtility.getDataProperties("DriverLocation"));
                break;
            default:
                break;
        }
        log("I should able to enter "+ strArg1+" on signup pages","I entered  "+strArg1 +" on sign up page", true);

    }

    @When("^I click \"([^\"]*)\" on driver portal$")
    public void i_click_something_on_driver_portal(String p0) throws Throwable {
        switch (p0) {
            case "LOG IN link":
                action.click(Page_Driver_Login.Tab_LogIn());
                break;
            case "LOG IN button":
                action.click(Page_Driver_Login.Button_DriverLogin());
                break;
            case "Forgot Password":
                action.click(Page_ForgotPassword.Link_ForgotPassword());
                break;
            case "Back to Login":
                action.click(Page_ForgotPassword.Link_BackToLogin());
                break;
            case "Send Verification Code":
                action.click(Page_ForgotPassword.Button_SendVerifCode());
                break;
            case "Resend Code on Verify your phone page":
                String Code_Initial = DbUtility.getVerificationCode(PropertyUtility.getDataProperties("DriverPhoneNumber"));
                cucumberContextManager.setScenarioContext("Code_Initial", Code_Initial);
                action.click(Page_VerifyPhone.Button_ResendCode());
                break;
            case "Reset Password":
                action.click(Page_VerifyPhone.Button_ResetPassword());
                break;
            case "Signup button":
                action.click(Page_Driver_Reg.Button_SignUp());
                break;
            case "Resend verification code":
                String DriverPhone = (String) cucumberContextManager.getScenarioContext("DriverPhone");
                String VerifCode_Initial = DbUtility.getVerificationCode(DriverPhone);
                cucumberContextManager.setScenarioContext("VerifCode_Initial", VerifCode_Initial);
                action.click(Page_Driver_Reg.Button_ResendCode());
                break;
            case "Submit verification code":
                action.click(Page_Driver_Reg.Button_SubmitVerification());
                break;
            case "Continue Registration":
                action.click(Page_Driver_Reg.Button_ContinueReg());
                break;
            case "I agree to the Terms and Conditions":
                if (Page_Driver_Terms.CheckBox_Agree(true) != null) {
                    if (Page_Driver_Terms.CheckBox_Agree(true).isSelected() == false)

                        action.click(Page_Driver_Terms.CheckBox_Agree_Click());
                }
                break;
            case "I have viewed the entire video":
                if (Page_Driver_Video.CheckBox_Viewed(true) != null) {
                    if (Page_Driver_Video.CheckBox_Viewed(true).isSelected() == false)
                        action.click(Page_Driver_Video.CheckBox_Viewed_Click());
                }
                break;
            case "Continue on Finish page":
                action.click(Page_Driver_Finish.Button_FinishContinue());
                break;
            default:
                break;
        }
        log("I click able to click "+p0+" on driver portal","I clicked  "+p0 +" on driver page", true);

    }

    @Then("^I should be directed to \"([^\"]*)\" on Driver portal$")
    public void i_should_be_directed_to_something_on_driver_portal(String strArg1) throws Throwable {
        switch (strArg1) {
            case "signup tab":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.Header_DriverRegistration()), PropertyUtility.getMessage("DriverRegistrationHeader"));
                break;
            case "LOG IN tab":
                testStepVerify.isEquals(action.getText(Page_Driver_Login.Header_DriverLogin()), PropertyUtility.getMessage("DriverLoginHeader"));
                break;
            case "Forgot Password tab":
                testStepVerify.isEquals(action.getText(Page_ForgotPassword.Header_ForgotPassword()), PropertyUtility.getMessage("DriverForgotPasswordHeader"));
                break;
            case "Verify Your Phone tab":
                testStepVerify.isEquals(action.getText(Page_VerifyPhone.Header_VerifyPhone()), PropertyUtility.getMessage("DriverVerifyPhoneHeader"));
                break;
            case "phone verification page":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.Text_Verification()), PropertyUtility.getMessage("RegSuccess"));
                break;
            case "Verification Successful page":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.Header_VerificationSuccess()), PropertyUtility.getMessage("SMSVerifSuccess"));
                break;
            case "driver Details page":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("DriverDetailsHeader"));
                break;
            case "Pickup Info page":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("PickupInfoHeader"));
                break;
            case "Documentation page":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("DocHeader"));
                break;
            case "Bank Details page":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("BankDetHeader"));
                break;
            case "Terms & Conditions":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("TermsHeader"));
                testStepVerify.isEquals(action.getText(Page_Driver_Terms.Terms_H5()), PropertyUtility.getMessage("TermsSubHeader"));
                testStepVerify.isEquals(action.getText(Page_Driver_Terms.Text_Terms()), PropertyUtility.getMessage("TermsText"));
                break;
            case "Video Training":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("VideoHeader"));
                testStepVerify.isElementDisplayed(Page_Driver_Video.Screen_Video(true), "Video screen should be displayed", "Video screen was  displayed", "");
                break;
            case "Finish":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("FinishHeader"));
                break;
            case "Dashboard":
               // testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.Header_Dashboard()), PropertyUtility.getMessage("DriverDashboardHeader"));
                testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.SideNavigationSetting()), PropertyUtility.getMessage("DriverHomeSetting"));
                testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.SideNavigationGeneral()), PropertyUtility.getMessage("DriverHomeGENERAL"));
                break;
            default:
                break;
        }
    }

    @Then("^I should see \"([^\"]*)\" on Driver Registration$")
    public void i_should_see_something_on_driver_registration(String p0) throws Throwable {
        switch (p0) {
            case "new verification code":
                String VerifCode_Initial = (String) cucumberContextManager.getScenarioContext("VerifCode_Initial");
                String DriverPhone = (String) cucumberContextManager.getScenarioContext("DriverPhone");
                String VerifCode_Updated = DbUtility.getVerificationCode(DriverPhone);
                cucumberContextManager.setScenarioContext("VerificationCode", VerifCode_Updated);
                testStepVerify.isFalse(VerifCode_Updated.equals(VerifCode_Initial), "New verification code should not be same as old code", "New verification code is not same as old code", "New verification code is same as old code");
                break;
            case "Logged in user name":
                String UserName = PropertyUtility.getDataProperties("DriverFirstName") + " " + (String) cucumberContextManager.getScenarioContext("LASTNAME"); //PropertyUtility.getDataProperties("DriverLastName");
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.Text_DriverName()), UserName);
                break;
            case "correct field validations":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_FirstName()), PropertyUtility.getMessage("DReg_FirstName_Invalid"));
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_LastName()), PropertyUtility.getMessage("DReg_LastName_Invalid"));
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_Email()), PropertyUtility.getMessage("DReg_Email_Invalid"));
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_Phone()), PropertyUtility.getMessage("DReg_Phone_Invalid"));
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_CreatePassword()), PropertyUtility.getMessage("DReg_Password_Invalid"));
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_ConfirmPassword()), PropertyUtility.getMessage("DReg_ConfirmPassword_Incorrect"));
                break;
            case "Global validation message":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_BlankFields()), PropertyUtility.getMessage("Err_Pages_BlankFields"));
                break;
            case "existing phone error":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_Phone()), PropertyUtility.getMessage("DReg_Phone_Exists"));
                break;
            case "field validation for short password":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_CreatePassword()), PropertyUtility.getMessage("DReg_Password_Short"));
                break;
            case "validation for blank verification code":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_VerifiCode_Blank()), PropertyUtility.getMessage("VerifCode_Err_Blank"));
                break;
            case "validation for incorrect verification code":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_VerifiCode_Invlid()), PropertyUtility.getMessage("VerifCode_Err_Invalid"));
                break;
            default:
                break;
        }

    }

    @When("^I enter \"([^\"]*)\" verification code$")
    public void i_enter_something_verification_code(String strArg1) throws Throwable {
        switch (strArg1) {
            case "correct":
                String DriverPhone = (String) cucumberContextManager.getScenarioContext("DriverPhone");
                String VerificationCode = DbUtility.getVerificationCode(DriverPhone);
                action.clearSendKeys(Page_Driver_Reg.TextBox_VerificationCode(), VerificationCode);
                break;
            case "empty":
                action.clear(Page_Driver_Reg.TextBox_VerificationCode());
                break;
            case "incorrect":
                action.clearSendKeys(Page_Driver_Reg.TextBox_VerificationCode(), PropertyUtility.getDataProperties("Invalid_DriverPhoneNumber"));
                break;
            default:
                break;
        }
        log("I should able to enter "+strArg1+" verification code","I entered "+strArg1, true);

    }
    @And("^I enter driver Phone number as \"([^\"]*)\" and valid password$")
    public void i_enter_driver_phone_number_as_something_and_valid_password(String phone) throws Throwable {
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), phone);
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("DriverPassword"));
        action.click(Page_Driver_Login.Button_DriverLogin());
    }
    @And("^I enter \"([^\"]*)\" driver phone number on Signup page$")
    public void i_enter_something_driver_phone_number_on_signup_page(String p0) throws Throwable {
        switch (p0) {
            case "unique":
                String DriverPhone = utility.generateMobileNumber();
                cucumberContextManager.setScenarioContext("DriverPhone", DriverPhone);
                action.clearSendKeys(Page_Driver_Reg.TextBox_PhoneNumber(), DriverPhone);
                break;
            case "invalid":
                action.clearSendKeys(Page_Driver_Reg.TextBox_PhoneNumber(), PropertyUtility.getDataProperties("Invalid_DriverPhoneNumber"));
                break;
            case "existing":
                action.clearSendKeys(Page_Driver_Reg.TextBox_PhoneNumber(), PropertyUtility.getDataProperties("Existing_DriverPhoneNumber"));
                break;
            default:
                break;
        }
        log("I should able to enter "+p0+" driver phone number on Signup page","I entered "+p0 +" driver phone number on signup page", true);

    }

}
