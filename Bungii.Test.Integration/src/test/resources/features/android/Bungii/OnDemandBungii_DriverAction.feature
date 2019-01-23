@Android
@DONE
Feature: OnDemandBungii_DriverActions
Scenarios where customer requests a Bungii and driver accepts/rejects and cancels the Bungii.

  Background:
    Given I am logged in as "valid" customer
    When I Switch to "driver" application on "same" devices
    Given I am logged in as "valid" driver
    When I tap on "Go Online button" on Driver Home page
    When I Switch to "customer" application on "same" devices

    When I enter "current location in pickup and dropoff fields" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "2" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    When I tap on "Yes on HeadsUp pop up" on Bungii estimate
    When I Switch to "driver" application on "same" devices
  @regression
  Scenario: OnDemand_DriverRejectsBungiiRequest
    When Bungii Driver "rejects On Demand Bungii" request
    Then Bungii driver should see "Home screen"
    When I Switch to "customer" application on "same" devices
    When I tap on "Cancel during search" on Bungii estimate
    And Quit Bungii Driver app
  @regression
  Scenario: OnDemand_DriverCancelBungii_EnrouteState
    When Bungii Driver "accepts On Demand Bungii" request
    When I Switch to "customer" application on "same" devices
    And I tap "OK on Driver Accepted screen" during a Bungii
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "cancels Bungii"

  @regression
  Scenario: OnDemand_DriverCancelBungii_ArrivedState
    When Bungii Driver "accepts On Demand Bungii" request
    When I Switch to "customer" application on "same" devices
    And I tap "OK on Driver Accepted screen" during a Bungii

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "cancels Bungii"