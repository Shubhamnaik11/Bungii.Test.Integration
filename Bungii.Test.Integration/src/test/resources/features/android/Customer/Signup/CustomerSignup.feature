@android
Feature: CustomerSignup
  Sign up as a Customer on Bungii app

  Background:
    Given I am on Sign up page

  @regression
  Scenario: Verify Referral Source Count Upon Customer Signup
    When I open new "Chrome" browser for "ADMIN_PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Referral Source" from admin sidebar
    Then I get Referral Source info for "OTHER"
    When I switch to "ORIGINAL" instance
    And I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I tap on the "Sign Up" button on Signup Page
#    And I tap on the "No, Continue" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then The user should be logged in
    When I switch to "ADMIN_PORTAL" instance
    And I Select "Referral Source" from admin sidebar
    Then account created info for "OTHER" should be "increase by 1"

  @regression
  Scenario: Verify Customer Signup With All Fields Blank
    When I enter "blank" customer phone number on Signup Page
    And I enter "blank" data in mandatory fields on Signup Page
    Then the new user should see "sign up button disabled"

  @regression

  Scenario: Verify Customer Signup With Invalid Details
    When I enter "invalid" customer phone number on Signup Page
    And I enter "invalid" data in mandatory fields on Signup Page
    Then the new user should see "validations for all fields"

  @email
  @regression
  Scenario: Verify Customer Signup With Valid Promo Code
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "ValidPercent" promo code on Signup Page
  #  And I enter "Referral" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    Then The user should be logged in
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I tap on "Menu" > "Promos" link
    Then "ValidPercent" promo code should be displayed
    When I click on "i" icon
    Then The "Info Message" is displayed
    And Customer should receive signup email

  @regression
  Scenario: Verify Signup With Invalid Referral Code
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "invalid" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I tap on the "Yes" button on Signup Page
    Then the new user should see "Signup page"


  @regression
  Scenario: Verify Signup With Existing Phone Number
    When I enter "existing" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I tap on the "Sign Up" button on Signup Page
  #  And I tap on the "No, Continue" button on Signup Page
    Then the new user should see "snackbar validation message for existing user"
    And the new user should see "Signup page"

  @regression
  Scenario: Verify Signup With Promo Code To Be Active In Future
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "FutureActive" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    Then the new user should see "Inactive Promo Code message"

  @regression
  Scenario: Verify Text On Promos Page When First Time Promo Code Is Added
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "ValidPercent" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then The user should be logged in
    When I tap on "Menu" > "Promos" link
    Then "ValidPercent" promo code should be displayed
    When I enter "PROMO1" promo code
    And I click on "ADD" button
    Then The "This code is only available for your first Bungii." is displayed
    When I click on "i" icon
    Then The "Info" is displayed
    
  @knownissue
  Scenario Outline: Verify Trip completed Count On Admin Portal Is Updated When Customer Completes A Bungii.
      When I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "valid" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    And I Select "HOME" from driver App menu
      And I Switch to "customer" application on "same" devices
    
      When I enter "unique" customer phone number on Signup Page
      And I enter "valid" data in mandatory fields on Signup Page
      And I enter "ValidPercent" promo code on Signup Page
      And I tap on the "Sign Up" button on Signup Page
      And I enter "valid" Verification code
      And I tap on the "Verification Continue" Link
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then The user should be logged in
    
      #creates bungii
      And I enter "kansas pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add "1" photos to the Bungii
      And I add loading/unloading time of "30 mins"
      And I select Bungii Time as "next possible scheduled"
      And I tap on "Request Bungii" on Bungii estimate
      And I click on "OK" button

      #add new payment method
      And I get the number of cards present
      And I tap on "Add" on Payment page
      And I tap on "Credit or Debit Card" on Payment page
      And I enter "<Card Detail>" on Card Details page
      And I enter "<Card Expiry>" on Card Details page
      And I enter "<CVV>" on Card Details page
      And I enter "<Postal Code>" on Card Details page
      And I tap on "Add Card" on Payment page
      Then I should see "the card has been added" on Payment page
      And I tap on "Set as default payment mode" on Payment page
      And I tap on "Save" on Payment page

      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I check if the customer is on success screen
      And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

      And I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE BUNGIIS" from driver App menu

      And I Select Trip from driver available trip
      And I tap on "ACCEPT" on driver Trip details Page
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip

      And Bungii Driver "Start Schedule Bungii" request
      Then Bungii driver should see "Enroute screen"
      And Bungii Driver "slides to the next state"
      And Bungii Driver "slides to the next state"
      And Bungii Driver "slides to the next state"
      And Bungii Driver "slides to the next state"
      And Bungii Driver "slides to the next state"

      And I Switch to "driver" application on "same" devices
      Then Bungii Driver "completes Bungii"
      And I Select "HOME" from driver App menu

      When I open new "Chrome" browser for "ADMIN_PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Customers" from admin sidebar
      And I Search for customer
      Then I verify the trip count

    Examples:
      | Scenario       | Card Detail                | Card Expiry       |CVV|Postal Code|
      | VALID_discover | valid discover card number | valid expiry date |valid cvv|valid postal code|

  #used one off
  #Know issue, no alert
  @regression
  Scenario: Verify That Validation Message Is Displayed On Signing Up With Invalid Or Used One off Promocode
    When I Switch to "customer" application on "same" devices
    And I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I Enter "Referral Code" value in "Referral code" field in "SIGN UP" Page
      | Referral Code |
      | R1D2INVALID   |
    And I Select Referral source
    And I tap on the "Sign Up" button on Signup Page
    Then the new user should see "Invalid Promo Code message"
    
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then The user should be logged in