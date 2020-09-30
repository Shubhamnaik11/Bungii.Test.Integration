package com.bungii.common.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;

import java.io.File;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
import static io.restassured.RestAssured.given;


public class ApiHelper {
    private static Gson gson;
    private static LogUtility logger = new LogUtility(ApiHelper.class);


    /**
     * Given config for customer app
     *
     * @return
     */
    public static RequestSpecification givenCustConfig() {
        return given()//.log().body()
                .header("MobileAppVersion", PropertyUtility.getDataProperties("CUST_MOBILE_APP_VERSION"))
                .header("AppVersion", PropertyUtility.getDataProperties("CUST_APP_VERSION"))
                .header("User-Agent", "okhttp/3.4.1")
                .header("Content-Type", "application/json")
                .header("Accept-Encoding", "gzip")
                .header("DeviceID", UUID.randomUUID())//PropertyUtility.getDataProperties("DEVICE_ID"))
                .auth().preemptive().basic(PropertyUtility.getDataProperties("auth.username"), PropertyUtility.getDataProperties("auth.password"));
    }

    /**
     * Given config for driver app
     *
     * @return
     */
    public static RequestSpecification givenDriverConfig() {
        return given()//.log().body()
                .header("MobileAppVersion", PropertyUtility.getDataProperties("DRIVER_MOBILE_APP_VERSION"))
                .header("AppVersion", PropertyUtility.getDataProperties("DRIVER_APP_VERSION"))
                .header("User-Agent", "okhttp/3.4.1")
                .header("Content-Type", "application/json")
                .header("Accept-Encoding", "gzip")
                .header("DeviceID",  UUID.randomUUID()) //PropertyUtility.getDataProperties("DEVICE_ID"))
                .auth().preemptive().basic(PropertyUtility.getDataProperties("auth.username"), PropertyUtility.getDataProperties("auth.password"));
    }


    /**
     * Post
     *
     * @param Path
     * @param data
     * @return
     */
    public static Response postDetailsForCustomer(String Path, Map<String, String> data) {
        Response response = givenCustConfig().
                body(gson().toJson(data, Map.class)).
                when().
                post(Path);
        return response;
    }


    /**
     * Post
     *
     * @param Path
     * @param data
     * @return
     */
    public static Response postDetailsForDriver(String Path, Map<String, String> data) {
        Response response = givenDriverConfig().
                body(gson().toJson(data, Map.class)).
                when().
                post(Path);
        return response;
    }

    /**
     * Post
     *
     * @param Path
     * @param data
     * @return
     */
    public static Response postRequestForCustomer(String Path, Map<String, String> data) {
        Response response = givenCustConfig().
                body(gson().toJson(data, Map.class)).
                when().
                post(Path);
        return response;
    }

    /**
     * Post
     *
     * @param Path
     * @param data
     * @return
     */
    public static Response postRequestForDriver(String Path, Map<String, String> data) {
        Response response = givenDriverConfig().
                body(gson().toJson(data, Map.class)).
                when().
                post(Path);
        return response;
    }

    /**
     * @param path
     * @param extraHeaders
     * @return
     */
    public static Response getRequestForCustomer(String path, Header... extraHeaders) {
        List<Header> headerList = new LinkedList<Header>();
        if (extraHeaders.length > 0) {
            for (int i = 0; i < extraHeaders.length; i++) {
                headerList.add(extraHeaders[i]);
            }
            Headers header = new Headers(headerList);
            Response response = givenCustConfig().headers(header).
                    when().
                    get(path);
           // response.then().log().body();
            return response;
        } else {
            Response response = givenCustConfig().
                    when().
                    get(path);

           // response.then().log().body();
            return response;
        }
    }

    /**
     * @param path
     * @param extraHeaders
     * @return
     */
    public static Response getRequestForDriver(String path, Header... extraHeaders) {
        List<Header> headerList = new LinkedList<Header>();
        if (extraHeaders.length > 0) {
            for (int i = 0; i < extraHeaders.length; i++) {
                headerList.add(extraHeaders[i]);
            }
            Headers header = new Headers(headerList);
            Response response = givenDriverConfig().headers(header).
                    when().
                    get(path);
          //  response.then().log().body();
            return response;
        } else {
            Response response = givenDriverConfig().
                    when().
                    get(path);

          //  response.then().log().body();
            return response;
        }
    }

    public static Response postDetailsForCustomer(String Path, JSONObject data, Header... extraHeaders) {
        if (extraHeaders.length > 0) {
            List<Header> headerList = new LinkedList<Header>();

            for (int i = 0; i < extraHeaders.length; i++) {
                headerList.add(extraHeaders[i]);
            }

            Headers header = new Headers(headerList);
            Response response = givenCustConfig().headers(header).
                    body(data.toString()).
                    when().
                    post(Path);
         //   response.then().log().body();
            return response;

        } else {
            Response response = givenCustConfig().
                    body(data.toString()).
                    when().
                    post(Path);
          //  response.then().log().body();

            return response;
        }
    }

    public static Response postDetailsForDriver(String Path, JSONObject data, Header... extraHeaders) {
        if (extraHeaders.length > 0) {
            List<Header> headerList = new LinkedList<Header>();

            for (int i = 0; i < extraHeaders.length; i++) {
                headerList.add(extraHeaders[i]);
            }

            Headers header = new Headers(headerList);
            Response response = givenDriverConfig().headers(header).
                    body(data.toString()).
                    when().
                    post(Path);
          //  response.then().log().body();
            return response;

        } else {
            Response response = givenDriverConfig().
                    body(data.toString()).
                    when().
                    post(Path);
        //    response.then().log().body();

            return response;
        }
    }

    public static Response uploadImageOndemand(String Path, JSONObject data, Header authToken) {
        String pickupImage = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("PICKUP_ITEM_IMAGE"));

        Response response = givenCustConfig().header(authToken).param("WalletRef", data.get("WalletRef")).param("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds")).param("PickupRequestID", data.get("PickupRequestID")).param("Description", data.get("Description")).param("PaymentMethodID", data.get("PaymentMethodID")).param("IsScheduledPickup", data.get("IsScheduledPickup"))
                .contentType("multipart/form-data")

                .multiPart("ItemImage1", new File(pickupImage))
                .multiPart("Content-Type", "image/jpeg")
                .multiPart("filename", "ItemImage1")
                .multiPart("WalletRef", data.get("WalletRef"))
                .multiPart("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds"))
                .multiPart("PickupRequestID", data.get("PickupRequestID"))
                .multiPart("Description", data.get("Description"))
                .multiPart("PaymentMethodID", data.get("PaymentMethodID"))
                .multiPart("IsScheduledPickup", data.get("IsScheduledPickup"))
                .when().post(Path);
        return response;
    }

    //Specify all one time default Gson config
    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gson(gsonBuilder);
        return gson;
    }

    public static Response uploadImage(String Path, JSONObject data, Header authToken) {
        Response response;
        String pickupImage = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("PICKUP_ITEM_IMAGE"));

        if ((Boolean) data.get("IsScheduledPickup")) {
            response = givenCustConfig().header(authToken).param("WalletRef", data.get("WalletRef")).param("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds")).param("PickupRequestID", data.get("PickupRequestID")).param("Description", data.get("Description")).param("PaymentMethodID", data.get("PaymentMethodID")).param("IsScheduledPickup", data.get("IsScheduledPickup"))
                    .contentType("multipart/form-data")
                    //.multiPart(new File("C:\\Users\\vishal.bagi\\Pictures\\ItemImage2.jpg"))
                    .multiPart("ItemImage1", new File(pickupImage))
                    .multiPart("Content-Type", "image/jpeg")
                    .multiPart("filename", "ItemImage1")
                    .multiPart("WalletRef", data.get("WalletRef"))
                    .multiPart("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds"))
                    .multiPart("PickupRequestID", data.get("PickupRequestID"))
                    .multiPart("Description", data.get("Description"))
                    .multiPart("PaymentMethodID", data.get("PaymentMethodID"))
                    .multiPart("IsScheduledPickup", data.get("IsScheduledPickup"))
                    .multiPart("ScheduledDateTime", data.get("ScheduledDateTime"))
                    .when().post(Path);
        } else {
            response = givenCustConfig().header(authToken).param("WalletRef", data.get("WalletRef")).param("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds")).param("PickupRequestID", data.get("PickupRequestID")).param("Description", data.get("Description")).param("PaymentMethodID", data.get("PaymentMethodID")).param("IsScheduledPickup", data.get("IsScheduledPickup"))
                    .contentType("multipart/form-data")
                    //.multiPart(new File("C:\\Users\\vishal.bagi\\Pictures\\ItemImage2.jpg"))
                    .multiPart("ItemImage1", new File(pickupImage))
                    .multiPart("Content-Type", "image/jpeg")
                    .multiPart("filename", "ItemImage1")
                    .multiPart("WalletRef", data.get("WalletRef"))
                    .multiPart("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds"))
                    .multiPart("PickupRequestID", data.get("PickupRequestID"))
                    .multiPart("Description", data.get("Description"))
                    .multiPart("PaymentMethodID", data.get("PaymentMethodID"))
                    .multiPart("IsScheduledPickup", data.get("IsScheduledPickup"))
                    .when().post(Path);
        }
        return response;
    }

    //Custom Gson config to override Default Gson  configuration
    public static Gson gson(GsonBuilder gsonBuilder) {
        gson = gsonBuilder.create();
        return gson;
    }

    public static void genericResponseValidation(Response response, String RequestText) {
        JsonPath jsonPathEvaluator;
        try {
            jsonPathEvaluator = response.jsonPath();
            HashMap error = jsonPathEvaluator.get("Error");

        if (error == null) {
          //  logger.detail(response.then().log().body()); //temporary checkin
            logger.detail(RequestText + " | API call response - Success ");
        }
        else
        {   logger.detail(RequestText + " | API call response - Failure ");
            logger.detail(response.then().log().body());
        }
            response.then().statusCode(200);
        }
        catch (AssertionError ex)
        {
            jsonPathEvaluator = response.jsonPath();
            HashMap error = jsonPathEvaluator.get("Error");
            if (error.size()!=0) {
                logger.error("API Call failed : ", " Failed due to : " + error.get("Message").toString());
                error("Step should be successful", "Failed due to :  " + error.get("Message").toString(),
                        true);
            }
            else
            {
                logger.error("API Call failed : ", " API Response is empty. It seems to be queue error. Please reset the queue and try again.");
                error("Step should be successful", "API Response is empty. It see seems to be queue error. Please reset the queue and try again.",
                        true);
            }
        }

    }

}