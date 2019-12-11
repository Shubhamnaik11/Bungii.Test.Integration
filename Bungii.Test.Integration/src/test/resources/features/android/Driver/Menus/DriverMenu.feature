@android
Feature: DriverMenu
  In Bungii Driver
  As a logged in driver
  I want to check all menu links

  Background:
    Given I am on Driver logged in Home page

  @regression
  Scenario: As Bungii driver I should able to access FAQ menu item
    When I Select "FAQ" from driver App menu
    And the "FAQ" page is opened
    Then I should be able to see data on "FAQ" page

  @regression
  Scenario: As Bungii driver I should able to access FAQ menu item
    When I Select "LEADERBOARD" from driver App menu
    And the "LEADERBOARD" page is opened
    Then I should be able to see data on "LEADERBOARD" page

  @regression
  Scenario: As Bungii driver I should able to access SCHEDULED BUNGIIS menu item
    When I Select "SCHEDULED BUNGIIS" from driver App menu
    And the "SCHEDULED BUNGIIS" page is opened
    Then I should be able to see data on "SCHEDULED BUNGIIS" page

  @regression
  Scenario: As Bungii driver I should able to access AVAILABLE TRIPS menu item
    When I Select "AVAILABLE TRIPS" from driver App menu
    And the "AVAILABLE TRIPS" page is opened
    Then I should be able to see data on "AVAILABLE TRIPS" page

  @regression
  Scenario: As Bungii driver I should able to access EARNINGS menu item
    When I Select "EARNINGS" from driver App menu
    And the "EARNINGS" page is opened
    Then I should be able to see data on "EARNINGS" page

  @regression
  Scenario: As Bungii driver I should able to access ACCOUNT menu item
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    Then I should be able to see data on "ACCOUNT" page

  @regression
  Scenario: As Bungii driver I should able to access TRIP ALERT SETTINGS menu item
    When I Select "TRIP ALERT SETTINGS" from driver App menu
    And the "TRIP ALERT SETTINGS" page is opened
    Then I should be able to see data on "TRIP ALERT SETTINGS" page

  @regression
  Scenario: As Bungii driver I should able to access STORE menu item
    When I Select "STORE" from driver App menu
    And the "STORE" page is opened
    Then I should be able to see data on "STORE" page

  @regression
  Scenario: As Bungii driver I should able to access LOGOUT menu item
    When I Select "LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page