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
    
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When Bungii Driver "cancels Bungii request"
    Then user is alerted for "FOR EMERGENCY CONTACT SUPPORT LINE"
    And correct details should be displayed on the "SMS FOR CANCEL INCASE OF EMERGENCEY" app
    
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |