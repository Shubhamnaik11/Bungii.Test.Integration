package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.*;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.android.pages.driver.AvailableTripsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.utilityfunctions.DbUtility;
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
    DriverHomePage driverHomePage = new DriverHomePage();

    @And("I Select Trip from driver available trip")
    public void iSelectTripFromDriverAvailableTrip() {
        try {
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String numberOfDriver = (String) cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");
            //   customerName="Vishal Bagi";numberOfDriver="SOLO";
            boolean isSelected = selectBungiiFromList(numberOfDriver, customerName.substring(0, customerName.indexOf(" ") + 2));
            if (!isSelected) {

                if (action.isNotificationAlertDisplayed()) {
                    if (action.getText(Page_BungiiRequest.Alert_Msg(true)).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))) {
                        utility.acceptNotificationAlert();

                    } else {
                        //  action.click(Page_BungiiRequest.Button_Reject());
                        action.click(Page_BungiiRequest.AlertButton_Cancel());
                        isSelected = selectBungiiFromList(numberOfDriver, customerName.substring(0, customerName.indexOf(" ") + 2));
                    }

                }
            }

            log("I Select Trip from driver available trip", "I Select Trip from driver available trip");
            //  testStepVerify.isTrue(isSelected, "I should able to select trip from available trip", "I was not able find available trip for customer " + customerName + " Estimate and Customer Cancel type " + numberOfDriver);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
   /* @Then("^I should able to see \"([^\"]*)\" available trip$")
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
        }	}*/
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
            if (action.isElementPresent(driverHomePage.Alert_NewBungii(true))) {
                action.click(driverHomePage.Notification_AlertReject());

            }
            //List_AvailableBungiis
            List<WebElement> elements = availableTrips.List_AvailableBungiis();
            if (elements.size() == 0) {
                fail("Trip should be displayed in available bungii list of driver",
                        "Trip is not displayed in available bungii list of driver", true);
            } else if (elements.size() == 1) {
                logger.detail("Only One Available Bungii List Is Available. : " + elements.get(0).getText());
                for (WebElement element : elements) {
                    element.findElement(By.id("com.bungii.driver:id/row_available_pickup_imageview_arrow")).click();
                    isSelected = true;
                }
            } else
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

    @Then("^I should be navigated to Available Nungiis screen on driver app$")
    public void i_should_be_navigated_to_something_screen_on_driver_app() throws Throwable {
        try {
            String getNaviagationText = action.getText(availableTrips.NavigationBar_Text());
            testStepVerify.isEquals(PropertyUtility.getMessage("driver.navigation.available.trips"), getNaviagationText, "I should be navigated to Available Trip page", "I am not navigated to Available Trip, Title is" + getNaviagationText);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I Select Trip from available trip$")
    public void i_select_trip_from_available_trip() throws Throwable {
        try {
            Thread.sleep(6000);
            action.click(availableTrips.Row_AvailableTrip());
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Trips are not listed in Available Bungiis of Driver", true);
        }
    }

    @And("^I Select second Trip from available trip$")
    public void i_select_second_trip_from_available_trip() throws Throwable {
        try {
            Thread.sleep(6000);
            action.click(availableTrips.Row_SecondAvailable());
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Trips are not listed in Available Bungiis of Driver", true);
        }
    }

    @Then("^Partner Portal name should be displayed in \"([^\"]*)\" section$")
    public void partner_portal_name_should_be_displayed_in_something_section(String Screen) throws Throwable {
        try {
            String partnerNameExpected = (String) cucumberContextManager.getScenarioContext("Partner_Portal_Name");

            switch (Screen) {
                case "AVAILABLE BUNGIIS":
                case "SCHEDULED BUNGIIS":
                    String partnerName = action.getText(availableTrips.Partner_Name());
                    testStepAssert.isEquals(partnerName, partnerNameExpected, "Partner Portal name should be displayed on " + Screen + " screen", "Partner Portal name is displayed in " + Screen + " screen", "Partner Portal name is not displayed in " + Screen + " screen");
                    break;
                case "EN ROUTE":
                case "ARRIVED":
                case "LOADING ITEM":
                    String partnerNameText = action.getText(availableTrips.Partner_Name_For_Enroute());
                    testStepAssert.isEquals(partnerNameText, partnerNameExpected, "Partner Portal name should be displayed on " + Screen + " screen", "Partner Portal name is displayed in " + Screen + " screen", "Partner Portal name is not displayed in " + Screen + " screen");
                    break;
                case "DRIVING TO DROP OFF":
                case "UNLOADING ITEM":
                    break;
                default:
                    log("Correct screen", "Wrong screen", true);
                    break;
            }
        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Partner Portal name is not displayed on " + Screen,
                    true);
        }
    }
    @And("^Driver status should be \"([^\"]*)\"$")
    public void driver_status_should_be_something(String strArg1) throws Throwable {
        try {
            String expectedDriverOnlineStatus ="1";
            String phoneNumber= (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
            String driverOnlineStatus = com.bungii.web.utilityfunctions.DbUtility.getDriverStatus(phoneNumber);
            testStepAssert.isEquals(driverOnlineStatus,expectedDriverOnlineStatus,"Driver status should be online","Driver Status is online","Driver status is not online");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" popup displayed$")
    public void i_should_see_something_popup_displayed(String expectedPopupText) throws Throwable {
        try {
            Thread.sleep(4000);
            String popupText = action.getText(Page_BungiiRequest.Alert_NewBungiiRequest(true));
            boolean popupDisplayed = Page_BungiiRequest.Alert_NewBungiiRequest(true).isDisplayed();
            testStepAssert.isTrue(popupDisplayed, "Stack trip request should be displayed", "Stack trip request is  displayed", "Stack trip request is not displayed");
            testStepAssert.isEquals(popupText, expectedPopupText, "Stack trip request should be present", "Stack trip request is present", "Stack trip request is not present");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
}

