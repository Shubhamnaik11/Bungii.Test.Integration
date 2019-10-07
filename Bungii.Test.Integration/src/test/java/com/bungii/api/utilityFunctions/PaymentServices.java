package com.bungii.api.utilityFunctions;

import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.UrlBuilder;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PaymentServices {

    private static String GET_PAYMENT_METHOD = "/api/payment/getpaymentmethods";


    public Response getGetPaymentMethod(String authToken) {
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("payment", GET_PAYMENT_METHOD);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.getRequestForCustomer(apiURL, header);
        return response;
    }

    public String getPaymentMethodRef(String authToken) {
        Response response = getGetPaymentMethod(authToken);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String paymentRef = jsonPathEvaluator.get("PaymentMethods[0].PaymentMethodRef");
        return paymentRef;
    }
}
