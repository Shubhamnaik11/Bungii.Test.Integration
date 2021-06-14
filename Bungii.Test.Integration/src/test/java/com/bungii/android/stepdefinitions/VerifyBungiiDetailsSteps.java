package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.LoginPage;
import com.bungii.android.pages.customer.MyBungiisPage;
import com.bungii.android.stepdefinitions.Customer.LoginSteps;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class VerifyBungiiDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    LoginPage loginPage = new LoginPage();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    MyBungiisPage myBungiisPage = new MyBungiisPage();

    @Then("^I verify driver names and trip cost$")
    public void i_verify_driver_names_pickup_and_drop_off_address_and_trip_cost() throws Throwable {
        String expectedDriverName=(String)cucumberContextManager.getScenarioContext("DRIVER_1");
        String[] Name = expectedDriverName.split(" ");
        expectedDriverName = Name[0]+" "+Name[1].charAt(0); //Last Name initial
        String actualDriverName=action.getText(myBungiisPage.Text_FirstDriverName());

        testStepAssert.isEquals(actualDriverName,expectedDriverName,"Driver name expected is "+expectedDriverName,"Expected Driver name is displayed.",expectedDriverName+" driver name is not displayed.");

        expectedDriverName=(String)cucumberContextManager.getScenarioContext("DRIVER_2");
        if(expectedDriverName!="") {
            Name = expectedDriverName.split(" ");
            expectedDriverName = Name[0] + " " + Name[1].charAt(0); //Last Name initial
            actualDriverName = action.getText(myBungiisPage.Text_SecondDriverName());
            testStepAssert.isEquals(actualDriverName, expectedDriverName, "Driver name expected is " + expectedDriverName, "Expected Driver name is displayed.", expectedDriverName + " driver name is not displayed.");
        }
        String expectedTripCost=(String)cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
        expectedTripCost= expectedTripCost.replace("~","");
        String actualTripCost=action.getText(myBungiisPage.Text_TripCost());
        testStepAssert.isEquals(actualTripCost,expectedTripCost,"Trip cost expected is "+expectedTripCost,"Expected Trip Cost is displayed.",expectedTripCost+" is not displayed.");

    }

    @Then("^Driver names and trip cost is displayed correctly$")
    public void i__driver_names_pickup_and_drop_off_address_and_trip_cost() throws Throwable {
        String expectedDriverName=(String)cucumberContextManager.getScenarioContext("DRIVER_1");
        String[] Name = expectedDriverName.split(" ");
        expectedDriverName = Name[0]+" "+Name[1].charAt(0); //Last Name initial
        String actualDriverName=action.getText(myBungiisPage.Text_FirstDriverName());

        testStepAssert.isEquals(actualDriverName,expectedDriverName,"Driver name expected is "+expectedDriverName,"Expected Driver name is displayed.",expectedDriverName+" driver name is not displayed.");

        expectedDriverName=(String)cucumberContextManager.getScenarioContext("DRIVER_2");
        if(expectedDriverName!="") {
            Name = expectedDriverName.split(" ");
            expectedDriverName = Name[0] + " " + Name[1].charAt(0); //Last Name initial
            actualDriverName = action.getText(myBungiisPage.Text_SecondDriverName());
            testStepAssert.isEquals(actualDriverName, expectedDriverName, "Driver name expected is " + expectedDriverName, "Expected Driver name is displayed.", expectedDriverName + " driver name is not displayed.");
        }
       // String expectedTripCost=(String)cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
        String pickupref =  (String)cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String expectedTripCost = dbUtility.getFinalBungiiCost(pickupref);
        expectedTripCost= expectedTripCost.replace("~","");
        String actualTripCost=action.getText(myBungiisPage.Text_TripCost());
        testStepAssert.isEquals(actualTripCost,expectedTripCost,"Trip cost expected is "+expectedTripCost,"Expected Trip Cost is displayed.",expectedTripCost+" is not displayed.");

    }
    @Then("^I verify the field \"([^\"]*)\"$")
    public void i_verify_the_field_something(String option) throws Throwable {
        try{
            switch (option){
                case "driver name":
                    String expectedDriverName=(String)cucumberContextManager.getScenarioContext("DRIVER1NAME");
                    expectedDriverName= expectedDriverName.replace(".","");
                    String actualDriverName=action.getText(myBungiisPage.Text_FirstDriverName());
                    testStepAssert.isEquals(actualDriverName,expectedDriverName,"Driver name expected is "+expectedDriverName,"Expected Driver name is displayed.",expectedDriverName+" driver name is not displayed.");
                    break;
                case "pickup address":
                    String expectedpickuplocation1=(String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1");
                    String expectedpickuplocation2=(String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2");
                    String actualpickuplocation1=action.getText(myBungiisPage.Text_PickUp_Location1());
                    String actualpickuplocation2=action.getText(myBungiisPage.Text_PickUp_Location2());
                    if(expectedpickuplocation1.equalsIgnoreCase(actualpickuplocation1) && expectedpickuplocation2.equalsIgnoreCase(actualpickuplocation2))
                    {
                        testStepAssert.isTrue(true,"Pickup Address is correct.", "Pickup Address does not match.");
                    }
                    else
                    {
                        testStepAssert.isFail("Pickup Address does not match.");
                    }
                    break;
                case "dropoff address":
                    String expecteddropofflocation1=(String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1");
                    String expecteddropofflocation2=(String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2");
                    String actualdropofflocation1=action.getText(myBungiisPage.Text_DropOff_Location1());
                    String actualdropofflocation2=action.getText(myBungiisPage.Text_DropOff_Location2());
                    if(expecteddropofflocation1.equalsIgnoreCase(actualdropofflocation1) && expecteddropofflocation2.equalsIgnoreCase(actualdropofflocation2))
                    {
                        testStepAssert.isTrue(true,"DropOff Address is correct.", "DropOff Address does not match.");
                    }
                    else
                    {
                        testStepAssert.isFail("DropOff Address does not match.");
                    }
                    break;
                case "trip cost":
                    String cost = (String)cucumberContextManager.getScenarioContext("ACTUAL_COST");
                    String expectedTripCost = new DecimalFormat("0.00").format(Double.parseDouble(cost)).toString();
                    expectedTripCost="$"+expectedTripCost;
                    String actualTripCost=action.getText(myBungiisPage.Text_TripCost());
                    testStepAssert.isEquals(actualTripCost,expectedTripCost,"Trip cost expected is "+expectedTripCost,"Expected Trip Cost is displayed. ",expectedTripCost+" is not displayed.");
                    break;
                case "timezone":
                    String expectedBungiiTime=(String)cucumberContextManager.getScenarioContext("BUNGII_TIME");
                     expectedBungiiTime=expectedBungiiTime.replace(" GMT+5:30","");

                    if(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()).inDaylightTime( new Date() ))
                        expectedBungiiTime = expectedBungiiTime.replace("ST","DT");
                    String actualBungiiTime=action.getText(myBungiisPage.Text_TripScheduledDate());
                    testStepAssert.isEquals(actualBungiiTime,expectedBungiiTime,"Bungii time expected is "+expectedBungiiTime,"Expected Bungii Time is displayed.",expectedBungiiTime+" is not displayed.");
                    break;
                default:
                    error("UnImplemented Step or incorrect option.", "UnImplemented Step");
                    break;
            }

        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in verifying details under Past Bungiis", true);
        }

    }
    @Then("^correct date of the trip is displayed as per the timezone of the geofence$")
    public void correct_date_of_the_trip_is_displayed_as_per_the_timezone_of_the_geofence() throws Throwable {
        String expectedBungiiTime=(String)cucumberContextManager.getScenarioContext("BUNGII_TIME");
        expectedBungiiTime=expectedBungiiTime.replace("GMT+5:30","GMT+05:30");

        if(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()).inDaylightTime( new Date() ))
            expectedBungiiTime = expectedBungiiTime.replace("ST","DT");
        int year = new DateTime().getYear();
        expectedBungiiTime = expectedBungiiTime.substring(0,7)+expectedBungiiTime.substring(16,expectedBungiiTime.length());
        String actualBungiiTime =action.getText(myBungiisPage.Text_TripScheduledDate()).replace(" "+String.valueOf(year),"");
        testStepAssert.isTrue(expectedBungiiTime.contains(actualBungiiTime),"Bungii time expected is "+expectedBungiiTime,"Expected Bungii Time is displayed.",actualBungiiTime+" is displayed instead of "+expectedBungiiTime);
    }

    @When("^I view last completed bungii$")
    public void i_view_last_completed_bungii() throws Throwable {
        action.click(myBungiisPage.Text_DeliveryDate());
    }
    @And("^I open first trip in past trips$")
    public void i_view_last_completed_bungii_trip() throws Throwable {
        try{

            List<WebElement> selectDriver;
            selectDriver= SetupManager.getDriver().findElements(By.xpath("//android.widget.ImageView[@resource-id='com.bungii.customer:id/item_my_bungii_iv_arrow'][1]"));
            action.click(selectDriver.get(0));
            log("I open first trip from Past Bungiis ","I opened first trip from Past Bungiis ",true);
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            //logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Trip is not displayed in Past Trips", true);
        }
    }

    @And("^I open the trip for \"([^\"]*)\" driver$")
    public void i_open_the_trip_for_something_driver(String driverName) throws Throwable {
        try{

            WebElement selectDriver;
            String[] Name = driverName.split(" ");
            driverName = Name[0]+" "+Name[1].charAt(0)+"."; //Last Name initial
             Thread.sleep(5000);
            selectDriver= SetupManager.getDriver().findElement(By.xpath("//*[contains(@text, '"+driverName+"')]/following::android.widget.ImageView[@resource-id='com.bungii.customer:id/item_my_bungii_iv_arrow'][1]"));
            action.click(selectDriver);
            log("I open trip from Past Bungiis ","I opened trip of "+driverName+" from Past Bungiis ",true);

            cucumberContextManager.setScenarioContext("DRIVER1NAME",driverName);
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            //logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Trip is not displayed in Past Trips", true);
        }
    }
}

