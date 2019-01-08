Feature: Sanity

  Background:

    Given I am logged in as "valid" customer
    When I Switch to "driver" application on "same" devices
    Given I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices

  @Android
  Scenario: Validate That I am able to create on demand bungii. Also Validate that Correct contact number is displayed on Call and SMS Option


    When I enter "current location in pickup and dropoff fields" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    When I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    When I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    When I Switch to "driver" application on "same" devices
    When I tap on "Go Online button" on Driver Home page
#    Then I click on notification for "on demand trip"
    And Bungii Driver "accepts On Demand Bungii" request
    When I Switch to "customer" application on "same" devices
    When I tap "OK on Driver Accepted screen" during a Bungii

    Then for a Bungii I should see "Enroute screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "Enroute screen"
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app

    When I Switch to "driver" application on "same" devices
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app

    When I Switch to "driver" application on "same" devices
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app

    When I Switch to "driver" application on "same" devices
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app

    When I Switch to "driver" application on "same" devices
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver "slides to the next state"

    When I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "completes Bungii"

  @Android_Sanity
  Scenario: Validate That I am able to create on demand bungii.
    When I enter "current location in pickup and dropoff fields" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    When I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    When I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    When I Switch to "driver" application on "same" devices
    When I tap on "Go Online button" on Driver Home page
#    Then I click on notification for "on demand trip"
    And Bungii Driver "accepts On Demand Bungii" request
    Then Bungii driver should see "Enroute screen"

    When I Switch to "customer" application on "same" devices
    When I tap "OK on Driver Accepted screen" during a Bungii
    Then for a Bungii I should see "Enroute screen"

    When I Switch to "driver" application on "same" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"

    When I Switch to "driver" application on "same" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"

    When I Switch to "driver" application on "same" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"
    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "driver" application on "same" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
    When I Switch to "driver" application on "same" devices
    When Bungii Driver "slides to the next state"

    When I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "completes Bungii"


  Scenario: Validate That I am able to create Schedule  bungii. Also Validate that Correct contact number is displayed on Call and SMS Option

    When I enter "current location in pickup and dropoff fields" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    When I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled"
    And I tap on "Request Bungii" on Bungii estimate
    When I tap on "Yes on HeadsUp pop up" on Bungii estimate
    When I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    When I tap on "Available Trips link" on Driver Home page

    And I Select Trip from driver available trip
    When I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When I wait for Minimum duration for Bungii Start Time

    And Bungii Driver "Start Schedule Bungii" request

    When I Switch to "customer" application on "same" devices

    Then for a Bungii I should see "Enroute screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "Enroute screen"
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app

    When I Switch to "driver" application on "same" devices
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app

    When I Switch to "driver" application on "same" devices
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app

    When I Switch to "driver" application on "same" devices
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app

    When I Switch to "driver" application on "same" devices
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a solo driver" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver "slides to the next state"

    When I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "completes Bungii"


  @Sanity_ON
  @Android
  Scenario: Validate That I am able to create Schedule  bungii.
    When I tap on "Menu" > "Account" link
    Then I get customer account details
    When I tap on "Menu" > "Home" link

    When I enter "current location in pickup and dropoff fields" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    When I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled"
    And I tap on "Request Bungii" on Bungii estimate
    When I tap on "Yes on HeadsUp pop up" on Bungii estimate
    When I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    When I tap on "Available Trips link" on Driver Home page

    And I Select Trip from driver available trip
    When I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When I wait for Minimum duration for Bungii Start Time

    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Enroute screen"

    When I Switch to "driver" application on "same" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"

    When I Switch to "driver" application on "same" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"

    When I Switch to "driver" application on "same" devices
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "driver" application on "same" devices


    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"

    When I Switch to "driver" application on "same" devices
    When Bungii Driver "slides to the next state"

    When I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "completes Bungii"





