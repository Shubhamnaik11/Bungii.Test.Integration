package com.bungii.android.stepdefinitions;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.bungiiCustomer.*;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.manager.AssertManager;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.*;

public class CustomerForgotPasswordSteps {

    ForgotPasswordPage Page_ForgotPassword = new ForgotPasswordPage();
    SignupPage Page_Signup = new SignupPage();
    LoginPage Page_CustomerLogin = new LoginPage();

    AssertManager assertManager = new AssertManager();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbutility = new DbUtility();

    @Given("I am on Sign up page")
    public void iAmOnSignUpPage() {
        action.waitUntilIsElementExistsAndDisplayed(Page_Signup.Button_Signup());
    }

    @And("I tap on the {string} Link")
    public void iTapOnTheLink(String arg0) {
        switch (arg0)
        {
            case "Login":
                action.click(Page_Signup.Link_Login());
                break;
            case "Forgot Password":
                action.click(Page_CustomerLogin.Link_ForgotPassword());
                break;
            case "Send":
                action.click(Page_ForgotPassword.Button_ForgotPass_Send());
                break;
            case "Continue":
                action.click(Page_ForgotPassword.Button_Continue());
                break;
            case "Sign Up":
                action.click(Page_Signup.Button_Signup());
                break;
            case "Verification Continue":
                action.click(Page_Signup.Button_VerifyContinue());
                break;
            default: break;
        }
    }

    @When("I enter {string} Phone Number")
    public void i_enter_Phone_Number(String string) {
        switch (string)
        {
            case "valid":
                action.sendKeys(Page_ForgotPassword.TextField_ForgotPass_PhoneNumber(),  PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                break;
            case "invalid":
                action.sendKeys(Page_ForgotPassword.TextField_ForgotPass_PhoneNumber(),PropertyUtility.getDataProperties("customer_Invalid.phonenumber "));
                break;
            case "less than 10 digit":
                action.sendKeys(Page_ForgotPassword.TextField_ForgotPass_PhoneNumber(), PropertyUtility.getDataProperties("customer_LessThan10.phonenumber"));
                break;
            default: break;
        }
    }

    @When("I enter {string} SMS code")
    public void i_enter_SMS_code(String string) {
        switch (string)
        {
            case "valid":
                String SMSCode = dbutility.getVerificationCode( PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                action.sendKeys(Page_ForgotPassword.TextField_SMSCode(), SMSCode);
                break;
            case "invalid":
                action.sendKeys(Page_ForgotPassword.TextField_SMSCode(), PropertyUtility.getDataProperties("verificationcode.incorrect"));
                break;
            default: break;
        }
    }

    @When("I enter customers new {string} Password")
    public void i_enter_customers_new_Password(String string) {
        switch (string)
        {
            case "valid":
                action.sendKeys(Page_ForgotPassword.TextField_NewPassword(), PropertyUtility.getDataProperties("customer_generic.password"));
                break;
            case "invalid":
                action.sendKeys(Page_ForgotPassword.TextField_NewPassword(), PropertyUtility.getDataProperties("customer_LessThan6.password"));
                break;
            default: break;
        }
    }

    @Then("The user should see {string}")
    public void the_user_should_see(String string) {

    }

    @Then("The user should be logged in")
    public void the_user_should_be_logged_in() {

    }

}
