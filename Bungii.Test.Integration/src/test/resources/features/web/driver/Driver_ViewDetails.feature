@web
Feature: Driver_ViewDetails

  Background:
    Given I navigate to "Bungii Driver URL"
    When I click "LOG IN link" on driver portal
    Then I should be directed to "LOG IN tab" on Driver portal

  @regression
    #CORE-4163 changes has been added
    #stable
  Scenario: Verify My Stats Section Populated Data on Driver Dashboard
    When I enter driver Phone number as "8888881014" and valid password
    And I click "LOG IN button" on driver portal
    Then I should be directed to "Dashboard" on Driver portal
    And I note trip count for driver
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
    And I wait for data to synch
    Then The My Stats section should be updated
    When I open datepicker and hit enter key
    Then I should see delivery details displayed to the driver