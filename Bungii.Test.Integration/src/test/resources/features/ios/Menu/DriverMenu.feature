@ios
Feature: DriverMenu
  In Bungii Driver
  As a logged in driver
  I want to check all menu links

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
  @regression
  Scenario: As Bungii driver I should able to access FAQ menu item
    When I Select "FAQ" from driver App menu
    Then I should be navigated to "FAQ" screen
    Then I should be able to see data on "FAQ" page