package com.bungii.web.stepdefinitions.partnerManagement;

import com.bungii.api.utilityFunctions.GoogleMaps;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.partnerManagement.PartnerManagement_Email;
import com.bungii.web.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Partner_Management_Email_Steps extends DriverBase {
    ActionManager action = new ActionManager();
    PartnerManagement_Email Page_PartnerManagement_Email = new PartnerManagement_Email();
    private static LogUtility logger = new LogUtility(Partner_Management_Email_Steps.class);


    @And("^I search for \"([^\"]*)\" partner on partner management$")
    public void i_search_for_something_partner_on_partner_management(String partner) throws Throwable {
        try{
        Thread.sleep(2000);
        action.clearSendKeys(Page_PartnerManagement_Email.TextBox_Search(),partner+ Keys.ENTER);
        cucumberContextManager.setScenarioContext("NameOfPartnerLocationOrPartnerPortalName",partner);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I add \"([^\"]*)\" as the new email$")
    public void i_add_something_as_the_new_email(String email) throws Throwable {
        try{
        Thread.sleep(3000);
        String oldEmailAddress = action.getAttributeValue(Page_PartnerManagement_Email.Text_OldEmailAddress());
        cucumberContextManager.setScenarioContext("Old Email",oldEmailAddress);
        switch (email){
            case "Primary email address":
                action.clearSendKeys(Page_PartnerManagement_Email.TextBox_AddNewEmail(), PropertyUtility.getDataProperties("qa.auto.test.email.address"));
                cucumberContextManager.setScenarioContext("NewEmail",PropertyUtility.getDataProperties("qa.auto.test.email.address"));
                break;
            case "Secondary email address":
                action.clearSendKeys(Page_PartnerManagement_Email.TextBox_AddNewEmail(),PropertyUtility.getDataProperties("DriverEmail"));
                cucumberContextManager.setScenarioContext("NewEmail",PropertyUtility.getDataProperties("DriverEmail"));
                break;
        }
        log("I should be able to add the email address "+cucumberContextManager.getScenarioContext("NewEmail"),"I could add the email address "+cucumberContextManager.getScenarioContext("NewEmail"),false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^All email addresses should be displayed for the mentioned partner$")
    public void all_email_addresses_should_be_displayed_for_the_mentioned_partner() throws Throwable {
        try{
        String newlyAddedEmail =(String) cucumberContextManager.getScenarioContext("NewEmail");
        String partner = (String) cucumberContextManager.getScenarioContext("NameOfPartnerLocationOrPartnerPortalName") ;
        String emailAddressStoredInDB[] = com.bungii.web.utilityfunctions.DbUtility.getAllEmailAddress(partner).split(",");
        boolean isNewlyAddedEmailPresent =false;
        for(String differentEmailAddresses:emailAddressStoredInDB){
            if(differentEmailAddresses.contentEquals(newlyAddedEmail)){
                testStepAssert.isTrue(true,"Newly added Email "+newlyAddedEmail+ " should be present ","Newly added Email "+newlyAddedEmail+ " is present ","Newly added Email "+differentEmailAddresses+ " is not present ");
                isNewlyAddedEmailPresent =true;
            }

        }
        if(!isNewlyAddedEmailPresent){
            testStepAssert.isFail("Newly added email is not present");

        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The \"([^\"]*)\" should match$")
    public void the_something_should_match(String strArg1) throws Throwable {
//       String pickupReference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
//       String pickupReference = "96e5791a-2366-3e60-3899-50d61a1d73cb";
//        String []ArrivalTimeAndUnloadingLoadingTime = DbUtility.getArrivalTimeAndLoadingUnloadingTime(pickupReference);
        switch (strArg1){
            case "blah":
                String expectedDroffTimeRange= "11:45";
//                if(expectedDroffTimeRange.contains("Pm") && expectedDroffTimeRange.contains("Am")){
//                    String onlyTimeRange = expectedDroffTimeRange.replace("Pm","").replace("Am","").replace(" ","");
//                    cucumberContextManager.setScenarioContext("UITimeRange",onlyTimeRange);
//                }
//                else {
//                    String onlyTimeRange = expectedDroffTimeRange.substring(0, expectedDroffTimeRange.length()-3);
//                    cucumberContextManager.setScenarioContext("UITimeRange",onlyTimeRange);
//                }


            case "Arrival time":
//               String za = ConvertTimeToCST(ArrivalTimeAndUnloadingLoadingTime[1].split(" "));
//                String pickupReference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
//;               String dateAndTime[] =ArrivalTimeAndUnloadingLoadingTime[1].split(" ");
//                String onlyTime[] = dateAndTime[1].split(":");
//                String onlyTime1[] = dateAndTime[0].split("-");
////                ZonedDateTime now = ZonedDateTime.ofInstant(Instant.,ZoneId.of("UTC"));
////                ZoneId defaultZone = TimeZone.getTimeZone("UTC").t;
//                ZonedDateTime instant = ZonedDateTime.of(Integer.parseInt(onlyTime1[0]),Integer.parseInt(onlyTime1[1]),Integer.parseInt(onlyTime1[2]),Integer.parseInt(onlyTime[0]),Integer.parseInt(onlyTime[1]),Integer.parseInt(onlyTime[2]),0,ZoneId.of("UTC"));
//                ZonedDateTime instantInUTC = instant.withZoneSameInstant(ZoneId.of("America/Chicago"));
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
//                String duh = instantInUTC.format(formatter);
//                String blah[] =instantInUTC.format(formatter).split(":");
//                if(blah[0].startsWith("0")){
//                    String hourWithoutZero= blah[0].replaceFirst("0","");
//                    String properArrivalTime =hourWithoutZero +":"+blah[1];
//                    System.out.println(properArrivalTime);
//                }
//                else {
//                    String properArrivalTime =blah[0] +":"+blah[1];
//                    System.out.println(properArrivalTime);
//                }

//                ZoneId zoneId = ZoneId.of("America/Chicago");
//                ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(, zoneId);
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
//                String expectedTime = zonedDateTime.format(formatter);
//                String fakeTime ="4:45 AM";
//                System.out.println(za);
//                if(za.startsWith("0")){
//                    String hourWithoutZero =za.replaceFirst("0","");
//                    cucumberContextManager.setScenarioContext("ArrivalTime",hourWithoutZero);
//                }
//               else {
//                    cucumberContextManager.setScenarioContext("ArrivalTime",za);
//               }
//                System.out.println("ArrivalTime is "+cucumberContextManager.getScenarioContext("ArrivalTime"));
                break;
            case"admin edits pickup Address":
            case "Expected time at drop-off":
            case "driver at arrival state":
            case "Stacked dropOff range":
//                if(strArg1.contentEquals("Expected time at drop-off")){
//                     String arrivalTime = (String) cucumberContextManager.getScenarioContext("ArrivalTime");
////                    String arrivalTime =  "1:15 AM";
//                    String[] hoursAndMinutes =arrivalTime.substring(0, arrivalTime.length() - 3).split(":");
//                    String hours = hoursAndMinutes[0];
//                    String minutes = hoursAndMinutes[1];
//                    cucumberContextManager.setScenarioContext("Hours",hours);
//                    cucumberContextManager.setScenarioContext("Minutes",minutes);
//                }
//
//
//
//                String hours =(String) cucumberContextManager.getScenarioContext("Hours");
//                String minutes =(String) cucumberContextManager.getScenarioContext("Minutes");
//
//                int convertHoursToMinutes = (Integer.parseInt( hours)*60) +Integer.parseInt( minutes) ;
//
//                int unloadingLoadingTime = (int) Float.parseFloat(ArrivalTimeAndUnloadingLoadingTime[2]);
//                int totalMinutes = convertHoursToMinutes  + (unloadingLoadingTime/3)+ (Integer.parseInt(ArrivalTimeAndUnloadingLoadingTime[0]))+40;
//                final SimpleDateFormat formatTochangeChangeTo12Hours = new SimpleDateFormat("hh:mm");
////
//               String roundedTime =timeIn10MinutesInterval(LocalTime.MIN.plus(Duration.ofMinutes( totalMinutes)).toString());

               String time ="04:48";
//
               String roundedTime =roundedUpTime(time);


                String add1hour = (String) cucumberContextManager.getScenarioContext("Add1hour");
//                if(Integer.parseInt(add1hour)>=56){
//                    LocalTime time =LocalTime.parse(roundedTime);
//        ;          String plus1Hour = formatTochangeChangeTo12Hours.format(formatTochangeChangeTo12Hours.parse(String.valueOf(time.plusHours(2)))) ;
//                    cucumberContextManager.setScenarioContext("Timeplus1hour",plus1Hour);
//                    cucumberContextManager.setScenarioContext("Timeminus1hour",time);
//                }
//                else {
//                    LocalTime TimeInhours =LocalTime.parse(roundedTime);
//                    String plus1Hour = formatTochangeChangeTo12Hours.format(formatTochangeChangeTo12Hours.parse(String.valueOf(TimeInhours.plusHours(1)))) ;
//                    String minus1Hour = formatTochangeChangeTo12Hours.format(formatTochangeChangeTo12Hours.parse(String.valueOf(TimeInhours.minusHours(1)))) ;
//                    cucumberContextManager.setScenarioContext("Timeplus1hour",plus1Hour);
//                    cucumberContextManager.setScenarioContext("Timeminus1hour",minus1Hour);
//                }
//
//
//
//                String timePlus1HourIn15MinutesInterval = (String) cucumberContextManager.getScenarioContext("Timeplus1hour");
//                String timeMinus1HourIn15MinutesInterval =(String) cucumberContextManager.getScenarioContext("Timeminus1hour") ;
//
//                if (timePlus1HourIn15MinutesInterval.startsWith("0")) {
//                        String hourWithoutZero = timePlus1HourIn15MinutesInterval.replaceFirst("0", "");
//                        cucumberContextManager.setScenarioContext("StartingDropOffTimeRange", hourWithoutZero);
//                    } else {
//                        cucumberContextManager.setScenarioContext("StartingDropOffTimeRange", timePlus1HourIn15MinutesInterval);
//
//                }
//                if (timeMinus1HourIn15MinutesInterval.startsWith("0")) {
//                    String hourWithoutZero = timeMinus1HourIn15MinutesInterval.replaceFirst("0", "");
//                    cucumberContextManager.setScenarioContext("EndingDropOffTimeRange", hourWithoutZero);
//                } else {
//                    cucumberContextManager.setScenarioContext("EndingDropOffTimeRange", timeMinus1HourIn15MinutesInterval);
//
//                }
//                String startingTimeRange =(String) cucumberContextManager.getScenarioContext("StartingDropOffTimeRange");
//                String endingTimeRange =(String) cucumberContextManager.getScenarioContext("EndingDropOffTimeRange");
//                String expectedTimeRange =endingTimeRange +"-"+startingTimeRange;
//                System.out.println("drop of range "+expectedTimeRange);
                break;
        }


    }
    @Then("^I save the dropoff latitude and longitude of the first delivery$")
    public void i_save_the_dropoff_latitude_and_longitude_of_the_first_delivery() throws Throwable {
//         String pickupReference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String pickupReference ="96e5791a-2366-3e60-3899-50d61a1d73cb";
        String teletTimeInDb = DbUtility.getTelet(pickupReference);
        String zaas = ConvertTimeToCST(teletTimeInDb.substring(0, teletTimeInDb.length() - 4).split(" "));
        String[] tcreatedDeliveryInHoursAndMinutes =zaas.substring(0, zaas.length() - 3).split(":");
        String onlyHours = tcreatedDeliveryInHoursAndMinutes[0];
        String onlyMinutes = tcreatedDeliveryInHoursAndMinutes[1];
        cucumberContextManager.setScenarioContext("Hours",onlyHours);
        cucumberContextManager.setScenarioContext("Minutes",onlyMinutes);;
        String[] location1PickupAndDropOffLatLong = DbUtility.getLatAndLonPickupAndDropLocation(pickupReference);
        cucumberContextManager.setScenarioContext("onlyDropOffLat",location1PickupAndDropOffLatLong[2]);
        cucumberContextManager.setScenarioContext("onlyDropOffLong",location1PickupAndDropOffLatLong[3]);;
    }

    private String roundedUpTime(String IncorrectTime) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        Date dt = formatter.parse(IncorrectTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int unroundedMinutes = cal.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 5;
        cal.add(Calendar.MINUTE, mod < 3 ? -mod : (5-mod));
        String hourAndMinute = formatter.format(cal.getTime());
        logger.detail("The rounded up time for "+ IncorrectTime+ " time is "+ hourAndMinute);
        return hourAndMinute;
    }

    private String ConvertTimeToCST(String[] uctToCstTime) {
        String date[] = uctToCstTime[0].split("-");
        String time[] = uctToCstTime[1].split(":");
        ZonedDateTime instant1 = ZonedDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(time[2]),0,ZoneId.of("UTC"));
        ZonedDateTime instantInUTC = instant1.withZoneSameInstant(ZoneId.of("America/Chicago"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String timeInCST = instantInUTC.format(formatter);
        return timeInCST;
    }
}
