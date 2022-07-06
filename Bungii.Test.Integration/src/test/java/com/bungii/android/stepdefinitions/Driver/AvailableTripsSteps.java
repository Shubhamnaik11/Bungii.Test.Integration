package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.enums.Rejection_Reason;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.*;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.android.pages.driver.AvailableTripsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;

public class AvailableTripsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(AvailableTripsSteps.class);
    AvailableTripsPage availableTrips = new AvailableTripsPage();
    ActionManager action = new ActionManager();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    GeneralUtility utility= new GeneralUtility();
    DriverHomePage driverHomePage = new DriverHomePage();
    DbUtility dbUtility = new DbUtility();

    @And("I Select Trip from driver available trip")
    public void iSelectTripFromDriverAvailableTrip() {
        try {
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String numberOfDriver = (String) cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");
            //   customerName="Vishal Bagi";numberOfDriver="SOLO";
            boolean isSelected = selectBungiiFromList(numberOfDriver, customerName.substring(0, customerName.indexOf(" ") + 2));
            if(!isSelected){

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

            log("I Select Trip from driver available trip","I Select Trip from driver available trip");
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
            if(action.isElementPresent(driverHomePage.Alert_NewBungii(true)))
            {
                action.click(driverHomePage.Notification_AlertReject());

            }
            //List_AvailableBungiis
            List<WebElement> elements = availableTrips.List_AvailableBungiis();
            if(elements.size()==0)
            {
                fail("Trip should be displayed in available bungii list of driver",
                        "Trip is not displayed in available bungii list of driver", true);
            }
            else if (elements.size()==1)
            {
                logger.detail("Only One Available Bungii List Is Available. : "+ elements.get(0).getText());
                for (WebElement element : elements) {
                element.findElement(By.id("com.bungii.driver:id/row_available_pickup_imageview_arrow")).click();
                isSelected = true;
                }
            }
            else
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
            String expectedText = action.getText(availableTrips.Text_FromHomeMiles());
            boolean textDisplayed = expectedText.contains("miles");
            testStepAssert.isTrue(textDisplayed,"Text should be updated to miles","Text is updated to miles","Text is not updated to miles");
        action.click(availableTrips.Row_AvailableTrip());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Trips are not listed in Available Bungiis of Driver", true);
        }
    }
    @And("^I Select second Trip from available trip$")
    public void i_select_second_trip_from_available_trip() throws Throwable {
        try{
            Thread.sleep(6000);

            action.click(availableTrips.Row_SecondAvailable());
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Trips are not listed in Available Bungiis of Driver", true);
        }
    }

    @Then("^Partner Portal name should be displayed in \"([^\"]*)\" section$")
    public void partner_portal_name_should_be_displayed_in_something_section(String Screen) throws Throwable {
        try {
            String partnerNameExpected = (String) cucumberContextManager.getScenarioContext("Partner_Portal_Name");
            String customerNameFull= (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            String [] customerName = customerNameFull.split(" ");
            String expectedCustomerName =customerName[0];


            switch (Screen) {
                case "AVAILABLE BUNGIIS":
                case "SCHEDULED BUNGIIS":
                    String partnerName = action.getText(availableTrips.Partner_Name());
                    testStepAssert.isEquals(partnerName, partnerNameExpected, "Partner Portal name should be displayed on " + Screen + " screen", "Partner Portal name is displayed in " + Screen + " screen", "Partner Portal name is not displayed in " + Screen + " screen");
                    break;
                case "EN ROUTE":
                case "ARRIVED":
                case "LOADING ITEMS":
                    String partnerNameText = action.getText(availableTrips.Partner_Name_For_Enroute());
                    testStepAssert.isEquals(partnerNameText, partnerNameExpected, "Partner Portal name should be displayed on " + Screen + " screen", "Partner Portal name is displayed in " + Screen + " screen", "Partner Portal name is not displayed in " + Screen + " screen");
                    break;
                case "DRIVING TO DROP-OFF":
                case "UNLOADING ITEMS":
                    String[] customerNameText = action.getText(availableTrips.Partner_Name_For_Enroute()).split(" ");
                    String customerNameproper = customerNameText[0];
                    testStepAssert.isEquals(customerNameproper, expectedCustomerName, "Customer  name should be displayed on " + Screen + " screen", "Customer name is displayed in " + Screen + " screen", "Customer name is not displayed in " + Screen + " screen");
                    break;
                default:
                    log("Correct screen", "Wrong screen", true);
                    break;
            }
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Partner Portal name is not displayed on "+Screen,
                    true);
        }
    }
    @And("^I click on the back button and verify the rejection popup$")
    public void i_click_on_the_back_button_and_verify_the_rejection_popup() throws Throwable {
      try{
            Thread.sleep(1000);
            action.click(availableTrips.Button_Back());
            Thread.sleep(12000);

            testStepAssert.isElementDisplayed(availableTrips.Text_RejectionPopup(),"Rejection Reason pop-up must be displayed","Rejection Reason pop-up is displayed","Rejection Reason pop-up is not displayed");
      }
      catch (Exception ex){
          logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
          error("Step should be successful", "I cannot click on back button",
                  true);
      }
    }
    @Then("^I check if \"([^\"]*)\" customer trip that is rejected is displayed$")
    public void i_check_if_something_customer_trip_that_is_rejected_is_displayed(String custName) throws Throwable {
       try{
           String customerName =custName.substring(0,29);

           if (action.isElementPresent(availableTrips.Text_CustomerName(true))){
               String actualName=availableTrips.Text_CustomerName().getText();
               if(actualName==customerName)
               {
                   testStepAssert.isTrue(false,"Customer trip should not be present","Customer trip is present");
               }
           }
           else if(action.isElementPresent(availableTrips.Text_NoBungiisAvailable(true)))
           {
               testStepAssert.isTrue(true,"Customer trip should not be present","Customer trip is present");
           }
           else {
               testStepAssert.isTrue(false,"Customer trip should not be present","Customer trip is present");

           }
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful",
                   "Error performing step,Please check logs for more details", true);
       }
    }
    @And("^I click on the back button and verify that rejection popup is absent$")
    public void i_click_on_the_back_button_and_verify_that_rejection_popup_is_absent() throws Throwable {
        try{
            action.click(availableTrips.Button_Back());
            Thread.sleep(2000);

            testStepAssert.isFalse(action.isElementPresent(availableTrips.Text_RejectionPopup(true)),"Rejection Reason pop-up must not be displayed","Rejection Reason pop-up is not displayed", "Rejection Reason pop-up is displayed");

        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "I cannot click on back button",
                    true);
        }
    }
    @And("^I check if all reasons are displayed on rejection popup$")
    public void i_check_if_all_reasons_are_displayed_on_rejection_popup() throws Throwable {
       try{
           List<String> expectedOptions = new ArrayList() {{
              Rejection_Reason.TOO_FAR_AWAY.toString();
              Rejection_Reason.EARNINGS.toString();
              Rejection_Reason.LABOR_REQUIREMENTS.toString();
              Rejection_Reason.TYPE_OF_ITEM.toString();
              Rejection_Reason.NOT_ENOUGH_INFORMATION.toString();
              Rejection_Reason.NOT_AVAILABLE.toString();
           }};
          for (int j =0;j<expectedOptions.size();j++){
             String expectedReason= expectedOptions.get(j);
             String actualReason = availableTrips.Text_RejectionReason(j+1).getAttribute("text");
             testStepAssert.isEquals(actualReason,expectedReason,"The actual and expected reasons should be same","The actual and expected reasons are the same","The actual and expected reasons are not the same");
          }

       }
       catch (Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step Should be successful", "Error in viewing result set",
                   true);
       }
    }
    @And("^I click on \"([^\"]*)\" button on rejection popup$")
    public void i_click_on_something_button_on_rejection_popup(String button) throws Throwable {
        try {
            switch (button){
                case "CANCEL":
                    action.click(availableTrips.Button_Cancel());
                    break;
                case "SUBMIT":
                    action.click(availableTrips.Button_Submit());
                    break;
            }
            log("I should be able to click on "+button+" button",
                    "I am able to click on "+button+" button",
                    false);
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "I cannot click on "+button+" button",
                    true);
        }
    }
    @Then("^I check if the reason is saved in db$")
    public void i_check_if_the_reason_is_saved_in_db() throws Throwable {
        try{
            String driverNumber = (String) cucumberContextManager.getScenarioContext("DRIVER_PHONE_NUMBER");
            String reason = dbUtility.checkRejectionReason(driverNumber);
            if(!(reason.isEmpty()))
            {
                testStepAssert.isTrue(true,"The rejection reason is saved in db","The rejection reason is not saved in db");
            }
            else{
                testStepAssert.isTrue(false,"The rejection reason is saved in db","The rejection reason is not saved in db");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I verify the rejection popup is displayed$")
    public void i_verify_the_rejection_popup_is_displayed() throws Throwable {
        try{
            testStepAssert.isElementDisplayed(availableTrips.Text_RejectionPopup(),"Rejection Reason pop-up must be displayed","Rejection Reason pop-up is displayed","Rejection Reason pop-up is not displayed");
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I check that latest reason is saved when \"([^\"]*)\" button is clicked$")
    public void i_check_that_latest_reason_is_saved_when_something_button_is_clicked(String strArg1) throws Throwable {
        try {
                Thread.sleep(2000);
                Boolean checkedRadioButton= Boolean.valueOf(availableTrips.RadioButton_LatestRejectionReason().getAttribute("checked"));
                Thread.sleep(2000);
                testStepAssert.isTrue(checkedRadioButton,"The latest reason is selected","The latest reason is selected","The latest reason is not selected");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Latest reason is not saved", true);
        }
    }

}

