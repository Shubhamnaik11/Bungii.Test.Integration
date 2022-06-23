package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.*;
import com.bungii.android.pages.driver.ScheduledBungiiPage;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class ScheduledBungiiSteps extends DriverBase {
    ScheduledBungiiPage scheduledBungiiPage = new ScheduledBungiiPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    InProgressBungiiPages inProgressBungiiPages = new InProgressBungiiPages();
    private static LogUtility logger = new LogUtility(ScheduledBungiiSteps.class);
    @And("I open first Trip from driver scheduled trip")
    public void iSelectFirstTripFromDriverScheduledTrip() {
        try{
            boolean skipNormalFlow = false;
            boolean isSelected = false;
            if(action.isNotificationAlertDisplayed()){
                if(action.getText(Page_BungiiRequest.Alert_Msg(true)).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))){
                    utility.acceptNotificationAlert();
                    skipNormalFlow=true;
                }
                else{
                    action.click(Page_BungiiRequest.AlertButton_Cancel());
                }
                isSelected=true;
            }
            if(!skipNormalFlow) {
                String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
                List<WebElement> listOfScheduledTrip = scheduledBungiiPage.List_ScheduledBungiis();
                String timeZone=utility.getTimeZoneBasedOnGeofence();
                for (WebElement element : listOfScheduledTrip) {
                    WebElement schDate = element.findElement(By.id("com.bungii.driver:id/scheduled_row_textview_scheduleddatetime"));
                        WebElement rowViewIcom = element.findElement(By.id("com.bungii.driver:id/scheduled_row_textview_icon"));
                        action.click(new Point(rowViewIcom.getLocation().getX(), rowViewIcom.getLocation().getY()));
                        //action.click(rowViewIcom);
                        isSelected = true;
                        break;
                }
            }
            if(skipNormalFlow)
                testStepVerify.isTrue(isSelected,"I should able to Select Trip from driver scheduled trip","I selected trip using alert for upcoming trip to driver ","I was not able to start Bungii");
            else
                testStepVerify.isTrue(isSelected,"I should able to Select Trip from driver scheduled trip","I selected trip using list of Bungii's present in avialable bungii list","I was not able to start Bungii");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I should see the notification for address change$")
    public void i_should_see_the_notification_for_address_change() throws Throwable {
      try{
          action.scrollToTop();
          action.isElementPresent(Page_BungiiRequest.Notification_AddressChanged());
          action.click(Page_BungiiRequest.Button_NotificationOk());
          log("I should be to see address change notification","I was able to see address change notification",false);
      }
      catch (Exception e) {
          logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
          error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
      }
    }
    @And("^I swipe to check trip details$")
    public void i_swipe_to_check_trip_details() throws Throwable {
      try{
          action.scrollToBottom();
          Thread.sleep(3000);

          log("I should be able to swipe to view delivery details","I am able to swipe to view delivery details",false);
      }
      catch (Exception e) {
          logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
          error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
      }
    }

    @Then("^I check if \"([^\"]*)\" is updated for live trip$")
    public void i_check_if_something_is_updated_for_live_trip(String address) throws Throwable {
       try{
           switch (address){
               case "dropoff address":
                   action.scrollToBottom();
                   String changedDropOff= (String) cucumberContextManager.getScenarioContext("Change_Drop_Off");
                   String actualDropOff=Page_BungiiRequest.Text_DropOffAddress().getText();
                   testStepAssert.isEquals(actualDropOff,changedDropOff, "The drop off address should be updated", "The drop off address is updated", "The drop off address is not updated");
                   break;

               case "pickup address":
                   action.scrollToTop();
                   String changedPickup = (String) cucumberContextManager.getScenarioContext("Change_Pickup");
                   String actualPickUp = Page_BungiiRequest.Text_PickUpAddress().getText();
                   testStepAssert.isEquals(actualPickUp,changedPickup, "The pick up address should be updated", "The pick up address is updated", "The pick up address is not updated");
                   break;

           }
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
       }
    }

    @And("^I check if \"([^\"]*)\" is updated$")
    public void i_check_if_something_is_updated(String addressType) throws Throwable {
        try{
            switch (addressType){
                case "pickup address":
                    action.scrollToTop();
                    String changedPickup[] = cucumberContextManager.getScenarioContext("Change_Pickup").toString().split(",");
                    String actualPickUpLineOne=action.getText(Page_BungiiRequest.Text_PickupLocation_LineOne1());;
                    testStepAssert.isEquals(actualPickUpLineOne,changedPickup[0], "The pick up address should be updated", "The pick up address is updated", "The pick up address is not updated");
                    break;
                case "dropoff address":
                    action.scrollToTop();
                    String changedDropOff[]=cucumberContextManager.getScenarioContext("Change_Drop_Off").toString().split(",");
                    String actualDropOffLineOne=action.getText(Page_BungiiRequest.Text_DropOffLocation_LineOne1());;
                    testStepAssert.isEquals(actualDropOffLineOne,changedDropOff[0], "The drop off address should be updated", "The drop off address is updated", "The drop off address is not updated");
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("I Select Trip from driver scheduled trip")
    public void iSelectTripFromDriverScheduledTrip() {
        try{
        boolean skipNormalFlow = false;
        boolean isSelected = false;
        if(action.isNotificationAlertDisplayed()){
            if(action.getText(Page_BungiiRequest.Alert_Msg(true)).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))){
                utility.acceptNotificationAlert();
                skipNormalFlow=true;
            }
            else{
                action.click(Page_BungiiRequest.AlertButton_Cancel());
            }
            isSelected=true;
        }
        if(!skipNormalFlow) {
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            List<WebElement> listOfScheduledTrip = scheduledBungiiPage.List_ScheduledBungiis();
            String timeZone=utility.getTimeZoneBasedOnGeofence();
            for (WebElement element : listOfScheduledTrip) {
                WebElement schDate = element.findElement(By.id("com.bungii.driver:id/scheduled_row_textview_scheduleddatetime"));
               // WebElement schTimeZone = element.findElement(By.id("com.bungii.driver:id/scheduled_row_textview_timezone_label"));
               /* if(!tripTime.contains(timeZone))
                    tripTime=tripTime+" "+timeZone;*/
                //if ((action.getText(schDate)+""+action.getText(schTimeZone)).equalsIgnoreCase(tripTime)) {
                if(TimeZone.getTimeZone("CST6CDT").inDaylightTime(new Date()))
                    tripTime = tripTime.replace("ST","DT");

              //  Thread.sleep(4000);
                //if ((action.getText(schDate)).equalsIgnoreCase(tripTime)) {
                    WebElement rowViewIcom = element.findElement(By.id("com.bungii.driver:id/scheduled_row_textview_icon"));
                    action.click(new Point(rowViewIcom.getLocation().getX(), rowViewIcom.getLocation().getY()));
                    //action.click(rowViewIcom);
                    isSelected = true;
                    break;
                //}
            }
        }
        if(skipNormalFlow)
            testStepAssert.isTrue(isSelected,"I should able to Select Trip from driver scheduled trip","I selected trip using alert for upcoming trip to driver ","I was not able to select Bungii");
        else
            testStepAssert.isTrue(isSelected,"I should able to Select Trip from driver scheduled trip","I selected trip using list of Bungii's present in avialable bungii list","I was not able to select Bungii");

    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }

    @And("^I click the \"([^\"]*)\" button on \"([^\"]*)\" screen$")
    public void i_click_the_something_button_on_something_screen(String strArg1, String strArg2) throws Throwable {
        try {
            action.click(inProgressBungiiPages.Button_MoreOptions());
            action.click(inProgressBungiiPages.Button_Cancel());
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I wait for Minimum duration for Bungii Start Time$")
    public void i_wait_for_minimum_duration_for_bungii_start_time() {
        /*try {
            Date currentDate = new Date();

            String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
      //      bungiiTime =bungiiTime+" "+(currentDate.getYear()+1900);
            int mininumWaitTime = Integer.parseInt(PropertyUtility.getProp("scheduled.min.start.time"));
            if (!bungiiTime.equalsIgnoreCase("NOW")) {
                DateFormat formatter = new SimpleDateFormat("MMM d, h:mm a");
          //      DateFormat formatter = new SimpleDateFormat("MMM dd, hh:mm a yyyy");
             //   formatter.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()));
                formatter.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()));
                Date bungiiDate = formatter.parse(bungiiTime);


                bungiiDate.setYear(currentDate.getYear());//(Integer.parseInt(currentDate.getYear()));
                long duration = bungiiDate.getTime() - currentDate.getTime();

                long diffInMinutes;
                if (duration > 0) {
                    diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration) - mininumWaitTime;
                    diffInMinutes = diffInMinutes > 0 ? diffInMinutes : 0;
                } else {
                    diffInMinutes = 0;
                }
                action.hardWaitWithSwipeUp((int) diffInMinutes + 1);
                log("I wait for "+diffInMinutes+" Minutes for Bungii Start Time ", "I waited for "+diffInMinutes, true);
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
        */
        logger.detail("Temparory Commented since it is taking longer time.");
    }
}
