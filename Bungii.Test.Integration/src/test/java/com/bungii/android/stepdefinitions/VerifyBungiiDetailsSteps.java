package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.LoginPage;
import com.bungii.android.pages.customer.MyBungiisPage;
import com.bungii.android.stepdefinitions.Customer.LoginSteps;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;

public class VerifyBungiiDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    LoginPage loginPage = new LoginPage();
    GeneralUtility utility = new GeneralUtility();
    MyBungiisPage myBungiisPage = new MyBungiisPage();

    @Then("^I verify the field \"([^\"]*)\"$")
    public void i_verify_the_field_something(String option) throws Throwable {
        try{
            switch (option){
                case "driver name":
                    String expectedDriverName=(String)cucumberContextManager.getScenarioContext("DRIVER1NAME");
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
                    String actualdropofflocation1=action.getText(myBungiisPage.Text_PickUp_Location1());
                    String actualdropofflocation2=action.getText(myBungiisPage.Text_PickUp_Location2());
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
                    String expectedTripCost=(String)cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
                    String actualTripCost=action.getText(myBungiisPage.Text_TripCost());
                    testStepAssert.isEquals(actualTripCost,expectedTripCost,"Trip cost expected is "+expectedTripCost,"Expected Trip Cost is displayed.",expectedTripCost+" is not displayed.");
                    break;
                case "timezone":
                    String expectedBungiiTime=(String)cucumberContextManager.getScenarioContext("BUNGII_TIME");
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
            logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @And("^I open the trip for \"([^\"]*)\" driver$")
    public void i_open_the_trip_for_something_driver(String driverName) throws Throwable {
        try{
            WebElement selectDriver;
            selectDriver= SetupManager.getDriver().findElement(By.xpath("//*[contains(@text, '"+driverName+"')]/following::android.widget.ImageView[@resource-id='com.bungii.customer:id/item_my_bungii_iv_arrow']"));
            action.click(selectDriver);
            cucumberContextManager.setScenarioContext("DRIVER1NAME",driverName);
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
