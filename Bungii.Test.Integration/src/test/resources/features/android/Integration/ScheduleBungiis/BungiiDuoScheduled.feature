@android
@duo
Feature: Duo

  @sanity
  @regression
  Scenario: Validate That I am able to create Schedule duo bungii.
    Given I am logged in as "valid" customer

    When I Switch to "driver" application on "same" devices
    Given I am logged in as "valid" driver

    When I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    Given I am logged in as "valid driver 2" driver
    And I Select "Home" from driver App menu
    When I Switch to "customer" application on "ORIGINAL" devices
    When I Switch to "customer" application on "same" devices
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
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
  @regression1
  @regression
  Scenario: Validate That I am able to create Schedule duo bungii. Verify Details

    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | kansas   | enroute     | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
    When I Switch to "customer" application on "same" devices
    Given I am logged in as "valid" customer

    When I Switch to "driver" application on "same" devices
    Given I am logged in as "valid" driver

    When I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    Given I am logged in as "valid driver 2" driver

#    And I Select "SCHEDULED BUNGIIS" from driver App menu
#    And I Select Trip from driver scheduled trip
 #   When I wait for Minimum duration for Bungii Start Time
 #   And Bungii Driver "Start Schedule Bungii" request
 #   Then Bungii driver should see "Enroute screen"
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver

    When I Switch to "driver" application on "ORIGINAL" devices
#    And I Select "SCHEDULED BUNGIIS" from driver App menu
 #   And I Select Trip from driver scheduled trip
#    And Bungii Driver "Start Schedule Bungii" request
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for customer
    When I tap "SMS for driver 1" during a Bungii
    Then correct details should be displayed on "Driver 1 SMS" app
    When I tap "Call for driver 1" during a Bungii
    Then correct details should be displayed on "Driver 1 Calling" app
    When I tap "SMS for driver 2" during a Bungii
    Then correct details should be displayed on "Driver 2 SMS" app
    When I tap "Call for driver 2" during a Bungii
    Then correct details should be displayed on "Driver 2 Calling" app

    When I Switch to "driver" application on "Driver2" devices


    When Bungii Driver taps "SMS for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 2 SMS" app
    When Bungii Driver taps "Call for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 2 Calling" app
    When Bungii Driver taps "Contact support for driver" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    When Bungii Driver "slides to the next state"
 #   Then Bungii driver should see "Arrived screen"
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for driver

    When Bungii Driver taps "SMS for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 2 SMS" app
    When Bungii Driver taps "Call for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 2 Calling" app
    When Bungii Driver taps "Contact support for driver" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"

    When I Switch to "driver" application on "ORIGINAL" devices
    When Bungii Driver taps "SMS for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 1 SMS" app
    When Bungii Driver taps "Call for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 1 Calling" app
    When Bungii Driver taps "Contact support for driver" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"
    When Bungii Driver "slides to the next state"
  #  Then Bungii driver should see "Arrived screen"
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for driver

    When Bungii Driver taps "SMS for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 1 SMS" app
    When Bungii Driver taps "Call for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 1 Calling" app
    When Bungii Driver taps "Contact support for driver" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"

    When I Switch to "customer" application on "same" devices
 #   Then for a Bungii I should see "Arrived screen"
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for customer
    When I tap "SMS for driver 1" during a Bungii
    Then correct details should be displayed on "Driver 1 SMS" app
    When I tap "Call for driver 1" during a Bungii
    Then correct details should be displayed on "Driver 1 Calling" app
    When I tap "SMS for driver 2" during a Bungii
    Then correct details should be displayed on "Driver 2 SMS" app
    When I tap "Call for driver 2" during a Bungii
    Then correct details should be displayed on "Driver 2 Calling" app

    When I Switch to "driver" application on "Driver2" devices
    When Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"
  #  Then Bungii driver should see "Loading Item screen"
    Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for driver
    When Bungii Driver taps "SMS for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 2 SMS" app
    When Bungii Driver taps "Call for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 2 Calling" app
    When Bungii Driver taps "Contact support for driver" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"

    When I Switch to "driver" application on "ORIGINAL" devices
    When Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"
 #   Then Bungii driver should see "Loading Item screen"
    Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for driver
    When Bungii Driver taps "SMS for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 1 SMS" app
    When Bungii Driver taps "Call for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 1 Calling" app
    When Bungii Driver taps "Contact support for driver" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"

    When I Switch to "customer" application on "same" devices
  #  Then for a Bungii I should see "Loading Item screen"
    Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for customer
    When I tap "SMS for driver 1" during a Bungii
    Then correct details should be displayed on "Driver 1 SMS" app
    When I tap "Call for driver 1" during a Bungii
    Then correct details should be displayed on "Driver 1 Calling" app
    When I tap "SMS for driver 2" during a Bungii
    Then correct details should be displayed on "Driver 2 SMS" app
    When I tap "Call for driver 2" during a Bungii
    Then correct details should be displayed on "Driver 2 Calling" app

    When I Switch to "driver" application on "Driver2" devices
    When Bungii Driver "slides to the next state"
 #   Then Bungii driver should see "Driving to DropOff screen"
    Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for driver
    When Bungii Driver taps "SMS for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 2 SMS" app
    When Bungii Driver taps "Call for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 2 Calling" app
    When Bungii Driver taps "Contact support for driver" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"

    When I Switch to "driver" application on "ORIGINAL" devices
    When Bungii Driver "slides to the next state"
 #   Then Bungii driver should see "Driving to DropOff screen"
    Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for driver
    When Bungii Driver taps "SMS for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 1 SMS" app
    When Bungii Driver taps "Call for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 1 Calling" app
    When Bungii Driver taps "Contact support for driver" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"

    When I Switch to "customer" application on "same" devices
 #   Then for a Bungii I should see "Driving to DropOff screen"
    Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for customer
    When I tap "SMS for driver 1" during a Bungii
    Then correct details should be displayed on "Driver 1 SMS" app
    When I tap "Call for driver 1" during a Bungii
    Then correct details should be displayed on "Driver 1 Calling" app
    When I tap "SMS for driver 2" during a Bungii
    Then correct details should be displayed on "Driver 2 SMS" app
    When I tap "Call for driver 2" during a Bungii
    Then correct details should be displayed on "Driver 2 Calling" app

    When I Switch to "driver" application on "Driver2" devices
    When Bungii Driver "slides to the next state"
 #   Then Bungii driver should see "Unloading Item screen"
    Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for driver
    When Bungii Driver taps "SMS for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 2 SMS" app
    When Bungii Driver taps "Call for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 2 Calling" app
    When Bungii Driver taps "Contact support for driver" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"

    When I Switch to "driver" application on "ORIGINAL" devices
    When Bungii Driver "slides to the next state"
 #   Then Bungii driver should see "Unloading Item screen"
    Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for driver
    When Bungii Driver taps "SMS for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 1 SMS" app
    When Bungii Driver taps "Call for a driver" during a Bungii
    Then correct details should be displayed to driver on "Driver 1 Calling" app
    When Bungii Driver taps "Contact support for driver" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "SMS for a customer" during a Bungii
    Then correct details should be displayed to driver on "SMS" app
    When Bungii Driver taps "Call for a customer" during a Bungii
    Then correct details should be displayed to driver on "Calling" app
    When Bungii Driver taps "Contact support" during a Bungii
    Then correct details should be displayed to driver on "Support-SMS" app
    When Bungii Driver taps "View items" during a Bungii
    Then Bungii driver should see "Pickup Item"

    When I Switch to "customer" application on "same" devices
 #   Then for a Bungii I should see "Unloading Item screen"
    Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for customer
    When I tap "SMS for driver 1" during a Bungii
    Then correct details should be displayed on "Driver 1 SMS" app
    When I tap "Call for driver 1" during a Bungii
    Then correct details should be displayed on "Driver 1 Calling" app

    When I tap "SMS for driver 2" during a Bungii
    Then correct details should be displayed on "Driver 2 SMS" app
    When I tap "Call for driver 2" during a Bungii
    Then correct details should be displayed on "Driver 2 Calling" app

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