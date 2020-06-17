@android
Feature: DriverMenu
  In Bungii Driver
  As a logged in driver
  I want to check Trip Alert Settings

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app

  @regression
  Scenario Outline: Verify Trip Alert Settings On Trip Alerts Tab (Default:7.00AM-9.00PM)
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    And I Select "TRIP ALERT SETTINGS" from driver App menu
    And the "TRIP ALERT SETTINGS" page is opened
    And I click on "Trip Alerts" tab
    Then I should be able to see "Trip Alerts" Text and Time
    When I Select "LOGOUT" from driver App menu

    Examples:
      | Username   | Password   |
      | 8888882020 | Cci12345   |

  #@regression
  @ready
  Scenario Outline: Verify Trip Alert Settings On SMS Alerts Tab (Default:7.00AM-9.00PM)
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    And I Select "TRIP ALERT SETTINGS" from driver App menu
    And the "TRIP ALERT SETTINGS" page is opened
    And I click on "SMS Alerts" tab
    Then I should be able to see "SMS Alert" Text and Time
    Examples:
      | Username   | Password   |
      | 8989890909 | Cci12345   |


    Scenario Outline: Verify Correct Data Is Displayed In Trip And Sms Alert Settings Upon Switching Between Trip And SMS Alerts Tabs
      When I enter phoneNumber :<Username> and  Password :<Password>
      And I click "Log In" button on Log In screen on driver app
      And I Select "TRIP ALERT SETTINGS" from driver App menu
      And I click on "Trip Alerts" tab
      And I click on time and change "From" time
      And I click on "SAVE TIME" button
      And I click on "SMS Alerts" tab
      And I click on "Trip Alerts" tab
      Then I verify that changes in time are saved
      Examples:
        | Username   | Password   |
        | 8888881001 | Cci12345   |