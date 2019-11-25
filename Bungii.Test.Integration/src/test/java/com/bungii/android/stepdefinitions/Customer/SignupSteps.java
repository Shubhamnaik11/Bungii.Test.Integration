package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.SignupPage;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.*;

public class SignupSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    SignupPage Page_Signup = new SignupPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbutility = new DbUtility();

    @When("^I enter \"([^\"]*)\" customer phone number on Signup Page$")
    public void i_enter_something_customer_phone_number_on_signup_page(String strArg1) throws Throwable {
        try {
            String customerPhone = "";
            switch (strArg1) {
                case "unique":
                    customerPhone = utility.generateMobileNumber();
                    break;
                case "blank":
                    break;
                case "invalid":
                    customerPhone = PropertyUtility.getDataProperties("customer.phone.number.invalid");
                    break;
                case "existing":
                    customerPhone = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            action.sendKeys(Page_Signup.TextField_Phonenumber(), customerPhone);
            cucumberContextManager.setScenarioContext("CustomerPhoneNum", customerPhone);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I enter \"([^\"]*)\" data in mandatory fields on Signup Page$")
    public void i_enter_something_data_in_mandatory_fields_on_signup_page(String strArg1) throws Throwable {
        try {

            switch (strArg1) {
                case "valid":
                    action.clearSendKeys(Page_Signup.TextField_FirstName(), PropertyUtility.getDataProperties("customer.first.name")+ RandomGeneratorUtility.getData("{RANDOM_STRING}",3));
                    action.clearSendKeys(Page_Signup.TextField_LastName(), PropertyUtility.getDataProperties("customer.last.name"));
                    action.click(Page_Signup.TextField_Email());
                    action.sendKeys(PropertyUtility.getDataProperties("customer.email"));
                    action.hideKeyboard();
                    //    action.clearsendKeys(Page_Signup.TextField_Email(), /*PropertyUtility.getDataProperties("customer.email")*/"@cc.com");
                    action.clearSendKeys(Page_Signup.TextField_Password(), PropertyUtility.getDataProperties("customer.password.new.password"));
                    action.click(Page_Signup.Select_ReferralSource());
                    action.click(Page_Signup.Option_ReferralSource());
                    action.click(Page_Signup.Link_ReferralSourceDone());
                    break;

                case "blank":
                    action.clearSendKeys(Page_Signup.TextField_FirstName(), "");
                    action.clearSendKeys(Page_Signup.TextField_LastName(), "");
                    action.clearSendKeys(Page_Signup.TextField_Email(), "");
                    action.clearSendKeys(Page_Signup.TextField_Password(), "");
                    break;

                case "invalid":
                    action.click(Page_Signup.TextField_Email());
                    action.sendKeys(PropertyUtility.getDataProperties("customer.email.invalid"));
                    action.hideKeyboard();
                    //action.sendKeys(Page_Signup.TextField_Email(), PropertyUtility.getDataProperties("customer.email.invalid"));
                    action.sendKeys(Page_Signup.TextField_Password(), PropertyUtility.getDataProperties("customer.password.invalid"));
                    Page_Signup.TextField_Referral().click();
                    action.hideKeyboard();
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I enter \"([^\"]*)\" Verification code$")
    public void i_enter_something_verificationcode(String strArg1) {
        String smsCode = "";
        try {
            switch (strArg1) {
                case "valid":
                    Thread.sleep(20000);
                    smsCode = dbutility.getVerificationCode((String) cucumberContextManager.getScenarioContext("CustomerPhoneNum"));
                    break;
                default:
                        error("UnImplemented Step or incorrect button name", "UnImplemented Step");
            }
            action.sendKeys(Page_Signup.Textfield_SMSCode(), smsCode);

            pass("I should able to enter verification code",
                    "I entered verification code : " + smsCode + "in sms code field", true);
            //TODO:REMOVE THIS
            //  Thread.sleep(20000);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("I am on Sign up page")
    public void iAmOnSignUpPage() {
        try {
            if (!action.isElementPresent(Page_Signup.Header_SignUp(true)))
                utility.goToSignupPage();
            testStepVerify.isElementDisplayed(Page_Signup.Header_SignUp(true), "Signup button should be displayed ", "Sign up button is displayed", "Sign up button is not displayed");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I tap on the \"([^\"]*)\" button on Signup Page$")
    public void i_tap_on_the_something_button_on_signup_page(String strArg1) throws Throwable {
        try {
        switch (strArg1) {
            case "Sign Up":
                action.scrollToBottom();
                action.click(Page_Signup.Button_Signup());
                break;
            case "No, Continue":
                action.click(Page_Signup.Button_NoReferralConfirm());
                break;
            case "Yes":
                action.click(Page_Signup.Button_NoReferralYes());
                break;
            case"Log in":
                action.click(Page_Signup.Link_Login());
                break;
            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^the new user should see \"([^\"]*)\"$")
    public void the_new_user_should_see_something(String strArg1) throws Throwable {
        try {
        switch (strArg1) {
            case "sign up button disabled":
                testStepVerify.isElementNotEnabled(Page_Signup.Button_Signup(true), "Signup button should be disabled", "Signup button is disabled", "Signup button is enabled");
                break;

            case "validations for all fields":
                testStepVerify.isElementTextEquals(Page_Signup.Cust_Signup_Error_Email(), PropertyUtility.getMessage("customer.signup.invalid.email.android"));
                testStepVerify.isElementTextEquals(Page_Signup.Cust_Signup_Error_Phone(), PropertyUtility.getMessage("customer.signup.invalid.phone.number.android"));
                testStepVerify.isElementTextEquals(Page_Signup.Cust_Signup_Error_Password(), PropertyUtility.getMessage("customer.signup.invalid.password.android"));
                break;

            case "Signup page":
                testStepVerify.isElementDisplayed(Page_Signup.Button_Signup(), "Signup button should be displayed", "Signup button is displayed ", "Signup button is not displayed");

                testStepVerify.isTrue(utility.isCorrectPage("Signup"), "Signup should be displayed", "Signup page is displayed", "Signup page is not displayed");
                break;

            case "snackbar validation message for existing user":
                testStepVerify.isEquals(utility.getSnackBarMessage(), PropertyUtility.getMessage("customer.signup.existinguser"), "Warning message for Existing message should be displayed", "Snackbar message is displayed", "Snackbar message is not displayed");
                break;

            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I enter \"([^\"]*)\" promo code on Signup Page$")
    public void i_enter_something_promo_code_on_signup_page(String strArg1) throws Throwable {
        try {
        String strPromoCode = "";
        switch (strArg1) {
            case "ValidPercent":
                strPromoCode = PropertyUtility.getDataProperties("promocode.valid.percent");
                break;
            case "invalid":
                strPromoCode = PropertyUtility.getDataProperties("promocode.invalid");
                break;
            case "Referral":
                strPromoCode = PropertyUtility.getDataProperties("referral.code");
                break;
            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }
        action.click(Page_Signup.CheckBox_Promo());
        action.sendKeys(Page_Signup.TextField_Referral(), strPromoCode);
        log("I should able to enter Promo code in signup Page ",
                "I entered  " + strPromoCode + " as " + strArg1 + "promoCode", true);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
}
