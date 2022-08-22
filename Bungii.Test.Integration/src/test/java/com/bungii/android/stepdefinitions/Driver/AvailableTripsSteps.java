package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.enums.Rejection_Reason;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.ScheduledTripsPage;
import com.bungii.android.pages.driver.*;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.android.pages.driver.AvailableTripsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.appium.java_client.MobileElement;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;

public class AvailableTripsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(AvailableTripsSteps.class);
    AvailableTripsPage availableTrips = new AvailableTripsPage();
    ActionManager action = new ActionManager();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    GeneralUtility utility= new GeneralUtility();
    DriverHomePage driverHomePage = new DriverHomePage();
    ScheduledTripsPage scheduledTripsPage = new ScheduledTripsPage();
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
            boolean textDisplayed = (expectedText.contains("miles") || expectedText.contains("mile") )? true : false;

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
    @And("^I check if variable sign is shown under \"([^\"]*)\"$")
    public void i_check_if_variable_sign_is_shown_under_something(String page) throws Throwable {
      try{
          switch (page){
              case "available bungii details":
                  Thread.sleep(2000);
                String driverEarnings = availableTrips.Text_DriverEarning().getText();
                testStepAssert.isTrue(driverEarnings.contains("~"),
                        "The variable sign (~) should be present",
                        "The variable sign (~) is not present");
                  break;
              case "schedule bungii details":
                  Thread.sleep(2000);
                  String driverEarningsSchedulePage = availableTrips.Text_DriverEarningSchedulePage().getText();
                  testStepAssert.isTrue(driverEarningsSchedulePage.contains("~"),
                          "The variable sign (~) should be present",
                          "The variable sign (~) is not present");
                  break;
          }
          log("I should be able to check the variable sign","I was able to check the variable sign",false);
      }
      catch (Exception e) {
          logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
          error("Step  Should be successful",
                  "Error performing step,Please check logs for more details", true);
      }
    }
    @And("^I check if variable sign is not shown under \"([^\"]*)\"$")
    public void i_check_if_variable_sign_is_not_shown_under_something(String page) throws Throwable {
        try{
            switch (page){
                case "available bungii details":
                    Thread.sleep(2000);
                    String driverEarnings = availableTrips.Text_DriverEarning().getText();
                    testStepAssert.isFalse(driverEarnings.contains("~"),
                            "The variable sign (~) should not be present",
                            "The variable sign (~) is present");
                    break;
                case "schedule bungii details":
                    Thread.sleep(2000);
                    String driverEarningsSchedulePage = availableTrips.Text_DriverEarningSchedulePage().getText();
                    testStepAssert.isFalse(driverEarningsSchedulePage.contains("~"),
                            "The variable sign (~) should not be present",
                            "The variable sign (~) is present");
                    break;
            }
            log("I should be able to check if the variable sign is absent","I was able to check if the variable sign is absent",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I click on the back button and verify the rejection popup$")
    public void i_click_on_the_back_button_and_verify_the_rejection_popup() throws Throwable {
      try{
            Thread.sleep(1000);
            action.click(availableTrips.Button_Back());
            Thread.sleep(2000);

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
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful",
                   "Error performing step,Please check logs for more details", true);
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
            String driverNumber = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
            String reason = dbUtility.checkRejectionReason(driverNumber);
            if(!(reason.isEmpty()))
            {
                testStepAssert.isTrue(true,"The rejection reason is saved in db","The rejection reason is not saved in db");
            }
            else{
                testStepAssert.isTrue(false,"The rejection reason should be saved in db","The rejection reason is not saved in db");
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
    @Then("^I check the notification for \"([^\"]*)\"$")
    public void i_check_the_notification_for_something(String strArg1) throws Throwable {
        try{
            String expectedMessage="";
            action.showNotifications();

            String scheduleDate = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
            String date=scheduleDate.substring(0,6);
            String checkZero=date.substring(4,5);
            if (checkZero.equalsIgnoreCase("0"))
            {
                date= date.replace("0","");
            }
            String time=scheduleDate.substring(8,16);
            Date d=new Date();
            String year= String.valueOf(d).substring(24);
            String message = PropertyUtility.getMessage("biglots.partner.cancel");
            String subMessage = PropertyUtility.getMessage("partner.cancel.message");
            expectedMessage = message+" "+date+", "+year+" at "+time+". "+subMessage;
            cucumberContextManager.setScenarioContext("EXPECTED_MESSAGE",expectedMessage);

            log("Checking notifications","Checking notifications",true);

            boolean isFound = utility.clickOnNofitication("Bungii", expectedMessage);
            if (!isFound) {
                Thread.sleep(80000);
                isFound = utility.clickOnNofitication("Bungii", expectedMessage);
            }
            //if no notificatiaon then hide
            if (!isFound) {
                action.hideNotifications();
            }
            testStepAssert.isTrue(isFound, "I should be able to click on notification for " + strArg1, "I clicked on notification for " + strArg1 + " with message " + expectedMessage, "PUSH NOTIFICATION NOT RECEIVED : " + expectedMessage + " message");
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
   }
    @And("^I check \"([^\"]*)\" details are displayed on \"([^\"]*)\" page$")
    public void i_check_something_details_are_displayed_on_something_page(String pallet, String page) throws Throwable {
        try{
            String palletOneWeight= PropertyUtility.getDataProperties("partner.washingtondc.weight.item.one");
            String palletOneDimensions= PropertyUtility.getDataProperties("partner.washingtondc.dimensions.item.one");
            String palletOneName= PropertyUtility.getDataProperties("partner.washingtondc.name.item.one");
            switch (page){
                case "available bungii":
                    switch (pallet){
                        case "pallet-1":
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneWeight()),palletOneWeight+" lbs",
                                    "The correct weight should be displayed.",
                                    "The correct weight is displayed.",
                                    "The incorrect weight is displayed.");
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneDimensions()),palletOneDimensions+" in",
                                    "The correct dimension should be displayed.",
                                    "The correct dimension is displayed.",
                                    "The incorrect dimension is displayed.");
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneName()),palletOneName,
                                    "The correct name should be displayed.",
                                    "The correct name is displayed.",
                                    "The incorrect name is displayed.");
                            break;
                    }
                    break;
                case "schedule bungii":
                    switch (pallet) {
                        case "pallet-1":
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneWeightSchedulePage()),palletOneWeight+" lbs",
                                    "The correct weight should be displayed.",
                                    "The correct weight is displayed.",
                                    "The incorrect weight is displayed.");
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneDimensionsSchedulePage()),palletOneDimensions+" in",
                                    "The correct dimension should be displayed.",
                                    "The correct dimension is displayed.",
                                    "The incorrect dimension is displayed.");
                            testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PalletOneNameSchedulePage()),palletOneName,
                                    "The correct name should be displayed.",
                                    "The correct name is displayed.",
                                    "The incorrect name is displayed.");
                            break;

                    }
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I check already accepted pallet pop up is displayed$")
    public void i_check_already_accepted_pallet_pop_up_is_displayed() throws Throwable {
        try{
            String expectedSnackMsg = PropertyUtility.getMessage("pallet.already.accepted.message");
            testStepVerify.isEquals(utility.getDriverSnackBarMessage(), expectedSnackMsg);

            log("I should be able to see the pallet already accepted message",
                    "I am able to see the pallet already accepted message",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

}

