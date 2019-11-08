package com.bungii.android.utilityfunctions;

import com.bungii.common.manager.DbContextManager;
import com.bungii.common.utilities.LogUtility;

public class DbUtility extends DbContextManager {
    private static LogUtility logger = new LogUtility(DbUtility.class);

    /**
     * Connect to MS sql server and get verification SMS verification code
     *
     * @param phoneNumber
     * @return
     */
    public static String getVerificationCode(String phoneNumber) {
        String smsCode = "";
/*        String queryString = "SELECT SmsVerificationCode FROM Driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMsSqlServer(queryString);*/
        String queryString = "SELECT SmsVerificationCode FROM customer WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("SMS code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }

    /**
     * Check if phoneNumber is unique
     *
     * @param phoneNumber Phone Number that us to be check
     * @return
     */
    public static boolean isPhoneNumberUnique(String phoneNumber) {
        String id = "";
/*        String queryString = "SELECT Id FROM Driver WHERE Phone = " + phoneNumber;
        id = getDataFromMsSqlServer(queryString);*/
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
    public static String getVerificationCodeDriver(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT SmsVerificationCode FROM driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("SMS code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }
    public static String getTELETfromDb(String custRef) {
        String PickupID = "";
        String queryString = "SELECT TELET FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        PickupID = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference is " + custRef + " Extimate time is " + PickupID);
        return PickupID;
    }
    public static void getLoadingTimeStamp(String customerPhone, String driverPhone){
        String pickupRef = "",custRef=getCustomerRefference(customerPhone),driverRef=getDriverReference(driverPhone);
        String queryString = "SELECT PickupRef FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        pickupRef = getDataFromMySqlServer(queryString);
    }
    public static String getDriverReference(String phoneNumber) {
        String driverRef = "";
        String queryString = "SELECT DriverRef  FROM driver WHERE Phone = " + phoneNumber;
        driverRef = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "DriverRef is " + driverRef);
        return driverRef;
    }
    public static String[] getDriverLocation(String phoneNumber) {
        String driverId = "";String driverLocation[] = new String[2];
        String queryString = "SELECT Id FROM driver WHERE Phone = " + phoneNumber;
        driverId = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "driverId is " + driverId);

        driverLocation[0]=    getDataFromMySqlServer("select Latitude from driverlocation where driverid = "+driverId);
        driverLocation[1]=    getDataFromMySqlServer("select Longitude from driverlocation where driverid = "+driverId);

        return driverLocation;
    }

    public static String[] getPickupAndDropLocation(String pickupRef){
        String tripLocation[] = new String[4];
        tripLocation[0]=    getDataFromMySqlServer("select PickupLat from pickupdetails pd inner join pickupdropaddress pda on pd.pickupid = pda.pickupid where pd.pickupref="+pickupRef);
        tripLocation[1]=    getDataFromMySqlServer("select PickupLong from pickupdetails pd inner join pickupdropaddress pda on pd.pickupid = pda.pickupid where pd.pickupref="+pickupRef);
        return tripLocation;
    }

    public static String getStatusTimeStampForStack(String pickupRef){
        String statusTimeStamp=  getDataFromMySqlServer("select StatusTimestamp from tripevents te join pickupdetails pd on te.pickupid = pd.pickupid where pickupRef = '"+pickupRef+"' and te.TripStatus = 40");
        return statusTimeStamp;
    }

}