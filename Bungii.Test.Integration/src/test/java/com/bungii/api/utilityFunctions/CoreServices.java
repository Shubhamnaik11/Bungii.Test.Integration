package com.bungii.api.utilityFunctions;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.UrlBuilder;
import com.bungii.ios.stepdefinitions.customer.EstimateSteps;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.junit.Cucumber;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;

public class CoreServices extends DriverBase {
    private static LogUtility logger = new LogUtility(CoreServices.class);
    private static String VALIDATE_PICKUP_REQUEST = "/api/pickup/validatepickuprequest";
    private static String PICKUP_REQUEST = "/api/pickup/request";
    private static String RECALCULATE_ESTIMAT = "/api/pickup/recalculateestimate";
    private static String CUSTOMER_CONFIRMATION = "/api/pickup/customerconfirmation";
    private static String CUSTOMER_VIEW = "/api/pickup/customerview";
    private static String DRIVER_VIEW = "/api/pickup/driverview";
    private static String UPDATE_LOCATION = "/api/driver/updatelocation";
    private static String UPDATE_STATUS = "/api/driver/updatestatus";
    private static String PICKUP_DETAILS = "/api/driver/pickupdetails";
    private static String UPDATE_PICKUP_STATUS = "/api/pickup/updatestatus";
    private static String RATE_AND_TIP = "/api/customer/rateandtipdriver";
    private static String AVAILABLE_PICKUPLIST = "/api/driver/availablepickuplist";
    private static String CUSTOMER_SCHEDULEDLIST = "/api/customer/scheduledpickuplist";
    private static String CUSTOMER_SCHEDULEDPICKUPLIST = "/api/customer/scheduledpickupdetails";
    private static String CUSTOMER_CANCELPICKUPLIST = "/api/customer/cancelpickup";
    GeneralUtility utility = new GeneralUtility();


    public Response validatePickupRequest(String authToken, String geoFence) {

        JSONObject jsonObj = new JSONObject();
        JSONObject dropOffCordinate = new JSONObject();
        JSONObject pickupCordinates = new JSONObject();

        if (geoFence.equalsIgnoreCase("goa")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("goa.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("goa.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("goa.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("goa.pickup.longitude")));
        } else if (geoFence.equalsIgnoreCase("kansas")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("kansas.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("kansas.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("kansas.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("kansas.pickup.longitude")));
        } else if (geoFence.equalsIgnoreCase("boston")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("boston.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("boston.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("boston.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("boston.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("atlanta")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("atlanta.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("baltimore")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("baltimore.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("baltimore.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("baltimore.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("baltimore.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("miami")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("miami.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("miami.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("miami.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("miami.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("nashville")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("nashville.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("nashville.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("nashville.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("nashville.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("denver")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("denver.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("denver.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("denver.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("denver.pickup.longitude")));
        }else if (geoFence.equalsIgnoreCase("washingtondc")) {
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.drop.longitude")));
            pickupCordinates.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.pickup.latitude")));
            pickupCordinates.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties("washingtondc.pickup.longitude")));
        }


        jsonObj.put("DropoffLocation", dropOffCordinate);
        jsonObj.put("PickupLocation", pickupCordinates);


        Header header = new Header("AuthorizationToken", authToken);


        String apiURL = UrlBuilder.createApiUrl("core", VALIDATE_PICKUP_REQUEST);
        Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
        logger.detail(response.then().log().all());
        ApiHelper.genericResponseValidation(response);
        return response;

    }

    public Response availablePickupList(String authToken) {
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("core", AVAILABLE_PICKUPLIST);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.getRequestForDriver(apiURL, header);
        return response;
    }

    public boolean isPickupIsListedInAvailableTrip(String authToken, String expectedPickupRequest) {
        boolean isPickupInAvailableTrip = false;
        JsonPath jsonPathEvaluator = availablePickupList(authToken).jsonPath();
        ArrayList availableArray = jsonPathEvaluator.get("AvailablePickups");
        if (availableArray != null) {
            for (int i = 0; i < availableArray.size(); i++) {
                HashMap pickupDetails = (HashMap) availableArray.get(i);
                String pickupRequest = (String) pickupDetails.get("PickupRef");
                if (pickupRequest.equalsIgnoreCase(expectedPickupRequest)) {
                    isPickupInAvailableTrip = true;
                    break;
                }
            }
        }
        return isPickupInAvailableTrip;
    }

    // wait for 5 mins for pickup to be displayed in available trips
    public void waitForAvailableTrips(String authToken, String expectedPickupRequest) {
        try {

            boolean foundPickup = false;
            for (int i = 0; i < 30; i++) {
                foundPickup = isPickupIsListedInAvailableTrip(authToken, expectedPickupRequest);
                if (foundPickup) {
                    break;
                } else {
                    Thread.sleep(10000);
                }

            }
            if (!foundPickup)
                error("Scheduled trip should be displayed in available trip", "Scheduled trip should be displayed in available trip", false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", false);
        }
    }

    public Response pickupRequest(String authToken, int numberOfDriver, String geoFence) {

        JSONObject jsonObj = new JSONObject();
        JSONObject dropOffAddress = new JSONObject();
        JSONObject dropOffCordinate = new JSONObject();
        JSONObject pickUpAddress = new JSONObject();
        JSONObject pickUpCordinate = new JSONObject();
        if (geoFence.equalsIgnoreCase("nashville")||geoFence.equalsIgnoreCase("goa")||geoFence.equalsIgnoreCase("kansas")||geoFence.equalsIgnoreCase("boston")||geoFence.equalsIgnoreCase("atlanta")||geoFence.equalsIgnoreCase("baltimore") ||geoFence.equalsIgnoreCase("miami")||geoFence.equalsIgnoreCase("denver")||geoFence.equalsIgnoreCase("washingtondc")) {
            dropOffAddress.put("Address1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address1"));
            dropOffAddress.put("Address2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address2"));
            dropOffAddress.put("City", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.city"));
            dropOffAddress.put("Country", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.country"));
            dropOffCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.latitude")));
            dropOffCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.longitude")));
            dropOffAddress.put("Location", dropOffCordinate);
            dropOffAddress.put("State", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.state"));
            dropOffAddress.put("ZipPostalCode", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.zipcode"));

            pickUpAddress.put("Address1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address1"));
            pickUpAddress.put("Address2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address2"));
            pickUpAddress.put("City", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.city"));
            pickUpAddress.put("Country", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.country"));
            pickUpCordinate.put("Latitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.latitude")));
            pickUpCordinate.put("Longitude", Float.valueOf(PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.longitude")));
            pickUpAddress.put("Location", pickUpCordinate);
            pickUpAddress.put("State", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.state"));
            pickUpAddress.put("ZipPostalCode", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.zipcode"));

            jsonObj.put("DropOffAddress", dropOffAddress);
            jsonObj.put("PickupAddress", pickUpAddress);
            jsonObj.put("NoOfDrivers", numberOfDriver);

            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address1") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.address2"));
            cucumberContextManager.setScenarioContext("BUNGII_PICK_LOCATION_LINE_2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.city") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".pickup.state"));
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_1", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address1") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.address2"));
            cucumberContextManager.setScenarioContext("BUNGII_DROP_LOCATION_LINE_2", PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.city") + ", " + PropertyUtility.getDataProperties(geoFence.toLowerCase()+".drop.state"));
            cucumberContextManager.setScenarioContext("BUNGII_NO_DRIVER", numberOfDriver == 1 ? "SOLO" : "DUO");
        } else {
            logger.detail("Specify valid geofence");
        }
        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = UrlBuilder.createApiUrl("core", PICKUP_REQUEST);
        Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
        ApiHelper.genericResponseValidation(response);
        return response;


    }

    public String getPickupRequest(String authToken, int numberOfDriver, String geoFence) {
        Response response = pickupRequest(authToken, numberOfDriver, geoFence);
        JsonPath jsonPathEvaluator = response.jsonPath();
        saveAppliedPromoCode(response);
        return jsonPathEvaluator.get("PickupRequestID");

    }
    public String  saveAppliedPromoCode(Response response){
        String promoCode="",walletRef="";
        JsonPath jsonPathEvaluator =response.jsonPath();
        ArrayList availableArray = jsonPathEvaluator.get("Estimate.DiscountCost");
        //interation to go through all promo code, will be useful in future
        if (availableArray != null) {
            for (int i = 0; i < availableArray.size(); i++) {
                HashMap pickupDetails = (HashMap) availableArray.get(i);
                promoCode = (String) pickupDetails.get("Code");
                walletRef=(String) pickupDetails.get("WalletRef");
                cucumberContextManager.setScenarioContext("ADDED_PROMOCODE", promoCode);
                cucumberContextManager.setScenarioContext("ADDED_PROMOCODE_WALLETREF", walletRef);
                break;
            }
        }
        return walletRef;
    }
    public void recalculateEstimate(String pickupRequestID, String walletReferance, String authToken) {
        try {

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("PickupRequestID", pickupRequestID);
            jsonObj.put("WalletRef", walletReferance);
            jsonObj.put("EstLoadUnloadTimeInMilliseconds", 900000);
            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", RECALCULATE_ESTIMAT);
            Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
            JsonPath jsonPathEvaluator = response.jsonPath();

            ApiHelper.genericResponseValidation(response);
            cucumberContextManager.setScenarioContext("BUNGII_TIME", "NOW");
            String bungiiDistance="";
            if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS"))
                bungiiDistance = new DecimalFormat("#.0").format(jsonPathEvaluator.get("Estimate.DistancePickupToDropOff")) + " miles";
            else
                bungiiDistance = jsonPathEvaluator.get("Estimate.DistancePickupToDropOff") + " miles";

            cucumberContextManager.setScenarioContext("BUNGII_DISTANCE", bungiiDistance);
            cucumberContextManager.setScenarioContext("BUNGII_ESTIMATE", "$" + jsonPathEvaluator.get("Estimate.Cost"));
            cucumberContextManager.setScenarioContext("BUNGII_LOADTIME", "15 mins");
        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
    }
/*
    public void customerConfirmation(String pickRequestID, String paymentMethodID, String authToken) {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("WalletRef", "");
            jsonObj.put("EstLoadUnloadTimeInMilliseconds", 900000);
            jsonObj.put("PickupRequestID", pickRequestID);
            jsonObj.put("Description", "");
            jsonObj.put("PaymentMethodID", paymentMethodID);
            jsonObj.put("IsScheduledPickup", false);

            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_CONFIRMATION);
            Response response = ApiHelper.uploadImage(apiURL, jsonObj, header);
            ApiHelper.genericResponseValidation(response);

        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
    }*/


    public Response customerConfirmation(String pickRequestID, String paymentMethodID, String authToken, String scheduledDateTime) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("WalletRef", (String)cucumberContextManager.getScenarioContext("ADDED_PROMOCODE_WALLETREF"));
        jsonObj.put("EstLoadUnloadTimeInMilliseconds", 900000);
        jsonObj.put("PickupRequestID", pickRequestID);
        jsonObj.put("Description", "");
        jsonObj.put("PaymentMethodID", paymentMethodID);
        if (!scheduledDateTime.equals("")) {
            jsonObj.put("IsScheduledPickup", true);
            jsonObj.put("ScheduledDateTime", scheduledDateTime);
        } else {
            jsonObj.put("IsScheduledPickup", false);

        }

        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_CONFIRMATION);
        Response response = ApiHelper.uploadImage(apiURL, jsonObj, header);
        ApiHelper.genericResponseValidation(response);
        return response;
    }

    public String[] getScheduledBungiiTime() {
        String[] rtnArray = new String[2];
        int bufferTimeToStartTrip = 0;
        Calendar calendar = Calendar.getInstance();
        int mnts = calendar.get(Calendar.MINUTE);

        calendar.set(Calendar.MINUTE, mnts + 30);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 15;
        calendar.add(Calendar.MINUTE, (15 - mod));
        calendar.set(Calendar.SECOND, 0);
        Date nextQuatter = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// create a formatter for date
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = sdf.format(nextQuatter);

        String wait = (((15 - mod) + bufferTimeToStartTrip) * 1000 * 60) + "";
        rtnArray[0] = formattedDate;
        rtnArray[1] = wait;
        return rtnArray;

    }

    public int customerConfirmationScheduled(String pickRequestID, String paymentMethodID, String authToken) {
        //get utc time and time for bungii to start
        String[] nextAvailableBungii = getScheduledBungiiTime();
        Date date = new EstimateSteps().getNextScheduledBungiiTime();
        String strTime = new EstimateSteps().bungiiTimeDisplayInTextArea(date);


        if(PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID")){
            String timeLabel=" "+new com.bungii.ios.utilityfunctions.GeneralUtility().getTimeZoneBasedOnGeofence();
            if(strTime.contains(timeLabel))
                strTime=strTime.replace(timeLabel,"");
        }
            cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);
     //   if (PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID"))
    //        cucumberContextManager.setScenarioContext("BUNGII_TIME", strTime);

        int waitDuraton = Integer.parseInt(nextAvailableBungii[1]);
        customerConfirmation(pickRequestID, paymentMethodID, authToken, nextAvailableBungii[0]);
        return waitDuraton;
    }

    public Response customerView(String pickuprequestid, String authToken) {

        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_VIEW);
        Response response = ApiHelper.givenCustConfig().header(header).param("pickuprequestid", pickuprequestid).when().
                get(apiURL);
        response.then().log().all();
        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response);
        return response;

    }

    public Response driverView(String pickuprequestid, String authToken) {


        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", DRIVER_VIEW);
        Response response = ApiHelper.givenDriverConfig().header(header).param("pickuprequestid", pickuprequestid).when().
                get(apiURL);
        response.then().log().all();
        ApiHelper.genericResponseValidation(response);
        return response;


    }

    public String driverPaymentMethod(String pickuprequestid, String authToken) {
        try {
            Response response = driverView(pickuprequestid, authToken);
            JsonPath jsonPathEvaluator = response.jsonPath();
            return jsonPathEvaluator.get("PickupDetails.PaymentMethodRef");
        } catch (Exception e) {
            return "";
        }
    }

    public Response updateDriverLocation(String authToken, String geofence) {
        Float[] driverLocations = utility.getDriverLocation(geofence);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Latitude", driverLocations[0]);
        jsonObj.put("Longitude", driverLocations[1]);
        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", UPDATE_LOCATION);
        Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
        return response;

    }


    public Response updateDriverStatus(String authToken) {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("DeviceName", "XT1092");
        //make status online
        jsonObj.put("Status", 1);
        jsonObj.put("DeviceToken", "fYUrbPrSXAo:APAS1bFc7QqYIWYyYaIvlcu1Nz30Swc67UDBg75rwUlNbPZDIi2dLdrsgdplYB5GmJqOihXVB64bwVmfEqZAF0DkTOsYX8b8VrjleMHjkSVdQy3ao2nWrCot_HcXx6jYY7pksq3JbKCHP0QYyvmywSA6HRNIhXgiSa" + utility.genearateRandomString());
        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", UPDATE_STATUS);
        Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
        return response;
    }


    public void updateStatus(String pickupID, String authToken, int statusID) {
        try {

            JSONObject jsonObj = new JSONObject();
            JSONObject status = new JSONObject();
            JSONArray statusArray = new JSONArray();
            status.put("PickupId", pickupID);
            status.put("PickupStatusId", pickupID);
            status.put("synced", false);
            status.put("StatusTimestamp", utility.getCurrentUTCTime());
            status.put("Status", statusID);
            statusArray.put(status);
            jsonObj.put("Statuses", statusArray);

            //make status online
            jsonObj.put("PickupRequestID", pickupID);
            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", UPDATE_PICKUP_STATUS);
            Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
            ApiHelper.genericResponseValidation(response);


        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }

    }


    public void pickupdetails(String pickupID, String authToken, String geofence) {
        try {

            JSONObject jsonObj = new JSONObject();
            JSONObject driverCordinate = new JSONObject();
            Float[] driverLocations = utility.getDriverLocation(geofence);

            driverCordinate.put("Latitude", driverLocations[0]);
            driverCordinate.put("Longitude", driverLocations[1]);
            //        driverCordinate.put("Latitude", 15.36773730);
            //       driverCordinate.put("Longitude", 73.936542900);
            //make status online
            jsonObj.put("Location", driverCordinate);
            jsonObj.put("PickupRequestID", pickupID);
            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", PICKUP_DETAILS);
            Response response = ApiHelper.postDetailsForDriver(apiURL, jsonObj, header);
            ApiHelper.genericResponseValidation(response);


        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }

    }

    public void rateAndTip(String pickupRef, String authToken, String driverRef, String tipPaymentMethod, Double
            rating, Double tipAmount) {
        try {

            JSONObject jsonObj = new JSONObject();
            JSONObject driver = new JSONObject();
            JSONArray driverArray = new JSONArray();

            driver.put("DriverRef", driverRef);
            driver.put("TipPaymentMethodRef", tipPaymentMethod);
            driver.put("Rating", rating);
            driver.put("TipAmount", tipAmount);
            driverArray.put(driver);
            jsonObj.put("Driver", driverArray);
            jsonObj.put("PickupRequestID", pickupRef);

            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", RATE_AND_TIP);
            Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response);

        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
    }

    public void rateAndTip(String pickupRef, String authToken, String driverRef, String tipPaymentMethod, Double
            rating, Double tipAmount, String driver2Ref, String tipPayment2Method) {
        try {

            JSONObject jsonObj = new JSONObject();
            JSONObject driver = new JSONObject();
            JSONObject driver2 = new JSONObject();
            JSONArray driverArray = new JSONArray();

            driver.put("DriverRef", driverRef);
            driver.put("TipPaymentMethodRef", tipPaymentMethod);
            driver.put("Rating", rating);
            driver.put("TipAmount", tipAmount);

            driver2.put("DriverRef", driver2Ref);
            driver2.put("TipPaymentMethodRef", tipPayment2Method);
            driver2.put("Rating", rating);
            driver2.put("TipAmount", tipAmount);

            driverArray.put(driver);
            driverArray.put(driver2);
            jsonObj.put("Driver", driverArray);
            jsonObj.put("PickupRequestID", pickupRef);

            Header header = new Header("AuthorizationToken", authToken);

            String apiURL = null;

            apiURL = UrlBuilder.createApiUrl("core", RATE_AND_TIP);
            Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
            JsonPath jsonPathEvaluator = response.jsonPath();
            ApiHelper.genericResponseValidation(response);

        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
    }
    public void driverPollingCalls(String pickupRequest, String geofence, String driverAccessToken){
        if(!driverAccessToken.equalsIgnoreCase("")) {
            driverView(pickupRequest, driverAccessToken);
            updateDriverLocation(driverAccessToken, geofence);
        }else{
            logger.detail("Please specify valid access token");
        }

    }
    public Response getCustomersScheduledPickupList(String authToken) {
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_SCHEDULEDLIST);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.getRequestForDriver(apiURL, header);
        ApiHelper.genericResponseValidation(response);

        return response;

    }
    public void cancelAllScheduledBungiis(String authToken){
        JsonPath jsonPathEvaluator = getCustomersScheduledPickupList(authToken).jsonPath();

        ArrayList ScheduledPickups = jsonPathEvaluator.get("ScheduledPickups");
        if (ScheduledPickups != null) {
            for (int i = 0; i < ScheduledPickups.size(); i++) {
                HashMap pickupDetails = (HashMap) ScheduledPickups.get(i);
                String pickupRequest = (String) pickupDetails.get("PickupRef");
                boolean CanBeCancelled = (boolean) pickupDetails.get("CanBeCancelled");
                getScheduledPickupDetails(pickupRequest,authToken);
                if(CanBeCancelled)
                    cancelBungiiAsCustomer(pickupRequest,authToken);
                else
                    new WebPortal().cancelBungiiAsAdmin(pickupRequest);
            }
        }

    }
    public void cancelOngoingBungii(String custAccessToken){
        Response response= customerView("", custAccessToken);


        JsonPath jsonPathEvaluator = response.jsonPath();
        Object pickupDetails = jsonPathEvaluator.get("PickupDetails");

        //IF customer is has some bungii onn screen
        if(pickupDetails != null ) {
            String pickupRequestID = jsonPathEvaluator.get("PickupDetails.PickupRequestID");
            int pickupStatus = jsonPathEvaluator.get("PickupDetails.PickupStatus");
            int numberOfDriver = jsonPathEvaluator.get("PickupDetails.NoOfDrivers");
            //on demand searching
            if (pickupStatus == 4)
                cancelBungiiAsCustomer(pickupRequestID, custAccessToken);
            else if(pickupStatus == 23 || pickupStatus == 24) {
                //cancel Bungii as driver
                String driverPhoneCode="1";
                String driverPhoneNum=new DbUtility().getDriverAssignedForTrip(pickupRequestID);
                String driverPassword= ((String) cucumberContextManager.getScenarioContext("DRIVER_1_PASSWORD")).equals("")? "Cci12345":(String) cucumberContextManager.getScenarioContext("DRIVER_1_PASSWORD");
                String driverAccessToken = new AuthServices().getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);

                updateStatus(pickupRequestID, driverAccessToken, 66);
            } else if(pickupStatus == 25 || pickupStatus == 26 ||pickupStatus == 27 ||pickupStatus == 28) {
                //complete Bungii as driver
                String driverPhoneCode="1";
                String driverPhoneNum=new DbUtility().getDriverAssignedForTrip(pickupRequestID);
                String driverPassword=((String) cucumberContextManager.getScenarioContext("DRIVER_1_PASSWORD")).equals("")? "Cci12345":(String) cucumberContextManager.getScenarioContext("DRIVER_1_PASSWORD");
                String driverAccessToken = new AuthServices().getDriverToken(driverPhoneCode, driverPhoneNum, driverPassword);
                switch (pickupStatus){
                    case 25:
                        updateStatus(pickupRequestID, driverAccessToken, 26);
                    case 26:
                        updateStatus(pickupRequestID, driverAccessToken, 27);
                    case 27:
                        updateStatus(pickupRequestID, driverAccessToken, 28);
                    case 28:
                        try {Thread.sleep(35000); } catch (InterruptedException e) {e.printStackTrace();}
                }
                String paymentMethod = new PaymentServices().getPaymentMethodRef(custAccessToken);

                JsonPath jsonPathEvaluator1 = response.jsonPath();
                if(numberOfDriver==1) {
                    String driverRef = jsonPathEvaluator1.get("PickupDetails.TripDetails[0].Driver.DriverRef");
                    rateAndTip(pickupRequestID, custAccessToken, driverRef, paymentMethod, 5.0, 5.0);
                }else{
                    String driver1Ref = jsonPathEvaluator1.get("PickupDetails.TripDetails[0].Driver.DriverRef");
                    String driver2Ref = jsonPathEvaluator1.get("PickupDetails.TripDetails[1].Driver.DriverRef");
                    rateAndTip(pickupRequestID, custAccessToken, driver1Ref, paymentMethod, 5.0, 5.0, driver2Ref, paymentMethod);

                }
            }

        }
    }
    public Response getScheduledPickupDetails(String pickuprequestid, String authToken) {

        Header header = new Header("AuthorizationToken", authToken);

        String apiURL = null;

        apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_SCHEDULEDPICKUPLIST);
        Response response = ApiHelper.givenCustConfig().header(header).param("pickuprequestid", pickuprequestid).when().
                get(apiURL);
        response.then().log().all();
        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response);
        return response;
    }

    public void cancelBungiiAsCustomer(String pickupRef, String authToken) {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("Status", 64);
            jsonObj.put("StatusTimestamp", utility.getCurrentUTCTime());
            jsonObj.put("PickupRequestID", pickupRef);
            Header header = new Header("AuthorizationToken", authToken);
            String apiURL = null;
            apiURL = UrlBuilder.createApiUrl("core", CUSTOMER_CANCELPICKUPLIST);
            Response response = ApiHelper.postDetailsForCustomer(apiURL, jsonObj, header);
            ApiHelper.genericResponseValidation(response);

        } catch (Exception e) {
            System.out.println("Not able to Log in" + e.getMessage());
        }
    }
}
