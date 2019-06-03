@web
Feature: Driver_Details

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I enter "valid" details on Signup page
    And I enter "unique" driver phone number on Signup page
    And I click "Signup button" on driver portal
    Then I should be directed to "phone verification page" on Driver portal
    When I enter "correct" verification code
    And I click "Submit verification code" on driver portal
    And I should see "Logged in user name" on Driver Registration
    And I click "Continue Registration" on driver portal

  @regression
  Scenario: Driver_Details_InvalidData
    When I click Next on "Driver Details" page
    Then I should see blank fields validation on "Driver Details" page
    When I enter "invalid" data on Driver Details page
    And I click Next on "Driver Details" page
    Then I should see individual field validations on "Driver Details" page
