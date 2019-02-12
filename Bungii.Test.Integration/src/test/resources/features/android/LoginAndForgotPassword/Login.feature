@android
@CUST
Feature: Login
  In order to login to bungii
  As a customer and perform functions

  Background:
    Given I am on customer Log in page

  @regression
  Scenario: Cust_Login_ValidCredentials
    When I enter customers "valid" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    Then The user should be logged in

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
    Then The user should see "field validations for password" on log in page
    Then The user should see "login button disabled" on log in page