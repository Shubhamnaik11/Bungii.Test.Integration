@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled Part F

  Background:
    
  @regression
  Scenario: Re-searched trip request should show Urgent Notification text if admin re-searches less than one hour from scheduled trip time or for trip time between 24 hours prior to current time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    When I Switch to "customer" application on "same" devices
    Then I wait for "2" mins
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    
    And I remove current driver and researches Bungii
    When I switch to "ORIGINAL" instance
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    When I Switch to "customer" application on "same" devices
    And Notification for "driver" for "URGENT SCHEDULED PICKUP AVAILABLE" should be displayed
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  @regression
  
  Scenario: Verify Re-searched Trip Request Doesnt Show Urgent Notification Text If Is More Than One Hour From The Scheduled Trip Time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 2 hour ahead |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    When I Switch to "customer" application on "same" devices
    Then I wait for "2" mins
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    
    And I remove current driver and researches Bungii
    When I switch to "ORIGINAL" instance
    And I should not get notification for "driver" for "URGENT SCHEDULED PICKUP AVAILABLE"
    When I Switch to "customer" application on "same" devices
    Then Notification for "driver" for "SCHEDULED PICKUP AVAILABLE" should be displayed
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
    
     #@regression
  @ready
    @ad
  Scenario:Verify Driver Cannot Start Bungii If The Customer Is Currently In An Ongoing Trip :Solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1.25 hour ahead |
    And I wait for "3" mins
    Given that ondemand bungii is in progress
      | geofence | Bungii State | Driver label | Trip Label |
      | same   | Enroute      | driver 2     | 2          |
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I wait for "3" mins
    And I am logged in as "valid" driver
    And I wait for "3" mins
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I wait for "3" mins
  
    When I wait for 1 hour for Bungii Schedule Time
    And I Select Trip from driver scheduled trip
    And I start selected Bungii
    Then I should see "CUSTOMER HAS ONGOING BUNGII" on screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  #@regression
  @ready
  Scenario:Verify Driver Cannot Start Bungii If The Customer Is Currently In An Ongoing Trip - Duo
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  | Customer        | Driver1         | Driver2         |
      | Kansas   | Accepted     | 0.5 hour ahead | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    Given that ondemand bungii is in progress
      | geofence | Bungii State | Driver label | Trip Label |
      | Kansas   | Enroute      | driver 2     | 2          |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 1" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    When I wait for 1 hour for Bungii Schedule Time
    And I Select Trip from driver scheduled trip
    And I start selected Bungii
    Then I should see "CUSTOMER HAS ONGOING BUNGII" on screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
    
     #@regression
  @ready
  Scenario: Verify If TELET Of Re-searched Trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    Then I wait for "1" mins
    
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    
    And I remove current driver and researches Bungii
    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    Then Telet time of research trip should be not be same as previous trips
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  
  @ready
  
  Scenario: Verify If Incoming Scheduled Trip Request TELET (Trip A) Overlaps Start Time Of Previously Scheduled Trip (Trip B) Then Driver Doesnt Receive Notification Or Offline SMS
    
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Accepted     | NEXT_POSSIBLE |
    And I get TELET time of of the current trip
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Switch to "customer" application on "same" devices
    Given I login as customer "9871450107" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    
    And I tap on "Get Estimate button" on Bungii estimate
    And I confirm trip with following details
      | Day | Trip Type | Time                              |
      | 0   | DUO       | <TIME WITHIN TELET OF CUSTOMER 1> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    Then I should not get notification for SCHEDULED PICKUP AVAILABLE
    And I Switch to "driver" application on "same" devices
    And I tap on "Available Trips link" on Driver Home page
    Then I should able to see "zero" available trip
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone      |
      | CUSTOMER1_PHONE | CUSTOMER_PHONE_EXTRA |
    

  #@regression
  @ready
  Scenario: Verify If Control Driver Is Allowed To Complete The Trip And Proper Summary Is Shown
    Given that duo schedule bungii is in progress
      | geofence | Bungii State    | Bungii Time   | Customer        | Driver1         | Driver2         |
      | Kansas   | unloading items | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    
    And I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "kansas driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"
    
    When I Switch to "customer" application on "same" devices
    Then Bungii customer should see "correct details for duo trip" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    
    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details for duo trip" on Bungii completed page
    And I click "On To The Next One" button on the "Bungii Completed" screen
  
 