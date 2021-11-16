package com.bungii.web.utilityfunctions;

import com.bungii.common.manager.DbContextManager;
import com.bungii.common.utilities.LogUtility;

import java.text.DecimalFormat;
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
    public static List<HashMap<String,Object>> getDriverRating(String pickupId) {
       // String driverRating ;
        List<HashMap<String,Object>> driverRating = new ArrayList<>();
        String queryString = "SELECT DriverRating from triprequest where pickupid ='" + pickupId+"'";
        //driverRating =getDataFromMySqlServer(queryString);
        driverRating = getDataFromMySqlServerMap(queryString);
        logger.detail("Driver Rating =  " + driverRating + " is shown in database for PickupId= " + pickupId );
        return driverRating;
    }
    public static String getPickupIdFromFactPickup(String pickupRef) {
        String pickupid = "";
        String queryString = "SELECT id FROM factpickup WHERE pickupref ='" + pickupRef+"'";
        pickupid =getDataFromMySqlReportServer(queryString);
        logger.detail("Pickupid  " + pickupid + " of pickupref " + pickupRef );
        return pickupid;
    }
    public static String getLinkedPickupRef(String pickupRef) {
        String linkedpickupref = "";
        String queryString = "SELECT PICKUPREF FROM pickupdetails WHERE LINKEDPICKUPID in (SELECT Pickupid FROM pickupdetails WHERE pickupref ='" + pickupRef+"' )";
        linkedpickupref =getDataFromMySqlServer(queryString);
        logger.detail("Linked Pickupref " + linkedpickupref + " of pickupref " + pickupRef );
        return linkedpickupref;
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

    public static String getBungiiRate(String Geofence){
        String bungiiRate="";
        String queryString = "select Value from geofencesettings where GeofenceAttributeID=11 and GeofenceVersionID   in (select GeofenceSettingVersionID  from geofencesettingsversions where Geofenceid in (select id from geofence where name='" + Geofence+"') and IsActive = 1)";
        bungiiRate =getDataFromMySqlServer(queryString);
        logger.detail("Bungii Rate for  " + Geofence + " Geofence is " + bungiiRate );
        return bungiiRate;
    }

    public static String getEstPrice(String PickupRequest){
        String ActualPriceAfterDiscount="";
        String queryString = "SELECT ActualEstCostAfterDiscount  FROM pickupdetails where PickupRef='"+PickupRequest+"'";
        ActualPriceAfterDiscount =getDataFromMySqlServer(queryString);
        logger.detail("Actual Cost after discount for  " + PickupRequest + " is " + ActualPriceAfterDiscount );
        return ActualPriceAfterDiscount;
    }

    public static String getActualPrice(String PickupRequest){
        String ActualPriceAfterDiscount="";
        Integer PickupId;
        Integer TRID;
        String queryString ="select ActualCostAfterDiscount from trippaymentdetails where trid in(select TRID from triprequest where Pickupid in(SELECT PickupID  FROM pickupdetails where PickupRef='"+PickupRequest+"'))";

        ActualPriceAfterDiscount = getDataFromMySqlServer(queryString);
        logger.detail("Actual Cost after discount for  " + PickupRequest + " is " + ActualPriceAfterDiscount );
        return ActualPriceAfterDiscount;
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

    public static String getEstimateDistance(String alias) {
    String Estimate_distance;
   // String queryString = "SELECT EstDistance FROM pickupdetails where customerRef='"+customerReference+"'order by  pickupid desc limit 1";
        String queryString = "SELECT EstDistance FROM pickupdetails where customerRef in (select customerRef from customer where Id in (select customer_id from business_partner_location where alias like '"+alias+"')) order by  pickupid  desc limit 1";

        Estimate_distance = getDataFromMySqlServer(queryString);
        logger.detail("Estimate Distance =  " + Estimate_distance + " of latest delivery" );
        return Estimate_distance;

    }
    public static String getEstimateDistanceByPartnerReference(String partnerRef) {
        String Estimate_distance;
        String queryString = "SELECT EstDistance FROM pickupdetails where customerRef in (select CustomerRef from business_partner_location bpl join customer c on c.id = bpl.customer_id where business_partner_location_ref = '"+partnerRef+"')order by  pickupid desc limit 1";
        Estimate_distance = getDataFromMySqlServer(queryString);
        logger.detail("Estimate Distance =  " + Estimate_distance + " of Partner Location Reference " );
        return Estimate_distance;

    }

    public static String getEstimateTimeByPartnerReference(String partnerRef) {
        String Estimate_time;
        String queryString = "SELECT EstTime FROM pickupdetails where customerRef in (select CustomerRef from business_partner_location bpl join customer c on c.id = bpl.customer_id where business_partner_location_ref = '"+partnerRef+"')order by  pickupid desc limit 1";
        Estimate_time = getDataFromMySqlServer(queryString);
        logger.detail("Estimate Time =  " + Estimate_time + " of latest trip of Partner Location Reference " + partnerRef );
        return Estimate_time;

    }

    public static String getServicePrice(String Alias,int No_of_Driver,String Trip_Estimate_Distance,String Service_name) {
        String Trip_Price;

        String queryString ="select amount\n" +
                "from business_partner_loc_fixed_distance_pricing fp\n" +
                "join business_partner_location_config_version c on c.business_partner_location_config_version_id =fp.business_partner_location_config_version_id\n" +
                "join business_partner_location d on d.business_partner_location_id = c.business_partner_location_id\n" +
                "join bp_supplementary_service ss on ss.bp_supplementary_service_id = fp.bp_supplementary_service_id\n" +
                "where c.IsActive = 1 and alias like '"+Alias+"%'\n" +
                "and ss.service_name = '"+Service_name+"' and no_of_drivers="+No_of_Driver+" and  "+Trip_Estimate_Distance+" BETWEEN mile_range_min and mile_range_max\n" +
                "order by ss.service_level_number, fp.tier_number, fp.no_of_drivers";

        Trip_Price = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Calculated Estimated Service Price  =  " + Trip_Price + " of "+ Service_name +" [ Alias :"+Alias+" ]" + " For Drivers : "+No_of_Driver+" | Trip Distance : "+ Trip_Estimate_Distance);
        return Trip_Price;

    }

    public static String getServicePriceLastTier(String Alias,int No_of_Driver,String Trip_Estimate_Distance,String Service_name) {
        Double Trip_Price;
        String Last_Tier;
        Double Second_Last_Tier_Amount;
        double Second_Last_Tier_Milenge;

        //Query for selecting last tier number
        String queryString ="select max(tier_number)\n" +
                "from business_partner_loc_fixed_distance_pricing fp\n" +
                "join business_partner_location_config_version c on c.business_partner_location_config_version_id =fp.business_partner_location_config_version_id\n" +
                "join business_partner_location d on d.business_partner_location_id = c.business_partner_location_id\n" +
                "join bp_supplementary_service ss on ss.bp_supplementary_service_id = fp.bp_supplementary_service_id\n" +
                "where c.IsActive = 1 and alias like '"+Alias+"%'\n" +
                "and ss.service_name = '"+Service_name+"'\n" +
                "order by ss.service_level_number, fp.tier_number, fp.no_of_drivers";
        Last_Tier = getDataFromMySqlMgmtServer(queryString);
        int Last_Tier_Number = Integer.parseInt(Last_Tier);
        logger.detail("Last_Tier_Number : " +Last_Tier_Number);
        int Second_Last_Tier = Last_Tier_Number-1;
        logger.detail("Second_Last_Tier : " +Second_Last_Tier);

        //Query for selecting second last tier amount
        String queryString1 ="select amount\n" +
                "from business_partner_loc_fixed_distance_pricing fp\n" +
                "join business_partner_location_config_version c on c.business_partner_location_config_version_id =fp.business_partner_location_config_version_id\n" +
                "join business_partner_location d on d.business_partner_location_id = c.business_partner_location_id\n" +
                "join bp_supplementary_service ss on ss.bp_supplementary_service_id = fp.bp_supplementary_service_id\n" +
                "where c.IsActive = 1 and alias like '"+Alias+"%'\n" +
                "and ss.service_name = '"+Service_name+"' and no_of_drivers="+No_of_Driver+" and  tier_number="+Second_Last_Tier+"\n" +
                "order by ss.service_level_number, fp.tier_number, fp.no_of_drivers";
        Second_Last_Tier_Amount = Double.parseDouble(getDataFromMySqlMgmtServer(queryString1));
        logger.detail("Second_Last_Tier_Amount =  " + Second_Last_Tier_Amount );

        String queryString2 ="select mile_range_max\n" +
                "from business_partner_loc_fixed_distance_pricing fp\n" +
                "join business_partner_location_config_version c on c.business_partner_location_config_version_id =fp.business_partner_location_config_version_id\n" +
                "join business_partner_location d on d.business_partner_location_id = c.business_partner_location_id\n" +
                "join bp_supplementary_service ss on ss.bp_supplementary_service_id = fp.bp_supplementary_service_id\n" +
                "where c.IsActive = 1 and alias like '"+Alias+"%'\n" +
                "and ss.service_name = '"+Service_name+"' and no_of_drivers="+No_of_Driver+" and  tier_number="+Second_Last_Tier+"\n" +
                "order by ss.service_level_number, fp.tier_number, fp.no_of_drivers";
        Second_Last_Tier_Milenge = Double.parseDouble(getDataFromMySqlMgmtServer(queryString2));
        logger.detail("Second_Last_Tier_Milenge =  " + Second_Last_Tier_Milenge);
        Double Remaining_Milenge = Double.parseDouble(Trip_Estimate_Distance) - Second_Last_Tier_Milenge;
        logger.detail("Remaining_Milenge =  " + Remaining_Milenge);

        //Multiplying factor
        String queryString3 ="select amount\n" +
                "from business_partner_loc_fixed_distance_pricing fp\n" +
                "join business_partner_location_config_version c on c.business_partner_location_config_version_id =fp.business_partner_location_config_version_id\n" +
                "join business_partner_location d on d.business_partner_location_id = c.business_partner_location_id\n" +
                "join bp_supplementary_service ss on ss.bp_supplementary_service_id = fp.bp_supplementary_service_id\n" +
                "where c.IsActive = 1 and alias like '"+Alias+"%'\n" +
                "and ss.service_name = '"+Service_name+"' and no_of_drivers="+No_of_Driver+" and  tier_number="+Last_Tier_Number+"\n" +
                "order by ss.service_level_number, fp.tier_number, fp.no_of_drivers";


        Double Multiplier = Double.parseDouble(getDataFromMySqlMgmtServer(queryString3));
        logger.detail("Multiplier =  " + Multiplier);
        Double Cal2 = Remaining_Milenge*Multiplier;
        Trip_Price = Second_Last_Tier_Amount + Cal2;
        DecimalFormat dec = new DecimalFormat("#.00");
        String priceValue = dec.format(Trip_Price).toString();
        logger.detail("Calculated Estimate amount [For Exceeding Last Tier] =  " + priceValue + " of latest trip" );
        return priceValue;

    }

    public static String getMaxMilengeValue(String Alias,String Service_name) {
        String Max_Value_Min_Milenge;

        String queryString ="select max(mile_range_min)\n" +
                "from business_partner_loc_fixed_distance_pricing fp\n" +
                "join business_partner_location_config_version c on c.business_partner_location_config_version_id =fp.business_partner_location_config_version_id\n" +
                "join business_partner_location d on d.business_partner_location_id = c.business_partner_location_id\n" +
                "join bp_supplementary_service ss on ss.bp_supplementary_service_id = fp.bp_supplementary_service_id\n" +
                "where c.IsActive = 1 and alias like '"+Alias+"%'\n" +
                "and ss.service_name = '"+Service_name+"'\n" +
                "order by ss.service_level_number, fp.tier_number, fp.no_of_drivers";


        Max_Value_Min_Milenge = getDataFromMySqlMgmtServer(queryString);
        logger.detail("Max mile_range_min =  " + Max_Value_Min_Milenge + " of Alias "+ Alias +" and service " + Service_name );
        return Max_Value_Min_Milenge;

    }


    public static String getAccessorialCharge(String pickupref) {
        String accessorial_Charge;
        String queryString = "SELECT accessorial_fee_amount FROM triprequest WHERE pickupid IN (SELECT  pickupid FROM pickupdetails WHERE pickupref='"+pickupref+"');";
        accessorial_Charge = getDataFromMySqlServer(queryString);
        logger.detail("Total Accessorial Charge =  " + accessorial_Charge + " of delivery "+ pickupref );
        return accessorial_Charge;

    }
    public static String getBusinessNotes(String pickupref) {
        String business_notes;
        String queryString = "SELECT business_notes FROM trippaymentdetails where TRID in (SELECT TRID FROM triprequest WHERE pickupid IN (SELECT  pickupid FROM pickupdetails WHERE pickupref='"+pickupref+"'));";
        business_notes = getDataFromMySqlServer(queryString);
        logger.detail("Business Note =  " + business_notes + " of delivery "+ pickupref );
        return business_notes;

    }
    public static long getEstimateTimeforPickup(String Pickup_Reference) {
        long Estimate_time;
        String queryString = "SELECT EstTime FROM pickupdetails where PickupRef='"+Pickup_Reference+"'";
        Estimate_time = Long.parseLong(getDataFromMySqlServer(queryString));
        logger.detail("Estimate Time=  " + Estimate_time + " of PickupRef="+Pickup_Reference);
        return Estimate_time;

    }

    public static String getPickupToken(String Pickup_Reference) {
        String pickup_token;
        String queryString = "SELECT pickup_token FROM pickupdetails where PickupRef='"+Pickup_Reference+"'";
        pickup_token = getDataFromMySqlServer(queryString);
        logger.detail("Pickup_Token =  " + pickup_token + " of PickupRef="+Pickup_Reference);
        return pickup_token;

    }

    public static long getDefaultPickupTime(String Service_Name,String SubDomain){
        long default_Pickup_Time=0;
        String queryString ="select ss.default_pickup_time\n" +
                "from bp_supplementary_service ss\n"+
                "join business_partner_location_config_version c on c.business_partner_location_config_version_id =ss.business_partner_location_config_version_id\n" +
                "join business_partner_location d on d.business_partner_location_id = c.business_partner_location_id\n" +
                "where c.IsActive = 1 and service_name='"+Service_Name+"' and subdomainname like '%"+SubDomain+"%'";

        String default_Pickup_Time_Db = getDataFromMySqlMgmtServer(queryString);
        if(default_Pickup_Time_Db!=null){
            default_Pickup_Time = Long.parseLong(default_Pickup_Time_Db);
            logger.detail("Default Pickup Time=  " + default_Pickup_Time + " for Service: "+Service_Name);
        }
        else{
            logger.error("Default pickup time is not fetch for service "+Service_Name+" for SubDomain "+SubDomain);
        }


        return default_Pickup_Time;
    }

    public static long getDefaultDropoffTime(String Service_Name,String SubDomain){
        long default_Dropoff_Time=0;
        String queryString ="select ss.default_dropoff_time\n" +
                "from bp_supplementary_service ss\n"+
                "join business_partner_location_config_version c on c.business_partner_location_config_version_id =ss.business_partner_location_config_version_id\n" +
                "join business_partner_location d on d.business_partner_location_id = c.business_partner_location_id\n" +
                "where c.IsActive = 1 and service_name='"+Service_Name+"' and subdomainname like '%"+SubDomain+"%'";

        String default_Dropoff_Time_Db = getDataFromMySqlMgmtServer(queryString);
        if(default_Dropoff_Time_Db!=null){
            default_Dropoff_Time = Long.parseLong(default_Dropoff_Time_Db);
            logger.detail("Default Dropoff Time=  " + default_Dropoff_Time + " for Service: "+Service_Name);
        }
        else{
            logger.error("Default Dropoff time is not fetch for service "+Service_Name+" for SubDomain "+SubDomain);
        }

        return default_Dropoff_Time;
    }

    public static List<HashMap<String,Object>> getListOfService(String Alias_Name){
        List<HashMap<String,Object>> Service = new ArrayList<>();
        String queryString = "select ss.service_name\n" +
                "from bp_supplementary_service ss\n" +
                "join business_partner_location_config_version c on c.business_partner_location_config_version_id =ss.business_partner_location_config_version_id\n" +
                "join business_partner_location d on d.business_partner_location_id = c.business_partner_location_id\n" +
                "where c.IsActive = 1 and alias like '"+Alias_Name+"%'";
        Service = getListDataFromMySqlMgmtServer(queryString);
        return Service;
    }

    public static List<HashMap<String, Object>> getRegionsList() {
        List<HashMap<String,Object>> regions=  new ArrayList<>();
        String queryString = "select name from geofence where org_level = 2";
        regions = getListDataFromMySqlMgmtServer(queryString);
        return regions;
    }
}