@android
Feature: DriverMenu-Earnings
  In Bungii Driver
  As a logged in driver
  I want to check Earnings page

  Background:
    Given I am on Driver logged in Home page

  @regression
  Scenario: As Bungii driver I should be able to see the trip histoy page upon clicking Itemised earnings hyperlink
    When I Select "EARNINGS" from driver App menu
    And the "EARNINGS" page is opened
    Then I should be able to see data on "EARNINGS" page
    When I click on "Click here to view itemized earnings" hyperlink
    Then I am redirected to "Trip History Page"