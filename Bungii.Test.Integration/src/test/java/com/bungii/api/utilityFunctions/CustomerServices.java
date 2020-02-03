package com.bungii.api.utilityFunctions;

import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.UrlBuilder;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CustomerServices {
    private static String customer_PROFILE = "/api/customer/profile";
    private static LogUtility logger = new LogUtility(CustomerServices.class);


    public Response customerProfile(String authToken) {
        logger.detail("API REQUEST : Get Customer Profile by authToken " + authToken);
        String loginURL = null;
        loginURL = UrlBuilder.createApiUrl("customer", customer_PROFILE);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.getRequestForDriver(loginURL, header);
        return response;
    }

    public String getCustomerRef(String authToken) {
        logger.detail("API REQUEST : Get Customer Reference by authToken " + authToken);
        Response response = customerProfile(authToken);
        ApiHelper.genericResponseValidation(response);
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("CustomerProfile.CustomerRef");
    }

}
