@android

Feature: Duo

  Background:

  #  Given I am logged in as "valid" customer
  #  When I tap on "Menu" > "Account" link
  #  Then I get customer account details
  #  When I tap on "Menu" > "Home" link

  #  When I Switch to "driver" application on "same" devices
  #  Given I am logged in as "valid" driver
  #  When I connect to "extra1" using "Driver2" instance
  #  Given I am logged in as "valid driver 2" driver
  #  When I Switch to "customer" application on "ORIGINAL" devices

@Duo
  @sanity
  @regression
  Scenario: Validate That I am able to create Schedule duo bungii.

    When I enter "current location in pickup and dropoff fields" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    When I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled for duo"
    And I tap on "Request Bungii" on Bungii estimate
    When I tap on "Yes on HeadsUp pop up" on Bungii estimate
    When I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    When I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    When I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu

    When I Switch to "driver" application on "Driver2" devices
    When I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    When I tap on "ACCEPT" on driver Trip details Page

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When I wait for Minimum duration for Bungii Start Time
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Enroute screen"

    When I Switch to "driver" application on "Driver2" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"

    When I Switch to "driver" application on "Driver2" devices
    When Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    When Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"

    When I Switch to "driver" application on "Driver2" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "driver" application on "Driver2" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"

    When I Switch to "driver" application on "Driver2" devices
    When Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "driver" application on "ORIGINAL" devices
    When Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "completes Bungii"

    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "completes Bungii"


