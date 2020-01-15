@android

  @ondemand
  #These feature will run in baltimore geofence
Feature: On Demand Bungii


  @regression
  Scenario: Validate That I am able to create on demand bungii. Also Validate that Correct contact number is displayed on Call and SMS Option

    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | baltimore   | Enroute      |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
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

  @regression
  Scenario:Manually end Bungii option should only be available in the last 3 states and Not in the first two.
    Given that ondemand bungii is in progress
      | geofence  | Bungii State |
      | baltimore | Enroute      |

    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid baltimore" customer

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid baltimore" driver
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

     #this scenario is moved from signup to ondemand feature as we can use test data generated in this test case
  @regression1
  Scenario:Referral code signup
    Given I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I am logged in as "valid baltimore" customer
    And I tap "Referral Invite link" on Home page
    Then I should see "Referral Code" on Invite Page
    When I get Invite Code
    And I click on "Back" button of android mobile
    Then I tap on "Menu" > "Logout" link
    And I Switch to "customer" application on "same" devices
    Given I am on Sign up page
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "ValidPercent" promo code on Signup Page
    And I enter "Code" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    Then The user should be logged in
    When I tap on "Menu" > "Promos" link
    And I should able to see expected promo code in available promo code
    Then I should see "first time code subtext" on Promos page
#    When I Select "PAYMENT" from Customer App menu
#    Then I should be navigated to "PAYMENT" screen
#    When I click "Add-Button" button on "PAYMENT" screen
#    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
#    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
#    And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
#    Then I should see "new card" on Payment page
#    When I Select "LOGOUT" from Customer App menu
#    Then I save customer phone and referral code in feature context
#
#    Examples:
#      | First Name                 | Last Name       | Email ID                        | Phone Number       | Password | Promo Code    | Source   | CardNo        | Expiry | Postal Code       | Cvv       |
#      | Testcustomertywd_apple_REFC | {RANDOM_STRING} | richa.naik@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 | REFERRAL CODE | facebook | DISCOVER CARD | 12/22  | VALID POSTAL CODE | VALID CVV |


  @regression
  Scenario:on demand with referral code
    Given I have customer with referral code
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid baltimore" driver
    And I Select "HOME" from driver App menu
    Then I tap on "Go Online button" on Driver Home page
    When I Switch to "customer" application on "same" devices
    #When I am on the "LOG IN" page
    And I logged in Customer application using  "newly created user" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | baltimore |
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


  @regression
  Scenario:on demand with referred code promo received
    Given I have customer with referral code received
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid baltimore" driver
    And I Select "HOME" from driver App menu
    Then I change driver status to "Online"
    When I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid baltimore" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | baltimore |
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
  Scenario:on demand with fb share
    Given that ondemand bungii is in progress
      | geofence  | Bungii State   |
      | baltimore | UNLOADING ITEM |

    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid baltimore" user
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid baltimore" driver
    And I slide update button on "UNLOADING ITEM" Screen
    And I should be navigated to "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And I click "CLOSE BUTTON" button on "Bungii Complete" screen
    When I click "YES, I'LL TAKE $5" button on "Promotion" screen
    When I enter "valid data" on Overlay Facebook screen
    And I tap "Next" button on Overlay Facebook screen
    When I tap "Share" button on Overlay Facebook screen

    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                                        | Geofence  |
      | Solo   | Nashville International Airport | 5629 Nashville Rd, Franklin, KY 42134, United States | baltimore |
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