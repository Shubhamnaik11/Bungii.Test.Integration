@web
@partnermanagement
Feature: Partner Management login

  Background:
#    Given  I navigate to "Partner Management"

  @ready
  Scenario Outline: To verify the invalid/valid user credentials cannot/can access directly to Partner Management Portal
    And I add credentials of "<User>"
    And I click on the "Login" Button
    Then The "<Text>" "text" should be displayed

    Examples:
      |  User          | Text                |
      | Invalid user   | Invalid credentials |
      | Valid user     | Dashboard           |


  @ready
  Scenario:To verify details on Partners listing page
    And I add credentials of "Valid user"
    And I click on the "Login" Button
    Then Only "Multiple partners locations" should be displayed
    #To verify Bungii name and logo listed on the Partner listing page of Partner Management portal
    Then The "Bungii" "Image" should be displayed
    Then The "Bungii Admin" "Username" should be displayed
    Then The "User Profile" "Image" should be displayed
    Then The "Logout" "Button" should be displayed
    Then The "Search" "Textbox" should be displayed

  @testAllan
  Scenario:To verify Logout button is displayed below Username on Partner Listing page
#    And I add credentials of "Valid user"
#    And I click on the "Login" Button
#    Then The "Dashboard" "Header" should be displayed
#    And I click on the "Logout" Button
##    And The "Welcome to Bungii Partner Management" "Header" should be displayed
   Then The "Arrival time" should match
    Then The "Expected time at drop-off" should match
#   Then The "admin edits pickup Address" should match
#   Then The "driver at arrival state" should match
#    When I request Partner Portal "Solo" Trip for "Equip-bid" partner
#      |Geofence| Bungii Time   | Customer Phone | Customer Name |
#      |kansas| NEXT_POSSIBLE | 8877661097 | Testcustomertywd_appleMarkCT LutherCT|
#    And As a driver "Testdrivertywd_appleks_a_drvae Kansas_ae" perform below action with respective "Solo Scheduled" partner portal trip
#      | driver1 state |
#      | Accepted      |
#      | Enroute      |
#      | Arrived      |
#  Then I save the dropoff latitude and longitude of the first delivery -- for stacked delivery
#    When I request "Solo Ondemand" Bungii as a customer in "kansas" geofence
#      | Bungii Time   | Customer Phone | Customer Name |
#      | NEXT_POSSIBLE | 9999999103     | Testcustomertywd_appleNewQC Customer |
#    Then The "stacked bungii" should match
#   Then The "Stacked dropOff range" should match
#    Then The "blah" should match
#    And I wait for 1 minutes
#   Then The "Ondemand bungii" should match
    Then The "Ondemand dropOff range" should match
    
  @ready
  Scenario:To verify Valid user can login to Partner Management portal using Admin Portal
    When I am logged in as Admin
    And I click on the "PARTNERS" link
    And I click on the "Partner Settings" link
    And I refresh the page
    Then The "Dashboard" "Header on partner management" should be displayed