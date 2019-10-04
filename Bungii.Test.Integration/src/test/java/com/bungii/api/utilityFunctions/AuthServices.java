package com.bungii.api.utilityFunctions;

import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.UrlBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

public class AuthServices {
    private static String CUST_LOGIN_ENDPOINT = "/api/customer/login";
    private static String DRIVER_LOGIN_ENDPOINT = "/api/driver/login";

    /**
     * Customer login
     * @param custPhoneCode Phone country code
     * @param custPhoneNum Phone number
     * @param custPassword Password
     * @return access token
     */
    public Response customerLogin(String custPhoneCode, String custPhoneNum, String custPassword) {
        String token = "";
            Map<String, String> data = new HashedMap();
            data.put("PhoneCountryCode", custPhoneCode);
            data.put("Password", custPassword);
            data.put("PhoneNo", custPhoneNum);
            String loginURL = UrlBuilder.createApiUrl("auth", CUST_LOGIN_ENDPOINT);
            Response response = ApiHelper.postDetailsForCustomer(loginURL, data);
            return response;


    }
    public String getCustomerToken(String custPhoneCode, String custPhoneNum, String custPassword){
        Response response=customerLogin( custPhoneCode, custPhoneNum, custPassword);
        ApiHelper.genericResponseValidation(response);

        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("AccessToken");
    }

    public Response driverLogin(String driverPhoneCode, String driverPhoneNum, String driverPassword) {
            Map<String, String> data = new HashedMap();
            data.put("PhoneCountryCode", driverPhoneCode);
            data.put("Password", driverPassword);
            data.put("PhoneNo", driverPhoneNum);
            String loginURL = UrlBuilder.createApiUrl("auth", DRIVER_LOGIN_ENDPOINT);
            Response response= ApiHelper.postDetailsForDriver(loginURL, data);
            return response;
    }

    public String getDriverToken(String driverPhoneCode, String driverPhoneNum, String driverPassword){
        Response response=driverLogin( driverPhoneCode, driverPhoneNum, driverPassword);
        ApiHelper.genericResponseValidation(response);
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("AccessToken");
    }
}
