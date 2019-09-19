@android
@TESTBUNGII
@regression1
Feature: OnDemandBungii_DriverActions
Scenarios where customer requests a Bungii and driver accepts/rejects and cancels the Bungii.

  Background:
    Given I am on customer Log in page
    And I am logged in as "valid" customer
    When I Switch to "driver" application on "same" devices
    And I am logged in as "valid" driver
    When I tap on "Go Online button" on Driver Home page
    And I Switch to "customer" application on "same" devices
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "2" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I Switch to "driver" application on "same" devices

  @regression
  Scenario: OnDemand_DriverRejectsBungiiRequest
    And Bungii Driver "rejects On Demand Bungii" request
    Then Bungii driver should see "Home screen"
    When I Switch to "customer" application on "same" devices
    And I tap on "Cancel during search" on Bungii estimate
    And Quit Bungii Driver app

  @regression
  Scenario: OnDemand_DriverCancelBungii_EnrouteState
    And Bungii Driver "accepts On Demand Bungii" request
    And I Switch to "customer" application on "same" devices
    And I tap "OK on Driver Accepted screen" during a Bungii
    And I Switch to "driver" application on "same" devices
    And Bungii Driver "cancels Bungii"
    And I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened


  @regression
  Scenario: OnDemand_DriverCancelBungii_ArrivedState
    And Bungii Driver "accepts On Demand Bungii" request
    And I Switch to "customer" application on "same" devices
    And I tap "OK on Driver Accepted screen" during a Bungii

    And I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "cancels Bungii"
    And I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened