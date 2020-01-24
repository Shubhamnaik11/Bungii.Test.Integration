package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.AvailableTripsPage;
import com.bungii.android.pages.driver.BungiiRequest;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.common.manager.ResultManager.*;

public class AvailableTripsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(AvailableTripsSteps.class);
    AvailableTripsPage availableTrips = new AvailableTripsPage();
    ActionManager action = new ActionManager();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    GeneralUtility utility= new GeneralUtility();
    @And("I Select Trip from driver available trip")
    public void iSelectTripFromDriverAvailableTrip() {
        try {

            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String numberOfDriver = (String) cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");
            //   customerName="Vishal Bagi";numberOfDriver="SOLO";
            boolean isSelected = selectBungiiFromList(numberOfDriver, customerName.substring(0, customerName.indexOf(" ") + 2));
            if(!isSelected){

                if (action.isNotificationAlertDisplayed()) {
                    if (action.getText(Page_BungiiRequest.Alert_Msg()).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))) {
                        utility.acceptNotificationAlert();

                    } else {
                        //  action.click(Page_BungiiRequest.Button_Reject());
                        action.click(Page_BungiiRequest.AlertButton_Cancel());
                    }

                }
            }
            isSelected = selectBungiiFromList(numberOfDriver, customerName.substring(0, customerName.indexOf(" ") + 2));
            log("I Select Trip from driver available trip","I Select Trip from driver available trip");
          //  testStepVerify.isTrue(isSelected, "I should able to select trip from available trip", "I was not able find available trip for customer " + customerName + " Estimate and Customer Cancel type " + numberOfDriver);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I should able to see \"([^\"]*)\" available trip$")
    public void i_should_able_to_see_something_available_trip(String strArg1) throws Throwable {
        try {
            List<WebElement> listOfBungii=availableTrips.List_AvailableBungiis();
            switch (strArg1) {
                case "two":
                    testStepVerify.isTrue(listOfBungii.size()==2,"There should be two available trip");
                    break;
                case "zero":
                    testStepVerify.isTrue(listOfBungii.size()==0,"There should be two available trip");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }	}
    public boolean selectBungiiFromList(String bungiiType, String customerName) {
        boolean isSelected = false;

        try {
            //  action.scrollToBottom();
            //TODO: check diff between solo and duo on screen
            String expectedInstance = "2";
            if (bungiiType.toUpperCase().equals("SOLO")) {
                expectedInstance = "2";
            } else {
                expectedInstance = "4";

            }

            //List_AvailableBungiis
            List<WebElement> elements = availableTrips.List_AvailableBungiis();
            for (WebElement element : elements) {
                MobileElement image = element.findElement(By.id("com.bungii.driver:id/row_available_pickup_imageview_type"));
                WebElement actualCustomer = element.findElement(By.id("com.bungii.driver:id/row_available_pickup_drivername"));
                String actualCustomerName = actualCustomer.getText();
                System.out.println(SetupManager.getDriver().getPageSource());
                //      String  instance =image.getAttribute("instance");

                if (actualCustomerName.equals(customerName)) {
                    element.findElement(By.id("com.bungii.driver:id/row_available_pickup_imageview_arrow")).click();
                    isSelected = true;
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        //    error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
        return isSelected;

    }
    @Then("^I should be navigated to Available Trip screen on driver app$")
    public void i_should_be_navigated_to_something_screen_on_driver_app() throws Throwable {
        try {
            String getNaviagationText = action.getText(availableTrips.NavigationBar_Text());
            testStepVerify.isEquals(PropertyUtility.getMessage("driver.navigation.available.trips"), getNaviagationText, "I should be navigated to Available Trip page", "I am not navigated to Available Trip, Title is" + getNaviagationText);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I Select Trip from available trip$")
    public void i_select_trip_from_available_trip() throws Throwable {
        try{
            Thread.sleep(6000);
        action.click(availableTrips.Row_AvailableTrip());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

}

