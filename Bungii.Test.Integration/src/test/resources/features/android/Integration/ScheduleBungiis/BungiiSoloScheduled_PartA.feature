@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled

  Background:
  
 #######################################################################################
  @regression
  Scenario: Verify Minimum Scheduled Time For Solo Trip in San Francisco Geofence
    Given I am on customer Log in page
    When I am logged in as "New" customer
    And I enter "San Francisco pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then I should see the minimum scheduled time for Solo Bungii displayed on the Estimate page
  
  @regression
    #stable
  Scenario: Verify Customer Can Cancel Through SMS To Admin If Only One Driver Accepts And Processing Gets Over
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8888888881     | Testcustomertywd_appleRicha Test | Cci12345          |
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "Duo Scheduled" trip
      | driver1 state |
      | Accepted      |
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
  
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
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
  @regression
  Scenario: Verify Customer Can Cancel Through SMS To Admin If Required Number Of Drivers Have Accepted The Trip  - case : duo
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     | Customer        | Driver1         | Driver2         |
      | kansas   | Accepted     | 0.75 hour ahead | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
  
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    Then I wait for "2" mins
    And I select 1st trip from scheduled bungii
    
    #And I select already scheduled bungii
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

  @regression
  Scenario:Verify When Bungii Is Not Started Driver Can Cancel Scheduled Bungii From The App :solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas1  | Accepted     | NEXT_POSSIBLE |
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "kansas driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When Bungii Driver "cancels Bungii request"
    Then user is alerted for "FOR EMERGENCY CONTACT SUPPORT LINE"
    And correct details should be displayed on the "SMS FOR CANCEL INCASE OF EMERGENCEY" app
    
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  @regression
  Scenario:Verify When Bungii Is Not Started Driver Can Cancel Scheduled Bungii From The App :duo
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | kansas   | Accepted     | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When Bungii Driver "cancels Bungii request"
    Then user is alerted for "FOR EMERGENCY CONTACT SUPPORT LINE"
    And correct details should be displayed on the "SMS FOR CANCEL INCASE OF EMERGENCEY" app
    
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
    
  
  @regression
  Scenario: Verify Customer Can Cancel Through SMS To Admin If No driver Accepts And Processing Gets Over :Duo
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8888888881     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
  
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "MY BUNGIIS" link
    Then I wait for "2" mins
    And I select already scheduled bungii
   # When I Cancel selected Bungii
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

  @regression
  Scenario: Verify Control Driver Can Cancel Duo Bungii From The App In The First Two States Of Started Bungii :enroute
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | kansas   | enroute      | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas 2" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then for a Bungii I should see "Enroute screen"

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    Then Bungii driver should see "Enroute screen"

    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    And I Select "HOME" from driver App menu
    Then Bungii driver should see "Home screen"

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


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
