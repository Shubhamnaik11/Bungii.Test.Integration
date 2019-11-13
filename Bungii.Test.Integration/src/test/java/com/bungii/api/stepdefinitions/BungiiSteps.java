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
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

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
                    } else if (bungiiType.equalsIgnoreCase("SOLO SCHEDULED")) {
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
                                Thread.sleep(wait);
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
                        String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

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

                            coreServices.waitForAvailableTrips(driverAccessToken, pickupRequest);
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
                                    Thread.sleep(wait);
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
                                        Thread.sleep(wait);
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
            String bungiiTime = dataMap.get("Bungii Time").trim();
            String customer = dataMap.get("Customer Phone").trim();
            String customerName = dataMap.get("Customer Name").trim();

            int numberOfDriver =bungiiType.trim().equalsIgnoreCase("duo")?2:1;
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "";

            custPhoneNum = customer;// PropertyUtility.getDataProperties("web.customer.user");
            custPassword = PropertyUtility.getDataProperties("web.customer.password");

            cucumberContextManager.setScenarioContext("CUSTOMER", customerName);//PropertyUtility.getDataProperties("web.customer.name"));
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);
            cucumberContextManager.setScenarioContext("GEOFENCE", geofence);

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
            if(bungiiType.equalsIgnoreCase("Solo Ondemand"))
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

    }
    @Given("that duo schedule bungii is in progress")
    public void thatduoScheduleBungiiIsInProgress(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String geofence = dataMap.get("geofence").trim();
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

            } else {
                custPhoneNum = PropertyUtility.getDataProperties("atlanta.customer.phone");
                custPassword = PropertyUtility.getDataProperties("atlanta.customer.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("atlanta.customer.name"));


                driverPhoneNum = PropertyUtility.getDataProperties("atlanta.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("atlanta.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("atlanta.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);

                driver2PhoneNum = PropertyUtility.getDataProperties("valid.driver2.phone");
                driver2Password = PropertyUtility.getDataProperties("valid.driver2.password");
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("valid.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);

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
                int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);

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
            } else if (state.equalsIgnoreCase("enroute")) {

                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
            } else {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

                try {
                    Thread.sleep(wait);
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
                    custPhoneNum = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                    custPassword = PropertyUtility.getDataProperties("customer_generic.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer_generic.name"));

                    driverPhoneNum = PropertyUtility.getDataProperties("valid.driver.phone");
                    driverPassword = PropertyUtility.getDataProperties("valid.driver.password");
                    cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));
                }else {
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
            int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);

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
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
            } else if (state.equalsIgnoreCase("Scheduled")) {
                //do nothing, already in scheduled state
            } else {
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
            coreServices.recalculateEstimate(pickupRequest, (String) cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"), custAccessToken);
            coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
            Boolean isDriverEligibel = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
            if (!isDriverEligibel)
                error("Diver should be eligible for on demand trip", "Dirver ID is not in eligibleDriver list", false);

            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            if (state.equalsIgnoreCase("Enroute")) {
            } else if (state.equalsIgnoreCase("ARRIVED")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
            } else {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
            }
            log("that duo schedule bungii is in progress", "that duo schedule bungii is on" + state, false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    public void recoveryScenario(){
        String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";

        custPhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        custPassword = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PASSWORD");
        custPassword=custPassword.equalsIgnoreCase("")?"Cci12345":custPassword;
        if (!custPhoneNum.equalsIgnoreCase("")) {
            cancelCurrentBungii(custPhoneCode, custPhoneNum, custPassword);
            cancelScheduledBungii(custPhoneCode, custPhoneNum, custPassword);
        }

    }
    public void cancelCurrentBungii(String custPhoneCode,String custPhoneNum, String custPassword){
        String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
        coreServices.cancelOngoingBungii( custAccessToken);
    }
    public void cancelScheduledBungii(String custPhoneCode,String custPhoneNum, String custPassword){
        String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
        coreServices.cancelScheduledBungii(custAccessToken);
    }
}
