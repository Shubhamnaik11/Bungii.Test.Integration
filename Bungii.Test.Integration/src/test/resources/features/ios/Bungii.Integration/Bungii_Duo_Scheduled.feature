@ios
@DUO
@scheduled
Feature: To Test Duo - Scheduled Bungii
  I want  request Scheduled Bungii with Duo type

  @regression
  @sanity
  Scenario: Create Duo Bungii


    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I Switch to "customer" application on "ORIGINAL" devices
    And I logged in Customer application using  "customer-duo" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Duo    | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen

    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |              | NEXT_POSSIBLE | 1 images    | Yes            |
    Then I should be navigated to "Success" screen
    When I click "Done" button on "Success" screen
    And I Select "Home" from Customer App menu
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "TRIP DETAILS" screen
    And Trip Information should be correctly displayed on TRIP DETAILS screen

    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Switch to "driver" application on "Driver2" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    Then Trip Information should be correctly displayed on TRIP DETAILS screen
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" trip status screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" trip status screen

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "EN ROUTE" trip status screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "ARRIVED" trip status screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    And I should be navigated to "LOADING ITEM" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    And I should be navigated to "LOADING ITEM" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "LOADING ITEM" trip status screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "UNLOADING ITEM" trip status screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I click "On To The Next One" button on "Bungii Completed" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    When I click "On To The Next One" button on "Bungii Completed" screen
    And I Select "HOME" from driver App menu

    And I Switch to "customer" application on "ORIGINAL" devices
    Then I should be navigated to "Bungii Complete" screen
    When I rate Bungii Driver  with following details and Press "CLOSE" Button
      | Ratting | Tip |
      | 5       | 5   |
    And I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen



  @regression
  Scenario: Create Duo Bungii, Verify driver can contact customer

    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Accepted     | NEXT_POSSIBLE | customer-duo | valid duo driver 1 | valid driver 2 |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver

    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I Switch to "customer" application on "ORIGINAL" devices
    When I logged in Customer application using  "customer-duo" user

    And I Switch to "driver" application on "Driver2" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time
    And I start selected Bungii

    And I Switch to "driver" application on "ORIGINAL" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii

    And I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "EN ROUTE" trip status screen
    Then correct details should be displayed to customer for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-TEXT DRIVER"

    When I Switch to "driver" application on "same" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    And correct details should be displayed to driver for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 2-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    And I slide update button on "EN ROUTE" Screen

    When I Switch to "driver" application on "Driver2" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    Then correct details should be displayed to driver for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "EN ROUTE" Screen

    And I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
    Then correct details should be displayed to customer for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-TEXT DRIVER"

    When I Switch to "driver" application on "same" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    And correct details should be displayed to driver for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 2-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "ARRIVED" Screen
    And I accept Alert message for "Reminder: both driver at pickup"

    And I Switch to "driver" application on "Driver2" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    Then correct details should be displayed to driver for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"

    When I slide update button on "ARRIVED" Screen
    And I accept Alert message for "Reminder: both driver at pickup"

    And I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "LOADING ITEM" trip status screen
    Then correct details should be displayed to customer for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-TEXT DRIVER"

    When I Switch to "driver" application on "same" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    And correct details should be displayed to driver for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 2-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"

    When I slide update button on "LOADING ITEM" Screen

    And I Switch to "driver" application on "Driver2" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    Then correct details should be displayed to driver for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "LOADING ITEM" Screen

    And I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then correct details should be displayed to customer for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-TEXT DRIVER"

    When I Switch to "driver" application on "same" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    And correct details should be displayed to driver for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 2-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"
    When I slide update button on "DRIVING TO DROP OFF" Screen

    And I Switch to "driver" application on "Driver2" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    Then correct details should be displayed to driver for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"

    And I slide update button on "DRIVING TO DROP OFF" Screen

    When I Switch to "customer" application on "ORIGINAL" devices
    Then Customer should be navigated to "UNLOADING ITEM" trip status screen
    Then correct details should be displayed to customer for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to customer for "DUO DRIVER 2-TEXT DRIVER"


    When I Switch to "driver" application on "same" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    And correct details should be displayed to driver for "DUO DRIVER 2-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 2-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"

    When I Switch to "driver" application on "Driver2" devices
    Then correct details should be displayed to driver for "DUO CUSTOMER-VIEW ITEM"
    And correct details should be displayed to driver for "DUO CUSTOMER-CALL CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT CUSTOMER"
    And correct details should be displayed to driver for "DUO CUSTOMER-TEXT BUNGII SUPPORT"
    Then correct details should be displayed to driver for "DUO DRIVER 1-CALL DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER 1-TEXT DRIVER"
    And correct details should be displayed to driver for "DUO DRIVER-TEXT BUNGII SUPPORT"

    When I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"


    And I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    When I click "On To The Next One" button on "Bungii Completed" screen
    And I Select "HOME" from driver App menu

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen

    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "Driver2" devices
    When I click "On To The Next One" button on "Bungii Completed" screen
    And I Select "HOME" from driver App menu

  @regression
  Scenario: Create Long stack, verify decked detail/alert msgs/status of current and stacked bungii

    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa   | Enroute |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "existing" user


    And I connect to "extra1" using "Customer2" instance
    And I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid customer2" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo    |Creative capsule verna  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 15       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen

    When I open "customer" application on "ORIGINAL" devices
    And I click on notification for "Driver" for "stack trip"
    And Alert message with STACK TRIP REQUEST AVAILABLE text should be displayed
    When I click "VIEW" on alert message
    When I click "ACCEPT" button on "Bungii Request" screen
    And Alert message with STACK TRIP REQUEST ACCEPTED text should be displayed
    When I click "OK" on alert message
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When  I switch to "Customer2" instance
    Then correct details should do be displayed on BUNGII ACCEPTED screen for Stack screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen
    Then correct details should do be displayed on BUNGII ACCEPTED with arrival time screen for Stack screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" screen
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "Customer2" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen


  @regression
  Scenario: Create Long stack, base scheduled trip , verify decked detail/alert msgs/status of current and stacked bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | goa   | enroute    | NEXT_POSSIBLE |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "existing" user


    And I connect to "extra1" using "Customer2" instance
    And I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid customer2" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo    |Creative capsule verna  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 15       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen

    When I open "customer" application on "ORIGINAL" devices
    And I click on notification for "Driver" for "stack trip"
    And Alert message with STACK TRIP REQUEST AVAILABLE text should be displayed
    When I click "VIEW" on alert message
    When I click "ACCEPT" button on "Bungii Request" screen
    And Alert message with STACK TRIP REQUEST ACCEPTED text should be displayed
    When I click "OK" on alert message
    And try to finish time should be correctly displayed for long stack trip

    When  I switch to "Customer2" instance
    Then correct details should do be displayed on BUNGII ACCEPTED screen for Stack screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen
    Then correct details should do be displayed on BUNGII ACCEPTED with arrival time screen for Stack screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "Customer2" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen

  @regression
  Scenario: Create short stack, verify decked detail/alert msgs/status of current and stacked bungii

    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa   | DRIVING TO DROP OFF |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "existing" user


    And I connect to "extra1" using "Customer2" instance
    And I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid customer2" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo    |Creative capsule verna  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 15       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen

    When I open "customer" application on "ORIGINAL" devices
    And I click on notification for "Driver" for "stack trip"
    And Alert message with STACK TRIP REQUEST AVAILABLE text should be displayed
    When I click "VIEW" on alert message
    When I click "ACCEPT" button on "Bungii Request" screen
    Then I calculate projected driver arrival time
    And Alert message with STACK TRIP REQUEST ACCEPTED text should be displayed
    When I click "OK" on alert message
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for short stack trip
    When  I switch to "Customer2" instance
    Then correct details should do be displayed on BUNGII ACCEPTED screen for Stack screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen
    Then correct details should do be displayed on BUNGII ACCEPTED with arrival time screen for Stack screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for short stack trip

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "customer" application on "Customer2" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "Customer2" devices
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen

  @regression
  Scenario:Verify driver can Long stack request on Arrived status.
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa   | ARRIVED |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name                | Customer label |
      | now         | 9403960183     | Mark Cuban| 2              |
    And I click on notification for "Driver" for "stack trip"
    When I click "VIEW" on alert message
    Then "correct stack trip details" should be displayed on Bungii request screen
    When I click "ACCEPT" button on "Bungii Request" screen
    When I click "OK" on alert message
    And stack trip information should be displayed on deck
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |
#
  @regression
  Scenario:Verify driver can get Long stack request on Loading item status. Verify Cancel Bungii button. Cancel Notification
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa   | LOADING ITEM |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name                | Customer label |
      | now         | 9403960183     | Mark Cuban| 2              |
    And I click on notification for "Driver" for "stack trip"
    When I click "VIEW" on alert message
    When I click "ACCEPT" button on "Bungii Request" screen
    When I click "OK" on alert message
    And stack trip information should be displayed on deck
    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I enter Username :9403960183 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "CANCEL BUNGII" on bungii accepted screen
    Then I see "Alert: Bungii cancel confirmation" on bungii accepted screen
    When I click "Dismiss on Alert message" on bungii accepted screen
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "CANCEL BUNGII" on bungii accepted screen
    When I click "Cantact Support on Alert message" on bungii accepted screen
    And correct details should be displayed to customer for "customer support-SMS"
    When I click "CANCEL BUNGII" on bungii accepted screen
    When I click "CANCEL BUNGII on Alert message" on bungii accepted screen
    Then I see "Alert: Bungii cancel sucessfully" on bungii accepted screen
    When I click "OK" on alert message
    Then I should be navigated to "HOME" screen
    And I click on notification for "Driver" for "CUSTOMER CANCEL STACK TRIP"
    And stack trip information should not be displayed on deck
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:Verify driver can short stack request on unloading item status. Verify Cancel Bungii button. Cancel Notification
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa   | UNLOADING ITEM  |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name                | Customer label |
      | now         | 9403960183     | Mark Cuban| 2              |
    And I click on notification for "Driver" for "stack trip"
    When I click "VIEW" on alert message
    When I click "ACCEPT" button on "Bungii Request" screen
    When I click "OK" on alert message
    And stack trip information should be displayed on deck
    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I enter Username :9403960183 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "CANCEL BUNGII" on bungii accepted screen
    Then I see "Alert: Bungii cancel confirmation" on bungii accepted screen
    When I click "Dismiss on Alert message" on bungii accepted screen
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "CANCEL BUNGII" on bungii accepted screen
    When I click "Cantact Support on Alert message" on bungii accepted screen
    And correct details should be displayed to customer for "customer support-SMS"
    When I click "CANCEL BUNGII" on bungii accepted screen
    When I click "CANCEL BUNGII on Alert message" on bungii accepted screen
    Then I see "Alert: Bungii cancel sucessfully" on bungii accepted screen
    When I click "OK" on alert message
    Then I should be navigated to "HOME" screen
    And I click on notification for "Driver" for "CUSTOMER CANCEL STACK TRIP"
    And stack trip information should not be displayed on deck
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:Verify Customer notification(Stack bungii accepted, Stack driver started, )
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa   | UNLOADING ITEM  |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name                | Customer label |
      | now         | 9403960183     | Mark Cuban| 2              |

    Given I am on the "LOG IN" page
    When I enter Username :9403960183 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen

    And I click on notification for "Driver" for "stack trip"
    When I click "VIEW" on alert message
    When I click "ACCEPT" button on "Bungii Request" screen
    When I click "OK" on alert message
    And I click on notification for "Customer" for "Driver accepted stack Bungii"
    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen
    And I click on notification for "Customer" for "DRIVER STARTED STACK BUNGII"
    When I click "Ok" button on "BUNGII ACCEPTED" screen
    Then I should be navigated to "EN ROUTE" screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 9403960183     |                 |


  @regression
  Scenario: Manually ending a Bungii for a driver that has a stacked Bungii should display summary and start the stacked bungii.
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa   | DRIVING TO DROP OFF  |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name                | Customer label |
      | now         | 9403960183     | Mark Cuban| 2              |
    And I click on notification for "Driver" for "stack trip"
    When I click "VIEW" on alert message
    When I click "ACCEPT" button on "Bungii Request" screen
    When I click "OK" on alert message
    And stack trip information should be displayed on deck
    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I enter Username :9403960183 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I Switch to "driver" application on "same" devices

    When bungii admin manually end bungii created by "CUSTOMER1"

    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen
    Then I should be navigated to "EN ROUTE" screen
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "EN ROUTE" screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |

    #move to top
    #need to do in atlanta

  @regression
  Scenario: Long stack non control driver

    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | enroute     | NEXT_POSSIBLE | customer-duo | valid duo driver 1 | valid driver 2 |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver

    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I Switch to "customer" application on "same" devices

    And I Switch to "customer" application on "ORIGINAL" devices

    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name                | Customer label |
      | now         | 9403960183     | Mark Cuban | 2              |

    And I click on notification for "Driver" for "stack trip"
    When I click "VIEW" on alert message
    When I click "REJECT" button on "Bungii Request" screen

    And I open "driver" application on "Driver2" devices
    And I click on notification for "Driver" for "stack trip"
    When I click "VIEW" on alert message
    When I click "ACCEPT" button on "Bungii Request" screen
    When I click "OK" on alert message
    And stack trip information should be displayed on deck
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I accept Alert message for "Reminder: both driver at pickup"
    And I Switch to "driver" application on "ORIGINAL" devices

    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I accept Alert message for "Reminder: both driver at pickup"
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen

    And I open "driver" application on "Driver2" devices
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I accept Alert message for "Reminder: both driver at drop off"
    And I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEM" Screen
    And I accept Alert message for "Reminder: both driver at drop off"
    When I click "On To The Next One" button on "Bungii Completed" screen
    And I open "driver" application on "Driver2" devices
    When I click "On To The Next One" button on "Bungii Completed" screen
    Then I should be navigated to "EN ROUTE" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      |  | CUSTOMER2_PHONE |



  @regression

  Scenario: Short stack control driver

    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Driving To Dropoff     | NEXT_POSSIBLE | customer-duo | valid duo driver 1 | valid driver 2 |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver

    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I Switch to "customer" application on "same" devices

    And I Switch to "customer" application on "ORIGINAL" devices

    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name                | Customer label |
      | now         |9403960183     | Mark Cuban | 2              |
    And I Switch to "customer" application on "Driver2" devices

    And I click on notification for "Driver" for "stack trip"
    When I click "VIEW" on alert message
    When I click "REJECT" button on "Bungii Request" screen

    And I open "customer" application on "ORIGINAL" devices
    And I click on notification for "Driver" for "stack trip"
    When I click "VIEW" on alert message
    When I click "ACCEPT" button on "Bungii Request" screen
    When I click "OK" on alert message
    And stack trip information should be displayed on deck
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I accept Alert message for "Reminder: both driver at drop off"

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I accept Alert message for "Reminder: both driver at drop off"
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I open "driver" application on "ORIGINAL" devices
    When I click "On To The Next One" button on "Bungii Completed" screen
    Then I should be navigated to "EN ROUTE" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      |  | CUSTOMER2_PHONE |

  @regression
  Scenario: A driver should Not receive a LONG stacked request if the drivers location is more than 100 mins from the current location of the driver to the pickup of the newly requested trip.
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa      | LOADING ITEM |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver

    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    When I logged in Customer application using  "valid customer2" user
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Commercial tax check post polem  | froggyland Goa |

    And I click "Get Estimate" button on "Home" screen
    And I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen

    When I Switch to "driver" application on "same" devices
    When I verify that driver to pickup time is greater than 100 mins for second trip
    And I should not get notification for "driver" for "stack trip"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |

  @regression
  Scenario: A driver should Not receive a Short stacked request if the drivers location is more than 100 mins from the current location of the driver to the pickup of the newly requested trip.
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa      | UNLOADING ITEM |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver

    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    When I logged in Customer application using  "valid customer2" user
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Commercial tax check post polem  | froggyland Goa |

    And I click "Get Estimate" button on "Home" screen
    And I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen

    When I Switch to "driver" application on "same" devices
    When I verify that driver to pickup time is greater than 100 mins for second trip
    And I should not get notification for "driver" for "stack trip"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |


  @regression
  Scenario: Non-control driver should Not receive long stacking request if started before the control driver. Non control cannot cancel trip if control not started

    Given that duo schedule bungii is in progress
      | geofence | Bungii State       | Bungii Time   | Customer | Driver1 | Driver2        |
      | goa   | Accepted | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |

    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    When I wait for Minimum duration for Bungii Start Time
    And I start selected Bungii

    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name                | Customer label |
      | now         | 9403960183     | Mark Cuban | 2              |
    And I should not get notification for "driver" for "stack trip"
    And I Switch to "driver" application on "same" devices
    And I click "Cancel" button on "update" screen
    When I click "Yes" on alert message
    Then Alert message with TRIP CANNOT BE CANCELED AS CONTROL DRIVER NOT STARTED text should be displayed
    Then Alert should have "cancel,proceed" button
    When I click "Cancel" on alert message
    Then I should be navigated to "EN ROUTE" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |
