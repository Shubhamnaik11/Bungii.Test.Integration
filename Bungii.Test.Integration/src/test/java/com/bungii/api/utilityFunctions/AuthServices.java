package com.bungii.api.utilityFunctions;

import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.UrlBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONObject;

import java.util.Map;

public class AuthServices extends DriverBase {
    private static String CUST_LOGIN_ENDPOINT = "/api/customer/login";
    private static String DRIVER_LOGIN_ENDPOINT = "/api/driver/login";
    private static String BUSINESSPARTNER_LOGIN = "/api/businesspartner/login";
    private static LogUtility logger = new LogUtility(AuthServices.class);

    /**
     * Customer login
     * @param custPhoneCode Phone country code
     * @param custPhoneNum Phone number
     * @param custPassword Password
     * @return access token
     */
    public Response customerLogin(String custPhoneCode, String custPhoneNum, String custPassword) {
       // logger.detail("API REQUEST : Customer Login of " + custPhoneNum);
        String RequestText = "API REQUEST : Login Customer :  " + custPhoneNum;
        String token = "";
            Map<String, String> data = new HashedMap();
            data.put("PhoneCountryCode", custPhoneCode);
            data.put("Password", custPassword);
            data.put("PhoneNo", custPhoneNum);
            String loginURL = UrlBuilder.createApiUrl("auth", CUST_LOGIN_ENDPOINT);
            Response response = ApiHelper.postDetailsForCustomer(loginURL, data);
            ApiHelper.genericResponseValidation(response,RequestText);
        return response;
    }
    //get customer access token
    public String getCustomerToken(String custPhoneCode, String custPhoneNum, String custPassword){
       // String RequestText = "API REQUEST : Get Customer Token of " + custPhoneNum;
        Response response=customerLogin( custPhoneCode, custPhoneNum, custPassword);
        //ApiHelper.genericResponseValidation(response,RequestText);

        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("AccessToken");
    }
    //Driver login
    public Response driverLogin(String driverPhoneCode, String driverPhoneNum, String driverPassword) {
        String RequestText = "API REQUEST : Login Driver : " + driverPhoneNum;
        Map<String, String> data = new HashedMap();
            data.put("PhoneCountryCode", driverPhoneCode);
            data.put("Password", driverPassword);
            data.put("PhoneNo", driverPhoneNum);
            String loginURL = UrlBuilder.createApiUrl("auth", DRIVER_LOGIN_ENDPOINT);
            Response response= ApiHelper.postDetailsForDriver(loginURL, data);
            ApiHelper.genericResponseValidation(response, RequestText);
        return response;
    }
    //Get driver access token
    public String getDriverToken(String driverPhoneCode, String driverPhoneNum, String driverPassword){
       // String RequestText = "API REQUEST : Get Driver Token of " + driverPhoneNum;
        Response response=driverLogin( driverPhoneCode, driverPhoneNum, driverPassword);
       // ApiHelper.genericResponseValidation(response, RequestText);
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("AccessToken");
    }

    public String partnerLogin(String Partner_Portal){
        String RequestText ="API REQUEST : Partner Login(Post) |  : "+ Partner_Portal;
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("auth",BUSINESSPARTNER_LOGIN);
        String Partner_Location_Reference = "";

        if(Partner_Portal.equalsIgnoreCase("MRFM")){
            Partner_Location_Reference = PropertyUtility.getDataProperties("partner.location.reference.MRFM");
            cucumberContextManager.setScenarioContext("PartnerLocationReference",Partner_Location_Reference);
            logger.detail("PartnerLocationReference="+Partner_Location_Reference);
        }
        else{
            logger.detail("Please provide proper partner portal alias.");
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("PartnerLocationReference", Partner_Location_Reference);
        String Pwd = PropertyUtility.getDataProperties("PartnerPassword");
        jsonObj.put("Password", Pwd);
        //Header header = new Header("AuthorizationToken",);

        Response response = ApiHelper.givenPartnerConfig().body(jsonObj.toString()).when().post(apiURL);
        //response.then().log().all();

        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response, RequestText);
        return jsonPathEvaluator.get("AccessToken");
        //return response;

    }
}
