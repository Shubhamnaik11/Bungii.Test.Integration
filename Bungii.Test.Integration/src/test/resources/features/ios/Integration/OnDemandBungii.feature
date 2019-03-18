@ios

Feature: Create on demand bungii
  I want to use this template for my feature file
  Assume driver and user already logged in

  Background:
    Given I am on Customer logged in Home page


  @regression
  Scenario: I Create and Complete on demand bungii when driver and customer are login in same device. Verify SMS/Call
    And I clear all notification
    When I Switch to "driver" application on "same" devices
    And I Select "ACCOUNT" from driver App menu
    Then I get driver account details for driver 1
    And I Select "HOME" from driver App menu
    When I Switch to "customer" application on "same" devices
    When I Select "ACCOUNT" from Customer App menu
    Then I get customer account details

    When I Select "Home" from Customer App menu
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    Then Trip Information should be correctly displayed on Estimate screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |             | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    Then I click on notification for "Driver" for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    And I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    And I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "EN ROUTE" trip status screen
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
    Then correct details should be displayed to driver on "SMS" app
    Then correct details should be displayed to driver on "Call" app

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    And I click "Ok" button on "BUNGII ACCEPTED" screen
    Then Customer should be navigated to "EN ROUTE" trip status screen
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for customer
    Then correct details should be displayed to customer on "SMS" app
    Then correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    When I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for driver
    Then correct details should be displayed to driver on "SMS" app
    Then correct details should be displayed to driver on "Call" app

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for customer
    Then correct details should be displayed to customer on "SMS" app
    Then correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    When I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" trip status screen
    Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for driver
    Then correct details should be displayed to driver on "SMS" app
    Then correct details should be displayed to driver on "Call" app
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEM" trip status screen
    Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for customer
    Then correct details should be displayed to customer on "SMS" app
    Then correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    When I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for driver
    Then correct details should be displayed to driver on "SMS" app
    Then correct details should be displayed to driver on "Call" app
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for customer
    Then correct details should be displayed to customer on "SMS" app
    Then correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    When I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen
    Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for driver
    Then correct details should be displayed to driver on "SMS" app
    Then correct details should be displayed to driver on "Call" app
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEM" trip status screen
    Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for customer
    Then correct details should be displayed to customer on "SMS" app
    Then correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    When I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    And I click "On To The Next One" button on "Bungii Completed" screen
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    When I rate Bungii Driver  with following details and Press "OK" Button
      | Ratting | Tip |
      | 5       | 5   |
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

  @sanity
  @regression
  Scenario: I Create and Complete on demand bungii when driver and customer are login in same device.

    When I Switch to "driver" application on "same" devices
    Given I am logged in as "valid" driver
    Then I change driver status to "Online"
    And I Select "ACCOUNT" from driver App menu
    Then I get driver account details for driver 1
    And I Select "HOME" from driver App menu
    When I Switch to "customer" application on "same" devices
    When I Select "ACCOUNT" from Customer App menu
    Then I get customer account details

    When I Select "Home" from Customer App menu
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    Then Trip Information should be correctly displayed on Estimate screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |             | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    Then I click on notification for "Driver" for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    And I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    And I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "EN ROUTE" trip status screen
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    And I click "Ok" button on "BUNGII ACCEPTED" screen
    Then Customer should be navigated to "EN ROUTE" trip status screen
#    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for customer

    When I Switch to "driver" application on "same" devices
    When I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen
 #   Then Trip Information should be correctly displayed on "ARRIVED" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
#    Then Trip Information should be correctly displayed on "ARRIVED" status screen for customer

    When I Switch to "driver" application on "same" devices
    When I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" trip status screen
 #   Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEM" trip status screen
 #   Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for customer

    When I Switch to "driver" application on "same" devices
    When I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
  #  Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
 #   Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for customer

    When I Switch to "driver" application on "same" devices
    When I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen
  #  Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEM" trip status screen
#    Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for customer

    When I Switch to "driver" application on "same" devices
    When I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    And I click "On To The Next One" button on "Bungii Completed" screen
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    When I rate Bungii Driver  with following details and Press "OK" Button
      | Ratting | Tip |
      | 5       | 5   |
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

  @regression
  Scenario: Estimate value for the Bungii Should be correctly displayed in Estimate Page

    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |             | Now  | Default     |
    Then Estimate value for trip should be properly displayed
