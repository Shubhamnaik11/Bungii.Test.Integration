package com.bungii.ios.utilityfunctions;

import com.bungii.common.manager.DbContextManager;
import com.bungii.common.utilities.LogUtility;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtility extends DbContextManager {
    private static LogUtility logger = new LogUtility(DbUtility.class);

    /**
     * Connect to MS squl server and get verification SMS verification code
     *
     * @param phoneNumber
     * @return
     */
    public static String getVerificationCode(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT SmsVerificationCode FROM customer WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("SMS code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }

    public static String getVerificationCodeDriver(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT SmsVerificationCode FROM driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("SMS code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }


    public static boolean isPhoneNumberUnique(String phoneNumber) {
        String id = "";
        String queryString = "SELECT Id FROM customer WHERE Phone = " + phoneNumber;
        id = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Phone Number " + phoneNumber + "is unique " + id.equals(""));
        return id.equals("");
    }

    public static String getCustomerRefference(String phoneNumber) {
        String custRef = "";
        String queryString = "SELECT CustomerRef  FROM customer WHERE Phone = " + phoneNumber;
        custRef = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "customer reference is " + custRef);
        return custRef;
    }

    public static String getEstimateTime(String custRef) {
        String estTime = "";
        String queryString = "SELECT EstTime FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        estTime = getDataFromMySqlServer(queryString);
        logger.detail("For customer reference is " + custRef + " Extimate time is " + estTime);
        return estTime;
    }

    public static String getEstimateDistance(String custRef) {
        String estTime = "";
        String queryString = "SELECT EstDistance FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        estTime = getDataFromMySqlServer(queryString);
        logger.detail("For customer reference is " + custRef + " Extimate time is " + estTime);
        return estTime;
    }

    public static String getPickupID(String custRef) {
        String PickupID = "";
        String queryString = "SELECT PickupID FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        PickupID = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference is " + custRef + " Extimate time is " + PickupID);
        return PickupID;
    }

    public static String getActualTime(String pickupID) {
        String queryString = "select ActualTime from triprequest  WHERE PickupID = '" + pickupID + "' order by pickupid desc limit 1";

        String actualTime = getDataFromMySqlServer(queryString);

        logger.detail("pickupID" + pickupID + "  time is " + actualTime);
        return actualTime;
    }

    public static String getDriverRating(String phoneNumber) {
        String rating = "";
        String queryString = "SELECT Rating FROM driver WHERE phone = " + phoneNumber;
        rating = getDataFromMySqlServer(queryString);
        logger.detail("Rating is" + rating + ", query, " + queryString);
        return rating;
    }

    public static String getDriverAssignedForTrip(String pickupId) {
        String phoneNumber = "";
        String queryString = "select Phone  from driver where id In (select ControlDriverID from pickupdetails where PickupRef='"+pickupId+"' );";
        phoneNumber = getDataFromMySqlServer(queryString);
        logger.detail("phoneNumber is" + phoneNumber + ", query, " + queryString);
        return phoneNumber;
    }
    public static String getTELETfromDb(String custRef) {
        String PickupID = "";
        String queryString = "SELECT TELET FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        PickupID = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference is " + custRef + " Extimate time is " + PickupID);
        return PickupID;
    }
    public static boolean isDriverEligibleForTrip(String phoneNumber, String pickupRequest) {
            String queryString = "SELECT Id FROM driver WHERE phone = " + phoneNumber;
            String driverID = getDataFromMySqlServer(queryString);
            String queryString2 = "select DriverID from eligibletripdriver where pickupid IN (select PickupID from pickupdetails where pickupRef ='" + pickupRequest + "' )";
            boolean isDriverEligible =false;/* checkIfExpectedDataFromMySqlServer(queryString2,driverID);*/

            for(int i=0;i<30 && !isDriverEligible;i++){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isDriverEligible = checkIfExpectedDataFromMySqlServer(queryString2,driverID);

            }
            return isDriverEligible;

    }

    public static String getPickupRef(String customerPhone){
        String custRef=getCustomerRefference(customerPhone);
        String pickupRef=getDataFromMySqlServer("SELECT PickupRef FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1");
        return pickupRef;
    }

    public static String getDriverToPickupTime(String driverPhoneNumber, String pickupID){
        String queryString = "SELECT Id FROM driver WHERE phone = " + driverPhoneNumber;
        String driverID = getDataFromMySqlServer(queryString);
        String queryString2 = "select DriverToPickupTime from eligibletripdriver where pickupid ="+pickupID+ " and  DriverID="+driverID;
        String driverToPickupTime = getDataFromMySqlServer(queryString2);
        return driverToPickupTime;
    }
    public static String getReference(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT Reference FROM customer WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Reference code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }
    public static String getActiveFlag(String phoneNumber){
        String UserRef = getReference(phoneNumber);
        String queryString2 = "select Active from device where UserRef ='"+UserRef+ "' order by devid desc limit 1";
        String activeFlag = getDataFromMySqlServer(queryString2);
        return activeFlag;
    }

    public static String getDriverReference(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT Reference FROM driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Reference code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }
    public static String getDriverActiveFlag(String phoneNumber){
        String UserRef = getDriverReference(phoneNumber);
        String queryString2 = "select Active from device where UserRef ='"+UserRef+ "' order by devid desc limit 1";
        String activeFlag = getDataFromMySqlServer(queryString2);
        return activeFlag;
    }
}