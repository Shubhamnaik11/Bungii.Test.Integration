@android
Feature: CustomerForgotPassword
  Forgot password functionality in Customer app

  Background:
    Given I am on customer Log in page
    And I tap on the "Forgot Password" Link

  @regression

  Scenario: Cust_ForgotPassword_Success
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    And I enter "valid" SMS code
    And I enter customers new "valid" Password
#    And I tap on the "Continue" Link
    Then The user should see "snackbar validation message for success once I click continue" on forgot password page
    And The user should be logged in
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Cust_ForgotPassword_IncorretPhone
    When I enter "invalid" Phone Number
    And I tap on the "Send" Link
    Then The user should see "snackbar validation message for invalid number" on forgot password page

  @regression
  Scenario: Cust_ForgotPassword_PhoneLessThan10char
    When I enter "less than 10 digit" Phone Number
    Then The user should see "validation for incorrect number" on forgot password page
    And The user should see "Send button disabled" on forgot password page

  @regression
  Scenario: Cust_ForgotPassword_IncorrectSMSCode
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    And I enter "invalid" SMS code
    And I enter customers new "valid" Password
    And I tap on the "Continue" Link
    Then The user should see "snackbar validation message for invalid sms code" on forgot password page

  @regression
  Scenario: Cust_ForgotPassword_PasswordLessThan6Char
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    And I enter "valid" SMS code
    And I enter customers new "invalid" Password
    Then The user should see "validation for incorrect password" on forgot password page

  @regression
  Scenario: Cust_ForgotPassword_ResendSMSCode
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    And I tap on the "Resend Code" Link
    And I enter "valid" SMS code
    And I enter customers new "valid" Password
    And I tap on the "Continue" Link
    Then The user should be logged in

  @regression
  Scenario: Cust_ForgotPassword_PreviousSMSCode_ShouldNotWork
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    And I record the SMS Code
    And I tap on the "Resend Code" Link
    And I enter "previous" SMS code
    And I enter customers new "valid" Password
    And I tap on the "Continue" Link
    Then The user should see "snackbar validation message for invalid sms code" on forgot password page