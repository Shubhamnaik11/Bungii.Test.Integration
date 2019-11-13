package com.bungii.common.utilities;


public class UrlBuilder {


    public static String createApiUrl(String services, String endpoint) {
        try {

            String protocol = PropertyUtility.getDataProperties("PROTOCOL");
            String baseUrl = "", basePort = "";
            switch (services.toLowerCase()) {
                case "core":
                    baseUrl = PropertyUtility.getDataProperties("CORE_URL");
                    basePort = PropertyUtility.getDataProperties("CORE_PORT");
                    break;
                case "auth":
                    baseUrl = PropertyUtility.getDataProperties("AUTH_URL");
                    basePort = PropertyUtility.getDataProperties("AUTH_PORT");
                    break;
                case "infra":
                    baseUrl = PropertyUtility.getDataProperties("INFRA_URL");
                    basePort = PropertyUtility.getDataProperties("INFRA_PORT");
                    break;
                case "driver":
                    baseUrl = PropertyUtility.getDataProperties("DRIVER_URL");
                    basePort = PropertyUtility.getDataProperties("DRIVER_PORT");
                    break;

                case "customer":
                    baseUrl = PropertyUtility.getDataProperties("CUSTOMER_URL");
                    basePort = PropertyUtility.getDataProperties("CUSTOMER_PORT");
                    break;
                case "payment":
                    baseUrl = PropertyUtility.getDataProperties("PAYMENT_URL");
                    basePort = PropertyUtility.getDataProperties("PAYMENT_PORT");
                    break;
                case "web core":
                    baseUrl = PropertyUtility.getDataProperties("WEBCORE_URL");
                    basePort = PropertyUtility.getDataProperties("WEBCORE_PORT");
                    break;
            }
            String urlString = protocol + "://" + baseUrl + ":" + basePort+endpoint;
            return urlString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
