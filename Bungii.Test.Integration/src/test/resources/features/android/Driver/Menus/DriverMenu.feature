@android
Feature: DriverMenu
  In Bungii Driver
  As a logged in driver
  I want to check all menu links

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    Given I am on Driver logged in Home page

  @regression

  Scenario: Verify Driver FAQ Menu
    When I Select "FAQ" from driver App menu
    And the "FAQ" page is opened
    Then I should be able to see data on "FAQ" page

  @regression

  Scenario:  Verify Driver LEADERBOARD Menu
    When I Select "LEADERBOARD" from driver App menu
    And the "LEADERBOARD" page is opened
    Then I should be able to see data on "LEADERBOARD" page

  @regression
  Scenario:  Verify Driver SCHEDULED BUNGIIS Menu
    When I Select "SCHEDULED BUNGIIS" from driver App menu
    And the "SCHEDULED BUNGIIS" page is opened
    Then I should be able to see data on "SCHEDULED BUNGIIS" page

  @regression
  Scenario: Verify Driver AVAILABLE TRIPS Menu
    When I Select "AVAILABLE TRIPS" from driver App menu
    And the "AVAILABLE TRIPS" page is opened
    Then I should be able to see data on "AVAILABLE TRIPS" page

  @regression
  Scenario: Verify Driver EARNINGS Menu
    When I Select "EARNINGS" from driver App menu
    And the "EARNINGS" page is opened
    Then I should be able to see data on "EARNINGS" page

  @regression
  Scenario: Verify Driver ACCOUNT Menu
    When I Select "ACCOUNT" from driver App menu
    And the "ACCOUNT" page is opened
    Then I should be able to see data on "ACCOUNT" page

  @regression

  Scenario:  Verify Driver TRIP ALERT SETTINGS Menu
    When I Select "TRIP ALERT SETTINGS" from driver App menu
    And the "TRIP ALERT SETTINGS" page is opened
    Then I should be able to see data on "TRIP ALERT SETTINGS" page

  @regression
  Scenario:  Verify Driver STORE Menu
    When I Select "STORE" from driver App menu
    And the "STORE" page is opened
    Then I should be able to see data on "STORE" page

  @regression

  Scenario:  Verify Driver LOGOUT Menu
    When I Select "LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
#failing due to BCKD-1103
  @regression

  Scenario: Verify Device Token De-registration Upon Driver Logout
    Then Driver active flag should be "1"
    When I Select "LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    Then Driver active flag should be "0"

  @regression
  Scenario: Verify Driver Can Access Trip Histoy Page Upon Clicking Itemised Earnings Hyperlink
    When I Select "EARNINGS" from driver App menu
    And the "EARNINGS" page is opened
    Then I should be able to see data on "EARNINGS" page
    When I click on "Click here to view itemized earnings" hyperlink
    Then I am redirected to "Trip History Page"