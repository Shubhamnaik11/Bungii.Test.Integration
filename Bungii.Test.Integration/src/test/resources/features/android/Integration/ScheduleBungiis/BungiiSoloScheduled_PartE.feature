@android
@SoloScheduled
@bungii
  @Solo
Feature: SoloScheduled Part E
  #Move inconcluive cases of Solo CSsheduled PartA and PartE

   # Customer 8805368840 Testcustomertywd_appleRicha Test
 # With 8805368840 - 15 cases
  Background:
	

  #@regression
@ready
@r
Scenario: Pushnotification : Verify Driver Receives Alert Stating That The Trip Has Already Been Accepted By Him If He Receives Request Notification After Accepting The Trip From Available Trips
And I Switch to "driver" application on "same" devices
And I am on the LOG IN page on driver app
And I enter phoneNumber :8888881019 and  Password :Cci12345
And I click "Log In" button on Log In screen on driver app
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

Given I Switch to "customer" application on "same" devices
Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
| Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
| NEXT_POSSIBLE | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |
Then I wait for "2" mins
And I Switch to "driver" application on "same" devices
And I Select "AVAILABLE BUNGIIS" from driver App menu
And I Select Trip from available trip
When I click "ACCEPT" button on Bungii Request screen
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
Scenario:Verify Customer Can Cancel Through SMS To Admin If No driver Accepts And Processing Gets Over - case :Solo
Given that solo schedule bungii is in progress
| geofence | Bungii State | Bungii Time   |
| kansas1  | Scheduled    | NEXT_POSSIBLE |
And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state

When I Switch to "customer" application on "same" devices
Given I am on customer Log in page
Then I wait for "2" mins
And I am logged in as "valid kansas" customer
Then I wait for "2" mins
And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
And I close "Tutorial" if exist
And I tap on "Menu" > "MY BUNGIIS" link
Then I wait for "4" mins
And I select already scheduled bungii
Then I wait for "2" mins
    #When I Cancel selected Bungii
When I tap on "Cancel Bungii" button
Then correct details should be displayed on the "ADMIN-SMS" app
And I click on device "Back" button
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

 