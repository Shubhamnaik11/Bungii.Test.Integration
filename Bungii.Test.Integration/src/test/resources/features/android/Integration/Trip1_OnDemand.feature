@android
  #These feature will run in baltimore geofence
Feature: On Demand Bungii


  @regression
  Scenario: Validate That I am able to create on demand bungii. Also Validate that Correct contact number is displayed on Call and SMS Option

    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | baltimore   | Enroute      |
    When I Switch to "driver" application on "same" devices
    And I am logged in as "valid baltimore" driver

    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid baltimore" customer
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
    When I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And Bungii Driver "completes Bungii"

  @sanity
  @regression
  Scenario: Validate That I am able to create on demand bungii.

    Given I am logged in as "valid baltimore" customer
    When I Switch to "driver" application on "same" devices
    And I am logged in as "valid baltimore" driver
    And I Select "HOME" from driver App menu
    And I tap on "Go Online button" on Driver Home page
    And I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "HOME" link
    And I enter "baltimore pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    When I Open "driver" application on "same" devices
    And Bungii Driver "accepts On Demand Bungii" request
    Then Bungii driver should see "Enroute screen"

    When I Switch to "customer" application on "same" devices
    And I tap "OK on Driver Accepted screen" during a Bungii
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

#no existing promocode
  @regression
  Scenario Outline: Validate That I am able to create on demand bungii with Promo codes .Scenario:<Scenario>

    Given I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I am logged in as "<User>" customer

    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid baltimore" driver
    And I tap on "Go Online button" on Driver Home page
    And I Switch to "customer" application on "same" devices

    And I tap on "Menu" > "HOME" link
    And I enter "baltimore pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I tap on "Promo Code" on Bungii estimate
    And I add "<Promo Code>" PromoCode
    And I tap "Add" on Save Money page
    And I tap on "desired Promo Code" on Bungii estimate
    And I get Bungii details on Bungii Estimate
    Then I should see "all elements" on Bungii estimate
    When I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    When I Open "driver" application on "same" devices
    And Bungii Driver "accepts On Demand Bungii" request
    Then Bungii driver should see "Enroute screen"

    When I Switch to "customer" application on "same" devices
    And I tap "OK on Driver Accepted screen" during a Bungii
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
    When Bungii Driver "slides to the next state"
    Then Bungii driver should see "Unloading Item screen"

    When I Switch to "customer" application on "same" devices
    Then for a Bungii I should see "Unloading Item screen"
    When I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"

    When I Switch to "customer" application on "same" devices
    Then Bungii customer should see "correct details with promo" on Bungii completed page
    When I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    And I Switch to "driver" application on "same" devices
    And Bungii Driver "completes Bungii"
    Examples:
      | Scenario            | Promo Code    | User         |
      | Promo fixed         | valid         | valid baltimore |
      | Promo percentage    | valid percent | valid baltimore |
      | valid one off fixed | valid one off | valid baltimore |