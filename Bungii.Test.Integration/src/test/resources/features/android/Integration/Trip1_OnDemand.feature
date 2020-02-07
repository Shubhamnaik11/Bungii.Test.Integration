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
    And Customer should receive "Your Bungii Receipt" email

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
    #And Bungii Driver "completes Bungii"
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on the "Bungii Completed" screen

    And I wait for "2" mins
    And I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "trips" from admin sidebar
    And I select trip from trips
    Then On admin trip details page "<Expected value in admin>" should be displayed
    Examples:
      | Scenario            | Promo Code    | User            |Expected value in admin |
      | Promo fixed         | valid         | valid baltimore |promo                   |
      | Promo percentage    | valid percent | valid baltimore |promo                   |
      | valid one off fixed | valid one off | valid baltimore |oneoff                  |

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
  @regression
  Scenario Outline:Referral code signup
    Given I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    And I am logged in as "valid baltimore" customer
    And I tap "Referral Invite link" on Home page
    Then I should see "Referral Code" on Invite Page
    When I get Invite Code
    And I tap on "Back" icon of page
    Then I tap on "Menu" > "Logout" link
    And I Switch to "customer" application on "same" devices
    Given I am on Sign up page
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
 #   And I enter "ValidPercent" promo code on Signup Page
    And I enter "Code" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    Then The user should be logged in
    When I tap on "Menu" > "Promos" link
    And I should able to see expected promo code in available promo code
    Then The "This code is only available for your first Bungii." is displayed
    When I tap on "Menu" > "Payment" link
    And I tap on "Add" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "<Card Detail>" on Card Details page
    And I enter "<Card Expiry>" on Card Details page
    And I enter "<CVV>" on Card Details page
    And I enter "<Postal Code>" on Card Details page

    And I tap on "Add Card" on Payment page
    Then I should see "the card has been added" on Payment page
    And I tap on "Menu" > "Logout" link
    Then I save customer phone and referral code in feature context
    Examples:
      | Scenario       | Card Detail                | Card Expiry       |CVV      |Postal Code      |
      | VALID_discover | valid discover card number | valid expiry date |valid cvv|valid postal code|

  @regression
  Scenario:on demand with referral code
    Given I have customer with referral code
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid baltimore" driver
    And I Select "HOME" from driver App menu
    Then I tap on "Go Online button" on Driver Home page
    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "newly created user" customer

    And I enter "baltimore pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "15 mins"
    And I tap on "Promo code value" on Estimate screen
    Then I should able to see expected promo code in available promo code
    When I tap on "Back" button of android mobile
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    Then I click on notification for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" button on alert message
    And I click "ACCEPT" button on the "Bungii Request" screen

    And I Switch to "customer" application on "same" devices
    When I click "Ok" button on the "BUNGII ACCEPTED" screen

    And I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on the "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    And Bungii customer should see "correct details with promo" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate

  @regression
  Scenario:on demand with referred code promo received
    Given I have customer with referral code
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid baltimore" driver
    And I Select "HOME" from driver App menu
    Then I tap on "Go Online button" on Driver Home page
    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "valid baltimore" customer

    And I enter "baltimore pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    And I tap on "Promo code value" on Estimate screen
    Then I should able to see expected promo code in available promo code
    Then The "referral code received with out first time tag" is displayed
    When I tap on "Back" button of android mobile
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    Then I click on notification for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" button on alert message
    And I click "ACCEPT" button on the "Bungii Request" screen

    And I Switch to "customer" application on "same" devices
    Then I click "Ok" button on the "Bungii Request" screen

    And I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"

    Then Bungii driver should see "correct details" on Bungii completed page
    And Bungii Driver "completes Bungii"

    And I Switch to "customer" application on "same" devices
    And Bungii customer should see "correct details with promo" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate

  @regression
  Scenario:on demand with fb share
    Given that ondemand bungii is in progress
      | geofence  | Bungii State   |
      | baltimore | UNLOADING ITEM |

    And I Switch to "customer" application on "same" devices
    And I am logged in as "valid baltimore" customer

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid baltimore" driver

    And Bungii Driver "slides to the next state"
    Then Bungii Driver "completes Bungii"

    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    When I click "YES, I'LL TAKE $5" button on the "Promotion" screen
    And I share on "Facebook with app installed"

    And I Switch to "customer" application on "same" devices
    And I enter "baltimore pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    And I tap on "Promo code" on Estimate screen
    Then I should able to see expected promo code in available promo code
    And The "referral code received with out first time tag" is displayed
    When I tap on "Back" button of android mobile
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    Then I click on notification for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" button on alert message
    And I click "ACCEPT" button on the "Bungii Request" screen

    And I Switch to "customer" application on "same" devices
    Then I click "Ok" button on the "Bungii Request" screen

    And I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on the "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    And Bungii customer should see "correct details with promo" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate

  @regression
  Scenario Outline: I Create and Complete on demand bungii with promo code when driver and customer are login in same device. Promo code :<Scenario>
    When I am on customer Log in page
    And I am logged in as "valid baltimore" customer
    And I Switch to "driver" application on "same" devices
    And I am logged in as "valid baltimore" driver
    And I Select "HOME" from driver App menu
    Then I tap on "Go Online button" on Driver Home page
    When I Switch to "customer" application on "same" devices

    And I enter "baltimore pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "15 mins"
    And I tap on "Promo code value" on Estimate screen
    And I add "PROMOTER TYPE PROMO" PromoCode
    And I tap "Add" on Save Money page
    Then I should able to see expected promo code in available promo code
    And I tap on "Back" icon of page
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"

    Then I click on notification for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" button on alert message
    And I click "ACCEPT" button on the "Bungii Request" screen

    And I Switch to "customer" application on "same" devices
    When I click "Ok" button on the "BUNGII ACCEPTED" screen

    And I Switch to "driver" application on "same" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on the "Bungii Completed" screen

    And I Switch to "customer" application on "same" devices
    And Bungii customer should see "correct details with promoter" on Bungii completed page
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate

    And I wait for "2" mins
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select trip from live trips
    Then On admin trip details page "<Expected value in admin>" should be displayed

    Examples:
      | Expected value in admin |
      | promo                   |

