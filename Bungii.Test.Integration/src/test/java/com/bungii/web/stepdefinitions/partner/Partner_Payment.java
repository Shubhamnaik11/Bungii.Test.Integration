package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Map;

import static com.bungii.common.manager.ResultManager.fail;
import static com.bungii.common.manager.ResultManager.pass;

public class Partner_Payment extends DriverBase {

    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();
    ActionManager action = new ActionManager();


    @And("^I Select \"([^\"]*)\" as Payment Method$")
    public void i_select_something_as_payment_method(String str) throws InterruptedException {

        switch (str){
            case "Customer Card":
                action.click(Page_Partner_Delivery.Customer_Card());
                Thread.sleep(5000);
                break;
            case "Partner Pay":
                action.click(Page_Partner_Delivery.Partner_Pay());
            default:break;

        }

    }

    @And(("^I enter following Credit Card details on Partner Portal$"))
    public void i_enter_credit_card_details(DataTable data) {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String cardType = dataMap.get("CardNo");
        String expiry = dataMap.get("Expiry");
        String postal_code = dataMap.get("Postal_Code");
        String cvv = dataMap.get("Cvv");

        //action.iframeSwitch();

        try {
            String cardNumber;
            switch (cardType.toUpperCase()) {
                case "VISA CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.visa");
                    break;
                case "DISCOVER CARD":
                    cardNumber = PropertyUtility.getDataProperties("payment.valid.card.discover");
                    break;
                case "INVALID CARD":
                    cardNumber = PropertyUtility.getDataProperties("partner.invalid.card");
                    break;
                default:
                    cardNumber = cardType;

            }

            String postalCodeValue="",cvvValue="";
            switch (cvv.toUpperCase()) {
                case "VALID CVV":
                    cvvValue = PropertyUtility.getDataProperties("valid.card.cvv");
                    break;
                case "INVALID CVV":
                    cvvValue = PropertyUtility.getDataProperties("invalid.card.cvv");
                    break;
                default:break;
            }

            switch (postal_code.toUpperCase()) {
                case "VALID POSTAL CODE":
                    postalCodeValue = PropertyUtility.getDataProperties("valid.card.postal.code");
                    break;
                case "INVALID POSTAL CODE":
                    postalCodeValue = PropertyUtility.getDataProperties("invalid.card.postal.code");
                    break;
                default:break;
            }


            addCardDetails(cardNumber, expiry, cvvValue,postalCodeValue);


            cucumberContextManager.setScenarioContext("CARD_NUMBER", cardNumber);
            cucumberContextManager.setScenarioContext("CARD_EXPIRY", expiry);
            cucumberContextManager.setScenarioContext("Postal_Code", postalCodeValue);
            cucumberContextManager.setScenarioContext("CVV", cvvValue);

            pass("I should able enter " + cardNumber + " and " + expiry + " and " + postal_code + " and " + cvv + " on Card Details page",
                    "I entered " + cardNumber + " and " + expiry + " and " + postal_code + " and " + cvv + " on Card Details page",
                    true);
        } catch (Exception e) {
            fail(
                    "I should able enter " + cardType + " and " + expiry + " and " + postal_code + " and " + cvv + " on Card Details page",
                    "I was not able to entered " + cardType + " and " + expiry + " and " + postal_code + " and " + cvv + " on Card Details page",
                    true);
        }
    }

    @Then("^I should \"([^\"]*)\" on partner portal$")
    public void i_should_see_some_validation_message(String str){

        switch(str){
            case "see validation message for invalid card number":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.messageInvalidCardNumber()), PropertyUtility.getMessage("Invalid_Card_Number"));
                break;
            case "see validation message for Expired date":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.messageInvalidExpiredDate()), PropertyUtility.getMessage("Invalid_Expired_Date"));
                break;
            case "see validation message for Cvv":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.messageInvalidCVV()), PropertyUtility.getMessage("Invalid_Cvv"));
                break;
            case "see validation message for Postal Code":
                testStepVerify.isEquals(action.getText(Page_Partner_Delivery.messageInvalidPostal_Code()), PropertyUtility.getMessage("Invalid_Postal_Code"));
                break;
            default:break;
        }

    }

    public void addCardDetails(String cardNo, String expiry, String cvv, String postalCode) {
//		action.waitForExpectedElement(paymentPage.TextBox_CardNumber());
        //action.waitUntilIsElementExistsAndDisplayed(Page_Partner_Delivery.Card_Number(),10);



        action.switchToFrame("CardNumber_Frame");
        action.click(Page_Partner_Delivery.Card_Number());
        action.sendKeys(Page_Partner_Delivery.Card_Number(),cardNo);
        action.switchToFrame("Main");

        action.switchToFrame("Expiry_Frame");
        action.click(Page_Partner_Delivery.Expiry_Date());
        action.sendKeys(Page_Partner_Delivery.Expiry_Date(), expiry);
        action.switchToFrame("Main");

        action.switchToFrame("Cvv_Frame");
        action.click(Page_Partner_Delivery.CVV());
        action.sendKeys(Page_Partner_Delivery.CVV(), cvv);
        action.switchToFrame("Main");

        action.switchToFrame("PostalCode_Frame");
        action.click(Page_Partner_Delivery.Postal_Code());
        action.sendKeys(Page_Partner_Delivery.Postal_Code(), postalCode);
        action.switchToFrame("Main");

    }
}
