package com.bungii.api.utilityFunctions;

import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.UrlBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GoogleMaps {
    //Move this hard coded value in the Data properties(during AWS environment)
    private static String DISTANCE_MATRIX_API = "https://maps.googleapis.com/maps/api/distancematrix/json";
    //private static String DISTANCE_MATRIX_API = PropertyUtility.getDataProperties("GOOGLE_DISTANCE_BASE_URL");
    private static LogUtility logger = new LogUtility(AuthServices.class);

    public int[] getDurationInTraffic(String[] driverCoordinate, String[] dropCoordinate, String[] stackPickupCoordinate) {
        Date date= new Date();
        long epoch = date.getTime();
        String strOrigins = driverCoordinate[0]+","+driverCoordinate[1]+"|"+dropCoordinate[0]+","+dropCoordinate[1];
        String strDestinations = dropCoordinate[0]+","+dropCoordinate[1]+"|"+stackPickupCoordinate[0]+","+stackPickupCoordinate[1];
        Map<String, String> data = new HashedMap();
        String RequestText="API REQUEST : Get duration in Traffic";

        Response response =given()//.log().all()
                .header("User-Agent", "okhttp/3.4.1")
                .header("Content-Type", "x-www-form-urlencoded")
                .header("Accept-Encoding", "gzip")
                .urlEncodingEnabled(true)
                .contentType("x-www-form-urlencoded")
                .param("units", "imperial")
                .param("origins", strOrigins)
                .param("destinations", strDestinations)

                .param("key", "AIzaSyD5z-ZpO46vNQIXTGeNYJSIy9vvlR-ViQI")
                .param("departure_time", String.valueOf(epoch))
                .param("traffic_model","best_guess")
                .param("mode","driving")
                .when()
                .get(DISTANCE_MATRIX_API);

        //response.then().log().all();
        ApiHelper.genericResponseValidation(response,RequestText);
        return  getStackDuration(response);
    }

    public int[] getStackDuration(Response response){
        int [] timingInformation= new int[2];
        JsonPath jsonPathEvaluator = response.jsonPath();
        ArrayList jsonArray = jsonPathEvaluator.getJsonObject("rows");

        HashMap hashMapjson = (HashMap) jsonArray.get(0);
        JSONObject json=new JSONObject(hashMapjson);
        JSONArray elements = json.getJSONArray("elements");
        JSONObject elementZero = elements.getJSONObject(0);
        JSONObject distanceInTraffic =elementZero.getJSONObject("duration_in_traffic");
        String timeToDropUp =distanceInTraffic.getString("text").toLowerCase();

        HashMap hashMapjsonEleFour = (HashMap) jsonArray.get(1);
        JSONObject jsonEleFour=new JSONObject(hashMapjsonEleFour);
        JSONArray elementsjsonEleFour = jsonEleFour.getJSONArray("elements");
        JSONObject elementelementsjsonEleFourZero = elementsjsonEleFour.getJSONObject(1);
        JSONObject distanceInTraffic2 =elementelementsjsonEleFourZero.getJSONObject("duration_in_traffic");

        String timeFromDropToNewPickup =distanceInTraffic2.getString("text").toLowerCase();
        timingInformation[0]=Integer.valueOf(timeToDropUp.replace(" mins","").replace(" min",""));
        timingInformation[1]=Integer.valueOf(timeFromDropToNewPickup.replace(" mins","").replace(" min",""));
    return timingInformation;
    }
}
