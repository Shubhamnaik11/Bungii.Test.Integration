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



    @And("I driver perfom this action with Bungii")
    public void iAcceptBungii(DataTable data) {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

        String driver1 = dataMap.get("driver1").trim();
        String driver2 = dataMap.get("driver2").trim();
        String driver1State = dataMap.get("driver1 state").trim();//status like accepted/enroute etc
        String driver2State = dataMap.get("driver1 state").trim();
        String bungiiType = dataMap.get("Bungii Type").trim();//duo/solo/ONDEMAND

        String driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "", driver2PhoneCode = "1", driver2PhoneNum = "", driver2Password = "";
        String driverAccessToken="",driver2AccessToken="";
        //get geofence and pickup request from context
        String geofence =(String) cucumberContextManager.getScenarioContext("GEOFENCE");
        String pickupRequest =(String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        if(driver1.equalsIgnoreCase("valid")) {
            driverPhoneNum = PropertyUtility.getDataProperties("web.valid.driver.phone");
            driverPassword = PropertyUtility.getDataProperties("web.valid.driver.password");
            cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("web.valid.driver.name"));
            cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", driverPhoneNum);
            driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
            coreServices.updateDriverLocation(driverAccessToken, geofence);
            coreServices.updateDriverStatus(driverAccessToken);
        }
        if(driver2.equalsIgnoreCase("valid") && bungiiType.equalsIgnoreCase("duo")) {

        driver2PhoneNum = PropertyUtility.getDataProperties("web.valid.driver2.phone");
        driver2Password = PropertyUtility.getDataProperties("web.valid.driver2.password");
        cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("web.valid.driver2.name"));
        cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", driver2PhoneNum);
        driver2AccessToken = authServices.getDriverToken(driver2PhoneCode, driver2PhoneNum, driver2Password);
        coreServices.updateDriverLocation(driver2AccessToken, geofence);
        coreServices.updateDriverStatus(driver2AccessToken);
        }
        if(bungiiType.equalsIgnoreCase("ONDEMAND"))
        {
            Boolean isDriverEligibel = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequest);
            if (!isDriverEligibel)
                error("Diver should be eligible for on demand trip", "Dirver ID is not in eligibleDriver list", false);

            coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
            //for on demand enroute and accepted are same
            if (driver1State.equalsIgnoreCase("Enroute") || driver1State.equalsIgnoreCase("Accepted")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            } else if (driver1State.equalsIgnoreCase("ARRIVED")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
                coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            } else if (driver1State.equalsIgnoreCase("loading item")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            } else if (driver1State.equalsIgnoreCase("DRIVING TO DROP OFF")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            } else if (driver1State.equalsIgnoreCase("unloading item")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            } else if (driver1State.equalsIgnoreCase("bungii completed")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
            }
        }else if(bungiiType.equalsIgnoreCase("solo")){

            coreServices.waitForAvailableTrips(driverAccessToken, pickupRequest);

            if (driver1State.equalsIgnoreCase("Accepted")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            }
            else if (driver1State.equalsIgnoreCase("enroute")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                int wait =(int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                try { Thread.sleep(wait); } catch (InterruptedException e) {e.printStackTrace(); }
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            }
            else if (driver1State.equalsIgnoreCase("ARRIVED")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                int wait =(int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                try { Thread.sleep(wait); } catch (InterruptedException e) {e.printStackTrace(); }
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            } else if (driver1State.equalsIgnoreCase("loading item")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                int wait =(int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                try { Thread.sleep(wait); } catch (InterruptedException e) {e.printStackTrace(); }
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            } else if (driver1State.equalsIgnoreCase("DRIVING TO DROP OFF")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                int wait =(int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                try { Thread.sleep(wait); } catch (InterruptedException e) {e.printStackTrace(); }
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            } else if (driver1State.equalsIgnoreCase("unloading item")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                int wait =(int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                try { Thread.sleep(wait); } catch (InterruptedException e) {e.printStackTrace(); }
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            } else if (driver1State.equalsIgnoreCase("bungii completed")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
                int wait =(int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                try { Thread.sleep(wait); } catch (InterruptedException e) {e.printStackTrace(); }
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
            }

         }else if(bungiiType.equalsIgnoreCase("duo")){

            coreServices.waitForAvailableTrips(driverAccessToken, pickupRequest);
            coreServices.waitForAvailableTrips(driver2AccessToken, pickupRequest);

            if (driver1State.equalsIgnoreCase("Accepted")||driver1State.equalsIgnoreCase("enroute")||driver1State.equalsIgnoreCase("ARRIVED")||driver1State.equalsIgnoreCase("loading item")||driver1State.equalsIgnoreCase("DRIVING TO DROP OFF")||driver1State.equalsIgnoreCase("unloading item")||driver1State.equalsIgnoreCase("bungii completed")) {
                coreServices.pickupdetails(pickupRequest, driverAccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            }
            if (driver2State.equalsIgnoreCase("Accepted")||driver1State.equalsIgnoreCase("enroute")||driver2State.equalsIgnoreCase("ARRIVED")||driver2State.equalsIgnoreCase("loading item")||driver2State.equalsIgnoreCase("DRIVING TO DROP OFF")||driver2State.equalsIgnoreCase("unloading item")||driver2State.equalsIgnoreCase("bungii completed")) {
                coreServices.pickupdetails(pickupRequest, driver2AccessToken, geofence);
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);
            }

            boolean waitedForMinTime=false;
            if (driver1State.equalsIgnoreCase("enroute")||driver1State.equalsIgnoreCase("ARRIVED")||driver1State.equalsIgnoreCase("loading item")||driver1State.equalsIgnoreCase("DRIVING TO DROP OFF")||driver1State.equalsIgnoreCase("unloading item")||driver1State.equalsIgnoreCase("bungii completed")) {
                int wait =(int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                try { Thread.sleep(wait);waitedForMinTime=true; } catch (InterruptedException e) { e.printStackTrace(); }
                coreServices.updateStatus(pickupRequest, driverAccessToken, 23);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            }
            if (driver2State.equalsIgnoreCase("enroute")||driver2State.equalsIgnoreCase("ARRIVED")||driver2State.equalsIgnoreCase("loading item")||driver2State.equalsIgnoreCase("DRIVING TO DROP OFF")||driver2State.equalsIgnoreCase("unloading item")||driver2State.equalsIgnoreCase("bungii completed")) {
                if(!waitedForMinTime) {
                    int wait = (int) cucumberContextManager.getScenarioContext("MIN_WAIT_BUNGII_START");
                    try { Thread.sleep(wait); } catch (InterruptedException e) {e.printStackTrace(); }
                }
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);coreServices.driverPollingCalls( pickupRequest, geofence,  driver2AccessToken);
            }
            if (driver1State.equalsIgnoreCase("ARRIVED")||driver1State.equalsIgnoreCase("loading item")||driver1State.equalsIgnoreCase("DRIVING TO DROP OFF")||driver1State.equalsIgnoreCase("unloading item")||driver1State.equalsIgnoreCase("bungii completed")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 24);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            }
            if (driver2State.equalsIgnoreCase("ARRIVED")||driver2State.equalsIgnoreCase("loading item")||driver2State.equalsIgnoreCase("DRIVING TO DROP OFF")||driver2State.equalsIgnoreCase("unloading item")||driver2State.equalsIgnoreCase("bungii completed")) {
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 24);coreServices.driverPollingCalls( pickupRequest, geofence,  driver2AccessToken);
            }

            if (driver1State.equalsIgnoreCase("loading item")||driver1State.equalsIgnoreCase("DRIVING TO DROP OFF")||driver1State.equalsIgnoreCase("unloading item")||driver1State.equalsIgnoreCase("bungii completed")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 25);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            }
            if (driver2State.equalsIgnoreCase("loading item")||driver2State.equalsIgnoreCase("DRIVING TO DROP OFF")||driver2State.equalsIgnoreCase("unloading item")||driver2State.equalsIgnoreCase("bungii completed")) {
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 25);coreServices.driverPollingCalls( pickupRequest, geofence,  driver2AccessToken);
            }


            if (driver1State.equalsIgnoreCase("DRIVING TO DROP OFF")||driver1State.equalsIgnoreCase("unloading item")||driver1State.equalsIgnoreCase("bungii completed")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 26);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            }
            if (driver2State.equalsIgnoreCase("DRIVING TO DROP OFF")||driver2State.equalsIgnoreCase("unloading item")||driver2State.equalsIgnoreCase("bungii completed")) {
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 26);coreServices.driverPollingCalls( pickupRequest, geofence,  driver2AccessToken);
            }

            if (driver1State.equalsIgnoreCase("unloading item")||driver1State.equalsIgnoreCase("bungii completed")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 27);coreServices.driverPollingCalls( pickupRequest, geofence,  driverAccessToken);
            }
            if (driver2State.equalsIgnoreCase("unloading item")||driver2State.equalsIgnoreCase("bungii completed")) {
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 27);coreServices.driverPollingCalls( pickupRequest, geofence,  driver2AccessToken);
            }

            if (driver1State.equalsIgnoreCase("bungii completed")) {
                coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
            }
            if (driver2State.equalsIgnoreCase("bungii completed")) {
                coreServices.updateStatus(pickupRequest, driver2AccessToken, 28);
            }


        }
        if(!driver2.equalsIgnoreCase(""))
            log("I driver perfom this action with Bungi", "As driver 1, for "+bungiiType+" in '" + geofence+"' is at"+driver1State+"state" +"And driver 2, is at"+driver2State+"state", false);
        else
            log("I driver perfom this action with Bungi", "As driver 1, for "+bungiiType+" in '" + geofence+"' is at"+driver1State+"state", false);

    }
    @When("I request Bungii")
    public void iRequestBungii(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String geofence = dataMap.get("geofence").trim();cucumberContextManager.setScenarioContext("GEOFENCE",geofence.toLowerCase());
            String bungiiType = dataMap.get("Bungii Type").trim();//duo/solo/ONDEMAND
            String bungiiTime = dataMap.get("Bungii Time").trim();
            String customer = dataMap.get("Customer").trim();
            int numberOfDriver =bungiiType.trim().equalsIgnoreCase("duo")?2:1;


            String custPhoneCode = "1", custPhoneNum = "", custPassword = "";


            custPhoneNum = PropertyUtility.getDataProperties("web.customer.user");
            custPassword = PropertyUtility.getDataProperties("web.customer.password");
            cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("web.customer.name"));
            cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", custPhoneNum);


            //LOGIN
            String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
            String custRef = customerServices.getCustomerRef(custAccessToken);

            //CUSTOMER& DRIVER VIEW
            coreServices.customerView("", custAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, numberOfDriver, geofence);cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickupRequest);
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
                custPhoneNum = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                custPassword = PropertyUtility.getDataProperties("customer_generic.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer_generic.name"));


                driverPhoneNum = PropertyUtility.getDataProperties("valid.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("valid.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));
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

    @Given("that solo schedule bungii is in progress")
    public void thatsoloscheduleBungiiIsInProgress(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String geofence = dataMap.get("geofence").trim();
            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence.toLowerCase());
            String state = dataMap.get("Bungii State").trim();
            String custPhoneCode = "1", custPhoneNum = "", custPassword = "", driverPhoneCode = "1", driverPhoneNum = "", driverPassword = "";


            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS")) {
                custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                custPassword = PropertyUtility.getDataProperties("customer.password");

                driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.password");

                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));

                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));

            } else {
                custPhoneNum = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                custPassword = PropertyUtility.getDataProperties("customer_generic.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer_generic.name"));

                driverPhoneNum = PropertyUtility.getDataProperties("valid.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("valid.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));

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
                custPhoneNum = PropertyUtility.getDataProperties("customer.user");
                custPassword = PropertyUtility.getDataProperties("customer.password");

                driverPhoneNum = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("ios.valid.driver.password");

                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));

                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));

            } else {
                custPhoneNum = PropertyUtility.getDataProperties("customer_generic.phonenumber");
                custPassword = PropertyUtility.getDataProperties("customer_generic.password");
                cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer_generic.name"));

                driverPhoneNum = PropertyUtility.getDataProperties("valid.driver.phone");
                driverPassword = PropertyUtility.getDataProperties("valid.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.name"));

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
}
