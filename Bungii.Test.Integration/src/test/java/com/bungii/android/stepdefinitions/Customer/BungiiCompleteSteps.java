package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.BungiiCompletePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.text.DecimalFormat;

import static com.bungii.common.manager.ResultManager.error;

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
                case "correct details with promoter":
                    verifyPromoterValue();
                    verifyPromoterDiscount();
                    break;
                case "correct details":
                    verifyTripValue();
                    break;
                case "correct details for duo trip":
                    action.scrollToBottom();
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
     * Verify Static texts on Bungii Completed page
     */
    public void verifyBungiiCompletedPage(){
        testStepVerify.isElementEnabled(bungiiCompletePage.PageTitle_BungiiComplete(),"Bungii Complete Page should be displayed");
   //     testStepVerify.isElementEnabled(bungiiCompletePage.Title_RateYourDriver(),"'Rate Your driver'  should be displayed");
        String totalTime=action.getText(bungiiCompletePage.Text_BungiiTime()),totalDistance=action.getText(bungiiCompletePage.Text_Distance());
        int tripActualTime=Integer.parseInt(utility.getActualTime());
        String tripDistance =(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");

        String expectedTime="";
        if (tripActualTime>1)expectedTime=tripActualTime+ " mins";
        else expectedTime=tripActualTime+ " mins";
        testStepVerify.isEquals(totalTime,expectedTime,"Total time should contains"+tripActualTime+" minute" ,"Total time is"+totalTime);
        testStepVerify.isTrue(totalDistance.equalsIgnoreCase(tripDistance),"Total distance should contains "+tripDistance );
        //Vishal[2503]:TODO: add more
    }

    /**
     * Verify variable texts in Bungii Complete Page
     */
    public void verifyTripValue(){
        action.scrollToBottom();
        String totalTime=action.getText(bungiiCompletePage.Text_BungiiTime()).split(" ")[0],totalDistance=action.getText(bungiiCompletePage.Text_Distance()).split(" ")[0],totalCost=action.getText(bungiiCompletePage.FinalCost()).split(" ")[0];
        String promoValue=String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE"));
        String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

        Double expectedTotalCost=utility.bungiiCustomerCost(totalDistance,totalTime,promoValue,numberOfDriver);
        String truncValue = new DecimalFormat("#.00").format(expectedTotalCost);
        if(!truncValue.contains("."))truncValue=truncValue+".00";
        testStepVerify.isEquals(totalCost,"$" + String.valueOf(truncValue));

        cucumberContextManager.setScenarioContext("BUNGII_COST_CUSTOMER",totalCost);
    }
    public  void verifyDiscount(){
        action.scrollToBottom();
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
        testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Discount(),
                "$" + promoDiscountValue,"Discount value should be promo Value"+Promo,
                "Discount value is "+promoDiscountValue,"Discount value is not "+promoDiscountValue);
    }

    public void verifyPromoterValue(){
        action.scrollToBottom();
        double tripActualTime=Double.parseDouble(utility.getActualTime());

        String totalCost="";
        //	String totalTime=action.getValueAttribute(bungiiCompletePage.Text_BungiiTime()).split(" ")[0],totalDistance=action.getValueAttribute(bungiiCompletePage.Text_Distance()).split(" ")[0],totalCost=action.getValueAttribute(bungiiCompletePage.Text_FinalCost()).split(" ")[0];
        String promoValue=String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE"));
        String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
        String totalDistance=utility.getEstimateDistance();

        Double expectedTotalCost=utility.bungiiCustomerCost(totalDistance,String.valueOf(tripActualTime),"ADD",numberOfDriver);
        String truncValue = new DecimalFormat("#.00").format(expectedTotalCost);

        if(numberOfDriver.equalsIgnoreCase("DUO"))
            totalCost=action.getText(bungiiCompletePage.FinalCost()).split(" ")[0];//TO BE HANDLED FOR DUO
        else
            totalCost=action.getText(bungiiCompletePage.FinalCost()).split(" ")[0];

        testStepVerify.isEquals(totalCost,"$0.00");
        cucumberContextManager.setScenarioContext("BUNGII_COST_CUSTOMER",expectedTotalCost+"");
    }

    public  void verifyPromoterDiscount(){
        //get current geofence
        String currentGeofence=(String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get minimum cost,Mile value,Minutes value of Geofence
        double minCost =Double.parseDouble(utility.getGeofenceData(currentGeofence,"geofence.minimum.cost")),
                perMileValue=Double.parseDouble(utility.getGeofenceData(currentGeofence,"geofence.dollar.per.miles")),
                perMinutesValue=Double.parseDouble(utility.getGeofenceData(currentGeofence,"geofence.dollar.per.minutes"));

        double tripActualTime=Double.parseDouble(utility.getActualTime());
        String totalTime=action.getText(bungiiCompletePage.Text_BungiiTime()).split(" ")[0],totalDistance=action.getText(bungiiCompletePage.Text_Distance()).split(" ")[0];
        String Promo=String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE"));
        String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

        Double promoValue=0.0;
        //String distanceValueDB=utility.getEstimateDistance();

        double //distance =Double.parseDouble(distanceValueDB);
         distance = Double.parseDouble(totalDistance.replace(" miles", ""));
        double tripValue = distance *perMileValue + tripActualTime *perMinutesValue;
        if(numberOfDriver.equalsIgnoreCase("DUO"))
            tripValue=tripValue*2;

        if(Promo.contains("$"))
            promoValue=Double.valueOf(Promo.replace("-$", ""));
        else if(Promo.contains("%"))
            promoValue=Double.valueOf(tripValue*Double.parseDouble(Promo.replace("-", "").replace("%", ""))/100);

        String promoDiscountValue = new DecimalFormat("#.00").format(promoValue);

        if(promoDiscountValue.indexOf(".")==0)
            promoDiscountValue="0"+promoDiscountValue;

        cucumberContextManager.setScenarioContext("DISCOUNT_VALUE",promoDiscountValue);

        testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Discount(),"$" + promoDiscountValue,"Discount value should be promo Value"+promoDiscountValue,"Discount value is "+promoDiscountValue,"Discount value is not "+promoDiscountValue);
    }

}
