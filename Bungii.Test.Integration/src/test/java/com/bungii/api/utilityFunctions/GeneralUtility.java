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
        }else if(geofence.equalsIgnoreCase("boston")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("boston.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("boston.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("baltimore")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("baltimore.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("baltimore.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("atlanta")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("atlanta.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("atlanta.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("miami")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("miami.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("miami.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("nashville")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("nashville.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("nashville.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("denver")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("denver.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("denver.pickup.longitude"));
        }else if(geofence.equalsIgnoreCase("washingtondc")){
            pickupLat = Float.valueOf(PropertyUtility.getDataProperties("washingtondc.pickup.latitude"));
            pickupLong = Float.valueOf(PropertyUtility.getDataProperties("washingtondc.pickup.longitude"));
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
    public static String getBungiiEndTimeForManuallyEnd() {
        //11/15/2019 12:43 AM
        Calendar calendar = Calendar.getInstance();
        Date dateTime = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone(new com.bungii.ios.utilityfunctions.GeneralUtility().getTimeZoneBasedOnGeofenceId()));
        String dateFormatted = sdf.format(dateTime);
        return dateFormatted;
    }

    public String getBungiiTimeZoneLanel(){
        String timeLabel=" "+new com.bungii.ios.utilityfunctions.GeneralUtility().getTimeZoneBasedOnGeofence();
        String timeZoneCompleteText="";
        //TODO: Add other timezone
                switch (timeLabel.trim().toUpperCase()){
                    case "CST":
                        timeZoneCompleteText="Central Standard Time";
                        break;
                }
        return timeZoneCompleteText;
    }
}
