@android
@duo

  #These feature will run in atlanta geofence

Feature: Duo

  #@regression
  @ready1
  @sanity
  Scenario: Verify Scheduling Of Duo Bungii As An Android Customer
  #  Given I have Large image on my device
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
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I select Bungii Time as "next possible scheduled for duo"
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
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
  #  And I wait for Minimum duration for Bungii Start Time
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
    And Bungii customer should see "correct details" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    Then Bungii Driver "completes Bungii"

    When I Switch to "driver" application on "Driver2" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    Then Bungii Driver "completes Bungii"
    And Customer should receive "Your Bungii Receipt" email

  #given i have large image on phone
  #@regression
  @ready1
  Scenario Outline: Verify Customer Amount Calculation For The Scheduled Duo Bungii Having Promocode Applied To It
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
    And I enter "atlanta long pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I add large image photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Promo Code" on Bungii estimate
    And I add "<PROMO CODE>" PromoCode
    And I tap "Add" on Save Money page
    And I tap on "desired Promo Code" on Bungii estimate
    And I get Bungii details on Bungii Estimate
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    And I Switch to "driver" application on "same" devices
    And I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page

    And I Switch to "driver" application on "Driver2" devices
    And I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
  #  And I wait for Minimum duration for Bungii Start Time
    And Bungii Driver "Start Schedule Bungii" request
    And Bungii Driver "slides to the next state"

    When I Switch to "driver" application on "ORIGINAL" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    And Bungii Driver "slides to the next state"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Arrived screen"

    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"
#    And Bungii driver should see "Loading Item screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"
 #   And Bungii driver should see "Loading Item screen"

 #   When I Switch to "customer" application on "same" devices
 #   Then for a Bungii I should see "Loading Item screen"

    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
 #   Then Bungii driver should see "Driving to DropOff screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
  #  Then Bungii driver should see "Driving to DropOff screen"

#    When I Switch to "customer" application on "same" devices
#    Then for a Bungii I should see "Driving to DropOff screen"

    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
  #  Then Bungii driver should see "Unloading Item screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
  #  Then Bungii driver should see "Unloading Item screen"

 #   When I Switch to "customer" application on "same" devices
  #  Then for a Bungii I should see "Unloading Item screen"

    When I Switch to "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "customer" application on "same" devices
    And Bungii customer should see "correct details with promo" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    Then Bungii Driver "completes Bungii"

    When I Switch to "driver" application on "Driver2" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    Then Bungii Driver "completes Bungii"
    Then I wait for "1" mins

    And I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "trips" from admin sidebar
    And I select "The Beginning of Time" from search peroid
    And I select trip from trips
    Then On admin trip details page "promo" should be displayed
    Examples:
      | PROMO CODE       |
      | PROMO DOLLAR OFF |
      |PROMO PERCENT OFF|

  #@regression
  @ready1
  Scenario: Verify Schedululing Of Duo Bungii And Verifying Bungii Details

    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta  | enroute      | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
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

  #@regression
  @ready1
  Scenario: Long Stack : Verify Status Of Current Ondemand Bungii And Stacked pickup And Stack Request Alert Message And Decked Request
    Given I am on customer Log in page
    When I am logged in as "valid" customer
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    And I tap on "Go Online button" on Driver Home page

    And I connect to "extra1" using "customer2" instance
    And I Open "customer" application on "same" devices
    And I am logged in as "valid customer 2" customer

    And I Switch to "customer" application on "ORIGINAL" devices
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    And I Open "driver" application on "same" devices
    And Bungii Driver "accepts On Demand Bungii" request
    Then "Enroute screen" page should be opened

    When I Switch to "customer" application on "same" devices
    And I tap "OK on Driver Accepted screen" during a Bungii
    Then "Enroute screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
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
    And try to finish time should be correctly displayed for long stack trip

    When  I switch to "customer2" instance
    Then for a Bungii I should see "BUNGII ACCEPTED for Stack screen"
    When I tap "OK on Driver Accepted screen" during a Bungii
    Then for a Bungii I should see "Stack accepted screen"


    When I Switch to "driver" application on "ORIGINAL" devices
    Then "Enroute screen" page should be opened
    When Bungii Driver "slides to the next state"
    Then "Arrived screen" page should be opened
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then "Arrived screen" page should be opened

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then "Loading Item screen" page should be opened

    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then "Loading Item screen" page should be opened

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then "Driving to DropOff screen" page should be opened
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip


    When I Switch to "customer" application on "same" devices
    Then "Driving to DropOff screen" page should be opened

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then "Unloading Item screen" page should be opened
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then "Unloading Item screen" page should be opened
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    #Complete second trip
    And I Switch to "driver" application on "same" devices
    Then Bungii Driver "completes Bungii"

    When I Switch to "customer" application on "customer2" devices
    Then "Enroute screen" page should be opened

    And I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Arrived screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    Then "Arrived screen" page should be opened

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Loading Item screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    Then "Loading Item screen" page should be opened

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Driving to DropOff screen" page should be opened
    When I Switch to "customer" application on "customer2" devices
    Then "Driving to DropOff screen" page should be opened

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Unloading Item screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    Then "Unloading Item screen" page should be opened
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "customer2" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii Driver "completes Bungii"

  #@regression
  @ready1
  Scenario: Long Stack : Verify Status Of Current Scheduled Pickup And Stacked Pickup And Stack Request Alert Message And Decked Request
    Given I am on customer Log in page
    When I am logged in as "valid" customer
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    And I tap on "Go Online button" on Driver Home page

    And I connect to "extra1" using "customer2" instance
    And I Open "customer" application on "same" devices
    And I am logged in as "valid customer 2" customer

    And I Switch to "customer" application on "ORIGINAL" devices
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    And I Switch to "driver" application on "same" devices
    And I tap on "Available Trips link" on Driver Home page

    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
#    And I wait for Minimum duration for Bungii Start Time

    And Bungii Driver "Start Schedule Bungii" request
    Then "Enroute screen" page should be opened
    When I Switch to "customer" application on "same" devices
    Then "Enroute screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    And I Open "driver" application on "ORIGINAL" devices
    And Bungii Driver "accepts stack message" request
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    Then try to finish time should be correctly displayed for long stack trip
    When  I switch to "customer2" instance
    Then for a Bungii I should see "BUNGII ACCEPTED for Stack screen"
    And I tap "OK on Driver Accepted screen" during a Bungii
    And for a Bungii I should see "Stack accepted screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    Then "Enroute screen" page should be opened
    When Bungii Driver "slides to the next state"
    Then "Arrived screen" page should be opened
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then "Arrived screen" page should be opened

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then "Loading Item screen" page should be opened
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then "Loading Item screen" page should be opened

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then "Driving to DropOff screen" page should be opened
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then "Driving to DropOff screen" page should be opened

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    Then "Unloading Item screen" page should be opened
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then "Unloading Item screen" page should be opened
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    #Complete second trip
    And I Switch to "driver" application on "same" devices
    Then Bungii Driver "completes Bungii"

    When I Switch to "customer" application on "customer2" devices
    Then "Enroute screen" page should be opened

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Arrived screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    Then "Arrived screen" page should be opened

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Loading Item screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    Then "Loading Item screen" page should be opened

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Driving to DropOff screen" page should be opened
    When I Switch to "customer" application on "customer2" devices
    Then "Driving to DropOff screen" page should be opened

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Unloading Item screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    Then "Unloading Item screen" page should be opened
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "customer2" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii Driver "completes Bungii"


  #@regression
@ready1
  Scenario: Short Stack : Verify Status Of Current Ondemand Pickup And Stacked Pickup And Stack Request Alert Message And Decked Request
    Given I am on customer Log in page
    When I am logged in as "valid" customer
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    And I tap on "Go Online button" on Driver Home page

    And I connect to "extra1" using "customer2" instance
    And I Open "customer" application on "same" devices
    And I am logged in as "valid customer 2" customer

    And I Switch to "customer" application on "ORIGINAL" devices
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    And I Open "driver" application on "same" devices
    And Bungii Driver "accepts On Demand Bungii" request
    Then "Enroute screen" page should be opened

    When I Switch to "customer" application on "same" devices
    And I tap "OK on Driver Accepted screen" during a Bungii
    Then "Enroute screen" page should be opened

    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then "Driving to DropOff screen" page should be opened

    When I Switch to "customer" application on "same" devices
    Then "Driving to DropOff screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    And I Open "driver" application on "ORIGINAL" devices
    Then Bungii Driver "verify stack message" request
    And Bungii Driver "accepts stack message" request
    Then I calculate projected driver arrival time
    And I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for short stack trip

    When  I switch to "customer2" instance
    Then for a Bungii I should see "BUNGII ACCEPTED for Stack screen"
    When I tap "OK on Driver Accepted screen" during a Bungii
    Then for a Bungii I should see "Stack accepted screen"

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Unloading Item screen" page should be opened
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for short stack trip

    When I Switch to "customer" application on "same" devices
    Then "Unloading Item screen" page should be opened
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    #Complete second trip
    And I Switch to "driver" application on "same" devices
    Then Bungii Driver "completes Bungii"

    When I Switch to "customer" application on "customer2" devices
    Then "Enroute screen" page should be opened

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Arrived screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    Then "Arrived screen" page should be opened

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Loading Item screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    Then "Loading Item screen" page should be opened

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Driving to DropOff screen" page should be opened
    When I Switch to "customer" application on "customer2" devices
    Then "Driving to DropOff screen" page should be opened

    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then "Unloading Item screen" page should be opened

    When I Switch to "customer" application on "customer2" devices
    Then "Unloading Item screen" page should be opened
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"

    And I Switch to "customer" application on "customer2" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii Driver "completes Bungii"

  #@regression
  @ready1
  Scenario: Verify Driver Can Long Stack Request On Arrived State
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | atlanta  | ARRIVED      |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
        #put driver on background
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "view stack message" request
    Then "correct stack trip details" should be displayed on Bungii request screen
    And I tap on the "ACCEPT" Button on Bungii Request screen
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |

  @regression
  Scenario:Verify Driver Can Get Long Stack Request On Loading Item State And Cancellation
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | atlanta  | LOADING ITEM |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    #switch to customer so that driver app is in background :Click by notification
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
    Then I click on notification for "STACK TRIP"
    Then Bungii Driver "verify stack message" request
    And Bungii Driver "accepts stack message" request
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I enter customers "9871450107" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then for a Bungii I should see "bungii accepted screen"
    When I click "CANCEL BUNGII" on bungii accepted screen
    Then I see "Alert: Bungii cancel confirmation" on bungii accepted screen
    When I click "Dismiss on Alert message" on bungii accepted screen
    Then for a Bungii I should see "bungii accepted screen"
    When I click "CANCEL BUNGII" on bungii accepted screen
    When I click "Cantact Support on Alert message" on bungii accepted screen
    Then correct details should be displayed on "customer support-SMS" app

    When I click "CANCEL BUNGII" on bungii accepted screen
    When I click "CANCEL BUNGII on Alert message" on bungii accepted screen
    Then for a Bungii I should see "Bungii Home page"
    Then I click on notification for "CUSTOMER CANCEL STACK TRIP"
    And stack trip information should not be displayed on deck
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:Verify Driver Can Short Stack Request On Unloading Item State and Cancellation
    Given that ondemand bungii is in progress
      | geofence | Bungii State   |
      | atlanta  | UNLOADING ITEM |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    #switch to customer so that driver app is in background :Click by notification
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
    Then I click on notification for "STACK TRIP"
    Then Bungii Driver "verify stack message" request
    And Bungii Driver "accepts stack message" request
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I enter customers "9871450107" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then for a Bungii I should see "bungii accepted screen"
    When I click "CANCEL BUNGII" on bungii accepted screen
    Then I see "Alert: Bungii cancel confirmation" on bungii accepted screen
    When I click "Dismiss on Alert message" on bungii accepted screen
    Then for a Bungii I should see "bungii accepted screen"
    When I click "CANCEL BUNGII" on bungii accepted screen
    When I click "Cantact Support on Alert message" on bungii accepted screen
    Then correct details should be displayed on "customer support-SMS" app

    When I click "CANCEL BUNGII" on bungii accepted screen
    When I click "CANCEL BUNGII on Alert message" on bungii accepted screen
    Then for a Bungii I should see "Bungii Home page"
    Then I click on notification for "CUSTOMER CANCEL STACK TRIP"
    And stack trip information should not be displayed on deck
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
@demo1
  Scenario:  Verify Customer Notification For Stack Bungii Accepted And Stack Driver Started
    Given that ondemand bungii is in progress
      | geofence | Bungii State   |
      | atlanta  | UNLOADING ITEM |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    #switch to customer so that driver app is in background :Click by notification
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |

    When I Open "customer" application on "same" devices
    And I am on customer Log in page
    And I enter customers "9871450107" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    And I accept Alert message for "Alert: Display Stack trip after current trip"
    Then I click on notification for "CUSTOMER -Driver accepted stack Bungii"
    When I tap "OK on Driver Accepted screen" during a Bungii
    And I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then Bungii Driver "completes Bungii"
    Then I click on notification for "CUSTOMER -Driver started stack Bungii"
    Then "Enroute screen" page should be opened
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 9871450107     |                 |

  @duo1
  #@regression
    @ready1
  Scenario: Manually Ending A Bungii For A Driver Who Has Stacked Bungii Should Display Summary And Start With The Stacked Bungii

    Given that ondemand bungii is in progress
      | geofence | Bungii State        |
      | atlanta  | DRIVING TO DROP OFF |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver

    #switch to customer so that driver app is in background :Click by notification
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |

    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    When I Open "customer" application on "same" devices
    And I am on customer Log in page
    And I enter customers "9871450107" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then for a Bungii I should see "bungii accepted screen"

    When I Switch to "driver" application on "same" devices

    When bungii admin manually end bungii created by "CUSTOMER1"
    Then Bungii driver should see "summary" on Bungii completed page
    Then Bungii Driver "tab On to Next"
    Then "Enroute screen" page should be opened
    When I Switch to "customer" application on "same" devices
    Then "Enroute screen" page should be opened
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |

  @duo1
  @sanity
    #move to top
    #need to do in atlanta
  @regression
  Scenario: Verify Long Stack Request Acceptance By Non Control Driver
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    Then I am logged in as "valid atlanta" driver

    When I connect to "extra1" using "Driver2" instance
    And I Switch "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    Then I am logged in as "valid driver 2" driver

    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta  | enroute      | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |

#    And I Switch to "driver" application on "same" devices
#    And I am on the LOG IN page on driver app
#    And I am logged in as "valid atlanta" driver
#
#    And I connect to "extra1" using "Driver2" instance
#    And I Open "driver" application on "same" devices
#    And I am on the LOG IN page on driver app
#    And I am logged in as "valid driver 2" driver
      #put driver app in background
    And I Open "customer" application on "same" devices

    When I Switch to "customer" application on "ORIGINAL" devices
    And I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
    #control driver reject stak trip
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "reject stack message" request

    And I Open "driver" application on "Driver2" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck

    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"

    And I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at pickup"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"

    And I Open "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"

    Then I accept Alert message for "Reminder: both driver at drop off"

    And I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I Open "driver" application on "Driver2" devices
    Then Bungii Driver "tab On to Next"
    Then "Enroute screen" page should be opened
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |
 #need to do in atlanta
          #move to top
  @duo1
 # @regression
@ready1
  Scenario: Verify Short Stack Request Acceptance By Control Driver

    Given that duo schedule bungii is in progress
      | geofence | Bungii State       | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta  | Driving To Dropoff | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
      #put driver app in background
    And I Open "customer" application on "same" devices

    And I connect to "extra1" using "Driver2" instance
    And I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver
      #put driver app in background
    And I Open "customer" application on "same" devices

    When I Switch to "customer" application on "ORIGINAL" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |

    And I Open "customer" application on "Driver2" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "reject stack message" request

          #control driver accept  stack trip
    And I Open "customer" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"

    And I Open "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"
    Then Bungii Driver "tab On to Next"

    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii Driver "tab On to Next"
    Then "Enroute screen" page should be opened
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |

  #change user login

  @regression
  Scenario: Verify Driver Does Not Receive Long Stacked Request If Drivers Location Is More Than 100 Mins From The Current Location Of Driver To The Pickup Of The Newly Requested Trip
    Given that ondemand bungii is in progress
      | geofence    | Bungii State | Driver label            |
      | atlanta.far | LOADING ITEM | far away atlanta driver |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid far away atlanta" driver

    And I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "valid customer 2" customer
    And I enter "atlanta pickup and dropoff locations away from driver" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    And I Open "driver" application on "ORIGINAL" devices
    When I verify that driver to pickup time is greater than 100 mins for second trip
    Then I should not get notification for stack trip
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |

  @regression
  Scenario: Verify Driver Does Not Receive Short Stacked Request If Drivers Location Is More Than 100 Mins From The Current Location Of The Driver To The Pickup Of The Newly Requested Trip
    Given that ondemand bungii is in progress
      | geofence    | Bungii State   | Driver label            |
      | atlanta.far | UNLOADING ITEM | far away atlanta driver |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid far away atlanta" driver

    And I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "valid customer 2" customer
    And I enter "atlanta pickup and dropoff locations away from driver" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    And I Open "driver" application on "ORIGINAL" devices
    When I verify that driver to pickup time is greater than 100 mins for second trip
    Then I should not get notification for stack trip
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |

  @duo1
  @regression
  Scenario: Verify Non-control Driver Does Not Receive Long Stacking Request If Started Before The Controlled Driver - Also Non Control Driver Cannot Cancel Trip If Controlled driver has Not Started
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta  | Accepted     | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
    And I connect to "extra1" using "Driver2" instance
    And I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
 #   And I wait for Minimum duration for Bungii Start Time
    #non control driver start the trip
    And Bungii Driver "Start Schedule Bungii" request
    And I Open "customer" application on "same" devices

    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
    Then I should not get notification for stack trip
  #  When I Open "customer" application on "same" devices
    When I Switch to "driver" application on "same" devices
    When Bungii Driver "tab on Cancel bungii"
    Then Alert message with TRIP CANNOT BE CANCELED AS CONTROL DRIVER NOT STARTED text should be displayed
    Then Alert should have "cancel,proceed" button
    When I click "Cancel" on alert message
    Then "Enroute screen" page should be opened
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |

    #need to execute later
    @regression

    Scenario: Verify Minimum Scheduled Time For The Duo Trip
      Given I am on customer Log in page
      When I am logged in as "New" customer
      And I enter "San Francisco pickup and dropoff locations" on Bungii estimate
      And I tap on "two drivers selector" on Bungii estimate
      Then I should see "two drivers selected" on Bungii estimate
      When I tap on "Get Estimate button" on Bungii estimate
      Then I should see the minimum scheduled time displayed on the Estimate page

  @regression
  Scenario: Verify When Customer Cancels Duo Trip Accepted By One Driver Then Driver Gets A Notification Though The App Remains In Background
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta  | Scheduled    | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |

    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "valid atlanta" customer

    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices

    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver

    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page

    #put driver on background
    When I Open "customer" application on "same" devices
    When I Switch to "customer" application on "ORIGINAL" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    When I Cancel selected Bungii

    When I Switch to "customer" application on "Driver1" devices
    And I click on notification for "CUSTOMER CANCELLED SCHEDULED BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  #@regression
  @ready1
  Scenario: Verify When Customer Cancels Duo Trip Accepted By One Driver Then Driver Gets A Notification When The App Remains open

    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta  | Scheduled    | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |

    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "valid atlanta" customer

    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page

    When I Switch to "customer" application on "ORIGINAL" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    When I Cancel selected Bungii

    When I switch to "Driver1" instance
    Then Alert message with CUSTOMER CANCELLED SCHEDULED BUNGII text should be displayed
    When I click "OK" on alert message
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
  Scenario: Verify Other Driver And Customer Are Notified When One Driver Cancels The Duo Bungii
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta  | enroute      | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |

    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "valid atlanta" customer

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver

    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver

    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message

    When I switch to "ORIGINAL" instance
    #message to driver
    Then Alert message with OTHER DRIVER CANCELLED BUNGII text should be displayed
    When I Switch to "driver" application on "same" devices
    And I click on notification for "DRIVER CANCELLED BUNGII"

  @regression
  Scenario: Verify Driver Notification When Other Driver Cancels Duo Bungii
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1            | Driver2         |
      | atlanta  | enroute      | NEXT_POSSIBLE | valid        | valid   | valid driver 2 |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    When I Switch to "customer" application on "same" devices
    #driver1 in background
    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver

    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message

    When I switch to "ORIGINAL" instance
    Then I click on notification for "OTHER DRIVER CANCELLED BUNGII"
    Then Alert message with OTHER DRIVER CANCELLED BUNGII text should be displayed

  @regression

  Scenario: Verify Driver Alert When Other Driver cancels Duo Bungii
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1            | Driver2         |
      | atlanta  | enroute      | NEXT_POSSIBLE | valid        | valid   | valid driver 2 |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    #driver1 in foregroundground
    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver

    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message

    When I switch to "ORIGINAL" instance
    Then Alert message with OTHER DRIVER CANCELLED BUNGII text should be displayed

  #@regression
  @ready1
  Scenario: Verify Customer Can View Ongoing Bungii Progress Screens When Trip Is Started By Control Driver
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | Kansas   | Accepted     | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |

    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid kansas" customer

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 1" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" screen
    Then I check ETA of "control driver"

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "EN ROUTE" screen
    Then "control driver" eta should be displayed to customer

    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "ARRIVED" screen

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "EN ROUTE" Screen


    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "LOADING ITEM" screen

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen
    Then I check ETA of "control driver"

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "DRIVING TO DROP OFF" screen
    Then "control driver" eta should be displayed to customer

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "LOADING ITEM" Screen


    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on the "Bungii Completed" screen

    When I Switch to "customer" application on "same" devices
    Then I wait for "2" mins
    Then I should be navigated to "BUNGII COMPLETE" screen
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on the "Promotion" screen

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    When I click "On To The Next One" button on the "Bungii Completed" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
