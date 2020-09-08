@web
Feature: Kiosk Mode

  Background:
    Given I navigate to "Bungii Kiosk mode Partner Portal" URL

  # @gs
  Scenario: Verify login to Partner portal configured with Kiosk mode with Valid Credentials for Admin access
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I click on "Setting" Link on Kiosk Partner Portal
    And I enter "valid" password for Admin access
    And I click on "Continue" button on Kiosk Partner Portal
    Then I should "see Admin access" on Kiosk Partner Portal
    And I should logout from Kiosk Partner Portal

  #   @gs
  Scenario: Verify login to Partner portal configured with Kiosk mode with InValid Password for Admin access
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I click on "Setting" Link on Kiosk Partner Portal
    And I enter "invalid" password for Admin access
    And I click on "Continue" button on Kiosk Partner Portal
    Then I should "see validations message for incorrect password field" on Kiosk Partner Portal

    @gs
  Scenario: Verify login to Partner portal configured with Kiosk mode with Blank Password for Admin access
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I click on "Setting" Link on Kiosk Partner Portal
    And I click on "Continue" button on Kiosk Partner Portal
    Then I should "see validations message for blank password field" on Kiosk Partner Portal
