package com.bungii.ios.stepdefinitions.driver;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.BungiiDetailsPage;
import com.bungii.ios.pages.driver.BungiiRequestPage;
import com.bungii.ios.stepdefinitions.customer.SignupSteps;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DecimalFormat;

import static com.bungii.common.manager.ResultManager.error;

public class BungiiRequestSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    ActionManager action = new ActionManager();
    private BungiiRequestPage bungiiRequestPage=new BungiiRequestPage();
    @Then("\"([^\"]*)\" should be displayed on Bungii request screen")
    public void i_click_something_button_on_forgot_password_screen_on_driver_app(String option) {
        try {
            switch (option) {
                case "correct stack trip details":
                    logger.detail(SetupManager.getDriver().getPageSource());
                    String expectedPickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")),expectedPickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")),
                            expectedDropLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")),expectedDropLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")), expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO") ? "DUO" : "SOLO";
                    String pickUpLocationLine1 = action.getNameAttribute(bungiiRequestPage.TextBox_Pickup_LineOne()),pickUpLocationLine2= action.getNameAttribute(bungiiRequestPage.TextBox_Pickup_LineTwo());
                    String dropUpLocationLine1 = action.getNameAttribute(bungiiRequestPage.TextBox_Drop_LineOne()),dropUpLocationLine2 = action.getNameAttribute(bungiiRequestPage.TextBox_Drop_LineTwo());
                    testStepVerify.isTrue(pickUpLocationLine1.equals(expectedPickUpLocationLineOne) && pickUpLocationLine2.equals(expectedPickUpLocationLineTwo),

                            "Pick up address should be " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo, "Pick up address is " + pickUpLocationLine1+pickUpLocationLine2,
                            "Expected pickup address is " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo + ", but actual is" + pickUpLocationLine1+pickUpLocationLine2);
                    testStepVerify.isTrue(dropUpLocationLine1.equals(expectedDropLocationLineOne) &&  dropUpLocationLine2.equals(expectedDropLocationLineTwo),

                            "Drop address should be " + expectedDropLocationLineOne +expectedDropLocationLineTwo, "Drop address is " + dropUpLocationLine1 +dropUpLocationLine2,
                            "Expected Drop address is " + expectedDropLocationLineOne +expectedDropLocationLineTwo + ", but actual is" + dropUpLocationLine1 +dropUpLocationLine2);
                    testStepVerify.isElementEnabled(bungiiRequestPage.Text_DistanceTag(),"Distance tag should be displayed");
                    testStepVerify.isElementEnabled(bungiiRequestPage.Text_EstimatedEarningTag(),"Earning tag should be displayed");
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_ValueDistance(),(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
                    String estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
                    double flestimate=Double.valueOf(estimate.replace("$","").trim());
                    double transactionFee=(flestimate*0.029)+0.3;
                    double estimatedDriverCut=(0.7*flestimate)-transactionFee;
                    String truncValue = new DecimalFormat("#.00").format(estimatedDriverCut);
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_EstimatedEarningValue(),"$"+truncValue);
                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_Reject(),"Reject button should be displayed");
                    testStepVerify.isElementEnabled(bungiiRequestPage.Button_Accept(),"Accept button should be displayed");
                    testStepVerify.isElementTextEquals(bungiiRequestPage.Text_NavigationBar(),"STACKED BUNGII REQUEST");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
