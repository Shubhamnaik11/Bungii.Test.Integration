package com.bungii.web.utilityfunctions;

import com.bungii.common.manager.DbContextManager;
import com.bungii.common.utilities.LogUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbUtility extends DbContextManager {
    private static LogUtility logger = new LogUtility(DbUtility.class);
    //Sample function
    /**
     * Connect to MS sql server and get verification SMS verification code
     *
     * @param phoneNumber
     * @return
     */
    public static String getVerificationCode(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT SmsVerificationCode FROM driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("SMS code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }
    public static boolean isPhoneNumberUnique(String phoneNumber) {
        String id = "";
        String queryString = "SELECT Id FROM driver WHERE Phone = " + phoneNumber;
        id =getDataFromMySqlMgmtServer(queryString);
        logger.detail("Phone Number " + phoneNumber + "is unique " + id.equals(""));
        return id.equals("");
    }
    public static String getLatestPickupRefOfCustomer(String customerRef) {
        String pickupRef = "";
        String queryString = "SELECT PickupRef FROM pickupdetails WHERE CustomerRef='" + customerRef +"' ORDER BY Pickupid DESC LIMIT 1";
        pickupRef =getDataFromMySqlServer(queryString);
        logger.detail("Pickup Reference " + pickupRef + " of customer " + customerRef );
        return pickupRef;
    }
    public static String getCustomerPhone(String firstName, String lastName) {
        String phone = "";
        String queryString = "SELECT Phone FROM customer WHERE FirstName like'" + firstName +"' and Lastname like '"+lastName+"'LIMIT 1";
        phone =getDataFromMySqlServer(queryString);
        logger.detail("Phone  " + phone + " of customer " + firstName+ " " + lastName );
        return phone;
    }
    public static String getCustomerEmail(String firstName, String lastName) {
        String email = "";
        String queryString = "SELECT EmailAddress FROM customer WHERE FirstName like'" + firstName +"' and Lastname like '"+lastName+"'LIMIT 1";
        email =getDataFromMySqlServer(queryString);
        logger.detail("Email  " + email + " of customer " + firstName+ " " + lastName );
        return email;
    }
    public static String getPickupId(String pickupRef) {
        String pickupid = "";
        String queryString = "SELECT Pickupid FROM pickupdetails WHERE pickupref ='" + pickupRef+"'";
        pickupid =getDataFromMySqlServer(queryString);
        logger.detail("Pickupid  " + pickupid + " of pickupref " + pickupRef );
        return pickupid;
    }
    public static String getPickupLocation(String pickupId) {
        String pickupLat, pickupLong, location = "";
        String queryString = "SELECT concat(ifnull(pickuplat,''),',',ifnull(pickuplong,'')) FROM pickupdropaddress WHERE pickupId =" + pickupId;
        location =getDataFromMySqlServer(queryString);
         //queryString = "SELECT PickupLong FROM customer WHERE pickupId ='" + pickupId;
       // pickupLong =getDataFromMySqlServer(queryString);
       // location = pickupLat+","+pickupLong;
        logger.detail("Location  " + location + " of PickupId " + pickupId );
        return location;
    }
    public static String getOndemandStartTime(String pickupref) {
        String ondemandStartTime = "";
        String queryString = "SELECT StatusTimestamp  FROM tripevents where TripStatus = 23 and pickupid in (SELECT pickupid FROM pickupdetails WHERE pickupref ='" + pickupref+"')";
        ondemandStartTime =getDataFromMySqlServer(queryString);
        logger.detail("Ondemand Start time  " + ondemandStartTime + " of PickupRef " + pickupref );
        return ondemandStartTime;
    }

    public static List<HashMap<String,Object>> getListOfGeoFenceIds() {
        List<HashMap<String,Object>> listOfGeofenceIds = new ArrayList<>();
        String queryString = "select gsv.geofenceID from geofence gf\n" +
                "inner join geofencesettingsversions gsv on gf.Id = gsv.geofenceID\n" +
                "group by gsv.geofenceID";
        listOfGeofenceIds = getDataFromMySqlServerMap(queryString);
        return listOfGeofenceIds;
    }


    public static int getGeofenceSettingsVersions(int geofenceId) {
        int geoFenceRefId = 0;
        //select *   from  geofencesettingsversions where GeofenceID = '12' and IsActive = true
        String queryString = "select GeofenceSettingVersionID from geofencesettingsversions where geofenceID ='" + geofenceId +"' and isActive = true";
        geoFenceRefId =Integer.parseInt(getDataFromMySqlServer(queryString));
        logger.detail("geoFenceRefId  " + geoFenceRefId + " of geofenceId " + geofenceId );
        return geoFenceRefId;
    }

    public static int getGeofenceSettings(int geoFenceSettingVerId) {
        int geoFenceSettingVerRef = 0;
        //select *   from  geofencesettingsversions where GeofenceID = '12' and IsActive = true
        String queryString = "select count(*) from geofencesettings where GeofenceSettingVersionID ='" + geoFenceSettingVerId +"'";
        System.out.println(queryString);
        geoFenceSettingVerRef = Integer.parseInt(getDataFromMySqlMgmtServer(queryString));
        logger.detail("geoFenceSettingVerRef  " + geoFenceSettingVerRef + " of geofencesettingVersionID " + geoFenceSettingVerId );
        return geoFenceSettingVerRef;
    }


    public static int getGeofenceAttributes() {
        int returnedKey = 0;
        String queryString = "SELECT count(*) FROM geofenceattributes";
        returnedKey =Integer.parseInt(getDataFromMySqlMgmtServer(queryString));
        logger.detail("Value returned " + returnedKey );
        return returnedKey;
    }

    public static List<HashMap<String,Object>> fetchAllDataForGeoFence() {
        List<HashMap<String,Object>> listOfGeofences = new ArrayList<>();
        String queryString = "select count(*),  gfs.GeofenceSettingVersionID from geofence gf\n" +
                "inner join geofencesettingsversions gsv on gf.Id = gsv.geofenceID and gsv.isActive=true\n" +
                "inner join geofencesettings gfs on gsv.GeofenceSettingVersionID = gfs.GeofenceSettingVersionID \n" +
                "group by gfs.GeofenceSettingVersionID";
        listOfGeofences = getDataFromMySqlServerMap(queryString);
        return listOfGeofences;
    }
}