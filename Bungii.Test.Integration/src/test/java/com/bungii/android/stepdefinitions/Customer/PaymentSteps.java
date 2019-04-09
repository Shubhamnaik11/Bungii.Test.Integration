package com.bungii.android.stepdefinitions.Customer;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.PaymentPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class PaymentSteps extends DriverBase {
    PaymentPage paymentPage = new PaymentPage();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(PaymentSteps.class);

    @And("^I tap on \"([^\"]*)\" on Payment page$")
    public void i_tap_on_something_on_payment_page(String strArg1) throws Throwable {
        try {
        switch (strArg1) {
            case "the 2nd payment method":
                cucumberContextManager.setScenarioContext("CardNumber", action.getText(paymentPage.PaymentCard2()));
                testStepVerify.isElementDisplayed(paymentPage.Checkbox_Default(), "Default checkbox should be displayed", "Default checkbox is displayed ", "Default checkbox is not displayed");
                testStepVerify.isElementDisplayed(paymentPage.Button_Save(), "Save button should be displayed", "Save button is displayed ", "Save button is not displayed");
                action.click(paymentPage.PaymentCard2());
                testStepVerify.isElementDisplayed(paymentPage.DefaultTick_payment2(), "Default checkbox should be displayed against second payment method", "Default checkbox is displayed against second payment method ", "Default checkbox is not displayed against second payment method");
                break;

            case "Set as default payment mode":
                testStepVerify.isElementDisplayed(paymentPage.Checkbox_Default(), "Default checkbox should be displayed", "Default checkbox is displayed ", "Default checkbox is not displayed");
                action.click(paymentPage.Checkbox_Default());
                break;
            case "Save":
                testStepVerify.isElementDisplayed(paymentPage.Button_Save(), "Save button should be displayed", "Save button is displayed ", "Save button is not displayed");
                action.click(paymentPage.Button_Save());
                break;

            case "Delete":
                action.click(paymentPage.Button_Delete());
                action.click(paymentPage.Button_Delete_Yes());
                break;

            case "Add":
                WebElement Button_Add = paymentPage.Button_Add();
                action.click(new Point(Button_Add.getLocation().getX(), Button_Add.getLocation().getY()));
                break;
            case "Add New":
                WebElement Button_AddNew = paymentPage.Link_AddNew();
                action.click(new Point(Button_AddNew.getLocation().getX(), Button_AddNew.getLocation().getY()));
                break;
            case "Credit or Debit Card":
                action.click(paymentPage.Select_Method_Card());
                if (action.isElementPresent(paymentPage.Header_CardDetailsPage()))
                    log("Header card details should be displayed", "Header card details is displayed ", false);
                else
                    log("Header card details should be displayed", "Header card details is not displayed ", false);
                break;

            case "Add Card":
                action.click(paymentPage.Button_AddCard());
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


    @When("^I swipe \"([^\"]*)\" card on the payment page$")
    public void i_swipe_something_card_on_the_payment_page(String strArg1) throws Throwable {
        try {
        switch (strArg1) {
            case "2nd":
                action.swipeLeft(paymentPage.PaymentCard2());
                cucumberContextManager.setScenarioContext("NO_ACTIVE_CARD", paymentPage.List_Card().size());
                break;
            case "default":
                action.swipeLeft(paymentPage.DefaultCard());
                break;
            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }
        log("I should able to swipe on "+strArg1 +" card on payment page","I swiped on "+strArg1+"card on payment page",true);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see \"([^\"]*)\" on Payment page$")
    public void i_should_see_something_on_payment_page(String strArg1) throws Throwable {
        try {
        switch (strArg1) {
            case "message when no payment methods exist":
                //  String ActualNoPaymentText = UtilFunctions.TrimString(paymentPage.Text_NoPaymentExists().Text);
                String ActualNoPaymentText = action.getText(paymentPage.Text_NoPaymentExists());
                testStepVerify.isEquals(ActualNoPaymentText,PropertyUtility.getMessage("customer.payment.add.android") );
                testStepVerify.isElementDisplayed(paymentPage.Button_Add(), "Add Buttons should be displayed on Payment Page", "Add button is displayed on Payment Page", "Add button is not displayed on Payment Page");
                break;

            case "default payment set":
                testStepVerify.isElementDisplayed(paymentPage.DefaultTick_payment1(), "Default Tick should be displayed on Payment Page", "Add button is displayed on Payment Page", "Add button is not displayed on Payment Page");
                String CardNumber = (String) cucumberContextManager.getScenarioContext("CardNumber");
                testStepAssert.isEquals(action.getText(paymentPage.PaymentCard1()), CardNumber, "Card number " + CardNumber + " should be default payment card ", "Card number " + CardNumber + " is default card", "Card number " + CardNumber + " is not default card");
                testStepVerify.isElementDisplayed(paymentPage.Checkbox_Default(), "Default checkbox should be displayed", "Default checkbox is displayed ", "Default checkbox is not displayed");
                testStepVerify.isElementDisplayed(paymentPage.Button_Save(), "Save button should be displayed", "Save button is displayed ", "Save button is not displayed");
                break;

            case "the card has been deleted":
                Thread.sleep(5000);
                int oldCardCount = (int) cucumberContextManager.getScenarioContext("NO_ACTIVE_CARD");
                int currentCardCount = paymentPage.List_Card().size();
                testStepAssert.isTrue(currentCardCount == oldCardCount - 1, "There should be " + String.valueOf(oldCardCount - 1) + " card present on customer payment page ", "There are " + currentCardCount + "payment method on customer page page");
                //  testStepAssert.isNotElementDisplayed(paymentPage.PaymentCard2(),"Payment card should not be visible","Payment card is not visible","Payment card is visible");
                break;

            case "the card has been added":
                testStepVerify.isElementDisplayed(paymentPage.PaymentCard1(), "Default Tick should be displayed on Payment Page", "Add button is displayed on Payment Page", "Add button is not displayed on Payment Page");
                testStepVerify.isElementDisplayed(paymentPage.DefaultTick(), "Default checkbox should be displayed", "Default checkbox is displayed ", "Default checkbox is not displayed");
                String ExpectedLast4Digits = (String) cucumberContextManager.getScenarioContext("Last4Digits");
                String ActualLast4Digits = action.getText(paymentPage.DefaultCard()).replace("*", "").replace(" ", "");
                testStepVerify.isEquals(ActualLast4Digits,ExpectedLast4Digits);
                break;

            case "invalid card error":
                if(action.getText(paymentPage.Error_CardNumber()).equals(PropertyUtility.getMessage("customer.payment.invalid.card")))
                    log("Invalid card message should be displayed", "Invalid card message is displayed", false);
                else
                    log("Invalid card message should be displayed", "Invalid card message is not displayed", false);
                break;
            case "no option to add previous year":
                if(!action.isElementPresent(paymentPage.Year_2018(true)))
                    log("I should not able to select previous year ","I was not able to see previous year , Expiry year starts from"+action.getText(paymentPage.Expiry_Years()),false);
                else
                    log("I should not able to select previous year ","I was able to see previous year option in expiry year, options are "+action.getText(paymentPage.Expiry_Years()),false);
                break;
            case "no delete button":
                testStepVerify.isElementNotEnabled(paymentPage.Button_Delete(true),"Delete button should not be visible for default card","Delete button is not enabled","Delete button is enabled");
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

    @And("^I enter \"([^\"]*)\" on Card Details page$")
    public void i_enter_something_on_card_details_page(String p1) throws Throwable {
        try {
        switch (p1) {
            case "valid card number":
                paymentPage.Textfield_CardNumber().sendKeys(PropertyUtility.getDataProperties("payment.valid.card.jcb"));
                cucumberContextManager.setScenarioContext("Last4Digits", PropertyUtility.getDataProperties("payment.valid.card.jcb").substring(PropertyUtility.getDataProperties("payment.valid.card.jcb").length() - 4));
                break;
            case "valid visa card number":
                paymentPage.Textfield_CardNumber().sendKeys(PropertyUtility.getDataProperties("payment.valid.card.visa"));
                cucumberContextManager.setScenarioContext("Last4Digits", PropertyUtility.getDataProperties("payment.valid.card.visa").substring(PropertyUtility.getDataProperties("payment.valid.card.jcb").length() - 4));
                break;
            case "valid discover card number":
                paymentPage.Textfield_CardNumber().sendKeys(PropertyUtility.getDataProperties("payment.valid.card.discover"));
                cucumberContextManager.setScenarioContext("Last4Digits", PropertyUtility.getDataProperties("payment.valid.card.discover").substring(PropertyUtility.getDataProperties("payment.valid.card.jcb").length() - 4));
                break;
            case "invalid card number":
                action.click(paymentPage.Textfield_CardNumber());
                action.sendKeys(PropertyUtility.getDataProperties("payment.invalid.card"));
               // paymentPage.Textfield_CardNumber().sendKeys(PropertyUtility.getDataProperties("payment.invalid.card"));
                break;

            case "valid expiry date":
                action.click(paymentPage.Month_12());
                action.click(paymentPage.Year_2020());
                SetupManager.getDriver().navigate().back();
                break;

            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }
            log("I enter "+p1 +" on Card Details page","I enter"+p1+"on Card Details page",false);

        } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
}
