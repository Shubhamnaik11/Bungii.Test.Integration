package com.bungii.api.stepdefinitions;

import com.bungii.api.utilityFunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.*;

public class BungiiSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(BungiiSteps.class);
    AuthServices authServices = new AuthServices();
    CoreServices coreServices = new CoreServices();
    PaymentServices paymentServices = new PaymentServices();
    DriverServices driverServices = new DriverServices();
    CustomerServices customerServices = new CustomerServices();


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

public String getDriverPhone(String driverName)
{
    String phone = null;
    switch (driverName) {
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
            phone=PropertyUtility.getDataProperties("valid.driver.kansas.phone");
            break;
        case "Testdrivertywd_appledc_a_jack Smith":
            phone = PropertyUtility.getDataProperties("web.valid.driver7.phone");
            break;
        case "Testdrivertywd_appledc_a_john Smith":
            phone = PropertyUtility.getDataProperties("web.valid.driver8.phone");
            break;

    }

    return phone;
}

    @And("^As a driver \"([^\"]*)\" perform below action with respective \"([^\"]*)\" trip$")
    public void as_a_driver_something_perform_below_action_with_respective_something_trip(String driverName, String bungiiType, DataTable data) throws Throwable {
        {
            //Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                try {
                    String driver1State = DataList.get(i).get("driver1 state").trim();//status like accepted/enroute etc

                    cucumberContextManager.setScenarioContext("BUNGII_TYPE", bungiiType);
                    cucumberContextManager.setScenarioContext("DRIVER_1", driverName);

                    String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";
                    String driverAccessToken = "", driver2AccessToken = "";
                    //get geofence and pickup request from context
                    String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");
                    String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

                    driverPhoneNum = getDriverPhone(driverName);
                    driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
                    //cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("web.valid.driver.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
                    driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
                    coreServices.updateDriverLocation(driverAccessToken, geofence);
                    coreServices.updateDriverStatus(driverAccessToken);

                    if (bungiiType.equalsIgnoreCase("SOLO ONDEMAND")) {
                        Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
                        if (!isDriverEligible)
                            error("Diver should be eligible for on demand trip", "Driver ID is not in eligibleDriver list", false);


                        //for on demand enroute and accepted are same
                        if (driver1State.equalsIgnoreCase("Enroute") || driver1State.equalsIgnoreCase("Accepted")) {
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

                    } else if (bungiiType.equalsIgnoreCase("SOLO SCHEDULED")||bungiiType.equalsIgnoreCase("Duo Scheduled")) {
                        if (driver1State.equalsIgnoreCase("Accepted")) {

                            coreServices.waitForAvailableTrips(driverAccessToken, pickupRequest);
                            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                        } else if (driver1State.equalsIgnoreCase("Enroute")) {
                            // coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                            // coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                            int wait = (int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                            try {
                                logger.detail("Waiting for " + wait / 60000 + " minutes before Scheduled trip can be started");
                              //  Thread.sleep(wait);
                                //sprint 32 changes
                                Thread.sleep(5000);

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
        public void as_a_driver_something_and_something_perform_below_action_with_respective_something_trip(String driverAName,String driverBName, String bungiiType, DataTable data) throws Throwable {
            {
                List<Map<String, String>> DataList = data.asMaps();
                int i = 0;
                while (i < DataList.size()) {
                    try {

                        String driver1State = DataList.get(i).get("driver1 state").trim();//status like accepted/enroute etc
                        String driver2State = DataList.get(i).get("driver2 state").trim();//status like accepted/enroute etc
                        cucumberContextManager.setScenarioContext("BUNGII_TYPE", bungiiType);
                        cucumberContextManager.setScenarioContext("DRIVER_1", driverAName);
                        cucumberContextManager.setScenarioContext("DRIVER_2", driverBName);

                        String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";
                        String driverAccessToken = "", driver2AccessToken = "";
                        //get geofence and pickup request from context
                        String geofence = (String) cucumberContextManager.getScenarioContext("GEOFENCE");
                        String tripLabel="",pickupRequest="";
                        try { tripLabel= DataList.get(i).get("label").trim();}catch (Exception e){}
                        if(tripLabel.equals(""))
                            pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                        else
                            pickupRequest = (String) cucumberContextManager.getFeatureContextContext("PICKUP_REQUEST_"+tripLabel);

                        driverPhoneNum = getDriverPhone(driverAName);
                        driverPassword = PropertyUtility.getDataProperties("web.valid.common.driver.password");
                      //  cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("web.valid.driver.name"));
                        cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
                        driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
                        coreServices.updateDriverLocation(driverAccessToken, geofence);
                        coreServices.updateDriverStatus(driverAccessToken);



                    driver2PhoneNum = getDriverPhone(driverBName);
                    driver2Password = PropertyUtility.getDataProperties("web.valid.common.driver.password");
                   // cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("web.valid.driver2.name"));
                    cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);
                    driver2AccessToken = authServices.getDriverToken(driver2PhoneCode, driver2PhoneNum, driver2Password);
                    coreServices.updateDriverLocation(driver2AccessToken, geofence);
                    coreServices.updateDriverStatus(driver2AccessToken);

                        if (bungiiType.equalsIgnoreCase("DUO SCHEDULED")) {

                            if(driver1State.equalsIgnoreCase("Accepted"))
                                coreServices.waitForAvailableTrips(driverAccessToken, pickupRequest);
                            if(driver2State.equalsIgnoreCase("Accepted"))
                                coreServices.waitForAvailableTrips(driver2AccessToken, pickupRequest);

                            if (driver1State.equalsIgnoreCase("Accepted") || driver1State.equalsIgnoreCase("Enroute") || driver1State.equalsIgnoreCase("Arrived") || driver1State.equalsIgnoreCase("Loading Item") || driver1State.equalsIgnoreCase("Driving To Dropoff") || driver1State.equalsIgnoreCase("Unloading Item") || driver1State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                            }
                            if (driver2State.equalsIgnoreCase("Accepted") || driver1State.equalsIgnoreCase("Enroute") || driver2State.equalsIgnoreCase("Arrived") || driver2State.equalsIgnoreCase("Loading Item") || driver2State.equalsIgnoreCase("Driving To Dropoff") || driver2State.equalsIgnoreCase("Unloading Item") || driver2State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);
                            }

                            boolean waitedForMinTime = false;
                            if (driver1State.equalsIgnoreCase("Enroute") || driver1State.equalsIgnoreCase("Arrived") || driver1State.equalsIgnoreCase("Loading Item") || driver1State.equalsIgnoreCase("Driving To Dropoff") || driver1State.equalsIgnoreCase("Unloading Item") || driver1State.equalsIgnoreCase("Bungii Completed")) {
                                int wait = (int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
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
                            if (driver2State.equalsIgnoreCase("Enroute") || driver2State.equalsIgnoreCase("Arrived") || driver2State.equalsIgnoreCase("Loading Item") || driver2State.equalsIgnoreCase("Driving To Dropoff") || driver2State.equalsIgnoreCase("Unloading Item") || driver2State.equalsIgnoreCase("Bungii Completed")) {
                                if (!waitedForMinTime) {
                                    int wait = (int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                                    try {
                                        logger.detail("Waiting for " + wait / 60000 + " minutes before Scheduled trip can be started");
                                        //Thread.sleep(wait);
                                        //from sprint 32 min time is changed to  1 hour
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                            }
                            if (driver1State.equalsIgnoreCase("Arrived") || driver1State.equalsIgnoreCase("Loading item") || driver1State.equalsIgnoreCase("Driving To Dropoff") || driver1State.equalsIgnoreCase("Unloading Item") || driver1State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            }
                            if (driver2State.equalsIgnoreCase("Arrived") || driver2State.equalsIgnoreCase("Loading Item") || driver2State.equalsIgnoreCase("Driving To Dropoff") || driver2State.equalsIgnoreCase("Unloading Item") || driver2State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                            }

                            if (driver1State.equalsIgnoreCase("Loading Item") || driver1State.equalsIgnoreCase("Driving To Dropoff") || driver1State.equalsIgnoreCase("Unloading Item") || driver1State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            }
                            if (driver2State.equalsIgnoreCase("Loading Item") || driver2State.equalsIgnoreCase("Driving To Dropoff") || driver2State.equalsIgnoreCase("Unloading Item") || driver2State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                            }


                            if (driver1State.equalsIgnoreCase("Driving To Dropoff") || driver1State.equalsIgnoreCase("Unloading item") || driver1State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            }
                            if (driver2State.equalsIgnoreCase("Driving To Dropoff") || driver2State.equalsIgnoreCase("Unloading item") || driver2State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                            }

                            if (driver1State.equalsIgnoreCase("Unloading item") || driver1State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driverAccessToken);
                            }
                            if (driver2State.equalsIgnoreCase("Unloading Item") || driver2State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 27);
                                coreServices.driverPollingCalls(pickupRequest, geofence, driver2AccessToken);
                            }

                            if (driver1State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
                            }
                            if (driver2State.equalsIgnoreCase("Bungii Completed")) {
                                coreServices.updateStatus(pickupRequest, driver2AccessToken, 28);
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
    @When("^I request \"([^\"]*)\" Bungii as a customer in \"([^\"]*)\" geofence$")
    public void i_request_something_bungii_as_a_customer_in_something_geofence(String bungiiType, String geofence, DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String customerLabel="";
            try {customerLabel = dataMap.get("Customer label").trim();   logger.detail("customerLabel is  specified as input"+customerLabel);} catch (Exception e) { }

            String bungiiTime = dataMap.get("Bungii Time").trim();
            String customer = dataMap.get("Customer Phone").trim();
            String customerName = dataMap.get("Customer Name").trim();
            String customerPasswordLabel="";
            try { customerPasswordLabel= dataMap.get("Customer Password").trim();}catch (Exception e){}

            int numberOfDriver =bungiiType.trim().equalsIgnoreCase("duo")?2:1;
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "";

            custPhoneNum = customer;// PropertyUtility.getDataProperties("web.customer.user");
            if(custPhoneNum.equalsIgnoreCase("NEW_USER_NUMBER"))
                custPhoneNum=(String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");

            if(customerPasswordLabel.equals(""))
                custPassword = PropertyUtility.getDataProperties("web.customer.password");
            else
                custPassword=customerPasswordLabel;

/*            cucumberContextManager.setScenarioContext("CUSTOMER", customerName);//PropertyUtility.getDataProperties("web.customer.name"));
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);*/


            if(customerLabel.equalsIgnoreCase("")) {
                cucumberContextManager.setScenarioContext("CUSTOMER", customerName);
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            }else
            {
                cucumberContextManager.setScenarioContext("CUSTOMER"+customerLabel, customerName);
                cucumberContextManager.setScenarioContext("CUSTOMER"+customerLabel+"_PHONE", custPhoneNum);
            }
            cucumberContextManager.setScenarioContext("GEOFENCE", geofence);
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence);

            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String custRef = customerServices.getCustomerRef(custAccessToken);

            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            cucumberContextManager.setScenarioContext("CUSTOMER_TOKEN", custAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, numberOfDriver, geofence);
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickupRequest);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            if(customerLabel.equalsIgnoreCase(""))
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            else
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken,customerLabel);

            if(bungiiType.equalsIgnoreCase("Solo Ondemand"))
                coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            else {

                int wait =coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,customerLabel);
                cucumberContextManager.setScenarioContext("MIN_WAIT_BUNGII_START",wait);
            }
            cucumberContextManager.setFeatureContextContext("BUNGII_INITIAL_SCH_TIME", System.currentTimeMillis() / 1000L);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log("I should able to request bungii ", "I requested "+bungiiType+" for '" + geofence+"'", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    public void createTripAndSaveInFeatureContext(String bungiiType, String geofence, String customer, String customerName, String customerPasswordLabel,String scenarioLabel) {
        try {
            logger.detail("Inside before hook");
            scenarioLabel="_"+scenarioLabel;
            int numberOfDriver =bungiiType.trim().equalsIgnoreCase("duo")?2:1;
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "";

            custPhoneNum = customer;// PropertyUtility.getDataProperties("web.customer.user");
            if(customerPasswordLabel.equals(""))
                custPassword = PropertyUtility.getDataProperties("web.customer.password");
            else
                custPassword=customerPasswordLabel;

            cucumberContextManager.setFeatureContextContext("CUSTOMER"+scenarioLabel, customerName);//PropertyUtility.getDataProperties("web.customer.name"));
            cucumberContextManager.setFeatureContextContext("CUSTOMER_PHONE"+scenarioLabel, custPhoneNum);
            cucumberContextManager.setFeatureContextContext("GEOFENCE"+scenarioLabel, geofence);
            cucumberContextManager.setFeatureContextContext("BUNGII_GEOFENCE"+scenarioLabel, geofence);
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence);

            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String custRef = customerServices.getCustomerRef(custAccessToken);

            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, numberOfDriver, geofence);
            cucumberContextManager.setFeatureContextContext("PICKUP_REQUEST"+scenarioLabel,pickupRequest);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"+scenarioLabel), custAccessToken);
            if(bungiiType.equalsIgnoreCase("Solo Ondemand"))
                coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            else {
                int wait =coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
                cucumberContextManager.setFeatureContextContext("MIN_WAIT_BUNGII_START"+scenarioLabel,wait);
                cucumberContextManager.setFeatureContextContext("BUNGII_INITIAL_SCH_TIME"+scenarioLabel, System.currentTimeMillis() / 1000L);

            }
            cucumberContextManager.setFeatureContextContext("BUNGII_TIME"+scenarioLabel,cucumberContextManager.getScenarioContext("BUNGII_TIME"));
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
    public void i_have_already_scheduled_bungii_with_something_label(String scenarioLabel) throws Throwable {
        logger.detail("cucumberContextManager"+cucumberContextManager.toString());
        testStepAssert.isTrue(!((String)cucumberContextManager.getFeatureContextContext("PICKUP_REQUEST"+"_"+scenarioLabel)).equals(""),"I should have already scheduled bungii","I should have already scheduled bungii,pickid"+(String)cucumberContextManager.getFeatureContextContext("PICKUP_REQUEST"+scenarioLabel));
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
            String driver1 = dataMap.get("Driver1").trim();
            String driver2 = dataMap.get("Driver2").trim();
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";

            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {

                if (customer.equalsIgnoreCase("customer-duo")) {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.phone.usedin.duo");
                    custPassword = PropertyUtility.getDataProperties("customer.password.usedin.duo");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name.usedin.duo"));

                }
                else if (customer.equalsIgnoreCase("denver customer")) {
                    custPhoneNum = PropertyUtility.getDataProperties("denver.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));

                }
                else {
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

            }
            else if (customer.equalsIgnoreCase("Kansas customer")) {
                custPhoneNum = PropertyUtility.getDataProperties("Kansas.customer2.phone");
                custPassword = PropertyUtility.getDataProperties("Kansas.customer2.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("Kansas.customer2.name"));
            }
            else {
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
            if(driver1.equalsIgnoreCase("Kansas driver 1"))
            {
                driverPhoneNum = PropertyUtility.getDataProperties("Kansas.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("Kansas.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            }
            if(driver2.equalsIgnoreCase("Kansas driver 2")) {
                driver2PhoneNum = PropertyUtility.getDataProperties("Kansas.driver2.phone");
                driver2Password = PropertyUtility.getDataProperties("Kansas.driver2.password");
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("Kansas.driver2.name"));
            }
            if(driver1.equalsIgnoreCase("denver driver 1"))
            {
                driverPhoneNum = PropertyUtility.getDataProperties("denver.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("denver.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            }
            if(driver2.equalsIgnoreCase("denver driver 2"))
            {
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
            int wait=0;

            if(scheduleTime.equalsIgnoreCase("1 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,60);
            else if(scheduleTime.equalsIgnoreCase("2 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,120);
            else if(scheduleTime.equalsIgnoreCase("0.75 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,45);
            else if(scheduleTime.equalsIgnoreCase("0.5 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,30);
            else if(scheduleTime.equalsIgnoreCase("15 min ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,15);
            else
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            coreServices.waitForAvailableTrips(driverAccessToken, pickupRequest);
            coreServices.waitForAvailableTrips(driver2AccessToken, pickupRequest);


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
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
            }else if (state.equalsIgnoreCase("arrived")) {

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
            }
            else if (state.equalsIgnoreCase("Driving To Dropoff")) {
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
            }else if (state.equalsIgnoreCase("unloading items")) {
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
            }
            else {
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

                coreServices.rateAndTip(pickupRequest, custAccessToken, driverRef, driverPaymentMethod, 5.0, 5.0, driver2Ref, driver2PaymentMethod);
                System.out.println(pickupRequest);
            }
            log("that duo schedule bungii is in progress", "that duo schedule bungii is on" + state, false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @When("^I request below Bungiis as a customer$")
    public void i_request_below_bungiis_as_a_customer(DataTable data) throws Throwable {
        List<Map<String, String>> DataList = data.asMaps();
        int i =0;
                while (i < DataList.size()) {
                    try {
                        String geofence = DataList.get(i).get("geofence").trim();cucumberContextManager.setScenarioContext("GEOFENCE",geofence.toLowerCase());
                        String bungiiType = DataList.get(i).get("Bungii Type").trim();//duo/solo/ONDEMAND
                        String bungiiTime = DataList.get(i).get("Bungii Time").trim();
                        String customer = DataList.get(i).get("Customer Phone").trim();
                        String customerName = DataList.get(i).get("Customer Name").trim();

                        int numberOfDriver =bungiiType.trim().equalsIgnoreCase("duo")?2:1;


                        String custPhoneCode = "1", custPhoneNum = "", custPassword = "";


                        custPhoneNum = customer;// PropertyUtility.getDataProperties("web.customer.user");
                        custPassword = PropertyUtility.getDataProperties("web.customer.password");

                        cucumberContextManager.setScenarioContext("CUSTOMER", customerName);//PropertyUtility.getDataProperties("web.customer.name"));
                        cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
                        cucumberContextManager.setScenarioContext("BUNGII_TYPE", bungiiType);


                        //LOGIN
                        String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
                        String custRef = customerServices.getCustomerRef(custAccessToken);

                        //CUSTOMER& DRIVER VIEW
                        coreServices.customerView("", custAccessToken);

                        //request Bungii
                        coreServices.validatePickupRequest(custAccessToken, geofence);
                        String pickupRequest = coreServices.getPickupRequest(custAccessToken, numberOfDriver, geofence);
                        cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickupRequest);
                        String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
                        coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
                        if(bungiiType.equalsIgnoreCase("ONDEMAND"))
                            coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
                        else {
                            int wait =coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
                            cucumberContextManager.setScenarioContext("MIN_WAIT_BUNGII_START",wait);
                        }

                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }



                        log("I should able to request bungii ", "I requested "+bungiiType+" for '" + geofence+"'", false);
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

                if(geofence.equalsIgnoreCase("denver")){
                    custPhoneNum = PropertyUtility.getDataProperties("denver.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("denver.customer.password");

                    driverPhoneNum = PropertyUtility.getDataProperties("denver.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("denver.driver.password");

                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));

                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));
                }
                else {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                    custPassword = PropertyUtility.getDataProperties("customer.password");

                    driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.password");

                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));

                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                }
            } else {

                if(geofence.equalsIgnoreCase("atlanta")){
                    custPhoneNum = PropertyUtility.getDataProperties("atlanta.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("atlanta.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("atlanta.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("atlanta.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.driver.name"));
                }else if(geofence.equalsIgnoreCase("kansas")){
                    custPhoneNum = PropertyUtility.getDataProperties("kansas.customer1.phone");
                    custPassword = PropertyUtility.getDataProperties("kansas.customer1.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("kansas.customer1.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));
                }
                else {
                    custPhoneNum = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                    custPassword = PropertyUtility.getDataProperties("customer_generic.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer_generic.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));
                }
            }
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            cucumberContextManager.setScenarioContext("CUSTOMER1_PHONE", custPhoneNum);
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);

            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            String driverRef = driverServices.getDriverRef(driverAccessToken);
            String custRef = customerServices.getCustomerRef(custAccessToken);

            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            coreServices.driverView("", driverAccessToken);

            //update location and driver status
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            int wait=0;

            if(scheduleTime.equalsIgnoreCase("1 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,60);
            else if(scheduleTime.equalsIgnoreCase("2 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,120);
            else if(scheduleTime.equalsIgnoreCase("0.75 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,45);
            else if(scheduleTime.equalsIgnoreCase("0.5 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,30);
            else if(scheduleTime.equalsIgnoreCase("15 min ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,15);
            else if(scheduleTime.equalsIgnoreCase("1.5 hour ahead"))
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken,90);
            else
                wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);

            cucumberContextManager.setFeatureContextContext("BUNGII_INITIAL_SCH_TIME", System.currentTimeMillis() / 1000L);


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            coreServices.waitForAvailableTrips(driverAccessToken, pickupRequest);

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
            } else {

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

                coreServices.rateAndTip(pickupRequest, custAccessToken, driverRef, driverPaymentMethod, 5.0, 5.0);
            }
            log("that solo schedule bungii is in progress", "that solo schedule bungii is on" + state, false);
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
            String driverLabel="",tripLabel="";
            try {driverLabel = dataMap.get("Driver label").trim();   logger.detail("Label is  specified as input"+driverLabel);} catch (Exception e) { }
            try {tripLabel = dataMap.get("Trip Label").trim();   logger.detail("Label is  specified as input"+driverLabel);} catch (Exception e) { }


            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {
                if(geofence.equalsIgnoreCase("miami")){
                    custPhoneNum = PropertyUtility.getDataProperties("miami.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("miami.customer.password");
                    driverPhoneNum = PropertyUtility.getDataProperties("miami.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("miami.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("miami.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("miami.customer.name"));
                }else if(geofence.equalsIgnoreCase("nashville")){
                    custPhoneNum = PropertyUtility.getDataProperties("nashville.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("nashville.customer.password");
                    driverPhoneNum = PropertyUtility.getDataProperties("nashville.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("nashville.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("nashville.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("nashville.customer.name"));
                }else if(geofence.equalsIgnoreCase("denver")){
                    custPhoneNum = PropertyUtility.getDataProperties("denver.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("denver.customer.password");
                    if (driverLabel.equalsIgnoreCase("driver 2")) {
                        driverPhoneNum = PropertyUtility.getDataProperties("denver.driver2.phone");
                        driverPassword = PropertyUtility.getDataProperties("denver.driver2.password");
                    }else {
                        driverPhoneNum = PropertyUtility.getDataProperties("denver.driver.phone");
                        driverPassword = PropertyUtility.getDataProperties("denver.driver.password");
                    }
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));
                }
                else {
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
                }else if (geofence.equals("baltimore")) {
                    custPhoneNum = PropertyUtility.getDataProperties("baltimore.customer.phone");
                    custPassword = PropertyUtility.getDataProperties("baltimore.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("baltimore.customer.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("baltimore.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("baltimore.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("baltimore.driver.name"));
                }else if (geofence.contains("atlanta")) {
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
                }else if (geofence.equals("goa")) {
                    custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                    custPassword = PropertyUtility.getDataProperties("customer.password");
                    driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                }
                else{
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

            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);
            coreServices.driverView("", driverAccessToken);


            //update location and driver status
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            //In case of having default promo code  "ADDED_PROMOCODE_WALLETREF" hold value of wallet ref, else return empty string
            if(tripLabel.trim().equalsIgnoreCase(""))
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            else
                coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken,tripLabel);
            coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            Boolean isDriverEligibel = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
            if (!isDriverEligibel)
                error("Diver should be eligible for on demand trip", "Dirver ID is not in eligibleDriver list", false);

            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            if (state.equalsIgnoreCase("Enroute")) {
            } else if (state.equalsIgnoreCase("ARRIVED")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
            }else if (state.equalsIgnoreCase("LOADING ITEM")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
            }else if (state.equalsIgnoreCase("DRIVING TO DROP OFF")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
            }else if (state.equalsIgnoreCase("UNLOADING ITEM")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
            }
            else {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
            }
            log("that  bungii is in progress", "that   bungii is on" + state, false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    public void recoveryScenario(){
        String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";
        logger.detail("***********Inside recovery method of api");

        custPhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        custPassword = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PASSWORD");
        custPassword=custPassword.equalsIgnoreCase("")?"Cci12345":custPassword;
        if (!custPhoneNum.equalsIgnoreCase("")) {
            handleOngoingBungii(custPhoneCode, custPhoneNum, custPassword);
            cancelScheduledBungii(custPhoneCode, custPhoneNum, custPassword);
        }
        // remove driver 2 bungiis
        custPhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
        custPassword = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PASSWORD");
        custPassword=custPassword.equalsIgnoreCase("")?"Cci12345":custPassword;
        if (!custPhoneNum.equalsIgnoreCase("")) {
            handleOngoingBungii(custPhoneCode, custPhoneNum, custPassword);
            cancelScheduledBungii(custPhoneCode, custPhoneNum, custPassword);
        }


    }
    public void handleOngoingBungii(String custPhoneCode,String custPhoneNum, String custPassword){
        String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
        coreServices.cancelOrCompleteOngoingBungii( custAccessToken);
    }
    public void cancelScheduledBungii(String custPhoneCode,String custPhoneNum, String custPassword){
        String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
        coreServices.cancelAllScheduledBungiis(custAccessToken);
    }

    @When("^I cancel all bungiis of customer$")
    public void i_request_something_bungii_as_a_customer_in_something_geofence( DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String custPhoneCode = "1", custPhoneNum = "", custPassword = "",cust2PhoneNum = "", cust2Password = "";

            custPhoneNum = dataMap.get("Customer Phone").trim();
            if(custPhoneNum.equalsIgnoreCase("CUSTOMER1_PHONE")){
                custPhoneNum=(String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
            }
            custPassword = PropertyUtility.getDataProperties("customer.password");

            if (!custPhoneNum.equalsIgnoreCase("")) {
                handleOngoingBungii(custPhoneCode, custPhoneNum, custPassword);
                cancelScheduledBungii(custPhoneCode, custPhoneNum, custPassword);
            }

            cust2PhoneNum = dataMap.get("Customer2 Phone").trim();
            if(cust2PhoneNum.equalsIgnoreCase("CUSTOMER2_PHONE")){
                cust2PhoneNum=(String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
            } else if(cust2PhoneNum.equalsIgnoreCase("CUSTOMER_PHONE_EXTRA")){
                cust2PhoneNum=(String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA");
            }
            cust2Password = PropertyUtility.getDataProperties("customer.password");

            if (!cust2PhoneNum.equalsIgnoreCase("")) {
                Thread.sleep(10000);
                handleOngoingBungii(custPhoneCode, cust2PhoneNum, cust2Password);
                cancelScheduledBungii(custPhoneCode, cust2PhoneNum, cust2Password);
            }

    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^bungii admin manually end bungii created by \"([^\"]*)\"$")
    public void i_request_something_bungii_as_a_customer_in_something_geofence( String customer) {
        try {
            //wait for trip to be reflected
            Thread.sleep(120000);
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "",cust2PhoneNum = "", cust2Password = "";

            if(customer.equalsIgnoreCase("CUSTOMER1")){
                custPhoneNum=(String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
            }else if(customer.equalsIgnoreCase("CUSTOMER2")){
                custPhoneNum=(String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
            }
            custPassword = PropertyUtility.getDataProperties("customer.password");

            if (!custPhoneNum.equalsIgnoreCase("")) {
                String pickupRef = new DbUtility().getPickupRef( custPhoneNum);
                new WebPortal().asAdminManuallyEndBungii(pickupRef);
                log("bungii admin manually end bungii created by "+customer, "manually ended bungii" , false);

            }else{
                fail("bungii admin manually end bungii created by "+customer, "Not able to find customer" , false);

            }



        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^The first time promo code should get released$")
    public void the_first_time_promo_code_should_get_released() throws Throwable {
        String custAccessToken = (String) cucumberContextManager.getScenarioContext("CUSTOMER_TOKEN");
        Response promoData=coreServices.getPromoCodes(custAccessToken,"");
        String promo=getPromoCode(promoData,"");
        testStepAssert.isTrue(promo.equalsIgnoreCase("PROMO1"),"First time promo code is released","Pass");
    }

    public String  getPromoCode(Response response, String codeType){
        String promoCode="";
        JsonPath jsonPathEvaluator =response.jsonPath();
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
}
