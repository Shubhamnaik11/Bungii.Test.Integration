@web
Feature: Driver_Details

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I click on "Login" link
    And I enter driver Phone number as "9999994028" and valid password
    And I click "LOG IN button" on driver portal

  @regression
  Scenario: Driver_Details_InvalidData
    When I click Next on "Driver Details" page
    Then I should see blank fields validation on "Driver Details" page
    When I enter "invalid" data on Driver Details page
    And I click Next on "Driver Details" page
    Then I should see individual field validations on "Driver Details" page
