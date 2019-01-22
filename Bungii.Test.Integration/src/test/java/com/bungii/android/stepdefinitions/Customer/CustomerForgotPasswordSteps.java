package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.ForgotPasswordPage;
import com.bungii.android.pages.customer.LoginPage;
import com.bungii.android.pages.customer.SignupPage;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.AssertManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import static com.bungii.common.manager.ResultManager.*;

public class CustomerForgotPasswordSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(CustomerForgotPasswordSteps.class);
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
    SignupPage Page_Signup = new SignupPage();
    LoginPage Page_CustomerLogin = new LoginPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbutility = new DbUtility();


    @And("I tap on the {string} Link")
    public void iTapOnTheLink(String arg0) {
        try {

            switch (arg0) {
                case "Login":
                    action.click(Page_Signup.Link_Login());
                    break;
                case "Forgot Password":
                    action.click(Page_CustomerLogin.Link_ForgotPassword());
                    break;
                case "Send":
                    action.click(forgotPasswordPage.Button_ForgotPass_Send());
                    break;
                case "Continue":
                    action.click(forgotPasswordPage.Button_Continue());
                    break;
                case "Sign Up":
                    action.click(Page_Signup.Button_Signup());
                    break;
                case "Verification Continue":
                    action.click(Page_Signup.Button_VerifyContinue());
                    break;
                default:
                    break;
            }
            log(" I tap on the " + arg0 + "Link",
                    "I tapped on " + arg0, true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("I enter {string} Phone Number")
    public void i_enter_Phone_Number(String string) {
        try {

            switch (string) {
                case "valid":
                    action.sendKeys(forgotPasswordPage.TextField_ForgotPass_PhoneNumber(), PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                    break;
                case "invalid":
                    action.sendKeys(forgotPasswordPage.TextField_ForgotPass_PhoneNumber(), PropertyUtility.getDataProperties("customer_Invalid.phonenumber"));
                    break;
                case "less than 10 digit":
                    action.sendKeys(forgotPasswordPage.TextField_ForgotPass_PhoneNumber(), PropertyUtility.getDataProperties("customer_LessThan10.phonenumber"));
                    break;
                default:
                    break;
            }
            log(" I enter" + string + " Phone Number",
                    "I entered " + string + " phone number", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("I enter {string} SMS code")
    public void i_enter_SMS_code(String string) {
        try {

            switch (string) {
                case "valid":
                    String SMSCode = dbutility.getVerificationCode(PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                    action.sendKeys(forgotPasswordPage.TextField_SMSCode(), SMSCode);
                    break;
                case "invalid":
                    action.sendKeys(forgotPasswordPage.TextField_SMSCode(), PropertyUtility.getDataProperties("verificationcode.incorrect"));
                    break;
                default:
                    break;
            }
            log(" I enter" + string + " SMS code",
                    "I entered " + string + " SMS code", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("I enter customers new {string} Password")
    public void i_enter_customers_new_Password(String string) {
        try {

            String newPassword = "";
            switch (string) {
                case "valid":
                    newPassword = PropertyUtility.getDataProperties("customer_generic.password");
                    break;
                case "invalid":
                    newPassword = PropertyUtility.getDataProperties("customer_LessThan6.password");
                    break;
                default:
                    break;
            }
            action.sendKeys(forgotPasswordPage.TextField_NewPassword(), newPassword);

            log(" I enter customers new " + string + "Password",
                    "I entered " + newPassword + "as customers new " + string + " Password", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @Then("The user should be logged in")
    public void the_user_should_be_logged_in() {
        utility.isCorrectPage("Home");
        testStepAssert.isTrue(utility.isCorrectPage("Home"),"Home page should be displayed" ,"Home page is displayed","Home page was not displayed");

    }

    @Then("^The user should see \"([^\"]*)\" on forgot password page$")
    public void the_user_should_see_something_on_forgot_password_page(String strArg1) throws Throwable {
        String errorMessage = "";
        switch (strArg1) {
            case "snackbar validation message for success":
                WebElement snackBar = forgotPasswordPage.Snackbar_ForgotPassword(true);
                String actualMessage = "";
                if (snackBar == null) {
                    warning("Snackbar message for success should be displayed", "Snackbar message was not displayed or was displayed for small amount of time to capture snackbar message text");
                    break;
                } else {
                    actualMessage = snackBar.getText();
                    testStepVerify.isElementTextEquals(forgotPasswordPage.Snackbar_ForgotPassword(true), PropertyUtility.getMessage("customer.forgotpassword.success.android"));
                }
                break;

            case "snackbar validation message for invalid sms code":
                testStepVerify.isElementTextEquals(forgotPasswordPage.Snackbar_ForgotPassword(), PropertyUtility.getMessage("customer.forgotpassword.invalid.code.android"));
                break;
            case "Send button disabled":
                testStepVerify.isElementNotEnabled(forgotPasswordPage.Button_ForgotPass_Send(true), "Send buttons should be disabled ", "Send button is disabled", "Send Button is not disabled");
                break;
            case "validation for incorrect password":
                errorMessage = PropertyUtility.getMessage("customer.forgotpassword.invalid.password.android");
                testStepVerify.isEquals(action.getText(forgotPasswordPage.Err_InvalidPassword()), errorMessage);
                break;
            case "validation for incorrect number":
                errorMessage = PropertyUtility.getMessage("customer.forgotpassword.invalid.phone");
                testStepVerify.isEquals(action.getText(forgotPasswordPage.Err_InvalidPassword()), errorMessage);
                break;
            case "snackbar validation message for invalid number":
                errorMessage = PropertyUtility.getMessage("customer.forgotpassword.failed.reset");
                testStepVerify.isElementTextEquals(forgotPasswordPage.Snackbar_ForgotPassword(), errorMessage);
                break;


            default:
                throw new Exception("Unimplemented step");
        }
    }
}
