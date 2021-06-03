@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled Part I
  #Move inconcluive cases of Solo CSsheduled PartA and PartE

   # Customer 8805368840 Testcustomertywd_appleRicha Test
 # With 8805368840 - 15 cases
  Background:
    ##################################################################################################
  
  @regression
    #Stable
  Scenario: Verify If Incoming On-demend Trip Request TELET (Trip A) Overlaps Start Time Of Previously Scheduled Trip (Trip B) Then Driver Doesnt Receive Notification Or offline SMS
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I tap on "Go Online button" on Driver Home page
    When I request "Solo Ondemand" Bungii as a customer in "kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    | Customer label |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test | 2              |
    
    Then I should not get notification for ON DEMAND TRIP
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |
  
  @regression
    #Stable
  Scenario: Verify If Incoming Ondemand Trip TELET Overlaps Scheduled Trip Telet Then Request Should Not Be Sent To driver.
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time    |
      | kansas   | Accepted     | 0.5 hour ahead |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I tap on "Go Online button" on Driver Home page
    When I request "Solo Ondemand" Bungii as a customer in "kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    | Customer label |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test | 2              |
    
    Then I should not get notification for ON DEMAND TRIP
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |

    
  #@regression
  @failed123
  Scenario: Verify Status On Customers Scheduled Bungiis Screen When Both Drivers Have Accepted Trip
    When I Switch to "driver" application on "same" devices
    Then As a driver "Testdrivertywd_appleks_ra_four Kent" I log in
    
    And I Switch to "customer" application on "same" devices
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    
    And I login as customer "8805368840" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    Then The status on "MY BUNGIIS" should be displayed as "estimated cost"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver1 name"
    Then trips status on bungii details should be "driver2 name"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
    
    #@regression
  @ready
  Scenario:  Verify Customer Receives Notification When Control Driver Starts Solo Bungii
    When I clear all notification
    And I request "Solo Scheduled" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    Then I Switch to "customer" application on "same" devices
    And I login as customer "8805368840" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
      | Enroute       |
    When I Switch to "customer" application on "same" devices
    And I click on notification for "Customer" for "DRIVERS ARE ENROUTE"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
  
  
  #@regression
  @ready
    @ad1
  Scenario: Verify Validation Message Is Shown If Driver Tries To Start Bungii More Than 60 Mins Before The Scheduled Time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1.5 hour ahead |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    Then User should see message "60 MINS BEFORE SCHEDULE TRIP TIME" text on the screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
    
      #@regression
  @ready
  Scenario: Verify If Incoming Scheduled Request Start Time (Trip 3) Overlaps With TELET Of Accepted Stacked Request (Trip 2) Then Driver Doesn't Receive Scheduled Notification Or offline SMS
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | kansas   | Enroute      |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Switch to "customer" application on "same" devices
    
    When I request "Solo Ondemand" Bungii as a customer in "kansas" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                    | Customer label |
      | now         | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test | 2              |
    
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "view stack message" request
    And I tap on the "ACCEPT" Button on Bungii Request screen
    And I get TELET time of currrent trip of customer 2
    
    And I Switch to "customer" application on "same" devices
    Given I login as customer "9999990069" and is on Home Page
    
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I confirm trip with following details
      | Day | Trip Type | Time                              |
      | 0   | SOLO      | <TIME WITHIN TELET OF CUSTOMER 2> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    Then I should not get notification for SCHEDULED PICKUP AVAILABLE
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8805368840      |

 

  @regression
    #stable
  Scenario: Verify Driver Doesnt Receive Scheduled Trip Request If His Home Is Over 30 Mins Away From Pickup Location
    When I clear all notification
    When I Switch to "customer" application on "same" devices
    And I login as customer "8805368840" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    And I Switch to "customer" application on "same" devices

    And I enter "kansas pickup and dropoff locations greater than 30mins" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then I should be navigated to "Estimate" screen
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
    And I Switch to "customer" application on "same" devices
    When  I am on customer Log in page
    And I enter customers "8805368840" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    Then Bungii must be removed from "MY BUNGIIS" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8805368840 |                 |


  #@regression
  @ready
  Scenario: Verify Driver Receives Alert Stating That The Trip Has Already Been Accepted By Him If He Receives Request Notification After Accepting The Trip From Available Trips
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    Given I Switch to "customer" application on "same" devices

    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |

    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    When I click "ACCEPT" button on Bungii Request screen
    Then I wait for "1" mins
    And I Switch to "customer" application on "same" devices
    And I click on notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then user is alerted for "PICKUP ALREADY ACCEPTED BY YOU"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  
  @regression
  Scenario: Verify If Non control Driver Completes The Trip First Then He Is Shown With Waiting Screen Till The Control Driver Completes And The Correct Summary Is Shown Thereafter
    When I request "duo" Bungii as a customer in "Kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state  | driver2 state  |
      | Unloading Item | Unloading Item |

    And I Switch to "customer" application on "same" devices
    Given I login as customer "8805368840" and is on Home Page

    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :9999999991 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"
    Then non control driver should see "waiting for other driver" screen

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
	#control driver complete bungii
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "Duo Scheduled" trip
      | driver1 state    |
      | Bungii Completed |
    Then Bungii customer should see "correct details for duo trip" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I click "I DON'T LIKE FREE MONEY" button on the "Promotion" screen

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details for duo trip" on Bungii completed page
    And I click "On To The Next One" button on the "Bungii Completed" screen


  @regression
  Scenario: Rate: Verify If Customer Can Rate Driver For The Duo Trip
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8888888881     | Testcustomertywd_appleRicha Test | Cci12345          |

    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist

    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state    | driver2 state    |
      | Bungii Completed | Bungii Completed |

    When I Switch to "customer" application on "same" devices
    And Bungii customer should see "correct rating detail for duo" on Bungii completed page
    When I select "3" Ratting star for duo "Driver 1"
    And I select "5" Ratting star for duo "Driver 2"
    Then I tap on "OK" on Bungii Complete

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8888888881 |                 |

  @regression
  Scenario:Verify Customer Can Cancel Through SMS To Admin If No driver Accepts And Processing Gets Over - case :Solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas1  | Scheduled    | NEXT_POSSIBLE |
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state

    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    Then I wait for "4" mins
    And I select already scheduled bungii
    Then I wait for "1" mins
    #When I Cancel selected Bungii
    When I tap on "Cancel Bungii" button
    Then correct details should be displayed on the "ADMIN-SMS" app
 #   And I click "TOP BACK" button on "Bungii Details" screen

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

