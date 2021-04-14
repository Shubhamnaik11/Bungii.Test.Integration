@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled Part D
  
  
  Background:
  
  #########################################
 


  @regression
  Scenario: Verify Customer Can Cancel The Scheduled Bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Scheduled    | NEXT_POSSIBLE |
    When I am logged in as "valid" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    And I Cancel selected Bungii
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

 @regression
  Scenario: Verify Status Of Scheduled Bungii Trip In Scheduled Bungiis Menu Screen When Required Drivers Have Not Accepted It
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Scheduled    | NEXT_POSSIBLE |
    When I am logged in as "valid" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    Then The status on "MY BUNGIIS" should be displayed as "Contacting Drivers"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver 1 - contacting drivers"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  
  @regression
  Scenario: Verify When Bungii Is Cancelled By Admin It Is Removed From The Scheduled Trip List On Drivers App
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    When I Switch to "customer" application on "same" devices
    And I am logged in as "valid" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I wait for "2" mins
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    And I Cancel Bungii with following details
      | Charge | Comments | Reason                         |
      | 0      | TEST     | Outside of delivery scope      |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And I wait for "2" mins
    And Bungii must be removed from the List
    
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
    
    ##########################################
  @regression
  Scenario:Verify Alert Message Is Displayed When Customer Tries To Contact Driver More Than One Hour From Scheduled Time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1 hour ahead |
    
    And I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I am logged in as "valid" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select 1st trip from scheduled bungii
    When I try to contact driver using "sms driver1"
    Then user is alerted for "more than 1 hour from scheduled time"
    And correct support details should be displayed to customer on "SMS" app
    When I try to contact driver using "call driver1"
    Then user is alerted for "more than 1 hour from scheduled time"
    And correct support details should be displayed to customer on "SMS" app
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  
  @regression
  Scenario: Verify that that Past Trips page correctly displays completed Scheduled Solo Bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Completed     | NEXT_POSSIBLE |
    
    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
  
    And I tap on "Menu" > "My Bungiis" link
    Then "MY BUNGIIS" page should be opened
    And I click on "Past" tab
    And I open the trip for "Testdrivertywd_appleks_a_kay Stark_ksThreE" driver
    Then I verify driver names and trip cost
  
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  
  @knownissue
  Scenario: Verify Customer Can Create A Scheduled Bungii - Also Verify Correct Contact Number Is Displayed On Call And SMS Option
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    When I Switch to "customer" application on "same" devices
    And I am logged in as "valid" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
   # And I wait for Minimum duration for Bungii Start Time
    And Bungii Driver "Start Schedule Bungii" request
    And I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Enroute screen"
    
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for customer
    
    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "Enroute screen"
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
    
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"
    When Bungii Driver "slides to the next state"
    
    And I Switch to "customer" application on "same" devices
    Then Bungii customer should see "correct details" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    
    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And Bungii Driver "completes Bungii"
    And I Select "HOME" from driver App menu
