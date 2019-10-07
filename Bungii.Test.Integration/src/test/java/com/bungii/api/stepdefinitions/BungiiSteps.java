package com.bungii.api.stepdefinitions;

import com.bungii.api.utilityFunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.Given;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;

public class BungiiSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(BungiiSteps.class);
    AuthServices authServices = new AuthServices();
    CoreServices coreServices = new CoreServices();
    PaymentServices paymentServices = new PaymentServices();
    DriverServices driverServices = new DriverServices();
    CustomerServices customerServices = new CustomerServices();

    public void createBungii() {
        String custPhoneCode = "1", custPhoneNum = "9871450101", custPassword = "Cci12345";
        String driverPhoneCode = "1", driverPhoneNum = "8871450101", driverPassword = "Cci12345";
        //LOGIN
        String custAccessToken = authServices.getCustomerToken(custPhoneCode, custPhoneNum, custPassword);
        String driverAccessToken = authServices.getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);

        //CUSTOMER& DRIVER VIEW
        coreServices.customerView("", custAccessToken);
        coreServices.driverView("", driverAccessToken);

        //update location and driver status
        coreServices.updateDriverLocation(driverAccessToken,"goa");
        coreServices.updateDriverStatus(driverAccessToken);

        //request Bungii
        coreServices.validatePickupRequest(custAccessToken, "goa");
        String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, "goa");
        String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
        coreServices.recalculateEstimate(pickupRequest, "", custAccessToken);
        coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");


        coreServices.pickupdetails(pickupRequest, driverAccessToken,"goa");
        coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
        coreServices.updateStatus(pickupRequest, driverAccessToken, 24);
        coreServices.updateStatus(pickupRequest, driverAccessToken, 25);
        coreServices.updateStatus(pickupRequest, driverAccessToken, 26);
        coreServices.updateStatus(pickupRequest, driverAccessToken, 27);
        coreServices.updateStatus(pickupRequest, driverAccessToken, 28);
        System.out.println(pickupRequest);
    }

    public void createSoloBungii() {
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
        coreServices.updateDriverLocation(driverAccessToken,"goa");
        coreServices.updateDriverStatus(driverAccessToken);

        //request Bungii
        coreServices.validatePickupRequest(custAccessToken, "goa");
        String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, "goa");
        String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
        coreServices.recalculateEstimate(pickupRequest, "", custAccessToken);
        int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);


        coreServices.pickupdetails(pickupRequest, driverAccessToken);
        coreServices.updateStatus(pickupRequest, driverAccessToken, 21);

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
        System.out.println(pickupRequest);
    }

    public void createDuoBungii() {
        String custPhoneCode = "1", custPhoneNum = "9871450101", custPassword = "Cci12345";
        String driverPhoneCode = "1", driverPhoneNum = "8871450101", driverPassword = "Cci12345";
        String driver2PhoneCode = "1", driver2PhoneNum = "8871450102", driver2Password = "Cci12345";
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
        coreServices.updateDriverLocation(driverAccessToken,"goa");
        coreServices.updateDriverStatus(driverAccessToken);
        coreServices.updateDriverLocation(driver2AccessToken,"goa");
        coreServices.updateDriverStatus(driver2AccessToken);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //request Bungii
        coreServices.validatePickupRequest(custAccessToken, "goa");
        String pickupRequest = coreServices.getPickupRequest(custAccessToken, 2, "goa");
        String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
        coreServices.recalculateEstimate(pickupRequest, "", custAccessToken);
        int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        coreServices.pickupdetails(pickupRequest, driverAccessToken);
        coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
        coreServices.pickupdetails(pickupRequest, driver2AccessToken);
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

    public void givenIamIAlreadyhasschdeuledBungii() {
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
        coreServices.updateDriverLocation(driverAccessToken,"goa");
        coreServices.updateDriverStatus(driverAccessToken);

        //request Bungii
        coreServices.validatePickupRequest(custAccessToken, "goa");
        String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, "goa");
        String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
        coreServices.recalculateEstimate(pickupRequest, "", custAccessToken);
        int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
    }

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
        coreServices.updateDriverLocation(driverAccessToken,"goa");
        coreServices.updateDriverStatus(driverAccessToken);

        //request Bungii
        coreServices.validatePickupRequest(custAccessToken, "goa");
        String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, "goa");
        String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
        coreServices.recalculateEstimate(pickupRequest, "", custAccessToken);
        coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");
    }

    @Given("that duo schedule bungii is in progress")
    public void thatduoScheduleBungiiIsInProgress(DataTable data) {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

        String geofence = dataMap.get("geofence").trim();
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
        coreServices.updateDriverLocation(driverAccessToken,geofence);
        coreServices.updateDriverStatus(driverAccessToken);
        coreServices.updateDriverLocation(driver2AccessToken,geofence);
        coreServices.updateDriverStatus(driver2AccessToken);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //request Bungii
        coreServices.validatePickupRequest(custAccessToken, geofence);
        String pickupRequest = coreServices.getPickupRequest(custAccessToken, 2, geofence);
        String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
        coreServices.recalculateEstimate(pickupRequest, "", custAccessToken);
        int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);
        coreServices.waitForAvailableTrips(driverAccessToken,pickupRequest);
        coreServices.waitForAvailableTrips(driver2AccessToken,pickupRequest);


        if (state.equalsIgnoreCase("Accepted")) {
            coreServices.pickupdetails(pickupRequest, driverAccessToken);
            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            coreServices.pickupdetails(pickupRequest, driver2AccessToken);
            coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);
        } else if (state.equalsIgnoreCase("enroute")) {

            coreServices.pickupdetails(pickupRequest, driverAccessToken);
            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            coreServices.pickupdetails(pickupRequest, driver2AccessToken);
            coreServices.updateStatus(pickupRequest, driver2AccessToken, 21);

            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            coreServices.updateStatus(pickupRequest, driverAccessToken, 23);
            coreServices.updateStatus(pickupRequest, driver2AccessToken, 23);
        } else {
            coreServices.pickupdetails(pickupRequest, driverAccessToken);
            coreServices.updateStatus(pickupRequest, driverAccessToken, 21);
            coreServices.pickupdetails(pickupRequest, driver2AccessToken);
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


    }

    @Given("that solo schedule bungii is in progress")
    public void thatsoloscheduleBungiiIsInProgress(DataTable data) {
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
        coreServices.updateDriverLocation(driverAccessToken,geofence);
        coreServices.updateDriverStatus(driverAccessToken);

        //request Bungii
        coreServices.validatePickupRequest(custAccessToken, geofence);
        String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, geofence);
        String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
        coreServices.recalculateEstimate(pickupRequest, "", custAccessToken);
        int wait = coreServices.customerConfirmationScheduled(pickupRequest, paymentMethod, custAccessToken);


         coreServices.waitForAvailableTrips(driverAccessToken,pickupRequest);

        coreServices.pickupdetails(pickupRequest, driverAccessToken);
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
        System.out.println(pickupRequest);
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
            coreServices.updateDriverLocation(driverAccessToken,geofence);
            coreServices.updateDriverStatus(driverAccessToken);

            //request Bungii
            coreServices.validatePickupRequest(custAccessToken, geofence);
            String pickupRequest = coreServices.getPickupRequest(custAccessToken, 1, geofence);
            String paymentMethod = paymentServices.getPaymentMethodRef(custAccessToken);
            coreServices.recalculateEstimate(pickupRequest, "", custAccessToken);
            coreServices.customerConfirmation(pickupRequest, paymentMethod, custAccessToken, "");


            coreServices.pickupdetails(pickupRequest, driverAccessToken);
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
            System.out.println(pickupRequest);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
