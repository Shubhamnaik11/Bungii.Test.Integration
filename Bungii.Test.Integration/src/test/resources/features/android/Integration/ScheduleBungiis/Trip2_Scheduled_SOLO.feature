@android
@SoloScheduled
@failed
#These feature will run in kansas geofence
Feature: SoloScheduled
  Background:

  @regression
  Scenario: Validate That I am able to create Schedule  bungii. Also Validate that Correct contact number is displayed on Call and SMS Option
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas      | Accepted     | NEXT_POSSIBLE |
    When I Switch to "customer" application on "same" devices
    And I am logged in as "valid" customer
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And I wait for Minimum duration for Bungii Start Time
    And Bungii Driver "Start Schedule Bungii" request
    And I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Enroute screen"

    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for customer

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "Enroute screen"
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for driver

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    And Trip Information should be correctly displayed on "ARRIVED" status screen for customer

    When I Switch to "driver" application on "same" devices
    And Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    And Trip Information should be correctly displayed on "ARRIVED" status screen for driver

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    And Trip Information should be correctly displayed on "LOADING ITEM" status screen for customer

    When I Switch to "driver" application on "same" devices
    And Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    And Trip Information should be correctly displayed on "LOADING ITEM" status screen for driver

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    And Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for customer

    When I Switch to "driver" application on "same" devices
    And Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    And Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for driver

    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
    When I tap "SMS for a solo driver" during a Bungii
    Then correct details should be displayed on "SMS" app
    When I tap "Call for a solo driver" during a Bungii
    Then correct details should be displayed on "Calling" app
    And Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for customer

    When I Switch to "driver" application on "same" devices
    And Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    And Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for driver

    When Bungii Driver "slides to the next state"
    And I Switch to "customer" application on "same" devices
    Then Bungii customer should see "correct details" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And Bungii Driver "completes Bungii"
    And I Select "HOME" from driver App menu


  @sanity
  @regression
  Scenario: Validate That I am able to create Schedule  bungii.
    Given I am logged in as "valid" customer
    When I Switch to "driver" application on "same" devices
    And I am logged in as "valid" driver
    And I Select "HOME" from driver App menu
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "Home" link

    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    And I Switch to "driver" application on "same" devices
    And I tap on "Available Trips link" on Driver Home page

    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And I wait for Minimum duration for Bungii Start Time

    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Enroute screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "same" devices
    Then Bungii Driver "completes Bungii"
    And I Select "HOME" from driver App menu

  @regression
  Scenario: Cancel Bungii from Admin Panel , verify trip is gone from scheduled trip in app

    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas      | Accepted     | NEXT_POSSIBLE |
    When I Switch to "customer" application on "same" devices
    And I am logged in as "valid" customer

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I Cancel Bungii with following details
      | Charge | Comments |
      | 15     | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "SCHEDULED BUNGIIS" link
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @regression
  Scenario: To check that Customer cannot schedule a Bungii at same time as an already scheduled bungii

    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas      | Scheduled     | NEXT_POSSIBLE |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid" customer
    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "OLD BUNGII TIME"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then I tap on "ok on already scheduled bungii message" on Bungii estimate
    When I tap on "back" on Bungii estimate
    And I tap on "Menu" > "SCHEDULED BUNGIIS" link
    And I select already scheduled bungii
    Then I Cancel selected Bungii

  @regression
  Scenario: Customer should able to cancel scheduled bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas      | Scheduled     | NEXT_POSSIBLE |
    When I am logged in as "valid" customer
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "SCHEDULED BUNGIIS" link
    And I select already scheduled bungii
    And I Cancel selected Bungii
    And I tap on "Menu" > "SCHEDULED BUNGIIS" link
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen