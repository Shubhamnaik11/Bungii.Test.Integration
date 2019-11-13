package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.InProgressBungiiPages;
import com.bungii.android.stepdefinitions.Customer.SignupSteps;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.api.utilityFunctions.GoogleMaps;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.*;

public class BungiiInProgressSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    InProgressBungiiPages bungiiProgressPage = new InProgressBungiiPages();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    @Then("^Trip Information should be correctly displayed on \"([^\"]*)\" status screen for driver$")
    public void trip_information_should_be_correctly_displayed_on_something_status_screen_for_customer(String key) {
        try {


            String expectedCustName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            expectedCustName = expectedCustName.substring(0, expectedCustName.indexOf(" ") + 2);
            boolean isCustomerNameCorrect;
            //drivername and customer name validation
            if(String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO")){
                isCustomerNameCorrect=action.getText(bungiiProgressPage.Text_DuoCustomer_Name()).equals(expectedCustName);
                String driver1Name=(String) cucumberContextManager.getScenarioContext("DRIVER_1"),driver2Name=(String) cucumberContextManager.getScenarioContext("DRIVER_2");
                boolean isDriverNameCorrect=action.getText(bungiiProgressPage.Text_DuoDriver_Name()).equals(driver1Name.substring(0,driver1Name.indexOf(" ")+2))||action.getText(bungiiProgressPage.Text_DuoDriver_Name()).equals(driver2Name.substring(0,driver2Name.indexOf(" ")+2));
                logger.detail("driver1Name"+driver1Name.substring(0,driver1Name.indexOf(" ")+2) +"|||Driver 2"+driver2Name.substring(0,driver2Name.indexOf(" ")+2));
                testStepVerify.isTrue(isDriverNameCorrect,
                        "Driver name should correctly display",
                        "Driver name was correctly display",
                        "Driver name was not correctly display");
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
        boolean isETACorrect = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("minute");
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
        boolean isETAdisplayed = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("minute");
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
            testStepVerify.isElementTextEquals(bungiiProgressPage.Text_NextLabel(), "NEXT","'NEXT' text lable should be displayed","'NEXT' text lable is displayed","'NEXT' text lable is not displayed");
            testStepVerify.isElementTextEquals(bungiiProgressPage.Text_OnDeckLabel(), "ON DECK","'NEXT' text lable should be displayed","'NEXT' text lable is displayed","'NEXT' text lable is not displayed");
            testStepVerify.isElementTextEquals(bungiiProgressPage.Text_StackCustomer(), customerName.substring(0, customerName.indexOf(" ") + 2));
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
        testStepVerify.isElementTextEquals(bungiiProgressPage.Text_FinishBy(),"Try to finish by "+((String)cucumberContextManager.getScenarioContext("DRIVER_TELET"))+" "+utility.getTimeZoneBasedOnGeofence());
    }
    @Then("^try to finish time should be correctly displayed for short stack trip$")
    public void try_to_finish_time_should_be_correctly_displayed_ShortStack() throws Throwable {
     //   calculateShortStack();
        testStepVerify.isElementTextEquals(bungiiProgressPage.Text_FinishBy(),"Try to finish by "+((String)cucumberContextManager.getScenarioContext("DRIVER_FINISH_BY"))+" "+utility.getTimeZoneBasedOnGeofence());

    }
    @Then("^I calculate projected driver arrival time$")
    public void i_calculate_projected_driver_arrival_time() throws Throwable {
           calculateShortStack();
    }

    @Then("^correct message should be displayed after clicking info button$")
    public void correct_message_should_be_displayed_after_clicking_info_button() throws Throwable {
        action.click(bungiiProgressPage.Button_StackInfo());
        InProgressBungiiPages Page_DriverBungiiProgress = new InProgressBungiiPages();

        testStepVerify.isElementTextEquals(Page_DriverBungiiProgress.Alert_Message(),PropertyUtility.getMessage("driver.stack.info.button.alert"));
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
        cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "kansas");

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

        int[]  timeToCoverDistance=new GoogleMaps().getDurationInTraffic(driverLocation,dropLocation,newPickupLocations);
     //   String custRef = DbUtility.getCustomerRefference(phoneNumber);
//
        //=if((C5<1),10,C5)+D5+E5+4
        int FLUFF_TIME=4;
        loadingTime=(loadingTime<1?10:loadingTime);
        loadingTime=10;
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
    /**
     * Get Driver Name
     *
     * @return value of customer name
     */
    public String getCustomerName() {

        return action.getText(bungiiProgressPage.Bungii_Customer_Name());
    }
}
