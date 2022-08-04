@android
@bungii
@SoloScheduled
#These feature will run in kansas geofence
Feature: SoloScheduled Part B
   # All Stable 4 Cases
  
  Background:
  
  @regression
  Scenario: Verify Trip limit (150 miles) For Delivery
    Given I login as customer "8805368840" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "kansas pickup and dropoff locations less than 150 miles" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened

    And I click on device "Back" button
    And I enter "kansas pickup and dropoff locations equal to 150 miles" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened

    And I click on device "Back" button
    And I enter the "kansas pickup and dropoff locations greater than 150 miles" on the Bungii estimate
    Then Alert message with Oops! We focus on local deliveries within 150 miles of pickup. It looks like this trip is a little outside our scope. text should be displayed


  @regression
    #stable
  Scenario: Verify Customer Cannot Schedule Bungii That Overlaps With Another Scheduled Trip TELET Time :Solo
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I get TELET time of of the current trip
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "SOLO SCHEDULED" trip
      | driver1 state |
      | Accepted      |
    
    Given I login as customer "8805368840" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "kansas pickup and dropoff locations less than 150 miles" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened
    When I confirm trip with following details
      | Day | Trip Type | Time                |
      | 0   | SOLO      | <TIME WITHIN TELET> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then Alert message with Hmm, it looks like you already have a Bungii scheduled. At this time, our system only allows one Bungii at a time. text should be displayed
  
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |
    
  @regression
   #stable
    #new scenario added
  Scenario: Verify Customer can Schedule Bungii for a time that doesnt overlap With Another Scheduled Trip TELET Time :Solo
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I get TELET time of of the current trip
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "SOLO SCHEDULED" trip
      | driver1 state |
      | Accepted      |
  
    Given I login as customer "8805368840" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "kansas pickup and dropoff locations less than 150 miles" on Bungii estimate
  
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened

    When I confirm trip with following details
      | Day | Trip Type | Time          |
      | 0   | SOLO      | <AFTER TELET> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @testAllan
  Scenario:To verify whether service level instructions are displayed on the admin portal live trips for BEST BUY Minnesota Partner Portal
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid baltimore" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661035 | Testcustomertywd_appleMarkAJ LutherAJ|
    And I wait for 1 minutes
    And I click on "View Request" button
    Then I should see service level information displayed
    And I click on "Accept" button
    And I Select Trip from driver scheduled trip
    Then The service level information should be displayed
    And I start selected Bungii
    Then Bungii driver should see "General Instructions"
    And I slide update button on "EN ROUTE" Screen
    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAJ LutherAJ" the customer
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then The delivery details on "Live" deliveries should have proper pickup "Store" location and service level instructions displayed