package com.bungii.api.utilityFunctions;

import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.UrlBuilder;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class WebPortal {
    private static Cookies adminCookies;
    private static String CUSTOMER_CANCELPICKUP = "/BungiiReports/CustomerCancelPickup";


    public Response AdminLogin() {
        Map<String, String> data = new HashedMap();
        data.put("Password", PropertyUtility.getDataProperties("admin.password"));
        data.put("PhoneNo", PropertyUtility.getDataProperties("admin.user"));
        String loginURL = new GeneralUtility().GetAdminUrl();
        Response response = given().log().all()
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

        response.then().log().all();
        adminCookies = response.then().extract().response().getDetailedCookies();
        return response;
    }

    public void cancelScheduledTrip(String pickupRequestId) {
        Map<String, String> data = new HashedMap();
        data.put("PickupRequestID", pickupRequestId);
        data.put("CancellationFee", "6");
        data.put("CancelComments", "test ");
        String cancelBungii = UrlBuilder.createApiUrl("web core", CUSTOMER_CANCELPICKUP);
        Response response = given().cookies(adminCookies)
                .formParams("PickupRequestID", pickupRequestId, "CancellationFee", "6", "CancelComments", "test")
                .log().all().
                        when().
                        post(cancelBungii);
        response.then().log().all();

    }

    public void loginToPortalAndCancelBungii(String pickupRequestId) {
        AdminLogin();
        cancelScheduledTrip(pickupRequestId);
    }
}
