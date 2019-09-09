package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.AccountPage;
import com.bungii.android.pages.customer.BungiiCompletePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DecimalFormat;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class BungiiCompleteSteps  extends DriverBase {
    private static LogUtility logger = new LogUtility(AccountSteps.class);
    ActionManager action = new ActionManager();
    BungiiCompletePage bungiiCompletePage = new BungiiCompletePage();
    GeneralUtility utility = new GeneralUtility();
    static final double MIN_COST = 39;

    @Then("^Bungii customer should see \"([^\"]*)\" on Bungii completed page$")
    public void bungii_customer_should_see_something_on_bungii_completed_page(String identifier) throws Throwable {
        try {
            verifyBungiiCompletedPage();

            switch (identifier) {
                case "correct details with promo":
                    verifyTripValue();
                    verifyDiscount();
                    break;
                case "correct details":
                    verifyTripValue();
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
    /**
     * Verify Static texts on Estimate and Customer Cancel Completed page
     */
    public void verifyBungiiCompletedPage(){
        testStepVerify.isElementEnabled(bungiiCompletePage.PageTitle_BungiiComplete(),"Estimate and Customer Cancel Complete Page should be displayed");
   //     testStepVerify.isElementEnabled(bungiiCompletePage.Title_RateYourDriver(),"'Rate Your driver'  should be displayed");
        String totalTime=action.getText(bungiiCompletePage.Text_BungiiTime()),totalDistance=action.getText(bungiiCompletePage.Text_Distance());
        int tripActualTime=Integer.parseInt(utility.getActualTime());
        String tripDistance =(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
        String expectedTime="";
        if (tripActualTime>1)expectedTime=tripActualTime+ " minutes";
        else expectedTime=tripActualTime+ " minute";
        testStepVerify.isEquals(totalTime,expectedTime,"Total time should contains"+tripActualTime+" minute" ,"Total time is"+totalTime);
        testStepVerify.isTrue(totalDistance.equalsIgnoreCase(tripDistance),"Total distance should contains "+tripDistance );
        //Vishal[2503]:TODO: add more
    }

    /**
     * Verify variable texts in Estimate and Customer Cancel Complete Page
     */
    public void verifyTripValue(){        action.scrollToBottom();

        String totalTime=action.getText(bungiiCompletePage.Text_BungiiTime()).split(" ")[0],totalDistance=action.getText(bungiiCompletePage.Text_Distance()).split(" ")[0],totalCost=action.getText(bungiiCompletePage.FinalCost()).split(" ")[0];
        String promoValue=String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE"));
        String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));



        Double expectedTotalCost=utility.bungiiCustomerCost(totalDistance,totalTime,promoValue,numberOfDriver);
        String truncValue = new DecimalFormat("#.##").format(expectedTotalCost);
        if(!truncValue.contains("."))truncValue=truncValue+".00";
        testStepVerify.isEquals(totalCost,"$" + String.valueOf(truncValue));

        cucumberContextManager.setScenarioContext("BUNGII_COST_CUSTOMER",totalCost);
    }
    public  void verifyDiscount(){
        double tripActualTime=Double.parseDouble(utility.getActualTime());
        String totalTime=action.getText(bungiiCompletePage.Text_BungiiTime()).split(" ")[0],totalDistance=action.getText(bungiiCompletePage.Text_Distance()).split(" ")[0];
        String Promo=String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE"));
        String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

        Double promoValue=0.0;
        double distance = Double.parseDouble(totalDistance.replace(" miles", ""));

        //double tripActualTime = Double.parseDouble(totalTime);
        double tripValue = distance + tripActualTime;
        if(numberOfDriver.equalsIgnoreCase("DUO"))
            tripValue=tripValue*2;

        if(Promo.contains("$"))
            promoValue=Double.valueOf(Promo.replace("-$", ""));
        else if(Promo.contains("%"))
            promoValue=Double.valueOf(tripValue*Double.parseDouble(Promo.replace("-", "").replace("%", ""))/100);
        //if final cost with promo is less than 39, then discount is reduced
        if((tripValue-promoValue)<MIN_COST)
            promoValue=tripValue-MIN_COST;

        String promoDiscountValue = new DecimalFormat("#.##").format(promoValue);

        if(!promoDiscountValue.contains("."))promoDiscountValue=promoDiscountValue+".00";

      //  testStepVerify.isEquals(actualDiscount,"$" + promoValue);
        testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Discount(),"$" + promoDiscountValue,"Discount value should be promo Value"+Promo,"Discount value is "+promoDiscountValue,"Discount value is not "+promoDiscountValue);
    }
}
