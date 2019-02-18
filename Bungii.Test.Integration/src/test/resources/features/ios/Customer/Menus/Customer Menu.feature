@ios
Feature: Customer	Menu
  As a Bungii customer I want to check all menu links

  Background: 
    Given I am on Customer logged in Home page

  @regression
  Scenario: As Bungii customer I want to check all menu links
    When I Select "Home" from Customer App menu
    Then I should be navigated to "Home" screen
    When I Select "FAQ" from Customer App menu
    Then I should be navigated to "FAQ" screen
    When I Select "ACCOUNT" from Customer App menu
    Then I should be navigated to "ACCOUNT" screen
    When I Select "SCHEDULED BUNGIIS" from Customer App menu
    Then I should be navigated to "SCHEDULED BUNGIIS" screen
    When I Select "PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    When I Select "SUPPORT" from Customer App menu
    Then I should be navigated to "SUPPORT" screen
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I Select "DRIVE WITH BUNGII" from Customer App menu
    Then I should be navigated to "bungii.com" screen
    When I Switch to "customer" application on "same" devices
    When I Select "LOGOUT" from Customer App menu
    Then I should be navigated to "LOG IN" screen

  @regression
  Scenario: As Bungii customer I want to Ask question using support menu
    When I Select "SUPPORT" from Customer App menu
    Then I should be navigated to "SUPPORT" screen
    Then "Support Question" should be present in "SUPPORT" screen
    Then "Bungii Customer Logo" should be present in "SUPPORT" screen
    When I Enter "{RANDOM_STRING}" value in "Support Textbox" field in "SUPPORT" Page
    And I click "SEND" button on "SUPPORT" screen
    Then user is alerted for "SUPPORT QUESTION SUBMITTED"

  @regression
  Scenario: As Bungii customer I dont enter any question and click on send button
    When I Select "SUPPORT" from Customer App menu
    Then I should be navigated to "SUPPORT" screen
    When I Enter "{EMPTY}" value in "Support Textbox" field in "SUPPORT" Page
    And I click "SEND" button on "SUPPORT" screen
    Then user is alerted for "EMPTY SUPPORT QUESTION"

  @regression
  Scenario: As Bungii customer I want to check FAQ
    When I Select "FAQ" from Customer App menu
    Then I should be navigated to "FAQ" screen
    Then I should see "faq image" on FAQ page
    When I tap on "first question" on FAQ page
    Then I should see "first answer dropdown open" on FAQ page
    When I tap on "first question" on FAQ page
    Then I should see "first answer dropdown close" on FAQ page
    Then I should see "social media links" on FAQ page
