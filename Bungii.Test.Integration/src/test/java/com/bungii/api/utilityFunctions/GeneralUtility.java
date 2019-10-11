package com.bungii.api.utilityFunctions;

import com.bungii.common.utilities.PropertyUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class GeneralUtility {

    public String genearateRandomString() {
        int stringlength = 10;
        String charsName = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
//generate random string
        String randomstring = "";
        for (int i = 0; i < stringlength; i++) {
            Random randomGeneratorName = new Random();
            int randomIntName = randomGeneratorName.nextInt(charsName.length());
            randomstring += charsName.substring(randomIntName, randomIntName + 1);
        }
        return randomstring;
    }

    public Float[] getDriverLocation(String geofence) {
        Float maxChange = 0.003f;
        Float minChange = -0.003f;
        Float pickupLat = 0f, pickupLong = 0f;
        // Add random float to  driver location
        Random rand = new Random();

        if (geofence.equalsIgnoreCase("goa")) {
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("goa.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("goa.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("kansas")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("kansas.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("kansas.pickup.longitude"));
        }
        Float[] driverCordinate = new Float[2];

        driverCordinate[0] = pickupLat + rand.nextFloat() * (maxChange - minChange) + minChange;

        driverCordinate[1] = pickupLong + rand.nextFloat() * (maxChange - minChange) + minChange;
        return driverCordinate;
    }

    public static String getCurrentUTCTime() {
        // Get formatted UTC time
        Calendar calendar = Calendar.getInstance();
        Date dateTime = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("GTM"));
        String dateFormatted = sdf.format(dateTime);
        return dateFormatted;
    }
}
