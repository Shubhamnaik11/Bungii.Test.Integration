Feature: testfeature
  In order to login to bungii
  As a customer and perform functions

  Background:
    Given I am on Sign up page1

  @Android1
  Scenario: Cust_Login_ValidCredentials2
    Then I enter customers "valid" Phone Number
