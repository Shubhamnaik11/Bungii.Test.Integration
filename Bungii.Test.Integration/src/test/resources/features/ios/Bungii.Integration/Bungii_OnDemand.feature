@ios
  # this will run in 	nashville
Feature: Create on demand bungii

  @POSTDUO
  @sanity
  @regression
  Scenario: I Create and Complete on demand bungii when driver and customer are login in same device. verify pickup status highlight
    Given I am on the "LOG IN" page
   # When I am on Customer logged in Home page
    When I logged in Customer application using  "valid nashville" user
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid nashville" driver
    And I Select "HOME" from driver App menu
    And I change driver status to "Online"
    And I Switch to "customer" application on "same" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                      | geofence  |
      | Solo   | Nashville International Airport | Graylynn Drive Nashville Tennessee | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    And I click on notification for "Driver" for "on demand trip"
    And Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    When I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "EN ROUTE" trip status screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen
    Then Customer should be navigated to "EN ROUTE" trip status screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "ARRIVED" trip status screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" trip status screen

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEM" trip status screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEM" trip status screen

    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

  @regression
  Scenario: I Create and Complete on demand bungii when driver and customer are login in same device. Verify SMS/Call/View Item
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | nashville      | Enroute      |

    When I am on the "LOG IN" page
   # And I am on Customer logged in Home page
    And I logged in Customer application using  "valid nashville" user
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver

    Then correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen

    Then correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen

    Then correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen

    Then correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    When I rate Bungii Driver  with following details and Press "OK" Button
      | Ratting | Tip |
      | 5       | 5   |
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    And I click "On To The Next One" button on "Bungii Completed" screen
    And I Select "Logout" from driver App menu


  @regression
  Scenario: I Create and Complete on demand bungii when driver and customer are login in same device. Verify Trip information/Bungii completed page
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | nashville      | Enroute      |

    When I am on the "LOG IN" page
   # And I am on Customer logged in Home page
    And I logged in Customer application using  "valid nashville" user
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
    When I Switch to "customer" application on "same" devices
    Then Trip Information should be correctly displayed on "EN ROUTE" status screen for customer

    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for driver


    When I Switch to "customer" application on "same" devices
    Then Trip Information should be correctly displayed on "ARRIVED" status screen for customer


    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen
    Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for driver


    When I Switch to "customer" application on "same" devices
    Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for customer

    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen
    Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for customer

    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for driver

    When I Switch to "customer" application on "same" devices
    Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for customer

    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details" on Bungii completed page
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen
    And I Select "Logout" from driver App menu


  @regression
  Scenario Outline: I Create and Complete on demand bungii with promo code when driver and customer are login in same device. Promo code :<Scenario>
    Given I am on the "LOG IN" page
 #   When I am on Customer logged in Home page
    When I logged in Customer application using  "valid nashville" user
    And I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "<Promo Code>"
    When I switch to "ORIGINAL" instance

 #   And I clear all notification
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid nashville" driver
    And I Select "HOME" from driver App menu
    Then I change driver status to "Online"
    When I Switch to "customer" application on "same" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location           | Geofence |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States| nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    And Trip Information should be correctly displayed on Estimate screen
    When I select load time as "15" mins
    And I tap "Promo code" on Estimate screen
    And I should be navigated to "PROMOS" screen
    And I add "<Promo Code>" PromoCode
    And I click "ADD" button on "PROMOS" screen
    Then I should able to see expected promo code in available promo code
    When I tap "Back" on Promos screen
    And I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      |          |           |              | Now  | Default     |
    And I request for bungii using Request Bungii Button
    Then I should be navigated to "SEARCHING" screen

    When I click on notification for "Driver" for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    When I click "ACCEPT" button on "Bungii Request" screen
 #   Then I should be navigated to "EN ROUTE" trip status screen
 #   Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen
  #  Then Customer should be navigated to "EN ROUTE" trip status screen
  #  Then Trip Information should be correctly displayed on "EN ROUTE" status screen for customer

    And I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
  #  Then I should be navigated to "ARRIVED" trip status screen
  #  Then Trip Information should be correctly displayed on "ARRIVED" status screen for driver

#    And I Switch to "customer" application on "same" devices
#    Then Customer should be navigated to "ARRIVED" trip status screen
#    Then Trip Information should be correctly displayed on "ARRIVED" status screen for customer


    And I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen
#    Then I should be navigated to "LOADING ITEM" trip status screen
#    Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for driver


 #   And I Switch to "customer" application on "same" devices
  #  Then Customer should be navigated to "LOADING ITEM" trip status screen
  #  Then Trip Information should be correctly displayed on "LOADING ITEM" status screen for customer

    And I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen
  #  Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
  #  Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for driver

 #   And I Switch to "customer" application on "same" devices
 #   Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
 #   Then Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for customer

    And I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
 #   Then I should be navigated to "UNLOADING ITEM" trip status screen
 #   Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for driver

  #  And I Switch to "customer" application on "same" devices
  #  Then Customer should be navigated to "UNLOADING ITEM" trip status screen
  #  Then Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for customer

    And I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEM" Screen
    And I should be navigated to "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details with promo" on Bungii completed page
    And I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen

    Examples:
      | Scenario         | Promo Code      | User         |
      | fixed valid      | {PROMO FIXED}   | no promocode |
      | Promo percentage | {PROMO PERCENT} | no promocode |
      | valid one off    | {valid one off} | no promocode |