package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.BungiiAcceptedPage;
import com.bungii.android.pages.customer.EstimatePage;
import com.bungii.android.pages.driver.InProgressBungiiPages;
import com.bungii.android.pages.otherApps.OtherAppsPage;
import com.bungii.android.stepdefinitions.Customer.SignupSteps;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.api.utilityFunctions.GoogleMaps;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Point;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.*;

public class BungiiInProgressSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    InProgressBungiiPages bungiiProgressPage = new InProgressBungiiPages();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    BungiiAcceptedPage bungiiAcceptedPage = new BungiiAcceptedPage();
    OtherAppsPage otherAppsPage = new OtherAppsPage();
    InProgressBungiiPages inProgressBungiiPages=new InProgressBungiiPages();
    EstimatePage bungiiEstimatePage = new EstimatePage();
    @Then("^Trip Information should be correctly displayed on \"([^\"]*)\" status screen for \"([^\"]*)\" driver$")
    public void trip_information_should_be_correctly_displayed_on_something_status_screen_for_customer(String key, String driverType) {
        try {


            String expectedCustName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            expectedCustName = expectedCustName.substring(0, expectedCustName.indexOf(" ") + 2);
            boolean isCustomerNameCorrect = false;
            boolean isDriverNameCorrect = false;
            if (driverType.equalsIgnoreCase("controller")) {
                //drivername and customer name validation
                if (String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO")) {
                    isCustomerNameCorrect = action.getText(bungiiProgressPage.Text_DuoCustomer_Name()).equals(expectedCustName);

                    String driver2Name = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
                    Thread.sleep(3000);
                    String driverName = action.getText(bungiiProgressPage.Text_DuoDriver_Name());
                    String expected2 = driver2Name.substring(0, driver2Name.indexOf(" ") + 2);

                    isDriverNameCorrect = driverName.equals(expected2);

                    logger.detail("Driver 2" + driver2Name.substring(0, driver2Name.indexOf(" ") + 2));
                    testStepVerify.isTrue(isDriverNameCorrect,
                            "Driver name should correctly display",
                            "Driver name was correctly displayed",
                            "Driver name was not correctly displayed. [" + driverName + " ] is displayed instead of " + expected2);
                } else
                    isCustomerNameCorrect = getCustomerName().equals(expectedCustName);

            }
            else
            {
                Thread.sleep(5000);
                if (String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO")) {
                    isCustomerNameCorrect = action.getText(bungiiProgressPage.Text_DuoCustomer_Name()).equals(expectedCustName);

                    String driver1Name = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
                    Thread.sleep(3000);

                    String driverName = action.getText(bungiiProgressPage.Text_DuoDriver_Name());
                    String expected1 = driver1Name.substring(0, driver1Name.indexOf(" ") + 2);

                    isDriverNameCorrect = driverName.equals(expected1);

                    logger.detail("driver1Name" + driver1Name.substring(0, driver1Name.indexOf(" ") + 2));
                    testStepVerify.isTrue(isDriverNameCorrect,
                            "Driver name should correctly display",
                            "Driver name was correctly displayed",
                            "Driver name was not correctly displayed. [" + driverName + " ] is displayed instead of " + expected1);
                } else
                    isCustomerNameCorrect = getCustomerName().equals(expectedCustName);
            }
            switch (key) {
                case "EN ROUTE":
                    validateEnRouteInfo(getTripInformation(key));
                    break;
                case "ARRIVED":
                    validateArrivedInfo(getTripInformation(key));
                    break;
                case "LOADING ITEM":
                    validateArrivedInfo(getTripInformation(key));
                    break;
                case "DRIVING TO DROP OFF":
                    validateDrivingInfo(getTripInformation(key));
                    break;
                case "UNLOADING ITEM":
                    validateUnloadingInfo(getTripInformation(key));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            if (/*isInfoCorrectlyDisplayed && */isCustomerNameCorrect) {
                pass("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");
            } else {
                fail("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");

            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^Trip Information should be correctly displayed on \"([^\"]*)\" status screen for driver$")
    public void trip_information_should_be_correctly_displayed_on_something_status_screen_for_customer(String key) {
        try {


            String expectedCustName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            expectedCustName = expectedCustName.substring(0, expectedCustName.indexOf(" ") + 2);
            boolean isCustomerNameCorrect;
            //drivername and customer name validation
            if(String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO")){
                isCustomerNameCorrect=action.getText(bungiiProgressPage.Text_DuoCustomer_Name()).equals(expectedCustName);

                String driver1Name=(String) cucumberContextManager.getScenarioContext("DRIVER_1");
                String driver2Name=(String) cucumberContextManager.getScenarioContext("DRIVER_2");

                     String driverName = action.getText(bungiiProgressPage.Text_DuoDriver_Name());
                     String expected1 = driver1Name.substring(0,driver1Name.indexOf(" ")+2);
                     String expected2 = driver2Name.substring(0,driver2Name.indexOf(" ")+2);

                boolean isDriverNameCorrect=driverName.equals(expected1) || driverName.equals(expected2);

                logger.detail("driver1Name"+driver1Name.substring(0,driver1Name.indexOf(" ")+2) +"|||Driver 2"+driver2Name.substring(0,driver2Name.indexOf(" ")+2));
                testStepVerify.isTrue(isDriverNameCorrect,
                        "Driver name should correctly display",
                        "Driver name was correctly displayed",
                        "Driver name was not correctly displayed. ["+driverName+" ] is displayed instead of "+ expected1 +" or "+ expected2);
            }
            else
                isCustomerNameCorrect = getCustomerName().equals(expectedCustName);
            switch (key) {
                case "EN ROUTE":
                    validateEnRouteInfo(getTripInformation(key));
                    break;
                case "ARRIVED":
                    validateArrivedInfo(getTripInformation(key));
                    break;
                case "LOADING ITEM":
                    validateArrivedInfo(getTripInformation(key));
                    break;
                case "DRIVING TO DROP OFF":
                    validateDrivingInfo(getTripInformation(key));
                    break;
                case "UNLOADING ITEM":
                    validateUnloadingInfo(getTripInformation(key));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

            if (/*isInfoCorrectlyDisplayed && */isCustomerNameCorrect) {
                pass("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");
            } else {
                fail("Trip Information should be correctly displayed and customer name :" + expectedCustName + "should be displayed", "Trip Information is correctly displayed and customer name :" + expectedCustName + "is displayed correctly");

            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    /**
     * Get trip information , Information while is below status icon , ex drop location etc
     *
     * @return Get trip information
     */
    public List<String> getTripInformation(String key) {
        List<String> details = new ArrayList<>();
        switch (key) {
            case "EN ROUTE":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                details.add(action.getText(bungiiProgressPage.Bungii_ETA()));
                break;
            case "ARRIVED":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                break;
            case "LOADING ITEM":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                break;
            case "DRIVING TO DROP OFF":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                details.add(action.getText(bungiiProgressPage.Bungii_ETA()));
                break;
            case "UNLOADING ITEM":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                break;
            default:
                error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                break;
        }

        return details;
    }

    private boolean validateUnloadingInfo(List<String> actualInfo) {
        logger.detail("INside trip info validation");

        boolean isTagDisplayed = actualInfo.get(0).equals("DROP OFF LOCATION");
        String actualDroplocation=actualInfo.get(1).replace(",","").replace("  "," ");

        String dropOffLocationLineOne=String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        boolean isDropDisplayed =actualDroplocation.contains(dropOffLocationLineOne) &&actualDroplocation.contains(dropOffLocationLineTwo) ;


            testStepVerify.isEquals(actualInfo.get(0), "DROP OFF LOCATION",
                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'PDROP OFF LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isDropDisplayed,

                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is " + actualInfo.get(1) + "and expected is " + dropOffLocationLineOne+dropOffLocationLineTwo
                    ,
                    "Pick up location was not displayed correctly, actual was is " + actualInfo.get(1) + " and expected is" +dropOffLocationLineOne+dropOffLocationLineTwo
            );


        return isTagDisplayed && isDropDisplayed;
    }

    private boolean validateEnRouteInfo(List<String> actualInfo) {
        logger.detail("INside trip info validation");

        boolean isTagDisplayed = actualInfo.get(0).equals("PICKUP LOCATION");
        boolean isETACorrect = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("mins");
        String actualPickuplocation=actualInfo.get(1).replace(",","").replace("  "," ");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",","").replace("  "," ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",","").replace("  "," ").trim();
        boolean isPickUpDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) &&actualPickuplocation.contains(pickUpLocationLineTwo);

        String actualDroplocation=actualInfo.get(1).replace(",","").replace("  "," ");
        testStepVerify.isTrue(isTagDisplayed, "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed", "'PICKUP LOCATION' Tag was not correctly displayed");
            testStepVerify.isTrue(isETACorrect,
                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));
            testStepVerify.isTrue(isPickUpDisplayed,
                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + " and expected is " + pickUpLocationLineOne+pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualInfo.get(1) + " and expected is " +pickUpLocationLineOne+pickUpLocationLineTwo);

        return isTagDisplayed && isETACorrect && isPickUpDisplayed;
    }

    private boolean validateDrivingInfo(List<String> actualInfo) {
        logger.detail("inside trip info validation");

        boolean isTagDisplayed = actualInfo.get(0).equals("DROP OFF LOCATION");
        boolean isETAdisplayed = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("mins");
        String actualDropoffLocation=actualInfo.get(1).replace(",","").replace("  "," ");

        String dropOffLocationLineOne=String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        boolean isDropDisplayed =actualDropoffLocation.contains(dropOffLocationLineOne) &&actualDropoffLocation.contains(dropOffLocationLineTwo) ;


            testStepVerify.isTrue(isTagDisplayed,
                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'PDROP OFF LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isETAdisplayed,
                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

            testStepVerify.isTrue(isDropDisplayed,
                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + " and expected is " + dropOffLocationLineOne+dropOffLocationLineTwo
                    ,
                    "Pick up location was not displayed correctly, actual was is" + actualInfo.get(1) + "and expected is" + dropOffLocationLineOne+dropOffLocationLineTwo
            );

        return isTagDisplayed && isETAdisplayed && isDropDisplayed;
    }

    private boolean validateArrivedInfo(List<String> actualInfo) {
        logger.detail("inside trip info validation");

        boolean isTagDisplayed = actualInfo.get(0).equals("PICKUP LOCATION");
        String actualPickuplocation=actualInfo.get(1).replace(",","").replace("  "," ");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",","").replace("  "," ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",","").replace("  "," ").trim();
        boolean isPickUpDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) &&actualPickuplocation.contains(pickUpLocationLineTwo);

        String actualDroplocation=actualInfo.get(1).replace(",","").replace("  "," ");
            testStepVerify.isTrue(isTagDisplayed,
                    "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
                    "'PICKUP LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isPickUpDisplayed,
                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + " and expected is " + pickUpLocationLineOne+pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualInfo.get(1) + "and expected is" + pickUpLocationLineOne+pickUpLocationLineTwo);

        return isTagDisplayed && isPickUpDisplayed;
    }

    @And("^stack trip information should be displayed on deck$")
    public void stack_trip_information_should_be_displayed_on_deck() {
        try {
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER2");
//            testStepVerify.isElementTextEquals(bungiiProgressPage.Text_NextLabel(), "NEXT CUSTOMER","'NEXT CUSTOMER' text lable should be displayed","'NEXT' text lable is displayed","'NEXT' text lable is not displayed"); - Next customer not displayed with new driver app changes
            testStepVerify.isTrue(bungiiProgressPage.Text_OnDeckLabel().getText().contains("Bungii on deck, try to finish by"),"On Deck text label should be displayed","'On Deck text label is not displayed");
//            testStepVerify.isElementTextEquals(bungiiProgressPage.Text_StackCustomer(), customerName.substring(0, customerName.indexOf(" ") + 2));
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^stack trip information should not be displayed on deck$")
    public void stack_trip_information_should_not_be_displayed_on_deck() {
        try {
            testStepVerify.isElementNotEnabled(bungiiProgressPage.Text_NextLabel(true), "Next tag should not be enabled","Next tag is not displayed","Next tag is displayed");
            testStepVerify.isElementNotEnabled(bungiiProgressPage.Text_OnDeckLabel(true), "ON DECK should not be displayed" ,"'ON DECK' text lable is not displayed","ON DECK is displayed");
            testStepVerify.isElementNotEnabled(bungiiProgressPage.Text_StackCustomer(true),"stack Customer name should be not be diplayed","stack Customer name should be not be diplayed","stack Customer name is diplayed");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^try to finish time should be correctly displayed for long stack trip$")
    public void try_to_finish_time_should_be_correctly_displayed() throws Throwable {

        if(((String)cucumberContextManager.getScenarioContext("DRIVER_MIN_ARRIVAL")).equalsIgnoreCase(""))
        {
            String[] calculatedTime=getTeletTimeinLocalTimeZone();
            cucumberContextManager.setScenarioContext("DRIVER_TELET",calculatedTime[0]);
            cucumberContextManager.setScenarioContext("DRIVER_MIN_ARRIVAL",calculatedTime[1]);
            cucumberContextManager.setScenarioContext("DRIVER_MAX_ARRIVAL",calculatedTime[2]);
        }
        String telet=((String)cucumberContextManager.getScenarioContext("DRIVER_TELET"));
        //Initial Zero is truncated
        //Sprint 32 , Initial zero are displayed
/*        if (telet.startsWith("0"))
            telet = telet.substring(1);*/
//Richa
        String[] timeZone=utility.getDayLightTimeZoneBasedOnGeofence();
        String normalTimeZone="try to finish up by "+telet+" "+timeZone[0];
        String dayLightTimeZone="try to finish up by "+telet+" "+timeZone[1];
        String actualTime=action.getText(bungiiProgressPage.Text_OnDeckLabel());

        if(actualTime.contains(normalTimeZone) || actualTime.contains(dayLightTimeZone)) {
            testStepAssert.isTrue(true,"The finish time should be displayed correctly.", "The finish time is not displayed correctly.");
        }
        else
        {
            testStepAssert.isFail("The finish time is not displayed correctly.");
        }

    }
    @Then("^try to finish time should be correctly displayed for short stack trip$")
    public void try_to_finish_time_should_be_correctly_displayed_ShortStack() throws Throwable {
        calculateShortStack();
        testStepVerify.isElementTextEquals(bungiiProgressPage.Text_FinishBy(),"Bungii on deck, try to finish up by "+((String)cucumberContextManager.getScenarioContext("DRIVER_FINISH_BY"))+" "+utility.getTimeZoneBasedOnGeofence());

    }
    @Then("^I calculate projected driver arrival time$")
    public void i_calculate_projected_driver_arrival_time() throws Throwable {
           calculateShortStack();
    }

    @Then("^correct message should be displayed after clicking info button$")
    public void correct_message_should_be_displayed_after_clicking_info_button() throws Throwable {
        action.click(bungiiProgressPage.Button_StackInfo());
        testStepVerify.isElementTextEquals(bungiiProgressPage.Alert_Message(),PropertyUtility.getMessage("driver.stack.info.button.alert"));
    }

    public String[] getTeletTimeinLocalTimeZone(){
        String[] calculatedTime=new String[3];
        try {
            String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
            String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
            String custRef = DbUtility.getCustomerRefference(phoneNumber);
            String teletTime = DbUtility.getTELETfromDb(custRef);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            //By default data is in UTC
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date teletTimeInUtc = formatter.parse(teletTime);
            DateFormat formatterForLocalTimezone = new SimpleDateFormat("hh:mm a");
            formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
            String teletInLocalTime = formatterForLocalTimezone.format(teletTimeInUtc);
            long t= teletTimeInUtc.getTime();
            long ONE_MINUTE_IN_MILLIS=60000;//millisecs
            Date minTime=new Date(t - (15 * ONE_MINUTE_IN_MILLIS));
            String strMindate = formatterForLocalTimezone.format(minTime);


            Date maxTime=new Date(t + (30 * ONE_MINUTE_IN_MILLIS));
            String strMaxdate = formatterForLocalTimezone.format(maxTime);
            calculatedTime[0] =teletInLocalTime;
            calculatedTime[1] =strMindate;
            calculatedTime[2] =strMaxdate;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calculatedTime;
    }
    public void calculateShortStack() throws ParseException {
//        cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "kansas");
        try {

            int FROM_RANGE_FROM = -10;
            int FROM_RANGE_TO = +20;
            long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

            String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
            String customerPhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");//customerPhoneNumber="9999991889";
            String customer2PhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");//customer2PhoneNumber="9999991259";
            String driverPhoneNumber = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");//driverPhoneNumber="9955112208";

            String[] loadingTimeStamp = com.bungii.android.utilityfunctions.DbUtility.getLoadingTimeStamp(customerPhoneNumber);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //By default data is in UTC
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date loadingStartTime = formatter.parse(loadingTimeStamp[0]);
            Date loadingEtartTime = formatter.parse(loadingTimeStamp[1]);
            long duration = loadingEtartTime.getTime() - loadingStartTime.getTime();
            long loadingTime = TimeUnit.MILLISECONDS.toMinutes(duration);

            String[] driverLocation = com.bungii.android.utilityfunctions.DbUtility.getDriverLocation(driverPhoneNumber);
            String[] pickup1Locations = com.bungii.android.utilityfunctions.DbUtility.getPickupAndDropLocation(customerPhoneNumber);
            String[] pickup2Locations = com.bungii.android.utilityfunctions.DbUtility.getPickupAndDropLocation(customer2PhoneNumber);

            String[] dropLocation = new String[2];
            dropLocation[0] = pickup1Locations[2];
            dropLocation[1] = pickup1Locations[3];
            String[] newPickupLocations = new String[2];
            newPickupLocations[0] = pickup2Locations[0];
            newPickupLocations[1] = pickup2Locations[1];

            long[] timeToCoverDistance = new GoogleMaps().getDurationInTraffic(driverLocation, dropLocation, newPickupLocations);
            logger.detail("timeToCoverDistance [google api call] "+timeToCoverDistance[0]+" and "+timeToCoverDistance[1]);
            int FLUFF_TIME = 4;
            loadingTime = (loadingTime < 1 ? 10 : loadingTime);
            // loadingTime=10;
            logger.detail("loadingTime "+loadingTime);
            Double totalTimeETAtoPickup = (double)loadingTime + (double)timeToCoverDistance[0] / 60 + (double)timeToCoverDistance[1] / 60 + FLUFF_TIME;
            logger.detail("totalTimeETAtoPickup "+totalTimeETAtoPickup);
            Double tripProjectedEndTime = (double)loadingTime + (double) timeToCoverDistance[0] / 60;
            logger.detail("tripProjectedEndTime "+tripProjectedEndTime);
            String tripStartTime = com.bungii.android.utilityfunctions.DbUtility.getStatusTimeStampForStack(customer2PhoneNumber);
            logger.detail("Status Timestamp "+tripStartTime);
            Date tryToFinishTome_Temp = formatter.parse(tripStartTime);
            DateFormat formatterForLocalTimezone = new SimpleDateFormat("hh:mm a");
            formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

            Date tryToFinishTome = new Date(tryToFinishTome_Temp.getTime() +new Double(ONE_MINUTE_IN_MILLIS * new Double(tripProjectedEndTime)).longValue());
            String driverTime = formatterForLocalTimezone.format(tryToFinishTome);

            Date timeStampToCalculateDate = new Date(tryToFinishTome_Temp.getTime() + new Double(ONE_MINUTE_IN_MILLIS * new Double(totalTimeETAtoPickup)).longValue());


            Date minTime = new Date(timeStampToCalculateDate.getTime() + (FROM_RANGE_FROM * ONE_MINUTE_IN_MILLIS));
            String strMindate = formatterForLocalTimezone.format(minTime);

            Date maxTime = new Date(timeStampToCalculateDate.getTime() + (FROM_RANGE_TO * ONE_MINUTE_IN_MILLIS));
            String strMaxdate = formatterForLocalTimezone.format(maxTime);
            cucumberContextManager.setScenarioContext("DRIVER_FINISH_BY", driverTime);
            cucumberContextManager.setScenarioContext("DRIVER_MIN_ARRIVAL", strMindate);
            cucumberContextManager.setScenarioContext("DRIVER_MAX_ARRIVAL", strMaxdate);
            logger.detail("[As Per calculation Of Short Stack for Trip of Customer "+customer2PhoneNumber+"] Driver to Finish By :"+ driverTime + "Range "+FROM_RANGE_FROM+","+FROM_RANGE_TO+"["+strMindate+" : "+strMaxdate+"]");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

   /* public void calculateShortStack() throws ParseException {
        int FROM_RANGE_FROM =-10;
        int FROM_RANGE_TO =+20;

        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        String customerPhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");//customerPhoneNumber="9999991889";
        String customer2PhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");//customer2PhoneNumber="9999991259";
        String driverPhoneNumber = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");//driverPhoneNumber="9955112208";

         String [] loadingTimeStamp=DbUtility.getLoadingTimeStamp(customerPhoneNumber);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //By default data is in UTC
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date loadingStartTime = formatter.parse(loadingTimeStamp[0]);
        Date loadingEtartTime = formatter.parse(loadingTimeStamp[1]);
        long duration = loadingEtartTime.getTime() - loadingStartTime.getTime();
        long loadingTime=TimeUnit.MILLISECONDS.toMinutes(duration);


        String[] driverLocation = DbUtility.getDriverLocation(driverPhoneNumber);
        String [] pickup1Locations=DbUtility.getPickupAndDropLocation(customerPhoneNumber);
        String [] pickup2Locations=DbUtility.getPickupAndDropLocation(customer2PhoneNumber);

        String[] dropLocation = new String[2];dropLocation[0]=pickup1Locations[2];dropLocation[1]=pickup1Locations[3];
        String[] newPickupLocations = new String[2];newPickupLocations[0]=pickup2Locations[0];newPickupLocations[1]=pickup2Locations[1];

        long[]  timeToCoverDistance=new GoogleMaps().getDurationInTraffic(driverLocation,dropLocation,newPickupLocations);
     //   String custRef = DbUtility.getCustomerRefference(phoneNumber);
//
        //=if((C5<1),10,C5)+D5+E5+4
        int FLUFF_TIME=4;
        loadingTime=(loadingTime<1?10:loadingTime);
     //   loadingTime=10;
        long totalTimeETAtoPickup=loadingTime+timeToCoverDistance[0]+timeToCoverDistance[1]+FLUFF_TIME;
        long tripProjectedEndTime=loadingTime+timeToCoverDistance[0];
        String tripStartTime=DbUtility.getStatusTimeStampForStack(customer2PhoneNumber);
        Date tryToFinishTome_Temp = formatter.parse(tripStartTime);
        long ONE_MINUTE_IN_MILLIS=60000;//millisecs
        DateFormat formatterForLocalTimezone = new SimpleDateFormat("hh:mm a");
        formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

        Date tryToFinishTome=new Date(tryToFinishTome_Temp.getTime() + (ONE_MINUTE_IN_MILLIS * tripProjectedEndTime));
        String driverTime = formatterForLocalTimezone.format(tryToFinishTome);

        Date timeStampToCalculateDate=new Date(tryToFinishTome_Temp.getTime() + (ONE_MINUTE_IN_MILLIS * totalTimeETAtoPickup));


        Date minTime=new Date(timeStampToCalculateDate.getTime() + (FROM_RANGE_FROM * ONE_MINUTE_IN_MILLIS));
        String strMindate = formatterForLocalTimezone.format(minTime);

        Date maxTime=new Date(timeStampToCalculateDate.getTime() + (FROM_RANGE_TO * ONE_MINUTE_IN_MILLIS));
        String strMaxdate = formatterForLocalTimezone.format(maxTime);
        cucumberContextManager.setScenarioContext("DRIVER_FINISH_BY",driverTime);
        cucumberContextManager.setScenarioContext("DRIVER_MIN_ARRIVAL",strMindate);
        cucumberContextManager.setScenarioContext("DRIVER_MAX_ARRIVAL",strMaxdate);

    }
*/
    @When("^I click \"([^\"]*)\" on bungii accepted screen$")
    public void i_click_something_on_bungii_accepted_screen(String button) throws Throwable {
        try {
            switch (button) {
                case "CANCEL BUNGII":
                    testStepVerify.isElementTextEquals(bungiiAcceptedPage.Button_CancelBungii(),"CANCEL BUNGII");
                    action.click(bungiiAcceptedPage.Button_CancelBungii());
                    break;
                case "Cantact Support on Alert message":
                    testStepVerify.isElementTextEquals(bungiiAcceptedPage.Alert_ContactSupport(),"Contact Customer Support");
                    action.click(bungiiAcceptedPage.Alert_ContactSupport());
                    break;
                case "CANCEL BUNGII on Alert message":
                    testStepVerify.isElementTextEquals(bungiiAcceptedPage.Alert_CancelBungii(),"Cancel Bungii");
                    action.click(bungiiAcceptedPage.Alert_CancelBungii());
                    break;
                case"Dismiss on Alert message":
                    testStepVerify.isElementTextEquals(bungiiAcceptedPage.Alert_Dismiss(),"Dismiss");
                    action.click(bungiiAcceptedPage.Alert_Dismiss());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I tap on" + button, "I tapped on actionItem"+button, true);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }    }

    @Then("^I see \"([^\"]*)\" on bungii accepted screen$")
    public void     i_see_something_on_bungii_accepted_screen(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Alert: Bungii cancel confirmation":
                    testStepVerify.isElementTextEquals(bungiiAcceptedPage.Text_StackConfirmation(),PropertyUtility.getMessage("customer.stack.cancel.confirm.alert"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
    }


    @When("^I verify that driver to pickup time is greater than 100 mins for second trip$")
    public void i_verify_that_driver_to_pickup_time_is_greater_than_100_mins_for_second_trip() {
        String customer2PhoneNumber=(String)cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
        String driverPhoneNumber=(String)cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
        String custRef = DbUtility.getCustomerRefference(customer2PhoneNumber);
        String pickupID = DbUtility.getPickupID(custRef);
        String pickupRef = DbUtility.getPickupReff(custRef);
        DbUtility.isDriverEligibleForTrip(driverPhoneNumber,pickupRef);
        int driverToPickUP=Integer.valueOf(DbUtility.getDriverToPickupTime(driverPhoneNumber,pickupID));

        testStepVerify.isTrue(driverToPickUP>100,"Driver to pickp value should be greater that 100 ", "Driver to pickup value is "+driverToPickUP +" min","Driver to pickup value is "+driverToPickUP +" min");
    }

    @Then("^I should not get notification for ([^\"]*)$")
    public void i_should_not_get_notification_for_stack_trip(String message) {
            try {
                //   SetupManager.getObject().terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                action.showNotifications();
                log("Checking notifications","Checking notifications",true);
                String expecteMessage = utility.getExpectedNotification(message.toUpperCase());

                boolean isFound = utility.getNofitication("Bungii QAAuto", expecteMessage);
                action.hideNotifications();

               /* boolean isFound = utility.clickOnNofitication("Bungii", expecteMessage);
                if (!isFound) {
                    Thread.sleep(5000);
                    isFound = utility.clickOnNofitication("Bungii", expecteMessage);
                }
                logger.detail(SetupManager.getDriver().getPageSource());
                //stack take times to get notifications
                    for (int i=0; i<0 &&!isFound;i++){
                        Thread.sleep(40000);
                        isFound = utility.clickOnNofitication("Bungii", expecteMessage);
                        i++;
                    }

                //if no notification then hide
                if (!isFound) {
                    action.hideNotifications();
                    Thread.sleep(5000);

                    action.click(otherAppsPage.Status_Bar());

                }*/
                testStepVerify.isFalse(isFound, "I should not get notification "+ message ," I didnt get notification for stack trip","I got notifcation of stack trip");

            } catch (Exception e) {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
            }


    }
    @And("^I wait for Minimum duration for current Bungii to be T-2 hours$")
    public void i_wait_for_minimum_duration_for_something_bungii_to_be_in_t_minus2() {
        try {
/*
            String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");


            DateFormat formatter = new SimpleDateFormat("MMM d, h:mm a");
            formatter.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()));
            Date bungiiDate = formatter.parse(bungiiTime);


            Date currentDate = new Date();
            bungiiDate.setYear(currentDate.getYear());//(Integer.parseInt(currentDate.getYear()));
            long duration = bungiiDate.getTime() - currentDate.getTime();

            long diffInMinutes;
            int mininumWaitTime = 120;

            diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration) - mininumWaitTime;
            //minimum 1  min wait
            diffInMinutes = diffInMinutes + 1;
            if (diffInMinutes > 0) {
                action.hardWaitWithSwipeUp((int) diffInMinutes);
            } else {
                // minimum wait of 30 mins

            }
            */
                  //Commented since it waits more than hour

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should not be able to see \"([^\"]*)\" on screen$")
    public void i_should_not_be_able_to_see_something_on_screen(String option) throws Throwable {
        try {
            switch (option) {
                case "Details From Customer":
                    testStepAssert.isFalse(action.isElementPresent(inProgressBungiiPages.Button_DetailsFromCustomer(true)),"Option should not be present.", "Option is not present.", "Option is present.");
                    break;
            }

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    /**
     * Get Driver Name
     *
     * @return value of customer name
     */
    public String getCustomerName() {

        return action.getText(bungiiProgressPage.Bungii_Customer_Name());
    }

    @When("^Bungii driver uploads \"([^\"]*)\" image$")
    public void bungii_driver_uploads_something_image(String numberofimages) throws Throwable {
        try {
            action.click(inProgressBungiiPages.Button_AddPhoto());
            if (action.isElementPresent(bungiiEstimatePage.Message_CameraPermissions(true)))
                action.click(bungiiEstimatePage.Permissions_CameraAllow());
            Thread.sleep(2000);
            switch (numberofimages){
                case "1":
                    action.click(bungiiEstimatePage.Button_CameraIcon());
                    Thread.sleep(2000);
                    Point p1 = new Point(360,1310);
                    Point p2= new Point(430, 1242);
                    action.click(p1);
                    Thread.sleep(2000);

                    action.click(p2);
                    Thread.sleep(2000);
                    testStepVerify.isElementDisplayed(inProgressBungiiPages.Image_UploadedImage(),"Captured Image should be shown","Captured Image is shown", "Captured Image is not shown");
                    action.click(bungiiProgressPage.Button_Save());
                    break;
            }
        }catch(Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }

    }
    @And("^I calculate the \"([^\"]*)\" time after \"([^\"]*)\"$")
    public void
    i_calculate_the_something_time_after_something(String timeType, String changeType) throws Throwable {
        try{
            switch (changeType){
                case "changed pickup":
                    switch (timeType){
                        case "telet":
//                      TELET = Pickup address Edited time + ((Estimated Duration from Pickup point to drop off point + loading/unloading time) * 1.5)+30
//                      Formula = (TELET + drive time from drop off A to pickup B) - 15 minutes, + 30 minutes
                            String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"); //phoneNumber="9403960189"; c/// Stacked trip will be 2 customer you need of first trip
                            String custRef = DbUtility.getCustomerRefference(phoneNumber);
                            String pickUpID = DbUtility.getPickupID(custRef);
                            String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();

                            String teletTimeInDb = DbUtility.getTELETfromDb(custRef);
                            System.out.println(teletTimeInDb);

                            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            //By default data is in UTC
                            formatter.setTimeZone(TimeZone.getTimeZone("EDT"));
                            formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
                            Date time2 = formatter.parse(teletTimeInDb);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(time2);


                            String teletInLocalTime = String.valueOf(calendar.getTime());
                            cucumberContextManager.setScenarioContext("NEW_TELET",teletInLocalTime);
                            String customerPhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");

                            String[] pickupLoc= DbUtility.getPickupAndDropLocation(customerPhoneNumber);
                            String[] pickup2Locations = DbUtility.getPickupAndDropLocation("9871450107");

                            String[] dropLoc = new String[2];
                            dropLoc[0] = pickupLoc[2];
                            dropLoc[1] = pickupLoc[3];
                            String[] newPickupLocations = new String[2];
                            newPickupLocations[0] = pickup2Locations[0];
                            newPickupLocations[1] = pickup2Locations[1];

                            DateFormat formatter1 = new SimpleDateFormat("HH:mm");
                            long[] timeToCoverDistance2 = new GoogleMaps().getDurationInTraffic(dropLoc, newPickupLocations);
                            logger.detail("timeToCoverDistance [google api call] "+timeToCoverDistance2[0]+" and "+timeToCoverDistance2[1]);
                            int times = (int) (timeToCoverDistance2[0]/60);

//                            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse(teletInLocalTime);
                            String time=teletInLocalTime.substring(11,16);
                            formatter1.format(time);
                            Time timeValue = new Time(formatter.parse(time).getTime());
                            calendar.setTime(timeValue);
                            calendar.add(Calendar.MINUTE, times);
                            calendar.add(Calendar.MINUTE, -15);
                            String lowerRangeInLocalTime = String.valueOf(calendar.getTime());
                            cucumberContextManager.setScenarioContext("PAT_LOWER_RANGE",lowerRangeInLocalTime);

                            calendar.setTime(timeValue);
                            calendar.add(Calendar.MINUTE, 30);
                            String upperRangeInLocalTime = String.valueOf(calendar.getTime());
                            cucumberContextManager.setScenarioContext("PAT_UPPER_RANGE",upperRangeInLocalTime);
                            break;

                    }
                    break;
            }

        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^correct details should do be displayed on (.+) screen for Stack screen$")
    public void correct_details_should_do_be_displayed_on_bungii_accepted_screen_for_stack_screen(String key)  {
        try{
            switch (key.trim()){
                case "BUNGII ACCEPTED with recalculation":
                    String labelOne="";
                    String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

                    if (geofence.equalsIgnoreCase("goa") || geofence.equalsIgnoreCase(""))
                        labelOne =  PropertyUtility.getDataProperties("time.label");
                    else
                        labelOne =  utility.getTimeZoneBasedOnGeofence();
                    String expectedPAT=(String)cucumberContextManager.getScenarioContext("PAT_LOWER_RANGE")+" - "+(String)cucumberContextManager.getScenarioContext("PAT_UPPER_RANGE")+" "+labelOne;
                    expectedPAT=expectedPAT.replace("am", "AM").replace("pm","PM");
                    String actualPAT = action.getText(bungiiAcceptedPage.Textlabel_ProjectedTimeValue());
                    testStepVerify.isTrue(expectedPAT.contains(actualPAT),"Projected arrival Time : "+ actualPAT +" is displayed","Projected arrival Time : "+ actualPAT +" is displayed instead of "+ expectedPAT);
                    break;
            }}
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

}
