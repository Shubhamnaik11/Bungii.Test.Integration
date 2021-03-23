@web
Feature: Other Miscellaneous

  Background:
    Given I navigate to "Partner" portal configured for "normal" URL

  @ready
  Scenario: Verify Partner portal login with Valid Credentials
  When I enter "valid" password on Partner Portal
  And I click "SIGN IN" button on Partner Portal
  And I click "Track Deliveries" button on Partner Portal
  #Then I should "see the trip in the Delivery List"
  And I Clear the browser local storage and refresh the Page
  Then I should be navigated to Login screen