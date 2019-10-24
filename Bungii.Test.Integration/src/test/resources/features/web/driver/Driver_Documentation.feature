@web
Feature: Driver_Documentation

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I click on "Login" link
    And I enter driver Phone number as "9999994028" and valid password
    And I click "LOG IN button" on driver portal
    And I click Next on "Driver Details" page
    And I click Next on "Pickup Information" page

  @regression
  Scenario: Driver_Documentation_InvalidData_ExisitingNonFountainApplication
    When I click Next on "Documentation" page
    Then I should see blank fields validation on "Documentation" page
    When I enter "invalid date" data on Documentation page
    And I click Next on "Documentation" page
    Then I should see individual field validations on "date on Documentation" page
    When I enter "invalid" data on Documentation page
    When I click Next on "Documentation" page
    Then I should see individual field validations on "Documentation" page
