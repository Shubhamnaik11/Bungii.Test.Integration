@web
Feature: Admin_DriverDetails

  Background:
    Given I am logged in as Admin
    When I click on "Drivers" Menu
    Then I should be directed to "Drivers Page"

  @regression
  @failed
  Scenario: Verify Driver Trip List Status Updation for Solo Scheduled Bungii
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
    Then The Driver Trip List page should display the trip in "Driver(s) Accepted" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Enroute |
    Then The Driver Trip List page should display the trip in "Trip Started" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Arrived |
    Then The Driver Trip List page should display the trip in "Driver(s) Arrived" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Loading Item |
    Then The Driver Trip List page should display the trip in "Loading Items" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Driving To Dropoff |
    Then The Driver Trip List page should display the trip in "Driving To Dropoff" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Unloading Item |
    Then The Driver Trip List page should display the trip in "Unloading Items" state
    When As a driver "Macy Chang" perform below action with respective "Solo Scheduled" trip
      | driver1 state|
      | Bungii Completed |
    Then The Driver Trip List page should display the trip in "Payment Successful" state


  @regression
  Scenario: Verify Driver Search On Drivers Page
    When I enter "drivers" "first name" in the "Drivers search" box
    Then I should see "driver first name" listed on the "Drivers" page

    When I enter "drivers" "last name" in the "Drivers search" box
    Then I should see "driver last name" listed on the "Drivers" page

  @regression
  Scenario: Verify Driver Search On Various Pages
    When I navigate to following pages one by one
      |Page |
      | Dashboard    |
    And I enter "drivers" "first name" in the "Dashboard search" box
    Then I should see "driver first name" listed on the "Dashboard" page

    When I enter "drivers" "last name" in the "Dashboard search" box
    Then I should see "driver last name" listed on the "Dashboard" page

    When I navigate to following pages one by one
      |Page |
      | Trips |
    And I enter "drivers" "first name" in the "Trips search" box
    Then I should see "driver first name" listed on the "Trips" page

    When I enter "drivers" "last name" in the "Trips search" box
    Then I should see "driver last name" listed on the "Trips" page

    @regression
    Scenario: Verify masking of SSN for Existing driver
      When I navigate to following pages one by one
        |Page |
        | Drivers |
      And I search driver "Testdrivertywd_appleks_rathree Test"
      And I click on "Driver Trips" icon
      Then I check if driver SSN is masked

      #need to create data on base for following testcase
  @regression
  Scenario: Verify masking of SSN for New driver
    When I navigate to following pages one by one
      |Page |
      | Drivers |
    And I search driver "Testdrivertywd_appleks_rathree Test"
    And I click on "Driver Trips" icon
    Then I check if driver SSN is masked