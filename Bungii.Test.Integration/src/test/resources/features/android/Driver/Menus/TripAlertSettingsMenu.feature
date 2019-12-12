@android
Feature: DriverMenu
  In Bungii Driver
  As a logged in driver
  I want to check Trip Alert Settings

  Background:
    Given I am on Driver logged in Home page

  @regression
    @test1
  Scenario: Trip Alert Settings : Trip Alerts tab ( default : 7.00AM- 9.00PM)
    When I Select "TRIP ALERT SETTINGS" from driver App menu
    And the "TRIP ALERT SETTINGS" page is opened
    And I click on "Trip Alerts" tab
    Then I should be able to see "Trip Alerts" Text and Time

  @regression
  Scenario: Trip Alert Settings : SMS Alerts tab ( default: 7.00AM-9.00PM)
    When I Select "TRIP ALERT SETTINGS" from driver App menu
    And the "TRIP ALERT SETTINGS" page is opened
    And I click on "SMS Alerts" tab
    Then I should be able to see "SMS Alert" Text and Time