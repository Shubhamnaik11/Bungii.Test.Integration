@web
Feature: Other Miscellaneous

  Background:
    Given I navigate to "Partner" portal configured for "normal" URL

  @ready
    @tes
  Scenario: Verify Partner portal login with Valid Credentials
  When I enter "valid" password on Partner Portal
  And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I Clear the browser local storage and refresh the Page
  Then I should be navigated to Login screen