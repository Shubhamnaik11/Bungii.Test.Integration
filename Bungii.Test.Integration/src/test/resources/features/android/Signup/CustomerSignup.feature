@android
@MENU

Feature: CustomerSignup
  Sign up as a Customer on Bungii app

  Background:

    Given I am on Sign up page

  @regression
  Scenario: Signup_NoPromo_Success_ReferralSourceCount
    When I open new "Chrome" browser for "ADMIN_PORTAL"
    When I navigate to admin portal
    And I log in to admin portal
    When I Select "Referral Source" from admin sidebar
    Then I get Referral Source info for "OTHER"
    When I switch to "ORIGINAL" instance
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I tap on the "No, Continue" button on Signup Page
    When I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    Then The user should be logged in
    When I switch to "ADMIN_PORTAL" instance
    When I Select "Referral Source" from admin sidebar
    Then account created info for "OTHER" should be "increase by 1"



  @regression
  Scenario: Signup_AllFieldsBlank
    When I enter "blank" customer phone number on Signup Page
    And I enter "blank" data in mandatory fields on Signup Page
    Then the new user should see "sign up button disabled"

  @regression
  Scenario: Signup_InvalidDetails
    When I enter "invalid" customer phone number on Signup Page
    And I enter "invalid" data in mandatory fields on Signup Page
    Then the new user should see "validations for all fields"


  @regression
  Scenario: Signup_SuccessValidPromoCode
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "ValidPercent" promo code on Signup Page
  #  And I enter "Referral" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    Then The user should be logged in
    When I tap on "Menu" > "Promos" link
    Then "ValidPercent" promo code should be displayed


  @regression
  Scenario: Signup_InvalidReferralCode-Yes
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I enter "invalid" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I tap on the "Yes" button on Signup Page
    Then the new user should see "Signup page"
  @regression
  Scenario: Signup_ExistingPhoneNumber
    When I enter "existing" customer phone number on Signup Page
    And I enter "valid" data in mandatory fields on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I tap on the "No, Continue" button on Signup Page
    Then the new user should see "snackbar validation message for existing user"
    And the new user should see "Signup page"