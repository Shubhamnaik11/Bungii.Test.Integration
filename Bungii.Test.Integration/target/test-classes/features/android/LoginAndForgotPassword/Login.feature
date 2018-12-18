Feature: Login
  In order to login to bungii
  As a customer and perform functions

  Background:
    Given I am on Sign up page
    When I tap on the "Login" Link
  @Android
  Scenario: Cust_Login_ValidCredentials
    When I enter customers "valid" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button
    Then The user should be logged in
  @Android
  Scenario: Cust_Login_InvalidCredentials
    When I enter customers "invalid" Phone Number
    And I enter customers "invalid" Password
    And I tap on the "Log in" Button
    Then The user should see "snackbar validation message"
    When I enter customers "blank" Phone Number
    And I enter customers "blank" Password
    And I enter customers "blank" Phone Number
    Then The user should see "field validations"
    And The user should see "login button disabled"
  @Android
  Scenario: Cust_Login_EmptyCredentials
    When I enter customers "blank" Phone Number
    And I enter customers "blank" Password
    Then The user should see "login button disabled"