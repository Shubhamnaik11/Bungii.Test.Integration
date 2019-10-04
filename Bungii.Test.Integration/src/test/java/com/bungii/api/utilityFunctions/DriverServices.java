package com.bungii.api.utilityFunctions;

import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.UrlBuilder;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DriverServices {
    private static String DRIVER_PROFILE = "/api/driver/profile";


    public Response driverProfile(String authToken) {
            String loginURL = null;
            loginURL = UrlBuilder.createApiUrl("driver", DRIVER_PROFILE);
            Header header = new Header("AuthorizationToken", authToken);
            Response response = ApiHelper.getRequestForDriver(loginURL, header);
            return response;
    }
    public String getDriverRef(String authToken){
        Response response=driverProfile( authToken);
        ApiHelper.genericResponseValidation(response);
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("DriverProfile.DriverRef");
    }

}
