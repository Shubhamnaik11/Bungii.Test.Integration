@webundertest
Feature: Driver_Details

  Background:
	Given I navigate to "Bungii Driver URL"
    Given I am logged in as driver


  @reworkneeded
  Scenario: Driver_Details_Blank
    When I click Next on "Driver Details" page
    Then I should see blank fields validation on "Driver Details" page

  @reworkneeded
  Scenario: Driver_Details_Invalid
    When I enter "invalid" data on Driver Details page
    And I click Next on "Driver Details" page
    Then I should see individual field validations on "Driver Details" page