@webundertest
Feature: Driver_Documentation

  Background:
    Given I am logged in as driver
    When I click Next on "Driver Details" page
    Then I should be directed to "Pickup Info page" on Driver portal
    When I click Next on "Pickup Information" page
    Then I should be directed to "Documentation page" on Driver portal

  @reworkneeded
  Scenario: Driver_Documentation_Blank
    When I click Next on "Documentation" page
    Then I should see blank fields validation on "Documentation" page

  @reworkneeded
  Scenario: Driver_Documentation_Invalid
    When I enter "invalid date" data on Documentation page
    And I click Next on "Documentation" page
    Then I should see individual field validations on "date on Documentation" page
    When I enter "invalid" data on Documentation page
    When I click Next on "Documentation" page
    Then I should see individual field validations on "Documentation" page