package com.bungii.ios.utilityfunctions;

import com.bungii.common.manager.DbContextManager;
import com.bungii.common.utilities.LogUtility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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
        String queryString = "SELECT CustomerRef FROM customer WHERE Phone = " + phoneNumber;
        custRef = getDataFromMySqlServer(queryString);
        logger.detail("For Phone Number " + phoneNumber + "customer reference is " + custRef);
        return custRef;
    }

    public static String getEstimateTime(String custRef) {
        String estTime = "";
        String queryString = "SELECT EstTime FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        estTime = getDataFromMySqlServer(queryString);
        logger.detail("For customer reference  " + custRef + " Extimate time is " + estTime);
        return estTime;
    }

    public static String getEstimateDistance(String custRef) {
        String estTime = "";
        String queryString = "SELECT EstDistance FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        estTime = getDataFromMySqlServer(queryString);
        logger.detail("For customer reference  " + custRef + " Extimate time is " + estTime);
        return estTime;
    }

    public static String getPickupID(String custRef) {
        String PickupID = "";
        String queryString = "SELECT PickupID FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1";
        PickupID = getDataFromMySqlServer(queryString);

        logger.detail("For customer reference " + custRef + " Extimate time is " + PickupID);
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

            for(int i=0;i<8 && !isDriverEligible;i++){
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
        String pickupRef="";
        if(customerPhone!="") {
            String custRef = getCustomerRefference(customerPhone);
             pickupRef = getDataFromMySqlServer("SELECT PickupRef FROM pickupdetails WHERE customerRef = '" + custRef + "' order by pickupid desc limit 1");
        }
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

    public static String getResarchedPickupReference(String pickupRequest) {
        String pickupRef = "";
        String queryString = "SELECT PickupRef FROM pickupdetails WHERE LinkedPickupID in (SELECT PickupID from pickupdetails where pickupref='" + pickupRequest+"')";
        try {
            Thread.sleep(120000); //Waiting for research trip to synch
        }
        catch(Exception ex){}
        pickupRef = getDataFromMySqlServer(queryString);
        logger.detail("Researched Pickup Ref is" + pickupRef + ", query, " + queryString);
        return pickupRef;
    }

    public static String getCustomerDeviceToken(String phoneNumber){
        String queryString2 = " select token from device where UserRef IN (select CustomerRef from customer where phone="+phoneNumber+") order by DevID desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public static String getDriverDeviceToken(String phoneNumber){
        String queryString2 = " select token from device where UserRef IN (select DriverRef from driver  where phone="+phoneNumber+") order by DevID desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public static String getCustomersMostRecentBungii(String phoneNumber){

        String queryString2 = "SELECT PickupRef FROM pickupdetails  WHERE customerRef IN(SELECT CustomerRef FROM customer WHERE phone="+phoneNumber+") order by pickupid desc limit 1";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public static String UnassignTripFromCustomer(String pickupRef){

        String queryString2 = "update customer c\n" +
                "join pickupdetails pd on pd.customerRef = c.customerRef\n" +
                "set currentassignedpickup = null\n" +
                "where pd.PickupRef = "+pickupRef+";";
        String deviceToken = getDataFromMySqlServer(queryString2);
        return deviceToken;
    }

    public static String getDriverCurrentToken(String phoneNumber){

        String queryString2 = "select Token from driverlogin where driverid in (select id from driver where Phone= '"+phoneNumber+"')  and OutTime is null";
        String deviceToken = getDataFromMySqlMgmtServer(queryString2);
        return deviceToken;
    }

     public static String getCustomerCurrentToken(String phoneNumber){

        String queryString2 = "select Token from customerlogin where customerid in (select id from customer where Phone= '"+phoneNumber+"') and OutTime is null";
        String deviceToken = getDataFromMySqlMgmtServer(queryString2);
        return deviceToken;
    }
    public static String getPushNotificationContent(String phoneNumber, String pickupRef){
        String queryString2= "select Payload from pushnotification where userid in (select Id from driver where phone = '"+phoneNumber+"') and Payload Like '%"+pickupRef+"%'";
        String Payload = getDataFromMySqlServer(queryString2);
        logger.detail("Query : "+ queryString2 +" | Payload : "+ Payload);
        return Payload;
    }

    public static String getCustomerPushNotificationContent(String customerPhoneNum, String pickupRef, String content)
    {
        String queryString2= "select Payload from pushnotification where userid in (select Id from customer where phone = '"+customerPhoneNum+"') and Payload Like '%"+pickupRef+"%' and Payload Like '%"+content+"%'";
        String Payload = getDataFromMySqlServer(queryString2);
        logger.detail("Query : "+ queryString2 +" | Payload : "+ Payload);
        return Payload;

    }
    public static String getDriverPushNotificationContent(String driverPhoneNum, String pickupRef, String content)
    {
        String queryString2= "select Payload from pushnotification where userid in (select Id from driver where phone = '"+driverPhoneNum+"') and Payload Like '%"+pickupRef+"%' and Payload Like '%"+content+"%'";
        String Payload = getDataFromMySqlServer(queryString2);
        logger.detail("Query : "+ queryString2 +" | Payload : "+ Payload);
        return Payload;

    }
    public static String getCustomerName(String phoneNumber) {
        String fullname = "";
        String queryString = "SELECT fullname FROM customer WHERE Phone = " + phoneNumber;
        fullname = getDataFromMySqlServer(queryString);
        logger.detail("Fullname is" + fullname + ", query, " + queryString);
        return fullname;
    }

    public static String checkRejectionReason(String driverPhone){
        String driverId=getDataFromMySqlServer("Select Id from driver where phone= "+driverPhone);
        String reasonId = getDataFromMySqlServer("select pickup_driver_reject_reason_id from pickup_driver_reject_reason where driver_id = "+driverId);
        return reasonId;
    }
    public static String getDriverShareSameTier() {
        String driverShare = "";
        String queryString = "select driver_share from bungii_admin_qa_auto.bp_service_level sl\n" +
                "join bungii_admin_qa_auto.bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bungii_admin_qa_auto.bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bungii_admin_qa_auto.bp_fixed_distance_weight_price_matrix pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like 'qaauto-floordecor166%' and sl.service_level_number = 1 and weight_range_min = '1000' and weight_range_max = '1500' \n" +
                "and mile_range_min = '0' and mile_range_max = '10'\n" +
                "order by subdomain_name, sl.service_level_number, mile_range_min, weight_range_min;";
        driverShare = getDataFromMySqlServer(queryString);
        logger.detail("Driver Share is "+ driverShare);
        return driverShare;
    }
    public static String getDriverShareDifferentTier() {
        String driverShare = "";
        String queryString = "select driver_share from bungii_admin_qa_auto.bp_service_level sl\n" +
                "join bungii_admin_qa_auto.bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bungii_admin_qa_auto.bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bungii_admin_qa_auto.bp_fixed_distance_weight_price_matrix pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like 'qaauto-floordecor166%' and sl.service_level_number = 1 and weight_range_min = '0' and weight_range_max = '500' \n" +
                "and mile_range_min = '0' and mile_range_max = '10'\n" +
                "order by subdomain_name, sl.service_level_number, mile_range_min, weight_range_min;";
        driverShare = getDataFromMySqlServer(queryString);
        logger.detail("Driver Share is " + driverShare);
        return driverShare;
    }
    public static String getDriverShareDifferentSeviceLevel() {
        String driverShare = "";
        String queryString = "select driver_share from bungii_admin_qa_auto.bp_service_level sl\n" +
                "join bungii_admin_qa_auto.bp_store_setting_fn_matrix fnm on fnm.bp_config_version_id = sl.bp_config_version_id\n" +
                "join bungii_admin_qa_auto.bp_store s on s.bp_store_id = fnm.bp_store_id\n" +
                "join bungii_admin_qa_auto.bp_fixed_distance_weight_price_matrix pr on pr.bp_service_level_id = sl.bp_service_level_id\n" +
                "where fnm.bp_setting_fn_id = 3 and subdomain_name is not null\n" +
                "and subdomain_name like 'qaauto-floordecor166%' and sl.service_level_number = 2 and weight_range_min = '1000' and weight_range_max = '1500' " +
                "and mile_range_min = '20' and mile_range_max = '30'\n" +
                "order by subdomain_name, sl.service_level_number, mile_range_min, weight_range_min;";
        driverShare = getDataFromMySqlServer(queryString);
        logger.detail("Driver Share is " + driverShare);
        return driverShare;
    }

}