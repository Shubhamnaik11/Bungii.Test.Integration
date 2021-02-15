@android
Feature: Trip Alert Settings Menu
  In Bungii Driver
  As a logged in driver
  I want to check Trip Alert Settings
  
  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    

  @regression
  Scenario Outline: Verify Trip Alert Settings On Trip Alerts Tab [Default7.00AM-9.00PM]
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    And I Select "ALERT SETTINGS" from ACCOUNT menu
    #And I Select "ALERT SETTINGS" from driver App menu
    And the "ALERT SETTINGS" page is opened
    And I click on "Delivery Alerts" tab
    Then I should be able to see "Delivery Alerts" Text and Time
    Then I click on "Navigate Back" button on the "ALERT SETTINGS" page
    #And I Select "LOGOUT" from driver App menu
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
    
    Examples:
      | Username   | Password   |
      | 8888882020 | Cci12345   |

  #@regression
  @regression
  Scenario Outline: Verify Trip Alert Settings On SMS Alerts Tab [Default7.00AM-9.00PM]
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    And I Select "ALERT SETTINGS" from ACCOUNT menu
    #And I Select "ALERT SETTINGS" from driver App menu
    And the "ALERT SETTINGS" page is opened
    And I click on "SMS Alerts" tab
    Then I should be able to see "SMS Alerts" Text and Time
    Then I click on "Navigate Back" button on the "ALERT SETTINGS" page
    #And I Select "LOGOUT" from driver App menu
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
  
    Examples:
      | Username   | Password   |
      | 8989890909 | Cci12345   |
  
  @regression
    Scenario Outline: Verify Correct Data Is Displayed In Trip And Sms Alert Settings Upon Switching Between Trip And SMS Alerts Tabs
    When I enter phoneNumber :<Username> and  Password :<Password>
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    And I Select "ALERT SETTINGS" from ACCOUNT menu
    #And I Select "ALERT SETTINGS" from driver App menu
    And the "ALERT SETTINGS" page is opened
      And I click on "Delivery Alerts" tab
      And I click on time and change "From" time
      And I click on "SAVE TIME" button
      And I click on "SMS Alerts" tab
      And I click on "Delivery Alerts" tab
      Then I verify that changes in time are saved
      Examples:
        | Username   | Password   |
        | 8888881001 | Cci12345   |