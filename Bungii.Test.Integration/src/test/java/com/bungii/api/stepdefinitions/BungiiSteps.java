package com.bungii.api.stepdefinitions;

import com.bungii.api.utilityFunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bungii.common.manager.ResultManager.*;

public class BungiiSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(BungiiSteps.class);
    AuthServices authServices = new AuthServices();
    CoreServices coreServices = new CoreServices();
    PaymentServices paymentServices = new PaymentServices();
    DriverServices driverServices = new DriverServices();
    CustomerServices customerServices = new CustomerServices();
    com.bungii.android.utilityfunctions.GeneralUtility utility=new com.bungii.android.utilityfunctions.GeneralUtility();
    DbUtility dbUtility = new DbUtility();

    public void givenIamOnSearchingpage() {
        String custPhoneCode = "1", custPhoneNum = "9871450101", custPassword = "Cci12345";
        String driverPhoneCode = "1", driverPhoneNum = "8871450101", driverPassword = "Cci12345";
        //LOGIN
        String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
        String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);

        String driverRef = driverServices.getDriverRef(driverAccessToken);
        String custRef = customerServices.getCustomerRef(custAccessToken);


        //CUSTOMER& DRIVER VIEW
        coreServices.customerView("", custAccessToken);
        coreServices.driverView("", driverAccessToken);

        //update location and driver status
        coreServices.updateDriverLocation(driverAccessToken, "goa");
        coreServices.updateDriverStatus(driverAccessToken);
        //request Bungii
        coreServices.validatePickupRequest(custAccessToken, "goa");
        String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, "goa");
        String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
        coreServices.recalculateEstimate(pickupRequest, "", custAccessToken);
        coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
    }

    public static String getDriverPhone(String driverName) {
        String phone = null;
        switch (driverName) {
            case "valid duo driver 1":
                phone = PropertyUtility.getDataProperties("ios.valid.driver.duo.phone");
                break;
            case "Testdrivertywd_appledc_a_web TestdriverA":
                phone = PropertyUtility.getDataProperties("web.valid.driver1.phone");
                break;
            case "Testdrivertywd_appledc_a_web TestdriverB":
                phone = PropertyUtility.getDataProperties("web.valid.driver2.phone");
                break;
            case "Testdrivertywd_appledc_a_web TestdriverC":
                phone = PropertyUtility.getDataProperties("web.valid.driver3.phone");
                break;
            case "Testdrivertywd_appledc_a_web TestdriverD":
                phone = PropertyUtility.getDataProperties("web.valid.driver4.phone");
                break;
            case "Testdrivertywd_appledc_a_web TestdriverE":
                phone = PropertyUtility.getDataProperties("web.valid.driver5.phone");
                break;
            case "Testdrivertywd_appledc_a_web TestdriverF":
                phone = PropertyUtility.getDataProperties("web.valid.driver6.phone");
                break;
            case "Brad Hilton":
                phone = PropertyUtility.getDataProperties("web.valid.driver2.phone");
                break;

            case "Testdrivertywd_appleks_ra_four Kent":
                phone = PropertyUtility.getDataProperties("android.valid.driver1.phone");
                break;

            case "Testdrivertywd_appleks_rathree Test":
                phone = PropertyUtility.getDataProperties("android.valid.driver2.phone");
                break;

            case "Testdrivertywd_appleks_a_vic Klumm":
                phone = PropertyUtility.getDataProperties("android.valid.driver3.phone");
                break;

            case "Testdrivertywd_appledv_b_matt Stark_dvOnE":
                phone = PropertyUtility.getDataProperties("denver.common.driver.phone");
                break;
            case "Testdrivertywd_appledv_b_seni Stark_dvThree":
                phone = PropertyUtility.getDataProperties("denver.common.driver2.phone");
                break;
            case "testdriver4 Test":
                phone = PropertyUtility.getDataProperties("ios.common.valid.driver.phone");
                break;
            case "Testdrivertywd_appleks_ra_five Test":
                phone = PropertyUtility.getDataProperties("valid.driver.kansas.phone");
                break;
            case "Testdrivertywd_appledc_a_john Smith":
                phone = PropertyUtility.getDataProperties("web.valid.driver101.phone");
                break;
            case "Testdrivertywd_appledc_a_jack Smith":
                phone = PropertyUtility.getDataProperties("web.valid.driver102.phone");
                break;
            case "Macy Chang":
                phone = PropertyUtility.getDataProperties("web.valid.driver103.phone");
                break;
            case "Ethan Edison":
                phone = PropertyUtility.getDataProperties("web.valid.driver104.phone");
                break;
            case "Testdrivertywd_appledc_a_web TestdriverY":
                phone = PropertyUtility.getDataProperties("web.valid.driver105.phone");
                break;
            case "Melvin Johnson":
                phone = PropertyUtility.getDataProperties("web.valid.driver106.phone");
                break;
            case "Testdrivertywd_appledc_a_ptner Driverone":
                phone = PropertyUtility.getDataProperties("web.valid.partner.driver1.phone");
                break;
            case "Testdrivertywd_appledc_a_ronny James":
                phone = PropertyUtility.getDataProperties("web.valid.partner.driver2.phone");
                break;
            case "Testdrivertywd_appledc_a_mate Gate":
                phone = PropertyUtility.getDataProperties("web.valid.partner.driver3.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundara":
                phone = PropertyUtility.getDataProperties("web.valid.driver7.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundarb":
                phone = PropertyUtility.getDataProperties("web.valid.driver8.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundarc":
                phone = PropertyUtility.getDataProperties("web.valid.driver9.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundard":
                phone = PropertyUtility.getDataProperties("web.valid.driver10.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundare":
                phone = PropertyUtility.getDataProperties("web.valid.driver11.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundarf":
                phone = PropertyUtility.getDataProperties("web.valid.driver12.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundarg":
                phone = PropertyUtility.getDataProperties("web.valid.driver13.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundarh":
                phone = PropertyUtility.getDataProperties("web.valid.driver14.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundari":
                phone = PropertyUtility.getDataProperties("web.valid.driver15.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundarj":
                phone = PropertyUtility.getDataProperties("web.valid.driver16.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundark":
                phone = PropertyUtility.getDataProperties("web.valid.driver17.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundarl":
                phone = PropertyUtility.getDataProperties("web.valid.driver18.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundarm":
                phone = PropertyUtility.getDataProperties("web.valid.driver19.phone");
                break;
            case "Testdrivertywd_appledc_a_web Sundarn":
                phone = PropertyUtility.getDataProperties("web.valid.driver20.phone");
                break;
            case "Testdriver_goa_a Android_test":
                phone = PropertyUtility.getDataProperties("driverA.phone.number");
                break;
            case "Testdriver_goa_b Android_test":
                phone = PropertyUtility.getDataProperties("driverB.phone.number");
                break;
            case "Testdriver_goa_c Android_test":
                phone = PropertyUtility.getDataProperties("driverC.phone.number");
                break;
            case "Testdriver_goa_d Android_test":
                phone = PropertyUtility.getDataProperties("driverD.phone.number");
                break;
            case "Driver_goa_e Android_test":
                phone = PropertyUtility.getDataProperties("driverE.phone.number");
                break;
            case "Driver_goa_f Android_test":
                phone = PropertyUtility.getDataProperties("driverF.phone.number");
                break;
            case "Testdrivertywd_appledc_a_drva Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver200.phone");
                break;
            case "Testdrivertywd_appledc_a_drvb Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver201.phone");
                break;
            case "Testdrivertywd_appledc_a_drvc Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver202.phone");
                break;
            case "Testdrivertywd_appledc_a_drvd Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver203.phone");
                break;
            case "Testdrivertywd_appledc_a_drve Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver204.phone");
                break;
            case "Testdrivertywd_appledc_a_drvf Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver205.phone");
                break;
            case "Testdrivertywd_appledc_a_drvg Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver206.phone");
                break;
            case "Testdrivertywd_appledc_a_drvh Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver207.phone");
                break;
            case "Testdrivertywd_appledc_a_drvi Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver208.phone");
                break;
            case "Testdrivertywd_appledc_a_drvj Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver209.phone");
                break;
            case "Testdrivertywd_appledc_a_drvk Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver210.phone");
                break;
            case "Testdrivertywd_appledc_a_drvl Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver211.phone");
                break;
            case "Testdrivertywd_appledc_a_drvm Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver212.phone");
                break;
            case "Testdrivertywd_appledc_a_drvn Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver213.phone");
                break;
            case "Testdrivertywd_appledc_a_drvo Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver214.phone");
                break;
            case "Testdrivertywd_appledc_a_drvp Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver215.phone");
                break;
            case "Testdrivertywd_appledc_a_drvq Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver216.phone");
                break;
            case "Testdrivertywd_appledc_a_drvr Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver217.phone");
                break;
            case "Testdrivertywd_appledc_a_drvs Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver218.phone");
                break;
            case "Testdrivertywd_appledc_a_drvt Driver":
                phone = PropertyUtility.getDataProperties("web.valid.driver219.phone");
                break;
            case "Testdrivertywd_appledc_a_webaa Testdriveraa":
                phone = PropertyUtility.getDataProperties("web.valid.driver1000.phone");
                break;
            case "Testdrivertywd_appledc_a_webkk Testdriverkk":
                phone = PropertyUtility.getDataProperties("web.valid.driver301.phone");
                break;
            case "Testdrivertywd_appledc_a_webll Testdriverll":
                phone = PropertyUtility.getDataProperties("web.valid.driver302.phone");
                break;
            case "Testdrivertywd_appledc_a_webmm Testdrivermm":
                phone = PropertyUtility.getDataProperties("web.valid.driver303.phone");
                break;
            case "Testdrivertywd_appledc_a_webnn Testdrivernn":
                phone = PropertyUtility.getDataProperties("web.valid.driver304.phone");
                break;
            case "Testdrivertywd_appledc_a_weboo Testdriveroo":
                phone = PropertyUtility.getDataProperties("web.valid.driver305.phone");
                break;
            case "Testdrivertywd_appledc_a_webpp Testdriverpp":
                phone = PropertyUtility.getDataProperties("web.valid.driver306.phone");
                break;
                case "Testdrivertywd_appledc_a_webcc Testdrivercc":
                phone = PropertyUtility.getDataProperties("web.valid.driver1002.phone");
                break;
            case "Testdrivertywd_appledc_a_webdd Testdriverdd":
                phone = PropertyUtility.getDataProperties("web.valid.driver1003.phone");
                break;
            case "Testdrivertywd_appledc_a_webee Testdriveree":
                phone = PropertyUtility.getDataProperties("web.valid.driver1004.phone");
                break;
            case "Testdrivertywd_appledc_a_webff Testdriverff":
                phone = PropertyUtility.getDataProperties("web.valid.driver1005.phone");
                break;
            default:
                throw new PendingException("New Driver used which is not added to BungiiSteps.java and login properties file");

        }

                return phone;
    }

    @And("^As a driver \"([^\"]*)\" perform below action with respective \"([^\"]*)\" Delivery$")
    public void as_a_driver_something_perform_below_action_with_respective_something_trip(String driverName, String bungiiType, DataTable data) {
        {
            //Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            List<Map<String, String>> DataList = data.asMaps();

            String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

            if (bungiiType.equalsIgnoreCase("Solo Scheduled Researched") || bungiiType.equalsIgnoreCase("Duo Scheduled Researched")) {
                pickupRequest = new DbUtility().getResarchedPickupReference(pickupRequest); //researched pickup ref
                cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
                bungiiType = bungiiType.replace(" Researched", "");
            }
            cucumberContextManager.setScenarioContext("BUNGII_TYPE", bungiiType);
            cucumberContextManager.setScenarioContext("DRIVER_1", driverName);

            String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";
            String driverAccessToken = "", driver2AccessToken = "";
            //get geofence and pickup request from context
            String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");

            driverPhoneNum = getDriverPhone(driverName);
            driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
            //cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("web.valid.driver.name"));
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            authServices.driverLogin(driverPhoneCode, driverPhoneNum, driverPassword); //Force login dunno why
            driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            coreServices.updateDriverLocation(driverAccessToken, geofence); //to uncomment
            coreServices.updateDriverStatus(driverAccessToken);
            String driver1State = "";
            int i = 0;
            while (i < DataList.size()) {
                try {
                    driver1State = DataList.get(i).get("driver1 state").trim();//status like accepted/enroute etc
                    logger.detail("*** As a driver " + driverName + "(" + driverPhoneNum + ") " + bungiiType + "(" + pickupRequest + ") is being " + driver1State);
                    try{ coreServices.getDriverScheduledPickupList(driverAccessToken);coreServices.driverView("",driverAccessToken);}catch (Exception e){}

                    if (bungiiType.equalsIgnoreCase("SOLO ONDEMAND")) {
                        Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
                        if (!isDriverEligible)
                            logger.detail("Diver should be eligible for on demand trip", "Driver ID is not in eligibleDriver list. Continue.");


                        //for on demand enroute and accepted are same
                        if (driver1State.equalsIgnoreCase("Stacked Pickup Accepted")) {
                            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            coreServices.stackedPickupConfirmation(pickupRequest, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Enroute") || driver1State.equalsIgnoreCase("Accepted")) {
                            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                        } else if (driver1State.equalsIgnoreCase("Arrived")) {
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Loading Item")) {
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Driving To Dropoff")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Unloading Item")) {
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Bungii Completed")) {
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                        }

                    } else if (bungiiType.equalsIgnoreCase("SOLO SCHEDULED") || bungiiType.equalsIgnoreCase("Duo Scheduled")) {
                        if (driver1State.equalsIgnoreCase("Accepted")) {

                            //coreServices.waitForAvailableTrips(driverName + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest);
                            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                        } else if (driver1State.equalsIgnoreCase("Enroute")) {
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Arrived")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Loading Item")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Driving To Dropoff")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Unloading Item")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Bungii Completed")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                        }

                    }
                    i++;
                    pass("As a driver, perform  action on Delivery", "As a driver "+driverName+" perform "+ driver1State+" action on "+bungiiType+" Delivery : "+ pickupRequest);

                } catch (Exception e) {

                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                    error("Step  Should be successful", "Error performing step,Please check logs for more details",
                            true);

                }
            }

        }
    }

    @And("^As a driver \"([^\"]*)\" perform below action with respective \"([^\"]*)\" partner portal trip$")
    public void as_a_driver_something_perform_below_action_with_respective_something_partner_portal_trip(String driverName, String bungiiType, DataTable data) {
        {
            //Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                try {
                    String driver1State = DataList.get(i).get("driver1 state").trim();//status like accepted/enroute etc
                    String pickupRequest = (String) cucumberContextManager.getScenarioContext("pickupRequestPartner");

                    cucumberContextManager.setScenarioContext("BUNGII_TYPE", bungiiType);
                    cucumberContextManager.setScenarioContext("DRIVER_1", driverName);

                    String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";
                    String driverAccessToken = "", driver2AccessToken = "";
                    //get geofence and pickup request from context
                    String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");

                    driverPhoneNum = getDriverPhone(driverName);
                    driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
                    //cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("web.valid.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
                    authServices.driverLogin(driverPhoneCode, driverPhoneNum, driverPassword); //Force login dunno why
                    driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
                    coreServices.updateDriverLocation(driverAccessToken, geofence); //to uncomment
                    coreServices.updateDriverStatus(driverAccessToken);
                    logger.detail("*** As a driver " + driverName + "(" + driverPhoneNum + ") " + bungiiType + "(" + pickupRequest + ") is being " + driver1State);
                    try{ coreServices.getDriverScheduledPickupList(driverAccessToken);coreServices.driverView("",driverAccessToken);}catch (Exception e){}

                    if (bungiiType.equalsIgnoreCase("SOLO SCHEDULED") || bungiiType.equalsIgnoreCase("Duo Scheduled")) {
                        if (driver1State.equalsIgnoreCase("Accepted")) {

                           // coreServices.waitForAvailableTrips(driverName + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest);
                            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                        } else if (driver1State.equalsIgnoreCase("Enroute")) {
                            // coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            // coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                 //           int wait = (int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
/*                            try {
                                while (wait > 1) {
                                    logger.detail("Waiting for " + wait / (60000 * 4) + " minute(s) before Scheduled trip can be started");
                                    Thread.sleep(60000);
                                    wait = wait - 60000 * 4;
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }*/
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Arrived")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Loading Item")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Driving To Dropoff")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Unloading Item")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Bungii Completed")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                        } else if (driver1State.equalsIgnoreCase("Driver Canceled")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 66);
                        }

                    }
                    i++;
                } catch (Exception e) {

                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                    error("Step  Should be successful", "Error performing step,Please check logs for more details",
                            true);

                }
            }

        }
    }

    @And("^As a driver \"([^\"]*)\" and \"([^\"]*)\" perform below action with respective \"([^\"]*)\" partner portal trip$")
    public void as_a_driver_something_and_something_perform_below_action_with_respective_something_partner_portal_trip(String driverAName, String driverBName, String bungiiType, DataTable data) {
        {
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                try {

                    String driver1State = DataList.get(i).get("driver1 state").trim();//status like accepted/enroute etc
                    String driver2State = DataList.get(i).get("driver2 state").trim();//status like accepted/enroute etc

                    String pickupRequest = (String) cucumberContextManager.getScenarioContext("pickupRequest");

                    cucumberContextManager.setScenarioContext("BUNGII_TYPE", bungiiType);
                    cucumberContextManager.setScenarioContext("DRIVER_1", driverAName);
                    cucumberContextManager.setScenarioContext("DRIVER_2", driverBName);

                    String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";
                    String driverAccessToken = "", driver2AccessToken = "";
                    //get geofence and pickup request from context
                    String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");


                    driverPhoneNum = getDriverPhone(driverAName);
                    driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
                    //  cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("web.valid.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
                    authServices.driverLogin(driverPhoneCode, driverPhoneNum, driverPassword); //Force login dunno why
                    driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
                    coreServices.updateDriverLocation(driverAccessToken, geofence);
                    coreServices.updateDriverStatus(driverAccessToken);


                    driver2PhoneNum = getDriverPhone(driverBName);
                    driver2Password = PropertyUtility.getDataProperties("web.valid.common.driver.password");
                    // cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("web.valid.driver2.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);
                    authServices.driverLogin(driver2PhoneCode, driver2PhoneNum, driver2Password);
                    driver2AccessToken = authServices.getDriverToken(driver2PhoneCode, driver2PhoneNum, driver2Password);
                    coreServices.updateDriverLocation(driver2AccessToken, geofence);
                    coreServices.updateDriverStatus(driver2AccessToken);
                    logger.detail("*** As a driver " + driverAName + "(" + driverPhoneNum + ") " + bungiiType + "(" + pickupRequest + ") is being " + driver1State);

                    if (bungiiType.equalsIgnoreCase("Duo Scheduled")) {
                        switch(driver1State){
                            case "Accepted":
                                try {
                                    coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                                    coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                                }
                                catch (Exception e){
                                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                                    error("Step  Should be successful", "Error performing step,Please check logs for more details",
                                            true);
                                }
                                break;
                            case "Enroute":
                                try{
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                                }catch (Exception e){
                                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                                    error("Step  Should be successful", "Error performing step,Please check logs for more details",
                                            true);
                                }
                                break;
                            case "Arrived":
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                                break;
                            case "Loading Item":
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                                break;
                            case "Driving To Dropoff":
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                                break;
                            case "Unloading Item":
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                                break;
                            case "Bungii Completed":
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                                break;
                            /*case "Driver Canceled":
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 66);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                                break;*/
                            default:break;
                        }

                        switch(driver2State){
                            case "Accepted":
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                                break;
                            case "Enroute":
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                                break;
                            case "Arrived":
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                                break;
                            case "Loading Item":
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                                break;
                            case "Driving To Dropoff":
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                                break;
                            case "Unloading Item":
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 27);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                                break;
                            case "Bungii Completed":
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 28);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                                break;
                            /*case "Driver Canceled":
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 66);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                                break;*/
                            default:break;
                        }


                        if (driver1State.equalsIgnoreCase("Driver Canceled")) {
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 66);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        }
                        if (driver2State.equalsIgnoreCase("Driver Canceled")) {
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 66);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                        }


                    }

                    i++;
                } catch (Exception e) {

                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                    error("Step  Should be successful", "Error performing step,Please check logs for more details",
                            true);

                }
            }
        }
    }


    @And("^As a driver \"([^\"]*)\" and \"([^\"]*)\" perform below action with respective \"([^\"]*)\" trip$")
    public void as_a_driver_something_and_something_perform_below_action_with_respective_something_trip(String driverAName, String driverBName, String bungiiType, DataTable data) {
        {
            cucumberContextManager.setScenarioContext("BUNGII_TYPE", bungiiType);
            cucumberContextManager.setScenarioContext("DRIVER_1", driverAName);
            cucumberContextManager.setScenarioContext("DRIVER_2", driverBName);

            String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";
            String driverAccessToken = "", driver2AccessToken = "";
            //get geofence and pickup request from context
            String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");


            driverPhoneNum = getDriverPhone(driverAName);
            driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
            //  cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("web.valid.driver.name"));
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            authServices.driverLogin(driverPhoneCode, driverPhoneNum, driverPassword); //Force login dunno why
            driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);


            driver2PhoneNum = getDriverPhone(driverBName);
            driver2Password = PropertyUtility.getDataProperties("web.valid.common.driver.password");
            // cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("web.valid.driver2.name"));
            cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);
            authServices.driverLogin(driver2PhoneCode, driver2PhoneNum, driver2Password);
            driver2AccessToken = authServices.getDriverToken(driver2PhoneCode, driver2PhoneNum, driver2Password);
            coreServices.updateDriverLocation(driver2AccessToken, geofence);
            coreServices.updateDriverStatus(driver2AccessToken);

            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                try {
                    String driver1State = DataList.get(i).get("driver1 state").trim();//status like accepted/enroute etc
                    String driver2State = DataList.get(i).get("driver2 state").trim();//status like accepted/enroute etc
                    String tripLabel = "", pickupRequest = "";
                    try {
                        tripLabel = DataList.get(i).get("label").trim();
                    } catch (Exception e) {
                    }

                    if (tripLabel.equals(""))
                        pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    else
                        pickupRequest = (String) cucumberContextManager.getFeatureContextContext("PICKUP_REQUEST_" + tripLabel);
                    if (bungiiType.equalsIgnoreCase("Solo Scheduled Researched") || bungiiType.equalsIgnoreCase("Duo Scheduled Researched")) {
                        pickupRequest = new DbUtility().getResarchedPickupReference(pickupRequest); //researched pickup ref
                        cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
                        bungiiType = bungiiType.replace("Researched", "");
                    }

                    logger.detail("*** As a driver " + driverAName + "(" + driverPhoneNum + ") " + bungiiType + "(" + pickupRequest + ") is being " + driver1State);

                    if (bungiiType.equalsIgnoreCase("DUO SCHEDULED")) {

                        if (driver1State.equalsIgnoreCase("Accepted")) {
                            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);coreServices.driverView("",driverAccessToken);}catch (Exception e){}
                          //  coreServices.waitForAvailableTrips(driverAName + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest); //temporary comment
                        }
                        logger.detail("*** As a driver " + driverBName + "(" + driver2PhoneNum + ") " + bungiiType + "(" + pickupRequest + ") is being " + driver2State);

                        if (driver2State.equalsIgnoreCase("Accepted")){
                            try{ coreServices.getDriverScheduledPickupList(driver2AccessToken);coreServices.driverView("",driver2AccessToken);}catch (Exception e){}
                          //  coreServices.waitForAvailableTrips(driverBName + "(" + driver2PhoneNum + ")", driver2AccessToken, pickupRequest);
                            }

                        if (driver2State.equalsIgnoreCase("Accepted")) {
                            try{ coreServices.getDriverScheduledPickupList(driver2AccessToken);coreServices.driverView("",driver2AccessToken);}catch (Exception e){}
                            //  coreServices.waitForAvailableTrips(driverAName + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest); //temporary comment
                        }
                        if (driver1State.equalsIgnoreCase("Accepted")) {
                            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);

                        }

                       boolean  waitedForMinTime = false;
                        if (driver1State.equalsIgnoreCase("Enroute")) {
                            //int wait = (int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                            int wait = Integer.parseInt((String)cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START"));
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                            Thread.sleep(5000); //hardwait for driver2AccessToken to be non control driver
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            if (!driver2State.equalsIgnoreCase("Accepted")) { // new addition
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                            }
                        }
                        if (driver2State.equalsIgnoreCase("Accepted")) {
                            if (!driver1State.equalsIgnoreCase("Enroute")) {
                                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);
                            }
                        }
                        if (driver2State.equalsIgnoreCase("Enroute")) {
                            if (!driver1State.equalsIgnoreCase("Enroute")) {
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);
                                //If Driver 1 is accepted and Driver 2 starts trip
                            }
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                        }
                        if (driver1State.equalsIgnoreCase("Arrived")) {
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        }
                        if (driver2State.equalsIgnoreCase("Arrived")) {

                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                        }

                        if (driver1State.equalsIgnoreCase("Loading Item")) {
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        }
                        if (driver2State.equalsIgnoreCase("Loading Item")) {

                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                        }


                        if (driver1State.equalsIgnoreCase("Driving To Dropoff") ) {
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        }
                        if (driver2State.equalsIgnoreCase("Driving To Dropoff") ) {

                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                        }



                        if (driver1State.equalsIgnoreCase("Unloading item")) {
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        }
                        if (driver2State.equalsIgnoreCase("Unloading item")) {

                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 27);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                        }

                        if (driver1State.equalsIgnoreCase("Bungii Completed")) {
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                            if (!driver2State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);  //Control driver completing trip
                            }
                        }
                        if (driver2State.equalsIgnoreCase("Bungii Completed")) {
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 27);
                            coreServices.updateStatus(pickupRequest, driver2AccessToken, 28);
                            if (driver1State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);  //Control driver completing trip
                            }

                        }
                    }

                    i++;
                } catch (Exception e) {

                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                    error("Step  Should be successful", "Error performing step,Please check logs for more details",
                            true);

                }
            }
        }
    }
    @And("^As a driver \"([^\"]*)\" perform below action with respective \"([^\"]*)\" trip$")
    public void as_a_driver_something_and_something_perform_below_action_with_respective_something_trip(String driverAName, String bungiiType, DataTable data) {
        {
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                try {

                    String driver1State = DataList.get(i).get("driver1 state").trim();//status like accepted/enroute etc
                    String tripLabel = "", pickupRequest = "";
                    try {
                        tripLabel = DataList.get(i).get("label").trim();
                    } catch (Exception e) {
                    }
                    if (tripLabel.equals(""))
                        pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    else
                        pickupRequest = (String) cucumberContextManager.getFeatureContextContext("PICKUP_REQUEST_" + tripLabel);
                    if (bungiiType.equalsIgnoreCase("Solo Scheduled Researched") || bungiiType.equalsIgnoreCase("Duo Scheduled Researched")) {
                        pickupRequest = new DbUtility().getResarchedPickupReference(pickupRequest); //researched pickup ref
                        cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
                        bungiiType = bungiiType.replace("Researched", "");
                    }
                    cucumberContextManager.setScenarioContext("BUNGII_TYPE", bungiiType);
                    cucumberContextManager.setScenarioContext("DRIVER_1", driverAName);

                    String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";
                    String driverAccessToken = "";
                    //get geofence and pickup request from context
                    String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");


                    driverPhoneNum = getDriverPhone(driverAName);
                    driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
                    //  cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("web.valid.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
                    authServices.driverLogin(driverPhoneCode, driverPhoneNum, driverPassword); //Force login dunno why
                    driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
                    coreServices.updateDriverLocation(driverAccessToken, geofence);
                    coreServices.updateDriverStatus(driverAccessToken);

                    logger.detail("*** As a driver " + driverAName + "(" + driverPhoneNum + ") " + bungiiType + "(" + pickupRequest + ") is being " + driver1State);


                        if (driver1State.equalsIgnoreCase("Accepted")) {
                            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);coreServices.driverView("",driverAccessToken);}catch (Exception e){}
                            //  coreServices.waitForAvailableTrips(driverAName + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest); //temporary comment
                        }

                        if (driver1State.equalsIgnoreCase("Accepted")) {
                            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                        }

                        boolean waitedForMinTime = false;
                        if (driver1State.equalsIgnoreCase("Enroute")) {
                            //int wait = (int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                            int wait = Integer.parseInt((String)cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START"));
                            try {

                                logger.detail("Waiting for " + wait / 60000 + " minutes before Scheduled trip can be started");
                                //from sprint 32 min time is changed to  1 hour
                                //Thread.sleep(wait);
                                Thread.sleep(1000);
                                waitedForMinTime = true;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        }

                        if (driver1State.equalsIgnoreCase("Arrived")) {
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        }


                        if (driver1State.equalsIgnoreCase("Loading Item")) {

                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        }



                        if (driver1State.equalsIgnoreCase("Driving To Dropoff") ) {

                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        }


                        if (driver1State.equalsIgnoreCase("Unloading item")) {

                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        }


                        if (driver1State.equalsIgnoreCase("Bungii Completed")) {

                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                        }



                    i++;
                } catch (Exception e) {

                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                    error("Step  Should be successful", "Error performing step,Please check logs for more details",
                            true);

                }
            }
        }
    }
    @And("^As a driver \"([^\"]*)\" perform below action with other \"([^\"]*)\" trip$")
    public void as_a_driver_something_and_something_perform_below_action_with_other_something_trip(String driverAName, String bungiiType, DataTable data) {
        {
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                try {

                    String driver1State = DataList.get(i).get("driver1 state").trim();//status like accepted/enroute etc
                    String tripLabel = "", pickupRequest = "";
                    try {
                        tripLabel = DataList.get(i).get("label").trim();
                    } catch (Exception e) {
                    }
                    if (tripLabel.equals(""))
                        pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST2");
                    else
                        pickupRequest = (String) cucumberContextManager.getFeatureContextContext("PICKUP_REQUEST2_" + tripLabel);
                    if (bungiiType.equalsIgnoreCase("Solo Scheduled Researched") || bungiiType.equalsIgnoreCase("Duo Scheduled Researched")) {
                        pickupRequest = new DbUtility().getResarchedPickupReference(pickupRequest); //researched pickup ref
                        cucumberContextManager.setScenarioContext("PICKUP_REQUEST2", pickupRequest);
                        bungiiType = bungiiType.replace("Researched2", "");
                    }
                    cucumberContextManager.setScenarioContext("BUNGII_TYPE2", bungiiType);
                    cucumberContextManager.setScenarioContext("DRIVER_2", driverAName);

                    String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";
                    String driverAccessToken = "";
                    //get geofence and pickup request from context
                    String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");


                    driverPhoneNum = getDriverPhone(driverAName);
                    driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
                    //  cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("web.valid.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driverPhoneNum);
                    authServices.driverLogin(driverPhoneCode, driverPhoneNum, driverPassword); //Force login dunno why
                    driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
                    coreServices.updateDriverLocation(driverAccessToken, geofence);
                    coreServices.updateDriverStatus(driverAccessToken);

                    logger.detail("*** As a driver " + driverAName + "(" + driverPhoneNum + ") " + bungiiType + "(" + pickupRequest + ") is being " + driver1State);


                    if (driver1State.equalsIgnoreCase("Accepted")) {
                        try{ coreServices.getDriverScheduledPickupList(driverAccessToken);coreServices.driverView("",driverAccessToken);}catch (Exception e){}
                        //  coreServices.waitForAvailableTrips(driverAName + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest); //temporary comment
                    }

                    if (driver1State.equalsIgnoreCase("Accepted")) {
                        coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                    }

                    boolean waitedForMinTime = false;
                    if (driver1State.equalsIgnoreCase("Enroute")) {
                        //int wait = (int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                        int wait = Integer.parseInt((String)cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START"));
                        try {

                            logger.detail("Waiting for " + wait / 60000 + " minutes before Scheduled trip can be started");
                            //from sprint 32 min time is changed to  1 hour
                            //Thread.sleep(wait);
                            Thread.sleep(1000);
                            waitedForMinTime = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                        coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                    }

                    if (driver1State.equalsIgnoreCase("Arrived")) {
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                        coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                    }


                    if (driver1State.equalsIgnoreCase("Loading Item")) {

                        coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                        coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                    }



                    if (driver1State.equalsIgnoreCase("Driving To Dropoff") ) {

                        coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                        coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                    }


                    if (driver1State.equalsIgnoreCase("Unloading item")) {

                        coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                        coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                    }


                    if (driver1State.equalsIgnoreCase("Bungii Completed")) {

                        coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                    }



                    i++;
                } catch (Exception e) {

                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                    error("Step  Should be successful", "Error performing step,Please check logs for more details",
                            true);

                }
            }
        }
    }
    @When("^I request another \"([^\"]*)\" Bungii as a customer in \"([^\"]*)\" geofence$")
    public void i_request_another_something_bungii_as_a_customer_in_something_geofence(String bungiiType, String geofence, DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String customerLabel = "";
            try {
                customerLabel = dataMap.get("Customer label").trim();
                logger.detail("customerLabel is specified as input" + customerLabel);
            } catch (Exception e) {
            }

            String bungiiTime = dataMap.get("Bungii Time").trim();
            String customer = dataMap.get("Customer Phone").trim();
            String customerName = dataMap.get("Customer Name").trim();

            cucumberContextManager.setScenarioContext("Bungii_Type",bungiiType);
            String customerPasswordLabel = "";
            try {
                customerPasswordLabel = dataMap.get("Customer Password").trim();
            } catch (Exception e) {
            }

            int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "";

            custPhoneNum = customer;// PropertyUtility.getDataProperties("web.customer.user");
            if (custPhoneNum.equalsIgnoreCase("NEW_USER_NUMBER"))
                custPhoneNum = (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");

            if (customerPasswordLabel.equals(""))
                custPassword = PropertyUtility.getDataProperties("web.customer.password");
            else
                custPassword = customerPasswordLabel;

/*            cucumberContextManager.setScenarioContext("CUSTOMER", customerName);//PropertyUtility.getDataProperties("web.customer.name"));
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);*/

            cucumberContextManager.setScenarioContext("LATEST_LOGGEDIN_CUSTOMER_NAME", customerName);
            if (customerLabel.equalsIgnoreCase("")) {
                cucumberContextManager.setScenarioContext("CUSTOMER2", customerName);
                cucumberContextManager.setScenarioContext("CUSTOMER2_PHONE", custPhoneNum);
            } else {
                cucumberContextManager.setScenarioContext("CUSTOMER" + customerLabel, customerName);
                cucumberContextManager.setScenarioContext("CUSTOMER" + customerLabel + "_PHONE", custPhoneNum);
            }
            cucumberContextManager.setScenarioContext("CUSTOMER2_PUSH", custPhoneNum);

            cucumberContextManager.setScenarioContext("GEOFENCE", geofence);
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence);
            logger.detail("*** Requesting second " + bungiiType + " as a customer " + customerName + "(" + custPhoneNum + ") for geofence " + geofence + " ***");

            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String custRef = customerServices.getCustomerRef(custAccessToken);

            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            cucumberContextManager.setScenarioContext("CUSTOMER_TOKEN", custAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, numberOfDriver, geofence);
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST2", pickupRequest);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            if (customerLabel.equalsIgnoreCase(""))
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            else
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken, customerLabel);

            if (bungiiType.equalsIgnoreCase("Solo Ondemand")) {
                coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            }
            else if(bungiiTime.equalsIgnoreCase("TELET SAME TIME")
                    || bungiiTime.equalsIgnoreCase("TELET OVERLAP")){
                String teletTime=(String)cucumberContextManager.getScenarioContext("TELET");
                cucumberContextManager.setScenarioContext("TELET_TYPE",bungiiTime);
                coreServices.customerConfirmationScheduledForTelet(pickupRequest, paymentMethod, custAccessToken, teletTime);
            }
            else{
                int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, customerLabel);
                cucumberContextManager.setScenarioContext("MIN_WAIT_BUNGII_START", wait);
            }
            cucumberContextManager.setFeatureContextContext("BUNGII_INITIAL_SCH_TIME", System.currentTimeMillis() / 1000L);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pass("I should be able to request bungii ", "Requested second " + bungiiType + " trip as a customer " + customerName + "(" + custPhoneNum + ") for geofence " + geofence +" Pickup Reference : "+ pickupRequest);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @When("^I request \"([^\"]*)\" Bungii as a customer in \"([^\"]*)\" geofence$")
    public void i_request_something_bungii_as_a_customer_in_something_geofence(String bungiiType, String geofence, DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String customerLabel = "";
            try {
                customerLabel = dataMap.get("Customer label").trim();
                logger.detail("customerLabel is specified as input" + customerLabel);
            } catch (Exception e) {
            }

            String bungiiTime = dataMap.get("Bungii Time").trim();
            String customer = dataMap.get("Customer Phone").trim();
            String customerName = dataMap.get("Customer Name").trim();

            cucumberContextManager.setScenarioContext("Bungii_Type",bungiiType);
            cucumberContextManager.setScenarioContext("BUNGII_TYPE",bungiiType);
            String customerPasswordLabel = "";
            try {
                customerPasswordLabel = dataMap.get("Customer Password").trim();
            } catch (Exception e) {
            }

            int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "";

            custPhoneNum = customer;// PropertyUtility.getDataProperties("web.customer.user");
            if (custPhoneNum.equalsIgnoreCase("NEW_USER_NUMBER"))
                custPhoneNum = (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");

            if (customerPasswordLabel.equals(""))
                custPassword = PropertyUtility.getDataProperties("web.customer.password");
            else
                custPassword = customerPasswordLabel;

/*            cucumberContextManager.setScenarioContext("CUSTOMER", customerName);//PropertyUtility.getDataProperties("web.customer.name"));
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);*/

            cucumberContextManager.setScenarioContext("LATEST_LOGGEDIN_CUSTOMER_NAME", customerName);
            if (customerLabel.equalsIgnoreCase("")) {
                cucumberContextManager.setScenarioContext("CUSTOMER", customerName);
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            } else {
                cucumberContextManager.setScenarioContext("CUSTOMER" + customerLabel, customerName);
                cucumberContextManager.setScenarioContext("CUSTOMER" + customerLabel + "_PHONE", custPhoneNum);
            }
            cucumberContextManager.setScenarioContext("CUSTOMER_PUSH", custPhoneNum);

            cucumberContextManager.setScenarioContext("GEOFENCE", geofence);
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence);
            logger.detail("*** Requesting " + bungiiType + " as a customer " + customerName + "(" + custPhoneNum + ") for geofence " + geofence + " ***");

            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String custRef = customerServices.getCustomerRef(custAccessToken);

            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            cucumberContextManager.setScenarioContext("CUSTOMER_TOKEN", custAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, numberOfDriver, geofence);
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            if (customerLabel.equalsIgnoreCase(""))
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            else
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken, customerLabel);

            if (bungiiType.equalsIgnoreCase("Solo Ondemand")) {
                coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            }
            else if(bungiiTime.equalsIgnoreCase("TELET SAME TIME")
                     || bungiiTime.equalsIgnoreCase("TELET OVERLAP")){
                String teletTime=(String)cucumberContextManager.getScenarioContext("TELET");
                cucumberContextManager.setScenarioContext("TELET_TYPE",bungiiTime);
                coreServices.customerConfirmationScheduledForTelet(pickupRequest, paymentMethod, custAccessToken, teletTime);
            }
            else if(bungiiTime.equalsIgnoreCase("3_DAY_LATER"))
            {
                coreServices.customerConfirmationScheduledForFuture(pickupRequest, paymentMethod, custAccessToken, getDaysLaterTime(3).toString());
            }
            else{
                int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, customerLabel);
                cucumberContextManager.setScenarioContext("MIN_WAIT_BUNGII_START", wait);
            }
            cucumberContextManager.setFeatureContextContext("BUNGII_INITIAL_SCH_TIME", System.currentTimeMillis() / 1000L);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pass("I should be able to request bungii ", "Requested " + bungiiType + " trip as a customer " + customerName + "(" + custPhoneNum + ") for geofence " + geofence +" Pickup Reference : "+ pickupRequest);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @When("^I request \"([^\"]*)\" Bungii as a customer in \"([^\"]*)\" geofence with minimum possible distance$")
    public void i_request_something_bungii_as_a_customer_in_something_geofence_with_minimum_possible_distance(String bungiiType, String geofence, DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String customerLabel = "";
            try {
                customerLabel = dataMap.get("Customer label").trim();
                logger.detail("customerLabel is specified as input" + customerLabel);
            } catch (Exception e) {
            }

            String bungiiTime = dataMap.get("Bungii Time").trim();
            String customer = dataMap.get("Customer Phone").trim();
            String customerName = dataMap.get("Customer Name").trim();

            cucumberContextManager.setScenarioContext("Bungii_Type",bungiiType);
            String customerPasswordLabel = "";
            try {
                customerPasswordLabel = dataMap.get("Customer Password").trim();
            } catch (Exception e) {
            }

            int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "";

            custPhoneNum = customer;// PropertyUtility.getDataProperties("web.customer.user");
            if (custPhoneNum.equalsIgnoreCase("NEW_USER_NUMBER"))
                custPhoneNum = (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");

            if (customerPasswordLabel.equals(""))
                custPassword = PropertyUtility.getDataProperties("web.customer.password");
            else
                custPassword = customerPasswordLabel;

/*            cucumberContextManager.setScenarioContext("CUSTOMER", customerName);//PropertyUtility.getDataProperties("web.customer.name"));
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);*/


            if (customerLabel.equalsIgnoreCase("")) {
                cucumberContextManager.setScenarioContext("CUSTOMER", customerName);
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            } else {
                cucumberContextManager.setScenarioContext("CUSTOMER" + customerLabel, customerName);
                cucumberContextManager.setScenarioContext("CUSTOMER" + customerLabel + "_PHONE", custPhoneNum);
            }
            cucumberContextManager.setScenarioContext("CUSTOMER_PUSH", custPhoneNum);

            cucumberContextManager.setScenarioContext("GEOFENCE", geofence);
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence);
            logger.detail("*** Requesting " + bungiiType + " as a customer " + customerName + "(" + custPhoneNum + ") for geofence " + geofence + " ***");

            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String custRef = customerServices.getCustomerRef(custAccessToken);

            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            cucumberContextManager.setScenarioContext("CUSTOMER_TOKEN", custAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequestWithZeroDistance(custAccessToken, numberOfDriver, geofence);
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            if (customerLabel.equalsIgnoreCase(""))
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            else
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken, customerLabel);

            if (bungiiType.equalsIgnoreCase("Solo Ondemand")) {
                coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            }
            else if(bungiiTime.equalsIgnoreCase("TELET SAME TIME")
                    || bungiiTime.equalsIgnoreCase("TELET OVERLAP")){
                String teletTime=(String)cucumberContextManager.getScenarioContext("TELET");
                cucumberContextManager.setScenarioContext("TELET_TYPE",bungiiTime);
                coreServices.customerConfirmationScheduledForTelet(pickupRequest, paymentMethod, custAccessToken, teletTime);
            }
            else if(bungiiTime.equalsIgnoreCase("3_DAY_LATER"))
            {
                coreServices.customerConfirmationScheduledForTelet(pickupRequest, paymentMethod, custAccessToken, getDaysLaterTime(3).toString());
            }
            else{
                int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, customerLabel);
                cucumberContextManager.setScenarioContext("MIN_WAIT_BUNGII_START", wait);
            }
            cucumberContextManager.setFeatureContextContext("BUNGII_INITIAL_SCH_TIME", System.currentTimeMillis() / 1000L);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pass("I should be able to request bungii ", "Requested " + bungiiType + " trip as a customer " + customerName + "(" + custPhoneNum + ") for geofence " + geofence +" Pickup Reference : "+ pickupRequest);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    public void createTripAndSaveInFeatureContext(String bungiiType, String geofence, String customer, String customerName, String customerPasswordLabel, String scenarioLabel) {
        try {
            logger.detail("Inside before hook");
            scenarioLabel = "_" + scenarioLabel;
            int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "";

            custPhoneNum = customer;// PropertyUtility.getDataProperties("web.customer.user");
            if (customerPasswordLabel.equals(""))
                custPassword = PropertyUtility.getDataProperties("web.customer.password");
            else
                custPassword = customerPasswordLabel;

            cucumberContextManager.setFeatureContextContext("CUSTOMER" + scenarioLabel, customerName);//PropertyUtility.getDataProperties("web.customer.name"));
            cucumberContextManager.setFeatureContextContext("CUSTOMER_PHONE" + scenarioLabel, custPhoneNum);
            cucumberContextManager.setFeatureContextContext("GEOFENCE" + scenarioLabel, geofence);
            cucumberContextManager.setFeatureContextContext("BUNGII_GEOFENCE" + scenarioLabel, geofence);
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence);

            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String custRef = customerServices.getCustomerRef(custAccessToken);

            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, numberOfDriver, geofence);
            cucumberContextManager.setFeatureContextContext("PICKUP_REQUEST" + scenarioLabel, pickupRequest);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF" + scenarioLabel), custAccessToken);
            if (bungiiType.equalsIgnoreCase("Solo Ondemand"))
                coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            else {
                int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
                cucumberContextManager.setFeatureContextContext("MIN_WAIT_BUNGII_START" + scenarioLabel, wait);
                cucumberContextManager.setFeatureContextContext("BUNGII_INITIAL_SCH_TIME" + scenarioLabel, System.currentTimeMillis() / 1000L);

            }
            cucumberContextManager.setFeatureContextContext("BUNGII_TIME" + scenarioLabel, cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //  log("I should able to request bungii ", "I requested "+bungiiType+" for '" + geofence+"'", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }

    }

    @Given("^I have already scheduled bungii with \"([^\"]*)\" label$")
    public void i_have_already_scheduled_bungii_with_something_label(String scenarioLabel) {
        logger.detail("cucumberContextManager" + cucumberContextManager.toString());
        testStepAssert.isTrue(!((String) cucumberContextManager.getFeatureContextContext("PICKUP_REQUEST" + "_" + scenarioLabel)).equals(""), "I should have already scheduled bungii", "I should have already scheduled bungii,pickid" + (String) cucumberContextManager.getFeatureContextContext("PICKUP_REQUEST" + scenarioLabel));
    }
    @Given("that duo schedule bungii is scheduled")
    public void thatduoScheduleBungiiIsInScheduled(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String geofence = dataMap.get("geofence").trim();
            String scheduleTime = dataMap.get("Bungii Time").trim();

            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence.toLowerCase());

            String state = dataMap.get("Bungii State").trim();
            String customer = dataMap.get("Customer").trim();
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";

            if (PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID")) {

                if (customer.equalsIgnoreCase("valid atlanta")) {
                    custPhoneNum = PropertyUtility.getDataProperties("atlanta.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("atlanta.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer.name"));

                }
            }
            cucumberContextManager.setScenarioContext("CUSTOMER_PUSH", custPhoneNum);
            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 2, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            int wait = 0;

            if (scheduleTime.equalsIgnoreCase("1 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 60);
            else if (scheduleTime.equalsIgnoreCase("2 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 120);
            else if (scheduleTime.equalsIgnoreCase("0.75 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 45);
            else if (scheduleTime.equalsIgnoreCase("0.5 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 30);
            else if (scheduleTime.equalsIgnoreCase("15 min ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 15);
            else
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);

            //log("that duo schedule bungii is scheduled", "that duo schedule bungii is scheduled : "+  pickupRequest, false);
            pass("Precondition: Given that duo schedule bungii is scheduled  ", "Duo Schedule Bungii [ "+pickupRequest+" ]  by customer " +custPhoneNum+" is scehduled for "+ scheduleTime);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @And("^I accept and complete \"([^\"]*)\" geofence trip of \"([^\"]*)\" customer as a \"([^\"]*)\" and \"([^\"]*)\" driver$")
    public void i_accept_and_complete_something_geofence_trip_of_something_customer_as_a_something_and_something_driver(String geofence, String customer, String driver1, String driver2) throws Throwable {  try {


        String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";

        if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {

            if (customer.equalsIgnoreCase("customer-duo")) {
                custPhoneNum = PropertyUtility.getDataProperties("customer.phone.usedin.duo");
                custPassword = PropertyUtility.getDataProperties("customer.password.usedin.duo");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name.usedin.duo"));

            } else if (customer.equalsIgnoreCase("Kansas customer")) {
                custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer2.phone");
                custPassword = PropertyUtility.getDataProperties("Kansas.customer.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));
            } else if (customer.equalsIgnoreCase("denver customer")) {
                custPhoneNum = PropertyUtility.getDataProperties("denver.customer.phone");
                custPassword = PropertyUtility.getDataProperties("denver.customer.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));

            } else {
                custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                custPassword = PropertyUtility.getDataProperties("customer.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
            }
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);

            driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.duo.phone");
            driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.duo.password");
            cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.duo.name"));
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);

            driver2PhoneNum = PropertyUtility.getDataProperties("ios.valid.driver2.phone");
            driver2Password = PropertyUtility.getDataProperties("ios.valid.driver2.password");
            cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("ios.driver2.name"));
            cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);

        } else if (customer.equalsIgnoreCase("Kansas customer")) {
            custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer2.phone");
            custPassword = PropertyUtility.getDataProperties("Kansas.customer2.password");
            cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer2.name"));
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
        } else {
            custPhoneNum = PropertyUtility.getDataProperties("atlanta.customer.phone");
            custPassword = PropertyUtility.getDataProperties("atlanta.customer.password");
            cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer.name"));
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);


            driverPhoneNum = PropertyUtility.getDataProperties("atlanta.driver.phone");
            driverPassword = PropertyUtility.getDataProperties("atlanta.driver.password");
            cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.driver.name"));
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);

            driver2PhoneNum = PropertyUtility.getDataProperties("valid.driver2.phone");
            driver2Password = PropertyUtility.getDataProperties("valid.driver2.password");
            cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("valid.driver2.name"));
            cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);

        }
        if (driver1.equalsIgnoreCase("Kansas driver 1")) {
            driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver.phone");
            driverPassword = PropertyUtility.getDataProperties("Kansas.driver.password");
            cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver.name"));
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
        }
        if (driver2.equalsIgnoreCase("Kansas driver 2")) {
            driver2PhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
            driver2Password = PropertyUtility.getDataProperties("Kansas.driver2.password");
            cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("Kansas.driver2.name"));
        }
        if (driver1.equalsIgnoreCase("denver driver 1")) {
            driverPhoneNum = PropertyUtility.getDataProperties("denver.driver.phone");
            driverPassword = PropertyUtility.getDataProperties("denver.driver.password");
            cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
        }
        if (driver2.equalsIgnoreCase("denver driver 2")) {
            driver2PhoneNum = PropertyUtility.getDataProperties("denver.driver2.phone");
            driver2Password = PropertyUtility.getDataProperties("denver.driver2.password");
            cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("denver.driver2.name"));
            cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driverPhoneNum);
        }

            //LOGIN
            String custAccessToken = new DbUtility().getCustomerCurrentToken(custPhoneNum);
            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            String driver2AccessToken = authServices.getDriverToken(driver2PhoneCode, driver2PhoneNum, driver2Password);

            String driverRef = driverServices.getDriverRef(driverAccessToken);
            String driver2Ref = driverServices.getDriverRef(driver2AccessToken);
            String custRef = customerServices.getCustomerRef(custAccessToken);
        //update location and driver status
        coreServices.updateDriverLocation(driverAccessToken, geofence);
        coreServices.updateDriverStatus(driverAccessToken);
        coreServices.updateDriverLocation(driver2AccessToken, geofence);
        coreServices.updateDriverStatus(driver2AccessToken);

            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);coreServices.driverView("",driverAccessToken);}catch (Exception e){}
            try{ coreServices.getDriverScheduledPickupList(driver2AccessToken);coreServices.driverView("",driver2AccessToken);}catch (Exception e){}

        Response response= new CoreServices().getCustomersScheduledPickupList(custAccessToken);
        JsonPath jsonPathEvaluator = response.jsonPath();
        ArrayList ScheduledPickups = jsonPathEvaluator.get("ScheduledPickups");
        String pickupRequest = "";
        if (ScheduledPickups != null) {
            for (int i = 0; i < ScheduledPickups.size(); i++) {
                HashMap pickupDetails = (HashMap) ScheduledPickups.get(i);
                 pickupRequest = (String) pickupDetails.get("PickupRef");
                 break;
            }
        }

        //Temporary commented
           // coreServices.waitForAvailableTrips(cucumberContextManager.getScenarioContext("DRIVER_1") + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest);
           // coreServices.waitForAvailableTrips(cucumberContextManager.getScenarioContext("DRIVER_2") + "(" + driver2PhoneNum + ")", driver2AccessToken, pickupRequest);


                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);

                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    //  Thread.sleep(wait);
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 27);
                coreServices.driverView(pickupRequest, driverAccessToken);
                coreServices.driverView(pickupRequest, driver2AccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 28);

         response = coreServices.customerView(pickupRequest, custAccessToken);;
         jsonPathEvaluator = response.jsonPath();
         String discountValue = jsonPathEvaluator.get("PickupDetails.Actual.DiscountValue").toString();
        cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", discountValue);
        String actualCost = jsonPathEvaluator.get("PickupDetails.Actual.Cost").toString();
        cucumberContextManager.setScenarioContext("BUNGII_COST_CUSTOMER", actualCost);

        coreServices.driverView(pickupRequest, driverAccessToken);

                String driverPaymentMethod = coreServices.driverPaymentMethod(pickupRequest, driverAccessToken);
                String driver2PaymentMethod = coreServices.driverPaymentMethod(pickupRequest, driver2AccessToken);

                coreServices.rateAndTip(pickupRequest, custAccessToken, driverRef, driverPaymentMethod, 5.0, 0.0, driver2Ref, driver2PaymentMethod);
                System.out.println(pickupRequest);

            pass("Driver should accept and complete the trip", "Driver [ "+driverPhoneNum+" ] accepts and completes delivery [ "+pickupRequest+" ] ", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Given("that duo schedule bungii is in accepted by controlled driver")
    public void thatduoScheduleBungiiIsInProgressByControlled(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String geofence = dataMap.get("geofence").trim();
            String scheduleTime = dataMap.get("Bungii Time").trim();
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence.toLowerCase());

            String state = dataMap.get("Bungii State").trim();
            String customer = dataMap.get("Customer").trim();
            String driver1 = dataMap.get("Driver1").trim();
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";

            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {

                if (customer.equalsIgnoreCase("customer-duo")) {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.phone.usedin.duo");
                    custPassword = PropertyUtility.getDataProperties("customer.password.usedin.duo");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name.usedin.duo"));

                } else if (customer.equalsIgnoreCase("Kansas customer")) {
                    custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer2.phone");
                    custPassword = PropertyUtility.getDataProperties("Kansas.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));
                } else if (customer.equalsIgnoreCase("denver customer")) {
                    custPhoneNum = PropertyUtility.getDataProperties("denver.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));

                } else {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                    custPassword = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                }
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);

                driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.duo.phone");
                driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.duo.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.duo.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);

                driver2PhoneNum = PropertyUtility.getDataProperties("ios.valid.driver2.phone");
                driver2Password = PropertyUtility.getDataProperties("ios.valid.driver2.password");
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("ios.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);

            } else if (customer.equalsIgnoreCase("Kansas customer")) {
                custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer2.phone");
                custPassword = PropertyUtility.getDataProperties("Kansas.customer2.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer2.name"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            } else {
                custPhoneNum = PropertyUtility.getDataProperties("atlanta.customer.phone");
                custPassword = PropertyUtility.getDataProperties("atlanta.customer.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer.name"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);


                driverPhoneNum = PropertyUtility.getDataProperties("atlanta.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("atlanta.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);

                driver2PhoneNum = PropertyUtility.getDataProperties("valid.driver2.phone");
                driver2Password = PropertyUtility.getDataProperties("valid.driver2.password");
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("valid.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);

            }
            if (driver1.equalsIgnoreCase("Kansas driver 1")) {
                driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("Kansas.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            }

            if (driver1.equalsIgnoreCase("denver driver 1")) {
                driverPhoneNum = PropertyUtility.getDataProperties("denver.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("denver.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            }

            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            //  if(!driver2.equalsIgnoreCase("NA")) {
            //  }

           // String driverRef = driverServices.getDriverRef(driverAccessToken);



            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //CUSTOMER& DRIVER VIEW
            //  coreServices.customerView("",custAccessToken);
            //   coreServices.driverView("",driverAccessToken);

            //update location and driver status
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 2, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            //int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
            int wait = 0;
            if(TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date()))
            {
                if (scheduleTime.equalsIgnoreCase("1 hour ahead"))
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 60);
                else if (scheduleTime.equalsIgnoreCase("2 hour ahead"))
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 120);
                else if (scheduleTime.equalsIgnoreCase("0.75 hour ahead"))
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 45);
                else if (scheduleTime.equalsIgnoreCase("0.5 hour ahead"))
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 30);
                else if (scheduleTime.equalsIgnoreCase("15 min ahead"))
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 15);
                else
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
            }
            else
            {
                if (scheduleTime.equalsIgnoreCase("1 hour ahead"))
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 60);
                else if (scheduleTime.equalsIgnoreCase("2 hour ahead"))
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 120);
                else if (scheduleTime.equalsIgnoreCase("0.75 hour ahead"))
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 45);
                else if (scheduleTime.equalsIgnoreCase("0.5 hour ahead"))
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 30);
                else if (scheduleTime.equalsIgnoreCase("15 min ahead"))
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 15);
                else
                    wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);coreServices.driverView("",driverAccessToken);}catch (Exception e){}

            //  coreServices.waitForAvailableTrips(cucumberContextManager.getScenarioContext("DRIVER_1") + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest);
            // coreServices.waitForAvailableTrips(cucumberContextManager.getScenarioContext("DRIVER_2") + "(" + driver2PhoneNum + ")", driver2AccessToken, pickupRequest);


            if (state.equalsIgnoreCase("Accepted")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);

            } else if (state.equalsIgnoreCase("Scheduled")) {

            } else if (state.equalsIgnoreCase("enroute")) {

                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);


            }
            pass("Precondition: Given that duo schedule bungii is in progress ", "Duo Schedule Bungii [ "+pickupRequest+" ]  by customer " +custPhoneNum+" is in " + state);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Given("that duo schedule bungii is in progress")
    public void thatduoScheduleBungiiIsInProgress(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String geofence = dataMap.get("geofence").trim();
                String scheduleTime = dataMap.get("Bungii Time").trim();
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence.toLowerCase());

            String state = dataMap.get("Bungii State").trim();
            String customer = dataMap.get("Customer").trim();
            String driver1 = "";
            String driver2 ="";
            if (!state.equalsIgnoreCase("Requested")) {
                 driver1 = dataMap.get("Driver1").trim();
                 driver2 = dataMap.get("Driver2").trim();
            }
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";

            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {

                if (customer.equalsIgnoreCase("customer-duo")) {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.phone.usedin.duo");
                    custPassword = PropertyUtility.getDataProperties("customer.password.usedin.duo");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name.usedin.duo"));

                } else if (customer.equalsIgnoreCase("Kansas customer")) {
                    custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer2.phone");
                    custPassword = PropertyUtility.getDataProperties("Kansas.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));
                } else if (customer.equalsIgnoreCase("denver customer")) {
                    custPhoneNum = PropertyUtility.getDataProperties("denver.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));

                } else {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                    custPassword = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                }
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);

                driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.duo.phone");
                driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.duo.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.duo.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);

                driver2PhoneNum = PropertyUtility.getDataProperties("ios.valid.driver2.phone");
                driver2Password = PropertyUtility.getDataProperties("ios.valid.driver2.password");
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("ios.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);

            } else if (customer.equalsIgnoreCase("Kansas customer")) {
                custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer2.phone");
                custPassword = PropertyUtility.getDataProperties("Kansas.customer2.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer2.name"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            } else if (customer.equalsIgnoreCase("Kansas customer B")) {
                custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer.phone");
                custPassword = PropertyUtility.getDataProperties("Kansas.customer.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            } else {
                custPhoneNum = PropertyUtility.getDataProperties("atlanta.customer.phone");
                custPassword = PropertyUtility.getDataProperties("atlanta.customer.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer.name"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);


                driverPhoneNum = PropertyUtility.getDataProperties("atlanta.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("atlanta.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);

                driver2PhoneNum = PropertyUtility.getDataProperties("valid.driver2.phone");
                driver2Password = PropertyUtility.getDataProperties("valid.driver2.password");
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("valid.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);

            }
            if (driver1.equalsIgnoreCase("Kansas driver 1")) {
                driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("Kansas.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            }
            if (driver2.equalsIgnoreCase("Kansas driver 2")) {
                driver2PhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                driver2Password = PropertyUtility.getDataProperties("Kansas.driver2.password");
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("Kansas.driver2.name"));
            }
            if (driver1.equalsIgnoreCase("denver driver 1")) {
                driverPhoneNum = PropertyUtility.getDataProperties("denver.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("denver.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            }
            if (driver2.equalsIgnoreCase("denver driver 2")) {
                driver2PhoneNum = PropertyUtility.getDataProperties("denver.driver2.phone");
                driver2Password = PropertyUtility.getDataProperties("denver.driver2.password");
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("denver.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driverPhoneNum);
            }
            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);

            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            cucumberContextManager.setScenarioContext("CUSTOMER_PUSH", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PUSH", driverPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_2_PUSH", driver2PhoneNum);

            String driver2AccessToken = null;
            String driver2Ref = null;
                    String custRef = null;
          //  if(!driver2.equalsIgnoreCase("NA")) {
                driver2AccessToken = authServices.getDriverToken(driver2PhoneCode, driver2PhoneNum, driver2Password);
                custRef = customerServices.getCustomerRef(custAccessToken);
                driver2Ref = driverServices.getDriverRef(driver2AccessToken);
          //  }

            String driverRef = driverServices.getDriverRef(driverAccessToken);



            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //CUSTOMER& DRIVER VIEW
            //  coreServices.customerView("",custAccessToken);
            //   coreServices.driverView("",driverAccessToken);

            //update location and driver status
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);
            coreServices.updateDriverLocation(driver2AccessToken, geofence);
            coreServices.updateDriverStatus(driver2AccessToken);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 2, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            //int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
            int wait = 0;
            if(TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date()))
            {
    if (scheduleTime.equalsIgnoreCase("1 hour ahead"))
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 60);
    else if (scheduleTime.equalsIgnoreCase("2 hour ahead"))
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 120);
    else if (scheduleTime.equalsIgnoreCase("0.75 hour ahead"))
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 45);
    else if (scheduleTime.equalsIgnoreCase("0.5 hour ahead"))
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 30);
    else if (scheduleTime.equalsIgnoreCase("15 min ahead"))
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 15);
    else
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
}
else
{
    if (scheduleTime.equalsIgnoreCase("1 hour ahead"))
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 60);
    else if (scheduleTime.equalsIgnoreCase("2 hour ahead"))
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 120);
    else if (scheduleTime.equalsIgnoreCase("0.75 hour ahead"))
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 45);
    else if (scheduleTime.equalsIgnoreCase("0.5 hour ahead"))
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 30);
    else if (scheduleTime.equalsIgnoreCase("15 min ahead"))
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 15);
    else
        wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
}
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);coreServices.driverView("",driverAccessToken);}catch (Exception e){}
            try{ coreServices.getDriverScheduledPickupList(driver2AccessToken);coreServices.driverView("",driver2AccessToken);}catch (Exception e){}
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickupRequest) ;
          //  coreServices.waitForAvailableTrips(cucumberContextManager.getScenarioContext("DRIVER_1") + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest);
           // coreServices.waitForAvailableTrips(cucumberContextManager.getScenarioContext("DRIVER_2") + "(" + driver2PhoneNum + ")", driver2AccessToken, pickupRequest);

            if (state.equalsIgnoreCase("Requested")) {
                //do nothing
            }
            else if (state.equalsIgnoreCase("Accepted")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);
            } else if (state.equalsIgnoreCase("Scheduled")) {

            } else if (state.equalsIgnoreCase("enroute")) {

                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    //  Thread.sleep(wait);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
            } else if (state.equalsIgnoreCase("arrived")) {

                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    //  Thread.sleep(wait);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
            } else if (state.equalsIgnoreCase("Driving To Dropoff")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    //  Thread.sleep(wait);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);

                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
            } else if (state.equalsIgnoreCase("unloading items")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    //  Thread.sleep(wait);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);

                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 27);
                coreServices.customerView(pickupRequest, custAccessToken);
                coreServices.driverView(pickupRequest, driverAccessToken);
                coreServices.driverView(pickupRequest, driver2AccessToken);
            } else {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    //  Thread.sleep(wait);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 27);
                coreServices.customerView(pickupRequest, custAccessToken);
                coreServices.driverView(pickupRequest, driverAccessToken);
                coreServices.driverView(pickupRequest, driver2AccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 28);
                coreServices.customerView(pickupRequest, custAccessToken);
                coreServices.driverView(pickupRequest, driverAccessToken);
                String driverPaymentMethod = coreServices.driverPaymentMethod(pickupRequest, driverAccessToken);
                String driver2PaymentMethod = coreServices.driverPaymentMethod(pickupRequest, driver2AccessToken);

                coreServices.rateAndTip(pickupRequest, custAccessToken, driverRef, driverPaymentMethod, 5.0, 0.0, driver2Ref, driver2PaymentMethod);
                System.out.println(pickupRequest);
            }
            cucumberContextManager.setFeatureContextContext("BUNGII_INITIAL_SCH_TIME", System.currentTimeMillis() / 1000L);
            pass("Precondition: Given that duo schedule bungii is in progress ", "Duo Schedule Bungii [ "+pickupRequest+" ]  by customer " +custPhoneNum+" is in " + state +" state by driver "+ driverPhoneNum +" and " +  driver2PhoneNum);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }


    @When("^I request below Bungiis as a customer$")
    public void i_request_below_bungiis_as_a_customer(DataTable data) {
        List<Map<String, String>> DataList = data.asMaps();
        int i = 0;
        while (i < DataList.size()) {
            try {
                String geofence = DataList.get(i).get("geofence").trim();
                cucumberContextManager.setScenarioContext("GEOFENCE", geofence.toLowerCase());
                String bungiiType = DataList.get(i).get("Bungii Type").trim();//duo/solo/ONDEMAND
                String bungiiTime = DataList.get(i).get("Bungii Time").trim();
                String customer = DataList.get(i).get("Customer Phone").trim();
                String customerName = DataList.get(i).get("Customer Name").trim();

                int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;

                String custPhoneCode = "1", custPhoneNum = "", custPassword = "";


                custPhoneNum = customer;// PropertyUtility.getDataProperties("web.customer.user");
                custPassword = PropertyUtility.getDataProperties("web.customer.password");

                cucumberContextManager.setScenarioContext("CUSTOMER", customerName);//PropertyUtility.getDataProperties("web.customer.name"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
                cucumberContextManager.setScenarioContext("BUNGII_TYPE", bungiiType);

                logger.detail("*** Requesting " + bungiiType + " as a customer " + customerName + "(" + custPhoneNum + ") for geofence" + geofence + " ***");

                //LOGIN
                String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
                String custRef = customerServices.getCustomerRef(custAccessToken);

                //CUSTOMER& DRIVER VIEW
                coreServices.customerView("", custAccessToken);

                //request Bungii
                coreServices.validatePickupRequest(custAccessToken, geofence);
                String pickupRequest = coreServices.getPickupRequest(custAccessToken, numberOfDriver, geofence);
                cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
                String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
                if (bungiiType.equalsIgnoreCase("ONDEMAND"))
                    coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
                else {
                    int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
                    cucumberContextManager.setScenarioContext("MIN_WAIT_BUNGII_START", wait);
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                pass("I should able to request bungii ", "Requested " + bungiiType + " as a customer " + customerName + "(" + custPhoneNum + ") for geofence" + geofence );
            } catch (Exception e) {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step  Should be successful", "Error performing step,Please check logs for more details",
                        true);
            }

            i++;
        }


    }

    @Given("that solo schedule bungii is in progress")
    public void thatsoloscheduleBungiiIsInProgress(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String geofence = dataMap.get("geofence").trim();
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence.toLowerCase());
            String state = dataMap.get("Bungii State").trim();
            String scheduleTime = dataMap.get("Bungii Time").trim();

            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";

            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {

                if (geofence.equalsIgnoreCase("denver")) {
                    custPhoneNum = PropertyUtility.getDataProperties("denver.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("denver.customer.password");

                    driverPhoneNum = PropertyUtility.getDataProperties("denver.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("denver.driver.password");

                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));

                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));
                } else {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                    custPassword = PropertyUtility.getDataProperties("customer.password");

                    driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.password");

                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));

                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                }
            } else {

                if (geofence.equalsIgnoreCase("atlanta")) {
                    custPhoneNum = PropertyUtility.getDataProperties("atlanta.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("atlanta.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("atlanta.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("atlanta.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.driver.name"));
                } else if (geofence.equalsIgnoreCase("kansas1")) {
                    geofence = "kansas";
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence.toLowerCase());
                    custPhoneNum = PropertyUtility.getDataProperties("kansas.customer1.phone");
                    custPassword = PropertyUtility.getDataProperties("kansas.customer1.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("kansas.customer1.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("Kansas.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver.name"));
                }  else {
                    custPhoneNum = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                    custPassword = PropertyUtility.getDataProperties("customer_generic.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer_generic.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));
                }
            }
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            cucumberContextManager.setScenarioContext("CUSTOMER_PUSH", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PUSH", driverPhoneNum);
            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            String driverRef = driverServices.getDriverRef(driverAccessToken);
            String custRef = customerServices.getCustomerRef(custAccessToken);

            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            coreServices.driverView("", driverAccessToken);
            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);}catch (Exception e){}

            //update location and driver status
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            int wait = 0;

            if (scheduleTime.equalsIgnoreCase("1 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 60);
            else if (scheduleTime.equalsIgnoreCase("2.25 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 135);
            else if (scheduleTime.equalsIgnoreCase("2 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 120);
            else if (scheduleTime.equalsIgnoreCase("0.75 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 45);
            else if (scheduleTime.equalsIgnoreCase("0.5 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 30);
            else if (scheduleTime.equalsIgnoreCase("15 min ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 15);
            else if (scheduleTime.equalsIgnoreCase("1.5 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 90);
            else if (scheduleTime.equalsIgnoreCase("1.25 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 75);
            else
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);

            cucumberContextManager.setFeatureContextContext("BUNGII_INITIAL_SCH_TIME", System.currentTimeMillis() / 1000L);


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //coreServices.waitForAvailableTrips(cucumberContextManager.getScenarioContext("DRIVER_1") + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest);

            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
            if (state.equalsIgnoreCase("Accepted")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            } else if (state.equalsIgnoreCase("enroute")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);

                try {
                    //sprint 32 driver can start bungii 1 hour before
                    //Thread.sleep(wait);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
            } else if (state.equalsIgnoreCase("Scheduled")) {
                //do nothing, already in scheduled state
            } else if (state.equalsIgnoreCase("DRIVING TO DROP OFF")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
            }else {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.customerView(pickupRequest, custAccessToken);
                coreServices.driverView(pickupRequest, driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                coreServices.customerView(pickupRequest, custAccessToken);
                coreServices.driverView(pickupRequest, driverAccessToken);
                String driverPaymentMethod = coreServices.driverPaymentMethod(pickupRequest, driverAccessToken);

                coreServices.rateAndTip(pickupRequest, custAccessToken, driverRef, driverPaymentMethod, 5.0, 0.0);
            }
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickupRequest);

            pass("Given that the Solo Schedule Bungii is in progress", "Solo schedule bungii ["+ pickupRequest +" - " + scheduleTime+"] is in " + state +" for geofence "+ geofence +" by customer "+ custPhoneNum +" and driver "+ driverPhoneNum );
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^that solo schedule bungii is in progress for customer \"([^\"]*)\"$")
    public void that_solo_schedule_bungii_is_in_progress_for_customer_something(String customerName, DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String geofence = dataMap.get("geofence").trim();
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence.toLowerCase());
            String state = dataMap.get("Bungii State").trim();
            String scheduleTime = dataMap.get("Bungii Time").trim();

            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";
            //Richa
            String[] Details= getCustomerDriverDetails(customerName);
                custPhoneNum = Details[0];
                custPassword = Details[1];
                cucumberContextManager.setScenarioContext("CUSTOMER", customerName);

                driverPhoneNum = Details[3];
                driverPassword = Details[4];
                cucumberContextManager.setScenarioContext("DRIVER_1", Details[5]);

            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            cucumberContextManager.setScenarioContext("CUSTOMER_PUSH", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PUSH", driverPhoneNum);
            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            String driverRef = driverServices.getDriverRef(driverAccessToken);
            String custRef = customerServices.getCustomerRef(custAccessToken);

            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            coreServices.driverView("", driverAccessToken);
            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);}catch (Exception e){}

            //update location and driver status
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            int wait = 0;

            if (scheduleTime.equalsIgnoreCase("1 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 60);
            else if (scheduleTime.equalsIgnoreCase("2 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 120);
            else if (scheduleTime.equalsIgnoreCase("3 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 180);
            else if (scheduleTime.equalsIgnoreCase("0.75 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 45);
            else if (scheduleTime.equalsIgnoreCase("0.5 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 30);
            else if (scheduleTime.equalsIgnoreCase("15 min ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 15);
            else if (scheduleTime.equalsIgnoreCase("1.5 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 90);
            else
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);

            cucumberContextManager.setFeatureContextContext("BUNGII_INITIAL_SCH_TIME", System.currentTimeMillis() / 1000L);


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          //  coreServices.waitForAvailableTrips(cucumberContextManager.getScenarioContext("DRIVER_1") + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest);

            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
            if (state.equalsIgnoreCase("Accepted")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            } else if (state.equalsIgnoreCase("enroute")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);

                try {
                    //sprint 32 driver can start bungii 1 hour before
                    //Thread.sleep(wait);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
            } else if (state.equalsIgnoreCase("Scheduled")) {
                //do nothing, already in scheduled state
            } else if (state.equalsIgnoreCase("Unloading Items")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
            }else {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.customerView(pickupRequest, custAccessToken);
                coreServices.driverView(pickupRequest, driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                coreServices.customerView(pickupRequest, custAccessToken);
                coreServices.driverView(pickupRequest, driverAccessToken);
                String driverPaymentMethod = coreServices.driverPaymentMethod(pickupRequest, driverAccessToken);

                coreServices.rateAndTip(pickupRequest, custAccessToken, driverRef, driverPaymentMethod, 5.0, 0.0);
            }
            pass("Given that the Solo Schedule Bungii is in progress", "Solo schedule bungii ["+ pickupRequest+" - "+scheduleTime+"] is in " + state +" for geofence "+ geofence +" by customer "+ custPhoneNum+" and driver "+ driverPhoneNum );
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Given("that ondemand bungii is in progress")
    public void thatOndemandBungiiIsInProgress(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String geofence = dataMap.get("geofence").trim();
            String state = dataMap.get("Bungii State").trim();
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";
            String driverLabel = "", tripLabel = "";
            try {
                driverLabel = dataMap.get("Driver label").trim();
                logger.detail("Label is  specified as input" + driverLabel);
            } catch (Exception e) {
            }
            try {
                tripLabel = dataMap.get("Trip Label").trim();
                logger.detail("Label is  specified as input" + driverLabel);
            } catch (Exception e) {
            }


            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {
                if (geofence.equalsIgnoreCase("miami")) {
                    custPhoneNum = PropertyUtility.getDataProperties("miami.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("miami.customer.password");
                    driverPhoneNum = PropertyUtility.getDataProperties("miami.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("miami.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("miami.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("miami.customer.name"));
                } else if (geofence.equalsIgnoreCase("nashville")) {
                    custPhoneNum = PropertyUtility.getDataProperties("nashville.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("nashville.customer.password");
                    driverPhoneNum = PropertyUtility.getDataProperties("nashville.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("nashville.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("nashville.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("nashville.customer.name"));
                } else if (geofence.equalsIgnoreCase("Kansas")) {
                    custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("Kansas.customer.password");
                    if (driverLabel.equalsIgnoreCase("Kansas 2")) {
                        driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                        driverPassword = PropertyUtility.getDataProperties("Kansas.driver2.password");
                    } else {
                        driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver.phone");
                        driverPassword = PropertyUtility.getDataProperties("Kansas.driver.password");
                    }
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));
                }

                else if (geofence.equalsIgnoreCase("denver")) {
                    custPhoneNum = PropertyUtility.getDataProperties("denver.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("denver.customer.password");
                    if (driverLabel.equalsIgnoreCase("driver 2")) {
                        driverPhoneNum = PropertyUtility.getDataProperties("denver.driver2.phone");
                        driverPassword = PropertyUtility.getDataProperties("denver.driver2.password");
                    } else {
                        driverPhoneNum = PropertyUtility.getDataProperties("denver.driver.phone");
                        driverPassword = PropertyUtility.getDataProperties("denver.driver.password");
                    }
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));
                } else {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                    custPassword = PropertyUtility.getDataProperties("customer.password");
                    driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                }
            } else {
                if (geofence.equals("boston")) {
                    custPhoneNum = PropertyUtility.getDataProperties("boston.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("boston.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("boston.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("boston.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("boston.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("boston.driver.name"));
                } else if (geofence.equals("baltimore")) {
                    custPhoneNum = PropertyUtility.getDataProperties("baltimore.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("baltimore.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("baltimore.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("baltimore.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("baltimore.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("baltimore.driver.name"));
                } else if (geofence.contains("atlanta")) {
                    custPhoneNum = PropertyUtility.getDataProperties("atlanta.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("atlanta.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer.name"));
                    if (driverLabel.equalsIgnoreCase("far away atlanta driver")) {
                        driverPhoneNum = PropertyUtility.getDataProperties("atlanta.far.away.driver.phone");
                        driverPassword = PropertyUtility.getDataProperties("atlanta.far.away.driver.password");
                        cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.far.away.driver.name"));
                    } else {
                        driverPhoneNum = PropertyUtility.getDataProperties("atlanta.driver.phone");
                        driverPassword = PropertyUtility.getDataProperties("atlanta.driver.password");
                        cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.driver.name"));
                    }
                } else if (geofence.equals("goa")) {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                    custPassword = PropertyUtility.getDataProperties("customer.password");
                    driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                } else if (geofence.equalsIgnoreCase("Kansas")) {
                    custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("Kansas.customer.password");
                    if (driverLabel.equalsIgnoreCase("Kansas 2")) {
                        driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                        driverPassword = PropertyUtility.getDataProperties("Kansas.driver2.password");
                    } else {
                        driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver.phone");
                        driverPassword = PropertyUtility.getDataProperties("Kansas.driver.password");
                    }
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));
                }
                else if (geofence.equalsIgnoreCase("kansas1")) {
                    geofence = "kansas";
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence.toLowerCase());
                    custPhoneNum = PropertyUtility.getDataProperties("kansas.customer1.phone");
                    custPassword = PropertyUtility.getDataProperties("kansas.customer1.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("kansas.customer1.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                    driverPassword = PropertyUtility.getDataProperties("Kansas.driver2.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver2.name"));
                }
                else if (geofence.equalsIgnoreCase("same")){
                    geofence = "kansas";
                    custPhoneNum = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                    custPassword = PropertyUtility.getDataProperties("customer_generic.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                    driverPassword = PropertyUtility.getDataProperties("Kansas.driver2.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver2.name"));
                }
                else {
                    custPhoneNum = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                    custPassword = PropertyUtility.getDataProperties("customer_generic.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));
                }

            }

            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            cucumberContextManager.setScenarioContext("CUSTOMER_PUSH", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PUSH", driverPhoneNum);
            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            coreServices.driverView("", driverAccessToken);
            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);}catch (Exception e){}

            //update location and driver status
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            //In case of having default promo code  "ADDED_PROMOCODE_WALLETREF" hold value of wallet ref, else return empty string
            if (tripLabel.trim().equalsIgnoreCase(""))
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            else
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken, tripLabel);
            coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            Boolean isDriverEligibel = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
            if (!isDriverEligibel)
            //    error("Diver should be eligible for on demand trip", "Driver ID is not in eligibleDriver list", false);
              logger.detail("Diver should be eligible for on demand trip", " Warning Message :  Driver ID is not in eligibleDriver list", false);

            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            if (state.equalsIgnoreCase("Requested")) {
                //Do nothing
            }
            else if (state.equalsIgnoreCase("Enroute")) {
            } else if (state.equalsIgnoreCase("ARRIVED")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
            } else if (state.equalsIgnoreCase("LOADING ITEM")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
            } else if (state.equalsIgnoreCase("DRIVING TO DROP OFF")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
            } else if (state.equalsIgnoreCase("UNLOADING ITEM")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
            } else {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
            }
            pass("Given that the Solo Ondemand Bungii is in progress", "Solo schedule bungii [ "+pickupRequest+" ] is in " + state +" for geofence "+ geofence +" by customer "+ custPhoneNum );

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Given("that ondemand bungii is in progress for the minimum distance chosen")
    public void thatOndemandBungiiIsInProgressForTheMinimumDistance(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String geofence = dataMap.get("geofence").trim();
            String state = dataMap.get("Bungii State").trim();
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";
            String driverLabel = "", tripLabel = "";
            try {
                driverLabel = dataMap.get("Driver label").trim();
                logger.detail("Label is  specified as input" + driverLabel);
            } catch (Exception e) {
            }
            try {
                tripLabel = dataMap.get("Trip Label").trim();
                logger.detail("Label is  specified as input" + driverLabel);
            } catch (Exception e) {
            }


            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {
                if (geofence.equalsIgnoreCase("miami")) {
                    custPhoneNum = PropertyUtility.getDataProperties("miami.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("miami.customer.password");
                    driverPhoneNum = PropertyUtility.getDataProperties("miami.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("miami.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("miami.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("miami.customer.name"));
                } else if (geofence.equalsIgnoreCase("nashville")) {
                    custPhoneNum = PropertyUtility.getDataProperties("nashville.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("nashville.customer.password");
                    driverPhoneNum = PropertyUtility.getDataProperties("nashville.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("nashville.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("nashville.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("nashville.customer.name"));
                } else if (geofence.equalsIgnoreCase("Kansas")) {
                    custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("Kansas.customer.password");
                    if (driverLabel.equalsIgnoreCase("Kansas 2")) {
                        driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                        driverPassword = PropertyUtility.getDataProperties("Kansas.driver2.password");
                    } else {
                        driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver.phone");
                        driverPassword = PropertyUtility.getDataProperties("Kansas.driver.password");
                    }
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));
                }

                else if (geofence.equalsIgnoreCase("denver")) {
                    custPhoneNum = PropertyUtility.getDataProperties("denver.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("denver.customer.password");
                    if (driverLabel.equalsIgnoreCase("driver 2")) {
                        driverPhoneNum = PropertyUtility.getDataProperties("denver.driver2.phone");
                        driverPassword = PropertyUtility.getDataProperties("denver.driver2.password");
                    } else {
                        driverPhoneNum = PropertyUtility.getDataProperties("denver.driver.phone");
                        driverPassword = PropertyUtility.getDataProperties("denver.driver.password");
                    }
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));
                } else {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                    custPassword = PropertyUtility.getDataProperties("customer.password");
                    driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                }
            } else {
                if (geofence.equals("boston")) {
                    custPhoneNum = PropertyUtility.getDataProperties("boston.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("boston.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("boston.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("boston.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("boston.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("boston.driver.name"));
                } else if (geofence.equals("baltimore")) {
                    custPhoneNum = PropertyUtility.getDataProperties("baltimore.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("baltimore.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("baltimore.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("baltimore.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("baltimore.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("baltimore.driver.name"));
                } else if (geofence.contains("atlanta")) {
                    custPhoneNum = PropertyUtility.getDataProperties("atlanta.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("atlanta.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer.name"));
                    if (driverLabel.equalsIgnoreCase("far away atlanta driver")) {
                        driverPhoneNum = PropertyUtility.getDataProperties("atlanta.far.away.driver.phone");
                        driverPassword = PropertyUtility.getDataProperties("atlanta.far.away.driver.password");
                        cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.far.away.driver.name"));
                    } else {
                        driverPhoneNum = PropertyUtility.getDataProperties("atlanta.driver.phone");
                        driverPassword = PropertyUtility.getDataProperties("atlanta.driver.password");
                        cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.driver.name"));
                    }
                } else if (geofence.equals("goa")) {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                    custPassword = PropertyUtility.getDataProperties("customer.password");
                    driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                } else if (geofence.equalsIgnoreCase("Kansas")) {
                    custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("Kansas.customer.password");
                    if (driverLabel.equalsIgnoreCase("Kansas 2")) {
                        driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                        driverPassword = PropertyUtility.getDataProperties("Kansas.driver2.password");
                    } else {
                        driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver.phone");
                        driverPassword = PropertyUtility.getDataProperties("Kansas.driver.password");
                    }
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));
                }
                else if (geofence.equalsIgnoreCase("kansas1")) {
                    geofence = "kansas";
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence.toLowerCase());
                    custPhoneNum = PropertyUtility.getDataProperties("kansas.customer1.phone");
                    custPassword = PropertyUtility.getDataProperties("kansas.customer1.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("kansas.customer1.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                    driverPassword = PropertyUtility.getDataProperties("Kansas.driver2.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver2.name"));
                }
                else if (geofence.equalsIgnoreCase("same")){
                    geofence = "kansas";
                    custPhoneNum = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                    custPassword = PropertyUtility.getDataProperties("customer_generic.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                    driverPassword = PropertyUtility.getDataProperties("Kansas.driver2.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver2.name"));
                }
                else {
                    custPhoneNum = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                    custPassword = PropertyUtility.getDataProperties("customer_generic.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));
                }

            }

            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            cucumberContextManager.setScenarioContext("CUSTOMER_PUSH", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PUSH", driverPhoneNum);
            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            coreServices.driverView("", driverAccessToken);
            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);}catch (Exception e){}

            //update location and driver status
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequestWithZeroDistance(custAccessToken, 1, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            //In case of having default promo code  "ADDED_PROMOCODE_WALLETREF" hold value of wallet ref, else return empty string
            if (tripLabel.trim().equalsIgnoreCase(""))
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            else
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken, tripLabel);
            coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            Boolean isDriverEligibel = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
            if (!isDriverEligibel)
                //    error("Diver should be eligible for on demand trip", "Driver ID is not in eligibleDriver list", false);
                logger.detail("Diver should be eligible for on demand trip", " Warning Message :  Driver ID is not in eligibleDriver list", false);

            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            if (state.equalsIgnoreCase("Requested")) {
                //Do nothing
            }
            else if (state.equalsIgnoreCase("Enroute")) {
            } else if (state.equalsIgnoreCase("ARRIVED")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
            } else if (state.equalsIgnoreCase("LOADING ITEM")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
            } else if (state.equalsIgnoreCase("DRIVING TO DROP OFF")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
            } else if (state.equalsIgnoreCase("UNLOADING ITEM")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
            } else {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
            }
            pass("Given that the Solo Ondemand Bungii is in progress", "Solo schedule bungii [ "+pickupRequest+" ] is in " + state +" for geofence "+ geofence +" by customer "+ custPhoneNum );

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Given("that ondemand bungii is in progress as a second delivery")
    public void thatOndemandBungiiIsInProgressSecondDelivery(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String geofence = dataMap.get("geofence").trim();
            String state = dataMap.get("Bungii State").trim();
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";
            String driverLabel = "", tripLabel = "";
            try {
                driverLabel = dataMap.get("Driver label").trim();
                logger.detail("Label is  specified as input" + driverLabel);
            } catch (Exception e) {
            }
            try {
                tripLabel = dataMap.get("Trip Label").trim();
                logger.detail("Label is  specified as input" + driverLabel);
            } catch (Exception e) {
            }

                 if (geofence.equalsIgnoreCase("same")){
                    geofence = "kansas";
                    custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("Kansas.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer_generic.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));
                }
                else {
                    custPhoneNum = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                    custPassword = PropertyUtility.getDataProperties("customer_generic.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));
                }

            cucumberContextManager.setScenarioContext("CUSTOMER2_PHONE", custPhoneNum);
            cucumberContextManager.setScenarioContext("CUSTOMER2_PASSWORD", custPassword);

            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            cucumberContextManager.setScenarioContext("CUSTOMER_PUSH", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PUSH", driverPhoneNum);
            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            coreServices.driverView("", driverAccessToken);
            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);}catch (Exception e){}

            //update location and driver status
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            //In case of having default promo code  "ADDED_PROMOCODE_WALLETREF" hold value of wallet ref, else return empty string
            if (tripLabel.trim().equalsIgnoreCase(""))
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            else
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken, tripLabel);
            coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
           // Boolean isDriverEligibel = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
         //  if (!isDriverEligibel)
            //    error("Diver should be eligible for on demand trip", "Driver ID is not in eligibleDriver list", false);
          //  logger.detail("Diver should be eligible for on demand trip", " Warning Message :  Driver ID is not in eligibleDriver list", false);

            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            if (state.equalsIgnoreCase("Requested")) {
                //Do nothing
            }
            else if (state.equalsIgnoreCase("Enroute")) {
            } else if (state.equalsIgnoreCase("ARRIVED")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
            } else if (state.equalsIgnoreCase("LOADING ITEM")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
            } else if (state.equalsIgnoreCase("DRIVING TO DROP OFF")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
            } else if (state.equalsIgnoreCase("UNLOADING ITEM")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
            } else {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
            }
            pass("Given that the Solo Ondemand Bungii is in progress", "Solo schedule bungii [ "+pickupRequest+" ] is in " + state +" for geofence "+ geofence +" by customer "+ custPhoneNum );

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    //BOC RICHA

    @Given("^that ondemand bungii is in progress for customer \"([^\"]*)\"$")
    public void that_ondemand_bungii_is_in_progress_for_customer_something(String customerName, DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String geofence = dataMap.get("geofence").trim();
            String state = dataMap.get("Bungii State").trim();
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";
            String driverLabel = "", tripLabel = "";
            try {
                driverLabel = dataMap.get("Driver label").trim();
                logger.detail("Label is  specified as input" + driverLabel);
            } catch (Exception e) {
            }
            try {
                tripLabel = dataMap.get("Trip Label").trim();
                logger.detail("Label is  specified as input" + driverLabel);
            } catch (Exception e) {
            }

            String[] Details= getCustomerDriverDetails(customerName);
            custPhoneNum = Details[0];
            custPassword = Details[1];
            cucumberContextManager.setScenarioContext("CUSTOMER", customerName);

            driverPhoneNum = Details[3];
            driverPassword = Details[4];
            cucumberContextManager.setScenarioContext("DRIVER_1", Details[5]);

            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            cucumberContextManager.setScenarioContext("CUSTOMER_PUSH", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PUSH", driverPhoneNum);
            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            coreServices.driverView("", driverAccessToken);
            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);}catch (Exception e){}

            //update location and driver status
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            //In case of having default promo code  "ADDED_PROMOCODE_WALLETREF" hold value of wallet ref, else return empty string
            if (tripLabel.trim().equalsIgnoreCase(""))
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            else
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken, tripLabel);
            coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            Boolean isDriverEligibel = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
            if (!isDriverEligibel)
                error("Driver should be eligible for on demand trip", "Driver ID is not in eligibleDriver list", false);

            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            if (state.equalsIgnoreCase("Enroute")) {
            } else if (state.equalsIgnoreCase("ARRIVED")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
            } else if (state.equalsIgnoreCase("LOADING ITEM")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
            } else if (state.equalsIgnoreCase("DRIVING TO DROP OFF")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
            } else if (state.equalsIgnoreCase("UNLOADING ITEM")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
            } else {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
            }
            pass("Given that the Solo Ondemand Bungii is in progress", "Solo schedule bungii [ "+pickupRequest+" ] is in " + state +" for geofence "+ geofence +" by customer "+ custPhoneNum);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    //EOC RICHA

    public void recoveryScenario() {
        String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";
        logger.detail("********* CLEARING CUSTOMER BUNGIIS *********");

        custPhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        custPassword = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PASSWORD");
        custPassword = custPassword.equalsIgnoreCase("") ? "Cci12345" : custPassword;

        if (!custPhoneNum.equalsIgnoreCase("")) {
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            handleOngoingBungii(custAccessToken);
            cancelScheduledBungii(custAccessToken);
        }
        // remove driver 2 bungiis
        custPhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
        if (custPhoneNum.equalsIgnoreCase("")) {
            custPhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA");
        }
        custPassword = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PASSWORD");
        custPassword = custPassword.equalsIgnoreCase("") ? "Cci12345" : custPassword;
        if (!custPhoneNum.equalsIgnoreCase("")) {
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            handleOngoingBungii(custAccessToken);
            cancelScheduledBungii(custAccessToken);
        }
        logger.detail("***************** CLEARED CUSTOMER BUNGIIS ******************");

    }

    public void handleOngoingBungii(String custAccessToken) {
       // String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
        coreServices.cancelOrCompleteOngoingBungii(custAccessToken);
    }

    public void cancelScheduledBungii(String custAccessToken) {
        //String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
        coreServices.cancelAllScheduledBungiis(custAccessToken);
    }

    @When("^I cancel all bungiis of customer$")
    public void i_request_something_bungii_as_a_customer_in_something_geofence(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", cust2PhoneNum = "", cust2Password = "";

            custPhoneNum = dataMap.get("Customer Phone").trim();
            if (custPhoneNum.equalsIgnoreCase("CUSTOMER1_PHONE")) {
                custPhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
            } else if (custPhoneNum.equalsIgnoreCase("NEW_USER_NUMBER")) {
                custPhoneNum = (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");
            } else if (custPhoneNum.equalsIgnoreCase("NEW_USER_NUMBER")) {
                custPhoneNum = (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");
            }
            custPassword = PropertyUtility.getDataProperties("customer.password");

            if (!custPhoneNum.equalsIgnoreCase("")) {
                String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
                handleOngoingBungii(custAccessToken);
                cancelScheduledBungii(custAccessToken);
            }

            cust2PhoneNum = dataMap.get("Customer2 Phone").trim();
            if (cust2PhoneNum.equalsIgnoreCase("CUSTOMER2_PHONE")) {
                cust2PhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
            } else if (cust2PhoneNum.equalsIgnoreCase("CUSTOMER_PHONE_EXTRA")) {
                cust2PhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA");
            }
            cust2Password = PropertyUtility.getDataProperties("customer.password");

            if (!cust2PhoneNum.equalsIgnoreCase("")) {
                //Thread.sleep(10000);
                String custAccessToken = authServices.getCustomerToken(custPhoneCode, cust2PhoneNum, cust2Password);
                handleOngoingBungii(custAccessToken);
                cancelScheduledBungii(custAccessToken);
            }
            if (!cust2PhoneNum.equalsIgnoreCase("")) {
                pass("Test Data Cleanup : I cancel all the bungiis of a customer", "I cancelled all Bungiis of a customer : " + custPhoneNum + " & " + cust2PhoneNum);
            }
            else
                pass("Test Data Cleanup : I cancel all the bungiis of a customer", "I cancelled all Bungiis of a customer : " + custPhoneNum );

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^bungii admin manually end bungii created by \"([^\"]*)\"$")
    public void i_request_something_bungii_as_a_customer_in_something_geofence(String customer) {
        try {
            //wait for trip to be reflected
            Thread.sleep(120000);
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", cust2PhoneNum = "", cust2Password = "";

            if (customer.equalsIgnoreCase("CUSTOMER1")) {
                custPhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
            } else if (customer.equalsIgnoreCase("CUSTOMER2")) {
                custPhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
            }
            custPassword = PropertyUtility.getDataProperties("customer.password");

            if (!custPhoneNum.equalsIgnoreCase("")) {
                String pickupRef = new DbUtility().getPickupRef(custPhoneNum);
                new WebPortal().asAdminManuallyEndBungii(pickupRef);
                pass("bungii admin manually end bungii created by " + customer, "manually ended bungii", false);

            } else {
                fail("bungii admin manually end bungii created by " + customer, "Not able to find customer", false);

            }


        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^The first time promo code should get released$")
    public void the_first_time_promo_code_should_get_released() {
        String custAccessToken = (String) cucumberContextManager.getScenarioContext("CUSTOMER_TOKEN");
        Response promoData = coreServices.getPromoCodes(custAccessToken, "");
        String promo = getPromoCode(promoData, "");
        testStepAssert.isTrue(promo.equalsIgnoreCase("PROMO1"), "First time promo code is released", "Pass");
    }

    public String getPromoCode(Response response, String codeType) {
        String promoCode = "";
        JsonPath jsonPathEvaluator = response.jsonPath();
        ArrayList availableArray = jsonPathEvaluator.get("PromoCodes");
        //interation to go through all promo code
        if (availableArray != null) {
            for (int i = 0; i < availableArray.size(); i++) {
                HashMap pickupDetails = (HashMap) availableArray.get(i);
                promoCode = (String) pickupDetails.get("Code");
                cucumberContextManager.setScenarioContext("ADDED_PROMOCODE", promoCode);
                break;
            }
        }
        return promoCode;
    }


    @Then("^The driver \"([^\"]*)\" should receive On Demand requests as he is assigned to \"([^\"]*)\" geofence$")
    public void the_driver_something_should_receive_on_demand_requests_as_he_is_assigned_to_something_geofence(String driverName, String geofence) {
        cucumberContextManager.setScenarioContext("DRIVER_1", driverName);
        String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";
        String driverAccessToken = "";
        //get pickup request from context
        String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

        driverPhoneNum = getDriverPhone(driverName);
        driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
        cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
        driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
        coreServices.updateDriverStatus(driverAccessToken);
        Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
        testStepAssert.isTrue(isDriverEligible, "Driver should be eligible for the trip in " + geofence + " geofence", "Driver is eligible for the trip in " + geofence + " geofence", "Driver is NOT eligible for the trip in " + geofence + " geofence");
    }

    @Then("^the driver \"([^\"]*)\" should not receive On Demand requests as he is assigned NOT to \"([^\"]*)\" geofence$")
    public void the_driver_something_should_not_receive_on_demand_requests_as_he_is_assigned_not_to_something_geofence(String driverName, String geofence) {
        cucumberContextManager.setScenarioContext("DRIVER_1", driverName);
        Boolean isDriverEligible = true;
        String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";
        String driverAccessToken = "";
        //get pickup request from context
        String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

        driverPhoneNum = getDriverPhone(driverName);
        driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
        cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
        driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
        coreServices.updateDriverStatus(driverAccessToken);
        isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
        testStepAssert.isTrue(!isDriverEligible, "Driver should NOT be eligible for the trip in " + geofence + " geofence", "Driver is NOT eligible for the trip in " + geofence + " geofence", "Driver is eligible for the trip in " + geofence + " geofence");

    }

    @When("^I cancel \"([^\"]*)\" of customer \"([^\"]*)\"$")
    public void i_cancel_something_of_customer_something(String bungii_type, String customer) {
        String custPhoneCode = "1", custPassword = "";
        custPassword = PropertyUtility.getDataProperties("web.customer.password");
        if (!customer.equalsIgnoreCase("")) {
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, customer, custPassword);
            handleOngoingBungii(custAccessToken);
        }
        pass("I cancel the trip for the customer",
                "I have cancelled the trip for the customer");
    }

    @When("^I wait for 2 minutes$")
    public void i_wait_for_2_minutes() throws Throwable {
        Thread.sleep(120000);
    }
    @When("^I wait for 1 minutes$")
    public void i_wait_for_1_minutes() throws Throwable {
        Thread.sleep(60000);
    }

    @When("^I request \"([^\"]*)\" Bungii as a customer in \"([^\"]*)\" geofence from a partner location$")
    public void i_request_something_bungii_as_a_customer_in_something_geofence_from_a_partner_location(String bungiiType, String geofence, DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String bungiiTime = dataMap.get("Bungii Time").trim();
            String customer = dataMap.get("Customer Phone").trim();
            String customerName = dataMap.get("Customer Name").trim();

            int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "";

            custPhoneNum = customer;// PropertyUtility.getDataProperties("web.customer.user");
            custPassword = PropertyUtility.getDataProperties("web.customer.password");

            cucumberContextManager.setScenarioContext("CUSTOMER", customerName);//PropertyUtility.getDataProperties("web.customer.name"));
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            cucumberContextManager.setScenarioContext("GEOFENCE", geofence);

            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String custRef = customerServices.getCustomerRef(custAccessToken);
            cucumberContextManager.setScenarioContext("CUSTOMER_REF", custRef);
            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);

            //request Bungii
            coreServices.validatePickupRequestOfPartnerFirm(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequestOfPartnerFirm(custAccessToken, numberOfDriver, geofence);
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            if (bungiiType.equalsIgnoreCase("Solo Ondemand"))
                coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            else {
                int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
                cucumberContextManager.setScenarioContext("MIN_WAIT_BUNGII_START", wait);
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pass("I should able to request bungii ", "I requested " + bungiiType + " for '" + geofence + "'");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I cancel bungii as a customer \"([^\"]*)\" with phone number \"([^\"]*)\"$")
    public void i_cancel_bungii_as_a_customer_something_with_phone_number_something(String customerName, String customerPhone) throws Throwable {
        //throw new PendingException();
        String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String customerPhoneCode = "1", customerPassword = "";

        customerPassword = PropertyUtility.getDataProperties("web.customer.password");

        String custAccessToken = authServices.getCustomerToken(customerPhoneCode, customerPhone, customerPassword);

        coreServices.cancelBungiiAsCustomer(pickupRequest, custAccessToken);
        log("I cancel bungii as a Customer ","I canceled bungii "+pickupRequest+" as a customer  "+customerName  ,true );

    }

    @When("^I cancel bungii as a driver \"([^\"]*)\"$")
    public void i_cancel_bungii_as_a_driver_something(String driverName) throws Throwable {
        String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String driverPhoneCode = "1", driverPassword = "";
        driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
        String driverPhone = getDriverPhone(driverName);
        String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhone, driverPassword);
        coreServices.updateStatus(pickupRequest, driverAccessToken, 66);
        log("I cancel bungii as a driver","I canceled bungii "+pickupRequest+" as a driver "+driverName  ,true );

    }

    @Given("^that duo schedule bungii is in progress for customer \"([^\"]*)\"$")
    public void that_duo_schedule_bungii_is_in_progress_for_customer_something(String customerName, DataTable data) throws Throwable {

        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String geofence = dataMap.get("geofence").trim();
            String scheduleTime = dataMap.get("Bungii Time").trim();
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence.toLowerCase());

            String state = dataMap.get("Bungii State").trim();
            String customer = customerName;
            String driver1 = dataMap.get("Driver1").trim();
            String driver2 = dataMap.get("Driver2").trim();
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";

            String[] Details= getCustomerDriverDetailsForDuo(customerName);
            custPhoneNum = Details[0];
            custPassword = Details[1];
            cucumberContextManager.setScenarioContext("CUSTOMER", customerName);

            driverPhoneNum = Details[3];
            driverPassword = Details[4];
            cucumberContextManager.setScenarioContext("DRIVER_1", Details[5]);
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);

            driver2PhoneNum = Details[6];
            driver2Password = Details[7];
            cucumberContextManager.setScenarioContext("DRIVER_2", Details[8]);
            cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);


            if (driver1.equalsIgnoreCase("Kansas driver 1")) {
                driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("Kansas.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            }
            if (driver2.equalsIgnoreCase("Kansas driver 2")) {
                driver2PhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                driver2Password = PropertyUtility.getDataProperties("Kansas.driver2.password");
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("Kansas.driver2.name"));
            }
            if (driver1.equalsIgnoreCase("denver driver 1")) {
                driverPhoneNum = PropertyUtility.getDataProperties("denver.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("denver.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            }
            if (driver2.equalsIgnoreCase("denver driver 2")) {
                driver2PhoneNum = PropertyUtility.getDataProperties("denver.driver2.phone");
                driver2Password = PropertyUtility.getDataProperties("denver.driver2.password");
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("denver.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driverPhoneNum);
            }
            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            String driver2AccessToken = authServices.getDriverToken(driver2PhoneCode, driver2PhoneNum, driver2Password);

            String driverRef = driverServices.getDriverRef(driverAccessToken);
            String driver2Ref = driverServices.getDriverRef(driver2AccessToken);
            String custRef = customerServices.getCustomerRef(custAccessToken);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //update location and driver status
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);
            coreServices.updateDriverLocation(driver2AccessToken, geofence);
            coreServices.updateDriverStatus(driver2AccessToken);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 2, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            //int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
            int wait = 0;

            if (scheduleTime.equalsIgnoreCase("1 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 60);
            else if (scheduleTime.equalsIgnoreCase("2 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 120);
            else if (scheduleTime.equalsIgnoreCase("0.75 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 45);
            else if (scheduleTime.equalsIgnoreCase("0.5 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 30);
            else if (scheduleTime.equalsIgnoreCase("15 min ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken, 15);
            else
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try{ coreServices.getDriverScheduledPickupList(driverAccessToken);coreServices.driverView("",driverAccessToken);}catch (Exception e){}
            try{ coreServices.getDriverScheduledPickupList(driver2AccessToken);coreServices.driverView("",driver2AccessToken);}catch (Exception e){}
           // coreServices.waitForAvailableTrips(cucumberContextManager.getScenarioContext("DRIVER_1") + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest);
            //coreServices.waitForAvailableTrips(cucumberContextManager.getScenarioContext("DRIVER_2") + "(" + driverPhoneNum + ")", driver2AccessToken, pickupRequest);


            if (state.equalsIgnoreCase("Accepted")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);
            } else if (state.equalsIgnoreCase("Scheduled")) {

            } else if (state.equalsIgnoreCase("enroute")) {

                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    //  Thread.sleep(wait);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
            } else if (state.equalsIgnoreCase("arrived")) {

                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    //  Thread.sleep(wait);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
            } else if (state.equalsIgnoreCase("Driving To Dropoff")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    //  Thread.sleep(wait);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);

                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
            } else if (state.equalsIgnoreCase("unloading items")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    //  Thread.sleep(wait);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);

                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 27);
                coreServices.customerView(pickupRequest, custAccessToken);
                coreServices.driverView(pickupRequest, driverAccessToken);
                coreServices.driverView(pickupRequest, driver2AccessToken);
            } else {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    //  Thread.sleep(wait);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 27);
                coreServices.customerView(pickupRequest, custAccessToken);
                coreServices.driverView(pickupRequest, driverAccessToken);
                coreServices.driverView(pickupRequest, driver2AccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 28);
                coreServices.customerView(pickupRequest, custAccessToken);
                coreServices.driverView(pickupRequest, driverAccessToken);
                String driverPaymentMethod = coreServices.driverPaymentMethod(pickupRequest, driverAccessToken);
                String driver2PaymentMethod = coreServices.driverPaymentMethod(pickupRequest, driver2AccessToken);

                coreServices.rateAndTip(pickupRequest, custAccessToken, driverRef, driverPaymentMethod, 5.0, 0.0, driver2Ref, driver2PaymentMethod);
                System.out.println(pickupRequest);
            }
            pass("Precondition: Given that duo schedule bungii is in progress ", "Duo Schedule Bungii [ "+pickupRequest+" ]  by customer " +custPhoneNum+" is in " + state);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    public String[] getCustomerDriverDetails(String custName){
        String[] Details=new String[6];
        switch(custName)
        {
            case "Testcustomertywd_appleand_A Android":
                Details[0]=PropertyUtility.getDataProperties("customerA.phone.number");
                Details[1]=PropertyUtility.getDataProperties("customerA.phone.password");
                Details[2]=PropertyUtility.getDataProperties("customerA.phone.name");

                Details[3]=PropertyUtility.getDataProperties("driverA.phone.number");
                Details[4]=PropertyUtility.getDataProperties("driverA.phone.password");
                Details[5]=PropertyUtility.getDataProperties("driverA.phone.name");
                break;

            case "Testcustomertywd_appleand_B Android":
                Details[0]=PropertyUtility.getDataProperties("customerB.phone.number");
                Details[1]=PropertyUtility.getDataProperties("customerB.phone.password");
                Details[2]=PropertyUtility.getDataProperties("customerB.phone.name");

                Details[3]=PropertyUtility.getDataProperties("driverB.phone.number");
                Details[4]=PropertyUtility.getDataProperties("driverB.phone.password");
                Details[5]=PropertyUtility.getDataProperties("driverB.phone.name");
                break;

            case "Testcustomertywd_appleand_C Android":
                Details[0]=PropertyUtility.getDataProperties("customerC.phone.number");
                Details[1]=PropertyUtility.getDataProperties("customerC.phone.password");
                Details[2]=PropertyUtility.getDataProperties("customerC.phone.name");

                Details[3]=PropertyUtility.getDataProperties("driverC.phone.number");
                Details[4]=PropertyUtility.getDataProperties("driverC.phone.password");
                Details[5]=PropertyUtility.getDataProperties("driverC.phone.name");
                break;

            case "Testcustomertywd_appleand_D Android":
                Details[0]=PropertyUtility.getDataProperties("customerD.phone.number");
                Details[1]=PropertyUtility.getDataProperties("customerD.phone.password");
                Details[2]=PropertyUtility.getDataProperties("customerD.phone.name");

                Details[3]=PropertyUtility.getDataProperties("driverD.phone.number");
                Details[4]=PropertyUtility.getDataProperties("driverD.phone.password");
                Details[5]=PropertyUtility.getDataProperties("driverD.phone.name");
                break;

            case "Testcustomertywd_appleand_E Android":
                Details[0]=PropertyUtility.getDataProperties("customerE.phone.number");
                Details[1]=PropertyUtility.getDataProperties("customerE.phone.password");
                Details[2]=PropertyUtility.getDataProperties("customerE.phone.name");

                Details[3]=PropertyUtility.getDataProperties("driverE.phone.number");
                Details[4]=PropertyUtility.getDataProperties("driverE.phone.password");
                Details[5]=PropertyUtility.getDataProperties("driverE.phone.name");
                break;

            case "Testcustomertywd_appleand_F Android":
                Details[0]=PropertyUtility.getDataProperties("customerF.phone.number");
                Details[1]=PropertyUtility.getDataProperties("customerF.phone.password");
                Details[2]=PropertyUtility.getDataProperties("customerF.phone.name");

                Details[3]=PropertyUtility.getDataProperties("driverF.phone.number");
                Details[4]=PropertyUtility.getDataProperties("driverF.phone.password");
                Details[5]=PropertyUtility.getDataProperties("driverF.phone.name");
                break;

            case "Testcustomertywd_appleRicha Test":
                Details[0]=PropertyUtility.getDataProperties("Kansas.customer.phone");
                Details[1]=PropertyUtility.getDataProperties("Kansas.customer.password");
                Details[2]=PropertyUtility.getDataProperties("Kansas.customer.name");

                Details[3]=PropertyUtility.getDataProperties("Kansas.driver.phone");
                Details[4]=PropertyUtility.getDataProperties("Kansas.driver.password");
                Details[5]=PropertyUtility.getDataProperties("Kansas.driver.name");
                break;
            case "Testcustomertywd_appleNewMA Customer":
                Details[0]=PropertyUtility.getDataProperties("customerMA.phone.number");
                Details[1]=PropertyUtility.getDataProperties("customerMA.phone.password");
                Details[2]=PropertyUtility.getDataProperties("customerMA.phone.name");

                Details[3]=PropertyUtility.getDataProperties("driverG.phone.number");
                Details[4]=PropertyUtility.getDataProperties("driverG.phone.password");
                Details[5]=PropertyUtility.getDataProperties("driverG.phone.name");
                break;
            default:
                throw new IllegalStateException("The entry for the customer with the name: " + custName +" is not present.");
        }
        return Details;
    }

    public String[] getCustomerDriverDetailsForDuo(String custName){
        String[] Details=new String[9];
        switch(custName)
        {
            case "Testcustomertywd_appleand_A Android":
                    Details[0]=PropertyUtility.getDataProperties("customerA.phone.number");
                    Details[1]=PropertyUtility.getDataProperties("customerA.phone.password");
                    Details[2]=PropertyUtility.getDataProperties("customerA.phone.name");

                    Details[3]=PropertyUtility.getDataProperties("driverA.phone.number");
                    Details[4]=PropertyUtility.getDataProperties("driverA.phone.password");
                    Details[5]=PropertyUtility.getDataProperties("driverA.phone.name");

                    Details[6]=PropertyUtility.getDataProperties("driverB.phone.number");
                    Details[7]=PropertyUtility.getDataProperties("driverB.phone.password");
                    Details[8]=PropertyUtility.getDataProperties("driverB.phone.name");
                    break;

            case "Testcustomertywd_appleand_B Android":
                    Details[0]=PropertyUtility.getDataProperties("customerB.phone.number");
                    Details[1]=PropertyUtility.getDataProperties("customerB.phone.password");
                    Details[2]=PropertyUtility.getDataProperties("customerB.phone.name");

                    Details[3]=PropertyUtility.getDataProperties("driverB.phone.number");
                    Details[4]=PropertyUtility.getDataProperties("driverB.phone.password");
                    Details[5]=PropertyUtility.getDataProperties("driverB.phone.name");

                    Details[6]=PropertyUtility.getDataProperties("driverA.phone.number");
                    Details[7]=PropertyUtility.getDataProperties("driverA.phone.password");
                    Details[8]=PropertyUtility.getDataProperties("driverA.phone.name");
                    break;

            case "Testcustomertywd_appleand_C Android":
                    Details[0]=PropertyUtility.getDataProperties("customerC.phone.number");
                    Details[1]=PropertyUtility.getDataProperties("customerC.phone.password");
                    Details[2]=PropertyUtility.getDataProperties("customerC.phone.name");

                    Details[3]=PropertyUtility.getDataProperties("driverC.phone.number");
                    Details[4]=PropertyUtility.getDataProperties("driverC.phone.password");
                    Details[5]=PropertyUtility.getDataProperties("driverC.phone.name");

                    Details[6]=PropertyUtility.getDataProperties("driverB.phone.number");
                    Details[7]=PropertyUtility.getDataProperties("driverB.phone.password");
                    Details[8]=PropertyUtility.getDataProperties("driverB.phone.name");
                    break;

            case "Testcustomertywd_appleand_D Android":
                Details[0]=PropertyUtility.getDataProperties("customerD.phone.number");
                Details[1]=PropertyUtility.getDataProperties("customerD.phone.password");
                Details[2]=PropertyUtility.getDataProperties("customerD.phone.name");

                Details[3]=PropertyUtility.getDataProperties("driverD.phone.number");
                Details[4]=PropertyUtility.getDataProperties("driverD.phone.password");
                Details[5]=PropertyUtility.getDataProperties("driverD.phone.name");

                Details[6]=PropertyUtility.getDataProperties("driverC.phone.number");
                Details[7]=PropertyUtility.getDataProperties("driverC.phone.password");
                Details[8]=PropertyUtility.getDataProperties("driverC.phone.name");

               /// Details[6]=PropertyUtility.getDataProperties("driverB.phone.number");
               /// Details[7]=PropertyUtility.getDataProperties("driverB.phone.password");
                ///Details[8]=PropertyUtility.getDataProperties("driverB.phone.name");
                break;

            case "Testcustomertywd_appleand_E Android":
                Details[0]=PropertyUtility.getDataProperties("customerE.phone.number");
                Details[1]=PropertyUtility.getDataProperties("customerE.phone.password");
                Details[2]=PropertyUtility.getDataProperties("customerE.phone.name");

                Details[3]=PropertyUtility.getDataProperties("driverE.phone.number");
                Details[4]=PropertyUtility.getDataProperties("driverE.phone.password");
                Details[5]=PropertyUtility.getDataProperties("driverE.phone.name");

                Details[6]=PropertyUtility.getDataProperties("driverB.phone.number");
                Details[7]=PropertyUtility.getDataProperties("driverB.phone.password");
                Details[8]=PropertyUtility.getDataProperties("driverB.phone.name");
                break;

            case "Testcustomertywd_appleand_F Android":
                Details[0]=PropertyUtility.getDataProperties("customerF.phone.number");
                Details[1]=PropertyUtility.getDataProperties("customerF.phone.password");
                Details[2]=PropertyUtility.getDataProperties("customerF.phone.name");

                Details[3]=PropertyUtility.getDataProperties("driverF.phone.number");
                Details[4]=PropertyUtility.getDataProperties("driverF.phone.password");
                Details[5]=PropertyUtility.getDataProperties("driverF.phone.name");

                Details[6]=PropertyUtility.getDataProperties("driverE.phone.number");
                Details[7]=PropertyUtility.getDataProperties("driverE.phone.password");
                Details[8]=PropertyUtility.getDataProperties("driverE.phone.name");
                break;

            default:
                throw new IllegalStateException("The entry for the customer with the name: " + custName +" is not present.");
        }
        return Details;
    }

    public void ByPassNotification(String driverName, String bungiiType, String pickupRequest) {
        {
            //Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
           // List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            //while (i < DataList.size()) {
                try {
                    String driver1State = "Accepted";
                    //String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

                    cucumberContextManager.setScenarioContext("BUNGII_TYPE", bungiiType);
                    cucumberContextManager.setScenarioContext("DRIVER_1", driverName);

                    String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";
                    String driverAccessToken = "", driver2AccessToken = "";
                    //get geofence and pickup request from context
                    String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");

                    driverPhoneNum = getDriverPhone(driverName);
                    driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
                    //cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("web.valid.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
                    authServices.driverLogin(driverPhoneCode, driverPhoneNum, driverPassword); //Force login dunno why
                    driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
                    coreServices.updateDriverLocation(driverAccessToken, geofence); //to uncomment
                    coreServices.updateDriverStatus(driverAccessToken);
                    logger.detail("*** As a driver " + driverName + "(" + driverPhoneNum + ") " + bungiiType + "(" + pickupRequest + ") is being " + driver1State);
                    try{ coreServices.getDriverScheduledPickupList(driverAccessToken);coreServices.driverView("",driverAccessToken);}catch (Exception e){}

                    /*Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
                    if (!isDriverEligible)
                        error("Diver should be eligible for on demand trip", "Driver ID is not in eligibleDriver list", false);

                    if (driver1State.equalsIgnoreCase("Accepted")) {

                        coreServices.waitForAvailableTrips(driverName + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest);
                        coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                        coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                    }*/

                    if (bungiiType.equalsIgnoreCase("SOLO ONDEMAND")) {
                       Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
                        if (!isDriverEligible)
                            error("Diver should be eligible for on demand trip", "Driver ID is not in eligibleDriver list", false);

                        //for on demand enroute and accepted are same
                        if (driver1State.equalsIgnoreCase("Stacked Pickup Accepted")) {
                            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            coreServices.stackedPickupConfirmation(pickupRequest, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Enroute") || driver1State.equalsIgnoreCase("Accepted")) {
                            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                        } else if (driver1State.equalsIgnoreCase("Arrived")) {
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Loading Item")) {
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Driving To Dropoff")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Unloading Item")) {
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Bungii Completed")) {
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                        }

                    } else if (bungiiType.equalsIgnoreCase("SOLO SCHEDULED") || bungiiType.equalsIgnoreCase("Duo Scheduled")) {
                        if (driver1State.equalsIgnoreCase("Accepted")) {

                            coreServices.waitForAvailableTrips(driverName + "(" + driverPhoneNum + ")", driverAccessToken, pickupRequest);
                            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                        } else if (driver1State.equalsIgnoreCase("Enroute")) {
                            // coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            // coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                            int wait = (int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                           try {
                                while (wait > 1) {
                                    logger.detail("Waiting for " + wait / (60000 * 4) + " minute(s) before Scheduled trip can be started");
                                    Thread.sleep(60000);
                                    wait = wait - 60000 * 4;
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Arrived")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Loading Item")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Driving To Dropoff")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Unloading Item")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                        } else if (driver1State.equalsIgnoreCase("Bungii Completed")) {

                            coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                        }

                    }
                    //i++;
                } catch (Exception e) {

                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                    error("Step  Should be successful", "Error performing step,Please check logs for more details",
                            true);

                }
            //}

        }


    }
    private String getDaysLaterTime(int days)
    {
        String[] rtnArray = new String[2];
        int bufferTimeToStartTrip = 0;
        Calendar calendar = Calendar.getInstance();
        int mnts = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.DATE, days);
        calendar.set(Calendar.MINUTE, mnts+ 45);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 15;
        calendar.add(Calendar.MINUTE, (15 - mod));
        calendar.set(Calendar.SECOND, 0);
        Date nextQuatter = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// create a formatter for date
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = sdf.format(nextQuatter);

        String wait = (((15 - mod) + bufferTimeToStartTrip) * 1000 * 60) + "";
        rtnArray[0] = formattedDate+".000";
        rtnArray[1] = wait;

        logger.detail("TIME CALC BLOCK3 : "+  rtnArray[0]);
        cucumberContextManager.setScenarioContext("BUNGII_UTC", rtnArray[0]);

        return rtnArray[0];
    }


}
