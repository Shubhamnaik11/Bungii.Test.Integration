@android
@duo
  @failed
  #These feature will run in kansas geofence
Feature: Duo

  @sanity
  @regression
  Scenario: Validate That I am able to create Schedule duo bungii.
    Given I am logged in as "valid atlanta" customer

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver

    And I connect to "extra1" using "Driver2" instance
    And I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver
    And I Select "Home" from driver App menu
    And I Switch to "customer" application on "ORIGINAL" devices
    And I Switch to "customer" application on "same" devices
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled for duo"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    And I Switch to "driver" application on "same" devices
    And I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu

    And I Switch to "driver" application on "Driver2" devices
    And I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And I wait for Minimum duration for Bungii Start Time
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
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"

    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"
    And Bungii driver should see "Loading Item screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"
    And Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"

    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"

    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    When I Switch to "driver" application on "same" devices
    Then Bungii Driver "completes Bungii"

    When I Switch to "driver" application on "Driver2" devices
    Then Bungii Driver "completes Bungii"

  @regression
  Scenario: Validate That I am able to create Schedule duo bungii. Verify Details

    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta   | enroute     | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid atlanta" customer

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver

    And I connect to "extra1" using "Driver2" instance
    And I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver

    When I Switch to "driver" application on "ORIGINAL" devices
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
    And Bungii Driver taps "SMS for a driver" during a Bungii
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
    And Bungii Driver "slides to the next state"
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
    And Bungii Driver "slides to the next state"
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
    And Bungii Driver "slides to the next state"
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
    And Bungii Driver "slides to the next state"
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
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "completes Bungii"

    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "completes Bungii"

  @newScenario
    Scenario: long stack : Check status of current and stack pickup, Verify stack request alert message and decked request
      Given I am on customer Log in page
      When I am logged in as "valid" customer
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "valid" driver
      And I tap on "Go Online button" on Driver Home page

      And I connect to "extra1" using "customer2" instance
      And I Open "customer" application on "same" devices
      And I am logged in as "valid customer 2" customer

      And I Switch to "customer" application on "ORIGINAL" devices
      And I enter "kansas short pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add "1" photos to the Bungii
      And I add loading/unloading time of "30 mins"
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate

      And I Open "driver" application on "same" devices
      And Bungii Driver "accepts On Demand Bungii" request
      Then Bungii driver should see "Enroute screen"

      When I Switch to "customer" application on "same" devices
      And I tap "OK on Driver Accepted screen" during a Bungii
      Then for a Bungii I should see "Enroute screen"

      When I Switch to "customer" application on "customer2" devices
      And I enter "kansas short pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add "1" photos to the Bungii
      And I add loading/unloading time of "30 mins"
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I Open "driver" application on "ORIGINAL" devices
      Then Bungii Driver "verify stack message" request
      And Bungii Driver "accepts stack message" request
      And I accept Alert message for "Alert: Display Stack trip after current trip"
      And stack trip information should be displayed on deck

      When  I switch to "customer2" instance
      Then for a Bungii I should see "BUNGII ACCEPTED for Stack screen"
      When I tap "OK on Driver Accepted screen" during a Bungii
      Then for a Bungii I should see "Stack accepted screen"


      When I Switch to "driver" application on "ORIGINAL" devices
      Then Bungii driver should see "Enroute screen"
      When Bungii Driver "slides to the next state"
      Then Bungii driver should see "Arrived screen"
      And stack trip information should be displayed on deck

      When I Switch to "customer" application on "same" devices
      Then for a Bungii I should see "Arrived screen"

      When I Switch to "driver" application on "same" devices
      And Bungii Driver "slides to the next state"
      Then Bungii driver should see "Loading Item screen"
      And stack trip information should be displayed on deck

      When I Switch to "customer" application on "same" devices
      Then for a Bungii I should see "Loading Item screen"

      When I Switch to "driver" application on "same" devices
      And Bungii Driver "slides to the next state"
      Then Bungii driver should see "Driving to DropOff screen"
      And stack trip information should be displayed on deck

      When I Switch to "customer" application on "same" devices
      Then for a Bungii I should see "Driving to DropOff screen"

      When I Switch to "driver" application on "same" devices
      And Bungii Driver "slides to the next state"
      Then Bungii driver should see "Unloading Item screen"
      And stack trip information should be displayed on deck

      When I Switch to "customer" application on "same" devices
      Then for a Bungii I should see "Unloading Item screen"
      When I Switch to "driver" application on "same" devices
      And Bungii Driver "slides to the next state"

      And I Switch to "customer" application on "same" devices
      And I tap on "OK on complete" on Bungii estimate
      And I tap on "No free money" on Bungii estimate
    #Complete second trip
      And I Switch to "driver" application on "same" devices
      Then Bungii Driver "completes Bungii"

      When I Switch to "customer" application on "customer2" devices
      Then for a Bungii I should see "Enroute screen"

      And I Switch to "driver" application on "ORIGINAL" devices
      And Bungii Driver "slides to the next state"
      Then Bungii driver should see "Arrived screen"

      When I Switch to "customer" application on "customer2" devices
      Then for a Bungii I should see "Arrived screen"

      When I Switch to "driver" application on "ORIGINAL" devices
      And Bungii Driver "slides to the next state"
      Then Bungii driver should see "Loading Item screen"

      When I Switch to "customer" application on "customer2" devices
      Then for a Bungii I should see "Loading Item screen"

      When I Switch to "driver" application on "ORIGINAL" devices
      And Bungii Driver "slides to the next state"
      Then Bungii driver should see "Driving to DropOff screen"
      When I Switch to "customer" application on "customer2" devices
      Then for a Bungii I should see "Driving to DropOff screen"

      When I Switch to "driver" application on "ORIGINAL" devices
      And Bungii Driver "slides to the next state"
      Then Bungii driver should see "Unloading Item screen"

      When I Switch to "customer" application on "customer2" devices
      Then for a Bungii I should see "Unloading Item screen"
      When I Switch to "driver" application on "ORIGINAL" devices
      And Bungii Driver "slides to the next state"

      And I Switch to "customer" application on "customer2" devices
      And I tap on "OK on complete" on Bungii estimate
      And I tap on "No free money" on Bungii estimate
      And I Switch to "driver" application on "ORIGINAL" devices
      Then Bungii Driver "completes Bungii"

  @newScenario1
  Scenario: long stack : Check status of current and stack pickup, Verify stack request alert message and decked request.Base pickup is scheduled trip
    And I Switch to "driver" application on "same" devices
    Then try to finish time should be correctly displayed for shot stack trip
    Then correct message should be displayed after clicking info button
    Given I am on customer Log in page
    When I am logged in as "valid" customer
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I tap on "Go Online button" on Driver Home page

    And I connect to "extra1" using "customer2" instance
    And I Open "customer" application on "same" devices
    And I am logged in as "valid customer 2" customer

    And I Switch to "customer" application on "ORIGINAL" devices
    And I enter "kansas short pickup and dropoff locations" on Bungii estimate
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

    When I Switch to "customer" application on "customer2" devices
    And I enter "kansas short pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    And I Open "driver" application on "ORIGINAL" devices
    And Bungii Driver "accepts stack message" request
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    Then try to finish time should be correctly displayed
    When  I switch to "customer2" instance
    Then for a Bungii I should see "BUNGII ACCEPTED for Stack screen"
    And I tap "OK on Driver Accepted screen" during a Bungii
    And for a Bungii I should see "Stack accepted screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii driver should see "Enroute screen"
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"
    And stack trip information should be displayed on deck

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"
    And stack trip information should be displayed on deck

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Loading Item screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"
    And stack trip information should be displayed on deck

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"
    And stack trip information should be displayed on deck

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    #Complete second trip
    And I Switch to "driver" application on "same" devices
    Then Bungii Driver "completes Bungii"

    When I Switch to "customer" application on "customer2" devices
    Then for a Bungii I should see "Enroute screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "customer2" devices
    Then for a Bungii I should see "Arrived screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "customer2" devices
    Then for a Bungii I should see "Loading Item screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"
    When I Switch to "customer" application on "customer2" devices
    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "customer2" devices
    Then for a Bungii I should see "Unloading Item screen"
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "customer2" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii Driver "completes Bungii"


  @newScenario
  Scenario: Short stack : Check status of current and stack pickup ,Verify stack request alert message and decked request
    Given I am on customer Log in page
    When I am logged in as "valid" customer
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I tap on "Go Online button" on Driver Home page

    And I connect to "extra1" using "customer2" instance
    And I Open "customer" application on "same" devices
    And I am logged in as "valid customer 2" customer

    And I Switch to "customer" application on "ORIGINAL" devices
    And I enter "kansas short pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    And I Open "driver" application on "same" devices
    And Bungii Driver "accepts On Demand Bungii" request
    Then Bungii driver should see "Enroute screen"

    When I Switch to "customer" application on "same" devices
    And I tap "OK on Driver Accepted screen" during a Bungii
    Then for a Bungii I should see "Enroute screen"

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "customer" application on "customer2" devices
    And I enter "kansas short pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    And I Open "driver" application on "ORIGINAL" devices
    Then Bungii Driver "verify stack message" request
    And Bungii Driver "accepts stack message" request
    And I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck

    When  I switch to "customer2" instance
    Then for a Bungii I should see "BUNGII ACCEPTED for Stack screen"
    When I tap "OK on Driver Accepted screen" during a Bungii
    Then for a Bungii I should see "Stack accepted screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"
    And stack trip information should be displayed on deck

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    #Complete second trip
    And I Switch to "driver" application on "same" devices
    Then Bungii Driver "completes Bungii"

    When I Switch to "customer" application on "customer2" devices
    Then for a Bungii I should see "Enroute screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Arrived screen"

    When I Switch to "customer" application on "customer2" devices
    Then for a Bungii I should see "Arrived screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Loading Item screen"

    When I Switch to "customer" application on "customer2" devices
    Then for a Bungii I should see "Loading Item screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Driving to DropOff screen"
    When I Switch to "customer" application on "customer2" devices
    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "customer2" devices
    Then for a Bungii I should see "Unloading Item screen"
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "customer2" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii Driver "completes Bungii"