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
  Scenario: Verify Driver FAQ Menu
    When I Select "FAQ" from driver App menu
    Then I should be navigated to "FAQ" screen
    Then I should be able to see data on "FAQ" page

  @regression
  Scenario: Verify Driver LEADERBOARD Menu
    When I Select "LEADERBOARD" from driver App menu
    Then I should be navigated to "LEADERBOARD" screen
    Then I should be able to see data on "LEADERBOARD" page


  @regression
  Scenario: Verify Driver SCHEDULED BUNGII Menu
    When I Select "SCHEDULED BUNGIIS" from driver App menu
    Then I should be navigated to "SCHEDULED BUNGII" screen
    Then I should be able to see data on "SCHEDULED BUNGIIS" page



  @regression
  Scenario:Verify Driver AVAILABLE TRIPS menu
    When I Select "AVAILABLE TRIPS" from driver App menu
    Then I should be navigated to "AVAILABLE TRIPS" screen
    Then I should be able to see data on "AVAILABLE TRIPS" page

  @regression
  Scenario: Verify Driver EARNINGS Menu
    When I Select "EARNINGS" from driver App menu
    Then I should be navigated to "EARNINGS" screen
    Then I should be able to see data on "EARNINGS" page

  @regression
  Scenario: Verify Driver ITEMIZED EARNINGS Screen
    When I Select "EARNINGS" from driver App menu
    Then I should be navigated to "EARNINGS" screen
    When I click "ITEMIZED EARNINGS" button on "Home" screen on driverApp
    Then I should be able to see data on "ITEMIZED EARNINGS" page


  @regression
  Scenario: Verify Driver ACCOUNT Menu
    When I Select "ACCOUNT" from driver App menu
    Then I should be navigated to "ACCOUNT" screen
    Then I should be able to see data on "ACCOUNT" page

  @regression
  Scenario: Verify Driver TRIP ALERT SETTINGS Menu
    When I Select "TRIP ALERT SETTINGS" from driver App menu
    Then I should be navigated to "TRIP ALERT SETTINGS" screen
    Then I should be able to see data on "TRIP ALERT SETTINGS" page

  @regression
  Scenario:Verify Driver LOGOUT Menu
    When I Select "LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    Then I should be navigated to "LOG IN" screen
  #failing due to BCKD-1103
  @ready
    @failed
  Scenario: Verify Device Token Deregistration Upon Driver Logout
    Then I driver active flag should be "1"
    When I Select "LOGOUT" from driver App menu
    Then I driver active flag should be "0"

  @regression
  Scenario: Verify Driver STORE Menu
    When I Select "STORE" from driver App menu
    Then I should be navigated to "STORE" screen
    Then I should be able to see data on "STORE" page

  @regression
  Scenario: Verify Driver FEEDBACK Menu
    When I Select "FEEDBACK" from driver App menu
    Then I should be navigated to "FEEDBACK" screen
    Then I should be able to see data on "FEEDBACK" page

@failed
  @regression
  Scenario: Verify Trip Alert Settings On Trip Alerts Tab (Default:7.00AM-9.00PM)
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "new driver" driver
    When I Select "TRIP ALERT SETTINGS" from driver App menu
    When I click "SMS ALERT" button on "TRIP ALERT SETTINGS" screen on driverApp
    Then I should be able to see default data on "SMS ALERT" page


  @FAILED2602
  @regression
  Scenario: Verify Correct Data Is Displayed In Trip And Sms Alert Settings Upon Switching Between Trip And SMS Alerts Tabs
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


