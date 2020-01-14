package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.PromosPage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class PromosSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(PromosSteps.class);
    ActionManager action = new ActionManager();
    PromosPage promoPage = new PromosPage();
    GeneralUtility utilities = new GeneralUtility();

    @And("^I add \"([^\"]*)\" PromoCode$")
    public void iAddPromoCode(String arg0) throws Throwable {
        try {
            String promoCode = "";
            switch (arg0) {
                case "valid":
                    promoCode = PropertyUtility.getDataProperties("promocode.valid");
                    break;
                case "valid percent":
                    promoCode = PropertyUtility.getDataProperties("promocode.valid.percent");
                    break;
                case "valid one off":
                    promoCode=PropertyUtility.getDataProperties("promocode.one.off");
                    break;
                case "fixed valid":
                    promoCode = PropertyUtility.getDataProperties("promocode.fixedvalid");
                    break;
                case "invalid":
                    promoCode = PropertyUtility.getDataProperties("promocode.invalid");
                    break;
                case "expired":
                    promoCode = PropertyUtility.getDataProperties("promocode.expired");
                    break;
                case "referral":
                    promoCode = PropertyUtility.getDataProperties("referral.code");
                    break;
                case "first time":
                    promoCode = PropertyUtility.getDataProperties("promocode.firsttime");
                    break;
                case "used one off":
                    promoCode = PropertyUtility.getDataProperties("promocode.usedoneoff");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            action.clearSendKeys(promoPage.Textfield_PromoCode(), promoCode);
            cucumberContextManager.setScenarioContext("ADDED_PROMOCODE", promoCode);
            log(" I should able to add " + arg0 + " promo code ",
                    "I entered promo code '" + promoCode + "'", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" promo code should be displayed$")
    public void something_promo_code_should_be_displayed(String strArg1) throws Throwable {
        try {
            String expectedMessage = PropertyUtility.getDataProperties("promocode.valid.percent");
            action.scrollToBottom();
            testStepAssert.isTrue(utilities.isPromoCodePresent(expectedMessage), "Promo code should be added", "Promocode is added", "Promocode is not added");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on Save Money page$")
    public void i_should_see_something_on_save_money_page(String strArg1) throws Throwable {
        try {
            String expectedMessage = "";
            String actualMessage = "";
            switch (strArg1) {
                case "promocode added":
                    expectedMessage = PropertyUtility.getDataProperties("promocode.valid");
                    action.scrollToBottom();action.scrollToBottom();action.scrollToBottom();
                    testStepAssert.isTrue(utilities.isPromoCodePresent(expectedMessage), "Promo code should be added", "Promocode is added", "Promocode is not added");
                    break;
                case "snackbar message for invalid code":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.invalid");
//                    testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(utilities.getCustomerSnackBarMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    break;
                case "snackbar message for expired code":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.expired");
               //     testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(utilities.getCustomerSnackBarMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    break;
                case "snackbar message for already added code":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.already.existing.code");
            //        testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(utilities.getCustomerSnackBarMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    break;
                case "snackbar stating referrals are only for new users":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.referral.error");
                  //  actualMessage = action.getText(promoPage.Snackbar());
                    actualMessage = utilities.getCustomerSnackBarMessage();
                    testStepAssert.isEquals(actualMessage, expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    break;
                case "snackbar stating first time code is for new users":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.first.time.error.android");
                //    expectedMessage = PropertyUtility.getMessage("customer.promos.first.time.old.user");
                //    testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(utilities.getCustomerSnackBarMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    break;
                case "snackbar message for used one off code":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.invalid");
                //    testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(utilities.getCustomerSnackBarMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    break;
                case "snackbar message stating referral already exists":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.referral.error");
               //     testStepAssert.isElementTextEquals(promoPage.Snackbar(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    testStepAssert.isEquals(utilities.getCustomerSnackBarMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
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

/*    @And("^I tap \"([^\"]*)\" on Save Money page$")
    public void i_tap_something_on_save_money_page(String strArg1) throws Throwable {
        switch (strArg1)
        {
            case "Add":
                action.click(promoPage.Button_Add());
                break;
            case "Get More Money":
                action.click(promoPage.Button_GetMoreMoney());
                break;
            default: break;
        }
        log(" I should able at tap "+strArg1+" on Save Money page", "I clicked on "+strArg1, true);
    }*/

    @And("^I tap \"([^\"]*)\" on Save Money page$")
    public void iTapOnSaveMoneyPage(String arg0) throws Throwable {
        try {
            switch (arg0) {
                case "Add":
                    action.click(promoPage.Button_AddPromoPage());
                    break;
                case "Get More Money":
                    action.click(promoPage.Button_GetMoreMoney());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log(" I should able to tap " + arg0 + " on Save Money page",
                    "I tapped on " + arg0 + " on Save Money Page", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }



    @Then("^I should see \"([^\"]*)\" on Bungii estimate page$")
    public void i_should_see_something_on_bungii_estimate_page(String strArg1) throws Throwable {
        i_should_see_something_on_save_money_page(strArg1);
    }

    @And("^I tap \"([^\"]*)\" on Promos page$")
    public void i_tap_something_on_promos_page(String strArg1) throws Throwable {
        iTapOnSaveMoneyPage(strArg1);
    }

    @When("^I tap on the \"([^\"]*)\" icon$")
    public void i_tap_on_the_something_icon(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "i":
                    Thread.sleep(3000);
                    action.click(promoPage.Icon_i());
                    break;
                default:
                    error("UnImplemented Step or incorrect icon name", "UnImplemented Step");
                    break;
            }
            log(" I should able to tap " + strArg1 + " icon",
                    "I tapped on " + strArg1 + " icon", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" message on the Promos page$")
    public void i_should_see_something_message_on_the_promos_page(String strArg1) throws Throwable {
        try {
            String expectedMessage = "";
            String actualMessage = "";
            switch (strArg1) {
                case "Promo code for first Bungii selected by default":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.first.time.info");
                    testStepAssert.isEquals(utilities.getCustomerPromoInfoMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    action.click(promoPage.Button_OK());
                    log("I click OK button on the info popup",
                            "I have clicked OK button on the info popup", true);
                    break;
                case "Promo codes are automatically applied":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.info");
                    testStepAssert.isEquals(utilities.getCustomerPromoInfoMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    action.click(promoPage.Button_OK());
                    log("I click OK button on the info popup",
                            "I have clicked OK button on the info popup", true);
                    break;
                case "First time promo code not used":
                    expectedMessage = PropertyUtility.getMessage("customer.promos.first.time.unused.info");
                    testStepAssert.isEquals(utilities.getCustomerPromoInfoMessage(), expectedMessage, "Validation message :'" + expectedMessage + "' should be displayed", "'" + expectedMessage + "' message should be displayed", "'" + expectedMessage + "' message should be displayed");
                    action.click(promoPage.Button_Cancel());
                    log("I click Cancel button on the alert popup",
                            "I have clicked Cancel button on the alert popup", true);
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

    @And("^I should see the \"([^\"]*)\" PromoCode selected by default$")
    public void i_should_see_the_something_promocode_selected_by_default(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "first time":
                    testStepAssert.isElementDisplayed(promoPage.FirstTime_PromoCode_SelectedByDefault(), "Invite page header should be displayed", "Invite page is displayed", "Invite page is not displayed");
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

    @And("^I select \"([^\"]*)\" on the Promos page$")
    public void i_select_something_on_the_promos_page(String strArg1) throws Throwable {
        Thread.sleep(3000);
        action.click(promoPage.PromoCode_R0D1());
        log("I click on the promo Code on Promos page",
                "I have clicked on the promo Code on Promos page", true);
    }

    @Then("^I should see the unused promo code$")
    public void i_should_see_the_unused_promo_code() throws Throwable {
        testStepAssert.isElementDisplayed(promoPage.PromoCode_R0D1(), "Promo code should be displayed", "Promo code is displayed", "Promo code is not displayed");

    }

    @And("^I select the added promo code$")
    public void i_select_the_added_promo_code() throws Throwable {
        Thread.sleep(3000);
        action.click(promoPage.PromoCode_R0D1());
        log("I click on the promo Code on Promos page",
                "I have clicked on the promo Code on Promos page", true);
    }

    @Then("^I should see the previously added promo code present for current Bungii request$")
    public void i_should_see_the_previously_added_promo_code_present_for_current_bungii_request() throws Throwable {
        testStepAssert.isElementDisplayed(promoPage.PromoCode_R0D1_OnEstimate(), "Promo code should be displayed", "Promo code is displayed", "Promo code is not displayed");
    }

}
