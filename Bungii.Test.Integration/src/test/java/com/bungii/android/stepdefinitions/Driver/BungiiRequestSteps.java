package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.SearchingPage;
import com.bungii.android.pages.driver.BungiiRequest;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DecimalFormat;

import static com.bungii.common.manager.ResultManager.error;

public class BungiiRequestSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(BungiiRequestSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    BungiiRequest bungiiRequestPage = new BungiiRequest();


    @Then("\"([^\"]*)\" should be displayed on Bungii request screen")
    public void i_click_something_button_on_forgot_password_screen_on_driver_app(String option) {
        try {
            switch (option) {
                case "correct stack trip details":
                    String expectedPickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")),expectedPickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")),
                            expectedDropLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")),expectedDropLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")), expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO") ? "DUO" : "SOLO";
                    String pickUpLocationLine1 = action.getText(bungiiRequestPage.Text_PickupLocation_LineOne()),pickUpLocationLine2= action.getText(bungiiRequestPage.Text_PickupLocation_LineTwo());
                    String dropUpLocationLine1 = action.getText(bungiiRequestPage.Text_DropOffLocation_LineOne()),dropUpLocationLine2 = action.getText(bungiiRequestPage.Text_DropOffLocation_LineTwo());
                    testStepVerify.isTrue(pickUpLocationLine1.equals(expectedPickUpLocationLineOne) && pickUpLocationLine2.equals(expectedPickUpLocationLineTwo),

                            "Pick up address should be " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo, "Pick up address is " + pickUpLocationLine1+pickUpLocationLine2,
                            "Expected pickup address is " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo + ", but actual is" + pickUpLocationLine1+pickUpLocationLine2);
                    testStepVerify.isTrue(dropUpLocationLine1.equals(expectedDropLocationLineOne) &&  dropUpLocationLine2.equals(expectedDropLocationLineTwo),

                            "Drop address should be " + expectedDropLocationLineOne +expectedDropLocationLineTwo, "Drop address is " + dropUpLocationLine1 +dropUpLocationLine2,
                            "Expected Drop address is " + expectedDropLocationLineOne +expectedDropLocationLineTwo + ", but actual is" + dropUpLocationLine1 +dropUpLocationLine2);
                    testStepVerify.isElementEnabled(bungiiRequestPage.Text_Distance(),"Distance tag should be displayed");
                    testStepVerify.isElementEnabled(bungiiRequestPage.Text_Earning(),"Earning tag should be displayed");
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_ValueDistance(),(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
                    String estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
                    double flestimate=Double.valueOf(estimate.replace("$","").trim());
                    double transactionFee=(flestimate*0.029)+0.3;
                    double estimatedDriverCut=(0.7*flestimate)-transactionFee;
                    String truncValue = new DecimalFormat("#.00").format(estimatedDriverCut);
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_ValueEarning(),"$"+truncValue);
                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_Reject(),"Reject button should be displayed");
                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_Accept(),"Accept button should be displayed");
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Navigation_Header(),"STACKED BUNGII REQUEST");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I tap on the \"([^\"]*)\" Button on Bungii Request screen$")
    public void i_tap_on_the_something_button_on_bungii_request_screen(String option) throws Throwable {
        try {
            switch (option) {
                case "ACCEPT":
                    action.click(bungiiRequestPage.Button_Accept());
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }    }

}
