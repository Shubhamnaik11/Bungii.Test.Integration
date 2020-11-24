
@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled Part G
  
  Background:
    
    ############################################################################################
  #@regression
  @ready
  Scenario: Verify Scheduled Bungii Notification Info Is Correct (Estimaged earnings - Date etc.)
    When I clear all notification
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    Then I click "Go Online" button on Home screen on driver app
    When I Switch to "customer" application on "same" devices
    
    And I request "Solo Scheduled" Bungii as a customer in "Kansas" geofence
      | Bungii Time | Customer Phone | Customer Name                    | Customer Password |
      | now         | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    Then I click on notification for the "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen
    And "correct scheduled trip details" should be displayed on Bungii request screen
    When I click "ACCEPT" button on SCHEDULED BUNGII screen
    Then I should be navigated to "SCHEDULED BUNGIIS" screen
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
  
  @regression
  Scenario:Verify Driver Recieves Scheduled Bungii Request While In Offline State
    When I clear all notification
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    Then I click "Go Offline" button on Home screen on driver app
    When I Switch to "customer" application on "same" devices
    And I request "Solo Scheduled" Bungii as a customer in "Kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |
    Then I click on notification for the "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen
    When I click "REJECT" button on SCHEDULED BUNGII screen
    When I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
    And I Switch to "customer" application on "same" devices
    And I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |
    And I click on notification for the "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen
    When I click "REJECT" button on SCHEDULED BUNGII screen
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
  
  
  @regression
  Scenario:Verify Driver Is Not Able To Accept The Request If Trip Is Already Accepted By The Required Number Of Drivers
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    
    And I click "ACCEPT" button on Bungii Request screen
    Then user is alerted for "PICKUP REQUEST NO LONGER AVAILABLE"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  #@regression
  @ready
  Scenario: Verify If Driver receives More Than One Requests He Is Not Able To Accept The Bungii If He Has Already Accepted A Bungii whos TELET Time Overlaps - Scenario:Solo
    Given I Switch to "customer" application on "same" devices
    #trip 1
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Scheduled    | 15 min ahead |
     #trip 2
    Given I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    | Customer label |
      | NEXT_POSSIBLE | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test | 2              |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should able to see "two" available trip
    And I Select Trip from available trip
    And I click "ACCEPT" button on Bungii Request screen
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
    Then I should able to see "zero" available trip
    
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8805368840      |
  
  
  
  @regression
  Scenario: Verify Status Of Scheduled Bungii In the Scheduled Trip Screen When Only One Driver Accepts The Trip
    And I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      |               |
    
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then trips status should be "Contacting Other Driver"
    And I Select Trip from driver scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And "correct duo scheduled trip details" should be displayed on Bungii request screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
  
  
  @regression
  
  Scenario:Verify Details In The Bungii Details Screen When Required Number Of Drivers Accept The Trip
    
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then trips status should be "estimated cost of duo trip"
    And I Select Trip from driver scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And "correct duo scheduled trip details" should be displayed on Bungii request screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  #@regression
  @ready
  Scenario: Verify Customer Receives Notification When Control Driver Starts Duo Bungii
    When I clear all notification
    And I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    Then I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    When I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Enroute       | Accepted      |
    When I Switch to "customer" application on "same" devices
    And I click on notification for "Customer" for "DRIVERS ARE ENROUTE"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

    ##################################################################################################
  
  @ready
  Scenario: Verify If Re-searched Driver Can Cancel Trip After Starting The Scheduled Duo Trip
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    
    And I remove current driver and researches Bungii
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    
    When I switch to "ORIGINAL" instance
    
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "kansas driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    Then Bungii driver should see "Scheduled Bungii screen"
    
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
    
      #@regression
  @regression
  Scenario: Verify Customer Can Cancel Through SMS To Admin after 2 hour processing is over (Irrespective Of No. Of Required Drivers Have Accepted Or Not)
    When I Switch to "driver" application on "same" devices
    Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in
    
    And I Switch to "customer" application on "same" devices
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
  
    When I Switch to "customer" application on "same" devices
    Given I login as customer "8805368840" and is on Home Page
    
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    Then I wait for "2" mins
    And I select already scheduled bungii
    #When I Cancel selected Bungii
    When I tap on "Cancel Bungii" button
    Then correct details should be displayed on the "ADMIN-SMS" app
    
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
    
    
    
  #@regression
  Scenario: Verify If Customer Receives Notification Once Required Number Of Drivers Accepts The Scheduled Trip - Solo
    When I Switch to "driver" application on "same" devices
    Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in
    
    And I Switch to "customer" application on "same" devices
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I login as customer "8805368840" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
    Then I click on notification for "SCHEDULED PICKUP ACCEPTED"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  #@regression
  Scenario: Verify If Customer Receive Notification Once Required Number Of Drivers Accepts Scheduled Trip - DUO
    When I Switch to "driver" application on "same" devices
    Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in
    
    And I Switch to "customer" application on "same" devices
    And I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I login as customer "8805368840" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    Then I click on notification for "SCHEDULED PICKUP ACCEPTED"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
  
  @ready
  Scenario: Verify Customer Can See Text Stating That Driver Can Be Contacted On The Bungii Details Screen Only When The Trip Has Been Accepted By Required Number Of Drivers
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I login as customer "8805368840" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    
    And I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    Then I verify that text "You will have the ability to contact your drivers when the Bungii begins" is displayed
    Then I cancel all bungiis of customer
      
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
    
       #@regression
  @ready
  Scenario: Verify Driver Doesnt Receive Scheduled Request If The Request Is Sent Outside Of The Time That Is Set For Trip Alert Settings
    When I clear all notification
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    When I Select "ALERT SETTINGS" from driver App menu
    And I update kansas driver todays trip alert setting to outside current time
    When I Switch to "customer" application on "same" devices
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
    And I should able to see "zero" available trip
    And I Select "ALERT SETTINGS" from driver App menu
    And I update trip setting of "TODAY" to "12:00 AM" to "11:59 PM"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
