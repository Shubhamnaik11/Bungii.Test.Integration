@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled
 # All Stable 2 Cases
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
  Scenario:Verify When Bungii Is Not Started Driver Can Cancel Scheduled Bungii From The App :solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas1  | Accepted     | NEXT_POSSIBLE |
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "kansas driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    And I Switch to "driver" application on "same" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When Bungii Driver "cancels Bungii request"
    Then user is alerted for "FOR EMERGENCY CONTACT SUPPORT LINE"
    And correct details should be displayed on the "SMS FOR CANCEL INCASE OF EMERGENCEY" app
    
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

#  @testAllan
  Scenario: test1
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I get TELET time of of the current trip
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "SOLO SCHEDULED" trip
      | driver1 state |
      | Accepted      |
    And I wait for 1 minutes
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "SOLO SCHEDULED" trip
      | driver1 state |
      | Enroute      |
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_rathree Test" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then Bungii driver should see "Enroute screen"



   And I Switch to "customer" application on "same" devices
    Given I login as customer "9999999103" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "kansas very short trip location" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    When I confirm trip with following detail
      | Day | Trip Type |
      | 1   | SOLO      |
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

   And I Switch to "driver" application on "same" devices
   And I wait for 1 minutes
    Then I should a popup "New Bungii Request" displayed
    And I click on "View Request" button
    And I wait for 1 minutes
    Then I should see the trip details
    And I click on "Accept" button
    Then Bungii driver should see "Enroute screen"
    And I slide update button on "EN ROUTE" Screen

