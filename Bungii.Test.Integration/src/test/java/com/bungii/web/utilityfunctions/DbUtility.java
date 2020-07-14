package com.bungii.web.utilityfunctions;

import com.bungii.common.manager.DbContextManager;
import com.bungii.common.utilities.LogUtility;

import java.util.ArrayList;

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

    public static String getScheduledTime(String customerPhone){
        String pickupId=getPickupIdfrom_pickup_additional_info(customerPhone);
        String Scheduled_Time = getDataFromMySqlServer("SELECT ScheduledTimestamp FROM pickupdetails WHERE pickupid = '" + pickupId + "' order by pickupid desc limit 1");
        return Scheduled_Time;
    }

    public static String getPickupRef(String customerPhone){
        String pickupId=getPickupIdfrom_pickup_additional_info(customerPhone);
        String pickupRef=getDataFromMySqlServer("SELECT PickupRef FROM pickupdetails WHERE pickupid = '" + pickupId + "' order by pickupid desc limit 1");
        return pickupRef;
    }

    public static String getPickupIdfrom_pickup_additional_info(String phoneNumber) {
        String pickupId = "";
        //String queryString = "SELECT CustomerRef  FROM customer WHERE Phone = " + phoneNumber;
        String queryString = "SELECT pickup_id from pickup_additional_info where customer_phone=" + phoneNumber + " order by  pickup_id desc limit 1";
        pickupId = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "latest PickupId is " + pickupId);
        return pickupId;
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

    public static String getEstimateDistance() {
    String Estimate_distance;
    String queryString = "SELECT EstDistance,Est FROM pickupdetails order by  pickupid desc limit 1";
    Estimate_distance = getDataFromMySqlServer(queryString);
        logger.detail("Estimate Distance=  " + Estimate_distance + " of latest trip" );
        return Estimate_distance;

    }

    public static String getEstimateTime() {
        String Estimate_time;
        String queryString = "SELECT EstTime FROM pickupdetails order by  pickupid desc limit 1";
        Estimate_time = getDataFromMySqlServer(queryString);
        logger.detail("Estimate Time=  " + Estimate_time + " of latest trip" );
        return Estimate_time;

    }

}