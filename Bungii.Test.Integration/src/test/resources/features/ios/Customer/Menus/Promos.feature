@ios

Feature: Promos
  As a Bungii customer
  I Should able to add new promo code

  Background:
    Given I am on Customer logged in Home page

  @sanity
  @regression
  Scenario Outline:As a existing bungii customer , I should not be allowed to use First time only Promo code
    When I logged in Customer application using  "existing" user
    And I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I Enter "<Promo>" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen
    Then user is alerted for "<Expected Message>"
    And I Select "LOGOUT" from Customer App menu

#added promo code in
    Examples:
      | Scenario | Promo           | Expected Message      |
      | Invalid | first time only | FIRST TIME ONLY PROMO |

  @regression
  Scenario Outline: As a Bungii Customer , I should be alert while adding invalid promo code
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I Enter "<Promo>" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen
    Then user is alerted for "<Expected Message>"

    Examples:
      | Scenario | Promo   | Expected Message |
      | Invalid | AAAAAAA | Invalid Promo    |

  @regression
  Scenario Outline: As a Bungii Customer , I should not able to add Referral promo code after creating account . I Should be alerted that Referral code are for new customer only

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal

    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "referral"
    And I switch to "ORIGINAL" instance

    And I logged in Customer application using  "<User Type>" user
    And I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "Referral" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "<Expected Message>"
    And I Select "LOGOUT" from Customer App menu
    Examples:
      | Scenario                           | User Type | Expected Message      |
      | User already having REFERRAL code | referral  | REFERRAL FOR NEW USER |
      | New user (with out REFERRAL code)  | new       | REFERRAL FOR NEW USER |

  @regression
  Scenario: As a Bungii Customer , I should be alerted while added used one off promo code
  #  When I open new "Chrome" browser for "ADMIN PORTAL"
  #  When I navigate to admin portal
  #  And I log in to admin portal
  #  When I Select "Promo Code" from admin sidebar
  #  Then I get promo code for "USED ONE OFF"
  #  When I switch to "ORIGINAL" instance
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I Enter "USED ONE OFF" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen
    Then user is alerted for "Invalid Promo"

  @regression
  Scenario: As a Bungii Customer , I should be alerted while adding already existing code
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "VALID"
    And I switch to "ORIGINAL" instance
    And I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I add "VALID" PromoCode
    And I click "ADD" button on "PROMOS" screen
    Then I should able to see expected promo code in available promo code
    When I add Same Promo Code again
    And I click "ADD" button on "PROMOS" screen
    Then user is alerted for "Already Existing Code"

  @regression
  Scenario: When i try to enter expired promo code I should be alerted for Expired Promo code message
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "expired"
    When I switch to "ORIGINAL" instance
    And I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I Enter "expired" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen
    Then user is alerted for "EXPIRED PROMO"
  @notwitter
 # @regression
  Scenario: When i try to share my promo code , via twitter but there is no application installed then I should be alerted for No twitter Installed message
    Given I have "twitter" app "not installed"
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I click "GET MORE MONEY" button on "PROMOS" screen
    Then I should be navigated to "Invite" screen
    Then I get Invite Code
    When I click "SHARE" button on "INVITE" screen
    And I click "SHARE ON TWITTER" button on "INVITE" screen
    Then user is alerted for "No twitter installed"
    And I should be navigated to "Invite" screen

  @regression
  Scenario: Menu_SaveMoney_ReferralInvite_Facebook_AppInstalled
    Given I have "facebook" app "installed"
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I click "GET MORE MONEY" button on "PROMOS" screen
    Then I should be navigated to "Invite" screen
    Then I get Invite Code
    When I click "SHARE" button on "INVITE" screen
    And I click "SHARE ON FACEBOOK" button on "INVITE" screen
    Then I should see "popup to post" Overlay Facebook screen
    When I enter "valid data" on Overlay Facebook screen
  #  And I tap "Next" button on Overlay Facebook screen
    And I tap "POST" button on Overlay Facebook screen
#    When I tap "Share" button on Overlay Facebook screen
    Then I should be navigated to "Invite" screen

  @regression
  Scenario:To check the text in i on Promos page. (when any promo code is present)
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "VALID"
    And I switch to "ORIGINAL" instance
    And I am on the "LOG IN" page
#    When I enter Username :8888889917 and  Password :{VALID}
    When I enter Username :9999999923 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "PROMOS" from Customer App menu
    And I add "VALID" PromoCode
    And I click "ADD" button on "PROMOS" screen
    When I click "INFO" button on "PROMOS" screen
    Then user is alerted for "MINIMUM COST STILL APPLIES"

  @regression
  Scenario: Check that the 'i' text changes when first time use only promo or referral code is present in Promos page
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "VALID"
    And I switch to "ORIGINAL" instance
    And I am on the "LOG IN" page
 #   When I enter Username :8877995502 and  Password :{VALID}
    When I enter Username :9999990015 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "PROMOS" from Customer App menu
    When I Enter "first time only" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen
    When I click "INFO" button on "PROMOS" screen
    Then user is alerted for "FIRST TIME PROMO CODE"


  @regression
  Scenario:First time promo code/referral code, if present should be selected by default.
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "VALID"
    And I switch to "ORIGINAL" instance
    And I am on the "LOG IN" page
  #  When I enter Username :8877995500 and  Password :{VALID}
    When I enter Username :8877995508 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "PROMOS" from Customer App menu
    When I Enter "first time only" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen
    And I add "VALID" PromoCode
    And I click "ADD" button on "PROMOS" screen
    And I Select "HOME" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                | Geofence  |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa | goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should see "first time only" code selected on Bungii estimate
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | No image     |
    And I click "PROMO CODE LINE" button on "Estimate" screen
    And I click added "VALID" promo code from available promo code
    Then user is alerted for "CHOSSING NON FIRST TIME CODE"
    Then I should be navigated to "Estimate" screen
    Then I should see "selected" code selected on Bungii estimate



# add promo from app menu and verify on Estimate page and vice versa
  @regression
  Scenario:Check that Promos can be added from menu and Estimate page
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "VALID"
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "one off"
    And I switch to "ORIGINAL" instance
    And I am on the "LOG IN" page
    When I enter Username :8877995512 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "PROMOS" from Customer App menu
    When I Enter "one off" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen

    And I Select "HOME" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                | Geofence  |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa | goa |
    And I click "Get Estimate" button on "Home" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    And I click "PROMO CODE LINE" button on "Estimate" screen
    Then I should able to see expected promo code in available promo code

    When I Enter "VALID" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen

    When I tap "Back" on Promos screen
    And I click "Cancel" button on "Estimate" screen
    And I Select "PROMOS" from Customer App menu
    Then I should able to see expected promo code in available promo code

  @regression
  Scenario: Cancel after using Promo code, should Not get utilized

    And I am on the "LOG IN" page
    And I enter Username :8877995512 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                | Geofence  |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa | goa |
    And I click "Get Estimate" button on "Home" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    And I click "PROMO CODE LINE" button on "Estimate" screen
    And I Enter "promocode" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen

    When I tap "Back" on Promos screen
    And I should be navigated to "Estimate" screen
    And I request for bungii using Request Bungii Button
    Then I should be navigated to "SEARCHING" screen
    And I click "Cancel" button on "SEARCHING" screen
    Then user is alerted for "CANCEL BUNGII"
    And I should be navigated to "Home" screen
    And I Select "PROMOS" from Customer App menu
    Then I should able to see expected promo code in available promo code
  @FAILED
  @regression
  Scenario: Re-search after using Promo code, should be used for re-searched trip
    Given I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I Switch to "customer" application on "ORIGINAL" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 3650 New Center Point Colorado Springs  | denver   |

    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen

    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | NEXT_POSSIBLE  | Default     |
      And I click "PROMO CODE LINE" button on "Estimate" screen
    #  And I Enter "PROMOCODE" value in "Promo Code" field in "Promo" Page
    And I add "PROMO PERCENT OFF" PromoCode
      And I click "ADD" button on "PROMOS" screen
      When I tap "Back" on Promos screen
      And I should be navigated to "Estimate" screen
      And I request for bungii using Request Bungii Button
      Then I should be navigated to "Success" screen
      And I click "Done" button on "Success" screen
      And I Select "Home" from Customer App menu

      When I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE TRIPS" from driver App menu
      And I Select Trip from available trip
      Then I should be navigated to "TRIP DETAILS" screen
      And Trip Information should be correctly displayed on TRIP DETAILS screen
      When I accept selected Bungii
      Then I wait for "2" mins

      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I remove current driver and researches Bungii

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE TRIPS" from driver App menu
      And I Select Trip from available trip
      Then I should be navigated to "TRIP DETAILS" screen
      And Trip Information should be correctly displayed on TRIP DETAILS screen
      When I accept selected Bungii
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from scheduled trip
      Then I should be navigated to "BUNGII DETAILS" screen
      And I start selected Bungii
      When I slide update button on "EN ROUTE" Screen
      When I slide update button on "ARRIVED" Screen
      And I Switch to "customer" application on "same" devices
      And I Switch to "driver" application on "same" devices


      When I slide update button on "LOADING ITEM" Screen
      When I slide update button on "DRIVING TO DROP OFF" Screen
      When I slide update button on "UNLOADING ITEM" Screen
      Then I should be navigated to "Bungii Completed" screen

      And I Switch to "customer" application on "same" devices
      Then I wait for "2" mins
      Then I should be navigated to "Bungii Complete" screen
      And Bungii customer should see "correct details with promo" on Bungii completed page
      And I click "CLOSE BUTTON" button on "Bungii Complete" screen
      Then I should be navigated to "Promotion" screen
      When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
      Then I should be navigated to "Home" screen


  @regression
  Scenario Outline: Already applied Promo code used after its expiry

    And I am on the "LOG IN" page
    And I enter Username :8805368850 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location            | Drop Location                | Geofence  |
      | Solo   | Margao Railway Overbridge  | Old Goa Road, Velha Goa, Goa | goa |
    And I click "Get Estimate" button on "Home" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    And I click "PROMO CODE LINE" button on "Estimate" screen
    Then I should see the "<expired promo code>" no more displayed on the promos page
    Examples:
      | expired promo code   |
      | PREXP01              |






















