package com.bungii.api.utilityFunctions;

import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.UrlBuilder;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PaymentServices {

    private static String GET_PAYMENT_METHOD = "/api/payment/getpaymentmethods";
    private static LogUtility logger = new LogUtility(AuthServices.class);


    public Response getGetPaymentMethod(String authToken) {
        String apiURL = null;
        String RequestText ="API REQUEST : Get Payment Method by Authtoken : " + authToken;
        apiURL = UrlBuilder.createApiUrl("payment", GET_PAYMENT_METHOD);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.getRequestForCustomer(apiURL, header);
        ApiHelper.genericResponseValidation(response,RequestText);
        return response;
    }

    public String getPaymentMethodRef(String authToken) {
        String RequestText ="API REQUEST : Get Payment Method Reference by Authtoken : " + authToken;

        Response response = getGetPaymentMethod(authToken);
        ApiHelper.genericResponseValidation(response,RequestText);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String paymentRef = jsonPathEvaluator.get("PaymentMethods[0].PaymentMethodRef");
        return paymentRef;
    }
}
