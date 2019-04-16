@ios
Feature: To Test Duo - Scheduling Bungii
  I want to use request Scheduling Bungii with Duo type

  @regression
  @sanity
  Scenario: Create Duo Bungii


    When I Switch to "driver" application on "same" devices
    Given I am logged in as "valid" driver
    Then I change driver status to "Online"

    When I connect to "extra1" using "Driver2" instance
    When I Switch to "driver" application on "same" devices

    Given I am logged in as "valid driver 2" driver
    Then I change driver status to "Online"

    When I Switch to "customer" application on "ORIGINAL" devices
    Given I am on Customer logged in Home page
    When I Select "ACCOUNT" from Customer App menu
    Then I get customer account details
    When I Select "Home" from Customer App menu

    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Duo    | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |              | NEXT_POSSIBLE | 1 images    | Yes            |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    When I Select "Home" from Customer App menu

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "TRIP DETAILS" screen
    Then Trip Information should be correctly displayed on TRIP DETAILS screen
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu

    When I Switch to "driver" application on "Driver2" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    Then Trip Information should be correctly displayed on TRIP DETAILS screen
    When I accept selected Bungii

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time
    When I start selected Bungii
  #  Then I should be navigated to "EN ROUTE" trip status screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    When I start selected Bungii
  #  Then I should be navigated to "EN ROUTE" trip status screen

    When I Switch to "customer" application on "same" devices
  #  Then Customer should be navigated to "EN ROUTE" trip status screen

    When I Switch to "driver" application on "same" devices
    When I slide update button on "EN ROUTE" Screen
  #  Then I should be navigated to "ARRIVED" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    When I slide update button on "EN ROUTE" Screen
  #  Then I should be navigated to "ARRIVED" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
  #  Then Customer should be navigated to "ARRIVED" trip status screen

    When I Switch to "driver" application on "same" devices
    When I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
  #  Then I should be navigated to "LOADING ITEM" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    When I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
  #  Then I should be navigated to "LOADING ITEM" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
  #  Then Customer should be navigated to "LOADING ITEM" trip status screen

    When I Switch to "driver" application on "same" devices
    When I slide update button on "LOADING ITEM" Screen
  #  Then I should be navigated to "DRIVING TO DROP OFF" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    When I slide update button on "LOADING ITEM" Screen
  #  Then I should be navigated to "DRIVING TO DROP OFF" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
  #  Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen

    When I Switch to "driver" application on "same" devices
    When I slide update button on "DRIVING TO DROP OFF" Screen
  #  Then I should be navigated to "UNLOADING ITEM" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    When I slide update button on "DRIVING TO DROP OFF" Screen
  #  Then I should be navigated to "UNLOADING ITEM" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
  #  Then Customer should be navigated to "UNLOADING ITEM" trip status screen

    When I Switch to "driver" application on "same" devices
    When I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I click "On To The Next One" button on "Bungii Completed" screen

    When I Switch to "driver" application on "Driver2" devices
    When I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I click "On To The Next One" button on "Bungii Completed" screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then I should be navigated to "Bungii Complete" screen
    When I rate Bungii Driver  with following details and Press "CLOSE" Button
      | Ratting | Tip |
      | 5       | 5   |
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

  @regression
  Scenario: Create Duo Bungii, Verify Details
    When I Switch to "driver" application on "same" devices
    Given I am logged in as "valid" driver
    Then I change driver status to "Online"
    And I Select "ACCOUNT" from driver App menu
    Then I get driver account details for driver 1
    And I Select "HOME" from driver App menu

    When I connect to "extra1" using "Driver2" instance
    When I Switch to "driver" application on "same" devices
    Given I am logged in as "valid driver 2" driver
    Then I change driver status to "Online"
    And I Select "ACCOUNT" from driver App menu
    Then I get driver account details for driver 2
    And I Select "HOME" from driver App menu



    When I Switch to "customer" application on "ORIGINAL" devices
    Given I am on Customer logged in Home page
    When I Select "ACCOUNT" from Customer App menu
    Then I get customer account details
    When I Select "Home" from Customer App menu

    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Duo    | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |              | NEXT_POSSIBLE | 1 images    | Yes            |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    When I Select "Home" from Customer App menu

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "TRIP DETAILS" screen
    Then Trip Information should be correctly displayed on TRIP DETAILS screen
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu

    When I Switch to "driver" application on "Driver2" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    Then Trip Information should be correctly displayed on TRIP DETAILS screen
    When I accept selected Bungii

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time
    When I start selected Bungii
    Then I should be navigated to "EN ROUTE" trip status screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    When I start selected Bungii
    Then I should be navigated to "EN ROUTE" trip status screen

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "EN ROUTE" trip status screen
    Then correct details should be displayed to customer for "DUO DRIVER 1-CALL DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 1-TEXT DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 2-CALL DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 2-TEXT DRIVER"

    When I Switch to "driver" application on "same" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    Then correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"

    Then correct details should be displayed to driver for "DUO DRIVER 2-CALL DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER 2-TEXT DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    Then correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"

    Then correct details should be displayed to driver for "DUO DRIVER 1-CALL DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER 1-TEXT DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
    Then correct details should be displayed to customer for "DUO DRIVER 1-CALL DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 1-TEXT DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 2-CALL DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 2-TEXT DRIVER"

    When I Switch to "driver" application on "same" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    Then correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"

    Then correct details should be displayed to driver for "DUO DRIVER 2-CALL DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER 2-TEXT DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    Then I should be navigated to "LOADING ITEM" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    Then correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"

    Then correct details should be displayed to driver for "DUO DRIVER 1-CALL DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER 1-TEXT DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    Then I should be navigated to "LOADING ITEM" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "LOADING ITEM" trip status screen
    Then correct details should be displayed to customer for "DUO DRIVER 1-CALL DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 1-TEXT DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 2-CALL DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 2-TEXT DRIVER"

    When I Switch to "driver" application on "same" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    Then correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"

    Then correct details should be displayed to driver for "DUO DRIVER 2-CALL DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER 2-TEXT DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    Then correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"

    Then correct details should be displayed to driver for "DUO DRIVER 1-CALL DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER 1-TEXT DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then correct details should be displayed to customer for "DUO DRIVER 1-CALL DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 1-TEXT DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 2-CALL DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 2-TEXT DRIVER"

    When I Switch to "driver" application on "same" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    Then correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"

    Then correct details should be displayed to driver for "DUO DRIVER 2-CALL DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER 2-TEXT DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    Then correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"

    Then correct details should be displayed to driver for "DUO DRIVER 1-CALL DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER 1-TEXT DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "UNLOADING ITEM" trip status screen
    Then correct details should be displayed to customer for "DUO DRIVER 1-CALL DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 1-TEXT DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 2-CALL DRIVER"
    Then correct details should be displayed to customer for "DUO DRIVER 2-TEXT DRIVER"

    When I Switch to "driver" application on "same" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    Then correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"

    Then correct details should be displayed to driver for "DUO DRIVER 2-CALL DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER 2-TEXT DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I click "On To The Next One" button on "Bungii Completed" screen

    When I Switch to "driver" application on "Driver2" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    Then correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    Then correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"

    Then correct details should be displayed to driver for "DUO DRIVER 1-CALL DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER 1-TEXT DRIVER"
    Then correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I click "On To The Next One" button on "Bungii Completed" screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then I should be navigated to "Bungii Complete" screen
    When I rate Bungii Driver  with following details and Press "CLOSE" Button
      | Ratting | Tip |
      | 5       | 5   |
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen
