@web
Feature: Other Miscellaneous

  Background:
    Given I navigate to "Partner" portal configured for "normal" URL

  @regression
    #stable
  Scenario: Verify Partner portal logout after clearing local storage
  When I enter "valid" password on Partner Portal
  And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I Clear the browser local storage and refresh the Page
  Then I should be navigated to Login screen