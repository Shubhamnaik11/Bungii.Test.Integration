@ios
  @NONBUNGII
Feature: To Test Solo - Scheduling Bungii
  I want to use request Scheduling Bungii with Solo type
  Assume customer is logged in

  Background:
    When I Switch to "driver" application on "same" devices
    Given I am logged in as "valid" driver
    And I Select "HOME" from driver App menu
    Then I change driver status to "Online"
    And I Select "ACCOUNT" from driver App menu
    Then I get driver account details for driver 1
    And I Select "HOME" from driver App menu
    When I Switch to "customer" application on "ORIGINAL" devices
    Given I am on Customer logged in Home page

  @regression
  Scenario: I should able to Create and Complete Schedule Bungii, Verify details


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
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
      | 30       |           |              | NEXT_POSSIBLE | Default     |
    Then I should be navigated to "Success" screen
    Then Bungii Posted message should be displayed
    And I click "Done" button on "Success" screen
    Then I Select "Home" from Customer App menu
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "TRIP DETAILS" screen
    Then Trip Information should be correctly displayed on TRIP DETAILS screen
    When I accept selected Bungii

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time

    When I start selected Bungii
    Then I should be navigated to "EN ROUTE" trip status screen
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
    Then correct details should be displayed to driver on "SMS" app
    Then correct details should be displayed to driver on "Call" app
    Then correct details should be displayed to driver for "SMS FOR SUPPORT"
    Then correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "EN ROUTE" trip status screen
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for customer
    Then correct details should be displayed to customer on "SMS" app
    Then correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    When I verify and slide update button on "EN ROUTE" Screen

    Then I should be navigated to "ARRIVED" trip status screen
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for driver
    Then correct details should be displayed to driver on "SMS" app
    Then correct details should be displayed to driver on "Call" app
    Then correct details should be displayed to driver for "SMS FOR SUPPORT"
    Then correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for customer
    Then correct details should be displayed to customer on "SMS" app
    Then correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    When I verify and slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" trip status screen
    Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for driver
    Then correct details should be displayed to driver on "SMS" app
    Then correct details should be displayed to driver on "Call" app
    Then correct details should be displayed to driver for "SMS FOR SUPPORT"
    Then correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEM" trip status screen
    Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for customer
    Then correct details should be displayed to customer on "SMS" app
    Then correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    When I verify and slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for driver
    Then correct details should be displayed to driver on "SMS" app
    Then correct details should be displayed to driver on "Call" app
    Then correct details should be displayed to driver for "SMS FOR SUPPORT"
    Then correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for customer
    Then correct details should be displayed to customer on "SMS" app
    Then correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    When I verify and slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen
    Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for driver
    Then correct details should be displayed to driver on "SMS" app
    Then correct details should be displayed to driver on "Call" app
    Then correct details should be displayed to driver for "SMS FOR SUPPORT"
    Then correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEM" trip status screen
    Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for customer
    Then correct details should be displayed to customer on "SMS" app
    Then correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    When I verify and slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    Then Bungii customer should see "correct details" on Bungii completed page
    When I rate Bungii Driver  with following details and Press "OK" Button
      | Ratting | Tip |
      | 5       | 5   |
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

  @regression
  @sanity
  Scenario: I should able to Create and Complete Schedule Bungii

    When I Select "ACCOUNT" from Customer App menu
    Then I get customer account details
    When I Select "Home" from Customer App menu
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
#    Then Trip Information should be correctly displayed on Estimate screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
      | 30       |           |              | NEXT_POSSIBLE | Default     |
    Then I should be navigated to "Success" screen
    Then Bungii Posted message should be displayed
    And I click "Done" button on "Success" screen
    Then I Select "Home" from Customer App menu
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "TRIP DETAILS" screen
    Then Trip Information should be correctly displayed on TRIP DETAILS screen
    When I accept selected Bungii
#    When I Switch to "driver" application on "same" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time
    #TODO: verify bungii detail page
   # Then If Alerted I ""
    When I start selected Bungii
    Then I should be navigated to "EN ROUTE" trip status screen
    When I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEM" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEM" trip status screen
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
  Scenario: To check that Customer cannot schedule a Bungii at same time as an already scheduled bungii
 #   When I Switch to "customer" application on "same" devices
    When I Select "Home" from Customer App menu
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
  #  Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |              | NEXT_POSSIBLE | Default     | No             |
  #  Then I should be navigated to "Success" screen
#    Then Bungii Posted message should be displayed
    And I click "Done" button on "Success" screen
    Then I Select "Home" from Customer App menu
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
   # Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time              | PickUpImage | Save Trip Info |
      | 30       |           |              | <OLD BUNGII TIME> | Default     | No             |
    Then user is alerted for "already scheduled bungii"
    And I click "Cancel" button on "Estimate" screen
    When I Select "SCHEDULED BUNGIIS" from Customer App menu
    When I select already scheduled bungii
    Then I Cancel selected Bungii

  @regression
  Scenario: Customer cancel bungii , Verify trip details in Bungii Details
    When I Select "ACCOUNT" from Customer App menu
    Then I get customer account details
    When I Select "Home" from Customer App menu
    And I request for  bungii
      | Driver | Distance |
      | Solo   | Long     |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
      | 30       |           |              | NEXT_POSSIBLE | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    When I Select "SCHEDULED BUNGIIS" from Customer App menu
    When I select already scheduled bungii
    Then Trip Information should be correctly displayed on BUNGII DETAILS screen
    Then I Cancel selected Bungii
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @regression
  Scenario: Cancel Bungii from Admin Panel , verify trip is gone from scheduled trip in app

    When I Select "ACCOUNT" from Customer App menu
    Then I get customer account details
    When I Select "Home" from Customer App menu
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
      | 30       |           |              | NEXT_POSSIBLE | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    When I open new "Chrome" browser for "ADMIN"
    When I navigate to admin portal
    And I log in to admin portal
    When I Select "Scheduled Trip" from admin sidebar
    # 			Then I Select Bungii from "Scheduled Trip" List
    When I Cancel Bungii with following details
      | Charge | Comments |
      | 15     | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    Then Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    When I Switch to "customer" application on "same" devices
    When I Select "SCHEDULED BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen
