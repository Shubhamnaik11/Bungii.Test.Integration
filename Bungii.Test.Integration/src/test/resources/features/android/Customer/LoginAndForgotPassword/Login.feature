@android
Feature: Login
  In order to login to bungii
  As a customer and perform functions

  Background:
    Given I am on customer Log in page
  @sanity
  @regression
  Scenario: Cust_Login_ValidCredentials
    When I enter customers "valid" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should be logged in
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Cust_Login_InvalidCredentials
    When I enter customers "invalid" Phone Number
    And I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "snackbar validation message invalid password" on log in page

  @regression
  Scenario: Cust_Login_EmptyCredentials
    When I enter customers "blank" Phone Number
    And I enter customers "blank" Password
    Then The user should see "field validations for phone number" on log in page
    And The user should see "field validations for password" on log in page
    And The user should see "login button disabled" on log in page

  @regression
  Scenario: Cust_Login_EmptyPhoneNumber
    When I enter customers "blank" Phone Number
    And I enter customers "valid" Password
    Then The user should see "field validations for phone number" on log in page
    And The user should see "login button disabled" on log in page

  @regression
  Scenario: Cust_Login_EmptyPassword
    When I enter customers "blank" Password
    And I enter customers "valid" Phone Number
    Then The user should see "field validations for password" on log in page
    And The user should see "login button disabled" on log in page

  @regression
  Scenario: As Bungii customer , I should be shown terms and condition page on first time login ( Reset data of app to simulate fresh install)
    Given I newly installed "Bungii Customer" app
    When I tap on the "Log in" button on Signup Page
    And I enter customers "valid" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then "Terms and Conditions" page should be opened
    And I should see "all details" on Term and Condition agreement
    When I accept Term and Condition agreement
    Then "Tutorial" page should be opened
    When I close tutorial Page
    Then The user should be logged in
    And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Cust_Login_InvalidPassword_FiveTimes
    When I enter customers "Valid_ToBeLocked" Phone Number
    And I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "snackbar validation message invalid password" on log in page
    When I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "snackbar validation message invalid password" on log in page
    When I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "Invalid login credentials. 3 out of 5 attempts exhausted message" on log in page
    When I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "snackbar validation message invalid password" on log in page
    When I enter customers "invalid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should see "Invalid login credentials. Your account has been locked message" on log in page
    And I tap on the "Forgot Password" Link
    When I enter "Valid_ToBeLocked" Phone Number
    And I tap on the "Send" Link
    And I enter "valid code for locked" SMS code
    And I enter customers new "valid" Password
    And I tap on the "Continue" Link
 #   Then The user should see "snackbar validation message for success once I click continue" on forgot password page
    And The user should be logged in
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened
