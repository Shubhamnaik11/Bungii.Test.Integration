package com.bungii.api.utilityFunctions;

import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.UrlBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

public class AuthServices {
    private static String CUST_LOGIN_ENDPOINT = "/api/customer/login";
    private static String DRIVER_LOGIN_ENDPOINT = "/api/driver/login";
    private static LogUtility logger = new LogUtility(AuthServices.class);

    /**
     * Customer login
     * @param custPhoneCode Phone country code
     * @param custPhoneNum Phone number
     * @param custPassword Password
     * @return access token
     */
    public Response customerLogin(String custPhoneCode, String custPhoneNum, String custPassword) {
        logger.detail("API REQUEST : Customer Login of " + custPhoneNum);
        String token = "";
            Map<String, String> data = new HashedMap();
            data.put("PhoneCountryCode", custPhoneCode);
            data.put("Password", custPassword);
            data.put("PhoneNo", custPhoneNum);
            String loginURL = UrlBuilder.createApiUrl("auth", CUST_LOGIN_ENDPOINT);
            Response response = ApiHelper.postDetailsForCustomer(loginURL, data);
            return response;
    }
    //get customer access token
    public String getCustomerToken(String custPhoneCode, String custPhoneNum, String custPassword){
        logger.detail("API REQUEST : Get Customer Token of " + custPhoneNum);
        Response response=customerLogin( custPhoneCode, custPhoneNum, custPassword);
        ApiHelper.genericResponseValidation(response);

        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("AccessToken");
    }
    //Driver login
    public Response driverLogin(String driverPhoneCode, String driverPhoneNum, String driverPassword) {
        logger.detail("API REQUEST : Get Driver Login of " + driverPhoneNum);
        Map<String, String> data = new HashedMap();
            data.put("PhoneCountryCode", driverPhoneCode);
            data.put("Password", driverPassword);
            data.put("PhoneNo", driverPhoneNum);
            String loginURL = UrlBuilder.createApiUrl("auth", DRIVER_LOGIN_ENDPOINT);
            Response response= ApiHelper.postDetailsForDriver(loginURL, data);
            return response;
    }
    //Get driver access token
    public String getDriverToken(String driverPhoneCode, String driverPhoneNum, String driverPassword){
        logger.detail("API REQUEST : Get Driver Token of " + driverPhoneNum);
        Response response=driverLogin( driverPhoneCode, driverPhoneNum, driverPassword);
        ApiHelper.genericResponseValidation(response);
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("AccessToken");
    }
}
