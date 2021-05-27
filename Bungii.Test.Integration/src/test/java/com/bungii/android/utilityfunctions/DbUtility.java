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
    public static String getPickupReff(String custRef) {
        String PickupID = "";
        String queryString = "SELECT PickupRef FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        PickupID = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference is " + custRef + " PickupRef is " + PickupID);
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
        String telet = "";
        String queryString = "SELECT TELET FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        telet = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference is " + custRef + " TELET time is " + telet);
        return telet;
    }
    public static String[] getLoadingTimeStamp(String customerPhone){
        String[] loadingTme= new String[2];
        String pickupID = "",custRef=getCustomerRefference(customerPhone);
        String queryString = "SELECT PickupID FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        pickupID = getDataFromMySqlServer(queryString);
        loadingTme[0]=getDataFromMySqlServer("select StatusTimestamp from tripevents where pickupid ="+pickupID+" and TripStatus=25");
        loadingTme[1]=getDataFromMySqlServer("select StatusTimestamp from tripevents where pickupid ="+pickupID+" and TripStatus=26");
        return loadingTme;
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

    public static String[] getPickupAndDropLocation(String customerPhone){
        String custRef=getCustomerRefference(customerPhone);
        String queryString = "SELECT PickupID FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        String pickupID = getDataFromMySqlServer(queryString);
        String tripLocation[] = new String[4];
        tripLocation[0]=    getDataFromMySqlServer("select PickupLat from pickupdropaddress  where PickupID="+pickupID);
        tripLocation[1]=    getDataFromMySqlServer("select PickupLong from pickupdropaddress  where PickupID= "+pickupID);
        tripLocation[2]=    getDataFromMySqlServer("select DropOffLat from pickupdropaddress  where PickupID="+pickupID);
        tripLocation[3]=    getDataFromMySqlServer("select DropOffLong from pickupdropaddress  where PickupID= "+pickupID);
        return tripLocation;
    }

    public static String getStatusTimeStampForStack(String customerPhone){
        String custRef=getCustomerRefference(customerPhone);
        String pickupRef=getDataFromMySqlServer("SELECT PickupRef FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1");
        String statusTimeStamp=  getDataFromMySqlServer("select StatusTimestamp from tripevents te join pickupdetails pd on te.pickupid = pd.pickupid where pickupRef = '"+pickupRef+"' and te.TripStatus = 40");
        return statusTimeStamp;
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

    public static String getDriversReference(String phoneNumber) {
        String smsCode = "";
        String queryString = "SELECT Reference FROM driver WHERE Phone = " + phoneNumber;
        smsCode = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Reference code is" + smsCode + ", query, " + queryString);
        return smsCode;
    }
    public static String getDriverActiveFlag(String phoneNumber){
        String UserRef = getDriversReference(phoneNumber);
        String queryString2 = "select Active from device where UserRef ='"+UserRef+ "' order by devid desc limit 1";
        String activeFlag = getDataFromMySqlServer(queryString2);
        return activeFlag;
    }
    public String getCustomerDeviceToken(String phoneNumber){
        String queryString2 = "select token from device where UserRef IN (select CustomerRef from customer where phone='"+phoneNumber+"') order by DevID desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public String getDriverDeviceToken(String phoneNumber){
        String queryString2 = "select token from device where UserRef IN (select DriverRef from driver  where phone='"+phoneNumber+"') order by DevID desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public String getCustomersMostRecentBungii(String phoneNumber){

        String queryString2 = "SELECT PickupRef FROM pickupdetails  WHERE customerRef IN(SELECT CustomerRef FROM customer WHERE phone='"+phoneNumber+"') order by pickupid desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }
    public String getCustomersMostRecentBungiiPickupId(String phoneNumber){
        String queryString2 = "SELECT Pickupid FROM pickupdetails WHERE customerRef IN (SELECT CustomerRef FROM customer WHERE phone='"+phoneNumber+"') order by pickupid desc limit 1";
        String Pickupid = getDataFromMySqlServer(queryString2);
        return Pickupid;
    }
    public String getPickupNoteOfLastPickupOf(String phoneNumber){
        String getLastPickupId = getCustomersMostRecentBungiiPickupId(phoneNumber);
        String queryString2 = "select cust_sch_conversion_remark_comments from pickup_detail_remarks where pickup_id in ('"+getLastPickupId+"')";
        String getPickupNote = getDataFromMySqlServer(queryString2);
        return getPickupNote;
    }

    public static String getDriverPushNotificationContent(String phoneNumber, String pickupRef){
        String queryString2= "select Payload from pushnotification where userid in (select Id from driver where phone = '"+phoneNumber+"') and Payload Like '%"+pickupRef+"%' and UserType ='AUD'";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }
    public static String getCustomerPushNotificationContent(String phoneNumber, String pickupRef){
        String queryString2= "select Payload from pushnotification where userid in (select Id from customer where phone = '"+phoneNumber+"') and Payload Like '%"+pickupRef+"%' and UserType ='AUC'";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }
    public static String getPickupRef(String customerPhone){
        String custRef=getCustomerRefference(customerPhone);
        String pickupRef=getDataFromMySqlServer("SELECT PickupRef FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1");
        return pickupRef;
    }
}