@Android

Feature: CustomerForgotPassword
  Forgot password functionality in Customer app

  Background:
    Given I am on Sign up page
    When I tap on the "Login" Link
    And I tap on the "Forgot Password" Link
  @regression
  Scenario: Cust_ForgotPassword_Success
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    When I enter "valid" SMS code
    And I enter customers new "valid" Password
    And I tap on the "Continue" Link
    Then The user should see "snackbar validation message"
    And The user should be logged in
  @regression
  Scenario: Cust_ForgotPassword_IncorretPhone
    When I enter "invalid" Phone Number
    And I tap on the "Send" Link
    Then The user should see "snackbar validation message"
  @regression
  Scenario: Cust_ForgotPassword_PhoneLessThan10char
    When I enter "less than 10 digit" Phone Number
    And I tap on the "Send" Link
    Then The user should see "Send button disabled"
  @regression
  Scenario: Cust_ForgotPassword_IncorrectSMSCode
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    When I enter "invalid" SMS code
    And I tap on the "Continue" Link
    Then The user should see "snackbar validation message"
  @regression
  Scenario: Cust_ForgotPassword_PasswordLessThan6Char
    When I enter "valid" Phone Number
    And I tap on the "Send" Link
    When I enter "valid" SMS code
    And I enter customers new "invalid" Password
    Then The user should see "validation for incorrect password"