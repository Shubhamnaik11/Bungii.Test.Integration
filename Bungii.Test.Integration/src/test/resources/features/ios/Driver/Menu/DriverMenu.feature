@ios
Feature: DriverMenu
  In Bungii Driver
  As a logged in driver
  I want to check all menu links

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver

  @regression
  Scenario: As Bungii driver I should able to access FAQ menu item
    When I Select "FAQ" from driver App menu
    Then I should be navigated to "FAQ" screen
    Then I should be able to see data on "FAQ" page

  @regression
  Scenario: As Bungii driver I should able to access LEADERBOARD menu item
    When I Select "LEADERBOARD" from driver App menu
    Then I should be navigated to "LEADERBOARD" screen
    Then I should be able to see data on "LEADERBOARD" page


  @regression
  Scenario: As Bungii driver I should able to access SCHEDULED BUNGII menu item
    When I Select "SCHEDULED BUNGIIS" from driver App menu
    Then I should be navigated to "SCHEDULED BUNGII" screen
    Then I should be able to see data on "SCHEDULED BUNGII" page



  @regression
  Scenario: As Bungii driver I should able to access AVAILABLE TRIPS menu item
    When I Select "AVAILABLE TRIPS" from driver App menu
    Then I should be navigated to "AVAILABLE TRIPS" screen
    Then I should be able to see data on "AVAILABLE TRIPS" page

  @regression
  Scenario: As Bungii driver I should able to access EARNINGS menu item
    When I Select "EARNINGS" from driver App menu
    Then I should be navigated to "EARNINGS" screen
    Then I should be able to see data on "EARNINGS" page
  
  @regression
  Scenario: As Bungii driver I should able to access EARNINGS menu item
    When I Select "EARNINGS" from driver App menu
    Then I should be navigated to "EARNINGS" screen
    When I click "ITEMIZED EARNINGS" button on "Home" screen on driverApp
    Then I should be able to see data on "ITEMIZED EARNINGS" page


  @regression
  Scenario: As Bungii driver I should able to access ACCOUNT menu item
    When I Select "ACCOUNT" from driver App menu
    Then I should be navigated to "ACCOUNT" screen
    Then I should be able to see data on "ACCOUNT" page

  @regression
  Scenario: As Bungii driver I should able to access TRIP ALERT SETTINGS  menu item
    When I Select "TRIP ALERT SETTINGS" from driver App menu
    Then I should be navigated to "TRIP ALERT SETTINGS" screen
    Then I should be able to see data on "TRIP ALERT SETTINGS" page

  @regression
  Scenario: As Bungii driver I should able to access LOGOUT  menu item
    When I Select "LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    Then I should be navigated to "LOG IN" screen

  @regression
  Scenario: As Bungii driver I should able to access STORE  menu item
    When I Select "STORE" from driver App menu
    Then I should be navigated to "STORE" screen
    Then I should be able to see data on "STORE" page

  @regression
  Scenario: As Bungii driver I should able to access FEEDBACK  menu item
    When I Select "FEEDBACK" from driver App menu
    Then I should be navigated to "FEEDBACK" screen
    Then I should be able to see data on "FEEDBACK" page


  @regression
  Scenario: Trip Alert Settings : Trip Alerts tab ( default : 7.00AM- 9.00PM)
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "new driver" driver
    When I Select "TRIP ALERT SETTINGS" from driver App menu
    When I click "SMS ALERT" button on "TRIP ALERT SETTINGS" screen on driverApp
    Then I should be able to see default data on "SMS ALERT" page



  @regression
  Scenario: Correct data should be displayed after updating and then switching between Trip Alerts and SMS alerts tabs.
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "new driver" driver
    When I Select "TRIP ALERT SETTINGS" from driver App menu
    And I save "TRIP ALERT" settings data
    When I click "SMS ALERT" button on "TRIP ALERT SETTINGS" screen on driverApp
    And I update sms setting of "sunday" to "09:00 AM" to "12:00 PM"
    And I save "SMS ALERT" settings data
    When I click "TRIP ALERT" button on "TRIP ALERT SETTINGS" screen on driverApp
    Then previous "TRIP ALERT" data should be retained
    And I update trip setting of "sunday" to "05:00 AM" to "12:00 PM"
    When I click "SMS ALERT" button on "TRIP ALERT SETTINGS" screen on driverApp
    Then previous "SMS ALERT" data should be retained


