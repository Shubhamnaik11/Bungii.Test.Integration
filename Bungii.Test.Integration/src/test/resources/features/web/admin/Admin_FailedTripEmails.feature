@web
Feature: Admin_FailedTripEmails

  Background:
    Given I am logged in as TestAdmin

  @regression
  @email
  @failed
  Scenario: Verify Failed Trip Email - Ondemand Trip - No driver accepts
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999361 | Testcustomertywd_appleWashF Shah|
    And I note the Pickupref of trip
    When I ensure no driver accepts the trip
    Then Admin receives "Failed Ondemand Trips" trip email for "No Driver(s) Accepted" status

  @regression
  @email
  @failed
  Scenario: Verify Failed Trip Email - Ondemand Trip Cancelled By Customer
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999361 | Testcustomertywd_appleWashF Shah|
    And I note the Pickupref of trip
    When I cancel bungii as a customer "Testcustomertywd_appleWashF Shah" with phone number "9999999361"
    Then Admin receives "Failed Ondemand Trips" trip email for "Customer Cancelled" status
    And Partner firm should not receive "Bungii Delivery Pickup Canceled" email