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

    @regression
      @test
    Scenario: To verify that Reports option is added in menu button of PP
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"
      And The dropdown menu should have an "Hamburger" icon
      When I click on the "Dropdown" button on the top right side of the page
#      Then I should see "Reports" as an option
      When I click on the "Reports" link
      Then I should see "Delivery History Report" message on the popup