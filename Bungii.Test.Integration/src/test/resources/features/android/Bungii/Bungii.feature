Feature: Bungii

  Background:
    Given I am logged in as "my" customer
    When I enter "current location in pickup and dropoff fields" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate

    @Android
  Scenario: Bungii_CustomerCancelOnHeadsUp
    When I tap on "Cancel on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii Estimate page with all details"
  @Android
  Scenario: Bungii_CustomerCancelOnSearch
    When I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    When I tap on "Cancel during search" on Bungii estimate
    Then for a Bungii I should see "Bungii Home page with locations"
  @Android
  Scenario: Bungii_DriverRejects
    When I tap on "Cancel" on Bungii estimate
    Then I should see "driver cancelled" on Bungii estimate
  @Android
  Scenario: Bungii_DriverAcceptsAndThenCancels

  @Android
#Customer requests an On Demand Bungii. Driver accepts and completes the Bungii.
#Verify call and SMS functionality on all pages on Customer and Driver app while the Bungii is in progress.
  Scenario: Bungii_Complete_DriverApp
    Given I am logged in as "existing" driver
    When I tap on "Online/Offline button" on Driver Home page
    When I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And Bungii Driver "accepts On Demand Bungii" request
    When I tap "OK on Driver Accepted screen" during a Bungii

    Then for a Bungii I should see "Enroute screen"
    Then Bungii driver should see "Enroute screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then for a Bungii I should see "Arrived screen"
    Then Bungii driver should see "Arrived screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then for a Bungii I should see "Loading Item screen"
    Then Bungii driver should see "Loading Item screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then for a Bungii I should see "Driving to DropOff screen"
    Then Bungii driver should see "Driving to DropOff screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then for a Bungii I should see "Unloading Item screen"
    Then Bungii driver should see "Unloading Item screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And Bungii Driver "completes Bungii"
    And Quit Bungii Driver app