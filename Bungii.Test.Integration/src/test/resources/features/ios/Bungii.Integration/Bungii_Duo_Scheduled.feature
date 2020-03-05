@ios
@DUO
@scheduled
Feature: To Test Duo - Scheduled Bungii
  I want  request Scheduled Bungii with Duo type

    #this scenario also include large image test case
  @FAILED2702
  @regression
  @sanity
  Scenario: Verify Scheduling Of Duo Bungii As An iOS Customer

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
      | 30       |           |              | NEXT_POSSIBLE | large image | Yes            |
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
  #  When I wait for Minimum duration for Bungii Start Time
    And I start selected Bungii
 #   Then I should be navigated to "EN ROUTE" trip status screen
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
  #  Then I should be navigated to "EN ROUTE" trip status screen
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "customer" application on "same" devices
  #  Then Customer should be navigated to "EN ROUTE" trip status screen
    Then I should be navigated to "EN ROUTE" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
 #   Then I should be navigated to "ARRIVED" trip status screen
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "EN ROUTE" Screen
 #   Then I should be navigated to "ARRIVED" trip status screen
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "customer" application on "ORIGINAL" devices
#    Then Customer should be navigated to "ARRIVED" trip status screen
    Then I should be navigated to "ARRIVED" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
 #   And I should be navigated to "LOADING ITEM" trip status screen
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
  #  And I should be navigated to "LOADING ITEM" trip status screen
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "customer" application on "ORIGINAL" devices
 #   Then Customer should be navigated to "LOADING ITEM" trip status screen
    Then I should be navigated to "LOADING ITEM" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen
  #  Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "LOADING ITEM" Screen
  #  Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "customer" application on "ORIGINAL" devices
  #  Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
    Then I should be navigated to "DRIVING TO DROP OFF" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
#    Then I should be navigated to "UNLOADING ITEM" trip status screen
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
  #  Then I should be navigated to "UNLOADING ITEM" trip status screen
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "customer" application on "ORIGINAL" devices
#    Then Customer should be navigated to "UNLOADING ITEM" trip status screen
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"


    And I Switch to "customer" application on "ORIGINAL" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details" on Bungii completed page
    When I rate Bungii Driver  with following details and Press "CLOSE" Button
      | Ratting | Tip |
      | 5       | 5   |
    And I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen


    When I Switch to "driver" application on "Driver2" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    When I click "On To The Next One" button on "Bungii Completed" screen
    And I Select "HOME" from driver App menu

  @demo
  @regression
  Scenario: Verify Customer Can View Ongoing Bungii Progress Screens When Trip Is Started By Control Driver
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Accepted     | NEXT_POSSIBLE | customer-duo | valid duo driver 1 | valid driver 2 |

    And I Switch to "customer" application on "same" devices
    When I logged in Customer application using  "customer-duo" user

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" screen
    Then I check ETA of "control driver"

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "EN ROUTE" screen
    Then "control driver" eta should be displayed to customer

    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
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
    Then I wait for "1" mins
    When I click "On To The Next One" button on "Bungii Completed" screen

    When I Switch to "customer" application on "same" devices
    Then I wait for "2" mins
    Then I should be navigated to "BUNGII COMPLETE" screen
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen

    And I Switch to "driver" application on "Driver2" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    When I click "On To The Next One" button on "Bungii Completed" screen
  @FAILED2702
  @regression
  Scenario Outline: Verify Customer Amount Calculation For Scheduled Duo Bungii With Promo Code

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
      | Driver | Pickup Location | Drop Location           |
      | Duo    | Margoa Railway  | peerbaugh Rd, Peer wadi |
    And I click "Get Estimate" button on "Home" screen

    When I select load time as "15" mins
    And I tap "Promo code" on Estimate screen
    And I should be navigated to "PROMOS" screen
    And I add "<PROMO CODE>" PromoCode
    And I click "ADD" button on "PROMOS" screen
    Then I should able to see expected promo code in available promo code
    When I tap "Back" on Promos screen
    And I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
      |          |           |              | NEXT_POSSIBLE | Default     |
    And I request for bungii using Request Bungii Button


    When I click "Done" button on "Success" screen
    And I Select "Home" from Customer App menu
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Switch to "driver" application on "Driver2" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
  #  Then Trip Information should be correctly displayed on TRIP DETAILS screen
    When I accept selected Bungii
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
  #  Then I should be navigated to "BUNGII DETAILS" screen
  #  When I wait for Minimum duration for Bungii Start Time
    And I start selected Bungii

    When I Switch to "driver" application on "ORIGINAL" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    And I slide update button on "EN ROUTE" Screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "EN ROUTE" Screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "ARRIVED" Screen
    Then I accept Alert message for "Reminder: both driver at pickup"
    When I Switch to "driver" application on "ORIGINAL" devices

    And I slide update button on "LOADING ITEM" Screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "LOADING ITEM" Screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen

    And I Switch to "customer" application on "ORIGINAL" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    When I Switch to "driver" application on "same" devices

    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"

    And I Switch to "customer" application on "ORIGINAL" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details with promo" on Bungii completed page
    When I rate Bungii Driver  with following details and Press "CLOSE" Button
      | Ratting | Tip |
      | 5       | 5   |
    And I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen


    When I Switch to "driver" application on "Driver2" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    When I click "On To The Next One" button on "Bungii Completed" screen
    And I Select "HOME" from driver App menu
    Then I wait for "3" mins

    And I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "trips" from admin sidebar
    And I select "The Beginning of Time" from search peroid
    And I select trip from trips
    Then On admin trip details page "promo" should be displayed

    Examples:
      | PROMO CODE        |
      | PROMO DOLLAR OFF  |
      | PROMO PERCENT OFF |

  @FAILED2702
  @regression
  Scenario: Verify Driver Can Contact Customer Of A Requested Scheduled Duo Bungii

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
#    When I wait for Minimum duration for Bungii Start Time
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
#one valid failed , driver name 's Last name is not shown . This is verification and not assertion so test case will continue
  @FAILED2702
  @regression
  Scenario: Verify Decked Alert Status And Messages Of Current Ondemand Bungii And Long Stacked Bungii

    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa      | Enroute      |
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
      | Driver | Pickup Location        | Drop Location                |
      | Solo   | Creative capsule verna | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 15       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen
    When I Switch to "customer" application on "ORIGINAL" devices

  #  When I open "customer" application on "ORIGINAL" devices
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
  #one valid failed , driver name 's Last name is not shown . This is verification and not assertion so test case will continue
  @FAILED0203_08
  @regression
  Scenario: Verify Decked Alert Status And Messages Of Current Scheduled Bungii And Long Stacked Bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | goa      | enroute      | NEXT_POSSIBLE |

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
      | Driver | Pickup Location        | Drop Location                |
      | Solo   | Creative capsule verna | Old Goa Road, Velha Goa, Goa |
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
  #one valid failed , driver name 's Last name is not shown . This is verification and not assertion so test case will continue

  @FAILED2702
  @regression
  Scenario: Verify Decked Alert Status And Messages Of Current Ondemand Bungii And Short Stacked Bungii

    Given that ondemand bungii is in progress
      | geofence | Bungii State        |
      | goa      | DRIVING TO DROP OFF |
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
      | Driver | Pickup Location        | Drop Location                |
      | Solo   | Creative capsule verna | Old Goa Road, Velha Goa, Goa |
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
  Scenario:Verify Driver Can Receive Long Stack Request And Can Cancel Existing Bungii On Loading Item State
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa      | LOADING ITEM |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
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
    Then I wait for "1" mins
    And I click on notification for "Driver" for "CUSTOMER CANCEL STACK TRIP"
    And stack trip information should not be displayed on deck
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 9403960183      |


  @FAILED0203_1
    #move to top
    #need to do in atlanta
  @FAILED0203
  @regression
  Scenario: Verify Non Control Driver Of Ongoing Bungii Can Accept Long Stack Request

    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | enroute      | NEXT_POSSIBLE | customer-duo | valid duo driver 1 | valid driver 2 |

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
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |

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
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |

  @FAILED0203
  @regression
  @sanity
  Scenario: Verify Non Control Driver Of Ongoing Bungii Can Accept Short Stack Request

    Given that duo schedule bungii is in progress
      | geofence | Bungii State       | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Driving To Dropoff | NEXT_POSSIBLE | customer-duo | valid duo driver 1 | valid driver 2 |

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
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
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
  #  And I slide update button on "DRIVING TO DROP OFF" Screen
  #  And I slide update button on "UNLOADING ITEM" Screen
 #   And I accept Alert message for "Reminder: both driver at drop off"
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I open "driver" application on "ORIGINAL" devices
    When I click "On To The Next One" button on "Bungii Completed" screen
    Then I should be navigated to "EN ROUTE" screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |

  @FAILED0203
  @regression
  Scenario: Verify Non Control Driver Doesnt Receive Long Stack Request If Started Before The Control Driver Also Non Control Driver Cannot Cancel Bungii If Control Driver Has Not Started The Bungii

    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
      | goa      | Accepted     | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |

    And I connect to "extra1" using "Driver2" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
 #   When I wait for Minimum duration for Bungii Start Time
    And I start selected Bungii

    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
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

  @FAILED2702
  @regression
  Scenario: Verify When Customer Cancel A Scheduled Duo Trip Accepted By One Driver Then Driver Gets Notification When App Is In Foreground
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Scheduled    | NEXT_POSSIBLE | customer-duo | valid duo driver 1 | valid driver 2 |

    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I logged in Customer application using  "customer-duo" user


    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    When I accept selected Bungii

    When I Switch to "customer" application on "ORIGINAL" devices
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    When I Cancel selected Bungii

    When I switch to "Driver1" instance
    Then Alert message with CUSTOMER CANCELLED SCHEDULED BUNGII text should be displayed
    When I click "OK" on alert message
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @FAILED0203
  @regression
  Scenario: Verify When Customer Cancel A Scheduled Duo Trip Accepted By One Driver Then Driver Gets Notification When App Is In Background
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | Scheduled    | NEXT_POSSIBLE | customer-duo | valid duo driver 1 | valid driver 2 |

    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I logged in Customer application using  "customer-duo" user


    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    When I accept selected Bungii
    #put driver on background
    When I open "customer" application on "same" devices

    When I Switch to "customer" application on "ORIGINAL" devices
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    When I Cancel selected Bungii

    When I open "customer" application on "Driver1" devices
    And I click on notification for "Driver" for "CUSTOMER CANCELLED SCHEDULED BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @FAILED0203_02
  @regression
  Scenario: Verify Customer And Other Driver Is Notified When One Of The Driver Cancels The Scheduled Duo Bungii
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | enroute      | NEXT_POSSIBLE | customer-duo | valid duo driver 1 | valid driver 2 |


    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I logged in Customer application using  "customer-duo" user

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver

    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver

    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "Yes" on alert message

    When I switch to "ORIGINAL" instance
    #message to driver
    Then Alert message with OTHER DRIVER CANCELLED BUNGII text should be displayed
    When I Switch to "driver" application on "same" devices
    And I click on notification for "Customer" for "DRIVER CANCELLED BUNGII"
  @FAILED0203_02_02
  @FAILED0203
  @regression
  Scenario: Verify Other Driver Notification In Background When One Of The Driver Cancels Duo Scheduled Bungii
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | enroute      | NEXT_POSSIBLE | customer-duo | valid duo driver 1 | valid driver 2 |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
    When I Switch to "customer" application on "same" devices
  #driver1 in background
    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver

    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "Yes" on alert message
    Then I wait for "1" mins

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    Then I click on notification for "driver" for "OTHER DRIVER CANCELLED BUNGII"
    Then Alert message with OTHER DRIVER CANCELLED BUNGII text should be displayed

  @regression
  Scenario: Verify Other Driver Alert In Foreground When One Of The Driver Cancels Duo Scheduled Bungii
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer     | Driver1            | Driver2        |
      | goa      | enroute      | NEXT_POSSIBLE | customer-duo | valid duo driver 1 | valid driver 2 |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid duo driver 1" driver
  #driver1 in foregroundground
    And I connect to "extra1" using "Driver1" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid driver 2" driver

    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "Yes" on alert message

    When I switch to "ORIGINAL" instance
    Then Alert message with OTHER DRIVER CANCELLED BUNGII text should be displayed

  @regression
  Scenario: Verify Driver Doesnt Receive Short Stacked Request If The Driver Location Is More Than 100 Mins From The Current Location Of Driver To The Pickup Of Requesting Trip
    Given that ondemand bungii is in progress
      | geofence | Bungii State   |
      | goa      | UNLOADING ITEM |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver

    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    When I logged in Customer application using  "valid customer2" user
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location          | Drop Location  |
      | Solo   | St mary paralytic centre | froggyland Goa |

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
  Scenario: Verify Driver Doesnt Receive Long Stacked Request If The Driver Location Is More Than 100 Mins From The Current Location Of Driver To The Pickup Of Requesting Trip
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
      | Driver | Pickup Location          | Drop Location  |
      | Solo   | St mary paralytic centre | froggyland Goa |

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
  Scenario:Verify Customer Notification For Stack Bungii Accepted And Stack Driver Started
    Given that ondemand bungii is in progress
      | geofence | Bungii State   |
      | goa      | UNLOADING ITEM |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |

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
  Scenario: Verify Manually Ending Bungii For A Driver That Has Stacked Bungii Should See Summary And Start Screen Of The Stacked Bungii
    Given that ondemand bungii is in progress
      | geofence | Bungii State        |
      | goa      | DRIVING TO DROP OFF |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
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

  @regression
  Scenario:Verify Driver Can Receive Short Stack Request And Can Cancel Bungii On Unloading Item State
    Given that ondemand bungii is in progress
      | geofence | Bungii State   |
      | goa      | UNLOADING ITEM |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
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
  Scenario:Verify Driver Can Receive Long Stack Request On Arrived State
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa      | ARRIVED      |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
      | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
    And I click on notification for "Driver" for "stack trip"
    When I click "VIEW" on alert message
    Then "correct stack trip details" should be displayed on Bungii request screen
    When I click "ACCEPT" button on "Bungii Request" screen
    When I click "OK" on alert message
    And stack trip information should be displayed on deck
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |