package com.bungii.api.utilityFunctions;

import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.UrlBuilder;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import io.restassured.http.Cookies;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

import static com.bungii.common.manager.ResultManager.fail;
import static com.bungii.common.manager.ResultManager.log;
import static io.restassured.RestAssured.given;


public class WebPortal {
    private static Cookies adminCookies;
    private static String CUSTOMER_CANCELPICKUP = "/BungiiReports/CustomerCancelPickup";
    private static String CAN_EDIT_PICKUP = "/BungiiReports/CanEditPickup";
    private static String CALCULATE_COST = "/BungiiReports/CalculateCost";
    private static String MANUALLY_END = "/BungiiReports/ManuallyEndPickup";
    private static LogUtility logger = new LogUtility(AuthServices.class);


    public Response AdminLogin() {
        logger.detail("API REQUEST : Admin Login " + PropertyUtility.getDataProperties("admin.user"));
        String loginURL = new GeneralUtility().GetAdminUrl();
        Response response = given().log().body()
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:61.0) Gecko/20100101 Firefox/61.0")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .formParams("PhoneNo", PropertyUtility.getDataProperties("admin.user"), "Password", PropertyUtility.getDataProperties("admin.password"))
                .when().
                        post(loginURL);

        //response.then().log().all();
        adminCookies = response.then().extract().response().getDetailedCookies();
      //  ApiHelper.genericResponseValidation(response);
        return response;
    }

    public void cancelScheduledBungii(String pickupRequestId) {
        logger.detail("API REQUEST : Cancel Scheduled Bungii " + pickupRequestId);
        String cancelBungii = UrlBuilder.createApiUrl("web core", CUSTOMER_CANCELPICKUP);
        Response response = given().cookies(adminCookies)
                .formParams("PickupRequestID", pickupRequestId, "CancellationFee", "6", "CancelComments", "test")
                /*.log().body()*/.
                        when().
                        post(cancelBungii);
       // response.then().log().body();
      //  ApiHelper.genericResponseValidation(response);
    }

    public void cancelBungiiAsAdmin(String pickupRequestId) {
        AdminLogin();
        cancelScheduledBungii(pickupRequestId);
    }

    public void asAdminManuallyEndBungii(String pickupRequestId) {
        AdminLogin();
        try {Thread.sleep(30000);} catch (InterruptedException e) {
            e.printStackTrace();
        }
        com.bungii.api.utilityFunctions.GeneralUtility utility = new com.bungii.api.utilityFunctions.GeneralUtility();
        String bungiiEndTime=utility.getBungiiEndTimeForManuallyEnd();
        String bungiiTimeZoneLabel=utility.getBungiiTimeZoneLanel();
        canEditPickup(pickupRequestId);
        calculateManuallyEndCost(pickupRequestId,bungiiEndTime,bungiiTimeZoneLabel);
        calculateManuallyBungii(pickupRequestId,bungiiEndTime,bungiiTimeZoneLabel);
    }


    public void canEditPickup(String pickupRequestId) {
        logger.detail("API REQUEST : Edit Pickup " + pickupRequestId);
        String cancelBungii = UrlBuilder.createApiUrl("web core", CAN_EDIT_PICKUP);
        Response response = given().cookies(adminCookies)
                .param("pickupRequestID", pickupRequestId)
                /*.log().body()*/.
                        when().
                        get(cancelBungii);
       // response.then().log().body();
      //  ApiHelper.genericResponseValidation(response);
        JsonPath jsonPathEvaluator1 = response.jsonPath();

        boolean isSuccess = jsonPathEvaluator1.get("Success");
        if(!isSuccess)
            fail("I should able to end bungii"+pickupRequestId, "I was not able to edit bungii"+pickupRequestId);


    }

    public void calculateManuallyEndCost(String pickupRequestId,String bungiiEndTime,String bungiiTimeZoneLabel) {
        logger.detail("API REQUEST : Calculate Manually End Cost : " + pickupRequestId);
        String cancelBungii = UrlBuilder.createApiUrl("web core", CALCULATE_COST);
        Response response = given().cookies(adminCookies)
                .formParams("PickupRequestID", pickupRequestId, "PickupEndTime", bungiiEndTime, "PickupTimeZone", bungiiTimeZoneLabel)
                /*.log().body()*/.
                        when().
                        post(cancelBungii);
     //   response.then().log().body();
     //   ApiHelper.genericResponseValidation(response);
        JsonPath jsonPathEvaluator1 = response.jsonPath();
        boolean isSuccess = jsonPathEvaluator1.get("Success");
        if(!isSuccess)
            fail("I should able to end bungii"+pickupRequestId, "I was not able to edit bungii"+pickupRequestId);
    }

    public void calculateManuallyBungii(String pickupRequestId,String bungiiEndTime,String bungiiTimeZoneLabel) {
        logger.detail("API REQUEST : Calculate Manually Bungii : " + pickupRequestId);

        String cancelBungii = UrlBuilder.createApiUrl("web core", MANUALLY_END);
        Response response = given().cookies(adminCookies)
                .formParams("PickupRequestID", pickupRequestId, "PickupEndTime", bungiiEndTime, "PickupTimeZone", bungiiTimeZoneLabel)
                /*.log().body()*/.
                        when().
                        post(cancelBungii);
       // response.then().log().body();
      //  ApiHelper.genericResponseValidation(response);
        JsonPath jsonPathEvaluator1 = response.jsonPath();
        boolean isSuccess = jsonPathEvaluator1.get("Success");
        if(!isSuccess)
            fail("I should able to end bungii"+pickupRequestId, "I was not able to edit bungii"+pickupRequestId);

    }
}
