@ios
  # this will run in 	nashville
Feature: Create on demand bungii

  @regression
  Scenario:Manually end Bungii option should only be available in the last 3 states and Not in the first two.
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | nashville | Enroute      |

    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I wait for "2" mins
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select trip from live trips

    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Trip Started"
    Then manually end bungii should be "disabled"

    When I switch to "ORIGINAL" instance
    And I slide update button on "EN ROUTE" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Driver(s) Arrived"
    Then manually end bungii should be "disabled"

    When I switch to "ORIGINAL" instance
    And I slide update button on "ARRIVED" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Loading Items"
    Then manually end bungii should be "enabled"

    When I switch to "ORIGINAL" instance
    And I slide update button on "LOADING ITEM" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Driving To Dropoff"
    Then manually end bungii should be "enabled"

    When I switch to "ORIGINAL" instance
    And I slide update button on "DRIVING TO DROP OFF" Screen
    When I switch to "ADMIN" instance
    Then I wait for trip status to be "Unloading Items"
    Then manually end bungii should be "enabled"

    When I switch to "ORIGINAL" instance
    And I slide update button on "UNLOADING ITEM" Screen
    And I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    And I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen

  @sanity
  @regression
  Scenario: I Create and Complete on demand bungii when driver and customer are login in same device. verify pickup status highlight
    Given I am on the "LOG IN" page
   # When I am on Customer logged in Home page
    When I logged in Customer application using  "valid nashville" user
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
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
      | geofence  | Bungii State |
      | nashville | Enroute      |

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
      | geofence  | Bungii State |
      | nashville | Enroute      |

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

  @FAILED2602

  @regression
  Scenario Outline: I Create and Complete on demand bungii with promo code when driver and customer are login in same device. Promo code :<Scenario>
    Given I am on the "LOG IN" page
    When I logged in Customer application using  "<User>" user
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid nashville" driver
    And I Select "HOME" from driver App menu
    Then I change driver status to "Online"
    When I Switch to "customer" application on "same" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
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

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen

    And I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I should be navigated to "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "<Expected Details>" on Bungii completed page
    And I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen
    Then I wait for "3" mins

    And I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "trips" from admin sidebar
    And I select trip from trips
    Then On admin trip details page "<Expected value in admin>" should be displayed
    Examples:
      | Scenario         | Promo Code        | User                       | Expected Details           | Expected value in admin |
      | fixed valid      | PROMO DOLLAR OFF  | valid nashville            | correct details with promo | promo                   |
      | Promo percentage | PROMO PERCENT OFF | valid nashville            | correct details with promo | promo                   |
      | valid one off    | ONE OFF           | valid nashville            | correct details with promo | oneoff                  |
      | First time       | FIRST TIME        | valid nashville first time | correct details with promo | promo                   |

  @regression
  Scenario Outline: I Create and Complete on demand bungii with promo code when driver and customer are login in same device. PROMOTER_TYPE_PROMO
    Given I am on the "LOG IN" page
    When I logged in Customer application using  "<User>" user
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid nashville" driver
    And I Select "HOME" from driver App menu
    Then I change driver status to "Online"
    When I Switch to "customer" application on "same" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
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

    Then I click on notification for "Customer" for "DRIVER ENROUTE"
 #   And I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    Then ratting should be correctly displayed on Bungii accepted page
    When I click "Ok" button on "BUNGII ACCEPTED" screen

    And I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I slide update button on "UNLOADING ITEM" Screen
    And I should be navigated to "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "<Expected Details>" on Bungii completed page
    And I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen
  #  Then I wait for "2" mins

    And I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select trip from trips
    Then On admin trip details page "<Expected value in admin>" should be displayed
    Examples:
      | Scenario         | Promo Code        | User                       | Expected Details           | Expected value in admin |
      | PROMOTER_TYPE_PROMO | PROMOTER TYPE PROMO | valid nashville | correct details with delivery promo | promoter |

  @regression
  Scenario: Check if Driver rating is correctly shown on customer app when Bungii is in progress
    Given that ondemand bungii is in progress
      | geofence  | Bungii State   |
      | nashville | UNLOADING ITEM |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    Then ratting should be correctly displayed on Bungii progress page

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  @FAILED2602

  @regression
  Scenario:on demand with fb share
    Given that ondemand bungii is in progress
      | geofence  | Bungii State   |
      | nashville | UNLOADING ITEM |

    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I slide update button on "UNLOADING ITEM" Screen
    And I should be navigated to "Bungii Completed" screen
    And I click "On To The Next One" button on "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "YES, I'LL TAKE $5" button on "Promotion" screen
    When I enter "valid data" on Overlay Facebook screen
  #  And I tap "Next" button on Overlay Facebook screen
  #  When I tap "Share" button on Overlay Facebook screen
    When I tap "Post" button on Overlay Facebook screen

    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I select load time as "15" mins
    And I tap "Promo code" on Estimate screen
    And I should be navigated to "PROMOS" screen
    Then I should able to see facebook promo code in available promo code
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

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen

    And I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
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


  @FAILED2602

  #this scenario is moved from signup to ondemand feature as we can use test data generated in this test case
  @regression
  Scenario Outline:Referral code signup
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    And I click "Invite referrals" button on "HOME" screen
    Then I should be navigated to "Invite" screen
    When I get Invite Code
    When I Select "LOGOUT" from Customer App menu
    Given I am on the "SIGN UP" page
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Promo Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    Then I should be navigated to "Home" screen
    When I Select "PROMOS" from Customer App menu
    And I should able to see expected promo code in available promo code
    Then I should see "first time code subtext" on Promos page
    When I Select "PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    When I click "Add-Button" button on "PAYMENT" screen
    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
    And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
    Then I should see "new card" on Payment page
    When I Select "LOGOUT" from Customer App menu
    Then I save customer phone and referral code in feature context

    Examples:
      | First Name                 | Last Name       | Email ID                        | Phone Number       | Password | Promo Code    | Source   | CardNo        | Expiry | Postal Code       | Cvv       |
      | Testcustomertywd_appleREFC | {RANDOM_STRING} | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 | REFERRAL CODE | facebook | DISCOVER CARD | 12/22  | VALID POSTAL CODE | VALID CVV |
  @FAILED2602

  @regression
  Scenario:on demand with referral code
    Given I have customer with referral code
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid nashville" driver
    And I Select "HOME" from driver App menu
    Then I change driver status to "Online"
    When I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in Customer application using  "newly created user" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I select load time as "15" mins
    And I tap "Promo code" on Estimate screen
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

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen

    And I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
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
  @FAILED2602

  @regression
  Scenario:on demand with referred code promo received
    Given I have customer with referral code received
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid nashville" driver
    And I Select "HOME" from driver App menu
    Then I change driver status to "Online"
    When I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I select load time as "15" mins
    And I tap "Promo code" on Estimate screen
    Then I should able to see expected promo code in available promo code
    Then I should see "referral code received with out first time tag" on Promos page
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

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen

    And I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I slide update button on "LOADING ITEM" Screen
    And I slide update button on "DRIVING TO DROP OFF" Screen
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

  @regression
  Scenario: Check if customer is allowed to rate driver for solo trip
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | nashville | UNLOADING ITEM      |

    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I slide update button on "UNLOADING ITEM" Screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct rating detail for solo" on Bungii completed page
    When I select "3" Ratting star for solo Driver 1
    Then "3" starts should be highlighted for solo Driver 1
    When I click "OK" button on "BUNGII COMPLETE" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then poor driver ratting should be sent to customer


  @regression
  Scenario:DRIVER Notification - Tip
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | nashville | UNLOADING ITEM      |

    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid nashville" user
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I slide update button on "UNLOADING ITEM" Screen
    And I click on notification for "customer" for "BUNGII FINISHED -RATE DRIVER"

    When I rate Bungii Driver  with following details and Press "OK" Button
      | Ratting | Tip |
      | 5       | 5   |
    And I click on notification for "Driver" for "TIP RECEIVED 5 DOLLAR"

    And I click "On To The Next One" button on "Bungii Completed" screen
