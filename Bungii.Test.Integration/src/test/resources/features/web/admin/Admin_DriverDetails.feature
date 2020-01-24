@web
Feature: Admin_DriverDetails

  Background:
    Given I am logged in as Admin
    When I click on "Drivers" Menu
    Then I should be directed to "Drivers Page"

  @sanity
  @regression
  Scenario: Admin_ViewDriversTripList
    When I search driver "Macy Chang"
    And I click on "Driver Trips" icon
    Then The "Trip List" page should be displayed
    And List of trips completed by the driver should be displayed on the Trip List Page
    When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 7770081848 | Stephen North|
    And As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Accepted |
    Then The Trip List page should display the trip in "Driver(s) Accepted" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Enroute |
    Then The Trip List page should display the trip in "Trip Started" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Arrived |
    Then The Trip List page should display the trip in "Driver(s) Arrived" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Loading Item |
    Then The Trip List page should display the trip in "Loading Items" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Driving To Dropoff |
    Then The Trip List page should display the trip in "Driving To Dropoff" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Unloading Item |
    Then The Trip List page should display the trip in "Unloading Items" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Bungii Completed |
    Then The Trip List page should display the trip in "Payment Successful" state
