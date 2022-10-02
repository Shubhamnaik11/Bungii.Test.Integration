@web
Feature: Home Outlet

  Background:
    Given I navigate to "Partner" portal configured for "Home Outlet" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"


  @testAllan
  Scenario: working title
    When I click on "Date" dropdown
    Then I check if the day is "Monday" first time slot is "9" and last time slot is "6"