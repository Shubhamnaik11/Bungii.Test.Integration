@android
Feature: CustomerMenu
  In Bungii Customer
  As a logged in customer
  I want to check all menu links

  Background:
    Given I am on Customer logged in Home page

  @regression
  Scenario: As Bungii customer I should able to access menu items
    When I tap on "Menu" > "Home" link
    Then "Home" page should be opened
    When I tap on "Menu" > "SIGN UP TO DRIVE" link
    Then "bungii.com" page should be opened
    When I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "Logout" link
    Then "Login" page should be opened
#failing due to BCKD-1103
  @regression
  Scenario: Logout (check deregister device token) (Driver & Customer)
    Then Customer active flag should be "1"
    And I tap on "Menu" > "Logout" link
    Then Customer active flag should be "0"

  @regression
  Scenario: As Bungii customer I should able to access FAQ page
    When I tap on "Menu" > "FAQ" link
    Then "FAQ" page should be opened
  #  When I tap on "first question" on FAQ page
    Then I should see "first answer dropdown open" on FAQ page
    When I tap on "expanded first question" on FAQ page
    Then I should see "first answer dropdown close" on FAQ page
    And I should see "last question" on FAQ page
  #  And I should see "social media links" on FAQ page

  @regression
  Scenario:As Bungii customer I should able to access account page
    Given I am logged in as "valid" customer
    When I tap on "Menu" > "Account" link
    Then "Account" page should be opened
    And logged in Customer details should be displayed

  @regression
  Scenario: Cust_Menu_Payment
    When I tap on "Menu" > "Payment" link
    Then "Payment" page should be opened

  @regression
  Scenario:As Bungii customer I should able to access support page
    When I tap on "Menu" > "Support" link
    Then "Support" page should be opened

  @regression
  Scenario: As Bungii customer I should able to access promos page
    When I tap on "Menu" > "Promos" link
    Then "Promos" page should be opened