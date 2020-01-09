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
    public static String getCustomerPhone(String customerName) {
        String phone = "";
        String queryString = "SELECT Phone FROM customer WHERE FirstName like'" + customerName +"'LIMIT 1";
        phone =getDataFromMySqlServer(queryString);
        logger.detail("Phone  " + phone + " of customer " + customerName );
        return phone;
    }
    public static String getCustomerEmail(String customerName) {
        String email = "";
        String queryString = "SELECT EmailAddress FROM customer WHERE FirstName like'" + customerName +"'LIMIT 1";
        email =getDataFromMySqlServer(queryString);
        logger.detail("Email  " + email + " of customer " + customerName );
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
}