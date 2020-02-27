@web
Feature: Driver_Signup

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal

  @regression
  Scenario: Verify Driver Signup Invalid Data Validations
    When I enter "invalid" details on Signup page
    And I enter "invalid" driver phone number on Signup page
    And I click "Signup button" on driver portal
    Then I should see "correct field validations" on Driver Registration
    When I enter "short password" details on Signup page
    Then I should see "field validation for short password" on Driver Registration

  @regression
  Scenario: Verify Driver Signup Blank Field Validations
    When I click "Signup button" on driver portal
    Then I should see "Global validation message" on Driver Registration

  @regression
  Scenario: Verify Driver Signup Existing Phone Number Validations
    When I enter "existing" driver phone number on Signup page
    And I enter "valid" details on Signup page
    And I click "Signup button" on driver portal
    Then I should see "existing phone error" on Driver Registration