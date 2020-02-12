@web
Feature: Driver_ViewDetails

  Background:
    Given I navigate to "Bungii Driver URL"
    When I click "LOG IN link" on driver portal
    Then I should be directed to "LOG IN tab" on Driver portal

  @sanity
  @regression
  Scenario: Driver_Verify_Dashboard_MyStats_Section
    When I enter driver Phone number as "8888881014" and valid password
    And I click "LOG IN button" on driver portal
    When I click on calendar to select date range
    Then I can select date range for one year
    And The 'My Stats' section should be shown on the Dashboard page
    And Below stats are displayed correctly
    |Statistics|
    |Total Trips|
    |Trips / Month|
    |Total Earnings|
    |Earnings / Month|
    |Total Earnings per year|
    |Total Tips             |
    |My Rating              |
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8390409146 | Shanti Ramen|
    And As a driver "Ethan Edison" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Accepted |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
      | Bungii Completed |
    Then The My Stats section should be updated