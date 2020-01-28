package com.bungii.android.stepdefinitions.Customer;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.BungiiCompletePage;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

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
            switch (identifier) {
                case "correct details with promo":
                    verifyBungiiCompletedPage();
                    verifyTripValue();
                    verifyDiscount();
                    break;
                case "correct details with promoter":
                    verifyPromoterValue();
                    verifyPromoterDiscount();
                    break;
                case "correct details":
                    verifyBungiiCompletedPage();
                    verifyTripValue();
                    break;
                case "correct details for duo trip":
                    action.scrollToBottom();
                    verifyTripValue();
                break;
                case "correct rating detail for duo":
                    testStepVerify.isElementTextEquals(bungiiCompletePage.Text_DriverName1(), (String) cucumberContextManager.getScenarioContext("DRIVER_1"));
                    testStepVerify.isElementTextEquals(bungiiCompletePage.Text_DriverName2(), (String) cucumberContextManager.getScenarioContext("DRIVER_2"));
                    testStepVerify.isElementDisplayed(bungiiCompletePage.RatingBar1(),"Rating bar should be displayed", "Rating bar is displayed", "Rating bar is not displayed");
                    testStepVerify.isElementDisplayed(bungiiCompletePage.RatingBar2(),"Rating bar should be displayed", "Rating bar is displayed", "Rating bar is not displayed");
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

    @When("^I select \"([^\"]*)\" Ratting star for duo \"([^\"]*)\"$")
    public void i_select_something_ratting_star_for_duo_something(String strArg1, String strArg2) throws Throwable {
        try {
            Thread.sleep(60000);
            switch (strArg2) {
                case "Driver 1":
                    setStarRatingbar(strArg1, strArg2);
                    break;
                case "Driver 2":
                    setStarRatingbar(strArg1, strArg2);
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

    @And("^I tap on \"([^\"]*)\" on Bungii Complete$")
    public void i_tap_on_something_on_bungii_complete(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "OK":
                    action.click(bungiiCompletePage.Button_OK());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            log(" I tap on " + strArg1 + " on Bungii estimate",
                    "I  Selected " + strArg1, true);

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    /**
     * Verify Static texts on Bungii Completed page
     */
    public void verifyBungiiCompletedPage(){
        action.scrollToBottom();
        testStepVerify.isElementEnabled(bungiiCompletePage.PageTitle_BungiiComplete(),"Bungii Complete Page should be displayed");
   //     testStepVerify.isElementEnabled(bungiiCompletePage.Title_RateYourDriver(),"'Rate Your driver'  should be displayed");
        action.scrollToBottom();
        String totalTime=action.getText(bungiiCompletePage.Text_BungiiTime()),totalDistance=action.getText(bungiiCompletePage.Text_Distance());
        int tripActualTime=Integer.parseInt(utility.getActualTime());
        String tripDistance =(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");

        String expectedTime="";
        if (tripActualTime>1)expectedTime=tripActualTime+ " mins";
        else expectedTime=tripActualTime+ " min";
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


    public void setStarRatingbar(String rating, String driverCount){
        AndroidDriver driver = (AndroidDriver) SetupManager.getDriver();
        int startX, endX = 0, yAxis = 0, tapAt = 0;
        WebElement StarRatingbar;

        if(driverCount.equals("Driver 1")) {
            StarRatingbar = bungiiCompletePage.RatingBar1();
            //Get start point of threeStarRatingbar.
            startX = StarRatingbar.getLocation().getX();
            //Get end point of threeStarRatingbar.
            endX = StarRatingbar.getSize().getWidth();
            //Get vertical location of threeStarRatingbar.
            yAxis = StarRatingbar.getLocation().getY();
        }
        else if(driverCount.equals("Driver 2")){
            StarRatingbar = bungiiCompletePage.RatingBar2();
            //Get start point of threeStarRatingbar.
            startX = StarRatingbar.getLocation().getX();
            //Get end point of threeStarRatingbar.
            endX = StarRatingbar.getSize().getWidth();
            //Get vertical location of threeStarRatingbar.
            yAxis = StarRatingbar.getLocation().getY();
        }

        //Set threeStarRatingbar tap position to set Rating = 1 star.
        //You can use endX * 0.3 for 1 star, endX * 0.6 for 2 star, endX * 1 for 3 star.
        if(rating.equals("1")) {
            tapAt = (int) (endX * 0.3);
        }
        else if(rating.equals("2")) {
            tapAt = (int) (endX * 0.6);
        }
        else if(rating.equals("3")) {
            tapAt = (int) (endX * 0.9);
        }
        else if(rating.equals("4")) {
            tapAt = (int) (endX * 1.2);
        }
        else if(rating.equals("5")) {
            tapAt = (int) (endX * 1.5);
        }
        //Set threeStarRatingbar to Rating = 1.0 using TouchAction class.
        TouchAction act=new TouchAction(driver);
        act.press(PointOption.point(tapAt, yAxis)).release().perform();
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
